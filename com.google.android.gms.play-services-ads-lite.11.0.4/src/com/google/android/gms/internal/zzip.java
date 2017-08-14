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
public final class zzip extends zzp {
   public zzip() {
      super("com.google.android.gms.ads.AdLoaderBuilderCreatorImpl");
   }

   public final zzju zza(Context var1, String var2, zzuq var3) {
      try {
         IObjectWrapper var4 = zzn.zzw(var1);
         IBinder var5;
         if ((var5 = ((zzjx)this.zzaS(var1)).zza(var4, var2, var3, 11020000)) == null) {
            return null;
         }

         IInterface var6;
         if ((var6 = var5.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdLoaderBuilder")) instanceof zzju) {
            return (zzju)var6;
         }

         return new zzjw(var5);
      } catch (RemoteException var7) {
         zzajc.zzc("Could not create remote builder for AdLoader.", var7);
      } catch (zzq var8) {
         zzajc.zzc("Could not create remote builder for AdLoader.", var8);
      }

      return null;
   }

   // $FF: synthetic method
   protected final Object zzb(IBinder var1) {
      if (var1 == null) {
         return null;
      } else {
         IInterface var3;
         return (var3 = var1.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdLoaderBuilderCreator")) instanceof zzjx ? (zzjx)var3 : new zzjy(var1);
      }
   }
}
