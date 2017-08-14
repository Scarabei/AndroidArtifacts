package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.common.util.zzc;
import com.google.android.gms.common.util.zzo;
import com.google.android.gms.common.util.zzp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public abstract class zzbgi {
   public abstract Map zzrL();

   protected abstract Object zzcH(String var1);

   protected boolean zza(zzbgj var1) {
      if (var1.zzaIJ == 11) {
         String var10000;
         if (var1.zzaIK) {
            var10000 = var1.zzaIL;
            throw new UnsupportedOperationException("Concrete type arrays not supported");
         } else {
            var10000 = var1.zzaIL;
            throw new UnsupportedOperationException("Concrete types not supported");
         }
      } else {
         return this.zzcI(var1.zzaIL);
      }
   }

   protected abstract boolean zzcI(String var1);

   protected static Object zza(zzbgj var0, Object var1) {
      return zzbgj.zzc(var0) != null ? var0.convertBack(var1) : var1;
   }

   public String toString() {
      Map var1 = this.zzrL();
      StringBuilder var2 = new StringBuilder(100);
      Iterator var3 = var1.keySet().iterator();

      while(var3.hasNext()) {
         String var4 = (String)var3.next();
         zzbgj var5 = (zzbgj)var1.get(var4);
         if (this.zza(var5)) {
            Object var6 = this.zzb(var5);
            Object var7 = zza(var5, var6);
            if (var2.length() == 0) {
               var2.append("{");
            } else {
               var2.append(",");
            }

            var2.append("\"").append(var4).append("\":");
            if (var7 == null) {
               var2.append("null");
            } else {
               switch(var5.zzaIJ) {
               case 8:
                  var2.append("\"").append(zzc.encode((byte[])var7)).append("\"");
                  break;
               case 9:
                  var2.append("\"").append(zzc.zzg((byte[])var7)).append("\"");
                  break;
               case 10:
                  HashMap var8 = (HashMap)var7;
                  zzp.zza(var2, var8);
                  break;
               default:
                  if (var5.zzaII) {
                     zza(var2, var5, (ArrayList)var7);
                  } else {
                     zza(var2, var5, var7);
                  }
               }
            }
         }
      }

      if (var2.length() > 0) {
         var2.append("}");
      } else {
         var2.append("{}");
      }

      return var2.toString();
   }

   protected Object zzb(zzbgj var1) {
      String var2 = var1.zzaIL;
      if (var1.zzaIN != null) {
         this.zzcH(var1.zzaIL);
         zzbo.zza(true, "Concrete field shouldn't be value object: %s", new Object[]{var1.zzaIL});
         boolean var10000 = var1.zzaIK;

         try {
            char var4 = Character.toUpperCase(var2.charAt(0));
            String var5 = String.valueOf(var2.substring(1));
            String var3 = (new StringBuilder(4 + String.valueOf(var5).length())).append("get").append(var4).append(var5).toString();
            return this.getClass().getMethod(var3).invoke(this);
         } catch (Exception var6) {
            throw new RuntimeException(var6);
         }
      } else {
         return this.zzcH(var1.zzaIL);
      }
   }

   private static void zza(StringBuilder var0, zzbgj var1, Object var2) {
      if (var1.zzaIH == 11) {
         Class var3 = var1.zzaIN;
         var0.append(((zzbgi)var3.cast(var2)).toString());
      } else if (var1.zzaIH == 7) {
         var0.append("\"");
         var0.append(zzo.zzcK((String)var2));
         var0.append("\"");
      } else {
         var0.append(var2);
      }
   }

   private static void zza(StringBuilder var0, zzbgj var1, ArrayList var2) {
      var0.append("[");
      int var3 = 0;

      for(int var4 = var2.size(); var3 < var4; ++var3) {
         if (var3 > 0) {
            var0.append(",");
         }

         Object var5;
         if ((var5 = var2.get(var3)) != null) {
            zza(var0, var1, var5);
         }
      }

      var0.append("]");
   }
}
