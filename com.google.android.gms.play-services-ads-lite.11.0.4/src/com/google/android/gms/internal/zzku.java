package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzku extends zzed implements zzks {
   zzku(IBinder var1) {
      super(var1, "com.google.android.gms.ads.internal.client.IVideoController");
   }

   public final void play() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzb(1, var1);
   }

   public final void pause() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzb(2, var1);
   }

   public final void mute(boolean var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(3, var2);
   }

   public final boolean isMuted() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      boolean var3 = zzef.zza(var2 = this.zza(4, var1));
      var2.recycle();
      return var3;
   }

   public final int getPlaybackState() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      int var3 = (var2 = this.zza(5, var1)).readInt();
      var2.recycle();
      return var3;
   }

   public final float zzdv() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      float var3 = (var2 = this.zza(6, var1)).readFloat();
      var2.recycle();
      return var3;
   }

   public final float zzdw() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      float var3 = (var2 = this.zza(7, var1)).readFloat();
      var2.recycle();
      return var3;
   }

   public final void zza(zzkv var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(8, var2);
   }

   public final float getAspectRatio() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      float var3 = (var2 = this.zza(9, var1)).readFloat();
      var2.recycle();
      return var3;
   }

   public final boolean isCustomControlsEnabled() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      boolean var3 = zzef.zza(var2 = this.zza(10, var1));
      var2.recycle();
      return var3;
   }
}
