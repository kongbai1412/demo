package com.whb.shangmidemo.adapter;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.whb.shangmidemo.R;
import com.whb.shangmidemo.databinding.SecondItemBinding;
import com.whb.shangmidemo.entity.BusinessSecondBean;

import java.util.Locale;

public class BusinessSecondAdapter extends BaseQuickAdapter<BusinessSecondBean, BaseDataBindingHolder<SecondItemBinding>>{


    public BusinessSecondAdapter() {
        super(R.layout.second_item);
    }

    @Override
    protected void convert(@NonNull BaseDataBindingHolder<SecondItemBinding> bindingHolder, BusinessSecondBean businessSecondBean) {
        SecondItemBinding binding = bindingHolder.getDataBinding();
        if (binding != null) {
            binding.tvSecondItemTitle.setText(String.format("%s(元)", businessSecondBean.getName()));
            binding.tvSecondItemMoney.setText(businessSecondBean.getMoney());

            Integer numerical = businessSecondBean.getNumerical();
            if (numerical != null && numerical < 0) {
                binding.tvSecondItemSection.setTextColor(getContext().getResources().getColor(R.color.green, getContext().getTheme()));
                binding.tvSecondItemTendency.setImageDrawable(ResourcesCompat.getDrawable(getContext().getResources(), R.drawable.decline, getContext().getTheme()));
            } else {
                binding.tvSecondItemSection.setTextColor(getContext().getResources().getColor(R.color.red, getContext().getTheme()));
                binding.tvSecondItemTendency.setImageDrawable(ResourcesCompat.getDrawable(getContext().getResources(), R.drawable.arrow, getContext().getTheme()));
            }

            binding.tvSecondItemSection.setText(String.format(Locale.getDefault(),"%d%%", businessSecondBean.getNumerical()));
        }

    }
}
