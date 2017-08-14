package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.IObjectWrapper.zza;

public abstract class zzez extends zzee implements zzey {
   public zzez() {
      this.attachInterface(this, "com.google.android.gms.ads.adshield.internal.IAdShieldClient");
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else {
         IObjectWrapper var5;
         String var7;
         String var8;
         boolean var9;
         IObjectWrapper var10;
         String var11;
         IObjectWrapper var13;
         switch(var1) {
         case 1:
            var8 = this.zzaf();
            var3.writeNoException();
            var3.writeString(var8);
            break;
         case 2:
            var8 = var2.readString();
            var11 = var2.readString();
            this.zzb(var8, var11);
            var3.writeNoException();
            break;
         case 3:
            var5 = zza.zzM(var2.readStrongBinder());
            var9 = this.zza(var5);
            var3.writeNoException();
            zzef.zza(var3, var9);
            break;
         case 4:
            var5 = zza.zzM(var2.readStrongBinder());
            var9 = this.zzb(var5);
            var3.writeNoException();
            zzef.zza(var3, var9);
            break;
         case 5:
            var8 = var2.readString();
            this.zzk(var8);
            var3.writeNoException();
            break;
         case 6:
            var5 = zza.zzM(var2.readStrongBinder());
            var10 = zza.zzM(var2.readStrongBinder());
            var13 = this.zza(var5, var10);
            var3.writeNoException();
            zzef.zza(var3, var13);
            break;
         case 7:
            var5 = zza.zzM(var2.readStrongBinder());
            var11 = this.zzc(var5);
            var3.writeNoException();
            var3.writeString(var11);
            break;
         case 8:
            var5 = zza.zzM(var2.readStrongBinder());
            var11 = var2.readString();
            var7 = this.zza(var5, var11);
            var3.writeNoException();
            var3.writeString(var7);
            break;
         case 9:
            var5 = zza.zzM(var2.readStrongBinder());
            this.zzd(var5);
            var3.writeNoException();
            break;
         case 10:
            var5 = zza.zzM(var2.readStrongBinder());
            var10 = zza.zzM(var2.readStrongBinder());
            var13 = this.zzb(var5, var10);
            var3.writeNoException();
            zzef.zza(var3, var13);
            break;
         case 11:
            var8 = var2.readString();
            var9 = zzef.zza(var2);
            boolean var12 = this.zzb(var8, var9);
            var3.writeNoException();
            zzef.zza(var3, var12);
            break;
         case 12:
            var5 = zza.zzM(var2.readStrongBinder());
            byte[] var6 = var2.createByteArray();
            var7 = this.zza(var5, var6);
            var3.writeNoException();
            var3.writeString(var7);
            break;
         default:
            return false;
         }

         return true;
      }
   }
}
