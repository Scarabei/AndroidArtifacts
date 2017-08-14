package com.google.android.gms.internal;

import com.google.android.gms.cast.Cast;
import com.google.android.gms.cast.CastDevice;

final class zzaxa implements Cast.MessageReceivedCallback {
   // $FF: synthetic field
   private zzawz zzaxe;

   zzaxa(zzawz var1) {
      this.zzaxe = var1;
      super();
   }

   public final void onMessageReceived(CastDevice var1, String var2, String var3) {
      this.zzaxe.zzaxd.zzch(var3);
   }
}
