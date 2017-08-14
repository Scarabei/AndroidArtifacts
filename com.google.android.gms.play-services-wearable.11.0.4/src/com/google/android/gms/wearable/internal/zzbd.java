package com.google.android.gms.wearable.internal;

public final class zzbd extends zzdh {
   private final Object mLock = new Object();
   private zzbe zzbSv;
   private zzah zzbSr;

   public final void zza(zzbe var1) {
      Object var3 = this.mLock;
      zzah var2;
      synchronized(this.mLock) {
         this.zzbSv = (zzbe)com.google.android.gms.common.internal.zzbo.zzu(var1);
         var2 = this.zzbSr;
      }

      if (var2 != null) {
         var1.zzb(var2);
      }

   }

   public final void zzm(int var1, int var2) {
      Object var5 = this.mLock;
      zzbe var3;
      zzah var4;
      synchronized(this.mLock) {
         var3 = this.zzbSv;
         var4 = this.zzbSr = new zzah(var1, var2);
      }

      if (var3 != null) {
         var3.zzb(var4);
      }

   }
}
