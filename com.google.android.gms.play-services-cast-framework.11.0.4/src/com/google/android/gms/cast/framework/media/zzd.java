package com.google.android.gms.cast.framework.media;

import android.content.Intent;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzee;
import com.google.android.gms.internal.zzef;

public interface zzd extends IInterface {
   void onCreate() throws RemoteException;

   int onStartCommand(Intent var1, int var2, int var3) throws RemoteException;

   IBinder onBind(Intent var1) throws RemoteException;

   void onDestroy() throws RemoteException;

   public abstract static class zza extends zzee implements zzd {
      public static zzd zzE(IBinder var0) {
         if (var0 == null) {
            return null;
         } else {
            IInterface var1;
            return (zzd)((var1 = var0.queryLocalInterface("com.google.android.gms.cast.framework.media.IMediaNotificationService")) instanceof zzd ? (zzd)var1 : new zze(var0));
         }
      }

      public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
         if (this.zza(var1, var2, var3, var4)) {
            return true;
         } else {
            Intent var5;
            switch(var1) {
            case 1:
               this.onCreate();
               var3.writeNoException();
               break;
            case 2:
               var5 = (Intent)zzef.zza(var2, Intent.CREATOR);
               int var9 = var2.readInt();
               int var7 = var2.readInt();
               int var8 = this.onStartCommand(var5, var9, var7);
               var3.writeNoException();
               var3.writeInt(var8);
               break;
            case 3:
               var5 = (Intent)zzef.zza(var2, Intent.CREATOR);
               IBinder var6 = this.onBind(var5);
               var3.writeNoException();
               var3.writeStrongBinder(var6);
               break;
            case 4:
               this.onDestroy();
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
