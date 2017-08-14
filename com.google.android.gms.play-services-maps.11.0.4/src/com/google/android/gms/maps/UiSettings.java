package com.google.android.gms.maps;

import android.os.RemoteException;
import com.google.android.gms.maps.internal.IUiSettingsDelegate;
import com.google.android.gms.maps.model.RuntimeRemoteException;

public final class UiSettings {
   private final IUiSettingsDelegate zzbmY;

   UiSettings(IUiSettingsDelegate var1) {
      this.zzbmY = var1;
   }

   public final void setZoomControlsEnabled(boolean var1) {
      try {
         this.zzbmY.setZoomControlsEnabled(var1);
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public final void setCompassEnabled(boolean var1) {
      try {
         this.zzbmY.setCompassEnabled(var1);
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public final void setMyLocationButtonEnabled(boolean var1) {
      try {
         this.zzbmY.setMyLocationButtonEnabled(var1);
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public final void setIndoorLevelPickerEnabled(boolean var1) {
      try {
         this.zzbmY.setIndoorLevelPickerEnabled(var1);
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public final void setScrollGesturesEnabled(boolean var1) {
      try {
         this.zzbmY.setScrollGesturesEnabled(var1);
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public final void setZoomGesturesEnabled(boolean var1) {
      try {
         this.zzbmY.setZoomGesturesEnabled(var1);
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public final void setTiltGesturesEnabled(boolean var1) {
      try {
         this.zzbmY.setTiltGesturesEnabled(var1);
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public final void setRotateGesturesEnabled(boolean var1) {
      try {
         this.zzbmY.setRotateGesturesEnabled(var1);
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public final void setAllGesturesEnabled(boolean var1) {
      try {
         this.zzbmY.setAllGesturesEnabled(var1);
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public final void setMapToolbarEnabled(boolean var1) {
      try {
         this.zzbmY.setMapToolbarEnabled(var1);
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public final boolean isZoomControlsEnabled() {
      try {
         return this.zzbmY.isZoomControlsEnabled();
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public final boolean isCompassEnabled() {
      try {
         return this.zzbmY.isCompassEnabled();
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public final boolean isMyLocationButtonEnabled() {
      try {
         return this.zzbmY.isMyLocationButtonEnabled();
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public final boolean isIndoorLevelPickerEnabled() {
      try {
         return this.zzbmY.isIndoorLevelPickerEnabled();
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public final boolean isScrollGesturesEnabled() {
      try {
         return this.zzbmY.isScrollGesturesEnabled();
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public final boolean isZoomGesturesEnabled() {
      try {
         return this.zzbmY.isZoomGesturesEnabled();
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public final boolean isTiltGesturesEnabled() {
      try {
         return this.zzbmY.isTiltGesturesEnabled();
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public final boolean isRotateGesturesEnabled() {
      try {
         return this.zzbmY.isRotateGesturesEnabled();
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public final boolean isMapToolbarEnabled() {
      try {
         return this.zzbmY.isMapToolbarEnabled();
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }
}
