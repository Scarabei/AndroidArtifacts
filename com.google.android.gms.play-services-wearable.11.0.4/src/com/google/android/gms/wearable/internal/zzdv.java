package com.google.android.gms.wearable.internal;

import android.content.IntentFilter;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.zzbdw;
import com.google.android.gms.wearable.MessageApi;

final class zzdv extends zzn {
   private MessageApi.MessageListener zzbSV;
   private zzbdw zzaEU;
   private IntentFilter[] zzbSW;

   private zzdv(GoogleApiClient var1, MessageApi.MessageListener var2, zzbdw var3, IntentFilter[] var4) {
      super(var1);
      this.zzbSV = (MessageApi.MessageListener)com.google.android.gms.common.internal.zzbo.zzu(var2);
      this.zzaEU = (zzbdw)com.google.android.gms.common.internal.zzbo.zzu(var3);
      this.zzbSW = (IntentFilter[])com.google.android.gms.common.internal.zzbo.zzu(var4);
   }

   // $FF: synthetic method
   protected final void zza(com.google.android.gms.common.api.Api.zzb var1) throws RemoteException {
      zzfw var3 = (zzfw)var1;
      var3.zza(this, (MessageApi.MessageListener)this.zzbSV, this.zzaEU, this.zzbSW);
      this.zzbSV = null;
      this.zzaEU = null;
      this.zzbSW = null;
   }

   // $FF: synthetic method
   public final Result zzb(Status var1) {
      this.zzbSV = null;
      this.zzaEU = null;
      this.zzbSW = null;
      return var1;
   }

   // $FF: synthetic method
   zzdv(GoogleApiClient var1, MessageApi.MessageListener var2, zzbdw var3, IntentFilter[] var4, zzdt var5) {
      this(var1, var2, var3, var4);
   }
}
