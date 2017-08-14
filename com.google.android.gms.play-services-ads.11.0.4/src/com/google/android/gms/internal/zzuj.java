package com.google.android.gms.internal;

import android.content.Context;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzzn
public final class zzuj {
   public static List zza(JSONObject var0, String var1) throws JSONException {
      JSONArray var2;
      if ((var2 = var0.optJSONArray(var1)) == null) {
         return null;
      } else {
         ArrayList var3 = new ArrayList(var2.length());

         for(int var4 = 0; var4 < var2.length(); ++var4) {
            var3.add(var2.getString(var4));
         }

         return Collections.unmodifiableList(var3);
      }
   }

   public static void zza(Context var0, String var1, zzaff var2, String var3, boolean var4, List var5) {
      if (var5 != null && !var5.isEmpty()) {
         String var6 = var4 ? "1" : "0";
         Iterator var7 = var5.iterator();

         while(var7.hasNext()) {
            String var8 = zza(zza(zza(zza(zza(zza(zza((String)var7.next(), "@gw_adlocid@", var3), "@gw_adnetrefresh@", var6), "@gw_qdata@", var2.zzXN.zzMf), "@gw_sdkver@", var1), "@gw_sessid@", com.google.android.gms.ads.internal.zzbs.zzbD().getSessionId()), "@gw_seqnum@", var2.zzSC), "@gw_adnetstatus@", var2.zzXO);
            if (var2.zzMG != null) {
               var8 = zza(zza(var8, "@gw_adnetid@", var2.zzMG.zzLI), "@gw_allocid@", var2.zzMG.zzLK);
            }

            var8 = zzaez.zzb(var8, var0);
            com.google.android.gms.ads.internal.zzbs.zzbz();
            zzagz.zzd(var0, var1, var8);
         }

      }
   }

   private static String zza(String var0, String var1, String var2) {
      if (TextUtils.isEmpty(var2)) {
         var2 = "";
      }

      return var0.replaceAll(var1, var2);
   }

   public static boolean zza(String var0, int[] var1) {
      if (TextUtils.isEmpty(var0)) {
         return false;
      } else if (var1.length != 2) {
         return false;
      } else {
         String[] var2;
         if ((var2 = var0.split("x")).length != 2) {
            return false;
         } else {
            try {
               var1[0] = Integer.parseInt(var2[0]);
               var1[1] = Integer.parseInt(var2[1]);
               return true;
            } catch (NumberFormatException var3) {
               return false;
            }
         }
      }
   }
}
