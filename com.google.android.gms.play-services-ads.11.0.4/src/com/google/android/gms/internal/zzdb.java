package com.google.android.gms.internal;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Build.VERSION;
import android.support.annotation.VisibleForTesting;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.zze;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class zzdb {
   private static final String TAG = zzdb.class.getSimpleName();
   protected Context zzqD;
   private Context zzqE;
   private ExecutorService zzqF;
   private DexClassLoader zzqG;
   private zzcw zzqH;
   private byte[] zzqI;
   private volatile AdvertisingIdClient zzqJ = null;
   private volatile boolean zzqk = false;
   private Future zzqK = null;
   private volatile zzax zzqL = null;
   private Future zzqM = null;
   private zzcn zzqN;
   private GoogleApiClient zzqO = null;
   protected boolean zzqP = false;
   private static Object zzqQ = new Object();
   private boolean zzqR = false;
   private static zze zzqS = null;
   protected boolean zzqT = false;
   private Map zzqU;
   private boolean zzqV = false;

   public static zzdb zza(Context var0, String var1, String var2, boolean var3) {
      zzdb var4 = new zzdb(var0);

      try {
         var4.zzqF = Executors.newCachedThreadPool();
         var4.zzqk = var3;
         if (var3) {
            var4.zzqK = var4.zzqF.submit(new zzdc(var4));
         }

         zzdb var11 = var4;
         var4.zzqF.execute(new zzde(var4));

         try {
            zzqS = zze.zzoW();
            var11.zzqP = zze.zzau(var11.zzqD) > 0;
            var11.zzqR = zzqS.isGooglePlayServicesAvailable(var11.zzqD) == 0;
            if (var11.zzqD.getApplicationContext() != null) {
               var11.zzqO = (new Builder(var11.zzqD)).addApi(zzazn.API).build();
            }
         } catch (Throwable var34) {
            ;
         }

         var4.zza(0, true);
         if (zzdg.zzS()) {
            zzme var9 = zzmo.zzFa;
            if (((Boolean)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var9)).booleanValue()) {
               throw new IllegalStateException("Task Context initialization must not be called from the UI thread.");
            }
         }

         String var12 = var1;
         var11 = var4;
         var4.zzqH = new zzcw((SecureRandom)null);

         try {
            var11.zzqI = var11.zzqH.zzl(var12);
         } catch (zzcx var33) {
            throw new zzcy(var33);
         }

         var12 = var2;
         var11 = var4;

         try {
            File var13;
            if ((var13 = var11.zzqD.getCacheDir()) == null && (var13 = var11.zzqD.getDir("dex", 0)) == null) {
               throw new zzcy();
            }

            String var14 = "1489418796403";
            File var21;
            if (!(var21 = new File(String.format("%s/%s.jar", var13, var14))).exists()) {
               byte[] var22 = var11.zzqH.zza(var11.zzqI, var12);
               var21.createNewFile();
               FileOutputStream var23;
               (var23 = new FileOutputStream(var21)).write(var22, 0, var22.length);
               var23.close();
            }

            File var15 = var21;
            var11.zzb(var13, var14);

            try {
               var11.zzqG = new DexClassLoader(var15.getAbsolutePath(), var13.getAbsolutePath(), (String)null, var11.zzqD.getClassLoader());
            } finally {
               zza(var21);
               var11.zza(var13, var14);
               zzm(String.format("%s/%s.dex", var13, var14));
            }
         } catch (FileNotFoundException var35) {
            throw new zzcy(var35);
         } catch (IOException var36) {
            throw new zzcy(var36);
         } catch (zzcx var37) {
            throw new zzcy(var37);
         } catch (NullPointerException var38) {
            throw new zzcy(var38);
         }

         var4.zzqN = new zzcn(var4);
         var4.zzqV = true;
      } catch (zzcy var39) {
         ;
      }

      return var4;
   }

   public final Context getContext() {
      return this.zzqD;
   }

   public final boolean isInitialized() {
      return this.zzqV;
   }

   public final Context getApplicationContext() {
      return this.zzqE;
   }

   public final ExecutorService zzC() {
      return this.zzqF;
   }

   public final DexClassLoader zzD() {
      return this.zzqG;
   }

   public final zzcw zzE() {
      return this.zzqH;
   }

   public final byte[] zzF() {
      return this.zzqI;
   }

   public final GoogleApiClient zzG() {
      return this.zzqO;
   }

   public final boolean zzH() {
      return this.zzqP;
   }

   public final zzcn zzI() {
      return this.zzqN;
   }

   public final boolean zzJ() {
      return this.zzqR;
   }

   public final zzax zzK() {
      return this.zzqL;
   }

   public final Future zzL() {
      return this.zzqM;
   }

   private zzdb(Context var1) {
      this.zzqD = var1;
      this.zzqE = var1.getApplicationContext();
      this.zzqU = new HashMap();
   }

   private final void zza(File var1, String var2) {
      File var3;
      if (!(var3 = new File(String.format("%s/%s.tmp", var1, var2))).exists()) {
         File var4;
         if ((var4 = new File(String.format("%s/%s.dex", var1, var2))).exists()) {
            FileInputStream var5 = null;
            FileOutputStream var6 = null;
            long var7;
            if ((var7 = var4.length()) > 0L) {
               byte[] var9 = new byte[(int)var7];
               boolean var23 = false;

               label173: {
                  label174: {
                     try {
                        var23 = true;
                        if ((var5 = new FileInputStream(var4)).read(var9) <= 0) {
                           var23 = false;
                           break label173;
                        }

                        zzbc var10;
                        (var10 = new zzbc()).zzcG = VERSION.SDK.getBytes();
                        var10.zzcF = var2.getBytes();
                        byte[] var11 = this.zzqH.zzc(this.zzqI, var9).getBytes();
                        var10.data = var11;
                        var10.zzcE = zzbv.zzb(var11);
                        var3.createNewFile();
                        var6 = new FileOutputStream(var3);
                        byte[] var12 = adp.zzc(var10);
                        var6.write(var12, 0, var12.length);
                        var6.close();
                        var23 = false;
                        break label174;
                     } catch (NoSuchAlgorithmException | zzcx | IOException var31) {
                        var23 = false;
                     } finally {
                        if (var23) {
                           try {
                              if (var5 != null) {
                                 var5.close();
                              }
                           } catch (IOException var27) {
                              ;
                           }

                           try {
                              if (var6 != null) {
                                 var6.close();
                              }
                           } catch (IOException var26) {
                              ;
                           }

                           zza(var4);
                        }
                     }

                     try {
                        if (var5 != null) {
                           var5.close();
                        }
                     } catch (IOException var25) {
                        ;
                     }

                     try {
                        if (var6 != null) {
                           var6.close();
                        }
                     } catch (IOException var24) {
                        ;
                     }

                     zza(var4);
                     return;
                  }

                  try {
                     var5.close();
                  } catch (IOException var30) {
                     ;
                  }

                  try {
                     var6.close();
                  } catch (IOException var29) {
                     ;
                  }

                  zza(var4);
                  return;
               }

               try {
                  var5.close();
               } catch (IOException var28) {
                  ;
               }

               zza(var4);
            }
         }
      }
   }

   private static void zzm(String var0) {
      zza(new File(var0));
   }

   private static void zza(File var0) {
      if (!var0.exists()) {
         Log.d(TAG, String.format("File %s not found. No need for deletion", var0.getAbsolutePath()));
      } else {
         var0.delete();
      }
   }

   private final boolean zzb(File var1, String var2) {
      File var3;
      if (!(var3 = new File(String.format("%s/%s.tmp", var1, var2))).exists()) {
         return false;
      } else {
         File var4;
         if ((var4 = new File(String.format("%s/%s.dex", var1, var2))).exists()) {
            return false;
         } else {
            FileInputStream var5 = null;
            FileOutputStream var6 = null;
            boolean var24 = false;

            label216: {
               label217: {
                  label218: {
                     try {
                        var24 = true;
                        long var7;
                        if ((var7 = var3.length()) <= 0L) {
                           zza(var3);
                           return false;
                        }

                        byte[] var9 = new byte[(int)var7];
                        if ((var5 = new FileInputStream(var3)).read(var9) <= 0) {
                           Log.d(TAG, "Cannot read the cache data.");
                           zza(var3);
                           var24 = false;
                           break label216;
                        }

                        zzbc var10 = (zzbc)adp.zza(new zzbc(), var9);
                        if (var2.equals(new String(var10.zzcF)) && Arrays.equals(var10.zzcE, zzbv.zzb(var10.data)) && Arrays.equals(var10.zzcG, VERSION.SDK.getBytes())) {
                           byte[] var11 = this.zzqH.zza(this.zzqI, new String(var10.data));
                           var4.createNewFile();
                           (var6 = new FileOutputStream(var4)).write(var11, 0, var11.length);
                           var24 = false;
                           break label217;
                        }

                        zza(var3);
                        var24 = false;
                        break label218;
                     } catch (NoSuchAlgorithmException | zzcx | IOException var33) {
                        var24 = false;
                     } finally {
                        if (var24) {
                           try {
                              if (var5 != null) {
                                 var5.close();
                              }
                           } catch (IOException var28) {
                              ;
                           }

                           try {
                              if (var6 != null) {
                                 var6.close();
                              }
                           } catch (IOException var27) {
                              ;
                           }

                        }
                     }

                     try {
                        if (var5 != null) {
                           var5.close();
                        }
                     } catch (IOException var26) {
                        ;
                     }

                     try {
                        if (var6 != null) {
                           var6.close();
                        }
                     } catch (IOException var25) {
                        ;
                     }

                     return false;
                  }

                  try {
                     var5.close();
                  } catch (IOException var32) {
                     ;
                  }

                  return false;
               }

               try {
                  var5.close();
               } catch (IOException var31) {
                  ;
               }

               try {
                  var6.close();
               } catch (IOException var30) {
                  ;
               }

               return true;
            }

            try {
               var5.close();
            } catch (IOException var29) {
               ;
            }

            return false;
         }
      }
   }

   public final boolean zza(String var1, String var2, Class... var3) {
      if (!this.zzqU.containsKey(new Pair(var1, var2))) {
         this.zzqU.put(new Pair(var1, var2), new zzea(this, var1, var2, var3));
         return true;
      } else {
         return false;
      }
   }

   public final Method zzc(String var1, String var2) {
      zzea var3;
      return (var3 = (zzea)this.zzqU.get(new Pair(var1, var2))) == null ? null : var3.zzY();
   }

   private final void zzM() {
      try {
         if (this.zzqJ == null && this.zzqE != null) {
            AdvertisingIdClient var1;
            (var1 = new AdvertisingIdClient(this.zzqE)).start();
            this.zzqJ = var1;
         }

      } catch (IOException | GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException var2) {
         this.zzqJ = null;
      }
   }

   @VisibleForTesting
   final void zza(int var1, boolean var2) {
      if (this.zzqR) {
         Future var3 = this.zzqF.submit(new zzdd(this, var1, var2));
         if (var1 == 0) {
            this.zzqM = var3;
         }

      }
   }

   @VisibleForTesting
   final zzax zzb(int var1, boolean var2) {
      if (var1 > 0 && var2) {
         try {
            Thread.sleep((long)(var1 * 1000));
         } catch (InterruptedException var3) {
            ;
         }
      }

      return this.zzN();
   }

   private static boolean zza(int var0, zzax var1) {
      if (var0 < 4) {
         if (var1 == null) {
            return true;
         }

         zzme var2 = zzmo.zzFd;
         if (((Boolean)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var2)).booleanValue() && (var1.zzaT == null || var1.zzaT.equals("0000000000000000000000000000000000000000000000000000000000000000"))) {
            return true;
         }

         var2 = zzmo.zzFe;
         if (((Boolean)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var2)).booleanValue() && (var1.zzbZ == null || var1.zzbZ.zzcx == null || var1.zzbZ.zzcx.longValue() == -2L)) {
            return true;
         }
      }

      return false;
   }

   @VisibleForTesting
   private final zzax zzN() {
      zzax var1 = null;

      try {
         PackageInfo var2 = this.zzqD.getPackageManager().getPackageInfo(this.zzqD.getPackageName(), 0);
         var1 = zzcaq.zzn(this.zzqD, this.zzqD.getPackageName(), Integer.toString(var2.versionCode));
      } catch (Throwable var3) {
         ;
      }

      return var1;
   }

   public final AdvertisingIdClient zzO() {
      if (!this.zzqk) {
         return null;
      } else if (this.zzqJ != null) {
         return this.zzqJ;
      } else {
         if (this.zzqK != null) {
            try {
               this.zzqK.get(2000L, TimeUnit.MILLISECONDS);
               this.zzqK = null;
            } catch (InterruptedException var1) {
               ;
            } catch (ExecutionException var2) {
               ;
            } catch (TimeoutException var3) {
               this.zzqK.cancel(true);
            }
         }

         return this.zzqJ;
      }
   }

   public final void zzP() {
      try {
         Object var1 = zzqQ;
         synchronized(zzqQ) {
            if (!this.zzqT) {
               if (this.zzqR && this.zzqO != null) {
                  this.zzqO.connect();
                  this.zzqT = true;
               } else {
                  this.zzqT = false;
               }

            }
         }
      } catch (Throwable var4) {
         ;
      }
   }

   public final void zzQ() {
      Object var1 = zzqQ;
      synchronized(zzqQ) {
         if (this.zzqT && this.zzqO != null) {
            this.zzqO.disconnect();
            this.zzqT = false;
         }

      }
   }

   public final int zzy() {
      int var1 = Integer.MIN_VALUE;
      if (this.zzqN != null) {
         var1 = zzcn.zzy();
      }

      return var1;
   }

   // $FF: synthetic method
   static void zza(zzdb var0) {
      var0.zzM();
   }

   // $FF: synthetic method
   static zzax zza(zzdb var0, zzax var1) {
      return var0.zzqL = var1;
   }

   // $FF: synthetic method
   static boolean zzb(int var0, zzax var1) {
      return zza(var0, var1);
   }
}
