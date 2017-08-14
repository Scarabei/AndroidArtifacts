package com.google.android.gms.internal;

import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaQueueItem;
import com.google.android.gms.cast.MediaStatus;
import com.google.android.gms.cast.TextTrackStyle;
import com.google.android.gms.common.util.zzi;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class zzayp extends zzaxt {
   public static final String NAMESPACE = zzaye.zzcj("com.google.cast.media");
   private long zzayA;
   private MediaStatus zzayB;
   private final List zzawS;
   private zzayq zzayC;
   private final zzayu zzayD;
   private final zzayu zzayE;
   private final zzayu zzayF;
   private final zzayu zzayG;
   private final zzayu zzayH;
   private final zzayu zzayI;
   private final zzayu zzayJ;
   private final zzayu zzayK;
   private final zzayu zzayL;
   private final zzayu zzayM;
   private final zzayu zzayN;
   private final zzayu zzayO;
   private final zzayu zzayP;
   private final zzayu zzayQ;

   public zzayp(String var1) {
      super(NAMESPACE, zzi.zzrY(), "MediaControlChannel", (String)null, 1000L);
      this.zzayD = new zzayu(this.zzvw, 86400000L);
      this.zzayE = new zzayu(this.zzvw, 86400000L);
      this.zzayF = new zzayu(this.zzvw, 86400000L);
      this.zzayG = new zzayu(this.zzvw, 86400000L);
      this.zzayH = new zzayu(this.zzvw, 86400000L);
      this.zzayI = new zzayu(this.zzvw, 86400000L);
      this.zzayJ = new zzayu(this.zzvw, 86400000L);
      this.zzayK = new zzayu(this.zzvw, 86400000L);
      this.zzayL = new zzayu(this.zzvw, 86400000L);
      this.zzayM = new zzayu(this.zzvw, 86400000L);
      this.zzayN = new zzayu(this.zzvw, 86400000L);
      this.zzayO = new zzayu(this.zzvw, 86400000L);
      this.zzayP = new zzayu(this.zzvw, 86400000L);
      this.zzayQ = new zzayu(this.zzvw, 86400000L);
      this.zzawS = new ArrayList();
      this.zzawS.add(this.zzayD);
      this.zzawS.add(this.zzayE);
      this.zzawS.add(this.zzayF);
      this.zzawS.add(this.zzayG);
      this.zzawS.add(this.zzayH);
      this.zzawS.add(this.zzayI);
      this.zzawS.add(this.zzayJ);
      this.zzawS.add(this.zzayK);
      this.zzawS.add(this.zzayL);
      this.zzawS.add(this.zzayM);
      this.zzawS.add(this.zzayN);
      this.zzawS.add(this.zzayO);
      this.zzawS.add(this.zzayP);
      this.zzawS.add(this.zzayQ);
      this.zzoM();
   }

   public final void zza(zzayq var1) {
      this.zzayC = var1;
   }

   public final long zza(zzayt var1, MediaInfo var2, boolean var3, long var4, long[] var6, JSONObject var7) throws IOException {
      JSONObject var8 = new JSONObject();
      long var9 = this.zzoA();
      this.zzayD.zza(var9, var1);
      this.zzZ(true);

      try {
         var8.put("requestId", var9);
         var8.put("type", "LOAD");
         var8.put("media", var2.toJson());
         var8.put("autoplay", var3);
         var8.put("currentTime", (double)var4 / 1000.0D);
         if (var6 != null) {
            JSONArray var11 = new JSONArray();

            for(int var12 = 0; var12 < var6.length; ++var12) {
               var11.put(var12, var6[var12]);
            }

            var8.put("activeTrackIds", var11);
         }

         if (var7 != null) {
            var8.put("customData", var7);
         }
      } catch (JSONException var13) {
         ;
      }

      this.zza(var8.toString(), var9, (String)null);
      return var9;
   }

   public final long zza(zzayt var1, JSONObject var2) throws IOException, zzayr {
      JSONObject var3 = new JSONObject();
      long var4 = this.zzoA();
      this.zzayE.zza(var4, var1);
      this.zzZ(true);

      try {
         var3.put("requestId", var4);
         var3.put("type", "PAUSE");
         var3.put("mediaSessionId", this.zznk());
         if (var2 != null) {
            var3.put("customData", var2);
         }
      } catch (JSONException var6) {
         ;
      }

      this.zza(var3.toString(), var4, (String)null);
      return var4;
   }

   public final long zzb(zzayt var1, JSONObject var2) throws IOException, zzayr {
      JSONObject var3 = new JSONObject();
      long var4 = this.zzoA();
      this.zzayG.zza(var4, var1);
      this.zzZ(true);

      try {
         var3.put("requestId", var4);
         var3.put("type", "STOP");
         var3.put("mediaSessionId", this.zznk());
         if (var2 != null) {
            var3.put("customData", var2);
         }
      } catch (JSONException var6) {
         ;
      }

      this.zza(var3.toString(), var4, (String)null);
      return var4;
   }

   public final long zzc(zzayt var1, JSONObject var2) throws IOException, zzayr {
      JSONObject var3 = new JSONObject();
      long var4 = this.zzoA();
      this.zzayF.zza(var4, var1);
      this.zzZ(true);

      try {
         var3.put("requestId", var4);
         var3.put("type", "PLAY");
         var3.put("mediaSessionId", this.zznk());
         if (var2 != null) {
            var3.put("customData", var2);
         }
      } catch (JSONException var6) {
         ;
      }

      this.zza(var3.toString(), var4, (String)null);
      return var4;
   }

   public final long zza(zzayt var1, long var2, int var4, JSONObject var5) throws IOException, zzayr {
      JSONObject var6 = new JSONObject();
      long var7 = this.zzoA();
      this.zzayH.zza(var7, var1);
      this.zzZ(true);

      try {
         var6.put("requestId", var7);
         var6.put("type", "SEEK");
         var6.put("mediaSessionId", this.zznk());
         var6.put("currentTime", (double)var2 / 1000.0D);
         if (var4 == 1) {
            var6.put("resumeState", "PLAYBACK_START");
         } else if (var4 == 2) {
            var6.put("resumeState", "PLAYBACK_PAUSE");
         }

         if (var5 != null) {
            var6.put("customData", var5);
         }
      } catch (JSONException var9) {
         ;
      }

      this.zza(var6.toString(), var7, (String)null);
      return var7;
   }

   public final long zza(zzayt var1, double var2, JSONObject var4) throws IOException, zzayr, IllegalArgumentException {
      if (!Double.isInfinite(var2) && !Double.isNaN(var2)) {
         JSONObject var5 = new JSONObject();
         long var6 = this.zzoA();
         this.zzayI.zza(var6, var1);
         this.zzZ(true);

         try {
            var5.put("requestId", var6);
            var5.put("type", "SET_VOLUME");
            var5.put("mediaSessionId", this.zznk());
            JSONObject var8;
            (var8 = new JSONObject()).put("level", var2);
            var5.put("volume", var8);
            if (var4 != null) {
               var5.put("customData", var4);
            }
         } catch (JSONException var9) {
            ;
         }

         this.zza(var5.toString(), var6, (String)null);
         return var6;
      } else {
         throw new IllegalArgumentException((new StringBuilder(41)).append("Volume cannot be ").append(var2).toString());
      }
   }

   public final long zza(zzayt var1, boolean var2, JSONObject var3) throws IOException, zzayr {
      JSONObject var4 = new JSONObject();
      long var5 = this.zzoA();
      this.zzayJ.zza(var5, var1);
      this.zzZ(true);

      try {
         var4.put("requestId", var5);
         var4.put("type", "SET_VOLUME");
         var4.put("mediaSessionId", this.zznk());
         JSONObject var7;
         (var7 = new JSONObject()).put("muted", var2);
         var4.put("volume", var7);
         if (var3 != null) {
            var4.put("customData", var3);
         }
      } catch (JSONException var8) {
         ;
      }

      this.zza(var4.toString(), var5, (String)null);
      return var5;
   }

   public final long zza(zzayt var1) throws IOException {
      JSONObject var2 = new JSONObject();
      long var3 = this.zzoA();
      this.zzayK.zza(var3, var1);
      this.zzZ(true);

      try {
         var2.put("requestId", var3);
         var2.put("type", "GET_STATUS");
         if (this.zzayB != null) {
            var2.put("mediaSessionId", this.zzayB.zznk());
         }
      } catch (JSONException var5) {
         ;
      }

      this.zza(var2.toString(), var3, (String)null);
      return var3;
   }

   public final long zza(zzayt var1, long[] var2) throws IOException, zzayr {
      JSONObject var3 = new JSONObject();
      long var4 = this.zzoA();
      this.zzayL.zza(var4, var1);
      this.zzZ(true);

      try {
         var3.put("requestId", var4);
         var3.put("type", "EDIT_TRACKS_INFO");
         var3.put("mediaSessionId", this.zznk());
         JSONArray var6 = new JSONArray();

         for(int var7 = 0; var7 < var2.length; ++var7) {
            var6.put(var7, var2[var7]);
         }

         var3.put("activeTrackIds", var6);
      } catch (JSONException var8) {
         ;
      }

      this.zza(var3.toString(), var4, (String)null);
      return var4;
   }

   public final long zza(zzayt var1, TextTrackStyle var2) throws IOException, zzayr {
      JSONObject var3 = new JSONObject();
      long var4 = this.zzoA();
      this.zzayM.zza(var4, var1);
      this.zzZ(true);

      try {
         var3.put("requestId", var4);
         var3.put("type", "EDIT_TRACKS_INFO");
         if (var2 != null) {
            var3.put("textTrackStyle", var2.toJson());
         }

         var3.put("mediaSessionId", this.zznk());
      } catch (JSONException var6) {
         ;
      }

      this.zza(var3.toString(), var4, (String)null);
      return var4;
   }

   public final long getApproximateStreamPosition() {
      MediaInfo var1;
      if ((var1 = this.getMediaInfo()) == null) {
         return 0L;
      } else if (this.zzayA == 0L) {
         return 0L;
      } else {
         double var2 = this.zzayB.getPlaybackRate();
         long var4 = this.zzayB.getStreamPosition();
         int var6 = this.zzayB.getPlayerState();
         if (var2 != 0.0D && var6 == 2) {
            long var8 = var1.getStreamDuration();
            long var10;
            if ((var10 = this.zzvw.elapsedRealtime() - this.zzayA) < 0L) {
               var10 = 0L;
            }

            if (var10 == 0L) {
               return var4;
            } else {
               long var12 = var4 + (long)((double)var10 * var2);
               if (var8 > 0L && var12 > var8) {
                  var12 = var8;
               } else if (var12 < 0L) {
                  var12 = 0L;
               }

               return var12;
            }
         } else {
            return var4;
         }
      }
   }

   public final long getStreamDuration() {
      MediaInfo var1;
      return (var1 = this.getMediaInfo()) != null ? var1.getStreamDuration() : 0L;
   }

   public final MediaStatus getMediaStatus() {
      return this.zzayB;
   }

   public final MediaInfo getMediaInfo() {
      return this.zzayB == null ? null : this.zzayB.getMediaInfo();
   }

   public final long zza(zzayt var1, MediaQueueItem[] var2, int var3, int var4, long var5, JSONObject var7) throws IOException, IllegalArgumentException {
      if (var2 != null && var2.length != 0) {
         if (var3 >= 0 && var3 < var2.length) {
            if (var5 != -1L && var5 < 0L) {
               throw new IllegalArgumentException((new StringBuilder(54)).append("playPosition can not be negative: ").append(var5).toString());
            } else {
               JSONObject var8 = new JSONObject();
               long var9 = this.zzoA();
               this.zzayD.zza(var9, var1);
               this.zzZ(true);

               try {
                  var8.put("requestId", var9);
                  var8.put("type", "QUEUE_LOAD");
                  JSONArray var11 = new JSONArray();

                  for(int var12 = 0; var12 < var2.length; ++var12) {
                     var11.put(var12, var2[var12].toJson());
                  }

                  var8.put("items", var11);
                  switch(var4) {
                  case 0:
                     var8.put("repeatMode", "REPEAT_OFF");
                     break;
                  case 1:
                     var8.put("repeatMode", "REPEAT_ALL");
                     break;
                  case 2:
                     var8.put("repeatMode", "REPEAT_SINGLE");
                     break;
                  case 3:
                     var8.put("repeatMode", "REPEAT_ALL_AND_SHUFFLE");
                     break;
                  default:
                     throw new IllegalArgumentException((new StringBuilder(32)).append("Invalid repeat mode: ").append(var4).toString());
                  }

                  var8.put("startIndex", var3);
                  if (var5 != -1L) {
                     var8.put("currentTime", (double)var5 / 1000.0D);
                  }

                  if (var7 != null) {
                     var8.put("customData", var7);
                  }
               } catch (JSONException var13) {
                  ;
               }

               this.zza(var8.toString(), var9, (String)null);
               return var9;
            }
         } else {
            throw new IllegalArgumentException((new StringBuilder(31)).append("Invalid startIndex: ").append(var3).toString());
         }
      } else {
         throw new IllegalArgumentException("items must not be null or empty.");
      }
   }

   public final long zza(zzayt var1, MediaQueueItem[] var2, int var3, int var4, int var5, long var6, JSONObject var8) throws IOException, zzayr, IllegalArgumentException {
      if (var2 != null && var2.length != 0) {
         if (var5 != -1 && (var5 < 0 || var5 >= var2.length)) {
            throw new IllegalArgumentException(String.format(Locale.ROOT, "currentItemIndexInItemsToInsert %d out of range [0, %d).", var5, var2.length));
         } else if (var6 != -1L && var6 < 0L) {
            throw new IllegalArgumentException((new StringBuilder(54)).append("playPosition can not be negative: ").append(var6).toString());
         } else {
            JSONObject var9 = new JSONObject();
            long var10 = this.zzoA();
            this.zzayN.zza(var10, var1);
            this.zzZ(true);

            try {
               var9.put("requestId", var10);
               var9.put("type", "QUEUE_INSERT");
               var9.put("mediaSessionId", this.zznk());
               JSONArray var12 = new JSONArray();

               for(int var13 = 0; var13 < var2.length; ++var13) {
                  var12.put(var13, var2[var13].toJson());
               }

               var9.put("items", var12);
               if (var3 != 0) {
                  var9.put("insertBefore", var3);
               }

               if (var5 != -1) {
                  var9.put("currentItemIndex", var5);
               }

               if (var6 != -1L) {
                  var9.put("currentTime", (double)var6 / 1000.0D);
               }

               if (var8 != null) {
                  var9.put("customData", var8);
               }
            } catch (JSONException var14) {
               ;
            }

            this.zza(var9.toString(), var10, (String)null);
            return var10;
         }
      } else {
         throw new IllegalArgumentException("itemsToInsert must not be null or empty.");
      }
   }

   public final long zza(zzayt var1, int var2, long var3, MediaQueueItem[] var5, int var6, Integer var7, JSONObject var8) throws IllegalArgumentException, IOException, zzayr {
      if (var3 != -1L && var3 < 0L) {
         throw new IllegalArgumentException((new StringBuilder(53)).append("playPosition cannot be negative: ").append(var3).toString());
      } else {
         JSONObject var9 = new JSONObject();
         long var10 = this.zzoA();
         this.zzayO.zza(var10, var1);
         this.zzZ(true);

         try {
            var9.put("requestId", var10);
            var9.put("type", "QUEUE_UPDATE");
            var9.put("mediaSessionId", this.zznk());
            if (var2 != 0) {
               var9.put("currentItemId", var2);
            }

            if (var6 != 0) {
               var9.put("jump", var6);
            }

            if (var5 != null && var5.length > 0) {
               JSONArray var12 = new JSONArray();

               for(int var13 = 0; var13 < var5.length; ++var13) {
                  var12.put(var13, var5[var13].toJson());
               }

               var9.put("items", var12);
            }

            if (var7 != null) {
               switch(var7.intValue()) {
               case 0:
                  var9.put("repeatMode", "REPEAT_OFF");
                  break;
               case 1:
                  var9.put("repeatMode", "REPEAT_ALL");
                  break;
               case 2:
                  var9.put("repeatMode", "REPEAT_SINGLE");
                  break;
               case 3:
                  var9.put("repeatMode", "REPEAT_ALL_AND_SHUFFLE");
               }
            }

            if (var3 != -1L) {
               var9.put("currentTime", (double)var3 / 1000.0D);
            }

            if (var8 != null) {
               var9.put("customData", var8);
            }
         } catch (JSONException var14) {
            ;
         }

         this.zza(var9.toString(), var10, (String)null);
         return var10;
      }
   }

   public final long zza(zzayt var1, int[] var2, JSONObject var3) throws IOException, zzayr, IllegalArgumentException {
      if (var2 != null && var2.length != 0) {
         JSONObject var4 = new JSONObject();
         long var5 = this.zzoA();
         this.zzayP.zza(var5, var1);
         this.zzZ(true);

         try {
            var4.put("requestId", var5);
            var4.put("type", "QUEUE_REMOVE");
            var4.put("mediaSessionId", this.zznk());
            JSONArray var7 = new JSONArray();

            for(int var8 = 0; var8 < var2.length; ++var8) {
               var7.put(var8, var2[var8]);
            }

            var4.put("itemIds", var7);
            if (var3 != null) {
               var4.put("customData", var3);
            }
         } catch (JSONException var9) {
            ;
         }

         this.zza(var4.toString(), var5, (String)null);
         return var5;
      } else {
         throw new IllegalArgumentException("itemIdsToRemove must not be null or empty.");
      }
   }

   public final long zza(zzayt var1, int[] var2, int var3, JSONObject var4) throws IOException, zzayr, IllegalArgumentException {
      if (var2 != null && var2.length != 0) {
         JSONObject var5 = new JSONObject();
         long var6 = this.zzoA();
         this.zzayQ.zza(var6, var1);
         this.zzZ(true);

         try {
            var5.put("requestId", var6);
            var5.put("type", "QUEUE_REORDER");
            var5.put("mediaSessionId", this.zznk());
            JSONArray var8 = new JSONArray();

            for(int var9 = 0; var9 < var2.length; ++var9) {
               var8.put(var9, var2[var9]);
            }

            var5.put("itemIds", var8);
            if (var3 != 0) {
               var5.put("insertBefore", var3);
            }

            if (var4 != null) {
               var5.put("customData", var4);
            }
         } catch (JSONException var10) {
            ;
         }

         this.zza(var5.toString(), var6, (String)null);
         return var6;
      } else {
         throw new IllegalArgumentException("itemIdsToReorder must not be null or empty.");
      }
   }

   public final void zzch(String var1) {
      this.zzarK.zzb("message received: %s", var1);

      try {
         JSONObject var2;
         String var3 = (var2 = new JSONObject(var1)).getString("type");
         long var4 = var2.optLong("requestId", -1L);
         byte var7 = -1;
         switch(var3.hashCode()) {
         case -1830647528:
            if (var3.equals("LOAD_CANCELLED")) {
               var7 = 3;
            }
            break;
         case -1125000185:
            if (var3.equals("INVALID_REQUEST")) {
               var7 = 4;
            }
            break;
         case -262628938:
            if (var3.equals("LOAD_FAILED")) {
               var7 = 2;
            }
            break;
         case 431600379:
            if (var3.equals("INVALID_PLAYER_STATE")) {
               var7 = 1;
            }
            break;
         case 823510221:
            if (var3.equals("MEDIA_STATUS")) {
               var7 = 0;
            }
         }

         JSONObject var9;
         Iterator var10;
         switch(var7) {
         case 0:
            JSONArray var8;
            if ((var8 = var2.getJSONArray("status")).length() > 0) {
               this.zza(var4, var8.getJSONObject(0));
               return;
            }

            this.zzayB = null;
            this.onStatusUpdated();
            this.onMetadataUpdated();
            this.onQueueStatusUpdated();
            this.onPreloadStatusUpdated();
            this.zzayK.zzc(var4, 0, (Object)null);
            return;
         case 1:
            this.zzarK.zzf("received unexpected error: Invalid Player State.");
            var9 = var2.optJSONObject("customData");
            var10 = this.zzawS.iterator();

            while(var10.hasNext()) {
               ((zzayu)var10.next()).zzc(var4, 2100, var9);
            }

            return;
         case 2:
            var9 = var2.optJSONObject("customData");
            this.zzayD.zzc(var4, 2100, var9);
            return;
         case 3:
            var9 = var2.optJSONObject("customData");
            this.zzayD.zzc(var4, 2101, var9);
            return;
         case 4:
            this.zzarK.zzf("received unexpected error: Invalid Request.");
            var9 = var2.optJSONObject("customData");
            var10 = this.zzawS.iterator();

            while(var10.hasNext()) {
               ((zzayu)var10.next()).zzc(var4, 2100, var9);
            }
         default:
         }
      } catch (JSONException var11) {
         this.zzarK.zzf("Message is malformed (%s); ignoring: %s", var11.getMessage(), var1);
      }
   }

   private final void zza(long var1, JSONObject var3) throws JSONException {
      boolean var4 = this.zzayD.test(var1);
      boolean var5 = this.zzayH.zzoO() && !this.zzayH.test(var1);
      boolean var6 = this.zzayI.zzoO() && !this.zzayI.test(var1) || this.zzayJ.zzoO() && !this.zzayJ.test(var1);
      int var7 = 0;
      if (var5) {
         var7 = 2;
      }

      if (var6) {
         var7 |= 1;
      }

      int var8;
      if (!var4 && this.zzayB != null) {
         var8 = this.zzayB.zza(var3, var7);
      } else {
         this.zzayB = new MediaStatus(var3);
         this.zzayA = this.zzvw.elapsedRealtime();
         var8 = 127;
      }

      if ((var8 & 1) != 0) {
         this.zzayA = this.zzvw.elapsedRealtime();
         this.onStatusUpdated();
      }

      if ((var8 & 2) != 0) {
         this.zzayA = this.zzvw.elapsedRealtime();
         this.onStatusUpdated();
      }

      if ((var8 & 4) != 0) {
         this.onMetadataUpdated();
      }

      if ((var8 & 8) != 0) {
         this.onQueueStatusUpdated();
      }

      if ((var8 & 16) != 0) {
         this.onPreloadStatusUpdated();
      }

      if ((var8 & 32) != 0) {
         this.zzayA = this.zzvw.elapsedRealtime();
         if (this.zzayC != null) {
            this.zzayC.onAdBreakStatusUpdated();
         }
      }

      if ((var8 & 64) != 0) {
         this.zzayA = this.zzvw.elapsedRealtime();
         this.onStatusUpdated();
      }

      Iterator var9 = this.zzawS.iterator();

      while(var9.hasNext()) {
         ((zzayu)var9.next()).zzc(var1, 0, (Object)null);
      }

   }

   private final long zznk() throws zzayr {
      if (this.zzayB == null) {
         throw new zzayr();
      } else {
         return this.zzayB.zznk();
      }
   }

   private final void onStatusUpdated() {
      if (this.zzayC != null) {
         this.zzayC.onStatusUpdated();
      }

   }

   private final void onMetadataUpdated() {
      if (this.zzayC != null) {
         this.zzayC.onMetadataUpdated();
      }

   }

   private final void onQueueStatusUpdated() {
      if (this.zzayC != null) {
         this.zzayC.onQueueStatusUpdated();
      }

   }

   private final void onPreloadStatusUpdated() {
      if (this.zzayC != null) {
         this.zzayC.onPreloadStatusUpdated();
      }

   }

   private final void zzoM() {
      this.zzayA = 0L;
      this.zzayB = null;
      Iterator var1 = this.zzawS.iterator();

      while(var1.hasNext()) {
         ((zzayu)var1.next()).clear();
      }

   }

   public final void zzoz() {
      super.zzoz();
      this.zzoM();
   }

   public final void zzc(long var1, int var3) {
      Iterator var4 = this.zzawS.iterator();

      while(var4.hasNext()) {
         ((zzayu)var4.next()).zzc(var1, var3, (Object)null);
      }

   }

   protected final boolean zzz(long var1) {
      Iterator var3 = this.zzawS.iterator();

      while(var3.hasNext()) {
         ((zzayu)var3.next()).zzd(var1, 2102);
      }

      boolean var8 = false;
      Object var4 = zzayu.zzrl;
      synchronized(zzayu.zzrl) {
         Iterator var5 = this.zzawS.iterator();

         while(var5.hasNext()) {
            if (((zzayu)var5.next()).zzoO()) {
               var8 = true;
               break;
            }
         }

         return var8;
      }
   }
}
