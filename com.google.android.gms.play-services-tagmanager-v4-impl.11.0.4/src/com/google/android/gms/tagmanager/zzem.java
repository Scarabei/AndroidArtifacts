package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzbf;
import com.google.android.gms.internal.zzbg;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

final class zzem extends zzga {
   private static final String ID;
   private static final String zzbFR;

   public zzem() {
      super(ID);
   }

   protected final boolean zza(String var1, String var2, Map var3) {
      byte var4 = 64;
      if (zzgk.zzf((com.google.android.gms.internal.zzbr)var3.get(zzbFR)).booleanValue()) {
         var4 = 66;
      }

      try {
         return Pattern.compile(var2, var4).matcher(var1).find();
      } catch (PatternSyntaxException var5) {
         return false;
      }
   }

   static {
      ID = zzbf.zzev.toString();
      zzbFR = zzbg.zzhP.toString();
   }
}
