package cn.net_show.customer.activity;

import org.jivesoftware.smack.XMPPException;
import org.json.JSONObject;

import mark.utils.HttpUtils;
import cn.net_show.customer.MyApplication;
import cn.net_show.customer.R;
import cn.net_show.customer.util.JsonUtils;
import cn.net_show.customer.util.XmppTool;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SplashActivity extends FragmentActivity {
	private MyApplication app;
	private Handler handler;
	private JsonUtils jUtils;
	private EditText edt_name, edt_passwd;
	private TextView tv_phone, tv_findpasswd, tv_regedit;

	private boolean isBusy;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		app = (MyApplication) getApplication();
		jUtils = JsonUtils.getInstance();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		handler = new Handler();
		initView();

	}

	private void initView() {
		edt_name = (EditText) findViewById(R.id.edt_name);
		edt_passwd = (EditText) findViewById(R.id.edt_passwd);

		tv_phone = (TextView) findViewById(R.id.tv_phone);
		tv_findpasswd = (TextView) findViewById(R.id.tv_findpasswd);
		tv_regedit = (TextView) findViewById(R.id.tv_regedit);

		tv_phone.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
		tv_findpasswd.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
		tv_regedit.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);

	}

	public void onClick(View v) {
		int id = v.getId();
		if (id == R.id.btn1) {
			login();
		} else if (id == R.id.btn_regist) {
			startActivity(new Intent(this, RegistActivity.class));
			overridePendingTransition(R.anim.slide_in_right,
					R.anim.slide_out_left);
		} else {
		}

	}

	private void toast(final String msg) {
		handler.post(new Runnable() {
			@Override
			public void run() {
				Toast.makeText(app, msg, Toast.LENGTH_LONG).show();
			}
		});
	}

	private void conncetIM(String JID, String passwd) {
		toast("JID=" + JID);
		if (!XmppTool.getConnection(app).isConnected()) {
			toast("连接IM服务器失败");
			isBusy = false;
			return;
		}
		try {
			XmppTool.login(JID + "@"
					+ XmppTool.getConnection(app).getServiceName(), passwd, app);
			toast("登陆IM服务器成功");
		} catch (XMPPException e) {
			toast("登陆IM服务器失败");
			e.printStackTrace();
		}
	}

	private void login() {
		if (isBusy) {
			return;
		}
		isBusy = true;
		try {
			JSONObject json = new JSONObject();
			json.put("userName", edt_name.getText().toString().trim());
			String password = edt_passwd.getText().toString();
			json.put("password", password);
			Log.d("json", json.toString());
			HttpUtils.doPostAsyn("http://115.159.49.31:9000/user/login",
					json.toString(), new HttpUtils.CallBack() {
						@Override
						public void onRequestComplete(final String result) {
							Log.d("login json", result);
							app.Customer = jUtils.getLoginInfo(result);
							if (app.Customer == null) {
								toast("登陆失败！");
								isBusy = false;
								return;
							}
							handler.postDelayed(new Runnable() {
								@Override
								public void run() {
									// Toast.makeText(SplashActivity.this,
									// result, Toast.LENGTH_LONG).show();
									// finish();
									isBusy = false;
									startActivity(new Intent(
											SplashActivity.this,
											MainPagerActivity.class));
									overridePendingTransition(
											R.anim.slide_in_right,
											R.anim.slide_out_left);
								}
							}, 200);

							MyApplication.isLogin = true;
							conncetIM(app.Customer.jid, "123456");
						}
					});
		} catch (Exception e) {
			isBusy = false;
			e.printStackTrace();
			Toast.makeText(app, "登陆失败！", Toast.LENGTH_LONG).show();
		}
	}

}
