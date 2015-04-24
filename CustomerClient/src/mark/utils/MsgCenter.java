package mark.utils;

import java.util.HashMap;

import android.os.Handler;
import android.os.Message;

/**
 * @author cheng 消息中心
 * 
 */
public class MsgCenter {
	private static MsgCenter msgCenter;
	private HashMap<String, Handler> mhandlerMap = new HashMap<String, Handler>();
	private Handler mhandler;

	private MsgCenter() {
	}

	public static MsgCenter getInstance() {
		if (msgCenter == null) {
			msgCenter = new MsgCenter();
		}
		return msgCenter;
	}

	public void addHandler(String name, Handler handler) {
		mhandlerMap.put(name, handler);
	}

	public void removeHandler(String name) {
		mhandlerMap.remove(name);
	}

	public void clear() {
		mhandlerMap.clear();
	}

	public void sendMsg(String name, Message message) {
		for (String s : mhandlerMap.keySet()) {
			if (s.equals(name)) {
				mhandler = mhandlerMap.get(name);
				mhandler.sendMessage(message);
			}
		}
	}
}
