package android.support.v4.media.session;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.Rating;
import android.media.RemoteControlClient;
import android.media.RemoteControlClient.MetadataEditor;
import android.media.RemoteControlClient.OnMetadataUpdateListener;
import android.media.RemoteControlClient.OnPlaybackPositionUpdateListener;
import android.net.Uri;
import android.os.BadParcelableException;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.os.SystemClock;
import android.os.Build.VERSION;
import android.os.Parcelable.Creator;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.app.BundleCompat;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.RatingCompat;
import android.support.v4.media.VolumeProviderCompat;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.ViewConfiguration;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MediaSessionCompat {
   static final String TAG = "MediaSessionCompat";
   private final MediaSessionCompat.MediaSessionImpl mImpl;
   private final MediaControllerCompat mController;
   private final ArrayList mActiveListeners;
   public static final int FLAG_HANDLES_MEDIA_BUTTONS = 1;
   public static final int FLAG_HANDLES_TRANSPORT_CONTROLS = 2;
   public static final int FLAG_HANDLES_QUEUE_COMMANDS = 4;
   public static final String ACTION_FLAG_AS_INAPPROPRIATE = "android.support.v4.media.session.action.FLAG_AS_INAPPROPRIATE";
   public static final String ACTION_SKIP_AD = "android.support.v4.media.session.action.SKIP_AD";
   public static final String ACTION_FOLLOW = "android.support.v4.media.session.action.FOLLOW";
   public static final String ACTION_UNFOLLOW = "android.support.v4.media.session.action.UNFOLLOW";
   public static final String ARGUMENT_MEDIA_ATTRIBUTE = "android.support.v4.media.session.ARGUMENT_MEDIA_ATTRIBUTE";
   public static final String ARGUMENT_MEDIA_ATTRIBUTE_VALUE = "android.support.v4.media.session.ARGUMENT_MEDIA_ATTRIBUTE_VALUE";
   public static final int MEDIA_ATTRIBUTE_ARTIST = 0;
   public static final int MEDIA_ATTRIBUTE_ALBUM = 1;
   public static final int MEDIA_ATTRIBUTE_PLAYLIST = 2;
   static final String ACTION_PLAY_FROM_URI = "android.support.v4.media.session.action.PLAY_FROM_URI";
   static final String ACTION_PREPARE = "android.support.v4.media.session.action.PREPARE";
   static final String ACTION_PREPARE_FROM_MEDIA_ID = "android.support.v4.media.session.action.PREPARE_FROM_MEDIA_ID";
   static final String ACTION_PREPARE_FROM_SEARCH = "android.support.v4.media.session.action.PREPARE_FROM_SEARCH";
   static final String ACTION_PREPARE_FROM_URI = "android.support.v4.media.session.action.PREPARE_FROM_URI";
   static final String ACTION_SET_CAPTIONING_ENABLED = "android.support.v4.media.session.action.SET_CAPTIONING_ENABLED";
   static final String ACTION_SET_REPEAT_MODE = "android.support.v4.media.session.action.SET_REPEAT_MODE";
   static final String ACTION_SET_SHUFFLE_MODE_ENABLED = "android.support.v4.media.session.action.SET_SHUFFLE_MODE_ENABLED";
   static final String ACTION_SET_SHUFFLE_MODE = "android.support.v4.media.session.action.SET_SHUFFLE_MODE";
   static final String ACTION_SET_RATING = "android.support.v4.media.session.action.SET_RATING";
   static final String ACTION_ARGUMENT_MEDIA_ID = "android.support.v4.media.session.action.ARGUMENT_MEDIA_ID";
   static final String ACTION_ARGUMENT_QUERY = "android.support.v4.media.session.action.ARGUMENT_QUERY";
   static final String ACTION_ARGUMENT_URI = "android.support.v4.media.session.action.ARGUMENT_URI";
   static final String ACTION_ARGUMENT_RATING = "android.support.v4.media.session.action.ARGUMENT_RATING";
   static final String ACTION_ARGUMENT_EXTRAS = "android.support.v4.media.session.action.ARGUMENT_EXTRAS";
   static final String ACTION_ARGUMENT_CAPTIONING_ENABLED = "android.support.v4.media.session.action.ARGUMENT_CAPTIONING_ENABLED";
   static final String ACTION_ARGUMENT_REPEAT_MODE = "android.support.v4.media.session.action.ARGUMENT_REPEAT_MODE";
   static final String ACTION_ARGUMENT_SHUFFLE_MODE_ENABLED = "android.support.v4.media.session.action.ARGUMENT_SHUFFLE_MODE_ENABLED";
   static final String ACTION_ARGUMENT_SHUFFLE_MODE = "android.support.v4.media.session.action.ARGUMENT_SHUFFLE_MODE";
   static final String EXTRA_BINDER = "android.support.v4.media.session.EXTRA_BINDER";
   private static final int MAX_BITMAP_SIZE_IN_DP = 320;
   static int sMaxBitmapSize;

   public MediaSessionCompat(Context context, String tag) {
      this(context, tag, (ComponentName)null, (PendingIntent)null);
   }

   public MediaSessionCompat(Context context, String tag, ComponentName mbrComponent, PendingIntent mbrIntent) {
      this.mActiveListeners = new ArrayList();
      if (context == null) {
         throw new IllegalArgumentException("context must not be null");
      } else if (TextUtils.isEmpty(tag)) {
         throw new IllegalArgumentException("tag must not be null or empty");
      } else {
         if (mbrComponent == null) {
            mbrComponent = MediaButtonReceiver.getMediaButtonReceiverComponent(context);
            if (mbrComponent == null) {
               Log.w("MediaSessionCompat", "Couldn't find a unique registered media button receiver in the given context.");
            }
         }

         if (mbrComponent != null && mbrIntent == null) {
            Intent mediaButtonIntent = new Intent("android.intent.action.MEDIA_BUTTON");
            mediaButtonIntent.setComponent(mbrComponent);
            mbrIntent = PendingIntent.getBroadcast(context, 0, mediaButtonIntent, 0);
         }

         if (VERSION.SDK_INT >= 21) {
            this.mImpl = new MediaSessionCompat.MediaSessionImplApi21(context, tag);
            this.setCallback(new MediaSessionCompat.Callback() {
            });
            this.mImpl.setMediaButtonReceiver(mbrIntent);
         } else if (VERSION.SDK_INT >= 19) {
            this.mImpl = new MediaSessionCompat.MediaSessionImplApi19(context, tag, mbrComponent, mbrIntent);
         } else if (VERSION.SDK_INT >= 18) {
            this.mImpl = new MediaSessionCompat.MediaSessionImplApi18(context, tag, mbrComponent, mbrIntent);
         } else {
            this.mImpl = new MediaSessionCompat.MediaSessionImplBase(context, tag, mbrComponent, mbrIntent);
         }

         this.mController = new MediaControllerCompat(context, this);
         if (sMaxBitmapSize == 0) {
            sMaxBitmapSize = (int)TypedValue.applyDimension(1, 320.0F, context.getResources().getDisplayMetrics());
         }

      }
   }

   private MediaSessionCompat(Context context, MediaSessionCompat.MediaSessionImpl impl) {
      this.mActiveListeners = new ArrayList();
      this.mImpl = impl;
      if (VERSION.SDK_INT >= 21 && !MediaSessionCompatApi21.hasCallback(impl.getMediaSession())) {
         this.setCallback(new MediaSessionCompat.Callback() {
         });
      }

      this.mController = new MediaControllerCompat(context, this);
   }

   public void setCallback(MediaSessionCompat.Callback callback) {
      this.setCallback(callback, (Handler)null);
   }

   public void setCallback(MediaSessionCompat.Callback callback, Handler handler) {
      this.mImpl.setCallback(callback, handler != null ? handler : new Handler());
   }

   public void setSessionActivity(PendingIntent pi) {
      this.mImpl.setSessionActivity(pi);
   }

   public void setMediaButtonReceiver(PendingIntent mbr) {
      this.mImpl.setMediaButtonReceiver(mbr);
   }

   public void setFlags(int flags) {
      this.mImpl.setFlags(flags);
   }

   public void setPlaybackToLocal(int stream) {
      this.mImpl.setPlaybackToLocal(stream);
   }

   public void setPlaybackToRemote(VolumeProviderCompat volumeProvider) {
      if (volumeProvider == null) {
         throw new IllegalArgumentException("volumeProvider may not be null!");
      } else {
         this.mImpl.setPlaybackToRemote(volumeProvider);
      }
   }

   public void setActive(boolean active) {
      this.mImpl.setActive(active);
      Iterator var2 = this.mActiveListeners.iterator();

      while(var2.hasNext()) {
         MediaSessionCompat.OnActiveChangeListener listener = (MediaSessionCompat.OnActiveChangeListener)var2.next();
         listener.onActiveChanged();
      }

   }

   public boolean isActive() {
      return this.mImpl.isActive();
   }

   public void sendSessionEvent(String event, Bundle extras) {
      if (TextUtils.isEmpty(event)) {
         throw new IllegalArgumentException("event cannot be null or empty");
      } else {
         this.mImpl.sendSessionEvent(event, extras);
      }
   }

   public void release() {
      this.mImpl.release();
   }

   public MediaSessionCompat.Token getSessionToken() {
      return this.mImpl.getSessionToken();
   }

   public MediaControllerCompat getController() {
      return this.mController;
   }

   public void setPlaybackState(PlaybackStateCompat state) {
      this.mImpl.setPlaybackState(state);
   }

   public void setMetadata(MediaMetadataCompat metadata) {
      this.mImpl.setMetadata(metadata);
   }

   public void setQueue(List queue) {
      this.mImpl.setQueue(queue);
   }

   public void setQueueTitle(CharSequence title) {
      this.mImpl.setQueueTitle(title);
   }

   public void setRatingType(int type) {
      this.mImpl.setRatingType(type);
   }

   public void setCaptioningEnabled(boolean enabled) {
      this.mImpl.setCaptioningEnabled(enabled);
   }

   public void setRepeatMode(int repeatMode) {
      this.mImpl.setRepeatMode(repeatMode);
   }

   /** @deprecated */
   @Deprecated
   public void setShuffleModeEnabled(boolean enabled) {
      this.mImpl.setShuffleModeEnabled(enabled);
   }

   public void setShuffleMode(int shuffleMode) {
      this.mImpl.setShuffleMode(shuffleMode);
   }

   public void setExtras(Bundle extras) {
      this.mImpl.setExtras(extras);
   }

   public Object getMediaSession() {
      return this.mImpl.getMediaSession();
   }

   public Object getRemoteControlClient() {
      return this.mImpl.getRemoteControlClient();
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   public String getCallingPackage() {
      return this.mImpl.getCallingPackage();
   }

   public void addOnActiveChangeListener(MediaSessionCompat.OnActiveChangeListener listener) {
      if (listener == null) {
         throw new IllegalArgumentException("Listener may not be null");
      } else {
         this.mActiveListeners.add(listener);
      }
   }

   public void removeOnActiveChangeListener(MediaSessionCompat.OnActiveChangeListener listener) {
      if (listener == null) {
         throw new IllegalArgumentException("Listener may not be null");
      } else {
         this.mActiveListeners.remove(listener);
      }
   }

   public static MediaSessionCompat fromMediaSession(Context context, Object mediaSession) {
      return context != null && mediaSession != null && VERSION.SDK_INT >= 21 ? new MediaSessionCompat(context, new MediaSessionCompat.MediaSessionImplApi21(mediaSession)) : null;
   }

   private static PlaybackStateCompat getStateWithUpdatedPosition(PlaybackStateCompat state, MediaMetadataCompat metadata) {
      if (state != null && state.getPosition() != -1L) {
         if (state.getState() == 3 || state.getState() == 4 || state.getState() == 5) {
            long updateTime = state.getLastPositionUpdateTime();
            if (updateTime > 0L) {
               long currentTime = SystemClock.elapsedRealtime();
               long position = (long)(state.getPlaybackSpeed() * (float)(currentTime - updateTime)) + state.getPosition();
               long duration = -1L;
               if (metadata != null && metadata.containsKey("android.media.metadata.DURATION")) {
                  duration = metadata.getLong("android.media.metadata.DURATION");
               }

               if (duration >= 0L && position > duration) {
                  position = duration;
               } else if (position < 0L) {
                  position = 0L;
               }

               return (new PlaybackStateCompat.Builder(state)).setState(state.getState(), position, state.getPlaybackSpeed(), currentTime).build();
            }
         }

         return state;
      } else {
         return state;
      }
   }

   @RequiresApi(21)
   static class MediaSessionImplApi21 implements MediaSessionCompat.MediaSessionImpl {
      private final Object mSessionObj;
      private final MediaSessionCompat.Token mToken;
      private boolean mDestroyed = false;
      private final RemoteCallbackList mExtraControllerCallbacks = new RemoteCallbackList();
      private PlaybackStateCompat mPlaybackState;
      private List mQueue;
      private MediaMetadataCompat mMetadata;
      int mRatingType;
      boolean mCaptioningEnabled;
      int mRepeatMode;
      boolean mShuffleModeEnabled;
      int mShuffleMode;

      public MediaSessionImplApi21(Context context, String tag) {
         this.mSessionObj = MediaSessionCompatApi21.createSession(context, tag);
         this.mToken = new MediaSessionCompat.Token(MediaSessionCompatApi21.getSessionToken(this.mSessionObj), new MediaSessionCompat.MediaSessionImplApi21.ExtraSession());
      }

      public MediaSessionImplApi21(Object mediaSession) {
         this.mSessionObj = MediaSessionCompatApi21.verifySession(mediaSession);
         this.mToken = new MediaSessionCompat.Token(MediaSessionCompatApi21.getSessionToken(this.mSessionObj), new MediaSessionCompat.MediaSessionImplApi21.ExtraSession());
      }

      public void setCallback(MediaSessionCompat.Callback callback, Handler handler) {
         MediaSessionCompatApi21.setCallback(this.mSessionObj, callback == null ? null : callback.mCallbackObj, handler);
         if (callback != null) {
            callback.setSessionImpl(this, handler);
         }

      }

      public void setFlags(int flags) {
         MediaSessionCompatApi21.setFlags(this.mSessionObj, flags);
      }

      public void setPlaybackToLocal(int stream) {
         MediaSessionCompatApi21.setPlaybackToLocal(this.mSessionObj, stream);
      }

      public void setPlaybackToRemote(VolumeProviderCompat volumeProvider) {
         MediaSessionCompatApi21.setPlaybackToRemote(this.mSessionObj, volumeProvider.getVolumeProvider());
      }

      public void setActive(boolean active) {
         MediaSessionCompatApi21.setActive(this.mSessionObj, active);
      }

      public boolean isActive() {
         return MediaSessionCompatApi21.isActive(this.mSessionObj);
      }

      public void sendSessionEvent(String event, Bundle extras) {
         if (VERSION.SDK_INT < 23) {
            int size = this.mExtraControllerCallbacks.beginBroadcast();

            for(int i = size - 1; i >= 0; --i) {
               IMediaControllerCallback cb = (IMediaControllerCallback)this.mExtraControllerCallbacks.getBroadcastItem(i);

               try {
                  cb.onEvent(event, extras);
               } catch (RemoteException var7) {
                  ;
               }
            }

            this.mExtraControllerCallbacks.finishBroadcast();
         }

         MediaSessionCompatApi21.sendSessionEvent(this.mSessionObj, event, extras);
      }

      public void release() {
         this.mDestroyed = true;
         MediaSessionCompatApi21.release(this.mSessionObj);
      }

      public MediaSessionCompat.Token getSessionToken() {
         return this.mToken;
      }

      public void setPlaybackState(PlaybackStateCompat state) {
         this.mPlaybackState = state;
         int size = this.mExtraControllerCallbacks.beginBroadcast();

         for(int i = size - 1; i >= 0; --i) {
            IMediaControllerCallback cb = (IMediaControllerCallback)this.mExtraControllerCallbacks.getBroadcastItem(i);

            try {
               cb.onPlaybackStateChanged(state);
            } catch (RemoteException var6) {
               ;
            }
         }

         this.mExtraControllerCallbacks.finishBroadcast();
         MediaSessionCompatApi21.setPlaybackState(this.mSessionObj, state == null ? null : state.getPlaybackState());
      }

      public PlaybackStateCompat getPlaybackState() {
         return this.mPlaybackState;
      }

      public void setMetadata(MediaMetadataCompat metadata) {
         this.mMetadata = metadata;
         MediaSessionCompatApi21.setMetadata(this.mSessionObj, metadata == null ? null : metadata.getMediaMetadata());
      }

      public void setSessionActivity(PendingIntent pi) {
         MediaSessionCompatApi21.setSessionActivity(this.mSessionObj, pi);
      }

      public void setMediaButtonReceiver(PendingIntent mbr) {
         MediaSessionCompatApi21.setMediaButtonReceiver(this.mSessionObj, mbr);
      }

      public void setQueue(List queue) {
         this.mQueue = queue;
         List queueObjs = null;
         if (queue != null) {
            queueObjs = new ArrayList();
            Iterator var3 = queue.iterator();

            while(var3.hasNext()) {
               MediaSessionCompat.QueueItem item = (MediaSessionCompat.QueueItem)var3.next();
               queueObjs.add(item.getQueueItem());
            }
         }

         MediaSessionCompatApi21.setQueue(this.mSessionObj, queueObjs);
      }

      public void setQueueTitle(CharSequence title) {
         MediaSessionCompatApi21.setQueueTitle(this.mSessionObj, title);
      }

      public void setRatingType(int type) {
         if (VERSION.SDK_INT < 22) {
            this.mRatingType = type;
         } else {
            MediaSessionCompatApi22.setRatingType(this.mSessionObj, type);
         }

      }

      public void setCaptioningEnabled(boolean enabled) {
         if (this.mCaptioningEnabled != enabled) {
            this.mCaptioningEnabled = enabled;
            int size = this.mExtraControllerCallbacks.beginBroadcast();

            for(int i = size - 1; i >= 0; --i) {
               IMediaControllerCallback cb = (IMediaControllerCallback)this.mExtraControllerCallbacks.getBroadcastItem(i);

               try {
                  cb.onCaptioningEnabledChanged(enabled);
               } catch (RemoteException var6) {
                  ;
               }
            }

            this.mExtraControllerCallbacks.finishBroadcast();
         }

      }

      public void setRepeatMode(int repeatMode) {
         if (this.mRepeatMode != repeatMode) {
            this.mRepeatMode = repeatMode;
            int size = this.mExtraControllerCallbacks.beginBroadcast();

            for(int i = size - 1; i >= 0; --i) {
               IMediaControllerCallback cb = (IMediaControllerCallback)this.mExtraControllerCallbacks.getBroadcastItem(i);

               try {
                  cb.onRepeatModeChanged(repeatMode);
               } catch (RemoteException var6) {
                  ;
               }
            }

            this.mExtraControllerCallbacks.finishBroadcast();
         }

      }

      public void setShuffleModeEnabled(boolean enabled) {
         if (this.mShuffleModeEnabled != enabled) {
            this.mShuffleModeEnabled = enabled;
            int size = this.mExtraControllerCallbacks.beginBroadcast();

            for(int i = size - 1; i >= 0; --i) {
               IMediaControllerCallback cb = (IMediaControllerCallback)this.mExtraControllerCallbacks.getBroadcastItem(i);

               try {
                  cb.onShuffleModeChangedDeprecated(enabled);
               } catch (RemoteException var6) {
                  ;
               }
            }

            this.mExtraControllerCallbacks.finishBroadcast();
         }

      }

      public void setShuffleMode(int shuffleMode) {
         if (this.mShuffleMode != shuffleMode) {
            this.mShuffleMode = shuffleMode;
            int size = this.mExtraControllerCallbacks.beginBroadcast();

            for(int i = size - 1; i >= 0; --i) {
               IMediaControllerCallback cb = (IMediaControllerCallback)this.mExtraControllerCallbacks.getBroadcastItem(i);

               try {
                  cb.onShuffleModeChanged(shuffleMode);
               } catch (RemoteException var6) {
                  ;
               }
            }

            this.mExtraControllerCallbacks.finishBroadcast();
         }

      }

      public void setExtras(Bundle extras) {
         MediaSessionCompatApi21.setExtras(this.mSessionObj, extras);
      }

      public Object getMediaSession() {
         return this.mSessionObj;
      }

      public Object getRemoteControlClient() {
         return null;
      }

      public String getCallingPackage() {
         return VERSION.SDK_INT < 24 ? null : MediaSessionCompatApi24.getCallingPackage(this.mSessionObj);
      }

      class ExtraSession extends IMediaSession.Stub {
         public void sendCommand(String command, Bundle args, MediaSessionCompat.ResultReceiverWrapper cb) {
            throw new AssertionError();
         }

         public boolean sendMediaButton(KeyEvent mediaButton) {
            throw new AssertionError();
         }

         public void registerCallbackListener(IMediaControllerCallback cb) {
            if (!MediaSessionImplApi21.this.mDestroyed) {
               MediaSessionImplApi21.this.mExtraControllerCallbacks.register(cb);
            }

         }

         public void unregisterCallbackListener(IMediaControllerCallback cb) {
            MediaSessionImplApi21.this.mExtraControllerCallbacks.unregister(cb);
         }

         public String getPackageName() {
            throw new AssertionError();
         }

         public String getTag() {
            throw new AssertionError();
         }

         public PendingIntent getLaunchPendingIntent() {
            throw new AssertionError();
         }

         public long getFlags() {
            throw new AssertionError();
         }

         public ParcelableVolumeInfo getVolumeAttributes() {
            throw new AssertionError();
         }

         public void adjustVolume(int direction, int flags, String packageName) {
            throw new AssertionError();
         }

         public void setVolumeTo(int value, int flags, String packageName) {
            throw new AssertionError();
         }

         public void prepare() throws RemoteException {
            throw new AssertionError();
         }

         public void prepareFromMediaId(String mediaId, Bundle extras) throws RemoteException {
            throw new AssertionError();
         }

         public void prepareFromSearch(String query, Bundle extras) throws RemoteException {
            throw new AssertionError();
         }

         public void prepareFromUri(Uri uri, Bundle extras) throws RemoteException {
            throw new AssertionError();
         }

         public void play() throws RemoteException {
            throw new AssertionError();
         }

         public void playFromMediaId(String mediaId, Bundle extras) throws RemoteException {
            throw new AssertionError();
         }

         public void playFromSearch(String query, Bundle extras) throws RemoteException {
            throw new AssertionError();
         }

         public void playFromUri(Uri uri, Bundle extras) throws RemoteException {
            throw new AssertionError();
         }

         public void skipToQueueItem(long id) {
            throw new AssertionError();
         }

         public void pause() throws RemoteException {
            throw new AssertionError();
         }

         public void stop() throws RemoteException {
            throw new AssertionError();
         }

         public void next() throws RemoteException {
            throw new AssertionError();
         }

         public void previous() throws RemoteException {
            throw new AssertionError();
         }

         public void fastForward() throws RemoteException {
            throw new AssertionError();
         }

         public void rewind() throws RemoteException {
            throw new AssertionError();
         }

         public void seekTo(long pos) throws RemoteException {
            throw new AssertionError();
         }

         public void rate(RatingCompat rating) throws RemoteException {
            throw new AssertionError();
         }

         public void rateWithExtras(RatingCompat rating, Bundle extras) throws RemoteException {
            throw new AssertionError();
         }

         public void setCaptioningEnabled(boolean enabled) throws RemoteException {
            throw new AssertionError();
         }

         public void setRepeatMode(int repeatMode) throws RemoteException {
            throw new AssertionError();
         }

         public void setShuffleModeEnabledDeprecated(boolean enabled) throws RemoteException {
            throw new AssertionError();
         }

         public void setShuffleMode(int shuffleMode) throws RemoteException {
            throw new AssertionError();
         }

         public void sendCustomAction(String action, Bundle args) throws RemoteException {
            throw new AssertionError();
         }

         public MediaMetadataCompat getMetadata() {
            throw new AssertionError();
         }

         public PlaybackStateCompat getPlaybackState() {
            return MediaSessionCompat.getStateWithUpdatedPosition(MediaSessionImplApi21.this.mPlaybackState, MediaSessionImplApi21.this.mMetadata);
         }

         public List getQueue() {
            return null;
         }

         public void addQueueItem(MediaDescriptionCompat descriptionCompat) {
            throw new AssertionError();
         }

         public void addQueueItemAt(MediaDescriptionCompat descriptionCompat, int index) {
            throw new AssertionError();
         }

         public void removeQueueItem(MediaDescriptionCompat description) {
            throw new AssertionError();
         }

         public void removeQueueItemAt(int index) {
            throw new AssertionError();
         }

         public CharSequence getQueueTitle() {
            throw new AssertionError();
         }

         public Bundle getExtras() {
            throw new AssertionError();
         }

         public int getRatingType() {
            return MediaSessionImplApi21.this.mRatingType;
         }

         public boolean isCaptioningEnabled() {
            return MediaSessionImplApi21.this.mCaptioningEnabled;
         }

         public int getRepeatMode() {
            return MediaSessionImplApi21.this.mRepeatMode;
         }

         public boolean isShuffleModeEnabledDeprecated() {
            return MediaSessionImplApi21.this.mShuffleModeEnabled;
         }

         public int getShuffleMode() {
            return MediaSessionImplApi21.this.mShuffleMode;
         }

         public boolean isTransportControlEnabled() {
            throw new AssertionError();
         }
      }
   }

   @RequiresApi(19)
   static class MediaSessionImplApi19 extends MediaSessionCompat.MediaSessionImplApi18 {
      MediaSessionImplApi19(Context context, String tag, ComponentName mbrComponent, PendingIntent mbrIntent) {
         super(context, tag, mbrComponent, mbrIntent);
      }

      public void setCallback(MediaSessionCompat.Callback callback, Handler handler) {
         super.setCallback(callback, handler);
         if (callback == null) {
            this.mRcc.setMetadataUpdateListener((OnMetadataUpdateListener)null);
         } else {
            OnMetadataUpdateListener listener = new OnMetadataUpdateListener() {
               public void onMetadataUpdate(int key, Object newValue) {
                  if (key == 268435457 && newValue instanceof Rating) {
                     MediaSessionImplApi19.this.postToHandler(19, RatingCompat.fromRating(newValue));
                  }

               }
            };
            this.mRcc.setMetadataUpdateListener(listener);
         }

      }

      int getRccTransportControlFlagsFromActions(long actions) {
         int transportControlFlags = super.getRccTransportControlFlagsFromActions(actions);
         if ((actions & 128L) != 0L) {
            transportControlFlags |= 512;
         }

         return transportControlFlags;
      }

      MetadataEditor buildRccMetadata(Bundle metadata) {
         MetadataEditor editor = super.buildRccMetadata(metadata);
         long actions = this.mState == null ? 0L : this.mState.getActions();
         if ((actions & 128L) != 0L) {
            editor.addEditableKey(268435457);
         }

         if (metadata == null) {
            return editor;
         } else {
            if (metadata.containsKey("android.media.metadata.YEAR")) {
               editor.putLong(8, metadata.getLong("android.media.metadata.YEAR"));
            }

            if (metadata.containsKey("android.media.metadata.RATING")) {
               editor.putObject(101, metadata.getParcelable("android.media.metadata.RATING"));
            }

            if (metadata.containsKey("android.media.metadata.USER_RATING")) {
               editor.putObject(268435457, metadata.getParcelable("android.media.metadata.USER_RATING"));
            }

            return editor;
         }
      }
   }

   @RequiresApi(18)
   static class MediaSessionImplApi18 extends MediaSessionCompat.MediaSessionImplBase {
      private static boolean sIsMbrPendingIntentSupported = true;

      MediaSessionImplApi18(Context context, String tag, ComponentName mbrComponent, PendingIntent mbrIntent) {
         super(context, tag, mbrComponent, mbrIntent);
      }

      public void setCallback(MediaSessionCompat.Callback callback, Handler handler) {
         super.setCallback(callback, handler);
         if (callback == null) {
            this.mRcc.setPlaybackPositionUpdateListener((OnPlaybackPositionUpdateListener)null);
         } else {
            OnPlaybackPositionUpdateListener listener = new OnPlaybackPositionUpdateListener() {
               public void onPlaybackPositionUpdate(long newPositionMs) {
                  MediaSessionImplApi18.this.postToHandler(18, newPositionMs);
               }
            };
            this.mRcc.setPlaybackPositionUpdateListener(listener);
         }

      }

      void setRccState(PlaybackStateCompat state) {
         long position = state.getPosition();
         float speed = state.getPlaybackSpeed();
         long updateTime = state.getLastPositionUpdateTime();
         long currTime = SystemClock.elapsedRealtime();
         if (state.getState() == 3 && position > 0L) {
            long diff = 0L;
            if (updateTime > 0L) {
               diff = currTime - updateTime;
               if (speed > 0.0F && speed != 1.0F) {
                  diff = (long)((float)diff * speed);
               }
            }

            position += diff;
         }

         this.mRcc.setPlaybackState(this.getRccStateFromState(state.getState()), position, speed);
      }

      int getRccTransportControlFlagsFromActions(long actions) {
         int transportControlFlags = super.getRccTransportControlFlagsFromActions(actions);
         if ((actions & 256L) != 0L) {
            transportControlFlags |= 256;
         }

         return transportControlFlags;
      }

      void registerMediaButtonEventReceiver(PendingIntent mbrIntent, ComponentName mbrComponent) {
         if (sIsMbrPendingIntentSupported) {
            try {
               this.mAudioManager.registerMediaButtonEventReceiver(mbrIntent);
            } catch (NullPointerException var4) {
               Log.w("MediaSessionCompat", "Unable to register media button event receiver with PendingIntent, falling back to ComponentName.");
               sIsMbrPendingIntentSupported = false;
            }
         }

         if (!sIsMbrPendingIntentSupported) {
            super.registerMediaButtonEventReceiver(mbrIntent, mbrComponent);
         }

      }

      void unregisterMediaButtonEventReceiver(PendingIntent mbrIntent, ComponentName mbrComponent) {
         if (sIsMbrPendingIntentSupported) {
            this.mAudioManager.unregisterMediaButtonEventReceiver(mbrIntent);
         } else {
            super.unregisterMediaButtonEventReceiver(mbrIntent, mbrComponent);
         }

      }
   }

   static class MediaSessionImplBase implements MediaSessionCompat.MediaSessionImpl {
      static final int RCC_PLAYSTATE_NONE = 0;
      private final Context mContext;
      private final ComponentName mMediaButtonReceiverComponentName;
      private final PendingIntent mMediaButtonReceiverIntent;
      private final MediaSessionCompat.MediaSessionImplBase.MediaSessionStub mStub;
      private final MediaSessionCompat.Token mToken;
      final String mPackageName;
      final String mTag;
      final AudioManager mAudioManager;
      final RemoteControlClient mRcc;
      final Object mLock = new Object();
      final RemoteCallbackList mControllerCallbacks = new RemoteCallbackList();
      private MediaSessionCompat.MediaSessionImplBase.MessageHandler mHandler;
      boolean mDestroyed = false;
      boolean mIsActive = false;
      private boolean mIsMbrRegistered = false;
      private boolean mIsRccRegistered = false;
      volatile MediaSessionCompat.Callback mCallback;
      int mFlags;
      MediaMetadataCompat mMetadata;
      PlaybackStateCompat mState;
      PendingIntent mSessionActivity;
      List mQueue;
      CharSequence mQueueTitle;
      int mRatingType;
      boolean mCaptioningEnabled;
      int mRepeatMode;
      int mShuffleMode;
      boolean mShuffleModeEnabled;
      Bundle mExtras;
      int mVolumeType;
      int mLocalStream;
      VolumeProviderCompat mVolumeProvider;
      private VolumeProviderCompat.Callback mVolumeCallback = new VolumeProviderCompat.Callback() {
         public void onVolumeChanged(VolumeProviderCompat volumeProvider) {
            if (MediaSessionImplBase.this.mVolumeProvider == volumeProvider) {
               ParcelableVolumeInfo info = new ParcelableVolumeInfo(MediaSessionImplBase.this.mVolumeType, MediaSessionImplBase.this.mLocalStream, volumeProvider.getVolumeControl(), volumeProvider.getMaxVolume(), volumeProvider.getCurrentVolume());
               MediaSessionImplBase.this.sendVolumeInfoChanged(info);
            }
         }
      };

      public MediaSessionImplBase(Context context, String tag, ComponentName mbrComponent, PendingIntent mbrIntent) {
         if (mbrComponent == null) {
            throw new IllegalArgumentException("MediaButtonReceiver component may not be null.");
         } else {
            this.mContext = context;
            this.mPackageName = context.getPackageName();
            this.mAudioManager = (AudioManager)context.getSystemService("audio");
            this.mTag = tag;
            this.mMediaButtonReceiverComponentName = mbrComponent;
            this.mMediaButtonReceiverIntent = mbrIntent;
            this.mStub = new MediaSessionCompat.MediaSessionImplBase.MediaSessionStub();
            this.mToken = new MediaSessionCompat.Token(this.mStub);
            this.mRatingType = 0;
            this.mVolumeType = 1;
            this.mLocalStream = 3;
            this.mRcc = new RemoteControlClient(mbrIntent);
         }
      }

      public void setCallback(MediaSessionCompat.Callback callback, Handler handler) {
         this.mCallback = callback;
         if (callback != null) {
            if (handler == null) {
               handler = new Handler();
            }

            Object var3 = this.mLock;
            synchronized(this.mLock) {
               if (this.mHandler != null) {
                  this.mHandler.removeCallbacksAndMessages((Object)null);
               }

               this.mHandler = new MediaSessionCompat.MediaSessionImplBase.MessageHandler(handler.getLooper());
               this.mCallback.setSessionImpl(this, handler);
            }
         }

      }

      void postToHandler(int what) {
         this.postToHandler(what, (Object)null);
      }

      void postToHandler(int what, int arg1) {
         this.postToHandler(what, (Object)null, arg1);
      }

      void postToHandler(int what, Object obj) {
         this.postToHandler(what, obj, (Bundle)null);
      }

      void postToHandler(int what, Object obj, int arg1) {
         Object var4 = this.mLock;
         synchronized(this.mLock) {
            if (this.mHandler != null) {
               this.mHandler.post(what, obj, arg1);
            }

         }
      }

      void postToHandler(int what, Object obj, Bundle extras) {
         Object var4 = this.mLock;
         synchronized(this.mLock) {
            if (this.mHandler != null) {
               this.mHandler.post(what, obj, extras);
            }

         }
      }

      public void setFlags(int flags) {
         Object var2 = this.mLock;
         synchronized(this.mLock) {
            this.mFlags = flags;
         }

         this.update();
      }

      public void setPlaybackToLocal(int stream) {
         if (this.mVolumeProvider != null) {
            this.mVolumeProvider.setCallback((VolumeProviderCompat.Callback)null);
         }

         this.mVolumeType = 1;
         ParcelableVolumeInfo info = new ParcelableVolumeInfo(this.mVolumeType, this.mLocalStream, 2, this.mAudioManager.getStreamMaxVolume(this.mLocalStream), this.mAudioManager.getStreamVolume(this.mLocalStream));
         this.sendVolumeInfoChanged(info);
      }

      public void setPlaybackToRemote(VolumeProviderCompat volumeProvider) {
         if (volumeProvider == null) {
            throw new IllegalArgumentException("volumeProvider may not be null");
         } else {
            if (this.mVolumeProvider != null) {
               this.mVolumeProvider.setCallback((VolumeProviderCompat.Callback)null);
            }

            this.mVolumeType = 2;
            this.mVolumeProvider = volumeProvider;
            ParcelableVolumeInfo info = new ParcelableVolumeInfo(this.mVolumeType, this.mLocalStream, this.mVolumeProvider.getVolumeControl(), this.mVolumeProvider.getMaxVolume(), this.mVolumeProvider.getCurrentVolume());
            this.sendVolumeInfoChanged(info);
            volumeProvider.setCallback(this.mVolumeCallback);
         }
      }

      public void setActive(boolean active) {
         if (active != this.mIsActive) {
            this.mIsActive = active;
            if (this.update()) {
               this.setMetadata(this.mMetadata);
               this.setPlaybackState(this.mState);
            }

         }
      }

      public boolean isActive() {
         return this.mIsActive;
      }

      public void sendSessionEvent(String event, Bundle extras) {
         this.sendEvent(event, extras);
      }

      public void release() {
         this.mIsActive = false;
         this.mDestroyed = true;
         this.update();
         this.sendSessionDestroyed();
      }

      public MediaSessionCompat.Token getSessionToken() {
         return this.mToken;
      }

      public void setPlaybackState(PlaybackStateCompat state) {
         Object var2 = this.mLock;
         synchronized(this.mLock) {
            this.mState = state;
         }

         this.sendState(state);
         if (this.mIsActive) {
            if (state == null) {
               this.mRcc.setPlaybackState(0);
               this.mRcc.setTransportControlFlags(0);
            } else {
               this.setRccState(state);
               this.mRcc.setTransportControlFlags(this.getRccTransportControlFlagsFromActions(state.getActions()));
            }

         }
      }

      public PlaybackStateCompat getPlaybackState() {
         Object var1 = this.mLock;
         synchronized(this.mLock) {
            return this.mState;
         }
      }

      void setRccState(PlaybackStateCompat state) {
         this.mRcc.setPlaybackState(this.getRccStateFromState(state.getState()));
      }

      int getRccStateFromState(int state) {
         switch(state) {
         case 0:
            return 0;
         case 1:
            return 1;
         case 2:
            return 2;
         case 3:
            return 3;
         case 4:
            return 4;
         case 5:
            return 5;
         case 6:
         case 8:
            return 8;
         case 7:
            return 9;
         case 9:
            return 7;
         case 10:
         case 11:
            return 6;
         default:
            return -1;
         }
      }

      int getRccTransportControlFlagsFromActions(long actions) {
         int transportControlFlags = 0;
         if ((actions & 1L) != 0L) {
            transportControlFlags |= 32;
         }

         if ((actions & 2L) != 0L) {
            transportControlFlags |= 16;
         }

         if ((actions & 4L) != 0L) {
            transportControlFlags |= 4;
         }

         if ((actions & 8L) != 0L) {
            transportControlFlags |= 2;
         }

         if ((actions & 16L) != 0L) {
            transportControlFlags |= 1;
         }

         if ((actions & 32L) != 0L) {
            transportControlFlags |= 128;
         }

         if ((actions & 64L) != 0L) {
            transportControlFlags |= 64;
         }

         if ((actions & 512L) != 0L) {
            transportControlFlags |= 8;
         }

         return transportControlFlags;
      }

      public void setMetadata(MediaMetadataCompat metadata) {
         if (metadata != null) {
            metadata = (new MediaMetadataCompat.Builder(metadata, MediaSessionCompat.sMaxBitmapSize)).build();
         }

         Object var2 = this.mLock;
         synchronized(this.mLock) {
            this.mMetadata = metadata;
         }

         this.sendMetadata(metadata);
         if (this.mIsActive) {
            MetadataEditor editor = this.buildRccMetadata(metadata == null ? null : metadata.getBundle());
            editor.apply();
         }
      }

      MetadataEditor buildRccMetadata(Bundle metadata) {
         MetadataEditor editor = this.mRcc.editMetadata(true);
         if (metadata == null) {
            return editor;
         } else {
            Bitmap art;
            if (metadata.containsKey("android.media.metadata.ART")) {
               art = (Bitmap)metadata.getParcelable("android.media.metadata.ART");
               if (art != null) {
                  art = art.copy(art.getConfig(), false);
               }

               editor.putBitmap(100, art);
            } else if (metadata.containsKey("android.media.metadata.ALBUM_ART")) {
               art = (Bitmap)metadata.getParcelable("android.media.metadata.ALBUM_ART");
               if (art != null) {
                  art = art.copy(art.getConfig(), false);
               }

               editor.putBitmap(100, art);
            }

            if (metadata.containsKey("android.media.metadata.ALBUM")) {
               editor.putString(1, metadata.getString("android.media.metadata.ALBUM"));
            }

            if (metadata.containsKey("android.media.metadata.ALBUM_ARTIST")) {
               editor.putString(13, metadata.getString("android.media.metadata.ALBUM_ARTIST"));
            }

            if (metadata.containsKey("android.media.metadata.ARTIST")) {
               editor.putString(2, metadata.getString("android.media.metadata.ARTIST"));
            }

            if (metadata.containsKey("android.media.metadata.AUTHOR")) {
               editor.putString(3, metadata.getString("android.media.metadata.AUTHOR"));
            }

            if (metadata.containsKey("android.media.metadata.COMPILATION")) {
               editor.putString(15, metadata.getString("android.media.metadata.COMPILATION"));
            }

            if (metadata.containsKey("android.media.metadata.COMPOSER")) {
               editor.putString(4, metadata.getString("android.media.metadata.COMPOSER"));
            }

            if (metadata.containsKey("android.media.metadata.DATE")) {
               editor.putString(5, metadata.getString("android.media.metadata.DATE"));
            }

            if (metadata.containsKey("android.media.metadata.DISC_NUMBER")) {
               editor.putLong(14, metadata.getLong("android.media.metadata.DISC_NUMBER"));
            }

            if (metadata.containsKey("android.media.metadata.DURATION")) {
               editor.putLong(9, metadata.getLong("android.media.metadata.DURATION"));
            }

            if (metadata.containsKey("android.media.metadata.GENRE")) {
               editor.putString(6, metadata.getString("android.media.metadata.GENRE"));
            }

            if (metadata.containsKey("android.media.metadata.TITLE")) {
               editor.putString(7, metadata.getString("android.media.metadata.TITLE"));
            }

            if (metadata.containsKey("android.media.metadata.TRACK_NUMBER")) {
               editor.putLong(0, metadata.getLong("android.media.metadata.TRACK_NUMBER"));
            }

            if (metadata.containsKey("android.media.metadata.WRITER")) {
               editor.putString(11, metadata.getString("android.media.metadata.WRITER"));
            }

            return editor;
         }
      }

      public void setSessionActivity(PendingIntent pi) {
         Object var2 = this.mLock;
         synchronized(this.mLock) {
            this.mSessionActivity = pi;
         }
      }

      public void setMediaButtonReceiver(PendingIntent mbr) {
      }

      public void setQueue(List queue) {
         this.mQueue = queue;
         this.sendQueue(queue);
      }

      public void setQueueTitle(CharSequence title) {
         this.mQueueTitle = title;
         this.sendQueueTitle(title);
      }

      public Object getMediaSession() {
         return null;
      }

      public Object getRemoteControlClient() {
         return null;
      }

      public String getCallingPackage() {
         return null;
      }

      public void setRatingType(int type) {
         this.mRatingType = type;
      }

      public void setCaptioningEnabled(boolean enabled) {
         if (this.mCaptioningEnabled != enabled) {
            this.mCaptioningEnabled = enabled;
            this.sendCaptioningEnabled(enabled);
         }

      }

      public void setRepeatMode(int repeatMode) {
         if (this.mRepeatMode != repeatMode) {
            this.mRepeatMode = repeatMode;
            this.sendRepeatMode(repeatMode);
         }

      }

      public void setShuffleModeEnabled(boolean enabled) {
         if (this.mShuffleModeEnabled != enabled) {
            this.mShuffleModeEnabled = enabled;
            this.sendShuffleModeEnabled(enabled);
         }

      }

      public void setShuffleMode(int shuffleMode) {
         if (this.mShuffleMode != shuffleMode) {
            this.mShuffleMode = shuffleMode;
            this.sendShuffleMode(shuffleMode);
         }

      }

      public void setExtras(Bundle extras) {
         this.mExtras = extras;
         this.sendExtras(extras);
      }

      boolean update() {
         boolean registeredRcc = false;
         if (this.mIsActive) {
            if (!this.mIsMbrRegistered && (this.mFlags & 1) != 0) {
               this.registerMediaButtonEventReceiver(this.mMediaButtonReceiverIntent, this.mMediaButtonReceiverComponentName);
               this.mIsMbrRegistered = true;
            } else if (this.mIsMbrRegistered && (this.mFlags & 1) == 0) {
               this.unregisterMediaButtonEventReceiver(this.mMediaButtonReceiverIntent, this.mMediaButtonReceiverComponentName);
               this.mIsMbrRegistered = false;
            }

            if (!this.mIsRccRegistered && (this.mFlags & 2) != 0) {
               this.mAudioManager.registerRemoteControlClient(this.mRcc);
               this.mIsRccRegistered = true;
               registeredRcc = true;
            } else if (this.mIsRccRegistered && (this.mFlags & 2) == 0) {
               this.mRcc.setPlaybackState(0);
               this.mAudioManager.unregisterRemoteControlClient(this.mRcc);
               this.mIsRccRegistered = false;
            }
         } else {
            if (this.mIsMbrRegistered) {
               this.unregisterMediaButtonEventReceiver(this.mMediaButtonReceiverIntent, this.mMediaButtonReceiverComponentName);
               this.mIsMbrRegistered = false;
            }

            if (this.mIsRccRegistered) {
               this.mRcc.setPlaybackState(0);
               this.mAudioManager.unregisterRemoteControlClient(this.mRcc);
               this.mIsRccRegistered = false;
            }
         }

         return registeredRcc;
      }

      void registerMediaButtonEventReceiver(PendingIntent mbrIntent, ComponentName mbrComponent) {
         this.mAudioManager.registerMediaButtonEventReceiver(mbrComponent);
      }

      void unregisterMediaButtonEventReceiver(PendingIntent mbrIntent, ComponentName mbrComponent) {
         this.mAudioManager.unregisterMediaButtonEventReceiver(mbrComponent);
      }

      void adjustVolume(int direction, int flags) {
         if (this.mVolumeType == 2) {
            if (this.mVolumeProvider != null) {
               this.mVolumeProvider.onAdjustVolume(direction);
            }
         } else {
            this.mAudioManager.adjustStreamVolume(this.mLocalStream, direction, flags);
         }

      }

      void setVolumeTo(int value, int flags) {
         if (this.mVolumeType == 2) {
            if (this.mVolumeProvider != null) {
               this.mVolumeProvider.onSetVolumeTo(value);
            }
         } else {
            this.mAudioManager.setStreamVolume(this.mLocalStream, value, flags);
         }

      }

      void sendVolumeInfoChanged(ParcelableVolumeInfo info) {
         int size = this.mControllerCallbacks.beginBroadcast();

         for(int i = size - 1; i >= 0; --i) {
            IMediaControllerCallback cb = (IMediaControllerCallback)this.mControllerCallbacks.getBroadcastItem(i);

            try {
               cb.onVolumeInfoChanged(info);
            } catch (RemoteException var6) {
               ;
            }
         }

         this.mControllerCallbacks.finishBroadcast();
      }

      private void sendSessionDestroyed() {
         int size = this.mControllerCallbacks.beginBroadcast();

         for(int i = size - 1; i >= 0; --i) {
            IMediaControllerCallback cb = (IMediaControllerCallback)this.mControllerCallbacks.getBroadcastItem(i);

            try {
               cb.onSessionDestroyed();
            } catch (RemoteException var5) {
               ;
            }
         }

         this.mControllerCallbacks.finishBroadcast();
         this.mControllerCallbacks.kill();
      }

      private void sendEvent(String event, Bundle extras) {
         int size = this.mControllerCallbacks.beginBroadcast();

         for(int i = size - 1; i >= 0; --i) {
            IMediaControllerCallback cb = (IMediaControllerCallback)this.mControllerCallbacks.getBroadcastItem(i);

            try {
               cb.onEvent(event, extras);
            } catch (RemoteException var7) {
               ;
            }
         }

         this.mControllerCallbacks.finishBroadcast();
      }

      private void sendState(PlaybackStateCompat state) {
         int size = this.mControllerCallbacks.beginBroadcast();

         for(int i = size - 1; i >= 0; --i) {
            IMediaControllerCallback cb = (IMediaControllerCallback)this.mControllerCallbacks.getBroadcastItem(i);

            try {
               cb.onPlaybackStateChanged(state);
            } catch (RemoteException var6) {
               ;
            }
         }

         this.mControllerCallbacks.finishBroadcast();
      }

      private void sendMetadata(MediaMetadataCompat metadata) {
         int size = this.mControllerCallbacks.beginBroadcast();

         for(int i = size - 1; i >= 0; --i) {
            IMediaControllerCallback cb = (IMediaControllerCallback)this.mControllerCallbacks.getBroadcastItem(i);

            try {
               cb.onMetadataChanged(metadata);
            } catch (RemoteException var6) {
               ;
            }
         }

         this.mControllerCallbacks.finishBroadcast();
      }

      private void sendQueue(List queue) {
         int size = this.mControllerCallbacks.beginBroadcast();

         for(int i = size - 1; i >= 0; --i) {
            IMediaControllerCallback cb = (IMediaControllerCallback)this.mControllerCallbacks.getBroadcastItem(i);

            try {
               cb.onQueueChanged(queue);
            } catch (RemoteException var6) {
               ;
            }
         }

         this.mControllerCallbacks.finishBroadcast();
      }

      private void sendQueueTitle(CharSequence queueTitle) {
         int size = this.mControllerCallbacks.beginBroadcast();

         for(int i = size - 1; i >= 0; --i) {
            IMediaControllerCallback cb = (IMediaControllerCallback)this.mControllerCallbacks.getBroadcastItem(i);

            try {
               cb.onQueueTitleChanged(queueTitle);
            } catch (RemoteException var6) {
               ;
            }
         }

         this.mControllerCallbacks.finishBroadcast();
      }

      private void sendCaptioningEnabled(boolean enabled) {
         int size = this.mControllerCallbacks.beginBroadcast();

         for(int i = size - 1; i >= 0; --i) {
            IMediaControllerCallback cb = (IMediaControllerCallback)this.mControllerCallbacks.getBroadcastItem(i);

            try {
               cb.onCaptioningEnabledChanged(enabled);
            } catch (RemoteException var6) {
               ;
            }
         }

         this.mControllerCallbacks.finishBroadcast();
      }

      private void sendRepeatMode(int repeatMode) {
         int size = this.mControllerCallbacks.beginBroadcast();

         for(int i = size - 1; i >= 0; --i) {
            IMediaControllerCallback cb = (IMediaControllerCallback)this.mControllerCallbacks.getBroadcastItem(i);

            try {
               cb.onRepeatModeChanged(repeatMode);
            } catch (RemoteException var6) {
               ;
            }
         }

         this.mControllerCallbacks.finishBroadcast();
      }

      private void sendShuffleModeEnabled(boolean enabled) {
         int size = this.mControllerCallbacks.beginBroadcast();

         for(int i = size - 1; i >= 0; --i) {
            IMediaControllerCallback cb = (IMediaControllerCallback)this.mControllerCallbacks.getBroadcastItem(i);

            try {
               cb.onShuffleModeChangedDeprecated(enabled);
            } catch (RemoteException var6) {
               ;
            }
         }

         this.mControllerCallbacks.finishBroadcast();
      }

      private void sendShuffleMode(int shuffleMode) {
         int size = this.mControllerCallbacks.beginBroadcast();

         for(int i = size - 1; i >= 0; --i) {
            IMediaControllerCallback cb = (IMediaControllerCallback)this.mControllerCallbacks.getBroadcastItem(i);

            try {
               cb.onShuffleModeChanged(shuffleMode);
            } catch (RemoteException var6) {
               ;
            }
         }

         this.mControllerCallbacks.finishBroadcast();
      }

      private void sendExtras(Bundle extras) {
         int size = this.mControllerCallbacks.beginBroadcast();

         for(int i = size - 1; i >= 0; --i) {
            IMediaControllerCallback cb = (IMediaControllerCallback)this.mControllerCallbacks.getBroadcastItem(i);

            try {
               cb.onExtrasChanged(extras);
            } catch (RemoteException var6) {
               ;
            }
         }

         this.mControllerCallbacks.finishBroadcast();
      }

      class MessageHandler extends Handler {
         private static final int MSG_COMMAND = 1;
         private static final int MSG_ADJUST_VOLUME = 2;
         private static final int MSG_PREPARE = 3;
         private static final int MSG_PREPARE_MEDIA_ID = 4;
         private static final int MSG_PREPARE_SEARCH = 5;
         private static final int MSG_PREPARE_URI = 6;
         private static final int MSG_PLAY = 7;
         private static final int MSG_PLAY_MEDIA_ID = 8;
         private static final int MSG_PLAY_SEARCH = 9;
         private static final int MSG_PLAY_URI = 10;
         private static final int MSG_SKIP_TO_ITEM = 11;
         private static final int MSG_PAUSE = 12;
         private static final int MSG_STOP = 13;
         private static final int MSG_NEXT = 14;
         private static final int MSG_PREVIOUS = 15;
         private static final int MSG_FAST_FORWARD = 16;
         private static final int MSG_REWIND = 17;
         private static final int MSG_SEEK_TO = 18;
         private static final int MSG_RATE = 19;
         private static final int MSG_RATE_EXTRA = 31;
         private static final int MSG_CUSTOM_ACTION = 20;
         private static final int MSG_MEDIA_BUTTON = 21;
         private static final int MSG_SET_VOLUME = 22;
         private static final int MSG_SET_REPEAT_MODE = 23;
         private static final int MSG_SET_SHUFFLE_MODE_ENABLED = 24;
         private static final int MSG_ADD_QUEUE_ITEM = 25;
         private static final int MSG_ADD_QUEUE_ITEM_AT = 26;
         private static final int MSG_REMOVE_QUEUE_ITEM = 27;
         private static final int MSG_REMOVE_QUEUE_ITEM_AT = 28;
         private static final int MSG_SET_CAPTIONING_ENABLED = 29;
         private static final int MSG_SET_SHUFFLE_MODE = 30;
         private static final int KEYCODE_MEDIA_PAUSE = 127;
         private static final int KEYCODE_MEDIA_PLAY = 126;

         public MessageHandler(Looper looper) {
            super(looper);
         }

         public void post(int what, Object obj, Bundle bundle) {
            Message msg = this.obtainMessage(what, obj);
            msg.setData(bundle);
            msg.sendToTarget();
         }

         public void post(int what, Object obj) {
            this.obtainMessage(what, obj).sendToTarget();
         }

         public void post(int what) {
            this.post(what, (Object)null);
         }

         public void post(int what, Object obj, int arg1) {
            this.obtainMessage(what, arg1, 0, obj).sendToTarget();
         }

         public void handleMessage(Message msg) {
            MediaSessionCompat.Callback cb = MediaSessionImplBase.this.mCallback;
            if (cb != null) {
               switch(msg.what) {
               case 1:
                  MediaSessionCompat.MediaSessionImplBase.Command cmd = (MediaSessionCompat.MediaSessionImplBase.Command)msg.obj;
                  cb.onCommand(cmd.command, cmd.extras, cmd.stub);
                  break;
               case 2:
                  MediaSessionImplBase.this.adjustVolume(msg.arg1, 0);
                  break;
               case 3:
                  cb.onPrepare();
                  break;
               case 4:
                  cb.onPrepareFromMediaId((String)msg.obj, msg.getData());
                  break;
               case 5:
                  cb.onPrepareFromSearch((String)msg.obj, msg.getData());
                  break;
               case 6:
                  cb.onPrepareFromUri((Uri)msg.obj, msg.getData());
                  break;
               case 7:
                  cb.onPlay();
                  break;
               case 8:
                  cb.onPlayFromMediaId((String)msg.obj, msg.getData());
                  break;
               case 9:
                  cb.onPlayFromSearch((String)msg.obj, msg.getData());
                  break;
               case 10:
                  cb.onPlayFromUri((Uri)msg.obj, msg.getData());
                  break;
               case 11:
                  cb.onSkipToQueueItem(((Long)msg.obj).longValue());
                  break;
               case 12:
                  cb.onPause();
                  break;
               case 13:
                  cb.onStop();
                  break;
               case 14:
                  cb.onSkipToNext();
                  break;
               case 15:
                  cb.onSkipToPrevious();
                  break;
               case 16:
                  cb.onFastForward();
                  break;
               case 17:
                  cb.onRewind();
                  break;
               case 18:
                  cb.onSeekTo(((Long)msg.obj).longValue());
                  break;
               case 19:
                  cb.onSetRating((RatingCompat)msg.obj);
                  break;
               case 20:
                  cb.onCustomAction((String)msg.obj, msg.getData());
                  break;
               case 21:
                  KeyEvent keyEvent = (KeyEvent)msg.obj;
                  Intent intent = new Intent("android.intent.action.MEDIA_BUTTON");
                  intent.putExtra("android.intent.extra.KEY_EVENT", keyEvent);
                  if (!cb.onMediaButtonEvent(intent)) {
                     this.onMediaButtonEvent(keyEvent, cb);
                  }
                  break;
               case 22:
                  MediaSessionImplBase.this.setVolumeTo(msg.arg1, 0);
                  break;
               case 23:
                  cb.onSetRepeatMode(msg.arg1);
                  break;
               case 24:
                  cb.onSetShuffleModeEnabled(((Boolean)msg.obj).booleanValue());
                  break;
               case 25:
                  cb.onAddQueueItem((MediaDescriptionCompat)msg.obj);
                  break;
               case 26:
                  cb.onAddQueueItem((MediaDescriptionCompat)msg.obj, msg.arg1);
                  break;
               case 27:
                  cb.onRemoveQueueItem((MediaDescriptionCompat)msg.obj);
                  break;
               case 28:
                  if (MediaSessionImplBase.this.mQueue != null) {
                     MediaSessionCompat.QueueItem item = msg.arg1 >= 0 && msg.arg1 < MediaSessionImplBase.this.mQueue.size() ? (MediaSessionCompat.QueueItem)MediaSessionImplBase.this.mQueue.get(msg.arg1) : null;
                     if (item != null) {
                        cb.onRemoveQueueItem(item.getDescription());
                     }
                  }
                  break;
               case 29:
                  cb.onSetCaptioningEnabled(((Boolean)msg.obj).booleanValue());
                  break;
               case 30:
                  cb.onSetShuffleMode(msg.arg1);
                  break;
               case 31:
                  cb.onSetRating((RatingCompat)msg.obj, msg.getData());
               }

            }
         }

         private void onMediaButtonEvent(KeyEvent ke, MediaSessionCompat.Callback cb) {
            if (ke != null && ke.getAction() == 0) {
               long validActions = MediaSessionImplBase.this.mState == null ? 0L : MediaSessionImplBase.this.mState.getActions();
               switch(ke.getKeyCode()) {
               case 79:
               case 85:
                  Log.w("MediaSessionCompat", "KEYCODE_MEDIA_PLAY_PAUSE and KEYCODE_HEADSETHOOK are handled already");
                  break;
               case 86:
                  if ((validActions & 1L) != 0L) {
                     cb.onStop();
                  }
                  break;
               case 87:
                  if ((validActions & 32L) != 0L) {
                     cb.onSkipToNext();
                  }
                  break;
               case 88:
                  if ((validActions & 16L) != 0L) {
                     cb.onSkipToPrevious();
                  }
                  break;
               case 89:
                  if ((validActions & 8L) != 0L) {
                     cb.onRewind();
                  }
                  break;
               case 90:
                  if ((validActions & 64L) != 0L) {
                     cb.onFastForward();
                  }
                  break;
               case 126:
                  if ((validActions & 4L) != 0L) {
                     cb.onPlay();
                  }
                  break;
               case 127:
                  if ((validActions & 2L) != 0L) {
                     cb.onPause();
                  }
               }

            }
         }
      }

      private static final class Command {
         public final String command;
         public final Bundle extras;
         public final ResultReceiver stub;

         public Command(String command, Bundle extras, ResultReceiver stub) {
            this.command = command;
            this.extras = extras;
            this.stub = stub;
         }
      }

      class MediaSessionStub extends IMediaSession.Stub {
         public void sendCommand(String command, Bundle args, MediaSessionCompat.ResultReceiverWrapper cb) {
            MediaSessionImplBase.this.postToHandler(1, new MediaSessionCompat.MediaSessionImplBase.Command(command, args, cb.mResultReceiver));
         }

         public boolean sendMediaButton(KeyEvent mediaButton) {
            boolean handlesMediaButtons = (MediaSessionImplBase.this.mFlags & 1) != 0;
            if (handlesMediaButtons) {
               MediaSessionImplBase.this.postToHandler(21, mediaButton);
            }

            return handlesMediaButtons;
         }

         public void registerCallbackListener(IMediaControllerCallback cb) {
            if (MediaSessionImplBase.this.mDestroyed) {
               try {
                  cb.onSessionDestroyed();
               } catch (Exception var3) {
                  ;
               }

            } else {
               MediaSessionImplBase.this.mControllerCallbacks.register(cb);
            }
         }

         public void unregisterCallbackListener(IMediaControllerCallback cb) {
            MediaSessionImplBase.this.mControllerCallbacks.unregister(cb);
         }

         public String getPackageName() {
            return MediaSessionImplBase.this.mPackageName;
         }

         public String getTag() {
            return MediaSessionImplBase.this.mTag;
         }

         public PendingIntent getLaunchPendingIntent() {
            Object var1 = MediaSessionImplBase.this.mLock;
            synchronized(MediaSessionImplBase.this.mLock) {
               return MediaSessionImplBase.this.mSessionActivity;
            }
         }

         public long getFlags() {
            Object var1 = MediaSessionImplBase.this.mLock;
            synchronized(MediaSessionImplBase.this.mLock) {
               return (long)MediaSessionImplBase.this.mFlags;
            }
         }

         public ParcelableVolumeInfo getVolumeAttributes() {
            Object var6 = MediaSessionImplBase.this.mLock;
            int controlType;
            int max;
            int current;
            int stream;
            int volumeType;
            synchronized(MediaSessionImplBase.this.mLock) {
               volumeType = MediaSessionImplBase.this.mVolumeType;
               stream = MediaSessionImplBase.this.mLocalStream;
               VolumeProviderCompat vp = MediaSessionImplBase.this.mVolumeProvider;
               if (volumeType == 2) {
                  controlType = vp.getVolumeControl();
                  max = vp.getMaxVolume();
                  current = vp.getCurrentVolume();
               } else {
                  controlType = 2;
                  max = MediaSessionImplBase.this.mAudioManager.getStreamMaxVolume(stream);
                  current = MediaSessionImplBase.this.mAudioManager.getStreamVolume(stream);
               }
            }

            return new ParcelableVolumeInfo(volumeType, stream, controlType, max, current);
         }

         public void adjustVolume(int direction, int flags, String packageName) {
            MediaSessionImplBase.this.adjustVolume(direction, flags);
         }

         public void setVolumeTo(int value, int flags, String packageName) {
            MediaSessionImplBase.this.setVolumeTo(value, flags);
         }

         public void prepare() throws RemoteException {
            MediaSessionImplBase.this.postToHandler(3);
         }

         public void prepareFromMediaId(String mediaId, Bundle extras) throws RemoteException {
            MediaSessionImplBase.this.postToHandler(4, mediaId, extras);
         }

         public void prepareFromSearch(String query, Bundle extras) throws RemoteException {
            MediaSessionImplBase.this.postToHandler(5, query, extras);
         }

         public void prepareFromUri(Uri uri, Bundle extras) throws RemoteException {
            MediaSessionImplBase.this.postToHandler(6, uri, extras);
         }

         public void play() throws RemoteException {
            MediaSessionImplBase.this.postToHandler(7);
         }

         public void playFromMediaId(String mediaId, Bundle extras) throws RemoteException {
            MediaSessionImplBase.this.postToHandler(8, mediaId, extras);
         }

         public void playFromSearch(String query, Bundle extras) throws RemoteException {
            MediaSessionImplBase.this.postToHandler(9, query, extras);
         }

         public void playFromUri(Uri uri, Bundle extras) throws RemoteException {
            MediaSessionImplBase.this.postToHandler(10, uri, extras);
         }

         public void skipToQueueItem(long id) {
            MediaSessionImplBase.this.postToHandler(11, id);
         }

         public void pause() throws RemoteException {
            MediaSessionImplBase.this.postToHandler(12);
         }

         public void stop() throws RemoteException {
            MediaSessionImplBase.this.postToHandler(13);
         }

         public void next() throws RemoteException {
            MediaSessionImplBase.this.postToHandler(14);
         }

         public void previous() throws RemoteException {
            MediaSessionImplBase.this.postToHandler(15);
         }

         public void fastForward() throws RemoteException {
            MediaSessionImplBase.this.postToHandler(16);
         }

         public void rewind() throws RemoteException {
            MediaSessionImplBase.this.postToHandler(17);
         }

         public void seekTo(long pos) throws RemoteException {
            MediaSessionImplBase.this.postToHandler(18, pos);
         }

         public void rate(RatingCompat rating) throws RemoteException {
            MediaSessionImplBase.this.postToHandler(19, rating);
         }

         public void rateWithExtras(RatingCompat rating, Bundle extras) throws RemoteException {
            MediaSessionImplBase.this.postToHandler(31, rating, extras);
         }

         public void setCaptioningEnabled(boolean enabled) throws RemoteException {
            MediaSessionImplBase.this.postToHandler(29, enabled);
         }

         public void setRepeatMode(int repeatMode) throws RemoteException {
            MediaSessionImplBase.this.postToHandler(23, repeatMode);
         }

         public void setShuffleModeEnabledDeprecated(boolean enabled) throws RemoteException {
            MediaSessionImplBase.this.postToHandler(24, enabled);
         }

         public void setShuffleMode(int shuffleMode) throws RemoteException {
            MediaSessionImplBase.this.postToHandler(30, shuffleMode);
         }

         public void sendCustomAction(String action, Bundle args) throws RemoteException {
            MediaSessionImplBase.this.postToHandler(20, action, args);
         }

         public MediaMetadataCompat getMetadata() {
            return MediaSessionImplBase.this.mMetadata;
         }

         public PlaybackStateCompat getPlaybackState() {
            Object var3 = MediaSessionImplBase.this.mLock;
            PlaybackStateCompat state;
            MediaMetadataCompat metadata;
            synchronized(MediaSessionImplBase.this.mLock) {
               state = MediaSessionImplBase.this.mState;
               metadata = MediaSessionImplBase.this.mMetadata;
            }

            return MediaSessionCompat.getStateWithUpdatedPosition(state, metadata);
         }

         public List getQueue() {
            Object var1 = MediaSessionImplBase.this.mLock;
            synchronized(MediaSessionImplBase.this.mLock) {
               return MediaSessionImplBase.this.mQueue;
            }
         }

         public void addQueueItem(MediaDescriptionCompat description) {
            MediaSessionImplBase.this.postToHandler(25, description);
         }

         public void addQueueItemAt(MediaDescriptionCompat description, int index) {
            MediaSessionImplBase.this.postToHandler(26, description, index);
         }

         public void removeQueueItem(MediaDescriptionCompat description) {
            MediaSessionImplBase.this.postToHandler(27, description);
         }

         public void removeQueueItemAt(int index) {
            MediaSessionImplBase.this.postToHandler(28, index);
         }

         public CharSequence getQueueTitle() {
            return MediaSessionImplBase.this.mQueueTitle;
         }

         public Bundle getExtras() {
            Object var1 = MediaSessionImplBase.this.mLock;
            synchronized(MediaSessionImplBase.this.mLock) {
               return MediaSessionImplBase.this.mExtras;
            }
         }

         public int getRatingType() {
            return MediaSessionImplBase.this.mRatingType;
         }

         public boolean isCaptioningEnabled() {
            return MediaSessionImplBase.this.mCaptioningEnabled;
         }

         public int getRepeatMode() {
            return MediaSessionImplBase.this.mRepeatMode;
         }

         public boolean isShuffleModeEnabledDeprecated() {
            return MediaSessionImplBase.this.mShuffleModeEnabled;
         }

         public int getShuffleMode() {
            return MediaSessionImplBase.this.mShuffleMode;
         }

         public boolean isTransportControlEnabled() {
            return (MediaSessionImplBase.this.mFlags & 2) != 0;
         }
      }
   }

   interface MediaSessionImpl {
      void setCallback(MediaSessionCompat.Callback var1, Handler var2);

      void setFlags(int var1);

      void setPlaybackToLocal(int var1);

      void setPlaybackToRemote(VolumeProviderCompat var1);

      void setActive(boolean var1);

      boolean isActive();

      void sendSessionEvent(String var1, Bundle var2);

      void release();

      MediaSessionCompat.Token getSessionToken();

      void setPlaybackState(PlaybackStateCompat var1);

      PlaybackStateCompat getPlaybackState();

      void setMetadata(MediaMetadataCompat var1);

      void setSessionActivity(PendingIntent var1);

      void setMediaButtonReceiver(PendingIntent var1);

      void setQueue(List var1);

      void setQueueTitle(CharSequence var1);

      void setRatingType(int var1);

      void setCaptioningEnabled(boolean var1);

      void setRepeatMode(int var1);

      void setShuffleModeEnabled(boolean var1);

      void setShuffleMode(int var1);

      void setExtras(Bundle var1);

      Object getMediaSession();

      Object getRemoteControlClient();

      String getCallingPackage();
   }

   public interface OnActiveChangeListener {
      void onActiveChanged();
   }

   static final class ResultReceiverWrapper implements Parcelable {
      private ResultReceiver mResultReceiver;
      public static final Creator CREATOR = new Creator() {
         public MediaSessionCompat.ResultReceiverWrapper createFromParcel(Parcel p) {
            return new MediaSessionCompat.ResultReceiverWrapper(p);
         }

         public MediaSessionCompat.ResultReceiverWrapper[] newArray(int size) {
            return new MediaSessionCompat.ResultReceiverWrapper[size];
         }
      };

      public ResultReceiverWrapper(ResultReceiver resultReceiver) {
         this.mResultReceiver = resultReceiver;
      }

      ResultReceiverWrapper(Parcel in) {
         this.mResultReceiver = (ResultReceiver)ResultReceiver.CREATOR.createFromParcel(in);
      }

      public int describeContents() {
         return 0;
      }

      public void writeToParcel(Parcel dest, int flags) {
         this.mResultReceiver.writeToParcel(dest, flags);
      }
   }

   public static final class QueueItem implements Parcelable {
      public static final int UNKNOWN_ID = -1;
      private final MediaDescriptionCompat mDescription;
      private final long mId;
      private Object mItem;
      public static final Creator CREATOR = new Creator() {
         public MediaSessionCompat.QueueItem createFromParcel(Parcel p) {
            return new MediaSessionCompat.QueueItem(p);
         }

         public MediaSessionCompat.QueueItem[] newArray(int size) {
            return new MediaSessionCompat.QueueItem[size];
         }
      };

      public QueueItem(MediaDescriptionCompat description, long id) {
         this((Object)null, description, id);
      }

      private QueueItem(Object queueItem, MediaDescriptionCompat description, long id) {
         if (description == null) {
            throw new IllegalArgumentException("Description cannot be null.");
         } else if (id == -1L) {
            throw new IllegalArgumentException("Id cannot be QueueItem.UNKNOWN_ID");
         } else {
            this.mDescription = description;
            this.mId = id;
            this.mItem = queueItem;
         }
      }

      QueueItem(Parcel in) {
         this.mDescription = (MediaDescriptionCompat)MediaDescriptionCompat.CREATOR.createFromParcel(in);
         this.mId = in.readLong();
      }

      public MediaDescriptionCompat getDescription() {
         return this.mDescription;
      }

      public long getQueueId() {
         return this.mId;
      }

      public void writeToParcel(Parcel dest, int flags) {
         this.mDescription.writeToParcel(dest, flags);
         dest.writeLong(this.mId);
      }

      public int describeContents() {
         return 0;
      }

      public Object getQueueItem() {
         if (this.mItem == null && VERSION.SDK_INT >= 21) {
            this.mItem = MediaSessionCompatApi21.QueueItem.createItem(this.mDescription.getMediaDescription(), this.mId);
            return this.mItem;
         } else {
            return this.mItem;
         }
      }

      public static MediaSessionCompat.QueueItem fromQueueItem(Object queueItem) {
         if (queueItem != null && VERSION.SDK_INT >= 21) {
            Object descriptionObj = MediaSessionCompatApi21.QueueItem.getDescription(queueItem);
            MediaDescriptionCompat description = MediaDescriptionCompat.fromMediaDescription(descriptionObj);
            long id = MediaSessionCompatApi21.QueueItem.getQueueId(queueItem);
            return new MediaSessionCompat.QueueItem(queueItem, description, id);
         } else {
            return null;
         }
      }

      public static List fromQueueItemList(List itemList) {
         if (itemList != null && VERSION.SDK_INT >= 21) {
            List items = new ArrayList();
            Iterator var2 = itemList.iterator();

            while(var2.hasNext()) {
               Object itemObj = var2.next();
               items.add(fromQueueItem(itemObj));
            }

            return items;
         } else {
            return null;
         }
      }

      public String toString() {
         return "MediaSession.QueueItem {Description=" + this.mDescription + ", Id=" + this.mId + " }";
      }
   }

   public static final class Token implements Parcelable {
      private final Object mInner;
      private final IMediaSession mExtraBinder;
      public static final Creator CREATOR = new Creator() {
         public MediaSessionCompat.Token createFromParcel(Parcel in) {
            Object inner;
            if (VERSION.SDK_INT >= 21) {
               inner = in.readParcelable((ClassLoader)null);
            } else {
               inner = in.readStrongBinder();
            }

            return new MediaSessionCompat.Token(inner);
         }

         public MediaSessionCompat.Token[] newArray(int size) {
            return new MediaSessionCompat.Token[size];
         }
      };

      Token(Object inner) {
         this(inner, (IMediaSession)null);
      }

      Token(Object inner, IMediaSession extraBinder) {
         this.mInner = inner;
         this.mExtraBinder = extraBinder;
      }

      public static MediaSessionCompat.Token fromToken(Object token) {
         return fromToken(token, (IMediaSession)null);
      }

      @RestrictTo({Scope.LIBRARY_GROUP})
      public static MediaSessionCompat.Token fromToken(Object token, IMediaSession extraBinder) {
         return token != null && VERSION.SDK_INT >= 21 ? new MediaSessionCompat.Token(MediaSessionCompatApi21.verifyToken(token), extraBinder) : null;
      }

      public int describeContents() {
         return 0;
      }

      public void writeToParcel(Parcel dest, int flags) {
         if (VERSION.SDK_INT >= 21) {
            dest.writeParcelable((Parcelable)this.mInner, flags);
         } else {
            dest.writeStrongBinder((IBinder)this.mInner);
         }

      }

      public int hashCode() {
         return this.mInner == null ? 0 : this.mInner.hashCode();
      }

      public boolean equals(Object obj) {
         if (this == obj) {
            return true;
         } else if (!(obj instanceof MediaSessionCompat.Token)) {
            return false;
         } else {
            MediaSessionCompat.Token other = (MediaSessionCompat.Token)obj;
            if (this.mInner == null) {
               return other.mInner == null;
            } else {
               return other.mInner == null ? false : this.mInner.equals(other.mInner);
            }
         }
      }

      public Object getToken() {
         return this.mInner;
      }

      @RestrictTo({Scope.LIBRARY_GROUP})
      public IMediaSession getExtraBinder() {
         return this.mExtraBinder;
      }
   }

   public abstract static class Callback {
      final Object mCallbackObj;
      private WeakReference mSessionImpl;
      private MediaSessionCompat.Callback.CallbackHandler mCallbackHandler = null;
      private boolean mMediaPlayPauseKeyPending;

      public Callback() {
         if (VERSION.SDK_INT >= 24) {
            this.mCallbackObj = MediaSessionCompatApi24.createCallback(new MediaSessionCompat.Callback.StubApi24());
         } else if (VERSION.SDK_INT >= 23) {
            this.mCallbackObj = MediaSessionCompatApi23.createCallback(new MediaSessionCompat.Callback.StubApi23());
         } else if (VERSION.SDK_INT >= 21) {
            this.mCallbackObj = MediaSessionCompatApi21.createCallback(new MediaSessionCompat.Callback.StubApi21());
         } else {
            this.mCallbackObj = null;
         }

      }

      private void setSessionImpl(MediaSessionCompat.MediaSessionImpl impl, Handler handler) {
         this.mSessionImpl = new WeakReference(impl);
         if (this.mCallbackHandler != null) {
            this.mCallbackHandler.removeCallbacksAndMessages((Object)null);
         }

         this.mCallbackHandler = new MediaSessionCompat.Callback.CallbackHandler(handler.getLooper());
      }

      public void onCommand(String command, Bundle extras, ResultReceiver cb) {
      }

      public boolean onMediaButtonEvent(Intent mediaButtonEvent) {
         MediaSessionCompat.MediaSessionImpl impl = (MediaSessionCompat.MediaSessionImpl)this.mSessionImpl.get();
         if (impl != null && this.mCallbackHandler != null) {
            KeyEvent keyEvent = (KeyEvent)mediaButtonEvent.getParcelableExtra("android.intent.extra.KEY_EVENT");
            if (keyEvent != null && keyEvent.getAction() == 0) {
               int keyCode = keyEvent.getKeyCode();
               switch(keyCode) {
               case 79:
               case 85:
                  if (keyEvent.getRepeatCount() > 0) {
                     this.handleMediaPlayPauseKeySingleTapIfPending();
                  } else if (this.mMediaPlayPauseKeyPending) {
                     this.mCallbackHandler.removeMessages(1);
                     this.mMediaPlayPauseKeyPending = false;
                     PlaybackStateCompat state = impl.getPlaybackState();
                     long validActions = state == null ? 0L : state.getActions();
                     if ((validActions & 32L) != 0L) {
                        this.onSkipToNext();
                     }
                  } else {
                     this.mMediaPlayPauseKeyPending = true;
                     this.mCallbackHandler.sendEmptyMessageDelayed(1, (long)ViewConfiguration.getDoubleTapTimeout());
                  }

                  return true;
               default:
                  this.handleMediaPlayPauseKeySingleTapIfPending();
                  return false;
               }
            } else {
               return false;
            }
         } else {
            return false;
         }
      }

      private void handleMediaPlayPauseKeySingleTapIfPending() {
         if (this.mMediaPlayPauseKeyPending) {
            this.mMediaPlayPauseKeyPending = false;
            this.mCallbackHandler.removeMessages(1);
            MediaSessionCompat.MediaSessionImpl impl = (MediaSessionCompat.MediaSessionImpl)this.mSessionImpl.get();
            if (impl != null) {
               PlaybackStateCompat state = impl.getPlaybackState();
               long validActions = state == null ? 0L : state.getActions();
               boolean isPlaying = state != null && state.getState() == 3;
               boolean canPlay = (validActions & 516L) != 0L;
               boolean canPause = (validActions & 514L) != 0L;
               if (isPlaying && canPause) {
                  this.onPause();
               } else if (!isPlaying && canPlay) {
                  this.onPlay();
               }

            }
         }
      }

      public void onPrepare() {
      }

      public void onPrepareFromMediaId(String mediaId, Bundle extras) {
      }

      public void onPrepareFromSearch(String query, Bundle extras) {
      }

      public void onPrepareFromUri(Uri uri, Bundle extras) {
      }

      public void onPlay() {
      }

      public void onPlayFromMediaId(String mediaId, Bundle extras) {
      }

      public void onPlayFromSearch(String query, Bundle extras) {
      }

      public void onPlayFromUri(Uri uri, Bundle extras) {
      }

      public void onSkipToQueueItem(long id) {
      }

      public void onPause() {
      }

      public void onSkipToNext() {
      }

      public void onSkipToPrevious() {
      }

      public void onFastForward() {
      }

      public void onRewind() {
      }

      public void onStop() {
      }

      public void onSeekTo(long pos) {
      }

      public void onSetRating(RatingCompat rating) {
      }

      public void onSetRating(RatingCompat rating, Bundle extras) {
      }

      public void onSetCaptioningEnabled(boolean enabled) {
      }

      public void onSetRepeatMode(int repeatMode) {
      }

      /** @deprecated */
      @Deprecated
      public void onSetShuffleModeEnabled(boolean enabled) {
      }

      public void onSetShuffleMode(int shuffleMode) {
      }

      public void onCustomAction(String action, Bundle extras) {
      }

      public void onAddQueueItem(MediaDescriptionCompat description) {
      }

      public void onAddQueueItem(MediaDescriptionCompat description, int index) {
      }

      public void onRemoveQueueItem(MediaDescriptionCompat description) {
      }

      /** @deprecated */
      @Deprecated
      public void onRemoveQueueItemAt(int index) {
      }

      @RequiresApi(24)
      private class StubApi24 extends MediaSessionCompat.Callback.StubApi23 implements MediaSessionCompatApi24.Callback {
         StubApi24() {
            super();
         }

         public void onPrepare() {
            Callback.this.onPrepare();
         }

         public void onPrepareFromMediaId(String mediaId, Bundle extras) {
            Callback.this.onPrepareFromMediaId(mediaId, extras);
         }

         public void onPrepareFromSearch(String query, Bundle extras) {
            Callback.this.onPrepareFromSearch(query, extras);
         }

         public void onPrepareFromUri(Uri uri, Bundle extras) {
            Callback.this.onPrepareFromUri(uri, extras);
         }
      }

      @RequiresApi(23)
      private class StubApi23 extends MediaSessionCompat.Callback.StubApi21 implements MediaSessionCompatApi23.Callback {
         StubApi23() {
            super();
         }

         public void onPlayFromUri(Uri uri, Bundle extras) {
            Callback.this.onPlayFromUri(uri, extras);
         }
      }

      @RequiresApi(21)
      private class StubApi21 implements MediaSessionCompatApi21.Callback {
         public void onCommand(String command, Bundle extras, ResultReceiver cb) {
            try {
               MediaSessionCompat.MediaSessionImplApi21 impl;
               if (command.equals("android.support.v4.media.session.command.GET_EXTRA_BINDER")) {
                  impl = (MediaSessionCompat.MediaSessionImplApi21)Callback.this.mSessionImpl.get();
                  if (impl != null) {
                     Bundle result = new Bundle();
                     IMediaSession extraBinder = impl.getSessionToken().getExtraBinder();
                     BundleCompat.putBinder(result, "android.support.v4.media.session.EXTRA_BINDER", extraBinder == null ? null : extraBinder.asBinder());
                     cb.send(0, result);
                  }
               } else if (command.equals("android.support.v4.media.session.command.ADD_QUEUE_ITEM")) {
                  extras.setClassLoader(MediaDescriptionCompat.class.getClassLoader());
                  Callback.this.onAddQueueItem((MediaDescriptionCompat)extras.getParcelable("android.support.v4.media.session.command.ARGUMENT_MEDIA_DESCRIPTION"));
               } else if (command.equals("android.support.v4.media.session.command.ADD_QUEUE_ITEM_AT")) {
                  extras.setClassLoader(MediaDescriptionCompat.class.getClassLoader());
                  Callback.this.onAddQueueItem((MediaDescriptionCompat)extras.getParcelable("android.support.v4.media.session.command.ARGUMENT_MEDIA_DESCRIPTION"), extras.getInt("android.support.v4.media.session.command.ARGUMENT_INDEX"));
               } else if (command.equals("android.support.v4.media.session.command.REMOVE_QUEUE_ITEM")) {
                  extras.setClassLoader(MediaDescriptionCompat.class.getClassLoader());
                  Callback.this.onRemoveQueueItem((MediaDescriptionCompat)extras.getParcelable("android.support.v4.media.session.command.ARGUMENT_MEDIA_DESCRIPTION"));
               } else if (command.equals("android.support.v4.media.session.command.REMOVE_QUEUE_ITEM_AT")) {
                  impl = (MediaSessionCompat.MediaSessionImplApi21)Callback.this.mSessionImpl.get();
                  if (impl != null && impl.mQueue != null) {
                     int index = extras.getInt("android.support.v4.media.session.command.ARGUMENT_INDEX", -1);
                     MediaSessionCompat.QueueItem item = index >= 0 && index < impl.mQueue.size() ? (MediaSessionCompat.QueueItem)impl.mQueue.get(index) : null;
                     if (item != null) {
                        Callback.this.onRemoveQueueItem(item.getDescription());
                     }
                  }
               } else {
                  Callback.this.onCommand(command, extras, cb);
               }
            } catch (BadParcelableException var7) {
               Log.e("MediaSessionCompat", "Could not unparcel the extra data.");
            }

         }

         public boolean onMediaButtonEvent(Intent mediaButtonIntent) {
            return Callback.this.onMediaButtonEvent(mediaButtonIntent);
         }

         public void onPlay() {
            Callback.this.onPlay();
         }

         public void onPlayFromMediaId(String mediaId, Bundle extras) {
            Callback.this.onPlayFromMediaId(mediaId, extras);
         }

         public void onPlayFromSearch(String search, Bundle extras) {
            Callback.this.onPlayFromSearch(search, extras);
         }

         public void onSkipToQueueItem(long id) {
            Callback.this.onSkipToQueueItem(id);
         }

         public void onPause() {
            Callback.this.onPause();
         }

         public void onSkipToNext() {
            Callback.this.onSkipToNext();
         }

         public void onSkipToPrevious() {
            Callback.this.onSkipToPrevious();
         }

         public void onFastForward() {
            Callback.this.onFastForward();
         }

         public void onRewind() {
            Callback.this.onRewind();
         }

         public void onStop() {
            Callback.this.onStop();
         }

         public void onSeekTo(long pos) {
            Callback.this.onSeekTo(pos);
         }

         public void onSetRating(Object ratingObj) {
            Callback.this.onSetRating(RatingCompat.fromRating(ratingObj));
         }

         public void onSetRating(Object ratingObj, Bundle extras) {
            Callback.this.onSetRating(RatingCompat.fromRating(ratingObj), extras);
         }

         public void onCustomAction(String action, Bundle extras) {
            Uri uri;
            Bundle bundle;
            if (action.equals("android.support.v4.media.session.action.PLAY_FROM_URI")) {
               uri = (Uri)extras.getParcelable("android.support.v4.media.session.action.ARGUMENT_URI");
               bundle = (Bundle)extras.getParcelable("android.support.v4.media.session.action.ARGUMENT_EXTRAS");
               Callback.this.onPlayFromUri(uri, bundle);
            } else if (action.equals("android.support.v4.media.session.action.PREPARE")) {
               Callback.this.onPrepare();
            } else {
               String query;
               if (action.equals("android.support.v4.media.session.action.PREPARE_FROM_MEDIA_ID")) {
                  query = extras.getString("android.support.v4.media.session.action.ARGUMENT_MEDIA_ID");
                  bundle = extras.getBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS");
                  Callback.this.onPrepareFromMediaId(query, bundle);
               } else if (action.equals("android.support.v4.media.session.action.PREPARE_FROM_SEARCH")) {
                  query = extras.getString("android.support.v4.media.session.action.ARGUMENT_QUERY");
                  bundle = extras.getBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS");
                  Callback.this.onPrepareFromSearch(query, bundle);
               } else if (action.equals("android.support.v4.media.session.action.PREPARE_FROM_URI")) {
                  uri = (Uri)extras.getParcelable("android.support.v4.media.session.action.ARGUMENT_URI");
                  bundle = extras.getBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS");
                  Callback.this.onPrepareFromUri(uri, bundle);
               } else {
                  boolean enabled;
                  if (action.equals("android.support.v4.media.session.action.SET_CAPTIONING_ENABLED")) {
                     enabled = extras.getBoolean("android.support.v4.media.session.action.ARGUMENT_CAPTIONING_ENABLED");
                     Callback.this.onSetCaptioningEnabled(enabled);
                  } else {
                     int shuffleMode;
                     if (action.equals("android.support.v4.media.session.action.SET_REPEAT_MODE")) {
                        shuffleMode = extras.getInt("android.support.v4.media.session.action.ARGUMENT_REPEAT_MODE");
                        Callback.this.onSetRepeatMode(shuffleMode);
                     } else if (action.equals("android.support.v4.media.session.action.SET_SHUFFLE_MODE_ENABLED")) {
                        enabled = extras.getBoolean("android.support.v4.media.session.action.ARGUMENT_SHUFFLE_MODE_ENABLED");
                        Callback.this.onSetShuffleModeEnabled(enabled);
                     } else if (action.equals("android.support.v4.media.session.action.SET_SHUFFLE_MODE")) {
                        shuffleMode = extras.getInt("android.support.v4.media.session.action.ARGUMENT_SHUFFLE_MODE");
                        Callback.this.onSetShuffleMode(shuffleMode);
                     } else if (action.equals("android.support.v4.media.session.action.SET_RATING")) {
                        extras.setClassLoader(RatingCompat.class.getClassLoader());
                        RatingCompat rating = (RatingCompat)extras.getParcelable("android.support.v4.media.session.action.ARGUMENT_RATING");
                        bundle = extras.getBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS");
                        Callback.this.onSetRating(rating, bundle);
                     } else {
                        Callback.this.onCustomAction(action, extras);
                     }
                  }
               }
            }

         }
      }

      private class CallbackHandler extends Handler {
         private static final int MSG_MEDIA_PLAY_PAUSE_KEY_DOUBLE_TAP_TIMEOUT = 1;

         CallbackHandler(Looper looper) {
            super(looper);
         }

         public void handleMessage(Message msg) {
            if (msg.what == 1) {
               Callback.this.handleMediaPlayPauseKeySingleTapIfPending();
            }

         }
      }
   }

   @Retention(RetentionPolicy.SOURCE)
   @RestrictTo({Scope.LIBRARY_GROUP})
   public @interface SessionFlags {
   }
}
