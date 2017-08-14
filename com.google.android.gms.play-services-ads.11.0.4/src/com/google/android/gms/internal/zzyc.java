package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.view.Window;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.PopupWindow;

@zzzn
@TargetApi(19)
public final class zzyc extends zzxz {
   private Object zzQZ = new Object();
   private PopupWindow zzRa;
   private boolean zzRb = false;

   zzyc(Context var1, zzafg var2, zzaka var3, zzxy var4) {
      super(var1, var2, var3, var4);
   }

   protected final void zzgq() {
      Window var1 = null;
      if (this.mContext instanceof Activity) {
         var1 = ((Activity)this.mContext).getWindow();
      }

      if (var1 != null && var1.getDecorView() != null) {
         if (!((Activity)this.mContext).isDestroyed()) {
            FrameLayout var2;
            (var2 = new FrameLayout(this.mContext)).setLayoutParams(new LayoutParams(-1, -1));
            var2.addView(this.zzJH.getView(), -1, -1);
            Object var3 = this.zzQZ;
            synchronized(this.zzQZ) {
               if (!this.zzRb) {
                  this.zzRa = new PopupWindow(var2, 1, 1, false);
                  this.zzRa.setOutsideTouchable(true);
                  this.zzRa.setClippingEnabled(false);
                  zzafr.zzaC("Displaying the 1x1 popup off the screen.");

                  try {
                     this.zzRa.showAtLocation(var1.getDecorView(), 0, -1, -1);
                  } catch (Exception var5) {
                     this.zzRa = null;
                  }

               }
            }
         }
      }
   }

   public final void cancel() {
      this.zzgr();
      super.cancel();
   }

   protected final void zzr(int var1) {
      this.zzgr();
      super.zzr(var1);
   }

   private final void zzgr() {
      Object var1 = this.zzQZ;
      synchronized(this.zzQZ) {
         this.zzRb = true;
         if (this.mContext instanceof Activity && ((Activity)this.mContext).isDestroyed()) {
            this.zzRa = null;
         }

         if (this.zzRa != null) {
            if (this.zzRa.isShowing()) {
               this.zzRa.dismiss();
            }

            this.zzRa = null;
         }

      }
   }
}
