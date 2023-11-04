package com.whb.shangmidemo.adapter;

import android.icu.util.Calendar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.whb.shangmidemo.R;
import com.whb.shangmidemo.databinding.LvMonthItemBinding;
import com.whb.shangmidemo.entity.DateDayPickerBean;
import com.whb.shangmidemo.entity.DateMonthPickerBean;
import com.whb.shangmidemo.entity.YearMonthBean;
import com.whb.shangmidemo.event.EventBusMsgType;
import com.whb.shangmidemo.event.MessageEvent;
import com.whb.shangmidemo.utils.DateUtils;
import com.whb.shangmidemo.utils.GridSpaceItemDecoration;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class DateMonthAdapter extends BaseQuickAdapter<DateMonthPickerBean, BaseDataBindingHolder<LvMonthItemBinding>> {
    public DateMonthAdapter() {
        super(R.layout.lv_month_item);
    }
    @Override
    protected void convert(@NonNull BaseDataBindingHolder<LvMonthItemBinding> bindingHolder, DateMonthPickerBean bean) {
        LvMonthItemBinding binding = bindingHolder.getDataBinding();
        if (binding != null) {
            String s = bean.getMonthStr() + "  " + bean.getYear();
            binding.tvMonth.setText(s);

            DateDaysAdapter dateDaysAdapter = new DateDaysAdapter();
            binding.rvDays.setLayoutManager(new GridLayoutManager(getContext(), 7, LinearLayoutManager.VERTICAL, false));
            binding.rvDays.setAdapter(dateDaysAdapter);

            binding.tvMonth.setOnClickListener(v -> {
                if (!dateDaysAdapter.getData().isEmpty()) {
                    dateDaysAdapter.setNewInstance(null);
                    EventBus.getDefault().post(new MessageEvent(EventBusMsgType.DATE_PICKER_EVENT_MONTH, ""));
                    return;
                }

                int daysInMonth = DateUtils.getDaysInMonth(bean.getYear(), bean.getMonth());
                List<DateDayPickerBean> dayList = DateUtils.generateDayList(daysInMonth, DateUtils.getEmptyDaysInMonth(bean.getYear(), bean.getMonth()));
                bean.setDateDayPickerBean(dayList);
                dateDaysAdapter.setNewInstance(dayList);
                EventBus.getDefault().post(new MessageEvent(EventBusMsgType.DATE_PICKER_EVENT_MONTH, bean));
            });

            if (bean.getDateDayPickerBean() != null && !bean.getDateDayPickerBean().isEmpty()) {
                dateDaysAdapter.setNewInstance(bean.getDateDayPickerBean());
            }
        }

    }



}
