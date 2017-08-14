package com.google.android.gms.auth.api.signin.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.zzee;
import com.google.android.gms.internal.zzef;

public abstract class zzs extends zzee implements zzr {
   public zzs() {
      this.attachInterface(this, "com.google.android.gms.auth.api.signin.internal.ISignInCallbacks");
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else {
         Status var5;
         switch(var1) {
         case 101:
            GoogleSignInAccount var7 = (GoogleSignInAccount)zzef.zza(var2, GoogleSignInAccount.CREATOR);
            Status var6 = (Status)zzef.zza(var2, Status.CREATOR);
            this.zza(var7, var6);
            break;
         case 102:
            var5 = (Status)zzef.zza(var2, Status.CREATOR);
            this.zzh(var5);
            break;
         case 103:
            var5 = (Status)zzef.zza(var2, Status.CREATOR);
            this.zzi(var5);
            break;
         default:
            return false;
         }

         var3.writeNoException();
         return true;
      }
   }
}
