package android.support.v7.widget;

import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.widget.CompoundButtonCompat;
import android.support.v7.appcompat.R.styleable;
import android.support.v7.content.res.AppCompatResources;
import android.util.AttributeSet;
import android.widget.CompoundButton;

class AppCompatCompoundButtonHelper {
   private final CompoundButton mView;
   private ColorStateList mButtonTintList = null;
   private Mode mButtonTintMode = null;
   private boolean mHasButtonTint = false;
   private boolean mHasButtonTintMode = false;
   private boolean mSkipNextApply;

   AppCompatCompoundButtonHelper(CompoundButton view) {
      this.mView = view;
   }

   void loadFromAttributes(AttributeSet attrs, int defStyleAttr) {
      TypedArray a = this.mView.getContext().obtainStyledAttributes(attrs, styleable.CompoundButton, defStyleAttr, 0);

      try {
         if (a.hasValue(styleable.CompoundButton_android_button)) {
            int resourceId = a.getResourceId(styleable.CompoundButton_android_button, 0);
            if (resourceId != 0) {
               this.mView.setButtonDrawable(AppCompatResources.getDrawable(this.mView.getContext(), resourceId));
            }
         }

         if (a.hasValue(styleable.CompoundButton_buttonTint)) {
            CompoundButtonCompat.setButtonTintList(this.mView, a.getColorStateList(styleable.CompoundButton_buttonTint));
         }

         if (a.hasValue(styleable.CompoundButton_buttonTintMode)) {
            CompoundButtonCompat.setButtonTintMode(this.mView, DrawableUtils.parseTintMode(a.getInt(styleable.CompoundButton_buttonTintMode, -1), (Mode)null));
         }
      } finally {
         a.recycle();
      }

   }

   void setSupportButtonTintList(ColorStateList tint) {
      this.mButtonTintList = tint;
      this.mHasButtonTint = true;
      this.applyButtonTint();
   }

   ColorStateList getSupportButtonTintList() {
      return this.mButtonTintList;
   }

   void setSupportButtonTintMode(@Nullable Mode tintMode) {
      this.mButtonTintMode = tintMode;
      this.mHasButtonTintMode = true;
      this.applyButtonTint();
   }

   Mode getSupportButtonTintMode() {
      return this.mButtonTintMode;
   }

   void onSetButtonDrawable() {
      if (this.mSkipNextApply) {
         this.mSkipNextApply = false;
      } else {
         this.mSkipNextApply = true;
         this.applyButtonTint();
      }
   }

   void applyButtonTint() {
      Drawable buttonDrawable = CompoundButtonCompat.getButtonDrawable(this.mView);
      if (buttonDrawable != null && (this.mHasButtonTint || this.mHasButtonTintMode)) {
         buttonDrawable = DrawableCompat.wrap(buttonDrawable);
         buttonDrawable = buttonDrawable.mutate();
         if (this.mHasButtonTint) {
            DrawableCompat.setTintList(buttonDrawable, this.mButtonTintList);
         }

         if (this.mHasButtonTintMode) {
            DrawableCompat.setTintMode(buttonDrawable, this.mButtonTintMode);
         }

         if (buttonDrawable.isStateful()) {
            buttonDrawable.setState(this.mView.getDrawableState());
         }

         this.mView.setButtonDrawable(buttonDrawable);
      }

   }

   int getCompoundPaddingLeft(int superValue) {
      if (VERSION.SDK_INT < 17) {
         Drawable buttonDrawable = CompoundButtonCompat.getButtonDrawable(this.mView);
         if (buttonDrawable != null) {
            superValue += buttonDrawable.getIntrinsicWidth();
         }
      }

      return superValue;
   }

   interface DirectSetButtonDrawableInterface {
      void setButtonDrawable(Drawable var1);
   }
}
