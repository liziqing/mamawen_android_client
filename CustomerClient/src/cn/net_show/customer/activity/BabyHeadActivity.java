package cn.net_show.customer.activity;

import mark.widget.ObservableHorizontalScrollView;
import mark.widget.ObservableHorizontalScrollView.OnScrollStopListner;
import cn.net_show.customer.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.NumberPicker.OnValueChangeListener;

/**
 * 宝宝头围界面
 * 
 * @author cheng
 * 
 */
public class BabyHeadActivity extends Activity {
	private NumberPicker mNumberPicker;
	private TextView headSize;
	private Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_babyhead);
		context = this;
		initView();
	}

	@SuppressLint("ClickableViewAccessibility")
	private void initView() {

		mNumberPicker = (NumberPicker) findViewById(R.id.np_months);
		headSize = (TextView) findViewById(R.id.tv_headsize);

		mNumberPicker.setMaxValue(40);
		mNumberPicker.setMinValue(1);
		mNumberPicker.setValue(1);

		mNumberPicker.setOnValueChangedListener(new OnValueChangeListener() {

			@Override
			public void onValueChange(NumberPicker picker, int oldVal,
					int newVal) {

			}
		});

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

			@Override
			public void onScrollChange(int index) {
				if (index == 0) {
					headSize.setText("25");
				} else {
					int value = px2dip(context, index);
					headSize.setText((value / 7 + 25) + "");
				}

			}
		});

	}

	protected int px2dip(Context context2, int index) {
		// TODO Auto-generated method stub
		return 0;
	}

}
