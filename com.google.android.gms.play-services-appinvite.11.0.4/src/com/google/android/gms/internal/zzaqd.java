package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.Api.zzb;

final class zzaqd extends zzaqa {
   private final String zzajX;

   public zzaqd(zzapy var1, GoogleApiClient var2, String var3) {
      super(var2);
      this.zzajX = var3;
   }

   // $FF: synthetic method
   protected final void zza(zzb var1) throws RemoteException {
      zzaqh var3 = (zzaqh)var1;
      zzaqe var4 = new zzaqe(this);
      String var7 = this.zzajX;
      zzaqe var6 = var4;
      zzaqh var5 = var3;

      try {
         ((zzaql)var5.zzrf()).zzb(var6, var7);
      } catch (RemoteException var8) {
         ;
      }
   }

   // $FF: synthetic method
   protected final Result zzb(Status var1) {
      return var1;
   }
}
