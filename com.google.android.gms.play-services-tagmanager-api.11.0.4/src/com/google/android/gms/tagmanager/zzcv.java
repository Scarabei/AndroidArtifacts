package com.google.android.gms.tagmanager;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzcvg;
import com.google.android.gms.internal.zzcvh;
import com.google.android.gms.internal.zzed;
import com.google.android.gms.internal.zzef;

public final class zzcv extends zzed implements zzct {
   zzcv(IBinder var1) {
      super(var1, "com.google.android.gms.tagmanager.ITagManagerServiceProvider");
   }

   public final zzcvg getService(IObjectWrapper var1, zzcn var2, zzce var3) throws RemoteException {
      Parcel var4;
      zzef.zza(var4 = this.zzZ(), var1);
      zzef.zza(var4, var2);
      zzef.zza(var4, var3);
      Parcel var5;
      zzcvg var6 = zzcvh.zzak((var5 = this.zza(1, var4)).readStrongBinder());
      var5.recycle();
      return var6;
   }
}
