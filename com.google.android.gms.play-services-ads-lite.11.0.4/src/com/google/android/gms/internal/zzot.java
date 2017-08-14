package com.google.android.gms.internal;

import android.net.Uri;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public abstract class zzot extends zzee implements zzos {
   public zzot() {
      this.attachInterface(this, "com.google.android.gms.ads.internal.formats.client.INativeAdImage");
   }

   public static zzos zzi(IBinder var0) {
      if (var0 == null) {
         return null;
      } else {
         IInterface var1;
         return (zzos)((var1 = var0.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.INativeAdImage")) instanceof zzos ? (zzos)var1 : new zzou(var0));
      }
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else {
         switch(var1) {
         case 1:
            IObjectWrapper var8 = this.zzeg();
            var3.writeNoException();
            zzef.zza(var3, var8);
            break;
         case 2:
            Uri var7 = this.getUri();
            var3.writeNoException();
            zzef.zzb(var3, var7);
            break;
         case 3:
            double var5 = this.getScale();
            var3.writeNoException();
            var3.writeDouble(var5);
            break;
         default:
            return false;
         }

         return true;
      }
   }
}
