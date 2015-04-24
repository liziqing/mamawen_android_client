package cn.net_show.customer.fragment;

import com.android.pc.ioc.db.sqlite.CursorUtils.FindCacheSequence;

import mark.utils.MsgCenter;
import mark.widget.MyChart;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import cn.net_show.customer.R;
import cn.net_show.customer.model.UserInfo;

/**
 * 健康界面
 * 
 * @author cheng
 * 
 */
public class HealthFragment extends Fragment implements OnClickListener {

	public static final String TAG = HealthFragment.class.getSimpleName();

	private View rootView;
	private MyChart mc_prepregnant;// 备孕
	private MyChart mc_pregnant_weigth, mc_pregnant_bp, mc_pregnant_waistline;// 怀孕
	private MyChart mc_baby_weight, mc_baby_milk, mc_baby_heigth, mc_baby_head;// 育儿

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_health, null, false);
		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initView();
	}

	private void initView() {
		rootView.findViewById(R.id.btn_back).setOnClickListener(this);
		mc_prepregnant = (MyChart) rootView.findViewById(R.id.mychat_prepregnant);
		
		mc_pregnant_weigth = (MyChart) rootView.findViewById(R.id.mychat_pregnant_weigth);
		mc_pregnant_bp = (MyChart) rootView.findViewById(R.id.mychat_pregnant_bp);
		mc_pregnant_waistline = (MyChart) rootView.findViewById(R.id.mychat_pregnant_waistline);
		
		mc_baby_weight = (MyChart) rootView.findViewById(R.id.mychat_baby_weight);
		mc_baby_milk = (MyChart) rootView.findViewById(R.id.mychat_baby_milk);
		mc_baby_heigth = (MyChart) rootView.findViewById(R.id.mychat_baby_heigth);
		mc_baby_head = (MyChart) rootView.findViewById(R.id.mychat_baby_head);

		// 根据用户的state来判断显示不同的chart
//		if (UserInfo.state) {
//			
//			
//			
//		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_back:
			getActivity().finish();
			break;
		}
	}
}
