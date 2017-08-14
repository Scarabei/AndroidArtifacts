package com.google.android.gms.maps.model;

import android.os.RemoteException;
import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.dynamic.IObjectWrapper;

public final class GroundOverlay {
   private final com.google.android.gms.maps.model.internal.zzg zzbno;

   public GroundOverlay(com.google.android.gms.maps.model.internal.zzg var1) {
      this.zzbno = (com.google.android.gms.maps.model.internal.zzg)zzbo.zzu(var1);
   }

   public final void remove() {
      try {
         this.zzbno.remove();
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public final String getId() {
      try {
         return this.zzbno.getId();
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public final void setPosition(LatLng var1) {
      try {
         this.zzbno.setPosition(var1);
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public final LatLng getPosition() {
      try {
         return this.zzbno.getPosition();
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public final void setImage(@NonNull BitmapDescriptor var1) {
      zzbo.zzb(var1, "imageDescriptor must not be null");

      try {
         IObjectWrapper var2 = var1.zzwe();
         this.zzbno.zzJ(var2);
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public final void setDimensions(float var1) {
      try {
         this.zzbno.setDimensions(var1);
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public final void setDimensions(float var1, float var2) {
      try {
         this.zzbno.zzf(var1, var2);
      } catch (RemoteException var4) {
         throw new RuntimeRemoteException(var4);
      }
   }

   public final float getWidth() {
      try {
         return this.zzbno.getWidth();
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public final float getHeight() {
      try {
         return this.zzbno.getHeight();
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public final void setPositionFromBounds(LatLngBounds var1) {
      try {
         this.zzbno.setPositionFromBounds(var1);
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public final LatLngBounds getBounds() {
      try {
         return this.zzbno.getBounds();
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public final void setBearing(float var1) {
      try {
         this.zzbno.setBearing(var1);
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public final float getBearing() {
      try {
         return this.zzbno.getBearing();
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public final void setZIndex(float var1) {
      try {
         this.zzbno.setZIndex(var1);
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public final float getZIndex() {
      try {
         return this.zzbno.getZIndex();
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public final void setVisible(boolean var1) {
      try {
         this.zzbno.setVisible(var1);
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public final boolean isVisible() {
      try {
         return this.zzbno.isVisible();
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public final void setClickable(boolean var1) {
      try {
         this.zzbno.setClickable(var1);
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public final boolean isClickable() {
      try {
         return this.zzbno.isClickable();
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public final void setTransparency(float var1) {
      try {
         this.zzbno.setTransparency(var1);
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public final float getTransparency() {
      try {
         return this.zzbno.getTransparency();
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public final void setTag(Object var1) {
      try {
         this.zzbno.setTag(com.google.android.gms.dynamic.zzn.zzw(var1));
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public final Object getTag() {
      try {
         return com.google.android.gms.dynamic.zzn.zzE(this.zzbno.getTag());
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public final boolean equals(Object var1) {
      if (!(var1 instanceof GroundOverlay)) {
         return false;
      } else {
         try {
            return this.zzbno.zzb(((GroundOverlay)var1).zzbno);
         } catch (RemoteException var3) {
            throw new RuntimeRemoteException(var3);
         }
      }
   }

   public final int hashCode() {
      try {
         return this.zzbno.hashCodeRemote();
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }
}
