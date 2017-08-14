package com.google.android.gms.internal;

import android.support.annotation.NonNull;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.safetynet.SafetyNetApi.SafeBrowsingResult;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

final class zzaep implements ResultCallback {
   // $FF: synthetic field
   private Map zzXo;
   // $FF: synthetic field
   private String zzsD;
   // $FF: synthetic field
   private AtomicInteger zzXp;
   // $FF: synthetic field
   private GoogleApiClient zzXq;
   // $FF: synthetic field
   private zzajg zzXk;

   zzaep(zzael var1, Map var2, String var3, AtomicInteger var4, GoogleApiClient var5, zzajg var6) {
      this.zzXo = var2;
      this.zzsD = var3;
      this.zzXp = var4;
      this.zzXq = var5;
      this.zzXk = var6;
      super();
   }

   // $FF: synthetic method
   public final void onResult(@NonNull Result var1) {
      SafeBrowsingResult var3 = (SafeBrowsingResult)var1;
      Status var4;
      if ((var4 = var3.getStatus()) != null && var4.isSuccess() && var3.getMetadata() != null) {
         this.zzXo.put(this.zzsD, var3.getMetadata());
      } else {
         String var5 = String.valueOf(var4);
         zzaes.zzaC((new StringBuilder(36 + String.valueOf(var5).length())).append("SafeBrowsing lookup failed. Status: ").append(var5).toString());
      }

      if (this.zzXp.decrementAndGet() == 0) {
         this.zzXq.disconnect();
         this.zzXk.zzg(this.zzXo);
      }

   }
}
