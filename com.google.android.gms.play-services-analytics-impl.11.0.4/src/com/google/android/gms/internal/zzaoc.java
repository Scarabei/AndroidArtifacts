package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbo;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class zzaoc extends zzamh {
   private static String zzain = "3";
   private static String zzaio = "01VDIWEA?";
   private static zzaoc zzaip;

   public zzaoc(zzamj var1) {
      super(var1);
   }

   protected final void zzjD() {
      Class var1 = zzaoc.class;
      synchronized(zzaoc.class) {
         zzaip = this;
      }
   }

   public static zzaoc zzlM() {
      return zzaip;
   }

   public final void zza(zzanx var1, String var2) {
      String var3 = var1 != null ? var1.toString() : "no hit data";
      String var10002 = String.valueOf(var2);
      String var10001;
      if (var10002.length() != 0) {
         var10001 = "Discarding hit. ".concat(var10002);
      } else {
         String var10003 = new String;
         var10001 = var10003;
         var10003.<init>("Discarding hit. ");
      }

      this.zzd(var10001, var3);
   }

   public final void zze(Map var1, String var2) {
      String var3;
      if (var1 != null) {
         StringBuilder var4 = new StringBuilder();
         Iterator var5 = var1.entrySet().iterator();

         while(var5.hasNext()) {
            Entry var6 = (Entry)var5.next();
            if (var4.length() > 0) {
               var4.append(',');
            }

            var4.append((String)var6.getKey());
            var4.append('=');
            var4.append((String)var6.getValue());
         }

         var3 = var4.toString();
      } else {
         var3 = "no hit data";
      }

      String var10002 = String.valueOf(var2);
      String var10001;
      if (var10002.length() != 0) {
         var10001 = "Discarding hit. ".concat(var10002);
      } else {
         String var10003 = new String;
         var10001 = var10003;
         var10003.<init>("Discarding hit. ");
      }

      this.zzd(var10001, var3);
   }

   public final synchronized void zzb(int var1, String var2, Object var3, Object var4, Object var5) {
      zzbo.zzu(var2);
      if (var1 < 0) {
         var1 = 0;
      }

      if (var1 >= zzaio.length()) {
         var1 = zzaio.length() - 1;
      }

      char var6;
      if (this.zzks().zzln()) {
         var6 = 'C';
      } else {
         var6 = 'c';
      }

      String var7 = zzain;
      char var8 = zzaio.charAt(var1);
      String var9 = zzami.VERSION;
      String var10 = String.valueOf(zzc(var2, zzk(var3), zzk(var4), zzk(var5)));
      if ((var2 = (new StringBuilder(3 + String.valueOf(var7).length() + String.valueOf(var9).length() + String.valueOf(var10).length())).append(var7).append(var8).append(var6).append(var9).append(":").append(var10).toString()).length() > 1024) {
         var2 = var2.substring(0, 1024);
      }

      zzaog var11;
      if ((var11 = this.zzkp().zzkH()) != null) {
         var11.zzlZ().zzbA(var2);
      }

   }

   private static String zzk(Object var0) {
      if (var0 == null) {
         return null;
      } else {
         if (var0 instanceof Integer) {
            var0 = new Long((long)((Integer)var0).intValue());
         }

         if (var0 instanceof Long) {
            if (Math.abs(((Long)var0).longValue()) < 100L) {
               return String.valueOf(var0);
            } else {
               String var1 = String.valueOf(var0).charAt(0) == '-' ? "-" : "";
               String var2 = String.valueOf(Math.abs(((Long)var0).longValue()));
               StringBuilder var3;
               (var3 = new StringBuilder()).append(var1);
               var3.append(Math.round(Math.pow(10.0D, (double)(var2.length() - 1))));
               var3.append("...");
               var3.append(var1);
               var3.append(Math.round(Math.pow(10.0D, (double)var2.length()) - 1.0D));
               return var3.toString();
            }
         } else if (var0 instanceof Boolean) {
            return String.valueOf(var0);
         } else {
            return var0 instanceof Throwable ? var0.getClass().getCanonicalName() : "-";
         }
      }
   }
}
