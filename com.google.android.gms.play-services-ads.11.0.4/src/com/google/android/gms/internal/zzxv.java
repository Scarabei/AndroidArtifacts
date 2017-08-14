package com.google.android.gms.internal;

final class zzxv implements Runnable {
   // $FF: synthetic field
   private zzaff zztx;
   // $FF: synthetic field
   private zzxt zzQW;

   zzxv(zzxt var1, zzaff var2) {
      this.zzQW = var1;
      this.zztx = var2;
      super();
   }

   public final void run() {
      Object var1 = this.zzQW.mLock;
      synchronized(this.zzQW.mLock) {
         zzaff var3 = this.zztx;
         this.zzQW.zzQP.zzb(var3);
      }
   }
}
