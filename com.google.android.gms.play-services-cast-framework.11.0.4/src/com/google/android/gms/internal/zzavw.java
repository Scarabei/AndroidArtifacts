package com.google.android.gms.internal;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.framework.CastContext;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.media.CastMediaOptions;
import com.google.android.gms.cast.framework.media.ImageHints;
import com.google.android.gms.cast.framework.media.ImagePicker;
import com.google.android.gms.cast.framework.media.MediaUtils;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.uicontroller.UIController;
import com.google.android.gms.common.images.WebImage;

public final class zzavw extends UIController {
   private final ImageView zzavr;
   private final ImageHints zzauP;
   private final Bitmap zzavv;
   private final View zzavw;
   private final ImagePicker zzatH;
   private final zzavc zzavt;

   public zzavw(ImageView var1, Context var2, @NonNull ImageHints var3, int var4, View var5) {
      this.zzavr = var1;
      this.zzauP = var3;
      this.zzavv = var4 != 0 ? BitmapFactory.decodeResource(var2.getResources(), var4) : null;
      this.zzavw = var5;
      CastMediaOptions var6 = CastContext.getSharedInstance(var2).getCastOptions().getCastMediaOptions();
      this.zzatH = var6 != null ? var6.getImagePicker() : null;
      this.zzavt = new zzavc(var2.getApplicationContext());
   }

   public final void onSessionConnected(CastSession var1) {
      super.onSessionConnected(var1);
      this.zzavt.zza(new zzavx(this));
      this.zzol();
      this.zzok();
   }

   public final void onSessionEnded() {
      this.zzavt.clear();
      this.zzol();
      super.onSessionEnded();
   }

   public final void onMediaStatusUpdated() {
      this.zzok();
   }

   private final void zzok() {
      RemoteMediaClient var1;
      if ((var1 = this.getRemoteMediaClient()) != null && var1.hasMediaSession()) {
         MediaInfo var4 = var1.getMediaInfo();
         Uri var2;
         WebImage var5;
         if ((var2 = var4 == null ? null : (this.zzatH != null && (var5 = this.zzatH.onPickImage(var4.getMetadata(), this.zzauP)) != null && var5.getUrl() != null ? var5.getUrl() : MediaUtils.getImageUri(var4, 0))) == null) {
            this.zzol();
         } else {
            this.zzavt.zzm(var2);
         }
      } else {
         this.zzol();
      }
   }

   private final void zzol() {
      if (this.zzavw != null) {
         this.zzavw.setVisibility(0);
         this.zzavr.setVisibility(4);
      }

      if (this.zzavv != null) {
         this.zzavr.setImageBitmap(this.zzavv);
      }

   }

   // $FF: synthetic method
   static View zza(zzavw var0) {
      return var0.zzavw;
   }

   // $FF: synthetic method
   static ImageView zzb(zzavw var0) {
      return var0.zzavr;
   }
}
