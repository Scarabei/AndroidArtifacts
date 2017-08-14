package android.support.v4.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.support.annotation.Nullable;

public interface TintableCompoundButton {
   void setSupportButtonTintList(@Nullable ColorStateList var1);

   @Nullable
   ColorStateList getSupportButtonTintList();

   void setSupportButtonTintMode(@Nullable Mode var1);

   @Nullable
   Mode getSupportButtonTintMode();
}
