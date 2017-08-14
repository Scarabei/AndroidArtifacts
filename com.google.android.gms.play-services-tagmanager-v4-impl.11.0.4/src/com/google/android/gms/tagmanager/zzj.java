package com.google.android.gms.tagmanager;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import com.google.android.gms.internal.zzbf;
import java.util.Map;

final class zzj extends zzbr {
   private static final String ID;
   private final Context mContext;

   public zzj(Context var1) {
      super(ID);
      this.mContext = var1;
   }

   public final boolean zzAE() {
      return true;
   }

   public final com.google.android.gms.internal.zzbr zzo(Map var1) {
      try {
         PackageManager var2;
         ApplicationInfo var3 = (var2 = this.mContext.getPackageManager()).getApplicationInfo(this.mContext.getPackageName(), 0);
         return zzgk.zzI(var2.getApplicationLabel(var3).toString());
      } catch (NameNotFoundException var4) {
         zzdj.zzb("App name is not found.", var4);
         return zzgk.zzCh();
      }
   }

   static {
      ID = zzbf.zzdp.toString();
   }
}
