/**  
 * @Title: RecieverFileListener.java 
 * @Package cn.net_show.doctor.utils 
 * @author 王帅
 * @date 2015年3月9日 下午1:57:30  
 */ 
package cn.net_show.customer.util;

import java.io.File;
import java.io.IOException;

import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.filetransfer.FileTransferListener;
import org.jivesoftware.smackx.filetransfer.FileTransferRequest;
import org.jivesoftware.smackx.filetransfer.IncomingFileTransfer;

import cn.net_show.customer.model.MsgItem;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

/** 
 * @ClassName: RecieverFileListener 
 * @author 王帅
 * @date 2015年3月9日 下午1:57:30  
 */
public class ReceiverFileListener implements FileTransferListener {
	public static final String MESSAGE_ACTION="cn.net_show.fileReceiver";
	private Context mContext;
	protected static final String TAG ="FileReciever";
	/**
	 * @param mContext
	 */
	public ReceiverFileListener(Context mContext) {
		super();
		this.mContext = mContext;
	}

	@Override
	public void fileTransferRequest(FileTransferRequest requst) {
		Log.e("FILE","收到文件");
		ReceiveFileTask task = new ReceiveFileTask();
		task.execute(requst);
	}

	
	
	class ReceiveFileTask extends AsyncTask<FileTransferRequest, String, String> {
		private File mFile;
		private FileTransferRequest request;
		@Override
		protected String doInBackground(FileTransferRequest... params) {
			request = params[0];
			IncomingFileTransfer inFile = request.accept();
			File file = null;			
			try {
				file = new File(mContext.getExternalFilesDir(Environment.DIRECTORY_DCIM), inFile.getFileName());			
				Log.i("FILE","recieved size =" + inFile.getFileSize());
				Log.i("FILE","dir =" + file.getPath());
				if(!file.exists())
					file.createNewFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if(!file.canWrite()){
				return "存储出现错误！";
			}
			try {
				inFile.recieveFile(file);
			} catch (XMPPException e) {
				e.printStackTrace();
				return "接收中出现错误！";
			}
			mFile = file;			
			return "接收文件完毕！";
		}

		@Override
		protected void onPostExecute(String result) {
			Log.i("-----", "fileName:"+request.getFileName());
			Log.i("-----", "Requestor:"+request.getRequestor());
			Log.i("-----", "MimeType:"+request.getMimeType());
//			super.onPostExecute(result);
//			Toast.makeText(mContext, result, Toast.LENGTH_LONG).show();
//			
//			MsgItem item = new MsgItem();			
//			item.name=request.getRequestor().split("@")[0];
//			item.time=System.currentTimeMillis();
//			String mimeType = request.getMimeType();
//			if(mimeType.contains("image")){
//				item.type=MsgItem.TYPE_IMAGE;
//				//发送广播通知系统图库更新图片
//				Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
//				Uri uri = Uri.fromFile(mFile);
//				intent.setData(uri);
//				mContext.sendBroadcast(intent);
//			}else{
//				item.type=MsgItem.TYPE_VOICE;
//			}
//			
//			item.content=mFile.getAbsolutePath();
//			
//			Intent i = new Intent(MESSAGE_ACTION);
//			Bundle b = new Bundle();
//			b.putSerializable("message",item);
//			i.putExtras(b);
//			mContext.sendBroadcast(i);
			/*msgList.add(item);			
			adapter.notifyDataSetChanged();
			listView.setSelection(msgList.size());*/
		}
	}
}
