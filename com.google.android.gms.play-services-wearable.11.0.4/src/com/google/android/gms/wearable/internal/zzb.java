package com.google.android.gms.wearable.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.zzbdw;

final class zzb extends zzn {
   private Object mListener;
   private zzbdw zzaEU;
   private zzc zzbRI;

   static PendingResult zza(GoogleApiClient var0, zzc var1, Object var2) {
      zzbdw var3 = var0.zzp(var2);
      return var0.zzd(new zzb(var0, var2, var3, var1));
   }

   private zzb(GoogleApiClient var1, Object var2, zzbdw var3, zzc var4) {
      super(var1);
      this.mListener = com.google.android.gms.common.internal.zzbo.zzu(var2);
      this.zzaEU = (zzbdw)com.google.android.gms.common.internal.zzbo.zzu(var3);
      this.zzbRI = (zzc)com.google.android.gms.common.internal.zzbo.zzu(var4);
   }

   // $FF: synthetic method
   protected final void zza(com.google.android.gms.common.api.Api.zzb var1) throws RemoteException {
      zzfw var3 = (zzfw)var1;
      this.zzbRI.zza(var3, this, this.mListener, this.zzaEU);
      this.mListener = null;
      this.zzaEU = null;
   }

   // $FF: synthetic method
   protected final Result zzb(Status var1) {
      this.mListener = null;
      this.zzaEU = null;
      return var1;
   }
}
