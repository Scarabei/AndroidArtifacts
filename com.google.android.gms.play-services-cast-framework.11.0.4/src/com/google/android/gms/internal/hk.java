package com.google.android.gms.internal;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.support.v4.util.SimpleArrayMap;

public class hk extends AnimatorListenerAdapter {
   private SimpleArrayMap zzbUm = new SimpleArrayMap();

   public void onAnimationStart(Animator var1) {
      this.zzbUm.put(var1, false);
   }

   public void onAnimationCancel(Animator var1) {
      this.zzbUm.put(var1, true);
   }

   protected final boolean zzb(Animator var1) {
      return this.zzbUm.containsKey(var1) && ((Boolean)this.zzbUm.get(var1)).booleanValue();
   }
}
