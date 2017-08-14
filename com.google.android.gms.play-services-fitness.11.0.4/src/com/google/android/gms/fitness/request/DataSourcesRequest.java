package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.internal.zzbvy;
import com.google.android.gms.internal.zzbvz;
import java.util.Arrays;
import java.util.List;

public class DataSourcesRequest extends com.google.android.gms.common.internal.safeparcel.zza {
   private final int zzaku;
   private final List zzaTn;
   private final List zzaWG;
   private final boolean zzaWH;
   private final zzbvy zzaWI;
   public static final Creator CREATOR = new zzo();

   DataSourcesRequest(int var1, List var2, List var3, boolean var4, IBinder var5) {
      this.zzaku = var1;
      this.zzaTn = var2;
      this.zzaWG = var3;
      this.zzaWH = var4;
      this.zzaWI = zzbvz.zzQ(var5);
   }

   private DataSourcesRequest(DataSourcesRequest.Builder var1) {
      this(com.google.android.gms.common.util.zzb.zza(var1.zzaWJ), Arrays.asList(com.google.android.gms.common.util.zzb.zza(var1.zzaWK)), false, (zzbvy)null);
   }

   public DataSourcesRequest(DataSourcesRequest var1, zzbvy var2) {
      this(var1.zzaTn, var1.zzaWG, var1.zzaWH, var2);
   }

   private DataSourcesRequest(List var1, List var2, boolean var3, zzbvy var4) {
      this.zzaku = 4;
      this.zzaTn = var1;
      this.zzaWG = var2;
      this.zzaWH = var3;
      this.zzaWI = var4;
   }

   public List getDataTypes() {
      return this.zzaTn;
   }

   public String toString() {
      com.google.android.gms.common.internal.zzbg var1 = com.google.android.gms.common.internal.zzbe.zzt(this).zzg("dataTypes", this.zzaTn).zzg("sourceTypes", this.zzaWG);
      if (this.zzaWH) {
         var1.zzg("includeDbOnlySources", "true");
      }

      return var1.toString();
   }

   public void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1, this.getDataTypes(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzaWG, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.zzaWH);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.zzaWI == null ? null : this.zzaWI.asBinder(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1000, this.zzaku);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   // $FF: synthetic method
   DataSourcesRequest(DataSourcesRequest.Builder var1, zzn var2) {
      this(var1);
   }

   public static class Builder {
      private DataType[] zzaWJ = new DataType[0];
      private int[] zzaWK = new int[]{0, 1};
      private boolean zzaWH = false;

      public DataSourcesRequest.Builder setDataTypes(DataType... var1) {
         this.zzaWJ = var1;
         return this;
      }

      public DataSourcesRequest.Builder setDataSourceTypes(int... var1) {
         this.zzaWK = var1;
         return this;
      }

      public DataSourcesRequest build() {
         zzbo.zza(this.zzaWJ.length > 0, "Must add at least one data type");
         zzbo.zza(this.zzaWK.length > 0, "Must add at least one data source type");
         return new DataSourcesRequest(this, (zzn)null);
      }
   }
}
