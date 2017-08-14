package android.support.v4.media;

import android.media.MediaDescription;
import android.net.Uri;
import android.support.annotation.RequiresApi;

@RequiresApi(23)
class MediaDescriptionCompatApi23 extends MediaDescriptionCompatApi21 {
   public static Uri getMediaUri(Object descriptionObj) {
      return ((MediaDescription)descriptionObj).getMediaUri();
   }

   static class Builder extends MediaDescriptionCompatApi21.Builder {
      public static void setMediaUri(Object builderObj, Uri mediaUri) {
         ((android.media.MediaDescription.Builder)builderObj).setMediaUri(mediaUri);
      }
   }
}
