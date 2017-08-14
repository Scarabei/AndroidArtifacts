package com.google.android.gms.drive.events;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbe;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public final class zze extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzf();
   private int zzaku;
   private int zzaMS;
   private boolean zzaMT;
   private List zzaMU;
   private final Set zzaMV;

   zze(int var1, int var2, boolean var3, List var4) {
      this(var1, var2, var3, var4, var4 == null ? null : new HashSet(var4));
   }

   private zze(int var1, int var2, boolean var3, List var4, Set var5) {
      this.zzaku = var1;
      this.zzaMS = var2;
      this.zzaMT = var3;
      this.zzaMU = var4;
      this.zzaMV = var5;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1, this.zzaku);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 2, this.zzaMS);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.zzaMT);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 4, this.zzaMU, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public final String toString() {
      return String.format(Locale.US, "ChangesAvailableOptions[ChangesSizeLimit=%d, Repeats=%s, Spaces=%s]", this.zzaMS, this.zzaMT, this.zzaMU);
   }

   public final boolean equals(Object var1) {
      if (var1 != null && var1.getClass() == this.getClass()) {
         if (var1 == this) {
            return true;
         } else {
            zze var2 = (zze)var1;
            return zzbe.equal(this.zzaMV, var2.zzaMV) && this.zzaMS == var2.zzaMS && this.zzaMT == var2.zzaMT;
         }
      } else {
         return false;
      }
   }

   public final int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzaMV, this.zzaMS, this.zzaMT});
   }
}
