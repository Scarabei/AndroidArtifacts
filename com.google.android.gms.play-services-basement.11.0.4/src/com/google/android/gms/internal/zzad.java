package com.google.android.gms.internal;

import android.os.SystemClock;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.cookie.DateUtils;

public final class zzad implements zzk {
   private static boolean DEBUG;
   private static int zzam;
   private static int zzan;
   private zzan zzao;
   private zzae zzap;

   public zzad(zzan var1) {
      this(var1, new zzae(zzan));
   }

   private zzad(zzan var1, zzae var2) {
      this.zzao = var1;
      this.zzap = var2;
   }

   public final zzn zza(zzp var1) throws zzaa {
      long var2 = SystemClock.elapsedRealtime();

      while(true) {
         HttpResponse var4 = null;
         Object var5 = null;
         Map var6 = Collections.emptyMap();

         try {
            HashMap var7;
            HashMap var24 = var7 = new HashMap();
            zzc var12 = var1.zze();
            HashMap var11 = var24;
            if (var12 != null) {
               if (var12.zza != null) {
                  var11.put("If-None-Match", var12.zza);
               }

               if (var12.zzc > 0L) {
                  Date var14 = new Date(var12.zzc);
                  var11.put("If-Modified-Since", DateUtils.formatDate(var14));
               }
            }

            StatusLine var21;
            int var22 = (var21 = (var4 = this.zzao.zza(var1, var7)).getStatusLine()).getStatusCode();
            var6 = zza(var4.getAllHeaders());
            if (var22 == 304) {
               zzc var10;
               if ((var10 = var1.zze()) == null) {
                  return new zzn(304, (byte[])null, var6, true, SystemClock.elapsedRealtime() - var2);
               }

               var10.zzf.putAll(var6);
               return new zzn(304, var10.data, var10.zzf, true, SystemClock.elapsedRealtime() - var2);
            }

            byte[] var20;
            if (var4.getEntity() != null) {
               var20 = this.zza(var4.getEntity());
            } else {
               var20 = new byte[0];
            }

            long var23 = SystemClock.elapsedRealtime() - var2;
            if (DEBUG || var23 > (long)zzam) {
               zzab.zzb("HTTP response for request=<%s> [lifetime=%d], [size=%s], [rc=%d], [retryCount=%s]", var1, var23, var20 != null ? var20.length : "null", var21.getStatusCode(), var1.zzj().zzb());
            }

            if (var22 >= 200 && var22 <= 299) {
               return new zzn(var22, var20, var6, false, SystemClock.elapsedRealtime() - var2);
            }

            throw new IOException();
         } catch (SocketTimeoutException var16) {
            zza("socket", var1, new zzz());
         } catch (ConnectTimeoutException var17) {
            zza("connection", var1, new zzz());
         } catch (MalformedURLException var18) {
            RuntimeException var10000 = new RuntimeException;
            String var10003 = String.valueOf(var1.getUrl());
            String var10002;
            if (var10003.length() != 0) {
               var10002 = "Bad URL ".concat(var10003);
            } else {
               String var10004 = new String;
               var10002 = var10004;
               var10004.<init>("Bad URL ");
            }

            var10000.<init>(var10002, var18);
            throw var10000;
         } catch (IOException var19) {
            if (var4 == null) {
               throw new zzo(var19);
            }

            int var8 = var4.getStatusLine().getStatusCode();
            zzab.zzc("Unexpected response code %d for %s", var8, var1.getUrl());
            if (var5 != null) {
               zzn var9 = new zzn(var8, (byte[])var5, var6, false, SystemClock.elapsedRealtime() - var2);
               if (var8 != 401 && var8 != 403) {
                  if (var8 >= 400 && var8 <= 499) {
                     throw new zzf(var9);
                  }

                  if (var8 >= 500 && var8 <= 599) {
                     throw new zzy(var9);
                  }

                  throw new zzy(var9);
               }

               zza("auth", var1, new zza(var9));
            } else {
               zza("network", var1, new zzm());
            }
         }
      }
   }

   private static void zza(String var0, zzp var1, zzaa var2) throws zzaa {
      zzx var3 = var1.zzj();
      int var4 = var1.zzi();

      try {
         var3.zza(var2);
      } catch (zzaa var6) {
         var1.zzb(String.format("%s-timeout-giveup [timeout=%s]", var0, var4));
         throw var6;
      }

      var1.zzb(String.format("%s-retry [timeout=%s]", var0, var4));
   }

   private final byte[] zza(HttpEntity var1) throws IOException, zzy {
      zzaq var2 = new zzaq(this.zzap, (int)var1.getContentLength());
      byte[] var3 = null;

      try {
         InputStream var4;
         if ((var4 = var1.getContent()) == null) {
            throw new zzy();
         } else {
            var3 = this.zzap.zzb(1024);

            int var5;
            while((var5 = var4.read(var3)) != -1) {
               var2.write(var3, 0, var5);
            }

            byte[] var6 = var2.toByteArray();
            return var6;
         }
      } finally {
         try {
            var1.consumeContent();
         } catch (IOException var11) {
            zzab.zza("Error occured when calling consumingContent");
         }

         this.zzap.zza(var3);
         var2.close();
      }
   }

   private static Map zza(Header[] var0) {
      TreeMap var1 = new TreeMap(String.CASE_INSENSITIVE_ORDER);

      for(int var2 = 0; var2 < var0.length; ++var2) {
         var1.put(var0[var2].getName(), var0[var2].getValue());
      }

      return var1;
   }

   static {
      DEBUG = zzab.DEBUG;
      zzam = 3000;
      zzan = 4096;
   }
}
