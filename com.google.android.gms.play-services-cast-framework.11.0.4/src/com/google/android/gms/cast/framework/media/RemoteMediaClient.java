package com.google.android.gms.cast.framework.media;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaQueueItem;
import com.google.android.gms.cast.MediaStatus;
import com.google.android.gms.cast.TextTrackStyle;
import com.google.android.gms.cast.Cast.CastApi;
import com.google.android.gms.cast.Cast.MessageReceivedCallback;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.internal.zzaxs;
import com.google.android.gms.internal.zzaxx;
import com.google.android.gms.internal.zzayp;
import com.google.android.gms.internal.zzays;
import com.google.android.gms.internal.zzayt;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONObject;

public class RemoteMediaClient implements MessageReceivedCallback {
   private final Object mLock = new Object();
   private final Handler mHandler = new Handler(Looper.getMainLooper());
   private final zzayp zzaqP;
   private final RemoteMediaClient.zza zzauu = new RemoteMediaClient.zza();
   private final CastApi zzasb;
   private GoogleApiClient zzXj;
   private final List mListeners = new CopyOnWriteArrayList();
   private final Map zzauv = new ConcurrentHashMap();
   private final Map zzauw = new ConcurrentHashMap();
   private RemoteMediaClient.ParseAdsInfoCallback zzaux;
   public static final String NAMESPACE;
   public static final int RESUME_STATE_UNCHANGED = 0;
   public static final int RESUME_STATE_PLAY = 1;
   public static final int RESUME_STATE_PAUSE = 2;
   public static final int STATUS_SUCCEEDED = 0;
   public static final int STATUS_FAILED = 2100;
   public static final int STATUS_REPLACED = 2103;

   public RemoteMediaClient(@NonNull zzayp var1, @NonNull CastApi var2) {
      this.zzasb = var2;
      this.zzaqP = (zzayp)zzbo.zzu(var1);
      this.zzaqP.zza(new zzi(this));
      this.zzaqP.zza(this.zzauu);
   }

   public final void zzc(GoogleApiClient var1) throws IOException {
      if (this.zzXj != var1) {
         if (this.zzXj != null) {
            this.zzaqP.zzoz();
            this.zzasb.removeMessageReceivedCallbacks(this.zzXj, this.getNamespace());
            this.zzauu.zzb((GoogleApiClient)null);
            this.mHandler.removeCallbacksAndMessages((Object)null);
         }

         this.zzXj = var1;
         if (this.zzXj != null) {
            this.zzauu.zzb(this.zzXj);
         }

      }
   }

   public final void zznX() throws IOException {
      if (this.zzXj != null) {
         this.zzasb.setMessageReceivedCallbacks(this.zzXj, this.getNamespace(), this);
      }

   }

   public PendingResult load(MediaInfo var1) {
      return this.load(var1, true, 0L, (long[])null, (JSONObject)null);
   }

   public PendingResult load(MediaInfo var1, boolean var2) {
      return this.load(var1, var2, 0L, (long[])null, (JSONObject)null);
   }

   public PendingResult load(MediaInfo var1, boolean var2, long var3) {
      return this.load(var1, var2, var3, (long[])null, (JSONObject)null);
   }

   public PendingResult load(MediaInfo var1, boolean var2, long var3, JSONObject var5) {
      return this.load(var1, var2, var3, (long[])null, var5);
   }

   public PendingResult load(MediaInfo var1, boolean var2, long var3, long[] var5, JSONObject var6) {
      zzbo.zzcz("Must be called from the main thread.");
      this.zznY();
      return this.zza((RemoteMediaClient.zzb)(new zzt(this, this.zzXj, var1, var2, var3, var5, var6)));
   }

   public PendingResult pause() {
      return this.pause((JSONObject)null);
   }

   public PendingResult pause(JSONObject var1) {
      zzbo.zzcz("Must be called from the main thread.");
      this.zznY();
      return this.zza((RemoteMediaClient.zzb)(new zzy(this, this.zzXj, var1)));
   }

   public PendingResult stop() {
      return this.stop((JSONObject)null);
   }

   public PendingResult stop(JSONObject var1) {
      zzbo.zzcz("Must be called from the main thread.");
      this.zznY();
      return this.zza((RemoteMediaClient.zzb)(new zzz(this, this.zzXj, var1)));
   }

   public PendingResult play() {
      return this.play((JSONObject)null);
   }

   public PendingResult play(JSONObject var1) {
      zzbo.zzcz("Must be called from the main thread.");
      this.zznY();
      return this.zza((RemoteMediaClient.zzb)(new zzaa(this, this.zzXj, var1)));
   }

   public PendingResult seek(long var1) {
      return this.seek(var1, 0, (JSONObject)null);
   }

   public PendingResult seek(long var1, int var3) {
      return this.seek(var1, var3, (JSONObject)null);
   }

   public PendingResult seek(long var1, int var3, JSONObject var4) {
      zzbo.zzcz("Must be called from the main thread.");
      this.zznY();
      return this.zza((RemoteMediaClient.zzb)(new zzab(this, this.zzXj, var1, var3, var4)));
   }

   public PendingResult setStreamVolume(double var1) throws IllegalArgumentException {
      return this.setStreamVolume(var1, (JSONObject)null);
   }

   public PendingResult setStreamVolume(double var1, JSONObject var3) throws IllegalArgumentException {
      zzbo.zzcz("Must be called from the main thread.");
      this.zznY();
      if (!Double.isInfinite(var1) && !Double.isNaN(var1)) {
         return this.zza((RemoteMediaClient.zzb)(new zzac(this, this.zzXj, var1, var3)));
      } else {
         throw new IllegalArgumentException((new StringBuilder(41)).append("Volume cannot be ").append(var1).toString());
      }
   }

   public PendingResult setStreamMute(boolean var1) {
      return this.setStreamMute(var1, (JSONObject)null);
   }

   public PendingResult setStreamMute(boolean var1, JSONObject var2) {
      zzbo.zzcz("Must be called from the main thread.");
      this.zznY();
      return this.zza((RemoteMediaClient.zzb)(new zzad(this, this.zzXj, var1, var2)));
   }

   public PendingResult requestStatus() {
      zzbo.zzcz("Must be called from the main thread.");
      this.zznY();
      return this.zza((RemoteMediaClient.zzb)(new zzae(this, this.zzXj)));
   }

   public PendingResult setActiveMediaTracks(long[] var1) {
      zzbo.zzcz("Must be called from the main thread.");
      this.zznY();
      if (var1 == null) {
         throw new IllegalArgumentException("trackIds cannot be null");
      } else {
         return this.zza((RemoteMediaClient.zzb)(new zzj(this, this.zzXj, var1)));
      }
   }

   public PendingResult setTextTrackStyle(TextTrackStyle var1) {
      zzbo.zzcz("Must be called from the main thread.");
      this.zznY();
      if (var1 == null) {
         throw new IllegalArgumentException("trackStyle cannot be null");
      } else {
         return this.zza((RemoteMediaClient.zzb)(new zzk(this, this.zzXj, var1)));
      }
   }

   public PendingResult queueLoad(MediaQueueItem[] var1, int var2, int var3, JSONObject var4) throws IllegalArgumentException {
      return this.queueLoad(var1, var2, var3, -1L, var4);
   }

   public PendingResult queueLoad(MediaQueueItem[] var1, int var2, int var3, long var4, JSONObject var6) throws IllegalArgumentException {
      zzbo.zzcz("Must be called from the main thread.");
      this.zznY();
      return this.zza((RemoteMediaClient.zzb)(new zzl(this, this.zzXj, var1, var2, var3, var4, var6)));
   }

   public PendingResult queueInsertItems(MediaQueueItem[] var1, int var2, JSONObject var3) throws IllegalArgumentException {
      zzbo.zzcz("Must be called from the main thread.");
      this.zznY();
      return this.zza((RemoteMediaClient.zzb)(new zzm(this, this.zzXj, var1, var2, var3)));
   }

   public PendingResult queueAppendItem(MediaQueueItem var1, JSONObject var2) throws IllegalArgumentException {
      return this.queueInsertItems(new MediaQueueItem[]{var1}, 0, var2);
   }

   public PendingResult queueInsertAndPlayItem(MediaQueueItem var1, int var2, JSONObject var3) {
      return this.queueInsertAndPlayItem(var1, var2, -1L, var3);
   }

   public PendingResult queueInsertAndPlayItem(MediaQueueItem var1, int var2, long var3, JSONObject var5) {
      zzbo.zzcz("Must be called from the main thread.");
      this.zznY();
      return this.zza((RemoteMediaClient.zzb)(new zzn(this, this.zzXj, var1, var2, var3, var5)));
   }

   public PendingResult queueUpdateItems(MediaQueueItem[] var1, JSONObject var2) {
      zzbo.zzcz("Must be called from the main thread.");
      this.zznY();
      return this.zza((RemoteMediaClient.zzb)(new zzo(this, this.zzXj, var1, var2)));
   }

   public PendingResult queueRemoveItems(int[] var1, JSONObject var2) throws IllegalArgumentException {
      zzbo.zzcz("Must be called from the main thread.");
      this.zznY();
      return this.zza((RemoteMediaClient.zzb)(new zzp(this, this.zzXj, var1, var2)));
   }

   public PendingResult queueReorderItems(int[] var1, int var2, JSONObject var3) throws IllegalArgumentException {
      zzbo.zzcz("Must be called from the main thread.");
      this.zznY();
      return this.zza((RemoteMediaClient.zzb)(new zzq(this, this.zzXj, var1, var2, var3)));
   }

   public PendingResult queuePrev(JSONObject var1) {
      zzbo.zzcz("Must be called from the main thread.");
      this.zznY();
      return this.zza((RemoteMediaClient.zzb)(new zzr(this, this.zzXj, var1)));
   }

   public PendingResult queueNext(JSONObject var1) {
      zzbo.zzcz("Must be called from the main thread.");
      this.zznY();
      return this.zza((RemoteMediaClient.zzb)(new zzs(this, this.zzXj, var1)));
   }

   public PendingResult queueSetRepeatMode(int var1, JSONObject var2) {
      zzbo.zzcz("Must be called from the main thread.");
      this.zznY();
      return this.zza((RemoteMediaClient.zzb)(new zzu(this, this.zzXj, var1, var2)));
   }

   public PendingResult queueRemoveItem(int var1, JSONObject var2) {
      zzbo.zzcz("Must be called from the main thread.");
      this.zznY();
      return this.zza((RemoteMediaClient.zzb)(new zzv(this, this.zzXj, var1, var2)));
   }

   public PendingResult queueJumpToItem(int var1, JSONObject var2) {
      return this.queueJumpToItem(var1, -1L, var2);
   }

   public PendingResult queueJumpToItem(int var1, long var2, JSONObject var4) {
      zzbo.zzcz("Must be called from the main thread.");
      this.zznY();
      return this.zza((RemoteMediaClient.zzb)(new zzw(this, this.zzXj, var1, var2, var4)));
   }

   public PendingResult queueMoveItemToNewIndex(int var1, int var2, JSONObject var3) {
      zzbo.zzcz("Must be called from the main thread.");
      this.zznY();
      return this.zza((RemoteMediaClient.zzb)(new zzx(this, this.zzXj, var1, var2, var3)));
   }

   private final int zzX(int var1) {
      zzbo.zzcz("Must be called from the main thread.");
      MediaStatus var2 = this.getMediaStatus();

      for(int var3 = 0; var3 < var2.getQueueItemCount(); ++var3) {
         if (var2.getQueueItem(var3).getItemId() == var1) {
            return var3;
         }
      }

      return -1;
   }

   public long getApproximateStreamPosition() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         zzbo.zzcz("Must be called from the main thread.");
         return this.zzaqP.getApproximateStreamPosition();
      }
   }

   public long getStreamDuration() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         zzbo.zzcz("Must be called from the main thread.");
         return this.zzaqP.getStreamDuration();
      }
   }

   public MediaStatus getMediaStatus() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         zzbo.zzcz("Must be called from the main thread.");
         return this.zzaqP.getMediaStatus();
      }
   }

   public MediaInfo getMediaInfo() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         zzbo.zzcz("Must be called from the main thread.");
         return this.zzaqP.getMediaInfo();
      }
   }

   public int getPlayerState() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         zzbo.zzcz("Must be called from the main thread.");
         MediaStatus var2;
         return (var2 = this.getMediaStatus()) != null ? var2.getPlayerState() : 1;
      }
   }

   public int getIdleReason() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         zzbo.zzcz("Must be called from the main thread.");
         MediaStatus var2;
         return (var2 = this.getMediaStatus()) != null ? var2.getIdleReason() : 0;
      }
   }

   public boolean isLiveStream() {
      zzbo.zzcz("Must be called from the main thread.");
      MediaInfo var1;
      return (var1 = this.getMediaInfo()) != null && var1.getStreamType() == 2;
   }

   public boolean isPlaying() {
      zzbo.zzcz("Must be called from the main thread.");
      MediaStatus var1;
      return (var1 = this.getMediaStatus()) != null && var1.getPlayerState() == 2;
   }

   public boolean isPaused() {
      zzbo.zzcz("Must be called from the main thread.");
      MediaStatus var1;
      return (var1 = this.getMediaStatus()) != null && (var1.getPlayerState() == 3 || this.isLiveStream() && this.getIdleReason() == 2);
   }

   public boolean isBuffering() {
      zzbo.zzcz("Must be called from the main thread.");
      MediaStatus var1;
      return (var1 = this.getMediaStatus()) != null && var1.getPlayerState() == 4;
   }

   public boolean isLoadingNextItem() {
      zzbo.zzcz("Must be called from the main thread.");
      MediaStatus var1;
      return (var1 = this.getMediaStatus()) != null && var1.getLoadingItemId() != 0;
   }

   public MediaQueueItem getCurrentItem() {
      zzbo.zzcz("Must be called from the main thread.");
      MediaStatus var1;
      return (var1 = this.getMediaStatus()) == null ? null : var1.getQueueItemById(var1.getCurrentItemId());
   }

   public MediaQueueItem getLoadingItem() {
      zzbo.zzcz("Must be called from the main thread.");
      MediaStatus var1;
      return (var1 = this.getMediaStatus()) == null ? null : var1.getQueueItemById(var1.getLoadingItemId());
   }

   public MediaQueueItem getPreloadedItem() {
      zzbo.zzcz("Must be called from the main thread.");
      MediaStatus var1;
      return (var1 = this.getMediaStatus()) == null ? null : var1.getQueueItemById(var1.getPreloadedItemId());
   }

   public void togglePlayback() {
      zzbo.zzcz("Must be called from the main thread.");
      int var1;
      if ((var1 = this.getPlayerState()) != 4 && var1 != 2) {
         this.play();
      } else {
         this.pause();
      }
   }

   public boolean hasMediaSession() {
      zzbo.zzcz("Must be called from the main thread.");
      return this.isBuffering() || this.isPlaying() || this.isPaused() || this.isLoadingNextItem();
   }

   public void addListener(RemoteMediaClient.Listener var1) {
      zzbo.zzcz("Must be called from the main thread.");
      if (var1 != null) {
         this.mListeners.add(var1);
      }

   }

   public void removeListener(RemoteMediaClient.Listener var1) {
      zzbo.zzcz("Must be called from the main thread.");
      if (var1 != null) {
         this.mListeners.remove(var1);
      }

   }

   public boolean addProgressListener(RemoteMediaClient.ProgressListener var1, long var2) {
      zzbo.zzcz("Must be called from the main thread.");
      if (var1 != null && !this.zzauv.containsKey(var1)) {
         RemoteMediaClient.zzd var4;
         if ((var4 = (RemoteMediaClient.zzd)this.zzauw.get(var2)) == null) {
            var4 = new RemoteMediaClient.zzd(var2);
            this.zzauw.put(var2, var4);
         }

         var4.zza(var1);
         this.zzauv.put(var1, var4);
         if (this.hasMediaSession()) {
            var4.start();
         }

         return true;
      } else {
         return false;
      }
   }

   public void removeProgressListener(RemoteMediaClient.ProgressListener var1) {
      zzbo.zzcz("Must be called from the main thread.");
      RemoteMediaClient.zzd var2;
      if ((var2 = (RemoteMediaClient.zzd)this.zzauv.remove(var1)) != null) {
         var2.zzb(var1);
         if (!var2.zzoc()) {
            this.zzauw.remove(var2.zzob());
            var2.stop();
         }
      }

   }

   public void setParseAdsInfoCallback(RemoteMediaClient.ParseAdsInfoCallback var1) {
      zzbo.zzcz("Must be called from the main thread.");
      this.zzaux = var1;
   }

   public boolean isPlayingAd() {
      zzbo.zzcz("Must be called from the main thread.");
      MediaStatus var1;
      return (var1 = this.getMediaStatus()) != null && var1.isPlayingAd();
   }

   public String getNamespace() {
      zzbo.zzcz("Must be called from the main thread.");
      return this.zzaqP.getNamespace();
   }

   public void onMessageReceived(CastDevice var1, String var2, String var3) {
      this.zzaqP.zzch(var3);
   }

   private final void zznY() throws IllegalStateException {
      if (this.zzXj == null) {
         throw new IllegalStateException("No connection");
      }
   }

   private final RemoteMediaClient.zzb zza(RemoteMediaClient.zzb var1) {
      try {
         this.zzXj.zze(var1);
         return var1;
      } catch (IllegalStateException var4) {
         var1.setResult((RemoteMediaClient.MediaChannelResult)var1.zzb(new Status(2100)));
         return var1;
      } finally {
         return var1;
      }
   }

   private final void zza(Set var1) {
      if (!this.isBuffering() && !this.isPaused()) {
         HashSet var2 = new HashSet(var1);
         Iterator var3;
         if (this.isPlaying()) {
            var3 = var2.iterator();

            while(var3.hasNext()) {
               ((RemoteMediaClient.ProgressListener)var3.next()).onProgressUpdated(this.getApproximateStreamPosition(), this.getStreamDuration());
            }

         } else if (this.isLoadingNextItem()) {
            MediaQueueItem var5;
            if ((var5 = this.getLoadingItem()) != null && var5.getMedia() != null) {
               Iterator var4 = var2.iterator();

               while(var4.hasNext()) {
                  ((RemoteMediaClient.ProgressListener)var4.next()).onProgressUpdated(0L, var5.getMedia().getStreamDuration());
               }
            }

         } else {
            var3 = var2.iterator();

            while(var3.hasNext()) {
               ((RemoteMediaClient.ProgressListener)var3.next()).onProgressUpdated(0L, 0L);
            }

         }
      }
   }

   private final void zznZ() {
      Iterator var1 = this.zzauw.values().iterator();

      while(true) {
         RemoteMediaClient.zzd var2;
         do {
            do {
               if (!var1.hasNext()) {
                  return;
               }

               var2 = (RemoteMediaClient.zzd)var1.next();
               if (this.hasMediaSession() && !var2.isStarted()) {
                  var2.start();
               } else if (!this.hasMediaSession() && var2.isStarted()) {
                  var2.stop();
               }
            } while(!var2.isStarted());
         } while(!this.isBuffering() && !this.isPaused() && !this.isLoadingNextItem());

         this.zza(var2.zzauB);
      }
   }

   // $FF: synthetic method
   static void zza(RemoteMediaClient var0) {
      var0.zznZ();
   }

   // $FF: synthetic method
   static RemoteMediaClient.ParseAdsInfoCallback zzc(RemoteMediaClient var0) {
      return var0.zzaux;
   }

   // $FF: synthetic method
   static Object zzd(RemoteMediaClient var0) {
      return var0.mLock;
   }

   // $FF: synthetic method
   static zzayp zze(RemoteMediaClient var0) {
      return var0.zzaqP;
   }

   // $FF: synthetic method
   static int zza(RemoteMediaClient var0, int var1) {
      return var0.zzX(var1);
   }

   // $FF: synthetic method
   static void zza(RemoteMediaClient var0, Set var1) {
      var0.zza(var1);
   }

   static {
      NAMESPACE = zzayp.NAMESPACE;
   }

   class zzd {
      private final Set zzauB = new HashSet();
      private final long zzauC;
      private final Runnable zzauD;
      private boolean zzauE;

      public zzd(long var2) {
         this.zzauC = var2;
         this.zzauD = new zzai(this, RemoteMediaClient.this);
      }

      public final long zzob() {
         return this.zzauC;
      }

      public final void zza(RemoteMediaClient.ProgressListener var1) {
         this.zzauB.add(var1);
      }

      public final void zzb(RemoteMediaClient.ProgressListener var1) {
         this.zzauB.remove(var1);
      }

      public final boolean zzoc() {
         return !this.zzauB.isEmpty();
      }

      public final void start() {
         RemoteMediaClient.this.mHandler.removeCallbacks(this.zzauD);
         this.zzauE = true;
         RemoteMediaClient.this.mHandler.postDelayed(this.zzauD, this.zzauC);
      }

      public final void stop() {
         RemoteMediaClient.this.mHandler.removeCallbacks(this.zzauD);
         this.zzauE = false;
      }

      public final boolean isStarted() {
         return this.zzauE;
      }

      // $FF: synthetic method
      static long zzb(RemoteMediaClient.zzd var0) {
         return var0.zzauC;
      }
   }

   abstract static class zzb extends zzaxs {
      zzayt zzarw = new zzag(this);

      zzb(GoogleApiClient var1) {
         super(var1);
      }

      protected void zza(zzaxx var1) {
      }

      // $FF: synthetic method
      public final Result zzb(Status var1) {
         return new zzah(this, var1);
      }
   }

   static final class zzc implements RemoteMediaClient.MediaChannelResult {
      private final Status mStatus;
      private final JSONObject zzaoD;

      zzc(Status var1, JSONObject var2) {
         this.mStatus = var1;
         this.zzaoD = var2;
      }

      public final Status getStatus() {
         return this.mStatus;
      }

      public final JSONObject getCustomData() {
         return this.zzaoD;
      }
   }

   public interface MediaChannelResult extends Result {
      JSONObject getCustomData();
   }

   class zza implements zzays {
      private GoogleApiClient zzars;
      private long zzart = 0L;

      public final void zzb(GoogleApiClient var1) {
         this.zzars = var1;
      }

      public final long zznl() {
         return ++this.zzart;
      }

      public final void zza(String var1, String var2, long var3, String var5) throws IOException {
         if (this.zzars == null) {
            throw new IOException("No GoogleApiClient available");
         } else {
            Iterator var6 = RemoteMediaClient.this.mListeners.iterator();

            while(var6.hasNext()) {
               ((RemoteMediaClient.Listener)var6.next()).onSendingRemoteMediaRequest();
            }

            RemoteMediaClient.this.zzasb.sendMessage(this.zzars, var1, var2).setResultCallback(new zzaf(this, var3));
         }
      }
   }

   public interface ParseAdsInfoCallback {
      boolean parseIsPlayingAdFromMediaStatus(MediaStatus var1);

      List parseAdBreaksFromMediaStatus(MediaStatus var1);
   }

   public interface ProgressListener {
      void onProgressUpdated(long var1, long var3);
   }

   public interface Listener {
      void onStatusUpdated();

      void onMetadataUpdated();

      void onQueueStatusUpdated();

      void onPreloadStatusUpdated();

      void onSendingRemoteMediaRequest();

      void onAdBreakStatusUpdated();
   }
}
