package com.google.android.gms.maps;

import android.graphics.Point;
import android.os.RemoteException;
import com.google.android.gms.maps.internal.IProjectionDelegate;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import com.google.android.gms.maps.model.VisibleRegion;

public final class Projection {
   private final IProjectionDelegate zzbmB;

   Projection(IProjectionDelegate var1) {
      this.zzbmB = var1;
   }

   public final LatLng fromScreenLocation(Point var1) {
      try {
         return this.zzbmB.fromScreenLocation(com.google.android.gms.dynamic.zzn.zzw(var1));
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public final Point toScreenLocation(LatLng var1) {
      try {
         return (Point)com.google.android.gms.dynamic.zzn.zzE(this.zzbmB.toScreenLocation(var1));
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public final VisibleRegion getVisibleRegion() {
      try {
         return this.zzbmB.getVisibleRegion();
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }
}
