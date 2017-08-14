package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.ei;
import com.google.android.gms.internal.ek;
import com.google.android.gms.internal.el;
import com.google.android.gms.internal.zzbg;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

final class zzdb {
   public static ek zzfo(String var0) throws JSONException {
      com.google.android.gms.internal.zzbr var1 = zzgk.zzI(zzG(new JSONObject(var0)));
      el var2 = ek.zzDz();

      for(int var3 = 0; var3 < var1.zzlF.length; ++var3) {
         var2.zzc(ei.zzDx().zzb(zzbg.zzhS.toString(), var1.zzlF[var3]).zzb(zzbg.zzhG.toString(), zzgk.zzfy(zzt.zzAG())).zzb(zzt.zzAH(), var1.zzlG[var3]).zzDy());
      }

      return var2.zzDB();
   }

   private static Object zzG(Object var0) throws JSONException {
      if (var0 instanceof JSONArray) {
         throw new RuntimeException("JSONArrays are not supported");
      } else if (JSONObject.NULL.equals(var0)) {
         throw new RuntimeException("JSON nulls are not supported");
      } else if (!(var0 instanceof JSONObject)) {
         return var0;
      } else {
         JSONObject var1 = (JSONObject)var0;
         HashMap var2 = new HashMap();
         Iterator var3 = var1.keys();

         while(var3.hasNext()) {
            String var4 = (String)var3.next();
            var2.put(var4, zzG(var1.get(var4)));
         }

         return var2;
      }
   }
}
