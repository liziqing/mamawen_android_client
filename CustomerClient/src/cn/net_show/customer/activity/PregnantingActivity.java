package cn.net_show.customer.activity;

import cn.net_show.customer.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * 怀孕期
 * 
 * @author cheng
 * 
 */
public class PregnantingActivity extends Activity implements OnClickListener {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_duringpregnant);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_ok:
			startActivity(new Intent(this, PregnantingDataActivity.class));
			overridePendingTransition(R.anim.slide_in_right,
					R.anim.slide_out_left);

			break;

		default:
			break;
		}

	}

}
