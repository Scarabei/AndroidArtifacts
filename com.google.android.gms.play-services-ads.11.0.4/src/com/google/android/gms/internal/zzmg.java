package com.google.android.gms.internal;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import org.json.JSONObject;

final class zzmg extends zzme {
   zzmg(int var1, String var2, Integer var3) {
      super(var1, var2, var3, (zzmf)null);
   }

   // $FF: synthetic method
   public final void zza(Editor var1, Object var2) {
      Integer var5 = (Integer)var2;
      var1.putInt(this.getKey(), var5.intValue());
   }

   // $FF: synthetic method
   public final Object zzb(JSONObject var1) {
      return var1.optInt(this.getKey(), ((Integer)this.zzdI()).intValue());
   }

   // $FF: synthetic method
   public final Object zza(SharedPreferences var1) {
      return var1.getInt(this.getKey(), ((Integer)this.zzdI()).intValue());
   }
}
