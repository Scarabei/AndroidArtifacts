package com.google.android.gms.internal;

import java.util.HashMap;

public final class zzcao {
   private HashMap zzbcK = new HashMap();
   private int zzaxu = 0;

   public final zzcao zzs(String var1, int var2) {
      boolean var10000;
      switch(var2) {
      case 0:
      case 1:
      case 2:
      case 3:
         var10000 = true;
         break;
      default:
         var10000 = false;
      }

      if (var10000) {
         this.zzbcK.put(var1, var2);
      }

      return this;
   }

   public final zzcao zzbd(int var1) {
      this.zzaxu = var1;
      return this;
   }

   public final zzcam zzvm() {
      return new zzcam(this.zzaxu, this.zzbcK, (zzcan)null);
   }
}
