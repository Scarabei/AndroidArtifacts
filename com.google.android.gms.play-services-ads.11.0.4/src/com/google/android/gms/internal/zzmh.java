package com.google.android.gms.internal;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import org.json.JSONObject;

final class zzmh extends zzme {
   zzmh(int var1, String var2, Long var3) {
      super(var1, var2, var3, (zzmf)null);
   }

   // $FF: synthetic method
   public final void zza(Editor var1, Object var2) {
      Long var5 = (Long)var2;
      var1.putLong(this.getKey(), var5.longValue());
   }

   // $FF: synthetic method
   public final Object zzb(JSONObject var1) {
      return var1.optLong(this.getKey(), ((Long)this.zzdI()).longValue());
   }

   // $FF: synthetic method
   public final Object zza(SharedPreferences var1) {
      return var1.getLong(this.getKey(), ((Long)this.zzdI()).longValue());
   }
}
