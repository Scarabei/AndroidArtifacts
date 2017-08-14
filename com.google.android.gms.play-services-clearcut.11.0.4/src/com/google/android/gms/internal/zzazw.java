package com.google.android.gms.internal;

import android.content.Context;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Api.ApiOptions;

public final class zzazw extends GoogleApi implements zzazt {
   private zzazw(@NonNull Context var1) {
      super(var1, zzazn.API, (ApiOptions)null, new zzbas());
   }

   public static zzazt zzaq(@NonNull Context var0) {
      return new zzazw(var0);
   }

   public final PendingResult zza(zzazu var1) {
      return this.zzc(new zzazz(var1, this.zzpi()));
   }
}
