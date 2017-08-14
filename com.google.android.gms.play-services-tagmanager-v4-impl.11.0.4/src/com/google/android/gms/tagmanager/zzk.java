package com.google.android.gms.tagmanager;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import com.google.android.gms.internal.zzbf;
import java.util.Map;

final class zzk extends zzbr {
   private static final String ID;
   private final Context mContext;

   public zzk(Context var1) {
      super(ID);
      this.mContext = var1;
   }

   public final boolean zzAE() {
      return true;
   }

   public final com.google.android.gms.internal.zzbr zzo(Map var1) {
      try {
         return zzgk.zzI(this.mContext.getPackageManager().getPackageInfo(this.mContext.getPackageName(), 0).versionCode);
      } catch (NameNotFoundException var5) {
         String var3 = String.valueOf(this.mContext.getPackageName());
         String var4 = String.valueOf(var5.getMessage());
         zzdj.e((new StringBuilder(25 + String.valueOf(var3).length() + String.valueOf(var4).length())).append("Package name ").append(var3).append(" not found. ").append(var4).toString());
         return zzgk.zzCh();
      }
   }

   static {
      ID = zzbf.zzdq.toString();
   }
}
