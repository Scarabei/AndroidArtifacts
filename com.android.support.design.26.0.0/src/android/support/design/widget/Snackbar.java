package android.support.design.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.StringRes;
import android.support.annotation.RestrictTo.Scope;
import android.support.design.R.layout;
import android.support.design.internal.SnackbarContentLayout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.TextView;

public final class Snackbar extends BaseTransientBottomBar {
   public static final int LENGTH_INDEFINITE = -2;
   public static final int LENGTH_SHORT = -1;
   public static final int LENGTH_LONG = 0;
   @Nullable
   private BaseTransientBottomBar.BaseCallback mCallback;

   private Snackbar(ViewGroup parent, View content, BaseTransientBottomBar.ContentViewCallback contentViewCallback) {
      super(parent, content, contentViewCallback);
   }

   @NonNull
   public static Snackbar make(@NonNull View view, @NonNull CharSequence text, int duration) {
      ViewGroup parent = findSuitableParent(view);
      if (parent == null) {
         throw new IllegalArgumentException("No suitable parent found from the given view. Please provide a valid view.");
      } else {
         LayoutInflater inflater = LayoutInflater.from(parent.getContext());
         SnackbarContentLayout content = (SnackbarContentLayout)inflater.inflate(layout.design_layout_snackbar_include, parent, false);
         Snackbar snackbar = new Snackbar(parent, content, content);
         snackbar.setText(text);
         snackbar.setDuration(duration);
         return snackbar;
      }
   }

   @NonNull
   public static Snackbar make(@NonNull View view, @StringRes int resId, int duration) {
      return make(view, view.getResources().getText(resId), duration);
   }

   private static ViewGroup findSuitableParent(View view) {
      ViewGroup fallback = null;

      do {
         if (view instanceof CoordinatorLayout) {
            return (ViewGroup)view;
         }

         if (view instanceof FrameLayout) {
            if (view.getId() == 16908290) {
               return (ViewGroup)view;
            }

            fallback = (ViewGroup)view;
         }

         if (view != null) {
            ViewParent parent = view.getParent();
            view = parent instanceof View ? (View)parent : null;
         }
      } while(view != null);

      return fallback;
   }

   @NonNull
   public Snackbar setText(@NonNull CharSequence message) {
      SnackbarContentLayout contentLayout = (SnackbarContentLayout)this.mView.getChildAt(0);
      TextView tv = contentLayout.getMessageView();
      tv.setText(message);
      return this;
   }

   @NonNull
   public Snackbar setText(@StringRes int resId) {
      return this.setText(this.getContext().getText(resId));
   }

   @NonNull
   public Snackbar setAction(@StringRes int resId, OnClickListener listener) {
      return this.setAction(this.getContext().getText(resId), listener);
   }

   @NonNull
   public Snackbar setAction(CharSequence text, final OnClickListener listener) {
      SnackbarContentLayout contentLayout = (SnackbarContentLayout)this.mView.getChildAt(0);
      TextView tv = contentLayout.getActionView();
      if (!TextUtils.isEmpty(text) && listener != null) {
         tv.setVisibility(0);
         tv.setText(text);
         tv.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
               listener.onClick(view);
               Snackbar.this.dispatchDismiss(1);
            }
         });
      } else {
         tv.setVisibility(8);
         tv.setOnClickListener((OnClickListener)null);
      }

      return this;
   }

   @NonNull
   public Snackbar setActionTextColor(ColorStateList colors) {
      SnackbarContentLayout contentLayout = (SnackbarContentLayout)this.mView.getChildAt(0);
      TextView tv = contentLayout.getActionView();
      tv.setTextColor(colors);
      return this;
   }

   @NonNull
   public Snackbar setActionTextColor(@ColorInt int color) {
      SnackbarContentLayout contentLayout = (SnackbarContentLayout)this.mView.getChildAt(0);
      TextView tv = contentLayout.getActionView();
      tv.setTextColor(color);
      return this;
   }

   /** @deprecated */
   @Deprecated
   @NonNull
   public Snackbar setCallback(Snackbar.Callback callback) {
      if (this.mCallback != null) {
         this.removeCallback(this.mCallback);
      }

      if (callback != null) {
         this.addCallback(callback);
      }

      this.mCallback = callback;
      return this;
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   public static final class SnackbarLayout extends BaseTransientBottomBar.SnackbarBaseLayout {
      public SnackbarLayout(Context context) {
         super(context);
      }

      public SnackbarLayout(Context context, AttributeSet attrs) {
         super(context, attrs);
      }

      protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
         super.onMeasure(widthMeasureSpec, heightMeasureSpec);
         int childCount = this.getChildCount();
         int availableWidth = this.getMeasuredWidth() - this.getPaddingLeft() - this.getPaddingRight();

         for(int i = 0; i < childCount; ++i) {
            View child = this.getChildAt(i);
            if (child.getLayoutParams().width == -1) {
               child.measure(MeasureSpec.makeMeasureSpec(availableWidth, 1073741824), MeasureSpec.makeMeasureSpec(child.getMeasuredHeight(), 1073741824));
            }
         }

      }
   }

   public static class Callback extends BaseTransientBottomBar.BaseCallback {
      public static final int DISMISS_EVENT_SWIPE = 0;
      public static final int DISMISS_EVENT_ACTION = 1;
      public static final int DISMISS_EVENT_TIMEOUT = 2;
      public static final int DISMISS_EVENT_MANUAL = 3;
      public static final int DISMISS_EVENT_CONSECUTIVE = 4;

      public void onShown(Snackbar sb) {
      }

      public void onDismissed(Snackbar transientBottomBar, int event) {
      }
   }
}
