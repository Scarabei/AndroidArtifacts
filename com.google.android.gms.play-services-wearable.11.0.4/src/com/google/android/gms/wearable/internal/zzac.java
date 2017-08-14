package com.google.android.gms.wearable.internal;

import android.content.IntentFilter;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.wearable.ChannelApi;

public final class zzac implements ChannelApi {
   public final PendingResult openChannel(GoogleApiClient var1, String var2, String var3) {
      com.google.android.gms.common.internal.zzbo.zzb(var1, "client is null");
      com.google.android.gms.common.internal.zzbo.zzb(var2, "nodeId is null");
      com.google.android.gms.common.internal.zzbo.zzb(var3, "path is null");
      return var1.zzd(new zzad(this, var1, var2, var3));
   }

   public final PendingResult addListener(GoogleApiClient var1, ChannelApi.ChannelListener var2) {
      com.google.android.gms.common.internal.zzbo.zzb(var1, "client is null");
      com.google.android.gms.common.internal.zzbo.zzb(var2, "listener is null");
      IntentFilter[] var3 = new IntentFilter[]{zzez.zzgl("com.google.android.gms.wearable.CHANNEL_EVENT")};
      return zzb.zza(var1, new zzae(var3), var2);
   }

   public final PendingResult removeListener(GoogleApiClient var1, ChannelApi.ChannelListener var2) {
      com.google.android.gms.common.internal.zzbo.zzb(var1, "client is null");
      com.google.android.gms.common.internal.zzbo.zzb(var2, "listener is null");
      return var1.zzd(new zzag(var1, var2, (String)null));
   }
}
