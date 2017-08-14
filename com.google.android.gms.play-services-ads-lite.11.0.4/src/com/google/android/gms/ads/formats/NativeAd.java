package com.google.android.gms.ads.formats;

import android.graphics.drawable.Drawable;
import android.net.Uri;

public abstract class NativeAd {
   protected abstract Object zzag();

   public abstract static class Image {
      public abstract Drawable getDrawable();

      public abstract Uri getUri();

      public abstract double getScale();
   }
}
