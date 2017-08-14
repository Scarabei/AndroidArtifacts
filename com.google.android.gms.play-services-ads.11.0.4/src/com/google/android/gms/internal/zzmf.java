package com.google.android.gms.internal;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import org.json.JSONObject;

final class zzmf extends zzme {
   zzmf(int var1, String var2, Boolean var3) {
      super(var1, var2, var3, (zzmf)null);
   }

   // $FF: synthetic method
   public final void zza(Editor var1, Object var2) {
      Boolean var5 = (Boolean)var2;
      var1.putBoolean(this.getKey(), var5.booleanValue());
   }

   // $FF: synthetic method
   public final Object zzb(JSONObject var1) {
      return var1.optBoolean(this.getKey(), ((Boolean)this.zzdI()).booleanValue());
   }

   // $FF: synthetic method
   public final Object zza(SharedPreferences var1) {
      return var1.getBoolean(this.getKey(), ((Boolean)this.zzdI()).booleanValue());
   }
}
