package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzcvh extends zzee implements zzcvg {
   public zzcvh() {
      this.attachInterface(this, "com.google.android.gms.tagmanager.internal.ITagManagerService");
   }

   public static zzcvg zzak(IBinder var0) {
      if (var0 == null) {
         return null;
      } else {
         IInterface var1;
         return (zzcvg)((var1 = var0.queryLocalInterface("com.google.android.gms.tagmanager.internal.ITagManagerService")) instanceof zzcvg ? (zzcvg)var1 : new zzcvi(var0));
      }
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else {
         String var5;
         String var7;
         String var13;
         switch(var1) {
         case 1:
            var5 = var2.readString();
            var13 = var2.readString();
            var7 = var2.readString();
            this.zzn(var5, var13, var7);
            break;
         case 2:
            var5 = var2.readString();
            var13 = var2.readString();
            var7 = var2.readString();
            IBinder var11;
            IInterface var12;
            Object var14 = (var11 = var2.readStrongBinder()) == null ? null : ((var12 = var11.queryLocalInterface("com.google.android.gms.tagmanager.internal.ITagManagerLoadContainerCallback")) instanceof zzcvd ? (zzcvd)var12 : new zzcvf(var11));
            this.zza(var5, var13, var7, (zzcvd)var14);
            break;
         case 3:
            this.zzCr();
            break;
         case 101:
            var5 = var2.readString();
            Bundle var6 = (Bundle)zzef.zza(var2, Bundle.CREATOR);
            var7 = var2.readString();
            long var8 = var2.readLong();
            boolean var10 = zzef.zza(var2);
            this.zza(var5, var6, var7, var8, var10);
            break;
         case 102:
            this.dispatch();
            break;
         default:
            return false;
         }

         var3.writeNoException();
         return true;
      }
   }
}
