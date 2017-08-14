package android.support.v4.media.session;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.os.Build.VERSION;
import android.os.IBinder.DeathRecipient;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.BundleCompat;
import android.support.v4.app.SupportActivity;
import android.support.v4.app.SupportActivity.ExtraData;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.RatingCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public final class MediaControllerCompat {
   static final String TAG = "MediaControllerCompat";
   static final String COMMAND_GET_EXTRA_BINDER = "android.support.v4.media.session.command.GET_EXTRA_BINDER";
   static final String COMMAND_ADD_QUEUE_ITEM = "android.support.v4.media.session.command.ADD_QUEUE_ITEM";
   static final String COMMAND_ADD_QUEUE_ITEM_AT = "android.support.v4.media.session.command.ADD_QUEUE_ITEM_AT";
   static final String COMMAND_REMOVE_QUEUE_ITEM = "android.support.v4.media.session.command.REMOVE_QUEUE_ITEM";
   static final String COMMAND_REMOVE_QUEUE_ITEM_AT = "android.support.v4.media.session.command.REMOVE_QUEUE_ITEM_AT";
   static final String COMMAND_ARGUMENT_MEDIA_DESCRIPTION = "android.support.v4.media.session.command.ARGUMENT_MEDIA_DESCRIPTION";
   static final String COMMAND_ARGUMENT_INDEX = "android.support.v4.media.session.command.ARGUMENT_INDEX";
   private final MediaControllerCompat.MediaControllerImpl mImpl;
   private final MediaSessionCompat.Token mToken;
   private final HashSet mRegisteredCallbacks = new HashSet();

   public static void setMediaController(@NonNull Activity activity, MediaControllerCompat mediaController) {
      if (activity instanceof SupportActivity) {
         ((SupportActivity)activity).putExtraData(new MediaControllerCompat.MediaControllerExtraData(mediaController));
      }

      if (VERSION.SDK_INT >= 21) {
         Object controllerObj = null;
         if (mediaController != null) {
            Object sessionTokenObj = mediaController.getSessionToken().getToken();
            controllerObj = MediaControllerCompatApi21.fromToken(activity, sessionTokenObj);
         }

         MediaControllerCompatApi21.setMediaController(activity, controllerObj);
      }

   }

   public static MediaControllerCompat getMediaController(@NonNull Activity activity) {
      if (activity instanceof SupportActivity) {
         MediaControllerCompat.MediaControllerExtraData extraData = (MediaControllerCompat.MediaControllerExtraData)((SupportActivity)activity).getExtraData(MediaControllerCompat.MediaControllerExtraData.class);
         return extraData != null ? extraData.getMediaController() : null;
      } else {
         if (VERSION.SDK_INT >= 21) {
            Object controllerObj = MediaControllerCompatApi21.getMediaController(activity);
            if (controllerObj == null) {
               return null;
            }

            Object sessionTokenObj = MediaControllerCompatApi21.getSessionToken(controllerObj);

            try {
               return new MediaControllerCompat(activity, MediaSessionCompat.Token.fromToken(sessionTokenObj));
            } catch (RemoteException var4) {
               Log.e("MediaControllerCompat", "Dead object in getMediaController.", var4);
            }
         }

         return null;
      }
   }

   private static void validateCustomAction(String action, Bundle args) {
      if (action != null) {
         byte var3 = -1;
         switch(action.hashCode()) {
         case -1348483723:
            if (action.equals("android.support.v4.media.session.action.FOLLOW")) {
               var3 = 0;
            }
            break;
         case 503011406:
            if (action.equals("android.support.v4.media.session.action.UNFOLLOW")) {
               var3 = 1;
            }
         }

         switch(var3) {
         case 0:
         case 1:
            if (args == null || !args.containsKey("android.support.v4.media.session.ARGUMENT_MEDIA_ATTRIBUTE")) {
               throw new IllegalArgumentException("An extra field android.support.v4.media.session.ARGUMENT_MEDIA_ATTRIBUTE is required for this action " + action + ".");
            }
         default:
         }
      }
   }

   public MediaControllerCompat(Context context, @NonNull MediaSessionCompat session) {
      if (session == null) {
         throw new IllegalArgumentException("session must not be null");
      } else {
         this.mToken = session.getSessionToken();
         if (VERSION.SDK_INT >= 24) {
            this.mImpl = new MediaControllerCompat.MediaControllerImplApi24(context, session);
         } else if (VERSION.SDK_INT >= 23) {
            this.mImpl = new MediaControllerCompat.MediaControllerImplApi23(context, session);
         } else if (VERSION.SDK_INT >= 21) {
            this.mImpl = new MediaControllerCompat.MediaControllerImplApi21(context, session);
         } else {
            this.mImpl = new MediaControllerCompat.MediaControllerImplBase(this.mToken);
         }

      }
   }

   public MediaControllerCompat(Context context, @NonNull MediaSessionCompat.Token sessionToken) throws RemoteException {
      if (sessionToken == null) {
         throw new IllegalArgumentException("sessionToken must not be null");
      } else {
         this.mToken = sessionToken;
         if (VERSION.SDK_INT >= 24) {
            this.mImpl = new MediaControllerCompat.MediaControllerImplApi24(context, sessionToken);
         } else if (VERSION.SDK_INT >= 23) {
            this.mImpl = new MediaControllerCompat.MediaControllerImplApi23(context, sessionToken);
         } else if (VERSION.SDK_INT >= 21) {
            this.mImpl = new MediaControllerCompat.MediaControllerImplApi21(context, sessionToken);
         } else {
            this.mImpl = new MediaControllerCompat.MediaControllerImplBase(this.mToken);
         }

      }
   }

   public MediaControllerCompat.TransportControls getTransportControls() {
      return this.mImpl.getTransportControls();
   }

   public boolean dispatchMediaButtonEvent(KeyEvent keyEvent) {
      if (keyEvent == null) {
         throw new IllegalArgumentException("KeyEvent may not be null");
      } else {
         return this.mImpl.dispatchMediaButtonEvent(keyEvent);
      }
   }

   public PlaybackStateCompat getPlaybackState() {
      return this.mImpl.getPlaybackState();
   }

   public MediaMetadataCompat getMetadata() {
      return this.mImpl.getMetadata();
   }

   public List getQueue() {
      return this.mImpl.getQueue();
   }

   public void addQueueItem(MediaDescriptionCompat description) {
      this.mImpl.addQueueItem(description);
   }

   public void addQueueItem(MediaDescriptionCompat description, int index) {
      this.mImpl.addQueueItem(description, index);
   }

   public void removeQueueItem(MediaDescriptionCompat description) {
      this.mImpl.removeQueueItem(description);
   }

   /** @deprecated */
   @Deprecated
   public void removeQueueItemAt(int index) {
      List queue = this.getQueue();
      if (queue != null && index >= 0 && index < queue.size()) {
         MediaSessionCompat.QueueItem item = (MediaSessionCompat.QueueItem)queue.get(index);
         if (item != null) {
            this.removeQueueItem(item.getDescription());
         }
      }

   }

   public CharSequence getQueueTitle() {
      return this.mImpl.getQueueTitle();
   }

   public Bundle getExtras() {
      return this.mImpl.getExtras();
   }

   public int getRatingType() {
      return this.mImpl.getRatingType();
   }

   public boolean isCaptioningEnabled() {
      return this.mImpl.isCaptioningEnabled();
   }

   public int getRepeatMode() {
      return this.mImpl.getRepeatMode();
   }

   /** @deprecated */
   @Deprecated
   public boolean isShuffleModeEnabled() {
      return this.mImpl.isShuffleModeEnabled();
   }

   public int getShuffleMode() {
      return this.mImpl.getShuffleMode();
   }

   public long getFlags() {
      return this.mImpl.getFlags();
   }

   public MediaControllerCompat.PlaybackInfo getPlaybackInfo() {
      return this.mImpl.getPlaybackInfo();
   }

   public PendingIntent getSessionActivity() {
      return this.mImpl.getSessionActivity();
   }

   public MediaSessionCompat.Token getSessionToken() {
      return this.mToken;
   }

   public void setVolumeTo(int value, int flags) {
      this.mImpl.setVolumeTo(value, flags);
   }

   public void adjustVolume(int direction, int flags) {
      this.mImpl.adjustVolume(direction, flags);
   }

   public void registerCallback(@NonNull MediaControllerCompat.Callback callback) {
      this.registerCallback(callback, (Handler)null);
   }

   public void registerCallback(@NonNull MediaControllerCompat.Callback callback, Handler handler) {
      if (callback == null) {
         throw new IllegalArgumentException("callback must not be null");
      } else {
         if (handler == null) {
            handler = new Handler();
         }

         callback.setHandler(handler);
         this.mImpl.registerCallback(callback, handler);
         this.mRegisteredCallbacks.add(callback);
      }
   }

   public void unregisterCallback(@NonNull MediaControllerCompat.Callback callback) {
      if (callback == null) {
         throw new IllegalArgumentException("callback must not be null");
      } else {
         try {
            this.mRegisteredCallbacks.remove(callback);
            this.mImpl.unregisterCallback(callback);
         } finally {
            callback.setHandler((Handler)null);
         }

      }
   }

   public void sendCommand(@NonNull String command, Bundle params, ResultReceiver cb) {
      if (TextUtils.isEmpty(command)) {
         throw new IllegalArgumentException("command must neither be null nor empty");
      } else {
         this.mImpl.sendCommand(command, params, cb);
      }
   }

   public String getPackageName() {
      return this.mImpl.getPackageName();
   }

   public Object getMediaController() {
      return this.mImpl.getMediaController();
   }

   @RequiresApi(24)
   static class TransportControlsApi24 extends MediaControllerCompat.TransportControlsApi23 {
      public TransportControlsApi24(Object controlsObj) {
         super(controlsObj);
      }

      public void prepare() {
         MediaControllerCompatApi24.TransportControls.prepare(this.mControlsObj);
      }

      public void prepareFromMediaId(String mediaId, Bundle extras) {
         MediaControllerCompatApi24.TransportControls.prepareFromMediaId(this.mControlsObj, mediaId, extras);
      }

      public void prepareFromSearch(String query, Bundle extras) {
         MediaControllerCompatApi24.TransportControls.prepareFromSearch(this.mControlsObj, query, extras);
      }

      public void prepareFromUri(Uri uri, Bundle extras) {
         MediaControllerCompatApi24.TransportControls.prepareFromUri(this.mControlsObj, uri, extras);
      }
   }

   @RequiresApi(24)
   static class MediaControllerImplApi24 extends MediaControllerCompat.MediaControllerImplApi23 {
      public MediaControllerImplApi24(Context context, MediaSessionCompat session) {
         super(context, session);
      }

      public MediaControllerImplApi24(Context context, MediaSessionCompat.Token sessionToken) throws RemoteException {
         super(context, sessionToken);
      }

      public MediaControllerCompat.TransportControls getTransportControls() {
         Object controlsObj = MediaControllerCompatApi21.getTransportControls(this.mControllerObj);
         return controlsObj != null ? new MediaControllerCompat.TransportControlsApi24(controlsObj) : null;
      }
   }

   @RequiresApi(23)
   static class TransportControlsApi23 extends MediaControllerCompat.TransportControlsApi21 {
      public TransportControlsApi23(Object controlsObj) {
         super(controlsObj);
      }

      public void playFromUri(Uri uri, Bundle extras) {
         MediaControllerCompatApi23.TransportControls.playFromUri(this.mControlsObj, uri, extras);
      }
   }

   @RequiresApi(23)
   static class MediaControllerImplApi23 extends MediaControllerCompat.MediaControllerImplApi21 {
      public MediaControllerImplApi23(Context context, MediaSessionCompat session) {
         super(context, session);
      }

      public MediaControllerImplApi23(Context context, MediaSessionCompat.Token sessionToken) throws RemoteException {
         super(context, sessionToken);
      }

      public MediaControllerCompat.TransportControls getTransportControls() {
         Object controlsObj = MediaControllerCompatApi21.getTransportControls(this.mControllerObj);
         return controlsObj != null ? new MediaControllerCompat.TransportControlsApi23(controlsObj) : null;
      }
   }

   static class TransportControlsApi21 extends MediaControllerCompat.TransportControls {
      protected final Object mControlsObj;

      public TransportControlsApi21(Object controlsObj) {
         this.mControlsObj = controlsObj;
      }

      public void prepare() {
         this.sendCustomAction((String)"android.support.v4.media.session.action.PREPARE", (Bundle)null);
      }

      public void prepareFromMediaId(String mediaId, Bundle extras) {
         Bundle bundle = new Bundle();
         bundle.putString("android.support.v4.media.session.action.ARGUMENT_MEDIA_ID", mediaId);
         bundle.putBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS", extras);
         this.sendCustomAction("android.support.v4.media.session.action.PREPARE_FROM_MEDIA_ID", bundle);
      }

      public void prepareFromSearch(String query, Bundle extras) {
         Bundle bundle = new Bundle();
         bundle.putString("android.support.v4.media.session.action.ARGUMENT_QUERY", query);
         bundle.putBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS", extras);
         this.sendCustomAction("android.support.v4.media.session.action.PREPARE_FROM_SEARCH", bundle);
      }

      public void prepareFromUri(Uri uri, Bundle extras) {
         Bundle bundle = new Bundle();
         bundle.putParcelable("android.support.v4.media.session.action.ARGUMENT_URI", uri);
         bundle.putBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS", extras);
         this.sendCustomAction("android.support.v4.media.session.action.PREPARE_FROM_URI", bundle);
      }

      public void play() {
         MediaControllerCompatApi21.TransportControls.play(this.mControlsObj);
      }

      public void pause() {
         MediaControllerCompatApi21.TransportControls.pause(this.mControlsObj);
      }

      public void stop() {
         MediaControllerCompatApi21.TransportControls.stop(this.mControlsObj);
      }

      public void seekTo(long pos) {
         MediaControllerCompatApi21.TransportControls.seekTo(this.mControlsObj, pos);
      }

      public void fastForward() {
         MediaControllerCompatApi21.TransportControls.fastForward(this.mControlsObj);
      }

      public void rewind() {
         MediaControllerCompatApi21.TransportControls.rewind(this.mControlsObj);
      }

      public void skipToNext() {
         MediaControllerCompatApi21.TransportControls.skipToNext(this.mControlsObj);
      }

      public void skipToPrevious() {
         MediaControllerCompatApi21.TransportControls.skipToPrevious(this.mControlsObj);
      }

      public void setRating(RatingCompat rating) {
         MediaControllerCompatApi21.TransportControls.setRating(this.mControlsObj, rating != null ? rating.getRating() : null);
      }

      public void setRating(RatingCompat rating, Bundle extras) {
         Bundle bundle = new Bundle();
         bundle.putParcelable("android.support.v4.media.session.action.ARGUMENT_RATING", rating);
         bundle.putParcelable("android.support.v4.media.session.action.ARGUMENT_EXTRAS", extras);
         this.sendCustomAction("android.support.v4.media.session.action.SET_RATING", bundle);
      }

      public void setCaptioningEnabled(boolean enabled) {
         Bundle bundle = new Bundle();
         bundle.putBoolean("android.support.v4.media.session.action.ARGUMENT_CAPTIONING_ENABLED", enabled);
         this.sendCustomAction("android.support.v4.media.session.action.SET_CAPTIONING_ENABLED", bundle);
      }

      public void setRepeatMode(int repeatMode) {
         Bundle bundle = new Bundle();
         bundle.putInt("android.support.v4.media.session.action.ARGUMENT_REPEAT_MODE", repeatMode);
         this.sendCustomAction("android.support.v4.media.session.action.SET_REPEAT_MODE", bundle);
      }

      public void setShuffleModeEnabled(boolean enabled) {
         Bundle bundle = new Bundle();
         bundle.putBoolean("android.support.v4.media.session.action.ARGUMENT_SHUFFLE_MODE_ENABLED", enabled);
         this.sendCustomAction("android.support.v4.media.session.action.SET_SHUFFLE_MODE_ENABLED", bundle);
      }

      public void setShuffleMode(int shuffleMode) {
         Bundle bundle = new Bundle();
         bundle.putInt("android.support.v4.media.session.action.ARGUMENT_SHUFFLE_MODE", shuffleMode);
         this.sendCustomAction("android.support.v4.media.session.action.SET_SHUFFLE_MODE", bundle);
      }

      public void playFromMediaId(String mediaId, Bundle extras) {
         MediaControllerCompatApi21.TransportControls.playFromMediaId(this.mControlsObj, mediaId, extras);
      }

      public void playFromSearch(String query, Bundle extras) {
         MediaControllerCompatApi21.TransportControls.playFromSearch(this.mControlsObj, query, extras);
      }

      public void playFromUri(Uri uri, Bundle extras) {
         if (uri != null && !Uri.EMPTY.equals(uri)) {
            Bundle bundle = new Bundle();
            bundle.putParcelable("android.support.v4.media.session.action.ARGUMENT_URI", uri);
            bundle.putParcelable("android.support.v4.media.session.action.ARGUMENT_EXTRAS", extras);
            this.sendCustomAction("android.support.v4.media.session.action.PLAY_FROM_URI", bundle);
         } else {
            throw new IllegalArgumentException("You must specify a non-empty Uri for playFromUri.");
         }
      }

      public void skipToQueueItem(long id) {
         MediaControllerCompatApi21.TransportControls.skipToQueueItem(this.mControlsObj, id);
      }

      public void sendCustomAction(PlaybackStateCompat.CustomAction customAction, Bundle args) {
         MediaControllerCompat.validateCustomAction(customAction.getAction(), args);
         MediaControllerCompatApi21.TransportControls.sendCustomAction(this.mControlsObj, customAction.getAction(), args);
      }

      public void sendCustomAction(String action, Bundle args) {
         MediaControllerCompat.validateCustomAction(action, args);
         MediaControllerCompatApi21.TransportControls.sendCustomAction(this.mControlsObj, action, args);
      }
   }

   @RequiresApi(21)
   static class MediaControllerImplApi21 implements MediaControllerCompat.MediaControllerImpl {
      protected final Object mControllerObj;
      private final List mPendingCallbacks = new ArrayList();
      private IMediaSession mExtraBinder;
      private HashMap mCallbackMap = new HashMap();

      public MediaControllerImplApi21(Context context, MediaSessionCompat session) {
         this.mControllerObj = MediaControllerCompatApi21.fromToken(context, session.getSessionToken().getToken());
         this.mExtraBinder = session.getSessionToken().getExtraBinder();
         if (this.mExtraBinder == null) {
            this.requestExtraBinder();
         }

      }

      public MediaControllerImplApi21(Context context, MediaSessionCompat.Token sessionToken) throws RemoteException {
         this.mControllerObj = MediaControllerCompatApi21.fromToken(context, sessionToken.getToken());
         if (this.mControllerObj == null) {
            throw new RemoteException();
         } else {
            this.mExtraBinder = sessionToken.getExtraBinder();
            if (this.mExtraBinder == null) {
               this.requestExtraBinder();
            }

         }
      }

      public final void registerCallback(MediaControllerCompat.Callback callback, Handler handler) {
         MediaControllerCompatApi21.registerCallback(this.mControllerObj, callback.mCallbackObj, handler);
         if (this.mExtraBinder != null) {
            MediaControllerCompat.MediaControllerImplApi21.ExtraCallback extraCallback = new MediaControllerCompat.MediaControllerImplApi21.ExtraCallback(callback);
            this.mCallbackMap.put(callback, extraCallback);
            callback.mHasExtraCallback = true;

            try {
               this.mExtraBinder.registerCallbackListener(extraCallback);
            } catch (RemoteException var7) {
               Log.e("MediaControllerCompat", "Dead object in registerCallback.", var7);
            }
         } else {
            List var8 = this.mPendingCallbacks;
            synchronized(this.mPendingCallbacks) {
               this.mPendingCallbacks.add(callback);
            }
         }

      }

      public final void unregisterCallback(MediaControllerCompat.Callback callback) {
         MediaControllerCompatApi21.unregisterCallback(this.mControllerObj, callback.mCallbackObj);
         if (this.mExtraBinder != null) {
            try {
               MediaControllerCompat.MediaControllerImplApi21.ExtraCallback extraCallback = (MediaControllerCompat.MediaControllerImplApi21.ExtraCallback)this.mCallbackMap.remove(callback);
               if (extraCallback != null) {
                  this.mExtraBinder.unregisterCallbackListener(extraCallback);
               }
            } catch (RemoteException var5) {
               Log.e("MediaControllerCompat", "Dead object in unregisterCallback.", var5);
            }
         } else {
            List var6 = this.mPendingCallbacks;
            synchronized(this.mPendingCallbacks) {
               this.mPendingCallbacks.remove(callback);
            }
         }

      }

      public boolean dispatchMediaButtonEvent(KeyEvent event) {
         return MediaControllerCompatApi21.dispatchMediaButtonEvent(this.mControllerObj, event);
      }

      public MediaControllerCompat.TransportControls getTransportControls() {
         Object controlsObj = MediaControllerCompatApi21.getTransportControls(this.mControllerObj);
         return controlsObj != null ? new MediaControllerCompat.TransportControlsApi21(controlsObj) : null;
      }

      public PlaybackStateCompat getPlaybackState() {
         if (this.mExtraBinder != null) {
            try {
               return this.mExtraBinder.getPlaybackState();
            } catch (RemoteException var2) {
               Log.e("MediaControllerCompat", "Dead object in getPlaybackState.", var2);
            }
         }

         Object stateObj = MediaControllerCompatApi21.getPlaybackState(this.mControllerObj);
         return stateObj != null ? PlaybackStateCompat.fromPlaybackState(stateObj) : null;
      }

      public MediaMetadataCompat getMetadata() {
         Object metadataObj = MediaControllerCompatApi21.getMetadata(this.mControllerObj);
         return metadataObj != null ? MediaMetadataCompat.fromMediaMetadata(metadataObj) : null;
      }

      public List getQueue() {
         List queueObjs = MediaControllerCompatApi21.getQueue(this.mControllerObj);
         return queueObjs != null ? MediaSessionCompat.QueueItem.fromQueueItemList(queueObjs) : null;
      }

      public void addQueueItem(MediaDescriptionCompat description) {
         long flags = this.getFlags();
         if ((flags & 4L) == 0L) {
            throw new UnsupportedOperationException("This session doesn't support queue management operations");
         } else {
            Bundle params = new Bundle();
            params.putParcelable("android.support.v4.media.session.command.ARGUMENT_MEDIA_DESCRIPTION", description);
            this.sendCommand("android.support.v4.media.session.command.ADD_QUEUE_ITEM", params, (ResultReceiver)null);
         }
      }

      public void addQueueItem(MediaDescriptionCompat description, int index) {
         long flags = this.getFlags();
         if ((flags & 4L) == 0L) {
            throw new UnsupportedOperationException("This session doesn't support queue management operations");
         } else {
            Bundle params = new Bundle();
            params.putParcelable("android.support.v4.media.session.command.ARGUMENT_MEDIA_DESCRIPTION", description);
            params.putInt("android.support.v4.media.session.command.ARGUMENT_INDEX", index);
            this.sendCommand("android.support.v4.media.session.command.ADD_QUEUE_ITEM_AT", params, (ResultReceiver)null);
         }
      }

      public void removeQueueItem(MediaDescriptionCompat description) {
         long flags = this.getFlags();
         if ((flags & 4L) == 0L) {
            throw new UnsupportedOperationException("This session doesn't support queue management operations");
         } else {
            Bundle params = new Bundle();
            params.putParcelable("android.support.v4.media.session.command.ARGUMENT_MEDIA_DESCRIPTION", description);
            this.sendCommand("android.support.v4.media.session.command.REMOVE_QUEUE_ITEM", params, (ResultReceiver)null);
         }
      }

      public CharSequence getQueueTitle() {
         return MediaControllerCompatApi21.getQueueTitle(this.mControllerObj);
      }

      public Bundle getExtras() {
         return MediaControllerCompatApi21.getExtras(this.mControllerObj);
      }

      public int getRatingType() {
         if (VERSION.SDK_INT < 22 && this.mExtraBinder != null) {
            try {
               return this.mExtraBinder.getRatingType();
            } catch (RemoteException var2) {
               Log.e("MediaControllerCompat", "Dead object in getRatingType.", var2);
            }
         }

         return MediaControllerCompatApi21.getRatingType(this.mControllerObj);
      }

      public boolean isCaptioningEnabled() {
         if (this.mExtraBinder != null) {
            try {
               return this.mExtraBinder.isCaptioningEnabled();
            } catch (RemoteException var2) {
               Log.e("MediaControllerCompat", "Dead object in isCaptioningEnabled.", var2);
            }
         }

         return false;
      }

      public int getRepeatMode() {
         if (this.mExtraBinder != null) {
            try {
               return this.mExtraBinder.getRepeatMode();
            } catch (RemoteException var2) {
               Log.e("MediaControllerCompat", "Dead object in getRepeatMode.", var2);
            }
         }

         return 0;
      }

      public boolean isShuffleModeEnabled() {
         if (this.mExtraBinder != null) {
            try {
               return this.mExtraBinder.isShuffleModeEnabledDeprecated();
            } catch (RemoteException var2) {
               Log.e("MediaControllerCompat", "Dead object in isShuffleModeEnabled.", var2);
            }
         }

         return false;
      }

      public int getShuffleMode() {
         if (this.mExtraBinder != null) {
            try {
               return this.mExtraBinder.getShuffleMode();
            } catch (RemoteException var2) {
               Log.e("MediaControllerCompat", "Dead object in getShuffleMode.", var2);
            }
         }

         return 0;
      }

      public long getFlags() {
         return MediaControllerCompatApi21.getFlags(this.mControllerObj);
      }

      public MediaControllerCompat.PlaybackInfo getPlaybackInfo() {
         Object volumeInfoObj = MediaControllerCompatApi21.getPlaybackInfo(this.mControllerObj);
         return volumeInfoObj != null ? new MediaControllerCompat.PlaybackInfo(MediaControllerCompatApi21.PlaybackInfo.getPlaybackType(volumeInfoObj), MediaControllerCompatApi21.PlaybackInfo.getLegacyAudioStream(volumeInfoObj), MediaControllerCompatApi21.PlaybackInfo.getVolumeControl(volumeInfoObj), MediaControllerCompatApi21.PlaybackInfo.getMaxVolume(volumeInfoObj), MediaControllerCompatApi21.PlaybackInfo.getCurrentVolume(volumeInfoObj)) : null;
      }

      public PendingIntent getSessionActivity() {
         return MediaControllerCompatApi21.getSessionActivity(this.mControllerObj);
      }

      public void setVolumeTo(int value, int flags) {
         MediaControllerCompatApi21.setVolumeTo(this.mControllerObj, value, flags);
      }

      public void adjustVolume(int direction, int flags) {
         MediaControllerCompatApi21.adjustVolume(this.mControllerObj, direction, flags);
      }

      public void sendCommand(String command, Bundle params, ResultReceiver cb) {
         MediaControllerCompatApi21.sendCommand(this.mControllerObj, command, params, cb);
      }

      public String getPackageName() {
         return MediaControllerCompatApi21.getPackageName(this.mControllerObj);
      }

      public Object getMediaController() {
         return this.mControllerObj;
      }

      private void requestExtraBinder() {
         this.sendCommand("android.support.v4.media.session.command.GET_EXTRA_BINDER", (Bundle)null, new MediaControllerCompat.MediaControllerImplApi21.ExtraBinderRequestResultReceiver(this, new Handler()));
      }

      private void processPendingCallbacks() {
         if (this.mExtraBinder != null) {
            List var1 = this.mPendingCallbacks;
            synchronized(this.mPendingCallbacks) {
               Iterator var2 = this.mPendingCallbacks.iterator();

               while(var2.hasNext()) {
                  MediaControllerCompat.Callback callback = (MediaControllerCompat.Callback)var2.next();
                  MediaControllerCompat.MediaControllerImplApi21.ExtraCallback extraCallback = new MediaControllerCompat.MediaControllerImplApi21.ExtraCallback(callback);
                  this.mCallbackMap.put(callback, extraCallback);
                  callback.mHasExtraCallback = true;

                  try {
                     this.mExtraBinder.registerCallbackListener(extraCallback);
                  } catch (RemoteException var7) {
                     Log.e("MediaControllerCompat", "Dead object in registerCallback.", var7);
                     break;
                  }
               }

               this.mPendingCallbacks.clear();
            }
         }
      }

      private static class ExtraCallback extends MediaControllerCompat.Callback.StubCompat {
         ExtraCallback(MediaControllerCompat.Callback callback) {
            super(callback);
         }

         public void onSessionDestroyed() throws RemoteException {
            throw new AssertionError();
         }

         public void onMetadataChanged(MediaMetadataCompat metadata) throws RemoteException {
            throw new AssertionError();
         }

         public void onQueueChanged(List queue) throws RemoteException {
            throw new AssertionError();
         }

         public void onQueueTitleChanged(CharSequence title) throws RemoteException {
            throw new AssertionError();
         }

         public void onExtrasChanged(Bundle extras) throws RemoteException {
            throw new AssertionError();
         }

         public void onVolumeInfoChanged(ParcelableVolumeInfo info) throws RemoteException {
            throw new AssertionError();
         }
      }

      private static class ExtraBinderRequestResultReceiver extends ResultReceiver {
         private WeakReference mMediaControllerImpl;

         public ExtraBinderRequestResultReceiver(MediaControllerCompat.MediaControllerImplApi21 mediaControllerImpl, Handler handler) {
            super(handler);
            this.mMediaControllerImpl = new WeakReference(mediaControllerImpl);
         }

         protected void onReceiveResult(int resultCode, Bundle resultData) {
            MediaControllerCompat.MediaControllerImplApi21 mediaControllerImpl = (MediaControllerCompat.MediaControllerImplApi21)this.mMediaControllerImpl.get();
            if (mediaControllerImpl != null && resultData != null) {
               mediaControllerImpl.mExtraBinder = IMediaSession.Stub.asInterface(BundleCompat.getBinder(resultData, "android.support.v4.media.session.EXTRA_BINDER"));
               mediaControllerImpl.processPendingCallbacks();
            }
         }
      }
   }

   static class TransportControlsBase extends MediaControllerCompat.TransportControls {
      private IMediaSession mBinder;

      public TransportControlsBase(IMediaSession binder) {
         this.mBinder = binder;
      }

      public void prepare() {
         try {
            this.mBinder.prepare();
         } catch (RemoteException var2) {
            Log.e("MediaControllerCompat", "Dead object in prepare.", var2);
         }

      }

      public void prepareFromMediaId(String mediaId, Bundle extras) {
         try {
            this.mBinder.prepareFromMediaId(mediaId, extras);
         } catch (RemoteException var4) {
            Log.e("MediaControllerCompat", "Dead object in prepareFromMediaId.", var4);
         }

      }

      public void prepareFromSearch(String query, Bundle extras) {
         try {
            this.mBinder.prepareFromSearch(query, extras);
         } catch (RemoteException var4) {
            Log.e("MediaControllerCompat", "Dead object in prepareFromSearch.", var4);
         }

      }

      public void prepareFromUri(Uri uri, Bundle extras) {
         try {
            this.mBinder.prepareFromUri(uri, extras);
         } catch (RemoteException var4) {
            Log.e("MediaControllerCompat", "Dead object in prepareFromUri.", var4);
         }

      }

      public void play() {
         try {
            this.mBinder.play();
         } catch (RemoteException var2) {
            Log.e("MediaControllerCompat", "Dead object in play.", var2);
         }

      }

      public void playFromMediaId(String mediaId, Bundle extras) {
         try {
            this.mBinder.playFromMediaId(mediaId, extras);
         } catch (RemoteException var4) {
            Log.e("MediaControllerCompat", "Dead object in playFromMediaId.", var4);
         }

      }

      public void playFromSearch(String query, Bundle extras) {
         try {
            this.mBinder.playFromSearch(query, extras);
         } catch (RemoteException var4) {
            Log.e("MediaControllerCompat", "Dead object in playFromSearch.", var4);
         }

      }

      public void playFromUri(Uri uri, Bundle extras) {
         try {
            this.mBinder.playFromUri(uri, extras);
         } catch (RemoteException var4) {
            Log.e("MediaControllerCompat", "Dead object in playFromUri.", var4);
         }

      }

      public void skipToQueueItem(long id) {
         try {
            this.mBinder.skipToQueueItem(id);
         } catch (RemoteException var4) {
            Log.e("MediaControllerCompat", "Dead object in skipToQueueItem.", var4);
         }

      }

      public void pause() {
         try {
            this.mBinder.pause();
         } catch (RemoteException var2) {
            Log.e("MediaControllerCompat", "Dead object in pause.", var2);
         }

      }

      public void stop() {
         try {
            this.mBinder.stop();
         } catch (RemoteException var2) {
            Log.e("MediaControllerCompat", "Dead object in stop.", var2);
         }

      }

      public void seekTo(long pos) {
         try {
            this.mBinder.seekTo(pos);
         } catch (RemoteException var4) {
            Log.e("MediaControllerCompat", "Dead object in seekTo.", var4);
         }

      }

      public void fastForward() {
         try {
            this.mBinder.fastForward();
         } catch (RemoteException var2) {
            Log.e("MediaControllerCompat", "Dead object in fastForward.", var2);
         }

      }

      public void skipToNext() {
         try {
            this.mBinder.next();
         } catch (RemoteException var2) {
            Log.e("MediaControllerCompat", "Dead object in skipToNext.", var2);
         }

      }

      public void rewind() {
         try {
            this.mBinder.rewind();
         } catch (RemoteException var2) {
            Log.e("MediaControllerCompat", "Dead object in rewind.", var2);
         }

      }

      public void skipToPrevious() {
         try {
            this.mBinder.previous();
         } catch (RemoteException var2) {
            Log.e("MediaControllerCompat", "Dead object in skipToPrevious.", var2);
         }

      }

      public void setRating(RatingCompat rating) {
         try {
            this.mBinder.rate(rating);
         } catch (RemoteException var3) {
            Log.e("MediaControllerCompat", "Dead object in setRating.", var3);
         }

      }

      public void setRating(RatingCompat rating, Bundle extras) {
         try {
            this.mBinder.rateWithExtras(rating, extras);
         } catch (RemoteException var4) {
            Log.e("MediaControllerCompat", "Dead object in setRating.", var4);
         }

      }

      public void setCaptioningEnabled(boolean enabled) {
         try {
            this.mBinder.setCaptioningEnabled(enabled);
         } catch (RemoteException var3) {
            Log.e("MediaControllerCompat", "Dead object in setCaptioningEnabled.", var3);
         }

      }

      public void setRepeatMode(int repeatMode) {
         try {
            this.mBinder.setRepeatMode(repeatMode);
         } catch (RemoteException var3) {
            Log.e("MediaControllerCompat", "Dead object in setRepeatMode.", var3);
         }

      }

      public void setShuffleModeEnabled(boolean enabled) {
         try {
            this.mBinder.setShuffleModeEnabledDeprecated(enabled);
         } catch (RemoteException var3) {
            Log.e("MediaControllerCompat", "Dead object in setShuffleModeEnabled.", var3);
         }

      }

      public void setShuffleMode(int shuffleMode) {
         try {
            this.mBinder.setShuffleMode(shuffleMode);
         } catch (RemoteException var3) {
            Log.e("MediaControllerCompat", "Dead object in setShuffleMode.", var3);
         }

      }

      public void sendCustomAction(PlaybackStateCompat.CustomAction customAction, Bundle args) {
         this.sendCustomAction(customAction.getAction(), args);
      }

      public void sendCustomAction(String action, Bundle args) {
         MediaControllerCompat.validateCustomAction(action, args);

         try {
            this.mBinder.sendCustomAction(action, args);
         } catch (RemoteException var4) {
            Log.e("MediaControllerCompat", "Dead object in sendCustomAction.", var4);
         }

      }
   }

   static class MediaControllerImplBase implements MediaControllerCompat.MediaControllerImpl {
      private IMediaSession mBinder;
      private MediaControllerCompat.TransportControls mTransportControls;

      public MediaControllerImplBase(MediaSessionCompat.Token token) {
         this.mBinder = IMediaSession.Stub.asInterface((IBinder)token.getToken());
      }

      public void registerCallback(MediaControllerCompat.Callback callback, Handler handler) {
         if (callback == null) {
            throw new IllegalArgumentException("callback may not be null.");
         } else {
            try {
               this.mBinder.asBinder().linkToDeath(callback, 0);
               this.mBinder.registerCallbackListener((IMediaControllerCallback)callback.mCallbackObj);
            } catch (RemoteException var4) {
               Log.e("MediaControllerCompat", "Dead object in registerCallback.", var4);
               callback.onSessionDestroyed();
            }

         }
      }

      public void unregisterCallback(MediaControllerCompat.Callback callback) {
         if (callback == null) {
            throw new IllegalArgumentException("callback may not be null.");
         } else {
            try {
               this.mBinder.unregisterCallbackListener((IMediaControllerCallback)callback.mCallbackObj);
               this.mBinder.asBinder().unlinkToDeath(callback, 0);
            } catch (RemoteException var3) {
               Log.e("MediaControllerCompat", "Dead object in unregisterCallback.", var3);
            }

         }
      }

      public boolean dispatchMediaButtonEvent(KeyEvent event) {
         if (event == null) {
            throw new IllegalArgumentException("event may not be null.");
         } else {
            try {
               this.mBinder.sendMediaButton(event);
            } catch (RemoteException var3) {
               Log.e("MediaControllerCompat", "Dead object in dispatchMediaButtonEvent.", var3);
            }

            return false;
         }
      }

      public MediaControllerCompat.TransportControls getTransportControls() {
         if (this.mTransportControls == null) {
            this.mTransportControls = new MediaControllerCompat.TransportControlsBase(this.mBinder);
         }

         return this.mTransportControls;
      }

      public PlaybackStateCompat getPlaybackState() {
         try {
            return this.mBinder.getPlaybackState();
         } catch (RemoteException var2) {
            Log.e("MediaControllerCompat", "Dead object in getPlaybackState.", var2);
            return null;
         }
      }

      public MediaMetadataCompat getMetadata() {
         try {
            return this.mBinder.getMetadata();
         } catch (RemoteException var2) {
            Log.e("MediaControllerCompat", "Dead object in getMetadata.", var2);
            return null;
         }
      }

      public List getQueue() {
         try {
            return this.mBinder.getQueue();
         } catch (RemoteException var2) {
            Log.e("MediaControllerCompat", "Dead object in getQueue.", var2);
            return null;
         }
      }

      public void addQueueItem(MediaDescriptionCompat description) {
         try {
            long flags = this.mBinder.getFlags();
            if ((flags & 4L) == 0L) {
               throw new UnsupportedOperationException("This session doesn't support queue management operations");
            }

            this.mBinder.addQueueItem(description);
         } catch (RemoteException var4) {
            Log.e("MediaControllerCompat", "Dead object in addQueueItem.", var4);
         }

      }

      public void addQueueItem(MediaDescriptionCompat description, int index) {
         try {
            long flags = this.mBinder.getFlags();
            if ((flags & 4L) == 0L) {
               throw new UnsupportedOperationException("This session doesn't support queue management operations");
            }

            this.mBinder.addQueueItemAt(description, index);
         } catch (RemoteException var5) {
            Log.e("MediaControllerCompat", "Dead object in addQueueItemAt.", var5);
         }

      }

      public void removeQueueItem(MediaDescriptionCompat description) {
         try {
            long flags = this.mBinder.getFlags();
            if ((flags & 4L) == 0L) {
               throw new UnsupportedOperationException("This session doesn't support queue management operations");
            }

            this.mBinder.removeQueueItem(description);
         } catch (RemoteException var4) {
            Log.e("MediaControllerCompat", "Dead object in removeQueueItem.", var4);
         }

      }

      public CharSequence getQueueTitle() {
         try {
            return this.mBinder.getQueueTitle();
         } catch (RemoteException var2) {
            Log.e("MediaControllerCompat", "Dead object in getQueueTitle.", var2);
            return null;
         }
      }

      public Bundle getExtras() {
         try {
            return this.mBinder.getExtras();
         } catch (RemoteException var2) {
            Log.e("MediaControllerCompat", "Dead object in getExtras.", var2);
            return null;
         }
      }

      public int getRatingType() {
         try {
            return this.mBinder.getRatingType();
         } catch (RemoteException var2) {
            Log.e("MediaControllerCompat", "Dead object in getRatingType.", var2);
            return 0;
         }
      }

      public boolean isCaptioningEnabled() {
         try {
            return this.mBinder.isCaptioningEnabled();
         } catch (RemoteException var2) {
            Log.e("MediaControllerCompat", "Dead object in isCaptioningEnabled.", var2);
            return false;
         }
      }

      public int getRepeatMode() {
         try {
            return this.mBinder.getRepeatMode();
         } catch (RemoteException var2) {
            Log.e("MediaControllerCompat", "Dead object in getRepeatMode.", var2);
            return 0;
         }
      }

      public boolean isShuffleModeEnabled() {
         try {
            return this.mBinder.isShuffleModeEnabledDeprecated();
         } catch (RemoteException var2) {
            Log.e("MediaControllerCompat", "Dead object in isShuffleModeEnabled.", var2);
            return false;
         }
      }

      public int getShuffleMode() {
         try {
            return this.mBinder.getShuffleMode();
         } catch (RemoteException var2) {
            Log.e("MediaControllerCompat", "Dead object in getShuffleMode.", var2);
            return 0;
         }
      }

      public long getFlags() {
         try {
            return this.mBinder.getFlags();
         } catch (RemoteException var2) {
            Log.e("MediaControllerCompat", "Dead object in getFlags.", var2);
            return 0L;
         }
      }

      public MediaControllerCompat.PlaybackInfo getPlaybackInfo() {
         try {
            ParcelableVolumeInfo info = this.mBinder.getVolumeAttributes();
            MediaControllerCompat.PlaybackInfo pi = new MediaControllerCompat.PlaybackInfo(info.volumeType, info.audioStream, info.controlType, info.maxVolume, info.currentVolume);
            return pi;
         } catch (RemoteException var3) {
            Log.e("MediaControllerCompat", "Dead object in getPlaybackInfo.", var3);
            return null;
         }
      }

      public PendingIntent getSessionActivity() {
         try {
            return this.mBinder.getLaunchPendingIntent();
         } catch (RemoteException var2) {
            Log.e("MediaControllerCompat", "Dead object in getSessionActivity.", var2);
            return null;
         }
      }

      public void setVolumeTo(int value, int flags) {
         try {
            this.mBinder.setVolumeTo(value, flags, (String)null);
         } catch (RemoteException var4) {
            Log.e("MediaControllerCompat", "Dead object in setVolumeTo.", var4);
         }

      }

      public void adjustVolume(int direction, int flags) {
         try {
            this.mBinder.adjustVolume(direction, flags, (String)null);
         } catch (RemoteException var4) {
            Log.e("MediaControllerCompat", "Dead object in adjustVolume.", var4);
         }

      }

      public void sendCommand(String command, Bundle params, ResultReceiver cb) {
         try {
            this.mBinder.sendCommand(command, params, new MediaSessionCompat.ResultReceiverWrapper(cb));
         } catch (RemoteException var5) {
            Log.e("MediaControllerCompat", "Dead object in sendCommand.", var5);
         }

      }

      public String getPackageName() {
         try {
            return this.mBinder.getPackageName();
         } catch (RemoteException var2) {
            Log.e("MediaControllerCompat", "Dead object in getPackageName.", var2);
            return null;
         }
      }

      public Object getMediaController() {
         return null;
      }
   }

   interface MediaControllerImpl {
      void registerCallback(MediaControllerCompat.Callback var1, Handler var2);

      void unregisterCallback(MediaControllerCompat.Callback var1);

      boolean dispatchMediaButtonEvent(KeyEvent var1);

      MediaControllerCompat.TransportControls getTransportControls();

      PlaybackStateCompat getPlaybackState();

      MediaMetadataCompat getMetadata();

      List getQueue();

      void addQueueItem(MediaDescriptionCompat var1);

      void addQueueItem(MediaDescriptionCompat var1, int var2);

      void removeQueueItem(MediaDescriptionCompat var1);

      CharSequence getQueueTitle();

      Bundle getExtras();

      int getRatingType();

      boolean isCaptioningEnabled();

      int getRepeatMode();

      boolean isShuffleModeEnabled();

      int getShuffleMode();

      long getFlags();

      MediaControllerCompat.PlaybackInfo getPlaybackInfo();

      PendingIntent getSessionActivity();

      void setVolumeTo(int var1, int var2);

      void adjustVolume(int var1, int var2);

      void sendCommand(String var1, Bundle var2, ResultReceiver var3);

      String getPackageName();

      Object getMediaController();
   }

   public static final class PlaybackInfo {
      public static final int PLAYBACK_TYPE_LOCAL = 1;
      public static final int PLAYBACK_TYPE_REMOTE = 2;
      private final int mPlaybackType;
      private final int mAudioStream;
      private final int mVolumeControl;
      private final int mMaxVolume;
      private final int mCurrentVolume;

      PlaybackInfo(int type, int stream, int control, int max, int current) {
         this.mPlaybackType = type;
         this.mAudioStream = stream;
         this.mVolumeControl = control;
         this.mMaxVolume = max;
         this.mCurrentVolume = current;
      }

      public int getPlaybackType() {
         return this.mPlaybackType;
      }

      public int getAudioStream() {
         return this.mAudioStream;
      }

      public int getVolumeControl() {
         return this.mVolumeControl;
      }

      public int getMaxVolume() {
         return this.mMaxVolume;
      }

      public int getCurrentVolume() {
         return this.mCurrentVolume;
      }
   }

   public abstract static class TransportControls {
      public static final String EXTRA_LEGACY_STREAM_TYPE = "android.media.session.extra.LEGACY_STREAM_TYPE";

      public abstract void prepare();

      public abstract void prepareFromMediaId(String var1, Bundle var2);

      public abstract void prepareFromSearch(String var1, Bundle var2);

      public abstract void prepareFromUri(Uri var1, Bundle var2);

      public abstract void play();

      public abstract void playFromMediaId(String var1, Bundle var2);

      public abstract void playFromSearch(String var1, Bundle var2);

      public abstract void playFromUri(Uri var1, Bundle var2);

      public abstract void skipToQueueItem(long var1);

      public abstract void pause();

      public abstract void stop();

      public abstract void seekTo(long var1);

      public abstract void fastForward();

      public abstract void skipToNext();

      public abstract void rewind();

      public abstract void skipToPrevious();

      public abstract void setRating(RatingCompat var1);

      public abstract void setRating(RatingCompat var1, Bundle var2);

      public abstract void setCaptioningEnabled(boolean var1);

      public abstract void setRepeatMode(int var1);

      /** @deprecated */
      @Deprecated
      public abstract void setShuffleModeEnabled(boolean var1);

      public abstract void setShuffleMode(int var1);

      public abstract void sendCustomAction(PlaybackStateCompat.CustomAction var1, Bundle var2);

      public abstract void sendCustomAction(String var1, Bundle var2);
   }

   public abstract static class Callback implements DeathRecipient {
      private final Object mCallbackObj;
      MediaControllerCompat.Callback.MessageHandler mHandler;
      boolean mHasExtraCallback;

      public Callback() {
         if (VERSION.SDK_INT >= 21) {
            this.mCallbackObj = MediaControllerCompatApi21.createCallback(new MediaControllerCompat.Callback.StubApi21(this));
         } else {
            this.mCallbackObj = new MediaControllerCompat.Callback.StubCompat(this);
         }

      }

      public void onSessionDestroyed() {
      }

      public void onSessionEvent(String event, Bundle extras) {
      }

      public void onPlaybackStateChanged(PlaybackStateCompat state) {
      }

      public void onMetadataChanged(MediaMetadataCompat metadata) {
      }

      public void onQueueChanged(List queue) {
      }

      public void onQueueTitleChanged(CharSequence title) {
      }

      public void onExtrasChanged(Bundle extras) {
      }

      public void onAudioInfoChanged(MediaControllerCompat.PlaybackInfo info) {
      }

      public void onCaptioningEnabledChanged(boolean enabled) {
      }

      public void onRepeatModeChanged(int repeatMode) {
      }

      /** @deprecated */
      @Deprecated
      public void onShuffleModeChanged(boolean enabled) {
      }

      public void onShuffleModeChanged(int shuffleMode) {
      }

      public void binderDied() {
         this.onSessionDestroyed();
      }

      void setHandler(Handler handler) {
         if (handler == null) {
            if (this.mHandler != null) {
               this.mHandler.mRegistered = false;
               this.mHandler.removeCallbacksAndMessages((Object)null);
               this.mHandler = null;
            }
         } else {
            this.mHandler = new MediaControllerCompat.Callback.MessageHandler(handler.getLooper());
            this.mHandler.mRegistered = true;
         }

      }

      void postToHandler(int what, Object obj, Bundle data) {
         if (this.mHandler != null) {
            Message msg = this.mHandler.obtainMessage(what, obj);
            msg.setData(data);
            msg.sendToTarget();
         }

      }

      private class MessageHandler extends Handler {
         private static final int MSG_EVENT = 1;
         private static final int MSG_UPDATE_PLAYBACK_STATE = 2;
         private static final int MSG_UPDATE_METADATA = 3;
         private static final int MSG_UPDATE_VOLUME = 4;
         private static final int MSG_UPDATE_QUEUE = 5;
         private static final int MSG_UPDATE_QUEUE_TITLE = 6;
         private static final int MSG_UPDATE_EXTRAS = 7;
         private static final int MSG_DESTROYED = 8;
         private static final int MSG_UPDATE_REPEAT_MODE = 9;
         private static final int MSG_UPDATE_SHUFFLE_MODE_DEPRECATED = 10;
         private static final int MSG_UPDATE_CAPTIONING_ENABLED = 11;
         private static final int MSG_UPDATE_SHUFFLE_MODE = 12;
         boolean mRegistered = false;

         MessageHandler(Looper looper) {
            super(looper);
         }

         public void handleMessage(Message msg) {
            if (this.mRegistered) {
               switch(msg.what) {
               case 1:
                  Callback.this.onSessionEvent((String)msg.obj, msg.getData());
                  break;
               case 2:
                  Callback.this.onPlaybackStateChanged((PlaybackStateCompat)msg.obj);
                  break;
               case 3:
                  Callback.this.onMetadataChanged((MediaMetadataCompat)msg.obj);
                  break;
               case 4:
                  Callback.this.onAudioInfoChanged((MediaControllerCompat.PlaybackInfo)msg.obj);
                  break;
               case 5:
                  Callback.this.onQueueChanged((List)msg.obj);
                  break;
               case 6:
                  Callback.this.onQueueTitleChanged((CharSequence)msg.obj);
                  break;
               case 7:
                  Callback.this.onExtrasChanged((Bundle)msg.obj);
                  break;
               case 8:
                  Callback.this.onSessionDestroyed();
                  break;
               case 9:
                  Callback.this.onRepeatModeChanged(((Integer)msg.obj).intValue());
                  break;
               case 10:
                  Callback.this.onShuffleModeChanged(((Boolean)msg.obj).booleanValue());
                  break;
               case 11:
                  Callback.this.onCaptioningEnabledChanged(((Boolean)msg.obj).booleanValue());
                  break;
               case 12:
                  Callback.this.onShuffleModeChanged(((Integer)msg.obj).intValue());
               }

            }
         }
      }

      private static class StubCompat extends IMediaControllerCallback.Stub {
         private final WeakReference mCallback;

         StubCompat(MediaControllerCompat.Callback callback) {
            this.mCallback = new WeakReference(callback);
         }

         public void onEvent(String event, Bundle extras) throws RemoteException {
            MediaControllerCompat.Callback callback = (MediaControllerCompat.Callback)this.mCallback.get();
            if (callback != null) {
               callback.postToHandler(1, event, extras);
            }

         }

         public void onSessionDestroyed() throws RemoteException {
            MediaControllerCompat.Callback callback = (MediaControllerCompat.Callback)this.mCallback.get();
            if (callback != null) {
               callback.postToHandler(8, (Object)null, (Bundle)null);
            }

         }

         public void onPlaybackStateChanged(PlaybackStateCompat state) throws RemoteException {
            MediaControllerCompat.Callback callback = (MediaControllerCompat.Callback)this.mCallback.get();
            if (callback != null) {
               callback.postToHandler(2, state, (Bundle)null);
            }

         }

         public void onMetadataChanged(MediaMetadataCompat metadata) throws RemoteException {
            MediaControllerCompat.Callback callback = (MediaControllerCompat.Callback)this.mCallback.get();
            if (callback != null) {
               callback.postToHandler(3, metadata, (Bundle)null);
            }

         }

         public void onQueueChanged(List queue) throws RemoteException {
            MediaControllerCompat.Callback callback = (MediaControllerCompat.Callback)this.mCallback.get();
            if (callback != null) {
               callback.postToHandler(5, queue, (Bundle)null);
            }

         }

         public void onQueueTitleChanged(CharSequence title) throws RemoteException {
            MediaControllerCompat.Callback callback = (MediaControllerCompat.Callback)this.mCallback.get();
            if (callback != null) {
               callback.postToHandler(6, title, (Bundle)null);
            }

         }

         public void onCaptioningEnabledChanged(boolean enabled) throws RemoteException {
            MediaControllerCompat.Callback callback = (MediaControllerCompat.Callback)this.mCallback.get();
            if (callback != null) {
               callback.postToHandler(11, enabled, (Bundle)null);
            }

         }

         public void onRepeatModeChanged(int repeatMode) throws RemoteException {
            MediaControllerCompat.Callback callback = (MediaControllerCompat.Callback)this.mCallback.get();
            if (callback != null) {
               callback.postToHandler(9, repeatMode, (Bundle)null);
            }

         }

         public void onShuffleModeChangedDeprecated(boolean enabled) throws RemoteException {
            MediaControllerCompat.Callback callback = (MediaControllerCompat.Callback)this.mCallback.get();
            if (callback != null) {
               callback.postToHandler(10, enabled, (Bundle)null);
            }

         }

         public void onShuffleModeChanged(int shuffleMode) throws RemoteException {
            MediaControllerCompat.Callback callback = (MediaControllerCompat.Callback)this.mCallback.get();
            if (callback != null) {
               callback.postToHandler(12, shuffleMode, (Bundle)null);
            }

         }

         public void onExtrasChanged(Bundle extras) throws RemoteException {
            MediaControllerCompat.Callback callback = (MediaControllerCompat.Callback)this.mCallback.get();
            if (callback != null) {
               callback.postToHandler(7, extras, (Bundle)null);
            }

         }

         public void onVolumeInfoChanged(ParcelableVolumeInfo info) throws RemoteException {
            MediaControllerCompat.Callback callback = (MediaControllerCompat.Callback)this.mCallback.get();
            if (callback != null) {
               MediaControllerCompat.PlaybackInfo pi = null;
               if (info != null) {
                  pi = new MediaControllerCompat.PlaybackInfo(info.volumeType, info.audioStream, info.controlType, info.maxVolume, info.currentVolume);
               }

               callback.postToHandler(4, pi, (Bundle)null);
            }

         }
      }

      private static class StubApi21 implements MediaControllerCompatApi21.Callback {
         private final WeakReference mCallback;

         StubApi21(MediaControllerCompat.Callback callback) {
            this.mCallback = new WeakReference(callback);
         }

         public void onSessionDestroyed() {
            MediaControllerCompat.Callback callback = (MediaControllerCompat.Callback)this.mCallback.get();
            if (callback != null) {
               callback.onSessionDestroyed();
            }

         }

         public void onSessionEvent(String event, Bundle extras) {
            MediaControllerCompat.Callback callback = (MediaControllerCompat.Callback)this.mCallback.get();
            if (callback != null && (!callback.mHasExtraCallback || VERSION.SDK_INT >= 23)) {
               callback.onSessionEvent(event, extras);
            }

         }

         public void onPlaybackStateChanged(Object stateObj) {
            MediaControllerCompat.Callback callback = (MediaControllerCompat.Callback)this.mCallback.get();
            if (callback != null && !callback.mHasExtraCallback) {
               callback.onPlaybackStateChanged(PlaybackStateCompat.fromPlaybackState(stateObj));
            }

         }

         public void onMetadataChanged(Object metadataObj) {
            MediaControllerCompat.Callback callback = (MediaControllerCompat.Callback)this.mCallback.get();
            if (callback != null) {
               callback.onMetadataChanged(MediaMetadataCompat.fromMediaMetadata(metadataObj));
            }

         }

         public void onQueueChanged(List queue) {
            MediaControllerCompat.Callback callback = (MediaControllerCompat.Callback)this.mCallback.get();
            if (callback != null) {
               callback.onQueueChanged(MediaSessionCompat.QueueItem.fromQueueItemList(queue));
            }

         }

         public void onQueueTitleChanged(CharSequence title) {
            MediaControllerCompat.Callback callback = (MediaControllerCompat.Callback)this.mCallback.get();
            if (callback != null) {
               callback.onQueueTitleChanged(title);
            }

         }

         public void onExtrasChanged(Bundle extras) {
            MediaControllerCompat.Callback callback = (MediaControllerCompat.Callback)this.mCallback.get();
            if (callback != null) {
               callback.onExtrasChanged(extras);
            }

         }

         public void onAudioInfoChanged(int type, int stream, int control, int max, int current) {
            MediaControllerCompat.Callback callback = (MediaControllerCompat.Callback)this.mCallback.get();
            if (callback != null) {
               callback.onAudioInfoChanged(new MediaControllerCompat.PlaybackInfo(type, stream, control, max, current));
            }

         }
      }
   }

   private static class MediaControllerExtraData extends ExtraData {
      private final MediaControllerCompat mMediaController;

      MediaControllerExtraData(MediaControllerCompat mediaController) {
         this.mMediaController = mediaController;
      }

      MediaControllerCompat getMediaController() {
         return this.mMediaController;
      }
   }
}
