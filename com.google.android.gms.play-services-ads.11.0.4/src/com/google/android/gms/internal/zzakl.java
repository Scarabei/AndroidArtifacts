package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.zzbl;
import com.google.android.gms.ads.internal.zzv;
import java.util.concurrent.Callable;

final class zzakl implements Callable {
   // $FF: synthetic field
   private Context zztF;
   // $FF: synthetic field
   private zziv zzAG;
   // $FF: synthetic field
   private boolean zzabL;
   // $FF: synthetic field
   private boolean zzabM;
   // $FF: synthetic field
   private zzcu zzLf;
   // $FF: synthetic field
   private zzaje zzKO;
   // $FF: synthetic field
   private zznb zzabN;
   // $FF: synthetic field
   private zzbl zzabO;
   // $FF: synthetic field
   private zzv zzKQ;
   // $FF: synthetic field
   private zzig zzabP;

   zzakl(zzakk var1, Context var2, zziv var3, boolean var4, boolean var5, zzcu var6, zzaje var7, zznb var8, zzbl var9, zzv var10, zzig var11) {
      this.zztF = var2;
      this.zzAG = var3;
      this.zzabL = var4;
      this.zzabM = var5;
      this.zzLf = var6;
      this.zzKO = var7;
      this.zzabN = var8;
      this.zzabO = var9;
      this.zzKQ = var10;
      this.zzabP = var11;
      super();
   }

   // $FF: synthetic method
   public final Object call() throws Exception {
      zzakn var2;
      (var2 = new zzakn(zzako.zzb(this.zztF, this.zzAG, this.zzabL, this.zzabM, this.zzLf, this.zzKO, this.zzabN, this.zzabO, this.zzKQ, this.zzabP))).setWebViewClient(com.google.android.gms.ads.internal.zzbs.zzbB().zzb(var2, this.zzabM));
      var2.setWebChromeClient(com.google.android.gms.ads.internal.zzbs.zzbB().zzm(var2));
      return var2;
   }
}
