package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class dc {
   private final List zzbKW = new ArrayList();
   private final Map zzbKX = new HashMap();
   private String zzaxs = "";
   private int zzbKY = 0;

   public final dc zza(dg var1) {
      this.zzbKW.add(var1);
      return this;
   }

   public final dc zzb(dd var1) {
      String var2 = ((dm)var1.zzCZ().get("instance_name")).toString();
      this.zzbKX.put(var2, var1);
      return this;
   }

   public final dc zzfW(String var1) {
      this.zzaxs = var1;
      return this;
   }

   public final db zzCY() {
      return new db(this.zzbKW, this.zzbKX, this.zzaxs, 0);
   }
}
