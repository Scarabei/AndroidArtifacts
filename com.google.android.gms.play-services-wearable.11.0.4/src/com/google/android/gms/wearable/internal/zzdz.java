package com.google.android.gms.wearable.internal;

import android.content.IntentFilter;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.wearable.NodeApi;

public final class zzdz implements NodeApi {
   public final PendingResult getLocalNode(GoogleApiClient var1) {
      return var1.zzd(new zzea(this, var1));
   }

   public final PendingResult getConnectedNodes(GoogleApiClient var1) {
      return var1.zzd(new zzeb(this, var1));
   }

   public final PendingResult addListener(GoogleApiClient var1, NodeApi.NodeListener var2) {
      IntentFilter[] var3 = new IntentFilter[]{zzez.zzgl("com.google.android.gms.wearable.NODE_CHANGED")};
      return zzb.zza(var1, new zzec(var3), var2);
   }

   public final PendingResult removeListener(GoogleApiClient var1, NodeApi.NodeListener var2) {
      return var1.zzd(new zzed(this, var1, var2));
   }
}
