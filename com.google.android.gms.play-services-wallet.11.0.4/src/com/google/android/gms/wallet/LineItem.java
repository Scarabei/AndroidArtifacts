package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class LineItem extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzn();
   String description;
   String zzbOR;
   String zzbOS;
   String zzbOm;
   int zzbOT;
   String zzbOn;

   public static LineItem.Builder newBuilder() {
      return new LineItem().new Builder((zzm)null);
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.description, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.zzbOR, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.zzbOS, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 5, this.zzbOm, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 6, this.zzbOT);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 7, this.zzbOn, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   LineItem(String var1, String var2, String var3, String var4, int var5, String var6) {
      this.description = var1;
      this.zzbOR = var2;
      this.zzbOS = var3;
      this.zzbOm = var4;
      this.zzbOT = var5;
      this.zzbOn = var6;
   }

   LineItem() {
      this.zzbOT = 0;
   }

   public final String getDescription() {
      return this.description;
   }

   public final String getQuantity() {
      return this.zzbOR;
   }

   public final String getUnitPrice() {
      return this.zzbOS;
   }

   public final String getTotalPrice() {
      return this.zzbOm;
   }

   public final int getRole() {
      return this.zzbOT;
   }

   public final String getCurrencyCode() {
      return this.zzbOn;
   }

   public final class Builder {
      // $FF: synthetic field
      private LineItem zzbOU;

      private Builder() {
         this.zzbOU = LineItem.this;
         super();
      }

      public final LineItem.Builder setDescription(String var1) {
         this.zzbOU.description = var1;
         return this;
      }

      public final LineItem.Builder setQuantity(String var1) {
         this.zzbOU.zzbOR = var1;
         return this;
      }

      public final LineItem.Builder setUnitPrice(String var1) {
         this.zzbOU.zzbOS = var1;
         return this;
      }

      public final LineItem.Builder setTotalPrice(String var1) {
         this.zzbOU.zzbOm = var1;
         return this;
      }

      public final LineItem.Builder setRole(int var1) {
         this.zzbOU.zzbOT = var1;
         return this;
      }

      public final LineItem.Builder setCurrencyCode(String var1) {
         this.zzbOU.zzbOn = var1;
         return this;
      }

      public final LineItem build() {
         return this.zzbOU;
      }

      // $FF: synthetic method
      Builder(zzm var2) {
         this();
      }
   }

   public interface Role {
      int REGULAR = 0;
      int TAX = 1;
      int SHIPPING = 2;
   }
}
