package com.whb.shangmidemo.adapter;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.whb.shangmidemo.R;
import com.whb.shangmidemo.databinding.LvDaysItemBinding;
import com.whb.shangmidemo.databinding.LvWeekItemBinding;

public class DateWeekAdapter extends BaseQuickAdapter<String, BaseDataBindingHolder<LvWeekItemBinding>> {
    public DateWeekAdapter() {
        super(R.layout.lv_week_item);
    }

    @Override
    protected void convert(@NonNull BaseDataBindingHolder<LvWeekItemBinding> bindingHolder, String s) {
        LvWeekItemBinding binding = bindingHolder.getDataBinding();
        if (binding != null) {
            binding.tvWeek.setText(s);
        }

    }
}
