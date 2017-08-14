package com.google.android.gms.internal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import java.util.Set;
import java.util.Timer;

final class zzaen implements ConnectionCallbacks {
   // $FF: synthetic field
   final Set zzXl;
   // $FF: synthetic field
   final zzajg zzXk;
   // $FF: synthetic field
   final zzael zzXm;

   zzaen(zzael var1, Set var2, zzajg var3) {
      this.zzXm = var1;
      this.zzXl = var2;
      this.zzXk = var3;
   }

   public final void onConnected(@Nullable Bundle var1) {
      try {
         Timer var5 = zzael.zzb(this.zzXm);
         zzaeo var6 = new zzaeo(this);
         zzme var3 = zzmo.zzGd;
         var5.schedule(var6, ((Long)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var3)).longValue());
      } catch (IllegalStateException var4) {
         String var10001 = String.valueOf(var4.getMessage());
         String var10000;
         if (var10001.length() != 0) {
            var10000 = "Cannot schedule thread: ".concat(var10001);
         } else {
            String var10002 = new String;
            var10000 = var10002;
            var10002.<init>("Cannot schedule thread: ");
         }

         zzaes.zzaC(var10000);
      }
   }

   public final void onConnectionSuspended(int var1) {
   }
}
