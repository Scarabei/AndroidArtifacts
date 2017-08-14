package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.em;
import java.util.List;
import java.util.Map;
import java.util.Set;

final class zzff implements zzfh {
   // $FF: synthetic field
   private Map zzbGq;
   // $FF: synthetic field
   private Map zzbGr;
   // $FF: synthetic field
   private Map zzbGs;
   // $FF: synthetic field
   private Map zzbGt;

   zzff(zzfc var1, Map var2, Map var3, Map var4, Map var5) {
      this.zzbGq = var2;
      this.zzbGr = var3;
      this.zzbGs = var4;
      this.zzbGt = var5;
      super();
   }

   public final void zza(em var1, Set var2, Set var3, zzer var4) {
      List var5 = (List)this.zzbGq.get(var1);
      this.zzbGr.get(var1);
      if (var5 != null) {
         var2.addAll(var5);
         var4.zzBt();
      }

      List var6 = (List)this.zzbGs.get(var1);
      this.zzbGt.get(var1);
      if (var6 != null) {
         var3.addAll(var6);
         var4.zzBu();
      }

   }
}
