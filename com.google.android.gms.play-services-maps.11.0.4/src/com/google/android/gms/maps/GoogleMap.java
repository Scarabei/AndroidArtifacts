package com.google.android.gms.maps;

import android.graphics.Bitmap;
import android.location.Location;
import android.os.RemoteException;
import android.support.annotation.RequiresPermission;
import android.view.View;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.maps.internal.IGoogleMapDelegate;
import com.google.android.gms.maps.internal.ILocationSourceDelegate;
import com.google.android.gms.maps.internal.zzal;
import com.google.android.gms.maps.internal.zzan;
import com.google.android.gms.maps.internal.zzar;
import com.google.android.gms.maps.internal.zzat;
import com.google.android.gms.maps.internal.zzav;
import com.google.android.gms.maps.internal.zzax;
import com.google.android.gms.maps.internal.zzaz;
import com.google.android.gms.maps.internal.zzbb;
import com.google.android.gms.maps.internal.zzbd;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.IndoorBuilding;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PointOfInterest;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import com.google.android.gms.maps.model.TileOverlay;
import com.google.android.gms.maps.model.TileOverlayOptions;

public final class GoogleMap {
   public static final int MAP_TYPE_NONE = 0;
   public static final int MAP_TYPE_NORMAL = 1;
   public static final int MAP_TYPE_SATELLITE = 2;
   public static final int MAP_TYPE_TERRAIN = 3;
   public static final int MAP_TYPE_HYBRID = 4;
   private final IGoogleMapDelegate zzblx;
   private UiSettings zzbly;

   public GoogleMap(IGoogleMapDelegate var1) {
      this.zzblx = (IGoogleMapDelegate)zzbo.zzu(var1);
   }

   public final CameraPosition getCameraPosition() {
      try {
         return this.zzblx.getCameraPosition();
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public final float getMaxZoomLevel() {
      try {
         return this.zzblx.getMaxZoomLevel();
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public final float getMinZoomLevel() {
      try {
         return this.zzblx.getMinZoomLevel();
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public final void moveCamera(CameraUpdate var1) {
      try {
         this.zzblx.moveCamera(var1.zzwe());
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public final void animateCamera(CameraUpdate var1) {
      try {
         this.zzblx.animateCamera(var1.zzwe());
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public final void animateCamera(CameraUpdate var1, GoogleMap.CancelableCallback var2) {
      try {
         this.zzblx.animateCameraWithCallback(var1.zzwe(), var2 == null ? null : new GoogleMap.zza(var2));
      } catch (RemoteException var4) {
         throw new RuntimeRemoteException(var4);
      }
   }

   public final void animateCamera(CameraUpdate var1, int var2, GoogleMap.CancelableCallback var3) {
      try {
         this.zzblx.animateCameraWithDurationAndCallback(var1.zzwe(), var2, var3 == null ? null : new GoogleMap.zza(var3));
      } catch (RemoteException var5) {
         throw new RuntimeRemoteException(var5);
      }
   }

   public final void stopAnimation() {
      try {
         this.zzblx.stopAnimation();
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public final Polyline addPolyline(PolylineOptions var1) {
      try {
         return new Polyline(this.zzblx.addPolyline(var1));
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public final Polygon addPolygon(PolygonOptions var1) {
      try {
         return new Polygon(this.zzblx.addPolygon(var1));
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public final Circle addCircle(CircleOptions var1) {
      try {
         return new Circle(this.zzblx.addCircle(var1));
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public final Marker addMarker(MarkerOptions var1) {
      try {
         com.google.android.gms.maps.model.internal.zzp var2;
         return (var2 = this.zzblx.addMarker(var1)) != null ? new Marker(var2) : null;
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public final GroundOverlay addGroundOverlay(GroundOverlayOptions var1) {
      try {
         com.google.android.gms.maps.model.internal.zzg var2;
         return (var2 = this.zzblx.addGroundOverlay(var1)) != null ? new GroundOverlay(var2) : null;
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public final TileOverlay addTileOverlay(TileOverlayOptions var1) {
      try {
         com.google.android.gms.maps.model.internal.zzw var2;
         return (var2 = this.zzblx.addTileOverlay(var1)) != null ? new TileOverlay(var2) : null;
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public final void clear() {
      try {
         this.zzblx.clear();
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public final IndoorBuilding getFocusedBuilding() {
      try {
         com.google.android.gms.maps.model.internal.zzj var1;
         return (var1 = this.zzblx.getFocusedBuilding()) != null ? new IndoorBuilding(var1) : null;
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public final void setOnIndoorStateChangeListener(GoogleMap.OnIndoorStateChangeListener var1) {
      try {
         if (var1 == null) {
            this.zzblx.setOnIndoorStateChangeListener((com.google.android.gms.maps.internal.zzz)null);
         } else {
            this.zzblx.setOnIndoorStateChangeListener(new zza(this, var1));
         }
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public final int getMapType() {
      try {
         return this.zzblx.getMapType();
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public final void setMapType(int var1) {
      try {
         this.zzblx.setMapType(var1);
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public final boolean isTrafficEnabled() {
      try {
         return this.zzblx.isTrafficEnabled();
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public final void setTrafficEnabled(boolean var1) {
      try {
         this.zzblx.setTrafficEnabled(var1);
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public final boolean isIndoorEnabled() {
      try {
         return this.zzblx.isIndoorEnabled();
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public final boolean setIndoorEnabled(boolean var1) {
      try {
         return this.zzblx.setIndoorEnabled(var1);
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public final boolean isBuildingsEnabled() {
      try {
         return this.zzblx.isBuildingsEnabled();
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public final void setBuildingsEnabled(boolean var1) {
      try {
         this.zzblx.setBuildingsEnabled(var1);
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public final boolean isMyLocationEnabled() {
      try {
         return this.zzblx.isMyLocationEnabled();
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   @RequiresPermission(
      anyOf = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"}
   )
   public final void setMyLocationEnabled(boolean var1) {
      try {
         this.zzblx.setMyLocationEnabled(var1);
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   /** @deprecated */
   @Deprecated
   public final Location getMyLocation() {
      try {
         return this.zzblx.getMyLocation();
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public final void setLocationSource(LocationSource var1) {
      try {
         if (var1 == null) {
            this.zzblx.setLocationSource((ILocationSourceDelegate)null);
         } else {
            this.zzblx.setLocationSource(new zzl(this, var1));
         }
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public final UiSettings getUiSettings() {
      try {
         if (this.zzbly == null) {
            this.zzbly = new UiSettings(this.zzblx.getUiSettings());
         }

         return this.zzbly;
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public final Projection getProjection() {
      try {
         return new Projection(this.zzblx.getProjection());
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   /** @deprecated */
   @Deprecated
   public final void setOnCameraChangeListener(GoogleMap.OnCameraChangeListener var1) {
      try {
         if (var1 == null) {
            this.zzblx.setOnCameraChangeListener((com.google.android.gms.maps.internal.zzl)null);
         } else {
            this.zzblx.setOnCameraChangeListener(new zzs(this, var1));
         }
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public final void setOnCameraMoveStartedListener(GoogleMap.OnCameraMoveStartedListener var1) {
      try {
         if (var1 == null) {
            this.zzblx.setOnCameraMoveStartedListener((com.google.android.gms.maps.internal.zzt)null);
         } else {
            this.zzblx.setOnCameraMoveStartedListener(new zzt(this, var1));
         }
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public final void setOnCameraMoveListener(GoogleMap.OnCameraMoveListener var1) {
      try {
         if (var1 == null) {
            this.zzblx.setOnCameraMoveListener((com.google.android.gms.maps.internal.zzr)null);
         } else {
            this.zzblx.setOnCameraMoveListener(new zzu(this, var1));
         }
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public final void setOnCameraMoveCanceledListener(GoogleMap.OnCameraMoveCanceledListener var1) {
      try {
         if (var1 == null) {
            this.zzblx.setOnCameraMoveCanceledListener((com.google.android.gms.maps.internal.zzp)null);
         } else {
            this.zzblx.setOnCameraMoveCanceledListener(new zzv(this, var1));
         }
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public final void setOnCameraIdleListener(GoogleMap.OnCameraIdleListener var1) {
      try {
         if (var1 == null) {
            this.zzblx.setOnCameraIdleListener((com.google.android.gms.maps.internal.zzn)null);
         } else {
            this.zzblx.setOnCameraIdleListener(new zzw(this, var1));
         }
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public final void setOnMapClickListener(GoogleMap.OnMapClickListener var1) {
      try {
         if (var1 == null) {
            this.zzblx.setOnMapClickListener((com.google.android.gms.maps.internal.zzaj)null);
         } else {
            this.zzblx.setOnMapClickListener(new zzx(this, var1));
         }
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public final void setOnMapLongClickListener(GoogleMap.OnMapLongClickListener var1) {
      try {
         if (var1 == null) {
            this.zzblx.setOnMapLongClickListener((zzan)null);
         } else {
            this.zzblx.setOnMapLongClickListener(new zzy(this, var1));
         }
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public final void setOnMarkerClickListener(GoogleMap.OnMarkerClickListener var1) {
      try {
         if (var1 == null) {
            this.zzblx.setOnMarkerClickListener((zzar)null);
         } else {
            this.zzblx.setOnMarkerClickListener(new zzb(this, var1));
         }
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public final void setOnMarkerDragListener(GoogleMap.OnMarkerDragListener var1) {
      try {
         if (var1 == null) {
            this.zzblx.setOnMarkerDragListener((zzat)null);
         } else {
            this.zzblx.setOnMarkerDragListener(new zzc(this, var1));
         }
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public final void setOnInfoWindowClickListener(GoogleMap.OnInfoWindowClickListener var1) {
      try {
         if (var1 == null) {
            this.zzblx.setOnInfoWindowClickListener((com.google.android.gms.maps.internal.zzab)null);
         } else {
            this.zzblx.setOnInfoWindowClickListener(new zzd(this, var1));
         }
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public final void setOnInfoWindowLongClickListener(GoogleMap.OnInfoWindowLongClickListener var1) {
      try {
         if (var1 == null) {
            this.zzblx.setOnInfoWindowLongClickListener((com.google.android.gms.maps.internal.zzaf)null);
         } else {
            this.zzblx.setOnInfoWindowLongClickListener(new zze(this, var1));
         }
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public final void setOnInfoWindowCloseListener(GoogleMap.OnInfoWindowCloseListener var1) {
      try {
         if (var1 == null) {
            this.zzblx.setOnInfoWindowCloseListener((com.google.android.gms.maps.internal.zzad)null);
         } else {
            this.zzblx.setOnInfoWindowCloseListener(new zzf(this, var1));
         }
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public final void setInfoWindowAdapter(GoogleMap.InfoWindowAdapter var1) {
      try {
         if (var1 == null) {
            this.zzblx.setInfoWindowAdapter((com.google.android.gms.maps.internal.zzh)null);
         } else {
            this.zzblx.setInfoWindowAdapter(new zzg(this, var1));
         }
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   /** @deprecated */
   @Deprecated
   public final void setOnMyLocationChangeListener(GoogleMap.OnMyLocationChangeListener var1) {
      try {
         if (var1 == null) {
            this.zzblx.setOnMyLocationChangeListener((zzax)null);
         } else {
            this.zzblx.setOnMyLocationChangeListener(new zzh(this, var1));
         }
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public final void setOnMyLocationButtonClickListener(GoogleMap.OnMyLocationButtonClickListener var1) {
      try {
         if (var1 == null) {
            this.zzblx.setOnMyLocationButtonClickListener((zzav)null);
         } else {
            this.zzblx.setOnMyLocationButtonClickListener(new zzi(this, var1));
         }
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public final void setOnMapLoadedCallback(GoogleMap.OnMapLoadedCallback var1) {
      try {
         if (var1 == null) {
            this.zzblx.setOnMapLoadedCallback((zzal)null);
         } else {
            this.zzblx.setOnMapLoadedCallback(new zzj(this, var1));
         }
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public final void setOnGroundOverlayClickListener(GoogleMap.OnGroundOverlayClickListener var1) {
      try {
         if (var1 == null) {
            this.zzblx.setOnGroundOverlayClickListener((com.google.android.gms.maps.internal.zzx)null);
         } else {
            this.zzblx.setOnGroundOverlayClickListener(new zzk(this, var1));
         }
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public final void setOnCircleClickListener(GoogleMap.OnCircleClickListener var1) {
      try {
         if (var1 == null) {
            this.zzblx.setOnCircleClickListener((com.google.android.gms.maps.internal.zzv)null);
         } else {
            this.zzblx.setOnCircleClickListener(new zzn(this, var1));
         }
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public final void setOnPolygonClickListener(GoogleMap.OnPolygonClickListener var1) {
      try {
         if (var1 == null) {
            this.zzblx.setOnPolygonClickListener((zzbb)null);
         } else {
            this.zzblx.setOnPolygonClickListener(new zzo(this, var1));
         }
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public final void setOnPolylineClickListener(GoogleMap.OnPolylineClickListener var1) {
      try {
         if (var1 == null) {
            this.zzblx.setOnPolylineClickListener((zzbd)null);
         } else {
            this.zzblx.setOnPolylineClickListener(new zzp(this, var1));
         }
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public final void snapshot(GoogleMap.SnapshotReadyCallback var1) {
      this.snapshot(var1, (Bitmap)null);
   }

   public final void snapshot(GoogleMap.SnapshotReadyCallback var1, Bitmap var2) {
      com.google.android.gms.dynamic.zzn var3 = (com.google.android.gms.dynamic.zzn)(var2 != null ? com.google.android.gms.dynamic.zzn.zzw(var2) : null);

      try {
         this.zzblx.snapshot(new zzq(this, var1), var3);
      } catch (RemoteException var5) {
         throw new RuntimeRemoteException(var5);
      }
   }

   public final void setPadding(int var1, int var2, int var3, int var4) {
      try {
         this.zzblx.setPadding(var1, var2, var3, var4);
      } catch (RemoteException var6) {
         throw new RuntimeRemoteException(var6);
      }
   }

   public final void setContentDescription(String var1) {
      try {
         this.zzblx.setContentDescription(var1);
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public final void setOnPoiClickListener(GoogleMap.OnPoiClickListener var1) {
      try {
         if (var1 == null) {
            this.zzblx.setOnPoiClickListener((zzaz)null);
         } else {
            this.zzblx.setOnPoiClickListener(new zzr(this, var1));
         }
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public final boolean setMapStyle(MapStyleOptions var1) {
      try {
         return this.zzblx.setMapStyle(var1);
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public final void setMinZoomPreference(float var1) {
      try {
         this.zzblx.setMinZoomPreference(var1);
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public final void setMaxZoomPreference(float var1) {
      try {
         this.zzblx.setMaxZoomPreference(var1);
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public final void resetMinMaxZoomPreference() {
      try {
         this.zzblx.resetMinMaxZoomPreference();
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public final void setLatLngBoundsForCameraTarget(LatLngBounds var1) {
      try {
         this.zzblx.setLatLngBoundsForCameraTarget(var1);
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }

   public interface OnPoiClickListener {
      void onPoiClick(PointOfInterest var1);
   }

   static final class zza extends com.google.android.gms.maps.internal.zzd {
      private final GoogleMap.CancelableCallback zzblY;

      zza(GoogleMap.CancelableCallback var1) {
         this.zzblY = var1;
      }

      public final void onFinish() {
         this.zzblY.onFinish();
      }

      public final void onCancel() {
         this.zzblY.onCancel();
      }
   }

   public interface OnGroundOverlayClickListener {
      void onGroundOverlayClick(GroundOverlay var1);
   }

   public interface OnMapLoadedCallback {
      void onMapLoaded();
   }

   public interface OnMyLocationButtonClickListener {
      boolean onMyLocationButtonClick();
   }

   /** @deprecated */
   @Deprecated
   public interface OnMyLocationChangeListener {
      void onMyLocationChange(Location var1);
   }

   public interface InfoWindowAdapter {
      View getInfoWindow(Marker var1);

      View getInfoContents(Marker var1);
   }

   public interface SnapshotReadyCallback {
      void onSnapshotReady(Bitmap var1);
   }

   public interface CancelableCallback {
      void onFinish();

      void onCancel();
   }

   public interface OnInfoWindowCloseListener {
      void onInfoWindowClose(Marker var1);
   }

   public interface OnInfoWindowLongClickListener {
      void onInfoWindowLongClick(Marker var1);
   }

   public interface OnInfoWindowClickListener {
      void onInfoWindowClick(Marker var1);
   }

   public interface OnMarkerDragListener {
      void onMarkerDragStart(Marker var1);

      void onMarkerDrag(Marker var1);

      void onMarkerDragEnd(Marker var1);
   }

   public interface OnMarkerClickListener {
      boolean onMarkerClick(Marker var1);
   }

   public interface OnPolylineClickListener {
      void onPolylineClick(Polyline var1);
   }

   public interface OnPolygonClickListener {
      void onPolygonClick(Polygon var1);
   }

   public interface OnCircleClickListener {
      void onCircleClick(Circle var1);
   }

   /** @deprecated */
   @Deprecated
   public interface OnCameraChangeListener {
      void onCameraChange(CameraPosition var1);
   }

   public interface OnCameraIdleListener {
      void onCameraIdle();
   }

   public interface OnCameraMoveCanceledListener {
      void onCameraMoveCanceled();
   }

   public interface OnCameraMoveListener {
      void onCameraMove();
   }

   public interface OnCameraMoveStartedListener {
      int REASON_GESTURE = 1;
      int REASON_API_ANIMATION = 2;
      int REASON_DEVELOPER_ANIMATION = 3;

      void onCameraMoveStarted(int var1);
   }

   public interface OnMapLongClickListener {
      void onMapLongClick(LatLng var1);
   }

   public interface OnMapClickListener {
      void onMapClick(LatLng var1);
   }

   public interface OnIndoorStateChangeListener {
      void onIndoorBuildingFocused();

      void onIndoorLevelActivated(IndoorBuilding var1);
   }
}
