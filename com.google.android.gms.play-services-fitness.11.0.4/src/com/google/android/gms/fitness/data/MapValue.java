package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbo;

public class MapValue extends com.google.android.gms.common.internal.safeparcel.zza implements ReflectedParcelable {
   private final int zzaku;
   private final int zzaUT;
   private final float zzaUU;
   public static final Creator CREATOR = new zzw();

   public MapValue(int var1, float var2) {
      this(1, 2, var2);
   }

   MapValue(int var1, int var2, float var3) {
      this.zzaku = var1;
      this.zzaUT = var2;
      this.zzaUU = var3;
   }

   public final float asFloat() {
      zzbo.zza(this.zzaUT == 2, "Value is not in float format");
      return this.zzaUU;
   }

   public boolean equals(Object var1) {
      if (this != var1) {
         if (var1 instanceof MapValue) {
            MapValue var3 = (MapValue)var1;
            boolean var10000;
            if (this.zzaUT == var3.zzaUT) {
               switch(this.zzaUT) {
               case 2:
                  var10000 = this.asFloat() == var3.asFloat();
                  break;
               default:
                  var10000 = this.zzaUU == var3.zzaUU;
               }
            } else {
               var10000 = false;
            }

            if (var10000) {
               return true;
            }
         }

         return false;
      } else {
         return true;
      }
   }

   public int hashCode() {
      return (int)this.zzaUU;
   }

   public String toString() {
      switch(this.zzaUT) {
      case 2:
         return Float.toString(this.asFloat());
      default:
         return "unknown";
      }
   }

   public void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1, this.zzaUT);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzaUU);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1000, this.zzaku);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
