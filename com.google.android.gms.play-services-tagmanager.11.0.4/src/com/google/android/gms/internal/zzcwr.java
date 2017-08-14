package com.google.android.gms.internal;

import android.os.Bundle;
import com.google.android.gms.tagmanager.zzci;

final class zzcwr extends zzci {
   // $FF: synthetic field
   final zzcwn zzbJp;

   zzcwr(zzcwn var1) {
      this.zzbJp = var1;
   }

   public final void onEvent(String var1, String var2, Bundle var3, long var4) {
      if (!var1.endsWith("+gtm")) {
         String var6 = (new StringBuilder(4 + String.valueOf(var1).length())).append(var1).append("+gtm").toString();
         zzcwn.zzf(this.zzbJp).execute(new zzcws(this, var2, var3, var6, var4, var1));
      }

   }
}
