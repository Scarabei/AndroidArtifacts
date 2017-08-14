package com.google.android.gms.maps;

import android.graphics.Point;
import android.os.RemoteException;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate;
import com.google.android.gms.maps.internal.zzbf;
import com.google.android.gms.maps.internal.zzbh;
import com.google.android.gms.maps.internal.zzbj;
import com.google.android.gms.maps.internal.zzbl;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;
import com.google.android.gms.maps.model.StreetViewPanoramaLocation;
import com.google.android.gms.maps.model.StreetViewPanoramaOrientation;

public class StreetViewPanorama {
   private final IStreetViewPanoramaDelegate zzbmC;

   protected StreetViewPanorama(IStreetViewPanoramaDelegate var1) {
      this.zzbmC = (IStreetViewPanoramaDelegate)zzbo.zzu(var1);
   }

   public boolean isZoomGesturesEnabled() {
      try {
         return this.zzbmC.isZoomGesturesEnabled();
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public void setZoomGesturesEnabled(boolean var1) {
      try {
         this.zzbmC.enableZoom(var1);
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public boolean isPanningGesturesEnabled() {
      try {
         return this.zzbmC.isPanningGesturesEnabled();
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public void setPanningGesturesEnabled(boolean var1) {
      try {
         this.zzbmC.enablePanning(var1);
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public boolean isUserNavigationEnabled() {
      try {
         return this.zzbmC.isUserNavigationEnabled();
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public void setUserNavigationEnabled(boolean var1) {
      try {
         this.zzbmC.enableUserNavigation(var1);
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public boolean isStreetNamesEnabled() {
      try {
         return this.zzbmC.isStreetNamesEnabled();
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public void setStreetNamesEnabled(boolean var1) {
      try {
         this.zzbmC.enableStreetNames(var1);
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public void animateTo(StreetViewPanoramaCamera var1, long var2) {
      try {
         this.zzbmC.animateTo(var1, var2);
      } catch (RemoteException var5) {
         throw new RuntimeRemoteException(var5);
      }
   }

   public StreetViewPanoramaCamera getPanoramaCamera() {
      try {
         return this.zzbmC.getPanoramaCamera();
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public void setPosition(String var1) {
      try {
         this.zzbmC.setPositionWithID(var1);
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public void setPosition(LatLng var1) {
      try {
         this.zzbmC.setPosition(var1);
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public void setPosition(LatLng var1, int var2) {
      try {
         this.zzbmC.setPositionWithRadius(var1, var2);
      } catch (RemoteException var4) {
         throw new RuntimeRemoteException(var4);
      }
   }

   public StreetViewPanoramaLocation getLocation() {
      try {
         return this.zzbmC.getStreetViewPanoramaLocation();
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public StreetViewPanoramaOrientation pointToOrientation(Point var1) {
      try {
         return this.zzbmC.pointToOrientation(com.google.android.gms.dynamic.zzn.zzw(var1));
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public Point orientationToPoint(StreetViewPanoramaOrientation var1) {
      try {
         IObjectWrapper var2;
         return (var2 = this.zzbmC.orientationToPoint(var1)) == null ? null : (Point)com.google.android.gms.dynamic.zzn.zzE(var2);
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public final void setOnStreetViewPanoramaChangeListener(StreetViewPanorama.OnStreetViewPanoramaChangeListener var1) {
      try {
         if (var1 == null) {
            this.zzbmC.setOnStreetViewPanoramaChangeListener((zzbh)null);
         } else {
            this.zzbmC.setOnStreetViewPanoramaChangeListener(new zzac(this, var1));
         }
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public final void setOnStreetViewPanoramaCameraChangeListener(StreetViewPanorama.OnStreetViewPanoramaCameraChangeListener var1) {
      try {
         if (var1 == null) {
            this.zzbmC.setOnStreetViewPanoramaCameraChangeListener((zzbf)null);
         } else {
            this.zzbmC.setOnStreetViewPanoramaCameraChangeListener(new zzad(this, var1));
         }
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public final void setOnStreetViewPanoramaClickListener(StreetViewPanorama.OnStreetViewPanoramaClickListener var1) {
      try {
         if (var1 == null) {
            this.zzbmC.setOnStreetViewPanoramaClickListener((zzbj)null);
         } else {
            this.zzbmC.setOnStreetViewPanoramaClickListener(new zzae(this, var1));
         }
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public final void setOnStreetViewPanoramaLongClickListener(StreetViewPanorama.OnStreetViewPanoramaLongClickListener var1) {
      try {
         if (var1 == null) {
            this.zzbmC.setOnStreetViewPanoramaLongClickListener((zzbl)null);
         } else {
            this.zzbmC.setOnStreetViewPanoramaLongClickListener(new zzaf(this, var1));
         }
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public interface OnStreetViewPanoramaLongClickListener {
      void onStreetViewPanoramaLongClick(StreetViewPanoramaOrientation var1);
   }

   public interface OnStreetViewPanoramaClickListener {
      void onStreetViewPanoramaClick(StreetViewPanoramaOrientation var1);
   }

   public interface OnStreetViewPanoramaCameraChangeListener {
      void onStreetViewPanoramaCameraChange(StreetViewPanoramaCamera var1);
   }

   public interface OnStreetViewPanoramaChangeListener {
      void onStreetViewPanoramaChange(StreetViewPanoramaLocation var1);
   }
}
