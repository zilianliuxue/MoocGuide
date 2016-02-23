package com.richerpay.videoview.moocguide;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;

import com.richerpay.videoview.moocguide.view.CustomWebView;

/**
 * Created by zengyu on 2016/2/22.
 */
public class LoginActivity extends AppCompatActivity{
    private CustomWebView mCustomWebView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        initWebView();
    }

    private void initWebView() {
        mCustomWebView = new CustomWebView(this);
        mCustomWebView.loadUrl("http://www.imooc.com/user/newlogin/from_url/1003");//加载页面
        mCustomWebView.setFocusable(true);
        mCustomWebView.setFocusableInTouchMode(true);
        addContentView(mCustomWebView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
       Constant.isFrist=true;
    }
}
