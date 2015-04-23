package cn.net_show.customer.activity;

import cn.net_show.customer.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

public class AddPatientInfoActivity extends Activity implements OnClickListener {

	private EditText mEt_name, mEt_sex, mEt_birthday, mEt_Departments;// 科室

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// requestWindowFeature(Window.FEATURE_NO_TITLE);
		// getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		// WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_add_patient_info);
		initViews();

	}

	private void initViews() {
		findViewById(R.id.btn_back).setOnClickListener(this);
		findViewById(R.id.btn_sure_add).setOnClickListener(this);
		mEt_name = (EditText) findViewById(R.id.et_userName);
		mEt_sex = (EditText) findViewById(R.id.et_sex);
		mEt_birthday = (EditText) findViewById(R.id.et_birthday);
		mEt_Departments = (EditText) findViewById(R.id.et_Departments);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_back:
			AddPatientInfoActivity.this.finish();
			break;
		case R.id.btn_sure_add:// 确认添加
			saveData();// 保存数据
			break;
		}
	}

	private void saveData() {

	}
}
