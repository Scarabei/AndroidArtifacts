package com.google.android.gms.analytics;

import android.content.Context;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeSet;

public class StandardExceptionParser implements ExceptionParser {
   private final TreeSet zzaen = new TreeSet();

   public StandardExceptionParser(Context var1, Collection var2) {
      this.setIncludedPackages(var1, var2);
   }

   public void setIncludedPackages(Context var1, Collection var2) {
      this.zzaen.clear();
      HashSet var3 = new HashSet();
      if (var2 != null) {
         var3.addAll(var2);
      }

      if (var1 != null) {
         var3.add(var1.getApplicationContext().getPackageName());
      }

      Iterator var4 = var3.iterator();

      while(var4.hasNext()) {
         String var5 = (String)var4.next();
         boolean var6 = true;

         for(Iterator var7 = this.zzaen.iterator(); var7.hasNext(); var6 = false) {
            String var8 = (String)var7.next();
            if (!var5.startsWith(var8)) {
               if (var8.startsWith(var5)) {
                  this.zzaen.remove(var8);
               }
               break;
            }
         }

         if (var6) {
            this.zzaen.add(var5);
         }
      }

   }

   protected Throwable getCause(Throwable var1) {
      Throwable var2;
      for(var2 = var1; var2.getCause() != null; var2 = var2.getCause()) {
         ;
      }

      return var2;
   }

   protected StackTraceElement getBestStackTraceElement(Throwable var1) {
      StackTraceElement[] var2;
      if ((var2 = var1.getStackTrace()) != null && var2.length != 0) {
         StackTraceElement[] var3 = var2;
         int var4 = var2.length;

         for(int var5 = 0; var5 < var4; ++var5) {
            StackTraceElement var6;
            String var7 = (var6 = var3[var5]).getClassName();
            Iterator var8 = this.zzaen.iterator();

            while(var8.hasNext()) {
               String var9 = (String)var8.next();
               if (var7.startsWith(var9)) {
                  return var6;
               }
            }
         }

         return var2[0];
      } else {
         return null;
      }
   }

   protected String getDescription(Throwable var1, StackTraceElement var2, String var3) {
      StringBuilder var4;
      (var4 = new StringBuilder()).append(var1.getClass().getSimpleName());
      if (var2 != null) {
         String[] var5 = var2.getClassName().split("\\.");
         String var6 = "unknown";
         if (var5 != null && var5.length > 0) {
            var6 = var5[var5.length - 1];
         }

         var4.append(String.format(" (@%s:%s:%s)", var6, var2.getMethodName(), var2.getLineNumber()));
      }

      if (var3 != null) {
         var4.append(String.format(" {%s}", var3));
      }

      return var4.toString();
   }

   public String getDescription(String var1, Throwable var2) {
      return this.getDescription(this.getCause(var2), this.getBestStackTraceElement(this.getCause(var2)), var1);
   }
}
