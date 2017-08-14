package com.google.android.gms.maps.model;

import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.maps.model.internal.IPolylineDelegate;
import java.util.List;

public final class Polyline {
   private final IPolylineDelegate zzbnR;

   public Polyline(IPolylineDelegate var1) {
      this.zzbnR = (IPolylineDelegate)zzbo.zzu(var1);
   }

   public final void remove() {
      try {
         this.zzbnR.remove();
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public final String getId() {
      try {
         return this.zzbnR.getId();
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public final void setPoints(List var1) {
      try {
         this.zzbnR.setPoints(var1);
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public final List getPoints() {
      try {
         return this.zzbnR.getPoints();
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public final void setWidth(float var1) {
      try {
         this.zzbnR.setWidth(var1);
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public final float getWidth() {
      try {
         return this.zzbnR.getWidth();
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public final void setColor(int var1) {
      try {
         this.zzbnR.setColor(var1);
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public final int getColor() {
      try {
         return this.zzbnR.getColor();
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public final void setStartCap(@NonNull Cap var1) {
      zzbo.zzb(var1, "startCap must not be null");

      try {
         this.zzbnR.setStartCap(var1);
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   @NonNull
   public final Cap getStartCap() {
      try {
         return this.zzbnR.getStartCap().zzwk();
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public final void setEndCap(@NonNull Cap var1) {
      zzbo.zzb(var1, "endCap must not be null");

      try {
         this.zzbnR.setEndCap(var1);
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   @NonNull
   public final Cap getEndCap() {
      try {
         return this.zzbnR.getEndCap().zzwk();
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public final void setJointType(int var1) {
      try {
         this.zzbnR.setJointType(var1);
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public final int getJointType() {
      try {
         return this.zzbnR.getJointType();
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public final void setPattern(@Nullable List var1) {
      try {
         this.zzbnR.setPattern(var1);
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   @Nullable
   public final List getPattern() {
      try {
         return PatternItem.zzF(this.zzbnR.getPattern());
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public final void setZIndex(float var1) {
      try {
         this.zzbnR.setZIndex(var1);
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public final float getZIndex() {
      try {
         return this.zzbnR.getZIndex();
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public final void setVisible(boolean var1) {
      try {
         this.zzbnR.setVisible(var1);
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public final boolean isVisible() {
      try {
         return this.zzbnR.isVisible();
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public final void setGeodesic(boolean var1) {
      try {
         this.zzbnR.setGeodesic(var1);
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public final boolean isGeodesic() {
      try {
         return this.zzbnR.isGeodesic();
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public final void setClickable(boolean var1) {
      try {
         this.zzbnR.setClickable(var1);
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public final boolean isClickable() {
      try {
         return this.zzbnR.isClickable();
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public final void setTag(@Nullable Object var1) {
      try {
         this.zzbnR.setTag(com.google.android.gms.dynamic.zzn.zzw(var1));
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   @Nullable
   public final Object getTag() {
      try {
         return com.google.android.gms.dynamic.zzn.zzE(this.zzbnR.getTag());
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public final boolean equals(Object var1) {
      if (!(var1 instanceof Polyline)) {
         return false;
      } else {
         try {
            return this.zzbnR.equalsRemote(((Polyline)var1).zzbnR);
         } catch (RemoteException var3) {
            throw new RuntimeRemoteException(var3);
         }
      }
   }

   public final int hashCode() {
      try {
         return this.zzbnR.hashCodeRemote();
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }
}
