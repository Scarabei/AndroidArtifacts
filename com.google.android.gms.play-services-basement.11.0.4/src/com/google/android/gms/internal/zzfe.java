package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzfe extends zzee implements zzfd {
   public static zzfd zzc(IBinder var0) {
      if (var0 == null) {
         return null;
      } else {
         IInterface var1;
         return (zzfd)((var1 = var0.queryLocalInterface("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService")) instanceof zzfd ? (zzfd)var1 : new zzff(var0));
      }
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else {
         String var5;
         boolean var6;
         switch(var1) {
         case 1:
            var5 = this.getId();
            var3.writeNoException();
            var3.writeString(var5);
            break;
         case 2:
            boolean var7 = zzef.zza(var2);
            var6 = this.zzb(var7);
            var3.writeNoException();
            zzef.zza(var3, var6);
            break;
         case 3:
            var5 = var2.readString();
            String var8 = this.zzq(var5);
            var3.writeNoException();
            var3.writeString(var8);
            break;
         case 4:
            var5 = var2.readString();
            var6 = zzef.zza(var2);
            this.zzc(var5, var6);
            var3.writeNoException();
            break;
         default:
            return false;
         }

         return true;
      }
   }
}
