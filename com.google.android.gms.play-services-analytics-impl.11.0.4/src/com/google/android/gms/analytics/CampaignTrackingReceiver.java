package com.google.android.gms.analytics;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.RequiresPermission;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.internal.zzamj;
import com.google.android.gms.internal.zzaoc;
import com.google.android.gms.internal.zzaos;
import com.google.android.gms.internal.zzctz;

public class CampaignTrackingReceiver extends BroadcastReceiver {
   static Object zzuF = new Object();
   static zzctz zzads;
   private static Boolean zzadt;

   public static boolean zzac(Context var0) {
      zzbo.zzu(var0);
      if (zzadt != null) {
         return zzadt.booleanValue();
      } else {
         boolean var1;
         zzadt = var1 = zzaos.zza(var0, "com.google.android.gms.analytics.CampaignTrackingReceiver", true);
         return var1;
      }
   }

   @RequiresPermission(
      allOf = {"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE"}
   )
   public void onReceive(Context var1, Intent var2) {
      zzaoc var3 = zzamj.zzaf(var1).zzkr();
      if (var2 == null) {
         var3.zzbr("CampaignTrackingReceiver received null intent");
      } else {
         String var4 = var2.getStringExtra("referrer");
         String var5 = var2.getAction();
         var3.zza("CampaignTrackingReceiver received", var5);
         if ("com.android.vending.INSTALL_REFERRER".equals(var5) && !TextUtils.isEmpty(var4)) {
            boolean var6;
            if (!(var6 = CampaignTrackingService.zzad(var1))) {
               var3.zzbr("CampaignTrackingService not registered or disabled. Installation tracking not possible. See http://goo.gl/8Rd3yj for instructions.");
            }

            this.zzu(var1, var4);
            Class var7;
            zzbo.zzu(var7 = this.zzjm());
            Intent var8;
            (var8 = new Intent(var1, var7)).putExtra("referrer", var4);
            Object var9 = zzuF;
            synchronized(zzuF) {
               var1.startService(var8);
               if (var6) {
                  try {
                     if (zzads == null) {
                        (zzads = new zzctz(var1, 1, "Analytics campaign WakeLock")).setReferenceCounted(false);
                     }

                     zzads.acquire(1000L);
                  } catch (SecurityException var11) {
                     var3.zzbr("CampaignTrackingService service at risk of not starting. For more reliable installation campaign reports, add the WAKE_LOCK permission to your manifest. See http://goo.gl/8Rd3yj for instructions.");
                  }

               }
            }
         } else {
            var3.zzbr("CampaignTrackingReceiver received unexpected intent without referrer extra");
         }
      }
   }

   protected Class zzjm() {
      return CampaignTrackingService.class;
   }

   protected void zzu(Context var1, String var2) {
   }
}
