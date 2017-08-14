package com.google.android.gms.auth.account;

import android.accounts.Account;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzee;
import com.google.android.gms.internal.zzef;

public abstract class zzb extends zzee implements zza {
   public zzb() {
      this.attachInterface(this, "com.google.android.gms.auth.account.IWorkAccountCallback");
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else {
         switch(var1) {
         case 1:
            Account var6 = (Account)zzef.zza(var2, Account.CREATOR);
            this.zzd(var6);
            break;
         case 2:
            boolean var5 = zzef.zza(var2);
            this.zzN(var5);
            break;
         default:
            return false;
         }

         return true;
      }
   }
}
