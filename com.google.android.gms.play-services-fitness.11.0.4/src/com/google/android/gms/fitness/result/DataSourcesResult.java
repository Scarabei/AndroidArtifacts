package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbe;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class DataSourcesResult extends com.google.android.gms.common.internal.safeparcel.zza implements Result {
   private final int versionCode;
   private final List zzaXy;
   private final Status zzajl;
   public static final Creator CREATOR = new zzd();

   DataSourcesResult(int var1, List var2, Status var3) {
      this.versionCode = var1;
      this.zzaXy = Collections.unmodifiableList(var2);
      this.zzajl = var3;
   }

   public DataSourcesResult(List var1, Status var2) {
      this.versionCode = 3;
      this.zzaXy = Collections.unmodifiableList(var1);
      this.zzajl = var2;
   }

   public List getDataSources() {
      return this.zzaXy;
   }

   public List getDataSources(DataType var1) {
      ArrayList var2 = new ArrayList();
      Iterator var3 = this.zzaXy.iterator();

      while(var3.hasNext()) {
         DataSource var4;
         if ((var4 = (DataSource)var3.next()).getDataType().equals(var1)) {
            var2.add(var4);
         }
      }

      return Collections.unmodifiableList(var2);
   }

   public Status getStatus() {
      return this.zzajl;
   }

   public boolean equals(Object var1) {
      if (this != var1) {
         if (var1 instanceof DataSourcesResult) {
            DataSourcesResult var3 = (DataSourcesResult)var1;
            if (this.zzajl.equals(var3.zzajl) && zzbe.equal(this.zzaXy, var3.zzaXy)) {
               return true;
            }
         }

         return false;
      } else {
         return true;
      }
   }

   public int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzajl, this.zzaXy});
   }

   public String toString() {
      return zzbe.zzt(this).zzg("status", this.zzajl).zzg("dataSources", this.zzaXy).toString();
   }

   public void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1, this.getDataSources(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.getStatus(), var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1000, this.versionCode);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
