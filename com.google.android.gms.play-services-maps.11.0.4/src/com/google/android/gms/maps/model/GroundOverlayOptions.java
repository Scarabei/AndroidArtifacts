package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.zzbo;

public final class GroundOverlayOptions extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzd();
   public static final float NO_DIMENSION = -1.0F;
   @NonNull
   private BitmapDescriptor zzbnp;
   private LatLng zzbnq;
   private float zzbnr;
   private float zzbns;
   private LatLngBounds zzblq;
   private float zzbnf;
   private float zzbnk;
   private boolean zzbnl = true;
   private float zzbnt = 0.0F;
   private float zzbnu = 0.5F;
   private float zzbnv = 0.5F;
   private boolean zzbnm = false;

   GroundOverlayOptions(IBinder var1, LatLng var2, float var3, float var4, LatLngBounds var5, float var6, float var7, boolean var8, float var9, float var10, float var11, boolean var12) {
      this.zzbnp = new BitmapDescriptor(com.google.android.gms.dynamic.IObjectWrapper.zza.zzM(var1));
      this.zzbnq = var2;
      this.zzbnr = var3;
      this.zzbns = var4;
      this.zzblq = var5;
      this.zzbnf = var6;
      this.zzbnk = var7;
      this.zzbnl = var8;
      this.zzbnt = var9;
      this.zzbnu = var10;
      this.zzbnv = var11;
      this.zzbnm = var12;
   }

   public GroundOverlayOptions() {
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzbnp.zzwe().asBinder(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.getLocation(), var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.getWidth());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 5, this.getHeight());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 6, this.getBounds(), var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 7, this.getBearing());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 8, this.getZIndex());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 9, this.isVisible());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 10, this.getTransparency());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 11, this.getAnchorU());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 12, this.getAnchorV());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 13, this.isClickable());
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public final GroundOverlayOptions image(@NonNull BitmapDescriptor var1) {
      zzbo.zzb(var1, "imageDescriptor must not be null");
      this.zzbnp = var1;
      return this;
   }

   public final GroundOverlayOptions anchor(float var1, float var2) {
      this.zzbnu = var1;
      this.zzbnv = var2;
      return this;
   }

   public final GroundOverlayOptions position(LatLng var1, float var2) {
      zzbo.zza(this.zzblq == null, "Position has already been set using positionFromBounds");
      zzbo.zzb(var1 != null, "Location must be specified");
      zzbo.zzb(var2 >= 0.0F, "Width must be non-negative");
      return this.zza(var1, var2, -1.0F);
   }

   public final GroundOverlayOptions position(LatLng var1, float var2, float var3) {
      zzbo.zza(this.zzblq == null, "Position has already been set using positionFromBounds");
      zzbo.zzb(var1 != null, "Location must be specified");
      zzbo.zzb(var2 >= 0.0F, "Width must be non-negative");
      zzbo.zzb(var3 >= 0.0F, "Height must be non-negative");
      return this.zza(var1, var2, var3);
   }

   private final GroundOverlayOptions zza(LatLng var1, float var2, float var3) {
      this.zzbnq = var1;
      this.zzbnr = var2;
      this.zzbns = var3;
      return this;
   }

   public final GroundOverlayOptions positionFromBounds(LatLngBounds var1) {
      boolean var10000 = this.zzbnq == null;
      String var2 = String.valueOf(this.zzbnq);
      zzbo.zza(var10000, (new StringBuilder(46 + String.valueOf(var2).length())).append("Position has already been set using position: ").append(var2).toString());
      this.zzblq = var1;
      return this;
   }

   public final GroundOverlayOptions bearing(float var1) {
      this.zzbnf = (var1 % 360.0F + 360.0F) % 360.0F;
      return this;
   }

   public final GroundOverlayOptions zIndex(float var1) {
      this.zzbnk = var1;
      return this;
   }

   public final GroundOverlayOptions visible(boolean var1) {
      this.zzbnl = var1;
      return this;
   }

   public final GroundOverlayOptions transparency(float var1) {
      zzbo.zzb(var1 >= 0.0F && var1 <= 1.0F, "Transparency must be in the range [0..1]");
      this.zzbnt = var1;
      return this;
   }

   public final GroundOverlayOptions clickable(boolean var1) {
      this.zzbnm = var1;
      return this;
   }

   public final BitmapDescriptor getImage() {
      return this.zzbnp;
   }

   public final LatLng getLocation() {
      return this.zzbnq;
   }

   public final float getWidth() {
      return this.zzbnr;
   }

   public final float getHeight() {
      return this.zzbns;
   }

   public final LatLngBounds getBounds() {
      return this.zzblq;
   }

   public final float getBearing() {
      return this.zzbnf;
   }

   public final float getZIndex() {
      return this.zzbnk;
   }

   public final float getTransparency() {
      return this.zzbnt;
   }

   public final float getAnchorU() {
      return this.zzbnu;
   }

   public final float getAnchorV() {
      return this.zzbnv;
   }

   public final boolean isVisible() {
      return this.zzbnl;
   }

   public final boolean isClickable() {
      return this.zzbnm;
   }
}
