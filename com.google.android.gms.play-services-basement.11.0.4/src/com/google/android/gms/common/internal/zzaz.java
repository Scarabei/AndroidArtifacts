package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzee;
import com.google.android.gms.internal.zzef;

public abstract class zzaz extends zzee implements zzay {
   public static zzay zzJ(IBinder var0) {
      if (var0 == null) {
         return null;
      } else {
         IInterface var1;
         return (zzay)((var1 = var0.queryLocalInterface("com.google.android.gms.common.internal.IGoogleCertificatesApi")) instanceof zzay ? (zzay)var1 : new zzba(var0));
      }
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else {
         IObjectWrapper var6;
         boolean var7;
         String var8;
         IObjectWrapper var9;
         switch(var1) {
         case 1:
            var9 = this.zzrF();
            var3.writeNoException();
            zzef.zza(var3, (IInterface)var9);
            break;
         case 2:
            var9 = this.zzrG();
            var3.writeNoException();
            zzef.zza(var3, (IInterface)var9);
            break;
         case 3:
            var8 = var2.readString();
            var6 = IObjectWrapper.zza.zzM(var2.readStrongBinder());
            var7 = this.zze(var8, var6);
            var3.writeNoException();
            zzef.zza(var3, var7);
            break;
         case 4:
            var8 = var2.readString();
            var6 = IObjectWrapper.zza.zzM(var2.readStrongBinder());
            var7 = this.zzf(var8, var6);
            var3.writeNoException();
            zzef.zza(var3, var7);
            break;
         case 5:
            com.google.android.gms.common.zzm var5 = (com.google.android.gms.common.zzm)zzef.zza(var2, com.google.android.gms.common.zzm.CREATOR);
            var6 = IObjectWrapper.zza.zzM(var2.readStrongBinder());
            var7 = this.zza(var5, var6);
            var3.writeNoException();
            zzef.zza(var3, var7);
            break;
         default:
            return false;
         }

         return true;
      }
   }
}
