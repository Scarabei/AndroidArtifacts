package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.service.FitnessSensorServiceRequest;

public abstract class zzbzt extends zzee implements zzbzs {
   public zzbzt() {
      this.attachInterface(this, "com.google.android.gms.fitness.internal.service.IFitnessSensorService");
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else {
         zzbxg var6;
         switch(var1) {
         case 1:
            zzbzo var8 = (zzbzo)zzef.zza(var2, zzbzo.CREATOR);
            zzbvy var9 = zzbvz.zzQ(var2.readStrongBinder());
            this.zza(var8, var9);
            break;
         case 2:
            FitnessSensorServiceRequest var7 = (FitnessSensorServiceRequest)zzef.zza(var2, FitnessSensorServiceRequest.CREATOR);
            var6 = zzbxh.zzW(var2.readStrongBinder());
            this.zza(var7, var6);
            break;
         case 3:
            zzbzq var5 = (zzbzq)zzef.zza(var2, zzbzq.CREATOR);
            var6 = zzbxh.zzW(var2.readStrongBinder());
            this.zza(var5, var6);
            break;
         default:
            return false;
         }

         return true;
      }
   }
}
