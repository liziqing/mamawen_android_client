/**  
 * @Title: PatientListActivity.java 
 * @Package cn.net_show.customer.activity 
 * @author 王帅
 * @date 2015年3月12日 下午4:07:38  
 */ 
package cn.net_show.customer.activity;

import cn.net_show.customer.R;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ListView;

/** 
 * @ClassName: PatientListActivity 
 * @author 王帅
 * @date 2015年3月12日 下午4:07:38  
 */
public class PatientListActivity extends FragmentActivity {
	private ListView mListView;
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_patient_list);
		findViews();
	}
	
	public void onClick(View v){
		switch(v.getId()){
		case R.id.btn_back:
			this.finish();
			break;
		case R.id.btn_next:
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
	
	private void findViews(){
		mListView = (ListView) findViewById(R.id.patient_listview);
	}
}
