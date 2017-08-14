package com.google.android.gms.internal;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public final class zzavk extends zzed implements zzavj {
   zzavk(IBinder var1) {
      super(var1, "com.google.android.gms.cast.framework.media.internal.IFetchBitmapTask");
   }

   public final Bitmap zzn(Uri var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      Parcel var3;
      Bitmap var4 = (Bitmap)zzef.zza(var3 = this.zza(1, var2), Bitmap.CREATOR);
      var3.recycle();
      return var4;
   }

   public final void zzB(IObjectWrapper var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(2, var2);
   }
}
