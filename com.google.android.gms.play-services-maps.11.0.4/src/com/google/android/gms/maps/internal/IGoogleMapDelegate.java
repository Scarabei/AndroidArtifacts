package com.google.android.gms.maps.internal;

import android.location.Location;
import android.os.Bundle;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
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

public interface IGoogleMapDelegate extends IInterface {
   CameraPosition getCameraPosition() throws RemoteException;

   float getMaxZoomLevel() throws RemoteException;

   float getMinZoomLevel() throws RemoteException;

   void moveCamera(IObjectWrapper var1) throws RemoteException;

   void animateCamera(IObjectWrapper var1) throws RemoteException;

   void animateCameraWithCallback(IObjectWrapper var1, zzc var2) throws RemoteException;

   void animateCameraWithDurationAndCallback(IObjectWrapper var1, int var2, zzc var3) throws RemoteException;

   void stopAnimation() throws RemoteException;

   IPolylineDelegate addPolyline(PolylineOptions var1) throws RemoteException;

   com.google.android.gms.maps.model.internal.zzs addPolygon(PolygonOptions var1) throws RemoteException;

   com.google.android.gms.maps.model.internal.zzp addMarker(MarkerOptions var1) throws RemoteException;

   com.google.android.gms.maps.model.internal.zzg addGroundOverlay(GroundOverlayOptions var1) throws RemoteException;

   com.google.android.gms.maps.model.internal.zzw addTileOverlay(TileOverlayOptions var1) throws RemoteException;

   void clear() throws RemoteException;

   int getMapType() throws RemoteException;

   void setMapType(int var1) throws RemoteException;

   boolean isTrafficEnabled() throws RemoteException;

   void setTrafficEnabled(boolean var1) throws RemoteException;

   boolean isIndoorEnabled() throws RemoteException;

   boolean setIndoorEnabled(boolean var1) throws RemoteException;

   boolean isMyLocationEnabled() throws RemoteException;

   void setMyLocationEnabled(boolean var1) throws RemoteException;

   Location getMyLocation() throws RemoteException;

   void setLocationSource(ILocationSourceDelegate var1) throws RemoteException;

   IUiSettingsDelegate getUiSettings() throws RemoteException;

   IProjectionDelegate getProjection() throws RemoteException;

   void setOnCameraChangeListener(zzl var1) throws RemoteException;

   void setOnMapClickListener(zzaj var1) throws RemoteException;

   void setOnMapLongClickListener(zzan var1) throws RemoteException;

   void setOnMarkerClickListener(zzar var1) throws RemoteException;

   void setOnMarkerDragListener(zzat var1) throws RemoteException;

   void setOnInfoWindowClickListener(zzab var1) throws RemoteException;

   void setInfoWindowAdapter(zzh var1) throws RemoteException;

   com.google.android.gms.maps.model.internal.zzd addCircle(CircleOptions var1) throws RemoteException;

   void setOnMyLocationChangeListener(zzax var1) throws RemoteException;

   void setOnMyLocationButtonClickListener(zzav var1) throws RemoteException;

   void snapshot(zzbq var1, IObjectWrapper var2) throws RemoteException;

   void setPadding(int var1, int var2, int var3, int var4) throws RemoteException;

   boolean isBuildingsEnabled() throws RemoteException;

   void setBuildingsEnabled(boolean var1) throws RemoteException;

   void setOnMapLoadedCallback(zzal var1) throws RemoteException;

   com.google.android.gms.maps.model.internal.zzj getFocusedBuilding() throws RemoteException;

   void setOnIndoorStateChangeListener(zzz var1) throws RemoteException;

   void setWatermarkEnabled(boolean var1) throws RemoteException;

   void getMapAsync(zzap var1) throws RemoteException;

   void onCreate(Bundle var1) throws RemoteException;

   void onResume() throws RemoteException;

   void onPause() throws RemoteException;

   void onDestroy() throws RemoteException;

   void onLowMemory() throws RemoteException;

   boolean useViewLifecycleWhenInFragment() throws RemoteException;

   void onSaveInstanceState(Bundle var1) throws RemoteException;

   void setContentDescription(String var1) throws RemoteException;

   void snapshotForTest(zzbq var1) throws RemoteException;

   void setOnPoiClickListener(zzaz var1) throws RemoteException;

   void onEnterAmbient(Bundle var1) throws RemoteException;

   void onExitAmbient() throws RemoteException;

   void setOnGroundOverlayClickListener(zzx var1) throws RemoteException;

   void setOnInfoWindowLongClickListener(zzaf var1) throws RemoteException;

   void setOnPolygonClickListener(zzbb var1) throws RemoteException;

   void setOnInfoWindowCloseListener(zzad var1) throws RemoteException;

   void setOnPolylineClickListener(zzbd var1) throws RemoteException;

   void setOnCircleClickListener(zzv var1) throws RemoteException;

   void setMinZoomPreference(float var1) throws RemoteException;

   void setMaxZoomPreference(float var1) throws RemoteException;

   void resetMinMaxZoomPreference() throws RemoteException;

   void setLatLngBoundsForCameraTarget(LatLngBounds var1) throws RemoteException;

   void setOnCameraMoveStartedListener(zzt var1) throws RemoteException;

   void setOnCameraMoveListener(zzr var1) throws RemoteException;

   void setOnCameraMoveCanceledListener(zzp var1) throws RemoteException;

   void setOnCameraIdleListener(zzn var1) throws RemoteException;

   boolean setMapStyle(MapStyleOptions var1) throws RemoteException;

   void onStart() throws RemoteException;

   void onStop() throws RemoteException;
}
