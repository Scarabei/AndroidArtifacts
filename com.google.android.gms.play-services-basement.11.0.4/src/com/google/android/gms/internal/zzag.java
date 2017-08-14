package com.google.android.gms.internal;

import android.os.SystemClock;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public final class zzag implements zzb {
   private final Map zzav;
   private long zzaw;
   private final File zzax;
   private final int zzay;

   private zzag(File var1, int var2) {
      this.zzav = new LinkedHashMap(16, 0.75F, true);
      this.zzaw = 0L;
      this.zzax = var1;
      this.zzay = 5242880;
   }

   public zzag(File var1) {
      this(var1, 5242880);
   }

   public final synchronized zzc zza(String var1) {
      zzai var2;
      if ((var2 = (zzai)this.zzav.get(var1)) == null) {
         return null;
      } else {
         File var3 = this.zze(var1);
         zzaj var4 = null;
         boolean var18 = false;

         zzc var6;
         label112: {
            label113: {
               try {
                  var18 = true;
                  zzai.zzf(var4 = new zzaj(new BufferedInputStream(new FileInputStream(var3)), (zzah)null));
                  byte[] var5 = zza((InputStream)var4, (int)(var3.length() - (long)zzaj.zza(var4)));
                  zzc var10;
                  (var10 = new zzc()).data = var5;
                  var10.zza = var2.zza;
                  var10.zzb = var2.zzb;
                  var10.zzc = var2.zzc;
                  var10.zzd = var2.zzd;
                  var10.zze = var2.zze;
                  var10.zzf = var2.zzf;
                  var6 = var10;
                  var18 = false;
                  break label112;
               } catch (IOException var23) {
                  zzab.zzb("%s: %s", var3.getAbsolutePath(), var23.toString());
                  this.remove(var1);
                  var18 = false;
                  break label113;
               } catch (NegativeArraySizeException var24) {
                  zzab.zzb("%s: %s", var3.getAbsolutePath(), var24.toString());
                  this.remove(var1);
                  var18 = false;
               } finally {
                  if (var18) {
                     if (var4 != null) {
                        try {
                           var4.close();
                        } catch (IOException var21) {
                           return null;
                        }
                     }

                  }
               }

               if (var4 != null) {
                  try {
                     var4.close();
                  } catch (IOException var20) {
                     return null;
                  }
               }

               return null;
            }

            if (var4 != null) {
               try {
                  var4.close();
               } catch (IOException var19) {
                  return null;
               }
            }

            return null;
         }

         try {
            var4.close();
            return var6;
         } catch (IOException var22) {
            return null;
         }
      }
   }

   public final synchronized void initialize() {
      if (!this.zzax.exists()) {
         if (!this.zzax.mkdirs()) {
            zzab.zzc("Unable to create cache dir %s", this.zzax.getAbsolutePath());
         }

      } else {
         File[] var1;
         if ((var1 = this.zzax.listFiles()) != null) {
            File[] var2 = var1;
            int var3 = var1.length;

            for(int var4 = 0; var4 < var3; ++var4) {
               File var5 = var2[var4];
               BufferedInputStream var6 = null;
               boolean var14 = false;

               label123: {
                  try {
                     var14 = true;
                     zzai var7;
                     (var7 = zzai.zzf(var6 = new BufferedInputStream(new FileInputStream(var5)))).size = var5.length();
                     this.zza(var7.key, var7);
                     var14 = false;
                     break label123;
                  } catch (IOException var18) {
                     if (var5 != null) {
                        var5.delete();
                        var14 = false;
                     } else {
                        var14 = false;
                     }
                  } finally {
                     if (var14) {
                        try {
                           if (var6 != null) {
                              var6.close();
                           }
                        } catch (IOException var15) {
                           ;
                        }

                     }
                  }

                  try {
                     if (var6 != null) {
                        var6.close();
                     }
                  } catch (IOException var16) {
                     ;
                  }
                  continue;
               }

               try {
                  var6.close();
               } catch (IOException var17) {
                  ;
               }
            }

         }
      }
   }

   public final synchronized void zza(String var1, zzc var2) {
      int var7 = var2.data.length;
      zzag var6 = this;
      if (this.zzaw + (long)var7 >= (long)this.zzay) {
         if (zzab.DEBUG) {
            zzab.zza("Pruning old cache entries.");
         }

         long var8 = this.zzaw;
         int var10 = 0;
         long var11 = SystemClock.elapsedRealtime();
         Iterator var13 = this.zzav.entrySet().iterator();

         while(var13.hasNext()) {
            zzai var14 = (zzai)((Entry)var13.next()).getValue();
            if (var6.zze(var14.key).delete()) {
               var6.zzaw -= var14.size;
            } else {
               zzab.zzb("Could not delete cache entry for key=%s, filename=%s", var14.key, zzd(var14.key));
            }

            var13.remove();
            ++var10;
            if ((float)(var6.zzaw + (long)var7) < (float)var6.zzay * 0.9F) {
               break;
            }
         }

         if (zzab.DEBUG) {
            zzab.zza("pruned %d files, %d bytes, %d ms", var10, var6.zzaw - var8, SystemClock.elapsedRealtime() - var11);
         }
      }

      File var3 = this.zze(var1);

      try {
         BufferedOutputStream var4 = new BufferedOutputStream(new FileOutputStream(var3));
         zzai var5;
         if (!(var5 = new zzai(var1, var2)).zza(var4)) {
            var4.close();
            zzab.zzb("Failed to write header for %s", var3.getAbsolutePath());
            throw new IOException();
         } else {
            var4.write(var2.data);
            var4.close();
            this.zza(var1, var5);
         }
      } catch (IOException var15) {
         if (!var3.delete()) {
            zzab.zzb("Could not clean up file %s", var3.getAbsolutePath());
         }

      }
   }

   private final synchronized void remove(String var1) {
      boolean var2 = this.zze(var1).delete();
      zzai var5;
      if ((var5 = (zzai)this.zzav.get(var1)) != null) {
         this.zzaw -= var5.size;
         this.zzav.remove(var1);
      }

      if (!var2) {
         zzab.zzb("Could not delete cache entry for key=%s, filename=%s", var1, zzd(var1));
      }

   }

   private static String zzd(String var0) {
      int var1 = var0.length() / 2;
      String var10000 = String.valueOf(String.valueOf(var0.substring(0, var1).hashCode()));
      String var10001 = String.valueOf(String.valueOf(var0.substring(var1).hashCode()));
      return var10001.length() != 0 ? var10000.concat(var10001) : new String(var10000);
   }

   private final File zze(String var1) {
      return new File(this.zzax, zzd(var1));
   }

   private final void zza(String var1, zzai var2) {
      if (!this.zzav.containsKey(var1)) {
         this.zzaw += var2.size;
      } else {
         zzai var3 = (zzai)this.zzav.get(var1);
         this.zzaw += var2.size - var3.size;
      }

      this.zzav.put(var1, var2);
   }

   private static byte[] zza(InputStream var0, int var1) throws IOException {
      byte[] var2 = new byte[var1];

      int var3;
      int var4;
      for(var4 = 0; var4 < var1 && (var3 = var0.read(var2, var4, var1 - var4)) != -1; var4 += var3) {
         ;
      }

      if (var4 != var1) {
         throw new IOException((new StringBuilder(50)).append("Expected ").append(var1).append(" bytes, read ").append(var4).append(" bytes").toString());
      } else {
         return var2;
      }
   }

   private static int zza(InputStream var0) throws IOException {
      int var1;
      if ((var1 = var0.read()) == -1) {
         throw new EOFException();
      } else {
         return var1;
      }
   }

   static void zza(OutputStream var0, int var1) throws IOException {
      var0.write(var1 & 255);
      var0.write(var1 >> 8 & 255);
      var0.write(var1 >> 16 & 255);
      var0.write(var1 >>> 24);
   }

   static int zzb(InputStream var0) throws IOException {
      return 0 | zza(var0) | zza(var0) << 8 | zza(var0) << 16 | zza(var0) << 24;
   }

   static void zza(OutputStream var0, long var1) throws IOException {
      var0.write((byte)((int)var1));
      var0.write((byte)((int)(var1 >>> 8)));
      var0.write((byte)((int)(var1 >>> 16)));
      var0.write((byte)((int)(var1 >>> 24)));
      var0.write((byte)((int)(var1 >>> 32)));
      var0.write((byte)((int)(var1 >>> 40)));
      var0.write((byte)((int)(var1 >>> 48)));
      var0.write((byte)((int)(var1 >>> 56)));
   }

   static long zzc(InputStream var0) throws IOException {
      return 0L | (long)zza(var0) & 255L | ((long)zza(var0) & 255L) << 8 | ((long)zza(var0) & 255L) << 16 | ((long)zza(var0) & 255L) << 24 | ((long)zza(var0) & 255L) << 32 | ((long)zza(var0) & 255L) << 40 | ((long)zza(var0) & 255L) << 48 | ((long)zza(var0) & 255L) << 56;
   }

   static void zza(OutputStream var0, String var1) throws IOException {
      byte[] var2 = var1.getBytes("UTF-8");
      zza(var0, (long)var2.length);
      var0.write(var2, 0, var2.length);
   }

   static String zzd(InputStream var0) throws IOException {
      int var1 = (int)zzc(var0);
      byte[] var2 = zza(var0, var1);
      return new String(var2, "UTF-8");
   }

   static Map zze(InputStream var0) throws IOException {
      int var1;
      Object var2 = (var1 = zzb(var0)) == 0 ? Collections.emptyMap() : new HashMap(var1);

      for(int var3 = 0; var3 < var1; ++var3) {
         String var4 = zzd(var0).intern();
         String var5 = zzd(var0).intern();
         ((Map)var2).put(var4, var5);
      }

      return (Map)var2;
   }
}
