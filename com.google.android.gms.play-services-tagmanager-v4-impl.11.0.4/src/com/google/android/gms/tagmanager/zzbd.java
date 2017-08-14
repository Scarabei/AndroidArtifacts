package com.google.android.gms.tagmanager;

import android.os.Build;
import com.google.android.gms.internal.zzbf;
import java.util.Map;

final class zzbd extends zzbr {
   private static final String ID;

   public zzbd() {
      super(ID);
   }

   public final boolean zzAE() {
      return true;
   }

   public final com.google.android.gms.internal.zzbr zzo(Map var1) {
      String var2 = Build.MANUFACTURER;
      String var3 = Build.MODEL;
      if (!Build.MODEL.startsWith(var2) && !var2.equals("unknown")) {
         var3 = (new StringBuilder(1 + String.valueOf(var2).length() + String.valueOf(var3).length())).append(var2).append(" ").append(var3).toString();
      }

      return zzgk.zzI(var3);
   }

   static {
      ID = zzbf.zzdx.toString();
   }
}
