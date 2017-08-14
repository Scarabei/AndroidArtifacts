package com.google.android.gms.internal;

import android.accounts.Account;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.auth.AccountChangeEventsRequest;
import com.google.android.gms.auth.AccountChangeEventsResponse;

public final class zzei extends zzed implements zzeg {
   zzei(IBinder var1) {
      super(var1, "com.google.android.auth.IAuthManagerService");
   }

   public final Bundle zza(String var1, String var2, Bundle var3) throws RemoteException {
      Parcel var4;
      (var4 = this.zzZ()).writeString(var1);
      var4.writeString(var2);
      zzef.zza(var4, var3);
      Parcel var5;
      Bundle var6 = (Bundle)zzef.zza(var5 = this.zza(1, var4), Bundle.CREATOR);
      var5.recycle();
      return var6;
   }

   public final Bundle zza(String var1, Bundle var2) throws RemoteException {
      Parcel var3;
      (var3 = this.zzZ()).writeString(var1);
      zzef.zza(var3, var2);
      Parcel var4;
      Bundle var5 = (Bundle)zzef.zza(var4 = this.zza(2, var3), Bundle.CREATOR);
      var4.recycle();
      return var5;
   }

   public final AccountChangeEventsResponse zza(AccountChangeEventsRequest var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      Parcel var3;
      AccountChangeEventsResponse var4 = (AccountChangeEventsResponse)zzef.zza(var3 = this.zza(3, var2), AccountChangeEventsResponse.CREATOR);
      var3.recycle();
      return var4;
   }

   public final Bundle zza(Account var1, String var2, Bundle var3) throws RemoteException {
      Parcel var4;
      zzef.zza(var4 = this.zzZ(), var1);
      var4.writeString(var2);
      zzef.zza(var4, var3);
      Parcel var5;
      Bundle var6 = (Bundle)zzef.zza(var5 = this.zza(5, var4), Bundle.CREATOR);
      var5.recycle();
      return var6;
   }

   public final Bundle zza(Bundle var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      Parcel var3;
      Bundle var4 = (Bundle)zzef.zza(var3 = this.zza(6, var2), Bundle.CREATOR);
      var3.recycle();
      return var4;
   }

   public final Bundle zza(Account var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      Parcel var3;
      Bundle var4 = (Bundle)zzef.zza(var3 = this.zza(7, var2), Bundle.CREATOR);
      var3.recycle();
      return var4;
   }
}
