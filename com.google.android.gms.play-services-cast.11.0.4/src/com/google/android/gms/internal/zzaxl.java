package com.google.android.gms.internal;

import org.json.JSONException;
import org.json.JSONObject;

public final class zzaxl {
   private final String zzaxq;
   private final int zzaxr;
   private final String zzaxs;

   public zzaxl(JSONObject var1) throws JSONException {
      this(var1.optString("applicationName"), var1.optInt("maxPlayers"), var1.optString("version"));
   }

   private zzaxl(String var1, int var2, String var3) {
      this.zzaxq = var1;
      this.zzaxr = var2;
      this.zzaxs = var3;
   }

   public final String zzox() {
      return this.zzaxq;
   }

   public final int getMaxPlayers() {
      return this.zzaxr;
   }

   public final String getVersion() {
      return this.zzaxs;
   }
}
