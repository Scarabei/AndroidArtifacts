package com.google.android.gms.internal;

import android.support.annotation.NonNull;
import com.google.android.gms.auth.api.signin.internal.zzy;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

final class zzbct implements ResultCallback {
   // $FF: synthetic field
   private zzben zzaDP;
   // $FF: synthetic field
   private boolean zzaDQ;
   // $FF: synthetic field
   private GoogleApiClient zzaqW;
   // $FF: synthetic field
   private zzbcp zzaDN;

   zzbct(zzbcp var1, zzben var2, boolean var3, GoogleApiClient var4) {
      this.zzaDN = var1;
      this.zzaDP = var2;
      this.zzaDQ = var3;
      this.zzaqW = var4;
      super();
   }

   // $FF: synthetic method
   public final void onResult(@NonNull Result var1) {
      Status var3 = (Status)var1;
      zzy.zzaj(zzbcp.zzc(this.zzaDN)).zzmP();
      if (var3.isSuccess() && this.zzaDN.isConnected()) {
         this.zzaDN.reconnect();
      }

      this.zzaDP.setResult(var3);
      if (this.zzaDQ) {
         this.zzaqW.disconnect();
      }

   }
}
