package com.google.android.gms.internal;

import android.content.pm.ApplicationInfo;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.common.util.zzr;
import java.util.HashSet;
import java.util.Set;

public final class zzank {
   private final zzamj zzadj;
   private volatile Boolean zzagU;
   private String zzagV;
   private Set zzagW;

   protected zzank(zzamj var1) {
      zzbo.zzu(var1);
      this.zzadj = var1;
   }

   public final boolean zzln() {
      if (this.zzagU == null) {
         synchronized(this) {
            if (this.zzagU == null) {
               ApplicationInfo var2 = this.zzadj.getContext().getApplicationInfo();
               String var3 = zzr.zzsf();
               if (var2 != null) {
                  String var4 = var2.processName;
                  this.zzagU = var4 != null && var4.equals(var3);
               }

               if ((this.zzagU == null || !this.zzagU.booleanValue()) && "com.google.android.gms.analytics".equals(var3)) {
                  this.zzagU = Boolean.TRUE;
               }

               if (this.zzagU == null) {
                  this.zzagU = Boolean.TRUE;
                  this.zzadj.zzkr().zzbs("My process not in the list of running processes");
               }
            }
         }
      }

      return this.zzagU.booleanValue();
   }

   public static boolean zzlo() {
      return ((Boolean)zzans.zzahf.get()).booleanValue();
   }

   public static int zzlp() {
      return ((Integer)zzans.zzahC.get()).intValue();
   }

   public static long zzlq() {
      return ((Long)zzans.zzahn.get()).longValue();
   }

   public static long zzlr() {
      return ((Long)zzans.zzahq.get()).longValue();
   }

   public static int zzls() {
      return ((Integer)zzans.zzahs.get()).intValue();
   }

   public static int zzlt() {
      return ((Integer)zzans.zzaht.get()).intValue();
   }

   public static String zzlu() {
      return (String)zzans.zzahv.get();
   }

   public static String zzlv() {
      return (String)zzans.zzahu.get();
   }

   public static String zzlw() {
      return (String)zzans.zzahw.get();
   }

   public final Set zzlx() {
      String var1 = (String)zzans.zzahF.get();
      if (this.zzagW == null || this.zzagV == null || !this.zzagV.equals(var1)) {
         String[] var2 = TextUtils.split(var1, ",");
         HashSet var3 = new HashSet();
         String[] var4 = var2;
         int var5 = var2.length;

         for(int var6 = 0; var6 < var5; ++var6) {
            String var7 = var4[var6];

            try {
               var3.add(Integer.parseInt(var7));
            } catch (NumberFormatException var8) {
               ;
            }
         }

         this.zzagV = var1;
         this.zzagW = var3;
      }

      return this.zzagW;
   }

   public static long zzly() {
      return ((Long)zzans.zzahK.get()).longValue();
   }
}
