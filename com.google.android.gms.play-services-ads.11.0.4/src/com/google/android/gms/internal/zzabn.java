package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.js.zzl;
import com.google.android.gms.ads.internal.js.zzy;

final class zzabn implements Runnable {
   // $FF: synthetic field
   private zzl zztA;
   // $FF: synthetic field
   final zzabu zzUA;
   // $FF: synthetic field
   final zznb zztd;
   // $FF: synthetic field
   private zzmz zzUB;
   // $FF: synthetic field
   final String zzUC;

   zzabn(zzl var1, zzabu var2, zznb var3, zzmz var4, String var5) {
      this.zztA = var1;
      this.zzUA = var2;
      this.zztd = var3;
      this.zzUB = var4;
      this.zzUC = var5;
      super();
   }

   public final void run() {
      zzy var1 = this.zztA.zzb((zzcu)null);
      this.zzUA.zzUN = var1;
      this.zztd.zza(this.zzUB, "rwc");
      zzmz var2 = this.zztd.zzdS();
      var1.zza(new zzabo(this, var2), new zzabp(this));
   }
}
