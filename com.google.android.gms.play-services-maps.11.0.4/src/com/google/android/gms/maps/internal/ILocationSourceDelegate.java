package com.google.android.gms.maps.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzee;

public interface ILocationSourceDelegate extends IInterface {
   void activate(zzah var1) throws RemoteException;

   void deactivate() throws RemoteException;

   public abstract static class zza extends zzee implements ILocationSourceDelegate {
      public zza() {
         this.attachInterface(this, "com.google.android.gms.maps.internal.ILocationSourceDelegate");
      }

      public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
         if (this.zza(var1, var2, var3, var4)) {
            return true;
         } else {
            switch(var1) {
            case 1:
               IBinder var6;
               IInterface var7;
               Object var5 = (var6 = var2.readStrongBinder()) == null ? null : ((var7 = var6.queryLocalInterface("com.google.android.gms.maps.internal.IOnLocationChangeListener")) instanceof zzah ? (zzah)var7 : new zzai(var6));
               this.activate((zzah)var5);
               break;
            case 2:
               this.deactivate();
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
