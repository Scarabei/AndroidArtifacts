package com.google.android.gms.tagmanager;

import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import java.io.IOException;

final class zzb implements zzd {
   // $FF: synthetic field
   private zza zzbDm;

   zzb(zza var1) {
      this.zzbDm = var1;
      super();
   }

   public final Info zzAD() {
      Info var1 = null;

      try {
         var1 = AdvertisingIdClient.getAdvertisingIdInfo(zza.zza(this.zzbDm));
      } catch (IllegalStateException var3) {
         zzdj.zzc("IllegalStateException getting Advertising Id Info", var3);
      } catch (GooglePlayServicesRepairableException var4) {
         zzdj.zzc("GooglePlayServicesRepairableException getting Advertising Id Info", var4);
      } catch (IOException var5) {
         zzdj.zzc("IOException getting Ad Id Info", var5);
      } catch (GooglePlayServicesNotAvailableException var6) {
         zzdj.zzc("GooglePlayServicesNotAvailableException getting Advertising Id Info", var6);
      } catch (Exception var7) {
         zzdj.zzc("Unknown exception. Could not get the Advertising Id Info.", var7);
      }

      return var1;
   }
}
