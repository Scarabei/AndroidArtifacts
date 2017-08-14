package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.RequiresApi;
import android.support.v7.appcompat.R.styleable;
import android.util.AttributeSet;
import android.widget.TextView;

@RequiresApi(17)
class AppCompatTextHelperV17 extends AppCompatTextHelper {
   private TintInfo mDrawableStartTint;
   private TintInfo mDrawableEndTint;

   AppCompatTextHelperV17(TextView view) {
      super(view);
   }

   void loadFromAttributes(AttributeSet attrs, int defStyleAttr) {
      super.loadFromAttributes(attrs, defStyleAttr);
      Context context = this.mView.getContext();
      AppCompatDrawableManager drawableManager = AppCompatDrawableManager.get();
      TypedArray a = context.obtainStyledAttributes(attrs, styleable.AppCompatTextHelper, defStyleAttr, 0);
      if (a.hasValue(styleable.AppCompatTextHelper_android_drawableStart)) {
         this.mDrawableStartTint = createTintInfo(context, drawableManager, a.getResourceId(styleable.AppCompatTextHelper_android_drawableStart, 0));
      }

      if (a.hasValue(styleable.AppCompatTextHelper_android_drawableEnd)) {
         this.mDrawableEndTint = createTintInfo(context, drawableManager, a.getResourceId(styleable.AppCompatTextHelper_android_drawableEnd, 0));
      }

      a.recycle();
   }

   void applyCompoundDrawablesTints() {
      super.applyCompoundDrawablesTints();
      if (this.mDrawableStartTint != null || this.mDrawableEndTint != null) {
         Drawable[] compoundDrawables = this.mView.getCompoundDrawablesRelative();
         this.applyCompoundDrawableTint(compoundDrawables[0], this.mDrawableStartTint);
         this.applyCompoundDrawableTint(compoundDrawables[2], this.mDrawableEndTint);
      }

   }
}
