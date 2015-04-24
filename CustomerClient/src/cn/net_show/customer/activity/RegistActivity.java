/**  
 * @Title: RegistActivity.java 
 * @Package cn.net_show.customer.activity 
 * @author 王帅
 * @date 2015年3月31日 下午2:24:43  
 */
package cn.net_show.customer.activity;

import mark.utils.MsgCenter;
import cn.net_show.customer.R;
import cn.net_show.customer.fragment.HealthFragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 注册界面
 * 
 * @ClassName: RegistActivity
 * @author 王帅
 * @date 2015年3月31日 下午2:24:43
 */
public class RegistActivity extends FragmentActivity implements OnClickListener {
	private EditText edt_phone, edt_passwd, edt_vcode;
	private TextView tv_time;

	@Override
	protected void onCreate(Bundle bundle) {
		setContentView(R.layout.activity_regist);
		super.onCreate(bundle);
		findViews();
	}

	private void findViews() {
		edt_phone = (EditText) findViewById(R.id.edt_name);
		edt_passwd = (EditText) findViewById(R.id.edt_passwd);
		edt_vcode = (EditText) findViewById(R.id.edt_identifycode);
		tv_time = (TextView) findViewById(R.id.tv_time);

	}

	@Override
	public void finish() {
		super.finish();
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_back:
			this.finish();
			break;
		case R.id.btn_regist:
			regist();
			break;
		default:
			break;
		}
	}

	private void regist() {
		String phone = edt_phone.getText().toString().trim();
		String passwd = edt_passwd.getText().toString().trim();
		String vcode = edt_vcode.getText().toString().trim();
		String time = tv_time.getText().toString().trim();

		if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(passwd)
				|| TextUtils.isEmpty(vcode) || TextUtils.isEmpty(time)) {
			Toast.makeText(this, "请将信息填写完整", Toast.LENGTH_SHORT).show();
			return;
		}
		// if (!passwd1.equals(passwd2)) {
		// Toast.makeText(this, "两次输入密码不一致！", Toast.LENGTH_SHORT).show();
		// return;
		// }

		if (time.equals("备孕期")) {
			super.onDestroy();
			startActivity(new Intent(this, PrePregnantingActivity.class));
			overridePendingTransition(R.anim.slide_in_right,
					R.anim.slide_out_left);

		} else if (time.equals("怀孕期")) {
			startActivity(new Intent(this, PregnantingActivity.class));
			overridePendingTransition(R.anim.slide_in_right,
					R.anim.slide_out_left);

		} else if (time.equals("育儿期")) {
			startActivity(new Intent(this, StageActivity.class));
			overridePendingTransition(R.anim.slide_in_right,
					R.anim.slide_out_left);

		}
		Toast.makeText(this, "后台注册接口待完善！", Toast.LENGTH_SHORT).show();

	}
}
