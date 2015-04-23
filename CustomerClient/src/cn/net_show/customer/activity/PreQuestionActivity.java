/**  
 * @Title: PreQuestion.java 
 * @Package mark.widget 
 * @author 王帅
 * @date 2015年3月12日 下午4:59:07  
 */
package cn.net_show.customer.activity;

import java.util.ArrayList;

import org.apache.http.Consts;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.content.StringBody;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

import mark.utils.Logger;
import mark.utils.http.HttpUtil;
import mark.utils.http.HttpUtil.ContentPart;
import mark.widget.MySpinner;
import cn.net_show.customer.MyApplication;
import cn.net_show.customer.R;
import cn.net_show.customer.model.InquiryItem;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * 问题概述
 * 
 * @ClassName: PreQuestion
 * @author 王帅
 * @date 2015年3月12日 下午4:59:07
 */
public class PreQuestionActivity extends FragmentActivity {
	private HttpUtil mHUtil;
	private Handler handler;
	private EditText edt_describe;

	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.activity_prequestion);
		findViews();
	}

	private void findViews() {
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_back:
			this.finish();
			break;
		case R.id.btn_commit:
			break;
		default:
			break;
		}
	}

	@Override
	public void finish() {
		super.finish();
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
	}
}
