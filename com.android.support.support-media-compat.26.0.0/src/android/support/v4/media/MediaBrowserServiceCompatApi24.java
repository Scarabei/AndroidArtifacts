package android.support.v4.media;

import android.content.Context;
import android.media.browse.MediaBrowser.MediaItem;
import android.os.Bundle;
import android.os.Parcel;
import android.service.media.MediaBrowserService;
import android.service.media.MediaBrowserService.Result;
import android.support.annotation.RequiresApi;
import android.util.Log;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RequiresApi(24)
class MediaBrowserServiceCompatApi24 {
   private static final String TAG = "MBSCompatApi24";
   private static Field sResultFlags;

   public static Object createService(Context context, MediaBrowserServiceCompatApi24.ServiceCompatProxy serviceProxy) {
      return new MediaBrowserServiceCompatApi24.MediaBrowserServiceAdaptor(context, serviceProxy);
   }

   public static void notifyChildrenChanged(Object serviceObj, String parentId, Bundle options) {
      ((MediaBrowserService)serviceObj).notifyChildrenChanged(parentId, options);
   }

   public static Bundle getBrowserRootHints(Object serviceObj) {
      return ((MediaBrowserService)serviceObj).getBrowserRootHints();
   }

   static {
      try {
         sResultFlags = Result.class.getDeclaredField("mFlags");
         sResultFlags.setAccessible(true);
      } catch (NoSuchFieldException var1) {
         Log.w("MBSCompatApi24", var1);
      }

   }

   static class MediaBrowserServiceAdaptor extends MediaBrowserServiceCompatApi23.MediaBrowserServiceAdaptor {
      MediaBrowserServiceAdaptor(Context context, MediaBrowserServiceCompatApi24.ServiceCompatProxy serviceWrapper) {
         super(context, serviceWrapper);
      }

      public void onLoadChildren(String parentId, Result result, Bundle options) {
         ((MediaBrowserServiceCompatApi24.ServiceCompatProxy)this.mServiceProxy).onLoadChildren(parentId, new MediaBrowserServiceCompatApi24.ResultWrapper(result), options);
      }
   }

   static class ResultWrapper {
      Result mResultObj;

      ResultWrapper(Result result) {
         this.mResultObj = result;
      }

      public void sendResult(List result, int flags) {
         try {
            MediaBrowserServiceCompatApi24.sResultFlags.setInt(this.mResultObj, flags);
         } catch (IllegalAccessException var4) {
            Log.w("MBSCompatApi24", var4);
         }

         this.mResultObj.sendResult(this.parcelListToItemList(result));
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

   public interface ServiceCompatProxy extends MediaBrowserServiceCompatApi23.ServiceCompatProxy {
      void onLoadChildren(String var1, MediaBrowserServiceCompatApi24.ResultWrapper var2, Bundle var3);
   }
}
