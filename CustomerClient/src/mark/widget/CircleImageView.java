/**  
 * @Title: CircleImageView.java 
 * @Package mark.widget 
 * @author 王帅
 * @date 2015年3月11日 下午1:19:24  
 */
package mark.widget;

import mark.utils.BitmapTool;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

public class CircleImageView extends ImageView {
	private Paint paint;// = new Paint();

	public CircleImageView(Context context) {
		super(context);
		init();
	}

	public CircleImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public CircleImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	private void init() {
		paint = new Paint();
		// paint.setAntiAlias(true);
		// paint.setColor(Color.BLACK);
		// paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
	}

	@Override
	protected void onDraw(Canvas canvas) {

		Drawable drawable = getDrawable();
		if (null != drawable) {
			Bitmap bitmap = BitmapTool.zoomImg(
					((BitmapDrawable) drawable).getBitmap(), getWidth(),
					getHeight());
			Bitmap b = toRoundCorner(bitmap, 14);
			final Rect rect = new Rect(0, 0, getWidth(), getHeight());
			paint.reset();
			canvas.drawBitmap(b, rect, rect, paint);
			// canvas.drawBitmap(bitmap, 0, 0, paint);
		} else {
			super.onDraw(canvas);
		}
		/*
		 * Drawable drawable = getDrawable(); if (drawable == null) { return; }
		 * 
		 * if (getWidth() == 0 || getHeight() == 0) { return; } Bitmap b =
		 * ((BitmapDrawable)drawable).getBitmap() ; Bitmap bitmap =
		 * b.copy(Bitmap.Config.ARGB_8888, true); int r =
		 * Math.min(getWidth(),getHeight()); Bitmap roundBitmap =
		 * getCroppedBitmap(bitmap, r); canvas.drawBitmap(roundBitmap,
		 * getWidth()/2 -r/2, getHeight()/2 -r/2, null);
		 */
	}

	private Bitmap toRoundCorner(Bitmap bitmap, int pixels) {
		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
				bitmap.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(output);

		final int color = 0xff424242;
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		int w = bitmap.getWidth();
		int h = bitmap.getHeight();
		int r = Math.min(w / 2, h / 2);
		canvas.drawCircle(w / 2, h / 2, r, paint);
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);
		return output;
	}

	/*
	 * 对Bitmap裁剪，使其变成圆形，这步最关键
	 */
	public static Bitmap getCroppedBitmap(Bitmap bmp, int radius) {
		Bitmap sbmp;
		if (bmp.getWidth() != radius || bmp.getHeight() != radius)
			sbmp = Bitmap.createScaledBitmap(bmp, radius, radius, false);
		else
			sbmp = bmp;

		Bitmap output = Bitmap.createBitmap(sbmp.getWidth(), sbmp.getHeight(),
				Bitmap.Config.ARGB_8888);
		final Rect rect = new Rect(0, 0, sbmp.getWidth(), sbmp.getHeight());

		Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setFilterBitmap(true);
		paint.setDither(true);
		paint.setColor(Color.parseColor("#BAB399"));
		Canvas c = new Canvas(output);
		c.drawARGB(0, 0, 0, 0);
		c.drawCircle(sbmp.getWidth() / 2 + 0.7f, sbmp.getHeight() / 2 + 0.7f,
				sbmp.getWidth() / 2 + 0.1f, paint);
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		c.drawBitmap(sbmp, rect, rect, paint);
		return output;
	}

}
