package cn.net_show.customer.fragment;

import cn.net_show.customer.R;
import cn.net_show.customer.activity.BalanceActivity;
import cn.net_show.customer.activity.MakeMoneyActivity;
import cn.net_show.customer.activity.MoneyStoreActivity;
import cn.net_show.customer.activity.OpenVipActivity;
import cn.net_show.customer.activity.PersonalDetialActivity;
import cn.net_show.customer.activity.RegistActivity;
import cn.net_show.customer.activity.SettingActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/**
 * 个人中心
 * 
 * @author cheng
 */
public class PersonalFragment extends Fragment implements OnClickListener {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.fragment_personal, null);
		initData();// 初始化数据
		initViews(view);
		return view;
	}

	private void initData() {
	}

	private void initViews(View view) {
		view.findViewById(R.id.rl_openVip).setOnClickListener(this);
		view.findViewById(R.id.rl_makeMoney).setOnClickListener(this);
		view.findViewById(R.id.rl_moneyStore).setOnClickListener(this);
		view.findViewById(R.id.rl_setting).setOnClickListener(this);
		view.findViewById(R.id.rl_personal).setOnClickListener(this);
		view.findViewById(R.id.ll_balance).setOnClickListener(this);
		view.findViewById(R.id.ll_grade).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.rl_openVip:
			startActivity(new Intent(getActivity(), OpenVipActivity.class));
			getActivity().overridePendingTransition(R.anim.slide_in_right,
					R.anim.slide_out_left);
			break;
		case R.id.rl_makeMoney:
			startActivity(new Intent(getActivity(), MakeMoneyActivity.class));
			getActivity().overridePendingTransition(R.anim.slide_in_right,
					R.anim.slide_out_left);
			break;
		case R.id.rl_moneyStore:
			startActivity(new Intent(getActivity(), MoneyStoreActivity.class));
			getActivity().overridePendingTransition(R.anim.slide_in_right,
					R.anim.slide_out_left);
			break;
		case R.id.rl_setting:
			startActivity(new Intent(getActivity(), SettingActivity.class));
			getActivity().overridePendingTransition(R.anim.slide_in_right,
					R.anim.slide_out_left);
			break;
		// 进入个人信息详情界面
		case R.id.rl_personal:
			startActivity(new Intent(getActivity(),
					PersonalDetialActivity.class));
			getActivity().overridePendingTransition(R.anim.slide_in_right,
					R.anim.slide_out_left);
			break;
		// 进入余额界面
		case R.id.ll_balance:
			startActivity(new Intent(getActivity(), BalanceActivity.class));
			getActivity().overridePendingTransition(R.anim.slide_in_right,
					R.anim.slide_out_left);
			break;
		// 进入等级界面
		case R.id.ll_grade:
			// startActivity(new Intent(getActivity(),
			// .class));
			// getActivity().overridePendingTransition(R.anim.slide_in_right,
			// R.anim.slide_out_left);
			break;
		}
	}
}
