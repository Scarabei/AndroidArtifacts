package android.support.v7.widget;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.graphics.Rect;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v7.appcompat.R.dimen;
import android.support.v7.appcompat.R.id;
import android.support.v7.appcompat.R.layout;
import android.support.v7.appcompat.R.style;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.View.MeasureSpec;
import android.view.WindowManager.LayoutParams;
import android.widget.TextView;

@RestrictTo({Scope.LIBRARY_GROUP})
class TooltipPopup {
   private static final String TAG = "TooltipPopup";
   private final Context mContext;
   private final View mContentView;
   private final TextView mMessageView;
   private final LayoutParams mLayoutParams = new LayoutParams();
   private final Rect mTmpDisplayFrame = new Rect();
   private final int[] mTmpAnchorPos = new int[2];
   private final int[] mTmpAppPos = new int[2];

   TooltipPopup(Context context) {
      this.mContext = context;
      this.mContentView = LayoutInflater.from(this.mContext).inflate(layout.tooltip, (ViewGroup)null);
      this.mMessageView = (TextView)this.mContentView.findViewById(id.message);
      this.mLayoutParams.setTitle(this.getClass().getSimpleName());
      this.mLayoutParams.packageName = this.mContext.getPackageName();
      this.mLayoutParams.type = 1002;
      this.mLayoutParams.width = -2;
      this.mLayoutParams.height = -2;
      this.mLayoutParams.format = -3;
      this.mLayoutParams.windowAnimations = style.Animation_AppCompat_Tooltip;
      this.mLayoutParams.flags = 24;
   }

   void show(View anchorView, int anchorX, int anchorY, boolean fromTouch, CharSequence tooltipText) {
      if (this.isShowing()) {
         this.hide();
      }

      this.mMessageView.setText(tooltipText);
      this.computePosition(anchorView, anchorX, anchorY, fromTouch, this.mLayoutParams);
      WindowManager wm = (WindowManager)this.mContext.getSystemService("window");
      wm.addView(this.mContentView, this.mLayoutParams);
   }

   void hide() {
      if (this.isShowing()) {
         WindowManager wm = (WindowManager)this.mContext.getSystemService("window");
         wm.removeView(this.mContentView);
      }
   }

   boolean isShowing() {
      return this.mContentView.getParent() != null;
   }

   private void computePosition(View anchorView, int anchorX, int anchorY, boolean fromTouch, LayoutParams outParams) {
      int tooltipPreciseAnchorThreshold = this.mContext.getResources().getDimensionPixelOffset(dimen.tooltip_precise_anchor_threshold);
      int offsetX;
      if (anchorView.getWidth() >= tooltipPreciseAnchorThreshold) {
         offsetX = anchorX;
      } else {
         offsetX = anchorView.getWidth() / 2;
      }

      int offsetBelow;
      int offsetAbove;
      int tooltipOffset;
      if (anchorView.getHeight() >= tooltipPreciseAnchorThreshold) {
         tooltipOffset = this.mContext.getResources().getDimensionPixelOffset(dimen.tooltip_precise_anchor_extra_offset);
         offsetBelow = anchorY + tooltipOffset;
         offsetAbove = anchorY - tooltipOffset;
      } else {
         offsetBelow = anchorView.getHeight();
         offsetAbove = 0;
      }

      outParams.gravity = 49;
      tooltipOffset = this.mContext.getResources().getDimensionPixelOffset(fromTouch ? dimen.tooltip_y_offset_touch : dimen.tooltip_y_offset_non_touch);
      View appView = getAppRootView(anchorView);
      if (appView == null) {
         Log.e("TooltipPopup", "Cannot find app view");
      } else {
         appView.getWindowVisibleDisplayFrame(this.mTmpDisplayFrame);
         int tooltipHeight;
         int yAbove;
         if (this.mTmpDisplayFrame.left < 0 && this.mTmpDisplayFrame.top < 0) {
            Resources res = this.mContext.getResources();
            yAbove = res.getIdentifier("status_bar_height", "dimen", "android");
            if (yAbove != 0) {
               tooltipHeight = res.getDimensionPixelSize(yAbove);
            } else {
               tooltipHeight = 0;
            }

            DisplayMetrics metrics = res.getDisplayMetrics();
            this.mTmpDisplayFrame.set(0, tooltipHeight, metrics.widthPixels, metrics.heightPixels);
         }

         appView.getLocationOnScreen(this.mTmpAppPos);
         anchorView.getLocationOnScreen(this.mTmpAnchorPos);
         this.mTmpAnchorPos[0] -= this.mTmpAppPos[0];
         this.mTmpAnchorPos[1] -= this.mTmpAppPos[1];
         outParams.x = this.mTmpAnchorPos[0] + offsetX - this.mTmpDisplayFrame.width() / 2;
         int spec = MeasureSpec.makeMeasureSpec(0, 0);
         this.mContentView.measure(spec, spec);
         tooltipHeight = this.mContentView.getMeasuredHeight();
         yAbove = this.mTmpAnchorPos[1] + offsetAbove - tooltipOffset - tooltipHeight;
         int yBelow = this.mTmpAnchorPos[1] + offsetBelow + tooltipOffset;
         if (fromTouch) {
            if (yAbove >= 0) {
               outParams.y = yAbove;
            } else {
               outParams.y = yBelow;
            }
         } else if (yBelow + tooltipHeight <= this.mTmpDisplayFrame.height()) {
            outParams.y = yBelow;
         } else {
            outParams.y = yAbove;
         }

      }
   }

   private static View getAppRootView(View anchorView) {
      for(Context context = anchorView.getContext(); context instanceof ContextWrapper; context = ((ContextWrapper)context).getBaseContext()) {
         if (context instanceof Activity) {
            return ((Activity)context).getWindow().getDecorView();
         }
      }

      return anchorView.getRootView();
   }
}
