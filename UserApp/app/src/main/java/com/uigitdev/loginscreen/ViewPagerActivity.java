package com.uigitdev.loginscreen;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import com.uigitdev.loginscreen.adapter.ViewPagerAdapter;
import com.uigitdev.loginscreen.model.ItemObj;
import com.uigitdev.loginscreen.transformer.SwipeTransform;
import com.uigitdev.loginscreen.viewmodel.ItemView;

import java.util.ArrayList;

public class ViewPagerActivity extends AppCompatActivity implements ItemView.ItemArrowInterface {
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    private ArrayList<ItemObj> arrayList;
    private RelativeLayout parent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        viewPager = findViewById(R.id.viewPager);
        parent = findViewById(R.id.parent);
        initData();
        changeBackground(arrayList.get(0).getAccentColor());

        adapter = new ViewPagerAdapter(this, arrayList, ViewPagerActivity.this);
        viewPager.setPageTransformer(true, new SwipeTransform());
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                changeBackground(arrayList.get(position).getAccentColor());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initData() {
        arrayList = new ArrayList<>();

        arrayList.add(new ItemObj("https://user-images.githubusercontent.com/46577836/67385323-3a61db00-f593-11e9-97c6-5f961750e1ec.png", "#5B37B7", "Kedai 1"));
        arrayList.add(new ItemObj("https://user-images.githubusercontent.com/46577836/67610725-f807ec00-f794-11e9-87c2-dfb3633814b5.png", "#A1379D", "Kedai 2"));
        arrayList.add(new ItemObj("https://user-images.githubusercontent.com/46577836/66873962-14907100-efaa-11e9-9b2b-115c268a7327.png", "#0ABF53", "Kedai 3"));
        arrayList.add(new ItemObj("https://user-images.githubusercontent.com/46577836/66201795-6977e180-e6a4-11e9-8019-89a1a1dca44b.png", "#FFD000", "Kedai 4"));
    }

    private void changeBackground(String color) {
        parent.setBackgroundColor(Color.parseColor(color));
        changeStatusBarColor(color);
    }

    private void changeStatusBarColor(String color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor(color));
        }
    }

    @Override
    public void scrollNextPosition(int position) {
        viewPager.setCurrentItem(position, true);
    }
}
