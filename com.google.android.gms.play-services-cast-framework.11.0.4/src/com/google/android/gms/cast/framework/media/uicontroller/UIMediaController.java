package com.google.android.gms.cast.framework.media.uicontroller;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import com.google.android.gms.R.string;
import com.google.android.gms.cast.framework.CastContext;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.Session;
import com.google.android.gms.cast.framework.SessionManager;
import com.google.android.gms.cast.framework.SessionManagerListener;
import com.google.android.gms.cast.framework.media.ImageHints;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.internal.zzavs;
import com.google.android.gms.internal.zzavu;
import com.google.android.gms.internal.zzavw;
import com.google.android.gms.internal.zzavy;
import com.google.android.gms.internal.zzawc;
import com.google.android.gms.internal.zzawd;
import com.google.android.gms.internal.zzawe;
import com.google.android.gms.internal.zzawf;
import com.google.android.gms.internal.zzawi;
import com.google.android.gms.internal.zzawk;
import com.google.android.gms.internal.zzawl;
import com.google.android.gms.internal.zzawn;
import com.google.android.gms.internal.zzawp;
import com.google.android.gms.internal.zzawr;
import com.google.android.gms.internal.zzawt;
import com.google.android.gms.internal.zzawu;
import com.google.android.gms.internal.zzawv;
import com.google.android.gms.internal.zzaww;
import com.google.android.gms.internal.zzawx;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UIMediaController implements SessionManagerListener, RemoteMediaClient.Listener {
   private final Activity mActivity;
   private final SessionManager zzarO;
   private final Map zzavi = new HashMap();
   private final Set zzavj = new HashSet();
   private RemoteMediaClient.Listener zzavk;
   private RemoteMediaClient zzase;

   public UIMediaController(Activity var1) {
      this.mActivity = var1;
      this.zzarO = CastContext.getSharedInstance(var1).getSessionManager();
      this.zzarO.addSessionManagerListener(this, CastSession.class);
      this.zza((Session)this.zzarO.getCurrentCastSession());
   }

   public boolean isActive() {
      zzbo.zzcz("Must be called from the main thread.");
      return this.zzase != null;
   }

   public RemoteMediaClient getRemoteMediaClient() {
      zzbo.zzcz("Must be called from the main thread.");
      return this.zzase;
   }

   public void setPostRemoteMediaClientListener(RemoteMediaClient.Listener var1) {
      zzbo.zzcz("Must be called from the main thread.");
      this.zzavk = var1;
   }

   public void dispose() {
      zzbo.zzcz("Must be called from the main thread.");
      this.zzoi();
      this.zzavi.clear();
      this.zzarO.removeSessionManagerListener(this, CastSession.class);
      this.zzavk = null;
   }

   public void bindImageViewToPlayPauseToggle(@NonNull ImageView var1, @NonNull Drawable var2, @NonNull Drawable var3, Drawable var4, View var5, boolean var6) {
      zzbo.zzcz("Must be called from the main thread.");
      this.zza(var1, new zzawi(var1, this.mActivity, var2, var3, var4, var5, var6));
   }

   public void bindViewToSkipNext(View var1, int var2) {
      zzbo.zzcz("Must be called from the main thread.");
      this.zza(var1, new zzawp(var1, var2));
   }

   public void bindViewToSkipPrev(View var1, int var2) {
      zzbo.zzcz("Must be called from the main thread.");
      this.zza(var1, new zzawr(var1, var2));
   }

   public void bindViewToForward(View var1, long var2) {
      zzbo.zzcz("Must be called from the main thread.");
      this.zza(var1, new zzawn(var1, var2));
   }

   public void bindViewToRewind(View var1, long var2) {
      zzbo.zzcz("Must be called from the main thread.");
      this.bindViewToForward(var1, -var2);
   }

   public void bindViewToLoadingIndicator(View var1) {
      zzbo.zzcz("Must be called from the main thread.");
      this.zza(var1, new zzawc(var1));
   }

   public void bindProgressBar(ProgressBar var1) {
      this.bindProgressBar(var1, 1000L);
   }

   public void bindProgressBar(ProgressBar var1, long var2) {
      zzbo.zzcz("Must be called from the main thread.");
      this.zza(var1, new zzawk(var1, var2));
   }

   public void bindSeekBar(SeekBar var1) {
      this.bindSeekBar(var1, 1000L);
   }

   public void bindSeekBar(SeekBar var1, long var2) {
      zzbo.zzcz("Must be called from the main thread.");
      zza var4 = new zza(this);
      this.zza(var1, new zzawl(var1, var2, var4));
   }

   public void bindTextViewToStreamPosition(TextView var1, boolean var2) {
      this.bindTextViewToStreamPosition(var1, var2, 1000L);
   }

   public void bindTextViewToStreamPosition(TextView var1, boolean var2, long var3) {
      zzbo.zzcz("Must be called from the main thread.");
      zzawv var5 = new zzawv(var1, var3, this.mActivity.getString(string.cast_invalid_stream_position_text));
      if (var2) {
         this.zzavj.add(var5);
      }

      this.zza(var1, var5);
   }

   public void bindTextViewToStreamDuration(TextView var1) {
      zzbo.zzcz("Must be called from the main thread.");
      this.zza(var1, new zzawu(var1, this.mActivity.getString(string.cast_invalid_stream_duration_text), (View)null));
   }

   public void bindTextViewToStreamDuration(TextView var1, View var2) {
      zzbo.zzcz("Must be called from the main thread.");
      this.zza(var1, new zzawu(var1, this.mActivity.getString(string.cast_invalid_stream_duration_text), var2));
   }

   public void bindViewToLaunchExpandedController(View var1) {
      zzbo.zzcz("Must be called from the main thread.");
      this.zza(var1, new zzavy(var1, this.mActivity));
   }

   public void bindViewToClosedCaption(View var1) {
      zzbo.zzcz("Must be called from the main thread.");
      this.zza(var1, new zzavs(var1, this.mActivity));
   }

   public void bindImageViewToMuteToggle(ImageView var1) {
      zzbo.zzcz("Must be called from the main thread.");
      this.zza(var1, new zzawf(var1, this.mActivity));
   }

   public void bindTextViewToMetadataOfCurrentItem(TextView var1, String var2) {
      zzbo.zzcz("Must be called from the main thread.");
      this.bindTextViewToMetadataOfCurrentItem(var1, Collections.singletonList(var2));
   }

   public void bindTextViewToMetadataOfCurrentItem(TextView var1, List var2) {
      zzbo.zzcz("Must be called from the main thread.");
      this.zza(var1, new zzawe(var1, var2));
   }

   /** @deprecated */
   @Deprecated
   public void bindImageViewToImageOfCurrentItem(ImageView var1, int var2, @DrawableRes int var3) {
      zzbo.zzcz("Must be called from the main thread.");
      this.zza(var1, new zzavw(var1, this.mActivity, new ImageHints(var2, 0, 0), var3, (View)null));
   }

   /** @deprecated */
   @Deprecated
   public void bindImageViewToImageOfCurrentItem(ImageView var1, int var2, View var3) {
      zzbo.zzcz("Must be called from the main thread.");
      this.zza(var1, new zzavw(var1, this.mActivity, new ImageHints(var2, 0, 0), 0, var3));
   }

   public void bindImageViewToImageOfCurrentItem(ImageView var1, @NonNull ImageHints var2, @DrawableRes int var3) {
      zzbo.zzcz("Must be called from the main thread.");
      this.zza(var1, new zzavw(var1, this.mActivity, var2, var3, (View)null));
   }

   public void bindImageViewToImageOfCurrentItem(ImageView var1, @NonNull ImageHints var2, View var3) {
      zzbo.zzcz("Must be called from the main thread.");
      this.zza(var1, new zzavw(var1, this.mActivity, var2, 0, var3));
   }

   public void bindViewVisibilityToMediaSession(View var1, int var2) {
      zzbo.zzcz("Must be called from the main thread.");
      this.zza(var1, new zzawx(var1, var2));
   }

   public void bindTextViewToMetadataOfPreloadedItem(TextView var1, String var2) {
      zzbo.zzcz("Must be called from the main thread.");
      this.bindTextViewToMetadataOfPreloadedItem(var1, Collections.singletonList(var2));
   }

   public void bindTextViewToMetadataOfPreloadedItem(TextView var1, List var2) {
      zzbo.zzcz("Must be called from the main thread.");
      this.zza(var1, new zzawd(var1, var2));
   }

   /** @deprecated */
   @Deprecated
   public void bindImageViewToImageOfPreloadedItem(ImageView var1, int var2, @DrawableRes int var3) {
      zzbo.zzcz("Must be called from the main thread.");
      this.zza(var1, new zzavu(var1, this.mActivity, new ImageHints(var2, 0, 0), var3));
   }

   public void bindImageViewToImageOfPreloadedItem(ImageView var1, @NonNull ImageHints var2, @DrawableRes int var3) {
      zzbo.zzcz("Must be called from the main thread.");
      this.zza(var1, new zzavu(var1, this.mActivity, var2, var3));
   }

   public void bindViewVisibilityToPreloadingEvent(View var1, int var2) {
      zzbo.zzcz("Must be called from the main thread.");
      this.zza(var1, new zzaww(var1, var2));
   }

   public void bindViewToUIController(View var1, UIController var2) {
      zzbo.zzcz("Must be called from the main thread.");
      this.zza(var1, var2);
   }

   public void bindTextViewToSmartSubtitle(TextView var1) {
      zzbo.zzcz("Must be called from the main thread.");
      this.zza(var1, new zzawt(var1));
   }

   public void onSessionStarting(CastSession var1) {
   }

   public void onSessionStarted(CastSession var1, String var2) {
      this.zza((Session)var1);
   }

   public void onSessionResuming(CastSession var1, String var2) {
   }

   public void onSessionResumed(CastSession var1, boolean var2) {
      this.zza((Session)var1);
   }

   public void onSessionStartFailed(CastSession var1, int var2) {
      this.zzoi();
   }

   public void onSessionEnding(CastSession var1) {
   }

   public void onSessionEnded(CastSession var1, int var2) {
      this.zzoi();
   }

   public void onSessionResumeFailed(CastSession var1, int var2) {
      this.zzoi();
   }

   public void onSessionSuspended(CastSession var1, int var2) {
   }

   public void onStatusUpdated() {
      this.zzoj();
      if (this.zzavk != null) {
         this.zzavk.onStatusUpdated();
      }

   }

   public void onMetadataUpdated() {
      this.zzoj();
      if (this.zzavk != null) {
         this.zzavk.onMetadataUpdated();
      }

   }

   public void onQueueStatusUpdated() {
      this.zzoj();
      if (this.zzavk != null) {
         this.zzavk.onQueueStatusUpdated();
      }

   }

   public void onPreloadStatusUpdated() {
      this.zzoj();
      if (this.zzavk != null) {
         this.zzavk.onPreloadStatusUpdated();
      }

   }

   public void onAdBreakStatusUpdated() {
      this.zzoj();
      if (this.zzavk != null) {
         this.zzavk.onAdBreakStatusUpdated();
      }

   }

   public void onSendingRemoteMediaRequest() {
      Iterator var1 = this.zzavi.values().iterator();

      while(var1.hasNext()) {
         Iterator var2 = ((List)var1.next()).iterator();

         while(var2.hasNext()) {
            ((UIController)var2.next()).onSendingRemoteMediaRequest();
         }
      }

      if (this.zzavk != null) {
         this.zzavk.onSendingRemoteMediaRequest();
      }

   }

   private final void zza(Session var1) {
      if (!this.isActive() && var1 instanceof CastSession && var1.isConnected()) {
         CastSession var2 = (CastSession)var1;
         this.zzase = var2.getRemoteMediaClient();
         if (this.zzase != null) {
            this.zzase.addListener(this);
            Iterator var3 = this.zzavi.values().iterator();

            while(var3.hasNext()) {
               Iterator var4 = ((List)var3.next()).iterator();

               while(var4.hasNext()) {
                  ((UIController)var4.next()).onSessionConnected(var2);
               }
            }

            this.zzoj();
         }

      }
   }

   private final void zzoi() {
      if (this.isActive()) {
         Iterator var1 = this.zzavi.values().iterator();

         while(var1.hasNext()) {
            Iterator var2 = ((List)var1.next()).iterator();

            while(var2.hasNext()) {
               ((UIController)var2.next()).onSessionEnded();
            }
         }

         this.zzase.removeListener(this);
         this.zzase = null;
      }
   }

   private final void zza(View var1, UIController var2) {
      Object var3;
      if ((var3 = (List)this.zzavi.get(var1)) == null) {
         var3 = new ArrayList();
         this.zzavi.put(var1, var3);
      }

      ((List)var3).add(var2);
      if (this.isActive()) {
         var2.onSessionConnected(this.zzarO.getCurrentCastSession());
         this.zzoj();
      }

   }

   private final void zzoj() {
      Iterator var1 = this.zzavi.values().iterator();

      while(var1.hasNext()) {
         Iterator var2 = ((List)var1.next()).iterator();

         while(var2.hasNext()) {
            ((UIController)var2.next()).onMediaStatusUpdated();
         }
      }

   }

   // $FF: synthetic method
   static Set zza(UIMediaController var0) {
      return var0.zzavj;
   }
}
