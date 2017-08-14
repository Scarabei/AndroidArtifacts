package com.google.ads.mediation;

import com.google.android.gms.internal.zzajc;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/** @deprecated */
@Deprecated
public class MediationServerParameters {
   public void load(Map var1) throws MediationServerParameters.MappingException {
      HashMap var2 = new HashMap();
      Field[] var3;
      int var4 = (var3 = this.getClass().getFields()).length;

      for(int var5 = 0; var5 < var4; ++var5) {
         Field var6;
         MediationServerParameters.Parameter var7;
         if ((var7 = (MediationServerParameters.Parameter)(var6 = var3[var5]).getAnnotation(MediationServerParameters.Parameter.class)) != null) {
            var2.put(var7.name(), var6);
         }
      }

      if (var2.isEmpty()) {
         zzajc.zzaT("No server options fields detected. To suppress this message either add a field with the @Parameter annotation, or override the load() method.");
      }

      Iterator var10 = var1.entrySet().iterator();

      Field var14;
      while(var10.hasNext()) {
         Entry var12 = (Entry)var10.next();
         String var16;
         if ((var14 = (Field)var2.remove(var12.getKey())) != null) {
            try {
               var14.set(this, var12.getValue());
            } catch (IllegalAccessException var8) {
               var16 = (String)var12.getKey();
               zzajc.zzaT((new StringBuilder(49 + String.valueOf(var16).length())).append("Server option \"").append(var16).append("\" could not be set: Illegal Access").toString());
            } catch (IllegalArgumentException var9) {
               var16 = (String)var12.getKey();
               zzajc.zzaT((new StringBuilder(43 + String.valueOf(var16).length())).append("Server option \"").append(var16).append("\" could not be set: Bad Type").toString());
            }
         } else {
            String var15 = (String)var12.getKey();
            var16 = (String)var12.getValue();
            zzajc.zzaC((new StringBuilder(31 + String.valueOf(var15).length() + String.valueOf(var16).length())).append("Unexpected server option: ").append(var15).append(" = \"").append(var16).append("\"").toString());
         }
      }

      StringBuilder var11 = new StringBuilder();
      Iterator var13 = var2.values().iterator();

      String var10002;
      while(var13.hasNext()) {
         if (((MediationServerParameters.Parameter)(var14 = (Field)var13.next()).getAnnotation(MediationServerParameters.Parameter.class)).required()) {
            String var10001 = String.valueOf(((MediationServerParameters.Parameter)var14.getAnnotation(MediationServerParameters.Parameter.class)).name());
            String var10000;
            if (var10001.length() != 0) {
               var10000 = "Required server option missing: ".concat(var10001);
            } else {
               var10002 = new String;
               var10000 = var10002;
               var10002.<init>("Required server option missing: ");
            }

            zzajc.zzaT(var10000);
            if (var11.length() > 0) {
               var11.append(", ");
            }

            var11.append(((MediationServerParameters.Parameter)var14.getAnnotation(MediationServerParameters.Parameter.class)).name());
         }
      }

      if (var11.length() > 0) {
         MediationServerParameters.MappingException var17 = new MediationServerParameters.MappingException;
         String var10003 = String.valueOf(var11.toString());
         if (var10003.length() != 0) {
            var10002 = "Required server option(s) missing: ".concat(var10003);
         } else {
            String var10004 = new String;
            var10002 = var10004;
            var10004.<init>("Required server option(s) missing: ");
         }

         var17.<init>(var10002);
         throw var17;
      }
   }

   public static final class MappingException extends Exception {
      public MappingException(String var1) {
         super(var1);
      }
   }

   @Retention(RetentionPolicy.RUNTIME)
   @Target({ElementType.FIELD})
   public @interface Parameter {
      String name();

      boolean required() default true;
   }
}
