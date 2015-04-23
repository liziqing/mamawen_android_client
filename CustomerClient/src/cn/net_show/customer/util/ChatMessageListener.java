/**  
 * @Title: ChartMessageListener.java 
 * @Package cn.net_show.doctor.utils 
 * @author 王帅
 * @date 2015年3月1日 下午1:23:14  
 */ 
package cn.net_show.customer.util;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.packet.Message;

import com.google.gson.Gson;

import cn.net_show.customer.MyApplication;
import cn.net_show.customer.model.MsgItem;
import cn.net_show.customer.model.ServerMsgItem;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/** 
 * @ClassName: ChartMessageListener 
 * @author 王帅
 * @date 2015年3月1日 下午1:23:14  
 */
public class ChatMessageListener implements MessageListener{
	private static final String TAG="IMconnection";
	private Context mContext;
	public static final String ACTION_MESSAGE_LISTENER= "cn.net_show.customer.message";
	/**
	 * @param mContext
	 */
	public ChatMessageListener(Context mContext) {
		super();
		this.mContext = mContext;
	}

	@Override
	public void processMessage(Chat chat, Message msg) {
		Log.i(TAG, "from:"+msg.getFrom());
		Log.i(TAG, "body:"+msg.getBody());	
		Log.i(TAG, "part:"+chat.getParticipant());
		Gson gson = new Gson();
		ServerMsgItem message = gson.fromJson(msg.getBody(),ServerMsgItem.class);
		if(message==null){
			Log.e(TAG, "message==null");
			return;
		}
		Log.e(TAG, "message!=null");
		MyApplication app = (MyApplication) mContext.getApplicationContext();
		MsgItem item = new MsgItem();
		item.setUser2(message.sender.getId());
		item.setUser1(app.Customer.userID);
		item.setTime(System.currentTimeMillis());
		item.setIsSend(MsgItem.RECEIVE);
		item.setStatus(MsgItem.STATUS_UNREAD);
		if(message.category==ServerMsgItem.TYPE_CHAT){
			item.setType(message.subCategory);
			item.setContent(message.content);
		}else if(message.category==ServerMsgItem.TYPE_INQUIRY){
			item.setType(MsgItem.TYPE_INQUIRY);
			item.setContent(message.sender.getName()+"医生为您接诊！");
		}else if(message.category==ServerMsgItem.TYPE_REPORT){
			item.setType(MsgItem.TYPE_REPORT);
			item.setContent(message.description+"&**&"+message.suggestion);
		}else{
			
		}
		Log.e(TAG, "type=="+item.getType());
		item.save();
		Intent i = new Intent(ACTION_MESSAGE_LISTENER);
		i.putExtra("message", item);
		mContext.sendBroadcast(i);
	}
	
}
