package com.google.android.gms.internal;

import android.os.RemoteException;

final class zzst implements zzth {
   // $FF: synthetic field
   private String val$name;
   // $FF: synthetic field
   private String zzKd;

   zzst(zzss var1, String var2, String var3) {
      this.val$name = var2;
      this.zzKd = var3;
      super();
   }

   public final void zzb(zzti var1) throws RemoteException {
      if (var1.zzKi != null) {
         var1.zzKi.onAppEvent(this.val$name, this.zzKd);
      }

   }
}
