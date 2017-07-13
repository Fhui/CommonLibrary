package com.example.huifeng.library.custom_widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.huifeng.library.R;


/**
 * 快速索引栏
 * Created by ShineF on 2017/6/21 0021.
 */
public class SectionBar extends View {


    /**
     * 画笔
     */
    private Paint paint = new Paint();

    /**
     * 选中的字母索引
     */
    private int index = -1;

    /**
     * 字母默认颜色
     */
    private int defaultColor = Color.BLACK;

    /**
     * 字母选中颜色
     */
    private int chooseColor = Color.MAGENTA;

    /**
     * 选中背景颜色
     */
    private int chooseBackgroundColor = Color.LTGRAY;

    /**
     * 是否触摸
     */
    private boolean isTouch;

    /**
     * 字母字体大小
     */
    private int textSize;

    private int padding;

    private Rect rectBound;

    /**
     * 字母改变监听
     */
    private OnTouchLetterChangeListenner onTouchLetterChangeListenner;

    /**
     * 字母数组
     */
    private String[] letters = {"A", "B", "C", "D", "E", "F", "G",
            "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
            "U", "V", "W", "X", "Y", "Z", "#"};

    public SectionBar(Context context) {
        this(context, null);
    }

    public SectionBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SectionBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        padding = (int) (14 * getResources().getDisplayMetrics().density);
        rectBound = new Rect();
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.section_bar);
        textSize = a.getDimensionPixelSize(R.styleable.section_bar_android_textSize, (int) getResources().getDimension(R.dimen.common_dimen_s9_dp10));
        defaultColor = a.getColor(R.styleable.section_bar_android_textColor, getResources().getColor(R.color.common_color_c1_0971ce));
        chooseColor = a.getColor(R.styleable.section_bar_section_chooseTextColor, getResources().getColor(R.color.common_btn_color_0869c2));
        chooseBackgroundColor = a.getColor(R.styleable.section_bar_section_chooseBackgroundColor, Color.TRANSPARENT);
        a.recycle();
    }

    /**
     * 设置字母默认色
     *
     * @param color 默认颜色
     */
    public void setDefaultColor(int color) {
        this.defaultColor = color;
    }

    /**
     * 设置字母选中色
     *
     * @param color 选中颜色
     */
    public void setChooseColor(int color) {
        this.chooseColor = color;

    }

    /**
     * 设置选中时控件的背景色
     *
     * @param color 选中时背景色
     */
    public void setChooseBacegroundColor(int color) {
        this.chooseBackgroundColor = color;

    }

    /**
     * 文本字体大小  单位：dp
     *
     * @param size 大小
     */
    public void setTextSize(int size) {
        this.textSize = size;
    }


    /**
     * 获得section数组
     *
     * @return 返回索引数组
     */
    public String[] getLetters() {
        return letters;
    }

    /**
     * 设置字母数据
     *
     * @param letters 字母数组
     */
    public void setLetters(String[] letters) {
        this.letters = letters;
    }

    /**
     * 设置字母改变回调监听
     *
     * @param onTouchLetterChangeListenner 监听
     */
    public void setOnTouchLetterChangeListenner(OnTouchLetterChangeListenner onTouchLetterChangeListenner) {
        this.onTouchLetterChangeListenner = onTouchLetterChangeListenner;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        paint.setTextSize(textSize);
        paint.getTextBounds("#", 0, 1, rectBound);
        int w = rectBound.width() + padding;
        int h = rectBound.height() + padding;
        int defaultWidth = getPaddingLeft() + w + getPaddingRight();
        int defaultHeight = getPaddingTop() + h * letters.length + getPaddingBottom();
        int width = measureHandler(widthMeasureSpec, defaultWidth);
        int height = measureHandler(heightMeasureSpec, defaultHeight);
        setMeasuredDimension(width, height);
    }

    private int measureHandler(int measureSpec, int defaultSize) {
        int result = defaultSize;
        int measureMode = MeasureSpec.getMode(measureSpec);
        int measureSize = MeasureSpec.getSize(measureSpec);
        if (measureMode == MeasureSpec.EXACTLY) {
            result = measureSize;
        } else if (measureMode == MeasureSpec.AT_MOST) {
            result = Math.min(defaultSize, measureSize);
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        int len = letters.length;
        int singleHeight = height / len;
        //画字母
        for (int i = 0; i < len; i++) {
            paint.setTypeface((Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD)));
            paint.setTextAlign(Paint.Align.CENTER);
            paint.setAntiAlias(true);
            paint.setTextSize(textSize);
            if (i == index) {//选中时的画笔颜色
                paint.setColor(chooseColor);
            } else {//未选中时的画笔颜色
                paint.setColor(defaultColor);
            }
            float x = width / 2;
            float y = singleHeight * (i + 1) - paint.measureText(letters[i]) / 2;
            canvas.drawText(letters[i], x, y, paint);
            paint.reset();
        }
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {

        //当前选中字母的索引
        final int index = (int) (event.getY() / getHeight() * letters.length);
        //老的索引
        int oldIndex = this.index;

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                isTouch = true;
                if (index != oldIndex && index >= 0 && index < letters.length) {
                    this.index = index;
                    if (onTouchLetterChangeListenner != null) {//监听回调
                        onTouchLetterChangeListenner.onTouchLetterChange(true, letters[index]);
                    }
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                isTouch = true;
                if (index != oldIndex && index >= 0 && index < letters.length) {
                    this.index = index;
                    if (onTouchLetterChangeListenner != null) {//监听回调
                        onTouchLetterChangeListenner.onTouchLetterChange(true, letters[index]);
                    }
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
                isTouch = false;
                if (index >= 0 && index < letters.length) {
                    if (onTouchLetterChangeListenner != null) {//监听回调
                        onTouchLetterChangeListenner.onTouchLetterChange(false, letters[index]);
                    }
                }
                this.index = -1;
                invalidate();
                break;
        }
        return true;
    }

    /**
     * 字母改变监听接口
     */
    public interface OnTouchLetterChangeListenner {

        void onTouchLetterChange(boolean isTouch, String letter);
    }

}
