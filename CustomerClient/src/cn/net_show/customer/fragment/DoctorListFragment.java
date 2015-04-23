/**  
 * @Title: DoctorListFragment.java 
 * @Package cn.net_show.customer.fragment 
 * @author 王帅
 * @date 2015年3月14日 下午4:45:50  
 */
package cn.net_show.customer.fragment;

import java.util.ArrayList;

import org.json.JSONObject;

import mark.utils.HttpUtils;
import cn.net_show.customer.MyApplication;
import cn.net_show.customer.R;
import cn.net_show.customer.activity.IMActivity;
import cn.net_show.customer.adapter.DoctorListAdapter;
import cn.net_show.customer.model.Doctor;
import cn.net_show.customer.util.JsonUtils;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

/**
 * @ClassName: DoctorListFragment
 * @author 王帅
 * @date 2015年3月14日 下午4:45:50
 */
public class DoctorListFragment extends Fragment implements OnClickListener {
	private View view;
	private ListView listView;
	private MyApplication app;
	private ArrayList<Doctor> mList;
	private Handler mHandler;
	private DoctorListAdapter adapter;
	private JsonUtils jUtils;

	@SuppressLint("InflateParams")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		app = (MyApplication) getActivity().getApplication();
		view = inflater.inflate(R.layout.fragment_doctorlist, null);
		getDoctorList();
		findViews();
		init();
		return view;
	}

	private void findViews() {
		listView = (ListView) view.findViewById(R.id.lv_doctor);
		view.findViewById(R.id.btn_back).setOnClickListener(this);
	}

	private void init() {
		jUtils = JsonUtils.getInstance();
		mList = new ArrayList<>();
		adapter = new DoctorListAdapter(mList, getActivity());
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				startActivity(new Intent(getActivity(), IMActivity.class));
				getActivity().overridePendingTransition(R.anim.slide_in_right,
						R.anim.slide_out_left);
			}
		});
		mHandler = new Handler(getActivity().getMainLooper()) {

			@Override
			public void handleMessage(Message msg) {
				switch (msg.what) {
				case 1:
					Toast.makeText(getActivity(), "联网失败！", Toast.LENGTH_SHORT)
							.show();
					break;
				case 2:
					Toast.makeText(getActivity(), "没有获取到医生列表！",
							Toast.LENGTH_SHORT).show();
					break;
				case 3:
					adapter.notifyDataSetChanged();
					break;
				default:
					break;
				}
			}

		};
	}

	private void getDoctorList() {
		if (app.Customer == null) {
			return;
		}
		String url = MyApplication.ServerUrl + "/doctor/volunteer/list?uid="
				+ app.Customer.userID + "&sessionkey=123&page=0&limit=20";
		try {
			JSONObject obj = new JSONObject();
			obj.put("department", "儿科");
			HttpUtils.doPostAsyn(url, obj.toString(), new HttpUtils.CallBack() {
				@Override
				public void onRequestComplete(String result) {
					ArrayList<Doctor> list = jUtils.getDoctorList(result);
					if (list == null) {
						mHandler.sendEmptyMessage(1);
						return;
					}
					if (list.size() < 1) {
						mHandler.sendEmptyMessage(2);
						return;
					}
					mList.addAll(0, list);
					mHandler.sendEmptyMessage(3);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			mHandler.sendEmptyMessage(1);
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_back:
			this.getActivity().finish();
			break;
		}
	}
}
