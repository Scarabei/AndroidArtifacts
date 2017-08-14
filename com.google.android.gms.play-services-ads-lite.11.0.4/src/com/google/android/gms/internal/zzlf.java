package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.dynamic.zzn;

@zzzn
public final class zzlf {
   private static zzlf zzBn;
   private static final Object zzuF = new Object();
   private zzkn zzBo;
   private RewardedVideoAd zzBp;

   public static zzlf zzdD() {
      Object var0 = zzuF;
      synchronized(zzuF) {
         if (zzBn == null) {
            zzBn = new zzlf();
         }

         return zzBn;
      }
   }

   public final void zza(Context var1, String var2, zzlh var3) {
      Object var4 = zzuF;
      synchronized(zzuF) {
         if (this.zzBo == null) {
            if (var1 == null) {
               throw new IllegalArgumentException("Context cannot be null.");
            } else {
               try {
                  zziz var7 = zzji.zzdt();
                  this.zzBo = (zzkn)zziz.zza(var1, false, new zzje(var7, var1));
                  this.zzBo.initialize();
                  if (var2 != null) {
                     this.zzBo.zzc(var2, zzn.zzw(new zzlg(this, var1)));
                  }
               } catch (RemoteException var9) {
                  zzajc.zzc("MobileAdsSettingManager initialization failed", var9);
               }

            }
         }
      }
   }

   public final RewardedVideoAd getRewardedVideoAdInstance(Context var1) {
      Object var2 = zzuF;
      synchronized(zzuF) {
         if (this.zzBp != null) {
            return this.zzBp;
         } else {
            zzup var3 = new zzup();
            zziz var6 = zzji.zzdt();
            zzacy var4 = (zzacy)zziz.zza(var1, false, new zzjg(var6, var1, var3));
            this.zzBp = new zzadl(var1, var4);
            return this.zzBp;
         }
      }
   }

   public final void setAppVolume(float var1) {
      zzbo.zzb(0.0F <= var1 && var1 <= 1.0F, "The app volume must be a value between 0 and 1 inclusive.");
      zzbo.zza(this.zzBo != null, "MobileAds.initialize() must be called prior to setting the app volume.");

      try {
         this.zzBo.setAppVolume(var1);
      } catch (RemoteException var3) {
         zzajc.zzb("Unable to set app volume.", var3);
      }
   }

   public final void setAppMuted(boolean var1) {
      zzbo.zza(this.zzBo != null, "MobileAds.initialize() must be called prior to setting the app volume.");

      try {
         this.zzBo.setAppMuted(var1);
      } catch (RemoteException var3) {
         zzajc.zzb("Unable to set app mute state.", var3);
      }
   }

   public final void openDebugMenu(Context var1, String var2) {
      zzbo.zza(this.zzBo != null, "MobileAds.initialize() must be called prior to opening debug menu.");

      try {
         this.zzBo.zzb(zzn.zzw(var1), var2);
      } catch (RemoteException var4) {
         zzajc.zzb("Unable to open debug menu.", var4);
      }
   }
}
