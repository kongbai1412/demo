package com.whb.shangmidemo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.applandeo.materialcalendarview.DatePicker;
import com.applandeo.materialcalendarview.utils.CalendarProperties;
import com.whb.shangmidemo.adapter.BusinessFirstAdapter;
import com.whb.shangmidemo.databinding.FragmentFirstBinding;
import com.whb.shangmidemo.entity.BusinessFirstBean;
import com.whb.shangmidemo.utils.GridSpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class FirstFragment extends Fragment {
    
    private FragmentFirstBinding binding;

    private BusinessFirstAdapter businessFirstAdapter;
    
    private DatePicker datePicker;
    
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentFirstBinding.inflate(inflater, container, false);
        initButton();
        initIncomeDate();
        initAdapter();

        return binding.getRoot();
    }

    private void initAdapter() {
        businessFirstAdapter = new BusinessFirstAdapter();
        binding.rvStatement.setLayoutManager(new GridLayoutManager(requireActivity(), 4));
        binding.rvStatement.addItemDecoration(
                new GridSpaceItemDecoration(30, 40));
        binding.rvStatement.setAdapter(businessFirstAdapter);
        businessFirstAdapter.setNewInstance(initTestData());
    }

    /**
     * 所有button事件
     */
    private void initButton() {
        binding.btnToday.setOnClickListener(
                view -> Toast.makeText(requireActivity(), "点击了今天", Toast.LENGTH_SHORT).show());
        binding.btnYesterday.setOnClickListener(
                view -> Toast.makeText(requireActivity(), "点击了昨天", Toast.LENGTH_SHORT).show());
        binding.btnLastWeek.setOnClickListener(
                view -> Toast.makeText(requireActivity(), "点击了上周", Toast.LENGTH_SHORT).show());
        binding.btnWeek.setOnClickListener(
                view -> Toast.makeText(requireActivity(), "点击了本周", Toast.LENGTH_SHORT).show());
        binding.btnSevenDay.setOnClickListener(
                view -> Toast.makeText(requireActivity(), "点击了近7天", Toast.LENGTH_SHORT).show());
        binding.btnPastThirtyDays.setOnClickListener(
                view -> Toast.makeText(requireActivity(), "点击了过去30天", Toast.LENGTH_SHORT).show());
        binding.btnMonth.setOnClickListener(
                view -> Toast.makeText(requireActivity(), "点击了本月", Toast.LENGTH_SHORT).show());
        binding.btnLastMonth.setOnClickListener(
                view -> Toast.makeText(requireActivity(), "点击了上月", Toast.LENGTH_SHORT).show());
        binding.btnCustomize.setOnClickListener(view -> {
            initDatePicker();
            
//            datePicker.show();
        });
    }

    /***
     * 总营业额
     * 营业收入
     * */
    private void initIncomeDate() {
        //总营业额
        binding.tvPecuniary.setText("1258209.02");
        //环比上一区间
        binding.tvSectionText.setText("+47.23%");
        //营业收入
        binding.tvIncomePecuniary.setText("1080893.83");
        //环比上一区间
        binding.tvIncomeSectionText.setText("+45.41%");
        //更新时间
        binding.tvUpdateTime.setText("更新时间：2021-09-15 09:21:00");
        //切换至老版
        binding.tvSkipStatement.setText("切换至老版营业报表 >");
    }
    
    private void initDatePicker() {
//        datePicker = new DatePicker(requireActivity());
//        datePicker.getWheelLayout().setResetWhenLinkage(false);
//        datePicker.setOnDatePickedListener(this);
//        CalendarView calendarView = new CalendarView(requireActivity());
//        calendarView.showCurrentMonthPage();
        DatePicker picker = new DatePicker(requireActivity(), new CalendarProperties(requireActivity()));
        picker.show();
    }

    private List<BusinessFirstBean> initTestData() {
        List<BusinessFirstBean> list = new ArrayList<>();
        list.add(new BusinessFirstBean("支出金额(元)", "177315.17", 60.08f));
        list.add(new BusinessFirstBean("有效订单数(笔)", "29889", 49.25f));
        list.add(new BusinessFirstBean("折前单均价(元)", "42.10", -3.57f));
        list.add(new BusinessFirstBean("折后单均价(元)", "36.16", -2.20f));
        list.add(new BusinessFirstBean("支出金额(元)", "177315.17", 60.08f));
        list.add(new BusinessFirstBean("有效订单数(笔)", "29889", 49.25f));
        list.add(new BusinessFirstBean("折前单均价(元)", "42.10", 3.57f));
        list.add(new BusinessFirstBean("折后单均价(元)", "36.16", 2.20f));
        return list;
    }
    
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    
}