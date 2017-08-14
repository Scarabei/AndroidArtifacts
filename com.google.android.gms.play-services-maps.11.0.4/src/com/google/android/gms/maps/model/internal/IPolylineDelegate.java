package com.google.android.gms.maps.model.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzee;
import com.google.android.gms.internal.zzef;
import com.google.android.gms.maps.model.Cap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PatternItem;
import java.util.ArrayList;
import java.util.List;

public interface IPolylineDelegate extends IInterface {
   void remove() throws RemoteException;

   String getId() throws RemoteException;

   void setPoints(List var1) throws RemoteException;

   List getPoints() throws RemoteException;

   void setWidth(float var1) throws RemoteException;

   float getWidth() throws RemoteException;

   void setColor(int var1) throws RemoteException;

   int getColor() throws RemoteException;

   void setZIndex(float var1) throws RemoteException;

   float getZIndex() throws RemoteException;

   void setVisible(boolean var1) throws RemoteException;

   boolean isVisible() throws RemoteException;

   void setGeodesic(boolean var1) throws RemoteException;

   boolean isGeodesic() throws RemoteException;

   boolean equalsRemote(IPolylineDelegate var1) throws RemoteException;

   int hashCodeRemote() throws RemoteException;

   void setClickable(boolean var1) throws RemoteException;

   boolean isClickable() throws RemoteException;

   void setStartCap(Cap var1) throws RemoteException;

   Cap getStartCap() throws RemoteException;

   void setEndCap(Cap var1) throws RemoteException;

   Cap getEndCap() throws RemoteException;

   void setJointType(int var1) throws RemoteException;

   int getJointType() throws RemoteException;

   void setPattern(List var1) throws RemoteException;

   List getPattern() throws RemoteException;

   void setTag(IObjectWrapper var1) throws RemoteException;

   IObjectWrapper getTag() throws RemoteException;

   public abstract static class zza extends zzee implements IPolylineDelegate {
      public static IPolylineDelegate zzah(IBinder var0) {
         if (var0 == null) {
            return null;
         } else {
            IInterface var1;
            return (IPolylineDelegate)((var1 = var0.queryLocalInterface("com.google.android.gms.maps.model.internal.IPolylineDelegate")) instanceof IPolylineDelegate ? (IPolylineDelegate)var1 : new zzv(var0));
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
            Cap var12;
            boolean var13;
            float var15;
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
               var15 = var2.readFloat();
               this.setWidth(var15);
               var3.writeNoException();
               break;
            case 6:
               var15 = this.getWidth();
               var3.writeNoException();
               var3.writeFloat(var15);
               break;
            case 7:
               var11 = var2.readInt();
               this.setColor(var11);
               var3.writeNoException();
               break;
            case 8:
               var11 = this.getColor();
               var3.writeNoException();
               var3.writeInt(var11);
               break;
            case 9:
               var15 = var2.readFloat();
               this.setZIndex(var15);
               var3.writeNoException();
               break;
            case 10:
               var15 = this.getZIndex();
               var3.writeNoException();
               var3.writeFloat(var15);
               break;
            case 11:
               var13 = zzef.zza(var2);
               this.setVisible(var13);
               var3.writeNoException();
               break;
            case 12:
               var13 = this.isVisible();
               var3.writeNoException();
               zzef.zza(var3, var13);
               break;
            case 13:
               var13 = zzef.zza(var2);
               this.setGeodesic(var13);
               var3.writeNoException();
               break;
            case 14:
               var13 = this.isGeodesic();
               var3.writeNoException();
               zzef.zza(var3, var13);
               break;
            case 15:
               IBinder var7;
               IInterface var8;
               Object var14 = (var7 = var2.readStrongBinder()) == null ? null : ((var8 = var7.queryLocalInterface("com.google.android.gms.maps.model.internal.IPolylineDelegate")) instanceof IPolylineDelegate ? (IPolylineDelegate)var8 : new zzv(var7));
               boolean var6 = this.equalsRemote((IPolylineDelegate)var14);
               var3.writeNoException();
               zzef.zza(var3, var6);
               break;
            case 16:
               var11 = this.hashCodeRemote();
               var3.writeNoException();
               var3.writeInt(var11);
               break;
            case 17:
               var13 = zzef.zza(var2);
               this.setClickable(var13);
               var3.writeNoException();
               break;
            case 18:
               var13 = this.isClickable();
               var3.writeNoException();
               zzef.zza(var3, var13);
               break;
            case 19:
               var12 = (Cap)zzef.zza(var2, Cap.CREATOR);
               this.setStartCap(var12);
               var3.writeNoException();
               break;
            case 20:
               var12 = this.getStartCap();
               var3.writeNoException();
               zzef.zzb(var3, var12);
               break;
            case 21:
               var12 = (Cap)zzef.zza(var2, Cap.CREATOR);
               this.setEndCap(var12);
               var3.writeNoException();
               break;
            case 22:
               var12 = this.getEndCap();
               var3.writeNoException();
               zzef.zzb(var3, var12);
               break;
            case 23:
               var11 = var2.readInt();
               this.setJointType(var11);
               var3.writeNoException();
               break;
            case 24:
               var11 = this.getJointType();
               var3.writeNoException();
               var3.writeInt(var11);
               break;
            case 25:
               var10 = var2.createTypedArrayList(PatternItem.CREATOR);
               this.setPattern(var10);
               var3.writeNoException();
               break;
            case 26:
               var9 = this.getPattern();
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
}
