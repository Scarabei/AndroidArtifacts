package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources.NotFoundException;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v7.appcompat.R.styleable;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.widget.TextView;

@RequiresApi(9)
class AppCompatTextHelper {
   final TextView mView;
   private TintInfo mDrawableLeftTint;
   private TintInfo mDrawableTopTint;
   private TintInfo mDrawableRightTint;
   private TintInfo mDrawableBottomTint;
   @NonNull
   private final AppCompatTextViewAutoSizeHelper mAutoSizeTextHelper;
   private int mStyle = 0;
   private Typeface mFontTypeface;

   static AppCompatTextHelper create(TextView textView) {
      return (AppCompatTextHelper)(VERSION.SDK_INT >= 17 ? new AppCompatTextHelperV17(textView) : new AppCompatTextHelper(textView));
   }

   AppCompatTextHelper(TextView view) {
      this.mView = view;
      this.mAutoSizeTextHelper = new AppCompatTextViewAutoSizeHelper(this.mView);
   }

   void loadFromAttributes(AttributeSet attrs, int defStyleAttr) {
      Context context = this.mView.getContext();
      AppCompatDrawableManager drawableManager = AppCompatDrawableManager.get();
      TintTypedArray a = TintTypedArray.obtainStyledAttributes(context, attrs, styleable.AppCompatTextHelper, defStyleAttr, 0);
      int ap = a.getResourceId(styleable.AppCompatTextHelper_android_textAppearance, -1);
      if (a.hasValue(styleable.AppCompatTextHelper_android_drawableLeft)) {
         this.mDrawableLeftTint = createTintInfo(context, drawableManager, a.getResourceId(styleable.AppCompatTextHelper_android_drawableLeft, 0));
      }

      if (a.hasValue(styleable.AppCompatTextHelper_android_drawableTop)) {
         this.mDrawableTopTint = createTintInfo(context, drawableManager, a.getResourceId(styleable.AppCompatTextHelper_android_drawableTop, 0));
      }

      if (a.hasValue(styleable.AppCompatTextHelper_android_drawableRight)) {
         this.mDrawableRightTint = createTintInfo(context, drawableManager, a.getResourceId(styleable.AppCompatTextHelper_android_drawableRight, 0));
      }

      if (a.hasValue(styleable.AppCompatTextHelper_android_drawableBottom)) {
         this.mDrawableBottomTint = createTintInfo(context, drawableManager, a.getResourceId(styleable.AppCompatTextHelper_android_drawableBottom, 0));
      }

      a.recycle();
      boolean hasPwdTm = this.mView.getTransformationMethod() instanceof PasswordTransformationMethod;
      boolean allCaps = false;
      boolean allCapsSet = false;
      ColorStateList textColor = null;
      ColorStateList textColorHint = null;
      ColorStateList textColorLink = null;
      if (ap != -1) {
         a = TintTypedArray.obtainStyledAttributes(context, ap, styleable.TextAppearance);
         if (!hasPwdTm && a.hasValue(styleable.TextAppearance_textAllCaps)) {
            allCapsSet = true;
            allCaps = a.getBoolean(styleable.TextAppearance_textAllCaps, false);
         }

         this.updateTypefaceAndStyle(context, a);
         if (VERSION.SDK_INT < 23) {
            if (a.hasValue(styleable.TextAppearance_android_textColor)) {
               textColor = a.getColorStateList(styleable.TextAppearance_android_textColor);
            }

            if (a.hasValue(styleable.TextAppearance_android_textColorHint)) {
               textColorHint = a.getColorStateList(styleable.TextAppearance_android_textColorHint);
            }

            if (a.hasValue(styleable.TextAppearance_android_textColorLink)) {
               textColorLink = a.getColorStateList(styleable.TextAppearance_android_textColorLink);
            }
         }

         a.recycle();
      }

      a = TintTypedArray.obtainStyledAttributes(context, attrs, styleable.TextAppearance, defStyleAttr, 0);
      if (!hasPwdTm && a.hasValue(styleable.TextAppearance_textAllCaps)) {
         allCapsSet = true;
         allCaps = a.getBoolean(styleable.TextAppearance_textAllCaps, false);
      }

      if (VERSION.SDK_INT < 23) {
         if (a.hasValue(styleable.TextAppearance_android_textColor)) {
            textColor = a.getColorStateList(styleable.TextAppearance_android_textColor);
         }

         if (a.hasValue(styleable.TextAppearance_android_textColorHint)) {
            textColorHint = a.getColorStateList(styleable.TextAppearance_android_textColorHint);
         }

         if (a.hasValue(styleable.TextAppearance_android_textColorLink)) {
            textColorLink = a.getColorStateList(styleable.TextAppearance_android_textColorLink);
         }
      }

      this.updateTypefaceAndStyle(context, a);
      a.recycle();
      if (textColor != null) {
         this.mView.setTextColor(textColor);
      }

      if (textColorHint != null) {
         this.mView.setHintTextColor(textColorHint);
      }

      if (textColorLink != null) {
         this.mView.setLinkTextColor(textColorLink);
      }

      if (!hasPwdTm && allCapsSet) {
         this.setAllCaps(allCaps);
      }

      if (this.mFontTypeface != null) {
         this.mView.setTypeface(this.mFontTypeface, this.mStyle);
      }

      this.mAutoSizeTextHelper.loadFromAttributes(attrs, defStyleAttr);
      if (VERSION.SDK_INT >= 26 && this.mAutoSizeTextHelper.getAutoSizeTextType() != 0) {
         int[] autoSizeTextSizesInPx = this.mAutoSizeTextHelper.getAutoSizeTextAvailableSizes();
         if (autoSizeTextSizesInPx.length > 0) {
            if ((float)this.mView.getAutoSizeStepGranularity() != -1.0F) {
               this.mView.setAutoSizeTextTypeUniformWithConfiguration(this.mAutoSizeTextHelper.getAutoSizeMinTextSize(), this.mAutoSizeTextHelper.getAutoSizeMaxTextSize(), this.mAutoSizeTextHelper.getAutoSizeStepGranularity(), 0);
            } else {
               this.mView.setAutoSizeTextTypeUniformWithPresetSizes(autoSizeTextSizesInPx, 0);
            }
         }
      }

   }

   private void updateTypefaceAndStyle(Context context, TintTypedArray a) {
      this.mStyle = a.getInt(styleable.TextAppearance_android_textStyle, this.mStyle);
      if (a.hasValue(styleable.TextAppearance_android_fontFamily) || a.hasValue(styleable.TextAppearance_fontFamily)) {
         this.mFontTypeface = null;
         int fontFamilyId = a.hasValue(styleable.TextAppearance_android_fontFamily) ? styleable.TextAppearance_android_fontFamily : styleable.TextAppearance_fontFamily;
         if (!context.isRestricted()) {
            try {
               this.mFontTypeface = a.getFont(fontFamilyId, this.mStyle, this.mView);
            } catch (NotFoundException | UnsupportedOperationException var5) {
               ;
            }
         }

         if (this.mFontTypeface == null) {
            String fontFamilyName = a.getString(fontFamilyId);
            this.mFontTypeface = Typeface.create(fontFamilyName, this.mStyle);
         }
      }

   }

   void onSetTextAppearance(Context context, int resId) {
      TintTypedArray a = TintTypedArray.obtainStyledAttributes(context, resId, styleable.TextAppearance);
      if (a.hasValue(styleable.TextAppearance_textAllCaps)) {
         this.setAllCaps(a.getBoolean(styleable.TextAppearance_textAllCaps, false));
      }

      if (VERSION.SDK_INT < 23 && a.hasValue(styleable.TextAppearance_android_textColor)) {
         ColorStateList textColor = a.getColorStateList(styleable.TextAppearance_android_textColor);
         if (textColor != null) {
            this.mView.setTextColor(textColor);
         }
      }

      this.updateTypefaceAndStyle(context, a);
      a.recycle();
      if (this.mFontTypeface != null) {
         this.mView.setTypeface(this.mFontTypeface, this.mStyle);
      }

   }

   void setAllCaps(boolean allCaps) {
      this.mView.setAllCaps(allCaps);
   }

   void applyCompoundDrawablesTints() {
      if (this.mDrawableLeftTint != null || this.mDrawableTopTint != null || this.mDrawableRightTint != null || this.mDrawableBottomTint != null) {
         Drawable[] compoundDrawables = this.mView.getCompoundDrawables();
         this.applyCompoundDrawableTint(compoundDrawables[0], this.mDrawableLeftTint);
         this.applyCompoundDrawableTint(compoundDrawables[1], this.mDrawableTopTint);
         this.applyCompoundDrawableTint(compoundDrawables[2], this.mDrawableRightTint);
         this.applyCompoundDrawableTint(compoundDrawables[3], this.mDrawableBottomTint);
      }

   }

   final void applyCompoundDrawableTint(Drawable drawable, TintInfo info) {
      if (drawable != null && info != null) {
         AppCompatDrawableManager.tintDrawable(drawable, info, this.mView.getDrawableState());
      }

   }

   protected static TintInfo createTintInfo(Context context, AppCompatDrawableManager drawableManager, int drawableId) {
      ColorStateList tintList = drawableManager.getTintList(context, drawableId);
      if (tintList != null) {
         TintInfo tintInfo = new TintInfo();
         tintInfo.mHasTintList = true;
         tintInfo.mTintList = tintList;
         return tintInfo;
      } else {
         return null;
      }
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   void onLayout(boolean changed, int left, int top, int right, int bottom) {
      if (VERSION.SDK_INT < 26) {
         this.autoSizeText();
      }

   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   void setTextSize(int unit, float size) {
      if (VERSION.SDK_INT < 26 && !this.isAutoSizeEnabled()) {
         this.setTextSizeInternal(unit, size);
      }

   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   void autoSizeText() {
      this.mAutoSizeTextHelper.autoSizeText();
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   boolean isAutoSizeEnabled() {
      return this.mAutoSizeTextHelper.isAutoSizeEnabled();
   }

   private void setTextSizeInternal(int unit, float size) {
      this.mAutoSizeTextHelper.setTextSizeInternal(unit, size);
   }

   void setAutoSizeTextTypeWithDefaults(int autoSizeTextType) {
      this.mAutoSizeTextHelper.setAutoSizeTextTypeWithDefaults(autoSizeTextType);
   }

   void setAutoSizeTextTypeUniformWithConfiguration(int autoSizeMinTextSize, int autoSizeMaxTextSize, int autoSizeStepGranularity, int unit) throws IllegalArgumentException {
      this.mAutoSizeTextHelper.setAutoSizeTextTypeUniformWithConfiguration(autoSizeMinTextSize, autoSizeMaxTextSize, autoSizeStepGranularity, unit);
   }

   void setAutoSizeTextTypeUniformWithPresetSizes(@NonNull int[] presetSizes, int unit) throws IllegalArgumentException {
      this.mAutoSizeTextHelper.setAutoSizeTextTypeUniformWithPresetSizes(presetSizes, unit);
   }

   int getAutoSizeTextType() {
      return this.mAutoSizeTextHelper.getAutoSizeTextType();
   }

   int getAutoSizeStepGranularity() {
      return this.mAutoSizeTextHelper.getAutoSizeStepGranularity();
   }

   int getAutoSizeMinTextSize() {
      return this.mAutoSizeTextHelper.getAutoSizeMinTextSize();
   }

   int getAutoSizeMaxTextSize() {
      return this.mAutoSizeTextHelper.getAutoSizeMaxTextSize();
   }

   int[] getAutoSizeTextAvailableSizes() {
      return this.mAutoSizeTextHelper.getAutoSizeTextAvailableSizes();
   }
}
