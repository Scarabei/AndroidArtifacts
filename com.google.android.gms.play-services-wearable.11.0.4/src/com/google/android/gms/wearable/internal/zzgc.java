package com.google.android.gms.wearable.internal;

import com.google.android.gms.internal.zzbdz;
import com.google.android.gms.wearable.MessageApi;

final class zzgc implements zzbdz {
   // $FF: synthetic field
   private zzdx zzbRB;

   zzgc(zzdx var1) {
      this.zzbRB = var1;
      super();
   }

   public final void zzpT() {
   }

   // $FF: synthetic method
   public final void zzq(Object var1) {
      MessageApi.MessageListener var3 = (MessageApi.MessageListener)var1;
      var3.onMessageReceived(this.zzbRB);
   }
}
