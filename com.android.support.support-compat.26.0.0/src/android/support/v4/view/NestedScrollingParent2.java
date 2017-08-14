package android.support.v4.view;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

public interface NestedScrollingParent2 extends NestedScrollingParent {
   boolean onStartNestedScroll(@NonNull View var1, @NonNull View var2, int var3, int var4);

   void onNestedScrollAccepted(@NonNull View var1, @NonNull View var2, int var3, int var4);

   void onStopNestedScroll(@NonNull View var1, int var2);

   void onNestedScroll(@NonNull View var1, int var2, int var3, int var4, int var5, int var6);

   void onNestedPreScroll(@NonNull View var1, int var2, int var3, @Nullable int[] var4, int var5);
}
