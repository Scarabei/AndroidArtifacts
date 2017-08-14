package android.support.v7.media;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.os.IBinder.DeathRecipient;
import android.support.annotation.NonNull;
import android.util.Log;
import android.util.SparseArray;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

final class RegisteredMediaRouteProvider extends MediaRouteProvider implements ServiceConnection {
   static final String TAG = "MediaRouteProviderProxy";
   static final boolean DEBUG = Log.isLoggable("MediaRouteProviderProxy", 3);
   private final ComponentName mComponentName;
   final RegisteredMediaRouteProvider.PrivateHandler mPrivateHandler;
   private final ArrayList mControllers = new ArrayList();
   private boolean mStarted;
   private boolean mBound;
   private RegisteredMediaRouteProvider.Connection mActiveConnection;
   private boolean mConnectionReady;

   public RegisteredMediaRouteProvider(Context context, ComponentName componentName) {
      super(context, new MediaRouteProvider.ProviderMetadata(componentName));
      this.mComponentName = componentName;
      this.mPrivateHandler = new RegisteredMediaRouteProvider.PrivateHandler();
   }

   public MediaRouteProvider.RouteController onCreateRouteController(@NonNull String routeId) {
      if (routeId == null) {
         throw new IllegalArgumentException("routeId cannot be null");
      } else {
         return this.createRouteController(routeId, (String)null);
      }
   }

   public MediaRouteProvider.RouteController onCreateRouteController(@NonNull String routeId, @NonNull String routeGroupId) {
      if (routeId == null) {
         throw new IllegalArgumentException("routeId cannot be null");
      } else if (routeGroupId == null) {
         throw new IllegalArgumentException("routeGroupId cannot be null");
      } else {
         return this.createRouteController(routeId, routeGroupId);
      }
   }

   public void onDiscoveryRequestChanged(MediaRouteDiscoveryRequest request) {
      if (this.mConnectionReady) {
         this.mActiveConnection.setDiscoveryRequest(request);
      }

      this.updateBinding();
   }

   public void onServiceConnected(ComponentName name, IBinder service) {
      if (DEBUG) {
         Log.d("MediaRouteProviderProxy", this + ": Connected");
      }

      if (this.mBound) {
         this.disconnect();
         Messenger messenger = service != null ? new Messenger(service) : null;
         if (MediaRouteProviderProtocol.isValidRemoteMessenger(messenger)) {
            RegisteredMediaRouteProvider.Connection connection = new RegisteredMediaRouteProvider.Connection(messenger);
            if (connection.register()) {
               this.mActiveConnection = connection;
            } else if (DEBUG) {
               Log.d("MediaRouteProviderProxy", this + ": Registration failed");
            }
         } else {
            Log.e("MediaRouteProviderProxy", this + ": Service returned invalid messenger binder");
         }
      }

   }

   public void onServiceDisconnected(ComponentName name) {
      if (DEBUG) {
         Log.d("MediaRouteProviderProxy", this + ": Service disconnected");
      }

      this.disconnect();
   }

   public String toString() {
      return "Service connection " + this.mComponentName.flattenToShortString();
   }

   public boolean hasComponentName(String packageName, String className) {
      return this.mComponentName.getPackageName().equals(packageName) && this.mComponentName.getClassName().equals(className);
   }

   public void start() {
      if (!this.mStarted) {
         if (DEBUG) {
            Log.d("MediaRouteProviderProxy", this + ": Starting");
         }

         this.mStarted = true;
         this.updateBinding();
      }

   }

   public void stop() {
      if (this.mStarted) {
         if (DEBUG) {
            Log.d("MediaRouteProviderProxy", this + ": Stopping");
         }

         this.mStarted = false;
         this.updateBinding();
      }

   }

   public void rebindIfDisconnected() {
      if (this.mActiveConnection == null && this.shouldBind()) {
         this.unbind();
         this.bind();
      }

   }

   private void updateBinding() {
      if (this.shouldBind()) {
         this.bind();
      } else {
         this.unbind();
      }

   }

   private boolean shouldBind() {
      if (this.mStarted) {
         if (this.getDiscoveryRequest() != null) {
            return true;
         }

         if (!this.mControllers.isEmpty()) {
            return true;
         }
      }

      return false;
   }

   private void bind() {
      if (!this.mBound) {
         if (DEBUG) {
            Log.d("MediaRouteProviderProxy", this + ": Binding");
         }

         Intent service = new Intent("android.media.MediaRouteProviderService");
         service.setComponent(this.mComponentName);

         try {
            this.mBound = this.getContext().bindService(service, this, 1);
            if (!this.mBound && DEBUG) {
               Log.d("MediaRouteProviderProxy", this + ": Bind failed");
            }
         } catch (SecurityException var3) {
            if (DEBUG) {
               Log.d("MediaRouteProviderProxy", this + ": Bind failed", var3);
            }
         }
      }

   }

   private void unbind() {
      if (this.mBound) {
         if (DEBUG) {
            Log.d("MediaRouteProviderProxy", this + ": Unbinding");
         }

         this.mBound = false;
         this.disconnect();
         this.getContext().unbindService(this);
      }

   }

   private MediaRouteProvider.RouteController createRouteController(String routeId, String routeGroupId) {
      MediaRouteProviderDescriptor descriptor = this.getDescriptor();
      if (descriptor != null) {
         List routes = descriptor.getRoutes();
         int count = routes.size();

         for(int i = 0; i < count; ++i) {
            MediaRouteDescriptor route = (MediaRouteDescriptor)routes.get(i);
            if (route.getId().equals(routeId)) {
               RegisteredMediaRouteProvider.Controller controller = new RegisteredMediaRouteProvider.Controller(routeId, routeGroupId);
               this.mControllers.add(controller);
               if (this.mConnectionReady) {
                  controller.attachConnection(this.mActiveConnection);
               }

               this.updateBinding();
               return controller;
            }
         }
      }

      return null;
   }

   void onConnectionReady(RegisteredMediaRouteProvider.Connection connection) {
      if (this.mActiveConnection == connection) {
         this.mConnectionReady = true;
         this.attachControllersToConnection();
         MediaRouteDiscoveryRequest request = this.getDiscoveryRequest();
         if (request != null) {
            this.mActiveConnection.setDiscoveryRequest(request);
         }
      }

   }

   void onConnectionDied(RegisteredMediaRouteProvider.Connection connection) {
      if (this.mActiveConnection == connection) {
         if (DEBUG) {
            Log.d("MediaRouteProviderProxy", this + ": Service connection died");
         }

         this.disconnect();
      }

   }

   void onConnectionError(RegisteredMediaRouteProvider.Connection connection, String error) {
      if (this.mActiveConnection == connection) {
         if (DEBUG) {
            Log.d("MediaRouteProviderProxy", this + ": Service connection error - " + error);
         }

         this.unbind();
      }

   }

   void onConnectionDescriptorChanged(RegisteredMediaRouteProvider.Connection connection, MediaRouteProviderDescriptor descriptor) {
      if (this.mActiveConnection == connection) {
         if (DEBUG) {
            Log.d("MediaRouteProviderProxy", this + ": Descriptor changed, descriptor=" + descriptor);
         }

         this.setDescriptor(descriptor);
      }

   }

   private void disconnect() {
      if (this.mActiveConnection != null) {
         this.setDescriptor((MediaRouteProviderDescriptor)null);
         this.mConnectionReady = false;
         this.detachControllersFromConnection();
         this.mActiveConnection.dispose();
         this.mActiveConnection = null;
      }

   }

   void onControllerReleased(RegisteredMediaRouteProvider.Controller controller) {
      this.mControllers.remove(controller);
      controller.detachConnection();
      this.updateBinding();
   }

   private void attachControllersToConnection() {
      int count = this.mControllers.size();

      for(int i = 0; i < count; ++i) {
         ((RegisteredMediaRouteProvider.Controller)this.mControllers.get(i)).attachConnection(this.mActiveConnection);
      }

   }

   private void detachControllersFromConnection() {
      int count = this.mControllers.size();

      for(int i = 0; i < count; ++i) {
         ((RegisteredMediaRouteProvider.Controller)this.mControllers.get(i)).detachConnection();
      }

   }

   private static final class ReceiveHandler extends Handler {
      private final WeakReference mConnectionRef;

      public ReceiveHandler(RegisteredMediaRouteProvider.Connection connection) {
         this.mConnectionRef = new WeakReference(connection);
      }

      public void dispose() {
         this.mConnectionRef.clear();
      }

      public void handleMessage(Message msg) {
         RegisteredMediaRouteProvider.Connection connection = (RegisteredMediaRouteProvider.Connection)this.mConnectionRef.get();
         if (connection != null) {
            int what = msg.what;
            int requestId = msg.arg1;
            int arg = msg.arg2;
            Object obj = msg.obj;
            Bundle data = msg.peekData();
            if (!this.processMessage(connection, what, requestId, arg, obj, data) && RegisteredMediaRouteProvider.DEBUG) {
               Log.d("MediaRouteProviderProxy", "Unhandled message from server: " + msg);
            }
         }

      }

      private boolean processMessage(RegisteredMediaRouteProvider.Connection connection, int what, int requestId, int arg, Object obj, Bundle data) {
         switch(what) {
         case 0:
            connection.onGenericFailure(requestId);
            return true;
         case 1:
            connection.onGenericSuccess(requestId);
            return true;
         case 2:
            if (obj != null && !(obj instanceof Bundle)) {
               break;
            }

            return connection.onRegistered(requestId, arg, (Bundle)obj);
         case 3:
            if (obj != null && !(obj instanceof Bundle)) {
               break;
            }

            return connection.onControlRequestSucceeded(requestId, (Bundle)obj);
         case 4:
            if (obj != null && !(obj instanceof Bundle)) {
               break;
            }

            String error = data == null ? null : data.getString("error");
            return connection.onControlRequestFailed(requestId, error, (Bundle)obj);
         case 5:
            if (obj == null || obj instanceof Bundle) {
               return connection.onDescriptorChanged((Bundle)obj);
            }
         }

         return false;
      }
   }

   private final class PrivateHandler extends Handler {
   }

   private final class Connection implements DeathRecipient {
      private final Messenger mServiceMessenger;
      private final RegisteredMediaRouteProvider.ReceiveHandler mReceiveHandler;
      private final Messenger mReceiveMessenger;
      private int mNextRequestId = 1;
      private int mNextControllerId = 1;
      private int mServiceVersion;
      private int mPendingRegisterRequestId;
      private final SparseArray mPendingCallbacks = new SparseArray();

      public Connection(Messenger serviceMessenger) {
         this.mServiceMessenger = serviceMessenger;
         this.mReceiveHandler = new RegisteredMediaRouteProvider.ReceiveHandler(this);
         this.mReceiveMessenger = new Messenger(this.mReceiveHandler);
      }

      public boolean register() {
         this.mPendingRegisterRequestId = this.mNextRequestId++;
         if (!this.sendRequest(1, this.mPendingRegisterRequestId, 2, (Object)null, (Bundle)null)) {
            return false;
         } else {
            try {
               this.mServiceMessenger.getBinder().linkToDeath(this, 0);
               return true;
            } catch (RemoteException var2) {
               this.binderDied();
               return false;
            }
         }
      }

      public void dispose() {
         this.sendRequest(2, 0, 0, (Object)null, (Bundle)null);
         this.mReceiveHandler.dispose();
         this.mServiceMessenger.getBinder().unlinkToDeath(this, 0);
         RegisteredMediaRouteProvider.this.mPrivateHandler.post(new Runnable() {
            public void run() {
               Connection.this.failPendingCallbacks();
            }
         });
      }

      void failPendingCallbacks() {
         int count = false;

         for(int i = 0; i < this.mPendingCallbacks.size(); ++i) {
            ((MediaRouter.ControlRequestCallback)this.mPendingCallbacks.valueAt(i)).onError((String)null, (Bundle)null);
         }

         this.mPendingCallbacks.clear();
      }

      public boolean onGenericFailure(int requestId) {
         if (requestId == this.mPendingRegisterRequestId) {
            this.mPendingRegisterRequestId = 0;
            RegisteredMediaRouteProvider.this.onConnectionError(this, "Registration failed");
         }

         MediaRouter.ControlRequestCallback callback = (MediaRouter.ControlRequestCallback)this.mPendingCallbacks.get(requestId);
         if (callback != null) {
            this.mPendingCallbacks.remove(requestId);
            callback.onError((String)null, (Bundle)null);
         }

         return true;
      }

      public boolean onGenericSuccess(int requestId) {
         return true;
      }

      public boolean onRegistered(int requestId, int serviceVersion, Bundle descriptorBundle) {
         if (this.mServiceVersion == 0 && requestId == this.mPendingRegisterRequestId && serviceVersion >= 1) {
            this.mPendingRegisterRequestId = 0;
            this.mServiceVersion = serviceVersion;
            RegisteredMediaRouteProvider.this.onConnectionDescriptorChanged(this, MediaRouteProviderDescriptor.fromBundle(descriptorBundle));
            RegisteredMediaRouteProvider.this.onConnectionReady(this);
            return true;
         } else {
            return false;
         }
      }

      public boolean onDescriptorChanged(Bundle descriptorBundle) {
         if (this.mServiceVersion != 0) {
            RegisteredMediaRouteProvider.this.onConnectionDescriptorChanged(this, MediaRouteProviderDescriptor.fromBundle(descriptorBundle));
            return true;
         } else {
            return false;
         }
      }

      public boolean onControlRequestSucceeded(int requestId, Bundle data) {
         MediaRouter.ControlRequestCallback callback = (MediaRouter.ControlRequestCallback)this.mPendingCallbacks.get(requestId);
         if (callback != null) {
            this.mPendingCallbacks.remove(requestId);
            callback.onResult(data);
            return true;
         } else {
            return false;
         }
      }

      public boolean onControlRequestFailed(int requestId, String error, Bundle data) {
         MediaRouter.ControlRequestCallback callback = (MediaRouter.ControlRequestCallback)this.mPendingCallbacks.get(requestId);
         if (callback != null) {
            this.mPendingCallbacks.remove(requestId);
            callback.onError(error, data);
            return true;
         } else {
            return false;
         }
      }

      public void binderDied() {
         RegisteredMediaRouteProvider.this.mPrivateHandler.post(new Runnable() {
            public void run() {
               RegisteredMediaRouteProvider.this.onConnectionDied(Connection.this);
            }
         });
      }

      public int createRouteController(String routeId, String routeGroupId) {
         int controllerId = this.mNextControllerId++;
         Bundle data = new Bundle();
         data.putString("routeId", routeId);
         data.putString("routeGroupId", routeGroupId);
         this.sendRequest(3, this.mNextRequestId++, controllerId, (Object)null, data);
         return controllerId;
      }

      public void releaseRouteController(int controllerId) {
         this.sendRequest(4, this.mNextRequestId++, controllerId, (Object)null, (Bundle)null);
      }

      public void selectRoute(int controllerId) {
         this.sendRequest(5, this.mNextRequestId++, controllerId, (Object)null, (Bundle)null);
      }

      public void unselectRoute(int controllerId, int reason) {
         Bundle extras = new Bundle();
         extras.putInt("unselectReason", reason);
         this.sendRequest(6, this.mNextRequestId++, controllerId, (Object)null, extras);
      }

      public void setVolume(int controllerId, int volume) {
         Bundle data = new Bundle();
         data.putInt("volume", volume);
         this.sendRequest(7, this.mNextRequestId++, controllerId, (Object)null, data);
      }

      public void updateVolume(int controllerId, int delta) {
         Bundle data = new Bundle();
         data.putInt("volume", delta);
         this.sendRequest(8, this.mNextRequestId++, controllerId, (Object)null, data);
      }

      public boolean sendControlRequest(int controllerId, Intent intent, MediaRouter.ControlRequestCallback callback) {
         int requestId = this.mNextRequestId++;
         if (this.sendRequest(9, requestId, controllerId, intent, (Bundle)null)) {
            if (callback != null) {
               this.mPendingCallbacks.put(requestId, callback);
            }

            return true;
         } else {
            return false;
         }
      }

      public void setDiscoveryRequest(MediaRouteDiscoveryRequest request) {
         this.sendRequest(10, this.mNextRequestId++, 0, request != null ? request.asBundle() : null, (Bundle)null);
      }

      private boolean sendRequest(int what, int requestId, int arg, Object obj, Bundle data) {
         Message msg = Message.obtain();
         msg.what = what;
         msg.arg1 = requestId;
         msg.arg2 = arg;
         msg.obj = obj;
         msg.setData(data);
         msg.replyTo = this.mReceiveMessenger;

         try {
            this.mServiceMessenger.send(msg);
            return true;
         } catch (DeadObjectException var8) {
            ;
         } catch (RemoteException var9) {
            if (what != 2) {
               Log.e("MediaRouteProviderProxy", "Could not send message to service.", var9);
            }
         }

         return false;
      }
   }

   private final class Controller extends MediaRouteProvider.RouteController {
      private final String mRouteId;
      private final String mRouteGroupId;
      private boolean mSelected;
      private int mPendingSetVolume = -1;
      private int mPendingUpdateVolumeDelta;
      private RegisteredMediaRouteProvider.Connection mConnection;
      private int mControllerId;

      public Controller(String routeId, String routeGroupId) {
         this.mRouteId = routeId;
         this.mRouteGroupId = routeGroupId;
      }

      public void attachConnection(RegisteredMediaRouteProvider.Connection connection) {
         this.mConnection = connection;
         this.mControllerId = connection.createRouteController(this.mRouteId, this.mRouteGroupId);
         if (this.mSelected) {
            connection.selectRoute(this.mControllerId);
            if (this.mPendingSetVolume >= 0) {
               connection.setVolume(this.mControllerId, this.mPendingSetVolume);
               this.mPendingSetVolume = -1;
            }

            if (this.mPendingUpdateVolumeDelta != 0) {
               connection.updateVolume(this.mControllerId, this.mPendingUpdateVolumeDelta);
               this.mPendingUpdateVolumeDelta = 0;
            }
         }

      }

      public void detachConnection() {
         if (this.mConnection != null) {
            this.mConnection.releaseRouteController(this.mControllerId);
            this.mConnection = null;
            this.mControllerId = 0;
         }

      }

      public void onRelease() {
         RegisteredMediaRouteProvider.this.onControllerReleased(this);
      }

      public void onSelect() {
         this.mSelected = true;
         if (this.mConnection != null) {
            this.mConnection.selectRoute(this.mControllerId);
         }

      }

      public void onUnselect() {
         this.onUnselect(0);
      }

      public void onUnselect(int reason) {
         this.mSelected = false;
         if (this.mConnection != null) {
            this.mConnection.unselectRoute(this.mControllerId, reason);
         }

      }

      public void onSetVolume(int volume) {
         if (this.mConnection != null) {
            this.mConnection.setVolume(this.mControllerId, volume);
         } else {
            this.mPendingSetVolume = volume;
            this.mPendingUpdateVolumeDelta = 0;
         }

      }

      public void onUpdateVolume(int delta) {
         if (this.mConnection != null) {
            this.mConnection.updateVolume(this.mControllerId, delta);
         } else {
            this.mPendingUpdateVolumeDelta += delta;
         }

      }

      public boolean onControlRequest(Intent intent, MediaRouter.ControlRequestCallback callback) {
         return this.mConnection != null ? this.mConnection.sendControlRequest(this.mControllerId, intent, callback) : false;
      }
   }
}
