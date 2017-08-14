package com.google.android.gms.common.internal;

import java.util.ArrayList;
import java.util.List;

public final class zzbg {
   private final List zzaIh;
   private final Object zzaaw;

   private zzbg(Object var1) {
      this.zzaaw = zzbo.zzu(var1);
      this.zzaIh = new ArrayList();
   }

   public final zzbg zzg(String var1, Object var2) {
      List var10000 = this.zzaIh;
      String var3 = (String)zzbo.zzu(var1);
      String var4 = String.valueOf(String.valueOf(var2));
      var10000.add((new StringBuilder(1 + String.valueOf(var3).length() + String.valueOf(var4).length())).append(var3).append("=").append(var4).toString());
      return this;
   }

   public final String toString() {
      StringBuilder var1 = (new StringBuilder(100)).append(this.zzaaw.getClass().getSimpleName()).append('{');
      int var2 = this.zzaIh.size();

      for(int var3 = 0; var3 < var2; ++var3) {
         var1.append((String)this.zzaIh.get(var3));
         if (var3 < var2 - 1) {
            var1.append(", ");
         }
      }

      return var1.append('}').toString();
   }

   // $FF: synthetic method
   zzbg(Object var1, zzbf var2) {
      this(var1);
   }
}
