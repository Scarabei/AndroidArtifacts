package com.google.android.gms.maps.internal;

import android.location.Location;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzed;
import com.google.android.gms.internal.zzef;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.android.gms.maps.model.internal.IPolylineDelegate;

public final class zzg extends zzed implements IGoogleMapDelegate {
   zzg(IBinder var1) {
      super(var1, "com.google.android.gms.maps.internal.IGoogleMapDelegate");
   }

   public final CameraPosition getCameraPosition() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      CameraPosition var3 = (CameraPosition)zzef.zza(var2 = this.zza(1, var1), CameraPosition.CREATOR);
      var2.recycle();
      return var3;
   }

   public final float getMaxZoomLevel() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      float var3 = (var2 = this.zza(2, var1)).readFloat();
      var2.recycle();
      return var3;
   }

   public final float getMinZoomLevel() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      float var3 = (var2 = this.zza(3, var1)).readFloat();
      var2.recycle();
      return var3;
   }

   public final void moveCamera(IObjectWrapper var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(4, var2);
   }

   public final void animateCamera(IObjectWrapper var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(5, var2);
   }

   public final void animateCameraWithCallback(IObjectWrapper var1, zzc var2) throws RemoteException {
      Parcel var3;
      zzef.zza(var3 = this.zzZ(), var1);
      zzef.zza(var3, var2);
      this.zzb(6, var3);
   }

   public final void animateCameraWithDurationAndCallback(IObjectWrapper var1, int var2, zzc var3) throws RemoteException {
      Parcel var4;
      zzef.zza(var4 = this.zzZ(), var1);
      var4.writeInt(var2);
      zzef.zza(var4, var3);
      this.zzb(7, var4);
   }

   public final void stopAnimation() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzb(8, var1);
   }

   public final IPolylineDelegate addPolyline(PolylineOptions var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      Parcel var3;
      IPolylineDelegate var4 = IPolylineDelegate.zza.zzah((var3 = this.zza(9, var2)).readStrongBinder());
      var3.recycle();
      return var4;
   }

   public final com.google.android.gms.maps.model.internal.zzs addPolygon(PolygonOptions var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      Parcel var3;
      com.google.android.gms.maps.model.internal.zzs var4 = com.google.android.gms.maps.model.internal.zzt.zzag((var3 = this.zza(10, var2)).readStrongBinder());
      var3.recycle();
      return var4;
   }

   public final com.google.android.gms.maps.model.internal.zzp addMarker(MarkerOptions var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      Parcel var3;
      com.google.android.gms.maps.model.internal.zzp var4 = com.google.android.gms.maps.model.internal.zzq.zzaf((var3 = this.zza(11, var2)).readStrongBinder());
      var3.recycle();
      return var4;
   }

   public final com.google.android.gms.maps.model.internal.zzg addGroundOverlay(GroundOverlayOptions var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      Parcel var3;
      com.google.android.gms.maps.model.internal.zzg var4 = com.google.android.gms.maps.model.internal.zzh.zzac((var3 = this.zza(12, var2)).readStrongBinder());
      var3.recycle();
      return var4;
   }

   public final com.google.android.gms.maps.model.internal.zzw addTileOverlay(TileOverlayOptions var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      Parcel var3;
      com.google.android.gms.maps.model.internal.zzw var4 = com.google.android.gms.maps.model.internal.zzx.zzai((var3 = this.zza(13, var2)).readStrongBinder());
      var3.recycle();
      return var4;
   }

   public final void clear() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzb(14, var1);
   }

   public final int getMapType() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      int var3 = (var2 = this.zza(15, var1)).readInt();
      var2.recycle();
      return var3;
   }

   public final void setMapType(int var1) throws RemoteException {
      Parcel var2;
      (var2 = this.zzZ()).writeInt(var1);
      this.zzb(16, var2);
   }

   public final boolean isTrafficEnabled() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      boolean var3 = zzef.zza(var2 = this.zza(17, var1));
      var2.recycle();
      return var3;
   }

   public final void setTrafficEnabled(boolean var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(18, var2);
   }

   public final boolean isIndoorEnabled() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      boolean var3 = zzef.zza(var2 = this.zza(19, var1));
      var2.recycle();
      return var3;
   }

   public final boolean setIndoorEnabled(boolean var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      Parcel var3;
      boolean var4 = zzef.zza(var3 = this.zza(20, var2));
      var3.recycle();
      return var4;
   }

   public final boolean isMyLocationEnabled() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      boolean var3 = zzef.zza(var2 = this.zza(21, var1));
      var2.recycle();
      return var3;
   }

   public final void setMyLocationEnabled(boolean var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(22, var2);
   }

   public final Location getMyLocation() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      Location var3 = (Location)zzef.zza(var2 = this.zza(23, var1), Location.CREATOR);
      var2.recycle();
      return var3;
   }

   public final void setLocationSource(ILocationSourceDelegate var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(24, var2);
   }

   public final IUiSettingsDelegate getUiSettings() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      IBinder var4;
      IInterface var5;
      Object var3 = (var4 = (var2 = this.zza(25, var1)).readStrongBinder()) == null ? null : ((var5 = var4.queryLocalInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate")) instanceof IUiSettingsDelegate ? (IUiSettingsDelegate)var5 : new zzbv(var4));
      var2.recycle();
      return (IUiSettingsDelegate)var3;
   }

   public final IProjectionDelegate getProjection() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      IBinder var4;
      IInterface var5;
      Object var3 = (var4 = (var2 = this.zza(26, var1)).readStrongBinder()) == null ? null : ((var5 = var4.queryLocalInterface("com.google.android.gms.maps.internal.IProjectionDelegate")) instanceof IProjectionDelegate ? (IProjectionDelegate)var5 : new zzbp(var4));
      var2.recycle();
      return (IProjectionDelegate)var3;
   }

   public final void setOnCameraChangeListener(zzl var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(27, var2);
   }

   public final void setOnMapClickListener(zzaj var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(28, var2);
   }

   public final void setOnMapLongClickListener(zzan var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(29, var2);
   }

   public final void setOnMarkerClickListener(zzar var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(30, var2);
   }

   public final void setOnMarkerDragListener(zzat var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(31, var2);
   }

   public final void setOnInfoWindowClickListener(zzab var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(32, var2);
   }

   public final void setInfoWindowAdapter(zzh var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(33, var2);
   }

   public final com.google.android.gms.maps.model.internal.zzd addCircle(CircleOptions var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      Parcel var3;
      com.google.android.gms.maps.model.internal.zzd var4 = com.google.android.gms.maps.model.internal.zze.zzab((var3 = this.zza(35, var2)).readStrongBinder());
      var3.recycle();
      return var4;
   }

   public final void setOnMyLocationChangeListener(zzax var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(36, var2);
   }

   public final void setOnMyLocationButtonClickListener(zzav var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(37, var2);
   }

   public final void snapshot(zzbq var1, IObjectWrapper var2) throws RemoteException {
      Parcel var3;
      zzef.zza(var3 = this.zzZ(), var1);
      zzef.zza(var3, var2);
      this.zzb(38, var3);
   }

   public final void setPadding(int var1, int var2, int var3, int var4) throws RemoteException {
      Parcel var5;
      (var5 = this.zzZ()).writeInt(var1);
      var5.writeInt(var2);
      var5.writeInt(var3);
      var5.writeInt(var4);
      this.zzb(39, var5);
   }

   public final boolean isBuildingsEnabled() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      boolean var3 = zzef.zza(var2 = this.zza(40, var1));
      var2.recycle();
      return var3;
   }

   public final void setBuildingsEnabled(boolean var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(41, var2);
   }

   public final void setOnMapLoadedCallback(zzal var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(42, var2);
   }

   public final com.google.android.gms.maps.model.internal.zzj getFocusedBuilding() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      com.google.android.gms.maps.model.internal.zzj var3 = com.google.android.gms.maps.model.internal.zzk.zzad((var2 = this.zza(44, var1)).readStrongBinder());
      var2.recycle();
      return var3;
   }

   public final void setOnIndoorStateChangeListener(zzz var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(45, var2);
   }

   public final void setWatermarkEnabled(boolean var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(51, var2);
   }

   public final void getMapAsync(zzap var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(53, var2);
   }

   public final void onCreate(Bundle var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(54, var2);
   }

   public final void onResume() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzb(55, var1);
   }

   public final void onPause() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzb(56, var1);
   }

   public final void onDestroy() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzb(57, var1);
   }

   public final void onLowMemory() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzb(58, var1);
   }

   public final boolean useViewLifecycleWhenInFragment() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      boolean var3 = zzef.zza(var2 = this.zza(59, var1));
      var2.recycle();
      return var3;
   }

   public final void onSaveInstanceState(Bundle var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      Parcel var3;
      if ((var3 = this.zza(60, var2)).readInt() != 0) {
         var1.readFromParcel(var3);
      }

      var3.recycle();
   }

   public final void setContentDescription(String var1) throws RemoteException {
      Parcel var2;
      (var2 = this.zzZ()).writeString(var1);
      this.zzb(61, var2);
   }

   public final void snapshotForTest(zzbq var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(71, var2);
   }

   public final void setOnPoiClickListener(zzaz var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(80, var2);
   }

   public final void onEnterAmbient(Bundle var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(81, var2);
   }

   public final void onExitAmbient() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzb(82, var1);
   }

   public final void setOnGroundOverlayClickListener(zzx var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(83, var2);
   }

   public final void setOnInfoWindowLongClickListener(zzaf var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(84, var2);
   }

   public final void setOnPolygonClickListener(zzbb var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(85, var2);
   }

   public final void setOnInfoWindowCloseListener(zzad var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(86, var2);
   }

   public final void setOnPolylineClickListener(zzbd var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(87, var2);
   }

   public final void setOnCircleClickListener(zzv var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(89, var2);
   }

   public final void setMinZoomPreference(float var1) throws RemoteException {
      Parcel var2;
      (var2 = this.zzZ()).writeFloat(var1);
      this.zzb(92, var2);
   }

   public final void setMaxZoomPreference(float var1) throws RemoteException {
      Parcel var2;
      (var2 = this.zzZ()).writeFloat(var1);
      this.zzb(93, var2);
   }

   public final void resetMinMaxZoomPreference() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzb(94, var1);
   }

   public final void setLatLngBoundsForCameraTarget(LatLngBounds var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(95, var2);
   }

   public final void setOnCameraMoveStartedListener(zzt var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(96, var2);
   }

   public final void setOnCameraMoveListener(zzr var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(97, var2);
   }

   public final void setOnCameraMoveCanceledListener(zzp var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(98, var2);
   }

   public final void setOnCameraIdleListener(zzn var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(99, var2);
   }

   public final boolean setMapStyle(MapStyleOptions var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      Parcel var3;
      boolean var4 = zzef.zza(var3 = this.zza(91, var2));
      var3.recycle();
      return var4;
   }

   public final void onStart() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzb(101, var1);
   }

   public final void onStop() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzb(102, var1);
   }
}
