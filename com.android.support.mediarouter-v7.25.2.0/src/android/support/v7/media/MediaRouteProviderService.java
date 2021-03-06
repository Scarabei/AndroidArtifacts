package android.support.v7.media;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.os.IBinder.DeathRecipient;
import android.util.Log;
import android.util.SparseArray;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public abstract class MediaRouteProviderService extends Service {
   static final String TAG = "MediaRouteProviderSrv";
   static final boolean DEBUG = Log.isLoggable("MediaRouteProviderSrv", 3);
   private final ArrayList mClients = new ArrayList();
   private final MediaRouteProviderService.ReceiveHandler mReceiveHandler = new MediaRouteProviderService.ReceiveHandler(this);
   private final Messenger mReceiveMessenger;
   final MediaRouteProviderService.PrivateHandler mPrivateHandler;
   private final MediaRouteProviderService.ProviderCallback mProviderCallback;
   MediaRouteProvider mProvider;
   private MediaRouteDiscoveryRequest mCompositeDiscoveryRequest;
   public static final String SERVICE_INTERFACE = "android.media.MediaRouteProviderService";
   static final int PRIVATE_MSG_CLIENT_DIED = 1;

   public MediaRouteProviderService() {
      this.mReceiveMessenger = new Messenger(this.mReceiveHandler);
      this.mPrivateHandler = new MediaRouteProviderService.PrivateHandler();
      this.mProviderCallback = new MediaRouteProviderService.ProviderCallback();
   }

   public abstract MediaRouteProvider onCreateMediaRouteProvider();

   public MediaRouteProvider getMediaRouteProvider() {
      return this.mProvider;
   }

   public IBinder onBind(Intent intent) {
      if (intent.getAction().equals("android.media.MediaRouteProviderService")) {
         if (this.mProvider == null) {
            MediaRouteProvider provider = this.onCreateMediaRouteProvider();
            if (provider != null) {
               String providerPackage = provider.getMetadata().getPackageName();
               if (!providerPackage.equals(this.getPackageName())) {
                  throw new IllegalStateException("onCreateMediaRouteProvider() returned a provider whose package name does not match the package name of the service.  A media route provider service can only export its own media route providers.  Provider package name: " + providerPackage + ".  Service package name: " + this.getPackageName() + ".");
               }

               this.mProvider = provider;
               this.mProvider.setCallback(this.mProviderCallback);
            }
         }

         if (this.mProvider != null) {
            return this.mReceiveMessenger.getBinder();
         }
      }

      return null;
   }

   public boolean onUnbind(Intent intent) {
      if (this.mProvider != null) {
         this.mProvider.setCallback((MediaRouteProvider.Callback)null);
      }

      return super.onUnbind(intent);
   }

   boolean onRegisterClient(Messenger messenger, int requestId, int version) {
      if (version >= 1) {
         int index = this.findClient(messenger);
         if (index < 0) {
            MediaRouteProviderService.ClientRecord client = new MediaRouteProviderService.ClientRecord(messenger, version);
            if (client.register()) {
               this.mClients.add(client);
               if (DEBUG) {
                  Log.d("MediaRouteProviderSrv", client + ": Registered, version=" + version);
               }

               if (requestId != 0) {
                  MediaRouteProviderDescriptor descriptor = this.mProvider.getDescriptor();
                  sendReply(messenger, 2, requestId, 1, this.createDescriptorBundleForClient(descriptor, client), (Bundle)null);
               }

               return true;
            }
         }
      }

      return false;
   }

   boolean onUnregisterClient(Messenger messenger, int requestId) {
      int index = this.findClient(messenger);
      if (index >= 0) {
         MediaRouteProviderService.ClientRecord client = (MediaRouteProviderService.ClientRecord)this.mClients.remove(index);
         if (DEBUG) {
            Log.d("MediaRouteProviderSrv", client + ": Unregistered");
         }

         client.dispose();
         sendGenericSuccess(messenger, requestId);
         return true;
      } else {
         return false;
      }
   }

   void onBinderDied(Messenger messenger) {
      int index = this.findClient(messenger);
      if (index >= 0) {
         MediaRouteProviderService.ClientRecord client = (MediaRouteProviderService.ClientRecord)this.mClients.remove(index);
         if (DEBUG) {
            Log.d("MediaRouteProviderSrv", client + ": Binder died");
         }

         client.dispose();
      }

   }

   boolean onCreateRouteController(Messenger messenger, int requestId, int controllerId, String routeId, String routeGroupId) {
      MediaRouteProviderService.ClientRecord client = this.getClient(messenger);
      if (client != null && client.createRouteController(routeId, routeGroupId, controllerId)) {
         if (DEBUG) {
            Log.d("MediaRouteProviderSrv", client + ": Route controller created, controllerId=" + controllerId + ", routeId=" + routeId + ", routeGroupId=" + routeGroupId);
         }

         sendGenericSuccess(messenger, requestId);
         return true;
      } else {
         return false;
      }
   }

   boolean onReleaseRouteController(Messenger messenger, int requestId, int controllerId) {
      MediaRouteProviderService.ClientRecord client = this.getClient(messenger);
      if (client != null && client.releaseRouteController(controllerId)) {
         if (DEBUG) {
            Log.d("MediaRouteProviderSrv", client + ": Route controller released" + ", controllerId=" + controllerId);
         }

         sendGenericSuccess(messenger, requestId);
         return true;
      } else {
         return false;
      }
   }

   boolean onSelectRoute(Messenger messenger, int requestId, int controllerId) {
      MediaRouteProviderService.ClientRecord client = this.getClient(messenger);
      if (client != null) {
         MediaRouteProvider.RouteController controller = client.getRouteController(controllerId);
         if (controller != null) {
            controller.onSelect();
            if (DEBUG) {
               Log.d("MediaRouteProviderSrv", client + ": Route selected" + ", controllerId=" + controllerId);
            }

            sendGenericSuccess(messenger, requestId);
            return true;
         }
      }

      return false;
   }

   boolean onUnselectRoute(Messenger messenger, int requestId, int controllerId, int reason) {
      MediaRouteProviderService.ClientRecord client = this.getClient(messenger);
      if (client != null) {
         MediaRouteProvider.RouteController controller = client.getRouteController(controllerId);
         if (controller != null) {
            controller.onUnselect(reason);
            if (DEBUG) {
               Log.d("MediaRouteProviderSrv", client + ": Route unselected" + ", controllerId=" + controllerId);
            }

            sendGenericSuccess(messenger, requestId);
            return true;
         }
      }

      return false;
   }

   boolean onSetRouteVolume(Messenger messenger, int requestId, int controllerId, int volume) {
      MediaRouteProviderService.ClientRecord client = this.getClient(messenger);
      if (client != null) {
         MediaRouteProvider.RouteController controller = client.getRouteController(controllerId);
         if (controller != null) {
            controller.onSetVolume(volume);
            if (DEBUG) {
               Log.d("MediaRouteProviderSrv", client + ": Route volume changed" + ", controllerId=" + controllerId + ", volume=" + volume);
            }

            sendGenericSuccess(messenger, requestId);
            return true;
         }
      }

      return false;
   }

   boolean onUpdateRouteVolume(Messenger messenger, int requestId, int controllerId, int delta) {
      MediaRouteProviderService.ClientRecord client = this.getClient(messenger);
      if (client != null) {
         MediaRouteProvider.RouteController controller = client.getRouteController(controllerId);
         if (controller != null) {
            controller.onUpdateVolume(delta);
            if (DEBUG) {
               Log.d("MediaRouteProviderSrv", client + ": Route volume updated" + ", controllerId=" + controllerId + ", delta=" + delta);
            }

            sendGenericSuccess(messenger, requestId);
            return true;
         }
      }

      return false;
   }

   boolean onRouteControlRequest(final Messenger messenger, final int requestId, final int controllerId, final Intent intent) {
      final MediaRouteProviderService.ClientRecord client = this.getClient(messenger);
      if (client != null) {
         MediaRouteProvider.RouteController controller = client.getRouteController(controllerId);
         if (controller != null) {
            MediaRouter.ControlRequestCallback callback = null;
            if (requestId != 0) {
               callback = new MediaRouter.ControlRequestCallback() {
                  public void onResult(Bundle data) {
                     if (MediaRouteProviderService.DEBUG) {
                        Log.d("MediaRouteProviderSrv", client + ": Route control request succeeded" + ", controllerId=" + controllerId + ", intent=" + intent + ", data=" + data);
                     }

                     if (MediaRouteProviderService.this.findClient(messenger) >= 0) {
                        MediaRouteProviderService.sendReply(messenger, 3, requestId, 0, data, (Bundle)null);
                     }

                  }

                  public void onError(String error, Bundle data) {
                     if (MediaRouteProviderService.DEBUG) {
                        Log.d("MediaRouteProviderSrv", client + ": Route control request failed" + ", controllerId=" + controllerId + ", intent=" + intent + ", error=" + error + ", data=" + data);
                     }

                     if (MediaRouteProviderService.this.findClient(messenger) >= 0) {
                        if (error != null) {
                           Bundle bundle = new Bundle();
                           bundle.putString("error", error);
                           MediaRouteProviderService.sendReply(messenger, 4, requestId, 0, data, bundle);
                        } else {
                           MediaRouteProviderService.sendReply(messenger, 4, requestId, 0, data, (Bundle)null);
                        }
                     }

                  }
               };
            }

            if (controller.onControlRequest(intent, callback)) {
               if (DEBUG) {
                  Log.d("MediaRouteProviderSrv", client + ": Route control request delivered" + ", controllerId=" + controllerId + ", intent=" + intent);
               }

               return true;
            }
         }
      }

      return false;
   }

   boolean onSetDiscoveryRequest(Messenger messenger, int requestId, MediaRouteDiscoveryRequest request) {
      MediaRouteProviderService.ClientRecord client = this.getClient(messenger);
      if (client != null) {
         boolean actuallyChanged = client.setDiscoveryRequest(request);
         if (DEBUG) {
            Log.d("MediaRouteProviderSrv", client + ": Set discovery request, request=" + request + ", actuallyChanged=" + actuallyChanged + ", compositeDiscoveryRequest=" + this.mCompositeDiscoveryRequest);
         }

         sendGenericSuccess(messenger, requestId);
         return true;
      } else {
         return false;
      }
   }

   void sendDescriptorChanged(MediaRouteProviderDescriptor descriptor) {
      int count = this.mClients.size();

      for(int i = 0; i < count; ++i) {
         MediaRouteProviderService.ClientRecord client = (MediaRouteProviderService.ClientRecord)this.mClients.get(i);
         sendReply(client.mMessenger, 5, 0, 0, this.createDescriptorBundleForClient(descriptor, client), (Bundle)null);
         if (DEBUG) {
            Log.d("MediaRouteProviderSrv", client + ": Sent descriptor change event, descriptor=" + descriptor);
         }
      }

   }

   private Bundle createDescriptorBundleForClient(MediaRouteProviderDescriptor descriptor, MediaRouteProviderService.ClientRecord client) {
      if (descriptor == null) {
         return null;
      } else {
         List routes = descriptor.getRoutes();

         for(int i = routes.size() - 1; i >= 0; --i) {
            if (client.mVersion < ((MediaRouteDescriptor)routes.get(i)).getMinClientVersion() || client.mVersion > ((MediaRouteDescriptor)routes.get(i)).getMaxClientVersion()) {
               routes.remove(i);
            }
         }

         Bundle bundle = descriptor.asBundle();
         bundle.remove("routes");
         return (new MediaRouteProviderDescriptor.Builder(MediaRouteProviderDescriptor.fromBundle(bundle))).addRoutes(routes).build().asBundle();
      }
   }

   boolean updateCompositeDiscoveryRequest() {
      MediaRouteDiscoveryRequest composite = null;
      MediaRouteSelector.Builder selectorBuilder = null;
      boolean activeScan = false;
      int count = this.mClients.size();

      for(int i = 0; i < count; ++i) {
         MediaRouteDiscoveryRequest request = ((MediaRouteProviderService.ClientRecord)this.mClients.get(i)).mDiscoveryRequest;
         if (request != null && (!request.getSelector().isEmpty() || request.isActiveScan())) {
            activeScan |= request.isActiveScan();
            if (composite == null) {
               composite = request;
            } else {
               if (selectorBuilder == null) {
                  selectorBuilder = new MediaRouteSelector.Builder(composite.getSelector());
               }

               selectorBuilder.addSelector(request.getSelector());
            }
         }
      }

      if (selectorBuilder != null) {
         composite = new MediaRouteDiscoveryRequest(selectorBuilder.build(), activeScan);
      }

      if (this.mCompositeDiscoveryRequest == composite || this.mCompositeDiscoveryRequest != null && this.mCompositeDiscoveryRequest.equals(composite)) {
         return false;
      } else {
         this.mCompositeDiscoveryRequest = composite;
         this.mProvider.setDiscoveryRequest(composite);
         return true;
      }
   }

   private MediaRouteProviderService.ClientRecord getClient(Messenger messenger) {
      int index = this.findClient(messenger);
      return index >= 0 ? (MediaRouteProviderService.ClientRecord)this.mClients.get(index) : null;
   }

   int findClient(Messenger messenger) {
      int count = this.mClients.size();

      for(int i = 0; i < count; ++i) {
         MediaRouteProviderService.ClientRecord client = (MediaRouteProviderService.ClientRecord)this.mClients.get(i);
         if (client.hasMessenger(messenger)) {
            return i;
         }
      }

      return -1;
   }

   static void sendGenericFailure(Messenger messenger, int requestId) {
      if (requestId != 0) {
         sendReply(messenger, 0, requestId, 0, (Object)null, (Bundle)null);
      }

   }

   private static void sendGenericSuccess(Messenger messenger, int requestId) {
      if (requestId != 0) {
         sendReply(messenger, 1, requestId, 0, (Object)null, (Bundle)null);
      }

   }

   static void sendReply(Messenger messenger, int what, int requestId, int arg, Object obj, Bundle data) {
      Message msg = Message.obtain();
      msg.what = what;
      msg.arg1 = requestId;
      msg.arg2 = arg;
      msg.obj = obj;
      msg.setData(data);

      try {
         messenger.send(msg);
      } catch (DeadObjectException var8) {
         ;
      } catch (RemoteException var9) {
         Log.e("MediaRouteProviderSrv", "Could not send message to " + getClientId(messenger), var9);
      }

   }

   static String getClientId(Messenger messenger) {
      return "Client connection " + messenger.getBinder().toString();
   }

   private static final class ReceiveHandler extends Handler {
      private final WeakReference mServiceRef;

      public ReceiveHandler(MediaRouteProviderService service) {
         this.mServiceRef = new WeakReference(service);
      }

      public void handleMessage(Message msg) {
         Messenger messenger = msg.replyTo;
         if (MediaRouteProviderProtocol.isValidRemoteMessenger(messenger)) {
            int what = msg.what;
            int requestId = msg.arg1;
            int arg = msg.arg2;
            Object obj = msg.obj;
            Bundle data = msg.peekData();
            if (!this.processMessage(what, messenger, requestId, arg, obj, data)) {
               if (MediaRouteProviderService.DEBUG) {
                  Log.d("MediaRouteProviderSrv", MediaRouteProviderService.getClientId(messenger) + ": Message failed, what=" + what + ", requestId=" + requestId + ", arg=" + arg + ", obj=" + obj + ", data=" + data);
               }

               MediaRouteProviderService.sendGenericFailure(messenger, requestId);
            }
         } else if (MediaRouteProviderService.DEBUG) {
            Log.d("MediaRouteProviderSrv", "Ignoring message without valid reply messenger.");
         }

      }

      private boolean processMessage(int what, Messenger messenger, int requestId, int arg, Object obj, Bundle data) {
         MediaRouteProviderService service = (MediaRouteProviderService)this.mServiceRef.get();
         if (service != null) {
            int delta;
            switch(what) {
            case 1:
               return service.onRegisterClient(messenger, requestId, arg);
            case 2:
               return service.onUnregisterClient(messenger, requestId);
            case 3:
               String routeId = data.getString("routeId");
               String routeGroupId = data.getString("routeGroupId");
               if (routeId != null) {
                  return service.onCreateRouteController(messenger, requestId, arg, routeId, routeGroupId);
               }
               break;
            case 4:
               return service.onReleaseRouteController(messenger, requestId, arg);
            case 5:
               return service.onSelectRoute(messenger, requestId, arg);
            case 6:
               int reason = data == null ? 0 : data.getInt("unselectReason", 0);
               return service.onUnselectRoute(messenger, requestId, arg, reason);
            case 7:
               delta = data.getInt("volume", -1);
               if (delta >= 0) {
                  return service.onSetRouteVolume(messenger, requestId, arg, delta);
               }
               break;
            case 8:
               delta = data.getInt("volume", 0);
               if (delta != 0) {
                  return service.onUpdateRouteVolume(messenger, requestId, arg, delta);
               }
               break;
            case 9:
               if (obj instanceof Intent) {
                  return service.onRouteControlRequest(messenger, requestId, arg, (Intent)obj);
               }
               break;
            case 10:
               if (obj == null || obj instanceof Bundle) {
                  MediaRouteDiscoveryRequest request = MediaRouteDiscoveryRequest.fromBundle((Bundle)obj);
                  return service.onSetDiscoveryRequest(messenger, requestId, request != null && request.isValid() ? request : null);
               }
            }
         }

         return false;
      }
   }

   private final class ClientRecord implements DeathRecipient {
      public final Messenger mMessenger;
      public final int mVersion;
      public MediaRouteDiscoveryRequest mDiscoveryRequest;
      private final SparseArray mControllers = new SparseArray();

      public ClientRecord(Messenger messenger, int version) {
         this.mMessenger = messenger;
         this.mVersion = version;
      }

      public boolean register() {
         try {
            this.mMessenger.getBinder().linkToDeath(this, 0);
            return true;
         } catch (RemoteException var2) {
            this.binderDied();
            return false;
         }
      }

      public void dispose() {
         int count = this.mControllers.size();

         for(int i = 0; i < count; ++i) {
            ((MediaRouteProvider.RouteController)this.mControllers.valueAt(i)).onRelease();
         }

         this.mControllers.clear();
         this.mMessenger.getBinder().unlinkToDeath(this, 0);
         this.setDiscoveryRequest((MediaRouteDiscoveryRequest)null);
      }

      public boolean hasMessenger(Messenger other) {
         return this.mMessenger.getBinder() == other.getBinder();
      }

      public boolean createRouteController(String routeId, String routeGroupId, int controllerId) {
         if (this.mControllers.indexOfKey(controllerId) < 0) {
            MediaRouteProvider.RouteController controller = routeGroupId == null ? MediaRouteProviderService.this.mProvider.onCreateRouteController(routeId) : MediaRouteProviderService.this.mProvider.onCreateRouteController(routeId, routeGroupId);
            if (controller != null) {
               this.mControllers.put(controllerId, controller);
               return true;
            }
         }

         return false;
      }

      public boolean releaseRouteController(int controllerId) {
         MediaRouteProvider.RouteController controller = (MediaRouteProvider.RouteController)this.mControllers.get(controllerId);
         if (controller != null) {
            this.mControllers.remove(controllerId);
            controller.onRelease();
            return true;
         } else {
            return false;
         }
      }

      public MediaRouteProvider.RouteController getRouteController(int controllerId) {
         return (MediaRouteProvider.RouteController)this.mControllers.get(controllerId);
      }

      public boolean setDiscoveryRequest(MediaRouteDiscoveryRequest request) {
         if (this.mDiscoveryRequest == request || this.mDiscoveryRequest != null && this.mDiscoveryRequest.equals(request)) {
            return false;
         } else {
            this.mDiscoveryRequest = request;
            return MediaRouteProviderService.this.updateCompositeDiscoveryRequest();
         }
      }

      public void binderDied() {
         MediaRouteProviderService.this.mPrivateHandler.obtainMessage(1, this.mMessenger).sendToTarget();
      }

      public String toString() {
         return MediaRouteProviderService.getClientId(this.mMessenger);
      }
   }

   private final class ProviderCallback extends MediaRouteProvider.Callback {
      public void onDescriptorChanged(MediaRouteProvider provider, MediaRouteProviderDescriptor descriptor) {
         MediaRouteProviderService.this.sendDescriptorChanged(descriptor);
      }
   }

   private final class PrivateHandler extends Handler {
      public void handleMessage(Message msg) {
         switch(msg.what) {
         case 1:
            MediaRouteProviderService.this.onBinderDied((Messenger)msg.obj);
         default:
         }
      }
   }
}
