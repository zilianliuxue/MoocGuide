package com.richerpay.videoview.moocguide;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup.LayoutParams;

import com.richerpay.videoview.moocguide.view.CustomWebView;

/**
 * Created by zengyu on 2016/2/22.
 */
public class IndexActivity extends AppCompatActivity {
    private CustomWebView mCustomWebView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        getSupportActionBar().hide();
        initWebView();
    }

    private void initWebView() {
        // 初始WebView化控件
        mCustomWebView = new CustomWebView(this);
        mCustomWebView.loadUrl("http://www.imooc.com/course/list");//加载页面
        mCustomWebView.setFocusable(true);
        mCustomWebView.setFocusableInTouchMode(true);
        addContentView(mCustomWebView, new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
    }
}

