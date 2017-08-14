package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.fitness.result.SessionReadResult;

final class zzbzg extends zzbxb {
   private final zzbaz zzaIz;

   private zzbzg(zzbaz var1) {
      this.zzaIz = var1;
   }

   public final void zza(SessionReadResult var1) throws RemoteException {
      this.zzaIz.setResult(var1);
   }

   // $FF: synthetic method
   zzbzg(zzbaz var1, zzbza var2) {
      this(var1);
   }
}
