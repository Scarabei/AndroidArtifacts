package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.dynamic.zzn;

final class zzjh extends zziz.zza {
   // $FF: synthetic field
   private Activity val$activity;
   // $FF: synthetic field
   private zziz zzAI;

   zzjh(zziz var1, Activity var2) {
      this.zzAI = var1;
      this.val$activity = var2;
      super();
   }

   // $FF: synthetic method
   public final Object zzdo() throws RemoteException {
      zzwx var2;
      if ((var2 = zziz.zzg(this.zzAI).zze(this.val$activity)) != null) {
         return var2;
      } else {
         zziz.zza((zziz)this.zzAI, (Context)this.val$activity, (String)"ad_overlay");
         return null;
      }
   }

   // $FF: synthetic method
   public final Object zza(zzkh var1) throws RemoteException {
      return var1.createAdOverlay(zzn.zzw(this.val$activity));
   }
}
