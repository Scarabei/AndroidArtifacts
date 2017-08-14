package com.google.android.gms.internal;

import android.os.RemoteException;
import java.util.List;

final class zzyt implements zzajl {
   // $FF: synthetic field
   private String zzRL;
   // $FF: synthetic field
   private Integer zzRM;
   // $FF: synthetic field
   private Integer zzRN;
   // $FF: synthetic field
   private int zzRO;
   // $FF: synthetic field
   private int zzRP;
   // $FF: synthetic field
   private int zzRQ;
   // $FF: synthetic field
   private int zzRR;
   // $FF: synthetic field
   private boolean zzRS;

   zzyt(zzyn var1, String var2, Integer var3, Integer var4, int var5, int var6, int var7, int var8, boolean var9) {
      this.zzRL = var2;
      this.zzRM = var3;
      this.zzRN = var4;
      this.zzRO = var5;
      this.zzRP = var6;
      this.zzRQ = var7;
      this.zzRR = var8;
      this.zzRS = var9;
      super();
   }

   private final zznn zzl(List var1) {
      try {
         return var1 != null && !var1.isEmpty() ? new zznn(this.zzRL, zzyn.zzk(var1), this.zzRM, this.zzRN, this.zzRO > 0 ? this.zzRO : null, this.zzRP + this.zzRQ, this.zzRR, this.zzRS) : null;
      } catch (RemoteException var3) {
         zzafr.zzb("Could not get attribution icon", var3);
         return null;
      }
   }

   // $FF: synthetic method
   public final Object apply(Object var1) {
      return this.zzl((List)var1);
   }
}
