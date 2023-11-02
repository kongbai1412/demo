package com.whb.shangmidemo;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import cn.bingoogolapple.badgeview.BGABadgeViewHelper;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.google.android.material.tabs.TabLayout;
import com.whb.shangmidemo.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import me.jessyan.autosize.internal.CustomAdapt;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CustomAdapt {
    
    private ActivityMainBinding binding;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        
        // 添加默认的Fragment到FrameLayout
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.nav_host_fragment_content, new FirstFragment()).commit();
        }
        
        initTab();
        initSidebar();
    }
    
    private void initSidebar() {
        binding.bgCall.showTextBadge("99+");
        binding.btnCallNumber.setOnClickListener(v -> {
            Snackbar.make(v, "叫号", Snackbar.LENGTH_SHORT).show();
        });
        binding.ivMainIcon.setOnClickListener(v -> {
            Snackbar.make(v, "设置", Snackbar.LENGTH_SHORT).show();
        });
        binding.ivUser.setOnClickListener(v -> {
            Snackbar.make(v, "用户", Snackbar.LENGTH_SHORT).show();
        });
        binding.ivWallet.setOnClickListener(v -> {
            Snackbar.make(v, "对账", Snackbar.LENGTH_SHORT).show();
        });
        binding.btnCommodity.setOnClickListener(v -> {
            Snackbar.make(v, "商品", Snackbar.LENGTH_SHORT).show();
        });
    }
    
    private void initTab() {
        List<String> tabList = tab();
        for (int i = 0; i < tabList.size(); i++) {
            binding.tlMain.addTab(binding.tlMain.newTab().setText(tabList.get(i)));
        }
        
        ViewGroup tabs = (ViewGroup) binding.tlMain.getChildAt(0);
        for (int i = 0; i < tabs.getChildCount() - 1; i++) {
            View tab = tabs.getChildAt(i);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) tab.getLayoutParams();
            layoutParams.weight = 0;
            layoutParams.setMarginEnd(50);
            layoutParams.setMarginEnd(50);
            layoutParams.width = 350;
            tab.setLayoutParams(layoutParams);
            binding.tlMain.requestLayout();
        }
        
        binding.tlMain.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Toast.makeText(getApplicationContext(), "点击了" + tab.getText(), Toast.LENGTH_SHORT).show();
            }
            
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            
            }
            
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            
            }
        });
    }
    
    @Override
    public boolean isBaseOnWidth() {
        return true;
    }
    
    @Override
    public float getSizeInDp() {
        return 1920;
    }
    
    private List<String> tab() {
        List<String> tab = new ArrayList<>();
        tab.add("营业报表");
        tab.add("商业报表");
        tab.add("日结");
        tab.add("三方券");
        tab.add("交接班");
        tab.add("货价签");
        tab.add("商品");
        return tab;
    }
}