package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public final class MarkerOptions extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzh();
   private LatLng zzbmN;
   private String zzaoy;
   private String zzbnE;
   private BitmapDescriptor zzbnF;
   private float zzbnu = 0.5F;
   private float zzbnv = 1.0F;
   private boolean zzbnG;
   private boolean zzbnl = true;
   private boolean zzbnH = false;
   private float zzbnI = 0.0F;
   private float zzbnJ = 0.5F;
   private float zzbnK = 0.0F;
   private float mAlpha = 1.0F;
   private float zzbnk;

   public MarkerOptions() {
   }

   MarkerOptions(LatLng var1, String var2, String var3, IBinder var4, float var5, float var6, boolean var7, boolean var8, boolean var9, float var10, float var11, float var12, float var13, float var14) {
      this.zzbmN = var1;
      this.zzaoy = var2;
      this.zzbnE = var3;
      if (var4 == null) {
         this.zzbnF = null;
      } else {
         this.zzbnF = new BitmapDescriptor(com.google.android.gms.dynamic.IObjectWrapper.zza.zzM(var4));
      }

      this.zzbnu = var5;
      this.zzbnv = var6;
      this.zzbnG = var7;
      this.zzbnl = var8;
      this.zzbnH = var9;
      this.zzbnI = var10;
      this.zzbnJ = var11;
      this.zzbnK = var12;
      this.mAlpha = var13;
      this.zzbnk = var14;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.getPosition(), var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.getTitle(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.getSnippet(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 5, this.zzbnF == null ? null : this.zzbnF.zzwe().asBinder(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 6, this.getAnchorU());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 7, this.getAnchorV());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 8, this.isDraggable());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 9, this.isVisible());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 10, this.isFlat());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 11, this.getRotation());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 12, this.getInfoWindowAnchorU());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 13, this.getInfoWindowAnchorV());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 14, this.getAlpha());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 15, this.getZIndex());
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public final MarkerOptions position(@NonNull LatLng var1) {
      if (var1 == null) {
         throw new IllegalArgumentException("latlng cannot be null - a position is required.");
      } else {
         this.zzbmN = var1;
         return this;
      }
   }

   public final MarkerOptions zIndex(float var1) {
      this.zzbnk = var1;
      return this;
   }

   public final MarkerOptions icon(@Nullable BitmapDescriptor var1) {
      this.zzbnF = var1;
      return this;
   }

   public final MarkerOptions anchor(float var1, float var2) {
      this.zzbnu = var1;
      this.zzbnv = var2;
      return this;
   }

   public final MarkerOptions infoWindowAnchor(float var1, float var2) {
      this.zzbnJ = var1;
      this.zzbnK = var2;
      return this;
   }

   public final MarkerOptions title(@Nullable String var1) {
      this.zzaoy = var1;
      return this;
   }

   public final MarkerOptions snippet(@Nullable String var1) {
      this.zzbnE = var1;
      return this;
   }

   public final MarkerOptions draggable(boolean var1) {
      this.zzbnG = var1;
      return this;
   }

   public final MarkerOptions visible(boolean var1) {
      this.zzbnl = var1;
      return this;
   }

   public final MarkerOptions flat(boolean var1) {
      this.zzbnH = var1;
      return this;
   }

   public final MarkerOptions rotation(float var1) {
      this.zzbnI = var1;
      return this;
   }

   public final MarkerOptions alpha(float var1) {
      this.mAlpha = var1;
      return this;
   }

   public final LatLng getPosition() {
      return this.zzbmN;
   }

   public final String getTitle() {
      return this.zzaoy;
   }

   public final String getSnippet() {
      return this.zzbnE;
   }

   public final BitmapDescriptor getIcon() {
      return this.zzbnF;
   }

   public final float getAnchorU() {
      return this.zzbnu;
   }

   public final float getAnchorV() {
      return this.zzbnv;
   }

   public final boolean isDraggable() {
      return this.zzbnG;
   }

   public final boolean isVisible() {
      return this.zzbnl;
   }

   public final boolean isFlat() {
      return this.zzbnH;
   }

   public final float getRotation() {
      return this.zzbnI;
   }

   public final float getInfoWindowAnchorU() {
      return this.zzbnJ;
   }

   public final float getInfoWindowAnchorV() {
      return this.zzbnK;
   }

   public final float getAlpha() {
      return this.mAlpha;
   }

   public final float getZIndex() {
      return this.zzbnk;
   }
}
