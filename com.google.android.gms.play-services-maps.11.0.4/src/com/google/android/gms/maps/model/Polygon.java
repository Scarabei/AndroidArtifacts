package com.google.android.gms.maps.model;

import android.os.RemoteException;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.zzbo;
import java.util.List;

public final class Polygon {
   private final com.google.android.gms.maps.model.internal.zzs zzbnM;

   public Polygon(com.google.android.gms.maps.model.internal.zzs var1) {
      this.zzbnM = (com.google.android.gms.maps.model.internal.zzs)zzbo.zzu(var1);
   }

   public final void remove() {
      try {
         this.zzbnM.remove();
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public final String getId() {
      try {
         return this.zzbnM.getId();
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public final void setPoints(List var1) {
      try {
         this.zzbnM.setPoints(var1);
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public final List getPoints() {
      try {
         return this.zzbnM.getPoints();
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public final void setHoles(List var1) {
      try {
         this.zzbnM.setHoles(var1);
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public final List getHoles() {
      try {
         return this.zzbnM.getHoles();
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public final void setStrokeWidth(float var1) {
      try {
         this.zzbnM.setStrokeWidth(var1);
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public final float getStrokeWidth() {
      try {
         return this.zzbnM.getStrokeWidth();
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public final void setStrokeColor(int var1) {
      try {
         this.zzbnM.setStrokeColor(var1);
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public final int getStrokeColor() {
      try {
         return this.zzbnM.getStrokeColor();
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public final void setStrokeJointType(int var1) {
      try {
         this.zzbnM.setStrokeJointType(var1);
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public final int getStrokeJointType() {
      try {
         return this.zzbnM.getStrokeJointType();
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public final void setStrokePattern(@Nullable List var1) {
      try {
         this.zzbnM.setStrokePattern(var1);
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   @Nullable
   public final List getStrokePattern() {
      try {
         return PatternItem.zzF(this.zzbnM.getStrokePattern());
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public final void setFillColor(int var1) {
      try {
         this.zzbnM.setFillColor(var1);
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public final int getFillColor() {
      try {
         return this.zzbnM.getFillColor();
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public final void setZIndex(float var1) {
      try {
         this.zzbnM.setZIndex(var1);
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public final float getZIndex() {
      try {
         return this.zzbnM.getZIndex();
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public final void setVisible(boolean var1) {
      try {
         this.zzbnM.setVisible(var1);
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public final boolean isVisible() {
      try {
         return this.zzbnM.isVisible();
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public final void setGeodesic(boolean var1) {
      try {
         this.zzbnM.setGeodesic(var1);
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public final boolean isGeodesic() {
      try {
         return this.zzbnM.isGeodesic();
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public final void setClickable(boolean var1) {
      try {
         this.zzbnM.setClickable(var1);
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public final boolean isClickable() {
      try {
         return this.zzbnM.isClickable();
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public final void setTag(@Nullable Object var1) {
      try {
         this.zzbnM.setTag(com.google.android.gms.dynamic.zzn.zzw(var1));
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   @Nullable
   public final Object getTag() {
      try {
         return com.google.android.gms.dynamic.zzn.zzE(this.zzbnM.getTag());
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public final boolean equals(Object var1) {
      if (!(var1 instanceof Polygon)) {
         return false;
      } else {
         try {
            return this.zzbnM.zzb(((Polygon)var1).zzbnM);
         } catch (RemoteException var3) {
            throw new RuntimeRemoteException(var3);
         }
      }
   }

   public final int hashCode() {
      try {
         return this.zzbnM.hashCodeRemote();
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }
}
