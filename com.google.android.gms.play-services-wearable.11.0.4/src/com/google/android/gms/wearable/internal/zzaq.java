package com.google.android.gms.wearable.internal;

import android.net.Uri;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

final class zzaq extends zzn {
   // $FF: synthetic field
   private Uri zzbzR;
   // $FF: synthetic field
   private long zzbSm;
   // $FF: synthetic field
   private long zzbSn;
   // $FF: synthetic field
   private zzak zzbSk;

   zzaq(zzak var1, GoogleApiClient var2, Uri var3, long var4, long var6) {
      this.zzbSk = var1;
      this.zzbzR = var3;
      this.zzbSm = var4;
      this.zzbSn = var6;
      super(var2);
   }

   // $FF: synthetic method
   protected final void zza(com.google.android.gms.common.api.Api.zzb var1) throws RemoteException {
      zzfw var3 = (zzfw)var1;
      var3.zza(this, zzak.zza(this.zzbSk), this.zzbzR, this.zzbSm, this.zzbSn);
   }

   // $FF: synthetic method
   public final Result zzb(Status var1) {
      return var1;
   }
}
