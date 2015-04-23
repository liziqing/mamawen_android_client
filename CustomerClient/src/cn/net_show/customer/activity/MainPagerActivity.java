/**  
 * @Title: LeadActivity.java 
 * @Package cn.net_show.customer.activity 
 * @author 王帅
 * @date 2015年3月2日 下午3:02:17  
 */
package cn.net_show.customer.activity;

import cn.net_show.customer.MyApplication;
import cn.net_show.customer.R;
import cn.net_show.customer.fragment.DoctorListFragment;
import cn.net_show.customer.fragment.HealthFragment;
import cn.net_show.customer.fragment.MainPageFragment;
import cn.net_show.customer.fragment.PersonalFragment;
import cn.net_show.customer.util.XmppTool;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * @ClassName: LeadActivity
 * @author 王帅
 * @date 2015年3月2日 下午3:02:17
 */
public class MainPagerActivity extends FragmentActivity implements
		RadioGroup.OnCheckedChangeListener {
	private FragmentManager fm;// 管理
	private MyApplication app;// 相当于上下文
	private Fragment mFragment;
	private RadioGroup rdg;

	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.activity_main);
		init();
		findViews();
	}

	private void init() {
		app = (MyApplication) getApplication();
		fm = getSupportFragmentManager();
		mFragment = new MainPageFragment();
		fm.beginTransaction().add(R.id.container_main, mFragment, "MainPage")
				.commit();
	}

	private void findViews() {
		rdg = (RadioGroup) findViewById(R.id.main_rd_group);
		rdg.setOnCheckedChangeListener(this);
	}

	@Override
	public void finish() {
		super.finish();
		XmppTool.closeConnection();
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		FragmentTransaction ft = fm.beginTransaction();
		if (mFragment != null) {
			ft.hide(mFragment);
		}

		switch (checkedId) {
		case R.id.rd_mainpage:// 主页
			mFragment = fm.findFragmentByTag("MainPage");
			if (mFragment == null) {
				mFragment = new MainPageFragment();
				fm.beginTransaction()
						.add(R.id.container_main, mFragment, "MainPage")
						.show(mFragment).commit();
			} else {
				ft.show(mFragment).commit();
			}

			break;
		case R.id.rd_consultation:// 问诊
			mFragment = fm.findFragmentByTag("DoctorList");
			if (mFragment == null) {
				mFragment = new DoctorListFragment();
				fm.beginTransaction()
						.add(R.id.container_main, mFragment, "DoctorList")
						.show(mFragment).commit();
			} else {
				ft.show(mFragment).commit();
			}
			break;
		case R.id.rd_health:// 健康
			mFragment = fm.findFragmentByTag("HealthFragment");
			if (mFragment == null) {
				mFragment = new HealthFragment();
				fm.beginTransaction()
						.add(R.id.container_main, mFragment, "HealthFragment")
						.show(mFragment).commit();
			} else {
				ft.show(mFragment).commit();
			}
			break;
		case R.id.rd_discovery:// 个人
			// Toast.makeText(app, "有待完善！", Toast.LENGTH_SHORT).show();
			mFragment = fm.findFragmentByTag("personal");
			if (mFragment == null) {
				mFragment = new PersonalFragment();
				fm.beginTransaction()
						.add(R.id.container_main, mFragment, "personal")
						.show(mFragment).commit();
			} else {
				ft.show(mFragment).commit();
			}
			break;
		}
	}
}
