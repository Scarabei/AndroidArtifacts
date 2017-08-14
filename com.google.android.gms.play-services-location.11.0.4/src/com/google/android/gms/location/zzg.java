package com.google.android.gms.location;

import android.os.RemoteException;
import com.google.android.gms.internal.zzbdw;
import com.google.android.gms.internal.zzbee;
import com.google.android.gms.internal.zzccu;
import com.google.android.gms.internal.zzcdj;
import com.google.android.gms.internal.zzcdn;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzg extends zzbee {
   // $FF: synthetic field
   private zzcdn zzbhD;
   // $FF: synthetic field
   private zzbdw zzbhE;

   zzg(FusedLocationProviderClient var1, zzbdw var2, zzcdn var3, zzbdw var4) {
      this.zzbhD = var3;
      this.zzbhE = var4;
      super(var2);
   }

   // $FF: synthetic method
   protected final void zzb(com.google.android.gms.common.api.Api.zzb var1, TaskCompletionSource var2) throws RemoteException {
      zzcdj var4 = (zzcdj)var1;
      FusedLocationProviderClient.zza var6 = new FusedLocationProviderClient.zza(var2);
      var4.zza((zzcdn)this.zzbhD, (zzbdw)this.zzbhE, (zzccu)var6);
   }
}
