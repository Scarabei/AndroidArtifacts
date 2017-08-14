package com.google.android.gms.location.places.internal;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbe;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

public final class PlaceEntity extends com.google.android.gms.common.internal.safeparcel.zza implements ReflectedParcelable, Place {
   public static final Creator CREATOR = new zzad();
   private final String zzIi;
   private final Bundle zzbkr;
   /** @deprecated */
   @Deprecated
   private final zzaj zzbks;
   private final LatLng zzbji;
   private final float zzbkt;
   private final LatLngBounds zzbku;
   private final String zzbkv;
   private final Uri zzbjl;
   private final boolean zzbkw;
   private final float zzbkx;
   private final int zzbky;
   private final List zzbkz;
   private final List zzbjj;
   private final String mName;
   private final String zzaTl;
   private final String zzbjk;
   private final String zzbkA;
   private final List zzbkB;
   private final zzal zzbkC;
   private final zzae zzbkD;
   private final Map zzbkE;
   private final TimeZone zzbkF;
   private Locale zzbjV;

   PlaceEntity(String var1, List var2, List var3, Bundle var4, String var5, String var6, String var7, String var8, List var9, LatLng var10, float var11, LatLngBounds var12, String var13, Uri var14, boolean var15, float var16, int var17, zzaj var18, zzal var19, zzae var20) {
      this.zzIi = var1;
      this.zzbjj = Collections.unmodifiableList(var2);
      this.zzbkz = var3;
      this.zzbkr = var4 != null ? var4 : new Bundle();
      this.mName = var5;
      this.zzaTl = var6;
      this.zzbjk = var7;
      this.zzbkA = var8;
      this.zzbkB = var9 != null ? var9 : Collections.emptyList();
      this.zzbji = var10;
      this.zzbkt = var11;
      this.zzbku = var12;
      this.zzbkv = var13 != null ? var13 : "UTC";
      this.zzbjl = var14;
      this.zzbkw = var15;
      this.zzbkx = var16;
      this.zzbky = var17;
      HashMap var21 = new HashMap();
      this.zzbkE = Collections.unmodifiableMap(var21);
      this.zzbkF = null;
      this.zzbjV = null;
      this.zzbks = var18;
      this.zzbkC = var19;
      this.zzbkD = var20;
   }

   public final String getId() {
      return this.zzIi;
   }

   public final List getPlaceTypes() {
      return this.zzbjj;
   }

   public final Locale getLocale() {
      return this.zzbjV;
   }

   public final void setLocale(Locale var1) {
      this.zzbjV = var1;
   }

   public final LatLng getLatLng() {
      return this.zzbji;
   }

   public final LatLngBounds getViewport() {
      return this.zzbku;
   }

   public final Uri getWebsiteUri() {
      return this.zzbjl;
   }

   public final CharSequence getAttributions() {
      return zzg.zzi(this.zzbkB);
   }

   public final float getRating() {
      return this.zzbkx;
   }

   public final int getPriceLevel() {
      return this.zzbky;
   }

   @SuppressLint({"DefaultLocale"})
   public final String toString() {
      return zzbe.zzt(this).zzg("id", this.zzIi).zzg("placeTypes", this.zzbjj).zzg("locale", this.zzbjV).zzg("name", this.mName).zzg("address", this.zzaTl).zzg("phoneNumber", this.zzbjk).zzg("latlng", this.zzbji).zzg("viewport", this.zzbku).zzg("websiteUri", this.zzbjl).zzg("isPermanentlyClosed", this.zzbkw).zzg("priceLevel", this.zzbky).toString();
   }

   public final int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzIi, this.zzbjV});
   }

   public final boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof PlaceEntity)) {
         return false;
      } else {
         PlaceEntity var2 = (PlaceEntity)var1;
         return this.zzIi.equals(var2.zzIi) && zzbe.equal(this.zzbjV, var2.zzbjV);
      }
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 1, this.getId(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzbkr, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.zzbks, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.getLatLng(), var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 5, this.zzbkt);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 6, this.getViewport(), var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 7, this.zzbkv, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 8, this.getWebsiteUri(), var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 9, this.zzbkw);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 10, this.getRating());
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 11, this.getPriceLevel());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 13, this.zzbkz, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 14, (String)this.getAddress(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 15, (String)this.getPhoneNumber(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 16, this.zzbkA, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzb(var1, 17, this.zzbkB, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 19, (String)this.getName(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 20, this.getPlaceTypes(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 21, this.zzbkC, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 22, this.zzbkD, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public final boolean isDataValid() {
      return true;
   }

   // $FF: synthetic method
   public final CharSequence getPhoneNumber() {
      return this.zzbjk;
   }

   // $FF: synthetic method
   public final CharSequence getName() {
      return this.mName;
   }

   // $FF: synthetic method
   public final CharSequence getAddress() {
      return this.zzaTl;
   }

   public static class zza {
      private String zzIi;
      private String mName;
      private LatLng zzbji;
      private float zzbkt;
      private LatLngBounds zzbku;
      private Uri zzbjl;
      private boolean zzbkw;
      private float zzbkx = -1.0F;
      private int zzbky = -1;
      private List zzbkG;
      private String zzaTl;
      private String zzbjk;
      private List zzbkB;
      private zzal zzbkC;
      private zzae zzbkH;

      public final PlaceEntity.zza zzdx(String var1) {
         this.zzIi = var1;
         return this;
      }

      public final PlaceEntity.zza zzdy(String var1) {
         this.mName = var1;
         return this;
      }

      public final PlaceEntity.zza zza(LatLng var1) {
         this.zzbji = var1;
         return this;
      }

      public final PlaceEntity.zza zzc(float var1) {
         this.zzbkt = var1;
         return this;
      }

      public final PlaceEntity.zza zza(LatLngBounds var1) {
         this.zzbku = var1;
         return this;
      }

      public final PlaceEntity.zza zzp(Uri var1) {
         this.zzbjl = var1;
         return this;
      }

      public final PlaceEntity.zza zzaj(boolean var1) {
         this.zzbkw = var1;
         return this;
      }

      public final PlaceEntity.zza zzd(float var1) {
         this.zzbkx = var1;
         return this;
      }

      public final PlaceEntity.zza zzbm(int var1) {
         this.zzbky = var1;
         return this;
      }

      public final PlaceEntity.zza zzD(List var1) {
         this.zzbkG = var1;
         return this;
      }

      public final PlaceEntity.zza zzdz(String var1) {
         this.zzaTl = var1;
         return this;
      }

      public final PlaceEntity.zza zzdA(String var1) {
         this.zzbjk = var1;
         return this;
      }

      public final PlaceEntity.zza zzE(List var1) {
         this.zzbkB = var1;
         return this;
      }

      public final PlaceEntity.zza zza(zzal var1) {
         this.zzbkC = var1;
         return this;
      }

      public final PlaceEntity.zza zza(zzae var1) {
         this.zzbkH = var1;
         return this;
      }

      public final PlaceEntity zzvZ() {
         String var10002 = this.zzIi;
         List var10003 = this.zzbkG;
         List var10004 = Collections.emptyList();
         List var4 = this.zzbkB;
         String var3 = this.zzbjk;
         String var2 = this.zzaTl;
         String var1 = this.mName;
         return new PlaceEntity(var10002, var10003, var10004, (Bundle)null, this.mName, this.zzaTl, this.zzbjk, (String)null, this.zzbkB, this.zzbji, this.zzbkt, this.zzbku, (String)null, this.zzbjl, this.zzbkw, this.zzbkx, this.zzbky, new zzaj(var1, var2, var3, (String)null, var4), this.zzbkC, this.zzbkH);
      }
   }
}
