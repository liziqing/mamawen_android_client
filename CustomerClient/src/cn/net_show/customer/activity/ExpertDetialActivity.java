package cn.net_show.customer.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import cn.net_show.customer.R;
import cn.net_show.customer.model.Doctor;

public class ExpertDetialActivity extends Activity implements OnClickListener {

	private Doctor mDoctor;
	private TextView mTv_doc_name, mTv_doc_job, mTv_doc_hospital;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_expert_detial);
		initData();
		initViews();

	}

	private void initData() {
		// 得到doctor对象
		mDoctor = (Doctor) getIntent().getSerializableExtra("doctor");

	}

	private void initViews() {
		findViewById(R.id.btn_back).setOnClickListener(this);
		mTv_doc_name = (TextView) findViewById(R.id.txt_doctor_name);
		mTv_doc_job = (TextView) findViewById(R.id.txt_doctor_job);
		mTv_doc_hospital = (TextView) findViewById(R.id.txt_doctor_hospital);

		mTv_doc_name.setText(mDoctor.name);
		mTv_doc_job.setText(mDoctor.title);
		mTv_doc_hospital.setText(mDoctor.hospital);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_back:
			// 专辑详情页面
			ExpertDetialActivity.this.finish();
			break;
		}
	}
}
