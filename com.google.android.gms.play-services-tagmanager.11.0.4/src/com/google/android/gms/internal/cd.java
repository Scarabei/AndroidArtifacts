package com.google.android.gms.internal;

import android.content.Context;
import android.support.annotation.Nullable;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.common.util.zzi;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class cd {
   private final Context mContext;
   private final co zzbKv;
   private final zze zzvw;
   private String zzbEa;
   private Map zzbKw;
   private final Map zzbKx;

   public cd(Context var1) {
      this(var1, new HashMap(), new co(var1), zzi.zzrY());
   }

   private cd(Context var1, Map var2, co var3, zze var4) {
      this.zzbEa = null;
      this.zzbKw = new HashMap();
      this.mContext = var1.getApplicationContext();
      this.zzvw = var4;
      this.zzbKv = var3;
      this.zzbKx = var2;
   }

   public final void zza(String var1, @Nullable String var2, @Nullable String var3, List var4, ce var5, zzcuo var6) {
      zzbo.zzaf(!var4.isEmpty());
      zzcvs var9;
      cl var7 = (new cl()).zza(new bz(var1, var2, var3, (var9 = zzcvs.zzCw()).isPreview() && var1.equals(var9.getContainerId()), zzcvs.zzCw().zzCx()));
      this.zza(var7, Collections.unmodifiableList(var4), 0, var5, var6);
   }

   final void zza(cl var1, List var2, int var3, ce var4, @Nullable zzcuo var5) {
      while(true) {
         if (var3 == 0) {
            zzcvk.v("Starting to fetch a new resource");
         }

         if (var3 >= var2.size()) {
            String var10001 = String.valueOf(var1.zzCP().getContainerId());
            String var10000;
            if (var10001.length() != 0) {
               var10000 = "There is no valid resource for the container: ".concat(var10001);
            } else {
               String var10002 = new String;
               var10000 = var10002;
               var10002.<init>("There is no valid resource for the container: ");
            }

            String var6 = var10000;
            zzcvk.v(var10000);
            var4.zza(new cm(new Status(16, var6), ((Integer)var2.get(var3 - 1)).intValue()));
            return;
         }

         bz var12;
         String var13;
         switch(((Integer)var2.get(var3)).intValue()) {
         case 0:
            boolean var18 = false;
            bz var14 = var1.zzCP();
            cg var15 = (cg)this.zzbKw.get(var14.getContainerId());
            if (var1.zzCP().zzCL()) {
               var18 = true;
            } else if ((var15 != null ? var15.zzCO() : this.zzbKv.zzfR(var14.getContainerId())) + 900000L < this.zzvw.currentTimeMillis()) {
               var18 = true;
            }

            if (var18) {
               cz var16;
               if ((var16 = (cz)this.zzbKx.get(var1.getId())) == null) {
                  var16 = new cz();
                  this.zzbKx.put(var1.getId(), var16);
               }

               String var17 = String.valueOf(var14.getContainerId());
               zzcvk.v((new StringBuilder(43 + String.valueOf(var17).length())).append("Attempting to fetch container ").append(var17).append(" from network").toString());
               var16.zza(this.mContext, var1, 0L, new cf(this, 0, var1, ci.zzbKD, var2, var3, var4, var5));
               return;
            }

            int var10003 = var3 + 1;
            var5 = var5;
            var4 = var4;
            var3 = var10003;
            var2 = var2;
            var1 = var1;
            this = this;
            break;
         case 1:
            var13 = String.valueOf((var12 = var1.zzCP()).getContainerId());
            zzcvk.v((new StringBuilder(52 + String.valueOf(var13).length())).append("Attempting to fetch container ").append(var13).append(" from a saved resource").toString());
            this.zzbKv.zza(var12.zzCK(), new cf(this, 1, var1, ci.zzbKD, var2, var3, var4, (zzcuo)null));
            return;
         case 2:
            var13 = String.valueOf((var12 = var1.zzCP()).getContainerId());
            zzcvk.v((new StringBuilder(56 + String.valueOf(var13).length())).append("Attempting to fetch container ").append(var13).append(" from the default resource").toString());
            this.zzbKv.zza(var12.zzCK(), var12.zzCI(), new cf(this, 2, var1, ci.zzbKD, var2, var3, var4, (zzcuo)null));
            return;
         default:
            throw new UnsupportedOperationException((new StringBuilder(36)).append("Unknown fetching source: ").append(var3).toString());
         }
      }
   }

   final void zza(Status var1, cn var2) {
      String var3 = var2.zzCU().getContainerId();
      db var4 = var2.zzCV();
      if (this.zzbKw.containsKey(var3)) {
         cg var5;
         (var5 = (cg)this.zzbKw.get(var3)).zzak(this.zzvw.currentTimeMillis());
         if (var1 == Status.zzaBm) {
            var5.zzJ(var1);
            var5.zzP(var4);
         }

      } else {
         this.zzbKw.put(var3, new cg(var1, var4, this.zzvw.currentTimeMillis()));
      }
   }

   // $FF: synthetic method
   static co zza(cd var0) {
      return var0.zzbKv;
   }
}
