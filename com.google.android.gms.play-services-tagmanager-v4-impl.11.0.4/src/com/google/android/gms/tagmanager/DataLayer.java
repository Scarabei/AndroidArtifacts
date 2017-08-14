package com.google.android.gms.tagmanager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataLayer {
   public static final Object OBJECT_NOT_PRESENT = new Object();
   public static final String EVENT_KEY = "event";
   private static String[] zzbEd = "gtm.lifetime".toString().split("\\.");
   private static final Pattern zzbEe = Pattern.compile("(\\d+)\\s*([smhd]?)");
   private final ConcurrentHashMap zzbEf;
   private final Map zzbEg;
   private final ReentrantLock zzbEh;
   private final LinkedList zzbEi;
   private final DataLayer.zzc zzbEj;
   private final CountDownLatch zzbEk;

   DataLayer() {
      this(new zzao());
   }

   DataLayer(DataLayer.zzc var1) {
      this.zzbEj = var1;
      this.zzbEf = new ConcurrentHashMap();
      this.zzbEg = new HashMap();
      this.zzbEh = new ReentrantLock();
      this.zzbEi = new LinkedList();
      this.zzbEk = new CountDownLatch(1);
      this.zzbEj.zza(new zzap(this));
   }

   public String toString() {
      Map var1 = this.zzbEg;
      synchronized(this.zzbEg) {
         StringBuilder var2 = new StringBuilder();
         Iterator var3 = this.zzbEg.entrySet().iterator();

         while(var3.hasNext()) {
            Entry var4 = (Entry)var3.next();
            var2.append(String.format("{\n\tKey: %s\n\tValue: %s\n}\n", var4.getKey(), var4.getValue()));
         }

         return var2.toString();
      }
   }

   public void pushEvent(String var1, Map var2) {
      HashMap var3;
      (var3 = new HashMap(var2)).put("event", var1);
      this.push(var3);
   }

   public void push(String var1, Object var2) {
      Map var3 = zzn(var1, var2);
      this.push(var3);
   }

   public void push(Map var1) {
      try {
         this.zzbEk.await();
      } catch (InterruptedException var2) {
         zzdj.zzaT("DataLayer.push: unexpected InterruptedException");
      }

      this.zzr(var1);
   }

   private final void zzr(Map var1) {
      this.zzbEh.lock();

      try {
         this.zzbEi.offer(var1);
         if (this.zzbEh.getHoldCount() == 1) {
            DataLayer var3 = this;
            int var5 = 0;

            Map var4;
            while((var4 = (Map)var3.zzbEi.poll()) != null) {
               Map var11 = var4;
               DataLayer var10 = var3;
               synchronized(var3.zzbEg) {
                  Iterator var13 = var11.keySet().iterator();

                  while(true) {
                     if (!var13.hasNext()) {
                        break;
                     }

                     String var14 = (String)var13.next();
                     var10.zzd(zzn(var14, var11.get(var14)), var10.zzbEg);
                  }
               }

               Map var16 = var11;
               Iterator var17 = var10.zzbEf.keySet().iterator();

               while(var17.hasNext()) {
                  ((DataLayer.zzb)var17.next()).zzp(var16);
               }

               ++var5;
               if (var5 > 500) {
                  var3.zzbEi.clear();
                  throw new RuntimeException("Seems like an infinite loop of pushing to the data layer");
               }
            }
         }

         Object var9;
         Long var10000 = (var9 = zzs(var1)) == null ? null : zzfd(var9.toString());
         Long var22 = var10000;
         if (var10000 != null) {
            ArrayList var23 = new ArrayList();
            this.zza(var1, "", var23);
            this.zzbEj.zza(var23, var22.longValue());
         }
      } finally {
         this.zzbEh.unlock();
      }

   }

   private static Object zzs(Map var0) {
      Object var1 = var0;
      String[] var2 = zzbEd;
      int var3 = zzbEd.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         String var5 = var2[var4];
         if (!(var1 instanceof Map)) {
            return null;
         }

         var1 = ((Map)var1).get(var5);
      }

      return var1;
   }

   final void zzfc(String var1) {
      this.push(var1, (Object)null);
      this.zzbEj.zzfe(var1);
   }

   private final void zza(Map var1, String var2, Collection var3) {
      Iterator var4 = var1.entrySet().iterator();

      while(var4.hasNext()) {
         Entry var5 = (Entry)var4.next();
         String var7 = var2.length() == 0 ? "" : ".";
         String var8 = (String)var5.getKey();
         String var6 = (new StringBuilder(String.valueOf(var2).length() + String.valueOf(var7).length() + String.valueOf(var8).length())).append(var2).append(var7).append(var8).toString();
         if (var5.getValue() instanceof Map) {
            Map var9 = (Map)var5.getValue();
            this.zza(var9, var6, var3);
         } else if (!var6.equals("gtm.lifetime")) {
            var3.add(new DataLayer.zza(var6, var5.getValue()));
         }
      }

   }

   private static Long zzfd(String var0) {
      String var10000;
      String var10001;
      Matcher var1;
      String var10002;
      if (!(var1 = zzbEe.matcher(var0)).matches()) {
         var10001 = String.valueOf(var0);
         if (var10001.length() != 0) {
            var10000 = "unknown _lifetime: ".concat(var10001);
         } else {
            var10002 = new String;
            var10000 = var10002;
            var10002.<init>("unknown _lifetime: ");
         }

         zzdj.zzaS(var10000);
         return null;
      } else {
         long var2 = 0L;

         try {
            var2 = Long.parseLong(var1.group(1));
         } catch (NumberFormatException var5) {
            var10001 = String.valueOf(var0);
            if (var10001.length() != 0) {
               var10000 = "illegal number in _lifetime value: ".concat(var10001);
            } else {
               var10002 = new String;
               var10000 = var10002;
               var10002.<init>("illegal number in _lifetime value: ");
            }

            zzdj.zzaT(var10000);
         }

         if (var2 <= 0L) {
            var10001 = String.valueOf(var0);
            if (var10001.length() != 0) {
               var10000 = "non-positive _lifetime: ".concat(var10001);
            } else {
               var10002 = new String;
               var10000 = var10002;
               var10002.<init>("non-positive _lifetime: ");
            }

            zzdj.zzaS(var10000);
            return null;
         } else {
            String var4;
            if ((var4 = var1.group(2)).length() == 0) {
               return var2;
            } else {
               switch(var4.charAt(0)) {
               case 'd':
                  return var2 * 1000L * 60L * 60L * 24L;
               case 'h':
                  return var2 * 1000L * 60L * 60L;
               case 'm':
                  return var2 * 1000L * 60L;
               case 's':
                  return var2 * 1000L;
               default:
                  var10001 = String.valueOf(var0);
                  if (var10001.length() != 0) {
                     var10000 = "unknown units in _lifetime: ".concat(var10001);
                  } else {
                     var10002 = new String;
                     var10000 = var10002;
                     var10002.<init>("unknown units in _lifetime: ");
                  }

                  zzdj.zzaT(var10000);
                  return null;
               }
            }
         }
      }
   }

   public Object get(String var1) {
      Map var2 = this.zzbEg;
      synchronized(this.zzbEg) {
         Object var3 = this.zzbEg;
         String[] var4;
         int var5 = (var4 = var1.split("\\.")).length;

         for(int var6 = 0; var6 < var5; ++var6) {
            String var7 = var4[var6];
            if (!(var3 instanceof Map)) {
               return null;
            }

            Object var8;
            if ((var8 = ((Map)var3).get(var7)) == null) {
               return null;
            }

            var3 = var8;
         }

         return var3;
      }
   }

   public static Map mapOf(Object... var0) {
      if (var0.length % 2 != 0) {
         throw new IllegalArgumentException("expected even number of key-value pairs");
      } else {
         HashMap var1 = new HashMap();

         for(int var2 = 0; var2 < var0.length; var2 += 2) {
            if (!(var0[var2] instanceof String)) {
               String var3 = String.valueOf(var0[var2]);
               throw new IllegalArgumentException((new StringBuilder(21 + String.valueOf(var3).length())).append("key is not a string: ").append(var3).toString());
            }

            var1.put((String)var0[var2], var0[var2 + 1]);
         }

         return var1;
      }
   }

   public static List listOf(Object... var0) {
      ArrayList var1 = new ArrayList();

      for(int var2 = 0; var2 < var0.length; ++var2) {
         var1.add(var0[var2]);
      }

      return var1;
   }

   final void zza(DataLayer.zzb var1) {
      this.zzbEf.put(var1, Integer.valueOf(0));
   }

   static Map zzn(String var0, Object var1) {
      HashMap var2;
      HashMap var3 = var2 = new HashMap();
      String[] var4 = var0.toString().split("\\.");

      for(int var5 = 0; var5 < var4.length - 1; ++var5) {
         HashMap var6 = new HashMap();
         var3.put(var4[var5], var6);
         var3 = var6;
      }

      var3.put(var4[var4.length - 1], var1);
      return var2;
   }

   private final void zzd(Map var1, Map var2) {
      Iterator var3 = var1.keySet().iterator();

      while(var3.hasNext()) {
         String var4 = (String)var3.next();
         Object var5;
         if ((var5 = var1.get(var4)) instanceof List) {
            if (!(var2.get(var4) instanceof List)) {
               var2.put(var4, new ArrayList());
            }

            List var6 = (List)var5;
            List var7 = (List)var2.get(var4);
            this.zzb(var6, var7);
         } else if (var5 instanceof Map) {
            if (!(var2.get(var4) instanceof Map)) {
               var2.put(var4, new HashMap());
            }

            Map var8 = (Map)var5;
            Map var9 = (Map)var2.get(var4);
            this.zzd(var8, var9);
         } else {
            var2.put(var4, var5);
         }
      }

   }

   private final void zzb(List var1, List var2) {
      while(var2.size() < var1.size()) {
         var2.add((Object)null);
      }

      for(int var3 = 0; var3 < var1.size(); ++var3) {
         Object var4;
         if ((var4 = var1.get(var3)) instanceof List) {
            if (!(var2.get(var3) instanceof List)) {
               var2.set(var3, new ArrayList());
            }

            List var5 = (List)var4;
            List var6 = (List)var2.get(var3);
            this.zzb(var5, var6);
         } else if (var4 instanceof Map) {
            if (!(var2.get(var3) instanceof Map)) {
               var2.set(var3, new HashMap());
            }

            Map var7 = (Map)var4;
            Map var8 = (Map)var2.get(var3);
            this.zzd(var7, var8);
         } else if (var4 != OBJECT_NOT_PRESENT) {
            var2.set(var3, var4);
         }
      }

   }

   // $FF: synthetic method
   static void zza(DataLayer var0, Map var1) {
      var0.zzr(var1);
   }

   // $FF: synthetic method
   static CountDownLatch zza(DataLayer var0) {
      return var0.zzbEk;
   }

   interface zzc {
      void zza(List var1, long var2);

      void zza(zzaq var1);

      void zzfe(String var1);
   }

   static final class zza {
      public final String zzBN;
      public final Object mValue;

      zza(String var1, Object var2) {
         this.zzBN = var1;
         this.mValue = var2;
      }

      public final String toString() {
         String var1 = this.zzBN;
         String var2 = String.valueOf(this.mValue.toString());
         return (new StringBuilder(13 + String.valueOf(var1).length() + String.valueOf(var2).length())).append("Key: ").append(var1).append(" value: ").append(var2).toString();
      }

      public final int hashCode() {
         return Arrays.hashCode(new Integer[]{this.zzBN.hashCode(), this.mValue.hashCode()});
      }

      public final boolean equals(Object var1) {
         if (!(var1 instanceof DataLayer.zza)) {
            return false;
         } else {
            DataLayer.zza var2 = (DataLayer.zza)var1;
            return this.zzBN.equals(var2.zzBN) && this.mValue.equals(var2.mValue);
         }
      }
   }

   interface zzb {
      void zzp(Map var1);
   }
}
