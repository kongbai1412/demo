package com.whb.shangmidemo.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.whb.shangmidemo.R;
import com.whb.shangmidemo.databinding.SecondItemBinding;
import com.whb.shangmidemo.entity.BusinessFirstBean;

import java.util.Locale;

public class BusinessFirstAdapter extends BaseQuickAdapter<BusinessFirstBean, BaseDataBindingHolder<SecondItemBinding>>{


    public BusinessFirstAdapter() {
        super(R.layout.second_item);
    }

    @Override
    protected void convert(@NonNull BaseDataBindingHolder<SecondItemBinding> bindingHolder, BusinessFirstBean businessSecondBean) {
        SecondItemBinding binding = bindingHolder.getDataBinding();
        if (binding != null) {
            binding.tvSecondItemTitle.setText(String.format("%s", businessSecondBean.getTitle()));
            binding.tvSecondItemMoney.setText(businessSecondBean.getMoney());

            Float numerical = businessSecondBean.getNumerical();
            if (numerical != null && numerical < 0) {
                binding.tvSecondItemSection.setTextColor(getContext().getResources().getColor(R.color.green, getContext().getTheme()));
                binding.tvSecondItemTendency.setImageDrawable(ResourcesCompat.getDrawable(getContext().getResources(), R.drawable.decline, getContext().getTheme()));
            } else {
                binding.tvSecondItemSection.setTextColor(getContext().getResources().getColor(R.color.red, getContext().getTheme()));
                binding.tvSecondItemTendency.setImageDrawable(ResourcesCompat.getDrawable(getContext().getResources(), R.drawable.arrow, getContext().getTheme()));
            }

            binding.tvSecondItemSection.setText(String.format(Locale.getDefault(),"%f%%", businessSecondBean.getNumerical()));
        }

    }
}
