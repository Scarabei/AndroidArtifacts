package com.google.android.gms.ads.internal;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.widget.ViewSwitcher;
import com.google.android.gms.internal.zzafr;
import com.google.android.gms.internal.zzahq;
import com.google.android.gms.internal.zzaix;
import com.google.android.gms.internal.zzaka;
import java.util.ArrayList;

public final class zzbu extends ViewSwitcher {
   private final zzahq zzwB;
   @Nullable
   private final zzaix zzwC;
   private boolean zzwD;

   public zzbu(Context var1, String var2, String var3, OnGlobalLayoutListener var4, OnScrollChangedListener var5) {
      super(var1);
      this.zzwB = new zzahq(var1);
      this.zzwB.setAdUnitId(var2);
      this.zzwB.zzaO(var3);
      this.zzwD = true;
      if (var1 instanceof Activity) {
         this.zzwC = new zzaix((Activity)var1, this, var4, var5);
      } else {
         this.zzwC = new zzaix((Activity)null, this, var4, var5);
      }

      this.zzwC.zzig();
   }

   public final zzahq zzcf() {
      return this.zzwB;
   }

   public final boolean onInterceptTouchEvent(MotionEvent var1) {
      if (this.zzwD) {
         this.zzwB.zzf(var1);
      }

      return false;
   }

   public final void removeAllViews() {
      ArrayList var1 = new ArrayList();

      for(int var2 = 0; var2 < this.getChildCount(); ++var2) {
         View var3;
         if ((var3 = this.getChildAt(var2)) != null && var3 instanceof zzaka) {
            var1.add((zzaka)var3);
         }
      }

      super.removeAllViews();
      ArrayList var4;
      int var5 = (var4 = (ArrayList)var1).size();
      int var6 = 0;

      while(var6 < var5) {
         Object var10000 = var4.get(var6);
         ++var6;
         ((zzaka)var10000).destroy();
      }

   }

   public final void zzcg() {
      zzafr.v("Disable position monitoring on adFrame.");
      if (this.zzwC != null) {
         this.zzwC.zzih();
      }

   }

   public final void zzch() {
      zzafr.v("Enable debug gesture detector on adFrame.");
      this.zzwD = true;
   }

   public final void zzci() {
      zzafr.v("Disable debug gesture detector on adFrame.");
      this.zzwD = false;
   }

   protected final void onAttachedToWindow() {
      super.onAttachedToWindow();
      if (this.zzwC != null) {
         this.zzwC.onAttachedToWindow();
      }

   }

   protected final void onDetachedFromWindow() {
      super.onDetachedFromWindow();
      if (this.zzwC != null) {
         this.zzwC.onDetachedFromWindow();
      }

   }
}
