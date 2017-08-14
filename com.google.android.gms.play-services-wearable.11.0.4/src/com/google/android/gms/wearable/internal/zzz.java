package com.google.android.gms.wearable.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.wearable.CapabilityApi;

final class zzz extends zzn {
   private CapabilityApi.CapabilityListener zzbRY;

   private zzz(GoogleApiClient var1, CapabilityApi.CapabilityListener var2) {
      super(var1);
      this.zzbRY = var2;
   }

   // $FF: synthetic method
   protected final void zza(com.google.android.gms.common.api.Api.zzb var1) throws RemoteException {
      zzfw var3 = (zzfw)var1;
      var3.zza(this, (CapabilityApi.CapabilityListener)this.zzbRY);
      this.zzbRY = null;
   }

   // $FF: synthetic method
   public final Result zzb(Status var1) {
      this.zzbRY = null;
      return var1;
   }

   // $FF: synthetic method
   zzz(GoogleApiClient var1, CapabilityApi.CapabilityListener var2, zzp var3) {
      this(var1, var2);
   }
}
