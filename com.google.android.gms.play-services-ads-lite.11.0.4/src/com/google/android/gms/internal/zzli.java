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
public final class zzli extends zzp {
   public zzli() {
      super("com.google.android.gms.ads.MobileAdsSettingManagerCreatorImpl");
   }

   public final zzkn zzh(Context var1) {
      try {
         IObjectWrapper var2 = zzn.zzw(var1);
         IBinder var3;
         if ((var3 = ((zzkq)this.zzaS(var1)).zza(var2, 11020000)) == null) {
            return null;
         } else {
            IInterface var4;
            return (zzkn)((var4 = var3.queryLocalInterface("com.google.android.gms.ads.internal.client.IMobileAdsSettingManager")) instanceof zzkn ? (zzkn)var4 : new zzkp(var3));
         }
      } catch (RemoteException var5) {
         zzajc.zzc("Could not get remote MobileAdsSettingManager.", var5);
         return null;
      } catch (zzq var6) {
         zzajc.zzc("Could not get remote MobileAdsSettingManager.", var6);
         return null;
      }
   }

   // $FF: synthetic method
   protected final Object zzb(IBinder var1) {
      if (var1 == null) {
         return null;
      } else {
         IInterface var3;
         return (var3 = var1.queryLocalInterface("com.google.android.gms.ads.internal.client.IMobileAdsSettingManagerCreator")) instanceof zzkq ? (zzkq)var3 : new zzkr(var1);
      }
   }
}
