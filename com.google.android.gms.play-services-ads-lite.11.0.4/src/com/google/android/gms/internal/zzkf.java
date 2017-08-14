package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzkf extends zzee implements zzke {
   public zzkf() {
      this.attachInterface(this, "com.google.android.gms.ads.internal.client.IAppEventListener");
   }

   public static zzke zzf(IBinder var0) {
      if (var0 == null) {
         return null;
      } else {
         IInterface var1;
         return (zzke)((var1 = var0.queryLocalInterface("com.google.android.gms.ads.internal.client.IAppEventListener")) instanceof zzke ? (zzke)var1 : new zzkg(var0));
      }
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else if (var1 == 1) {
         String var5 = var2.readString();
         String var6 = var2.readString();
         this.onAppEvent(var5, var6);
         var3.writeNoException();
         return true;
      } else {
         return false;
      }
   }
}
