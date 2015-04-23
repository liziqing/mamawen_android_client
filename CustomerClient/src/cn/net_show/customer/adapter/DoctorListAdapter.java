/**  
 * @Title: DoctorListAdapter.java 
 * @Package cn.net_show.customer.adapter 
 * @author 王帅
 * @date 2015年3月14日 下午8:22:28  
 */
package cn.net_show.customer.adapter;

import java.util.ArrayList;
import cn.net_show.customer.R;
import cn.net_show.customer.model.Doctor;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * @ClassName: DoctorListAdapter
 * @author 王帅
 * @date 2015年3月14日 下午8:22:28
 */
public class DoctorListAdapter extends BaseAdapter {
	private ArrayList<Doctor> mList;
	private Context mContext;

	/**
	 * @param mList
	 * @param mContext
	 */
	public DoctorListAdapter(ArrayList<Doctor> mList, Context mContext) {
		super();
		this.mList = mList;
		this.mContext = mContext;
	}

	@Override
	public int getCount() {
		if (mList == null) {
			return 0;
		}
		return mList.size();
	}

	@Override
	public Object getItem(int position) {

		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = View.inflate(mContext, R.layout.item_doctor, null);
			holder = new ViewHolder();
			holder.name = (TextView) convertView
					.findViewById(R.id.txt_doctor_name);
			holder.job = (TextView) convertView
					.findViewById(R.id.txt_doctor_job);
			holder.hospital = (TextView) convertView
					.findViewById(R.id.txt_doctor_hospital);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		Doctor item = mList.get(position);
		holder.name.setText(item.name);
		holder.job.setText(item.title);
		holder.hospital.setText(item.hospital);
		return convertView;
	}

	public class ViewHolder {
		public TextView name;
		public TextView job;
		public TextView hospital;
	}
}
