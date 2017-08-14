package com.google.android.gms.internal;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@zzzn
public final class zzaca {
   private final List zzVw;
   private final List zzVx;
   private final String zzVy;
   private final String zzVz;
   private final String zzVA;
   private final String zzVB;
   private final boolean zzVC;
   private final boolean zzVD;
   private final String zzQx;
   private final String zzVE;
   private String zzD;
   private int mErrorCode;

   private static boolean parseBoolean(String var0) {
      return var0 != null && (var0.equals("1") || var0.equals("true"));
   }

   private static List zzau(String var0) {
      return var0 == null ? null : Arrays.asList(var0.split(","));
   }

   public zzaca(int var1, Map var2) {
      this.zzD = (String)var2.get("url");
      this.zzVz = (String)var2.get("base_uri");
      this.zzVA = (String)var2.get("post_parameters");
      this.zzVC = parseBoolean((String)var2.get("drt_include"));
      this.zzVD = parseBoolean((String)var2.get("pan_include"));
      this.zzVy = (String)var2.get("activation_overlay_url");
      this.zzVx = zzau((String)var2.get("check_packages"));
      this.zzQx = (String)var2.get("request_id");
      this.zzVB = (String)var2.get("type");
      this.zzVw = zzau((String)var2.get("errors"));
      this.mErrorCode = var1;
      this.zzVE = (String)var2.get("fetched_ad");
   }

   public final void setUrl(String var1) {
      this.zzD = var1;
   }

   public final int getErrorCode() {
      return this.mErrorCode;
   }

   public final List zzgH() {
      return this.zzVw;
   }

   public final String zzgI() {
      return this.zzVz;
   }

   public final String zzgJ() {
      return this.zzVA;
   }

   public final String getUrl() {
      return this.zzD;
   }

   public final String getType() {
      return this.zzVB;
   }

   public final boolean zzgK() {
      return this.zzVC;
   }

   public final String getRequestId() {
      return this.zzQx;
   }

   public final String zzgL() {
      return this.zzVE;
   }
}
