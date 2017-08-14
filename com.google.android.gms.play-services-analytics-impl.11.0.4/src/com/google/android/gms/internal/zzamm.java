package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbo;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class zzamm {
   private final long zzagc;
   private final String zzafd;
   private final String zzagd;
   private final boolean zzage;
   private long zzagf;
   private final Map zzHa;

   public zzamm(long var1, String var3, String var4, boolean var5, long var6, Map var8) {
      zzbo.zzcF(var3);
      zzbo.zzcF(var4);
      this.zzagc = 0L;
      this.zzafd = var3;
      this.zzagd = var4;
      this.zzage = var5;
      this.zzagf = var6;
      if (var8 != null) {
         this.zzHa = new HashMap(var8);
      } else {
         this.zzHa = Collections.emptyMap();
      }
   }

   public final long zzkK() {
      return this.zzagc;
   }

   public final String zzjX() {
      return this.zzafd;
   }

   public final String zzkL() {
      return this.zzagd;
   }

   public final boolean zzkM() {
      return this.zzage;
   }

   public final long zzkN() {
      return this.zzagf;
   }

   public final void zzm(long var1) {
      this.zzagf = var1;
   }

   public final Map zzdV() {
      return this.zzHa;
   }
}
