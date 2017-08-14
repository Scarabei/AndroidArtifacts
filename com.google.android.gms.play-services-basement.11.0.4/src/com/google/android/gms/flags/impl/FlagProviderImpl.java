package com.google.android.gms.flags.impl;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;
import com.google.android.gms.common.util.DynamiteApi;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.internal.zzcad;

@DynamiteApi
public class FlagProviderImpl extends zzcad {
   private boolean zzuH = false;
   private SharedPreferences zzBT;

   public void init(IObjectWrapper var1) {
      Context var2 = (Context)zzn.zzE(var1);
      if (!this.zzuH) {
         try {
            Context var3 = var2.createPackageContext("com.google.android.gms", 0);
            this.zzBT = zzj.zzaW(var3);
            this.zzuH = true;
         } catch (NameNotFoundException var4) {
            ;
         } catch (Exception var5) {
            String var10002 = String.valueOf(var5.getMessage());
            String var10001;
            if (var10002.length() != 0) {
               var10001 = "Could not retrieve sdk flags, continuing with defaults: ".concat(var10002);
            } else {
               String var10003 = new String;
               var10001 = var10003;
               var10003.<init>("Could not retrieve sdk flags, continuing with defaults: ");
            }

            Log.w("FlagProviderImpl", var10001);
         }
      }
   }

   public boolean getBooleanFlagValue(String var1, boolean var2, int var3) {
      return !this.zzuH ? var2 : zzb.zza(this.zzBT, var1, var2).booleanValue();
   }

   public int getIntFlagValue(String var1, int var2, int var3) {
      return !this.zzuH ? var2 : zzd.zza(this.zzBT, var1, var2).intValue();
   }

   public long getLongFlagValue(String var1, long var2, int var4) {
      return !this.zzuH ? var2 : zzf.zza(this.zzBT, var1, var2).longValue();
   }

   public String getStringFlagValue(String var1, String var2, int var3) {
      return !this.zzuH ? var2 : zzh.zza(this.zzBT, var1, var2);
   }
}
