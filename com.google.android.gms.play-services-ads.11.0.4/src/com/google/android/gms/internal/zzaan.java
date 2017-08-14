package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzaan extends zzee implements zzaam {
   public zzaan() {
      this.attachInterface(this, "com.google.android.gms.ads.internal.request.IAdRequestService");
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else {
         Object var6;
         IBinder var7;
         IInterface var8;
         zzaae var9;
         switch(var1) {
         case 1:
            var9 = (zzaae)zzef.zza(var2, zzaae.CREATOR);
            zzaai var10 = this.zzc(var9);
            var3.writeNoException();
            zzef.zzb(var3, var10);
            break;
         case 2:
            var9 = (zzaae)zzef.zza(var2, zzaae.CREATOR);
            var6 = (var7 = var2.readStrongBinder()) == null ? null : ((var8 = var7.queryLocalInterface("com.google.android.gms.ads.internal.request.IAdResponseListener")) instanceof zzaap ? (zzaap)var8 : new zzaar(var7));
            this.zza(var9, (zzaap)var6);
            var3.writeNoException();
            break;
         case 3:
            zzaax var5 = (zzaax)zzef.zza(var2, zzaax.CREATOR);
            var6 = (var7 = var2.readStrongBinder()) == null ? null : ((var8 = var7.queryLocalInterface("com.google.android.gms.ads.internal.request.INonagonResponseListener")) instanceof zzaas ? (zzaas)var8 : new zzaat(var7));
            this.zza(var5, (zzaas)var6);
            var3.writeNoException();
            break;
         default:
            return false;
         }

         return true;
      }
   }
}
