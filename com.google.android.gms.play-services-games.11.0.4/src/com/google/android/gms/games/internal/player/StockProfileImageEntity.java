package com.google.android.gms.games.internal.player;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbe;
import java.util.Arrays;

public final class StockProfileImageEntity extends com.google.android.gms.games.internal.zzc implements StockProfileImage {
   public static final Creator CREATOR = new zzf();
   private final String zzbcH;
   private final Uri zzbcI;

   public StockProfileImageEntity(StockProfileImage var1) {
      this(var1.getImageUrl(), var1.zzvl());
   }

   public StockProfileImageEntity(String var1, Uri var2) {
      this.zzbcH = var1;
      this.zzbcI = var2;
   }

   public final String getImageUrl() {
      return this.zzbcH;
   }

   public final Uri zzvl() {
      return this.zzbcI;
   }

   public final boolean isDataValid() {
      return true;
   }

   public final int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzbcH, this.zzbcI});
   }

   public final boolean equals(Object var1) {
      if (!(var1 instanceof StockProfileImage)) {
         return false;
      } else if (var1 == this) {
         return true;
      } else {
         StockProfileImage var2 = (StockProfileImage)var1;
         return zzbe.equal(this.zzbcH, var2.getImageUrl()) && zzbe.equal(this.zzbcI, var2.zzvl());
      }
   }

   public final String toString() {
      return zzbe.zzt(this).zzg("ImageId", this.zzbcH).zzg("ImageUri", this.zzbcI).toString();
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 1, this.getImageUrl(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzbcI, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
