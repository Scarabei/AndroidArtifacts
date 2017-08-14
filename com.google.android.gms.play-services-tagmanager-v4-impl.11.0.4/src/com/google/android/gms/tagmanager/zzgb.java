package com.google.android.gms.tagmanager;

import java.util.Map;

final class zzgb implements DataLayer.zzb {
   // $FF: synthetic field
   private TagManager zzbGX;

   zzgb(TagManager var1) {
      this.zzbGX = var1;
      super();
   }

   public final void zzp(Map var1) {
      Object var2;
      if ((var2 = var1.get("event")) != null) {
         TagManager.zza(this.zzbGX, var2.toString());
      }

   }
}
