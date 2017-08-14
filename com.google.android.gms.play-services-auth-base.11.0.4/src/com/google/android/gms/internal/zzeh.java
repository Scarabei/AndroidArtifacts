package com.google.android.gms.internal;

import android.accounts.Account;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.auth.AccountChangeEventsRequest;
import com.google.android.gms.auth.AccountChangeEventsResponse;

public abstract class zzeh extends zzee implements zzeg {
   public static zzeg zza(IBinder var0) {
      if (var0 == null) {
         return null;
      } else {
         IInterface var1;
         return (zzeg)((var1 = var0.queryLocalInterface("com.google.android.auth.IAuthManagerService")) instanceof zzeg ? (zzeg)var1 : new zzei(var0));
      }
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else {
         Account var5;
         Bundle var6;
         Bundle var7;
         Bundle var8;
         String var11;
         String var12;
         switch(var1) {
         case 1:
            var11 = var2.readString();
            var12 = var2.readString();
            var7 = (Bundle)zzef.zza(var2, Bundle.CREATOR);
            var8 = this.zza(var11, var12, var7);
            var3.writeNoException();
            zzef.zzb(var3, var8);
            break;
         case 2:
            var11 = var2.readString();
            var6 = (Bundle)zzef.zza(var2, Bundle.CREATOR);
            var7 = this.zza(var11, var6);
            var3.writeNoException();
            zzef.zzb(var3, var7);
            break;
         case 3:
            AccountChangeEventsRequest var10 = (AccountChangeEventsRequest)zzef.zza(var2, AccountChangeEventsRequest.CREATOR);
            AccountChangeEventsResponse var13 = this.zza(var10);
            var3.writeNoException();
            zzef.zzb(var3, var13);
            break;
         case 4:
         default:
            return false;
         case 5:
            var5 = (Account)zzef.zza(var2, Account.CREATOR);
            var12 = var2.readString();
            var7 = (Bundle)zzef.zza(var2, Bundle.CREATOR);
            var8 = this.zza(var5, var12, var7);
            var3.writeNoException();
            zzef.zzb(var3, var8);
            break;
         case 6:
            Bundle var9 = (Bundle)zzef.zza(var2, Bundle.CREATOR);
            var6 = this.zza(var9);
            var3.writeNoException();
            zzef.zzb(var3, var6);
            break;
         case 7:
            var5 = (Account)zzef.zza(var2, Account.CREATOR);
            var6 = this.zza(var5);
            var3.writeNoException();
            zzef.zzb(var3, var6);
         }

         return true;
      }
   }
}
