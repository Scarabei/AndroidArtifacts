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

public abstract class zzt extends zzee implements zzs {
   public static zzs zzag(IBinder var0) {
      if (var0 == null) {
         return null;
      } else {
         IInterface var1;
         return (zzs)((var1 = var0.queryLocalInterface("com.google.android.gms.maps.model.internal.IPolygonDelegate")) instanceof zzs ? (zzs)var1 : new zzu(var0));
      }
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else {
         IObjectWrapper var5;
         List var9;
         ArrayList var10;
         int var11;
         boolean var12;
         float var14;
         switch(var1) {
         case 1:
            this.remove();
            var3.writeNoException();
            break;
         case 2:
            String var15 = this.getId();
            var3.writeNoException();
            var3.writeString(var15);
            break;
         case 3:
            var10 = var2.createTypedArrayList(LatLng.CREATOR);
            this.setPoints(var10);
            var3.writeNoException();
            break;
         case 4:
            var9 = this.getPoints();
            var3.writeNoException();
            var3.writeTypedList(var9);
            break;
         case 5:
            var10 = zzef.zzb(var2);
            this.setHoles(var10);
            var3.writeNoException();
            break;
         case 6:
            var9 = this.getHoles();
            var3.writeNoException();
            var3.writeList(var9);
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
            var11 = var2.readInt();
            this.setStrokeColor(var11);
            var3.writeNoException();
            break;
         case 10:
            var11 = this.getStrokeColor();
            var3.writeNoException();
            var3.writeInt(var11);
            break;
         case 11:
            var11 = var2.readInt();
            this.setFillColor(var11);
            var3.writeNoException();
            break;
         case 12:
            var11 = this.getFillColor();
            var3.writeNoException();
            var3.writeInt(var11);
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
            var12 = zzef.zza(var2);
            this.setVisible(var12);
            var3.writeNoException();
            break;
         case 16:
            var12 = this.isVisible();
            var3.writeNoException();
            zzef.zza(var3, var12);
            break;
         case 17:
            var12 = zzef.zza(var2);
            this.setGeodesic(var12);
            var3.writeNoException();
            break;
         case 18:
            var12 = this.isGeodesic();
            var3.writeNoException();
            zzef.zza(var3, var12);
            break;
         case 19:
            IBinder var7;
            IInterface var8;
            Object var13 = (var7 = var2.readStrongBinder()) == null ? null : ((var8 = var7.queryLocalInterface("com.google.android.gms.maps.model.internal.IPolygonDelegate")) instanceof zzs ? (zzs)var8 : new zzu(var7));
            boolean var6 = this.zzb((zzs)var13);
            var3.writeNoException();
            zzef.zza(var3, var6);
            break;
         case 20:
            var11 = this.hashCodeRemote();
            var3.writeNoException();
            var3.writeInt(var11);
            break;
         case 21:
            var12 = zzef.zza(var2);
            this.setClickable(var12);
            var3.writeNoException();
            break;
         case 22:
            var12 = this.isClickable();
            var3.writeNoException();
            zzef.zza(var3, var12);
            break;
         case 23:
            var11 = var2.readInt();
            this.setStrokeJointType(var11);
            var3.writeNoException();
            break;
         case 24:
            var11 = this.getStrokeJointType();
            var3.writeNoException();
            var3.writeInt(var11);
            break;
         case 25:
            var10 = var2.createTypedArrayList(PatternItem.CREATOR);
            this.setStrokePattern(var10);
            var3.writeNoException();
            break;
         case 26:
            var9 = this.getStrokePattern();
            var3.writeNoException();
            var3.writeTypedList(var9);
            break;
         case 27:
            var5 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzM(var2.readStrongBinder());
            this.setTag(var5);
            var3.writeNoException();
            break;
         case 28:
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
