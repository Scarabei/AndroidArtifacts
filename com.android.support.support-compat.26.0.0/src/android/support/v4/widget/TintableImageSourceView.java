package android.support.v4.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;

@RestrictTo({Scope.LIBRARY_GROUP})
public interface TintableImageSourceView {
   void setSupportImageTintList(@Nullable ColorStateList var1);

   @Nullable
   ColorStateList getSupportImageTintList();

   void setSupportImageTintMode(@Nullable Mode var1);

   @Nullable
   Mode getSupportImageTintMode();
}
