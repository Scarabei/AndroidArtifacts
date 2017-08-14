package com.google.android.gms.maps;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbe;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;

public final class StreetViewPanoramaOptions extends com.google.android.gms.common.internal.safeparcel.zza implements ReflectedParcelable {
   public static final Creator CREATOR = new zzah();
   private StreetViewPanoramaCamera zzbmL;
   private String zzbmM;
   private LatLng zzbmN;
   private Integer zzbmO;
   private Boolean zzbmP = true;
   private Boolean zzbmg = true;
   private Boolean zzbmQ = true;
   private Boolean zzbmR = true;
   private Boolean zzbma;

   StreetViewPanoramaOptions(StreetViewPanoramaCamera var1, String var2, LatLng var3, Integer var4, byte var5, byte var6, byte var7, byte var8, byte var9) {
      this.zzbmL = var1;
      this.zzbmN = var3;
      this.zzbmO = var4;
      this.zzbmM = var2;
      this.zzbmP = com.google.android.gms.maps.internal.zza.zza(var5);
      this.zzbmg = com.google.android.gms.maps.internal.zza.zza(var6);
      this.zzbmQ = com.google.android.gms.maps.internal.zza.zza(var7);
      this.zzbmR = com.google.android.gms.maps.internal.zza.zza(var8);
      this.zzbma = com.google.android.gms.maps.internal.zza.zza(var9);
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.getStreetViewPanoramaCamera(), var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.getPanoramaId(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.getPosition(), var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 5, this.getRadius(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 6, com.google.android.gms.maps.internal.zza.zzb(this.zzbmP));
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 7, com.google.android.gms.maps.internal.zza.zzb(this.zzbmg));
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 8, com.google.android.gms.maps.internal.zza.zzb(this.zzbmQ));
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 9, com.google.android.gms.maps.internal.zza.zzb(this.zzbmR));
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 10, com.google.android.gms.maps.internal.zza.zzb(this.zzbma));
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public StreetViewPanoramaOptions() {
   }

   public final StreetViewPanoramaOptions panoramaCamera(StreetViewPanoramaCamera var1) {
      this.zzbmL = var1;
      return this;
   }

   public final StreetViewPanoramaOptions panoramaId(String var1) {
      this.zzbmM = var1;
      return this;
   }

   public final StreetViewPanoramaOptions position(LatLng var1) {
      this.zzbmN = var1;
      return this;
   }

   public final StreetViewPanoramaOptions position(LatLng var1, Integer var2) {
      this.zzbmN = var1;
      this.zzbmO = var2;
      return this;
   }

   public final StreetViewPanoramaOptions userNavigationEnabled(boolean var1) {
      this.zzbmP = var1;
      return this;
   }

   public final StreetViewPanoramaOptions zoomGesturesEnabled(boolean var1) {
      this.zzbmg = var1;
      return this;
   }

   public final StreetViewPanoramaOptions panningGesturesEnabled(boolean var1) {
      this.zzbmQ = var1;
      return this;
   }

   public final StreetViewPanoramaOptions streetNamesEnabled(boolean var1) {
      this.zzbmR = var1;
      return this;
   }

   public final StreetViewPanoramaOptions useViewLifecycleInFragment(boolean var1) {
      this.zzbma = var1;
      return this;
   }

   public final StreetViewPanoramaCamera getStreetViewPanoramaCamera() {
      return this.zzbmL;
   }

   public final LatLng getPosition() {
      return this.zzbmN;
   }

   public final Integer getRadius() {
      return this.zzbmO;
   }

   public final String getPanoramaId() {
      return this.zzbmM;
   }

   public final Boolean getUserNavigationEnabled() {
      return this.zzbmP;
   }

   public final Boolean getZoomGesturesEnabled() {
      return this.zzbmg;
   }

   public final Boolean getPanningGesturesEnabled() {
      return this.zzbmQ;
   }

   public final Boolean getStreetNamesEnabled() {
      return this.zzbmR;
   }

   public final Boolean getUseViewLifecycleInFragment() {
      return this.zzbma;
   }

   public final String toString() {
      return zzbe.zzt(this).zzg("PanoramaId", this.zzbmM).zzg("Position", this.zzbmN).zzg("Radius", this.zzbmO).zzg("StreetViewPanoramaCamera", this.zzbmL).zzg("UserNavigationEnabled", this.zzbmP).zzg("ZoomGesturesEnabled", this.zzbmg).zzg("PanningGesturesEnabled", this.zzbmQ).zzg("StreetNamesEnabled", this.zzbmR).zzg("UseViewLifecycleInFragment", this.zzbma).toString();
   }
}
