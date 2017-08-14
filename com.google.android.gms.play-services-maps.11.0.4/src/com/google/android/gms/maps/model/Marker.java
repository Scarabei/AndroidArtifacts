package com.google.android.gms.maps.model;

import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.dynamic.IObjectWrapper;

public final class Marker {
   private final com.google.android.gms.maps.model.internal.zzp zzbnD;

   public Marker(com.google.android.gms.maps.model.internal.zzp var1) {
      this.zzbnD = (com.google.android.gms.maps.model.internal.zzp)zzbo.zzu(var1);
   }

   public final void remove() {
      try {
         this.zzbnD.remove();
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public final String getId() {
      try {
         return this.zzbnD.getId();
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public final void setPosition(@NonNull LatLng var1) {
      if (var1 == null) {
         throw new IllegalArgumentException("latlng cannot be null - a position is required.");
      } else {
         try {
            this.zzbnD.setPosition(var1);
         } catch (RemoteException var3) {
            throw new RuntimeRemoteException(var3);
         }
      }
   }

   public final LatLng getPosition() {
      try {
         return this.zzbnD.getPosition();
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public final void setZIndex(float var1) {
      try {
         this.zzbnD.setZIndex(var1);
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public final float getZIndex() {
      try {
         return this.zzbnD.getZIndex();
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public final void setIcon(@Nullable BitmapDescriptor var1) {
      try {
         if (var1 == null) {
            this.zzbnD.zzK((IObjectWrapper)null);
         } else {
            IObjectWrapper var2 = var1.zzwe();
            this.zzbnD.zzK(var2);
         }
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public final void setAnchor(float var1, float var2) {
      try {
         this.zzbnD.setAnchor(var1, var2);
      } catch (RemoteException var4) {
         throw new RuntimeRemoteException(var4);
      }
   }

   public final void setInfoWindowAnchor(float var1, float var2) {
      try {
         this.zzbnD.setInfoWindowAnchor(var1, var2);
      } catch (RemoteException var4) {
         throw new RuntimeRemoteException(var4);
      }
   }

   public final void setTitle(@Nullable String var1) {
      try {
         this.zzbnD.setTitle(var1);
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public final String getTitle() {
      try {
         return this.zzbnD.getTitle();
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public final void setSnippet(@Nullable String var1) {
      try {
         this.zzbnD.setSnippet(var1);
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public final String getSnippet() {
      try {
         return this.zzbnD.getSnippet();
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public final void setDraggable(boolean var1) {
      try {
         this.zzbnD.setDraggable(var1);
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public final boolean isDraggable() {
      try {
         return this.zzbnD.isDraggable();
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public final void showInfoWindow() {
      try {
         this.zzbnD.showInfoWindow();
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public final void hideInfoWindow() {
      try {
         this.zzbnD.hideInfoWindow();
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public final boolean isInfoWindowShown() {
      try {
         return this.zzbnD.isInfoWindowShown();
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public final void setVisible(boolean var1) {
      try {
         this.zzbnD.setVisible(var1);
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public final boolean isVisible() {
      try {
         return this.zzbnD.isVisible();
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public final void setFlat(boolean var1) {
      try {
         this.zzbnD.setFlat(var1);
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public final boolean isFlat() {
      try {
         return this.zzbnD.isFlat();
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public final void setRotation(float var1) {
      try {
         this.zzbnD.setRotation(var1);
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public final float getRotation() {
      try {
         return this.zzbnD.getRotation();
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public final void setAlpha(float var1) {
      try {
         this.zzbnD.setAlpha(var1);
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public final float getAlpha() {
      try {
         return this.zzbnD.getAlpha();
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public final void setTag(@Nullable Object var1) {
      try {
         this.zzbnD.setTag(com.google.android.gms.dynamic.zzn.zzw(var1));
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   @Nullable
   public final Object getTag() {
      try {
         return com.google.android.gms.dynamic.zzn.zzE(this.zzbnD.getTag());
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public final boolean equals(Object var1) {
      if (!(var1 instanceof Marker)) {
         return false;
      } else {
         try {
            return this.zzbnD.zzj(((Marker)var1).zzbnD);
         } catch (RemoteException var3) {
            throw new RuntimeRemoteException(var3);
         }
      }
   }

   public final int hashCode() {
      try {
         return this.zzbnD.hashCodeRemote();
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }
}
