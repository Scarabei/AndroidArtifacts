package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.internal.zzaye;
import java.util.Arrays;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

public class AdBreakClipInfo extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zza();
   private final String zzIi;
   private final String zzaoy;
   private final long zzaoz;
   private final String zzAk;
   private final String zzaoA;
   private final String zzaoB;
   private String zzaoC;
   private JSONObject zzaoD;

   AdBreakClipInfo(String var1, String var2, long var3, String var5, String var6, String var7, String var8) {
      this.zzIi = var1;
      this.zzaoy = var2;
      this.zzaoz = var3;
      this.zzAk = var5;
      this.zzaoA = var6;
      this.zzaoB = var7;
      this.zzaoC = var8;
      if (!TextUtils.isEmpty(this.zzaoC)) {
         try {
            this.zzaoD = new JSONObject(var8);
         } catch (JSONException var10) {
            Log.w("AdBreakClipInfo", String.format(Locale.ROOT, "Error creating AdBreakClipInfo: %s", var10.getMessage()));
            this.zzaoC = null;
            this.zzaoD = new JSONObject();
         }
      } else {
         this.zzaoD = new JSONObject();
      }
   }

   public String getId() {
      return this.zzIi;
   }

   public String getTitle() {
      return this.zzaoy;
   }

   public long getDurationInMs() {
      return this.zzaoz;
   }

   public String getContentUrl() {
      return this.zzAk;
   }

   public String getMimeType() {
      return this.zzaoA;
   }

   public String getClickThroughUrl() {
      return this.zzaoB;
   }

   public JSONObject getCustomData() {
      return this.zzaoD;
   }

   public void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.getId(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.getTitle(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.getDurationInMs());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 5, this.getContentUrl(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 6, this.getMimeType(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 7, this.getClickThroughUrl(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 8, this.zzaoC, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzIi, this.zzaoy, this.zzaoz, this.zzAk, this.zzaoA, this.zzaoB, this.zzaoC});
   }

   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof AdBreakClipInfo)) {
         return false;
      } else {
         AdBreakClipInfo var2 = (AdBreakClipInfo)var1;
         return zzaye.zza(this.zzIi, var2.zzIi) && zzaye.zza(this.zzaoy, var2.zzaoy) && this.zzaoz == var2.zzaoz && zzaye.zza(this.zzAk, var2.zzAk) && zzaye.zza(this.zzaoA, var2.zzaoA) && zzaye.zza(this.zzaoB, var2.zzaoB) && zzaye.zza(this.zzaoC, var2.zzaoC);
      }
   }

   static AdBreakClipInfo zzh(JSONObject var0) {
      if (var0 == null) {
         return null;
      } else if (!var0.has("id")) {
         return null;
      } else {
         try {
            String var1 = var0.getString("id");
            long var2 = (long)((double)var0.optLong("duration") * 1000.0D);
            String var4 = var0.optString("clickThroughUrl", (String)null);
            String var5 = var0.optString("contentUrl", (String)null);
            String var6 = var0.optString("mimeType", (String)null);
            String var7 = var0.optString("title", (String)null);
            JSONObject var8 = var0.optJSONObject("customData");
            return new AdBreakClipInfo(var1, var7, var2, var5, var6, var4, var8 == null ? null : var8.toString());
         } catch (JSONException var9) {
            Log.d("AdBreakClipInfo", String.format(Locale.ROOT, "Error while creating an AdBreakClipInfo from JSON: %s", var9.getMessage()));
            return null;
         }
      }
   }
}
