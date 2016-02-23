package com.richerpay.videoview.moocguide;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.richerpay.videoview.moocguide.fragmnet.GuideFragment;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewPager viewpager;
    private FragmentPagerAdapter mAdapter;
    private List<Fragment> mFragments;
    private ImageView[] images;
    private GuideFragment mTab01;
    private GuideFragment mTab02;
    private GuideFragment mTab03;
    private TextView login, enter;
    private LinearLayout button_layout, dot_layout;
    private ObjectAnimator mAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //   requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initView();

        setSelect(0);
        initDot(0);
    }

    private void initEvent() {
        login.setOnClickListener(this);
        enter.setOnClickListener(this);
    }


    private void initView() {
        login = (TextView) findViewById(R.id.login);
        enter = (TextView) findViewById(R.id.enter);
        button_layout = (LinearLayout) findViewById(R.id.button_layout);
        dot_layout = (LinearLayout) findViewById(R.id.dot_layout);
        viewpager = (ViewPager) findViewById(R.id.id_viewpager);
        mFragments = new ArrayList<Fragment>();
        Bundle bundel = new Bundle();
        mTab01 = new GuideFragment();
        bundel.putInt("index", 1);
        mTab01.setArguments(bundel);
        mTab02 = new GuideFragment();
        Bundle bunde2 = new Bundle();
        bunde2.putInt("index", 2);
        mTab02.setArguments(bunde2);
        mTab03 = new GuideFragment();
        Bundle bunde3 = new Bundle();
        bunde3.putInt("index", 3);
        mTab03.setArguments(bunde3);
        mFragments.add(mTab01);
        mFragments.add(mTab02);
        mFragments.add(mTab03);

        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {

            @Override
            public int getCount() {
                // TODO Auto-generated method stub
                return mFragments.size();
            }

            @Override
            public Fragment getItem(int arg0) {
                // TODO Auto-generated method stub
                return mFragments.get(arg0);
            }
        };

        viewpager.setAdapter(mAdapter);

        viewpager.setOnPageChangeListener(new android.support.v4.view.ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int pos) {
                int currentItem = viewpager.getCurrentItem();
                setTab(currentItem);
                initDot(pos);
                images[pos].setEnabled(true);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {


            }

            @Override
            public void onPageScrollStateChanged(int arg0) {


            }
        });
    }


    private void setSelect(int i) {
        setTab(i);
        viewpager.setCurrentItem(i);
    }

    private void setTab(int i) {

        switch (i) {
            case 0:
                button_layout.setVisibility(View.GONE);
                fadeInAnim(button_layout);
                break;
            case 1:
                button_layout.setVisibility(View.GONE);
                fadeInAnim(button_layout);
                break;
            case 2:
                button_layout.setVisibility(View.VISIBLE);
                initEvent();
                break;

            default:
                break;
        }
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.login) {
            goLoginActivity();
        }
        if (v.getId() == R.id.enter) {
            if (Constant.isFrist)
                goIndexActivity();
            else
                goInitActivity();

        }
    }

    private void goInitActivity() {
        startActivity(new Intent(this, InitActivity.class));
        finish();
    }

    private void goIndexActivity() {
        startActivity(new Intent(this, IndexActivity.class));
        finish();
    }

    private void goLoginActivity() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    private void fadeInAnim(View paramView) {
        if (Build.VERSION.SDK_INT < 11)
            return;
        float[] arrayOfFloat = new float[2];
        arrayOfFloat[0] = 0.0F;
        arrayOfFloat[1] = 1.0F;
        mAnim = ObjectAnimator.ofFloat(paramView, "alpha", arrayOfFloat).setDuration(1000L);
        AnimatorSet localAnimatorSet = new AnimatorSet();
        localAnimatorSet.play(mAnim);
        localAnimatorSet.start();
    }

    private void initDot(int select) {
        images = new ImageView[mFragments.size()];
        dot_layout.removeAllViews();
        for (int i = 0; i < mFragments.size(); i++) {
            ImageView localImageView = new ImageView(this);
            LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-2, -2);

            localLayoutParams.leftMargin = 30;
            localImageView.setLayoutParams(localLayoutParams);
            localImageView.setImageResource(R.drawable.dot_bg);
            if (i == select)
                localImageView.setEnabled(true);
            else
                localImageView.setEnabled(false);
            images[i] = localImageView;
            dot_layout.addView(localImageView);
        }
    }
}
