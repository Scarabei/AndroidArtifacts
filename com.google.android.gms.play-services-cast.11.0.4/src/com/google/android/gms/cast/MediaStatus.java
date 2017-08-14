package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import android.util.SparseArray;
import com.google.android.gms.internal.zzaye;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MediaStatus extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final long COMMAND_PAUSE = 1L;
   public static final long COMMAND_SEEK = 2L;
   public static final long COMMAND_SET_VOLUME = 4L;
   public static final long COMMAND_TOGGLE_MUTE = 8L;
   public static final long COMMAND_SKIP_FORWARD = 16L;
   public static final long COMMAND_SKIP_BACKWARD = 32L;
   public static final int PLAYER_STATE_UNKNOWN = 0;
   public static final int PLAYER_STATE_IDLE = 1;
   public static final int PLAYER_STATE_PLAYING = 2;
   public static final int PLAYER_STATE_PAUSED = 3;
   public static final int PLAYER_STATE_BUFFERING = 4;
   public static final int IDLE_REASON_NONE = 0;
   public static final int IDLE_REASON_FINISHED = 1;
   public static final int IDLE_REASON_CANCELED = 2;
   public static final int IDLE_REASON_INTERRUPTED = 3;
   public static final int IDLE_REASON_ERROR = 4;
   public static final int REPEAT_MODE_REPEAT_OFF = 0;
   public static final int REPEAT_MODE_REPEAT_ALL = 1;
   public static final int REPEAT_MODE_REPEAT_SINGLE = 2;
   public static final int REPEAT_MODE_REPEAT_ALL_AND_SHUFFLE = 3;
   public static final Creator CREATOR = new zzag();
   private MediaInfo zzaqg;
   private long zzaqw;
   private int zzaqx;
   private double zzaqy;
   private int zzaqz;
   private int zzaqA;
   private long zzaqB;
   private long zzaqC;
   private double zzaqD;
   private boolean zzaqE;
   private long[] zzaqu;
   private int zzaqF;
   private int zzaqG;
   private String zzaoC;
   private JSONObject zzaoD;
   private int zzaqH;
   private ArrayList zzaqI;
   private boolean zzaqJ;
   private AdBreakStatus zzaqK;
   private VideoInfo zzaqL;
   private final SparseArray zzaqM;

   MediaStatus(MediaInfo var1, long var2, int var4, double var5, int var7, int var8, long var9, long var11, double var13, boolean var15, long[] var16, int var17, int var18, String var19, int var20, List var21, boolean var22, AdBreakStatus var23, VideoInfo var24) {
      this.zzaqI = new ArrayList();
      this.zzaqM = new SparseArray();
      this.zzaqg = var1;
      this.zzaqw = var2;
      this.zzaqx = var4;
      this.zzaqy = var5;
      this.zzaqz = var7;
      this.zzaqA = var8;
      this.zzaqB = var9;
      this.zzaqC = var11;
      this.zzaqD = var13;
      this.zzaqE = var15;
      this.zzaqu = var16;
      this.zzaqF = var17;
      this.zzaqG = var18;
      this.zzaoC = var19;
      if (this.zzaoC != null) {
         try {
            this.zzaoD = new JSONObject(this.zzaoC);
         } catch (JSONException var25) {
            this.zzaoD = null;
            this.zzaoC = null;
         }
      } else {
         this.zzaoD = null;
      }

      this.zzaqH = var20;
      if (var21 != null && !var21.isEmpty()) {
         this.zza((MediaQueueItem[])var21.toArray(new MediaQueueItem[var21.size()]));
      }

      this.zzaqJ = var22;
      this.zzaqK = var23;
      this.zzaqL = var24;
   }

   public MediaStatus(JSONObject var1) throws JSONException {
      this((MediaInfo)null, 0L, 0, 0.0D, 0, 0, 0L, 0L, 0.0D, false, (long[])null, 0, 0, (String)null, 0, (List)null, false, (AdBreakStatus)null, (VideoInfo)null);
      this.zza(var1, 0);
   }

   public final long zznk() {
      return this.zzaqw;
   }

   public int getPlayerState() {
      return this.zzaqz;
   }

   public int getIdleReason() {
      return this.zzaqA;
   }

   public double getPlaybackRate() {
      return this.zzaqy;
   }

   public MediaInfo getMediaInfo() {
      return this.zzaqg;
   }

   public long getStreamPosition() {
      return this.zzaqB;
   }

   public boolean isMediaCommandSupported(long var1) {
      return (this.zzaqC & var1) != 0L;
   }

   public double getStreamVolume() {
      return this.zzaqD;
   }

   public boolean isMute() {
      return this.zzaqE;
   }

   public long[] getActiveTrackIds() {
      return this.zzaqu;
   }

   public JSONObject getCustomData() {
      return this.zzaoD;
   }

   public int getCurrentItemId() {
      return this.zzaqx;
   }

   public int getLoadingItemId() {
      return this.zzaqF;
   }

   public int getPreloadedItemId() {
      return this.zzaqG;
   }

   public int getQueueRepeatMode() {
      return this.zzaqH;
   }

   public List getQueueItems() {
      return this.zzaqI;
   }

   public int getQueueItemCount() {
      return this.zzaqI.size();
   }

   public MediaQueueItem getQueueItemById(int var1) {
      return this.getItemById(var1);
   }

   public MediaQueueItem getQueueItem(int var1) {
      return this.getItemByIndex(var1);
   }

   public boolean isPlayingAd() {
      return this.zzaqJ;
   }

   public final void zzV(boolean var1) {
      this.zzaqJ = var1;
   }

   public AdBreakStatus getAdBreakStatus() {
      return this.zzaqK;
   }

   public VideoInfo getVideoInfo() {
      return this.zzaqL;
   }

   public final int zza(JSONObject var1, int var2) throws JSONException {
      int var3 = 0;
      long var4;
      if ((var4 = var1.getLong("mediaSessionId")) != this.zzaqw) {
         this.zzaqw = var4;
         var3 = 1;
      }

      if (var1.has("playerState")) {
         byte var6 = 0;
         String var7;
         if ((var7 = var1.getString("playerState")).equals("IDLE")) {
            var6 = 1;
         } else if (var7.equals("PLAYING")) {
            var6 = 2;
         } else if (var7.equals("PAUSED")) {
            var6 = 3;
         } else if (var7.equals("BUFFERING")) {
            var6 = 4;
         }

         if (var6 != this.zzaqz) {
            this.zzaqz = var6;
            var3 |= 2;
         }

         if (var6 == 1 && var1.has("idleReason")) {
            byte var8 = 0;
            if ((var7 = var1.getString("idleReason")).equals("CANCELLED")) {
               var8 = 2;
            } else if (var7.equals("INTERRUPTED")) {
               var8 = 3;
            } else if (var7.equals("FINISHED")) {
               var8 = 1;
            } else if (var7.equals("ERROR")) {
               var8 = 4;
            }

            if (var8 != this.zzaqA) {
               this.zzaqA = var8;
               var3 |= 2;
            }
         }
      }

      if (var1.has("playbackRate")) {
         double var25 = var1.getDouble("playbackRate");
         if (this.zzaqy != var25) {
            this.zzaqy = var25;
            var3 |= 2;
         }
      }

      long var27;
      if (var1.has("currentTime") && (var2 & 2) == 0 && (var27 = (long)(var1.getDouble("currentTime") * 1000.0D)) != this.zzaqB) {
         this.zzaqB = var27;
         var3 |= 2;
      }

      if (var1.has("supportedMediaCommands") && (var27 = var1.getLong("supportedMediaCommands")) != this.zzaqC) {
         this.zzaqC = var27;
         var3 |= 2;
      }

      if (var1.has("volume") && (var2 & 1) == 0) {
         double var24;
         JSONObject var28;
         if ((var24 = (var28 = var1.getJSONObject("volume")).getDouble("level")) != this.zzaqD) {
            this.zzaqD = var24;
            var3 |= 2;
         }

         boolean var9;
         if ((var9 = var28.getBoolean("muted")) != this.zzaqE) {
            this.zzaqE = var9;
            var3 |= 2;
         }
      }

      boolean var29 = false;
      long[] var26 = null;
      int var10;
      int var30;
      if (var1.has("activeTrackIds")) {
         JSONArray var32;
         var26 = new long[var30 = (var32 = var1.getJSONArray("activeTrackIds")).length()];

         for(var10 = 0; var10 < var30; ++var10) {
            var26[var10] = var32.getLong(var10);
         }

         if (this.zzaqu == null) {
            var29 = true;
         } else if (this.zzaqu.length != var30) {
            var29 = true;
         } else {
            for(var10 = 0; var10 < var30; ++var10) {
               if (this.zzaqu[var10] != var26[var10]) {
                  var29 = true;
                  break;
               }
            }
         }

         if (var29) {
            this.zzaqu = var26;
         }
      } else if (this.zzaqu != null) {
         var29 = true;
      }

      if (var29) {
         this.zzaqu = var26;
         var3 |= 2;
      }

      if (var1.has("customData")) {
         this.zzaoD = var1.getJSONObject("customData");
         this.zzaoC = null;
         var3 |= 2;
      }

      if (var1.has("media")) {
         JSONObject var33 = var1.getJSONObject("media");
         MediaInfo var31 = new MediaInfo(var33);
         if (this.zzaqg == null || this.zzaqg != null && !this.zzaqg.equals(var31)) {
            this.zzaqg = var31;
            var3 |= 2;
         }

         if (var33.has("metadata")) {
            var3 |= 4;
         }
      }

      int var34;
      if (var1.has("currentItemId")) {
         var34 = var1.getInt("currentItemId");
         if (this.zzaqx != var34) {
            this.zzaqx = var34;
            var3 |= 2;
         }
      }

      var34 = var1.optInt("preloadedItemId", 0);
      if (this.zzaqG != var34) {
         this.zzaqG = var34;
         var3 |= 16;
      }

      var30 = var1.optInt("loadingItemId", 0);
      if (this.zzaqF != var30) {
         this.zzaqF = var30;
         var3 |= 2;
      }

      var10 = this.zzaqg == null ? -1 : this.zzaqg.getStreamType();
      int var15 = this.zzaqF;
      int var14 = this.zzaqA;
      boolean var10000;
      if (this.zzaqz != 1) {
         var10000 = false;
      } else {
         label252: {
            switch(var14) {
            case 1:
            case 3:
               if (var15 != 0) {
                  var10000 = false;
                  break label252;
               }
               break;
            case 2:
               if (var10 == 2) {
                  var10000 = false;
                  break label252;
               }
            }

            var10000 = true;
         }
      }

      if (!var10000) {
         MediaStatus var13 = this;
         boolean var36 = false;
         if (var1.has("repeatMode")) {
            int var16 = this.zzaqH;
            String var18 = var1.getString("repeatMode");
            byte var19 = -1;
            switch(var18.hashCode()) {
            case -1118317585:
               if (var18.equals("REPEAT_ALL_AND_SHUFFLE")) {
                  var19 = 3;
               }
               break;
            case -962896020:
               if (var18.equals("REPEAT_SINGLE")) {
                  var19 = 2;
               }
               break;
            case 1645938909:
               if (var18.equals("REPEAT_ALL")) {
                  var19 = 1;
               }
               break;
            case 1645952171:
               if (var18.equals("REPEAT_OFF")) {
                  var19 = 0;
               }
            }

            switch(var19) {
            case 0:
               var16 = 0;
               break;
            case 1:
               var16 = 1;
               break;
            case 2:
               var16 = 2;
               break;
            case 3:
               var16 = 3;
            }

            if (this.zzaqH != var16) {
               this.zzaqH = var16;
               var36 = true;
            }
         }

         if (var1.has("items")) {
            JSONArray var37;
            int var17 = (var37 = var1.getJSONArray("items")).length();
            SparseArray var38 = new SparseArray();

            for(int var39 = 0; var39 < var17; ++var39) {
               var38.put(var39, var37.getJSONObject(var39).getInt("itemId"));
            }

            MediaQueueItem[] var40 = new MediaQueueItem[var17];

            for(int var20 = 0; var20 < var17; ++var20) {
               Integer var21 = (Integer)var38.get(var20);
               JSONObject var22 = var37.getJSONObject(var20);
               MediaQueueItem var23;
               if ((var23 = var13.getItemById(var21.intValue())) != null) {
                  var36 |= var23.zzm(var22);
                  var40[var20] = var23;
                  if (var20 != var13.getIndexById(var21.intValue()).intValue()) {
                     var36 = true;
                  }
               } else {
                  var36 = true;
                  if (var21.intValue() == var13.zzaqx) {
                     var40[var20] = (new MediaQueueItem.Builder(var13.zzaqg)).build();
                     var40[var20].zzm(var22);
                  } else {
                     var40[var20] = new MediaQueueItem(var22);
                  }
               }
            }

            if (var13.zzaqI.size() != var17) {
               var36 = true;
            }

            var13.zza(var40);
         }

         if (var36) {
            var3 |= 8;
         }
      } else {
         this.zzaqx = 0;
         this.zzaqF = 0;
         this.zzaqG = 0;
         if (!this.zzaqI.isEmpty()) {
            this.zzaqH = 0;
            this.zzaqI.clear();
            this.zzaqM.clear();
            var3 |= 8;
         }
      }

      AdBreakStatus var11 = AdBreakStatus.zzj(var1.optJSONObject("breakStatus"));
      if (this.zzaqK == null && var11 != null || this.zzaqK != null && !this.zzaqK.equals(var11)) {
         boolean var35;
         this.zzaqJ = var35 = var11 != null;
         this.zzaqK = var11;
         var3 |= 32;
      }

      VideoInfo var12 = VideoInfo.zzn(var1.optJSONObject("videoInfo"));
      if (this.zzaqL == null && var12 != null || this.zzaqL != null && !this.zzaqL.equals(var12)) {
         this.zzaqL = var12;
         var3 |= 64;
      }

      if (var1.has("breakInfo") && this.zzaqg != null) {
         this.zzaqg.zzk(var1.getJSONObject("breakInfo"));
         var3 |= 2;
      }

      return var3;
   }

   public MediaQueueItem getItemById(int var1) {
      Integer var2;
      return (var2 = (Integer)this.zzaqM.get(var1)) == null ? null : (MediaQueueItem)this.zzaqI.get(var2.intValue());
   }

   public MediaQueueItem getItemByIndex(int var1) {
      return var1 >= 0 && var1 < this.zzaqI.size() ? (MediaQueueItem)this.zzaqI.get(var1) : null;
   }

   public Integer getIndexById(int var1) {
      return (Integer)this.zzaqM.get(var1);
   }

   private final void zza(MediaQueueItem[] var1) {
      this.zzaqI.clear();
      this.zzaqM.clear();

      for(int var2 = 0; var2 < var1.length; ++var2) {
         MediaQueueItem var3 = var1[var2];
         this.zzaqI.add(var3);
         this.zzaqM.put(var3.getItemId(), var2);
      }

   }

   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof MediaStatus)) {
         return false;
      } else {
         MediaStatus var2 = (MediaStatus)var1;
         if (this.zzaoD == null != (var2.zzaoD == null)) {
            return false;
         } else {
            return this.zzaqw == var2.zzaqw && this.zzaqx == var2.zzaqx && this.zzaqy == var2.zzaqy && this.zzaqz == var2.zzaqz && this.zzaqA == var2.zzaqA && this.zzaqB == var2.zzaqB && this.zzaqD == var2.zzaqD && this.zzaqE == var2.zzaqE && this.zzaqF == var2.zzaqF && this.zzaqG == var2.zzaqG && this.zzaqH == var2.zzaqH && Arrays.equals(this.zzaqu, var2.zzaqu) && zzaye.zza(this.zzaqC, var2.zzaqC) && zzaye.zza(this.zzaqI, var2.zzaqI) && zzaye.zza(this.zzaqg, var2.zzaqg) && (this.zzaoD == null || var2.zzaoD == null || com.google.android.gms.common.util.zzo.zzc(this.zzaoD, var2.zzaoD)) && this.zzaqJ == var2.isPlayingAd();
         }
      }
   }

   public int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzaqg, this.zzaqw, this.zzaqx, this.zzaqy, this.zzaqz, this.zzaqA, this.zzaqB, this.zzaqC, this.zzaqD, this.zzaqE, Arrays.hashCode(this.zzaqu), this.zzaqF, this.zzaqG, String.valueOf(this.zzaoD), this.zzaqH, this.zzaqI, this.zzaqJ});
   }

   public void writeToParcel(Parcel var1, int var2) {
      this.zzaoC = this.zzaoD == null ? null : this.zzaoD.toString();
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.getMediaInfo(), var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.zzaqw);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 4, this.getCurrentItemId());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 5, this.getPlaybackRate());
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 6, this.getPlayerState());
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 7, this.getIdleReason());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 8, this.getStreamPosition());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 9, this.zzaqC);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 10, this.getStreamVolume());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 11, this.isMute());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 12, this.getActiveTrackIds(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 13, this.getLoadingItemId());
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 14, this.getPreloadedItemId());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 15, this.zzaoC, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 16, this.zzaqH);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 17, this.zzaqI, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 18, this.isPlayingAd());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 19, this.getAdBreakStatus(), var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 20, this.getVideoInfo(), var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public AdBreakInfo getCurrentAdBreak() {
      if (this.zzaqK != null && this.zzaqg != null) {
         String var1;
         if (TextUtils.isEmpty(var1 = this.zzaqK.getBreakId())) {
            return null;
         } else {
            List var2;
            if ((var2 = this.zzaqg.getAdBreaks()) != null && !var2.isEmpty()) {
               Iterator var3 = var2.iterator();

               AdBreakInfo var4;
               do {
                  if (!var3.hasNext()) {
                     return null;
                  }

                  var4 = (AdBreakInfo)var3.next();
               } while(!var1.equals(var4.getId()));

               return var4;
            } else {
               return null;
            }
         }
      } else {
         return null;
      }
   }

   public AdBreakClipInfo getCurrentAdBreakClip() {
      if (this.zzaqK != null && this.zzaqg != null) {
         String var1;
         if (TextUtils.isEmpty(var1 = this.zzaqK.getBreakClipId())) {
            return null;
         } else {
            List var2;
            if ((var2 = this.zzaqg.getAdBreakClips()) != null && !var2.isEmpty()) {
               Iterator var3 = var2.iterator();

               AdBreakClipInfo var4;
               do {
                  if (!var3.hasNext()) {
                     return null;
                  }

                  var4 = (AdBreakClipInfo)var3.next();
               } while(!var1.equals(var4.getId()));

               return var4;
            } else {
               return null;
            }
         }
      } else {
         return null;
      }
   }
}
