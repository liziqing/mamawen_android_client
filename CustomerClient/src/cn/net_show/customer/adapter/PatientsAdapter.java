package cn.net_show.customer.adapter;

import java.util.List;

import cn.net_show.customer.R;
import cn.net_show.customer.model.Patients;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class PatientsAdapter extends BaseAdapter {
	private List<Patients> mData;
	private Context mContext;

	public PatientsAdapter(List<Patients> mDtos, Context context) {
		mData = mDtos;
		mContext = context;
	}

	@Override
	public int getCount() {
		return mData.size() == 0 ? 0 : mData.size();
	}

	@Override
	public Object getItem(int position) {
		return mData.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.item_patient, null);
			holder.mTextView = (TextView) convertView
					.findViewById(R.id.tv_edit);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.mTextView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(mContext, "编辑患者", Toast.LENGTH_SHORT).show();

			}
		});
		return convertView;
	}

	public static class ViewHolder {
		TextView mTextView;

	}

}
