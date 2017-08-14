package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import org.json.JSONObject;

@zzzn
public final class zzafg {
   public final zzaae zzUj;
   public final zzaai zzXY;
   public final zzub zzXN;
   @Nullable
   public final zziv zzvX;
   public final int errorCode;
   public final long zzXR;
   public final long zzXS;
   @Nullable
   public final JSONObject zzXL;
   public final zzig zzXX;

   public zzafg(zzaae var1, zzaai var2, zzub var3, zziv var4, int var5, long var6, long var8, JSONObject var10, zzij var11) {
      this.zzUj = var1;
      this.zzXY = var2;
      this.zzXN = var3;
      this.zzvX = var4;
      this.errorCode = var5;
      this.zzXR = var6;
      this.zzXS = var8;
      this.zzXL = var10;
      zzme var12 = zzmo.zzGC;
      this.zzXX = new zzig(var11, ((Boolean)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var12)).booleanValue());
   }

   public zzafg(zzaae var1, zzaai var2, zzub var3, zziv var4, int var5, long var6, long var8, JSONObject var10, zzig var11) {
      this.zzUj = var1;
      this.zzXY = var2;
      this.zzXN = var3;
      this.zzvX = var4;
      this.errorCode = var5;
      this.zzXR = var6;
      this.zzXS = var8;
      this.zzXL = var10;
      this.zzXX = var11;
   }
}
