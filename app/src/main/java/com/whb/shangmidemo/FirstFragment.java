package com.whb.shangmidemo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.DatePicker;
import com.applandeo.materialcalendarview.builders.DatePickerBuilder;
import com.applandeo.materialcalendarview.listeners.OnSelectDateListener;
import com.whb.shangmidemo.adapter.BusinessFirstAdapter;
import com.whb.shangmidemo.databinding.FragmentFirstBinding;
import com.whb.shangmidemo.entity.BusinessFirstBean;
import com.whb.shangmidemo.utils.GridSpaceItemDecoration;
import me.jessyan.autosize.internal.CustomAdapt;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class FirstFragment extends Fragment implements CustomAdapt {
    
    private FragmentFirstBinding binding;
    
    private BusinessFirstAdapter businessFirstAdapter;
    
    private DatePicker datePicker;
    
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        binding = FragmentFirstBinding.inflate(inflater, container, false);
        initButton();
        initIncomeDate();
        initAdapter();
        initDatePicker();
        initSpinner();
        return binding.getRoot();
    }
    
    /***
     * 财务报表
     * */
    private void initAdapter() {
        businessFirstAdapter = new BusinessFirstAdapter();
        binding.rvStatement.setLayoutManager(new GridLayoutManager(requireActivity(), 4));
        binding.rvStatement.addItemDecoration(new GridSpaceItemDecoration(30, 40));
        binding.rvStatement.setAdapter(businessFirstAdapter);
        businessFirstAdapter.setNewInstance(initTestData());
        
        binding.lvPullDown.setOnClickListener(v -> {
            if (businessFirstAdapter.getData().size() > 4) {
                businessFirstAdapter.setNewInstance(initTestData(4));
            } else {
                businessFirstAdapter.setNewInstance(initTestData());
            }
        });
    }
    
    /***
     * 选择列表
     * */
    private void initSpinner() {
        List<String> list = new ArrayList<>();
        list.add("今天");
        list.add("昨天");
        list.add("上周");
        list.add("本周");
        list.add("近7天");
        list.add("过去30天");
        list.add("本月");
        list.add("上月");
        list.add("自定义");
        
        SpinnerAdapter adapter = new ArrayAdapter<>(requireActivity(), R.layout.select_serial_item, list);
        binding.spSelect.setAdapter(adapter);
        binding.spSelect.setSelection(list.indexOf("今天"), true);
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
        binding.btnPrint.setOnClickListener(
                view -> Toast.makeText(requireActivity(), "点击了打印", Toast.LENGTH_SHORT).show());
        binding.btnCustomize.setOnClickListener(view -> {
            datePicker.show();
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
        OnSelectDateListener onSelectDateListener = new OnSelectDateListener() {
            List<String> dates = new ArrayList<>();
            
            @Override
            public void onSelect(List<Calendar> calendar) {
                for (Calendar cal : calendar) {
                    dates.add(cal.getTime().toString());
                }
                
                Toast.makeText(requireActivity(), "点击了" + dates, Toast.LENGTH_SHORT).show();
            }
        };
        
        DatePickerBuilder builder = new DatePickerBuilder(requireActivity(),
                onSelectDateListener).setPickerType(CalendarView.RANGE_PICKER);
        
        datePicker = builder.build();
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
    
    private List<BusinessFirstBean> initTestData(Integer count) {
        List<BusinessFirstBean> list = new ArrayList<>();
        list.add(new BusinessFirstBean("支出金额(元)", "177315.17", 60.08f));
        list.add(new BusinessFirstBean("有效订单数(笔)", "29889", 49.25f));
        list.add(new BusinessFirstBean("折前单均价(元)", "42.10", -3.57f));
        list.add(new BusinessFirstBean("折后单均价(元)", "36.16", -2.20f));
        return list;
    }
    
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        datePicker = null;
        binding = null;
    }
    
    @Override
    public boolean isBaseOnWidth() {
        return true;
    }
    
    @Override
    public float getSizeInDp() {
        return 1920;
    }
}