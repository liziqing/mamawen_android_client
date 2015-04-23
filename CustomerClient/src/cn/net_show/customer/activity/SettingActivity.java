package cn.net_show.customer.activity;

import cn.net_show.customer.R;
import cn.net_show.customer.R.id;
import cn.net_show.customer.R.layout;
import cn.net_show.customer.R.menu;
import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * 设置界面
 * 
 */
public class SettingActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
		initViews();
	}

	private void initViews() {
		findViewById(R.id.rl_bindAccount).setOnClickListener(this);
		findViewById(R.id.rl_changePassword).setOnClickListener(this);
		findViewById(R.id.rl_newMesNoti).setOnClickListener(this);
		findViewById(R.id.rl_cacheClean).setOnClickListener(this);
		findViewById(R.id.rl_aboutUs).setOnClickListener(this);
		findViewById(R.id.btn_logOut).setOnClickListener(this);
		findViewById(R.id.btn_back).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_back:
			// 返回按鈕
			SettingActivity.this.finish();
			break;
		case R.id.btn_logOut:
			// 退出登录

			break;
		case R.id.rl_bindAccount:

			break;
		case R.id.rl_changePassword:
			// 修改密码
			startActivity(new Intent(this, ChangePasswordActivity.class));
			overridePendingTransition(R.anim.slide_in_right,
					R.anim.slide_out_left);
			break;
		case R.id.rl_newMesNoti:
			// 新消息通知界面
			startActivity(new Intent(this, NewMessNotificationActivity.class));
			overridePendingTransition(R.anim.slide_in_right,
					R.anim.slide_out_left);
			break;
		case R.id.rl_cacheClean:

			break;
		case R.id.rl_aboutUs:
			// 关于我们
			startActivity(new Intent(this, AboutUsActivity.class));
			overridePendingTransition(R.anim.slide_in_right,
					R.anim.slide_out_left);
			break;
		}
	}
}
