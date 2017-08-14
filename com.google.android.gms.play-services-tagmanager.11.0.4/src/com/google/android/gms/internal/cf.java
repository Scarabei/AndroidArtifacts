package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import com.google.android.gms.common.api.Status;
import java.util.List;

final class cf extends cc {
   private final ce zzbKy;
   private final List zzbKz;
   private final int zzbKA;
   // $FF: synthetic field
   private cd zzbKB;

   cf(cd var1, int var2, cl var3, ch var4, List var5, int var6, @Nullable ce var7, zzcuo var8) {
      this.zzbKB = var1;
      super(var2, var3, var4, var8);
      this.zzbKy = var7;
      this.zzbKz = var5;
      this.zzbKA = var6;
   }

   protected final void zza(cm var1) {
      boolean var2 = false;
      String var10000;
      String var10001;
      String var10002;
      if (var1.getStatus() == Status.zzaBm) {
         var10001 = String.valueOf(var1.zzCS());
         if (var10001.length() != 0) {
            var10000 = "Container resource successfully loaded from ".concat(var10001);
         } else {
            var10002 = new String;
            var10000 = var10002;
            var10002.<init>("Container resource successfully loaded from ");
         }

         zzcvk.v(var10000);
         cn var3;
         if (var1.getSource() == 0 && !(var3 = var1.zzCQ()).zzCU().zzCL()) {
            this.zzbKB.zza(var1.getStatus(), var3);
            if (var3.zzCT() != null && var3.zzCT().length > 0) {
               cd.zza(this.zzbKB).zzd(var3.zzCU().zzCK(), var3.zzCT());
               var2 = true;
            }
         } else {
            var2 = true;
         }
      }

      if (var2) {
         this.zzbKy.zza(var1);
      } else {
         String var5 = String.valueOf(var1.zzCS());
         String var4 = var1.getStatus().isSuccess() ? "SUCCESS" : "FAILURE";
         zzcvk.v((new StringBuilder(54 + String.valueOf(var5).length() + String.valueOf(var4).length())).append("Cannot fetch a valid resource from ").append(var5).append(". Response status: ").append(var4).toString());
         if (var1.getStatus().isSuccess()) {
            var10001 = String.valueOf(var1.zzCS());
            if (var10001.length() != 0) {
               var10000 = "Response source: ".concat(var10001);
            } else {
               var10002 = new String;
               var10000 = var10002;
               var10002.<init>("Response source: ");
            }

            zzcvk.v(var10000);
            int var6 = var1.zzCQ().zzCT().length;
            zzcvk.v((new StringBuilder(26)).append("Response size: ").append(var6).toString());
         }

         this.zzbKB.zza(this.zzbKt, this.zzbKz, this.zzbKA + 1, this.zzbKy, this.zzbHO);
      }
   }
}
