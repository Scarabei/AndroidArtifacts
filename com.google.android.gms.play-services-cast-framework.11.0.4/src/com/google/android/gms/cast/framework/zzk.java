package com.google.android.gms.cast.framework;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzee;
import com.google.android.gms.internal.zzef;

public interface zzk extends IInterface {
   Bundle zznr() throws RemoteException;

   boolean isAppVisible() throws RemoteException;

   void zza(zzf var1) throws RemoteException;

   void zzb(zzf var1) throws RemoteException;

   zzw zzns() throws RemoteException;

   zzq zznt() throws RemoteException;

   void destroy() throws RemoteException;

   void zzx(IObjectWrapper var1) throws RemoteException;

   void zzy(IObjectWrapper var1) throws RemoteException;

   IObjectWrapper zznu() throws RemoteException;

   public abstract static class zza extends zzee implements zzk {
      public static zzk zzA(IBinder var0) {
         if (var0 == null) {
            return null;
         } else {
            IInterface var1;
            return (zzk)((var1 = var0.queryLocalInterface("com.google.android.gms.cast.framework.ICastContext")) instanceof zzk ? (zzk)var1 : new zzl(var0));
         }
      }

      public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
         if (this.zza(var1, var2, var3, var4)) {
            return true;
         } else {
            IObjectWrapper var5;
            IBinder var6;
            IInterface var7;
            Object var10;
            switch(var1) {
            case 1:
               Bundle var12 = this.zznr();
               var3.writeNoException();
               zzef.zzb(var3, var12);
               break;
            case 2:
               boolean var11 = this.isAppVisible();
               var3.writeNoException();
               zzef.zza(var3, var11);
               break;
            case 3:
               var10 = (var6 = var2.readStrongBinder()) == null ? null : ((var7 = var6.queryLocalInterface("com.google.android.gms.cast.framework.IAppVisibilityListener")) instanceof zzf ? (zzf)var7 : new zzh(var6));
               this.zza((zzf)var10);
               var3.writeNoException();
               break;
            case 4:
               var10 = (var6 = var2.readStrongBinder()) == null ? null : ((var7 = var6.queryLocalInterface("com.google.android.gms.cast.framework.IAppVisibilityListener")) instanceof zzf ? (zzf)var7 : new zzh(var6));
               this.zzb((zzf)var10);
               var3.writeNoException();
               break;
            case 5:
               zzw var9 = this.zzns();
               var3.writeNoException();
               zzef.zza(var3, var9);
               break;
            case 6:
               zzq var8 = this.zznt();
               var3.writeNoException();
               zzef.zza(var3, var8);
               break;
            case 7:
               this.destroy();
               var3.writeNoException();
               break;
            case 8:
               var5 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzM(var2.readStrongBinder());
               this.zzx(var5);
               var3.writeNoException();
               break;
            case 9:
               var5 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzM(var2.readStrongBinder());
               this.zzy(var5);
               var3.writeNoException();
               break;
            case 10:
               var5 = this.zznu();
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
