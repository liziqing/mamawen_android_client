package cn.net_show.customer.activity;

import cn.net_show.customer.R;
import cn.net_show.customer.R.id;
import cn.net_show.customer.R.layout;
import cn.net_show.customer.R.menu;
import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;

/**
 * 开通会员界面
 * 
 */
public class OpenVipActivity extends Activity implements OnClickListener {
	
	private  RadioButton mRadioButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_open_vip);
		initViews();
	}

	private void initViews() {
		findViewById(R.id.btn_back).setOnClickListener(this);
		mRadioButton=(RadioButton) findViewById(R.id.rb);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_back:
			OpenVipActivity.this.finish();
			break;
		}
	}
}
