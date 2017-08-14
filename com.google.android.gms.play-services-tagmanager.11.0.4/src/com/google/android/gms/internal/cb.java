package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.zzbo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class cb {
   static db zzfO(String var0) throws JSONException, ca {
      Object var2;
      if (!((var2 = (new JSONObject(var0)).get("resource")) instanceof JSONObject)) {
         throw new ca("Resource map not found");
      } else {
         JSONObject var1 = (JSONObject)var2;
         dc var3;
         (var3 = new dc()).zzfW(var1.optString("version"));
         List var4 = zzc(var1.getJSONArray("macros"));
         List var5 = zzb(var1.getJSONArray("tags"), var4);
         List var6 = zzb(var1.getJSONArray("predicates"), var4);
         Iterator var7 = zzb(var1.getJSONArray("macros"), var4).iterator();

         while(var7.hasNext()) {
            dd var8 = (dd)var7.next();
            var3.zzb(var8);
         }

         JSONArray var9 = var1.getJSONArray("rules");

         for(int var10 = 0; var10 < var9.length(); ++var10) {
            var3.zza(zza(var9.getJSONArray(var10), var5, var6));
         }

         return var3.zzCY();
      }
   }

   private static List zzc(JSONArray var0) throws JSONException {
      ArrayList var1 = new ArrayList();

      for(int var2 = 0; var2 < var0.length(); ++var2) {
         var1.add(var0.getJSONObject(var2).getString("instance_name"));
      }

      return var1;
   }

   private static List zzb(JSONArray var0, List var1) throws JSONException, ca {
      ArrayList var2 = new ArrayList();

      for(int var3 = 0; var3 < var0.length(); ++var3) {
         var2.add(zzb(var0.getJSONObject(var3), var1));
      }

      return var2;
   }

   private static dd zzb(JSONObject var0, List var1) throws ca, JSONException {
      df var2 = new df();
      Iterator var3 = var0.keys();

      while(var3.hasNext()) {
         String var4 = (String)var3.next();
         dm var5 = zzb(var0.get(var4), var1).zzDj();
         if ("push_after_evaluate".equals(var4)) {
            var2.zzb(var5);
         } else {
            var2.zza(var4, var5);
         }
      }

      return var2.zzDa();
   }

   private static dg zza(JSONArray var0, List var1, List var2) throws ca, JSONException {
      di var3 = new di();

      for(int var4 = 0; var4 < var0.length(); ++var4) {
         JSONArray var5;
         int var6;
         if ((var5 = var0.getJSONArray(var4)).getString(0).equals("if")) {
            for(var6 = 1; var6 < var5.length(); ++var6) {
               var3.zzc((dd)var2.get(var5.getInt(var6)));
            }
         } else if (var5.getString(0).equals("unless")) {
            for(var6 = 1; var6 < var5.length(); ++var6) {
               var3.zzd((dd)var2.get(var5.getInt(var6)));
            }
         } else if (var5.getString(0).equals("add")) {
            for(var6 = 1; var6 < var5.length(); ++var6) {
               var3.zze((dd)var1.get(var5.getInt(var6)));
            }
         } else if (var5.getString(0).equals("block")) {
            for(var6 = 1; var6 < var5.length(); ++var6) {
               var3.zzf((dd)var1.get(var5.getInt(var6)));
            }
         } else {
            String var10001 = String.valueOf(var5.getString(0));
            String var10000;
            if (var10001.length() != 0) {
               var10000 = "Unknown Rule property: ".concat(var10001);
            } else {
               String var10002 = new String;
               var10000 = var10002;
               var10002.<init>("Unknown Rule property: ");
            }

            zzfQ(var10000);
         }
      }

      return var3.zzDf();
   }

   private static do zzb(Object var0, List var1) throws ca, JSONException {
      do var2;
      if (var0 instanceof JSONArray) {
         JSONArray var3;
         String var4;
         if ((var4 = (var3 = (JSONArray)var0).getString(0)).equals("escape")) {
            var2 = zzb(var3.get(1), var1);

            for(int var5 = 2; var5 < var3.length(); ++var5) {
               var2.zzbF(var3.getInt(var5));
            }
         } else {
            int var6;
            ArrayList var8;
            if (var4.equals("list")) {
               var8 = new ArrayList();

               for(var6 = 1; var6 < var3.length(); ++var6) {
                  var8.add(zzb(var3.get(var6), var1).zzDj());
               }

               (var2 = new do(2, var8)).zzau(true);
            } else if (var4.equals("map")) {
               HashMap var9 = new HashMap();

               for(var6 = 1; var6 < var3.length(); var6 += 2) {
                  var9.put(zzb(var3.get(var6), var1).zzDj(), zzb(var3.get(var6 + 1), var1).zzDj());
               }

               (var2 = new do(3, var9)).zzau(true);
            } else if (var4.equals("macro")) {
               (var2 = new do(4, var1.get(var3.getInt(1)))).zzau(true);
            } else {
               if (!var4.equals("template")) {
                  String var10 = String.valueOf(var0);
                  zzfQ((new StringBuilder(20 + String.valueOf(var10).length())).append("Invalid value type: ").append(var10).toString());
                  return null;
               }

               var8 = new ArrayList();

               for(var6 = 1; var6 < var3.length(); ++var6) {
                  var8.add(zzb(var3.get(var6), var1).zzDj());
               }

               (var2 = new do(7, var8)).zzau(true);
            }
         }
      } else if (var0 instanceof Boolean) {
         var2 = new do(8, var0);
      } else if (var0 instanceof Integer) {
         var2 = new do(6, var0);
      } else {
         if (!(var0 instanceof String)) {
            String var7 = String.valueOf(var0);
            zzfQ((new StringBuilder(20 + String.valueOf(var7).length())).append("Invalid value type: ").append(var7).toString());
            return null;
         }

         var2 = new do(1, var0);
      }

      return var2;
   }

   @Nullable
   static dj zzfP(String var0) throws JSONException, ca {
      JSONObject var1;
      JSONArray var2;
      if ((var2 = (var1 = new JSONObject(var0)).optJSONArray("runtime")) == null) {
         return null;
      } else {
         dl var3 = new dl();
         Object var4;
         if (!((var4 = var1.get("resource")) instanceof JSONObject)) {
            throw new ca("Resource map not found");
         } else {
            JSONObject var5 = (JSONObject)var4;
            var3.zzfX(var5.optString("version"));

            for(int var7 = 0; var7 < var2.length(); ++var7) {
               Object var6;
               if (!((var6 = var2.get(var7)) instanceof JSONArray) || ((JSONArray)var6).length() != 0) {
                  var3.zza(zzO(var6));
               }
            }

            return var3.zzDh();
         }
      }
   }

   public static zzcxn zzO(Object var0) throws JSONException {
      String var1;
      JSONArray var2;
      JSONArray var3;
      int var6;
      if (var0 instanceof JSONObject) {
         JSONObject var4;
         var1 = (var4 = (JSONObject)var0).getString("name");
         var2 = var4.getJSONArray("params");
         var3 = var4.getJSONArray("instructions");
      } else {
         if (!(var0 instanceof JSONArray)) {
            throw new IllegalArgumentException("invalid JSON in runtime section");
         }

         JSONArray var8;
         zzbo.zzaf((var8 = (JSONArray)var0).length() >= 3);
         var1 = var8.getString(1);
         JSONArray var5 = var8.getJSONArray(2);
         var2 = new JSONArray();

         for(var6 = 1; var6 < var5.length(); ++var6) {
            zzbo.zzaf(var5.get(var6) instanceof String);
            var2.put(var5.get(var6));
         }

         var3 = new JSONArray();

         for(var6 = 3; var6 < var8.length(); ++var6) {
            var3.put(var8.get(var6));
         }
      }

      ArrayList var9 = new ArrayList();

      for(int var10 = 0; var10 < var2.length(); ++var10) {
         var9.add(var2.getString(var10));
      }

      ArrayList var11 = new ArrayList();

      for(var6 = 0; var6 < var3.length(); ++var6) {
         JSONArray var7;
         if ((var7 = var3.getJSONArray(var6)).length() != 0) {
            var11.add(zzd(var7));
         }
      }

      return new zzcxn((zzcwa)null, var1, var9, var11);
   }

   private static ea zzd(JSONArray var0) throws JSONException {
      zzbo.zzaf(var0.length() > 0);
      String var1 = var0.getString(0);
      ArrayList var2 = new ArrayList();

      for(int var3 = 1; var3 < var0.length(); ++var3) {
         Object var4;
         if ((var4 = var0.get(var3)) instanceof JSONArray) {
            JSONArray var5;
            if ((var5 = (JSONArray)var4).length() != 0) {
               var2.add(zzd(var5));
            }
         } else if (var4 == JSONObject.NULL) {
            var2.add(dv.zzbLt);
         } else {
            var2.add(ed.zzQ(var4));
         }
      }

      return new ea(var1, var2);
   }

   private static void zzfQ(String var0) throws ca {
      zzcvk.e(var0);
      throw new ca(var0);
   }
}
