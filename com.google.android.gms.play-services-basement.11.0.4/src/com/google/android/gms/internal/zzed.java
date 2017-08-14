package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public class zzed implements IInterface {
   private final IBinder zzrD;
   private final String zzrE;

   protected zzed(IBinder var1, String var2) {
      this.zzrD = var1;
      this.zzrE = var2;
   }

   public IBinder asBinder() {
      return this.zzrD;
   }

   protected final Parcel zzZ() {
      Parcel var1;
      (var1 = Parcel.obtain()).writeInterfaceToken(this.zzrE);
      return var1;
   }

   protected final Parcel zza(int var1, Parcel var2) throws RemoteException {
      Parcel var3 = Parcel.obtain();

      try {
         this.zzrD.transact(var1, var2, var3, 0);
         var3.readException();
      } catch (RuntimeException var8) {
         var3.recycle();
         throw var8;
      } finally {
         var2.recycle();
      }

      return var3;
   }

   protected final void zzb(int var1, Parcel var2) throws RemoteException {
      Parcel var3 = Parcel.obtain();

      try {
         this.zzrD.transact(var1, var2, var3, 0);
         var3.readException();
      } finally {
         var2.recycle();
         var3.recycle();
      }

   }

   protected final void zzc(int var1, Parcel var2) throws RemoteException {
      try {
         this.zzrD.transact(var1, var2, (Parcel)null, 1);
      } finally {
         var2.recycle();
      }

   }
}
