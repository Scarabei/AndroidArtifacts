package com.google.android.gms.cast.framework;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.internal.zzee;
import com.google.android.gms.internal.zzef;

public interface zzm extends IInterface {
   void onConnected(Bundle var1) throws RemoteException;

   void onConnectionSuspended(int var1) throws RemoteException;

   void onConnectionFailed(ConnectionResult var1) throws RemoteException;

   void zza(ApplicationMetadata var1, String var2, String var3, boolean var4) throws RemoteException;

   void zzZ(int var1) throws RemoteException;

   void zzb(boolean var1, int var2) throws RemoteException;

   public abstract static class zza extends zzee implements zzm {
      public static zzm zzB(IBinder var0) {
         if (var0 == null) {
            return null;
         } else {
            IInterface var1;
            return (zzm)((var1 = var0.queryLocalInterface("com.google.android.gms.cast.framework.ICastSession")) instanceof zzm ? (zzm)var1 : new zzn(var0));
         }
      }

      public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
         if (this.zza(var1, var2, var3, var4)) {
            return true;
         } else {
            int var9;
            switch(var1) {
            case 1:
               Bundle var13 = (Bundle)zzef.zza(var2, Bundle.CREATOR);
               this.onConnected(var13);
               break;
            case 2:
               var9 = var2.readInt();
               this.onConnectionSuspended(var9);
               break;
            case 3:
               ConnectionResult var11 = (ConnectionResult)zzef.zza(var2, ConnectionResult.CREATOR);
               this.onConnectionFailed(var11);
               break;
            case 4:
               ApplicationMetadata var10 = (ApplicationMetadata)zzef.zza(var2, ApplicationMetadata.CREATOR);
               String var12 = var2.readString();
               String var7 = var2.readString();
               boolean var8 = zzef.zza(var2);
               this.zza(var10, var12, var7, var8);
               break;
            case 5:
               var9 = var2.readInt();
               this.zzZ(var9);
               break;
            case 6:
               boolean var5 = zzef.zza(var2);
               int var6 = var2.readInt();
               this.zzb(var5, var6);
               break;
            default:
               return false;
            }

            var3.writeNoException();
            return true;
         }
      }
   }
}
