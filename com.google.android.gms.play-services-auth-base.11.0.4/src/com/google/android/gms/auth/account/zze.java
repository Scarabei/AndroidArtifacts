package com.google.android.gms.auth.account;

import android.accounts.Account;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzee;
import com.google.android.gms.internal.zzef;

public abstract class zze extends zzee implements zzd {
   public static zzd zzz(IBinder var0) {
      if (var0 == null) {
         return null;
      } else {
         IInterface var1;
         return (zzd)((var1 = var0.queryLocalInterface("com.google.android.gms.auth.account.IWorkAccountService")) instanceof zzd ? (zzd)var1 : new zzf(var0));
      }
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else {
         Object var5;
         IBinder var7;
         IInterface var8;
         switch(var1) {
         case 1:
            boolean var9 = zzef.zza(var2);
            this.zzO(var9);
            break;
         case 2:
            var5 = (var7 = var2.readStrongBinder()) == null ? null : ((var8 = var7.queryLocalInterface("com.google.android.gms.auth.account.IWorkAccountCallback")) instanceof zza ? (zza)var8 : new zzc(var7));
            String var10 = var2.readString();
            this.zza((zza)var5, var10);
            break;
         case 3:
            var5 = (var7 = var2.readStrongBinder()) == null ? null : ((var8 = var7.queryLocalInterface("com.google.android.gms.auth.account.IWorkAccountCallback")) instanceof zza ? (zza)var8 : new zzc(var7));
            Account var6 = (Account)zzef.zza(var2, Account.CREATOR);
            this.zza((zza)var5, var6);
            break;
         default:
            return false;
         }

         var3.writeNoException();
         return true;
      }
   }
}
