package com.google.android.gms.internal;

import android.support.annotation.NonNull;
import android.widget.TextView;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.uicontroller.UIController;

public final class zzawt extends UIController {
   private final TextView zzavZ;

   public zzawt(@NonNull TextView var1) {
      this.zzavZ = var1;
   }

   public final void onMediaStatusUpdated() {
      RemoteMediaClient var1;
      if ((var1 = this.getRemoteMediaClient()) != null) {
         MediaInfo var2;
         if ((var2 = var1.getMediaInfo()) != null) {
            MediaMetadata var3;
            if ((var3 = var2.getMetadata()) != null) {
               String var4 = "com.google.android.gms.cast.metadata.SUBTITLE";
               if (!var3.containsKey(var4)) {
                  switch(var3.getMediaType()) {
                  case 1:
                     var4 = "com.google.android.gms.cast.metadata.STUDIO";
                     break;
                  case 2:
                     var4 = "com.google.android.gms.cast.metadata.SERIES_TITLE";
                     break;
                  case 3:
                     if (!var3.containsKey("com.google.android.gms.cast.metadata.ARTIST")) {
                        if (var3.containsKey("com.google.android.gms.cast.metadata.ALBUM_ARTIST")) {
                           var4 = "com.google.android.gms.cast.metadata.ALBUM_ARTIST";
                        } else if (var3.containsKey("com.google.android.gms.cast.metadata.COMPOSER")) {
                           var4 = "com.google.android.gms.cast.metadata.COMPOSER";
                        }
                        break;
                     }
                  case 4:
                     var4 = "com.google.android.gms.cast.metadata.ARTIST";
                  }
               }

               if (var3.containsKey(var4)) {
                  this.zzavZ.setText(var3.getString(var4));
               }

            }
         }
      }
   }
}
