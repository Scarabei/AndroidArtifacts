package com.google.android.gms.common.internal;

import android.support.annotation.NonNull;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.common.api.Status;

public final class zzb {
   @NonNull
   public static ApiException zzx(@NonNull Status var0) {
      return (ApiException)(var0.hasResolution() ? new ResolvableApiException(var0) : new ApiException(var0));
   }
}
