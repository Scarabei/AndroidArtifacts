package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;

@zzzn
public final class zzadm extends zzafp implements zzads, zzadv {
   private final zzafg zzQQ;
   private final Context mContext;
   private final zzadz zzWy;
   private final zzadv zzWz;
   private final Object mLock;
   private final String zzMs;
   private final String zzWA;
   private final zzua zzWB;
   private final long zzWC;
   private int zzWD = 0;
   private int mErrorCode = 3;
   private zzadp zzWE;

   public zzadm(Context var1, String var2, String var3, zzua var4, zzafg var5, zzadz var6, zzadv var7, long var8) {
      this.mContext = var1;
      this.zzMs = var2;
      this.zzWA = var3;
      this.zzWB = var4;
      this.zzQQ = var5;
      this.zzWy = var6;
      this.mLock = new Object();
      this.zzWz = var7;
      this.zzWC = var8;
   }

   public final void zzbd() {
      if (this.zzWy != null && this.zzWy.zzgX() != null && this.zzWy.zzgW() != null) {
         zzadu var1;
         (var1 = this.zzWy.zzgX()).zza((zzadv)null);
         var1.zza((zzads)this);
         zzir var2 = this.zzQQ.zzUj.zzSz;
         zzut var3 = this.zzWy.zzgW();

         try {
            if (var3.isInitialized()) {
               zzaiy.zzaaH.post(new zzadn(this, var2, var3));
            } else {
               zzaiy.zzaaH.post(new zzado(this, var3, var2, var1));
            }
         } catch (RemoteException var10) {
            zzafr.zzc("Fail to check if adapter is initialized.", var10);
            this.zza(this.zzMs, 0);
         }

         long var6 = com.google.android.gms.ads.internal.zzbs.zzbF().elapsedRealtime();
         zzadm var5 = this;

         while(true) {
            Object var8 = var5.mLock;
            synchronized(var5.mLock) {
               if (var5.zzWD != 0) {
                  var5.zzWE = (new zzadr()).zzg(com.google.android.gms.ads.internal.zzbs.zzbF().elapsedRealtime() - var6).zzw(1 == var5.zzWD ? 6 : var5.mErrorCode).zzax(var5.zzMs).zzay(var5.zzWB.zzLK).zzgU();
                  break;
               }

               if (!var5.zzf(var6)) {
                  var5.zzWE = (new zzadr()).zzw(var5.mErrorCode).zzg(com.google.android.gms.ads.internal.zzbs.zzbF().elapsedRealtime() - var6).zzax(var5.zzMs).zzay(var5.zzWB.zzLK).zzgU();
                  break;
               }
            }
         }

         var1.zza((zzadv)null);
         var1.zza((zzads)null);
         if (this.zzWD == 1) {
            this.zzWz.zzaw(this.zzMs);
         } else {
            this.zzWz.zza(this.zzMs, this.mErrorCode);
         }
      }
   }

   public final zzadp zzgR() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         return this.zzWE;
      }
   }

   public final zzua zzgS() {
      return this.zzWB;
   }

   private final boolean zzf(long var1) {
      long var3;
      if ((var3 = this.zzWC - (com.google.android.gms.ads.internal.zzbs.zzbF().elapsedRealtime() - var1)) <= 0L) {
         this.mErrorCode = 4;
         return false;
      } else {
         try {
            this.mLock.wait(var3);
            return true;
         } catch (InterruptedException var5) {
            Thread.currentThread().interrupt();
            this.mErrorCode = 5;
            return false;
         }
      }
   }

   public final void zzaw(String var1) {
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         this.zzWD = 1;
         this.mLock.notify();
      }
   }

   public final void zza(String var1, int var2) {
      Object var3 = this.mLock;
      synchronized(this.mLock) {
         this.zzWD = 2;
         this.mErrorCode = var2;
         this.mLock.notify();
      }
   }

   public final void onStop() {
   }

   public final void zzgT() {
      zzir var1 = this.zzQQ.zzUj.zzSz;
      zzut var2 = this.zzWy.zzgW();
      this.zza(var1, var2);
   }

   public final void zzv(int var1) {
      this.zza(this.zzMs, 0);
   }

   private final void zza(zzir var1, zzut var2) {
      this.zzWy.zzgX().zza((zzadv)this);

      try {
         if ("com.google.ads.mediation.admob.AdMobAdapter".equals(this.zzMs)) {
            var2.zza(var1, this.zzWA, this.zzWB.zzLH);
         } else {
            var2.zzc(var1, this.zzWA);
         }
      } catch (RemoteException var4) {
         zzafr.zzc("Fail to load ad from adapter.", var4);
         this.zza(this.zzMs, 0);
      }
   }

   // $FF: synthetic method
   static void zza(zzadm var0, zzir var1, zzut var2) {
      var0.zza(var1, var2);
   }

   // $FF: synthetic method
   static Context zza(zzadm var0) {
      return var0.mContext;
   }

   // $FF: synthetic method
   static String zzb(zzadm var0) {
      return var0.zzWA;
   }

   // $FF: synthetic method
   static String zzc(zzadm var0) {
      return var0.zzMs;
   }
}
