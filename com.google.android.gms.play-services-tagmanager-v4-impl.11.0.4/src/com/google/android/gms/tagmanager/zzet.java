package com.google.android.gms.tagmanager;

import android.content.Context;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

final class zzet implements zzag {
   private final String zzbDw;
   private final Context mContext;
   private final ScheduledExecutorService zzbFY;
   private final zzew zzbFZ;
   private ScheduledFuture zzbGa;
   private boolean mClosed;
   private zzal zzbFW;
   private String zzbDU;
   private zzdi zzbFV;

   public zzet(Context var1, String var2, zzal var3) {
      this(var1, var2, var3, (zzex)null, (zzew)null);
   }

   private zzet(Context var1, String var2, zzal var3, zzex var4, zzew var5) {
      this.zzbFW = var3;
      this.mContext = var1;
      this.zzbDw = var2;
      zzeu var6 = new zzeu(this);
      this.zzbFY = var6.zzBH();
      this.zzbFZ = new zzev(this);
   }

   public final synchronized void release() {
      this.zzBG();
      if (this.zzbGa != null) {
         this.zzbGa.cancel(false);
      }

      this.zzbFY.shutdown();
      this.mClosed = true;
   }

   public final synchronized void zza(zzdi var1) {
      this.zzBG();
      this.zzbFV = var1;
   }

   public final synchronized void zza(long var1, String var3) {
      String var4 = this.zzbDw;
      zzdj.v((new StringBuilder(55 + String.valueOf(var4).length())).append("loadAfterDelay: containerId=").append(var4).append(" delay=").append(var1).toString());
      this.zzBG();
      if (this.zzbFV == null) {
         throw new IllegalStateException("callback must be set before loadAfterDelay() is called.");
      } else {
         if (this.zzbGa != null) {
            this.zzbGa.cancel(false);
         }

         ScheduledExecutorService var10001 = this.zzbFY;
         zzes var7;
         (var7 = this.zzbFZ.zza(this.zzbFW)).zza(this.zzbFV);
         var7.zzfb(this.zzbDU);
         var7.zzfr(var3);
         this.zzbGa = var10001.schedule(var7, var1, TimeUnit.MILLISECONDS);
      }
   }

   public final synchronized void zzfb(String var1) {
      this.zzBG();
      this.zzbDU = var1;
   }

   private final synchronized void zzBG() {
      if (this.mClosed) {
         throw new IllegalStateException("called method after closed");
      }
   }

   // $FF: synthetic method
   static Context zza(zzet var0) {
      return var0.mContext;
   }

   // $FF: synthetic method
   static String zzb(zzet var0) {
      return var0.zzbDw;
   }
}
