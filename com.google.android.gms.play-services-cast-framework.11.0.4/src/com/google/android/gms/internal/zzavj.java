package com.google.android.gms.internal;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public interface zzavj extends IInterface {
   Bitmap zzn(Uri var1) throws RemoteException;

   void zzB(IObjectWrapper var1) throws RemoteException;

   public abstract static class zza extends zzee implements zzavj {
      public static zzavj zzF(IBinder var0) {
         if (var0 == null) {
            return null;
         } else {
            IInterface var1;
            return (zzavj)((var1 = var0.queryLocalInterface("com.google.android.gms.cast.framework.media.internal.IFetchBitmapTask")) instanceof zzavj ? (zzavj)var1 : new zzavk(var0));
         }
      }

      public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
         if (this.zza(var1, var2, var3, var4)) {
            return true;
         } else {
            switch(var1) {
            case 1:
               Uri var7 = (Uri)zzef.zza(var2, Uri.CREATOR);
               Bitmap var6 = this.zzn(var7);
               var3.writeNoException();
               zzef.zzb(var3, var6);
               break;
            case 2:
               IObjectWrapper var5 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzM(var2.readStrongBinder());
               this.zzB(var5);
               var3.writeNoException();
               break;
            default:
               return false;
            }

            return true;
         }
      }
   }
}
