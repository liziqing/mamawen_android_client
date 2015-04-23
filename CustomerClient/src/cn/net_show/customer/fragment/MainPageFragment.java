/**  
 * @Title: MainPageFragment.java 
 * @Package cn.net_show.customer.fragment 
 * @author 王帅
 * @date 2015年3月12日 下午4:09:59  
 */
package cn.net_show.customer.fragment;

//import cn.net_show.customer.MyApplication;
import cn.net_show.customer.R;
import cn.net_show.customer.activity.ExpertInquiryActivity;
import cn.net_show.customer.activity.LoginActivity;
import cn.net_show.customer.activity.PatientListActivity;
import cn.net_show.customer.activity.PatientsActivity;
import cn.net_show.customer.activity.PreQuestionActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

/**
 * @ClassName: MainPageFragment
 * @author 王帅
 * @date 2015年3月12日 下午4:09:59
 */
public class MainPageFragment extends Fragment implements View.OnClickListener {
	private View view;
	// private MyApplication app;
	private ImageButton btn_add;
	private Button btn_free, btn_paid;
	private LinearLayout addLayout;

	@SuppressLint("InflateParams")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// app = (MyApplication) getActivity().getApplication();
		view = inflater.inflate(R.layout.fragment_mainpager, null, false);
		findViews();
		return view;

	}

	private void findViews() {
		btn_add = (ImageButton) view.findViewById(R.id.imgBtn_addpatient);
		btn_add.setOnClickListener(this);
		// 问医生
		btn_free = (Button) view.findViewById(R.id.btn_free);
		btn_free.setOnClickListener(this);
		// 找医生
		btn_paid = (Button) view.findViewById(R.id.btn_paid);
		btn_paid.setOnClickListener(this);
		addLayout = (LinearLayout) view.findViewById(R.id.layout_login);
		addLayout.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.imgBtn_addpatient:
			startActivity(new Intent(getActivity(), PatientListActivity.class));
			getActivity().overridePendingTransition(R.anim.slide_in_right,
					R.anim.slide_out_left);
			break;
		case R.id.btn_free:
			// 问医生
			startActivity(new Intent(getActivity(), PatientsActivity.class));
			getActivity().overridePendingTransition(R.anim.slide_in_right,
					R.anim.slide_out_left);
			break;
		case R.id.btn_paid:
			// 专家问诊
			startActivity(new Intent(getActivity(), ExpertInquiryActivity.class));
			getActivity().overridePendingTransition(R.anim.slide_in_right,
					R.anim.slide_out_left);
			break;
		case R.id.layout_login:
			// 登录
			startActivity(new Intent(getActivity(), LoginActivity.class));
			getActivity().overridePendingTransition(R.anim.slide_in_right,
					R.anim.slide_out_left);
			break;
		}
	}
}
