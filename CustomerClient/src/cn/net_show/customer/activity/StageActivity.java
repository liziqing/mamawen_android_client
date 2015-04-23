package cn.net_show.customer.activity;

import cn.net_show.customer.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * 育儿期
 * 
 * @author cheng
 * 
 */
public class StageActivity extends Activity implements OnClickListener {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stage);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_regist:
			startActivity(new Intent(this, BabyBaseActivity.class));
			overridePendingTransition(R.anim.slide_in_right,
					R.anim.slide_out_left);
			break;

		default:
			break;
		}

	}

}
