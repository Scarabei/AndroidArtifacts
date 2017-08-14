package com.google.android.gms.tagmanager;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.IObjectWrapper.zza;
import com.google.android.gms.internal.zzcvg;
import com.google.android.gms.internal.zzee;
import com.google.android.gms.internal.zzef;

public abstract class zzcu extends zzee implements zzct {
   public zzcu() {
      this.attachInterface(this, "com.google.android.gms.tagmanager.ITagManagerServiceProvider");
   }

   public static zzct asInterface(IBinder var0) {
      if (var0 == null) {
         return null;
      } else {
         IInterface var1;
         return (zzct)((var1 = var0.queryLocalInterface("com.google.android.gms.tagmanager.ITagManagerServiceProvider")) instanceof zzct ? (zzct)var1 : new zzcv(var0));
      }
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else if (var1 == 1) {
         IObjectWrapper var5 = zza.zzM(var2.readStrongBinder());
         IBinder var9;
         IInterface var10;
         Object var6 = (var9 = var2.readStrongBinder()) == null ? null : ((var10 = var9.queryLocalInterface("com.google.android.gms.tagmanager.IMeasurementProxy")) instanceof zzcn ? (zzcn)var10 : new zzcp(var9));
         Object var7 = (var9 = var2.readStrongBinder()) == null ? null : ((var10 = var9.queryLocalInterface("com.google.android.gms.tagmanager.ICustomEvaluatorProxy")) instanceof zzce ? (zzce)var10 : new zzcg(var9));
         zzcvg var8 = this.getService(var5, (zzcn)var6, (zzce)var7);
         var3.writeNoException();
         zzef.zza(var3, var8);
         return true;
      } else {
         return false;
      }
   }
}
