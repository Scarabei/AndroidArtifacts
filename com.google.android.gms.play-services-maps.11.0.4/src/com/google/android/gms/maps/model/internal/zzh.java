package com.google.android.gms.maps.model.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzee;
import com.google.android.gms.internal.zzef;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

public abstract class zzh extends zzee implements zzg {
   public static zzg zzac(IBinder var0) {
      if (var0 == null) {
         return null;
      } else {
         IInterface var1;
         return (zzg)((var1 = var0.queryLocalInterface("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate")) instanceof zzg ? (zzg)var1 : new zzi(var0));
      }
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else {
         IObjectWrapper var5;
         boolean var9;
         float var13;
         LatLngBounds var14;
         LatLng var15;
         switch(var1) {
         case 1:
            this.remove();
            var3.writeNoException();
            break;
         case 2:
            String var16 = this.getId();
            var3.writeNoException();
            var3.writeString(var16);
            break;
         case 3:
            var15 = (LatLng)zzef.zza(var2, LatLng.CREATOR);
            this.setPosition(var15);
            var3.writeNoException();
            break;
         case 4:
            var15 = this.getPosition();
            var3.writeNoException();
            zzef.zzb(var3, var15);
            break;
         case 5:
            var13 = var2.readFloat();
            this.setDimensions(var13);
            var3.writeNoException();
            break;
         case 6:
            var13 = var2.readFloat();
            float var10 = var2.readFloat();
            this.zzf(var13, var10);
            var3.writeNoException();
            break;
         case 7:
            var13 = this.getWidth();
            var3.writeNoException();
            var3.writeFloat(var13);
            break;
         case 8:
            var13 = this.getHeight();
            var3.writeNoException();
            var3.writeFloat(var13);
            break;
         case 9:
            var14 = (LatLngBounds)zzef.zza(var2, LatLngBounds.CREATOR);
            this.setPositionFromBounds(var14);
            var3.writeNoException();
            break;
         case 10:
            var14 = this.getBounds();
            var3.writeNoException();
            zzef.zzb(var3, var14);
            break;
         case 11:
            var13 = var2.readFloat();
            this.setBearing(var13);
            var3.writeNoException();
            break;
         case 12:
            var13 = this.getBearing();
            var3.writeNoException();
            var3.writeFloat(var13);
            break;
         case 13:
            var13 = var2.readFloat();
            this.setZIndex(var13);
            var3.writeNoException();
            break;
         case 14:
            var13 = this.getZIndex();
            var3.writeNoException();
            var3.writeFloat(var13);
            break;
         case 15:
            var9 = zzef.zza(var2);
            this.setVisible(var9);
            var3.writeNoException();
            break;
         case 16:
            var9 = this.isVisible();
            var3.writeNoException();
            zzef.zza(var3, var9);
            break;
         case 17:
            var13 = var2.readFloat();
            this.setTransparency(var13);
            var3.writeNoException();
            break;
         case 18:
            var13 = this.getTransparency();
            var3.writeNoException();
            var3.writeFloat(var13);
            break;
         case 19:
            IBinder var7;
            IInterface var8;
            Object var12 = (var7 = var2.readStrongBinder()) == null ? null : ((var8 = var7.queryLocalInterface("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate")) instanceof zzg ? (zzg)var8 : new zzi(var7));
            boolean var6 = this.zzb((zzg)var12);
            var3.writeNoException();
            zzef.zza(var3, var6);
            break;
         case 20:
            int var11 = this.hashCodeRemote();
            var3.writeNoException();
            var3.writeInt(var11);
            break;
         case 21:
            var5 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzM(var2.readStrongBinder());
            this.zzJ(var5);
            var3.writeNoException();
            break;
         case 22:
            var9 = zzef.zza(var2);
            this.setClickable(var9);
            var3.writeNoException();
            break;
         case 23:
            var9 = this.isClickable();
            var3.writeNoException();
            zzef.zza(var3, var9);
            break;
         case 24:
            var5 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzM(var2.readStrongBinder());
            this.setTag(var5);
            var3.writeNoException();
            break;
         case 25:
            var5 = this.getTag();
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
