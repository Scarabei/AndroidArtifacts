package com.google.android.gms.wearable.internal;

import android.content.IntentFilter;
import android.net.Uri;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.wearable.CapabilityApi;

public final class zzo implements CapabilityApi {
   public final PendingResult getCapability(GoogleApiClient var1, String var2, int var3) {
      com.google.android.gms.common.internal.zzbo.zzaf(var3 == 0 || var3 == 1);
      return var1.zzd(new zzp(this, var1, var2, var3));
   }

   public final PendingResult getAllCapabilities(GoogleApiClient var1, int var2) {
      com.google.android.gms.common.internal.zzbo.zzaf(var2 == 0 || var2 == 1);
      return var1.zzd(new zzq(this, var1, var2));
   }

   public final PendingResult addLocalCapability(GoogleApiClient var1, String var2) {
      return var1.zzd(new zzr(this, var1, var2));
   }

   public final PendingResult removeLocalCapability(GoogleApiClient var1, String var2) {
      return var1.zzd(new zzs(this, var1, var2));
   }

   public final PendingResult addCapabilityListener(GoogleApiClient var1, CapabilityApi.CapabilityListener var2, String var3) {
      com.google.android.gms.common.internal.zzbo.zzb(var3 != null, "capability must not be null");
      zzv var4 = new zzv(var2, var3);
      IntentFilter var5 = zzez.zzgl("com.google.android.gms.wearable.CAPABILITY_CHANGED");
      if (!var3.startsWith("/")) {
         String var10001 = String.valueOf(var3);
         String var10000;
         if (var10001.length() != 0) {
            var10000 = "/".concat(var10001);
         } else {
            String var10002 = new String;
            var10000 = var10002;
            var10002.<init>("/");
         }

         var3 = var10000;
      }

      var5.addDataPath(var3, 0);
      IntentFilter[] var6 = new IntentFilter[]{var5};
      return zza(var1, var4, var6);
   }

   public final PendingResult addListener(GoogleApiClient var1, CapabilityApi.CapabilityListener var2, Uri var3, int var4) {
      com.google.android.gms.common.internal.zzbo.zzb(var3 != null, "uri must not be null");
      com.google.android.gms.common.internal.zzbo.zzb(var4 == 0 || var4 == 1, "invalid filter type");
      IntentFilter var5 = zzez.zza("com.google.android.gms.wearable.CAPABILITY_CHANGED", var3, var4);
      return zza(var1, var2, new IntentFilter[]{var5});
   }

   private static PendingResult zza(GoogleApiClient var0, CapabilityApi.CapabilityListener var1, IntentFilter[] var2) {
      return zzb.zza(var0, new zzt(var2), var1);
   }

   public final PendingResult removeCapabilityListener(GoogleApiClient var1, CapabilityApi.CapabilityListener var2, String var3) {
      zzv var4 = new zzv(var2, var3);
      return var1.zzd(new zzz(var1, var4, (zzp)null));
   }

   public final PendingResult removeListener(GoogleApiClient var1, CapabilityApi.CapabilityListener var2) {
      return var1.zzd(new zzz(var1, var2, (zzp)null));
   }
}
