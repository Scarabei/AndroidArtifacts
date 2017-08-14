package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.drive.query.Filter;

public class FilterHolder extends com.google.android.gms.common.internal.safeparcel.zza implements ReflectedParcelable {
   public static final Creator CREATOR = new zzh();
   private zzb zzaRh;
   private zzd zzaRi;
   private zzr zzaRj;
   private zzv zzaRk;
   private zzp zzaRl;
   private zzt zzaRm;
   private zzn zzaRn;
   private zzl zzaRo;
   private zzz zzaRp;
   private final Filter zzaMD;

   FilterHolder(zzb var1, zzd var2, zzr var3, zzv var4, zzp var5, zzt var6, zzn var7, zzl var8, zzz var9) {
      this.zzaRh = var1;
      this.zzaRi = var2;
      this.zzaRj = var3;
      this.zzaRk = var4;
      this.zzaRl = var5;
      this.zzaRm = var6;
      this.zzaRn = var7;
      this.zzaRo = var8;
      this.zzaRp = var9;
      if (this.zzaRh != null) {
         this.zzaMD = this.zzaRh;
      } else if (this.zzaRi != null) {
         this.zzaMD = this.zzaRi;
      } else if (this.zzaRj != null) {
         this.zzaMD = this.zzaRj;
      } else if (this.zzaRk != null) {
         this.zzaMD = this.zzaRk;
      } else if (this.zzaRl != null) {
         this.zzaMD = this.zzaRl;
      } else if (this.zzaRm != null) {
         this.zzaMD = this.zzaRm;
      } else if (this.zzaRn != null) {
         this.zzaMD = this.zzaRn;
      } else if (this.zzaRo != null) {
         this.zzaMD = this.zzaRo;
      } else if (this.zzaRp != null) {
         this.zzaMD = this.zzaRp;
      } else {
         throw new IllegalArgumentException("At least one filter must be set.");
      }
   }

   public FilterHolder(Filter var1) {
      zzbo.zzb(var1, "Null filter.");
      this.zzaRh = var1 instanceof zzb ? (zzb)var1 : null;
      this.zzaRi = var1 instanceof zzd ? (zzd)var1 : null;
      this.zzaRj = var1 instanceof zzr ? (zzr)var1 : null;
      this.zzaRk = var1 instanceof zzv ? (zzv)var1 : null;
      this.zzaRl = var1 instanceof zzp ? (zzp)var1 : null;
      this.zzaRm = var1 instanceof zzt ? (zzt)var1 : null;
      this.zzaRn = var1 instanceof zzn ? (zzn)var1 : null;
      this.zzaRo = var1 instanceof zzl ? (zzl)var1 : null;
      this.zzaRp = var1 instanceof zzz ? (zzz)var1 : null;
      if (this.zzaRh == null && this.zzaRi == null && this.zzaRj == null && this.zzaRk == null && this.zzaRl == null && this.zzaRm == null && this.zzaRn == null && this.zzaRo == null && this.zzaRp == null) {
         throw new IllegalArgumentException("Invalid filter type.");
      } else {
         this.zzaMD = var1;
      }
   }

   public final Filter getFilter() {
      return this.zzaMD;
   }

   public void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 1, this.zzaRh, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzaRi, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.zzaRj, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.zzaRk, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 5, this.zzaRl, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 6, this.zzaRm, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 7, this.zzaRn, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 8, this.zzaRo, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 9, this.zzaRp, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public String toString() {
      return String.format("FilterHolder[%s]", this.zzaMD);
   }
}
