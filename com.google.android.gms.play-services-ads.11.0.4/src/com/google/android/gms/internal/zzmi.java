package com.google.android.gms.internal;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import org.json.JSONObject;

final class zzmi extends zzme {
   zzmi(int var1, String var2, Float var3) {
      super(var1, var2, var3, (zzmf)null);
   }

   // $FF: synthetic method
   public final void zza(Editor var1, Object var2) {
      Float var5 = (Float)var2;
      var1.putFloat(this.getKey(), var5.floatValue());
   }

   // $FF: synthetic method
   public final Object zzb(JSONObject var1) {
      return (float)var1.optDouble(this.getKey(), (double)((Float)this.zzdI()).floatValue());
   }

   // $FF: synthetic method
   public final Object zza(SharedPreferences var1) {
      return var1.getFloat(this.getKey(), ((Float)this.zzdI()).floatValue());
   }
}
