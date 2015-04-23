package cn.net_show.customer.activity;

import mark.widget.MyChart;
import cn.net_show.customer.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

/**
 * 孕期数据
 * 
 * @author cheng
 * 
 */
public class PregnantingDataActivity extends Activity implements
		OnClickListener {
	private RadioGroup mGroup;
	private MyChart babyHead, babyWeight, babyHeight;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_prepregnantingdata);
		initView();
	}

	private void initView() {

		babyHead = (MyChart) findViewById(R.id.mychat_babyhead);
		babyWeight = (MyChart) findViewById(R.id.mychat_babyweight);
		babyHeight = (MyChart) findViewById(R.id.mychat_babyheight);
		babyHead.setOnClickListener(this);
		babyHeight.setOnClickListener(this);
		babyWeight.setOnClickListener(this);
		initChart();
		babyHead.setVisibility(View.VISIBLE);

		mGroup = (RadioGroup) findViewById(R.id.radiogroup_babyinfo);
		mGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.head:
					initChart();
					babyHead.setVisibility(View.VISIBLE);
					break;
				case R.id.height:
					initChart();
					babyHeight.setVisibility(View.VISIBLE);
					break;
				case R.id.weight:
					initChart();
					babyWeight.setVisibility(View.VISIBLE);
					break;

				default:
					break;
				}

			}
		});

	}

	private void initChart() {
		babyHead.setVisibility(View.GONE);
		babyWeight.setVisibility(View.GONE);
		babyHeight.setVisibility(View.GONE);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.mychat_babyhead:
			startActivity(new Intent(this, BabyHeadActivity.class));
			overridePendingTransition(R.anim.slide_in_right,
					R.anim.slide_out_left);

			break;
		case R.id.mychat_babyweight:
			startActivity(new Intent(this, BabyWeightActivity.class));
			overridePendingTransition(R.anim.slide_in_right,
					R.anim.slide_out_left);

			break;
		case R.id.mychat_babyheight:
			startActivity(new Intent(this, BabyHeightActivity.class));
			overridePendingTransition(R.anim.slide_in_right,
					R.anim.slide_out_left);

			break;

		default:
			break;
		}

	}

}
