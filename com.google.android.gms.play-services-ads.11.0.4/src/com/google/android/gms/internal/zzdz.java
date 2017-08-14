package com.google.android.gms.internal;

import android.util.DisplayMetrics;
import android.view.View;
import java.lang.reflect.InvocationTargetException;

public final class zzdz extends zzec {
   private final View zzru;

   public zzdz(zzdb var1, String var2, String var3, zzax var4, int var5, int var6, View var7) {
      super(var1, var2, var3, var4, var5, 57);
      this.zzru = var7;
   }

   protected final void zzT() throws IllegalAccessException, InvocationTargetException {
      if (this.zzru != null) {
         DisplayMetrics var1 = this.zzpJ.getContext().getResources().getDisplayMetrics();
         zzme var6 = zzmo.zzEY;
         boolean var2 = ((Boolean)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var6)).booleanValue();
         String var3 = (String)this.zzrx.invoke((Object)null, this.zzru, var1, var2);
         zzdh var4 = new zzdh(var3);
         zzaz var5;
         (var5 = new zzaz()).zzcu = var4.zzrj;
         var5.zzcv = var4.zzcv;
         var5.zzcw = var4.zzcw;
         this.zzro.zzbR = var5;
      }

   }
}
