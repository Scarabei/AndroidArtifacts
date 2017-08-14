package com.google.android.gms.tagmanager;

import android.content.Intent;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.IObjectWrapper.zza;
import com.google.android.gms.internal.zzee;
import com.google.android.gms.internal.zzef;

public abstract class zzcr extends zzee implements zzcq {
   public zzcr() {
      this.attachInterface(this, "com.google.android.gms.tagmanager.ITagManagerApi");
   }

   public static zzcq asInterface(IBinder var0) {
      if (var0 == null) {
         return null;
      } else {
         IInterface var1;
         return (zzcq)((var1 = var0.queryLocalInterface("com.google.android.gms.tagmanager.ITagManagerApi")) instanceof zzcq ? (zzcq)var1 : new zzcs(var0));
      }
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else {
         Intent var5;
         IObjectWrapper var6;
         IBinder var10;
         IInterface var11;
         switch(var1) {
         case 1:
            IObjectWrapper var12 = zza.zzM(var2.readStrongBinder());
            Object var13 = (var10 = var2.readStrongBinder()) == null ? null : ((var11 = var10.queryLocalInterface("com.google.android.gms.tagmanager.IMeasurementProxy")) instanceof zzcn ? (zzcn)var11 : new zzcp(var10));
            Object var14 = (var10 = var2.readStrongBinder()) == null ? null : ((var11 = var10.queryLocalInterface("com.google.android.gms.tagmanager.ICustomEvaluatorProxy")) instanceof zzce ? (zzce)var11 : new zzcg(var10));
            this.initialize(var12, (zzcn)var13, (zzce)var14);
            break;
         case 2:
            var5 = (Intent)zzef.zza(var2, Intent.CREATOR);
            var6 = zza.zzM(var2.readStrongBinder());
            this.preview(var5, var6);
            break;
         case 3:
            var5 = (Intent)zzef.zza(var2, Intent.CREATOR);
            var6 = zza.zzM(var2.readStrongBinder());
            IObjectWrapper var7 = zza.zzM(var2.readStrongBinder());
            Object var8 = (var10 = var2.readStrongBinder()) == null ? null : ((var11 = var10.queryLocalInterface("com.google.android.gms.tagmanager.IMeasurementProxy")) instanceof zzcn ? (zzcn)var11 : new zzcp(var10));
            Object var9 = (var10 = var2.readStrongBinder()) == null ? null : ((var11 = var10.queryLocalInterface("com.google.android.gms.tagmanager.ICustomEvaluatorProxy")) instanceof zzce ? (zzce)var11 : new zzcg(var10));
            this.previewIntent(var5, var6, var7, (zzcn)var8, (zzce)var9);
            break;
         default:
            return false;
         }

         var3.writeNoException();
         return true;
      }
   }
}
