package android.support.v4.view;

import android.support.annotation.Nullable;

public interface NestedScrollingChild {
   void setNestedScrollingEnabled(boolean var1);

   boolean isNestedScrollingEnabled();

   boolean startNestedScroll(int var1);

   void stopNestedScroll();

   boolean hasNestedScrollingParent();

   boolean dispatchNestedScroll(int var1, int var2, int var3, int var4, @Nullable int[] var5);

   boolean dispatchNestedPreScroll(int var1, int var2, @Nullable int[] var3, @Nullable int[] var4);

   boolean dispatchNestedFling(float var1, float var2, boolean var3);

   boolean dispatchNestedPreFling(float var1, float var2);
}
