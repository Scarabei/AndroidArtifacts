package android.support.design.widget;

import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Build.VERSION;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.support.design.R.attr;
import android.support.design.R.id;
import android.support.design.R.layout;
import android.support.design.R.style;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v7.app.AppCompatDialog;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;

public class BottomSheetDialog extends AppCompatDialog {
   private BottomSheetBehavior mBehavior;
   boolean mCancelable;
   private boolean mCanceledOnTouchOutside;
   private boolean mCanceledOnTouchOutsideSet;
   private BottomSheetBehavior.BottomSheetCallback mBottomSheetCallback;

   public BottomSheetDialog(@NonNull Context context) {
      this(context, 0);
   }

   public BottomSheetDialog(@NonNull Context context, @StyleRes int theme) {
      super(context, getThemeResId(context, theme));
      this.mCancelable = true;
      this.mCanceledOnTouchOutside = true;
      this.mBottomSheetCallback = new BottomSheetBehavior.BottomSheetCallback() {
         public void onStateChanged(@NonNull View bottomSheet, int newState) {
            if (newState == 5) {
               BottomSheetDialog.this.cancel();
            }

         }

         public void onSlide(@NonNull View bottomSheet, float slideOffset) {
         }
      };
      this.supportRequestWindowFeature(1);
   }

   protected BottomSheetDialog(@NonNull Context context, boolean cancelable, OnCancelListener cancelListener) {
      super(context, cancelable, cancelListener);
      this.mCancelable = true;
      this.mCanceledOnTouchOutside = true;
      this.mBottomSheetCallback = new BottomSheetBehavior.BottomSheetCallback() {
         public void onStateChanged(@NonNull View bottomSheet, int newState) {
            if (newState == 5) {
               BottomSheetDialog.this.cancel();
            }

         }

         public void onSlide(@NonNull View bottomSheet, float slideOffset) {
         }
      };
      this.supportRequestWindowFeature(1);
      this.mCancelable = cancelable;
   }

   public void setContentView(@LayoutRes int layoutResId) {
      super.setContentView(this.wrapInBottomSheet(layoutResId, (View)null, (LayoutParams)null));
   }

   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      Window window = this.getWindow();
      if (window != null) {
         if (VERSION.SDK_INT >= 21) {
            window.clearFlags(67108864);
            window.addFlags(Integer.MIN_VALUE);
         }

         window.setLayout(-1, -1);
      }

   }

   public void setContentView(View view) {
      super.setContentView(this.wrapInBottomSheet(0, view, (LayoutParams)null));
   }

   public void setContentView(View view, LayoutParams params) {
      super.setContentView(this.wrapInBottomSheet(0, view, params));
   }

   public void setCancelable(boolean cancelable) {
      super.setCancelable(cancelable);
      if (this.mCancelable != cancelable) {
         this.mCancelable = cancelable;
         if (this.mBehavior != null) {
            this.mBehavior.setHideable(cancelable);
         }
      }

   }

   protected void onStart() {
      super.onStart();
      if (this.mBehavior != null) {
         this.mBehavior.setState(4);
      }

   }

   public void setCanceledOnTouchOutside(boolean cancel) {
      super.setCanceledOnTouchOutside(cancel);
      if (cancel && !this.mCancelable) {
         this.mCancelable = true;
      }

      this.mCanceledOnTouchOutside = cancel;
      this.mCanceledOnTouchOutsideSet = true;
   }

   private View wrapInBottomSheet(int layoutResId, View view, LayoutParams params) {
      FrameLayout container = (FrameLayout)View.inflate(this.getContext(), layout.design_bottom_sheet_dialog, (ViewGroup)null);
      CoordinatorLayout coordinator = (CoordinatorLayout)container.findViewById(id.coordinator);
      if (layoutResId != 0 && view == null) {
         view = this.getLayoutInflater().inflate(layoutResId, coordinator, false);
      }

      FrameLayout bottomSheet = (FrameLayout)coordinator.findViewById(id.design_bottom_sheet);
      this.mBehavior = BottomSheetBehavior.from(bottomSheet);
      this.mBehavior.setBottomSheetCallback(this.mBottomSheetCallback);
      this.mBehavior.setHideable(this.mCancelable);
      if (params == null) {
         bottomSheet.addView(view);
      } else {
         bottomSheet.addView(view, params);
      }

      coordinator.findViewById(id.touch_outside).setOnClickListener(new OnClickListener() {
         public void onClick(View view) {
            if (BottomSheetDialog.this.mCancelable && BottomSheetDialog.this.isShowing() && BottomSheetDialog.this.shouldWindowCloseOnTouchOutside()) {
               BottomSheetDialog.this.cancel();
            }

         }
      });
      ViewCompat.setAccessibilityDelegate(bottomSheet, new AccessibilityDelegateCompat() {
         public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfoCompat info) {
            super.onInitializeAccessibilityNodeInfo(host, info);
            if (BottomSheetDialog.this.mCancelable) {
               info.addAction(1048576);
               info.setDismissable(true);
            } else {
               info.setDismissable(false);
            }

         }

         public boolean performAccessibilityAction(View host, int action, Bundle args) {
            if (action == 1048576 && BottomSheetDialog.this.mCancelable) {
               BottomSheetDialog.this.cancel();
               return true;
            } else {
               return super.performAccessibilityAction(host, action, args);
            }
         }
      });
      bottomSheet.setOnTouchListener(new OnTouchListener() {
         public boolean onTouch(View view, MotionEvent event) {
            return true;
         }
      });
      return container;
   }

   boolean shouldWindowCloseOnTouchOutside() {
      if (!this.mCanceledOnTouchOutsideSet) {
         if (VERSION.SDK_INT < 11) {
            this.mCanceledOnTouchOutside = true;
         } else {
            TypedArray a = this.getContext().obtainStyledAttributes(new int[]{16843611});
            this.mCanceledOnTouchOutside = a.getBoolean(0, true);
            a.recycle();
         }

         this.mCanceledOnTouchOutsideSet = true;
      }

      return this.mCanceledOnTouchOutside;
   }

   private static int getThemeResId(Context context, int themeId) {
      if (themeId == 0) {
         TypedValue outValue = new TypedValue();
         if (context.getTheme().resolveAttribute(attr.bottomSheetDialogTheme, outValue, true)) {
            themeId = outValue.resourceId;
         } else {
            themeId = style.Theme_Design_Light_BottomSheetDialog;
         }
      }

      return themeId;
   }
}
