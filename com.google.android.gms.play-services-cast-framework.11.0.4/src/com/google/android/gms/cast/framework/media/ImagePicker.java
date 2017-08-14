package com.google.android.gms.cast.framework.media;

import android.support.annotation.NonNull;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.common.images.WebImage;
import com.google.android.gms.dynamic.IObjectWrapper;

public class ImagePicker {
   public static final int IMAGE_TYPE_UNKNOWN = -1;
   public static final int IMAGE_TYPE_MEDIA_ROUTE_CONTROLLER_DIALOG_BACKGROUND = 0;
   public static final int IMAGE_TYPE_NOTIFICATION_THUMBNAIL = 1;
   public static final int IMAGE_TYPE_MINI_CONTROLLER_THUMBNAIL = 2;
   public static final int IMAGE_TYPE_LOCK_SCREEN_BACKGROUND = 3;
   public static final int IMAGE_TYPE_EXPANDED_CONTROLLER_BACKGROUND = 4;
   private final zzb zzatK = new ImagePicker.zza((zzg)null);

   /** @deprecated */
   @Deprecated
   public WebImage onPickImage(MediaMetadata var1, int var2) {
      return var1 != null && var1.hasImages() ? (WebImage)var1.getImages().get(0) : null;
   }

   public WebImage onPickImage(MediaMetadata var1, @NonNull ImageHints var2) {
      return this.onPickImage(var1, var2.getType());
   }

   public final zzb zznU() {
      return this.zzatK;
   }

   class zza extends zzb.zza {
      // $FF: synthetic field
      private ImagePicker zzatL;

      private zza() {
         this.zzatL = ImagePicker.this;
         super();
      }

      public final int zznm() {
         return 11020208;
      }

      public final WebImage onPickImage(MediaMetadata var1, int var2) {
         return this.zzatL.onPickImage(var1, var2);
      }

      public final WebImage zza(MediaMetadata var1, ImageHints var2) {
         return this.zzatL.onPickImage(var1, var2);
      }

      public final IObjectWrapper zznT() {
         return com.google.android.gms.dynamic.zzn.zzw(this.zzatL);
      }

      // $FF: synthetic method
      zza(zzg var2) {
         this();
      }
   }
}
