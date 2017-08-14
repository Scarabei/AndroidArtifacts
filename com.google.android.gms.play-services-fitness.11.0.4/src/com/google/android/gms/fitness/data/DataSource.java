package com.google.android.gms.fitness.data;

import android.content.Context;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbo;

public class DataSource extends com.google.android.gms.common.internal.safeparcel.zza {
   private static final int[] zzaTE = new int[0];
   public static final String EXTRA_DATA_SOURCE = "vnd.google.fitness.data_source";
   public static final int TYPE_RAW = 0;
   public static final int TYPE_DERIVED = 1;
   public static final int DATA_QUALITY_BLOOD_PRESSURE_ESH2002 = 1;
   public static final int DATA_QUALITY_BLOOD_PRESSURE_ESH2010 = 2;
   public static final int DATA_QUALITY_BLOOD_PRESSURE_AAMI = 3;
   public static final int DATA_QUALITY_BLOOD_PRESSURE_BHS_A_A = 4;
   public static final int DATA_QUALITY_BLOOD_PRESSURE_BHS_A_B = 5;
   public static final int DATA_QUALITY_BLOOD_PRESSURE_BHS_B_A = 6;
   public static final int DATA_QUALITY_BLOOD_PRESSURE_BHS_B_B = 7;
   public static final int DATA_QUALITY_BLOOD_GLUCOSE_ISO151972003 = 8;
   public static final int DATA_QUALITY_BLOOD_GLUCOSE_ISO151972013 = 9;
   private final int versionCode;
   private final DataType zzaSZ;
   private final String name;
   private final int type;
   private final Device zzaTF;
   private final zzb zzaTG;
   private final String zzaTH;
   private final int[] zzaTI;
   private final String zzaTJ;
   public static final Creator CREATOR = new zzk();

   DataSource(int var1, DataType var2, String var3, int var4, Device var5, zzb var6, String var7, int[] var8) {
      this.versionCode = var1;
      this.zzaSZ = var2;
      this.type = var4;
      this.name = var3;
      this.zzaTF = var5;
      this.zzaTG = var6;
      this.zzaTH = var7;
      this.zzaTJ = this.zztN();
      this.zzaTI = var8 != null ? var8 : zzaTE;
   }

   private DataSource(DataSource.Builder var1) {
      this.versionCode = 3;
      this.zzaSZ = var1.zzaSZ;
      this.type = var1.type;
      this.name = var1.name;
      this.zzaTF = var1.zzaTF;
      this.zzaTG = var1.zzaTG;
      this.zzaTH = var1.zzaTH;
      this.zzaTJ = this.zztN();
      this.zzaTI = var1.zzaTI;
   }

   public static DataSource extract(Intent var0) {
      return var0 == null ? null : (DataSource)com.google.android.gms.common.internal.safeparcel.zze.zza(var0, "vnd.google.fitness.data_source", CREATOR);
   }

   public DataType getDataType() {
      return this.zzaSZ;
   }

   public int getType() {
      return this.type;
   }

   public String getName() {
      return this.name;
   }

   public String getAppPackageName() {
      return this.zzaTG == null ? null : this.zzaTG.getPackageName();
   }

   public final zzb zztM() {
      return this.zzaTG;
   }

   public Device getDevice() {
      return this.zzaTF;
   }

   public String getStreamName() {
      return this.zzaTH;
   }

   public int[] getDataQualityStandards() {
      return this.zzaTI;
   }

   public String getStreamIdentifier() {
      return this.zzaTJ;
   }

   private final String zztN() {
      StringBuilder var1;
      (var1 = new StringBuilder()).append(this.getTypeString());
      var1.append(":").append(this.zzaSZ.getName());
      if (this.zzaTG != null) {
         var1.append(":").append(this.zzaTG.getPackageName());
      }

      if (this.zzaTF != null) {
         var1.append(":").append(this.zzaTF.getStreamIdentifier());
      }

      if (this.zzaTH != null) {
         var1.append(":").append(this.zzaTH);
      }

      return var1.toString();
   }

   public boolean equals(Object var1) {
      if (this != var1) {
         if (var1 instanceof DataSource) {
            DataSource var2 = (DataSource)var1;
            if (this.zzaTJ.equals(var2.zzaTJ)) {
               return true;
            }
         }

         return false;
      } else {
         return true;
      }
   }

   public int hashCode() {
      return this.zzaTJ.hashCode();
   }

   public String toString() {
      StringBuilder var1;
      (var1 = new StringBuilder("DataSource{")).append(this.getTypeString());
      if (this.name != null) {
         var1.append(":").append(this.name);
      }

      if (this.zzaTG != null) {
         var1.append(":").append(this.zzaTG);
      }

      if (this.zzaTF != null) {
         var1.append(":").append(this.zzaTF);
      }

      if (this.zzaTH != null) {
         var1.append(":").append(this.zzaTH);
      }

      var1.append(":").append(this.zzaSZ);
      return var1.append("}").toString();
   }

   public final String toDebugString() {
      String var10000;
      switch(this.type) {
      case 0:
         var10000 = "r";
         break;
      case 1:
         var10000 = "d";
         break;
      case 2:
         var10000 = "c";
         break;
      case 3:
         var10000 = "v";
         break;
      default:
         var10000 = "?";
      }

      String var1 = String.valueOf(var10000);
      String var2 = String.valueOf(this.zzaSZ.zztO());
      String var10001;
      String var10002;
      if (this.zzaTG == null) {
         var10000 = "";
      } else if (this.zzaTG.equals(zzb.zzaTj)) {
         var10000 = ":gms";
      } else {
         var10001 = String.valueOf(this.zzaTG.getPackageName());
         if (var10001.length() != 0) {
            var10000 = ":".concat(var10001);
         } else {
            var10002 = new String;
            var10000 = var10002;
            var10002.<init>(":");
         }
      }

      String var3 = var10000;
      String var4;
      String var5;
      if (this.zzaTF != null) {
         var4 = String.valueOf(this.zzaTF.getModel());
         var5 = String.valueOf(this.zzaTF.getUid());
         var10000 = (new StringBuilder(2 + String.valueOf(var4).length() + String.valueOf(var5).length())).append(":").append(var4).append(":").append(var5).toString();
      } else {
         var10000 = "";
      }

      var4 = var10000;
      if (this.zzaTH != null) {
         var10001 = String.valueOf(this.zzaTH);
         if (var10001.length() != 0) {
            var10000 = ":".concat(var10001);
         } else {
            var10002 = new String;
            var10000 = var10002;
            var10002.<init>(":");
         }
      } else {
         var10000 = "";
      }

      var5 = var10000;
      return (new StringBuilder(1 + String.valueOf(var1).length() + String.valueOf(var2).length() + String.valueOf(var3).length() + String.valueOf(var4).length() + String.valueOf(var5).length())).append(var1).append(":").append(var2).append(var3).append(var4).append(var5).toString();
   }

   public static String zzaV(int var0) {
      switch(var0) {
      case 1:
         return "blood_pressure_esh2002";
      case 2:
         return "blood_pressure_esh2010";
      case 3:
         return "blood_pressure_aami";
      case 4:
         return "blood_pressure_bhs_a_a";
      case 5:
         return "blood_pressure_bhs_a_b";
      case 6:
         return "blood_pressure_bhs_b_a";
      case 7:
         return "blood_pressure_bhs_b_b";
      case 8:
         return "blood_glucose_iso151972003";
      case 9:
         return "blood_glucose_iso151972013";
      default:
         return "unknown";
      }
   }

   private final String getTypeString() {
      switch(this.type) {
      case 0:
         return "raw";
      case 1:
         return "derived";
      case 2:
         return "cleaned";
      case 3:
         return "converted";
      default:
         return "derived";
      }
   }

   public void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 1, this.getDataType(), var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.getName(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 3, this.getType());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.getDevice(), var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 5, this.zzaTG, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 6, this.getStreamName(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1000, this.versionCode);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 8, this.getDataQualityStandards(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   // $FF: synthetic method
   DataSource(DataSource.Builder var1, zzj var2) {
      this(var1);
   }

   public static final class Builder {
      private DataType zzaSZ;
      private int type = -1;
      private String name;
      private Device zzaTF;
      private zzb zzaTG;
      private String zzaTH = "";
      private int[] zzaTI;

      public final DataSource.Builder setDataType(DataType var1) {
         this.zzaSZ = var1;
         return this;
      }

      public final DataSource.Builder setType(int var1) {
         this.type = var1;
         return this;
      }

      public final DataSource.Builder setName(String var1) {
         this.name = var1;
         return this;
      }

      public final DataSource.Builder setDevice(Device var1) {
         this.zzaTF = var1;
         return this;
      }

      public final DataSource.Builder setAppPackageName(String var1) {
         this.zzaTG = zzb.zzcX(var1);
         return this;
      }

      public final DataSource.Builder setAppPackageName(Context var1) {
         return this.setAppPackageName(var1.getPackageName());
      }

      public final DataSource.Builder setStreamName(String var1) {
         zzbo.zzb(var1 != null, "Must specify a valid stream name");
         this.zzaTH = var1;
         return this;
      }

      public final DataSource.Builder setDataQualityStandards(int... var1) {
         this.zzaTI = var1;
         return this;
      }

      public final DataSource build() {
         zzbo.zza(this.zzaSZ != null, "Must set data type");
         zzbo.zza(this.type >= 0, "Must set data source type");
         return new DataSource(this, (zzj)null);
      }
   }
}
