package com.google.android.gms.wearable;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions.Optional;
import com.google.android.gms.wearable.internal.zzac;
import com.google.android.gms.wearable.internal.zzbh;
import com.google.android.gms.wearable.internal.zzbi;
import com.google.android.gms.wearable.internal.zzds;
import com.google.android.gms.wearable.internal.zzdz;
import com.google.android.gms.wearable.internal.zzey;
import com.google.android.gms.wearable.internal.zzgi;

public class Wearable {
   public static final DataApi DataApi = new zzbi();
   public static final CapabilityApi CapabilityApi = new com.google.android.gms.wearable.internal.zzo();
   public static final MessageApi MessageApi = new zzds();
   public static final NodeApi NodeApi = new zzdz();
   public static final ChannelApi ChannelApi = new zzac();
   private static zzc zzbRl = new com.google.android.gms.wearable.internal.zzk();
   private static zza zzbRm = new com.google.android.gms.wearable.internal.zzh();
   private static zzf zzbRn = new zzbh();
   private static zzi zzbRo = new zzey();
   private static zzu zzbRp = new zzgi();
   private static com.google.android.gms.common.api.Api.zzf zzajR = new com.google.android.gms.common.api.Api.zzf();
   private static final com.google.android.gms.common.api.Api.zza zzajS = new zzj();
   public static final Api API;

   static {
      API = new Api("Wearable.API", zzajS, zzajR);
   }

   public static final class WearableOptions implements Optional {
      private WearableOptions(Wearable.WearableOptions.Builder var1) {
      }

      // $FF: synthetic method
      WearableOptions(Wearable.WearableOptions.Builder var1, zzj var2) {
         this(var1);
      }

      public static class Builder {
         public Wearable.WearableOptions build() {
            return new Wearable.WearableOptions(this, (zzj)null);
         }
      }
   }
}
