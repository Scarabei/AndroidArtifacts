package com.google.android.gms.internal;

import android.content.Context;
import android.os.SystemClock;

@zzzn
public abstract class zzxt extends zzafp {
   protected final zzxy zzQP;
   protected final Context mContext;
   protected final Object mLock = new Object();
   protected final Object zzQT = new Object();
   protected final zzafg zzQQ;
   protected zzaai zzQR;

   protected zzxt(Context var1, zzafg var2, zzxy var3) {
      super(true);
      this.mContext = var1;
      this.zzQQ = var2;
      this.zzQR = var2.zzXY;
      this.zzQP = var3;
   }

   public final void zzbd() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         zzafr.zzaC("AdRendererBackgroundTask started.");
         int var2 = this.zzQQ.errorCode;

         try {
            this.zzd(SystemClock.elapsedRealtime());
         } catch (zzxw var5) {
            if ((var2 = var5.getErrorCode()) != 3 && var2 != -1) {
               zzafr.zzaT(var5.getMessage());
            } else {
               zzafr.zzaS(var5.getMessage());
            }

            if (this.zzQR == null) {
               this.zzQR = new zzaai(var2);
            } else {
               this.zzQR = new zzaai(var2, this.zzQR.zzMg);
            }

            zzagz.zzZr.post(new zzxu(this));
         }

         zzaff var3 = this.zzs(var2);
         zzagz.zzZr.post(new zzxv(this, var3));
      }
   }

   public void onStop() {
   }

   protected abstract void zzd(long var1) throws zzxw;

   protected abstract zzaff zzs(int var1);
}
