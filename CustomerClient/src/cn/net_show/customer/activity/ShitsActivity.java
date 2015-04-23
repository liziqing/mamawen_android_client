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
 * 吐槽界面
 * 
 */
public class ShitsActivity extends Activity implements OnClickListener {
	private EditText mEt_content;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shits);
		initViews();
	}

	private void initViews() {
		findViewById(R.id.btn_back).setOnClickListener(this);
		mEt_content = (EditText) findViewById(R.id.et_shits_content);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_back:
			ShitsActivity.this.finish();
			break;
		case R.id.btn_commit:
			// 保存吐槽内容
			saveContent();
			break;
		}
	}

	private void saveContent() {
		String content = mEt_content.getText().toString();

	}
}
