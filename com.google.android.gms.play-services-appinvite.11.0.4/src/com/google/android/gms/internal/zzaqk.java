package com.google.android.gms.internal;

import android.content.Intent;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;

public abstract class zzaqk extends zzee implements zzaqj {
   public zzaqk() {
      this.attachInterface(this, "com.google.android.gms.appinvite.internal.IAppInviteCallbacks");
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else {
         Status var5;
         switch(var1) {
         case 1:
            var5 = (Status)zzef.zza(var2, Status.CREATOR);
            this.zzc(var5);
            break;
         case 2:
            var5 = (Status)zzef.zza(var2, Status.CREATOR);
            Intent var6 = (Intent)zzef.zza(var2, Intent.CREATOR);
            this.zza(var5, var6);
            break;
         default:
            return false;
         }

         var3.writeNoException();
         return true;
      }
   }
}
