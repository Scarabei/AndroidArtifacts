package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.customtabs.CustomTabsCallback;
import android.support.customtabs.CustomTabsClient;
import android.support.customtabs.CustomTabsServiceConnection;
import android.support.customtabs.CustomTabsSession;
import java.util.List;

@zzzn
public final class zznl implements aeq {
   @Nullable
   private CustomTabsSession zzHi;
   @Nullable
   private CustomTabsClient zzHj;
   @Nullable
   private CustomTabsServiceConnection zzHk;
   @Nullable
   private zznm zzHl;

   public static boolean zzi(Context var0) {
      PackageManager var1;
      if ((var1 = var0.getPackageManager()) == null) {
         return false;
      } else {
         Intent var2 = new Intent("android.intent.action.VIEW", Uri.parse("http://www.example.com"));
         ResolveInfo var3 = var1.resolveActivity(var2, 0);
         List var4;
         if ((var4 = var1.queryIntentActivities(var2, 65536)) != null && var3 != null) {
            for(int var5 = 0; var5 < var4.size(); ++var5) {
               ResolveInfo var6 = (ResolveInfo)var4.get(var5);
               if (var3.activityInfo.name.equals(var6.activityInfo.name)) {
                  return var3.activityInfo.packageName.equals(aeo.zzbU(var0));
               }
            }
         }

         return false;
      }
   }

   public final void zzc(Activity var1) {
      if (this.zzHk != null) {
         var1.unbindService(this.zzHk);
         this.zzHj = null;
         this.zzHi = null;
         this.zzHk = null;
      }
   }

   @Nullable
   public final CustomTabsSession zzdY() {
      if (this.zzHj == null) {
         this.zzHi = null;
      } else if (this.zzHi == null) {
         this.zzHi = this.zzHj.newSession((CustomTabsCallback)null);
      }

      return this.zzHi;
   }

   public final void zza(zznm var1) {
      this.zzHl = var1;
   }

   public final void zzd(Activity var1) {
      if (this.zzHj == null) {
         String var2;
         if ((var2 = aeo.zzbU(var1)) != null) {
            this.zzHk = new aep(this);
            CustomTabsClient.bindCustomTabsService(var1, var2, this.zzHk);
         }
      }
   }

   public final boolean mayLaunchUrl(Uri var1, Bundle var2, List var3) {
      if (this.zzHj == null) {
         return false;
      } else {
         CustomTabsSession var4;
         return (var4 = this.zzdY()) == null ? false : var4.mayLaunchUrl(var1, (Bundle)null, (List)null);
      }
   }

   public final void zza(CustomTabsClient var1) {
      this.zzHj = var1;
      this.zzHj.warmup(0L);
      if (this.zzHl != null) {
         this.zzHl.zzea();
      }

   }

   public final void zzdZ() {
      this.zzHj = null;
      this.zzHi = null;
      if (this.zzHl != null) {
         this.zzHl.zzeb();
      }

   }
}
