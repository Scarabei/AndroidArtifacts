package com.google.android.gms.cast.framework.media;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import com.google.android.gms.cast.framework.CastContext;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.Session;
import com.google.android.gms.cast.framework.SessionManager;
import org.json.JSONObject;

public class MediaIntentReceiver extends BroadcastReceiver {
   public static final String ACTION_TOGGLE_PLAYBACK = "com.google.android.gms.cast.framework.action.TOGGLE_PLAYBACK";
   public static final String ACTION_SKIP_NEXT = "com.google.android.gms.cast.framework.action.SKIP_NEXT";
   public static final String ACTION_SKIP_PREV = "com.google.android.gms.cast.framework.action.SKIP_PREV";
   public static final String ACTION_FORWARD = "com.google.android.gms.cast.framework.action.FORWARD";
   public static final String ACTION_REWIND = "com.google.android.gms.cast.framework.action.REWIND";
   public static final String ACTION_STOP_CASTING = "com.google.android.gms.cast.framework.action.STOP_CASTING";
   public static final String ACTION_DISCONNECT = "com.google.android.gms.cast.framework.action.DISCONNECT";
   public static final String EXTRA_SKIP_STEP_MS = "googlecast-extra_skip_step_ms";

   public void onReceive(Context var1, Intent var2) {
      String var3;
      if ((var3 = var2.getAction()) != null) {
         SessionManager var4 = CastContext.getSharedInstance(var1).getSessionManager();
         byte var6 = -1;
         switch(var3.hashCode()) {
         case -1699820260:
            if (var3.equals("com.google.android.gms.cast.framework.action.REWIND")) {
               var6 = 4;
            }
            break;
         case -945151566:
            if (var3.equals("com.google.android.gms.cast.framework.action.SKIP_NEXT")) {
               var6 = 1;
            }
            break;
         case -945080078:
            if (var3.equals("com.google.android.gms.cast.framework.action.SKIP_PREV")) {
               var6 = 2;
            }
            break;
         case -668151673:
            if (var3.equals("com.google.android.gms.cast.framework.action.STOP_CASTING")) {
               var6 = 5;
            }
            break;
         case -124479363:
            if (var3.equals("com.google.android.gms.cast.framework.action.DISCONNECT")) {
               var6 = 6;
            }
            break;
         case 235550565:
            if (var3.equals("com.google.android.gms.cast.framework.action.TOGGLE_PLAYBACK")) {
               var6 = 0;
            }
            break;
         case 1362116196:
            if (var3.equals("com.google.android.gms.cast.framework.action.FORWARD")) {
               var6 = 3;
            }
            break;
         case 1997055314:
            if (var3.equals("android.intent.action.MEDIA_BUTTON")) {
               var6 = 7;
            }
         }

         switch(var6) {
         case 0:
            this.onReceiveActionTogglePlayback(var4.getCurrentSession());
            return;
         case 1:
            this.onReceiveActionSkipNext(var4.getCurrentSession());
            return;
         case 2:
            this.onReceiveActionSkipPrev(var4.getCurrentSession());
            return;
         case 3:
            long var7 = var2.getLongExtra("googlecast-extra_skip_step_ms", 0L);
            this.onReceiveActionForward(var4.getCurrentSession(), var7);
            return;
         case 4:
            long var9 = var2.getLongExtra("googlecast-extra_skip_step_ms", 0L);
            this.onReceiveActionRewind(var4.getCurrentSession(), var9);
            return;
         case 5:
            var4.endCurrentSession(true);
            return;
         case 6:
            var4.endCurrentSession(false);
            return;
         case 7:
            this.onReceiveActionMediaButton(var4.getCurrentSession(), var2);
            return;
         default:
            this.onReceiveOtherAction(var3, var2);
         }
      }
   }

   protected void onReceiveActionTogglePlayback(Session var1) {
      if (var1 instanceof CastSession) {
         zzg((CastSession)var1);
      }

   }

   protected void onReceiveActionSkipNext(Session var1) {
      if (var1 instanceof CastSession) {
         RemoteMediaClient var2;
         if ((var2 = zzh((CastSession)var1)) == null || var2.isPlayingAd()) {
            return;
         }

         var2.queueNext((JSONObject)null);
      }

   }

   protected void onReceiveActionSkipPrev(Session var1) {
      if (var1 instanceof CastSession) {
         RemoteMediaClient var2;
         if ((var2 = zzh((CastSession)var1)) == null || var2.isPlayingAd()) {
            return;
         }

         var2.queuePrev((JSONObject)null);
      }

   }

   protected void onReceiveActionForward(Session var1, long var2) {
      if (var1 instanceof CastSession) {
         zza((CastSession)var1, var2);
      }

   }

   protected void onReceiveActionRewind(Session var1, long var2) {
      if (var1 instanceof CastSession) {
         zza((CastSession)var1, -var2);
      }

   }

   protected void onReceiveActionMediaButton(Session var1, Intent var2) {
      if (var1 instanceof CastSession) {
         if (!var2.hasExtra("android.intent.extra.KEY_EVENT")) {
            return;
         }

         KeyEvent var3;
         if ((var3 = (KeyEvent)var2.getExtras().get("android.intent.extra.KEY_EVENT")) == null || var3.getAction() != 0) {
            return;
         }

         if (var3.getKeyCode() == 85) {
            zzg((CastSession)var1);
         }
      }

   }

   protected void onReceiveOtherAction(String var1, Intent var2) {
   }

   private static void zzg(CastSession var0) {
      RemoteMediaClient var1;
      if ((var1 = zzh(var0)) != null) {
         var1.togglePlayback();
      }
   }

   private static void zza(CastSession var0, long var1) {
      if (var1 != 0L) {
         RemoteMediaClient var3;
         if ((var3 = zzh(var0)) != null && !var3.isLiveStream() && !var3.isPlayingAd()) {
            var3.seek(var3.getApproximateStreamPosition() + var1);
         }
      }
   }

   private static RemoteMediaClient zzh(CastSession var0) {
      return var0 != null && var0.isConnected() ? var0.getRemoteMediaClient() : null;
   }
}
