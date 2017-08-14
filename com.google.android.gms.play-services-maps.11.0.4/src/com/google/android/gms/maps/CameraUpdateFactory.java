package com.google.android.gms.maps;

import android.graphics.Point;
import android.os.RemoteException;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.RuntimeRemoteException;

public final class CameraUpdateFactory {
   private static ICameraUpdateFactoryDelegate zzblw;

   private static ICameraUpdateFactoryDelegate zzwf() {
      return (ICameraUpdateFactoryDelegate)zzbo.zzb(zzblw, "CameraUpdateFactory is not initialized");
   }

   public static void zza(ICameraUpdateFactoryDelegate var0) {
      zzblw = (ICameraUpdateFactoryDelegate)zzbo.zzu(var0);
   }

   public static CameraUpdate zoomIn() {
      try {
         return new CameraUpdate(zzwf().zoomIn());
      } catch (RemoteException var1) {
         throw new RuntimeRemoteException(var1);
      }
   }

   public static CameraUpdate zoomOut() {
      try {
         return new CameraUpdate(zzwf().zoomOut());
      } catch (RemoteException var1) {
         throw new RuntimeRemoteException(var1);
      }
   }

   public static CameraUpdate scrollBy(float var0, float var1) {
      try {
         return new CameraUpdate(zzwf().scrollBy(var0, var1));
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public static CameraUpdate zoomTo(float var0) {
      try {
         return new CameraUpdate(zzwf().zoomTo(var0));
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public static CameraUpdate zoomBy(float var0) {
      try {
         return new CameraUpdate(zzwf().zoomBy(var0));
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public static CameraUpdate zoomBy(float var0, Point var1) {
      try {
         return new CameraUpdate(zzwf().zoomByWithFocus(var0, var1.x, var1.y));
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public static CameraUpdate newCameraPosition(CameraPosition var0) {
      try {
         return new CameraUpdate(zzwf().newCameraPosition(var0));
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public static CameraUpdate newLatLng(LatLng var0) {
      try {
         return new CameraUpdate(zzwf().newLatLng(var0));
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public static CameraUpdate newLatLngZoom(LatLng var0, float var1) {
      try {
         return new CameraUpdate(zzwf().newLatLngZoom(var0, var1));
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public static CameraUpdate newLatLngBounds(LatLngBounds var0, int var1) {
      try {
         return new CameraUpdate(zzwf().newLatLngBounds(var0, var1));
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public static CameraUpdate newLatLngBounds(LatLngBounds var0, int var1, int var2, int var3) {
      try {
         return new CameraUpdate(zzwf().newLatLngBoundsWithSize(var0, var1, var2, var3));
      } catch (RemoteException var5) {
         throw new RuntimeRemoteException(var5);
      }
   }
}
