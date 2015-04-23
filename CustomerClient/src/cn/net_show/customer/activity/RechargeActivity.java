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
import android.widget.EditText;

/**
 * 充值界面
 * 
 */
public class RechargeActivity extends Activity implements OnClickListener {

	// 充值数目
	private EditText mEt_acount;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recharge);
		initViews();
	}

	private void initViews() {
		findViewById(R.id.btn_back).setOnClickListener(this);
		findViewById(R.id.btn_sure).setOnClickListener(this);
		mEt_acount = (EditText) findViewById(R.id.et_recharge_acount);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_back:
			RechargeActivity.this.finish();
			break;
		// 确认按钮
		case R.id.btn_sure:
			// mEt_acount.getText().toString();
			break;
		// case R.id.iv_shits:
		// // 进入吐槽界面
		// startActivity(new Intent(this, ShitsActivity.class));
		// overridePendingTransition(R.anim.slide_in_right,
		// R.anim.slide_out_left);
		// break;
		}
	}
}
