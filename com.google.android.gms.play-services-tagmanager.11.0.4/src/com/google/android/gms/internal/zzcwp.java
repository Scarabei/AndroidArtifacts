package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.tagmanager.zzcl;

final class zzcwp extends zzcl {
   // $FF: synthetic field
   final zzcwn zzbJp;

   zzcwp(zzcwn var1) {
      this.zzbJp = var1;
   }

   public final void interceptEvent(String var1, String var2, Bundle var3, long var4) throws RemoteException {
      String var6 = (new StringBuilder(4 + String.valueOf(var1).length())).append(var1).append("+gtm").toString();
      zzcwn.zzf(this.zzbJp).execute(new zzcwq(this, var2, var3, var6, var4, var1));
   }
}
