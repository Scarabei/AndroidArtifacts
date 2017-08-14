package com.google.android.gms.internal;

import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import java.io.IOException;

final class zzcub implements zzcud {
   // $FF: synthetic field
   private zzcua zzbHG;

   zzcub(zzcua var1) {
      this.zzbHG = var1;
      super();
   }

   public final Info zzAD() {
      Info var1 = null;

      try {
         var1 = AdvertisingIdClient.getAdvertisingIdInfo(zzcua.zza(this.zzbHG));
      } catch (IllegalStateException var3) {
         zzcvk.zzc("IllegalStateException getting Advertising Id Info", var3);
      } catch (GooglePlayServicesRepairableException var4) {
         zzcvk.zzc("GooglePlayServicesRepairableException getting Advertising Id Info", var4);
      } catch (IOException var5) {
         zzcvk.zzc("IOException getting Ad Id Info", var5);
      } catch (GooglePlayServicesNotAvailableException var6) {
         zzcua.zza(this.zzbHG, false);
         zzcvk.zzc("GooglePlayServicesNotAvailableException getting Advertising Id Info", var6);
      } catch (Exception var7) {
         zzcvk.zzc("Unknown exception. Could not get the Advertising Id Info.", var7);
      }

      return var1;
   }
}
