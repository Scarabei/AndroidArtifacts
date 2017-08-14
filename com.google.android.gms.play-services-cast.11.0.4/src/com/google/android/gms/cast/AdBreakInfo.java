package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.util.Log;
import com.google.android.gms.internal.zzaye;
import java.util.Arrays;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AdBreakInfo extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzb();
   private final long zzaoE;
   private final String zzIi;
   private final long zzaoz;
   private final boolean zzaoF;
   private String[] zzaoG;

   public AdBreakInfo(long var1, String var3, long var4, boolean var6, String[] var7) {
      this.zzaoE = var1;
      this.zzIi = var3;
      this.zzaoz = var4;
      this.zzaoF = var6;
      this.zzaoG = var7;
   }

   public long getPlaybackPositionInMs() {
      return this.zzaoE;
   }

   public String getId() {
      return this.zzIi;
   }

   public long getDurationInMs() {
      return this.zzaoz;
   }

   public boolean isWatched() {
      return this.zzaoF;
   }

   public String[] getBreakClipIds() {
      return this.zzaoG;
   }

   public void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.getPlaybackPositionInMs());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.getId(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.getDurationInMs());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 5, this.isWatched());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 6, this.getBreakClipIds(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public int hashCode() {
      return this.zzIi.hashCode();
   }

   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof AdBreakInfo)) {
         return false;
      } else {
         AdBreakInfo var2 = (AdBreakInfo)var1;
         return zzaye.zza(this.zzIi, var2.zzIi) && this.zzaoE == var2.zzaoE && this.zzaoz == var2.zzaoz && this.zzaoF == var2.zzaoF && Arrays.equals(this.zzaoG, var2.zzaoG);
      }
   }

   static AdBreakInfo zzi(JSONObject var0) {
      if (var0 == null) {
         return null;
      } else if (var0.has("id") && var0.has("position")) {
         try {
            String var1 = var0.getString("id");
            long var2 = (long)((double)var0.getLong("position") * 1000.0D);
            boolean var4 = var0.optBoolean("isWatched");
            long var5 = (long)((double)var0.optLong("duration") * 1000.0D);
            JSONArray var7 = var0.optJSONArray("breakClipIds");
            String[] var8 = null;
            if (var7 != null) {
               var8 = new String[var7.length()];

               for(int var9 = 0; var9 < var7.length(); ++var9) {
                  var8[var9] = var7.getString(var9);
               }
            }

            return new AdBreakInfo(var2, var1, var5, var4, var8);
         } catch (JSONException var10) {
            Log.d("AdBreakInfo", String.format(Locale.ROOT, "Error while creating an AdBreakInfo from JSON: %s", var10.getMessage()));
            return null;
         }
      } else {
         return null;
      }
   }

   public static class Builder {
      private long zzaoE = 0L;

      public Builder(long var1) {
         this.zzaoE = var1;
      }

      public AdBreakInfo build() {
         return new AdBreakInfo(this.zzaoE, (String)null, 0L, false, (String[])null);
      }
   }
}
