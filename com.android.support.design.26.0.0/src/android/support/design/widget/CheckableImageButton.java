package android.support.design.widget;

import android.content.Context;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v7.appcompat.R.attr;
import android.support.v7.widget.AppCompatImageButton;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Checkable;

@RestrictTo({Scope.LIBRARY_GROUP})
public class CheckableImageButton extends AppCompatImageButton implements Checkable {
   private static final int[] DRAWABLE_STATE_CHECKED = new int[]{16842912};
   private boolean mChecked;

   public CheckableImageButton(Context context) {
      this(context, (AttributeSet)null);
   }

   public CheckableImageButton(Context context, AttributeSet attrs) {
      this(context, attrs, attr.imageButtonStyle);
   }

   public CheckableImageButton(Context context, AttributeSet attrs, int defStyleAttr) {
      super(context, attrs, defStyleAttr);
      ViewCompat.setAccessibilityDelegate(this, new AccessibilityDelegateCompat() {
         public void onInitializeAccessibilityEvent(View host, AccessibilityEvent event) {
            super.onInitializeAccessibilityEvent(host, event);
            event.setChecked(CheckableImageButton.this.isChecked());
         }

         public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfoCompat info) {
            super.onInitializeAccessibilityNodeInfo(host, info);
            info.setCheckable(true);
            info.setChecked(CheckableImageButton.this.isChecked());
         }
      });
   }

   public void setChecked(boolean checked) {
      if (this.mChecked != checked) {
         this.mChecked = checked;
         this.refreshDrawableState();
         this.sendAccessibilityEvent(2048);
      }

   }

   public boolean isChecked() {
      return this.mChecked;
   }

   public void toggle() {
      this.setChecked(!this.mChecked);
   }

   public int[] onCreateDrawableState(int extraSpace) {
      return this.mChecked ? mergeDrawableStates(super.onCreateDrawableState(extraSpace + DRAWABLE_STATE_CHECKED.length), DRAWABLE_STATE_CHECKED) : super.onCreateDrawableState(extraSpace);
   }
}
