package com.google.android.gms.internal;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import org.json.JSONObject;

final class zzmj extends zzme {
   zzmj(int var1, String var2, String var3) {
      super(var1, var2, var3, (zzmf)null);
   }

   // $FF: synthetic method
   public final void zza(Editor var1, Object var2) {
      String var5 = (String)var2;
      var1.putString(this.getKey(), var5);
   }

   // $FF: synthetic method
   public final Object zzb(JSONObject var1) {
      return var1.optString(this.getKey(), (String)this.zzdI());
   }

   // $FF: synthetic method
   public final Object zza(SharedPreferences var1) {
      return var1.getString(this.getKey(), (String)this.zzdI());
   }
}
