package cn.net_show.customer.activity;

import mark.widget.ObservableHorizontalScrollView;
import mark.widget.ObservableHorizontalScrollView.OnScrollStopListner;
import cn.net_show.customer.R;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.NumberPicker;
import android.widget.Toast;
import android.widget.NumberPicker.OnValueChangeListener;
import android.widget.TextView;

/**
 * 备孕体温
 * 
 * @author cheng
 * 
 */
public class TemperatureInputActivity extends Activity implements
		OnClickListener {
	private Context context;
	private TextView weight_value;
	private NumberPicker month, day;
	private int monthNum, dayNum;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_temperatureinput);
		context = this;
		initView();

	}

	private void initView() {
		weight_value = (TextView) findViewById(R.id.weight_value);
		month = (NumberPicker) findViewById(R.id.np_month);
		day = (NumberPicker) findViewById(R.id.np_day);
		final ObservableHorizontalScrollView scrollView = (ObservableHorizontalScrollView) findViewById(R.id.weight_scrollview);
		scrollView.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_UP) {
					scrollView.startScrollerTask();
				}
				return false;
			}
		});
		scrollView.setOnScrollStopListner(new OnScrollStopListner() {
			public void onScrollChange(int index) {
				if (index == 0) {
					weight_value.setText("25");
				} else {
					int value = px2dip(context, index);
					weight_value.setText((value / 7 + 25) + "");
				}
			}
		});
		timeSet();

	}

	private void timeSet() {
		month.setMaxValue(12);
		month.setMinValue(1);
		month.setValue(1);

		day.setMaxValue(31);
		day.setMinValue(1);
		day.setValue(1);

		month.setOnValueChangedListener(new OnValueChangeListener() {

			@Override
			public void onValueChange(NumberPicker picker, int oldVal,
					int newVal) {
				monthNum = newVal;

			}
		});

		day.setOnValueChangedListener(new OnValueChangeListener() {

			@Override
			public void onValueChange(NumberPicker picker, int oldVal,
					int newVal) {
				dayNum = newVal;
			}
		});

	}

	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_ok:
			Toast.makeText(context, monthNum + "月" + dayNum + "日",
					Toast.LENGTH_SHORT).show();
			break;

		default:
			break;
		}

	}
}
