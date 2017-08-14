package com.google.android.gms.internal;

import org.json.JSONException;
import org.json.JSONObject;

@zzzn
public class zzwu {
   private final zzaka zzJH;
   private final String zzOf;

   public zzwu(zzaka var1) {
      this(var1, "");
   }

   public zzwu(zzaka var1, String var2) {
      this.zzJH = var1;
      this.zzOf = var2;
   }

   public final void zzan(String var1) {
      try {
         JSONObject var2 = (new JSONObject()).put("message", var1).put("action", this.zzOf);
         this.zzJH.zzb("onError", var2);
      } catch (JSONException var3) {
         zzafr.zzb("Error occurred while dispatching error event.", var3);
      }
   }

   public final void zzao(String var1) {
      try {
         JSONObject var2 = (new JSONObject()).put("js", var1);
         this.zzJH.zzb("onReadyEventReceived", var2);
      } catch (JSONException var3) {
         zzafr.zzb("Error occured while dispatching ready Event.", var3);
      }
   }

   public final void zzb(int var1, int var2, int var3, int var4) {
      try {
         JSONObject var5 = (new JSONObject()).put("x", var1).put("y", var2).put("width", var3).put("height", var4);
         this.zzJH.zzb("onSizeChanged", var5);
      } catch (JSONException var6) {
         zzafr.zzb("Error occured while dispatching size change.", var6);
      }
   }

   public final void zzc(int var1, int var2, int var3, int var4) {
      try {
         JSONObject var5 = (new JSONObject()).put("x", var1).put("y", var2).put("width", var3).put("height", var4);
         this.zzJH.zzb("onDefaultPositionReceived", var5);
      } catch (JSONException var6) {
         zzafr.zzb("Error occured while dispatching default position.", var6);
      }
   }

   public final void zzap(String var1) {
      try {
         JSONObject var2 = (new JSONObject()).put("state", var1);
         this.zzJH.zzb("onStateChanged", var2);
      } catch (JSONException var3) {
         zzafr.zzb("Error occured while dispatching state change.", var3);
      }
   }

   public final void zza(int var1, int var2, int var3, int var4, float var5, int var6) {
      try {
         JSONObject var7 = (new JSONObject()).put("width", var1).put("height", var2).put("maxSizeWidth", var3).put("maxSizeHeight", var4).put("density", (double)var5).put("rotation", var6);
         this.zzJH.zzb("onScreenInfoChanged", var7);
      } catch (JSONException var8) {
         zzafr.zzb("Error occured while obtaining screen information.", var8);
      }
   }
}
