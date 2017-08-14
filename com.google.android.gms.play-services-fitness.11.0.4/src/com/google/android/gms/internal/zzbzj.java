package com.google.android.gms.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.PendingResults;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.BleApi;
import com.google.android.gms.fitness.data.BleDevice;
import com.google.android.gms.fitness.request.BleScanCallback;
import com.google.android.gms.fitness.request.StartBleScanRequest;
import com.google.android.gms.fitness.result.BleDevicesResult;

public final class zzbzj implements BleApi {
   private static final Status zzaWh = new Status(5007);

   public final PendingResult startBleScan(GoogleApiClient var1, StartBleScanRequest var2) {
      return PendingResults.zza(zzaWh, var1);
   }

   public final PendingResult stopBleScan(GoogleApiClient var1, BleScanCallback var2) {
      return PendingResults.zza(zzaWh, var1);
   }

   public final PendingResult claimBleDevice(GoogleApiClient var1, String var2) {
      return PendingResults.zza(zzaWh, var1);
   }

   public final PendingResult claimBleDevice(GoogleApiClient var1, BleDevice var2) {
      return PendingResults.zza(zzaWh, var1);
   }

   public final PendingResult unclaimBleDevice(GoogleApiClient var1, String var2) {
      return PendingResults.zza(zzaWh, var1);
   }

   public final PendingResult unclaimBleDevice(GoogleApiClient var1, BleDevice var2) {
      return PendingResults.zza(zzaWh, var1);
   }

   public final PendingResult listClaimedBleDevices(GoogleApiClient var1) {
      return PendingResults.zza(BleDevicesResult.zzB(zzaWh), var1);
   }
}
