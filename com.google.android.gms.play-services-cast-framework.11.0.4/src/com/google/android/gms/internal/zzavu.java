package com.google.android.gms.internal;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.widget.ImageView;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaQueueItem;
import com.google.android.gms.cast.framework.CastContext;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.media.CastMediaOptions;
import com.google.android.gms.cast.framework.media.ImageHints;
import com.google.android.gms.cast.framework.media.ImagePicker;
import com.google.android.gms.cast.framework.media.MediaUtils;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.uicontroller.UIController;
import com.google.android.gms.common.images.WebImage;

public final class zzavu extends UIController {
   private final ImageView zzavr;
   private final ImageHints zzauP;
   private final Bitmap zzavs;
   private final ImagePicker zzatH;
   private final zzavc zzavt;

   public zzavu(ImageView var1, Context var2, @NonNull ImageHints var3, int var4) {
      this.zzavr = var1;
      this.zzauP = var3;
      this.zzavs = BitmapFactory.decodeResource(var2.getResources(), var4);
      CastMediaOptions var5 = CastContext.getSharedInstance(var2).getCastOptions().getCastMediaOptions();
      this.zzatH = var5 != null ? var5.getImagePicker() : null;
      this.zzavt = new zzavc(var2.getApplicationContext());
   }

   public final void onSessionConnected(CastSession var1) {
      super.onSessionConnected(var1);
      this.zzavt.zza(new zzavv(this));
      this.zzavr.setImageBitmap(this.zzavs);
      this.zzok();
   }

   public final void onSessionEnded() {
      this.zzavt.clear();
      this.zzavr.setImageBitmap(this.zzavs);
      super.onSessionEnded();
   }

   public final void onMediaStatusUpdated() {
      this.zzok();
   }

   private final void zzok() {
      RemoteMediaClient var1;
      if ((var1 = this.getRemoteMediaClient()) != null && var1.hasMediaSession()) {
         MediaQueueItem var4 = var1.getPreloadedItem();
         Uri var2;
         MediaInfo var5;
         WebImage var6;
         if ((var2 = var4 == null ? null : ((var5 = var4.getMedia()) == null ? null : (this.zzatH != null && (var6 = this.zzatH.onPickImage(var5.getMetadata(), this.zzauP)) != null && var6.getUrl() != null ? var6.getUrl() : MediaUtils.getImageUri(var5, 0)))) == null) {
            this.zzavr.setImageBitmap(this.zzavs);
         } else {
            this.zzavt.zzm(var2);
         }
      } else {
         this.zzavr.setImageBitmap(this.zzavs);
      }
   }

   // $FF: synthetic method
   static ImageView zza(zzavu var0) {
      return var0.zzavr;
   }
}
