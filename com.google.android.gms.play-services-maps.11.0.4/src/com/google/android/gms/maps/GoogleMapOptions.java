package com.google.android.gms.maps;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.util.AttributeSet;
import com.google.android.gms.R.styleable;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbe;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLngBounds;

public final class GoogleMapOptions extends com.google.android.gms.common.internal.safeparcel.zza implements ReflectedParcelable {
   public static final Creator CREATOR = new zzz();
   private Boolean zzblZ;
   private Boolean zzbma;
   private int zzbmb = -1;
   private CameraPosition zzbmc;
   private Boolean zzbmd;
   private Boolean zzbme;
   private Boolean zzbmf;
   private Boolean zzbmg;
   private Boolean zzbmh;
   private Boolean zzbmi;
   private Boolean zzbmj;
   private Boolean zzbmk;
   private Boolean zzbml;
   private Float zzbmm = null;
   private Float zzbmn = null;
   private LatLngBounds zzbmo = null;

   GoogleMapOptions(byte var1, byte var2, int var3, CameraPosition var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, byte var11, byte var12, byte var13, Float var14, Float var15, LatLngBounds var16) {
      this.zzblZ = com.google.android.gms.maps.internal.zza.zza(var1);
      this.zzbma = com.google.android.gms.maps.internal.zza.zza(var2);
      this.zzbmb = var3;
      this.zzbmc = var4;
      this.zzbmd = com.google.android.gms.maps.internal.zza.zza(var5);
      this.zzbme = com.google.android.gms.maps.internal.zza.zza(var6);
      this.zzbmf = com.google.android.gms.maps.internal.zza.zza(var7);
      this.zzbmg = com.google.android.gms.maps.internal.zza.zza(var8);
      this.zzbmh = com.google.android.gms.maps.internal.zza.zza(var9);
      this.zzbmi = com.google.android.gms.maps.internal.zza.zza(var10);
      this.zzbmj = com.google.android.gms.maps.internal.zza.zza(var11);
      this.zzbmk = com.google.android.gms.maps.internal.zza.zza(var12);
      this.zzbml = com.google.android.gms.maps.internal.zza.zza(var13);
      this.zzbmm = var14;
      this.zzbmn = var15;
      this.zzbmo = var16;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, com.google.android.gms.maps.internal.zza.zzb(this.zzblZ));
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, com.google.android.gms.maps.internal.zza.zzb(this.zzbma));
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 4, this.getMapType());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 5, this.getCamera(), var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 6, com.google.android.gms.maps.internal.zza.zzb(this.zzbmd));
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 7, com.google.android.gms.maps.internal.zza.zzb(this.zzbme));
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 8, com.google.android.gms.maps.internal.zza.zzb(this.zzbmf));
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 9, com.google.android.gms.maps.internal.zza.zzb(this.zzbmg));
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 10, com.google.android.gms.maps.internal.zza.zzb(this.zzbmh));
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 11, com.google.android.gms.maps.internal.zza.zzb(this.zzbmi));
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 12, com.google.android.gms.maps.internal.zza.zzb(this.zzbmj));
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 14, com.google.android.gms.maps.internal.zza.zzb(this.zzbmk));
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 15, com.google.android.gms.maps.internal.zza.zzb(this.zzbml));
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 16, this.getMinZoomPreference(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 17, this.getMaxZoomPreference(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 18, this.getLatLngBoundsForCameraTarget(), var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public GoogleMapOptions() {
   }

   public final GoogleMapOptions zOrderOnTop(boolean var1) {
      this.zzblZ = var1;
      return this;
   }

   public final GoogleMapOptions useViewLifecycleInFragment(boolean var1) {
      this.zzbma = var1;
      return this;
   }

   public final GoogleMapOptions mapType(int var1) {
      this.zzbmb = var1;
      return this;
   }

   public final GoogleMapOptions camera(CameraPosition var1) {
      this.zzbmc = var1;
      return this;
   }

   public final GoogleMapOptions zoomControlsEnabled(boolean var1) {
      this.zzbmd = var1;
      return this;
   }

   public final GoogleMapOptions compassEnabled(boolean var1) {
      this.zzbme = var1;
      return this;
   }

   public final GoogleMapOptions scrollGesturesEnabled(boolean var1) {
      this.zzbmf = var1;
      return this;
   }

   public final GoogleMapOptions zoomGesturesEnabled(boolean var1) {
      this.zzbmg = var1;
      return this;
   }

   public final GoogleMapOptions tiltGesturesEnabled(boolean var1) {
      this.zzbmh = var1;
      return this;
   }

   public final GoogleMapOptions rotateGesturesEnabled(boolean var1) {
      this.zzbmi = var1;
      return this;
   }

   public final GoogleMapOptions liteMode(boolean var1) {
      this.zzbmj = var1;
      return this;
   }

   public final GoogleMapOptions mapToolbarEnabled(boolean var1) {
      this.zzbmk = var1;
      return this;
   }

   public final GoogleMapOptions ambientEnabled(boolean var1) {
      this.zzbml = var1;
      return this;
   }

   public final GoogleMapOptions minZoomPreference(float var1) {
      this.zzbmm = var1;
      return this;
   }

   public final GoogleMapOptions maxZoomPreference(float var1) {
      this.zzbmn = var1;
      return this;
   }

   public final GoogleMapOptions latLngBoundsForCameraTarget(LatLngBounds var1) {
      this.zzbmo = var1;
      return this;
   }

   public final Boolean getZOrderOnTop() {
      return this.zzblZ;
   }

   public final Boolean getUseViewLifecycleInFragment() {
      return this.zzbma;
   }

   public final int getMapType() {
      return this.zzbmb;
   }

   public final CameraPosition getCamera() {
      return this.zzbmc;
   }

   public final Boolean getZoomControlsEnabled() {
      return this.zzbmd;
   }

   public final Boolean getCompassEnabled() {
      return this.zzbme;
   }

   public final Boolean getScrollGesturesEnabled() {
      return this.zzbmf;
   }

   public final Boolean getZoomGesturesEnabled() {
      return this.zzbmg;
   }

   public final Boolean getTiltGesturesEnabled() {
      return this.zzbmh;
   }

   public final Boolean getRotateGesturesEnabled() {
      return this.zzbmi;
   }

   public final Boolean getLiteMode() {
      return this.zzbmj;
   }

   public final Boolean getMapToolbarEnabled() {
      return this.zzbmk;
   }

   public final Boolean getAmbientEnabled() {
      return this.zzbml;
   }

   public final Float getMinZoomPreference() {
      return this.zzbmm;
   }

   public final Float getMaxZoomPreference() {
      return this.zzbmn;
   }

   public final LatLngBounds getLatLngBoundsForCameraTarget() {
      return this.zzbmo;
   }

   public static GoogleMapOptions createFromAttributes(Context var0, AttributeSet var1) {
      if (var1 == null) {
         return null;
      } else {
         TypedArray var2 = var0.getResources().obtainAttributes(var1, styleable.MapAttrs);
         GoogleMapOptions var3 = new GoogleMapOptions();
         if (var2.hasValue(styleable.MapAttrs_mapType)) {
            var3.mapType(var2.getInt(styleable.MapAttrs_mapType, -1));
         }

         if (var2.hasValue(styleable.MapAttrs_zOrderOnTop)) {
            var3.zOrderOnTop(var2.getBoolean(styleable.MapAttrs_zOrderOnTop, false));
         }

         if (var2.hasValue(styleable.MapAttrs_useViewLifecycle)) {
            var3.useViewLifecycleInFragment(var2.getBoolean(styleable.MapAttrs_useViewLifecycle, false));
         }

         if (var2.hasValue(styleable.MapAttrs_uiCompass)) {
            var3.compassEnabled(var2.getBoolean(styleable.MapAttrs_uiCompass, true));
         }

         if (var2.hasValue(styleable.MapAttrs_uiRotateGestures)) {
            var3.rotateGesturesEnabled(var2.getBoolean(styleable.MapAttrs_uiRotateGestures, true));
         }

         if (var2.hasValue(styleable.MapAttrs_uiScrollGestures)) {
            var3.scrollGesturesEnabled(var2.getBoolean(styleable.MapAttrs_uiScrollGestures, true));
         }

         if (var2.hasValue(styleable.MapAttrs_uiTiltGestures)) {
            var3.tiltGesturesEnabled(var2.getBoolean(styleable.MapAttrs_uiTiltGestures, true));
         }

         if (var2.hasValue(styleable.MapAttrs_uiZoomGestures)) {
            var3.zoomGesturesEnabled(var2.getBoolean(styleable.MapAttrs_uiZoomGestures, true));
         }

         if (var2.hasValue(styleable.MapAttrs_uiZoomControls)) {
            var3.zoomControlsEnabled(var2.getBoolean(styleable.MapAttrs_uiZoomControls, true));
         }

         if (var2.hasValue(styleable.MapAttrs_liteMode)) {
            var3.liteMode(var2.getBoolean(styleable.MapAttrs_liteMode, false));
         }

         if (var2.hasValue(styleable.MapAttrs_uiMapToolbar)) {
            var3.mapToolbarEnabled(var2.getBoolean(styleable.MapAttrs_uiMapToolbar, true));
         }

         if (var2.hasValue(styleable.MapAttrs_ambientEnabled)) {
            var3.ambientEnabled(var2.getBoolean(styleable.MapAttrs_ambientEnabled, false));
         }

         if (var2.hasValue(styleable.MapAttrs_cameraMinZoomPreference)) {
            var3.minZoomPreference(var2.getFloat(styleable.MapAttrs_cameraMinZoomPreference, Float.NEGATIVE_INFINITY));
         }

         if (var2.hasValue(styleable.MapAttrs_cameraMinZoomPreference)) {
            var3.maxZoomPreference(var2.getFloat(styleable.MapAttrs_cameraMaxZoomPreference, Float.POSITIVE_INFINITY));
         }

         LatLngBounds var4 = LatLngBounds.createFromAttributes(var0, var1);
         var3.latLngBoundsForCameraTarget(var4);
         CameraPosition var5 = CameraPosition.createFromAttributes(var0, var1);
         var3.camera(var5);
         var2.recycle();
         return var3;
      }
   }

   public final String toString() {
      return zzbe.zzt(this).zzg("MapType", this.zzbmb).zzg("LiteMode", this.zzbmj).zzg("Camera", this.zzbmc).zzg("CompassEnabled", this.zzbme).zzg("ZoomControlsEnabled", this.zzbmd).zzg("ScrollGesturesEnabled", this.zzbmf).zzg("ZoomGesturesEnabled", this.zzbmg).zzg("TiltGesturesEnabled", this.zzbmh).zzg("RotateGesturesEnabled", this.zzbmi).zzg("MapToolbarEnabled", this.zzbmk).zzg("AmbientEnabled", this.zzbml).zzg("MinZoomPreference", this.zzbmm).zzg("MaxZoomPreference", this.zzbmn).zzg("LatLngBoundsForCameraTarget", this.zzbmo).zzg("ZOrderOnTop", this.zzblZ).zzg("UseViewLifecycleInFragment", this.zzbma).toString();
   }
}
