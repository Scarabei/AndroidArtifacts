package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import java.util.List;

public final class CircleOptions extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzc();
   private LatLng zzbni = null;
   private double zzbnj = 0.0D;
   private float mStrokeWidth = 10.0F;
   private int mStrokeColor = -16777216;
   private int mFillColor = 0;
   private float zzbnk = 0.0F;
   private boolean zzbnl = true;
   private boolean zzbnm = false;
   @Nullable
   private List zzbnn = null;

   public CircleOptions() {
   }

   CircleOptions(LatLng var1, double var2, float var4, int var5, int var6, float var7, boolean var8, boolean var9, @Nullable List var10) {
      this.zzbni = var1;
      this.zzbnj = var2;
      this.mStrokeWidth = var4;
      this.mStrokeColor = var5;
      this.mFillColor = var6;
      this.zzbnk = var7;
      this.zzbnl = var8;
      this.zzbnm = var9;
      this.zzbnn = var10;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.getCenter(), var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.getRadius());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.getStrokeWidth());
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 5, this.getStrokeColor());
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 6, this.getFillColor());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 7, this.getZIndex());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 8, this.isVisible());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 9, this.isClickable());
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 10, this.getStrokePattern(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public final CircleOptions center(LatLng var1) {
      this.zzbni = var1;
      return this;
   }

   public final CircleOptions radius(double var1) {
      this.zzbnj = var1;
      return this;
   }

   public final CircleOptions strokeWidth(float var1) {
      this.mStrokeWidth = var1;
      return this;
   }

   public final CircleOptions strokeColor(int var1) {
      this.mStrokeColor = var1;
      return this;
   }

   public final CircleOptions strokePattern(@Nullable List var1) {
      this.zzbnn = var1;
      return this;
   }

   public final CircleOptions fillColor(int var1) {
      this.mFillColor = var1;
      return this;
   }

   public final CircleOptions zIndex(float var1) {
      this.zzbnk = var1;
      return this;
   }

   public final CircleOptions visible(boolean var1) {
      this.zzbnl = var1;
      return this;
   }

   public final CircleOptions clickable(boolean var1) {
      this.zzbnm = var1;
      return this;
   }

   public final LatLng getCenter() {
      return this.zzbni;
   }

   public final double getRadius() {
      return this.zzbnj;
   }

   public final float getStrokeWidth() {
      return this.mStrokeWidth;
   }

   public final int getStrokeColor() {
      return this.mStrokeColor;
   }

   @Nullable
   public final List getStrokePattern() {
      return this.zzbnn;
   }

   public final int getFillColor() {
      return this.mFillColor;
   }

   public final float getZIndex() {
      return this.zzbnk;
   }

   public final boolean isVisible() {
      return this.zzbnl;
   }

   public final boolean isClickable() {
      return this.zzbnm;
   }
}
