package com.google.android.gms.internal;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import java.io.ByteArrayOutputStream;

final class zzaeh implements Runnable {
   // $FF: synthetic field
   private Bitmap val$bitmap;
   // $FF: synthetic field
   private zzaeg zzXf;

   zzaeh(zzaeg var1, Bitmap var2) {
      this.zzXf = var1;
      this.val$bitmap = var2;
      super();
   }

   public final void run() {
      ByteArrayOutputStream var1 = new ByteArrayOutputStream();
      this.val$bitmap.compress(CompressFormat.PNG, 0, var1);
      synchronized(zzaeg.zza(this.zzXf)) {
         zzaeg.zzb(this.zzXf).zzcsQ = new aea();
         zzaeg.zzb(this.zzXf).zzcsQ.zzctl = var1.toByteArray();
         zzaeg.zzb(this.zzXf).zzcsQ.mimeType = "image/png";
         zzaeg.zzb(this.zzXf).zzcsQ.zzcsJ = Integer.valueOf(1);
      }
   }
}
