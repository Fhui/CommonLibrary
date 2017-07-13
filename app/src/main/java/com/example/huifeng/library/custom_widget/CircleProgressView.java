package com.example.huifeng.library.custom_widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ProgressBar;

import com.example.huifeng.library.R;
import com.example.huifeng.library.utils.ConvertUtils;

/**
 *  圆形进度条
 * Created by ShineF on 2017/7/10 0010.
 */

public class CircleProgressView extends ProgressBar {

    private int mBgColor = 0;
    private int mProColor = 0;
    private int mTextColor = 0;
    private int mTextSize = 0;
    private int mProgStyle = 0;
    private String mText = null;

    private static final int PROGRESS_STYLE_NORMAL = 0;
    private static final int PROGRESS_STYLE_CUSTOM_DESC = 1;
    private static final int PROGRESS_STYLE_NO_BACKGROUND = 2;

    private static final int DEFAULT_STROKE_WIDTH = 5;

    private final int mDefaultBgColor = getResources().getColor(R.color.common_color_c13_ececec);
    private final int mDefaultProgColor = getResources().getColor(R.color.common_color_c1cur_0869c2);
    private final int mDefaultTextColor = getResources().getColor(R.color.common_color_c1cur_0869c2);
    private final int mDefaultTextSize = 26;
    private final int mDefaultSymbolTextSize = 26;
    private int mStrokeWidth = 0;
    private int mDefaultRadius = 0;
    private int mRadius = 0;
    private RectF mRectF = null;
    private String mSignText = "%";

    private Paint mBgPaint, mTextPaint, mDescPaint, mProgressPaint;

    public CircleProgressView(Context context) {
        this(context, null);
    }

    public CircleProgressView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
        initData();
    }

    public void init(Context context, AttributeSet attrs){
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleProgressView);
        mBgColor = typedArray.getColor(R.styleable.CircleProgressView_backgroundColor, mDefaultBgColor);
        mProColor = typedArray.getColor(R.styleable.CircleProgressView_circleTextColor, mDefaultProgColor);
        mTextColor = typedArray.getColor(R.styleable.CircleProgressView_circleTextColor, mDefaultTextColor);
        mTextSize = typedArray.getDimensionPixelSize(R.styleable.CircleProgressView_circleTextSize, mDefaultTextSize);
        mProgStyle = typedArray.getInt(R.styleable.CircleProgressView_progressStyle, PROGRESS_STYLE_NORMAL);
        mText = typedArray.getString(R.styleable.CircleProgressView_circleText);
        typedArray.recycle();
    }

    public void initData(){
        mStrokeWidth = ConvertUtils.dip2px(getContext(), DEFAULT_STROKE_WIDTH);
        mBgPaint = createPaint(mBgColor, Paint.Style.STROKE, mStrokeWidth);
        mTextPaint = createTextPaint(mTextColor, mTextSize);
        mDescPaint = createTextPaint(mDefaultTextColor, ConvertUtils.dip2px(getContext(), mDefaultTextSize));
        switch (mProgStyle){
            case PROGRESS_STYLE_NORMAL:
                mProgressPaint = createNotRoundPaint(mProColor, Paint.Style.STROKE, mStrokeWidth);
                break;
            case PROGRESS_STYLE_CUSTOM_DESC:
                mProgressPaint = createPaint(mProColor, Paint.Style.STROKE, mStrokeWidth);
                break;
            case PROGRESS_STYLE_NO_BACKGROUND:
                mProgressPaint = createPaint(mProColor, Paint.Style.STROKE, mStrokeWidth);
                break;
        }
    }

    public Paint createPaint(int paintColor, Paint.Style style, int width){
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG|Paint.DITHER_FLAG);
        paint.setColor(paintColor);
        paint.setStrokeWidth(width);
        paint.setStyle(style);
        paint.setStrokeCap(Paint.Cap.ROUND);
        return paint;
    }

    private Paint createTextPaint(int paintColor, int textSize) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        paint.setColor(paintColor);
        paint.setTextSize(textSize);
        return paint;
    }

    private Paint createNotRoundPaint(int paintColor, Paint.Style style, int width) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        paint.setColor(paintColor);
        paint.setStyle(style);
        paint.setStrokeWidth(width);
        return paint;
    }

    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mDefaultRadius = Math.min(getMeasuredWidth(),  getMeasuredWidth())/ 2 - mStrokeWidth / 2;
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        int width = getMeasuredWidth()/2;
        int height = getMeasuredHeight()/2;
        if(mRadius == 0 || mRadius > mDefaultRadius){
            mRadius = mDefaultRadius;
        }
        if(mRectF == null){
            mRectF = new RectF(width - mRadius, height - mRadius, width + mRadius, height + mRadius);
        }
        switch (mProgStyle){
            case PROGRESS_STYLE_NORMAL:
                drawNormal(canvas, width, height);
                break;
            case PROGRESS_STYLE_CUSTOM_DESC:
                drawCustomDesc(canvas, width, height);
                break;
            case PROGRESS_STYLE_NO_BACKGROUND:
                drawNoBackground(canvas, width, height);
                break;
        }
    }

    public void drawNormal(Canvas canvas, int halfWidth, int halfHeight){
        canvas.drawArc(mRectF, -90, 360, false, mBgPaint);
        float sweepAngle = getProgress() * 1.0f / getMax() * 360;
        canvas.drawArc(mRectF, -90, sweepAngle, false, mProgressPaint);

        int progress = (int) (getProgress() * 1.0f / getMax() * 100);
        String processText = String.valueOf(progress);
        mTextPaint.setTextSize(ConvertUtils.dip2px(getContext(), mDefaultTextSize));
        float textWidth = mTextPaint.measureText(processText);
        float textHeight = mTextPaint.descent() + mTextPaint.ascent();
        canvas.drawText(processText, halfWidth-textWidth / 2, halfHeight-textHeight / 2, mTextPaint);

        mTextPaint.setTextSize(ConvertUtils.dip2px(getContext(), mDefaultSymbolTextSize));
        float percentTextWidth = mTextPaint.measureText(mSignText);
        float percentTextHeight = mTextPaint.descent() + mTextPaint.ascent();
        canvas.drawText(mSignText, halfWidth+textWidth / 2, halfHeight-textHeight / 2, mTextPaint);
    }

    public void drawCustomDesc(Canvas canvas, int halfWidth, int halfHeight){

        canvas.drawArc(mRectF, -90, 360, false, mBgPaint);
        float sweepAngle = getProgress() * 1.0f / getMax() * 360;
        canvas.drawArc(mRectF, -90, sweepAngle, false, mProgressPaint);

        int progress = (int) (getProgress() * 1.0f / getMax() * 100);
        String processText = String.valueOf(progress) + mSignText;
        mTextPaint.setTextSize(ConvertUtils.dip2px(getContext(), mDefaultTextSize));
        float textWidth = mTextPaint.measureText(processText);
        float textHeight = mTextPaint.descent() + mTextPaint.ascent();
        canvas.drawText(processText, halfWidth-textWidth / 2, halfHeight-textHeight / 2, mTextPaint);

        if(mText == null){
            return;
        }

        float descTextWidth = mDescPaint.measureText(mText);
        float descTextHeight = mDescPaint.descent() + mDescPaint.ascent();
        canvas.drawText(mText, halfWidth - descTextWidth/2, halfHeight-descTextHeight/2*3, mDescPaint);
    }

    public void drawNoBackground(Canvas canvas, int halfWidth, int halfHeight){
        float sweepAngle = getProgress() * 1.0f / getMax() * 360;
        canvas.drawArc(mRectF, -90, sweepAngle, false, mProgressPaint);
    }
}
