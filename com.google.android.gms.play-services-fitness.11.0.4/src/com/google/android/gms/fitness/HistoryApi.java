package com.google.android.gms.fitness;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.common.internal.safeparcel.zze;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.request.DataDeleteRequest;
import com.google.android.gms.fitness.request.DataReadRequest;
import com.google.android.gms.fitness.request.DataUpdateListenerRegistrationRequest;
import com.google.android.gms.fitness.request.DataUpdateRequest;
import java.util.concurrent.TimeUnit;

public interface HistoryApi {
   @RequiresPermission(
      anyOf = {"android.permission.ACCESS_FINE_LOCATION", "android.permission.BODY_SENSORS"},
      conditional = true
   )
   PendingResult readData(GoogleApiClient var1, DataReadRequest var2);

   PendingResult readDailyTotal(GoogleApiClient var1, DataType var2);

   PendingResult readDailyTotalFromLocalDevice(GoogleApiClient var1, DataType var2);

   PendingResult insertData(GoogleApiClient var1, DataSet var2);

   PendingResult deleteData(GoogleApiClient var1, DataDeleteRequest var2);

   PendingResult updateData(GoogleApiClient var1, DataUpdateRequest var2);

   @RequiresPermission(
      anyOf = {"android.permission.ACCESS_FINE_LOCATION", "android.permission.BODY_SENSORS"},
      conditional = true
   )
   PendingResult registerDataUpdateListener(GoogleApiClient var1, DataUpdateListenerRegistrationRequest var2);

   PendingResult unregisterDataUpdateListener(GoogleApiClient var1, PendingIntent var2);

   public static class ViewIntentBuilder {
      private final Context zzqD;
      private final DataType zzaSZ;
      private DataSource zzaTa;
      private long zzaTb;
      private long zzaTc;
      private String zzaTd;

      public ViewIntentBuilder(Context var1, DataType var2) {
         this.zzqD = var1;
         this.zzaSZ = var2;
      }

      public HistoryApi.ViewIntentBuilder setTimeInterval(long var1, long var3, TimeUnit var5) {
         this.zzaTb = var5.toMillis(var1);
         this.zzaTc = var5.toMillis(var3);
         return this;
      }

      public HistoryApi.ViewIntentBuilder setDataSource(DataSource var1) {
         zzbo.zzb(var1.getDataType().equals(this.zzaSZ), "Data source %s is not for the data type %s", new Object[]{var1, this.zzaSZ});
         this.zzaTa = var1;
         return this;
      }

      public HistoryApi.ViewIntentBuilder setPreferredApplication(String var1) {
         this.zzaTd = var1;
         return this;
      }

      public Intent build() {
         zzbo.zza(this.zzaTb > 0L, "Start time must be set");
         zzbo.zza(this.zzaTc > this.zzaTb, "End time must be set and after start time");
         Intent var1;
         (var1 = new Intent("vnd.google.fitness.VIEW")).setType(DataType.getMimeType(this.zzaTa.getDataType()));
         var1.putExtra("vnd.google.fitness.start_time", this.zzaTb);
         var1.putExtra("vnd.google.fitness.end_time", this.zzaTc);
         zze.zza(this.zzaTa, var1, "vnd.google.fitness.data_source");
         if (this.zzaTd != null) {
            Intent var4 = (new Intent(var1)).setPackage(this.zzaTd);
            ResolveInfo var5;
            if ((var5 = this.zzqD.getPackageManager().resolveActivity(var4, 0)) != null) {
               String var6 = var5.activityInfo.name;
               var4.setComponent(new ComponentName(this.zzaTd, var6));
               return var4;
            }
         }

         return var1;
      }
   }
}
