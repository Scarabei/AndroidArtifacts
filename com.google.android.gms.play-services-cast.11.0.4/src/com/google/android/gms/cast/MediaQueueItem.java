package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.zzaye;
import java.util.Arrays;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MediaQueueItem extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final int INVALID_ITEM_ID = 0;
   public static final double DEFAULT_PLAYBACK_DURATION = Double.POSITIVE_INFINITY;
   public static final Creator CREATOR = new zzaf();
   private MediaInfo zzaqo;
   private int zzaqp;
   private boolean zzaqq;
   private double zzaqr;
   private double zzaqs;
   private double zzaqt;
   private long[] zzaqu;
   private String zzaoC;
   private JSONObject zzaoD;

   MediaQueueItem(MediaInfo var1, int var2, boolean var3, double var4, double var6, double var8, long[] var10, String var11) {
      this.zzaqo = var1;
      this.zzaqp = var2;
      this.zzaqq = var3;
      this.zzaqr = var4;
      this.zzaqs = var6;
      this.zzaqt = var8;
      this.zzaqu = var10;
      this.zzaoC = var11;
      if (this.zzaoC != null) {
         try {
            this.zzaoD = new JSONObject(this.zzaoC);
         } catch (JSONException var12) {
            this.zzaoD = null;
            this.zzaoC = null;
         }
      } else {
         this.zzaoD = null;
      }
   }

   private MediaQueueItem(MediaInfo var1) throws IllegalArgumentException {
      this(var1, 0, true, 0.0D, Double.POSITIVE_INFINITY, 0.0D, (long[])null, (String)null);
      if (var1 == null) {
         throw new IllegalArgumentException("media cannot be null.");
      }
   }

   MediaQueueItem(JSONObject var1) throws JSONException {
      this((MediaInfo)null, 0, true, 0.0D, Double.POSITIVE_INFINITY, 0.0D, (long[])null, (String)null);
      this.zzm(var1);
   }

   private MediaQueueItem(MediaQueueItem var1) throws IllegalArgumentException {
      this(var1.getMedia(), var1.getItemId(), var1.getAutoplay(), var1.getStartTime(), var1.getPlaybackDuration(), var1.getPreloadTime(), var1.getActiveTrackIds(), (String)null);
      if (this.zzaqo == null) {
         throw new IllegalArgumentException("media cannot be null.");
      } else {
         this.zzaoD = var1.getCustomData();
      }
   }

   public final boolean zzm(JSONObject var1) throws JSONException {
      boolean var2 = false;
      if (var1.has("media")) {
         this.zzaqo = new MediaInfo(var1.getJSONObject("media"));
         var2 = true;
      }

      if (var1.has("itemId")) {
         int var3 = var1.getInt("itemId");
         if (this.zzaqp != var3) {
            this.zzaqp = var3;
            var2 = true;
         }
      }

      boolean var8;
      if (var1.has("autoplay")) {
         var8 = var1.getBoolean("autoplay");
         if (this.zzaqq != var8) {
            this.zzaqq = var8;
            var2 = true;
         }
      }

      double var9;
      if (var1.has("startTime") && Math.abs((var9 = var1.getDouble("startTime")) - this.zzaqr) > 1.0E-7D) {
         this.zzaqr = var9;
         var2 = true;
      }

      if (var1.has("playbackDuration") && Math.abs((var9 = var1.getDouble("playbackDuration")) - this.zzaqs) > 1.0E-7D) {
         this.zzaqs = var9;
         var2 = true;
      }

      if (var1.has("preloadTime") && Math.abs((var9 = var1.getDouble("preloadTime")) - this.zzaqt) > 1.0E-7D) {
         this.zzaqt = var9;
         var2 = true;
      }

      var8 = false;
      long[] var4 = null;
      if (var1.has("activeTrackIds")) {
         JSONArray var5;
         int var6;
         var4 = new long[var6 = (var5 = var1.getJSONArray("activeTrackIds")).length()];

         int var7;
         for(var7 = 0; var7 < var6; ++var7) {
            var4[var7] = var5.getLong(var7);
         }

         if (this.zzaqu == null) {
            var8 = true;
         } else if (this.zzaqu.length != var6) {
            var8 = true;
         } else {
            for(var7 = 0; var7 < var6; ++var7) {
               if (this.zzaqu[var7] != var4[var7]) {
                  var8 = true;
                  break;
               }
            }
         }
      }

      if (var8) {
         this.zzaqu = var4;
         var2 = true;
      }

      if (var1.has("customData")) {
         this.zzaoD = var1.getJSONObject("customData");
         var2 = true;
      }

      return var2;
   }

   public MediaInfo getMedia() {
      return this.zzaqo;
   }

   public int getItemId() {
      return this.zzaqp;
   }

   final void zzV(int var1) {
      this.zzaqp = 0;
   }

   public boolean getAutoplay() {
      return this.zzaqq;
   }

   final void zzU(boolean var1) {
      this.zzaqq = var1;
   }

   public double getStartTime() {
      return this.zzaqr;
   }

   final void zzc(double var1) throws IllegalArgumentException {
      if (!Double.isNaN(var1) && var1 >= 0.0D) {
         this.zzaqr = var1;
      } else {
         throw new IllegalArgumentException("startTime cannot be negative or NaN.");
      }
   }

   public double getPlaybackDuration() {
      return this.zzaqs;
   }

   final void zzd(double var1) throws IllegalArgumentException {
      if (Double.isNaN(var1)) {
         throw new IllegalArgumentException("playbackDuration cannot be NaN.");
      } else {
         this.zzaqs = var1;
      }
   }

   public double getPreloadTime() {
      return this.zzaqt;
   }

   final void zze(double var1) throws IllegalArgumentException {
      if (!Double.isNaN(var1) && var1 >= 0.0D) {
         this.zzaqt = var1;
      } else {
         throw new IllegalArgumentException("preloadTime cannot be negative or NaN.");
      }
   }

   public long[] getActiveTrackIds() {
      return this.zzaqu;
   }

   final void zza(long[] var1) {
      this.zzaqu = var1;
   }

   public JSONObject getCustomData() {
      return this.zzaoD;
   }

   final void setCustomData(JSONObject var1) {
      this.zzaoD = var1;
   }

   final void zznj() throws IllegalArgumentException {
      if (this.zzaqo == null) {
         throw new IllegalArgumentException("media cannot be null.");
      } else if (!Double.isNaN(this.zzaqr) && this.zzaqr >= 0.0D) {
         if (Double.isNaN(this.zzaqs)) {
            throw new IllegalArgumentException("playbackDuration cannot be NaN.");
         } else if (Double.isNaN(this.zzaqt) || this.zzaqt < 0.0D) {
            throw new IllegalArgumentException("preloadTime cannot be negative or Nan.");
         }
      } else {
         throw new IllegalArgumentException("startTime cannot be negative or NaN.");
      }
   }

   public final JSONObject toJson() {
      JSONObject var1 = new JSONObject();

      try {
         var1.put("media", this.zzaqo.toJson());
         if (this.zzaqp != 0) {
            var1.put("itemId", this.zzaqp);
         }

         var1.put("autoplay", this.zzaqq);
         var1.put("startTime", this.zzaqr);
         if (this.zzaqs != Double.POSITIVE_INFINITY) {
            var1.put("playbackDuration", this.zzaqs);
         }

         var1.put("preloadTime", this.zzaqt);
         if (this.zzaqu != null) {
            JSONArray var2 = new JSONArray();
            long[] var3 = this.zzaqu;
            int var4 = this.zzaqu.length;

            for(int var5 = 0; var5 < var4; ++var5) {
               long var6 = var3[var5];
               var2.put(var6);
            }

            var1.put("activeTrackIds", var2);
         }

         if (this.zzaoD != null) {
            var1.put("customData", this.zzaoD);
         }
      } catch (JSONException var8) {
         ;
      }

      return var1;
   }

   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof MediaQueueItem)) {
         return false;
      } else {
         MediaQueueItem var2 = (MediaQueueItem)var1;
         if (this.zzaoD == null != (var2.zzaoD == null)) {
            return false;
         } else if (this.zzaoD != null && var2.zzaoD != null && !com.google.android.gms.common.util.zzo.zzc(this.zzaoD, var2.zzaoD)) {
            return false;
         } else {
            return zzaye.zza(this.zzaqo, var2.zzaqo) && this.zzaqp == var2.zzaqp && this.zzaqq == var2.zzaqq && this.zzaqr == var2.zzaqr && this.zzaqs == var2.zzaqs && this.zzaqt == var2.zzaqt && Arrays.equals(this.zzaqu, var2.zzaqu);
         }
      }
   }

   public int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzaqo, this.zzaqp, this.zzaqq, this.zzaqr, this.zzaqs, this.zzaqt, Arrays.hashCode(this.zzaqu), String.valueOf(this.zzaoD)});
   }

   public void writeToParcel(Parcel var1, int var2) {
      this.zzaoC = this.zzaoD == null ? null : this.zzaoD.toString();
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.getMedia(), var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 3, this.getItemId());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.getAutoplay());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 5, this.getStartTime());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 6, this.getPlaybackDuration());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 7, this.getPreloadTime());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 8, this.getActiveTrackIds(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 9, this.zzaoC, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   // $FF: synthetic method
   MediaQueueItem(MediaInfo var1, zzae var2) throws IllegalArgumentException {
      this(var1);
   }

   // $FF: synthetic method
   MediaQueueItem(MediaQueueItem var1, zzae var2) throws IllegalArgumentException {
      this(var1);
   }

   public static class Builder {
      private final MediaQueueItem zzaqv;

      public Builder(MediaInfo var1) throws IllegalArgumentException {
         this.zzaqv = new MediaQueueItem(var1, (zzae)null);
      }

      public Builder(JSONObject var1) throws JSONException {
         this.zzaqv = new MediaQueueItem(var1);
      }

      public Builder(MediaQueueItem var1) throws IllegalArgumentException {
         this.zzaqv = new MediaQueueItem(var1, (zzae)null);
      }

      public MediaQueueItem.Builder clearItemId() {
         this.zzaqv.zzV(0);
         return this;
      }

      public MediaQueueItem.Builder setAutoplay(boolean var1) {
         this.zzaqv.zzU(var1);
         return this;
      }

      public MediaQueueItem.Builder setStartTime(double var1) throws IllegalArgumentException {
         this.zzaqv.zzc(var1);
         return this;
      }

      public MediaQueueItem.Builder setPlaybackDuration(double var1) {
         this.zzaqv.zzd(var1);
         return this;
      }

      public MediaQueueItem.Builder setPreloadTime(double var1) throws IllegalArgumentException {
         this.zzaqv.zze(var1);
         return this;
      }

      public MediaQueueItem.Builder setActiveTrackIds(long[] var1) {
         this.zzaqv.zza(var1);
         return this;
      }

      public MediaQueueItem.Builder setCustomData(JSONObject var1) {
         this.zzaqv.setCustomData(var1);
         return this;
      }

      public MediaQueueItem build() {
         this.zzaqv.zznj();
         return this.zzaqv;
      }
   }
}
