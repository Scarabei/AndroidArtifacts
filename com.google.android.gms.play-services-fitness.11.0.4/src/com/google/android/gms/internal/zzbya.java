package com.google.android.gms.internal;

import android.app.PendingIntent;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.fitness.HistoryApi;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.request.DataDeleteRequest;
import com.google.android.gms.fitness.request.DataReadRequest;
import com.google.android.gms.fitness.request.DataUpdateListenerRegistrationRequest;
import com.google.android.gms.fitness.request.DataUpdateRequest;

public final class zzbya implements HistoryApi {
   public final PendingResult insertData(GoogleApiClient var1, DataSet var2) {
      zzbo.zzb(var2, "Must set the data set");
      zzbo.zza(!var2.getDataPoints().isEmpty(), "Cannot use an empty data set");
      zzbo.zzb(var2.getDataSource().zztM(), "Must set the app package name for the data source");
      return var1.zzd(new zzbyb(this, var1, var2, false));
   }

   public final PendingResult deleteData(GoogleApiClient var1, DataDeleteRequest var2) {
      return var1.zzd(new zzbyc(this, var1, var2));
   }

   public final PendingResult updateData(GoogleApiClient var1, DataUpdateRequest var2) {
      zzbo.zzb(var2.getDataSet(), "Must set the data set");
      zzbo.zza(var2.zzmc(), "Must set a non-zero value for startTimeMillis/startTime");
      zzbo.zza(var2.zztU(), "Must set a non-zero value for endTimeMillis/endTime");
      return var1.zzd(new zzbyd(this, var1, var2));
   }

   public final PendingResult registerDataUpdateListener(GoogleApiClient var1, DataUpdateListenerRegistrationRequest var2) {
      return var1.zzd(new zzbye(this, var1, var2));
   }

   public final PendingResult unregisterDataUpdateListener(GoogleApiClient var1, PendingIntent var2) {
      return var1.zze(new zzbyf(this, var1, var2));
   }

   public final PendingResult readData(GoogleApiClient var1, DataReadRequest var2) {
      return var1.zzd(new zzbyg(this, var1, var2));
   }

   public final PendingResult readDailyTotal(GoogleApiClient var1, DataType var2) {
      return this.zza(var1, var2, false);
   }

   public final PendingResult readDailyTotalFromLocalDevice(GoogleApiClient var1, DataType var2) {
      return this.zza(var1, var2, true);
   }

   private final PendingResult zza(GoogleApiClient var1, DataType var2, boolean var3) {
      return var1.zzd(new zzbyh(this, var1, var2, var3));
   }
}
