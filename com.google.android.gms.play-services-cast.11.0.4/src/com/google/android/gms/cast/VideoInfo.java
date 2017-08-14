package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.util.Log;
import java.util.Arrays;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

public final class VideoInfo extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final int HDR_TYPE_UNKNOWN = 0;
   public static final int HDR_TYPE_SDR = 1;
   public static final int HDR_TYPE_HDR10 = 2;
   public static final int HDR_TYPE_DV = 3;
   public static final int HDR_TYPE_HDR = 4;
   public static final Creator CREATOR = new zzbj();
   private int zzrW;
   private int zzrX;
   private int zzarI;

   VideoInfo(int var1, int var2, int var3) {
      this.zzrW = var1;
      this.zzrX = var2;
      this.zzarI = var3;
   }

   public final int getWidth() {
      return this.zzrW;
   }

   public final int getHeight() {
      return this.zzrX;
   }

   public final int getHdrType() {
      return this.zzarI;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 2, this.getWidth());
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 3, this.getHeight());
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 4, this.getHdrType());
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public final boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof VideoInfo)) {
         return false;
      } else {
         VideoInfo var2 = (VideoInfo)var1;
         return this.zzrX == var2.getHeight() && this.zzrW == var2.getWidth() && this.zzarI == var2.getHdrType();
      }
   }

   public final int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzrX, this.zzrW, this.zzarI});
   }

   static VideoInfo zzn(JSONObject var0) {
      if (var0 == null) {
         return null;
      } else {
         try {
            String var1;
            String var3 = var1 = var0.getString("hdrType");
            byte var4 = -1;
            switch(var3.hashCode()) {
            case 3218:
               if (var3.equals("dv")) {
                  var4 = 0;
               }
               break;
            case 103158:
               if (var3.equals("hdr")) {
                  var4 = 2;
               }
               break;
            case 113729:
               if (var3.equals("sdr")) {
                  var4 = 3;
               }
               break;
            case 99136405:
               if (var3.equals("hdr10")) {
                  var4 = 1;
               }
            }

            byte var2;
            switch(var4) {
            case 0:
               var2 = 3;
               break;
            case 1:
               var2 = 2;
               break;
            case 2:
               var2 = 4;
               break;
            case 3:
               var2 = 1;
               break;
            default:
               Log.d("VideoInfo", String.format(Locale.ROOT, "Unknown HDR type: %s", var1));
               var2 = 0;
            }

            return new VideoInfo(var0.getInt("width"), var0.getInt("height"), var2);
         } catch (JSONException var5) {
            Log.d("VideoInfo", String.format(Locale.ROOT, "Error while creating a VideoInfo instance from JSON: %s", var5.getMessage()));
            return null;
         }
      }
   }
}
