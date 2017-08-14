package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.wallet.FullWalletRequest;
import com.google.android.gms.wallet.IsReadyToPayRequest;
import com.google.android.gms.wallet.MaskedWalletRequest;
import com.google.android.gms.wallet.NotifyTransactionStatusRequest;

public final class gg extends zzed implements gf {
   gg(IBinder var1) {
      super(var1, "com.google.android.gms.wallet.internal.IOwService");
   }

   public final void zza(MaskedWalletRequest var1, Bundle var2, gj var3) throws RemoteException {
      Parcel var4;
      zzef.zza(var4 = this.zzZ(), var1);
      zzef.zza(var4, var2);
      zzef.zza(var4, var3);
      this.zzc(1, var4);
   }

   public final void zza(FullWalletRequest var1, Bundle var2, gj var3) throws RemoteException {
      Parcel var4;
      zzef.zza(var4 = this.zzZ(), var1);
      zzef.zza(var4, var2);
      zzef.zza(var4, var3);
      this.zzc(2, var4);
   }

   public final void zza(String var1, String var2, Bundle var3, gj var4) throws RemoteException {
      Parcel var5;
      (var5 = this.zzZ()).writeString(var1);
      var5.writeString(var2);
      zzef.zza(var5, var3);
      zzef.zza(var5, var4);
      this.zzc(3, var5);
   }

   public final void zza(NotifyTransactionStatusRequest var1, Bundle var2) throws RemoteException {
      Parcel var3;
      zzef.zza(var3 = this.zzZ(), var1);
      zzef.zza(var3, var2);
      this.zzc(4, var3);
   }

   public final void zza(Bundle var1, gj var2) throws RemoteException {
      Parcel var3;
      zzef.zza(var3 = this.zzZ(), var1);
      zzef.zza(var3, var2);
      this.zzc(5, var3);
   }

   public final void zzb(Bundle var1, gj var2) throws RemoteException {
      Parcel var3;
      zzef.zza(var3 = this.zzZ(), var1);
      zzef.zza(var3, var2);
      this.zzc(11, var3);
   }

   public final void zza(IsReadyToPayRequest var1, Bundle var2, gj var3) throws RemoteException {
      Parcel var4;
      zzef.zza(var4 = this.zzZ(), var1);
      zzef.zza(var4, var2);
      zzef.zza(var4, var3);
      this.zzc(14, var4);
   }
}
