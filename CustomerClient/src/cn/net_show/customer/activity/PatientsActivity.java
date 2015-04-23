package cn.net_show.customer.activity;

import java.util.ArrayList;
import java.util.List;

import cn.net_show.customer.R;
import cn.net_show.customer.adapter.PatientsAdapter;
import cn.net_show.customer.model.Patients;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

/**
 * 预诊对象
 * 
 * @author cheng
 * 
 */
public class PatientsActivity extends Activity implements OnClickListener {
	private ListView mListView;
	private PatientsAdapter mAdapter;
	private List<Patients> mPatients = new ArrayList<Patients>();
	private Context mContext;

	private ImageView mImageView_add;// 添加患者

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_patients);
		initView();
	}

	private void initView() {
		mContext = this;
		mListView = (ListView) findViewById(R.id.lv_patients);
		mListView.setDivider(null);
		mAdapter = new PatientsAdapter(mPatients, mContext);
		Patients patients = new Patients();
		patients.name = "范冰冰";
		mPatients.add(patients);
		mListView.setAdapter(mAdapter);
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Patients patients = (Patients) parent
						.getItemAtPosition(position);
				startActivity(new Intent(mContext, PreQuestionActivity.class));
				overridePendingTransition(R.anim.slide_in_right,
						R.anim.slide_out_left);
			}
		});
		mImageView_add = (ImageView) findViewById(R.id.iv_add);
		mImageView_add.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_back:
			PatientsActivity.this.finish();
			break;
		case R.id.iv_add:
			Intent intent = new Intent();
			intent.setClass(PatientsActivity.this, AddPatientInfoActivity.class);
			startActivity(intent);
			break;
		}
	}
}
