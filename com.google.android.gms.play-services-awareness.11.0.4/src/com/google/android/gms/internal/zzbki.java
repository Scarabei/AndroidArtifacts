package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;

public abstract class zzbki extends zzee implements zzbkh {
   public zzbki() {
      this.attachInterface(this, "com.google.android.gms.contextmanager.internal.IContextManagerPendingResult");
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else {
         Status var5;
         DataHolder var10;
         switch(var1) {
         case 1:
            var5 = (Status)zzef.zza(var2, Status.CREATOR);
            this.zzd(var5);
            break;
         case 2:
            var5 = (Status)zzef.zza(var2, Status.CREATOR);
            var10 = (DataHolder)zzef.zza(var2, DataHolder.CREATOR);
            DataHolder var7 = (DataHolder)zzef.zza(var2, DataHolder.CREATOR);
            this.zza(var5, var10, var7);
            break;
         case 3:
            var5 = (Status)zzef.zza(var2, Status.CREATOR);
            zzbkl var11 = (zzbkl)zzef.zza(var2, zzbkl.CREATOR);
            this.zza(var5, var11);
            break;
         case 4:
         default:
            return false;
         case 5:
            var5 = (Status)zzef.zza(var2, Status.CREATOR);
            var10 = (DataHolder)zzef.zza(var2, DataHolder.CREATOR);
            this.zza(var5, var10);
            break;
         case 6:
            var5 = (Status)zzef.zza(var2, Status.CREATOR);
            zzati var9 = (zzati)zzef.zza(var2, zzati.CREATOR);
            this.zza(var5, var9);
            break;
         case 7:
            var5 = (Status)zzef.zza(var2, Status.CREATOR);
            zzbjf var8 = (zzbjf)zzef.zza(var2, zzbjf.CREATOR);
            this.zza(var5, var8);
            break;
         case 8:
            var5 = (Status)zzef.zza(var2, Status.CREATOR);
            zzbjd var6 = (zzbjd)zzef.zza(var2, zzbjd.CREATOR);
            this.zza(var5, var6);
         }

         var3.writeNoException();
         return true;
      }
   }
}
