package com.google.android.gms.internal;

import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

@zzzn
public final class zzrs implements zzrd {
   private final Object mLock = new Object();
   private final Map zzJI = new HashMap();

   public final void zza(zzaka var1, Map var2) {
      String var3 = (String)var2.get("id");
      String var4 = (String)var2.get("fail");
      var2.get("fail_reason");
      String var5 = (String)var2.get("result");
      Object var6 = this.mLock;
      synchronized(this.mLock) {
         if ((zzrt)this.zzJI.remove(var3) == null) {
            String var10001 = String.valueOf(var3);
            String var10000;
            if (var10001.length() != 0) {
               var10000 = "Received result for unexpected method invocation: ".concat(var10001);
            } else {
               String var10002 = new String;
               var10000 = var10002;
               var10002.<init>("Received result for unexpected method invocation: ");
            }

            zzafr.zzaT(var10000);
         } else if (TextUtils.isEmpty(var4)) {
            if (var5 != null) {
               try {
                  new JSONObject(var5);
               } catch (JSONException var8) {
                  var8.getMessage();
               }

            }
         }
      }
   }
}
