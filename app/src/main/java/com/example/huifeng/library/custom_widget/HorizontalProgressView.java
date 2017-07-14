package com.example.huifeng.library.custom_widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.ProgressBar;

import com.example.huifeng.library.R;
import com.example.huifeng.library.utils.ConvertUtils;

/**
 * 水平自定义progressbar
 * Created by ShineF on 2017/7/13 0013.
 */

public class HorizontalProgressView extends ProgressBar {

    private static final int RIGHT = 0; //文字在右边

    private static final int TOP = 1; //文字在上边

    private static final int FOLLOW = -1; //文字跟随progress动

    private Paint mTextPaint, mNormalPaint, mReachPaint;

    private int mNormalBarColor = getResources().getColor(R.color.common_color_c13_ececec); //progress没有选中的颜色

    private int mReachBarColor = getResources().getColor(R.color.common_color_c1_0971ce); //progress选中颜色

    private int mTextColor = getResources().getColor(R.color.common_color_c7_333333); //文字颜色

    private int mProgressHeight = 5;

    private Context mContext;

    private int mProgressStyle = FOLLOW;


    public HorizontalProgressView(Context context) {
        this(context, null);
    }

    public HorizontalProgressView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HorizontalProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.HorizontalProgressView);
        mProgressStyle = typedArray.getInt(R.styleable.HorizontalProgressView_progressTextPosition, FOLLOW);
        typedArray.recycle();
        int mTextSize = 12;
        mTextPaint = createTextPaint(mTextColor, ConvertUtils.dip2px(context, mTextSize));
        mNormalPaint = createLinePaint(mNormalBarColor, Paint.Style.FILL, ConvertUtils.dip2px(context, mProgressHeight));
        mReachPaint = createLinePaint(mReachBarColor, Paint.Style.FILL, ConvertUtils.dip2px(context, mProgressHeight));
    }

    private Paint createLinePaint(int mNormalBarColor, Paint.Style fill, int width) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        paint.setStyle(fill);
        paint.setStrokeWidth(width);
        paint.setColor(mNormalBarColor);
        return paint;
    }

    public Paint createTextPaint(int color, int textSize) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        paint.setColor(color);
        paint.setTextSize(textSize);
        return paint;
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        String mSign = "%";
        String text = getProgress() + mSign;
        canvas.save();
        canvas.translate(0, getHeight() / 2);
        float textWidth = mTextPaint.measureText(text);
        float textHeight = mTextPaint.descent() + mTextPaint.ascent();
        float mPaddingLeft = 34;
        float totalLineWidth = getMeasuredWidth() - 2 * ConvertUtils.dip2px(mContext, mPaddingLeft);
        float reachWidth = getProgress() * 1.0f / getMax() * totalLineWidth;
        canvas.drawLine(mPaddingLeft, 0, mPaddingLeft + totalLineWidth, 0, mNormalPaint);
        canvas.drawLine(mPaddingLeft, 0, mPaddingLeft + reachWidth, 0, mReachPaint);
        float mTextOffset = 5;
        float mTextRightOffset = 9;
        switch (mProgressStyle) {
            case RIGHT:
                canvas.drawText(text, mPaddingLeft + totalLineWidth + ConvertUtils.dip2px(mContext, mTextRightOffset), 0 - textHeight / 2, mTextPaint);
                break;
            case TOP:
                canvas.drawText(text, mPaddingLeft + totalLineWidth - textWidth, 0 - ConvertUtils.dip2px(mContext, mProgressHeight / 2 + mTextOffset), mTextPaint);
                break;
            case FOLLOW:
                canvas.drawText(text, mPaddingLeft + reachWidth - textWidth / 2, 0 - ConvertUtils.dip2px(mContext, mProgressHeight / 2 + mTextOffset), mTextPaint);
                break;
            default:
                break;
        }
        canvas.restore();
    }
}
