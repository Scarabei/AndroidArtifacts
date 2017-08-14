package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.common.util.zzi;
import java.util.HashMap;
import java.util.Map;

public final class ef {
   private final Context mContext;
   private final ep zzbLI;
   private final zze zzvw;
   private String zzbEa;
   private Map zzbKw;
   private final Map zzbKx;

   public ef(Context var1) {
      this(var1, new HashMap(), new ep(var1), zzi.zzrY());
   }

   private ef(Context var1, Map var2, ep var3, zze var4) {
      this.zzbEa = null;
      this.zzbKw = new HashMap();
      this.mContext = var1;
      this.zzvw = var4;
      this.zzbLI = var3;
      this.zzbKx = var2;
   }

   public final void zzgc(String var1) {
      this.zzbEa = var1;
   }
}
