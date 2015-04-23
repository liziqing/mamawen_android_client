package cn.net_show.customer.activity;

import cn.net_show.customer.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * 备孕期
 * 
 * @author cheng
 * 
 */
public class PrePregnantingActivity extends Activity implements OnClickListener {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_prepregnant);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_regist:
			startActivity(new Intent(this, PrePregnantingTempActivity.class));
			overridePendingTransition(R.anim.slide_in_right,
					R.anim.slide_out_left);

			break;

		default:
			break;
		}

	}

}
