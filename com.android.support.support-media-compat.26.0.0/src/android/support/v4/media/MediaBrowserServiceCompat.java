package android.support.v4.media;

import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
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
import android.support.v4.util.Pair;
import android.text.TextUtils;
import android.util.Log;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public abstract class MediaBrowserServiceCompat extends Service {
   static final String TAG = "MBServiceCompat";
   static final boolean DEBUG = Log.isLoggable("MBServiceCompat", 3);
   private static final float EPSILON = 1.0E-5F;
   private MediaBrowserServiceCompat.MediaBrowserServiceImpl mImpl;
   public static final String SERVICE_INTERFACE = "android.media.browse.MediaBrowserService";
   @RestrictTo({Scope.LIBRARY_GROUP})
   public static final String KEY_MEDIA_ITEM = "media_item";
   @RestrictTo({Scope.LIBRARY_GROUP})
   public static final String KEY_SEARCH_RESULTS = "search_results";
   static final int RESULT_FLAG_OPTION_NOT_HANDLED = 1;
   static final int RESULT_FLAG_ON_LOAD_ITEM_NOT_IMPLEMENTED = 2;
   static final int RESULT_FLAG_ON_SEARCH_NOT_IMPLEMENTED = 4;
   static final int RESULT_ERROR = -1;
   static final int RESULT_OK = 0;
   static final int RESULT_PROGRESS_UPDATE = 1;
   final ArrayMap mConnections = new ArrayMap();
   MediaBrowserServiceCompat.ConnectionRecord mCurConnection;
   final MediaBrowserServiceCompat.ServiceHandler mHandler = new MediaBrowserServiceCompat.ServiceHandler();
   MediaSessionCompat.Token mSession;

   public void onCreate() {
      super.onCreate();
      if (VERSION.SDK_INT >= 26) {
         this.mImpl = new MediaBrowserServiceCompat.MediaBrowserServiceImplApi24();
      } else if (VERSION.SDK_INT >= 23) {
         this.mImpl = new MediaBrowserServiceCompat.MediaBrowserServiceImplApi23();
      } else if (VERSION.SDK_INT >= 21) {
         this.mImpl = new MediaBrowserServiceCompat.MediaBrowserServiceImplApi21();
      } else {
         this.mImpl = new MediaBrowserServiceCompat.MediaBrowserServiceImplBase();
      }

      this.mImpl.onCreate();
   }

   public IBinder onBind(Intent intent) {
      return this.mImpl.onBind(intent);
   }

   public void dump(FileDescriptor fd, PrintWriter writer, String[] args) {
   }

   @Nullable
   public abstract MediaBrowserServiceCompat.BrowserRoot onGetRoot(@NonNull String var1, int var2, @Nullable Bundle var3);

   public abstract void onLoadChildren(@NonNull String var1, @NonNull MediaBrowserServiceCompat.Result var2);

   public void onLoadChildren(@NonNull String parentId, @NonNull MediaBrowserServiceCompat.Result result, @NonNull Bundle options) {
      result.setFlags(1);
      this.onLoadChildren(parentId, result);
   }

   public void onLoadItem(String itemId, @NonNull MediaBrowserServiceCompat.Result result) {
      result.setFlags(2);
      result.sendResult((Object)null);
   }

   public void onSearch(@NonNull String query, Bundle extras, @NonNull MediaBrowserServiceCompat.Result result) {
      result.setFlags(4);
      result.sendResult((Object)null);
   }

   public void onCustomAction(@NonNull String action, Bundle extras, @NonNull MediaBrowserServiceCompat.Result result) {
      result.sendError((Bundle)null);
   }

   public void setSessionToken(MediaSessionCompat.Token token) {
      if (token == null) {
         throw new IllegalArgumentException("Session token may not be null.");
      } else if (this.mSession != null) {
         throw new IllegalStateException("The session token has already been set.");
      } else {
         this.mSession = token;
         this.mImpl.setSessionToken(token);
      }
   }

   @Nullable
   public MediaSessionCompat.Token getSessionToken() {
      return this.mSession;
   }

   public final Bundle getBrowserRootHints() {
      return this.mImpl.getBrowserRootHints();
   }

   public void notifyChildrenChanged(@NonNull String parentId) {
      if (parentId == null) {
         throw new IllegalArgumentException("parentId cannot be null in notifyChildrenChanged");
      } else {
         this.mImpl.notifyChildrenChanged(parentId, (Bundle)null);
      }
   }

   public void notifyChildrenChanged(@NonNull String parentId, @NonNull Bundle options) {
      if (parentId == null) {
         throw new IllegalArgumentException("parentId cannot be null in notifyChildrenChanged");
      } else if (options == null) {
         throw new IllegalArgumentException("options cannot be null in notifyChildrenChanged");
      } else {
         this.mImpl.notifyChildrenChanged(parentId, options);
      }
   }

   boolean isValidPackage(String pkg, int uid) {
      if (pkg == null) {
         return false;
      } else {
         PackageManager pm = this.getPackageManager();
         String[] packages = pm.getPackagesForUid(uid);
         int N = packages.length;

         for(int i = 0; i < N; ++i) {
            if (packages[i].equals(pkg)) {
               return true;
            }
         }

         return false;
      }
   }

   void addSubscription(String id, MediaBrowserServiceCompat.ConnectionRecord connection, IBinder token, Bundle options) {
      List callbackList = (List)connection.subscriptions.get(id);
      if (callbackList == null) {
         callbackList = new ArrayList();
      }

      Iterator var6 = ((List)callbackList).iterator();

      Pair callback;
      do {
         if (!var6.hasNext()) {
            ((List)callbackList).add(new Pair(token, options));
            connection.subscriptions.put(id, callbackList);
            this.performLoadChildren(id, connection, options);
            return;
         }

         callback = (Pair)var6.next();
      } while(token != callback.first || !MediaBrowserCompatUtils.areSameOptions(options, (Bundle)callback.second));

   }

   boolean removeSubscription(String id, MediaBrowserServiceCompat.ConnectionRecord connection, IBinder token) {
      if (token == null) {
         return connection.subscriptions.remove(id) != null;
      } else {
         boolean removed = false;
         List callbackList = (List)connection.subscriptions.get(id);
         if (callbackList != null) {
            Iterator iter = callbackList.iterator();

            while(iter.hasNext()) {
               if (token == ((Pair)iter.next()).first) {
                  removed = true;
                  iter.remove();
               }
            }

            if (callbackList.size() == 0) {
               connection.subscriptions.remove(id);
            }
         }

         return removed;
      }
   }

   void performLoadChildren(final String parentId, final MediaBrowserServiceCompat.ConnectionRecord connection, final Bundle options) {
      MediaBrowserServiceCompat.Result result = new MediaBrowserServiceCompat.Result(parentId) {
         void onResultSent(List list) {
            if (MediaBrowserServiceCompat.this.mConnections.get(connection.callbacks.asBinder()) != connection) {
               if (MediaBrowserServiceCompat.DEBUG) {
                  Log.d("MBServiceCompat", "Not sending onLoadChildren result for connection that has been disconnected. pkg=" + connection.pkg + " id=" + parentId);
               }

            } else {
               List filteredList = (this.getFlags() & 1) != 0 ? MediaBrowserServiceCompat.this.applyOptions(list, options) : list;

               try {
                  connection.callbacks.onLoadChildren(parentId, filteredList, options);
               } catch (RemoteException var4) {
                  Log.w("MBServiceCompat", "Calling onLoadChildren() failed for id=" + parentId + " package=" + connection.pkg);
               }

            }
         }
      };
      this.mCurConnection = connection;
      if (options == null) {
         this.onLoadChildren(parentId, result);
      } else {
         this.onLoadChildren(parentId, result, options);
      }

      this.mCurConnection = null;
      if (!result.isDone()) {
         throw new IllegalStateException("onLoadChildren must call detach() or sendResult() before returning for package=" + connection.pkg + " id=" + parentId);
      }
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

   void performLoadItem(String itemId, MediaBrowserServiceCompat.ConnectionRecord connection, final ResultReceiver receiver) {
      MediaBrowserServiceCompat.Result result = new MediaBrowserServiceCompat.Result(itemId) {
         void onResultSent(MediaBrowserCompat.MediaItem item) {
            if ((this.getFlags() & 2) != 0) {
               receiver.send(-1, (Bundle)null);
            } else {
               Bundle bundle = new Bundle();
               bundle.putParcelable("media_item", item);
               receiver.send(0, bundle);
            }
         }
      };
      this.mCurConnection = connection;
      this.onLoadItem(itemId, result);
      this.mCurConnection = null;
      if (!result.isDone()) {
         throw new IllegalStateException("onLoadItem must call detach() or sendResult() before returning for id=" + itemId);
      }
   }

   void performSearch(String query, Bundle extras, MediaBrowserServiceCompat.ConnectionRecord connection, final ResultReceiver receiver) {
      MediaBrowserServiceCompat.Result result = new MediaBrowserServiceCompat.Result(query) {
         void onResultSent(List items) {
            if ((this.getFlags() & 4) == 0 && items != null) {
               Bundle bundle = new Bundle();
               bundle.putParcelableArray("search_results", (Parcelable[])items.toArray(new MediaBrowserCompat.MediaItem[0]));
               receiver.send(0, bundle);
            } else {
               receiver.send(-1, (Bundle)null);
            }
         }
      };
      this.mCurConnection = connection;
      this.onSearch(query, extras, result);
      this.mCurConnection = null;
      if (!result.isDone()) {
         throw new IllegalStateException("onSearch must call detach() or sendResult() before returning for query=" + query);
      }
   }

   void performCustomAction(String action, Bundle extras, MediaBrowserServiceCompat.ConnectionRecord connection, final ResultReceiver receiver) {
      MediaBrowserServiceCompat.Result result = new MediaBrowserServiceCompat.Result(action) {
         void onResultSent(Bundle result) {
            receiver.send(0, result);
         }

         void onProgressUpdateSent(Bundle data) {
            receiver.send(1, data);
         }

         void onErrorSent(Bundle data) {
            receiver.send(-1, data);
         }
      };
      this.mCurConnection = connection;
      this.onCustomAction(action, extras, result);
      this.mCurConnection = null;
      if (!result.isDone()) {
         throw new IllegalStateException("onCustomAction must call detach() or sendResult() or sendError() before returning for action=" + action + " extras=" + extras);
      }
   }

   public static final class BrowserRoot {
      public static final String EXTRA_RECENT = "android.service.media.extra.RECENT";
      public static final String EXTRA_OFFLINE = "android.service.media.extra.OFFLINE";
      public static final String EXTRA_SUGGESTED = "android.service.media.extra.SUGGESTED";
      /** @deprecated */
      @Deprecated
      public static final String EXTRA_SUGGESTION_KEYWORDS = "android.service.media.extra.SUGGESTION_KEYWORDS";
      private final String mRootId;
      private final Bundle mExtras;

      public BrowserRoot(@NonNull String rootId, @Nullable Bundle extras) {
         if (rootId == null) {
            throw new IllegalArgumentException("The root id in BrowserRoot cannot be null. Use null for BrowserRoot instead.");
         } else {
            this.mRootId = rootId;
            this.mExtras = extras;
         }
      }

      public String getRootId() {
         return this.mRootId;
      }

      public Bundle getExtras() {
         return this.mExtras;
      }
   }

   private static class ServiceCallbacksCompat implements MediaBrowserServiceCompat.ServiceCallbacks {
      final Messenger mCallbacks;

      ServiceCallbacksCompat(Messenger callbacks) {
         this.mCallbacks = callbacks;
      }

      public IBinder asBinder() {
         return this.mCallbacks.getBinder();
      }

      public void onConnect(String root, MediaSessionCompat.Token session, Bundle extras) throws RemoteException {
         if (extras == null) {
            extras = new Bundle();
         }

         extras.putInt("extra_service_version", 1);
         Bundle data = new Bundle();
         data.putString("data_media_item_id", root);
         data.putParcelable("data_media_session_token", session);
         data.putBundle("data_root_hints", extras);
         this.sendRequest(1, data);
      }

      public void onConnectFailed() throws RemoteException {
         this.sendRequest(2, (Bundle)null);
      }

      public void onLoadChildren(String mediaId, List list, Bundle options) throws RemoteException {
         Bundle data = new Bundle();
         data.putString("data_media_item_id", mediaId);
         data.putBundle("data_options", options);
         if (list != null) {
            data.putParcelableArrayList("data_media_item_list", list instanceof ArrayList ? (ArrayList)list : new ArrayList(list));
         }

         this.sendRequest(3, data);
      }

      private void sendRequest(int what, Bundle data) throws RemoteException {
         Message msg = Message.obtain();
         msg.what = what;
         msg.arg1 = 1;
         msg.setData(data);
         this.mCallbacks.send(msg);
      }
   }

   private interface ServiceCallbacks {
      IBinder asBinder();

      void onConnect(String var1, MediaSessionCompat.Token var2, Bundle var3) throws RemoteException;

      void onConnectFailed() throws RemoteException;

      void onLoadChildren(String var1, List var2, Bundle var3) throws RemoteException;
   }

   private class ServiceBinderImpl {
      public void connect(final String pkg, final int uid, final Bundle rootHints, final MediaBrowserServiceCompat.ServiceCallbacks callbacks) {
         if (!MediaBrowserServiceCompat.this.isValidPackage(pkg, uid)) {
            throw new IllegalArgumentException("Package/uid mismatch: uid=" + uid + " package=" + pkg);
         } else {
            MediaBrowserServiceCompat.this.mHandler.postOrRun(new Runnable() {
               public void run() {
                  IBinder b = callbacks.asBinder();
                  MediaBrowserServiceCompat.this.mConnections.remove(b);
                  MediaBrowserServiceCompat.ConnectionRecord connection = new MediaBrowserServiceCompat.ConnectionRecord();
                  connection.pkg = pkg;
                  connection.rootHints = rootHints;
                  connection.callbacks = callbacks;
                  connection.root = MediaBrowserServiceCompat.this.onGetRoot(pkg, uid, rootHints);
                  if (connection.root == null) {
                     Log.i("MBServiceCompat", "No root for client " + pkg + " from service " + this.getClass().getName());

                     try {
                        callbacks.onConnectFailed();
                     } catch (RemoteException var5) {
                        Log.w("MBServiceCompat", "Calling onConnectFailed() failed. Ignoring. pkg=" + pkg);
                     }
                  } else {
                     try {
                        MediaBrowserServiceCompat.this.mConnections.put(b, connection);
                        if (MediaBrowserServiceCompat.this.mSession != null) {
                           callbacks.onConnect(connection.root.getRootId(), MediaBrowserServiceCompat.this.mSession, connection.root.getExtras());
                        }
                     } catch (RemoteException var4) {
                        Log.w("MBServiceCompat", "Calling onConnect() failed. Dropping client. pkg=" + pkg);
                        MediaBrowserServiceCompat.this.mConnections.remove(b);
                     }
                  }

               }
            });
         }
      }

      public void disconnect(final MediaBrowserServiceCompat.ServiceCallbacks callbacks) {
         MediaBrowserServiceCompat.this.mHandler.postOrRun(new Runnable() {
            public void run() {
               IBinder b = callbacks.asBinder();
               MediaBrowserServiceCompat.ConnectionRecord old = (MediaBrowserServiceCompat.ConnectionRecord)MediaBrowserServiceCompat.this.mConnections.remove(b);
               if (old != null) {
                  ;
               }

            }
         });
      }

      public void addSubscription(final String id, final IBinder token, final Bundle options, final MediaBrowserServiceCompat.ServiceCallbacks callbacks) {
         MediaBrowserServiceCompat.this.mHandler.postOrRun(new Runnable() {
            public void run() {
               IBinder b = callbacks.asBinder();
               MediaBrowserServiceCompat.ConnectionRecord connection = (MediaBrowserServiceCompat.ConnectionRecord)MediaBrowserServiceCompat.this.mConnections.get(b);
               if (connection == null) {
                  Log.w("MBServiceCompat", "addSubscription for callback that isn't registered id=" + id);
               } else {
                  MediaBrowserServiceCompat.this.addSubscription(id, connection, token, options);
               }
            }
         });
      }

      public void removeSubscription(final String id, final IBinder token, final MediaBrowserServiceCompat.ServiceCallbacks callbacks) {
         MediaBrowserServiceCompat.this.mHandler.postOrRun(new Runnable() {
            public void run() {
               IBinder b = callbacks.asBinder();
               MediaBrowserServiceCompat.ConnectionRecord connection = (MediaBrowserServiceCompat.ConnectionRecord)MediaBrowserServiceCompat.this.mConnections.get(b);
               if (connection == null) {
                  Log.w("MBServiceCompat", "removeSubscription for callback that isn't registered id=" + id);
               } else {
                  if (!MediaBrowserServiceCompat.this.removeSubscription(id, connection, token)) {
                     Log.w("MBServiceCompat", "removeSubscription called for " + id + " which is not subscribed");
                  }

               }
            }
         });
      }

      public void getMediaItem(final String mediaId, final ResultReceiver receiver, final MediaBrowserServiceCompat.ServiceCallbacks callbacks) {
         if (!TextUtils.isEmpty(mediaId) && receiver != null) {
            MediaBrowserServiceCompat.this.mHandler.postOrRun(new Runnable() {
               public void run() {
                  IBinder b = callbacks.asBinder();
                  MediaBrowserServiceCompat.ConnectionRecord connection = (MediaBrowserServiceCompat.ConnectionRecord)MediaBrowserServiceCompat.this.mConnections.get(b);
                  if (connection == null) {
                     Log.w("MBServiceCompat", "getMediaItem for callback that isn't registered id=" + mediaId);
                  } else {
                     MediaBrowserServiceCompat.this.performLoadItem(mediaId, connection, receiver);
                  }
               }
            });
         }
      }

      public void registerCallbacks(final MediaBrowserServiceCompat.ServiceCallbacks callbacks, final Bundle rootHints) {
         MediaBrowserServiceCompat.this.mHandler.postOrRun(new Runnable() {
            public void run() {
               IBinder b = callbacks.asBinder();
               MediaBrowserServiceCompat.this.mConnections.remove(b);
               MediaBrowserServiceCompat.ConnectionRecord connection = new MediaBrowserServiceCompat.ConnectionRecord();
               connection.callbacks = callbacks;
               connection.rootHints = rootHints;
               MediaBrowserServiceCompat.this.mConnections.put(b, connection);
            }
         });
      }

      public void unregisterCallbacks(final MediaBrowserServiceCompat.ServiceCallbacks callbacks) {
         MediaBrowserServiceCompat.this.mHandler.postOrRun(new Runnable() {
            public void run() {
               IBinder b = callbacks.asBinder();
               MediaBrowserServiceCompat.this.mConnections.remove(b);
            }
         });
      }

      public void search(final String query, final Bundle extras, final ResultReceiver receiver, final MediaBrowserServiceCompat.ServiceCallbacks callbacks) {
         if (!TextUtils.isEmpty(query) && receiver != null) {
            MediaBrowserServiceCompat.this.mHandler.postOrRun(new Runnable() {
               public void run() {
                  IBinder b = callbacks.asBinder();
                  MediaBrowserServiceCompat.ConnectionRecord connection = (MediaBrowserServiceCompat.ConnectionRecord)MediaBrowserServiceCompat.this.mConnections.get(b);
                  if (connection == null) {
                     Log.w("MBServiceCompat", "search for callback that isn't registered query=" + query);
                  } else {
                     MediaBrowserServiceCompat.this.performSearch(query, extras, connection, receiver);
                  }
               }
            });
         }
      }

      public void sendCustomAction(final String action, final Bundle extras, final ResultReceiver receiver, final MediaBrowserServiceCompat.ServiceCallbacks callbacks) {
         if (!TextUtils.isEmpty(action) && receiver != null) {
            MediaBrowserServiceCompat.this.mHandler.postOrRun(new Runnable() {
               public void run() {
                  IBinder b = callbacks.asBinder();
                  MediaBrowserServiceCompat.ConnectionRecord connection = (MediaBrowserServiceCompat.ConnectionRecord)MediaBrowserServiceCompat.this.mConnections.get(b);
                  if (connection == null) {
                     Log.w("MBServiceCompat", "sendCustomAction for callback that isn't registered action=" + action + ", extras=" + extras);
                  } else {
                     MediaBrowserServiceCompat.this.performCustomAction(action, extras, connection, receiver);
                  }
               }
            });
         }
      }
   }

   public static class Result {
      private final Object mDebug;
      private boolean mDetachCalled;
      private boolean mSendResultCalled;
      private boolean mSendProgressUpdateCalled;
      private boolean mSendErrorCalled;
      private int mFlags;

      Result(Object debug) {
         this.mDebug = debug;
      }

      public void sendResult(Object result) {
         if (!this.mSendResultCalled && !this.mSendErrorCalled) {
            this.mSendResultCalled = true;
            this.onResultSent(result);
         } else {
            throw new IllegalStateException("sendResult() called when either sendResult() or sendError() had already been called for: " + this.mDebug);
         }
      }

      public void sendProgressUpdate(Bundle extras) {
         if (!this.mSendResultCalled && !this.mSendErrorCalled) {
            this.checkExtraFields(extras);
            this.mSendProgressUpdateCalled = true;
            this.onProgressUpdateSent(extras);
         } else {
            throw new IllegalStateException("sendProgressUpdate() called when either sendResult() or sendError() had already been called for: " + this.mDebug);
         }
      }

      public void sendError(Bundle extras) {
         if (!this.mSendResultCalled && !this.mSendErrorCalled) {
            this.mSendErrorCalled = true;
            this.onErrorSent(extras);
         } else {
            throw new IllegalStateException("sendError() called when either sendResult() or sendError() had already been called for: " + this.mDebug);
         }
      }

      public void detach() {
         if (this.mDetachCalled) {
            throw new IllegalStateException("detach() called when detach() had already been called for: " + this.mDebug);
         } else if (this.mSendResultCalled) {
            throw new IllegalStateException("detach() called when sendResult() had already been called for: " + this.mDebug);
         } else if (this.mSendErrorCalled) {
            throw new IllegalStateException("detach() called when sendError() had already been called for: " + this.mDebug);
         } else {
            this.mDetachCalled = true;
         }
      }

      boolean isDone() {
         return this.mDetachCalled || this.mSendResultCalled || this.mSendErrorCalled;
      }

      void setFlags(int flags) {
         this.mFlags = flags;
      }

      int getFlags() {
         return this.mFlags;
      }

      void onResultSent(Object result) {
      }

      void onProgressUpdateSent(Bundle extras) {
         throw new UnsupportedOperationException("It is not supported to send an interim update for " + this.mDebug);
      }

      void onErrorSent(Bundle extras) {
         throw new UnsupportedOperationException("It is not supported to send an error for " + this.mDebug);
      }

      private void checkExtraFields(Bundle extras) {
         if (extras != null) {
            if (extras.containsKey("android.media.browse.extra.DOWNLOAD_PROGRESS")) {
               float value = extras.getFloat("android.media.browse.extra.DOWNLOAD_PROGRESS");
               if (value < -1.0E-5F || value > 1.00001F) {
                  throw new IllegalArgumentException("The value of the EXTRA_DOWNLOAD_PROGRESS field must be a float number within [0.0, 1.0].");
               }
            }

         }
      }
   }

   private static class ConnectionRecord {
      String pkg;
      Bundle rootHints;
      MediaBrowserServiceCompat.ServiceCallbacks callbacks;
      MediaBrowserServiceCompat.BrowserRoot root;
      HashMap subscriptions = new HashMap();
   }

   private final class ServiceHandler extends Handler {
      private final MediaBrowserServiceCompat.ServiceBinderImpl mServiceBinderImpl = MediaBrowserServiceCompat.this.new ServiceBinderImpl();

      public void handleMessage(Message msg) {
         Bundle data = msg.getData();
         switch(msg.what) {
         case 1:
            this.mServiceBinderImpl.connect(data.getString("data_package_name"), data.getInt("data_calling_uid"), data.getBundle("data_root_hints"), new MediaBrowserServiceCompat.ServiceCallbacksCompat(msg.replyTo));
            break;
         case 2:
            this.mServiceBinderImpl.disconnect(new MediaBrowserServiceCompat.ServiceCallbacksCompat(msg.replyTo));
            break;
         case 3:
            this.mServiceBinderImpl.addSubscription(data.getString("data_media_item_id"), BundleCompat.getBinder(data, "data_callback_token"), data.getBundle("data_options"), new MediaBrowserServiceCompat.ServiceCallbacksCompat(msg.replyTo));
            break;
         case 4:
            this.mServiceBinderImpl.removeSubscription(data.getString("data_media_item_id"), BundleCompat.getBinder(data, "data_callback_token"), new MediaBrowserServiceCompat.ServiceCallbacksCompat(msg.replyTo));
            break;
         case 5:
            this.mServiceBinderImpl.getMediaItem(data.getString("data_media_item_id"), (ResultReceiver)data.getParcelable("data_result_receiver"), new MediaBrowserServiceCompat.ServiceCallbacksCompat(msg.replyTo));
            break;
         case 6:
            this.mServiceBinderImpl.registerCallbacks(new MediaBrowserServiceCompat.ServiceCallbacksCompat(msg.replyTo), data.getBundle("data_root_hints"));
            break;
         case 7:
            this.mServiceBinderImpl.unregisterCallbacks(new MediaBrowserServiceCompat.ServiceCallbacksCompat(msg.replyTo));
            break;
         case 8:
            this.mServiceBinderImpl.search(data.getString("data_search_query"), data.getBundle("data_search_extras"), (ResultReceiver)data.getParcelable("data_result_receiver"), new MediaBrowserServiceCompat.ServiceCallbacksCompat(msg.replyTo));
            break;
         case 9:
            this.mServiceBinderImpl.sendCustomAction(data.getString("data_custom_action"), data.getBundle("data_custom_action_extras"), (ResultReceiver)data.getParcelable("data_result_receiver"), new MediaBrowserServiceCompat.ServiceCallbacksCompat(msg.replyTo));
            break;
         default:
            Log.w("MBServiceCompat", "Unhandled message: " + msg + "\n  Service version: " + 1 + "\n  Client version: " + msg.arg1);
         }

      }

      public boolean sendMessageAtTime(Message msg, long uptimeMillis) {
         Bundle data = msg.getData();
         data.setClassLoader(MediaBrowserCompat.class.getClassLoader());
         data.putInt("data_calling_uid", Binder.getCallingUid());
         return super.sendMessageAtTime(msg, uptimeMillis);
      }

      public void postOrRun(Runnable r) {
         if (Thread.currentThread() == this.getLooper().getThread()) {
            r.run();
         } else {
            this.post(r);
         }

      }
   }

   @RequiresApi(26)
   class MediaBrowserServiceImplApi24 extends MediaBrowserServiceCompat.MediaBrowserServiceImplApi23 implements MediaBrowserServiceCompatApi24.ServiceCompatProxy {
      MediaBrowserServiceImplApi24() {
         super();
      }

      public void onCreate() {
         this.mServiceObj = MediaBrowserServiceCompatApi24.createService(MediaBrowserServiceCompat.this, this);
         MediaBrowserServiceCompatApi21.onCreate(this.mServiceObj);
      }

      public void notifyChildrenChanged(String parentId, Bundle options) {
         if (options == null) {
            MediaBrowserServiceCompatApi21.notifyChildrenChanged(this.mServiceObj, parentId);
         } else {
            MediaBrowserServiceCompatApi24.notifyChildrenChanged(this.mServiceObj, parentId, options);
         }

      }

      public void onLoadChildren(String parentId, final MediaBrowserServiceCompatApi24.ResultWrapper resultWrapper, Bundle options) {
         MediaBrowserServiceCompat.Result result = new MediaBrowserServiceCompat.Result(parentId) {
            void onResultSent(List list) {
               List parcelList = null;
               if (list != null) {
                  parcelList = new ArrayList();
                  Iterator var3 = list.iterator();

                  while(var3.hasNext()) {
                     MediaBrowserCompat.MediaItem item = (MediaBrowserCompat.MediaItem)var3.next();
                     Parcel parcel = Parcel.obtain();
                     item.writeToParcel(parcel, 0);
                     parcelList.add(parcel);
                  }
               }

               resultWrapper.sendResult(parcelList, this.getFlags());
            }

            public void detach() {
               resultWrapper.detach();
            }
         };
         MediaBrowserServiceCompat.this.onLoadChildren(parentId, result, options);
      }

      public Bundle getBrowserRootHints() {
         if (MediaBrowserServiceCompat.this.mCurConnection != null) {
            return MediaBrowserServiceCompat.this.mCurConnection.rootHints == null ? null : new Bundle(MediaBrowserServiceCompat.this.mCurConnection.rootHints);
         } else {
            return MediaBrowserServiceCompatApi24.getBrowserRootHints(this.mServiceObj);
         }
      }
   }

   @RequiresApi(23)
   class MediaBrowserServiceImplApi23 extends MediaBrowserServiceCompat.MediaBrowserServiceImplApi21 implements MediaBrowserServiceCompatApi23.ServiceCompatProxy {
      MediaBrowserServiceImplApi23() {
         super();
      }

      public void onCreate() {
         this.mServiceObj = MediaBrowserServiceCompatApi23.createService(MediaBrowserServiceCompat.this, this);
         MediaBrowserServiceCompatApi21.onCreate(this.mServiceObj);
      }

      public void onLoadItem(String itemId, final MediaBrowserServiceCompatApi21.ResultWrapper resultWrapper) {
         MediaBrowserServiceCompat.Result result = new MediaBrowserServiceCompat.Result(itemId) {
            void onResultSent(MediaBrowserCompat.MediaItem item) {
               if (item == null) {
                  resultWrapper.sendResult((Object)null);
               } else {
                  Parcel parcelItem = Parcel.obtain();
                  item.writeToParcel(parcelItem, 0);
                  resultWrapper.sendResult(parcelItem);
               }

            }

            public void detach() {
               resultWrapper.detach();
            }
         };
         MediaBrowserServiceCompat.this.onLoadItem(itemId, result);
      }
   }

   @RequiresApi(21)
   class MediaBrowserServiceImplApi21 implements MediaBrowserServiceCompat.MediaBrowserServiceImpl, MediaBrowserServiceCompatApi21.ServiceCompatProxy {
      final List mRootExtrasList = new ArrayList();
      Object mServiceObj;
      Messenger mMessenger;

      public void onCreate() {
         this.mServiceObj = MediaBrowserServiceCompatApi21.createService(MediaBrowserServiceCompat.this, this);
         MediaBrowserServiceCompatApi21.onCreate(this.mServiceObj);
      }

      public IBinder onBind(Intent intent) {
         return MediaBrowserServiceCompatApi21.onBind(this.mServiceObj, intent);
      }

      public void setSessionToken(final MediaSessionCompat.Token token) {
         MediaBrowserServiceCompat.this.mHandler.postOrRun(new Runnable() {
            public void run() {
               if (!MediaBrowserServiceImplApi21.this.mRootExtrasList.isEmpty()) {
                  IMediaSession extraBinder = token.getExtraBinder();
                  if (extraBinder != null) {
                     Iterator var2 = MediaBrowserServiceImplApi21.this.mRootExtrasList.iterator();

                     while(var2.hasNext()) {
                        Bundle rootExtras = (Bundle)var2.next();
                        BundleCompat.putBinder(rootExtras, "extra_session_binder", extraBinder.asBinder());
                     }
                  }

                  MediaBrowserServiceImplApi21.this.mRootExtrasList.clear();
               }

               MediaBrowserServiceCompatApi21.setSessionToken(MediaBrowserServiceImplApi21.this.mServiceObj, token.getToken());
            }
         });
      }

      public void notifyChildrenChanged(final String parentId, final Bundle options) {
         if (this.mMessenger == null) {
            MediaBrowserServiceCompatApi21.notifyChildrenChanged(this.mServiceObj, parentId);
         } else {
            MediaBrowserServiceCompat.this.mHandler.post(new Runnable() {
               public void run() {
                  Iterator var1 = MediaBrowserServiceCompat.this.mConnections.keySet().iterator();

                  while(true) {
                     MediaBrowserServiceCompat.ConnectionRecord connection;
                     List callbackList;
                     do {
                        if (!var1.hasNext()) {
                           return;
                        }

                        IBinder binder = (IBinder)var1.next();
                        connection = (MediaBrowserServiceCompat.ConnectionRecord)MediaBrowserServiceCompat.this.mConnections.get(binder);
                        callbackList = (List)connection.subscriptions.get(parentId);
                     } while(callbackList == null);

                     Iterator var5 = callbackList.iterator();

                     while(var5.hasNext()) {
                        Pair callback = (Pair)var5.next();
                        if (MediaBrowserCompatUtils.hasDuplicatedItems(options, (Bundle)callback.second)) {
                           MediaBrowserServiceCompat.this.performLoadChildren(parentId, connection, (Bundle)callback.second);
                        }
                     }
                  }
               }
            });
         }

      }

      public Bundle getBrowserRootHints() {
         if (this.mMessenger == null) {
            return null;
         } else if (MediaBrowserServiceCompat.this.mCurConnection == null) {
            throw new IllegalStateException("This should be called inside of onLoadChildren, onLoadItem or onSearch methods");
         } else {
            return MediaBrowserServiceCompat.this.mCurConnection.rootHints == null ? null : new Bundle(MediaBrowserServiceCompat.this.mCurConnection.rootHints);
         }
      }

      public MediaBrowserServiceCompatApi21.BrowserRoot onGetRoot(String clientPackageName, int clientUid, Bundle rootHints) {
         Bundle rootExtras = null;
         if (rootHints != null && rootHints.getInt("extra_client_version", 0) != 0) {
            rootHints.remove("extra_client_version");
            this.mMessenger = new Messenger(MediaBrowserServiceCompat.this.mHandler);
            rootExtras = new Bundle();
            rootExtras.putInt("extra_service_version", 1);
            BundleCompat.putBinder(rootExtras, "extra_messenger", this.mMessenger.getBinder());
            if (MediaBrowserServiceCompat.this.mSession != null) {
               IMediaSession extraBinder = MediaBrowserServiceCompat.this.mSession.getExtraBinder();
               BundleCompat.putBinder(rootExtras, "extra_session_binder", extraBinder == null ? null : extraBinder.asBinder());
            } else {
               this.mRootExtrasList.add(rootExtras);
            }
         }

         MediaBrowserServiceCompat.BrowserRoot root = MediaBrowserServiceCompat.this.onGetRoot(clientPackageName, clientUid, rootHints);
         if (root == null) {
            return null;
         } else {
            if (rootExtras == null) {
               rootExtras = root.getExtras();
            } else if (root.getExtras() != null) {
               rootExtras.putAll(root.getExtras());
            }

            return new MediaBrowserServiceCompatApi21.BrowserRoot(root.getRootId(), rootExtras);
         }
      }

      public void onLoadChildren(String parentId, final MediaBrowserServiceCompatApi21.ResultWrapper resultWrapper) {
         MediaBrowserServiceCompat.Result result = new MediaBrowserServiceCompat.Result(parentId) {
            void onResultSent(List list) {
               List parcelList = null;
               if (list != null) {
                  parcelList = new ArrayList();
                  Iterator var3 = list.iterator();

                  while(var3.hasNext()) {
                     MediaBrowserCompat.MediaItem item = (MediaBrowserCompat.MediaItem)var3.next();
                     Parcel parcel = Parcel.obtain();
                     item.writeToParcel(parcel, 0);
                     parcelList.add(parcel);
                  }
               }

               resultWrapper.sendResult(parcelList);
            }

            public void detach() {
               resultWrapper.detach();
            }
         };
         MediaBrowserServiceCompat.this.onLoadChildren(parentId, result);
      }
   }

   class MediaBrowserServiceImplBase implements MediaBrowserServiceCompat.MediaBrowserServiceImpl {
      private Messenger mMessenger;

      public void onCreate() {
         this.mMessenger = new Messenger(MediaBrowserServiceCompat.this.mHandler);
      }

      public IBinder onBind(Intent intent) {
         return "android.media.browse.MediaBrowserService".equals(intent.getAction()) ? this.mMessenger.getBinder() : null;
      }

      public void setSessionToken(final MediaSessionCompat.Token token) {
         MediaBrowserServiceCompat.this.mHandler.post(new Runnable() {
            public void run() {
               Iterator iter = MediaBrowserServiceCompat.this.mConnections.values().iterator();

               while(iter.hasNext()) {
                  MediaBrowserServiceCompat.ConnectionRecord connection = (MediaBrowserServiceCompat.ConnectionRecord)iter.next();

                  try {
                     connection.callbacks.onConnect(connection.root.getRootId(), token, connection.root.getExtras());
                  } catch (RemoteException var4) {
                     Log.w("MBServiceCompat", "Connection for " + connection.pkg + " is no longer valid.");
                     iter.remove();
                  }
               }

            }
         });
      }

      public void notifyChildrenChanged(@NonNull final String parentId, final Bundle options) {
         MediaBrowserServiceCompat.this.mHandler.post(new Runnable() {
            public void run() {
               Iterator var1 = MediaBrowserServiceCompat.this.mConnections.keySet().iterator();

               while(true) {
                  MediaBrowserServiceCompat.ConnectionRecord connection;
                  List callbackList;
                  do {
                     if (!var1.hasNext()) {
                        return;
                     }

                     IBinder binder = (IBinder)var1.next();
                     connection = (MediaBrowserServiceCompat.ConnectionRecord)MediaBrowserServiceCompat.this.mConnections.get(binder);
                     callbackList = (List)connection.subscriptions.get(parentId);
                  } while(callbackList == null);

                  Iterator var5 = callbackList.iterator();

                  while(var5.hasNext()) {
                     Pair callback = (Pair)var5.next();
                     if (MediaBrowserCompatUtils.hasDuplicatedItems(options, (Bundle)callback.second)) {
                        MediaBrowserServiceCompat.this.performLoadChildren(parentId, connection, (Bundle)callback.second);
                     }
                  }
               }
            }
         });
      }

      public Bundle getBrowserRootHints() {
         if (MediaBrowserServiceCompat.this.mCurConnection == null) {
            throw new IllegalStateException("This should be called inside of onLoadChildren, onLoadItem or onSearch methods");
         } else {
            return MediaBrowserServiceCompat.this.mCurConnection.rootHints == null ? null : new Bundle(MediaBrowserServiceCompat.this.mCurConnection.rootHints);
         }
      }
   }

   interface MediaBrowserServiceImpl {
      void onCreate();

      IBinder onBind(Intent var1);

      void setSessionToken(MediaSessionCompat.Token var1);

      void notifyChildrenChanged(String var1, Bundle var2);

      Bundle getBrowserRootHints();
   }

   @Retention(RetentionPolicy.SOURCE)
   @RestrictTo({Scope.LIBRARY_GROUP})
   private @interface ResultFlags {
   }
}
