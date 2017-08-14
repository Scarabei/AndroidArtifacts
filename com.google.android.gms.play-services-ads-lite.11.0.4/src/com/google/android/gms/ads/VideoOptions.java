package com.google.android.gms.ads;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.internal.zzlx;
import com.google.android.gms.internal.zzzn;

@zzzn
public final class VideoOptions {
   private final boolean zzsf;
   private final boolean zzsg;

   public VideoOptions(zzlx var1) {
      this.zzsf = var1.zzBJ;
      this.zzsg = var1.zzBK;
   }

   private VideoOptions(VideoOptions.Builder var1) {
      this.zzsf = var1.zzsf;
      this.zzsg = var1.zzsg;
   }

   public final boolean getStartMuted() {
      return this.zzsf;
   }

   @KeepForSdk
   public final boolean getCustomControlsRequested() {
      return this.zzsg;
   }

   // $FF: synthetic method
   VideoOptions(VideoOptions.Builder var1, zzc var2) {
      this(var1);
   }

   public static final class Builder {
      private boolean zzsf = true;
      private boolean zzsg = false;

      public final VideoOptions.Builder setStartMuted(boolean var1) {
         this.zzsf = var1;
         return this;
      }

      @KeepForSdk
      public final VideoOptions.Builder setCustomControlsRequested(boolean var1) {
         this.zzsg = var1;
         return this;
      }

      public final VideoOptions build() {
         return new VideoOptions(this, (zzc)null);
      }
   }
}
