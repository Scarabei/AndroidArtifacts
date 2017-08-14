package com.google.android.gms.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.fitness.BleApi;
import com.google.android.gms.fitness.data.BleDevice;
import com.google.android.gms.fitness.request.BleScanCallback;
import com.google.android.gms.fitness.request.StartBleScanRequest;

public final class zzbxk implements BleApi {
   public final PendingResult startBleScan(GoogleApiClient var1, StartBleScanRequest var2) {
      return var1.zzd(new zzbxl(this, var1, var2));
   }

   public final PendingResult stopBleScan(GoogleApiClient var1, BleScanCallback var2) {
      return var1.zzd(new zzbxm(this, var1, var2));
   }

   public final PendingResult claimBleDevice(GoogleApiClient var1, String var2) {
      return var1.zze(new zzbxn(this, var1, var2));
   }

   public final PendingResult claimBleDevice(GoogleApiClient var1, BleDevice var2) {
      return var1.zze(new zzbxo(this, var1, var2));
   }

   public final PendingResult unclaimBleDevice(GoogleApiClient var1, String var2) {
      return var1.zze(new zzbxp(this, var1, var2));
   }

   public final PendingResult unclaimBleDevice(GoogleApiClient var1, BleDevice var2) {
      return this.unclaimBleDevice(var1, var2.getAddress());
   }

   public final PendingResult listClaimedBleDevices(GoogleApiClient var1) {
      return var1.zzd(new zzbxq(this, var1));
   }
}
