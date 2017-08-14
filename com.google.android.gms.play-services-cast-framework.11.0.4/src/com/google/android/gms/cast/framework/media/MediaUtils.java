package com.google.android.gms.cast.framework.media;

import android.annotation.TargetApi;
import android.net.Uri;
import android.support.annotation.NonNull;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.cast.MediaTrack;
import com.google.android.gms.common.images.WebImage;
import java.util.Locale;

public class MediaUtils {
   public static String getImageUrl(MediaInfo var0, int var1) {
      Uri var2;
      return (var2 = getImageUri(var0, var1)) == null ? null : var2.toString();
   }

   public static Uri getImageUri(MediaInfo var0, int var1) {
      if (var0 == null) {
         return null;
      } else {
         MediaMetadata var2;
         return (var2 = var0.getMetadata()) != null && var2.getImages() != null && var2.getImages().size() > var1 ? ((WebImage)var2.getImages().get(var1)).getUrl() : null;
      }
   }

   @TargetApi(21)
   public static Locale getTrackLanguage(@NonNull MediaTrack var0) {
      if (var0.getLanguage() != null) {
         if (com.google.android.gms.common.util.zzq.zzse()) {
            return Locale.forLanguageTag(var0.getLanguage());
         } else {
            String[] var1;
            return (var1 = var0.getLanguage().split("-")).length == 1 ? new Locale(var1[0]) : new Locale(var1[0], var1[1]);
         }
      } else {
         return null;
      }
   }
}
