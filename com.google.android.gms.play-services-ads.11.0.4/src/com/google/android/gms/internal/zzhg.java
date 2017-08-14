package com.google.android.gms.internal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.PriorityQueue;

@zzzn
public final class zzhg {
   private final int zzyX;
   private final int zzyY;
   private final int zzyZ;
   private final zzhf zzza = new zzhk();

   public zzhg(int var1) {
      this.zzyY = var1;
      this.zzyX = 6;
      this.zzyZ = 0;
   }

   public final String zza(ArrayList var1) {
      StringBuffer var2 = new StringBuffer();
      ArrayList var4;
      int var5 = (var4 = (ArrayList)var1).size();
      int var6 = 0;

      while(var6 < var5) {
         Object var10000 = var4.get(var6);
         ++var6;
         String var3 = ((String)var10000).toLowerCase(Locale.US);
         var2.append(var3);
         var2.append('\n');
      }

      return this.zzz(var2.toString());
   }

   private final String zzz(String var1) {
      String[] var2;
      if ((var2 = var1.split("\n")).length == 0) {
         return "";
      } else {
         zzhi var3 = new zzhi();
         PriorityQueue var4 = new PriorityQueue(this.zzyY, new zzhh(this));

         for(int var5 = 0; var5 < var2.length; ++var5) {
            String[] var7;
            if ((var7 = zzhj.zzd(var2[var5], false)).length != 0) {
               zzhm.zza(var7, this.zzyY, this.zzyX, var4);
            }
         }

         Iterator var9 = var4.iterator();

         while(var9.hasNext()) {
            zzhn var6 = (zzhn)var9.next();

            try {
               var3.write(this.zzza.zzy(var6.zzze));
            } catch (IOException var8) {
               zzafr.zzb("Error while writing hash to byteStream", var8);
               break;
            }
         }

         return var3.toString();
      }
   }
}
