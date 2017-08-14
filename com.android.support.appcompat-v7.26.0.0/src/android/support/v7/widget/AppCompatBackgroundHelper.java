package android.support.v7.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.R.styleable;
import android.util.AttributeSet;
import android.view.View;

class AppCompatBackgroundHelper {
   private final View mView;
   private final AppCompatDrawableManager mDrawableManager;
   private int mBackgroundResId = -1;
   private TintInfo mInternalBackgroundTint;
   private TintInfo mBackgroundTint;
   private TintInfo mTmpInfo;

   AppCompatBackgroundHelper(View view) {
      this.mView = view;
      this.mDrawableManager = AppCompatDrawableManager.get();
   }

   void loadFromAttributes(AttributeSet attrs, int defStyleAttr) {
      TintTypedArray a = TintTypedArray.obtainStyledAttributes(this.mView.getContext(), attrs, styleable.ViewBackgroundHelper, defStyleAttr, 0);

      try {
         if (a.hasValue(styleable.ViewBackgroundHelper_android_background)) {
            this.mBackgroundResId = a.getResourceId(styleable.ViewBackgroundHelper_android_background, -1);
            ColorStateList tint = this.mDrawableManager.getTintList(this.mView.getContext(), this.mBackgroundResId);
            if (tint != null) {
               this.setInternalBackgroundTint(tint);
            }
         }

         if (a.hasValue(styleable.ViewBackgroundHelper_backgroundTint)) {
            ViewCompat.setBackgroundTintList(this.mView, a.getColorStateList(styleable.ViewBackgroundHelper_backgroundTint));
         }

         if (a.hasValue(styleable.ViewBackgroundHelper_backgroundTintMode)) {
            ViewCompat.setBackgroundTintMode(this.mView, DrawableUtils.parseTintMode(a.getInt(styleable.ViewBackgroundHelper_backgroundTintMode, -1), (Mode)null));
         }
      } finally {
         a.recycle();
      }

   }

   void onSetBackgroundResource(int resId) {
      this.mBackgroundResId = resId;
      this.setInternalBackgroundTint(this.mDrawableManager != null ? this.mDrawableManager.getTintList(this.mView.getContext(), resId) : null);
      this.applySupportBackgroundTint();
   }

   void onSetBackgroundDrawable(Drawable background) {
      this.mBackgroundResId = -1;
      this.setInternalBackgroundTint((ColorStateList)null);
      this.applySupportBackgroundTint();
   }

   void setSupportBackgroundTintList(ColorStateList tint) {
      if (this.mBackgroundTint == null) {
         this.mBackgroundTint = new TintInfo();
      }

      this.mBackgroundTint.mTintList = tint;
      this.mBackgroundTint.mHasTintList = true;
      this.applySupportBackgroundTint();
   }

   ColorStateList getSupportBackgroundTintList() {
      return this.mBackgroundTint != null ? this.mBackgroundTint.mTintList : null;
   }

   void setSupportBackgroundTintMode(Mode tintMode) {
      if (this.mBackgroundTint == null) {
         this.mBackgroundTint = new TintInfo();
      }

      this.mBackgroundTint.mTintMode = tintMode;
      this.mBackgroundTint.mHasTintMode = true;
      this.applySupportBackgroundTint();
   }

   Mode getSupportBackgroundTintMode() {
      return this.mBackgroundTint != null ? this.mBackgroundTint.mTintMode : null;
   }

   void applySupportBackgroundTint() {
      Drawable background = this.mView.getBackground();
      if (background != null) {
         if (this.shouldApplyFrameworkTintUsingColorFilter() && this.applyFrameworkTintUsingColorFilter(background)) {
            return;
         }

         if (this.mBackgroundTint != null) {
            AppCompatDrawableManager.tintDrawable(background, this.mBackgroundTint, this.mView.getDrawableState());
         } else if (this.mInternalBackgroundTint != null) {
            AppCompatDrawableManager.tintDrawable(background, this.mInternalBackgroundTint, this.mView.getDrawableState());
         }
      }

   }

   void setInternalBackgroundTint(ColorStateList tint) {
      if (tint != null) {
         if (this.mInternalBackgroundTint == null) {
            this.mInternalBackgroundTint = new TintInfo();
         }

         this.mInternalBackgroundTint.mTintList = tint;
         this.mInternalBackgroundTint.mHasTintList = true;
      } else {
         this.mInternalBackgroundTint = null;
      }

      this.applySupportBackgroundTint();
   }

   private boolean shouldApplyFrameworkTintUsingColorFilter() {
      int sdk = VERSION.SDK_INT;
      if (sdk > 21) {
         return this.mInternalBackgroundTint != null;
      } else {
         return sdk == 21;
      }
   }

   private boolean applyFrameworkTintUsingColorFilter(@NonNull Drawable background) {
      if (this.mTmpInfo == null) {
         this.mTmpInfo = new TintInfo();
      }

      TintInfo info = this.mTmpInfo;
      info.clear();
      ColorStateList tintList = ViewCompat.getBackgroundTintList(this.mView);
      if (tintList != null) {
         info.mHasTintList = true;
         info.mTintList = tintList;
      }

      Mode mode = ViewCompat.getBackgroundTintMode(this.mView);
      if (mode != null) {
         info.mHasTintMode = true;
         info.mTintMode = mode;
      }

      if (!info.mHasTintList && !info.mHasTintMode) {
         return false;
      } else {
         AppCompatDrawableManager.tintDrawable(background, info, this.mView.getDrawableState());
         return true;
      }
   }
}
