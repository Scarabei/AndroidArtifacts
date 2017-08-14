package android.support.v7.media;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import java.util.Iterator;

public class RemotePlaybackClient {
   static final String TAG = "RemotePlaybackClient";
   static final boolean DEBUG = Log.isLoggable("RemotePlaybackClient", 3);
   private final Context mContext;
   private final MediaRouter.RouteInfo mRoute;
   private final RemotePlaybackClient.ActionReceiver mActionReceiver;
   private final PendingIntent mItemStatusPendingIntent;
   private final PendingIntent mSessionStatusPendingIntent;
   private final PendingIntent mMessagePendingIntent;
   private boolean mRouteSupportsRemotePlayback;
   private boolean mRouteSupportsQueuing;
   private boolean mRouteSupportsSessionManagement;
   private boolean mRouteSupportsMessaging;
   String mSessionId;
   RemotePlaybackClient.StatusCallback mStatusCallback;
   RemotePlaybackClient.OnMessageReceivedListener mOnMessageReceivedListener;

   public RemotePlaybackClient(Context context, MediaRouter.RouteInfo route) {
      if (context == null) {
         throw new IllegalArgumentException("context must not be null");
      } else if (route == null) {
         throw new IllegalArgumentException("route must not be null");
      } else {
         this.mContext = context;
         this.mRoute = route;
         IntentFilter actionFilter = new IntentFilter();
         actionFilter.addAction("android.support.v7.media.actions.ACTION_ITEM_STATUS_CHANGED");
         actionFilter.addAction("android.support.v7.media.actions.ACTION_SESSION_STATUS_CHANGED");
         actionFilter.addAction("android.support.v7.media.actions.ACTION_MESSAGE_RECEIVED");
         this.mActionReceiver = new RemotePlaybackClient.ActionReceiver();
         context.registerReceiver(this.mActionReceiver, actionFilter);
         Intent itemStatusIntent = new Intent("android.support.v7.media.actions.ACTION_ITEM_STATUS_CHANGED");
         itemStatusIntent.setPackage(context.getPackageName());
         this.mItemStatusPendingIntent = PendingIntent.getBroadcast(context, 0, itemStatusIntent, 0);
         Intent sessionStatusIntent = new Intent("android.support.v7.media.actions.ACTION_SESSION_STATUS_CHANGED");
         sessionStatusIntent.setPackage(context.getPackageName());
         this.mSessionStatusPendingIntent = PendingIntent.getBroadcast(context, 0, sessionStatusIntent, 0);
         Intent messageIntent = new Intent("android.support.v7.media.actions.ACTION_MESSAGE_RECEIVED");
         messageIntent.setPackage(context.getPackageName());
         this.mMessagePendingIntent = PendingIntent.getBroadcast(context, 0, messageIntent, 0);
         this.detectFeatures();
      }
   }

   public void release() {
      this.mContext.unregisterReceiver(this.mActionReceiver);
   }

   public boolean isRemotePlaybackSupported() {
      return this.mRouteSupportsRemotePlayback;
   }

   public boolean isQueuingSupported() {
      return this.mRouteSupportsQueuing;
   }

   public boolean isSessionManagementSupported() {
      return this.mRouteSupportsSessionManagement;
   }

   public boolean isMessagingSupported() {
      return this.mRouteSupportsMessaging;
   }

   public String getSessionId() {
      return this.mSessionId;
   }

   public void setSessionId(String sessionId) {
      if (this.mSessionId != sessionId && (this.mSessionId == null || !this.mSessionId.equals(sessionId))) {
         if (DEBUG) {
            Log.d("RemotePlaybackClient", "Session id is now: " + sessionId);
         }

         this.mSessionId = sessionId;
         if (this.mStatusCallback != null) {
            this.mStatusCallback.onSessionChanged(sessionId);
         }
      }

   }

   public boolean hasSession() {
      return this.mSessionId != null;
   }

   public void setStatusCallback(RemotePlaybackClient.StatusCallback callback) {
      this.mStatusCallback = callback;
   }

   public void setOnMessageReceivedListener(RemotePlaybackClient.OnMessageReceivedListener listener) {
      this.mOnMessageReceivedListener = listener;
   }

   public void play(Uri contentUri, String mimeType, Bundle metadata, long positionMillis, Bundle extras, RemotePlaybackClient.ItemActionCallback callback) {
      this.playOrEnqueue(contentUri, mimeType, metadata, positionMillis, extras, callback, "android.media.intent.action.PLAY");
   }

   public void enqueue(Uri contentUri, String mimeType, Bundle metadata, long positionMillis, Bundle extras, RemotePlaybackClient.ItemActionCallback callback) {
      this.playOrEnqueue(contentUri, mimeType, metadata, positionMillis, extras, callback, "android.media.intent.action.ENQUEUE");
   }

   private void playOrEnqueue(Uri contentUri, String mimeType, Bundle metadata, long positionMillis, Bundle extras, RemotePlaybackClient.ItemActionCallback callback, String action) {
      if (contentUri == null) {
         throw new IllegalArgumentException("contentUri must not be null");
      } else {
         this.throwIfRemotePlaybackNotSupported();
         if (action.equals("android.media.intent.action.ENQUEUE")) {
            this.throwIfQueuingNotSupported();
         }

         Intent intent = new Intent(action);
         intent.setDataAndType(contentUri, mimeType);
         intent.putExtra("android.media.intent.extra.ITEM_STATUS_UPDATE_RECEIVER", this.mItemStatusPendingIntent);
         if (metadata != null) {
            intent.putExtra("android.media.intent.extra.ITEM_METADATA", metadata);
         }

         if (positionMillis != 0L) {
            intent.putExtra("android.media.intent.extra.ITEM_POSITION", positionMillis);
         }

         this.performItemAction(intent, this.mSessionId, (String)null, extras, callback);
      }
   }

   public void seek(String itemId, long positionMillis, Bundle extras, RemotePlaybackClient.ItemActionCallback callback) {
      if (itemId == null) {
         throw new IllegalArgumentException("itemId must not be null");
      } else {
         this.throwIfNoCurrentSession();
         Intent intent = new Intent("android.media.intent.action.SEEK");
         intent.putExtra("android.media.intent.extra.ITEM_POSITION", positionMillis);
         this.performItemAction(intent, this.mSessionId, itemId, extras, callback);
      }
   }

   public void getStatus(String itemId, Bundle extras, RemotePlaybackClient.ItemActionCallback callback) {
      if (itemId == null) {
         throw new IllegalArgumentException("itemId must not be null");
      } else {
         this.throwIfNoCurrentSession();
         Intent intent = new Intent("android.media.intent.action.GET_STATUS");
         this.performItemAction(intent, this.mSessionId, itemId, extras, callback);
      }
   }

   public void remove(String itemId, Bundle extras, RemotePlaybackClient.ItemActionCallback callback) {
      if (itemId == null) {
         throw new IllegalArgumentException("itemId must not be null");
      } else {
         this.throwIfQueuingNotSupported();
         this.throwIfNoCurrentSession();
         Intent intent = new Intent("android.media.intent.action.REMOVE");
         this.performItemAction(intent, this.mSessionId, itemId, extras, callback);
      }
   }

   public void pause(Bundle extras, RemotePlaybackClient.SessionActionCallback callback) {
      this.throwIfNoCurrentSession();
      Intent intent = new Intent("android.media.intent.action.PAUSE");
      this.performSessionAction(intent, this.mSessionId, extras, callback);
   }

   public void resume(Bundle extras, RemotePlaybackClient.SessionActionCallback callback) {
      this.throwIfNoCurrentSession();
      Intent intent = new Intent("android.media.intent.action.RESUME");
      this.performSessionAction(intent, this.mSessionId, extras, callback);
   }

   public void stop(Bundle extras, RemotePlaybackClient.SessionActionCallback callback) {
      this.throwIfNoCurrentSession();
      Intent intent = new Intent("android.media.intent.action.STOP");
      this.performSessionAction(intent, this.mSessionId, extras, callback);
   }

   public void startSession(Bundle extras, RemotePlaybackClient.SessionActionCallback callback) {
      this.throwIfSessionManagementNotSupported();
      Intent intent = new Intent("android.media.intent.action.START_SESSION");
      intent.putExtra("android.media.intent.extra.SESSION_STATUS_UPDATE_RECEIVER", this.mSessionStatusPendingIntent);
      if (this.mRouteSupportsMessaging) {
         intent.putExtra("android.media.intent.extra.MESSAGE_RECEIVER", this.mMessagePendingIntent);
      }

      this.performSessionAction(intent, (String)null, extras, callback);
   }

   public void sendMessage(Bundle message, RemotePlaybackClient.SessionActionCallback callback) {
      this.throwIfNoCurrentSession();
      this.throwIfMessageNotSupported();
      Intent intent = new Intent("android.media.intent.action.SEND_MESSAGE");
      this.performSessionAction(intent, this.mSessionId, message, callback);
   }

   public void getSessionStatus(Bundle extras, RemotePlaybackClient.SessionActionCallback callback) {
      this.throwIfSessionManagementNotSupported();
      this.throwIfNoCurrentSession();
      Intent intent = new Intent("android.media.intent.action.GET_SESSION_STATUS");
      this.performSessionAction(intent, this.mSessionId, extras, callback);
   }

   public void endSession(Bundle extras, RemotePlaybackClient.SessionActionCallback callback) {
      this.throwIfSessionManagementNotSupported();
      this.throwIfNoCurrentSession();
      Intent intent = new Intent("android.media.intent.action.END_SESSION");
      this.performSessionAction(intent, this.mSessionId, extras, callback);
   }

   private void performItemAction(final Intent intent, final String sessionId, final String itemId, Bundle extras, final RemotePlaybackClient.ItemActionCallback callback) {
      intent.addCategory("android.media.intent.category.REMOTE_PLAYBACK");
      if (sessionId != null) {
         intent.putExtra("android.media.intent.extra.SESSION_ID", sessionId);
      }

      if (itemId != null) {
         intent.putExtra("android.media.intent.extra.ITEM_ID", itemId);
      }

      if (extras != null) {
         intent.putExtras(extras);
      }

      logRequest(intent);
      this.mRoute.sendControlRequest(intent, new MediaRouter.ControlRequestCallback() {
         public void onResult(Bundle data) {
            if (data != null) {
               String sessionIdResult = RemotePlaybackClient.inferMissingResult(sessionId, data.getString("android.media.intent.extra.SESSION_ID"));
               MediaSessionStatus sessionStatus = MediaSessionStatus.fromBundle(data.getBundle("android.media.intent.extra.SESSION_STATUS"));
               String itemIdResult = RemotePlaybackClient.inferMissingResult(itemId, data.getString("android.media.intent.extra.ITEM_ID"));
               MediaItemStatus itemStatus = MediaItemStatus.fromBundle(data.getBundle("android.media.intent.extra.ITEM_STATUS"));
               RemotePlaybackClient.this.adoptSession(sessionIdResult);
               if (sessionIdResult != null && itemIdResult != null && itemStatus != null) {
                  if (RemotePlaybackClient.DEBUG) {
                     Log.d("RemotePlaybackClient", "Received result from " + intent.getAction() + ": data=" + RemotePlaybackClient.bundleToString(data) + ", sessionId=" + sessionIdResult + ", sessionStatus=" + sessionStatus + ", itemId=" + itemIdResult + ", itemStatus=" + itemStatus);
                  }

                  callback.onResult(data, sessionIdResult, sessionStatus, itemIdResult, itemStatus);
                  return;
               }
            }

            RemotePlaybackClient.this.handleInvalidResult(intent, callback, data);
         }

         public void onError(String error, Bundle data) {
            RemotePlaybackClient.this.handleError(intent, callback, error, data);
         }
      });
   }

   private void performSessionAction(final Intent intent, final String sessionId, Bundle extras, final RemotePlaybackClient.SessionActionCallback callback) {
      intent.addCategory("android.media.intent.category.REMOTE_PLAYBACK");
      if (sessionId != null) {
         intent.putExtra("android.media.intent.extra.SESSION_ID", sessionId);
      }

      if (extras != null) {
         intent.putExtras(extras);
      }

      logRequest(intent);
      this.mRoute.sendControlRequest(intent, new MediaRouter.ControlRequestCallback() {
         public void onResult(Bundle data) {
            if (data != null) {
               String sessionIdResult = RemotePlaybackClient.inferMissingResult(sessionId, data.getString("android.media.intent.extra.SESSION_ID"));
               MediaSessionStatus sessionStatus = MediaSessionStatus.fromBundle(data.getBundle("android.media.intent.extra.SESSION_STATUS"));
               RemotePlaybackClient.this.adoptSession(sessionIdResult);
               if (sessionIdResult != null) {
                  if (RemotePlaybackClient.DEBUG) {
                     Log.d("RemotePlaybackClient", "Received result from " + intent.getAction() + ": data=" + RemotePlaybackClient.bundleToString(data) + ", sessionId=" + sessionIdResult + ", sessionStatus=" + sessionStatus);
                  }

                  try {
                     callback.onResult(data, sessionIdResult, sessionStatus);
                  } finally {
                     if (intent.getAction().equals("android.media.intent.action.END_SESSION") && sessionIdResult.equals(RemotePlaybackClient.this.mSessionId)) {
                        RemotePlaybackClient.this.setSessionId((String)null);
                     }

                  }

                  return;
               }
            }

            RemotePlaybackClient.this.handleInvalidResult(intent, callback, data);
         }

         public void onError(String error, Bundle data) {
            RemotePlaybackClient.this.handleError(intent, callback, error, data);
         }
      });
   }

   void adoptSession(String sessionId) {
      if (sessionId != null) {
         this.setSessionId(sessionId);
      }

   }

   void handleInvalidResult(Intent intent, RemotePlaybackClient.ActionCallback callback, Bundle data) {
      Log.w("RemotePlaybackClient", "Received invalid result data from " + intent.getAction() + ": data=" + bundleToString(data));
      callback.onError((String)null, 0, data);
   }

   void handleError(Intent intent, RemotePlaybackClient.ActionCallback callback, String error, Bundle data) {
      int code;
      if (data != null) {
         code = data.getInt("android.media.intent.extra.ERROR_CODE", 0);
      } else {
         code = 0;
      }

      if (DEBUG) {
         Log.w("RemotePlaybackClient", "Received error from " + intent.getAction() + ": error=" + error + ", code=" + code + ", data=" + bundleToString(data));
      }

      callback.onError(error, code, data);
   }

   private void detectFeatures() {
      this.mRouteSupportsRemotePlayback = this.routeSupportsAction("android.media.intent.action.PLAY") && this.routeSupportsAction("android.media.intent.action.SEEK") && this.routeSupportsAction("android.media.intent.action.GET_STATUS") && this.routeSupportsAction("android.media.intent.action.PAUSE") && this.routeSupportsAction("android.media.intent.action.RESUME") && this.routeSupportsAction("android.media.intent.action.STOP");
      this.mRouteSupportsQueuing = this.mRouteSupportsRemotePlayback && this.routeSupportsAction("android.media.intent.action.ENQUEUE") && this.routeSupportsAction("android.media.intent.action.REMOVE");
      this.mRouteSupportsSessionManagement = this.mRouteSupportsRemotePlayback && this.routeSupportsAction("android.media.intent.action.START_SESSION") && this.routeSupportsAction("android.media.intent.action.GET_SESSION_STATUS") && this.routeSupportsAction("android.media.intent.action.END_SESSION");
      this.mRouteSupportsMessaging = this.doesRouteSupportMessaging();
   }

   private boolean routeSupportsAction(String action) {
      return this.mRoute.supportsControlAction("android.media.intent.category.REMOTE_PLAYBACK", action);
   }

   private boolean doesRouteSupportMessaging() {
      Iterator var1 = this.mRoute.getControlFilters().iterator();

      IntentFilter filter;
      do {
         if (!var1.hasNext()) {
            return false;
         }

         filter = (IntentFilter)var1.next();
      } while(!filter.hasAction("android.media.intent.action.SEND_MESSAGE"));

      return true;
   }

   private void throwIfRemotePlaybackNotSupported() {
      if (!this.mRouteSupportsRemotePlayback) {
         throw new UnsupportedOperationException("The route does not support remote playback.");
      }
   }

   private void throwIfQueuingNotSupported() {
      if (!this.mRouteSupportsQueuing) {
         throw new UnsupportedOperationException("The route does not support queuing.");
      }
   }

   private void throwIfSessionManagementNotSupported() {
      if (!this.mRouteSupportsSessionManagement) {
         throw new UnsupportedOperationException("The route does not support session management.");
      }
   }

   private void throwIfMessageNotSupported() {
      if (!this.mRouteSupportsMessaging) {
         throw new UnsupportedOperationException("The route does not support message.");
      }
   }

   private void throwIfNoCurrentSession() {
      if (this.mSessionId == null) {
         throw new IllegalStateException("There is no current session.");
      }
   }

   static String inferMissingResult(String request, String result) {
      if (result == null) {
         return request;
      } else {
         return request != null && !request.equals(result) ? null : result;
      }
   }

   private static void logRequest(Intent intent) {
      if (DEBUG) {
         Log.d("RemotePlaybackClient", "Sending request: " + intent);
      }

   }

   static String bundleToString(Bundle bundle) {
      if (bundle != null) {
         bundle.size();
         return bundle.toString();
      } else {
         return "null";
      }
   }

   public interface OnMessageReceivedListener {
      void onMessageReceived(String var1, Bundle var2);
   }

   public abstract static class SessionActionCallback extends RemotePlaybackClient.ActionCallback {
      public void onResult(Bundle data, String sessionId, MediaSessionStatus sessionStatus) {
      }
   }

   public abstract static class ItemActionCallback extends RemotePlaybackClient.ActionCallback {
      public void onResult(Bundle data, String sessionId, MediaSessionStatus sessionStatus, String itemId, MediaItemStatus itemStatus) {
      }
   }

   public abstract static class ActionCallback {
      public void onError(String error, int code, Bundle data) {
      }
   }

   public abstract static class StatusCallback {
      public void onItemStatusChanged(Bundle data, String sessionId, MediaSessionStatus sessionStatus, String itemId, MediaItemStatus itemStatus) {
      }

      public void onSessionStatusChanged(Bundle data, String sessionId, MediaSessionStatus sessionStatus) {
      }

      public void onSessionChanged(String sessionId) {
      }
   }

   private final class ActionReceiver extends BroadcastReceiver {
      public static final String ACTION_ITEM_STATUS_CHANGED = "android.support.v7.media.actions.ACTION_ITEM_STATUS_CHANGED";
      public static final String ACTION_SESSION_STATUS_CHANGED = "android.support.v7.media.actions.ACTION_SESSION_STATUS_CHANGED";
      public static final String ACTION_MESSAGE_RECEIVED = "android.support.v7.media.actions.ACTION_MESSAGE_RECEIVED";

      public void onReceive(Context context, Intent intent) {
         String sessionId = intent.getStringExtra("android.media.intent.extra.SESSION_ID");
         if (sessionId != null && sessionId.equals(RemotePlaybackClient.this.mSessionId)) {
            MediaSessionStatus sessionStatus = MediaSessionStatus.fromBundle(intent.getBundleExtra("android.media.intent.extra.SESSION_STATUS"));
            String action = intent.getAction();
            if (action.equals("android.support.v7.media.actions.ACTION_ITEM_STATUS_CHANGED")) {
               String itemId = intent.getStringExtra("android.media.intent.extra.ITEM_ID");
               if (itemId == null) {
                  Log.w("RemotePlaybackClient", "Discarding spurious status callback with missing item id.");
                  return;
               }

               MediaItemStatus itemStatus = MediaItemStatus.fromBundle(intent.getBundleExtra("android.media.intent.extra.ITEM_STATUS"));
               if (itemStatus == null) {
                  Log.w("RemotePlaybackClient", "Discarding spurious status callback with missing item status.");
                  return;
               }

               if (RemotePlaybackClient.DEBUG) {
                  Log.d("RemotePlaybackClient", "Received item status callback: sessionId=" + sessionId + ", sessionStatus=" + sessionStatus + ", itemId=" + itemId + ", itemStatus=" + itemStatus);
               }

               if (RemotePlaybackClient.this.mStatusCallback != null) {
                  RemotePlaybackClient.this.mStatusCallback.onItemStatusChanged(intent.getExtras(), sessionId, sessionStatus, itemId, itemStatus);
               }
            } else if (action.equals("android.support.v7.media.actions.ACTION_SESSION_STATUS_CHANGED")) {
               if (sessionStatus == null) {
                  Log.w("RemotePlaybackClient", "Discarding spurious media status callback with missing session status.");
                  return;
               }

               if (RemotePlaybackClient.DEBUG) {
                  Log.d("RemotePlaybackClient", "Received session status callback: sessionId=" + sessionId + ", sessionStatus=" + sessionStatus);
               }

               if (RemotePlaybackClient.this.mStatusCallback != null) {
                  RemotePlaybackClient.this.mStatusCallback.onSessionStatusChanged(intent.getExtras(), sessionId, sessionStatus);
               }
            } else if (action.equals("android.support.v7.media.actions.ACTION_MESSAGE_RECEIVED")) {
               if (RemotePlaybackClient.DEBUG) {
                  Log.d("RemotePlaybackClient", "Received message callback: sessionId=" + sessionId);
               }

               if (RemotePlaybackClient.this.mOnMessageReceivedListener != null) {
                  RemotePlaybackClient.this.mOnMessageReceivedListener.onMessageReceived(sessionId, intent.getBundleExtra("android.media.intent.extra.MESSAGE"));
               }
            }

         } else {
            Log.w("RemotePlaybackClient", "Discarding spurious status callback with missing or invalid session id: sessionId=" + sessionId);
         }
      }
   }
}
