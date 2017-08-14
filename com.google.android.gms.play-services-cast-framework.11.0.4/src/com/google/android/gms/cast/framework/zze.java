package com.google.android.gms.cast.framework;

import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzayo;

public final class zze {
   private static final zzayo zzarK = new zzayo("DiscoveryManager");
   private final zzq zzask;

   zze(zzq var1) {
      this.zzask = var1;
   }

   public final IObjectWrapper zznp() {
      try {
         return this.zzask.zznu();
      } catch (RemoteException var2) {
         zzarK.zzb(var2, "Unable to call %s on %s.", new Object[]{"getWrappedThis", zzq.class.getSimpleName()});
         return null;
      }
   }
}
