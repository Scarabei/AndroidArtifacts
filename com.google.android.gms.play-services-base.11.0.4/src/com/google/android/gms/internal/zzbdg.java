package com.google.android.gms.internal;

import com.google.android.gms.common.ConnectionResult;

final class zzbdg implements Runnable {
   // $FF: synthetic field
   private ConnectionResult zzaEw;
   // $FF: synthetic field
   private zzbdd zzaEv;

   zzbdg(zzbdd var1, ConnectionResult var2) {
      this.zzaEv = var1;
      this.zzaEw = var2;
      super();
   }

   public final void run() {
      this.zzaEv.onConnectionFailed(this.zzaEw);
   }
}
