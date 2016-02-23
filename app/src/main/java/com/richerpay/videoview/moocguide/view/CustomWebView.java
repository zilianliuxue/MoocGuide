package com.richerpay.videoview.moocguide.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebSettings.ZoomDensity;
import android.webkit.WebView;
import android.webkit.WebViewClient;
/**
 * 自定义WebView，长按图片获取图片url
 * @author LinZhang
 *
 */
public class CustomWebView extends WebView{
	private Context context;

	public CustomWebView(Context context) {
		super(context);
		this.context = context;
		initSettings();
	}

	private void initSettings() {
		// 初始化设置
		WebSettings mSettings = this.getSettings();
		mSettings.setJavaScriptEnabled(true);//开启javascript
		mSettings.setDomStorageEnabled(true);//开启DOM
		mSettings.setDefaultTextEncodingName("utf-8");//设置字符编码
		//设置web页面
		mSettings.setAllowFileAccess(true);//设置支持文件流
		mSettings.setSupportZoom(true);// 支持缩放
		mSettings.setBuiltInZoomControls(true);// 支持缩放
		mSettings.setUseWideViewPort(true);// 调整到适合webview大小
		mSettings.setLoadWithOverviewMode(true);// 调整到适合webview大小
		mSettings.setDefaultZoom(ZoomDensity.FAR);// 屏幕自适应网页,如果没有这个，在低分辨率的手机上显示可能会异常
		mSettings.setRenderPriority(RenderPriority.HIGH);
		//提高网页加载速度，暂时阻塞图片加载，然后网页加载好了，在进行加载图片
		mSettings.setBlockNetworkImage(true);
		mSettings.setAppCacheEnabled(true);//开启缓存机制

		setWebViewClient(new MyWebViewClient());

	}

	private class MyWebViewClient extends WebViewClient {
		/**
		 * 加载过程中 拦截加载的地址url
		 * @param view
		 * @param url  被拦截的url
		 * @return
		 */
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			return super.shouldOverrideUrlLoading(view, url);
		}
		/**
		 * 页面加载过程中，加载资源回调的方法
		 * @param view
		 * @param url
		 */
		@Override
		public void onLoadResource(WebView view, String url) {
			super.onLoadResource(view, url);
		}
		/**
		 * 页面加载完成回调的方法
		 * @param view
		 * @param url
		 */
		@Override
		public void onPageFinished(WebView view, String url) {
			super.onPageFinished(view, url);
			// 关闭图片加载阻塞
			view.getSettings().setBlockNetworkImage(false);

		}
		/**
		 * 页面开始加载调用的方法
		 * @param view
		 * @param url
		 * @param favicon
		 */
		@Override
		public void onPageStarted(WebView view, String url, Bitmap favicon) {
			super.onPageStarted(view, url, favicon);
		}

		@Override
		public void onReceivedError(WebView view, int errorCode,
									String description, String failingUrl) {
			super.onReceivedError(view, errorCode, description, failingUrl);
		}

		@Override
		public void onScaleChanged(WebView view, float oldScale, float newScale) {
			super.onScaleChanged(view, oldScale, newScale);
			CustomWebView.this.requestFocus();
			CustomWebView.this.requestFocusFromTouch();
		}

	}
	@Override
	// 设置回退
	// 覆盖Activity类的onKeyDown(int keyCoder,KeyEvent event)方法
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK) && this.canGoBack()) {
			this.goBack(); // goBack()表示返回WebView的上一页面
			return true;
		}
		return super.onKeyDown(keyCode,event);
	}


}
