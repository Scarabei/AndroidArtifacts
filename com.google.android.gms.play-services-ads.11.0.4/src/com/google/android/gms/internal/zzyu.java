package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory.Options;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Looper;
import android.os.SystemClock;
import com.google.android.gms.common.util.zzq;
import java.io.InputStream;

final class zzyu implements zzaii {
   // $FF: synthetic field
   private boolean zzRT;
   // $FF: synthetic field
   private double zzRU;
   // $FF: synthetic field
   private boolean zzRV;
   // $FF: synthetic field
   private String zzNO;
   // $FF: synthetic field
   private zzyn zzRF;

   zzyu(zzyn var1, boolean var2, double var3, boolean var5, String var6) {
      this.zzRF = var1;
      this.zzRT = var2;
      this.zzRU = var3;
      this.zzRV = var5;
      this.zzNO = var6;
      super();
   }

   @TargetApi(19)
   private final zznp zzg(InputStream var1) {
      Bitmap var2 = null;
      Options var3;
      (var3 = new Options()).inDensity = (int)(160.0D * this.zzRU);
      if (!this.zzRV) {
         var3.inPreferredConfig = Config.RGB_565;
      }

      long var4 = SystemClock.uptimeMillis();

      try {
         var2 = BitmapFactory.decodeStream(var1, (Rect)null, var3);
      } catch (Exception var14) {
         zzafr.zzb("Error grabbing image.", var14);
      }

      if (var2 == null) {
         this.zzRF.zzc(2, this.zzRT);
         return null;
      } else {
         long var6 = SystemClock.uptimeMillis();
         if (zzq.zzsc() && zzafr.zzhM()) {
            int var8 = var2.getWidth();
            int var9 = var2.getHeight();
            int var10 = var2.getAllocationByteCount();
            long var11 = var6 - var4;
            boolean var13 = Looper.getMainLooper().getThread() == Thread.currentThread();
            zzafr.v((new StringBuilder(108)).append("Decoded image w: ").append(var8).append(" h:").append(var9).append(" bytes: ").append(var10).append(" time: ").append(var11).append(" on ui thread: ").append(var13).toString());
         }

         return new zznp(new BitmapDrawable(Resources.getSystem(), var2), Uri.parse(this.zzNO), this.zzRU);
      }
   }

   // $FF: synthetic method
   public final Object zzgy() {
      this.zzRF.zzc(2, this.zzRT);
      return null;
   }

   // $FF: synthetic method
   @TargetApi(19)
   public final Object zzh(InputStream var1) {
      return this.zzg(var1);
   }
}
