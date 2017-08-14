package android.support.v4.media;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.BadParcelableException;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.os.Build.VERSION;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.app.BundleCompat;
import android.support.v4.media.session.IMediaSession;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.os.ResultReceiver;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.util.Log;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

public final class MediaBrowserCompat {
   static final String TAG = "MediaBrowserCompat";
   static final boolean DEBUG = Log.isLoggable("MediaBrowserCompat", 3);
   public static final String EXTRA_PAGE = "android.media.browse.extra.PAGE";
   public static final String EXTRA_PAGE_SIZE = "android.media.browse.extra.PAGE_SIZE";
   public static final String EXTRA_MEDIA_ID = "android.media.browse.extra.MEDIA_ID";
   public static final String EXTRA_DOWNLOAD_PROGRESS = "android.media.browse.extra.DOWNLOAD_PROGRESS";
   public static final String CUSTOM_ACTION_DOWNLOAD = "android.support.v4.media.action.DOWNLOAD";
   public static final String CUSTOM_ACTION_REMOVE_DOWNLOADED_FILE = "android.support.v4.media.action.REMOVE_DOWNLOADED_FILE";
   private final MediaBrowserCompat.MediaBrowserImpl mImpl;

   public MediaBrowserCompat(Context context, ComponentName serviceComponent, MediaBrowserCompat.ConnectionCallback callback, Bundle rootHints) {
      if (VERSION.SDK_INT >= 26) {
         this.mImpl = new MediaBrowserCompat.MediaBrowserImplApi24(context, serviceComponent, callback, rootHints);
      } else if (VERSION.SDK_INT >= 23) {
         this.mImpl = new MediaBrowserCompat.MediaBrowserImplApi23(context, serviceComponent, callback, rootHints);
      } else if (VERSION.SDK_INT >= 21) {
         this.mImpl = new MediaBrowserCompat.MediaBrowserImplApi21(context, serviceComponent, callback, rootHints);
      } else {
         this.mImpl = new MediaBrowserCompat.MediaBrowserImplBase(context, serviceComponent, callback, rootHints);
      }

   }

   public void connect() {
      this.mImpl.connect();
   }

   public void disconnect() {
      this.mImpl.disconnect();
   }

   public boolean isConnected() {
      return this.mImpl.isConnected();
   }

   @NonNull
   public ComponentName getServiceComponent() {
      return this.mImpl.getServiceComponent();
   }

   @NonNull
   public String getRoot() {
      return this.mImpl.getRoot();
   }

   @Nullable
   public Bundle getExtras() {
      return this.mImpl.getExtras();
   }

   @NonNull
   public MediaSessionCompat.Token getSessionToken() {
      return this.mImpl.getSessionToken();
   }

   public void subscribe(@NonNull String parentId, @NonNull MediaBrowserCompat.SubscriptionCallback callback) {
      if (TextUtils.isEmpty(parentId)) {
         throw new IllegalArgumentException("parentId is empty");
      } else if (callback == null) {
         throw new IllegalArgumentException("callback is null");
      } else {
         this.mImpl.subscribe(parentId, (Bundle)null, callback);
      }
   }

   public void subscribe(@NonNull String parentId, @NonNull Bundle options, @NonNull MediaBrowserCompat.SubscriptionCallback callback) {
      if (TextUtils.isEmpty(parentId)) {
         throw new IllegalArgumentException("parentId is empty");
      } else if (callback == null) {
         throw new IllegalArgumentException("callback is null");
      } else if (options == null) {
         throw new IllegalArgumentException("options are null");
      } else {
         this.mImpl.subscribe(parentId, options, callback);
      }
   }

   public void unsubscribe(@NonNull String parentId) {
      if (TextUtils.isEmpty(parentId)) {
         throw new IllegalArgumentException("parentId is empty");
      } else {
         this.mImpl.unsubscribe(parentId, (MediaBrowserCompat.SubscriptionCallback)null);
      }
   }

   public void unsubscribe(@NonNull String parentId, @NonNull MediaBrowserCompat.SubscriptionCallback callback) {
      if (TextUtils.isEmpty(parentId)) {
         throw new IllegalArgumentException("parentId is empty");
      } else if (callback == null) {
         throw new IllegalArgumentException("callback is null");
      } else {
         this.mImpl.unsubscribe(parentId, callback);
      }
   }

   public void getItem(@NonNull String mediaId, @NonNull MediaBrowserCompat.ItemCallback cb) {
      this.mImpl.getItem(mediaId, cb);
   }

   public void search(@NonNull String query, Bundle extras, @NonNull MediaBrowserCompat.SearchCallback callback) {
      if (TextUtils.isEmpty(query)) {
         throw new IllegalArgumentException("query cannot be empty");
      } else if (callback == null) {
         throw new IllegalArgumentException("callback cannot be null");
      } else {
         this.mImpl.search(query, extras, callback);
      }
   }

   public void sendCustomAction(@NonNull String action, Bundle extras, @Nullable MediaBrowserCompat.CustomActionCallback callback) {
      if (TextUtils.isEmpty(action)) {
         throw new IllegalArgumentException("action cannot be empty");
      } else {
         this.mImpl.sendCustomAction(action, extras, callback);
      }
   }

   private static class CustomActionResultReceiver extends ResultReceiver {
      private final String mAction;
      private final Bundle mExtras;
      private final MediaBrowserCompat.CustomActionCallback mCallback;

      CustomActionResultReceiver(String action, Bundle extras, MediaBrowserCompat.CustomActionCallback callback, Handler handler) {
         super(handler);
         this.mAction = action;
         this.mExtras = extras;
         this.mCallback = callback;
      }

      protected void onReceiveResult(int resultCode, Bundle resultData) {
         if (this.mCallback != null) {
            switch(resultCode) {
            case -1:
               this.mCallback.onError(this.mAction, this.mExtras, resultData);
               break;
            case 0:
               this.mCallback.onResult(this.mAction, this.mExtras, resultData);
               break;
            case 1:
               this.mCallback.onProgressUpdate(this.mAction, this.mExtras, resultData);
               break;
            default:
               Log.w("MediaBrowserCompat", "Unknown result code: " + resultCode + " (extras=" + this.mExtras + ", resultData=" + resultData + ")");
            }

         }
      }
   }

   private static class SearchResultReceiver extends ResultReceiver {
      private final String mQuery;
      private final Bundle mExtras;
      private final MediaBrowserCompat.SearchCallback mCallback;

      SearchResultReceiver(String query, Bundle extras, MediaBrowserCompat.SearchCallback callback, Handler handler) {
         super(handler);
         this.mQuery = query;
         this.mExtras = extras;
         this.mCallback = callback;
      }

      protected void onReceiveResult(int resultCode, Bundle resultData) {
         if (resultData != null) {
            resultData.setClassLoader(MediaBrowserCompat.class.getClassLoader());
         }

         if (resultCode == 0 && resultData != null && resultData.containsKey("search_results")) {
            Parcelable[] items = resultData.getParcelableArray("search_results");
            List results = null;
            if (items != null) {
               results = new ArrayList();
               Parcelable[] var5 = items;
               int var6 = items.length;

               for(int var7 = 0; var7 < var6; ++var7) {
                  Parcelable item = var5[var7];
                  results.add((MediaBrowserCompat.MediaItem)item);
               }
            }

            this.mCallback.onSearchResult(this.mQuery, this.mExtras, results);
         } else {
            this.mCallback.onError(this.mQuery, this.mExtras);
         }
      }
   }

   private static class ItemReceiver extends ResultReceiver {
      private final String mMediaId;
      private final MediaBrowserCompat.ItemCallback mCallback;

      ItemReceiver(String mediaId, MediaBrowserCompat.ItemCallback callback, Handler handler) {
         super(handler);
         this.mMediaId = mediaId;
         this.mCallback = callback;
      }

      protected void onReceiveResult(int resultCode, Bundle resultData) {
         if (resultData != null) {
            resultData.setClassLoader(MediaBrowserCompat.class.getClassLoader());
         }

         if (resultCode == 0 && resultData != null && resultData.containsKey("media_item")) {
            Parcelable item = resultData.getParcelable("media_item");
            if (item != null && !(item instanceof MediaBrowserCompat.MediaItem)) {
               this.mCallback.onError(this.mMediaId);
            } else {
               this.mCallback.onItemLoaded((MediaBrowserCompat.MediaItem)item);
            }

         } else {
            this.mCallback.onError(this.mMediaId);
         }
      }
   }

   private static class ServiceBinderWrapper {
      private Messenger mMessenger;
      private Bundle mRootHints;

      public ServiceBinderWrapper(IBinder target, Bundle rootHints) {
         this.mMessenger = new Messenger(target);
         this.mRootHints = rootHints;
      }

      void connect(Context context, Messenger callbacksMessenger) throws RemoteException {
         Bundle data = new Bundle();
         data.putString("data_package_name", context.getPackageName());
         data.putBundle("data_root_hints", this.mRootHints);
         this.sendRequest(1, data, callbacksMessenger);
      }

      void disconnect(Messenger callbacksMessenger) throws RemoteException {
         this.sendRequest(2, (Bundle)null, callbacksMessenger);
      }

      void addSubscription(String parentId, IBinder callbackToken, Bundle options, Messenger callbacksMessenger) throws RemoteException {
         Bundle data = new Bundle();
         data.putString("data_media_item_id", parentId);
         BundleCompat.putBinder(data, "data_callback_token", callbackToken);
         data.putBundle("data_options", options);
         this.sendRequest(3, data, callbacksMessenger);
      }

      void removeSubscription(String parentId, IBinder callbackToken, Messenger callbacksMessenger) throws RemoteException {
         Bundle data = new Bundle();
         data.putString("data_media_item_id", parentId);
         BundleCompat.putBinder(data, "data_callback_token", callbackToken);
         this.sendRequest(4, data, callbacksMessenger);
      }

      void getMediaItem(String mediaId, ResultReceiver receiver, Messenger callbacksMessenger) throws RemoteException {
         Bundle data = new Bundle();
         data.putString("data_media_item_id", mediaId);
         data.putParcelable("data_result_receiver", receiver);
         this.sendRequest(5, data, callbacksMessenger);
      }

      void registerCallbackMessenger(Messenger callbackMessenger) throws RemoteException {
         Bundle data = new Bundle();
         data.putBundle("data_root_hints", this.mRootHints);
         this.sendRequest(6, data, callbackMessenger);
      }

      void unregisterCallbackMessenger(Messenger callbackMessenger) throws RemoteException {
         this.sendRequest(7, (Bundle)null, callbackMessenger);
      }

      void search(String query, Bundle extras, ResultReceiver receiver, Messenger callbacksMessenger) throws RemoteException {
         Bundle data = new Bundle();
         data.putString("data_search_query", query);
         data.putBundle("data_search_extras", extras);
         data.putParcelable("data_result_receiver", receiver);
         this.sendRequest(8, data, callbacksMessenger);
      }

      void sendCustomAction(String action, Bundle extras, ResultReceiver receiver, Messenger callbacksMessenger) throws RemoteException {
         Bundle data = new Bundle();
         data.putString("data_custom_action", action);
         data.putBundle("data_custom_action_extras", extras);
         data.putParcelable("data_result_receiver", receiver);
         this.sendRequest(9, data, callbacksMessenger);
      }

      private void sendRequest(int what, Bundle data, Messenger cbMessenger) throws RemoteException {
         Message msg = Message.obtain();
         msg.what = what;
         msg.arg1 = 1;
         msg.setData(data);
         msg.replyTo = cbMessenger;
         this.mMessenger.send(msg);
      }
   }

   private static class CallbackHandler extends Handler {
      private final WeakReference mCallbackImplRef;
      private WeakReference mCallbacksMessengerRef;

      CallbackHandler(MediaBrowserCompat.MediaBrowserServiceCallbackImpl callbackImpl) {
         this.mCallbackImplRef = new WeakReference(callbackImpl);
      }

      public void handleMessage(Message msg) {
         if (this.mCallbacksMessengerRef != null && this.mCallbacksMessengerRef.get() != null && this.mCallbackImplRef.get() != null) {
            Bundle data = msg.getData();
            data.setClassLoader(MediaSessionCompat.class.getClassLoader());
            MediaBrowserCompat.MediaBrowserServiceCallbackImpl serviceCallback = (MediaBrowserCompat.MediaBrowserServiceCallbackImpl)this.mCallbackImplRef.get();
            Messenger callbacksMessenger = (Messenger)this.mCallbacksMessengerRef.get();

            try {
               switch(msg.what) {
               case 1:
                  serviceCallback.onServiceConnected(callbacksMessenger, data.getString("data_media_item_id"), (MediaSessionCompat.Token)data.getParcelable("data_media_session_token"), data.getBundle("data_root_hints"));
                  break;
               case 2:
                  serviceCallback.onConnectionFailed(callbacksMessenger);
                  break;
               case 3:
                  serviceCallback.onLoadChildren(callbacksMessenger, data.getString("data_media_item_id"), data.getParcelableArrayList("data_media_item_list"), data.getBundle("data_options"));
                  break;
               default:
                  Log.w("MediaBrowserCompat", "Unhandled message: " + msg + "\n  Client version: " + 1 + "\n  Service version: " + msg.arg1);
               }
            } catch (BadParcelableException var6) {
               Log.e("MediaBrowserCompat", "Could not unparcel the data.");
               if (msg.what == 1) {
                  serviceCallback.onConnectionFailed(callbacksMessenger);
               }
            }

         }
      }

      void setCallbacksMessenger(Messenger callbacksMessenger) {
         this.mCallbacksMessengerRef = new WeakReference(callbacksMessenger);
      }
   }

   private static class Subscription {
      private final List mCallbacks = new ArrayList();
      private final List mOptionsList = new ArrayList();

      public boolean isEmpty() {
         return this.mCallbacks.isEmpty();
      }

      public List getOptionsList() {
         return this.mOptionsList;
      }

      public List getCallbacks() {
         return this.mCallbacks;
      }

      public MediaBrowserCompat.SubscriptionCallback getCallback(Bundle options) {
         for(int i = 0; i < this.mOptionsList.size(); ++i) {
            if (MediaBrowserCompatUtils.areSameOptions((Bundle)this.mOptionsList.get(i), options)) {
               return (MediaBrowserCompat.SubscriptionCallback)this.mCallbacks.get(i);
            }
         }

         return null;
      }

      public void putCallback(Bundle options, MediaBrowserCompat.SubscriptionCallback callback) {
         for(int i = 0; i < this.mOptionsList.size(); ++i) {
            if (MediaBrowserCompatUtils.areSameOptions((Bundle)this.mOptionsList.get(i), options)) {
               this.mCallbacks.set(i, callback);
               return;
            }
         }

         this.mCallbacks.add(callback);
         this.mOptionsList.add(options);
      }
   }

   @RequiresApi(26)
   static class MediaBrowserImplApi24 extends MediaBrowserCompat.MediaBrowserImplApi23 {
      public MediaBrowserImplApi24(Context context, ComponentName serviceComponent, MediaBrowserCompat.ConnectionCallback callback, Bundle rootHints) {
         super(context, serviceComponent, callback, rootHints);
      }

      public void subscribe(@NonNull String parentId, @NonNull Bundle options, @NonNull MediaBrowserCompat.SubscriptionCallback callback) {
         if (options == null) {
            MediaBrowserCompatApi21.subscribe(this.mBrowserObj, parentId, callback.mSubscriptionCallbackObj);
         } else {
            MediaBrowserCompatApi24.subscribe(this.mBrowserObj, parentId, options, callback.mSubscriptionCallbackObj);
         }

      }

      public void unsubscribe(@NonNull String parentId, MediaBrowserCompat.SubscriptionCallback callback) {
         if (callback == null) {
            MediaBrowserCompatApi21.unsubscribe(this.mBrowserObj, parentId);
         } else {
            MediaBrowserCompatApi24.unsubscribe(this.mBrowserObj, parentId, callback.mSubscriptionCallbackObj);
         }

      }
   }

   @RequiresApi(23)
   static class MediaBrowserImplApi23 extends MediaBrowserCompat.MediaBrowserImplApi21 {
      public MediaBrowserImplApi23(Context context, ComponentName serviceComponent, MediaBrowserCompat.ConnectionCallback callback, Bundle rootHints) {
         super(context, serviceComponent, callback, rootHints);
      }

      public void getItem(@NonNull String mediaId, @NonNull MediaBrowserCompat.ItemCallback cb) {
         if (this.mServiceBinderWrapper == null) {
            MediaBrowserCompatApi23.getItem(this.mBrowserObj, mediaId, cb.mItemCallbackObj);
         } else {
            super.getItem(mediaId, cb);
         }

      }
   }

   @RequiresApi(21)
   static class MediaBrowserImplApi21 implements MediaBrowserCompat.MediaBrowserImpl, MediaBrowserCompat.MediaBrowserServiceCallbackImpl, MediaBrowserCompat.ConnectionCallback.ConnectionCallbackInternal {
      protected final Object mBrowserObj;
      protected final Bundle mRootHints;
      protected final MediaBrowserCompat.CallbackHandler mHandler = new MediaBrowserCompat.CallbackHandler(this);
      private final ArrayMap mSubscriptions = new ArrayMap();
      protected MediaBrowserCompat.ServiceBinderWrapper mServiceBinderWrapper;
      protected Messenger mCallbacksMessenger;
      private MediaSessionCompat.Token mMediaSessionToken;

      public MediaBrowserImplApi21(Context context, ComponentName serviceComponent, MediaBrowserCompat.ConnectionCallback callback, Bundle rootHints) {
         if (rootHints == null) {
            rootHints = new Bundle();
         }

         rootHints.putInt("extra_client_version", 1);
         this.mRootHints = new Bundle(rootHints);
         callback.setInternalConnectionCallback(this);
         this.mBrowserObj = MediaBrowserCompatApi21.createBrowser(context, serviceComponent, callback.mConnectionCallbackObj, this.mRootHints);
      }

      public void connect() {
         MediaBrowserCompatApi21.connect(this.mBrowserObj);
      }

      public void disconnect() {
         if (this.mServiceBinderWrapper != null && this.mCallbacksMessenger != null) {
            try {
               this.mServiceBinderWrapper.unregisterCallbackMessenger(this.mCallbacksMessenger);
            } catch (RemoteException var2) {
               Log.i("MediaBrowserCompat", "Remote error unregistering client messenger.");
            }
         }

         MediaBrowserCompatApi21.disconnect(this.mBrowserObj);
      }

      public boolean isConnected() {
         return MediaBrowserCompatApi21.isConnected(this.mBrowserObj);
      }

      public ComponentName getServiceComponent() {
         return MediaBrowserCompatApi21.getServiceComponent(this.mBrowserObj);
      }

      @NonNull
      public String getRoot() {
         return MediaBrowserCompatApi21.getRoot(this.mBrowserObj);
      }

      @Nullable
      public Bundle getExtras() {
         return MediaBrowserCompatApi21.getExtras(this.mBrowserObj);
      }

      @NonNull
      public MediaSessionCompat.Token getSessionToken() {
         if (this.mMediaSessionToken == null) {
            this.mMediaSessionToken = MediaSessionCompat.Token.fromToken(MediaBrowserCompatApi21.getSessionToken(this.mBrowserObj));
         }

         return this.mMediaSessionToken;
      }

      public void subscribe(@NonNull String parentId, Bundle options, @NonNull MediaBrowserCompat.SubscriptionCallback callback) {
         MediaBrowserCompat.Subscription sub = (MediaBrowserCompat.Subscription)this.mSubscriptions.get(parentId);
         if (sub == null) {
            sub = new MediaBrowserCompat.Subscription();
            this.mSubscriptions.put(parentId, sub);
         }

         callback.setSubscription(sub);
         Bundle copiedOptions = options == null ? null : new Bundle(options);
         sub.putCallback(copiedOptions, callback);
         if (this.mServiceBinderWrapper == null) {
            MediaBrowserCompatApi21.subscribe(this.mBrowserObj, parentId, callback.mSubscriptionCallbackObj);
         } else {
            try {
               this.mServiceBinderWrapper.addSubscription(parentId, callback.mToken, copiedOptions, this.mCallbacksMessenger);
            } catch (RemoteException var7) {
               Log.i("MediaBrowserCompat", "Remote error subscribing media item: " + parentId);
            }
         }

      }

      public void unsubscribe(@NonNull String parentId, MediaBrowserCompat.SubscriptionCallback callback) {
         MediaBrowserCompat.Subscription sub = (MediaBrowserCompat.Subscription)this.mSubscriptions.get(parentId);
         if (sub != null) {
            List callbacks;
            List optionsList;
            int i;
            if (this.mServiceBinderWrapper == null) {
               if (callback == null) {
                  MediaBrowserCompatApi21.unsubscribe(this.mBrowserObj, parentId);
               } else {
                  callbacks = sub.getCallbacks();
                  optionsList = sub.getOptionsList();

                  for(i = callbacks.size() - 1; i >= 0; --i) {
                     if (callbacks.get(i) == callback) {
                        callbacks.remove(i);
                        optionsList.remove(i);
                     }
                  }

                  if (callbacks.size() == 0) {
                     MediaBrowserCompatApi21.unsubscribe(this.mBrowserObj, parentId);
                  }
               }
            } else {
               try {
                  if (callback == null) {
                     this.mServiceBinderWrapper.removeSubscription(parentId, (IBinder)null, this.mCallbacksMessenger);
                  } else {
                     callbacks = sub.getCallbacks();
                     optionsList = sub.getOptionsList();

                     for(i = callbacks.size() - 1; i >= 0; --i) {
                        if (callbacks.get(i) == callback) {
                           this.mServiceBinderWrapper.removeSubscription(parentId, callback.mToken, this.mCallbacksMessenger);
                           callbacks.remove(i);
                           optionsList.remove(i);
                        }
                     }
                  }
               } catch (RemoteException var7) {
                  Log.d("MediaBrowserCompat", "removeSubscription failed with RemoteException parentId=" + parentId);
               }
            }

            if (sub.isEmpty() || callback == null) {
               this.mSubscriptions.remove(parentId);
            }

         }
      }

      public void getItem(@NonNull final String mediaId, @NonNull final MediaBrowserCompat.ItemCallback cb) {
         if (TextUtils.isEmpty(mediaId)) {
            throw new IllegalArgumentException("mediaId is empty");
         } else if (cb == null) {
            throw new IllegalArgumentException("cb is null");
         } else if (!MediaBrowserCompatApi21.isConnected(this.mBrowserObj)) {
            Log.i("MediaBrowserCompat", "Not connected, unable to retrieve the MediaItem.");
            this.mHandler.post(new Runnable() {
               public void run() {
                  cb.onError(mediaId);
               }
            });
         } else if (this.mServiceBinderWrapper == null) {
            this.mHandler.post(new Runnable() {
               public void run() {
                  cb.onError(mediaId);
               }
            });
         } else {
            MediaBrowserCompat.ItemReceiver receiver = new MediaBrowserCompat.ItemReceiver(mediaId, cb, this.mHandler);

            try {
               this.mServiceBinderWrapper.getMediaItem(mediaId, receiver, this.mCallbacksMessenger);
            } catch (RemoteException var5) {
               Log.i("MediaBrowserCompat", "Remote error getting media item: " + mediaId);
               this.mHandler.post(new Runnable() {
                  public void run() {
                     cb.onError(mediaId);
                  }
               });
            }

         }
      }

      public void search(@NonNull final String query, final Bundle extras, @NonNull final MediaBrowserCompat.SearchCallback callback) {
         if (!this.isConnected()) {
            throw new IllegalStateException("search() called while not connected");
         } else if (this.mServiceBinderWrapper == null) {
            Log.i("MediaBrowserCompat", "The connected service doesn't support search.");
            this.mHandler.post(new Runnable() {
               public void run() {
                  callback.onError(query, extras);
               }
            });
         } else {
            MediaBrowserCompat.SearchResultReceiver receiver = new MediaBrowserCompat.SearchResultReceiver(query, extras, callback, this.mHandler);

            try {
               this.mServiceBinderWrapper.search(query, extras, receiver, this.mCallbacksMessenger);
            } catch (RemoteException var6) {
               Log.i("MediaBrowserCompat", "Remote error searching items with query: " + query, var6);
               this.mHandler.post(new Runnable() {
                  public void run() {
                     callback.onError(query, extras);
                  }
               });
            }

         }
      }

      public void sendCustomAction(@NonNull final String action, final Bundle extras, @Nullable final MediaBrowserCompat.CustomActionCallback callback) {
         if (!this.isConnected()) {
            throw new IllegalStateException("Cannot send a custom action (" + action + ") with " + "extras " + extras + " because the browser is not connected to the " + "service.");
         } else {
            if (this.mServiceBinderWrapper == null) {
               Log.i("MediaBrowserCompat", "The connected service doesn't support sendCustomAction.");
               if (callback != null) {
                  this.mHandler.post(new Runnable() {
                     public void run() {
                        callback.onError(action, extras, (Bundle)null);
                     }
                  });
               }
            }

            MediaBrowserCompat.CustomActionResultReceiver receiver = new MediaBrowserCompat.CustomActionResultReceiver(action, extras, callback, this.mHandler);

            try {
               this.mServiceBinderWrapper.sendCustomAction(action, extras, receiver, this.mCallbacksMessenger);
            } catch (RemoteException var6) {
               Log.i("MediaBrowserCompat", "Remote error sending a custom action: action=" + action + ", extras=" + extras, var6);
               if (callback != null) {
                  this.mHandler.post(new Runnable() {
                     public void run() {
                        callback.onError(action, extras, (Bundle)null);
                     }
                  });
               }
            }

         }
      }

      public void onConnected() {
         Bundle extras = MediaBrowserCompatApi21.getExtras(this.mBrowserObj);
         if (extras != null) {
            IBinder serviceBinder = BundleCompat.getBinder(extras, "extra_messenger");
            if (serviceBinder != null) {
               this.mServiceBinderWrapper = new MediaBrowserCompat.ServiceBinderWrapper(serviceBinder, this.mRootHints);
               this.mCallbacksMessenger = new Messenger(this.mHandler);
               this.mHandler.setCallbacksMessenger(this.mCallbacksMessenger);

               try {
                  this.mServiceBinderWrapper.registerCallbackMessenger(this.mCallbacksMessenger);
               } catch (RemoteException var4) {
                  Log.i("MediaBrowserCompat", "Remote error registering client messenger.");
               }
            }

            IMediaSession sessionToken = IMediaSession.Stub.asInterface(BundleCompat.getBinder(extras, "extra_session_binder"));
            if (sessionToken != null) {
               this.mMediaSessionToken = MediaSessionCompat.Token.fromToken(MediaBrowserCompatApi21.getSessionToken(this.mBrowserObj), sessionToken);
            }

         }
      }

      public void onConnectionSuspended() {
         this.mServiceBinderWrapper = null;
         this.mCallbacksMessenger = null;
         this.mMediaSessionToken = null;
         this.mHandler.setCallbacksMessenger((Messenger)null);
      }

      public void onConnectionFailed() {
      }

      public void onServiceConnected(Messenger callback, String root, MediaSessionCompat.Token session, Bundle extra) {
      }

      public void onConnectionFailed(Messenger callback) {
      }

      public void onLoadChildren(Messenger callback, String parentId, List list, Bundle options) {
         if (this.mCallbacksMessenger == callback) {
            MediaBrowserCompat.Subscription subscription = (MediaBrowserCompat.Subscription)this.mSubscriptions.get(parentId);
            if (subscription == null) {
               if (MediaBrowserCompat.DEBUG) {
                  Log.d("MediaBrowserCompat", "onLoadChildren for id that isn't subscribed id=" + parentId);
               }

            } else {
               MediaBrowserCompat.SubscriptionCallback subscriptionCallback = subscription.getCallback(options);
               if (subscriptionCallback != null) {
                  if (options == null) {
                     if (list == null) {
                        subscriptionCallback.onError(parentId);
                     } else {
                        subscriptionCallback.onChildrenLoaded(parentId, list);
                     }
                  } else if (list == null) {
                     subscriptionCallback.onError(parentId, options);
                  } else {
                     subscriptionCallback.onChildrenLoaded(parentId, list, options);
                  }
               }

            }
         }
      }
   }

   static class MediaBrowserImplBase implements MediaBrowserCompat.MediaBrowserImpl, MediaBrowserCompat.MediaBrowserServiceCallbackImpl {
      static final int CONNECT_STATE_DISCONNECTING = 0;
      static final int CONNECT_STATE_DISCONNECTED = 1;
      static final int CONNECT_STATE_CONNECTING = 2;
      static final int CONNECT_STATE_CONNECTED = 3;
      static final int CONNECT_STATE_SUSPENDED = 4;
      final Context mContext;
      final ComponentName mServiceComponent;
      final MediaBrowserCompat.ConnectionCallback mCallback;
      final Bundle mRootHints;
      final MediaBrowserCompat.CallbackHandler mHandler = new MediaBrowserCompat.CallbackHandler(this);
      private final ArrayMap mSubscriptions = new ArrayMap();
      int mState = 1;
      MediaBrowserCompat.MediaBrowserImplBase.MediaServiceConnection mServiceConnection;
      MediaBrowserCompat.ServiceBinderWrapper mServiceBinderWrapper;
      Messenger mCallbacksMessenger;
      private String mRootId;
      private MediaSessionCompat.Token mMediaSessionToken;
      private Bundle mExtras;

      public MediaBrowserImplBase(Context context, ComponentName serviceComponent, MediaBrowserCompat.ConnectionCallback callback, Bundle rootHints) {
         if (context == null) {
            throw new IllegalArgumentException("context must not be null");
         } else if (serviceComponent == null) {
            throw new IllegalArgumentException("service component must not be null");
         } else if (callback == null) {
            throw new IllegalArgumentException("connection callback must not be null");
         } else {
            this.mContext = context;
            this.mServiceComponent = serviceComponent;
            this.mCallback = callback;
            this.mRootHints = rootHints == null ? null : new Bundle(rootHints);
         }
      }

      public void connect() {
         if (this.mState != 0 && this.mState != 1) {
            throw new IllegalStateException("connect() called while neigther disconnecting nor disconnected (state=" + getStateLabel(this.mState) + ")");
         } else {
            this.mState = 2;
            this.mHandler.post(new Runnable() {
               public void run() {
                  if (MediaBrowserImplBase.this.mState != 0) {
                     MediaBrowserImplBase.this.mState = 2;
                     if (MediaBrowserCompat.DEBUG && MediaBrowserImplBase.this.mServiceConnection != null) {
                        throw new RuntimeException("mServiceConnection should be null. Instead it is " + MediaBrowserImplBase.this.mServiceConnection);
                     } else if (MediaBrowserImplBase.this.mServiceBinderWrapper != null) {
                        throw new RuntimeException("mServiceBinderWrapper should be null. Instead it is " + MediaBrowserImplBase.this.mServiceBinderWrapper);
                     } else if (MediaBrowserImplBase.this.mCallbacksMessenger != null) {
                        throw new RuntimeException("mCallbacksMessenger should be null. Instead it is " + MediaBrowserImplBase.this.mCallbacksMessenger);
                     } else {
                        Intent intent = new Intent("android.media.browse.MediaBrowserService");
                        intent.setComponent(MediaBrowserImplBase.this.mServiceComponent);
                        MediaBrowserImplBase.this.mServiceConnection = MediaBrowserImplBase.this.new MediaServiceConnection();
                        boolean bound = false;

                        try {
                           bound = MediaBrowserImplBase.this.mContext.bindService(intent, MediaBrowserImplBase.this.mServiceConnection, 1);
                        } catch (Exception var4) {
                           Log.e("MediaBrowserCompat", "Failed binding to service " + MediaBrowserImplBase.this.mServiceComponent);
                        }

                        if (!bound) {
                           MediaBrowserImplBase.this.forceCloseConnection();
                           MediaBrowserImplBase.this.mCallback.onConnectionFailed();
                        }

                        if (MediaBrowserCompat.DEBUG) {
                           Log.d("MediaBrowserCompat", "connect...");
                           MediaBrowserImplBase.this.dump();
                        }

                     }
                  }
               }
            });
         }
      }

      public void disconnect() {
         this.mState = 0;
         this.mHandler.post(new Runnable() {
            public void run() {
               if (MediaBrowserImplBase.this.mCallbacksMessenger != null) {
                  try {
                     MediaBrowserImplBase.this.mServiceBinderWrapper.disconnect(MediaBrowserImplBase.this.mCallbacksMessenger);
                  } catch (RemoteException var2) {
                     Log.w("MediaBrowserCompat", "RemoteException during connect for " + MediaBrowserImplBase.this.mServiceComponent);
                  }
               }

               int state = MediaBrowserImplBase.this.mState;
               MediaBrowserImplBase.this.forceCloseConnection();
               if (state != 0) {
                  MediaBrowserImplBase.this.mState = state;
               }

               if (MediaBrowserCompat.DEBUG) {
                  Log.d("MediaBrowserCompat", "disconnect...");
                  MediaBrowserImplBase.this.dump();
               }

            }
         });
      }

      void forceCloseConnection() {
         if (this.mServiceConnection != null) {
            this.mContext.unbindService(this.mServiceConnection);
         }

         this.mState = 1;
         this.mServiceConnection = null;
         this.mServiceBinderWrapper = null;
         this.mCallbacksMessenger = null;
         this.mHandler.setCallbacksMessenger((Messenger)null);
         this.mRootId = null;
         this.mMediaSessionToken = null;
      }

      public boolean isConnected() {
         return this.mState == 3;
      }

      @NonNull
      public ComponentName getServiceComponent() {
         if (!this.isConnected()) {
            throw new IllegalStateException("getServiceComponent() called while not connected (state=" + this.mState + ")");
         } else {
            return this.mServiceComponent;
         }
      }

      @NonNull
      public String getRoot() {
         if (!this.isConnected()) {
            throw new IllegalStateException("getRoot() called while not connected(state=" + getStateLabel(this.mState) + ")");
         } else {
            return this.mRootId;
         }
      }

      @Nullable
      public Bundle getExtras() {
         if (!this.isConnected()) {
            throw new IllegalStateException("getExtras() called while not connected (state=" + getStateLabel(this.mState) + ")");
         } else {
            return this.mExtras;
         }
      }

      @NonNull
      public MediaSessionCompat.Token getSessionToken() {
         if (!this.isConnected()) {
            throw new IllegalStateException("getSessionToken() called while not connected(state=" + this.mState + ")");
         } else {
            return this.mMediaSessionToken;
         }
      }

      public void subscribe(@NonNull String parentId, Bundle options, @NonNull MediaBrowserCompat.SubscriptionCallback callback) {
         MediaBrowserCompat.Subscription sub = (MediaBrowserCompat.Subscription)this.mSubscriptions.get(parentId);
         if (sub == null) {
            sub = new MediaBrowserCompat.Subscription();
            this.mSubscriptions.put(parentId, sub);
         }

         Bundle copiedOptions = options == null ? null : new Bundle(options);
         sub.putCallback(copiedOptions, callback);
         if (this.isConnected()) {
            try {
               this.mServiceBinderWrapper.addSubscription(parentId, callback.mToken, copiedOptions, this.mCallbacksMessenger);
            } catch (RemoteException var7) {
               Log.d("MediaBrowserCompat", "addSubscription failed with RemoteException parentId=" + parentId);
            }
         }

      }

      public void unsubscribe(@NonNull String parentId, MediaBrowserCompat.SubscriptionCallback callback) {
         MediaBrowserCompat.Subscription sub = (MediaBrowserCompat.Subscription)this.mSubscriptions.get(parentId);
         if (sub != null) {
            try {
               if (callback == null) {
                  if (this.isConnected()) {
                     this.mServiceBinderWrapper.removeSubscription(parentId, (IBinder)null, this.mCallbacksMessenger);
                  }
               } else {
                  List callbacks = sub.getCallbacks();
                  List optionsList = sub.getOptionsList();

                  for(int i = callbacks.size() - 1; i >= 0; --i) {
                     if (callbacks.get(i) == callback) {
                        if (this.isConnected()) {
                           this.mServiceBinderWrapper.removeSubscription(parentId, callback.mToken, this.mCallbacksMessenger);
                        }

                        callbacks.remove(i);
                        optionsList.remove(i);
                     }
                  }
               }
            } catch (RemoteException var7) {
               Log.d("MediaBrowserCompat", "removeSubscription failed with RemoteException parentId=" + parentId);
            }

            if (sub.isEmpty() || callback == null) {
               this.mSubscriptions.remove(parentId);
            }

         }
      }

      public void getItem(@NonNull final String mediaId, @NonNull final MediaBrowserCompat.ItemCallback cb) {
         if (TextUtils.isEmpty(mediaId)) {
            throw new IllegalArgumentException("mediaId is empty");
         } else if (cb == null) {
            throw new IllegalArgumentException("cb is null");
         } else if (!this.isConnected()) {
            Log.i("MediaBrowserCompat", "Not connected, unable to retrieve the MediaItem.");
            this.mHandler.post(new Runnable() {
               public void run() {
                  cb.onError(mediaId);
               }
            });
         } else {
            MediaBrowserCompat.ItemReceiver receiver = new MediaBrowserCompat.ItemReceiver(mediaId, cb, this.mHandler);

            try {
               this.mServiceBinderWrapper.getMediaItem(mediaId, receiver, this.mCallbacksMessenger);
            } catch (RemoteException var5) {
               Log.i("MediaBrowserCompat", "Remote error getting media item: " + mediaId);
               this.mHandler.post(new Runnable() {
                  public void run() {
                     cb.onError(mediaId);
                  }
               });
            }

         }
      }

      public void search(@NonNull final String query, final Bundle extras, @NonNull final MediaBrowserCompat.SearchCallback callback) {
         if (!this.isConnected()) {
            throw new IllegalStateException("search() called while not connected (state=" + getStateLabel(this.mState) + ")");
         } else {
            MediaBrowserCompat.SearchResultReceiver receiver = new MediaBrowserCompat.SearchResultReceiver(query, extras, callback, this.mHandler);

            try {
               this.mServiceBinderWrapper.search(query, extras, receiver, this.mCallbacksMessenger);
            } catch (RemoteException var6) {
               Log.i("MediaBrowserCompat", "Remote error searching items with query: " + query, var6);
               this.mHandler.post(new Runnable() {
                  public void run() {
                     callback.onError(query, extras);
                  }
               });
            }

         }
      }

      public void sendCustomAction(@NonNull final String action, final Bundle extras, @Nullable final MediaBrowserCompat.CustomActionCallback callback) {
         if (!this.isConnected()) {
            throw new IllegalStateException("Cannot send a custom action (" + action + ") with " + "extras " + extras + " because the browser is not connected to the " + "service.");
         } else {
            MediaBrowserCompat.CustomActionResultReceiver receiver = new MediaBrowserCompat.CustomActionResultReceiver(action, extras, callback, this.mHandler);

            try {
               this.mServiceBinderWrapper.sendCustomAction(action, extras, receiver, this.mCallbacksMessenger);
            } catch (RemoteException var6) {
               Log.i("MediaBrowserCompat", "Remote error sending a custom action: action=" + action + ", extras=" + extras, var6);
               if (callback != null) {
                  this.mHandler.post(new Runnable() {
                     public void run() {
                        callback.onError(action, extras, (Bundle)null);
                     }
                  });
               }
            }

         }
      }

      public void onServiceConnected(Messenger callback, String root, MediaSessionCompat.Token session, Bundle extra) {
         if (this.isCurrent(callback, "onConnect")) {
            if (this.mState != 2) {
               Log.w("MediaBrowserCompat", "onConnect from service while mState=" + getStateLabel(this.mState) + "... ignoring");
            } else {
               this.mRootId = root;
               this.mMediaSessionToken = session;
               this.mExtras = extra;
               this.mState = 3;
               if (MediaBrowserCompat.DEBUG) {
                  Log.d("MediaBrowserCompat", "ServiceCallbacks.onConnect...");
                  this.dump();
               }

               this.mCallback.onConnected();

               try {
                  Iterator var5 = this.mSubscriptions.entrySet().iterator();

                  while(var5.hasNext()) {
                     Entry subscriptionEntry = (Entry)var5.next();
                     String id = (String)subscriptionEntry.getKey();
                     MediaBrowserCompat.Subscription sub = (MediaBrowserCompat.Subscription)subscriptionEntry.getValue();
                     List callbackList = sub.getCallbacks();
                     List optionsList = sub.getOptionsList();

                     for(int i = 0; i < callbackList.size(); ++i) {
                        this.mServiceBinderWrapper.addSubscription(id, ((MediaBrowserCompat.SubscriptionCallback)callbackList.get(i)).mToken, (Bundle)optionsList.get(i), this.mCallbacksMessenger);
                     }
                  }
               } catch (RemoteException var12) {
                  Log.d("MediaBrowserCompat", "addSubscription failed with RemoteException.");
               }

            }
         }
      }

      public void onConnectionFailed(Messenger callback) {
         Log.e("MediaBrowserCompat", "onConnectFailed for " + this.mServiceComponent);
         if (this.isCurrent(callback, "onConnectFailed")) {
            if (this.mState != 2) {
               Log.w("MediaBrowserCompat", "onConnect from service while mState=" + getStateLabel(this.mState) + "... ignoring");
            } else {
               this.forceCloseConnection();
               this.mCallback.onConnectionFailed();
            }
         }
      }

      public void onLoadChildren(Messenger callback, String parentId, List list, Bundle options) {
         if (this.isCurrent(callback, "onLoadChildren")) {
            if (MediaBrowserCompat.DEBUG) {
               Log.d("MediaBrowserCompat", "onLoadChildren for " + this.mServiceComponent + " id=" + parentId);
            }

            MediaBrowserCompat.Subscription subscription = (MediaBrowserCompat.Subscription)this.mSubscriptions.get(parentId);
            if (subscription == null) {
               if (MediaBrowserCompat.DEBUG) {
                  Log.d("MediaBrowserCompat", "onLoadChildren for id that isn't subscribed id=" + parentId);
               }

            } else {
               MediaBrowserCompat.SubscriptionCallback subscriptionCallback = subscription.getCallback(options);
               if (subscriptionCallback != null) {
                  if (options == null) {
                     if (list == null) {
                        subscriptionCallback.onError(parentId);
                     } else {
                        subscriptionCallback.onChildrenLoaded(parentId, list);
                     }
                  } else if (list == null) {
                     subscriptionCallback.onError(parentId, options);
                  } else {
                     subscriptionCallback.onChildrenLoaded(parentId, list, options);
                  }
               }

            }
         }
      }

      private static String getStateLabel(int state) {
         switch(state) {
         case 0:
            return "CONNECT_STATE_DISCONNECTING";
         case 1:
            return "CONNECT_STATE_DISCONNECTED";
         case 2:
            return "CONNECT_STATE_CONNECTING";
         case 3:
            return "CONNECT_STATE_CONNECTED";
         case 4:
            return "CONNECT_STATE_SUSPENDED";
         default:
            return "UNKNOWN/" + state;
         }
      }

      private boolean isCurrent(Messenger callback, String funcName) {
         if (this.mCallbacksMessenger == callback && this.mState != 0 && this.mState != 1) {
            return true;
         } else {
            if (this.mState != 0 && this.mState != 1) {
               Log.i("MediaBrowserCompat", funcName + " for " + this.mServiceComponent + " with mCallbacksMessenger=" + this.mCallbacksMessenger + " this=" + this);
            }

            return false;
         }
      }

      void dump() {
         Log.d("MediaBrowserCompat", "MediaBrowserCompat...");
         Log.d("MediaBrowserCompat", "  mServiceComponent=" + this.mServiceComponent);
         Log.d("MediaBrowserCompat", "  mCallback=" + this.mCallback);
         Log.d("MediaBrowserCompat", "  mRootHints=" + this.mRootHints);
         Log.d("MediaBrowserCompat", "  mState=" + getStateLabel(this.mState));
         Log.d("MediaBrowserCompat", "  mServiceConnection=" + this.mServiceConnection);
         Log.d("MediaBrowserCompat", "  mServiceBinderWrapper=" + this.mServiceBinderWrapper);
         Log.d("MediaBrowserCompat", "  mCallbacksMessenger=" + this.mCallbacksMessenger);
         Log.d("MediaBrowserCompat", "  mRootId=" + this.mRootId);
         Log.d("MediaBrowserCompat", "  mMediaSessionToken=" + this.mMediaSessionToken);
      }

      private class MediaServiceConnection implements ServiceConnection {
         public void onServiceConnected(final ComponentName name, final IBinder binder) {
            this.postOrRun(new Runnable() {
               public void run() {
                  if (MediaBrowserCompat.DEBUG) {
                     Log.d("MediaBrowserCompat", "MediaServiceConnection.onServiceConnected name=" + name + " binder=" + binder);
                     MediaBrowserImplBase.this.dump();
                  }

                  if (MediaServiceConnection.this.isCurrent("onServiceConnected")) {
                     MediaBrowserImplBase.this.mServiceBinderWrapper = new MediaBrowserCompat.ServiceBinderWrapper(binder, MediaBrowserImplBase.this.mRootHints);
                     MediaBrowserImplBase.this.mCallbacksMessenger = new Messenger(MediaBrowserImplBase.this.mHandler);
                     MediaBrowserImplBase.this.mHandler.setCallbacksMessenger(MediaBrowserImplBase.this.mCallbacksMessenger);
                     MediaBrowserImplBase.this.mState = 2;

                     try {
                        if (MediaBrowserCompat.DEBUG) {
                           Log.d("MediaBrowserCompat", "ServiceCallbacks.onConnect...");
                           MediaBrowserImplBase.this.dump();
                        }

                        MediaBrowserImplBase.this.mServiceBinderWrapper.connect(MediaBrowserImplBase.this.mContext, MediaBrowserImplBase.this.mCallbacksMessenger);
                     } catch (RemoteException var2) {
                        Log.w("MediaBrowserCompat", "RemoteException during connect for " + MediaBrowserImplBase.this.mServiceComponent);
                        if (MediaBrowserCompat.DEBUG) {
                           Log.d("MediaBrowserCompat", "ServiceCallbacks.onConnect...");
                           MediaBrowserImplBase.this.dump();
                        }
                     }

                  }
               }
            });
         }

         public void onServiceDisconnected(final ComponentName name) {
            this.postOrRun(new Runnable() {
               public void run() {
                  if (MediaBrowserCompat.DEBUG) {
                     Log.d("MediaBrowserCompat", "MediaServiceConnection.onServiceDisconnected name=" + name + " this=" + this + " mServiceConnection=" + MediaBrowserImplBase.this.mServiceConnection);
                     MediaBrowserImplBase.this.dump();
                  }

                  if (MediaServiceConnection.this.isCurrent("onServiceDisconnected")) {
                     MediaBrowserImplBase.this.mServiceBinderWrapper = null;
                     MediaBrowserImplBase.this.mCallbacksMessenger = null;
                     MediaBrowserImplBase.this.mHandler.setCallbacksMessenger((Messenger)null);
                     MediaBrowserImplBase.this.mState = 4;
                     MediaBrowserImplBase.this.mCallback.onConnectionSuspended();
                  }
               }
            });
         }

         private void postOrRun(Runnable r) {
            if (Thread.currentThread() == MediaBrowserImplBase.this.mHandler.getLooper().getThread()) {
               r.run();
            } else {
               MediaBrowserImplBase.this.mHandler.post(r);
            }

         }

         boolean isCurrent(String funcName) {
            if (MediaBrowserImplBase.this.mServiceConnection == this && MediaBrowserImplBase.this.mState != 0 && MediaBrowserImplBase.this.mState != 1) {
               return true;
            } else {
               if (MediaBrowserImplBase.this.mState != 0 && MediaBrowserImplBase.this.mState != 1) {
                  Log.i("MediaBrowserCompat", funcName + " for " + MediaBrowserImplBase.this.mServiceComponent + " with mServiceConnection=" + MediaBrowserImplBase.this.mServiceConnection + " this=" + this);
               }

               return false;
            }
         }
      }
   }

   interface MediaBrowserServiceCallbackImpl {
      void onServiceConnected(Messenger var1, String var2, MediaSessionCompat.Token var3, Bundle var4);

      void onConnectionFailed(Messenger var1);

      void onLoadChildren(Messenger var1, String var2, List var3, Bundle var4);
   }

   interface MediaBrowserImpl {
      void connect();

      void disconnect();

      boolean isConnected();

      ComponentName getServiceComponent();

      @NonNull
      String getRoot();

      @Nullable
      Bundle getExtras();

      @NonNull
      MediaSessionCompat.Token getSessionToken();

      void subscribe(@NonNull String var1, Bundle var2, @NonNull MediaBrowserCompat.SubscriptionCallback var3);

      void unsubscribe(@NonNull String var1, MediaBrowserCompat.SubscriptionCallback var2);

      void getItem(@NonNull String var1, @NonNull MediaBrowserCompat.ItemCallback var2);

      void search(@NonNull String var1, Bundle var2, @NonNull MediaBrowserCompat.SearchCallback var3);

      void sendCustomAction(@NonNull String var1, Bundle var2, @Nullable MediaBrowserCompat.CustomActionCallback var3);
   }

   public abstract static class CustomActionCallback {
      public void onProgressUpdate(String action, Bundle extras, Bundle data) {
      }

      public void onResult(String action, Bundle extras, Bundle resultData) {
      }

      public void onError(String action, Bundle extras, Bundle data) {
      }
   }

   public abstract static class SearchCallback {
      public void onSearchResult(@NonNull String query, Bundle extras, @NonNull List items) {
      }

      public void onError(@NonNull String query, Bundle extras) {
      }
   }

   public abstract static class ItemCallback {
      final Object mItemCallbackObj;

      public ItemCallback() {
         if (VERSION.SDK_INT >= 23) {
            this.mItemCallbackObj = MediaBrowserCompatApi23.createItemCallback(new MediaBrowserCompat.ItemCallback.StubApi23());
         } else {
            this.mItemCallbackObj = null;
         }

      }

      public void onItemLoaded(MediaBrowserCompat.MediaItem item) {
      }

      public void onError(@NonNull String itemId) {
      }

      private class StubApi23 implements MediaBrowserCompatApi23.ItemCallback {
         public void onItemLoaded(Parcel itemParcel) {
            if (itemParcel == null) {
               ItemCallback.this.onItemLoaded((MediaBrowserCompat.MediaItem)null);
            } else {
               itemParcel.setDataPosition(0);
               MediaBrowserCompat.MediaItem item = (MediaBrowserCompat.MediaItem)MediaBrowserCompat.MediaItem.CREATOR.createFromParcel(itemParcel);
               itemParcel.recycle();
               ItemCallback.this.onItemLoaded(item);
            }

         }

         public void onError(@NonNull String itemId) {
            ItemCallback.this.onError(itemId);
         }
      }
   }

   public abstract static class SubscriptionCallback {
      private final Object mSubscriptionCallbackObj;
      private final IBinder mToken;
      WeakReference mSubscriptionRef;

      public SubscriptionCallback() {
         if (VERSION.SDK_INT >= 26) {
            this.mSubscriptionCallbackObj = MediaBrowserCompatApi24.createSubscriptionCallback(new MediaBrowserCompat.SubscriptionCallback.StubApi24());
            this.mToken = null;
         } else if (VERSION.SDK_INT >= 21) {
            this.mSubscriptionCallbackObj = MediaBrowserCompatApi21.createSubscriptionCallback(new MediaBrowserCompat.SubscriptionCallback.StubApi21());
            this.mToken = new Binder();
         } else {
            this.mSubscriptionCallbackObj = null;
            this.mToken = new Binder();
         }

      }

      public void onChildrenLoaded(@NonNull String parentId, @NonNull List children) {
      }

      public void onChildrenLoaded(@NonNull String parentId, @NonNull List children, @NonNull Bundle options) {
      }

      public void onError(@NonNull String parentId) {
      }

      public void onError(@NonNull String parentId, @NonNull Bundle options) {
      }

      private void setSubscription(MediaBrowserCompat.Subscription subscription) {
         this.mSubscriptionRef = new WeakReference(subscription);
      }

      private class StubApi24 extends MediaBrowserCompat.SubscriptionCallback.StubApi21 implements MediaBrowserCompatApi24.SubscriptionCallback {
         StubApi24() {
            super();
         }

         public void onChildrenLoaded(@NonNull String parentId, List children, @NonNull Bundle options) {
            SubscriptionCallback.this.onChildrenLoaded(parentId, MediaBrowserCompat.MediaItem.fromMediaItemList(children), options);
         }

         public void onError(@NonNull String parentId, @NonNull Bundle options) {
            SubscriptionCallback.this.onError(parentId, options);
         }
      }

      private class StubApi21 implements MediaBrowserCompatApi21.SubscriptionCallback {
         public void onChildrenLoaded(@NonNull String parentId, List children) {
            MediaBrowserCompat.Subscription sub = SubscriptionCallback.this.mSubscriptionRef == null ? null : (MediaBrowserCompat.Subscription)SubscriptionCallback.this.mSubscriptionRef.get();
            if (sub == null) {
               SubscriptionCallback.this.onChildrenLoaded(parentId, MediaBrowserCompat.MediaItem.fromMediaItemList(children));
            } else {
               List itemList = MediaBrowserCompat.MediaItem.fromMediaItemList(children);
               List callbacks = sub.getCallbacks();
               List optionsList = sub.getOptionsList();

               for(int i = 0; i < callbacks.size(); ++i) {
                  Bundle options = (Bundle)optionsList.get(i);
                  if (options == null) {
                     SubscriptionCallback.this.onChildrenLoaded(parentId, itemList);
                  } else {
                     SubscriptionCallback.this.onChildrenLoaded(parentId, this.applyOptions(itemList, options), options);
                  }
               }
            }

         }

         public void onError(@NonNull String parentId) {
            SubscriptionCallback.this.onError(parentId);
         }

         List applyOptions(List list, Bundle options) {
            if (list == null) {
               return null;
            } else {
               int page = options.getInt("android.media.browse.extra.PAGE", -1);
               int pageSize = options.getInt("android.media.browse.extra.PAGE_SIZE", -1);
               if (page == -1 && pageSize == -1) {
                  return list;
               } else {
                  int fromIndex = pageSize * page;
                  int toIndex = fromIndex + pageSize;
                  if (page >= 0 && pageSize >= 1 && fromIndex < list.size()) {
                     if (toIndex > list.size()) {
                        toIndex = list.size();
                     }

                     return list.subList(fromIndex, toIndex);
                  } else {
                     return Collections.EMPTY_LIST;
                  }
               }
            }
         }
      }
   }

   public static class ConnectionCallback {
      final Object mConnectionCallbackObj;
      MediaBrowserCompat.ConnectionCallback.ConnectionCallbackInternal mConnectionCallbackInternal;

      public ConnectionCallback() {
         if (VERSION.SDK_INT >= 21) {
            this.mConnectionCallbackObj = MediaBrowserCompatApi21.createConnectionCallback(new MediaBrowserCompat.ConnectionCallback.StubApi21());
         } else {
            this.mConnectionCallbackObj = null;
         }

      }

      public void onConnected() {
      }

      public void onConnectionSuspended() {
      }

      public void onConnectionFailed() {
      }

      void setInternalConnectionCallback(MediaBrowserCompat.ConnectionCallback.ConnectionCallbackInternal connectionCallbackInternal) {
         this.mConnectionCallbackInternal = connectionCallbackInternal;
      }

      private class StubApi21 implements MediaBrowserCompatApi21.ConnectionCallback {
         public void onConnected() {
            if (ConnectionCallback.this.mConnectionCallbackInternal != null) {
               ConnectionCallback.this.mConnectionCallbackInternal.onConnected();
            }

            ConnectionCallback.this.onConnected();
         }

         public void onConnectionSuspended() {
            if (ConnectionCallback.this.mConnectionCallbackInternal != null) {
               ConnectionCallback.this.mConnectionCallbackInternal.onConnectionSuspended();
            }

            ConnectionCallback.this.onConnectionSuspended();
         }

         public void onConnectionFailed() {
            if (ConnectionCallback.this.mConnectionCallbackInternal != null) {
               ConnectionCallback.this.mConnectionCallbackInternal.onConnectionFailed();
            }

            ConnectionCallback.this.onConnectionFailed();
         }
      }

      interface ConnectionCallbackInternal {
         void onConnected();

         void onConnectionSuspended();

         void onConnectionFailed();
      }
   }

   public static class MediaItem implements Parcelable {
      private final int mFlags;
      private final MediaDescriptionCompat mDescription;
      public static final int FLAG_BROWSABLE = 1;
      public static final int FLAG_PLAYABLE = 2;
      public static final Creator CREATOR = new Creator() {
         public MediaBrowserCompat.MediaItem createFromParcel(Parcel in) {
            return new MediaBrowserCompat.MediaItem(in);
         }

         public MediaBrowserCompat.MediaItem[] newArray(int size) {
            return new MediaBrowserCompat.MediaItem[size];
         }
      };

      public static MediaBrowserCompat.MediaItem fromMediaItem(Object itemObj) {
         if (itemObj != null && VERSION.SDK_INT >= 21) {
            int flags = MediaBrowserCompatApi21.MediaItem.getFlags(itemObj);
            MediaDescriptionCompat description = MediaDescriptionCompat.fromMediaDescription(MediaBrowserCompatApi21.MediaItem.getDescription(itemObj));
            return new MediaBrowserCompat.MediaItem(description, flags);
         } else {
            return null;
         }
      }

      public static List fromMediaItemList(List itemList) {
         if (itemList != null && VERSION.SDK_INT >= 21) {
            List items = new ArrayList(itemList.size());
            Iterator var2 = itemList.iterator();

            while(var2.hasNext()) {
               Object itemObj = var2.next();
               items.add(fromMediaItem(itemObj));
            }

            return items;
         } else {
            return null;
         }
      }

      public MediaItem(@NonNull MediaDescriptionCompat description, int flags) {
         if (description == null) {
            throw new IllegalArgumentException("description cannot be null");
         } else if (TextUtils.isEmpty(description.getMediaId())) {
            throw new IllegalArgumentException("description must have a non-empty media id");
         } else {
            this.mFlags = flags;
            this.mDescription = description;
         }
      }

      MediaItem(Parcel in) {
         this.mFlags = in.readInt();
         this.mDescription = (MediaDescriptionCompat)MediaDescriptionCompat.CREATOR.createFromParcel(in);
      }

      public int describeContents() {
         return 0;
      }

      public void writeToParcel(Parcel out, int flags) {
         out.writeInt(this.mFlags);
         this.mDescription.writeToParcel(out, flags);
      }

      public String toString() {
         StringBuilder sb = new StringBuilder("MediaItem{");
         sb.append("mFlags=").append(this.mFlags);
         sb.append(", mDescription=").append(this.mDescription);
         sb.append('}');
         return sb.toString();
      }

      public int getFlags() {
         return this.mFlags;
      }

      public boolean isBrowsable() {
         return (this.mFlags & 1) != 0;
      }

      public boolean isPlayable() {
         return (this.mFlags & 2) != 0;
      }

      @NonNull
      public MediaDescriptionCompat getDescription() {
         return this.mDescription;
      }

      @Nullable
      public String getMediaId() {
         return this.mDescription.getMediaId();
      }

      @Retention(RetentionPolicy.SOURCE)
      @RestrictTo({Scope.LIBRARY_GROUP})
      public @interface Flags {
      }
   }
}
