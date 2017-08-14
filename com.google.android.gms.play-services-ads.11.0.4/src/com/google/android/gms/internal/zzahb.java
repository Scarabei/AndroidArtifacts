package com.google.android.gms.internal;

import android.content.Context;

final class zzahb implements Runnable {
   // $FF: synthetic field
   private Context zztF;
   // $FF: synthetic field
   private zzagz zzZy;

   zzahb(zzagz var1, Context var2) {
      this.zzZy = var1;
      this.zztF = var2;
      super();
   }

   public final void run() {
      synchronized(zzagz.zza(this.zzZy)) {
         zzagz.zza(this.zzZy, zzagz.zzF(this.zztF));
         zzagz.zza(this.zzZy).notifyAll();
      }
   }
}
