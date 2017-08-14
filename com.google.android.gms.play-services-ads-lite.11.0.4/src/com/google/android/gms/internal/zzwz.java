package com.google.android.gms.internal;

import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public final class zzwz extends zzed implements zzwx {
   zzwz(IBinder var1) {
      super(var1, "com.google.android.gms.ads.internal.overlay.client.IAdOverlay");
   }

   public final void onCreate(Bundle var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(1, var2);
   }

   public final void onRestart() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzb(2, var1);
   }

   public final void onStart() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzb(3, var1);
   }

   public final void onResume() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzb(4, var1);
   }

   public final void onPause() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzb(5, var1);
   }

   public final void onSaveInstanceState(Bundle var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      Parcel var3;
      if ((var3 = this.zza(6, var2)).readInt() != 0) {
         var1.readFromParcel(var3);
      }

      var3.recycle();
   }

   public final void onStop() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzb(7, var1);
   }

   public final void onDestroy() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzb(8, var1);
   }

   public final void zzaa() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzb(9, var1);
   }

   public final void onBackPressed() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzb(10, var1);
   }

   public final boolean zzfK() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      boolean var3 = zzef.zza(var2 = this.zza(11, var1));
      var2.recycle();
      return var3;
   }

   public final void onActivityResult(int var1, int var2, Intent var3) throws RemoteException {
      Parcel var4;
      (var4 = this.zzZ()).writeInt(var1);
      var4.writeInt(var2);
      zzef.zza(var4, var3);
      this.zzb(12, var4);
   }

   public final void zzo(IObjectWrapper var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(13, var2);
   }
}
