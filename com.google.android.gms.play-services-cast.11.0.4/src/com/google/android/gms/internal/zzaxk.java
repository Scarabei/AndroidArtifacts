package com.google.android.gms.internal;

import com.google.android.gms.cast.games.GameManagerClient;
import com.google.android.gms.common.api.Status;
import org.json.JSONObject;

final class zzaxk implements GameManagerClient.GameManagerResult {
   private final Status mStatus;
   private final String zzaxn;
   private final long zzaxo;
   private final JSONObject zzaxp;

   zzaxk(Status var1, String var2, long var3, JSONObject var5) {
      this.mStatus = var1;
      this.zzaxn = var2;
      this.zzaxo = var3;
      this.zzaxp = var5;
   }

   public final Status getStatus() {
      return this.mStatus;
   }

   public final String getPlayerId() {
      return this.zzaxn;
   }

   public final long getRequestId() {
      return this.zzaxo;
   }

   public final JSONObject getExtraMessageData() {
      return this.zzaxp;
   }
}
