package cn.net_show.customer.activity;

import java.util.ArrayList;

import cn.net_show.customer.R;
import cn.net_show.customer.R.anim;
import cn.net_show.customer.R.id;
import cn.net_show.customer.R.layout;
import cn.net_show.customer.adapter.DoctorListAdapter;
import cn.net_show.customer.model.Doctor;
import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

/**
 * 专家问诊界面
 */
public class ExpertInquiryActivity extends Activity implements
		OnItemClickListener, OnClickListener {
	private ListView mLv_DoctorList;
	private ArrayList<Doctor> mDoctorList;
	private DoctorListAdapter mAdapter;
	private Doctor mDoctor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_expert_inquiry);
		initData();
		initViews();
		initEvent();
	}

	private void initData() {
		// 获得医生列表信息
		mDoctorList = new ArrayList<>();
		Doctor doctor1 = new Doctor();
		doctor1.name = "黄华";
		doctor1.title = "副主任医师";
		doctor1.hospital = "上海交通大学儿童医学中心";

		Doctor doctor2 = new Doctor();
		doctor2.name = "dava";
		doctor2.title = "主任医师";
		doctor2.hospital = "北京医学中心";

		mDoctorList.add(doctor1);
		mDoctorList.add(doctor2);
	}

	private void initViews() {
		mLv_DoctorList = (ListView) findViewById(R.id.lv_doctorList);
		mAdapter = new DoctorListAdapter(mDoctorList, this);
		mLv_DoctorList.setAdapter(mAdapter);
		findViewById(R.id.btn_back).setOnClickListener(this);

	}

	private void initEvent() {
		mLv_DoctorList.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		mDoctor = mDoctorList.get(position);
		// 进入专家详情界面

		Intent intent = new Intent();
		intent.setClass(ExpertInquiryActivity.this, ExpertDetialActivity.class);
		// 将doctor对象通过intent传递
		Bundle bundle = new Bundle();
		bundle.putSerializable("doctor", mDoctor);
		intent.putExtras(bundle);
		startActivity(intent);
		this.overridePendingTransition(R.anim.slide_in_right,
				R.anim.slide_out_left);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_back:
			ExpertInquiryActivity.this.finish();
			break;
		}
	}
}
