package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.util.Log;
import com.google.android.gms.internal.zzaye;
import java.util.Arrays;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

public class AdBreakStatus extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzc();
   public static final int AD_BREAK_CLIP_NOT_SKIPPABLE = -1;
   private final long zzaoH;
   private final long zzaoI;
   private final String zzaoJ;
   private final String zzaoK;
   private final long zzaoL;

   AdBreakStatus(long var1, long var3, String var5, String var6, long var7) {
      this.zzaoH = var1;
      this.zzaoI = var3;
      this.zzaoJ = var5;
      this.zzaoK = var6;
      this.zzaoL = var7;
   }

   public String getBreakId() {
      return this.zzaoJ;
   }

   public String getBreakClipId() {
      return this.zzaoK;
   }

   public long getCurrentBreakTimeInMs() {
      return this.zzaoH;
   }

   public long getCurrentBreakClipTimeInMs() {
      return this.zzaoI;
   }

   public long getWhenSkippableInMs() {
      return this.zzaoL;
   }

   public void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.getCurrentBreakTimeInMs());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.getCurrentBreakClipTimeInMs());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.getBreakId(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 5, this.getBreakClipId(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 6, this.getWhenSkippableInMs());
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzaoH, this.zzaoI, this.zzaoJ, this.zzaoK, this.zzaoL});
   }

   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof AdBreakStatus)) {
         return false;
      } else {
         AdBreakStatus var2 = (AdBreakStatus)var1;
         return this.zzaoH == var2.zzaoH && this.zzaoI == var2.zzaoI && zzaye.zza(this.zzaoJ, var2.zzaoJ) && zzaye.zza(this.zzaoK, var2.zzaoK) && this.zzaoL == var2.zzaoL;
      }
   }

   static AdBreakStatus zzj(JSONObject var0) {
      if (var0 == null) {
         return null;
      } else if (var0.has("currentBreakTime") && var0.has("currentBreakClipTime")) {
         try {
            long var1 = (long)((double)var0.getLong("currentBreakTime") * 1000.0D);
            long var3 = (long)((double)var0.getLong("currentBreakClipTime") * 1000.0D);
            String var5 = var0.optString("breakId", (String)null);
            String var6 = var0.optString("breakClipId", (String)null);
            long var7;
            if ((var7 = var0.optLong("whenSkippable", -1L)) != -1L) {
               var7 = (long)((double)var7 * 1000.0D);
            }

            return new AdBreakStatus(var1, var3, var5, var6, var7);
         } catch (JSONException var9) {
            Log.d("AdBreakInfo", String.format(Locale.ROOT, "Error while creating an AdBreakClipInfo from JSON: %s", var9.getMessage()));
            return null;
         }
      } else {
         return null;
      }
   }
}
