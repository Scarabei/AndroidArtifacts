package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public final class PolygonOptions extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzk();
   private final List zzbnN;
   private final List zzbnO;
   private float mStrokeWidth = 10.0F;
   private int mStrokeColor = -16777216;
   private int mFillColor = 0;
   private float zzbnk = 0.0F;
   private boolean zzbnl = true;
   private boolean zzbnP = false;
   private boolean zzbnm = false;
   private int zzbnQ = 0;
   @Nullable
   private List zzbnn = null;

   public PolygonOptions() {
      this.zzbnN = new ArrayList();
      this.zzbnO = new ArrayList();
   }

   PolygonOptions(List var1, List var2, float var3, int var4, int var5, float var6, boolean var7, boolean var8, boolean var9, int var10, @Nullable List var11) {
      this.zzbnN = var1;
      this.zzbnO = var2;
      this.mStrokeWidth = var3;
      this.mStrokeColor = var4;
      this.mFillColor = var5;
      this.zzbnk = var6;
      this.zzbnl = var7;
      this.zzbnP = var8;
      this.zzbnm = var9;
      this.zzbnQ = var10;
      this.zzbnn = var11;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 2, this.getPoints(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzd(var1, 3, this.zzbnO, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.getStrokeWidth());
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 5, this.getStrokeColor());
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 6, this.getFillColor());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 7, this.getZIndex());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 8, this.isVisible());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 9, this.isGeodesic());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 10, this.isClickable());
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 11, this.getStrokeJointType());
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 12, this.getStrokePattern(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public final PolygonOptions add(LatLng var1) {
      this.zzbnN.add(var1);
      return this;
   }

   public final PolygonOptions add(LatLng... var1) {
      this.zzbnN.addAll(Arrays.asList(var1));
      return this;
   }

   public final PolygonOptions addAll(Iterable var1) {
      Iterator var2 = var1.iterator();

      while(var2.hasNext()) {
         LatLng var3 = (LatLng)var2.next();
         this.zzbnN.add(var3);
      }

      return this;
   }

   public final PolygonOptions addHole(Iterable var1) {
      ArrayList var2 = new ArrayList();
      Iterator var3 = var1.iterator();

      while(var3.hasNext()) {
         LatLng var4 = (LatLng)var3.next();
         var2.add(var4);
      }

      this.zzbnO.add(var2);
      return this;
   }

   public final PolygonOptions strokeWidth(float var1) {
      this.mStrokeWidth = var1;
      return this;
   }

   public final PolygonOptions strokeColor(int var1) {
      this.mStrokeColor = var1;
      return this;
   }

   public final PolygonOptions strokeJointType(int var1) {
      this.zzbnQ = var1;
      return this;
   }

   public final PolygonOptions strokePattern(@Nullable List var1) {
      this.zzbnn = var1;
      return this;
   }

   public final PolygonOptions fillColor(int var1) {
      this.mFillColor = var1;
      return this;
   }

   public final PolygonOptions zIndex(float var1) {
      this.zzbnk = var1;
      return this;
   }

   public final PolygonOptions visible(boolean var1) {
      this.zzbnl = var1;
      return this;
   }

   public final PolygonOptions geodesic(boolean var1) {
      this.zzbnP = var1;
      return this;
   }

   public final PolygonOptions clickable(boolean var1) {
      this.zzbnm = var1;
      return this;
   }

   public final List getPoints() {
      return this.zzbnN;
   }

   public final List getHoles() {
      return this.zzbnO;
   }

   public final float getStrokeWidth() {
      return this.mStrokeWidth;
   }

   public final int getStrokeColor() {
      return this.mStrokeColor;
   }

   public final int getStrokeJointType() {
      return this.zzbnQ;
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

   public final boolean isGeodesic() {
      return this.zzbnP;
   }

   public final boolean isClickable() {
      return this.zzbnm;
   }
}
