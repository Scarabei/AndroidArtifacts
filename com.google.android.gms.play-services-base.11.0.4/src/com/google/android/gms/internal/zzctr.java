package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Status;

public abstract class zzctr extends zzee implements zzctq {
   public zzctr() {
      this.attachInterface(this, "com.google.android.gms.signin.internal.ISignInCallbacks");
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else {
         switch(var1) {
         case 3:
            zzef.zza(var2, ConnectionResult.CREATOR);
            zzef.zza(var2, zzctn.CREATOR);
            break;
         case 4:
            zzef.zza(var2, Status.CREATOR);
            break;
         case 5:
         default:
            return false;
         case 6:
            zzef.zza(var2, Status.CREATOR);
            break;
         case 7:
            zzef.zza(var2, Status.CREATOR);
            zzef.zza(var2, GoogleSignInAccount.CREATOR);
            break;
         case 8:
            zzctx var5 = (zzctx)zzef.zza(var2, zzctx.CREATOR);
            this.zzb(var5);
         }

         var3.writeNoException();
         return true;
      }
   }
}
