package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.dynamic.zzp;
import com.google.android.gms.dynamic.zzq;

@zzzn
public final class zzadh extends zzp {
   public zzadh() {
      super("com.google.android.gms.ads.reward.RewardedVideoAdCreatorImpl");
   }

   public final zzacy zza(Context var1, zzuq var2) {
      try {
         IObjectWrapper var3 = zzn.zzw(var1);
         IBinder var4;
         if ((var4 = ((zzadb)this.zzaS(var1)).zza(var3, var2, 11020000)) == null) {
            return null;
         } else {
            IInterface var5;
            return (zzacy)((var5 = var4.queryLocalInterface("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAd")) instanceof zzacy ? (zzacy)var5 : new zzada(var4));
         }
      } catch (zzq | RemoteException var6) {
         zzajc.zzc("Could not get remote RewardedVideoAd.", var6);
         return null;
      }
   }

   // $FF: synthetic method
   protected final Object zzb(IBinder var1) {
      if (var1 == null) {
         return null;
      } else {
         IInterface var3;
         return (var3 = var1.queryLocalInterface("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAdCreator")) instanceof zzadb ? (zzadb)var3 : new zzadc(var1);
      }
   }
}
