package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import java.util.List;

public class GoalsResult extends com.google.android.gms.common.internal.safeparcel.zza implements Result {
   private final int versionCode;
   private final Status zzajl;
   private final List zzaXz;
   public static final Creator CREATOR = new zzf();

   GoalsResult(int var1, Status var2, List var3) {
      this.versionCode = var1;
      this.zzajl = var2;
      this.zzaXz = var3;
   }

   public GoalsResult(Status var1, List var2) {
      this(1, var1, var2);
   }

   public List getGoals() {
      return this.zzaXz;
   }

   public Status getStatus() {
      return this.zzajl;
   }

   public void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 1, this.getStatus(), var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 2, this.getGoals(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1000, this.versionCode);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
