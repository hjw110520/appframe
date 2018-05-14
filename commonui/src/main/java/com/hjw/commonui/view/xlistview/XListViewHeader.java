package com.hjw.commonui.view.xlistview;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hjw.commonui.R;

public class XListViewHeader extends LinearLayout {
    private LinearLayout mContainer;
    private ImageView mArrowImageView;
    private ImageView mProgressBar;
    private TextView mHintTextView;
    private int mState = STATE_NORMAL;
    private AnimationDrawable mAnimation;

    private Animation mRotateUpAnim;
    private Animation mRotateDownAnim;

    private final int ROTATE_ANIM_DURATION = 180;

    public final static int STATE_NORMAL = 0;
    public final static int STATE_READY = 1;
    public final static int STATE_REFRESHING = 2;

    public XListViewHeader(Context context) {
        super(context);
        initView(context);
    }

    /**
     * @param context
     * @param attrs
     */
    public XListViewHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        // 初始情况，设置下拉刷新view高度为0
        LayoutParams lp = new LayoutParams(
                LayoutParams.MATCH_PARENT, 0);
        mContainer = (LinearLayout) LayoutInflater.from(context).inflate(
                R.layout.xlistview_header, null);
        addView(mContainer, lp);
        setGravity(Gravity.BOTTOM);

        mArrowImageView = (ImageView) findViewById(R.id.xlistview_header_arrow);
        mHintTextView = (TextView) findViewById(R.id.xlistview_header_hint_textview);

        mProgressBar = (ImageView) findViewById(R.id.xlistview_header_progressbar);
        mAnimation = new AnimationDrawable();
        mAnimation.addFrame(getContext().getResources().getDrawable(R.mipmap.pull_icon_0001),150);
        mAnimation.addFrame(getContext().getResources().getDrawable(R.mipmap.pull_icon_0002),150);
        mAnimation.addFrame(getContext().getResources().getDrawable(R.mipmap.pull_icon_0003),150);
        mAnimation.addFrame(getContext().getResources().getDrawable(R.mipmap.pull_icon_0004),150);
        mAnimation.addFrame(getContext().getResources().getDrawable(R.mipmap.pull_icon_0005),150);
        mAnimation.addFrame(getContext().getResources().getDrawable(R.mipmap.pull_icon_0006),150);
        mAnimation.addFrame(getContext().getResources().getDrawable(R.mipmap.pull_icon_0007),150);
        mAnimation.addFrame(getContext().getResources().getDrawable(R.mipmap.pull_icon_0008),150);
        mAnimation.addFrame(getContext().getResources().getDrawable(R.mipmap.pull_icon_0009),150);
        mAnimation.addFrame(getContext().getResources().getDrawable(R.mipmap.pull_icon_0010),150);
        mAnimation.addFrame(getContext().getResources().getDrawable(R.mipmap.pull_icon_0011),150);
        mAnimation.addFrame(getContext().getResources().getDrawable(R.mipmap.pull_icon_0012),150);
        mAnimation.addFrame(getContext().getResources().getDrawable(R.mipmap.pull_icon_0013),150);
        mAnimation.addFrame(getContext().getResources().getDrawable(R.mipmap.pull_icon_0014),150);
        mAnimation.addFrame(getContext().getResources().getDrawable(R.mipmap.pull_icon_0015),150);
        mAnimation.setOneShot(false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            mProgressBar.setBackground(mAnimation);
        }
        if (mAnimation != null && !mAnimation.isRunning()) {
            mAnimation.start();
        }
        mRotateUpAnim = new RotateAnimation(0.0f, -180.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        mRotateUpAnim.setDuration(ROTATE_ANIM_DURATION);
        mRotateUpAnim.setFillAfter(true);
        mRotateDownAnim = new RotateAnimation(-180.0f, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        mRotateDownAnim.setDuration(ROTATE_ANIM_DURATION);
        mRotateDownAnim.setFillAfter(true);
    }

    public int getmState() {
        return mState;
    }

    public void setState(int state) {
        if (state == mState) return;

        if (state == STATE_REFRESHING) {    // 显示进度
            mArrowImageView.clearAnimation();
            mArrowImageView.setVisibility(View.GONE);
            mProgressBar.setVisibility(View.VISIBLE);
        } else {    // 显示箭头图片
            mArrowImageView.setVisibility(View.GONE);
            mProgressBar.setVisibility(View.INVISIBLE);
        }

        switch (state) {
            case STATE_NORMAL:
                if (mState == STATE_READY) {
                    mArrowImageView.startAnimation(mRotateDownAnim);
                }
                if (mState == STATE_REFRESHING) {
                    mArrowImageView.clearAnimation();
                }
                mHintTextView.setText(R.string.xlistview_header_hint_normal);
                break;
            case STATE_READY:
                if (mState != STATE_READY) {
                    mArrowImageView.clearAnimation();
                    mArrowImageView.startAnimation(mRotateUpAnim);
                    mHintTextView.setText(R.string.xlistview_header_hint_ready);
                }
                break;
            case STATE_REFRESHING:
                mHintTextView.setText(R.string.xlistview_header_hint_loading);
                break;
            default:
        }

        mState = state;
    }

    public void setVisiableHeight(int height) {
        if (height < 0)
            height = 0;
        LayoutParams lp = (LayoutParams) mContainer
                .getLayoutParams();
        lp.height = height;
        mContainer.setLayoutParams(lp);
    }

    public int getVisiableHeight() {
        return mContainer.getHeight();
    }

}
