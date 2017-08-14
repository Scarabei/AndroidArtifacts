package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class zzaxm {
   private static final zzayo zzapq = new zzayo("GameManagerMessage");
   protected final int zzaxt;
   protected final int zzaxu;
   protected final String zzaxv;
   protected final JSONObject zzaxp;
   protected final int zzaxw;
   protected final int zzaxx;
   protected final List zzaxy;
   protected final JSONObject zzaxz;
   protected final String zzaxA;
   protected final String zzaxn;
   protected final long zzaxo;
   protected final String zzaxB;
   protected final zzaxl zzawU;

   private zzaxm(int var1, int var2, String var3, JSONObject var4, int var5, int var6, List var7, JSONObject var8, String var9, String var10, long var11, String var13, zzaxl var14) {
      this.zzaxt = var1;
      this.zzaxu = var2;
      this.zzaxv = var3;
      this.zzaxp = var4;
      this.zzaxw = var5;
      this.zzaxx = var6;
      this.zzaxy = var7;
      this.zzaxz = var8;
      this.zzaxA = var9;
      this.zzaxn = var10;
      this.zzaxo = var11;
      this.zzaxB = var13;
      this.zzawU = var14;
   }

   protected static zzaxm zzo(JSONObject var0) {
      int var1 = var0.optInt("type", -1);

      try {
         switch(var1) {
         case 1:
            zzaxl var2 = null;
            JSONObject var3;
            if ((var3 = var0.optJSONObject("gameManagerConfig")) != null) {
               var2 = new zzaxl(var3);
            }

            return new zzaxm(var1, var0.optInt("statusCode"), var0.optString("errorDescription"), var0.optJSONObject("extraMessageData"), var0.optInt("gameplayState"), var0.optInt("lobbyState"), zzb(var0.optJSONArray("players")), var0.optJSONObject("gameData"), var0.optString("gameStatusText"), var0.optString("playerId"), var0.optLong("requestId"), var0.optString("playerToken"), var2);
         case 2:
            return new zzaxm(var1, var0.optInt("statusCode"), var0.optString("errorDescription"), var0.optJSONObject("extraMessageData"), var0.optInt("gameplayState"), var0.optInt("lobbyState"), zzb(var0.optJSONArray("players")), var0.optJSONObject("gameData"), var0.optString("gameStatusText"), var0.optString("playerId"), -1L, (String)null, (zzaxl)null);
         default:
            zzapq.zzf("Unrecognized Game Message type %d", var1);
         }
      } catch (JSONException var4) {
         zzapq.zzc(var4, "Exception while parsing GameManagerMessage from json");
      }

      return null;
   }

   private static List zzb(JSONArray var0) {
      ArrayList var1 = new ArrayList();
      if (var0 == null) {
         return var1;
      } else {
         for(int var2 = 0; var2 < var0.length(); ++var2) {
            JSONObject var3;
            if ((var3 = var0.optJSONObject(var2)) != null) {
               zzaxp var4 = null;

               try {
                  var4 = new zzaxp(var3);
               } catch (JSONException var6) {
                  zzapq.zzc(var6, "Exception when attempting to parse PlayerInfoMessageComponent at index %d", var2);
               }

               if (var4 != null) {
                  var1.add(var4);
               }
            }
         }

         return var1;
      }
   }
}
