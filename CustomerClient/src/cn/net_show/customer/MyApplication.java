/**  
 * @Title: MyApplication.java 
 * @Package cn.net_show.customer.activity 
 * @author 王帅
 * @date 2015年3月2日 下午2:28:24  
 */
package cn.net_show.customer;

import org.litepal.LitePalApplication;
import org.litepal.tablemanager.Connector;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import cn.net_show.customer.model.UserInfo;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * @ClassName: MyApplication
 * @author 王帅
 * @date 2015年3月2日 下午2:28:24
 */
public class MyApplication extends LitePalApplication {
	public static final String ServerUrl = "http://115.159.49.31:9000";
	public static final String MARK = "iphone";
	public static boolean isLogin = false;
	public UserInfo Customer;

	@Override
	public void onCreate() {
		// Ioc.getIoc().init(this);//必须在super.onCreate()之前调用
		super.onCreate();
		SQLiteDatabase db = Connector.getDatabase();
		initImageLoader(this);
	}

	public static void initImageLoader(Context context) {
		// This configuration tuning is custom. You can tune every option, you
		// may tune some of them,
		// or you can create default configuration by
		// ImageLoaderConfiguration.createDefault(this);
		// method.
		ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(
				context);
		config.threadPriority(Thread.NORM_PRIORITY - 2);
		config.denyCacheImageMultipleSizesInMemory();
		config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
		config.diskCacheSize(50 * 1024 * 1024); // 50 MiB
		config.tasksProcessingOrder(QueueProcessingType.LIFO);
		config.writeDebugLogs(); // Remove for release app
		// Initialize ImageLoader with configuration.
		ImageLoader.getInstance().init(config.build());
	}
}
