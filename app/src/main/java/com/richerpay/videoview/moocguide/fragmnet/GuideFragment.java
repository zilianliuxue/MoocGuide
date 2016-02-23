package com.richerpay.videoview.moocguide.fragmnet;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.richerpay.videoview.moocguide.R;
import com.richerpay.videoview.moocguide.view.MCVideoView;


public class GuideFragment extends Fragment {

    private View mBgView;
    private Context mContext;
    private MCVideoView mVideoView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext=context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View localView = inflater.inflate(R.layout.guide_item_layout, null);
        initView(localView);
        return localView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    private void initView(View localView) {
        mVideoView = ((MCVideoView)localView.findViewById(R.id.videoview));
        mBgView = localView.findViewById(R.id.guide_bg);
    }

    private void initData() {
        Bundle localBundle = getArguments();
        int i = 0;
        int j = 0;
        int k = 0;
        if ((localBundle != null) && (localBundle.containsKey("index")))
            i = localBundle.getInt("index");
        try
        {
            String str = "guide_" + i;
            j = R.raw.class.getDeclaredField(str).getInt(this);
            int l = R.mipmap.class.getDeclaredField(str).getInt(this);
            k = l;
            if (j != 0)
                this.mVideoView.playVideo(mContext, Uri.parse("android.resource://" + this.mContext.getPackageName() + "/" + j));
            if (k != 0)
                this.mBgView.setBackgroundResource(k);
            return;
        }
        catch (Exception localException)
        {
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (this.mVideoView == null)
            return;
        this.mVideoView.stopPlayback();
    }
}
