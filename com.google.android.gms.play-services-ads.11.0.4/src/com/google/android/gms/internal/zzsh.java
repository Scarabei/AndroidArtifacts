package com.google.android.gms.internal;

import com.google.android.gms.common.util.zze;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@zzzn
public final class zzsh extends zzsb {
   private static final Set zzJX = Collections.synchronizedSet(new HashSet());
   private static final DecimalFormat zzJY = new DecimalFormat("#,###");
   private File zzJZ;
   private boolean zzKa;

   public zzsh(zzaka var1) {
      super(var1);
      File var2;
      if ((var2 = this.mContext.getCacheDir()) == null) {
         zzafr.zzaT("Context.getCacheDir() returned null");
      } else {
         this.zzJZ = new File(var2, "admobVideoStreams");
         String var10000;
         String var10001;
         String var10002;
         if (!this.zzJZ.isDirectory() && !this.zzJZ.mkdirs()) {
            var10001 = String.valueOf(this.zzJZ.getAbsolutePath());
            if (var10001.length() != 0) {
               var10000 = "Could not create preload cache directory at ".concat(var10001);
            } else {
               var10002 = new String;
               var10000 = var10002;
               var10002.<init>("Could not create preload cache directory at ");
            }

            zzafr.zzaT(var10000);
            this.zzJZ = null;
         } else if (!this.zzJZ.setReadable(true, false) || !this.zzJZ.setExecutable(true, false)) {
            var10001 = String.valueOf(this.zzJZ.getAbsolutePath());
            if (var10001.length() != 0) {
               var10000 = "Could not set cache file permissions at ".concat(var10001);
            } else {
               var10002 = new String;
               var10000 = var10002;
               var10002.<init>("Could not set cache file permissions at ");
            }

            zzafr.zzaT(var10000);
            this.zzJZ = null;
         }
      }
   }

   public final boolean zzU(String var1) {
      if (this.zzJZ == null) {
         this.zza(var1, (String)null, "noCacheDir", (String)null);
         return false;
      } else {
         boolean var45;
         do {
            int var10000;
            if (this.zzJZ == null) {
               var10000 = 0;
            } else {
               int var30 = 0;
               File[] var31;
               int var32 = (var31 = this.zzJZ.listFiles()).length;

               for(int var33 = 0; var33 < var32; ++var33) {
                  if (!var31[var33].getName().endsWith(".done")) {
                     ++var30;
                  }
               }

               var10000 = var30;
            }

            zzme var29 = zzmo.zzCm;
            if (var10000 <= ((Integer)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var29)).intValue()) {
               zzji.zzds();
               String var2 = zzaiy.zzaR(var1);
               File var3 = new File(this.zzJZ, var2);
               File var4 = this.zzb(var3);
               String var10001;
               String var10002;
               String var46;
               if (var3.isFile() && var4.isFile()) {
                  int var43 = (int)var3.length();
                  var10001 = String.valueOf(var1);
                  if (var10001.length() != 0) {
                     var46 = "Stream cache hit at ".concat(var10001);
                  } else {
                     var10002 = new String;
                     var46 = var10002;
                     var10002.<init>("Stream cache hit at ");
                  }

                  zzafr.zzaC(var46);
                  this.zza(var1, var3.getAbsolutePath(), var43);
                  return true;
               }

               var46 = String.valueOf(this.zzJZ.getAbsolutePath());
               var10001 = String.valueOf(var1);
               if (var10001.length() != 0) {
                  var46 = var46.concat(var10001);
               } else {
                  var10002 = new String;
                  var10001 = var46;
                  var46 = var10002;
                  var10002.<init>(var10001);
               }

               String var5 = var46;
               Set var6 = zzJX;
               synchronized(zzJX) {
                  if (zzJX.contains(var5)) {
                     var10001 = String.valueOf(var1);
                     if (var10001.length() != 0) {
                        var46 = "Stream cache already in progress at ".concat(var10001);
                     } else {
                        var10002 = new String;
                        var46 = var10002;
                        var10002.<init>("Stream cache already in progress at ");
                     }

                     zzafr.zzaT(var46);
                     this.zza(var1, var3.getAbsolutePath(), "inProgress", (String)null);
                     return false;
                  }

                  zzJX.add(var5);
               }

               var6 = null;
               String var7 = "error";
               String var8 = null;

               try {
                  com.google.android.gms.ads.internal.zzbs.zzbM();
                  var29 = zzmo.zzCs;
                  HttpURLConnection var9;
                  int var10;
                  if ((var9 = zzajo.zzb(var1, ((Integer)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var29)).intValue())) instanceof HttpURLConnection && (var10 = ((HttpURLConnection)var9).getResponseCode()) >= 400) {
                     var7 = "badUrl";
                     var10001 = String.valueOf(Integer.toString(var10));
                     if (var10001.length() != 0) {
                        "HTTP request failed. Code: ".concat(var10001);
                     } else {
                        new String("HTTP request failed. Code: ");
                     }

                     throw new IOException((new StringBuilder(32 + String.valueOf(var1).length())).append("HTTP status code ").append(var10).append(" at ").append(var1).toString());
                  }

                  if ((var10 = var9.getContentLength()) < 0) {
                     var10001 = String.valueOf(var1);
                     if (var10001.length() != 0) {
                        var46 = "Stream cache aborted, missing content-length header at ".concat(var10001);
                     } else {
                        var10002 = new String;
                        var46 = var10002;
                        var10002.<init>("Stream cache aborted, missing content-length header at ");
                     }

                     zzafr.zzaT(var46);
                     this.zza(var1, var3.getAbsolutePath(), "contentLengthMissing", (String)null);
                     zzJX.remove(var5);
                     return false;
                  }

                  String var11 = zzJY.format((long)var10);
                  var29 = zzmo.zzCn;
                  int var12 = ((Integer)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var29)).intValue();
                  if (var10 > var12) {
                     zzafr.zzaT((new StringBuilder(33 + String.valueOf(var11).length() + String.valueOf(var1).length())).append("Content length ").append(var11).append(" exceeds limit at ").append(var1).toString());
                     var10001 = String.valueOf(var11);
                     if (var10001.length() != 0) {
                        var46 = "File too big for full file cache. Size: ".concat(var10001);
                     } else {
                        var10002 = new String;
                        var46 = var10002;
                        var10002.<init>("File too big for full file cache. Size: ");
                     }

                     String var13 = var46;
                     this.zza(var1, var3.getAbsolutePath(), "sizeExceeded", var13);
                     zzJX.remove(var5);
                     return false;
                  }

                  zzafr.zzaC((new StringBuilder(20 + String.valueOf(var11).length() + String.valueOf(var1).length())).append("Caching ").append(var11).append(" bytes from ").append(var1).toString());
                  ReadableByteChannel var14 = Channels.newChannel(var9.getInputStream());
                  FileOutputStream var44;
                  FileChannel var15 = (var44 = new FileOutputStream(var3)).getChannel();
                  ByteBuffer var16 = ByteBuffer.allocate(1048576);
                  zze var17 = com.google.android.gms.ads.internal.zzbs.zzbF();
                  int var18 = 0;
                  long var20 = var17.currentTimeMillis();
                  var29 = zzmo.zzCr;
                  long var22 = ((Long)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var29)).longValue();
                  zzair var24 = new zzair(var22);
                  var29 = zzmo.zzCq;
                  long var25 = ((Long)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var29)).longValue();

                  int var19;
                  while((var19 = var14.read(var16)) >= 0) {
                     if ((var18 += var19) > var12) {
                        var7 = "sizeExceeded";
                        var10001 = String.valueOf(Integer.toString(var18));
                        if (var10001.length() != 0) {
                           "File too big for full file cache. Size: ".concat(var10001);
                        } else {
                           new String("File too big for full file cache. Size: ");
                        }

                        throw new IOException("stream cache file size limit exceeded");
                     }

                     var16.flip();

                     while(var15.write(var16) > 0) {
                        ;
                     }

                     var16.clear();
                     if (var17.currentTimeMillis() - var20 > var25 * 1000L) {
                        var7 = "downloadTimeout";
                        String var28 = String.valueOf(Long.toString(var25));
                        var8 = (new StringBuilder(29 + String.valueOf(var28).length())).append("Timeout exceeded. Limit: ").append(var28).append(" sec").toString();
                        throw new IOException("stream cache time limit exceeded");
                     }

                     if (this.zzKa) {
                        var7 = "externalAbort";
                        throw new IOException("abort requested");
                     }

                     if (var24.tryAcquire()) {
                        String var50 = var3.getAbsolutePath();
                        zzaiy.zzaaH.post(new zzsc(this, var1, var50, var18, var10, false));
                     }
                  }

                  var44.close();
                  if (zzafr.zzz(3)) {
                     String var27 = zzJY.format((long)var18);
                     zzafr.zzaC((new StringBuilder(22 + String.valueOf(var27).length() + String.valueOf(var1).length())).append("Preloaded ").append(var27).append(" bytes from ").append(var1).toString());
                  }

                  var3.setReadable(true, false);
                  File var48 = var4;
                  if (var4.isFile()) {
                     var4.setLastModified(System.currentTimeMillis());
                  } else {
                     try {
                        var48.createNewFile();
                     } catch (IOException var40) {
                        ;
                     }
                  }

                  this.zza(var1, var3.getAbsolutePath(), var18);
                  zzJX.remove(var5);
                  return true;
               } catch (RuntimeException | IOException var41) {
                  if (var41 instanceof RuntimeException) {
                     com.google.android.gms.ads.internal.zzbs.zzbD().zza((Throwable)var41, (String)"VideoStreamFullFileCache.preload");
                  }

                  try {
                     var6.close();
                  } catch (NullPointerException | IOException var39) {
                     ;
                  }

                  if (this.zzKa) {
                     zzafr.zzaS((new StringBuilder(26 + String.valueOf(var1).length())).append("Preload aborted for URL \"").append(var1).append("\"").toString());
                  } else {
                     zzafr.zzc((new StringBuilder(25 + String.valueOf(var1).length())).append("Preload failed for URL \"").append(var1).append("\"").toString(), var41);
                  }

                  if (var3.exists() && !var3.delete()) {
                     var10001 = String.valueOf(var3.getAbsolutePath());
                     if (var10001.length() != 0) {
                        var46 = "Could not delete partial cache file at ".concat(var10001);
                     } else {
                        var10002 = new String;
                        var46 = var10002;
                        var10002.<init>("Could not delete partial cache file at ");
                     }

                     zzafr.zzaT(var46);
                  }

                  this.zza(var1, var3.getAbsolutePath(), var7, var8);
                  zzJX.remove(var5);
                  return false;
               }
            }

            if (this.zzJZ == null) {
               var45 = false;
            } else {
               File var47 = null;
               long var49 = Long.MAX_VALUE;
               File[] var51;
               int var34 = (var51 = this.zzJZ.listFiles()).length;

               for(int var35 = 0; var35 < var34; ++var35) {
                  File var36;
                  long var37;
                  if (!(var36 = var51[var35]).getName().endsWith(".done") && (var37 = var36.lastModified()) < var49) {
                     var47 = var36;
                     var49 = var37;
                  }
               }

               boolean var52 = false;
               if (var47 != null) {
                  var52 = var47.delete();
                  File var53;
                  if ((var53 = this.zzb(var47)).isFile()) {
                     var52 &= var53.delete();
                  }
               }

               var45 = var52;
            }
         } while(var45);

         zzafr.zzaT("Unable to expire stream cache");
         this.zza(var1, (String)null, "expireFailed", (String)null);
         return false;
      }
   }

   public final void abort() {
      this.zzKa = true;
   }

   private final File zzb(File var1) {
      return new File(this.zzJZ, String.valueOf(var1.getName()).concat(".done"));
   }
}
