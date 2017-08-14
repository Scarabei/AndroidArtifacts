package com.google.android.gms.internal;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

final class zzai {
   public long size;
   public String key;
   public String zza;
   public long zzb;
   public long zzc;
   public long zzd;
   public long zze;
   public Map zzf;

   private zzai() {
   }

   public zzai(String var1, zzc var2) {
      this.key = var1;
      this.size = (long)var2.data.length;
      this.zza = var2.zza;
      this.zzb = var2.zzb;
      this.zzc = var2.zzc;
      this.zzd = var2.zzd;
      this.zze = var2.zze;
      this.zzf = var2.zzf;
   }

   public static zzai zzf(InputStream var0) throws IOException {
      zzai var1 = new zzai();
      if (zzag.zzb(var0) != 538247942) {
         throw new IOException();
      } else {
         var1.key = zzag.zzd(var0);
         var1.zza = zzag.zzd(var0);
         if (var1.zza.equals("")) {
            var1.zza = null;
         }

         var1.zzb = zzag.zzc(var0);
         var1.zzc = zzag.zzc(var0);
         var1.zzd = zzag.zzc(var0);
         var1.zze = zzag.zzc(var0);
         var1.zzf = zzag.zze(var0);
         return var1;
      }
   }

   public final boolean zza(OutputStream var1) {
      try {
         zzag.zza(var1, 538247942);
         zzag.zza(var1, this.key);
         zzag.zza(var1, this.zza == null ? "" : this.zza);
         zzag.zza(var1, this.zzb);
         zzag.zza(var1, this.zzc);
         zzag.zza(var1, this.zzd);
         zzag.zza(var1, this.zze);
         OutputStream var4 = var1;
         Map var3 = this.zzf;
         if (this.zzf != null) {
            zzag.zza(var1, var3.size());
            Iterator var5 = var3.entrySet().iterator();

            while(var5.hasNext()) {
               Entry var6 = (Entry)var5.next();
               zzag.zza(var4, (String)var6.getKey());
               zzag.zza(var4, (String)var6.getValue());
            }
         } else {
            zzag.zza((OutputStream)var1, 0);
         }

         var1.flush();
         return true;
      } catch (IOException var7) {
         zzab.zzb("%s", var7.toString());
         return false;
      }
   }
}
