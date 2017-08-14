package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.internal.zzaye;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class MediaInfo extends com.google.android.gms.common.internal.safeparcel.zza implements ReflectedParcelable {
   public static final int STREAM_TYPE_NONE = 0;
   public static final int STREAM_TYPE_BUFFERED = 1;
   public static final int STREAM_TYPE_LIVE = 2;
   public static final int STREAM_TYPE_INVALID = -1;
   public static final long UNKNOWN_DURATION = -1L;
   public static final Creator CREATOR = new zzac();
   private final String zzapX;
   private int zzapY;
   private String zzapZ;
   private MediaMetadata zzaqa;
   private long zzaqb;
   private List zzaqc;
   private TextTrackStyle zzaqd;
   private String zzaoC;
   private List zzaqe;
   private List zzaqf;
   private JSONObject zzaoD;

   MediaInfo(String var1, int var2, String var3, MediaMetadata var4, long var5, List var7, TextTrackStyle var8, String var9, List var10, List var11) {
      this.zzapX = var1;
      this.zzapY = var2;
      this.zzapZ = var3;
      this.zzaqa = var4;
      this.zzaqb = var5;
      this.zzaqc = var7;
      this.zzaqd = var8;
      this.zzaoC = var9;
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

      this.zzaqe = var10;
      this.zzaqf = var11;
   }

   MediaInfo(String var1) throws IllegalArgumentException {
      this(var1, -1, (String)null, (MediaMetadata)null, -1L, (List)null, (TextTrackStyle)null, (String)null, (List)null, (List)null);
      if (TextUtils.isEmpty(var1)) {
         throw new IllegalArgumentException("content ID cannot be null or empty");
      }
   }

   MediaInfo(JSONObject var1) throws JSONException {
      this(var1.getString("contentId"), -1, (String)null, (MediaMetadata)null, -1L, (List)null, (TextTrackStyle)null, (String)null, (List)null, (List)null);
      String var2 = var1.getString("streamType");
      if ("NONE".equals(var2)) {
         this.zzapY = 0;
      } else if ("BUFFERED".equals(var2)) {
         this.zzapY = 1;
      } else if ("LIVE".equals(var2)) {
         this.zzapY = 2;
      } else {
         this.zzapY = -1;
      }

      this.zzapZ = var1.getString("contentType");
      JSONObject var3;
      int var4;
      if (var1.has("metadata")) {
         var4 = (var3 = var1.getJSONObject("metadata")).getInt("metadataType");
         this.zzaqa = new MediaMetadata(var4);
         this.zzaqa.zzl(var3);
      }

      this.zzaqb = -1L;
      double var7;
      if (var1.has("duration") && !var1.isNull("duration") && !Double.isNaN(var7 = var1.optDouble("duration", 0.0D)) && !Double.isInfinite(var7)) {
         this.zzaqb = (long)(var7 * 1000.0D);
      }

      if (var1.has("tracks")) {
         this.zzaqc = new ArrayList();
         JSONArray var8 = var1.getJSONArray("tracks");

         for(var4 = 0; var4 < var8.length(); ++var4) {
            JSONObject var5 = var8.getJSONObject(var4);
            MediaTrack var6 = new MediaTrack(var5);
            this.zzaqc.add(var6);
         }
      } else {
         this.zzaqc = null;
      }

      if (var1.has("textTrackStyle")) {
         var3 = var1.getJSONObject("textTrackStyle");
         TextTrackStyle var9;
         (var9 = new TextTrackStyle()).zzl(var3);
         this.zzaqd = var9;
      } else {
         this.zzaqd = null;
      }

      this.zzk(var1);
      this.zzaoD = var1.optJSONObject("customData");
   }

   public final String getContentId() {
      return this.zzapX;
   }

   final void setStreamType(int var1) throws IllegalArgumentException {
      if (var1 >= -1 && var1 <= 2) {
         this.zzapY = var1;
      } else {
         throw new IllegalArgumentException("invalid stream type");
      }
   }

   public final int getStreamType() {
      return this.zzapY;
   }

   final void setContentType(String var1) throws IllegalArgumentException {
      if (TextUtils.isEmpty(var1)) {
         throw new IllegalArgumentException("content type cannot be null or empty");
      } else {
         this.zzapZ = var1;
      }
   }

   public final String getContentType() {
      return this.zzapZ;
   }

   final void zza(MediaMetadata var1) {
      this.zzaqa = var1;
   }

   public final MediaMetadata getMetadata() {
      return this.zzaqa;
   }

   final void zzw(long var1) throws IllegalArgumentException {
      if (var1 < 0L && var1 != -1L) {
         throw new IllegalArgumentException("Invalid stream duration");
      } else {
         this.zzaqb = var1;
      }
   }

   public final long getStreamDuration() {
      return this.zzaqb;
   }

   final void zzy(List var1) {
      this.zzaqc = var1;
   }

   public final List getMediaTracks() {
      return this.zzaqc;
   }

   public final void setTextTrackStyle(TextTrackStyle var1) {
      this.zzaqd = var1;
   }

   public final TextTrackStyle getTextTrackStyle() {
      return this.zzaqd;
   }

   final void setCustomData(JSONObject var1) {
      this.zzaoD = var1;
   }

   public final JSONObject getCustomData() {
      return this.zzaoD;
   }

   public final List getAdBreaks() {
      return this.zzaqe == null ? null : Collections.unmodifiableList(this.zzaqe);
   }

   public final List getAdBreakClips() {
      return this.zzaqf == null ? null : Collections.unmodifiableList(this.zzaqf);
   }

   public final void zzz(List var1) {
      this.zzaqe = var1;
   }

   final void zzk(JSONObject var1) throws JSONException {
      JSONArray var2;
      int var3;
      if (var1.has("breaks")) {
         var2 = var1.getJSONArray("breaks");
         this.zzaqe = new ArrayList(var2.length());

         for(var3 = 0; var3 < var2.length(); ++var3) {
            AdBreakInfo var4;
            if ((var4 = AdBreakInfo.zzi(var2.getJSONObject(var3))) == null) {
               this.zzaqe.clear();
               break;
            }

            this.zzaqe.add(var4);
         }
      }

      if (var1.has("breakClips")) {
         var2 = var1.getJSONArray("breakClips");
         this.zzaqf = new ArrayList(var2.length());

         for(var3 = 0; var3 < var2.length(); ++var3) {
            AdBreakClipInfo var5;
            if ((var5 = AdBreakClipInfo.zzh(var2.getJSONObject(var3))) == null) {
               this.zzaqf.clear();
               return;
            }

            this.zzaqf.add(var5);
         }
      }

   }

   private final void zznj() throws IllegalArgumentException {
      if (TextUtils.isEmpty(this.zzapX)) {
         throw new IllegalArgumentException("content ID cannot be null or empty");
      } else if (TextUtils.isEmpty(this.zzapZ)) {
         throw new IllegalArgumentException("content type cannot be null or empty");
      } else if (this.zzapY == -1) {
         throw new IllegalArgumentException("a valid stream type must be specified");
      }
   }

   public final JSONObject toJson() {
      JSONObject var1 = new JSONObject();

      try {
         var1.put("contentId", this.zzapX);
         String var2;
         switch(this.zzapY) {
         case 1:
            var2 = "BUFFERED";
            break;
         case 2:
            var2 = "LIVE";
            break;
         default:
            var2 = "NONE";
         }

         var1.put("streamType", var2);
         if (this.zzapZ != null) {
            var1.put("contentType", this.zzapZ);
         }

         if (this.zzaqa != null) {
            var1.put("metadata", this.zzaqa.toJson());
         }

         if (this.zzaqb <= -1L) {
            var1.put("duration", JSONObject.NULL);
         } else {
            var1.put("duration", (double)this.zzaqb / 1000.0D);
         }

         if (this.zzaqc != null) {
            JSONArray var3 = new JSONArray();
            Iterator var4 = this.zzaqc.iterator();

            while(var4.hasNext()) {
               MediaTrack var5 = (MediaTrack)var4.next();
               var3.put(var5.toJson());
            }

            var1.put("tracks", var3);
         }

         if (this.zzaqd != null) {
            var1.put("textTrackStyle", this.zzaqd.toJson());
         }

         if (this.zzaoD != null) {
            var1.put("customData", this.zzaoD);
         }
      } catch (JSONException var6) {
         ;
      }

      return var1;
   }

   public final boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof MediaInfo)) {
         return false;
      } else {
         MediaInfo var2 = (MediaInfo)var1;
         if (this.zzaoD == null != (var2.zzaoD == null)) {
            return false;
         } else if (this.zzaoD != null && var2.zzaoD != null && !com.google.android.gms.common.util.zzo.zzc(this.zzaoD, var2.zzaoD)) {
            return false;
         } else {
            return zzaye.zza(this.zzapX, var2.zzapX) && this.zzapY == var2.zzapY && zzaye.zza(this.zzapZ, var2.zzapZ) && zzaye.zza(this.zzaqa, var2.zzaqa) && this.zzaqb == var2.zzaqb && zzaye.zza(this.zzaqc, var2.zzaqc) && zzaye.zza(this.zzaqd, var2.zzaqd) && zzaye.zza(this.zzaqe, var2.zzaqe) && zzaye.zza(this.zzaqf, var2.zzaqf);
         }
      }
   }

   public final int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzapX, this.zzapY, this.zzapZ, this.zzaqa, this.zzaqb, String.valueOf(this.zzaoD), this.zzaqc, this.zzaqd, this.zzaqe, this.zzaqf});
   }

   public final void writeToParcel(Parcel var1, int var2) {
      this.zzaoC = this.zzaoD == null ? null : this.zzaoD.toString();
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.getContentId(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 3, this.getStreamType());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.getContentType(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 5, this.getMetadata(), var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 6, this.getStreamDuration());
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 7, this.getMediaTracks(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 8, this.getTextTrackStyle(), var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 9, this.zzaoC, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 10, this.getAdBreaks(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 11, this.getAdBreakClips(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public static class Builder {
      private final MediaInfo zzaqg;

      public Builder(String var1) throws IllegalArgumentException {
         if (TextUtils.isEmpty(var1)) {
            throw new IllegalArgumentException("Content ID cannot be empty");
         } else {
            this.zzaqg = new MediaInfo(var1);
         }
      }

      public MediaInfo.Builder setStreamType(int var1) throws IllegalArgumentException {
         this.zzaqg.setStreamType(var1);
         return this;
      }

      public MediaInfo.Builder setContentType(String var1) throws IllegalArgumentException {
         this.zzaqg.setContentType(var1);
         return this;
      }

      public MediaInfo.Builder setMetadata(MediaMetadata var1) {
         this.zzaqg.zza(var1);
         return this;
      }

      public MediaInfo.Builder setStreamDuration(long var1) throws IllegalArgumentException {
         this.zzaqg.zzw(var1);
         return this;
      }

      public MediaInfo.Builder setCustomData(JSONObject var1) {
         this.zzaqg.setCustomData(var1);
         return this;
      }

      public MediaInfo.Builder setMediaTracks(List var1) {
         this.zzaqg.zzy(var1);
         return this;
      }

      public MediaInfo.Builder setTextTrackStyle(TextTrackStyle var1) {
         this.zzaqg.setTextTrackStyle(var1);
         return this;
      }

      public MediaInfo build() throws IllegalArgumentException {
         this.zzaqg.zznj();
         return this.zzaqg;
      }
   }
}
