package android.support.v4.media;

import android.content.Context;
import android.content.Intent;
import android.media.browse.MediaBrowser.MediaItem;
import android.media.session.MediaSession.Token;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.service.media.MediaBrowserService;
import android.service.media.MediaBrowserService.Result;
import android.support.annotation.RequiresApi;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RequiresApi(21)
class MediaBrowserServiceCompatApi21 {
   public static Object createService(Context context, MediaBrowserServiceCompatApi21.ServiceCompatProxy serviceProxy) {
      return new MediaBrowserServiceCompatApi21.MediaBrowserServiceAdaptor(context, serviceProxy);
   }

   public static void onCreate(Object serviceObj) {
      ((MediaBrowserService)serviceObj).onCreate();
   }

   public static IBinder onBind(Object serviceObj, Intent intent) {
      return ((MediaBrowserService)serviceObj).onBind(intent);
   }

   public static void setSessionToken(Object serviceObj, Object token) {
      ((MediaBrowserService)serviceObj).setSessionToken((Token)token);
   }

   public static void notifyChildrenChanged(Object serviceObj, String parentId) {
      ((MediaBrowserService)serviceObj).notifyChildrenChanged(parentId);
   }

   static class MediaBrowserServiceAdaptor extends MediaBrowserService {
      final MediaBrowserServiceCompatApi21.ServiceCompatProxy mServiceProxy;

      MediaBrowserServiceAdaptor(Context context, MediaBrowserServiceCompatApi21.ServiceCompatProxy serviceWrapper) {
         this.attachBaseContext(context);
         this.mServiceProxy = serviceWrapper;
      }

      public android.service.media.MediaBrowserService.BrowserRoot onGetRoot(String clientPackageName, int clientUid, Bundle rootHints) {
         MediaBrowserServiceCompatApi21.BrowserRoot browserRoot = this.mServiceProxy.onGetRoot(clientPackageName, clientUid, rootHints == null ? null : new Bundle(rootHints));
         return browserRoot == null ? null : new android.service.media.MediaBrowserService.BrowserRoot(browserRoot.mRootId, browserRoot.mExtras);
      }

      public void onLoadChildren(String parentId, Result result) {
         this.mServiceProxy.onLoadChildren(parentId, new MediaBrowserServiceCompatApi21.ResultWrapper(result));
      }
   }

   static class BrowserRoot {
      final String mRootId;
      final Bundle mExtras;

      BrowserRoot(String rootId, Bundle extras) {
         this.mRootId = rootId;
         this.mExtras = extras;
      }
   }

   static class ResultWrapper {
      Result mResultObj;

      ResultWrapper(Result result) {
         this.mResultObj = result;
      }

      public void sendResult(Object result) {
         if (result instanceof List) {
            this.mResultObj.sendResult(this.parcelListToItemList((List)result));
         } else if (result instanceof Parcel) {
            Parcel parcel = (Parcel)result;
            parcel.setDataPosition(0);
            this.mResultObj.sendResult(MediaItem.CREATOR.createFromParcel(parcel));
            parcel.recycle();
         } else {
            this.mResultObj.sendResult((Object)null);
         }

      }

      public void detach() {
         this.mResultObj.detach();
      }

      List parcelListToItemList(List parcelList) {
         if (parcelList == null) {
            return null;
         } else {
            List items = new ArrayList();
            Iterator var3 = parcelList.iterator();

            while(var3.hasNext()) {
               Parcel parcel = (Parcel)var3.next();
               parcel.setDataPosition(0);
               items.add(MediaItem.CREATOR.createFromParcel(parcel));
               parcel.recycle();
            }

            return items;
         }
      }
   }

   public interface ServiceCompatProxy {
      MediaBrowserServiceCompatApi21.BrowserRoot onGetRoot(String var1, int var2, Bundle var3);

      void onLoadChildren(String var1, MediaBrowserServiceCompatApi21.ResultWrapper var2);
   }
}
