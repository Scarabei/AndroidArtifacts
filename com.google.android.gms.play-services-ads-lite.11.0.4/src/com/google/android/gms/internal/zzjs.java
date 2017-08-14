package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzjs extends zzee implements zzjr {
   public zzjs() {
      this.attachInterface(this, "com.google.android.gms.ads.internal.client.IAdLoader");
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else {
         String var5;
         switch(var1) {
         case 1:
            zzir var7 = (zzir)zzef.zza(var2, zzir.CREATOR);
            this.zzc(var7);
            var3.writeNoException();
            break;
         case 2:
            var5 = this.getMediationAdapterClassName();
            var3.writeNoException();
            var3.writeString(var5);
            break;
         case 3:
            boolean var6 = this.isLoading();
            var3.writeNoException();
            zzef.zza(var3, var6);
            break;
         case 4:
            var5 = this.zzaI();
            var3.writeNoException();
            var3.writeString(var5);
            break;
         default:
            return false;
         }

         return true;
      }
   }
}
