package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import java.util.Arrays;
import java.util.Comparator;

public class DetectedActivity extends com.google.android.gms.common.internal.safeparcel.zza {
   private static Comparator zzbhy = new zzc();
   public static final int IN_VEHICLE = 0;
   public static final int ON_BICYCLE = 1;
   public static final int ON_FOOT = 2;
   public static final int STILL = 3;
   public static final int UNKNOWN = 4;
   public static final int TILTING = 5;
   public static final int WALKING = 7;
   public static final int RUNNING = 8;
   private static int[] zzbhz = new int[]{9, 10};
   private static int[] zzbhA = new int[]{0, 1, 2, 4, 5, 6, 7, 8, 10, 11, 12, 13, 14, 16, 17};
   public static final Creator CREATOR = new zzd();
   private int zzbhB;
   private int zzbhC;

   public int getType() {
      int var1 = this.zzbhB;
      return this.zzbhB > 17 ? 4 : var1;
   }

   public int getConfidence() {
      return this.zzbhC;
   }

   public DetectedActivity(int var1, int var2) {
      this.zzbhB = var1;
      this.zzbhC = var2;
   }

   public void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1, this.zzbhB);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 2, this.zzbhC);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 != null && this.getClass() == var1.getClass()) {
         DetectedActivity var2 = (DetectedActivity)var1;
         return this.zzbhB == var2.zzbhB && this.zzbhC == var2.zzbhC;
      } else {
         return false;
      }
   }

   public int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzbhB, this.zzbhC});
   }

   public String toString() {
      String var10000;
      int var3;
      switch(var3 = this.getType()) {
      case 0:
         var10000 = "IN_VEHICLE";
         break;
      case 1:
         var10000 = "ON_BICYCLE";
         break;
      case 2:
         var10000 = "ON_FOOT";
         break;
      case 3:
         var10000 = "STILL";
         break;
      case 4:
         var10000 = "UNKNOWN";
         break;
      case 5:
         var10000 = "TILTING";
         break;
      case 6:
      case 9:
      case 10:
      case 11:
      case 12:
      case 13:
      case 14:
      case 15:
      default:
         var10000 = Integer.toString(var3);
         break;
      case 7:
         var10000 = "WALKING";
         break;
      case 8:
         var10000 = "RUNNING";
         break;
      case 16:
         var10000 = "IN_ROAD_VEHICLE";
         break;
      case 17:
         var10000 = "IN_RAIL_VEHICLE";
      }

      String var1 = String.valueOf(var10000);
      int var2 = this.zzbhC;
      return (new StringBuilder(48 + String.valueOf(var1).length())).append("DetectedActivity [type=").append(var1).append(", confidence=").append(var2).append("]").toString();
   }
}
