package com.whb.shangmidemo.adapter;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.whb.shangmidemo.R;
import com.whb.shangmidemo.databinding.FirstItemBinding;
import com.whb.shangmidemo.entity.BusinessFirstBean;

import java.util.Locale;

public class BusinessFirstAdapter extends BaseQuickAdapter<BusinessFirstBean, BaseDataBindingHolder<FirstItemBinding>> {

    public BusinessFirstAdapter() {
        super(R.layout.first_item);
    }

    @Override
    protected void convert(@NonNull BaseDataBindingHolder<FirstItemBinding> bindingHolder, BusinessFirstBean businessFirstBean) {

        FirstItemBinding binding = bindingHolder.getDataBinding();
        if (binding != null) {
            binding.tvBusinessName.setText(String.format("%s(元)", businessFirstBean.getTitle()));
            binding.tvPecuniary.setText(businessFirstBean.getMoney());

            if (businessFirstBean.getNumerical() < 0) {
                binding.tvSectionText.setTextColor(getContext().getResources().getColor(R.color.green, getContext().getTheme()));
                binding.ivTendency.setImageDrawable(ResourcesCompat.getDrawable(getContext().getResources(), R.drawable.decline, getContext().getTheme()));
            } else {
                binding.tvSectionText.setTextColor(getContext().getResources().getColor(R.color.red, getContext().getTheme()));
                binding.ivTendency.setImageDrawable(ResourcesCompat.getDrawable(getContext().getResources(), R.drawable.arrow, getContext().getTheme()));
            }

            binding.tvSectionText.setText(String.format(Locale.getDefault(), "%d%%", businessFirstBean.getNumerical()));
        }

    }
}
