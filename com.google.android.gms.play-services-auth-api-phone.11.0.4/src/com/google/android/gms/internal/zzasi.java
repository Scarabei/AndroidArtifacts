package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import com.google.android.gms.auth.api.phone.SmsRetrieverClient;
import com.google.android.gms.tasks.Task;

public final class zzasi extends SmsRetrieverClient {
   public zzasi(@NonNull Context var1) {
      super(var1);
   }

   public zzasi(@NonNull Activity var1) {
      super(var1);
   }

   public final Task startSmsRetriever() {
      return this.zzb(new zzasj(this));
   }
}
