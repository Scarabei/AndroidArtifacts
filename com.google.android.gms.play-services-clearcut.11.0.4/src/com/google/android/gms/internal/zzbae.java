package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;

public abstract class zzbae extends zzee implements zzbad {
   public zzbae() {
      this.attachInterface(this, "com.google.android.gms.clearcut.internal.IClearcutLoggerCallbacks");
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else {
         Status var8;
         long var9;
         switch(var1) {
         case 1:
            var8 = (Status)zzef.zza(var2, Status.CREATOR);
            this.zzl(var8);
            break;
         case 2:
            var8 = (Status)zzef.zza(var2, Status.CREATOR);
            this.zzm(var8);
            break;
         case 3:
            var8 = (Status)zzef.zza(var2, Status.CREATOR);
            var9 = var2.readLong();
            this.zza(var8, var9);
            break;
         case 4:
            var8 = (Status)zzef.zza(var2, Status.CREATOR);
            this.zzn(var8);
            break;
         case 5:
            var8 = (Status)zzef.zza(var2, Status.CREATOR);
            var9 = var2.readLong();
            this.zzb(var8, var9);
            break;
         case 6:
            var8 = (Status)zzef.zza(var2, Status.CREATOR);
            zzazu[] var6 = (zzazu[])var2.createTypedArray(zzazu.CREATOR);
            this.zza(var8, var6);
            break;
         case 7:
            DataHolder var5 = (DataHolder)zzef.zza(var2, DataHolder.CREATOR);
            this.zza(var5);
            break;
         default:
            return false;
         }

         return true;
      }
   }
}
