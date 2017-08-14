package com.google.android.gms.maps.model.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzee;
import com.google.android.gms.internal.zzef;

public abstract class zzx extends zzee implements zzw {
   public static zzw zzai(IBinder var0) {
      if (var0 == null) {
         return null;
      } else {
         IInterface var1;
         return (zzw)((var1 = var0.queryLocalInterface("com.google.android.gms.maps.model.internal.ITileOverlayDelegate")) instanceof zzw ? (zzw)var1 : new zzy(var0));
      }
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else {
         float var5;
         boolean var9;
         switch(var1) {
         case 1:
            this.remove();
            var3.writeNoException();
            break;
         case 2:
            this.clearTileCache();
            var3.writeNoException();
            break;
         case 3:
            String var12 = this.getId();
            var3.writeNoException();
            var3.writeString(var12);
            break;
         case 4:
            var5 = var2.readFloat();
            this.setZIndex(var5);
            var3.writeNoException();
            break;
         case 5:
            var5 = this.getZIndex();
            var3.writeNoException();
            var3.writeFloat(var5);
            break;
         case 6:
            var9 = zzef.zza(var2);
            this.setVisible(var9);
            var3.writeNoException();
            break;
         case 7:
            var9 = this.isVisible();
            var3.writeNoException();
            zzef.zza(var3, var9);
            break;
         case 8:
            IBinder var7;
            IInterface var8;
            Object var11 = (var7 = var2.readStrongBinder()) == null ? null : ((var8 = var7.queryLocalInterface("com.google.android.gms.maps.model.internal.ITileOverlayDelegate")) instanceof zzw ? (zzw)var8 : new zzy(var7));
            boolean var6 = this.zza((zzw)var11);
            var3.writeNoException();
            zzef.zza(var3, var6);
            break;
         case 9:
            int var10 = this.hashCodeRemote();
            var3.writeNoException();
            var3.writeInt(var10);
            break;
         case 10:
            var9 = zzef.zza(var2);
            this.setFadeIn(var9);
            var3.writeNoException();
            break;
         case 11:
            var9 = this.getFadeIn();
            var3.writeNoException();
            zzef.zza(var3, var9);
            break;
         case 12:
            var5 = var2.readFloat();
            this.setTransparency(var5);
            var3.writeNoException();
            break;
         case 13:
            var5 = this.getTransparency();
            var3.writeNoException();
            var3.writeFloat(var5);
            break;
         default:
            return false;
         }

         return true;
      }
   }
}
