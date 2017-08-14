package com.google.android.gms.internal;

import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;
import com.google.android.gms.location.ActivityRecognitionResult;

public final class zzati extends zza {
   public static final Creator CREATOR = new zzaua();
   private final ActivityRecognitionResult zzaob;
   private final zzast zzaoc;
   private final zzasx zzaod;
   private final Location zzde;
   private final zzasz zzaoe;
   private final DataHolder zzaof;
   private final zzate zzaog;
   private final zzatg zzaoh;
   private final zzauh zzaoi;
   private final zzaue zzaoj;

   public zzati(ActivityRecognitionResult var1, zzast var2, zzasx var3, Location var4, zzasz var5, DataHolder var6, zzate var7, zzatg var8, zzauh var9, zzaue var10) {
      this.zzaob = var1;
      this.zzaoc = var2;
      this.zzaod = var3;
      this.zzde = var4;
      this.zzaoe = var5;
      this.zzaof = var6;
      this.zzaog = var7;
      this.zzaoh = var8;
      this.zzaoi = var9;
      this.zzaoj = var10;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zza(var1, 2, this.zzaob, var2, false);
      zzd.zza(var1, 3, this.zzaoc, var2, false);
      zzd.zza(var1, 4, this.zzaod, var2, false);
      zzd.zza(var1, 5, this.zzde, var2, false);
      zzd.zza(var1, 6, this.zzaoe, var2, false);
      zzd.zza(var1, 7, this.zzaof, var2, false);
      zzd.zza(var1, 8, this.zzaog, var2, false);
      zzd.zza(var1, 9, this.zzaoh, var2, false);
      zzd.zza(var1, 10, this.zzaoi, var2, false);
      zzd.zza(var1, 11, this.zzaoj, var2, false);
      zzd.zzI(var1, var5);
   }

   public final ActivityRecognitionResult getActivityRecognitionResult() {
      return this.zzaob;
   }

   public final zzast zzmW() {
      return this.zzaoc;
   }

   public final zzasx zzmX() {
      return this.zzaod;
   }

   public final Location getLocation() {
      return this.zzde;
   }

   public final DataHolder zzmY() {
      return this.zzaof;
   }

   public final zzauh zzmZ() {
      return this.zzaoi;
   }

   public final zzaue zzna() {
      return this.zzaoj;
   }
}
