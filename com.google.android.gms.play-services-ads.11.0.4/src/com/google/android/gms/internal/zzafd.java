package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import java.io.IOException;

final class zzafd implements Runnable {
   // $FF: synthetic field
   private Context zztF;
   // $FF: synthetic field
   private zzajg zzXK;

   zzafd(zzafc var1, Context var2, zzajg var3) {
      this.zztF = var2;
      this.zzXK = var3;
      super();
   }

   public final void run() {
      try {
         Info var1 = AdvertisingIdClient.getAdvertisingIdInfo(this.zztF);
         this.zzXK.zzg(var1);
      } catch (IllegalStateException | GooglePlayServicesNotAvailableException | GooglePlayServicesRepairableException | IOException var3) {
         this.zzXK.zzb(var3);
         zzajc.zzb("Exception while getting advertising Id info", var3);
      }
   }
}
