package com.google.android.gms.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.fitness.RecordingApi;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Subscription;

public final class zzbyl implements RecordingApi {
   public final PendingResult listSubscriptions(GoogleApiClient var1) {
      return var1.zzd(new zzbym(this, var1));
   }

   public final PendingResult listSubscriptions(GoogleApiClient var1, DataType var2) {
      return var1.zzd(new zzbyn(this, var1, var2));
   }

   private final PendingResult zza(GoogleApiClient var1, Subscription var2) {
      return var1.zzd(new zzbyo(this, var1, var2));
   }

   public final PendingResult subscribe(GoogleApiClient var1, DataType var2) {
      return this.zza(var1, (new Subscription.zza()).zza(var2).zztQ());
   }

   public final PendingResult subscribe(GoogleApiClient var1, DataSource var2) {
      return this.zza(var1, (new Subscription.zza()).zza(var2).zztQ());
   }

   public final PendingResult unsubscribe(GoogleApiClient var1, DataType var2) {
      return var1.zze(new zzbyp(this, var1, var2));
   }

   public final PendingResult unsubscribe(GoogleApiClient var1, DataSource var2) {
      return var1.zze(new zzbyq(this, var1, var2));
   }

   public final PendingResult unsubscribe(GoogleApiClient var1, Subscription var2) {
      return var2.getDataType() == null ? this.unsubscribe(var1, var2.getDataSource()) : this.unsubscribe(var1, var2.getDataType());
   }
}
