package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import android.util.Log;
import com.google.android.gms.common.internal.zzbe;
import com.google.android.gms.common.internal.zzbo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class PatternItem extends com.google.android.gms.common.internal.safeparcel.zza {
   private static final String TAG = PatternItem.class.getSimpleName();
   public static final Creator CREATOR = new zzi();
   private final int type;
   @Nullable
   private final Float zzbnL;

   public PatternItem(int var1, @Nullable Float var2) {
      boolean var10000 = var1 == 1 || var2 != null && var2.floatValue() >= 0.0F;
      String var3 = String.valueOf(var2);
      zzbo.zzb(var10000, (new StringBuilder(45 + String.valueOf(var3).length())).append("Invalid PatternItem: type=").append(var1).append(" length=").append(var3).toString());
      this.type = var1;
      this.zzbnL = var2;
   }

   public void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 2, this.type);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.zzbnL, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public int hashCode() {
      return Arrays.hashCode(new Object[]{this.type, this.zzbnL});
   }

   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof PatternItem)) {
         return false;
      } else {
         PatternItem var2 = (PatternItem)var1;
         return this.type == var2.type && zzbe.equal(this.zzbnL, var2.zzbnL);
      }
   }

   public String toString() {
      int var1 = this.type;
      String var2 = String.valueOf(this.zzbnL);
      return (new StringBuilder(39 + String.valueOf(var2).length())).append("[PatternItem: type=").append(var1).append(" length=").append(var2).append("]").toString();
   }

   @Nullable
   static List zzF(@Nullable List var0) {
      if (var0 == null) {
         return null;
      } else {
         ArrayList var1 = new ArrayList(var0.size());

         Object var10001;
         for(Iterator var2 = var0.iterator(); var2.hasNext(); var1.add(var10001)) {
            PatternItem var3 = (PatternItem)var2.next();
            if (var3 == null) {
               var10001 = null;
            } else {
               switch(var3.type) {
               case 0:
                  var10001 = new Dash(var3.zzbnL.floatValue());
                  break;
               case 1:
                  var10001 = new Dot();
                  break;
               case 2:
                  var10001 = new Gap(var3.zzbnL.floatValue());
                  break;
               default:
                  int var5 = var3.type;
                  Log.w(TAG, (new StringBuilder(37)).append("Unknown PatternItem type: ").append(var5).toString());
                  var10001 = var3;
               }
            }
         }

         return var1;
      }
   }
}
