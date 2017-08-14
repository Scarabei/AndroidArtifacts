package com.google.android.gms.cast.framework.media;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.common.images.WebImage;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzed;
import com.google.android.gms.internal.zzef;

public final class zzc extends zzed implements zzb {
   zzc(IBinder var1) {
      super(var1, "com.google.android.gms.cast.framework.media.IImagePicker");
   }

   public final int zznm() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      int var3 = (var2 = this.zza(3, var1)).readInt();
      var2.recycle();
      return var3;
   }

   public final WebImage onPickImage(MediaMetadata var1, int var2) throws RemoteException {
      Parcel var3;
      zzef.zza(var3 = this.zzZ(), var1);
      var3.writeInt(var2);
      Parcel var4;
      WebImage var5 = (WebImage)zzef.zza(var4 = this.zza(1, var3), WebImage.CREATOR);
      var4.recycle();
      return var5;
   }

   public final IObjectWrapper zznT() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      IObjectWrapper var3 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzM((var2 = this.zza(2, var1)).readStrongBinder());
      var2.recycle();
      return var3;
   }

   public final WebImage zza(MediaMetadata var1, ImageHints var2) throws RemoteException {
      Parcel var3;
      zzef.zza(var3 = this.zzZ(), var1);
      zzef.zza(var3, var2);
      Parcel var4;
      WebImage var5 = (WebImage)zzef.zza(var4 = this.zza(4, var3), WebImage.CREATOR);
      var4.recycle();
      return var5;
   }
}
