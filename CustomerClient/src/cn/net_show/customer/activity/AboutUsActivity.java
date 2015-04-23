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
 * 关于我们界面
 * 
 */
public class AboutUsActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about_us);
		initViews();
	}

	private void initViews() {
		findViewById(R.id.btn_back).setOnClickListener(this);
		// 进入吐槽界面
		findViewById(R.id.iv_shits).setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_back:
			AboutUsActivity.this.finish();
			break;
		case R.id.iv_shits:
			// 进入吐槽界面
			startActivity(new Intent(this, ShitsActivity.class));
			overridePendingTransition(R.anim.slide_in_right,
					R.anim.slide_out_left);
			break;
		}
	}
}
