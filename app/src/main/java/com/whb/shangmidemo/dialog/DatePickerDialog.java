package com.whb.shangmidemo.dialog;

import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.whb.shangmidemo.R;
import com.whb.shangmidemo.adapter.DateDaysAdapter;
import com.whb.shangmidemo.adapter.DateMonthAdapter;
import com.whb.shangmidemo.adapter.DateWeekAdapter;
import com.whb.shangmidemo.databinding.DatepickerBinding;
import com.whb.shangmidemo.databinding.FragmentFirstBinding;
import com.whb.shangmidemo.entity.DateDayPickerBean;
import com.whb.shangmidemo.entity.DateMonthPickerBean;
import com.whb.shangmidemo.entity.YearMonthBean;
import com.whb.shangmidemo.event.EventBusMsgType;
import com.whb.shangmidemo.event.MessageEvent;
import com.whb.shangmidemo.utils.DateUtils;
import com.whb.shangmidemo.utils.GridSpaceItemDecoration;
import com.whb.shangmidemo.utils.HorizontalSpacingItemDecoration;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class DatePickerDialog extends DialogFragment {

    private DatepickerBinding binding;

    private DateMonthAdapter dateMonthAdapter;

    private List<DateDayPickerBean> dateDayPickerBeanList;

    private List<DateMonthPickerBean> monthList;

    private List<Integer> selectDayList;

    private DateMonthPickerBean monthPickerBean;

    public static DatePickerDialog getInstance() {
        return new DatePickerDialog();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DatepickerBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Window window = requireDialog().getWindow();
        window.setBackgroundDrawableResource(R.color.lucency);
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.CENTER;
        wlp.width = (int) (requireActivity().getResources().getDisplayMetrics().widthPixels * 0.3);
        wlp.height = (int) (requireActivity().getResources().getDisplayMetrics().heightPixels * 0.5);
        window.setAttributes(wlp);

        EventBus.getDefault().register(this);

        DateWeekAdapter dateWeekAdapter = new DateWeekAdapter();
        dateWeekAdapter.setNewInstance(DateUtils.getWeeks());
//        binding.lvWeeks.addItemDecoration(new HorizontalSpacingItemDecoration(18));
//        binding.lvWeeks.setLayoutManager(new LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false));
        binding.lvWeeks.setLayoutManager(new GridLayoutManager(requireActivity(), 7, LinearLayoutManager.VERTICAL, false));
        binding.lvWeeks.setAdapter(dateWeekAdapter);

        dateMonthAdapter = new DateMonthAdapter();
        binding.rvMonthPicker.addItemDecoration(new HorizontalSpacingItemDecoration(25, 10));
        binding.rvMonthPicker.setLayoutManager(new LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false));
        binding.rvMonthPicker.setAdapter(dateMonthAdapter);
        monthList = getYearMonthList();

        // 创建 Calendar 实例
        Calendar calendar = Calendar.getInstance();

        // 获取当前年份
        int year = calendar.get(Calendar.YEAR);

        // 获取当前月份（注意：月份从 0 开始，所以要加 1）
        int month = calendar.get(Calendar.MONTH) + 1;
        int daysInMonth = DateUtils.getDaysInMonth(year, month);
        dateDayPickerBeanList = DateUtils.generateDayList(daysInMonth, DateUtils.getEmptyDaysInMonth(year, month));

        Optional<DateMonthPickerBean> optionalMonthPickerBean = monthList.stream()
                .filter(pickerBean -> pickerBean.getYear() == year && pickerBean.getMonth() == month)
                .findFirst();

        optionalMonthPickerBean.ifPresent(pickerBean -> {
            pickerBean.setDateDayPickerBean(dateDayPickerBeanList);
            monthPickerBean = pickerBean;
        });

        dateMonthAdapter.setNewInstance(monthList);

        //自动定位到当月
        int lastIndex = dateMonthAdapter.getData().size() - 1;
        binding.rvMonthPicker.smoothScrollToPosition(lastIndex);

        binding.tvCancel.setOnClickListener(v -> dismiss());
        binding.tvConfirm.setOnClickListener(v -> {
            Toast.makeText(requireActivity(), "选择的日期为：" + selectDayList, Toast.LENGTH_SHORT).show();
            dismiss();
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        if (event.getEventBusMsgType().equals(EventBusMsgType.DATE_PICKER_EVENT)) {
            selectDayList = (List<Integer>) event.getMsg();
            if (!selectDayList.isEmpty() && !dateDayPickerBeanList.isEmpty()) {
                monthList.stream()
                        .filter(dateMonthPickerBean ->
                                Objects.equals(dateMonthPickerBean.getYear(), monthPickerBean.getYear()) &&
                                        Objects.equals(dateMonthPickerBean.getMonth(), monthPickerBean.getMonth()))
                        .findFirst()
                        .ifPresent(dateMonthPickerBean -> {
                            dateMonthPickerBean.getDateDayPickerBean().forEach(dateDayPickerBean -> {
                                if (selectDayList.contains(dateDayPickerBean.getDataDay())) {
                                    dateDayPickerBean.setSelectList(selectDayList);
                                }
                            });
                            dateMonthPickerBean.setDateDayPickerBean(dateDayPickerBeanList);
                            dateMonthAdapter.setList(monthList);
                        });
            }

            return;
        }

        if (event.getEventBusMsgType().equals(EventBusMsgType.DATE_PICKER_EVENT_MONTH)) {
            monthPickerBean = (DateMonthPickerBean) event.getMsg();
            if (monthPickerBean.getDateDayPickerBean() != null && !monthPickerBean.getDateDayPickerBean().isEmpty()) {
                dateDayPickerBeanList = monthPickerBean.getDateDayPickerBean();
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(requireActivity());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private List<DateMonthPickerBean> getYearMonthList() {
        List<YearMonth> lastTwoYears = DateUtils.getLastNMonths(24);

        return lastTwoYears.stream()
                .map(yearMonth -> {
                    DateMonthPickerBean dateMonthPickerBean = new DateMonthPickerBean();
                    dateMonthPickerBean.setYear(yearMonth.getYear());
                    dateMonthPickerBean.setMonth(yearMonth.getMonthValue());
                    dateMonthPickerBean.setMonthStr(monthStr(yearMonth.getMonthValue()));
                    return dateMonthPickerBean;
                })
                .sorted(Comparator.comparing(DateMonthPickerBean::getYear).thenComparing(DateMonthPickerBean::getMonth))
                .collect(Collectors.toList());
    }

    private String monthStr(int key) {
        Map<Integer, String> monthMap = new HashMap<>();
        monthMap.put(1, "一月");
        monthMap.put(2, "二月");
        monthMap.put(3, "三月");
        monthMap.put(4, "四月");
        monthMap.put(5, "五月");
        monthMap.put(6, "六月");
        monthMap.put(7, "七月");
        monthMap.put(8, "八月");
        monthMap.put(9, "九月");
        monthMap.put(10, "十月");
        monthMap.put(11, "十一月");
        monthMap.put(12, "十二月");
        return monthMap.get(key);
    }
}
