package com.google.android.gms.cast.framework;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzee;
import com.google.android.gms.internal.zzef;

public abstract class zzz extends zzee implements zzy {
   public zzz() {
      this.attachInterface(this, "com.google.android.gms.cast.framework.ISessionManagerListener");
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else {
         IObjectWrapper var5;
         int var6;
         String var8;
         switch(var1) {
         case 1:
            var5 = this.zznn();
            var3.writeNoException();
            zzef.zza(var3, var5);
            break;
         case 2:
            var5 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzM(var2.readStrongBinder());
            this.zzz(var5);
            var3.writeNoException();
            break;
         case 3:
            var5 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzM(var2.readStrongBinder());
            var8 = var2.readString();
            this.zzc(var5, var8);
            var3.writeNoException();
            break;
         case 4:
            var5 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzM(var2.readStrongBinder());
            var6 = var2.readInt();
            this.zze(var5, var6);
            var3.writeNoException();
            break;
         case 5:
            var5 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzM(var2.readStrongBinder());
            this.zzA(var5);
            var3.writeNoException();
            break;
         case 6:
            var5 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzM(var2.readStrongBinder());
            var6 = var2.readInt();
            this.zzf(var5, var6);
            var3.writeNoException();
            break;
         case 7:
            var5 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzM(var2.readStrongBinder());
            var8 = var2.readString();
            this.zzd(var5, var8);
            var3.writeNoException();
            break;
         case 8:
            var5 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzM(var2.readStrongBinder());
            boolean var7 = zzef.zza(var2);
            this.zza(var5, var7);
            var3.writeNoException();
            break;
         case 9:
            var5 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzM(var2.readStrongBinder());
            var6 = var2.readInt();
            this.zzg(var5, var6);
            var3.writeNoException();
            break;
         case 10:
            var5 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzM(var2.readStrongBinder());
            var6 = var2.readInt();
            this.zzh(var5, var6);
            var3.writeNoException();
            break;
         case 11:
            var3.writeNoException();
            var3.writeInt(11020208);
            break;
         default:
            return false;
         }

         return true;
      }
   }
}
