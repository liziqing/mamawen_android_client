package cn.net_show.customer.activity;

import mark.widget.MyChart;
import cn.net_show.customer.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * 备孕体温
 * 
 * @author cheng
 * 
 */
public class PrePregnantingTempActivity extends Activity implements
		OnClickListener {
	private MyChart myChart;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_prepregnantingtemp);
		initView();
	}

	private void initView() {

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.mychat_temp:
			startActivity(new Intent(this, TemperatureInputActivity.class));
			overridePendingTransition(R.anim.slide_in_right,
					R.anim.slide_out_left);
			break;

		default:
			break;
		}

	}

}
