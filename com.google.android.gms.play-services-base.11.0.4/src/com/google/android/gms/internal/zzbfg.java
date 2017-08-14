package com.google.android.gms.internal;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.Callback;
import android.graphics.drawable.Drawable.ConstantState;
import android.os.SystemClock;

public final class zzbfg extends Drawable implements Callback {
   private int zzaGo;
   private long zzagZ;
   private int mFrom;
   private int zzaGp;
   private int zzaGq;
   private int zzaGr;
   private int zzaGs;
   private boolean zzaGj;
   private boolean zzaGt;
   private zzbfk zzaGu;
   private Drawable zzaGv;
   private Drawable zzaGw;
   private boolean zzaGx;
   private boolean zzaGy;
   private boolean zzaGz;
   private int zzaGA;

   public zzbfg(Drawable var1, Drawable var2) {
      this((zzbfk)null);
      if (var1 == null) {
         var1 = zzbfi.zzqX();
      }

      this.zzaGv = (Drawable)var1;
      ((Drawable)var1).setCallback(this);
      this.zzaGu.zzaGD |= ((Drawable)var1).getChangingConfigurations();
      if (var2 == null) {
         var2 = zzbfi.zzqX();
      }

      this.zzaGw = (Drawable)var2;
      ((Drawable)var2).setCallback(this);
      this.zzaGu.zzaGD |= ((Drawable)var2).getChangingConfigurations();
   }

   zzbfg(zzbfk var1) {
      this.zzaGo = 0;
      this.zzaGq = 255;
      this.zzaGs = 0;
      this.zzaGj = true;
      this.zzaGu = new zzbfk(var1);
   }

   public final void invalidateDrawable(Drawable var1) {
      Callback var2;
      if ((var2 = this.getCallback()) != null) {
         var2.invalidateDrawable(this);
      }

   }

   public final void scheduleDrawable(Drawable var1, Runnable var2, long var3) {
      Callback var5;
      if ((var5 = this.getCallback()) != null) {
         var5.scheduleDrawable(this, var2, var3);
      }

   }

   public final void unscheduleDrawable(Drawable var1, Runnable var2) {
      Callback var3;
      if ((var3 = this.getCallback()) != null) {
         var3.unscheduleDrawable(this, var2);
      }

   }

   public final int getChangingConfigurations() {
      return super.getChangingConfigurations() | this.zzaGu.mChangingConfigurations | this.zzaGu.zzaGD;
   }

   public final void setAlpha(int var1) {
      if (this.zzaGs == this.zzaGq) {
         this.zzaGs = var1;
      }

      this.zzaGq = var1;
      this.invalidateSelf();
   }

   public final void setColorFilter(ColorFilter var1) {
      this.zzaGv.setColorFilter(var1);
      this.zzaGw.setColorFilter(var1);
   }

   public final int getIntrinsicWidth() {
      return Math.max(this.zzaGv.getIntrinsicWidth(), this.zzaGw.getIntrinsicWidth());
   }

   public final int getIntrinsicHeight() {
      return Math.max(this.zzaGv.getIntrinsicHeight(), this.zzaGw.getIntrinsicHeight());
   }

   protected final void onBoundsChange(Rect var1) {
      this.zzaGv.setBounds(var1);
      this.zzaGw.setBounds(var1);
   }

   public final ConstantState getConstantState() {
      if (this.canConstantState()) {
         this.zzaGu.mChangingConfigurations = this.getChangingConfigurations();
         return this.zzaGu;
      } else {
         return null;
      }
   }

   public final int getOpacity() {
      if (!this.zzaGz) {
         this.zzaGA = Drawable.resolveOpacity(this.zzaGv.getOpacity(), this.zzaGw.getOpacity());
         this.zzaGz = true;
      }

      return this.zzaGA;
   }

   private final boolean canConstantState() {
      if (!this.zzaGx) {
         this.zzaGy = this.zzaGv.getConstantState() != null && this.zzaGw.getConstantState() != null;
         this.zzaGx = true;
      }

      return this.zzaGy;
   }

   public final Drawable mutate() {
      if (!this.zzaGt && super.mutate() == this) {
         if (!this.canConstantState()) {
            throw new IllegalStateException("One or more children of this LayerDrawable does not have constant state; this drawable cannot be mutated.");
         }

         this.zzaGv.mutate();
         this.zzaGw.mutate();
         this.zzaGt = true;
      }

      return this;
   }

   public final Drawable zzqW() {
      return this.zzaGw;
   }

   public final void startTransition(int var1) {
      this.mFrom = 0;
      this.zzaGp = this.zzaGq;
      this.zzaGs = 0;
      this.zzaGr = 250;
      this.zzaGo = 1;
      this.invalidateSelf();
   }

   public final void draw(Canvas var1) {
      boolean var2 = true;
      switch(this.zzaGo) {
      case 1:
         this.zzagZ = SystemClock.uptimeMillis();
         var2 = false;
         this.zzaGo = 2;
         break;
      case 2:
         if (this.zzagZ >= 0L) {
            float var3;
            if (var2 = (var3 = (float)(SystemClock.uptimeMillis() - this.zzagZ) / (float)this.zzaGr) >= 1.0F) {
               this.zzaGo = 0;
            }

            var3 = Math.min(var3, 1.0F);
            this.zzaGs = (int)(0.0F + (float)this.zzaGp * var3);
         }
      }

      int var7 = this.zzaGs;
      boolean var4 = this.zzaGj;
      Drawable var5 = this.zzaGv;
      Drawable var6 = this.zzaGw;
      if (!var2) {
         if (var4) {
            var5.setAlpha(this.zzaGq - var7);
         }

         var5.draw(var1);
         if (var4) {
            var5.setAlpha(this.zzaGq);
         }

         if (var7 > 0) {
            var6.setAlpha(var7);
            var6.draw(var1);
            var6.setAlpha(this.zzaGq);
         }

         this.invalidateSelf();
      } else {
         if (!var4 || var7 == 0) {
            var5.draw(var1);
         }

         if (var7 == this.zzaGq) {
            var6.setAlpha(this.zzaGq);
            var6.draw(var1);
         }

      }
   }
}
