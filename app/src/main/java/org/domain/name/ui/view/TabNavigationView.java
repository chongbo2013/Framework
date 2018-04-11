package org.domain.name.ui.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.lang.reflect.Field;

/**
 * 2018/3/2
 * By Liux
 * lx0758@qq.com
 */

public class TabNavigationView extends BottomNavigationView{

    private static final int[][] STATES = new int[][]{
            new int[]{-android.R.attr.state_checked},
            new int[]{android.R.attr.state_checked}
    };

    private ViewPager mViewPager;
    private NavigationViewOnPageChangeListener mNavigationViewOnPageChangeListener;
    private ViewPagerOnNavigationItemSelectedListener mViewPagerOnNavigationItemSelectedListener = new ViewPagerOnNavigationItemSelectedListener(this);

    public TabNavigationView(Context context) {
        super(context);
    }

    public TabNavigationView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TabNavigationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        setShiftMode(false);
    }

    /**
     * 设置 ICON 切换状态的颜色
     * @param unchecked
     * @param checked
     */
    public void setItemIconTintList(int unchecked, int checked) {
        ColorStateList colorStateList = getColorStateList(unchecked, checked);
        super.setItemIconTintList(colorStateList);
    }

    /**
     * 设置 ICON 切换状态的尺寸
     * @param size
     */
    public void setItemIconSize(float size) {
        setItemIconSize(TypedValue.COMPLEX_UNIT_DIP, size);
    }

    /**
     * 设置 ICON 切换状态的尺寸
     * @param unit
     * @param size
     */
    public void setItemIconSize(int unit, float size) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) getChildAt(0);
        try {
            size = TypedValue.applyDimension(unit, size, getContext().getResources().getDisplayMetrics());
            Field iconField = BottomNavigationItemView.class.getDeclaredField("mIcon");
            iconField.setAccessible(true);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                ImageView imageView = (ImageView) iconField.get(item);
                ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
                layoutParams.width = (int) size;
                layoutParams.height = (int) size;
            }
            iconField.setAccessible(false);
            menuView.requestLayout();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置 文本 切换状态的颜色
     * @param unchecked
     * @param checked
     */
    public void setItemTextColor(int unchecked, int checked) {
        ColorStateList colorStateList = getColorStateList(unchecked, checked);
        super.setItemTextColor(colorStateList);
    }

    /**
     * 设置 文本 切换状态的尺寸
     * @param unchecked
     * @param checked
     */
    public void setItemTextSize(float unchecked, float checked) {
        setItemTextSize(TypedValue.COMPLEX_UNIT_SP, unchecked, checked);
    }

    /**
     * 设置 文本 切换状态的尺寸
     * @param unit
     * @param unchecked
     * @param checked
     */
    @SuppressLint("RestrictedApi")
    public void setItemTextSize(int unit, float unchecked, float checked) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) getChildAt(0);
        try {
            float scale = getContext().getResources().getDisplayMetrics().density;
            int shiftAmount = (int) ((unchecked - checked) * scale + 0.5f);
            Field smallLabelField = BottomNavigationItemView.class.getDeclaredField("mSmallLabel");
            Field largeLabelField = BottomNavigationItemView.class.getDeclaredField("mLargeLabel");
            Field shiftAmountField = BottomNavigationItemView.class.getDeclaredField("mShiftAmount");
            Field scaleUpFactorField = BottomNavigationItemView.class.getDeclaredField("mScaleUpFactor");
            Field scaleDownFactorField = BottomNavigationItemView.class.getDeclaredField("mScaleDownFactor");
            smallLabelField.setAccessible(true);
            largeLabelField.setAccessible(true);
            shiftAmountField.setAccessible(true);
            scaleUpFactorField.setAccessible(true);
            scaleDownFactorField.setAccessible(true);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                TextView smallLabel = (TextView) smallLabelField.get(item);
                TextView largeLabel = (TextView) largeLabelField.get(item);
                smallLabel.setTextSize(unit, unchecked);
                largeLabel.setTextSize(unit, checked);
                shiftAmountField.setInt(item, shiftAmount);
                scaleUpFactorField.setFloat(item, 1f * checked / unchecked);
                scaleDownFactorField.setFloat(item, 1f * unchecked / checked);
                // set once again checked value, so view will be updated
                item.setChecked(item.getItemData().isChecked());
            }
            smallLabelField.setAccessible(false);
            largeLabelField.setAccessible(false);
            shiftAmountField.setAccessible(false);
            scaleUpFactorField.setAccessible(false);
            scaleDownFactorField.setAccessible(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置是否有位移切换效果
     * @param shiftMode
     */
    @SuppressLint("RestrictedApi")
    public void setShiftMode(boolean shiftMode) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, shiftMode);
            shiftingMode.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                item.setShiftingMode(false);
                // set once again checked value, so view will be updated
                item.setChecked(item.getItemData().isChecked());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置选中某个条目
     * @param index
     */
    public void setSelectedItemIndex(int index) {
        int size = getMenu().size();
        if (index < 0 || index >= size) return;
        setSelectedItemId(getMenu().getItem(index).getItemId());
    }

    /**
     * 和 ViewPager 进行绑定
     * @param viewPager
     */
    public void setupWithViewPager(ViewPager viewPager) {
        if (mViewPager != null) {
            // If we've already been setup with a ViewPager, remove us from it
            if (mNavigationViewOnPageChangeListener != null) {
                mViewPager.removeOnPageChangeListener(mNavigationViewOnPageChangeListener);
            }

            if (mViewPagerOnNavigationItemSelectedListener != null) {
                // If we already have a tab selected listener for the ViewPager, remove it
                mViewPagerOnNavigationItemSelectedListener.setViewPager(null);
            }
        }

        if (viewPager != null) {
            mViewPager = viewPager;

            // Add our custom OnPageChangeListener to the ViewPager
            if (mNavigationViewOnPageChangeListener == null) {
                mNavigationViewOnPageChangeListener = new NavigationViewOnPageChangeListener(this);
            }
            viewPager.addOnPageChangeListener(mNavigationViewOnPageChangeListener);

            // Now we'll add a tab selected listener to set ViewPager's current item
            mViewPagerOnNavigationItemSelectedListener.setViewPager(viewPager);
            super.setOnNavigationItemSelectedListener(mViewPagerOnNavigationItemSelectedListener);
        } else {
            // We've been given a null ViewPager so we need to clear out the internal state,
            // listeners and observers
            mViewPager = null;
        }
    }

    @Override
    public void setOnNavigationItemSelectedListener(OnNavigationItemSelectedListener listener) {
        mViewPagerOnNavigationItemSelectedListener.setOnNavigationItemSelectedListener(listener);
    }

    private ColorStateList getColorStateList(int unchecked, int checked) {
        int[] colors = new int[]{unchecked, checked};
        return new ColorStateList(STATES, colors);
    }

    private static class NavigationViewOnPageChangeListener implements ViewPager.OnPageChangeListener {
        private final WeakReference<TabNavigationView> mTabNavigationViewRef;

        public NavigationViewOnPageChangeListener(TabNavigationView tabNavigationView) {
            mTabNavigationViewRef = new WeakReference<>(tabNavigationView);
        }

        @Override
        public void onPageScrollStateChanged(final int state) {

        }

        @Override
        public void onPageScrolled(final int position, final float positionOffset,
                                   final int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(final int position) {
            final TabNavigationView tabNavigationView = mTabNavigationViewRef.get();
            if (tabNavigationView != null) {
                tabNavigationView.setSelectedItemIndex(position);
            }
        }
    }

    private static class ViewPagerOnNavigationItemSelectedListener implements OnNavigationItemSelectedListener {
        private TabNavigationView mTabNavigationView;

        private OnNavigationItemSelectedListener mOnNavigationItemSelectedListener;
        private ViewPager mViewPager;

        public ViewPagerOnNavigationItemSelectedListener(TabNavigationView tabNavigationView) {
            mTabNavigationView = tabNavigationView;
        }

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            int index = -1, size = mTabNavigationView.getMenu().size();
            for (int i = 0; i < size; i++) {
                if (mTabNavigationView.getMenu().getItem(i).getItemId() == item.getItemId()) {
                    index = i;
                    break;
                }
            }

            if (mOnNavigationItemSelectedListener != null &&
                    !mOnNavigationItemSelectedListener.onNavigationItemSelected(item)) {
                return false;
            }

            if (mViewPager != null && index != -1) {
                mViewPager.setCurrentItem(index);
                return true;
            }
            return false;
        }

        public void setOnNavigationItemSelectedListener(OnNavigationItemSelectedListener listener) {
            mOnNavigationItemSelectedListener = listener;
        }

        public void setViewPager(ViewPager viewPager) {
            mViewPager = viewPager;
        }
    }
}
