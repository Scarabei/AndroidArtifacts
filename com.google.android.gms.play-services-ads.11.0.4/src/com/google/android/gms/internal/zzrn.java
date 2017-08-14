package com.google.android.gms.internal;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;
import org.json.JSONException;
import org.json.JSONObject;

@zzzn
public final class zzrn implements zzrd {
   private HashMap zzJD = new HashMap();

   public final Future zzS(String var1) {
      zzajg var2 = new zzajg();
      this.zzJD.put(var1, var2);
      return var2;
   }

   public final void zzT(String var1) {
      zzajg var2;
      if ((var2 = (zzajg)this.zzJD.get(var1)) == null) {
         zzafr.e("Could not find the ad request for the corresponding ad response.");
      } else {
         if (!var2.isDone()) {
            var2.cancel(true);
         }

         this.zzJD.remove(var1);
      }
   }

   public final void zza(zzaka var1, Map var2) {
      String var3 = (String)var2.get("request_id");
      String var4 = (String)var2.get("fetched_ad");
      String var7 = var4;
      zzafr.zzaC("Received ad from the cache.");
      zzajg var8;
      if ((var8 = (zzajg)this.zzJD.get(var3)) == null) {
         zzafr.e("Could not find the ad request for the corresponding ad response.");
      } else {
         try {
            JSONObject var9 = new JSONObject(var7);
            var8.zzg(var9);
            return;
         } catch (JSONException var13) {
            zzafr.zzb("Failed constructing JSON object from value passed from javascript", var13);
            var8.zzg((Object)null);
         } finally {
            this.zzJD.remove(var3);
         }

      }
   }
}
