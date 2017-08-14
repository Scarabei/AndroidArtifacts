package com.google.android.gms.wearable.internal;

import android.net.Uri;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

final class zzap extends zzn {
   // $FF: synthetic field
   private Uri zzbzR;
   // $FF: synthetic field
   private boolean zzbSl;
   // $FF: synthetic field
   private zzak zzbSk;

   zzap(zzak var1, GoogleApiClient var2, Uri var3, boolean var4) {
      this.zzbSk = var1;
      this.zzbzR = var3;
      this.zzbSl = var4;
      super(var2);
   }

   // $FF: synthetic method
   protected final void zza(com.google.android.gms.common.api.Api.zzb var1) throws RemoteException {
      zzfw var3 = (zzfw)var1;
      var3.zza(this, zzak.zza(this.zzbSk), this.zzbzR, this.zzbSl);
   }

   // $FF: synthetic method
   public final Result zzb(Status var1) {
      return var1;
   }
}
