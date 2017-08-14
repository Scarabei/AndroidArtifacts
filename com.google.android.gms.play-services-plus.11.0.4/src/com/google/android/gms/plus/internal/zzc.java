package com.google.android.gms.plus.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.internal.zzbgt;
import com.google.android.gms.internal.zzcri;
import com.google.android.gms.internal.zzee;
import com.google.android.gms.internal.zzef;

public abstract class zzc extends zzee implements zzb {
   public zzc() {
      this.attachInterface(this, "com.google.android.gms.plus.internal.IPlusCallbacks");
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else {
         switch(var1) {
         case 1:
            var2.readInt();
            zzef.zza(var2, Bundle.CREATOR);
            zzef.zza(var2, Bundle.CREATOR);
            break;
         case 2:
            var2.readInt();
            zzef.zza(var2, Bundle.CREATOR);
            zzef.zza(var2, ParcelFileDescriptor.CREATOR);
            break;
         case 3:
            var2.readString();
            break;
         case 4:
            DataHolder var7 = (DataHolder)zzef.zza(var2, DataHolder.CREATOR);
            String var8 = var2.readString();
            this.zza(var7, var8);
            break;
         case 5:
            var2.readInt();
            zzef.zza(var2, Bundle.CREATOR);
            zzef.zza(var2, zzbgt.CREATOR);
            break;
         case 6:
            zzef.zza(var2, DataHolder.CREATOR);
            var2.readString();
            var2.readString();
            break;
         case 7:
            int var5 = var2.readInt();
            Bundle var6 = (Bundle)zzef.zza(var2, Bundle.CREATOR);
            this.zzf(var5, var6);
            break;
         case 8:
            var2.readString();
            break;
         case 9:
            var2.readInt();
            zzef.zza(var2, zzcri.CREATOR);
            break;
         case 10:
            zzef.zza(var2, Status.CREATOR);
            break;
         default:
            return false;
         }

         var3.writeNoException();
         return true;
      }
   }
}
