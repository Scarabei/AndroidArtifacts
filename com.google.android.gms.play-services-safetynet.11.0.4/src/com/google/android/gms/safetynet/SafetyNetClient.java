package com.google.android.gms.safetynet;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.internal.zzbh;
import com.google.android.gms.internal.zzbas;
import com.google.android.gms.internal.zzcsa;
import com.google.android.gms.tasks.Task;

public class SafetyNetClient extends GoogleApi {
   SafetyNetClient(@NonNull Context var1) {
      super(var1, SafetyNet.API, (ApiOptions)null, new zzbas());
   }

   SafetyNetClient(@NonNull Activity var1) {
      super(var1, SafetyNet.API, (ApiOptions)null, new zzbas());
   }

   public Task attest(byte[] var1, String var2) {
      return zzbh.zza(zzcsa.zza(this.zzpi(), var1, var2), new SafetyNetApi.AttestationResponse());
   }

   public Task lookupUri(String var1, String var2, int... var3) {
      return zzbh.zza(zzcsa.zza(this.zzpi(), var1, 3, var2, var3), new SafetyNetApi.SafeBrowsingResponse());
   }

   public Task isVerifyAppsEnabled() {
      return zzbh.zza(SafetyNet.SafetyNetApi.isVerifyAppsEnabled(this.zzpi()), new SafetyNetApi.VerifyAppsUserResponse());
   }

   public Task enableVerifyApps() {
      return zzbh.zza(SafetyNet.SafetyNetApi.enableVerifyApps(this.zzpi()), new SafetyNetApi.VerifyAppsUserResponse());
   }

   public Task listHarmfulApps() {
      return zzbh.zza(SafetyNet.SafetyNetApi.listHarmfulApps(this.zzpi()), new SafetyNetApi.HarmfulAppsResponse());
   }

   public Task verifyWithRecaptcha(String var1) {
      return zzbh.zza(SafetyNet.SafetyNetApi.verifyWithRecaptcha(this.zzpi(), var1), new SafetyNetApi.RecaptchaTokenResponse());
   }

   public Task initSafeBrowsing() {
      return this.zza(new zzj(this));
   }

   public Task shutdownSafeBrowsing() {
      return this.zza(new zzl(this));
   }
}
