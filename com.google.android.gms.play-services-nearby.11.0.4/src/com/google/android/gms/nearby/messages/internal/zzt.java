package com.google.android.gms.nearby.messages.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzed;
import com.google.android.gms.internal.zzef;

public final class zzt extends zzed implements zzs {
   zzt(IBinder var1) {
      super(var1, "com.google.android.gms.nearby.messages.internal.INearbyMessagesService");
   }

   public final void zza(zzax var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzc(1, var2);
   }

   public final void zza(zzbc var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzc(2, var2);
   }

   public final void zza(SubscribeRequest var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzc(3, var2);
   }

   public final void zza(zzbe var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzc(4, var2);
   }

   public final void zza(zzh var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzc(7, var2);
   }

   public final void zza(zzaz var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzc(8, var2);
   }

   public final void zza(zzj var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzc(9, var2);
   }
}
