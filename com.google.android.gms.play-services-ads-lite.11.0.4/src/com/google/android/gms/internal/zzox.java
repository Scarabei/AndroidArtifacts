package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.IObjectWrapper.zza;

public abstract class zzox extends zzee implements zzow {
   public zzox() {
      this.attachInterface(this, "com.google.android.gms.ads.internal.formats.client.INativeAdViewDelegate");
   }

   public static zzow zzj(IBinder var0) {
      if (var0 == null) {
         return null;
      } else {
         IInterface var1;
         return (zzow)((var1 = var0.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.INativeAdViewDelegate")) instanceof zzow ? (zzow)var1 : new zzoy(var0));
      }
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else {
         IObjectWrapper var5;
         String var7;
         IObjectWrapper var8;
         switch(var1) {
         case 1:
            var7 = var2.readString();
            var8 = zza.zzM(var2.readStrongBinder());
            this.zzd(var7, var8);
            var3.writeNoException();
            break;
         case 2:
            var7 = var2.readString();
            var8 = this.zzL(var7);
            var3.writeNoException();
            zzef.zza(var3, var8);
            break;
         case 3:
            var5 = zza.zzM(var2.readStrongBinder());
            this.zze(var5);
            var3.writeNoException();
            break;
         case 4:
            this.destroy();
            var3.writeNoException();
            break;
         case 5:
            var5 = zza.zzM(var2.readStrongBinder());
            int var6 = var2.readInt();
            this.zzb(var5, var6);
            var3.writeNoException();
            break;
         default:
            return false;
         }

         return true;
      }
   }
}
