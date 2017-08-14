package com.google.android.gms.common.images;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;
import com.google.android.gms.common.internal.zzbe;
import com.google.android.gms.internal.zzbfg;
import com.google.android.gms.internal.zzbfl;
import java.lang.ref.WeakReference;

public final class zzc extends zza {
   private WeakReference zzaGm;

   public zzc(ImageView var1, Uri var2) {
      super(var2, 0);
      com.google.android.gms.common.internal.zzc.zzr(var1);
      this.zzaGm = new WeakReference(var1);
   }

   public zzc(ImageView var1, int var2) {
      super((Uri)null, var2);
      com.google.android.gms.common.internal.zzc.zzr(var1);
      this.zzaGm = new WeakReference(var1);
   }

   public final int hashCode() {
      return 0;
   }

   public final boolean equals(Object var1) {
      if (!(var1 instanceof zzc)) {
         return false;
      } else if (this == var1) {
         return true;
      } else {
         zzc var2 = (zzc)var1;
         ImageView var3 = (ImageView)this.zzaGm.get();
         ImageView var4;
         return (var4 = (ImageView)var2.zzaGm.get()) != null && var3 != null && zzbe.equal(var4, var3);
      }
   }

   protected final void zza(Drawable var1, boolean var2, boolean var3, boolean var4) {
      ImageView var5;
      if ((var5 = (ImageView)this.zzaGm.get()) != null) {
         boolean var12;
         if ((var12 = !var3 && !var4) && var5 instanceof zzbfl) {
            int var14 = ((zzbfl)var5).zzqY();
            if (this.zzaGh != 0 && var14 == this.zzaGh) {
               return;
            }
         }

         boolean var13 = this.zzc(var2, var3);
         Object var21 = var1;
         if (var13) {
            Drawable var18 = var5.getDrawable();
            Drawable var20 = null;
            if (var18 != null) {
               if (var18 instanceof zzbfg) {
                  var20 = ((zzbfg)var18).zzqW();
               } else {
                  var20 = var18;
               }
            }

            var21 = new zzbfg(var20, var1);
         }

         var5.setImageDrawable((Drawable)var21);
         if (var5 instanceof zzbfl) {
            zzbfl var15 = (zzbfl)var5;
            Uri var16 = var4 ? this.zzaGf.uri : null;
            var15.zzo(var16);
            int var17 = var12 ? this.zzaGh : 0;
            var15.zzax(var17);
         }

         if (var13) {
            ((zzbfg)var21).startTransition(250);
         }
      }

   }
}
