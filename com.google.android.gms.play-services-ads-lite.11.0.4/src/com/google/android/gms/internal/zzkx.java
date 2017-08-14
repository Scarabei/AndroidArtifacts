package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzkx extends zzed implements zzkv {
   zzkx(IBinder var1) {
      super(var1, "com.google.android.gms.ads.internal.client.IVideoLifecycleCallbacks");
   }

   public final void onVideoStart() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzb(1, var1);
   }

   public final void onVideoPlay() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzb(2, var1);
   }

   public final void onVideoPause() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzb(3, var1);
   }

   public final void onVideoEnd() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzb(4, var1);
   }

   public final void onVideoMute(boolean var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(5, var2);
   }
}
