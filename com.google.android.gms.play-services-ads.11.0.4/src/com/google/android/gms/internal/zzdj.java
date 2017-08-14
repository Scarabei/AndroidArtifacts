package com.google.android.gms.internal;

import android.provider.Settings.SettingNotFoundException;
import java.lang.reflect.InvocationTargetException;

public final class zzdj extends zzec {
   public zzdj(zzdb var1, String var2, String var3, zzax var4, int var5, int var6) {
      super(var1, var2, var3, var4, var5, 49);
   }

   protected final void zzT() throws IllegalAccessException, InvocationTargetException {
      this.zzro.zzbI = Integer.valueOf(2);

      try {
         boolean var1 = ((Boolean)this.zzrx.invoke((Object)null, this.zzpJ.getApplicationContext())).booleanValue();
         this.zzro.zzbI = var1 ? 1 : 0;
      } catch (InvocationTargetException var2) {
         if (!(var2.getTargetException() instanceof SettingNotFoundException)) {
            throw var2;
         }
      }
   }
}
