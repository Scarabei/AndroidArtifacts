package com.google.android.gms.cast;

import android.annotation.SuppressLint;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.zzaxs;
import com.google.android.gms.internal.zzaxx;
import com.google.android.gms.internal.zzayp;
import com.google.android.gms.internal.zzayq;
import com.google.android.gms.internal.zzays;
import com.google.android.gms.internal.zzayt;
import java.io.IOException;
import org.json.JSONObject;

/** @deprecated */
@Deprecated
@SuppressLint({"MissingRemoteException"})
public class RemoteMediaPlayer implements Cast.MessageReceivedCallback {
   private final Object mLock;
   private final zzayp zzaqP;
   private final RemoteMediaPlayer.zza zzaqQ;
   private RemoteMediaPlayer.OnPreloadStatusUpdatedListener zzaqR;
   private RemoteMediaPlayer.OnQueueStatusUpdatedListener zzaqS;
   private RemoteMediaPlayer.OnMetadataUpdatedListener zzaqT;
   private RemoteMediaPlayer.OnStatusUpdatedListener zzaqU;
   public static final String NAMESPACE;
   public static final int RESUME_STATE_UNCHANGED = 0;
   public static final int RESUME_STATE_PLAY = 1;
   public static final int RESUME_STATE_PAUSE = 2;
   public static final int STATUS_SUCCEEDED = 0;
   public static final int STATUS_FAILED = 2100;
   public static final int STATUS_CANCELED = 2101;
   public static final int STATUS_TIMED_OUT = 2102;
   public static final int STATUS_REPLACED = 2103;

   public RemoteMediaPlayer() {
      this(new zzayp((String)null));
   }

   private RemoteMediaPlayer(zzayp var1) {
      this.mLock = new Object();
      this.zzaqP = var1;
      this.zzaqP.zza((zzayq)(new zzai(this)));
      this.zzaqQ = new RemoteMediaPlayer.zza();
      this.zzaqP.zza((zzays)this.zzaqQ);
   }

   public PendingResult load(GoogleApiClient var1, MediaInfo var2) {
      return this.load(var1, var2, true, 0L, (long[])null, (JSONObject)null);
   }

   public PendingResult load(GoogleApiClient var1, MediaInfo var2, boolean var3) {
      return this.load(var1, var2, var3, 0L, (long[])null, (JSONObject)null);
   }

   public PendingResult load(GoogleApiClient var1, MediaInfo var2, boolean var3, long var4) {
      return this.load(var1, var2, var3, var4, (long[])null, (JSONObject)null);
   }

   public PendingResult load(GoogleApiClient var1, MediaInfo var2, boolean var3, long var4, JSONObject var6) {
      return this.load(var1, var2, var3, var4, (long[])null, var6);
   }

   public PendingResult load(GoogleApiClient var1, MediaInfo var2, boolean var3, long var4, long[] var6, JSONObject var7) {
      return var1.zze(new zzat(this, var1, var1, var2, var3, var4, var6, var7));
   }

   public PendingResult pause(GoogleApiClient var1) {
      return this.pause(var1, (JSONObject)null);
   }

   public PendingResult pause(GoogleApiClient var1, JSONObject var2) {
      return var1.zze(new zzay(this, var1, var1, var2));
   }

   public PendingResult stop(GoogleApiClient var1) {
      return this.stop(var1, (JSONObject)null);
   }

   public PendingResult stop(GoogleApiClient var1, JSONObject var2) {
      return var1.zze(new zzaz(this, var1, var1, var2));
   }

   public PendingResult play(GoogleApiClient var1) {
      return this.play(var1, (JSONObject)null);
   }

   public PendingResult play(GoogleApiClient var1, JSONObject var2) {
      return var1.zze(new zzba(this, var1, var1, var2));
   }

   public PendingResult seek(GoogleApiClient var1, long var2) {
      return this.seek(var1, var2, 0, (JSONObject)null);
   }

   public PendingResult seek(GoogleApiClient var1, long var2, int var4) {
      return this.seek(var1, var2, var4, (JSONObject)null);
   }

   public PendingResult seek(GoogleApiClient var1, long var2, int var4, JSONObject var5) {
      return var1.zze(new zzbb(this, var1, var1, var2, var4, var5));
   }

   public PendingResult setStreamVolume(GoogleApiClient var1, double var2) throws IllegalArgumentException {
      return this.setStreamVolume(var1, var2, (JSONObject)null);
   }

   public PendingResult setStreamVolume(GoogleApiClient var1, double var2, JSONObject var4) throws IllegalArgumentException {
      if (!Double.isInfinite(var2) && !Double.isNaN(var2)) {
         return var1.zze(new zzbc(this, var1, var1, var2, var4));
      } else {
         throw new IllegalArgumentException((new StringBuilder(41)).append("Volume cannot be ").append(var2).toString());
      }
   }

   public PendingResult setStreamMute(GoogleApiClient var1, boolean var2) {
      return this.setStreamMute(var1, var2, (JSONObject)null);
   }

   public PendingResult setStreamMute(GoogleApiClient var1, boolean var2, JSONObject var3) {
      return var1.zze(new zzbd(this, var1, var1, var2, var3));
   }

   public PendingResult requestStatus(GoogleApiClient var1) {
      return var1.zze(new zzbe(this, var1, var1));
   }

   public PendingResult setActiveMediaTracks(GoogleApiClient var1, long[] var2) {
      if (var2 == null) {
         throw new IllegalArgumentException("trackIds cannot be null");
      } else {
         return var1.zze(new zzaj(this, var1, var1, var2));
      }
   }

   public PendingResult setTextTrackStyle(GoogleApiClient var1, TextTrackStyle var2) {
      if (var2 == null) {
         throw new IllegalArgumentException("trackStyle cannot be null");
      } else {
         return var1.zze(new zzak(this, var1, var1, var2));
      }
   }

   public PendingResult queueLoad(GoogleApiClient var1, MediaQueueItem[] var2, int var3, int var4, JSONObject var5) throws IllegalArgumentException {
      return this.queueLoad(var1, var2, var3, var4, -1L, var5);
   }

   public PendingResult queueLoad(GoogleApiClient var1, MediaQueueItem[] var2, int var3, int var4, long var5, JSONObject var7) throws IllegalArgumentException {
      return var1.zze(new zzal(this, var1, var1, var2, var3, var4, var5, var7));
   }

   public PendingResult queueInsertItems(GoogleApiClient var1, MediaQueueItem[] var2, int var3, JSONObject var4) throws IllegalArgumentException {
      return var1.zze(new zzam(this, var1, var1, var2, var3, var4));
   }

   public PendingResult queueAppendItem(GoogleApiClient var1, MediaQueueItem var2, JSONObject var3) throws IllegalArgumentException {
      return this.queueInsertItems(var1, new MediaQueueItem[]{var2}, 0, var3);
   }

   public PendingResult queueInsertAndPlayItem(GoogleApiClient var1, MediaQueueItem var2, int var3, JSONObject var4) {
      return this.queueInsertAndPlayItem(var1, var2, var3, -1L, var4);
   }

   public PendingResult queueInsertAndPlayItem(GoogleApiClient var1, MediaQueueItem var2, int var3, long var4, JSONObject var6) {
      return var1.zze(new zzan(this, var1, var1, var2, var3, var4, var6));
   }

   public PendingResult queueUpdateItems(GoogleApiClient var1, MediaQueueItem[] var2, JSONObject var3) {
      return var1.zze(new zzao(this, var1, var1, var2, var3));
   }

   public PendingResult queueRemoveItems(GoogleApiClient var1, int[] var2, JSONObject var3) throws IllegalArgumentException {
      return var1.zze(new zzap(this, var1, var1, var2, var3));
   }

   public PendingResult queueReorderItems(GoogleApiClient var1, int[] var2, int var3, JSONObject var4) throws IllegalArgumentException {
      return var1.zze(new zzaq(this, var1, var1, var2, var3, var4));
   }

   public PendingResult queuePrev(GoogleApiClient var1, JSONObject var2) {
      return var1.zze(new zzar(this, var1, var1, var2));
   }

   public PendingResult queueNext(GoogleApiClient var1, JSONObject var2) {
      return var1.zze(new zzas(this, var1, var1, var2));
   }

   public PendingResult queueSetRepeatMode(GoogleApiClient var1, int var2, JSONObject var3) {
      return var1.zze(new zzau(this, var1, var1, var2, var3));
   }

   public PendingResult queueRemoveItem(GoogleApiClient var1, int var2, JSONObject var3) {
      return var1.zze(new zzav(this, var1, var2, var1, var3));
   }

   public PendingResult queueJumpToItem(GoogleApiClient var1, int var2, JSONObject var3) {
      return this.queueJumpToItem(var1, var2, -1L, var3);
   }

   public PendingResult queueJumpToItem(GoogleApiClient var1, int var2, long var3, JSONObject var5) {
      return var1.zze(new zzaw(this, var1, var2, var1, var3, var5));
   }

   public PendingResult queueMoveItemToNewIndex(GoogleApiClient var1, int var2, int var3, JSONObject var4) {
      return var1.zze(new zzax(this, var1, var2, var3, var1, var4));
   }

   private final int zzX(int var1) {
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
         return this.zzaqP.getApproximateStreamPosition();
      }
   }

   public long getStreamDuration() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         return this.zzaqP.getStreamDuration();
      }
   }

   public MediaStatus getMediaStatus() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         return this.zzaqP.getMediaStatus();
      }
   }

   public MediaInfo getMediaInfo() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         return this.zzaqP.getMediaInfo();
      }
   }

   public void setOnStatusUpdatedListener(RemoteMediaPlayer.OnStatusUpdatedListener var1) {
      this.zzaqU = var1;
   }

   private final void onStatusUpdated() {
      if (this.zzaqU != null) {
         this.zzaqU.onStatusUpdated();
      }

   }

   public void setOnMetadataUpdatedListener(RemoteMediaPlayer.OnMetadataUpdatedListener var1) {
      this.zzaqT = var1;
   }

   private final void onMetadataUpdated() {
      if (this.zzaqT != null) {
         this.zzaqT.onMetadataUpdated();
      }

   }

   public void setOnQueueStatusUpdatedListener(RemoteMediaPlayer.OnQueueStatusUpdatedListener var1) {
      this.zzaqS = var1;
   }

   private final void onQueueStatusUpdated() {
      if (this.zzaqS != null) {
         this.zzaqS.onQueueStatusUpdated();
      }

   }

   public void setOnPreloadStatusUpdatedListener(RemoteMediaPlayer.OnPreloadStatusUpdatedListener var1) {
      this.zzaqR = var1;
   }

   private final void onPreloadStatusUpdated() {
      if (this.zzaqR != null) {
         this.zzaqR.onPreloadStatusUpdated();
      }

   }

   public String getNamespace() {
      return this.zzaqP.getNamespace();
   }

   public void onMessageReceived(CastDevice var1, String var2, String var3) {
      this.zzaqP.zzch(var3);
   }

   // $FF: synthetic method
   static void zza(RemoteMediaPlayer var0) {
      var0.onStatusUpdated();
   }

   // $FF: synthetic method
   static void zzb(RemoteMediaPlayer var0) {
      var0.onMetadataUpdated();
   }

   // $FF: synthetic method
   static void zzc(RemoteMediaPlayer var0) {
      var0.onQueueStatusUpdated();
   }

   // $FF: synthetic method
   static void zzd(RemoteMediaPlayer var0) {
      var0.onPreloadStatusUpdated();
   }

   // $FF: synthetic method
   static Object zze(RemoteMediaPlayer var0) {
      return var0.mLock;
   }

   // $FF: synthetic method
   static RemoteMediaPlayer.zza zzf(RemoteMediaPlayer var0) {
      return var0.zzaqQ;
   }

   // $FF: synthetic method
   static zzayp zzg(RemoteMediaPlayer var0) {
      return var0.zzaqP;
   }

   // $FF: synthetic method
   static int zza(RemoteMediaPlayer var0, int var1) {
      return var0.zzX(var1);
   }

   static {
      NAMESPACE = zzayp.NAMESPACE;
   }

   abstract static class zzb extends zzaxs {
      zzayt zzarw = new zzbg(this);

      zzb(GoogleApiClient var1) {
         super(var1);
      }

      protected void zza(zzaxx var1) {
      }

      // $FF: synthetic method
      public final Result zzb(Status var1) {
         return new zzbh(this, var1);
      }
   }

   static final class zzc implements RemoteMediaPlayer.MediaChannelResult {
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

   /** @deprecated */
   @Deprecated
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
            Cast.CastApi.sendMessage(this.zzars, var1, var2).setResultCallback(new zzbf(this, var3));
         }
      }
   }

   /** @deprecated */
   @Deprecated
   public interface OnPreloadStatusUpdatedListener {
      void onPreloadStatusUpdated();
   }

   /** @deprecated */
   @Deprecated
   public interface OnQueueStatusUpdatedListener {
      void onQueueStatusUpdated();
   }

   /** @deprecated */
   @Deprecated
   public interface OnMetadataUpdatedListener {
      void onMetadataUpdated();
   }

   /** @deprecated */
   @Deprecated
   public interface OnStatusUpdatedListener {
      void onStatusUpdated();
   }
}
