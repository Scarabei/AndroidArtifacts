package com.google.android.gms.ads.internal.js;

import android.content.Context;
import com.google.android.gms.internal.zzaje;
import com.google.android.gms.internal.zzakm;
import com.google.android.gms.internal.zzcu;

final class zzc implements Runnable {
   // $FF: synthetic field
   private Context zztF;
   // $FF: synthetic field
   private zzaje zzKO;
   // $FF: synthetic field
   private zzcu zzKP;
   // $FF: synthetic field
   private com.google.android.gms.ads.internal.zzv zzKQ;
   // $FF: synthetic field
   final JavascriptEngineFactory.JSEngineSettableFuture zzKR;
   // $FF: synthetic field
   private String zzKS;

   zzc(JavascriptEngineFactory var1, Context var2, zzaje var3, zzcu var4, com.google.android.gms.ads.internal.zzv var5, JavascriptEngineFactory.JSEngineSettableFuture var6, String var7) {
      this.zztF = var2;
      this.zzKO = var3;
      this.zzKP = var4;
      this.zzKQ = var5;
      this.zzKR = var6;
      this.zzKS = var7;
      super();
   }

   public final void run() {
      try {
         zze var1 = new zze(this.zztF, this.zzKO, this.zzKP, this.zzKQ);
         this.zzKR.mEngineReference = var1;
         var1.zza(new zzd(this));
         var1.zzad(this.zzKS);
      } catch (zzakm var2) {
         this.zzKR.zzb(var2);
      }
   }
}
