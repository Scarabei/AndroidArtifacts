package android.support.v4.media.session;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.MediaDescription;
import android.media.MediaMetadata;
import android.media.Rating;
import android.media.VolumeProvider;
import android.media.AudioAttributes.Builder;
import android.media.session.MediaSession;
import android.media.session.PlaybackState;
import android.media.session.MediaSession.Token;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.os.ResultReceiver;
import android.support.annotation.RequiresApi;
import android.util.Log;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RequiresApi(21)
class MediaSessionCompatApi21 {
   static final String TAG = "MediaSessionCompatApi21";

   public static Object createSession(Context context, String tag) {
      return new MediaSession(context, tag);
   }

   public static Object verifySession(Object mediaSession) {
      if (mediaSession instanceof MediaSession) {
         return mediaSession;
      } else {
         throw new IllegalArgumentException("mediaSession is not a valid MediaSession object");
      }
   }

   public static Object verifyToken(Object token) {
      if (token instanceof Token) {
         return token;
      } else {
         throw new IllegalArgumentException("token is not a valid MediaSession.Token object");
      }
   }

   public static Object createCallback(MediaSessionCompatApi21.Callback callback) {
      return new MediaSessionCompatApi21.CallbackProxy(callback);
   }

   public static void setCallback(Object sessionObj, Object callbackObj, Handler handler) {
      ((MediaSession)sessionObj).setCallback((android.media.session.MediaSession.Callback)callbackObj, handler);
   }

   public static void setFlags(Object sessionObj, int flags) {
      ((MediaSession)sessionObj).setFlags(flags);
   }

   public static void setPlaybackToLocal(Object sessionObj, int stream) {
      Builder bob = new Builder();
      bob.setLegacyStreamType(stream);
      ((MediaSession)sessionObj).setPlaybackToLocal(bob.build());
   }

   public static void setPlaybackToRemote(Object sessionObj, Object volumeProviderObj) {
      ((MediaSession)sessionObj).setPlaybackToRemote((VolumeProvider)volumeProviderObj);
   }

   public static void setActive(Object sessionObj, boolean active) {
      ((MediaSession)sessionObj).setActive(active);
   }

   public static boolean isActive(Object sessionObj) {
      return ((MediaSession)sessionObj).isActive();
   }

   public static void sendSessionEvent(Object sessionObj, String event, Bundle extras) {
      ((MediaSession)sessionObj).sendSessionEvent(event, extras);
   }

   public static void release(Object sessionObj) {
      ((MediaSession)sessionObj).release();
   }

   public static Parcelable getSessionToken(Object sessionObj) {
      return ((MediaSession)sessionObj).getSessionToken();
   }

   public static void setPlaybackState(Object sessionObj, Object stateObj) {
      ((MediaSession)sessionObj).setPlaybackState((PlaybackState)stateObj);
   }

   public static void setMetadata(Object sessionObj, Object metadataObj) {
      ((MediaSession)sessionObj).setMetadata((MediaMetadata)metadataObj);
   }

   public static void setSessionActivity(Object sessionObj, PendingIntent pi) {
      ((MediaSession)sessionObj).setSessionActivity(pi);
   }

   public static void setMediaButtonReceiver(Object sessionObj, PendingIntent pi) {
      ((MediaSession)sessionObj).setMediaButtonReceiver(pi);
   }

   public static void setQueue(Object sessionObj, List queueObjs) {
      if (queueObjs == null) {
         ((MediaSession)sessionObj).setQueue((List)null);
      } else {
         ArrayList queue = new ArrayList();
         Iterator var3 = queueObjs.iterator();

         while(var3.hasNext()) {
            Object itemObj = var3.next();
            queue.add((android.media.session.MediaSession.QueueItem)itemObj);
         }

         ((MediaSession)sessionObj).setQueue(queue);
      }
   }

   public static void setQueueTitle(Object sessionObj, CharSequence title) {
      ((MediaSession)sessionObj).setQueueTitle(title);
   }

   public static void setExtras(Object sessionObj, Bundle extras) {
      ((MediaSession)sessionObj).setExtras(extras);
   }

   public static boolean hasCallback(Object sessionObj) {
      Field callbackField = null;

      try {
         callbackField = sessionObj.getClass().getDeclaredField("mCallback");
         if (callbackField != null) {
            callbackField.setAccessible(true);
            return callbackField.get(sessionObj) != null;
         }
      } catch (IllegalAccessException | NoSuchFieldException var3) {
         Log.w("MediaSessionCompatApi21", "Failed to get mCallback object.");
      }

      return false;
   }

   static class QueueItem {
      public static Object createItem(Object mediaDescription, long id) {
         return new android.media.session.MediaSession.QueueItem((MediaDescription)mediaDescription, id);
      }

      public static Object getDescription(Object queueItem) {
         return ((android.media.session.MediaSession.QueueItem)queueItem).getDescription();
      }

      public static long getQueueId(Object queueItem) {
         return ((android.media.session.MediaSession.QueueItem)queueItem).getQueueId();
      }
   }

   static class CallbackProxy extends android.media.session.MediaSession.Callback {
      protected final MediaSessionCompatApi21.Callback mCallback;

      public CallbackProxy(MediaSessionCompatApi21.Callback callback) {
         this.mCallback = callback;
      }

      public void onCommand(String command, Bundle args, ResultReceiver cb) {
         this.mCallback.onCommand(command, args, cb);
      }

      public boolean onMediaButtonEvent(Intent mediaButtonIntent) {
         return this.mCallback.onMediaButtonEvent(mediaButtonIntent) || super.onMediaButtonEvent(mediaButtonIntent);
      }

      public void onPlay() {
         this.mCallback.onPlay();
      }

      public void onPlayFromMediaId(String mediaId, Bundle extras) {
         this.mCallback.onPlayFromMediaId(mediaId, extras);
      }

      public void onPlayFromSearch(String search, Bundle extras) {
         this.mCallback.onPlayFromSearch(search, extras);
      }

      public void onSkipToQueueItem(long id) {
         this.mCallback.onSkipToQueueItem(id);
      }

      public void onPause() {
         this.mCallback.onPause();
      }

      public void onSkipToNext() {
         this.mCallback.onSkipToNext();
      }

      public void onSkipToPrevious() {
         this.mCallback.onSkipToPrevious();
      }

      public void onFastForward() {
         this.mCallback.onFastForward();
      }

      public void onRewind() {
         this.mCallback.onRewind();
      }

      public void onStop() {
         this.mCallback.onStop();
      }

      public void onSeekTo(long pos) {
         this.mCallback.onSeekTo(pos);
      }

      public void onSetRating(Rating rating) {
         this.mCallback.onSetRating(rating);
      }

      public void onCustomAction(String action, Bundle extras) {
         this.mCallback.onCustomAction(action, extras);
      }
   }

   interface Callback {
      void onCommand(String var1, Bundle var2, ResultReceiver var3);

      boolean onMediaButtonEvent(Intent var1);

      void onPlay();

      void onPlayFromMediaId(String var1, Bundle var2);

      void onPlayFromSearch(String var1, Bundle var2);

      void onSkipToQueueItem(long var1);

      void onPause();

      void onSkipToNext();

      void onSkipToPrevious();

      void onFastForward();

      void onRewind();

      void onStop();

      void onSeekTo(long var1);

      void onSetRating(Object var1);

      void onSetRating(Object var1, Bundle var2);

      void onCustomAction(String var1, Bundle var2);
   }
}
