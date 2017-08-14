package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.zzbo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public final class PolylineOptions extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzl();
   private final List zzbnN;
   private float zzbnr = 10.0F;
   private int mColor = -16777216;
   private float zzbnk = 0.0F;
   private boolean zzbnl = true;
   private boolean zzbnP = false;
   private boolean zzbnm = false;
   @NonNull
   private Cap zzbnS = new ButtCap();
   @NonNull
   private Cap zzbnT = new ButtCap();
   private int zzbnU = 0;
   @Nullable
   private List zzbnV = null;

   public PolylineOptions() {
      this.zzbnN = new ArrayList();
   }

   PolylineOptions(List var1, float var2, int var3, float var4, boolean var5, boolean var6, boolean var7, @Nullable Cap var8, @Nullable Cap var9, int var10, @Nullable List var11) {
      this.zzbnN = var1;
      this.zzbnr = var2;
      this.mColor = var3;
      this.zzbnk = var4;
      this.zzbnl = var5;
      this.zzbnP = var6;
      this.zzbnm = var7;
      if (var8 != null) {
         this.zzbnS = var8;
      }

      if (var9 != null) {
         this.zzbnT = var9;
      }

      this.zzbnU = var10;
      this.zzbnV = var11;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 2, this.getPoints(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.getWidth());
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 4, this.getColor());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 5, this.getZIndex());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 6, this.isVisible());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 7, this.isGeodesic());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 8, this.isClickable());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 9, this.getStartCap(), var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 10, this.getEndCap(), var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 11, this.getJointType());
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 12, this.getPattern(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public final PolylineOptions add(LatLng var1) {
      this.zzbnN.add(var1);
      return this;
   }

   public final PolylineOptions add(LatLng... var1) {
      this.zzbnN.addAll(Arrays.asList(var1));
      return this;
   }

   public final PolylineOptions addAll(Iterable var1) {
      Iterator var2 = var1.iterator();

      while(var2.hasNext()) {
         LatLng var3 = (LatLng)var2.next();
         this.zzbnN.add(var3);
      }

      return this;
   }

   public final PolylineOptions width(float var1) {
      this.zzbnr = var1;
      return this;
   }

   public final PolylineOptions color(int var1) {
      this.mColor = var1;
      return this;
   }

   public final PolylineOptions startCap(@NonNull Cap var1) {
      this.zzbnS = (Cap)zzbo.zzb(var1, "startCap must not be null");
      return this;
   }

   public final PolylineOptions endCap(@NonNull Cap var1) {
      this.zzbnT = (Cap)zzbo.zzb(var1, "endCap must not be null");
      return this;
   }

   public final PolylineOptions jointType(int var1) {
      this.zzbnU = var1;
      return this;
   }

   public final PolylineOptions pattern(@Nullable List var1) {
      this.zzbnV = var1;
      return this;
   }

   public final PolylineOptions zIndex(float var1) {
      this.zzbnk = var1;
      return this;
   }

   public final PolylineOptions visible(boolean var1) {
      this.zzbnl = var1;
      return this;
   }

   public final PolylineOptions geodesic(boolean var1) {
      this.zzbnP = var1;
      return this;
   }

   public final PolylineOptions clickable(boolean var1) {
      this.zzbnm = var1;
      return this;
   }

   public final List getPoints() {
      return this.zzbnN;
   }

   public final float getWidth() {
      return this.zzbnr;
   }

   public final int getColor() {
      return this.mColor;
   }

   @NonNull
   public final Cap getStartCap() {
      return this.zzbnS;
   }

   @NonNull
   public final Cap getEndCap() {
      return this.zzbnT;
   }

   public final int getJointType() {
      return this.zzbnU;
   }

   @Nullable
   public final List getPattern() {
      return this.zzbnV;
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
