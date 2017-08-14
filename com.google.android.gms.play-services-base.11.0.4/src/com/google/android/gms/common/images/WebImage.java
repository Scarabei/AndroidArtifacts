package com.google.android.gms.common.images;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbe;
import java.util.Arrays;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

public final class WebImage extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zze();
   private int zzaku;
   private final Uri zzauQ;
   private final int zzrW;
   private final int zzrX;

   WebImage(int var1, Uri var2, int var3, int var4) {
      this.zzaku = var1;
      this.zzauQ = var2;
      this.zzrW = var3;
      this.zzrX = var4;
   }

   public WebImage(Uri var1, int var2, int var3) throws IllegalArgumentException {
      this(1, var1, var2, var3);
      if (var1 == null) {
         throw new IllegalArgumentException("url cannot be null");
      } else if (var2 < 0 || var3 < 0) {
         throw new IllegalArgumentException("width and height must not be negative");
      }
   }

   public WebImage(Uri var1) throws IllegalArgumentException {
      this(var1, 0, 0);
   }

   public WebImage(JSONObject var1) throws IllegalArgumentException {
      this(zzp(var1), var1.optInt("width", 0), var1.optInt("height", 0));
   }

   private static Uri zzp(JSONObject var0) {
      Uri var1 = null;
      if (var0.has("url")) {
         try {
            var1 = Uri.parse(var0.getString("url"));
         } catch (JSONException var2) {
            ;
         }
      }

      return var1;
   }

   public final Uri getUrl() {
      return this.zzauQ;
   }

   public final int getWidth() {
      return this.zzrW;
   }

   public final int getHeight() {
      return this.zzrX;
   }

   public final String toString() {
      return String.format(Locale.US, "Image %dx%d %s", this.zzrW, this.zzrX, this.zzauQ.toString());
   }

   public final JSONObject toJson() {
      JSONObject var1 = new JSONObject();

      try {
         var1.put("url", this.zzauQ.toString());
         var1.put("width", this.zzrW);
         var1.put("height", this.zzrX);
      } catch (JSONException var2) {
         ;
      }

      return var1;
   }

   public final boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 != null && var1 instanceof WebImage) {
         WebImage var2 = (WebImage)var1;
         return zzbe.equal(this.zzauQ, var2.zzauQ) && this.zzrW == var2.zzrW && this.zzrX == var2.zzrX;
      } else {
         return false;
      }
   }

   public final int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzauQ, this.zzrW, this.zzrX});
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1, this.zzaku);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.getUrl(), var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 3, this.getWidth());
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 4, this.getHeight());
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
