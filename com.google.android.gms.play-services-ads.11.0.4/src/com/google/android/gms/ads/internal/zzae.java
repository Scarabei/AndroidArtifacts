package com.google.android.gms.ads.internal;

import android.content.Context;
import com.google.android.gms.internal.zzajr;
import com.google.android.gms.internal.zzcu;
import com.google.android.gms.internal.zzrd;

final class zzae implements Runnable {
   // $FF: synthetic field
   private com.google.android.gms.ads.internal.js.zzl zztA;
   // $FF: synthetic field
   final zzrd zztB;
   // $FF: synthetic field
   final String zztC;
   // $FF: synthetic field
   final String zztD;
   // $FF: synthetic field
   final boolean zztE;
   // $FF: synthetic field
   final Context zztF;

   zzae(zzac var1, com.google.android.gms.ads.internal.js.zzl var2, zzrd var3, String var4, String var5, boolean var6, Context var7) {
      this.zztA = var2;
      this.zztB = var3;
      this.zztC = var4;
      this.zztD = var5;
      this.zztE = var6;
      this.zztF = var7;
      super();
   }

   public final void run() {
      this.zztA.zzb((zzcu)null).zza(new zzaf(this), new zzajr());
   }
}
