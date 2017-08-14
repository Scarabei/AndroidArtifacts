package android.support.design.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Build.VERSION;
import android.os.Parcelable.ClassLoaderCreator;
import android.os.Parcelable.Creator;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.annotation.StyleRes;
import android.support.annotation.VisibleForTesting;
import android.support.design.R.id;
import android.support.design.R.layout;
import android.support.design.R.string;
import android.support.design.R.style;
import android.support.design.R.styleable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.AbsSavedState;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.widget.Space;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.appcompat.R.color;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.widget.AppCompatDrawableManager;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.TintTypedArray;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStructure;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.AccelerateInterpolator;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TextInputLayout extends LinearLayout {
   private static final int ANIMATION_DURATION = 200;
   private static final int INVALID_MAX_LENGTH = -1;
   private static final String LOG_TAG = "TextInputLayout";
   private final FrameLayout mInputFrame;
   EditText mEditText;
   private CharSequence mOriginalHint;
   private boolean mHintEnabled;
   private CharSequence mHint;
   private Paint mTmpPaint;
   private final Rect mTmpRect;
   private LinearLayout mIndicatorArea;
   private int mIndicatorsAdded;
   private Typeface mTypeface;
   private boolean mErrorEnabled;
   TextView mErrorView;
   private int mErrorTextAppearance;
   private boolean mErrorShown;
   private CharSequence mError;
   boolean mCounterEnabled;
   private TextView mCounterView;
   private int mCounterMaxLength;
   private int mCounterTextAppearance;
   private int mCounterOverflowTextAppearance;
   private boolean mCounterOverflowed;
   private boolean mPasswordToggleEnabled;
   private Drawable mPasswordToggleDrawable;
   private CharSequence mPasswordToggleContentDesc;
   private CheckableImageButton mPasswordToggleView;
   private boolean mPasswordToggledVisible;
   private Drawable mPasswordToggleDummyDrawable;
   private Drawable mOriginalEditTextEndDrawable;
   private ColorStateList mPasswordToggleTintList;
   private boolean mHasPasswordToggleTintList;
   private Mode mPasswordToggleTintMode;
   private boolean mHasPasswordToggleTintMode;
   private ColorStateList mDefaultTextColor;
   private ColorStateList mFocusedTextColor;
   private boolean mHintExpanded;
   final CollapsingTextHelper mCollapsingTextHelper;
   private boolean mHintAnimationEnabled;
   private ValueAnimator mAnimator;
   private boolean mHasReconstructedEditTextBackground;
   private boolean mInDrawableStateChanged;
   private boolean mRestoringSavedState;

   public TextInputLayout(Context context) {
      this(context, (AttributeSet)null);
   }

   public TextInputLayout(Context context, AttributeSet attrs) {
      this(context, attrs, 0);
   }

   public TextInputLayout(Context context, AttributeSet attrs, int defStyleAttr) {
      super(context, attrs);
      this.mTmpRect = new Rect();
      this.mCollapsingTextHelper = new CollapsingTextHelper(this);
      ThemeUtils.checkAppCompatTheme(context);
      this.setOrientation(1);
      this.setWillNotDraw(false);
      this.setAddStatesFromChildren(true);
      this.mInputFrame = new FrameLayout(context);
      this.mInputFrame.setAddStatesFromChildren(true);
      this.addView(this.mInputFrame);
      this.mCollapsingTextHelper.setTextSizeInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
      this.mCollapsingTextHelper.setPositionInterpolator(new AccelerateInterpolator());
      this.mCollapsingTextHelper.setCollapsedTextGravity(8388659);
      TintTypedArray a = TintTypedArray.obtainStyledAttributes(context, attrs, styleable.TextInputLayout, defStyleAttr, style.Widget_Design_TextInputLayout);
      this.mHintEnabled = a.getBoolean(styleable.TextInputLayout_hintEnabled, true);
      this.setHint(a.getText(styleable.TextInputLayout_android_hint));
      this.mHintAnimationEnabled = a.getBoolean(styleable.TextInputLayout_hintAnimationEnabled, true);
      if (a.hasValue(styleable.TextInputLayout_android_textColorHint)) {
         this.mDefaultTextColor = this.mFocusedTextColor = a.getColorStateList(styleable.TextInputLayout_android_textColorHint);
      }

      int hintAppearance = a.getResourceId(styleable.TextInputLayout_hintTextAppearance, -1);
      if (hintAppearance != -1) {
         this.setHintTextAppearance(a.getResourceId(styleable.TextInputLayout_hintTextAppearance, 0));
      }

      this.mErrorTextAppearance = a.getResourceId(styleable.TextInputLayout_errorTextAppearance, 0);
      boolean errorEnabled = a.getBoolean(styleable.TextInputLayout_errorEnabled, false);
      boolean counterEnabled = a.getBoolean(styleable.TextInputLayout_counterEnabled, false);
      this.setCounterMaxLength(a.getInt(styleable.TextInputLayout_counterMaxLength, -1));
      this.mCounterTextAppearance = a.getResourceId(styleable.TextInputLayout_counterTextAppearance, 0);
      this.mCounterOverflowTextAppearance = a.getResourceId(styleable.TextInputLayout_counterOverflowTextAppearance, 0);
      this.mPasswordToggleEnabled = a.getBoolean(styleable.TextInputLayout_passwordToggleEnabled, false);
      this.mPasswordToggleDrawable = a.getDrawable(styleable.TextInputLayout_passwordToggleDrawable);
      this.mPasswordToggleContentDesc = a.getText(styleable.TextInputLayout_passwordToggleContentDescription);
      if (a.hasValue(styleable.TextInputLayout_passwordToggleTint)) {
         this.mHasPasswordToggleTintList = true;
         this.mPasswordToggleTintList = a.getColorStateList(styleable.TextInputLayout_passwordToggleTint);
      }

      if (a.hasValue(styleable.TextInputLayout_passwordToggleTintMode)) {
         this.mHasPasswordToggleTintMode = true;
         this.mPasswordToggleTintMode = ViewUtils.parseTintMode(a.getInt(styleable.TextInputLayout_passwordToggleTintMode, -1), (Mode)null);
      }

      a.recycle();
      this.setErrorEnabled(errorEnabled);
      this.setCounterEnabled(counterEnabled);
      this.applyPasswordToggleTint();
      if (ViewCompat.getImportantForAccessibility(this) == 0) {
         ViewCompat.setImportantForAccessibility(this, 1);
      }

      ViewCompat.setAccessibilityDelegate(this, new TextInputLayout.TextInputAccessibilityDelegate());
   }

   public void addView(View child, int index, LayoutParams params) {
      if (child instanceof EditText) {
         android.widget.FrameLayout.LayoutParams flp = new android.widget.FrameLayout.LayoutParams(params);
         flp.gravity = 16 | flp.gravity & -113;
         this.mInputFrame.addView(child, flp);
         this.mInputFrame.setLayoutParams(params);
         this.updateInputLayoutMargins();
         this.setEditText((EditText)child);
      } else {
         super.addView(child, index, params);
      }

   }

   public void setTypeface(@Nullable Typeface typeface) {
      if (this.mTypeface != null && !this.mTypeface.equals(typeface) || this.mTypeface == null && typeface != null) {
         this.mTypeface = typeface;
         this.mCollapsingTextHelper.setTypefaces(typeface);
         if (this.mCounterView != null) {
            this.mCounterView.setTypeface(typeface);
         }

         if (this.mErrorView != null) {
            this.mErrorView.setTypeface(typeface);
         }
      }

   }

   @NonNull
   public Typeface getTypeface() {
      return this.mTypeface;
   }

   public void dispatchProvideAutofillStructure(ViewStructure structure, int flags) {
      if (this.mOriginalHint != null && this.mEditText != null) {
         CharSequence hint = this.mEditText.getHint();
         this.mEditText.setHint(this.mOriginalHint);

         try {
            super.dispatchProvideAutofillStructure(structure, flags);
         } finally {
            this.mEditText.setHint(hint);
         }

      } else {
         super.dispatchProvideAutofillStructure(structure, flags);
      }
   }

   private void setEditText(EditText editText) {
      if (this.mEditText != null) {
         throw new IllegalArgumentException("We already have an EditText, can only have one");
      } else {
         if (!(editText instanceof TextInputEditText)) {
            Log.i("TextInputLayout", "EditText added is not a TextInputEditText. Please switch to using that class instead.");
         }

         this.mEditText = editText;
         boolean hasPasswordTransformation = this.hasPasswordTransformation();
         if (!hasPasswordTransformation) {
            this.mCollapsingTextHelper.setTypefaces(this.mEditText.getTypeface());
         }

         this.mCollapsingTextHelper.setExpandedTextSize(this.mEditText.getTextSize());
         int editTextGravity = this.mEditText.getGravity();
         this.mCollapsingTextHelper.setCollapsedTextGravity(48 | editTextGravity & -113);
         this.mCollapsingTextHelper.setExpandedTextGravity(editTextGravity);
         this.mEditText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
               TextInputLayout.this.updateLabelState(!TextInputLayout.this.mRestoringSavedState);
               if (TextInputLayout.this.mCounterEnabled) {
                  TextInputLayout.this.updateCounter(s.length());
               }

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
         });
         if (this.mDefaultTextColor == null) {
            this.mDefaultTextColor = this.mEditText.getHintTextColors();
         }

         if (this.mHintEnabled && TextUtils.isEmpty(this.mHint)) {
            this.mOriginalHint = this.mEditText.getHint();
            this.setHint(this.mOriginalHint);
            this.mEditText.setHint((CharSequence)null);
         }

         if (this.mCounterView != null) {
            this.updateCounter(this.mEditText.getText().length());
         }

         if (this.mIndicatorArea != null) {
            this.adjustIndicatorPadding();
         }

         this.updatePasswordToggleView();
         this.updateLabelState(false, true);
      }
   }

   private void updateInputLayoutMargins() {
      android.widget.LinearLayout.LayoutParams lp = (android.widget.LinearLayout.LayoutParams)this.mInputFrame.getLayoutParams();
      int newTopMargin;
      if (this.mHintEnabled) {
         if (this.mTmpPaint == null) {
            this.mTmpPaint = new Paint();
         }

         this.mTmpPaint.setTypeface(this.mCollapsingTextHelper.getCollapsedTypeface());
         this.mTmpPaint.setTextSize(this.mCollapsingTextHelper.getCollapsedTextSize());
         newTopMargin = (int)(-this.mTmpPaint.ascent());
      } else {
         newTopMargin = 0;
      }

      if (newTopMargin != lp.topMargin) {
         lp.topMargin = newTopMargin;
         this.mInputFrame.requestLayout();
      }

   }

   void updateLabelState(boolean animate) {
      this.updateLabelState(animate, false);
   }

   void updateLabelState(boolean animate, boolean force) {
      boolean isEnabled = this.isEnabled();
      boolean hasText = this.mEditText != null && !TextUtils.isEmpty(this.mEditText.getText());
      boolean isFocused = arrayContains(this.getDrawableState(), 16842908);
      boolean isErrorShowing = !TextUtils.isEmpty(this.getError());
      if (this.mDefaultTextColor != null) {
         this.mCollapsingTextHelper.setExpandedTextColor(this.mDefaultTextColor);
      }

      if (isEnabled && this.mCounterOverflowed && this.mCounterView != null) {
         this.mCollapsingTextHelper.setCollapsedTextColor(this.mCounterView.getTextColors());
      } else if (isEnabled && isFocused && this.mFocusedTextColor != null) {
         this.mCollapsingTextHelper.setCollapsedTextColor(this.mFocusedTextColor);
      } else if (this.mDefaultTextColor != null) {
         this.mCollapsingTextHelper.setCollapsedTextColor(this.mDefaultTextColor);
      }

      if (hasText || this.isEnabled() && (isFocused || isErrorShowing)) {
         if (force || this.mHintExpanded) {
            this.collapseHint(animate);
         }
      } else if (force || !this.mHintExpanded) {
         this.expandHint(animate);
      }

   }

   @Nullable
   public EditText getEditText() {
      return this.mEditText;
   }

   public void setHint(@Nullable CharSequence hint) {
      if (this.mHintEnabled) {
         this.setHintInternal(hint);
         this.sendAccessibilityEvent(2048);
      }

   }

   private void setHintInternal(CharSequence hint) {
      this.mHint = hint;
      this.mCollapsingTextHelper.setText(hint);
   }

   @Nullable
   public CharSequence getHint() {
      return this.mHintEnabled ? this.mHint : null;
   }

   public void setHintEnabled(boolean enabled) {
      if (enabled != this.mHintEnabled) {
         this.mHintEnabled = enabled;
         CharSequence editTextHint = this.mEditText.getHint();
         if (!this.mHintEnabled) {
            if (!TextUtils.isEmpty(this.mHint) && TextUtils.isEmpty(editTextHint)) {
               this.mEditText.setHint(this.mHint);
            }

            this.setHintInternal((CharSequence)null);
         } else if (!TextUtils.isEmpty(editTextHint)) {
            if (TextUtils.isEmpty(this.mHint)) {
               this.setHint(editTextHint);
            }

            this.mEditText.setHint((CharSequence)null);
         }

         if (this.mEditText != null) {
            this.updateInputLayoutMargins();
         }
      }

   }

   public boolean isHintEnabled() {
      return this.mHintEnabled;
   }

   public void setHintTextAppearance(@StyleRes int resId) {
      this.mCollapsingTextHelper.setCollapsedTextAppearance(resId);
      this.mFocusedTextColor = this.mCollapsingTextHelper.getCollapsedTextColor();
      if (this.mEditText != null) {
         this.updateLabelState(false);
         this.updateInputLayoutMargins();
      }

   }

   private void addIndicator(TextView indicator, int index) {
      if (this.mIndicatorArea == null) {
         this.mIndicatorArea = new LinearLayout(this.getContext());
         this.mIndicatorArea.setOrientation(0);
         this.addView(this.mIndicatorArea, -1, -2);
         Space spacer = new Space(this.getContext());
         android.widget.LinearLayout.LayoutParams spacerLp = new android.widget.LinearLayout.LayoutParams(0, 0, 1.0F);
         this.mIndicatorArea.addView(spacer, spacerLp);
         if (this.mEditText != null) {
            this.adjustIndicatorPadding();
         }
      }

      this.mIndicatorArea.setVisibility(0);
      this.mIndicatorArea.addView(indicator, index);
      ++this.mIndicatorsAdded;
   }

   private void adjustIndicatorPadding() {
      ViewCompat.setPaddingRelative(this.mIndicatorArea, ViewCompat.getPaddingStart(this.mEditText), 0, ViewCompat.getPaddingEnd(this.mEditText), this.mEditText.getPaddingBottom());
   }

   private void removeIndicator(TextView indicator) {
      if (this.mIndicatorArea != null) {
         this.mIndicatorArea.removeView(indicator);
         if (--this.mIndicatorsAdded == 0) {
            this.mIndicatorArea.setVisibility(8);
         }
      }

   }

   public void setErrorEnabled(boolean enabled) {
      if (this.mErrorEnabled != enabled) {
         if (this.mErrorView != null) {
            this.mErrorView.animate().cancel();
         }

         if (enabled) {
            this.mErrorView = new AppCompatTextView(this.getContext());
            this.mErrorView.setId(id.textinput_error);
            if (this.mTypeface != null) {
               this.mErrorView.setTypeface(this.mTypeface);
            }

            boolean useDefaultColor = false;

            try {
               TextViewCompat.setTextAppearance(this.mErrorView, this.mErrorTextAppearance);
               if (VERSION.SDK_INT >= 23 && this.mErrorView.getTextColors().getDefaultColor() == -65281) {
                  useDefaultColor = true;
               }
            } catch (Exception var4) {
               useDefaultColor = true;
            }

            if (useDefaultColor) {
               TextViewCompat.setTextAppearance(this.mErrorView, android.support.v7.appcompat.R.style.TextAppearance_AppCompat_Caption);
               this.mErrorView.setTextColor(ContextCompat.getColor(this.getContext(), color.error_color_material));
            }

            this.mErrorView.setVisibility(4);
            ViewCompat.setAccessibilityLiveRegion(this.mErrorView, 1);
            this.addIndicator(this.mErrorView, 0);
         } else {
            this.mErrorShown = false;
            this.updateEditTextBackground();
            this.removeIndicator(this.mErrorView);
            this.mErrorView = null;
         }

         this.mErrorEnabled = enabled;
      }

   }

   public void setErrorTextAppearance(@StyleRes int resId) {
      this.mErrorTextAppearance = resId;
      if (this.mErrorView != null) {
         TextViewCompat.setTextAppearance(this.mErrorView, resId);
      }

   }

   public boolean isErrorEnabled() {
      return this.mErrorEnabled;
   }

   public void setError(@Nullable CharSequence error) {
      this.setError(error, ViewCompat.isLaidOut(this) && this.isEnabled() && (this.mErrorView == null || !TextUtils.equals(this.mErrorView.getText(), error)));
   }

   private void setError(@Nullable final CharSequence error, boolean animate) {
      this.mError = error;
      if (!this.mErrorEnabled) {
         if (TextUtils.isEmpty(error)) {
            return;
         }

         this.setErrorEnabled(true);
      }

      this.mErrorShown = !TextUtils.isEmpty(error);
      this.mErrorView.animate().cancel();
      if (this.mErrorShown) {
         this.mErrorView.setText(error);
         this.mErrorView.setVisibility(0);
         if (animate) {
            if (this.mErrorView.getAlpha() == 1.0F) {
               this.mErrorView.setAlpha(0.0F);
            }

            this.mErrorView.animate().alpha(1.0F).setDuration(200L).setInterpolator(AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR).setListener(new AnimatorListenerAdapter() {
               public void onAnimationStart(Animator animator) {
                  TextInputLayout.this.mErrorView.setVisibility(0);
               }
            }).start();
         } else {
            this.mErrorView.setAlpha(1.0F);
         }
      } else if (this.mErrorView.getVisibility() == 0) {
         if (animate) {
            this.mErrorView.animate().alpha(0.0F).setDuration(200L).setInterpolator(AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR).setListener(new AnimatorListenerAdapter() {
               public void onAnimationEnd(Animator animator) {
                  TextInputLayout.this.mErrorView.setText(error);
                  TextInputLayout.this.mErrorView.setVisibility(4);
               }
            }).start();
         } else {
            this.mErrorView.setText(error);
            this.mErrorView.setVisibility(4);
         }
      }

      this.updateEditTextBackground();
      this.updateLabelState(animate);
   }

   public void setCounterEnabled(boolean enabled) {
      if (this.mCounterEnabled != enabled) {
         if (enabled) {
            this.mCounterView = new AppCompatTextView(this.getContext());
            this.mCounterView.setId(id.textinput_counter);
            if (this.mTypeface != null) {
               this.mCounterView.setTypeface(this.mTypeface);
            }

            this.mCounterView.setMaxLines(1);

            try {
               TextViewCompat.setTextAppearance(this.mCounterView, this.mCounterTextAppearance);
            } catch (Exception var3) {
               TextViewCompat.setTextAppearance(this.mCounterView, android.support.v7.appcompat.R.style.TextAppearance_AppCompat_Caption);
               this.mCounterView.setTextColor(ContextCompat.getColor(this.getContext(), color.error_color_material));
            }

            this.addIndicator(this.mCounterView, -1);
            if (this.mEditText == null) {
               this.updateCounter(0);
            } else {
               this.updateCounter(this.mEditText.getText().length());
            }
         } else {
            this.removeIndicator(this.mCounterView);
            this.mCounterView = null;
         }

         this.mCounterEnabled = enabled;
      }

   }

   public boolean isCounterEnabled() {
      return this.mCounterEnabled;
   }

   public void setCounterMaxLength(int maxLength) {
      if (this.mCounterMaxLength != maxLength) {
         if (maxLength > 0) {
            this.mCounterMaxLength = maxLength;
         } else {
            this.mCounterMaxLength = -1;
         }

         if (this.mCounterEnabled) {
            this.updateCounter(this.mEditText == null ? 0 : this.mEditText.getText().length());
         }
      }

   }

   public void setEnabled(boolean enabled) {
      recursiveSetEnabled(this, enabled);
      super.setEnabled(enabled);
   }

   private static void recursiveSetEnabled(ViewGroup vg, boolean enabled) {
      int i = 0;

      for(int count = vg.getChildCount(); i < count; ++i) {
         View child = vg.getChildAt(i);
         child.setEnabled(enabled);
         if (child instanceof ViewGroup) {
            recursiveSetEnabled((ViewGroup)child, enabled);
         }
      }

   }

   public int getCounterMaxLength() {
      return this.mCounterMaxLength;
   }

   void updateCounter(int length) {
      boolean wasCounterOverflowed = this.mCounterOverflowed;
      if (this.mCounterMaxLength == -1) {
         this.mCounterView.setText(String.valueOf(length));
         this.mCounterOverflowed = false;
      } else {
         this.mCounterOverflowed = length > this.mCounterMaxLength;
         if (wasCounterOverflowed != this.mCounterOverflowed) {
            TextViewCompat.setTextAppearance(this.mCounterView, this.mCounterOverflowed ? this.mCounterOverflowTextAppearance : this.mCounterTextAppearance);
         }

         this.mCounterView.setText(this.getContext().getString(string.character_counter_pattern, new Object[]{length, this.mCounterMaxLength}));
      }

      if (this.mEditText != null && wasCounterOverflowed != this.mCounterOverflowed) {
         this.updateLabelState(false);
         this.updateEditTextBackground();
      }

   }

   private void updateEditTextBackground() {
      if (this.mEditText != null) {
         Drawable editTextBackground = this.mEditText.getBackground();
         if (editTextBackground != null) {
            this.ensureBackgroundDrawableStateWorkaround();
            if (android.support.v7.widget.DrawableUtils.canSafelyMutateDrawable(editTextBackground)) {
               editTextBackground = editTextBackground.mutate();
            }

            if (this.mErrorShown && this.mErrorView != null) {
               editTextBackground.setColorFilter(AppCompatDrawableManager.getPorterDuffColorFilter(this.mErrorView.getCurrentTextColor(), Mode.SRC_IN));
            } else if (this.mCounterOverflowed && this.mCounterView != null) {
               editTextBackground.setColorFilter(AppCompatDrawableManager.getPorterDuffColorFilter(this.mCounterView.getCurrentTextColor(), Mode.SRC_IN));
            } else {
               DrawableCompat.clearColorFilter(editTextBackground);
               this.mEditText.refreshDrawableState();
            }

         }
      }
   }

   private void ensureBackgroundDrawableStateWorkaround() {
      int sdk = VERSION.SDK_INT;
      if (sdk == 21 || sdk == 22) {
         Drawable bg = this.mEditText.getBackground();
         if (bg != null) {
            if (!this.mHasReconstructedEditTextBackground) {
               Drawable newBg = bg.getConstantState().newDrawable();
               if (bg instanceof DrawableContainer) {
                  this.mHasReconstructedEditTextBackground = DrawableUtils.setContainerConstantState((DrawableContainer)bg, newBg.getConstantState());
               }

               if (!this.mHasReconstructedEditTextBackground) {
                  ViewCompat.setBackground(this.mEditText, newBg);
                  this.mHasReconstructedEditTextBackground = true;
               }
            }

         }
      }
   }

   public Parcelable onSaveInstanceState() {
      Parcelable superState = super.onSaveInstanceState();
      TextInputLayout.SavedState ss = new TextInputLayout.SavedState(superState);
      if (this.mErrorShown) {
         ss.error = this.getError();
      }

      ss.isPasswordToggledVisible = this.mPasswordToggledVisible;
      return ss;
   }

   protected void onRestoreInstanceState(Parcelable state) {
      if (!(state instanceof TextInputLayout.SavedState)) {
         super.onRestoreInstanceState(state);
      } else {
         TextInputLayout.SavedState ss = (TextInputLayout.SavedState)state;
         super.onRestoreInstanceState(ss.getSuperState());
         this.setError(ss.error);
         if (ss.isPasswordToggledVisible) {
            this.passwordVisibilityToggleRequested(true);
         }

         this.requestLayout();
      }
   }

   protected void dispatchRestoreInstanceState(SparseArray container) {
      this.mRestoringSavedState = true;
      super.dispatchRestoreInstanceState(container);
      this.mRestoringSavedState = false;
   }

   @Nullable
   public CharSequence getError() {
      return this.mErrorEnabled ? this.mError : null;
   }

   public boolean isHintAnimationEnabled() {
      return this.mHintAnimationEnabled;
   }

   public void setHintAnimationEnabled(boolean enabled) {
      this.mHintAnimationEnabled = enabled;
   }

   public void draw(Canvas canvas) {
      super.draw(canvas);
      if (this.mHintEnabled) {
         this.mCollapsingTextHelper.draw(canvas);
      }

   }

   protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
      this.updatePasswordToggleView();
      super.onMeasure(widthMeasureSpec, heightMeasureSpec);
   }

   private void updatePasswordToggleView() {
      if (this.mEditText != null) {
         Drawable[] compounds;
         if (this.shouldShowPasswordIcon()) {
            if (this.mPasswordToggleView == null) {
               this.mPasswordToggleView = (CheckableImageButton)LayoutInflater.from(this.getContext()).inflate(layout.design_text_input_password_icon, this.mInputFrame, false);
               this.mPasswordToggleView.setImageDrawable(this.mPasswordToggleDrawable);
               this.mPasswordToggleView.setContentDescription(this.mPasswordToggleContentDesc);
               this.mInputFrame.addView(this.mPasswordToggleView);
               this.mPasswordToggleView.setOnClickListener(new OnClickListener() {
                  public void onClick(View view) {
                     TextInputLayout.this.passwordVisibilityToggleRequested(false);
                  }
               });
            }

            if (this.mEditText != null && ViewCompat.getMinimumHeight(this.mEditText) <= 0) {
               this.mEditText.setMinimumHeight(ViewCompat.getMinimumHeight(this.mPasswordToggleView));
            }

            this.mPasswordToggleView.setVisibility(0);
            this.mPasswordToggleView.setChecked(this.mPasswordToggledVisible);
            if (this.mPasswordToggleDummyDrawable == null) {
               this.mPasswordToggleDummyDrawable = new ColorDrawable();
            }

            this.mPasswordToggleDummyDrawable.setBounds(0, 0, this.mPasswordToggleView.getMeasuredWidth(), 1);
            compounds = TextViewCompat.getCompoundDrawablesRelative(this.mEditText);
            if (compounds[2] != this.mPasswordToggleDummyDrawable) {
               this.mOriginalEditTextEndDrawable = compounds[2];
            }

            TextViewCompat.setCompoundDrawablesRelative(this.mEditText, compounds[0], compounds[1], this.mPasswordToggleDummyDrawable, compounds[3]);
            this.mPasswordToggleView.setPadding(this.mEditText.getPaddingLeft(), this.mEditText.getPaddingTop(), this.mEditText.getPaddingRight(), this.mEditText.getPaddingBottom());
         } else {
            if (this.mPasswordToggleView != null && this.mPasswordToggleView.getVisibility() == 0) {
               this.mPasswordToggleView.setVisibility(8);
            }

            if (this.mPasswordToggleDummyDrawable != null) {
               compounds = TextViewCompat.getCompoundDrawablesRelative(this.mEditText);
               if (compounds[2] == this.mPasswordToggleDummyDrawable) {
                  TextViewCompat.setCompoundDrawablesRelative(this.mEditText, compounds[0], compounds[1], this.mOriginalEditTextEndDrawable, compounds[3]);
                  this.mPasswordToggleDummyDrawable = null;
               }
            }
         }

      }
   }

   public void setPasswordVisibilityToggleDrawable(@DrawableRes int resId) {
      this.setPasswordVisibilityToggleDrawable(resId != 0 ? AppCompatResources.getDrawable(this.getContext(), resId) : null);
   }

   public void setPasswordVisibilityToggleDrawable(@Nullable Drawable icon) {
      this.mPasswordToggleDrawable = icon;
      if (this.mPasswordToggleView != null) {
         this.mPasswordToggleView.setImageDrawable(icon);
      }

   }

   public void setPasswordVisibilityToggleContentDescription(@StringRes int resId) {
      this.setPasswordVisibilityToggleContentDescription(resId != 0 ? this.getResources().getText(resId) : null);
   }

   public void setPasswordVisibilityToggleContentDescription(@Nullable CharSequence description) {
      this.mPasswordToggleContentDesc = description;
      if (this.mPasswordToggleView != null) {
         this.mPasswordToggleView.setContentDescription(description);
      }

   }

   @Nullable
   public Drawable getPasswordVisibilityToggleDrawable() {
      return this.mPasswordToggleDrawable;
   }

   @Nullable
   public CharSequence getPasswordVisibilityToggleContentDescription() {
      return this.mPasswordToggleContentDesc;
   }

   public boolean isPasswordVisibilityToggleEnabled() {
      return this.mPasswordToggleEnabled;
   }

   public void setPasswordVisibilityToggleEnabled(boolean enabled) {
      if (this.mPasswordToggleEnabled != enabled) {
         this.mPasswordToggleEnabled = enabled;
         if (!enabled && this.mPasswordToggledVisible && this.mEditText != null) {
            this.mEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
         }

         this.mPasswordToggledVisible = false;
         this.updatePasswordToggleView();
      }

   }

   public void setPasswordVisibilityToggleTintList(@Nullable ColorStateList tintList) {
      this.mPasswordToggleTintList = tintList;
      this.mHasPasswordToggleTintList = true;
      this.applyPasswordToggleTint();
   }

   public void setPasswordVisibilityToggleTintMode(@Nullable Mode mode) {
      this.mPasswordToggleTintMode = mode;
      this.mHasPasswordToggleTintMode = true;
      this.applyPasswordToggleTint();
   }

   private void passwordVisibilityToggleRequested(boolean shouldSkipAnimations) {
      if (this.mPasswordToggleEnabled) {
         int selection = this.mEditText.getSelectionEnd();
         if (this.hasPasswordTransformation()) {
            this.mEditText.setTransformationMethod((TransformationMethod)null);
            this.mPasswordToggledVisible = true;
         } else {
            this.mEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
            this.mPasswordToggledVisible = false;
         }

         this.mPasswordToggleView.setChecked(this.mPasswordToggledVisible);
         if (shouldSkipAnimations) {
            this.mPasswordToggleView.jumpDrawablesToCurrentState();
         }

         this.mEditText.setSelection(selection);
      }

   }

   private boolean hasPasswordTransformation() {
      return this.mEditText != null && this.mEditText.getTransformationMethod() instanceof PasswordTransformationMethod;
   }

   private boolean shouldShowPasswordIcon() {
      return this.mPasswordToggleEnabled && (this.hasPasswordTransformation() || this.mPasswordToggledVisible);
   }

   private void applyPasswordToggleTint() {
      if (this.mPasswordToggleDrawable != null && (this.mHasPasswordToggleTintList || this.mHasPasswordToggleTintMode)) {
         this.mPasswordToggleDrawable = DrawableCompat.wrap(this.mPasswordToggleDrawable).mutate();
         if (this.mHasPasswordToggleTintList) {
            DrawableCompat.setTintList(this.mPasswordToggleDrawable, this.mPasswordToggleTintList);
         }

         if (this.mHasPasswordToggleTintMode) {
            DrawableCompat.setTintMode(this.mPasswordToggleDrawable, this.mPasswordToggleTintMode);
         }

         if (this.mPasswordToggleView != null && this.mPasswordToggleView.getDrawable() != this.mPasswordToggleDrawable) {
            this.mPasswordToggleView.setImageDrawable(this.mPasswordToggleDrawable);
         }
      }

   }

   protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
      super.onLayout(changed, left, top, right, bottom);
      if (this.mHintEnabled && this.mEditText != null) {
         Rect rect = this.mTmpRect;
         ViewGroupUtils.getDescendantRect(this, this.mEditText, rect);
         int l = rect.left + this.mEditText.getCompoundPaddingLeft();
         int r = rect.right - this.mEditText.getCompoundPaddingRight();
         this.mCollapsingTextHelper.setExpandedBounds(l, rect.top + this.mEditText.getCompoundPaddingTop(), r, rect.bottom - this.mEditText.getCompoundPaddingBottom());
         this.mCollapsingTextHelper.setCollapsedBounds(l, this.getPaddingTop(), r, bottom - top - this.getPaddingBottom());
         this.mCollapsingTextHelper.recalculate();
      }

   }

   private void collapseHint(boolean animate) {
      if (this.mAnimator != null && this.mAnimator.isRunning()) {
         this.mAnimator.cancel();
      }

      if (animate && this.mHintAnimationEnabled) {
         this.animateToExpansionFraction(1.0F);
      } else {
         this.mCollapsingTextHelper.setExpansionFraction(1.0F);
      }

      this.mHintExpanded = false;
   }

   protected void drawableStateChanged() {
      if (!this.mInDrawableStateChanged) {
         this.mInDrawableStateChanged = true;
         super.drawableStateChanged();
         int[] state = this.getDrawableState();
         boolean changed = false;
         this.updateLabelState(ViewCompat.isLaidOut(this) && this.isEnabled());
         this.updateEditTextBackground();
         if (this.mCollapsingTextHelper != null) {
            changed |= this.mCollapsingTextHelper.setState(state);
         }

         if (changed) {
            this.invalidate();
         }

         this.mInDrawableStateChanged = false;
      }
   }

   private void expandHint(boolean animate) {
      if (this.mAnimator != null && this.mAnimator.isRunning()) {
         this.mAnimator.cancel();
      }

      if (animate && this.mHintAnimationEnabled) {
         this.animateToExpansionFraction(0.0F);
      } else {
         this.mCollapsingTextHelper.setExpansionFraction(0.0F);
      }

      this.mHintExpanded = true;
   }

   @VisibleForTesting
   void animateToExpansionFraction(float target) {
      if (this.mCollapsingTextHelper.getExpansionFraction() != target) {
         if (this.mAnimator == null) {
            this.mAnimator = new ValueAnimator();
            this.mAnimator.setInterpolator(AnimationUtils.LINEAR_INTERPOLATOR);
            this.mAnimator.setDuration(200L);
            this.mAnimator.addUpdateListener(new AnimatorUpdateListener() {
               public void onAnimationUpdate(ValueAnimator animator) {
                  TextInputLayout.this.mCollapsingTextHelper.setExpansionFraction(((Float)animator.getAnimatedValue()).floatValue());
               }
            });
         }

         this.mAnimator.setFloatValues(new float[]{this.mCollapsingTextHelper.getExpansionFraction(), target});
         this.mAnimator.start();
      }
   }

   @VisibleForTesting
   final boolean isHintExpanded() {
      return this.mHintExpanded;
   }

   private static boolean arrayContains(int[] array, int value) {
      int[] var2 = array;
      int var3 = array.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         int v = var2[var4];
         if (v == value) {
            return true;
         }
      }

      return false;
   }

   private class TextInputAccessibilityDelegate extends AccessibilityDelegateCompat {
      public void onInitializeAccessibilityEvent(View host, AccessibilityEvent event) {
         super.onInitializeAccessibilityEvent(host, event);
         event.setClassName(TextInputLayout.class.getSimpleName());
      }

      public void onPopulateAccessibilityEvent(View host, AccessibilityEvent event) {
         super.onPopulateAccessibilityEvent(host, event);
         CharSequence text = TextInputLayout.this.mCollapsingTextHelper.getText();
         if (!TextUtils.isEmpty(text)) {
            event.getText().add(text);
         }

      }

      public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfoCompat info) {
         super.onInitializeAccessibilityNodeInfo(host, info);
         info.setClassName(TextInputLayout.class.getSimpleName());
         CharSequence text = TextInputLayout.this.mCollapsingTextHelper.getText();
         if (!TextUtils.isEmpty(text)) {
            info.setText(text);
         }

         if (TextInputLayout.this.mEditText != null) {
            info.setLabelFor(TextInputLayout.this.mEditText);
         }

         CharSequence error = TextInputLayout.this.mErrorView != null ? TextInputLayout.this.mErrorView.getText() : null;
         if (!TextUtils.isEmpty(error)) {
            info.setContentInvalid(true);
            info.setError(error);
         }

      }
   }

   static class SavedState extends AbsSavedState {
      CharSequence error;
      boolean isPasswordToggledVisible;
      public static final Creator CREATOR = new ClassLoaderCreator() {
         public TextInputLayout.SavedState createFromParcel(Parcel in, ClassLoader loader) {
            return new TextInputLayout.SavedState(in, loader);
         }

         public TextInputLayout.SavedState createFromParcel(Parcel in) {
            return new TextInputLayout.SavedState(in, (ClassLoader)null);
         }

         public TextInputLayout.SavedState[] newArray(int size) {
            return new TextInputLayout.SavedState[size];
         }
      };

      SavedState(Parcelable superState) {
         super(superState);
      }

      SavedState(Parcel source, ClassLoader loader) {
         super(source, loader);
         this.error = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(source);
         this.isPasswordToggledVisible = source.readInt() == 1;
      }

      public void writeToParcel(Parcel dest, int flags) {
         super.writeToParcel(dest, flags);
         TextUtils.writeToParcel(this.error, dest, flags);
         dest.writeInt(this.isPasswordToggledVisible ? 1 : 0);
      }

      public String toString() {
         return "TextInputLayout.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " error=" + this.error + "}";
      }
   }
}
