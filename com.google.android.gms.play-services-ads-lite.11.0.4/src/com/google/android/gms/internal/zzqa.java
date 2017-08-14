package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.IObjectWrapper.zza;

public abstract class zzqa extends zzee implements zzpz {
   public zzqa() {
      this.attachInterface(this, "com.google.android.gms.ads.internal.formats.client.IOnPublisherAdViewLoadedListener");
   }

   public static zzpz zzp(IBinder var0) {
      if (var0 == null) {
         return null;
      } else {
         IInterface var1;
         return (zzpz)((var1 = var0.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.IOnPublisherAdViewLoadedListener")) instanceof zzpz ? (zzpz)var1 : new zzqb(var0));
      }
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else if (var1 == 1) {
         zzjz var5 = zzka.zze(var2.readStrongBinder());
         IObjectWrapper var6 = zza.zzM(var2.readStrongBinder());
         this.zza(var5, var6);
         var3.writeNoException();
         return true;
      } else {
         return false;
      }
   }
}
