package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import java.util.Locale;

public final class zzf extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzg();
   private String zzaPB;
   private boolean zzaRg;

   public zzf(String var1, boolean var2) {
      this.zzaPB = var1;
      this.zzaRg = var2;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 1, this.zzaPB, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzaRg);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public final String toString() {
      return String.format(Locale.US, "FieldWithSortOrder[%s %s]", this.zzaPB, this.zzaRg ? "ASC" : "DESC");
   }
}
