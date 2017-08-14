package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.common.api.Status;

public abstract class zzars extends zzee implements zzarr {
   public zzars() {
      this.attachInterface(this, "com.google.android.gms.auth.api.credentials.internal.ICredentialsCallbacks");
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else {
         Status var5;
         switch(var1) {
         case 1:
            var5 = (Status)zzef.zza(var2, Status.CREATOR);
            Credential var7 = (Credential)zzef.zza(var2, Credential.CREATOR);
            this.zza(var5, var7);
            break;
         case 2:
            var5 = (Status)zzef.zza(var2, Status.CREATOR);
            this.zzd(var5);
            break;
         case 3:
            var5 = (Status)zzef.zza(var2, Status.CREATOR);
            String var6 = var2.readString();
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
