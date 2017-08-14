package com.google.android.gms.internal;

import android.content.ComponentName;
import android.support.customtabs.CustomTabsClient;
import android.support.customtabs.CustomTabsServiceConnection;
import java.lang.ref.WeakReference;

public final class aep extends CustomTabsServiceConnection {
   private WeakReference zzcuA;

   public aep(aeq var1) {
      this.zzcuA = new WeakReference(var1);
   }

   public final void onCustomTabsServiceConnected(ComponentName var1, CustomTabsClient var2) {
      aeq var3;
      if ((var3 = (aeq)this.zzcuA.get()) != null) {
         var3.zza(var2);
      }

   }

   public final void onServiceDisconnected(ComponentName var1) {
      aeq var2;
      if ((var2 = (aeq)this.zzcuA.get()) != null) {
         var2.zzdZ();
      }

   }
}
