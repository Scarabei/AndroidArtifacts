package com.google.android.gms.tagmanager;

import android.content.Intent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzed;
import com.google.android.gms.internal.zzef;

public final class zzcs extends zzed implements zzcq {
   zzcs(IBinder var1) {
      super(var1, "com.google.android.gms.tagmanager.ITagManagerApi");
   }

   public final void initialize(IObjectWrapper var1, zzcn var2, zzce var3) throws RemoteException {
      Parcel var4;
      zzef.zza(var4 = this.zzZ(), var1);
      zzef.zza(var4, var2);
      zzef.zza(var4, var3);
      this.zzb(1, var4);
   }

   public final void preview(Intent var1, IObjectWrapper var2) throws RemoteException {
      Parcel var3;
      zzef.zza(var3 = this.zzZ(), var1);
      zzef.zza(var3, var2);
      this.zzb(2, var3);
   }

   public final void previewIntent(Intent var1, IObjectWrapper var2, IObjectWrapper var3, zzcn var4, zzce var5) throws RemoteException {
      Parcel var6;
      zzef.zza(var6 = this.zzZ(), var1);
      zzef.zza(var6, var2);
      zzef.zza(var6, var3);
      zzef.zza(var6, var4);
      zzef.zza(var6, var5);
      this.zzb(3, var6);
   }
}
