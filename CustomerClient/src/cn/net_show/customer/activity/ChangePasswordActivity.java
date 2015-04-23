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
import android.widget.EditText;

/**
 * 修改密码界面
 * 
 */
public class ChangePasswordActivity extends Activity implements OnClickListener {

	private EditText mEt_oldPass, mEt_newPass, mEt_newPass_again;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_change_password);
		initViews();
	}

	private void initViews() {
		findViewById(R.id.btn_back).setOnClickListener(this);
		findViewById(R.id.btn_save).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_back:
			ChangePasswordActivity.this.finish();
			break;
		case R.id.btn_save:
			// 保存

			break;
		}
	}
}
