package com.google.android.gms.maps.model;

import android.os.RemoteException;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.zzbo;
import java.util.List;

public final class Circle {
   private final com.google.android.gms.maps.model.internal.zzd zzbnh;

   public Circle(com.google.android.gms.maps.model.internal.zzd var1) {
      this.zzbnh = (com.google.android.gms.maps.model.internal.zzd)zzbo.zzu(var1);
   }

   public final void remove() {
      try {
         this.zzbnh.remove();
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public final String getId() {
      try {
         return this.zzbnh.getId();
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public final void setCenter(LatLng var1) {
      try {
         this.zzbnh.setCenter(var1);
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public final LatLng getCenter() {
      try {
         return this.zzbnh.getCenter();
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public final void setRadius(double var1) {
      try {
         this.zzbnh.setRadius(var1);
      } catch (RemoteException var4) {
         throw new RuntimeRemoteException(var4);
      }
   }

   public final double getRadius() {
      try {
         return this.zzbnh.getRadius();
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public final void setStrokeWidth(float var1) {
      try {
         this.zzbnh.setStrokeWidth(var1);
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public final float getStrokeWidth() {
      try {
         return this.zzbnh.getStrokeWidth();
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public final void setStrokeColor(int var1) {
      try {
         this.zzbnh.setStrokeColor(var1);
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public final int getStrokeColor() {
      try {
         return this.zzbnh.getStrokeColor();
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public final void setStrokePattern(@Nullable List var1) {
      try {
         this.zzbnh.setStrokePattern(var1);
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   @Nullable
   public final List getStrokePattern() {
      try {
         return PatternItem.zzF(this.zzbnh.getStrokePattern());
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public final void setFillColor(int var1) {
      try {
         this.zzbnh.setFillColor(var1);
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public final int getFillColor() {
      try {
         return this.zzbnh.getFillColor();
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public final void setZIndex(float var1) {
      try {
         this.zzbnh.setZIndex(var1);
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public final float getZIndex() {
      try {
         return this.zzbnh.getZIndex();
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public final void setVisible(boolean var1) {
      try {
         this.zzbnh.setVisible(var1);
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public final boolean isVisible() {
      try {
         return this.zzbnh.isVisible();
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public final void setClickable(boolean var1) {
      try {
         this.zzbnh.setClickable(var1);
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public final boolean isClickable() {
      try {
         return this.zzbnh.isClickable();
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public final void setTag(@Nullable Object var1) {
      try {
         this.zzbnh.setTag(com.google.android.gms.dynamic.zzn.zzw(var1));
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   @Nullable
   public final Object getTag() {
      try {
         return com.google.android.gms.dynamic.zzn.zzE(this.zzbnh.getTag());
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public final boolean equals(Object var1) {
      if (!(var1 instanceof Circle)) {
         return false;
      } else {
         try {
            return this.zzbnh.zzb(((Circle)var1).zzbnh);
         } catch (RemoteException var3) {
            throw new RuntimeRemoteException(var3);
         }
      }
   }

   public final int hashCode() {
      try {
         return this.zzbnh.hashCodeRemote();
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }
}
