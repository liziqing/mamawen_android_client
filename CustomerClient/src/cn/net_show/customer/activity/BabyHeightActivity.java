package cn.net_show.customer.activity;

import cn.net_show.customer.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.NumberPicker;
import android.widget.NumberPicker.OnValueChangeListener;

/**
 * 宝宝身高界面
 * 
 * @author cheng
 * 
 */
public class BabyHeightActivity extends Activity {
	private  NumberPicker mNumberPicker;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_babyheight);
		initView();
	}

	private void initView() {
		mNumberPicker = (NumberPicker) findViewById(R.id.np_height);
		
		mNumberPicker.setMaxValue(100);
		mNumberPicker.setMinValue(1);
		mNumberPicker.setValue(1);
		



		mNumberPicker.setOnValueChangedListener(new OnValueChangeListener() {

			@Override
			public void onValueChange(NumberPicker picker, int oldVal,
					int newVal) {

			}
		});
		
	}

}
