package org.domain.name.ui.view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.view.NestedScrollingChild;
import android.support.v4.view.NestedScrollingChild2;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.NestedScrollingParent2;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewParent;

/**
 * 2018/3/9
 * By Liux
 * lx0758@qq.com
 */

public class NestedViewPager extends ViewPager implements NestedScrollingParent2, NestedScrollingChild2 {

    public NestedViewPager(@NonNull Context context) {
        super(context);
    }

    public NestedViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    // NestedScrollingParent

    @Override
    public boolean onStartNestedScroll(@NonNull View child, @NonNull View target, int axes) {
        NestedScrollingParent nestedScrollingParent = getCurrentNestedScrollingParent();
        if (nestedScrollingParent != null) {
            return nestedScrollingParent.onStartNestedScroll(child, target, axes);
        } else {
            return super.onStartNestedScroll(child, target, axes);
        }
    }

    @Override
    public void onNestedScrollAccepted(@NonNull View child, @NonNull View target, int axes) {
        NestedScrollingParent nestedScrollingParent = getCurrentNestedScrollingParent();
        if (nestedScrollingParent != null) {
            nestedScrollingParent.onNestedScrollAccepted(child, target, axes);
        } else {
            super.onNestedScrollAccepted(child, target, axes);
        }
    }

    @Override
    public void onStopNestedScroll(@NonNull View target) {
        NestedScrollingParent nestedScrollingParent = getCurrentNestedScrollingParent();
        if (nestedScrollingParent != null) {
            nestedScrollingParent.onStopNestedScroll(target);
        } else {
            super.onStopNestedScroll(target);
        }
    }

    @Override
    public void onNestedScroll(@NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        NestedScrollingParent nestedScrollingParent = getCurrentNestedScrollingParent();
        if (nestedScrollingParent != null) {
            nestedScrollingParent.onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
        } else {
            super.onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
        }
    }

    @Override
    public void onNestedPreScroll(@NonNull View target, int dx, int dy, @NonNull int[] consumed) {
        NestedScrollingParent nestedScrollingParent = getCurrentNestedScrollingParent();
        if (nestedScrollingParent != null) {
            nestedScrollingParent.onNestedPreScroll(target, dx, dy, consumed);
        } else {
            super.onNestedPreScroll(target, dx, dy, consumed);
        }
    }

    @Override
    public boolean onNestedFling(@NonNull View target, float velocityX, float velocityY, boolean consumed) {
        NestedScrollingParent nestedScrollingParent = getCurrentNestedScrollingParent();
        if (nestedScrollingParent != null) {
            return nestedScrollingParent.onNestedFling(target, velocityX, velocityY, consumed);
        } else {
            return super.onNestedFling(target, velocityX, velocityY, consumed);
        }
    }

    @Override
    public boolean onNestedPreFling(@NonNull View target, float velocityX, float velocityY) {
        NestedScrollingParent nestedScrollingParent = getCurrentNestedScrollingParent();
        if (nestedScrollingParent != null) {
            return nestedScrollingParent.onNestedPreFling(target, velocityX, velocityY);
        } else {
            return super.onNestedPreFling(target, velocityX, velocityY);
        }
    }

    @Override
    public int getNestedScrollAxes() {
        NestedScrollingParent nestedScrollingParent = getCurrentNestedScrollingParent();
        if (nestedScrollingParent != null) {
            return nestedScrollingParent.getNestedScrollAxes();
        } else {
            return super.getNestedScrollAxes();
        }
    }

    // NestedScrollingParent2

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean onStartNestedScroll(@NonNull View child, @NonNull View target, int axes, int type) {
        NestedScrollingParent2 nestedScrollingParent2 = getCurrentNestedScrollingParent2();
        if (nestedScrollingParent2 != null) {
            return nestedScrollingParent2.onStartNestedScroll(child, target, axes, type);
        } else {
            return super.onStartNestedScroll(child, target, axes);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onNestedScrollAccepted(@NonNull View child, @NonNull View target, int axes, int type) {
        NestedScrollingParent2 nestedScrollingParent2 = getCurrentNestedScrollingParent2();
        if (nestedScrollingParent2 != null) {
            nestedScrollingParent2.onNestedScrollAccepted(child, target, axes, type);
        } else {
            super.onNestedScrollAccepted(child, target, axes);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onStopNestedScroll(@NonNull View target, int type) {
        NestedScrollingParent2 nestedScrollingParent2 = getCurrentNestedScrollingParent2();
        if (nestedScrollingParent2 != null) {
            nestedScrollingParent2.onStopNestedScroll(target, type);
        } else {
            super.onStopNestedScroll(target);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onNestedScroll(@NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {
        NestedScrollingParent2 nestedScrollingParent2 = getCurrentNestedScrollingParent2();
        if (nestedScrollingParent2 != null) {
            nestedScrollingParent2.onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type);
        } else {
            super.onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onNestedPreScroll(@NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        NestedScrollingParent2 nestedScrollingParent2 = getCurrentNestedScrollingParent2();
        if (nestedScrollingParent2 != null) {
            nestedScrollingParent2.onNestedPreScroll(target, dx, dy, consumed, type);
        } else {
            super.onNestedPreScroll(target, dx, dy, consumed);
        }
    }

    // NestedScrollingChild

    @Override
    public void setNestedScrollingEnabled(boolean enabled) {
        NestedScrollingChild nestedScrollingChild = getCurrentNestedScrollingChild();
        if (nestedScrollingChild != null) {
            nestedScrollingChild.setNestedScrollingEnabled(enabled);
        } else {
            super.setNestedScrollingEnabled(enabled);
        }
    }

    @Override
    public boolean isNestedScrollingEnabled() {
        NestedScrollingChild nestedScrollingChild = getCurrentNestedScrollingChild();
        if (nestedScrollingChild != null) {
            return nestedScrollingChild.isNestedScrollingEnabled();
        } else {
            return super.isNestedScrollingEnabled();
        }
    }

    @Override
    public boolean startNestedScroll(int axes) {
        NestedScrollingChild nestedScrollingChild = getCurrentNestedScrollingChild();
        if (nestedScrollingChild != null) {
            return nestedScrollingChild.startNestedScroll(axes);
        } else {
            return super.startNestedScroll(axes);
        }
    }

    @Override
    public void stopNestedScroll() {
        NestedScrollingChild nestedScrollingChild = getCurrentNestedScrollingChild();
        if (nestedScrollingChild != null) {
            nestedScrollingChild.stopNestedScroll();
        } else {
            super.stopNestedScroll();
        }
    }

    @Override
    public boolean hasNestedScrollingParent() {
        NestedScrollingChild nestedScrollingChild = getCurrentNestedScrollingChild();
        if (nestedScrollingChild != null) {
            return nestedScrollingChild.hasNestedScrollingParent();
        } else {
            return super.hasNestedScrollingParent();
        }
    }

    @Override
    public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, @Nullable int[] offsetInWindow) {
        NestedScrollingChild nestedScrollingChild = getCurrentNestedScrollingChild();
        if (nestedScrollingChild != null) {
            return nestedScrollingChild.dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow);
        } else {
            return super.dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow);
        }
    }

    @Override
    public boolean dispatchNestedPreScroll(int dx, int dy, @Nullable int[] consumed, @Nullable int[] offsetInWindow) {
        NestedScrollingChild nestedScrollingChild = getCurrentNestedScrollingChild();
        if (nestedScrollingChild != null) {
            return nestedScrollingChild.dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow);
        } else {
            return super.dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow);
        }
    }

    @Override
    public boolean dispatchNestedFling(float velocityX, float velocityY, boolean consumed) {
        NestedScrollingChild nestedScrollingChild = getCurrentNestedScrollingChild();
        if (nestedScrollingChild != null) {
            return nestedScrollingChild.dispatchNestedFling(velocityX, velocityY, consumed);
        } else {
            return super.dispatchNestedFling(velocityX, velocityY, consumed);
        }
    }

    @Override
    public boolean dispatchNestedPreFling(float velocityX, float velocityY) {
        NestedScrollingChild nestedScrollingChild = getCurrentNestedScrollingChild();
        if (nestedScrollingChild != null) {
            return nestedScrollingChild.dispatchNestedPreFling(velocityX, velocityY);
        } else {
            return super.dispatchNestedPreFling(velocityX, velocityY);
        }
    }

    // NestedScrollingChild2

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean startNestedScroll(int axes, int type) {
        NestedScrollingChild2 nestedScrollingChild2 = getCurrentNestedScrollingChild2();
        if (nestedScrollingChild2 != null) {
            return nestedScrollingChild2.startNestedScroll(axes, type);
        } else {
            return super.startNestedScroll(axes);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void stopNestedScroll(int type) {
        NestedScrollingChild2 nestedScrollingChild2 = getCurrentNestedScrollingChild2();
        if (nestedScrollingChild2 != null) {
            nestedScrollingChild2.stopNestedScroll(type);
        } else {
            super.stopNestedScroll();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean hasNestedScrollingParent(int type) {
        NestedScrollingChild2 nestedScrollingChild2 = getCurrentNestedScrollingChild2();
        if (nestedScrollingChild2 != null) {
            return nestedScrollingChild2.hasNestedScrollingParent(type);
        } else {
            return super.hasNestedScrollingParent();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, @Nullable int[] offsetInWindow, int type) {
        NestedScrollingChild2 nestedScrollingChild2 = getCurrentNestedScrollingChild2();
        if (nestedScrollingChild2 != null) {
            return nestedScrollingChild2.dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow, type);
        } else {
            return super.dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean dispatchNestedPreScroll(int dx, int dy, @Nullable int[] consumed, @Nullable int[] offsetInWindow, int type) {
        NestedScrollingChild2 nestedScrollingChild2 = getCurrentNestedScrollingChild2();
        if (nestedScrollingChild2 != null) {
            return nestedScrollingChild2.dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow, type);
        } else {
            return super.dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow);
        }
    }

    private NestedScrollingParent getCurrentNestedScrollingParent() {
        ViewParent viewParent = getParent();
        if (viewParent == null) return null;
        if (viewParent instanceof NestedScrollingParent) return (NestedScrollingParent) viewParent;
        return null;
    }

    private NestedScrollingParent2 getCurrentNestedScrollingParent2() {
        ViewParent viewParent = getParent();
        if (viewParent == null) return null;
        if (viewParent instanceof NestedScrollingParent2) return (NestedScrollingParent2) viewParent;
        return null;
    }

    private NestedScrollingChild getCurrentNestedScrollingChild() {
        if (getAdapter() == null) return null;
        if (getAdapter().getCount() == 0) return null;
        View view = getChildAt(getCurrentItem());
        if (view == null) return null;
        if (view instanceof NestedScrollingChild) return (NestedScrollingChild) view;
        return null;
    }

    private NestedScrollingChild2 getCurrentNestedScrollingChild2() {
        if (getAdapter() == null) return null;
        if (getAdapter().getCount() == 0) return null;
        View view = getChildAt(getCurrentItem());
        if (view == null) return null;
        if (view instanceof NestedScrollingChild2) return (NestedScrollingChild2) view;
        return null;
    }
}
