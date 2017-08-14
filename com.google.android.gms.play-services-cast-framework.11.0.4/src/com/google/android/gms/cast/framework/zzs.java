package com.google.android.gms.cast.framework;

import android.content.Intent;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzee;
import com.google.android.gms.internal.zzef;

public interface zzs extends IInterface {
   void onCreate() throws RemoteException;

   int onStartCommand(Intent var1, int var2, int var3) throws RemoteException;

   IBinder onBind(Intent var1) throws RemoteException;

   void onDestroy() throws RemoteException;

   IObjectWrapper zznv() throws RemoteException;

   public abstract static class zza extends zzee implements zzs {
      public static zzs zzC(IBinder var0) {
         if (var0 == null) {
            return null;
         } else {
            IInterface var1;
            return (zzs)((var1 = var0.queryLocalInterface("com.google.android.gms.cast.framework.IReconnectionService")) instanceof zzs ? (zzs)var1 : new zzt(var0));
         }
      }

      public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
         if (this.zza(var1, var2, var3, var4)) {
            return true;
         } else {
            Intent var9;
            switch(var1) {
            case 1:
               this.onCreate();
               var3.writeNoException();
               break;
            case 2:
               var9 = (Intent)zzef.zza(var2, Intent.CREATOR);
               int var10 = var2.readInt();
               int var7 = var2.readInt();
               int var8 = this.onStartCommand(var9, var10, var7);
               var3.writeNoException();
               var3.writeInt(var8);
               break;
            case 3:
               var9 = (Intent)zzef.zza(var2, Intent.CREATOR);
               IBinder var6 = this.onBind(var9);
               var3.writeNoException();
               var3.writeStrongBinder(var6);
               break;
            case 4:
               this.onDestroy();
               var3.writeNoException();
               break;
            case 5:
               IObjectWrapper var5 = this.zznv();
               var3.writeNoException();
               zzef.zza(var3, var5);
               break;
            default:
               return false;
            }

            return true;
         }
      }
   }
}
