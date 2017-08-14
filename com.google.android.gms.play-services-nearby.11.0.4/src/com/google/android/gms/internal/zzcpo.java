package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.nearby.messages.internal.zzaf;
import com.google.android.gms.nearby.messages.internal.zzn;
import java.util.List;

public final class zzcpo extends zzn implements zzcpn {
   private final zzbdw zzbzE;

   zzcpo(zzbdw var1) {
      this.zzbzE = var1;
   }

   public final void zza(zzaf var1) {
   }

   public final void zzb(zzaf var1) {
   }

   public final void zzH(List var1) throws RemoteException {
      this.zzbzE.zza(new zzcpp(this, var1));
   }

   public final zzbdw zzzX() {
      return this.zzbzE;
   }
}
