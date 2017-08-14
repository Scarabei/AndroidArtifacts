package com.google.android.gms.internal;

import android.widget.TextView;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.cast.MediaQueueItem;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.uicontroller.UIController;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class zzawd extends UIController {
   private final TextView mView;
   private final List zzavC = new ArrayList();

   public zzawd(TextView var1, List var2) {
      this.mView = var1;
      this.zzavC.addAll(var2);
   }

   public final void onMediaStatusUpdated() {
      RemoteMediaClient var1;
      if ((var1 = this.getRemoteMediaClient()) != null && var1.hasMediaSession()) {
         MediaQueueItem var2;
         if ((var2 = var1.getPreloadedItem()) != null) {
            MediaInfo var3;
            if ((var3 = var2.getMedia()) != null) {
               MediaMetadata var4;
               if ((var4 = var3.getMetadata()) != null) {
                  Iterator var5 = this.zzavC.iterator();

                  String var6;
                  do {
                     if (!var5.hasNext()) {
                        return;
                     }

                     var6 = (String)var5.next();
                  } while(!var4.containsKey(var6));

                  this.mView.setText(var4.getString(var6));
               }
            }
         }
      }
   }
}
