package com.whb.shangmidemo.adapter;

import android.annotation.SuppressLint;
import android.view.View;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.whb.shangmidemo.R;
import com.whb.shangmidemo.databinding.LvDaysItemBinding;
import com.whb.shangmidemo.entity.DateDayPickerBean;
import com.whb.shangmidemo.event.EventBusMsgType;
import com.whb.shangmidemo.event.MessageEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class DateDaysAdapter extends BaseQuickAdapter<DateDayPickerBean, BaseDataBindingHolder<LvDaysItemBinding>> {

    private final TreeSet<Integer> integerSet = new TreeSet<>();

    public DateDaysAdapter() {
        super(R.layout.lv_days_item);
    }

    @Override
    protected void convert(@NonNull BaseDataBindingHolder<LvDaysItemBinding> bindingHolder, DateDayPickerBean bean) {
        LvDaysItemBinding binding = bindingHolder.getDataBinding();
        if (binding != null) {
            if (bean.getDataDay() == 0) {
                binding.tvDays.setText("");
                binding.tvDays.setVisibility(View.INVISIBLE);
                return;
            }

            binding.tvDays.setText(String.valueOf(bean.getDataDay()));

            binding.tvDays.setOnClickListener(v -> {
                if (integerSet.contains(bean.getDataDay())) {
                    binding.tvDays.setSelected(false);
                    integerSet.remove(bean.getDataDay());
                    binding.tvDays.setTextColor(getContext().getResources().getColor(R.color.black111, getContext().getTheme()));
                    binding.tvDays.setBackgroundResource(R.color.lucency);
                } else {
                    binding.tvDays.setSelected(true);
                    integerSet.add(bean.getDataDay());
                }

                List<Integer> range = selectionRange();
                if (!range.isEmpty()) {
                    bean.setSelectList(range);
                    EventBus.getDefault().post(new MessageEvent(EventBusMsgType.DATE_PICKER_EVENT, range));
                }
            });

            if (bean.getSelectList() != null && !bean.getSelectList().isEmpty()) {
                integerSet.addAll(bean.getSelectList());
                notifySelect(binding, bean, new ArrayList<>(integerSet));
            }
        }

    }

    /**
     * 计算选择范围
     */
    private List<Integer> selectionRange() {
        List<Integer> list = new ArrayList<>(integerSet);

        if (list.size() <= 1) {
            return list;
        }

        TreeSet<Integer> set = new TreeSet<>(list); // 去重并排序
        int start = set.first();
        int end = set.last();

        if (end - start == set.size() - 1) {
            return new ArrayList<>(set); // 连续的范围
        }

        // 不连续的范围
        List<Integer> rangeList = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            rangeList.add(i);
        }

        return rangeList;
    }


    @SuppressLint("NotifyDataSetChanged")
    private void notifySelect(LvDaysItemBinding binding, DateDayPickerBean bean, List<Integer> selects) {
        if (selects.isEmpty()) {
            return;
        }

        if (selects.size() == 1) {
            binding.tvDays.setTextColor(getContext().getResources().getColor(R.color.white, getContext().getTheme()));
            binding.tvDays.setBackgroundDrawable(getContext().getResources().getDrawable(R.drawable.select_data_start_blue, getContext().getTheme()));
            return;
        }

        int dataDay = bean.getDataDay();
        int selectSize = selects.size();

        // 检查是否是起始日期
        boolean isStartDay = selectSize > 1 && selects.get(0) == dataDay;

        // 检查是否是结束日期
        boolean isEndDay = selectSize > 1 && selects.get(selectSize - 1) == dataDay;

        if (isStartDay) {
            binding.tvDays.setTextColor(getContext().getResources().getColor(R.color.white, getContext().getTheme()));
            binding.tvDays.setBackgroundDrawable(getContext().getResources().getDrawable(R.drawable.select_data_start_blue, getContext().getTheme()));
        } else if (isEndDay) {
            binding.tvDays.setTextColor(getContext().getResources().getColor(R.color.white, getContext().getTheme()));
            binding.tvDays.setBackgroundDrawable(getContext().getResources().getDrawable(R.drawable.select_data_end_blue, getContext().getTheme()));
        } else if (selectSize > 2) {
            // 检查是否是中间日期
            if (selects.subList(1, selectSize - 1).contains(dataDay)) {
                binding.tvDays.setTextColor(getContext().getResources().getColor(R.color.white, getContext().getTheme()));
                binding.tvDays.setBackgroundDrawable(getContext().getResources().getDrawable(R.drawable.select_data_middle_blue, getContext().getTheme()));
            }
        }
    }

}
