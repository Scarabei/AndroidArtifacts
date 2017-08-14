package com.google.android.gms.common.images;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import com.google.android.gms.internal.zzbfm;

public abstract class zza {
   final zzb zzaGf;
   private int zzaGg = 0;
   protected int zzaGh = 0;
   private boolean zzaGi = false;
   private boolean zzaGj = true;
   private boolean zzaGk = false;
   private boolean zzaGl = true;

   public zza(Uri var1, int var2) {
      this.zzaGf = new zzb(var1);
      this.zzaGh = var2;
   }

   final void zza(Context var1, Bitmap var2, boolean var3) {
      com.google.android.gms.common.internal.zzc.zzr(var2);
      BitmapDrawable var4 = new BitmapDrawable(var1.getResources(), var2);
      this.zza(var4, var3, false, true);
   }

   final void zza(Context var1, zzbfm var2) {
      if (this.zzaGl) {
         this.zza((Drawable)null, false, true, false);
      }

   }

   final void zza(Context var1, zzbfm var2, boolean var3) {
      Drawable var4 = null;
      if (this.zzaGh != 0) {
         int var6 = this.zzaGh;
         var4 = var1.getResources().getDrawable(var6);
      }

      this.zza(var4, var3, false, false);
   }

   protected abstract void zza(Drawable var1, boolean var2, boolean var3, boolean var4);

   protected final boolean zzc(boolean var1, boolean var2) {
      return this.zzaGj && !var2 && !var1;
   }
}
