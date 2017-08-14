package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.wearable.Node;

public final class zzeg extends com.google.android.gms.common.internal.safeparcel.zza implements Node {
   public static final Creator CREATOR = new zzeh();
   private final String zzIi;
   private final String zzalP;
   private final int zzbTa;
   private final boolean zzbTb;

   public zzeg(String var1, String var2, int var3, boolean var4) {
      this.zzIi = var1;
      this.zzalP = var2;
      this.zzbTa = var3;
      this.zzbTb = var4;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.getId(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.getDisplayName(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 4, this.zzbTa);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 5, this.isNearby());
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public final boolean equals(Object var1) {
      return !(var1 instanceof zzeg) ? false : ((zzeg)var1).zzIi.equals(this.zzIi);
   }

   public final int hashCode() {
      return this.zzIi.hashCode();
   }

   public final String toString() {
      String var1 = this.zzalP;
      String var2 = this.zzIi;
      int var3 = this.zzbTa;
      boolean var4 = this.zzbTb;
      return (new StringBuilder(45 + String.valueOf(var1).length() + String.valueOf(var2).length())).append("Node{").append(var1).append(", id=").append(var2).append(", hops=").append(var3).append(", isNearby=").append(var4).append("}").toString();
   }

   public final String getId() {
      return this.zzIi;
   }

   public final String getDisplayName() {
      return this.zzalP;
   }

   public final boolean isNearby() {
      return this.zzbTb;
   }
}
