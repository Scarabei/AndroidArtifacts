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
public final class zziq extends zzp {
   public zziq() {
      super("com.google.android.gms.ads.AdManagerCreatorImpl");
   }

   public final zzjz zza(Context var1, zziv var2, String var3, zzuq var4, int var5) {
      try {
         IObjectWrapper var6 = zzn.zzw(var1);
         IBinder var7;
         if ((var7 = ((zzkc)this.zzaS(var1)).zza(var6, var2, var3, var4, 11020000, var5)) == null) {
            return null;
         } else {
            IInterface var8;
            return (zzjz)((var8 = var7.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdManager")) instanceof zzjz ? (zzjz)var8 : new zzkb(var7));
         }
      } catch (zzq | RemoteException var9) {
         zzajc.zza("Could not create remote AdManager.", var9);
         return null;
      }
   }

   // $FF: synthetic method
   protected final Object zzb(IBinder var1) {
      if (var1 == null) {
         return null;
      } else {
         IInterface var3;
         return (var3 = var1.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdManagerCreator")) instanceof zzkc ? (zzkc)var3 : new zzkd(var1);
      }
   }
}
