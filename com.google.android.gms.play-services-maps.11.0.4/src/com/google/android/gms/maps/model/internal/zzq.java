package com.google.android.gms.maps.model.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzee;
import com.google.android.gms.internal.zzef;
import com.google.android.gms.maps.model.LatLng;

public abstract class zzq extends zzee implements zzp {
   public static zzp zzaf(IBinder var0) {
      if (var0 == null) {
         return null;
      } else {
         IInterface var1;
         return (zzp)((var1 = var0.queryLocalInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate")) instanceof zzp ? (zzp)var1 : new zzr(var0));
      }
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else {
         IObjectWrapper var5;
         float var6;
         float var9;
         boolean var11;
         String var14;
         LatLng var15;
         switch(var1) {
         case 1:
            this.remove();
            var3.writeNoException();
            break;
         case 2:
            var14 = this.getId();
            var3.writeNoException();
            var3.writeString(var14);
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
            var14 = var2.readString();
            this.setTitle(var14);
            var3.writeNoException();
            break;
         case 6:
            var14 = this.getTitle();
            var3.writeNoException();
            var3.writeString(var14);
            break;
         case 7:
            var14 = var2.readString();
            this.setSnippet(var14);
            var3.writeNoException();
            break;
         case 8:
            var14 = this.getSnippet();
            var3.writeNoException();
            var3.writeString(var14);
            break;
         case 9:
            var11 = zzef.zza(var2);
            this.setDraggable(var11);
            var3.writeNoException();
            break;
         case 10:
            var11 = this.isDraggable();
            var3.writeNoException();
            zzef.zza(var3, var11);
            break;
         case 11:
            this.showInfoWindow();
            var3.writeNoException();
            break;
         case 12:
            this.hideInfoWindow();
            var3.writeNoException();
            break;
         case 13:
            var11 = this.isInfoWindowShown();
            var3.writeNoException();
            zzef.zza(var3, var11);
            break;
         case 14:
            var11 = zzef.zza(var2);
            this.setVisible(var11);
            var3.writeNoException();
            break;
         case 15:
            var11 = this.isVisible();
            var3.writeNoException();
            zzef.zza(var3, var11);
            break;
         case 16:
            IBinder var7;
            IInterface var8;
            Object var13 = (var7 = var2.readStrongBinder()) == null ? null : ((var8 = var7.queryLocalInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate")) instanceof zzp ? (zzp)var8 : new zzr(var7));
            boolean var10 = this.zzj((zzp)var13);
            var3.writeNoException();
            zzef.zza(var3, var10);
            break;
         case 17:
            int var12 = this.hashCodeRemote();
            var3.writeNoException();
            var3.writeInt(var12);
            break;
         case 18:
            var5 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzM(var2.readStrongBinder());
            this.zzK(var5);
            var3.writeNoException();
            break;
         case 19:
            var9 = var2.readFloat();
            var6 = var2.readFloat();
            this.setAnchor(var9, var6);
            var3.writeNoException();
            break;
         case 20:
            var11 = zzef.zza(var2);
            this.setFlat(var11);
            var3.writeNoException();
            break;
         case 21:
            var11 = this.isFlat();
            var3.writeNoException();
            zzef.zza(var3, var11);
            break;
         case 22:
            var9 = var2.readFloat();
            this.setRotation(var9);
            var3.writeNoException();
            break;
         case 23:
            var9 = this.getRotation();
            var3.writeNoException();
            var3.writeFloat(var9);
            break;
         case 24:
            var9 = var2.readFloat();
            var6 = var2.readFloat();
            this.setInfoWindowAnchor(var9, var6);
            var3.writeNoException();
            break;
         case 25:
            var9 = var2.readFloat();
            this.setAlpha(var9);
            var3.writeNoException();
            break;
         case 26:
            var9 = this.getAlpha();
            var3.writeNoException();
            var3.writeFloat(var9);
            break;
         case 27:
            var9 = var2.readFloat();
            this.setZIndex(var9);
            var3.writeNoException();
            break;
         case 28:
            var9 = this.getZIndex();
            var3.writeNoException();
            var3.writeFloat(var9);
            break;
         case 29:
            var5 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzM(var2.readStrongBinder());
            this.setTag(var5);
            var3.writeNoException();
            break;
         case 30:
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
