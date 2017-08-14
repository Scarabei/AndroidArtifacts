package com.google.android.gms.wearable.internal;

import android.content.IntentFilter;
import android.net.Uri;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.internal.zzbdw;
import com.google.android.gms.wearable.MessageApi;

public final class zzds implements MessageApi {
   public final PendingResult sendMessage(GoogleApiClient var1, String var2, String var3, byte[] var4) {
      return var1.zzd(new zzdt(this, var1, var2, var3, var4));
   }

   public final PendingResult addListener(GoogleApiClient var1, MessageApi.MessageListener var2) {
      IntentFilter[] var3 = new IntentFilter[]{zzez.zzgl("com.google.android.gms.wearable.MESSAGE_RECEIVED")};
      return zza(var1, var2, var3);
   }

   public final PendingResult addListener(GoogleApiClient var1, MessageApi.MessageListener var2, Uri var3, int var4) {
      com.google.android.gms.common.internal.zzbo.zzb(var3 != null, "uri must not be null");
      com.google.android.gms.common.internal.zzbo.zzb(var4 == 0 || var4 == 1, "invalid filter type");
      IntentFilter var5 = zzez.zza("com.google.android.gms.wearable.MESSAGE_RECEIVED", var3, var4);
      return zza(var1, var2, new IntentFilter[]{var5});
   }

   private static PendingResult zza(GoogleApiClient var0, MessageApi.MessageListener var1, IntentFilter[] var2) {
      zzbdw var3 = var0.zzp(var1);
      return var0.zzd(new zzdv(var0, var1, var3, var2, (zzdt)null));
   }

   public final PendingResult removeListener(GoogleApiClient var1, MessageApi.MessageListener var2) {
      return var1.zzd(new zzdu(this, var1, var2));
   }
}
