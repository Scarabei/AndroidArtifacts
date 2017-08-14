package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzzn
public final class zzaeq extends zza {
   public static final Creator CREATOR = new zzaer();
   public final String zzXr;
   public final String zzXs;
   public final boolean zzXt;
   public final boolean zzXu;
   public final List zzXv;
   public final boolean zzXw;
   public final boolean zzXx;

   public zzaeq(String var1, String var2, boolean var3, boolean var4, List var5, boolean var6, boolean var7) {
      this.zzXr = var1;
      this.zzXs = var2;
      this.zzXt = var3;
      this.zzXu = var4;
      this.zzXv = var5;
      this.zzXw = var6;
      this.zzXx = var7;
   }

   @Nullable
   public static zzaeq zzf(JSONObject var0) throws JSONException {
      if (var0 == null) {
         return null;
      } else {
         String var1 = var0.optString("click_string", "");
         String var2 = var0.optString("report_url", "");
         boolean var3 = var0.optBoolean("rendered_ad_enabled", false);
         boolean var4 = var0.optBoolean("non_malicious_reporting_enabled", false);
         JSONArray var5;
         if ((var5 = var0.optJSONArray("allowed_headers")) == null) {
            var5 = new JSONArray();
         }

         ArrayList var6 = new ArrayList();

         for(int var7 = 0; var7 < var5.length(); ++var7) {
            String var8;
            if (!TextUtils.isEmpty(var8 = var5.optString(var7))) {
               var6.add(var8.toLowerCase(Locale.ENGLISH));
            }
         }

         boolean var9 = var0.optBoolean("protection_enabled", false);
         boolean var10 = var0.optBoolean("malicious_reporting_enabled", false);
         return new zzaeq(var1, var2, var3, var4, var6, var9, var10);
      }
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zza(var1, 2, this.zzXr, false);
      zzd.zza(var1, 3, this.zzXs, false);
      zzd.zza(var1, 4, this.zzXt);
      zzd.zza(var1, 5, this.zzXu);
      zzd.zzb(var1, 6, this.zzXv, false);
      zzd.zza(var1, 7, this.zzXw);
      zzd.zza(var1, 8, this.zzXx);
      zzd.zzI(var1, var5);
   }
}
