package com.google.android.gms.cast.framework;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzee;
import com.google.android.gms.internal.zzef;

public interface zzu extends IInterface {
   IObjectWrapper zznw() throws RemoteException;

   String getCategory() throws RemoteException;

   String getSessionId() throws RemoteException;

   String zznx() throws RemoteException;

   boolean isConnected() throws RemoteException;

   boolean isConnecting() throws RemoteException;

   boolean isDisconnecting() throws RemoteException;

   boolean isDisconnected() throws RemoteException;

   boolean isResuming() throws RemoteException;

   boolean isSuspended() throws RemoteException;

   void notifySessionStarted(String var1) throws RemoteException;

   void notifyFailedToStartSession(int var1) throws RemoteException;

   void notifySessionEnded(int var1) throws RemoteException;

   void notifySessionResumed(boolean var1) throws RemoteException;

   void notifyFailedToResumeSession(int var1) throws RemoteException;

   void notifySessionSuspended(int var1) throws RemoteException;

   public abstract static class zza extends zzee implements zzu {
      public static zzu zzD(IBinder var0) {
         if (var0 == null) {
            return null;
         } else {
            IInterface var1;
            return (zzu)((var1 = var0.queryLocalInterface("com.google.android.gms.cast.framework.ISession")) instanceof zzu ? (zzu)var1 : new zzv(var0));
         }
      }

      public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
         if (this.zza(var1, var2, var3, var4)) {
            return true;
         } else {
            int var5;
            boolean var6;
            String var7;
            switch(var1) {
            case 1:
               IObjectWrapper var8 = this.zznw();
               var3.writeNoException();
               zzef.zza(var3, var8);
               break;
            case 2:
               var7 = this.getCategory();
               var3.writeNoException();
               var3.writeString(var7);
               break;
            case 3:
               var7 = this.getSessionId();
               var3.writeNoException();
               var3.writeString(var7);
               break;
            case 4:
               var7 = this.zznx();
               var3.writeNoException();
               var3.writeString(var7);
               break;
            case 5:
               var6 = this.isConnected();
               var3.writeNoException();
               zzef.zza(var3, var6);
               break;
            case 6:
               var6 = this.isConnecting();
               var3.writeNoException();
               zzef.zza(var3, var6);
               break;
            case 7:
               var6 = this.isDisconnecting();
               var3.writeNoException();
               zzef.zza(var3, var6);
               break;
            case 8:
               var6 = this.isDisconnected();
               var3.writeNoException();
               zzef.zza(var3, var6);
               break;
            case 9:
               var6 = this.isResuming();
               var3.writeNoException();
               zzef.zza(var3, var6);
               break;
            case 10:
               var6 = this.isSuspended();
               var3.writeNoException();
               zzef.zza(var3, var6);
               break;
            case 11:
               var7 = var2.readString();
               this.notifySessionStarted(var7);
               var3.writeNoException();
               break;
            case 12:
               var5 = var2.readInt();
               this.notifyFailedToStartSession(var5);
               var3.writeNoException();
               break;
            case 13:
               var5 = var2.readInt();
               this.notifySessionEnded(var5);
               var3.writeNoException();
               break;
            case 14:
               var6 = zzef.zza(var2);
               this.notifySessionResumed(var6);
               var3.writeNoException();
               break;
            case 15:
               var5 = var2.readInt();
               this.notifyFailedToResumeSession(var5);
               var3.writeNoException();
               break;
            case 16:
               var5 = var2.readInt();
               this.notifySessionSuspended(var5);
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
