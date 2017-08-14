package com.google.android.gms.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ServiceInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.RemoteException;
import android.support.annotation.WorkerThread;
import android.util.Pair;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.tagmanager.TagManagerService;
import com.google.android.gms.tagmanager.zzce;
import com.google.android.gms.tagmanager.zzcn;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class zzcwn {
   private static final Pattern zzbJe = Pattern.compile("(gtm-[a-z0-9]{1,10})\\.json", 2);
   private static volatile zzcwn zzbJf;
   private final Context mContext;
   private final zzcn zzbHN;
   private final zzce zzbHW;
   private final zzcxi zzbJg;
   private final ExecutorService zzbHL;
   private final ScheduledExecutorService zzbHM;
   private final zzcvs zzbJh;
   private final zzcwn.zza zzbJi;
   private final Object zzbJj = new Object();
   private String zzbDw;
   private String zzbJk;
   private int zzbJl = 1;
   private final Queue zzbJm = new LinkedList();
   private volatile boolean zzuH = false;
   private volatile boolean zzbJn = false;
   private static zzcwn.zzc zzbJo = new zzcwo();

   public static zzcwn zza(Context var0, zzcn var1, zzce var2) {
      zzbo.zzu(var0);
      zzbo.zzu(var0);
      zzcwn var3 = zzbJf;
      if (zzbJf == null) {
         Class var4 = zzcwn.class;
         synchronized(zzcwn.class) {
            var3 = zzbJf;
            if (zzbJf == null) {
               zzbJf = var3 = zzbJo.zzb(var0, var1, var2);
            }
         }
      }

      return var3;
   }

   zzcwn(Context var1, zzcn var2, zzce var3, zzcxi var4, ExecutorService var5, ScheduledExecutorService var6, zzcvs var7, zzcwn.zza var8) {
      zzbo.zzu(var1);
      zzbo.zzu(var2);
      this.mContext = var1;
      this.zzbHN = var2;
      this.zzbHW = var3;
      this.zzbJg = var4;
      this.zzbHL = var5;
      this.zzbHM = var6;
      this.zzbJh = var7;
      this.zzbJi = var8;
   }

   @WorkerThread
   public final void initialize() {
      zzcwn var1 = this;
      zzcvk.v("Initializing Tag Manager.");
      long var2 = System.currentTimeMillis();
      Object var4 = this.zzbJj;
      synchronized(this.zzbJj) {
         label132: {
            if (var1.zzuH) {
               return;
            }

            try {
               if (zza(var1.mContext, TagManagerService.class)) {
                  Pair var5;
                  String var6 = (String)(var5 = var1.zzf((String[])null)).first;
                  String var7 = (String)var5.second;
                  if (var6 != null && var7 != null) {
                     String var10001 = String.valueOf(var6);
                     String var10000;
                     if (var10001.length() != 0) {
                        var10000 = "Loading container ".concat(var10001);
                     } else {
                        String var10002 = new String;
                        var10000 = var10002;
                        var10002.<init>("Loading container ");
                     }

                     zzcvk.zzaS(var10000);
                     var1.zzbHL.execute(new zzcwt(var1, var6, var7, (String)null));
                     var1.zzbHM.schedule(new zzcwu(var1), 5000L, TimeUnit.MILLISECONDS);
                     if (!var1.zzbJn) {
                        zzcvk.zzaS("Installing Tag Manager event handler.");
                        var1.zzbJn = true;
                        zzcwn var16 = var1;

                        try {
                           var16.zzbHN.zza(new zzcwp(var16));
                        } catch (RemoteException var23) {
                           zzcup.zza("Error communicating with measurement proxy: ", var23, var1.mContext);
                        }

                        try {
                           var16.zzbHN.zza(new zzcwr(var16));
                        } catch (RemoteException var22) {
                           zzcup.zza("Error communicating with measurement proxy: ", var22, var1.mContext);
                        }

                        var1.mContext.registerComponentCallbacks(new zzcww(var1));
                        zzcvk.zzaS("Tag Manager event handler installed.");
                     }
                     break label132;
                  }

                  zzcvk.zzaT("Tag Manager's event handler WILL NOT be installed (no container loaded)");
                  break label132;
               }

               zzcvk.zzaT("Tag Manager fails to initialize (TagManagerService not enabled in the manifest)");
            } finally {
               var1.zzuH = true;
            }

            return;
         }
      }

      long var26 = System.currentTimeMillis() - var2;
      zzcvk.zzaS((new StringBuilder(53)).append("Tag Manager initilization took ").append(var26).append("ms").toString());
   }

   @WorkerThread
   public final void zze(String[] var1) {
      zzcvk.v("Initializing Tag Manager.");
      long var2 = System.currentTimeMillis();
      Object var4 = this.zzbJj;
      synchronized(this.zzbJj) {
         label132: {
            if (this.zzuH) {
               return;
            }

            try {
               if (zza(this.mContext, TagManagerService.class)) {
                  Pair var5;
                  String var6 = (String)(var5 = this.zzf((String[])null)).first;
                  String var7 = (String)var5.second;
                  if (var6 != null && var7 != null) {
                     String var10001 = String.valueOf(var6);
                     String var10000;
                     if (var10001.length() != 0) {
                        var10000 = "Loading container ".concat(var10001);
                     } else {
                        String var10002 = new String;
                        var10000 = var10002;
                        var10002.<init>("Loading container ");
                     }

                     zzcvk.zzaS(var10000);
                     this.zzbHL.execute(new zzcwt(this, var6, var7, (String)null));
                     this.zzbHM.schedule(new zzcwu(this), 5000L, TimeUnit.MILLISECONDS);
                     if (!this.zzbJn) {
                        zzcvk.zzaS("Installing Tag Manager event handler.");
                        this.zzbJn = true;
                        zzcwn var10 = this;

                        try {
                           var10.zzbHN.zza(new zzcwp(var10));
                        } catch (RemoteException var21) {
                           zzcup.zza("Error communicating with measurement proxy: ", var21, this.mContext);
                        }

                        try {
                           var10.zzbHN.zza(new zzcwr(var10));
                        } catch (RemoteException var20) {
                           zzcup.zza("Error communicating with measurement proxy: ", var20, this.mContext);
                        }

                        this.mContext.registerComponentCallbacks(new zzcww(this));
                        zzcvk.zzaS("Tag Manager event handler installed.");
                     }
                     break label132;
                  }

                  zzcvk.zzaT("Tag Manager's event handler WILL NOT be installed (no container loaded)");
                  break label132;
               }

               zzcvk.zzaT("Tag Manager fails to initialize (TagManagerService not enabled in the manifest)");
            } finally {
               this.zzuH = true;
            }

            return;
         }
      }

      long var24 = System.currentTimeMillis() - var2;
      zzcvk.zzaS((new StringBuilder(53)).append("Tag Manager initilization took ").append(var24).append("ms").toString());
   }

   private final Pair zzf(String[] var1) {
      zzcvk.v("Looking up container asset.");
      if (this.zzbDw != null && this.zzbJk != null) {
         return Pair.create(this.zzbDw, this.zzbJk);
      } else {
         String[] var2;
         try {
            var2 = this.zzbJi.zzfL("containers");
         } catch (IOException var10) {
            zzcvk.zzb(String.format("Failed to enumerate assets in folder %s", "containers"), var10);
            return Pair.create((Object)null, (Object)null);
         }

         boolean var3 = false;

         String var10000;
         String var10001;
         String var10002;
         for(int var4 = 0; var4 < var2.length; ++var4) {
            Matcher var5;
            if ((var5 = zzbJe.matcher(var2[var4])).matches()) {
               if (!var3) {
                  this.zzbDw = var5.group(1);
                  String var6 = String.valueOf("containers");
                  String var7 = String.valueOf(File.separator);
                  String var8 = String.valueOf(var2[var4]);
                  this.zzbJk = (new StringBuilder(String.valueOf(var6).length() + String.valueOf(var7).length() + String.valueOf(var8).length())).append(var6).append(var7).append(var8).toString();
                  var3 = true;
                  var10001 = String.valueOf(this.zzbDw);
                  if (var10001.length() != 0) {
                     var10000 = "Asset found for container ".concat(var10001);
                  } else {
                     var10002 = new String;
                     var10000 = var10002;
                     var10002.<init>("Asset found for container ");
                  }

                  zzcvk.v(var10000);
               } else {
                  var10001 = String.valueOf(var2[var4]);
                  if (var10001.length() != 0) {
                     var10000 = "Extra container asset found, will not be loaded: ".concat(var10001);
                  } else {
                     var10002 = new String;
                     var10000 = var10002;
                     var10002.<init>("Extra container asset found, will not be loaded: ");
                  }

                  zzcvk.zzaT(var10000);
               }
            } else {
               zzcvk.zzaT(String.format("Ignoring container asset %s (does not match %s)", var2[var4], zzbJe.pattern()));
            }
         }

         if (!var3) {
            zzcvk.zzaT("No container asset found in /assets/containers. Checking top level /assets directory for container assets.");

            String[] var11;
            try {
               var11 = this.zzbJi.zzCC();
            } catch (IOException var9) {
               zzcvk.zzb("Failed to enumerate assets.", var9);
               return Pair.create((Object)null, (Object)null);
            }

            for(int var12 = 0; var12 < var11.length; ++var12) {
               Matcher var13;
               if ((var13 = zzbJe.matcher(var11[var12])).matches()) {
                  if (!var3) {
                     this.zzbDw = var13.group(1);
                     this.zzbJk = var11[var12];
                     var10001 = String.valueOf(this.zzbDw);
                     if (var10001.length() != 0) {
                        var10000 = "Asset found for container ".concat(var10001);
                     } else {
                        var10002 = new String;
                        var10000 = var10002;
                        var10002.<init>("Asset found for container ");
                     }

                     zzcvk.v(var10000);
                     zzcvk.zzaT("Loading container assets from top level /assets directory. Please move the container asset to /assets/containers");
                     var3 = true;
                  } else {
                     var10001 = String.valueOf(var11[var12]);
                     if (var10001.length() != 0) {
                        var10000 = "Extra container asset found, will not be loaded: ".concat(var10001);
                     } else {
                        var10002 = new String;
                        var10000 = var10002;
                        var10002.<init>("Extra container asset found, will not be loaded: ");
                     }

                     zzcvk.zzaT(var10000);
                  }
               }
            }
         }

         return Pair.create(this.zzbDw, this.zzbJk);
      }
   }

   private static boolean zza(Context var0, Class var1) {
      try {
         ServiceInfo var2;
         if ((var2 = var0.getPackageManager().getServiceInfo(new ComponentName(var0, var1), 4)) != null && var2.enabled) {
            return true;
         }
      } catch (NameNotFoundException var3) {
         ;
      }

      return false;
   }

   final void zzs(Uri var1) {
      this.zzbHL.execute(new zzcwy(this, var1));
   }

   // $FF: synthetic method
   static int zza(zzcwn var0) {
      return var0.zzbJl;
   }

   // $FF: synthetic method
   static zzcxi zzb(zzcwn var0) {
      return var0.zzbJg;
   }

   // $FF: synthetic method
   static zzcn zzc(zzcwn var0) {
      return var0.zzbHN;
   }

   // $FF: synthetic method
   static Context zzd(zzcwn var0) {
      return var0.mContext;
   }

   // $FF: synthetic method
   static Queue zze(zzcwn var0) {
      return var0.zzbJm;
   }

   // $FF: synthetic method
   static int zza(zzcwn var0, int var1) {
      return var0.zzbJl = var1;
   }

   // $FF: synthetic method
   static Object zzg(zzcwn var0) {
      return var0.zzbJj;
   }

   // $FF: synthetic method
   static Pair zza(zzcwn var0, String[] var1) {
      return var0.zzf((String[])null);
   }

   // $FF: synthetic method
   static zzcvs zzh(zzcwn var0) {
      return var0.zzbJh;
   }

   // $FF: synthetic method
   static boolean zzi(zzcwn var0) {
      return var0.zzuH;
   }

   // $FF: synthetic method
   static boolean zza(zzcwn var0, boolean var1) {
      return var0.zzuH = false;
   }

   public static class zza {
      private final Context mContext;

      public zza(Context var1) {
         this.mContext = var1;
      }

      public final String[] zzfL(String var1) throws IOException {
         return this.mContext.getAssets().list(var1);
      }

      public final String[] zzCC() throws IOException {
         return this.mContext.getAssets().list("");
      }
   }

   class zzb extends zzcve {
      private zzb() {
      }

      public final void zza(boolean var1, String var2) throws RemoteException {
         zzcwn.this.zzbHL.execute(new zzcwz(this, var1, var2));
      }

      // $FF: synthetic method
      zzb(zzcwo var2) {
         this();
      }
   }

   public interface zzc {
      zzcwn zzb(Context var1, zzcn var2, zzce var3);
   }
}
