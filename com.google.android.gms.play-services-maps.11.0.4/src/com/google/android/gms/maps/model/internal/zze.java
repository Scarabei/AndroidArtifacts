package com.google.android.gms.maps.model.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzee;
import com.google.android.gms.internal.zzef;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PatternItem;
import java.util.ArrayList;
import java.util.List;

public abstract class zze extends zzee implements zzd {
   public static zzd zzab(IBinder var0) {
      if (var0 == null) {
         return null;
      } else {
         IInterface var1;
         return (zzd)((var1 = var0.queryLocalInterface("com.google.android.gms.maps.model.internal.ICircleDelegate")) instanceof zzd ? (zzd)var1 : new zzf(var0));
      }
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else {
         IObjectWrapper var5;
         boolean var11;
         int var12;
         float var14;
         double var15;
         LatLng var16;
         switch(var1) {
         case 1:
            this.remove();
            var3.writeNoException();
            break;
         case 2:
            String var17 = this.getId();
            var3.writeNoException();
            var3.writeString(var17);
            break;
         case 3:
            var16 = (LatLng)zzef.zza(var2, LatLng.CREATOR);
            this.setCenter(var16);
            var3.writeNoException();
            break;
         case 4:
            var16 = this.getCenter();
            var3.writeNoException();
            zzef.zzb(var3, var16);
            break;
         case 5:
            var15 = var2.readDouble();
            this.setRadius(var15);
            var3.writeNoException();
            break;
         case 6:
            var15 = this.getRadius();
            var3.writeNoException();
            var3.writeDouble(var15);
            break;
         case 7:
            var14 = var2.readFloat();
            this.setStrokeWidth(var14);
            var3.writeNoException();
            break;
         case 8:
            var14 = this.getStrokeWidth();
            var3.writeNoException();
            var3.writeFloat(var14);
            break;
         case 9:
            var12 = var2.readInt();
            this.setStrokeColor(var12);
            var3.writeNoException();
            break;
         case 10:
            var12 = this.getStrokeColor();
            var3.writeNoException();
            var3.writeInt(var12);
            break;
         case 11:
            var12 = var2.readInt();
            this.setFillColor(var12);
            var3.writeNoException();
            break;
         case 12:
            var12 = this.getFillColor();
            var3.writeNoException();
            var3.writeInt(var12);
            break;
         case 13:
            var14 = var2.readFloat();
            this.setZIndex(var14);
            var3.writeNoException();
            break;
         case 14:
            var14 = this.getZIndex();
            var3.writeNoException();
            var3.writeFloat(var14);
            break;
         case 15:
            var11 = zzef.zza(var2);
            this.setVisible(var11);
            var3.writeNoException();
            break;
         case 16:
            var11 = this.isVisible();
            var3.writeNoException();
            zzef.zza(var3, var11);
            break;
         case 17:
            IBinder var7;
            IInterface var8;
            Object var13 = (var7 = var2.readStrongBinder()) == null ? null : ((var8 = var7.queryLocalInterface("com.google.android.gms.maps.model.internal.ICircleDelegate")) instanceof zzd ? (zzd)var8 : new zzf(var7));
            boolean var6 = this.zzb((zzd)var13);
            var3.writeNoException();
            zzef.zza(var3, var6);
            break;
         case 18:
            var12 = this.hashCodeRemote();
            var3.writeNoException();
            var3.writeInt(var12);
            break;
         case 19:
            var11 = zzef.zza(var2);
            this.setClickable(var11);
            var3.writeNoException();
            break;
         case 20:
            var11 = this.isClickable();
            var3.writeNoException();
            zzef.zza(var3, var11);
            break;
         case 21:
            ArrayList var10 = var2.createTypedArrayList(PatternItem.CREATOR);
            this.setStrokePattern(var10);
            var3.writeNoException();
            break;
         case 22:
            List var9 = this.getStrokePattern();
            var3.writeNoException();
            var3.writeTypedList(var9);
            break;
         case 23:
            var5 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzM(var2.readStrongBinder());
            this.setTag(var5);
            var3.writeNoException();
            break;
         case 24:
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
