package com.google.android.gms.internal;

import android.app.PendingIntent;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.PendingResults;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.SensorsApi;
import com.google.android.gms.fitness.data.zzt;
import com.google.android.gms.fitness.request.DataSourcesRequest;
import com.google.android.gms.fitness.request.OnDataPointListener;
import com.google.android.gms.fitness.request.SensorRequest;
import com.google.android.gms.fitness.request.zzak;
import com.google.android.gms.fitness.request.zzam;

public final class zzbys implements SensorsApi {
   public final PendingResult findDataSources(GoogleApiClient var1, DataSourcesRequest var2) {
      return var1.zzd(new zzbyt(this, var1, var2));
   }

   public final PendingResult add(GoogleApiClient var1, SensorRequest var2, OnDataPointListener var3) {
      zzak var4 = zzam.zztV().zza(var3);
      return this.zza(var1, (SensorRequest)var2, (zzt)var4, (PendingIntent)null);
   }

   public final PendingResult add(GoogleApiClient var1, SensorRequest var2, PendingIntent var3) {
      return this.zza(var1, (SensorRequest)var2, (zzt)null, (PendingIntent)var3);
   }

   private final PendingResult zza(GoogleApiClient var1, SensorRequest var2, zzt var3, PendingIntent var4) {
      return var1.zzd(new zzbyu(this, var1, var2, var3, var4));
   }

   public final PendingResult remove(GoogleApiClient var1, OnDataPointListener var2) {
      zzak var3;
      return (var3 = zzam.zztV().zzb(var2)) == null ? PendingResults.zza(Status.zzaBm, var1) : this.zza(var1, (zzt)var3, (PendingIntent)null, (zzbyx)(new zzbyv(this, var2)));
   }

   public final PendingResult remove(GoogleApiClient var1, PendingIntent var2) {
      return this.zza(var1, (zzt)null, (PendingIntent)var2, (zzbyx)null);
   }

   private final PendingResult zza(GoogleApiClient var1, zzt var2, PendingIntent var3, zzbyx var4) {
      return var1.zze(new zzbyw(this, var1, var4, var2, var3));
   }
}
