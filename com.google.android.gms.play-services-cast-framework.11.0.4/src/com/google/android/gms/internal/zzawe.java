package com.google.android.gms.internal;

import android.widget.TextView;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.uicontroller.UIController;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class zzawe extends UIController {
   private final TextView mView;
   private final List zzavC = new ArrayList();

   public zzawe(TextView var1, List var2) {
      this.mView = var1;
      this.zzavC.addAll(var2);
   }

   public final void onMediaStatusUpdated() {
      RemoteMediaClient var1;
      if ((var1 = this.getRemoteMediaClient()) != null && var1.hasMediaSession()) {
         MediaInfo var2;
         if ((var2 = var1.getMediaStatus().getMediaInfo()) != null) {
            MediaMetadata var3;
            if ((var3 = var2.getMetadata()) != null) {
               Iterator var4 = this.zzavC.iterator();

               String var5;
               do {
                  if (!var4.hasNext()) {
                     return;
                  }

                  var5 = (String)var4.next();
               } while(!var3.containsKey(var5));

               this.mView.setText(var3.getString(var5));
            }
         }
      }
   }
}
