package com.whb.shangmidemo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.DatePicker;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import com.whb.shangmidemo.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment {
    
    private FragmentFirstBinding binding;
    
    private DatePicker datePicker;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentFirstBinding.inflate(inflater, container, false);
        initButton();
        return binding.getRoot();
    }
    
    /**
     * 所有button事件
     */
    private void initButton() {
        binding.btnToday.setOnClickListener(
                view -> Toast.makeText(requireActivity(), "点击了今天", Toast.LENGTH_SHORT));
        binding.btnYesterday.setOnClickListener(
                view -> Toast.makeText(requireActivity(), "点击了昨天", Toast.LENGTH_SHORT));
        binding.btnLastWeek.setOnClickListener(
                view -> Toast.makeText(requireActivity(), "点击了上周", Toast.LENGTH_SHORT));
        binding.btnWeek.setOnClickListener(
                view -> Toast.makeText(requireActivity(), "点击了本周", Toast.LENGTH_SHORT));
        binding.btnSevenDay.setOnClickListener(
                view -> Toast.makeText(requireActivity(), "点击了近7天", Toast.LENGTH_SHORT));
        binding.btnPastThirtyDays.setOnClickListener(
                view -> Toast.makeText(requireActivity(), "点击了过去30天", Toast.LENGTH_SHORT));
        binding.btnMonth.setOnClickListener(
                view -> Toast.makeText(requireActivity(), "点击了本月", Toast.LENGTH_SHORT));
        binding.btnLastMonth.setOnClickListener(
                view -> Toast.makeText(requireActivity(), "点击了上月", Toast.LENGTH_SHORT));
        binding.btnCustomize.setOnClickListener(view -> {
           
            
            datePicker.show();
        });
    }
    
    private void initDatePicker() {
        datePicker = new DatePicker(requireActivity());
//        datePicker.getWheelLayout().setResetWhenLinkage(false);
//        datePicker.setOnDatePickedListener(this);
    }
    
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    
}