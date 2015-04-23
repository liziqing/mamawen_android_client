/**  
 * @Title: IMservice.java 
 * @Package cn.net_show.doctor.service 
 * @author 王帅
 * @date 2015年2月28日 下午1:03:32  
 */ 
package cn.net_show.customer.service;

import mark.utils.NetworkStateTool;

import org.jivesoftware.smack.ConnectionListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;

import cn.net_show.customer.MyApplication;
import cn.net_show.customer.util.XmppTool;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;


/** 
 * @ClassName: IMservice 
 * @author 王帅
 * @date 2015年2月28日 下午1:03:32  
 */
public class IMservice extends Service {
	private MyApplication app;
	
	private static final String TAG="IMconnection";
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		app = (MyApplication) getApplication();
		if(NetworkStateTool.isNetworkAvailable(this)){
			new Thread(){
				@Override
				public void run(){
					bindIMConnection();
				}
			}.start();
			
		}else{
			XmppTool.closeConnection();
		}
		return super.onStartCommand(intent, flags, startId);
	}
	
	
	private void bindIMConnection(){
		XMPPConnection conn=XmppTool.getConnection(getApplication());
		//判断IM是否已登陆
		if(conn.isAuthenticated()){
			return;
		}
		//判断应用是否已登陆
		if(!MyApplication.isLogin){
			return; 
		}
		try {
			//登陆添加监听
			app=(MyApplication) getApplication();
			XmppTool.login(app.Customer.jid, "123456",this);
			
		} catch (XMPPException e) {
			Log.e("XMPP","IM Login failed");
			e.printStackTrace();
			return;
		}
		ConnectionListener conListener =  new ConnectionListener(){

			@Override
			public void reconnectionSuccessful() {
				Log.i(TAG, "重新连接成功！");
			}
			
			@Override
			public void reconnectionFailed(Exception e) {
				Log.e(TAG, "重连失败："+e.getMessage());
			}
			
			@Override
			public void reconnectingIn(int s) {
				Log.i(TAG, "正在重新连接:"+s);
			}
			
			@Override
			public void connectionClosedOnError(Exception e) {
				Log.e(TAG, "关闭连接异常："+e.getMessage());
				//con.connect();
			}
			
			@Override
			public void connectionClosed() {
				Log.i(TAG, "关闭连接成功!");
			}
		};
		conn.addConnectionListener(conListener);
	}
}
