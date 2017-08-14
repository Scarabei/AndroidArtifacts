package com.google.android.gms.internal;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import com.google.android.gms.cast.framework.media.ImageHints;

public final class zzavc implements zzavg {
   private final Context zzarM;
   private final ImageHints zzauP;
   private Uri zzauQ;
   private zzave zzauR;
   private zzavh zzauS;
   private Bitmap mBitmap;
   private boolean zzauT;
   private zzavd zzauU;

   public zzavc(Context var1) {
      this(var1, new ImageHints(-1, 0, 0));
   }

   public zzavc(Context var1, @NonNull ImageHints var2) {
      this.zzarM = var1;
      this.zzauP = var2;
      this.zzauS = new zzavh();
      this.reset();
   }

   public final void zza(zzavd var1) {
      this.zzauU = var1;
   }

   public final boolean zzm(Uri var1) {
      if (var1 == null) {
         this.reset();
         return true;
      } else if (var1.equals(this.zzauQ)) {
         return this.zzauT;
      } else {
         this.reset();
         this.zzauQ = var1;
         Context var2;
         if (this.zzauP.getWidthInPixels() != 0 && this.zzauP.getHeightInPixels() != 0) {
            Context var10001 = this.zzarM;
            int var10002 = this.zzauP.getWidthInPixels();
            int var4 = this.zzauP.getHeightInPixels();
            int var3 = var10002;
            var2 = var10001;
            this.zzauR = new zzave(var2, var3, var4, false, this);
         } else {
            var2 = this.zzarM;
            this.zzauR = new zzave(var2, this);
         }

         Uri var6 = this.zzauQ;
         zzave var7 = this.zzauR;
         this.zzauR.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Uri[]{var6});
         return false;
      }
   }

   public final void clear() {
      this.reset();
      this.zzauU = null;
   }

   public final void onPostExecute(Bitmap var1) {
      this.mBitmap = var1;
      this.zzauT = true;
      if (this.zzauU != null) {
         this.zzauU.zzc(this.mBitmap);
      }

      this.zzauR = null;
   }

   private final void reset() {
      if (this.zzauR != null) {
         this.zzauR.cancel(true);
         this.zzauR = null;
      }

      this.zzauQ = null;
      this.mBitmap = null;
      this.zzauT = false;
   }
}
