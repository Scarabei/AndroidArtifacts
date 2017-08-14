package com.google.android.gms.internal;

import com.google.android.gms.tagmanager.zzgk;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class el {
   private final List zzbKW;
   private final Map zzbKX;
   private String zzaxs;
   private int zzbKY;

   private el() {
      this.zzbKW = new ArrayList();
      this.zzbKX = new HashMap();
      this.zzaxs = "";
      this.zzbKY = 0;
   }

   public final el zzb(em var1) {
      this.zzbKW.add(var1);
      return this;
   }

   public final el zzc(ei var1) {
      String var2 = zzgk.zzb((zzbr)var1.zzCZ().get(zzbg.zzhS.toString()));
      Object var3;
      if ((var3 = (List)this.zzbKX.get(var2)) == null) {
         var3 = new ArrayList();
         this.zzbKX.put(var2, var3);
      }

      ((List)var3).add(var1);
      return this;
   }

   public final el zzgd(String var1) {
      this.zzaxs = var1;
      return this;
   }

   public final el zzbJ(int var1) {
      this.zzbKY = var1;
      return this;
   }

   public final ek zzDB() {
      return new ek(this.zzbKW, this.zzbKX, this.zzaxs, this.zzbKY, (eh)null);
   }

   // $FF: synthetic method
   el(eh var1) {
      this();
   }
}
