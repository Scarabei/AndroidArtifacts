package android.support.v4.view;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.support.annotation.Nullable;

public interface TintableBackgroundView {
   void setSupportBackgroundTintList(@Nullable ColorStateList var1);

   @Nullable
   ColorStateList getSupportBackgroundTintList();

   void setSupportBackgroundTintMode(@Nullable Mode var1);

   @Nullable
   Mode getSupportBackgroundTintMode();
}
