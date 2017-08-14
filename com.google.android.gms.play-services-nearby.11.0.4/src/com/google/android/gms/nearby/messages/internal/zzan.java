package com.google.android.gms.nearby.messages.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.zzbdw;
import com.google.android.gms.internal.zzcpq;
import com.google.android.gms.internal.zzcpt;
import com.google.android.gms.nearby.messages.Message;
import com.google.android.gms.nearby.messages.PublishOptions;

final class zzan extends zzav {
   // $FF: synthetic field
   private Message zzbzk;
   // $FF: synthetic field
   private zzbdw zzbzl;
   // $FF: synthetic field
   private PublishOptions zzbzm;

   zzan(zzak var1, GoogleApiClient var2, Message var3, zzbdw var4, PublishOptions var5) {
      this.zzbzk = var3;
      this.zzbzl = var4;
      this.zzbzm = var5;
      super(var2);
   }

   // $FF: synthetic method
   protected final void zza(com.google.android.gms.common.api.Api.zzb var1) throws RemoteException {
      zzah var3 = (zzah)var1;
      zzbdw var10001 = this.zzzX();
      zzaf var10002 = zzaf.zza(this.zzbzk);
      PublishOptions var8 = this.zzbzm;
      zzbdw var7 = this.zzbzl;
      zzaf var6 = var10002;
      zzbdw var5 = var10001;
      zzax var9 = new zzax(var6, var8.getStrategy(), new zzcpq(var5), var7 == null ? null : new zzcpt(var7));
      ((zzs)var3.zzrf()).zza(var9);
   }
}
