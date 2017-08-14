package android.support.design.internal;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.design.R.dimen;
import android.support.design.R.id;
import android.support.design.R.styleable;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

@RestrictTo({Scope.LIBRARY_GROUP})
public class SnackbarContentLayout extends LinearLayout implements BaseTransientBottomBar.ContentViewCallback {
   private TextView mMessageView;
   private Button mActionView;
   private int mMaxWidth;
   private int mMaxInlineActionWidth;

   public SnackbarContentLayout(Context context) {
      this(context, (AttributeSet)null);
   }

   public SnackbarContentLayout(Context context, AttributeSet attrs) {
      super(context, attrs);
      TypedArray a = context.obtainStyledAttributes(attrs, styleable.SnackbarLayout);
      this.mMaxWidth = a.getDimensionPixelSize(styleable.SnackbarLayout_android_maxWidth, -1);
      this.mMaxInlineActionWidth = a.getDimensionPixelSize(styleable.SnackbarLayout_maxActionInlineWidth, -1);
      a.recycle();
   }

   protected void onFinishInflate() {
      super.onFinishInflate();
      this.mMessageView = (TextView)this.findViewById(id.snackbar_text);
      this.mActionView = (Button)this.findViewById(id.snackbar_action);
   }

   public TextView getMessageView() {
      return this.mMessageView;
   }

   public Button getActionView() {
      return this.mActionView;
   }

   protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
      super.onMeasure(widthMeasureSpec, heightMeasureSpec);
      if (this.mMaxWidth > 0 && this.getMeasuredWidth() > this.mMaxWidth) {
         widthMeasureSpec = MeasureSpec.makeMeasureSpec(this.mMaxWidth, 1073741824);
         super.onMeasure(widthMeasureSpec, heightMeasureSpec);
      }

      int multiLineVPadding = this.getResources().getDimensionPixelSize(dimen.design_snackbar_padding_vertical_2lines);
      int singleLineVPadding = this.getResources().getDimensionPixelSize(dimen.design_snackbar_padding_vertical);
      boolean isMultiLine = this.mMessageView.getLayout().getLineCount() > 1;
      boolean remeasure = false;
      if (isMultiLine && this.mMaxInlineActionWidth > 0 && this.mActionView.getMeasuredWidth() > this.mMaxInlineActionWidth) {
         if (this.updateViewsWithinLayout(1, multiLineVPadding, multiLineVPadding - singleLineVPadding)) {
            remeasure = true;
         }
      } else {
         int messagePadding = isMultiLine ? multiLineVPadding : singleLineVPadding;
         if (this.updateViewsWithinLayout(0, messagePadding, messagePadding)) {
            remeasure = true;
         }
      }

      if (remeasure) {
         super.onMeasure(widthMeasureSpec, heightMeasureSpec);
      }

   }

   private boolean updateViewsWithinLayout(int orientation, int messagePadTop, int messagePadBottom) {
      boolean changed = false;
      if (orientation != this.getOrientation()) {
         this.setOrientation(orientation);
         changed = true;
      }

      if (this.mMessageView.getPaddingTop() != messagePadTop || this.mMessageView.getPaddingBottom() != messagePadBottom) {
         updateTopBottomPadding(this.mMessageView, messagePadTop, messagePadBottom);
         changed = true;
      }

      return changed;
   }

   private static void updateTopBottomPadding(View view, int topPadding, int bottomPadding) {
      if (ViewCompat.isPaddingRelative(view)) {
         ViewCompat.setPaddingRelative(view, ViewCompat.getPaddingStart(view), topPadding, ViewCompat.getPaddingEnd(view), bottomPadding);
      } else {
         view.setPadding(view.getPaddingLeft(), topPadding, view.getPaddingRight(), bottomPadding);
      }

   }

   public void animateContentIn(int delay, int duration) {
      this.mMessageView.setAlpha(0.0F);
      this.mMessageView.animate().alpha(1.0F).setDuration((long)duration).setStartDelay((long)delay).start();
      if (this.mActionView.getVisibility() == 0) {
         this.mActionView.setAlpha(0.0F);
         this.mActionView.animate().alpha(1.0F).setDuration((long)duration).setStartDelay((long)delay).start();
      }

   }

   public void animateContentOut(int delay, int duration) {
      this.mMessageView.setAlpha(1.0F);
      this.mMessageView.animate().alpha(0.0F).setDuration((long)duration).setStartDelay((long)delay).start();
      if (this.mActionView.getVisibility() == 0) {
         this.mActionView.setAlpha(1.0F);
         this.mActionView.animate().alpha(0.0F).setDuration((long)duration).setStartDelay((long)delay).start();
      }

   }
}
