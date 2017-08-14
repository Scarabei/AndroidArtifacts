package com.google.android.gms.common.images;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import com.google.android.gms.common.internal.zzbe;
import java.lang.ref.WeakReference;
import java.util.Arrays;

public final class zzd extends zza {
   private WeakReference zzaGn;

   public zzd(ImageManager.OnImageLoadedListener var1, Uri var2) {
      super(var2, 0);
      com.google.android.gms.common.internal.zzc.zzr(var1);
      this.zzaGn = new WeakReference(var1);
   }

   public final int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzaGf});
   }

   public final boolean equals(Object var1) {
      if (!(var1 instanceof zzd)) {
         return false;
      } else if (this == var1) {
         return true;
      } else {
         zzd var2 = (zzd)var1;
         ImageManager.OnImageLoadedListener var3 = (ImageManager.OnImageLoadedListener)this.zzaGn.get();
         ImageManager.OnImageLoadedListener var4;
         return (var4 = (ImageManager.OnImageLoadedListener)var2.zzaGn.get()) != null && var3 != null && zzbe.equal(var4, var3) && zzbe.equal(var2.zzaGf, this.zzaGf);
      }
   }

   protected final void zza(Drawable var1, boolean var2, boolean var3, boolean var4) {
      ImageManager.OnImageLoadedListener var5;
      if (!var3 && (var5 = (ImageManager.OnImageLoadedListener)this.zzaGn.get()) != null) {
         var5.onImageLoaded(this.zzaGf.uri, var1, var4);
      }

   }
}
