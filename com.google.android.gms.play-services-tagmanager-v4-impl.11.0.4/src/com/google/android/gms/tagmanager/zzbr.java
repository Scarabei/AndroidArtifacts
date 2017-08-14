package com.google.android.gms.tagmanager;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

abstract class zzbr {
   private final Set zzbEL;
   private final String zzbEM;

   public String zzBk() {
      return this.zzbEM;
   }

   public zzbr(String var1, String... var2) {
      this.zzbEM = var1;
      this.zzbEL = new HashSet(var2.length);
      String[] var3 = var2;
      int var4 = var2.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         String var6 = var3[var5];
         this.zzbEL.add(var6);
      }

   }

   public abstract boolean zzAE();

   public abstract com.google.android.gms.internal.zzbr zzo(Map var1);

   public Set zzBl() {
      return this.zzbEL;
   }

   final boolean zzd(Set var1) {
      return var1.containsAll(this.zzbEL);
   }
}
