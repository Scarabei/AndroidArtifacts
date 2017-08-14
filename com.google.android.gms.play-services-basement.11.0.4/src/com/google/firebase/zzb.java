package com.google.firebase;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.zzbem;

public final class zzb implements zzbem {
   public final Exception zzq(Status var1) {
      return (Exception)(var1.getStatusCode() == 8 ? new FirebaseException(var1.zzpq()) : new FirebaseApiNotAvailableException(var1.zzpq()));
   }
}
