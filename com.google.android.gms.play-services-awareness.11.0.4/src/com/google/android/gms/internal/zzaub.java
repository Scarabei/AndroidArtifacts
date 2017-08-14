package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;
import java.util.ArrayList;
import java.util.Arrays;

public final class zzaub extends zza {
   private static final int[] zzaop = new int[]{10002, 10003, 10004, 10005, 10006, 10007, 10008};
   public static final Creator CREATOR = new zzauc();
   private final int zzaoq;
   private final ArrayList zzaor;

   public zzaub(int var1, ArrayList var2) {
      this.zzaoq = var1;
      this.zzaor = var2;
   }

   public final int hashCode() {
      int var1 = 0;
      if (this.zzaor != null) {
         ArrayList var3;
         int var4 = (var3 = (ArrayList)this.zzaor).size();

         zzasv var2;
         for(int var5 = 0; var5 < var4; var1 += 13 * var2.hashCode()) {
            Object var10000 = var3.get(var5);
            ++var5;
            var2 = (zzasv)var10000;
         }
      }

      return Arrays.hashCode(new Object[]{this.zzaoq, var1});
   }

   public final boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof zzaub)) {
         return false;
      } else {
         zzaub var2 = (zzaub)var1;
         if (this.zzaoq != var2.zzaoq) {
            return false;
         } else if (this.zzaor == null ^ var2.zzaor == null) {
            return false;
         } else {
            if (this.zzaor != null) {
               if (this.zzaor.size() != var2.zzaor.size()) {
                  return false;
               }

               ArrayList var4;
               int var5 = (var4 = (ArrayList)this.zzaor).size();
               int var6 = 0;

               while(var6 < var5) {
                  Object var10000 = var4.get(var6);
                  ++var6;
                  zzasv var3 = (zzasv)var10000;
                  if (!var2.zzaor.contains(var3)) {
                     return false;
                  }
               }
            }

            return true;
         }
      }
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zzc(var1, 2, this.zzaoq);
      zzd.zzc(var1, 3, this.zzaor, false);
      zzd.zzI(var1, var5);
   }
}
