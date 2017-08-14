package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.js.zzy;
import java.util.HashMap;
import java.util.concurrent.Future;

@zzzn
public final class zzabu {
   private final Object mLock = new Object();
   private String zzQx;
   private String zzUL;
   private zzajg zzUM = new zzajg();
   zzy zzUN;
   public final zzrd zzUO = new zzabv(this);
   public final zzrd zzUP = new zzabw(this);
   public final zzrd zzUQ = new zzabx(this);

   public zzabu(String var1, String var2) {
      this.zzUL = var2;
      this.zzQx = var1;
   }

   public final void fail() {
      zzaca var1 = new zzaca(0, new HashMap());
      this.zzUM.zzg(var1);
   }

   public final Future zzgG() {
      return this.zzUM;
   }

   // $FF: synthetic method
   static Object zza(zzabu var0) {
      return var0.mLock;
   }

   // $FF: synthetic method
   static zzajg zzb(zzabu var0) {
      return var0.zzUM;
   }

   // $FF: synthetic method
   static String zzc(zzabu var0) {
      return var0.zzQx;
   }

   // $FF: synthetic method
   static String zzd(zzabu var0) {
      return var0.zzUL;
   }
}
