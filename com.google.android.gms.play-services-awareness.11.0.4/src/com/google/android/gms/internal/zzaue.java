package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.awareness.state.TimeIntervals;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;

public final class zzaue extends zza implements TimeIntervals {
   public static final Creator CREATOR = new zzauf();
   private final int[] zzaos;

   public zzaue(int[] var1) {
      this.zzaos = var1;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zza(var1, 2, this.getTimeIntervals(), false);
      zzd.zzI(var1, var5);
   }

   public final int[] getTimeIntervals() {
      return this.zzaos;
   }

   public final boolean hasTimeInterval(int var1) {
      if (this.zzaos == null) {
         return false;
      } else {
         int[] var2 = this.zzaos;
         int var3 = this.zzaos.length;

         for(int var4 = 0; var4 < var3; ++var4) {
            if (var2[var4] == var1) {
               return true;
            }
         }

         return false;
      }
   }

   public final String toString() {
      StringBuilder var1;
      (var1 = new StringBuilder()).append("TimeIntervals=");
      if (this.zzaos == null) {
         var1.append("unknown");
      } else {
         var1.append("[");
         boolean var2 = true;
         int[] var3 = this.zzaos;
         int var4 = this.zzaos.length;

         for(int var5 = 0; var5 < var4; ++var5) {
            int var6 = var3[var5];
            if (!var2) {
               var1.append(", ");
            }

            var2 = false;
            var1.append(var6);
         }

         var1.append("]");
      }

      return var1.toString();
   }
}
