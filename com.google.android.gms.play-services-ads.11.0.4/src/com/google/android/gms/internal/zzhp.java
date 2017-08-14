package com.google.android.gms.internal;

import java.io.IOException;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;

@zzzn
public final class zzhp {
   private final zzhf zzza;
   private final int zzyY;
   private String zzzi;
   private String zzzj;
   private final boolean zzzk;
   private final int zzzl;
   private final int zzzm;

   public zzhp(int var1, int var2, int var3) {
      this.zzyY = var1;
      this.zzzk = false;
      if (var2 <= 64 && var2 >= 0) {
         this.zzzl = var2;
      } else {
         this.zzzl = 64;
      }

      if (var3 <= 0) {
         this.zzzm = 1;
      } else {
         this.zzzm = var3;
      }

      this.zzza = new zzho(this.zzzl);
   }

   private final boolean zza(String var1, HashSet var2) {
      String[] var3;
      if ((var3 = var1.split("\n")).length == 0) {
         return true;
      } else {
         for(int var4 = 0; var4 < var3.length; ++var4) {
            String var5;
            String var10000;
            if ((var5 = var3[var4]).indexOf("'") != -1) {
               boolean var12 = false;
               StringBuilder var13 = new StringBuilder(var5);
               int var14 = 1;

               while(true) {
                  if (var14 + 2 > var13.length()) {
                     var10000 = var12 ? var13.toString() : null;
                     String var6 = var10000;
                     if (var10000 != null) {
                        var5 = var6;
                        this.zzzj = var6;
                     }
                     break;
                  }

                  if (var13.charAt(var14) == '\'') {
                     if (var13.charAt(var14 - 1) == ' ' || var13.charAt(var14 + 1) != 's' && var13.charAt(var14 + 1) != 'S' || var14 + 2 != var13.length() && var13.charAt(var14 + 2) != ' ') {
                        var13.setCharAt(var14, ' ');
                     } else {
                        var13.insert(var14, ' ');
                        var14 += 2;
                     }

                     var12 = true;
                  }

                  ++var14;
               }
            }

            String[] var15;
            if ((var15 = zzhj.zzd(var5, true)).length >= this.zzzm) {
               for(int var7 = 0; var7 < var15.length; ++var7) {
                  String var8 = "";
                  boolean var9 = true;

                  for(int var10 = 0; var10 < this.zzzm; ++var10) {
                     if (var7 + var10 >= var15.length) {
                        var9 = false;
                        break;
                     }

                     if (var10 > 0) {
                        var8 = String.valueOf(var8).concat(" ");
                     }

                     var10000 = String.valueOf(var8);
                     String var10001 = String.valueOf(var15[var7 + var10]);
                     if (var10001.length() != 0) {
                        var10000 = var10000.concat(var10001);
                     } else {
                        String var10002 = new String;
                        var10001 = var10000;
                        var10000 = var10002;
                        var10002.<init>(var10001);
                     }

                     var8 = var10000;
                  }

                  if (!var9) {
                     break;
                  }

                  var2.add(var8);
                  if (var2.size() >= this.zzyY) {
                     return false;
                  }
               }

               if (var2.size() >= this.zzyY) {
                  return false;
               }
            }
         }

         return true;
      }
   }

   public final String zza(ArrayList var1, ArrayList var2) {
      Collections.sort(var2, new zzhq(this));
      HashSet var3 = new HashSet();

      String var6;
      for(int var4 = 0; var4 < var2.size(); ++var4) {
         int var5 = ((zzhe)var2.get(var4)).zzcV();
         var6 = Normalizer.normalize((CharSequence)var1.get(var5), Form.NFKC).toLowerCase(Locale.US);
         if (!this.zza(var6, var3)) {
            break;
         }
      }

      zzhi var9 = new zzhi();
      this.zzzi = "";
      Iterator var10 = var3.iterator();

      while(var10.hasNext()) {
         var6 = (String)var10.next();

         try {
            byte[] var7 = this.zzza.zzy(var6);
            var9.write(var7);
         } catch (IOException var8) {
            zzafr.zzb("Error while writing hash to byteStream", var8);
            break;
         }
      }

      return var9.toString();
   }
}
