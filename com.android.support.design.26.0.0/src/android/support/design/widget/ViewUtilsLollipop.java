package android.support.design.widget;

import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.RequiresApi;
import android.support.design.R.attr;
import android.support.design.R.integer;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewOutlineProvider;

@RequiresApi(21)
class ViewUtilsLollipop {
   private static final int[] STATE_LIST_ANIM_ATTRS = new int[]{16843848};

   static void setBoundsViewOutlineProvider(View view) {
      view.setOutlineProvider(ViewOutlineProvider.BOUNDS);
   }

   static void setStateListAnimatorFromAttrs(View view, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
      Context context = view.getContext();
      TypedArray a = context.obtainStyledAttributes(attrs, STATE_LIST_ANIM_ATTRS, defStyleAttr, defStyleRes);

      try {
         if (a.hasValue(0)) {
            android.animation.StateListAnimator sla = AnimatorInflater.loadStateListAnimator(context, a.getResourceId(0, 0));
            view.setStateListAnimator(sla);
         }
      } finally {
         a.recycle();
      }

   }

   static void setDefaultAppBarLayoutStateListAnimator(View view, float elevation) {
      int dur = view.getResources().getInteger(integer.app_bar_elevation_anim_duration);
      android.animation.StateListAnimator sla = new android.animation.StateListAnimator();
      sla.addState(new int[]{16842766, attr.state_collapsible, -attr.state_collapsed}, ObjectAnimator.ofFloat(view, "elevation", new float[]{0.0F}).setDuration((long)dur));
      sla.addState(new int[]{16842766}, ObjectAnimator.ofFloat(view, "elevation", new float[]{elevation}).setDuration((long)dur));
      sla.addState(new int[0], ObjectAnimator.ofFloat(view, "elevation", new float[]{0.0F}).setDuration(0L));
      view.setStateListAnimator(sla);
   }
}
