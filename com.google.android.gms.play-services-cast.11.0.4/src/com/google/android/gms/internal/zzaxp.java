package com.google.android.gms.internal;

import com.google.android.gms.common.util.zzo;
import org.json.JSONException;
import org.json.JSONObject;

public final class zzaxp {
   private final String zzaxn;
   private final int zzaqz;
   private final JSONObject zzaxD;

   public zzaxp(JSONObject var1) throws JSONException {
      this(var1.optString("playerId"), var1.optInt("playerState"), var1.optJSONObject("playerData"));
   }

   private zzaxp(String var1, int var2, JSONObject var3) {
      this.zzaxn = var1;
      this.zzaqz = var2;
      this.zzaxD = var3;
   }

   public final int getPlayerState() {
      return this.zzaqz;
   }

   public final JSONObject getPlayerData() {
      return this.zzaxD;
   }

   public final String getPlayerId() {
      return this.zzaxn;
   }

   public final boolean equals(Object var1) {
      if (var1 != null && var1 instanceof zzaxp) {
         zzaxp var2 = (zzaxp)var1;
         return this.zzaqz == var2.zzaqz && zzaye.zza(this.zzaxn, var2.zzaxn) && zzo.zzc(this.zzaxD, var2.zzaxD);
      } else {
         return false;
      }
   }
}
