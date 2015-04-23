/**  
 * @Title: IMActivity.java 
 * @Package cn.net_show.doctor.activity 
 * @author 王帅
 * @date 2015年2月27日 下午3:52:58  
 */
package cn.net_show.customer.activity;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import mark.utils.SimpleUtils;
import mark.utils.http.HttpUtil;
import cn.net_show.customer.MyApplication;
import cn.net_show.customer.R;
import cn.net_show.customer.adapter.MsgItemAdapter;
import cn.net_show.customer.model.MsgItem;
import cn.net_show.customer.model.Role;
import cn.net_show.customer.model.ServerMsgItem;
import cn.net_show.customer.util.ChatMessageListener;
import cn.net_show.customer.util.XmppTool;
import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @ClassName: IMActivity
 * @author 王帅
 * @date 2015年2月27日 下午3:52:58
 */
public class IMActivity extends FragmentActivity {
	private EditText edt_msg; // 消息输入框
	private Button btn_send;
	private ImageButton imgBtn_voice;
	private ListView listView; // 消息显示列表
	private ArrayList<MsgItem> msgList; // 消息列表
	private MsgItemAdapter adapter; // 消息适配器
	private MyApplication app; // application
	public static final String TAG = "IMconnectiono";
	private BroadcastReceiver broadcastReceiver;
	private Button btn_record;
	private MediaRecorder recorder;
	private File audio; // =
	private TextView tv;
	private int inquiryId = -1;
	private Role sender, receiver;
	private HttpUtil httpUtil;
	private String serverUrl;

	@Override
	protected void onCreate(Bundle saveBundle) {
		super.onCreate(saveBundle);
		setContentView(R.layout.activity_im);
		init();
		findViews();
	}

	@Override
	public void finish() {
		super.finish();
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
	}

	public void init() {
		// 设置控制的音频流
		httpUtil = HttpUtil.getInstance();
		setVolumeControlStream(AudioManager.STREAM_MUSIC);
		app = (MyApplication) getApplication();
		msgList = new ArrayList<MsgItem>();
		adapter = new MsgItemAdapter(this, msgList);
		broadcastReceiver = new MessageReciver();
		registerReceiver(broadcastReceiver, new IntentFilter(
				ChatMessageListener.ACTION_MESSAGE_LISTENER));
		sender = new Role();
		sender.setId(app.Customer.userID);
		sender.setRole(Role.ROLE_USER);
		serverUrl = MyApplication.ServerUrl + "/im/user/send?uid="
				+ app.Customer.userID + "&sessionkey="
				+ app.Customer.sessionkey;
		inquiryId = getIntent().getIntExtra("inquiryId", -1);
	}

	private void findViews() {
		btn_send = (Button) findViewById(R.id.btn_send);
		imgBtn_voice = (ImageButton) findViewById(R.id.imgBtn_voice);
		btn_record = (Button) findViewById(R.id.btn_record_voice);
		tv = (TextView) findViewById(R.id.txt_doctorInfo);
		btn_record.setOnTouchListener(new View.OnTouchListener() {
			@SuppressLint("ClickableViewAccessibility")
			@Override
			public boolean onTouch(View v, MotionEvent event) {

				try {
					switch (event.getAction()) {
					case MotionEvent.ACTION_DOWN:
						if (receiver == null) {
							Toast.makeText(IMActivity.this, "等待接诊中……",
									Toast.LENGTH_SHORT).show();
							return true;
						}
						audio = new File(
								getExternalFilesDir(Environment.DIRECTORY_DCIM),
								app.Customer.jid + "-"
										+ System.currentTimeMillis() + ".amr");
						audio.createNewFile();
						recordVice(audio.getCanonicalPath());
						break;
					case MotionEvent.ACTION_UP:
						if (receiver == null) {
							// Toast.makeText(IMActivity.this,
							// "等待接诊中……",Toast.LENGTH_SHORT).show();
							return true;
						}
						recorder.stop();
						recorder.reset();
						recorder.release();
						recorder = null;
						SendFileTask task = new SendFileTask();
						task.execute(audio);
						break;
					}
					return false;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return false;
			}
		});
		edt_msg = (EditText) findViewById(R.id.edt_msg);
		edt_msg.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
				if (s.toString().length() < 1) {
					imgBtn_voice.setVisibility(View.VISIBLE);
					btn_send.setVisibility(View.GONE);
				} else {
					btn_send.setVisibility(View.VISIBLE);
					imgBtn_voice.setVisibility(View.GONE);
				}

			}
		});
		/*
		 * title = (TextView) findViewById(R.id.title);
		 * title.setText(app.Doctor.name+"医生");
		 */
		listView = (ListView) findViewById(R.id.listView);
		listView.setAdapter(adapter);
	}

	@Override
	protected void onResume() {
		XMPPConnection conn = XmppTool.getConnection(app);
		if (!conn.isConnected() || !conn.isAuthenticated()) {
			new Thread() {
				public void run() {
					try {
						XmppTool.login(app.Customer.jid, "123456", app);
						Log.e(TAG, XmppTool.getConnection(app).getUser());
					} catch (XMPPException e) {
						e.printStackTrace();
					}
				}
			}.start();
		} else {
			Log.e(TAG, XmppTool.getConnection(app).getUser());
		}
		super.onResume();
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_back:
			this.finish();
			break;
		case R.id.imgBtn_more:
			Intent intent = new Intent(Intent.ACTION_PICK);
			intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
					"image/*");
			startActivityForResult(intent, 1111);
			break;
		case R.id.btn_send:
			if (receiver == null) {
				Toast.makeText(IMActivity.this, "等待接诊中……", Toast.LENGTH_SHORT)
						.show();
				return;
			}
			sendStringMsg(edt_msg.getText().toString());
			break;
		case R.id.imgBtn_voice:
			if (btn_record.getVisibility() == View.VISIBLE) {
				btn_record.setVisibility(View.GONE);
				edt_msg.setVisibility(View.VISIBLE);
			} else {
				edt_msg.setVisibility(View.GONE);
				btn_record.setVisibility(View.VISIBLE);
			}
			break;
		default:
			break;
		}
	}

	/**
	 * 发送文字信息
	 * 
	 * @Title: sendStringMsg
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @return void
	 */
	private void sendStringMsg(final String msg) {
		if (msg == null || msg.trim().length() < 1 || receiver == null) {
			return;
		}
		MsgItem item = new MsgItem();
		item.setUser1(app.Customer.userID);
		item.setUser2(receiver.getId());
		item.setTime(System.currentTimeMillis());
		item.setType(MsgItem.TYPE_STRING);
		item.setContent(msg);
		item.setIsSend(MsgItem.SEND);
		item.setStatus(MsgItem.STATUS_READ);
		item.save();
		msgList.add(item);
		adapter.notifyDataSetChanged();
		// listView.setSelection(msgList.size());
		listView.smoothScrollToPosition(listView.getCount() - 1);
		edt_msg.setText("");
		// listView.setSelection(msgList.size());
		new Thread() {
			public void run() {
				ServerMsgItem item = new ServerMsgItem();
				item.sender = sender;
				item.receiver = receiver;
				item.category = ServerMsgItem.TYPE_CHAT;
				item.subCategory = ServerMsgItem.SUBTYPE_STRING;
				item.content = msg;
				item.inquiryID = inquiryId;
				String result = httpUtil.sendIMMessage(serverUrl, item);
				Log.e(TAG, result + "----");
			}
		}.start();
	}

	@Override
	protected void onDestroy() {
		// 解除广播注册
		if (broadcastReceiver != null) {
			unregisterReceiver(broadcastReceiver);
		}
		super.onDestroy();
	}

	/**
	 * 上传文件
	 * 
	 * @ClassName: SendFileTask
	 * @author 王帅
	 * @date 2015年3月24日 下午3:43:01
	 */
	class SendFileTask extends AsyncTask<File, String, String> {
		private String path;

		@Override
		protected String doInBackground(File... params) {
			path = params[0].getAbsolutePath();
			ServerMsgItem item = new ServerMsgItem();
			item.sender = sender;
			item.receiver = receiver;
			item.category = ServerMsgItem.TYPE_CHAT;
			if (path.endsWith(".amr")) {
				item.subCategory = ServerMsgItem.SUBTYPE_VOICE;
			} else {
				item.subCategory = ServerMsgItem.SUBTYPE_IMAGE;
			}
			// type = item.subCategory;
			item.inquiryID = inquiryId;
			String result = httpUtil.sendIMMessage(serverUrl, item, params[0]);
			Log.e(TAG, result + "----");
			return "文件上传完成！";
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			Toast.makeText(IMActivity.this, result, Toast.LENGTH_LONG).show();
			MsgItem item = new MsgItem();
			item.setUser1(app.Customer.userID);
			item.setUser2(receiver.getId());
			item.setTime(System.currentTimeMillis());
			item.setIsSend(MsgItem.SEND);
			item.setStatus(MsgItem.STATUS_READ);
			if (path.endsWith(".amr")) {
				item.setType(MsgItem.TYPE_VOICE);
			} else {
				item.setType(MsgItem.TYPE_IMAGE);
			}
			item.setContent(path);
			item.save();
			msgList.add(item);
			adapter.notifyDataSetChanged();
			// listView.setSelection(msgList.size());
			listView.smoothScrollToPosition(listView.getCount() - 1);
		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case 1111:
			if (resultCode == 0) {
				Toast.makeText(this, "没有选中任何文件！", Toast.LENGTH_SHORT).show();
				return;
			}
			Log.e("resultCode", "resultCode=" + resultCode);
			Uri uri = data.getData();
			if (uri != null) {
				sendFile(uri);
			}
			break;
		default:
			break;
		}
	}

	/**
	 * 发送文件
	 * 
	 * @Title: sendFile
	 * @param uri
	 * @return void
	 */
	private void sendFile(Uri uri) {
		File file = SimpleUtils.Uri2File(this, uri);
		sendFile(file);
	}

	private void sendFile(File file) {
		SendFileTask task = new SendFileTask();
		task.execute(file);
	}

	class MessageReciver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			MsgItem item = (MsgItem) intent.getExtras().getSerializable(
					"message");
			Log.e(TAG, item.getContent());
			if (receiver == null) {
				receiver = new Role();
				receiver.setId(item.getUser2());
				receiver.setRole(Role.ROLE_DOCTOR);
				tv.setText(item.getContent());
			}
			// if(item.getType()==MsgItem.TYPE_INQUIRY){
			//
			// }else if(item.getType()==MsgItem.TYPE_REPORT){
			//
			// }else{
			//
			// }
			msgList.add(item);
			adapter.notifyDataSetChanged();
			// listView.setSelection(msgList.size());
			listView.smoothScrollToPosition(listView.getCount() - 1);
		}
	}

	@SuppressLint("InlinedApi")
	private void recordVice(String path) {
		if (SimpleUtils.isExtraStorageEnable()) {

			recorder = new MediaRecorder();
			recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
			recorder.setOutputFormat(MediaRecorder.OutputFormat.AMR_NB);
			recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
			recorder.setOutputFile(path);
			// recorder.setAudioSamplingRate();
			try {
				recorder.prepare();
			} catch (IllegalStateException e) {
				Toast.makeText(this, "录音失败！", Toast.LENGTH_SHORT).show();
				e.printStackTrace();
				return;
			} catch (IOException e) {
				Toast.makeText(this, "存储错误,录音失败！", Toast.LENGTH_SHORT).show();
				e.printStackTrace();
				return;
			}
			recorder.start();
		} else {
			Toast.makeText(this, "SD卡不可用,无法录音！", Toast.LENGTH_SHORT).show();
		}
	}

}
