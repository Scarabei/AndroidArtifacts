package com.google.android.gms.nearby.messages;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbo;
import java.util.ArrayList;

public class Strategy extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzg();
   public static final int DISCOVERY_MODE_BROADCAST = 1;
   public static final int DISCOVERY_MODE_SCAN = 2;
   public static final int DISCOVERY_MODE_DEFAULT = 3;
   public static final int DISTANCE_TYPE_DEFAULT = 0;
   public static final int DISTANCE_TYPE_EARSHOT = 1;
   public static final int TTL_SECONDS_DEFAULT = 300;
   public static final int TTL_SECONDS_MAX = 86400;
   public static final int TTL_SECONDS_INFINITE = Integer.MAX_VALUE;
   public static final Strategy DEFAULT = (new Strategy.Builder()).build();
   public static final Strategy BLE_ONLY;
   /** @deprecated */
   @Deprecated
   private static Strategy zzbyl;
   private int zzaku;
   /** @deprecated */
   @Deprecated
   private int zzbym;
   private int zzbyn;
   private int zzbyo;
   /** @deprecated */
   @Deprecated
   private boolean zzbyp;
   private int zzbyq;
   private int zzbyr;
   private final int zzbys;

   Strategy(int var1, int var2, int var3, int var4, boolean var5, int var6, int var7, int var8) {
      this.zzaku = var1;
      this.zzbym = var2;
      if (var2 == 0) {
         this.zzbyr = var7;
      } else {
         switch(var2) {
         case 2:
            this.zzbyr = 1;
            break;
         case 3:
            this.zzbyr = 2;
            break;
         default:
            this.zzbyr = 3;
         }
      }

      this.zzbyo = var4;
      this.zzbyp = var5;
      if (var5) {
         this.zzbyq = 2;
         this.zzbyn = Integer.MAX_VALUE;
      } else {
         this.zzbyn = var3;
         switch(var6) {
         case -1:
         case 0:
         case 1:
         case 6:
            this.zzbyq = -1;
            break;
         case 2:
         case 3:
         case 4:
         case 5:
         default:
            this.zzbyq = var6;
         }
      }

      this.zzbys = var8;
   }

   public final int zzzU() {
      return this.zzbys;
   }

   public void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1, this.zzbym);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 2, this.zzbyn);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 3, this.zzbyo);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.zzbyp);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 5, this.zzbyq);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 6, this.zzbyr);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 7, this.zzbys);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1000, this.zzaku);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof Strategy)) {
         return false;
      } else {
         Strategy var2 = (Strategy)var1;
         return this.zzaku == var2.zzaku && this.zzbyr == var2.zzbyr && this.zzbyn == var2.zzbyn && this.zzbyo == var2.zzbyo && this.zzbyq == var2.zzbyq;
      }
   }

   public int hashCode() {
      int var1 = this.zzaku;
      return (((this.zzaku * 31 + this.zzbyr) * 31 + this.zzbyn) * 31 + this.zzbyo) * 31 + this.zzbyq;
   }

   public String toString() {
      int var1 = this.zzbyn;
      int var6 = this.zzbyo;
      String var10000;
      switch(this.zzbyo) {
      case 0:
         var10000 = "DEFAULT";
         break;
      case 1:
         var10000 = "EARSHOT";
         break;
      default:
         var10000 = (new StringBuilder(19)).append("UNKNOWN:").append(var6).toString();
      }

      String var2 = String.valueOf(var10000);
      var6 = this.zzbyq;
      ArrayList var7;
      if (this.zzbyq == -1) {
         var10000 = "DEFAULT";
      } else {
         var7 = new ArrayList();
         if ((var6 & 4) > 0) {
            var7.add("ULTRASOUND");
         }

         if ((var6 & 2) > 0) {
            var7.add("BLE");
         }

         var10000 = var7.isEmpty() ? (new StringBuilder(19)).append("UNKNOWN:").append(var6).toString() : var7.toString();
      }

      String var3 = String.valueOf(var10000);
      var6 = this.zzbyr;
      if (this.zzbyr == 3) {
         var10000 = "DEFAULT";
      } else {
         var7 = new ArrayList();
         if ((var6 & 1) > 0) {
            var7.add("BROADCAST");
         }

         if ((var6 & 2) > 0) {
            var7.add("SCAN");
         }

         var10000 = var7.isEmpty() ? (new StringBuilder(19)).append("UNKNOWN:").append(var6).toString() : var7.toString();
      }

      String var4 = String.valueOf(var10000);
      var6 = this.zzbys;
      switch(this.zzbys) {
      case 0:
         var10000 = "DEFAULT";
         break;
      case 1:
         var10000 = "ALWAYS_ON";
         break;
      default:
         var10000 = (new StringBuilder(20)).append("UNKNOWN: ").append(var6).toString();
      }

      String var5 = String.valueOf(var10000);
      return (new StringBuilder(102 + String.valueOf(var2).length() + String.valueOf(var3).length() + String.valueOf(var4).length() + String.valueOf(var5).length())).append("Strategy{ttlSeconds=").append(var1).append(", distanceType=").append(var2).append(", discoveryMedium=").append(var3).append(", discoveryMode=").append(var4).append(", backgroundScanMode=").append(var5).append("}").toString();
   }

   static {
      zzbyl = BLE_ONLY = (new Strategy.Builder()).zzbr(2).setTtlSeconds(Integer.MAX_VALUE).build();
   }

   public static class Builder {
      private int zzbyt = 3;
      private int zzbyu = 300;
      private int zzbyv = 0;
      private int zzbyw = -1;
      private int zzbyx = 0;

      public Strategy.Builder setDiscoveryMode(int var1) {
         this.zzbyt = var1;
         return this;
      }

      public final Strategy.Builder zzbr(int var1) {
         this.zzbyw = 2;
         return this;
      }

      public Strategy.Builder setTtlSeconds(int var1) {
         zzbo.zzb(var1 == Integer.MAX_VALUE || var1 > 0 && var1 <= 86400, "mTtlSeconds(%d) must either be TTL_SECONDS_INFINITE, or it must be between 1 and TTL_SECONDS_MAX(%d) inclusive", new Object[]{var1, 86400});
         this.zzbyu = var1;
         return this;
      }

      public Strategy.Builder setDistanceType(int var1) {
         this.zzbyv = var1;
         return this;
      }

      public Strategy build() {
         if (this.zzbyw == 2 && this.zzbyv == 1) {
            throw new IllegalStateException("Cannot set EARSHOT with BLE only mode.");
         } else {
            return new Strategy(2, 0, this.zzbyu, this.zzbyv, false, this.zzbyw, this.zzbyt, 0);
         }
      }
   }
}
