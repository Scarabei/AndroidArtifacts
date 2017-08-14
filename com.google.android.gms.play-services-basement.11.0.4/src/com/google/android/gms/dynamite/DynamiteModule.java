package com.google.android.gms.dynamite;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.common.util.DynamiteApi;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public final class DynamiteModule {
   private static Boolean zzaSF;
   private static zzj zzaSG;
   private static zzl zzaSH;
   private static String zzaSI;
   private static final ThreadLocal zzaSJ = new ThreadLocal();
   private static final zzh zzaSK = new zza();
   public static final DynamiteModule.zzd zzaSL = new zzb();
   private static DynamiteModule.zzd zzaSM = new zzc();
   public static final DynamiteModule.zzd zzaSN = new zzd();
   public static final DynamiteModule.zzd zzaSO = new zze();
   public static final DynamiteModule.zzd zzaSP = new zzf();
   private final Context zzaSQ;

   public static DynamiteModule zza(Context var0, DynamiteModule.zzd var1, String var2) throws DynamiteModule.zzc {
      DynamiteModule.zza var3 = (DynamiteModule.zza)zzaSJ.get();
      DynamiteModule.zza var4 = new DynamiteModule.zza((zza)null);
      zzaSJ.set(var4);

      DynamiteModule var13;
      try {
         zzi var5 = var1.zza(var0, var2, zzaSK);
         int var6 = var5.zzaSU;
         int var7 = var5.zzaSV;
         Log.i("DynamiteModule", (new StringBuilder(68 + String.valueOf(var2).length() + String.valueOf(var2).length())).append("Considering local module ").append(var2).append(":").append(var6).append(" and remote module ").append(var2).append(":").append(var7).toString());
         if (var5.zzaSW == 0 || var5.zzaSW == -1 && var5.zzaSU == 0 || var5.zzaSW == 1 && var5.zzaSV == 0) {
            var6 = var5.zzaSU;
            var7 = var5.zzaSV;
            throw new DynamiteModule.zzc((new StringBuilder(91)).append("No acceptable module found. Local version is ").append(var6).append(" and remote version is ").append(var7).append(".").toString(), (zza)null);
         }

         if (var5.zzaSW != -1) {
            if (var5.zzaSW == 1) {
               try {
                  var13 = zza(var0, var2, var5.zzaSV);
                  return var13;
               } catch (DynamiteModule.zzc var11) {
                  String var10002 = String.valueOf(var11.getMessage());
                  String var10001;
                  if (var10002.length() != 0) {
                     var10001 = "Failed to load remote module: ".concat(var10002);
                  } else {
                     String var10003 = new String;
                     var10001 = var10003;
                     var10003.<init>("Failed to load remote module: ");
                  }

                  Log.w("DynamiteModule", var10001);
                  if (var5.zzaSU != 0 && var1.zza(var0, var2, new DynamiteModule.zzb(var5.zzaSU, 0)).zzaSW == -1) {
                     DynamiteModule var14 = zzG(var0, var2);
                     return var14;
                  }

                  throw new DynamiteModule.zzc("Remote load failed. No local fallback found.", var11, (zza)null);
               }
            }

            var6 = var5.zzaSW;
            throw new DynamiteModule.zzc((new StringBuilder(47)).append("VersionPolicy returned invalid code:").append(var6).toString(), (zza)null);
         }

         var13 = zzG(var0, var2);
      } finally {
         if (var4.zzaSR != null) {
            var4.zzaSR.close();
         }

         zzaSJ.set(var3);
      }

      return var13;
   }

   public static int zzE(Context var0, String var1) {
      try {
         ClassLoader var10000 = var0.getApplicationContext().getClassLoader();
         String var3 = String.valueOf("com.google.android.gms.dynamite.descriptors.");
         String var4 = String.valueOf("ModuleDescriptor");
         Class var2;
         Field var8 = (var2 = var10000.loadClass((new StringBuilder(1 + String.valueOf(var3).length() + String.valueOf(var1).length() + String.valueOf(var4).length())).append(var3).append(var1).append(".").append(var4).toString())).getDeclaredField("MODULE_ID");
         Field var9 = var2.getDeclaredField("MODULE_VERSION");
         if (!var8.get((Object)null).equals(var1)) {
            String var5 = String.valueOf(var8.get((Object)null));
            Log.e("DynamiteModule", (new StringBuilder(51 + String.valueOf(var5).length() + String.valueOf(var1).length())).append("Module descriptor id '").append(var5).append("' didn't match expected id '").append(var1).append("'").toString());
            return 0;
         }

         return var9.getInt((Object)null);
      } catch (ClassNotFoundException var6) {
         Log.w("DynamiteModule", (new StringBuilder(45 + String.valueOf(var1).length())).append("Local module descriptor class for ").append(var1).append(" not found.").toString());
      } catch (Exception var7) {
         String var10002 = String.valueOf(var7.getMessage());
         String var10001;
         if (var10002.length() != 0) {
            var10001 = "Failed to load module descriptor class: ".concat(var10002);
         } else {
            String var10003 = new String;
            var10001 = var10003;
            var10003.<init>("Failed to load module descriptor class: ");
         }

         Log.e("DynamiteModule", var10001);
      }

      return 0;
   }

   public static int zzb(Context var0, String var1, boolean var2) {
      Class var4 = DynamiteModule.class;
      Boolean var3;
      synchronized(DynamiteModule.class) {
         var3 = zzaSF;
         if (zzaSF == null) {
            try {
               Class var19;
               Field var7 = (var19 = var0.getApplicationContext().getClassLoader().loadClass(DynamiteModule.DynamiteLoaderClassLoader.class.getName())).getDeclaredField("sClassLoader");
               synchronized(var19) {
                  ClassLoader var9;
                  if ((var9 = (ClassLoader)var7.get((Object)null)) != null) {
                     if (var9 == ClassLoader.getSystemClassLoader()) {
                        var3 = Boolean.FALSE;
                     } else {
                        try {
                           zza(var9);
                        } catch (DynamiteModule.zzc var13) {
                           ;
                        }

                        var3 = Boolean.TRUE;
                     }
                  } else if ("com.google.android.gms".equals(var0.getApplicationContext().getPackageName())) {
                     var7.set((Object)null, ClassLoader.getSystemClassLoader());
                     var3 = Boolean.FALSE;
                  } else {
                     try {
                        int var10 = zzd(var0, var1, var2);
                        int var10000;
                        if (zzaSI == null || zzaSI.isEmpty()) {
                           var10000 = var10;
                           return var10000;
                        }

                        zzg var20;
                        zza(var20 = new zzg(zzaSI, ClassLoader.getSystemClassLoader()));
                        var7.set((Object)null, var20);
                        zzaSF = Boolean.TRUE;
                        var10000 = var10;
                        return var10000;
                     } catch (DynamiteModule.zzc var15) {
                        var7.set((Object)null, ClassLoader.getSystemClassLoader());
                        var3 = Boolean.FALSE;
                     }
                  }
               }
            } catch (IllegalAccessException | NoSuchFieldException | ClassNotFoundException var17) {
               String var6 = String.valueOf(var17);
               Log.w("DynamiteModule", (new StringBuilder(30 + String.valueOf(var6).length())).append("Failed to load module via V2: ").append(var6).toString());
               var3 = Boolean.FALSE;
            }

            zzaSF = var3;
         }
      }

      if (var3.booleanValue()) {
         try {
            return zzd(var0, var1, var2);
         } catch (DynamiteModule.zzc var14) {
            String var10002 = String.valueOf(var14.getMessage());
            String var10001;
            if (var10002.length() != 0) {
               var10001 = "Failed to retrieve remote module version: ".concat(var10002);
            } else {
               String var10003 = new String;
               var10001 = var10003;
               var10003.<init>("Failed to retrieve remote module version: ");
            }

            Log.w("DynamiteModule", var10001);
            return 0;
         }
      } else {
         return zzc(var0, var1, var2);
      }
   }

   private static int zzc(Context var0, String var1, boolean var2) {
      zzj var3;
      if ((var3 = zzaT(var0)) == null) {
         return 0;
      } else {
         try {
            return var3.zza(zzn.zzw(var0), var1, var2);
         } catch (RemoteException var5) {
            String var10002 = String.valueOf(var5.getMessage());
            String var10001;
            if (var10002.length() != 0) {
               var10001 = "Failed to retrieve remote module version: ".concat(var10002);
            } else {
               String var10003 = new String;
               var10001 = var10003;
               var10003.<init>("Failed to retrieve remote module version: ");
            }

            Log.w("DynamiteModule", var10001);
            return 0;
         }
      }
   }

   private static int zzd(Context var0, String var1, boolean var2) throws DynamiteModule.zzc {
      Cursor var3 = null;

      int var4;
      try {
         String var11 = var2 ? "api_force_staging" : "api";
         String var13 = String.valueOf("content://com.google.android.gms.chimera/");
         Uri var12 = Uri.parse((new StringBuilder(1 + String.valueOf(var13).length() + String.valueOf(var11).length() + String.valueOf(var1).length())).append(var13).append(var11).append("/").append(var1).toString());
         if ((var3 = var0.getContentResolver().query(var12, (String[])null, (String)null, (String[])null, (String)null)) == null || !var3.moveToFirst()) {
            Log.w("DynamiteModule", "Failed to retrieve remote module version.");
            throw new DynamiteModule.zzc("Failed to connect to dynamite module ContentResolver.", (zza)null);
         }

         if ((var4 = var3.getInt(0)) > 0) {
            Class var5 = DynamiteModule.class;
            synchronized(DynamiteModule.class) {
               zzaSI = var3.getString(2);
            }

            DynamiteModule.zza var20;
            if ((var20 = (DynamiteModule.zza)zzaSJ.get()) != null && var20.zzaSR == null) {
               var20.zzaSR = var3;
               var3 = null;
            }
         }
      } catch (Exception var18) {
         if (var18 instanceof DynamiteModule.zzc) {
            throw var18;
         }

         throw new DynamiteModule.zzc("V2 version check failed", var18, (zza)null);
      } finally {
         if (var3 != null) {
            var3.close();
         }

      }

      return var4;
   }

   public static int zzF(Context var0, String var1) {
      return zzb(var0, var1, false);
   }

   private static DynamiteModule zzG(Context var0, String var1) {
      String var10002 = String.valueOf(var1);
      String var10001;
      if (var10002.length() != 0) {
         var10001 = "Selected local version of ".concat(var10002);
      } else {
         String var10003 = new String;
         var10001 = var10003;
         var10003.<init>("Selected local version of ");
      }

      Log.i("DynamiteModule", var10001);
      return new DynamiteModule(var0.getApplicationContext());
   }

   private static DynamiteModule zza(Context var0, String var1, int var2) throws DynamiteModule.zzc {
      Class var4 = DynamiteModule.class;
      Boolean var3;
      synchronized(DynamiteModule.class) {
         var3 = zzaSF;
      }

      if (var3 == null) {
         throw new DynamiteModule.zzc("Failed to determine which loading route to use.", (zza)null);
      } else {
         return var3.booleanValue() ? zzc(var0, var1, var2) : zzb(var0, var1, var2);
      }
   }

   private static DynamiteModule zzb(Context var0, String var1, int var2) throws DynamiteModule.zzc {
      Log.i("DynamiteModule", (new StringBuilder(51 + String.valueOf(var1).length())).append("Selected remote version of ").append(var1).append(", version >= ").append(var2).toString());
      zzj var3;
      if ((var3 = zzaT(var0)) == null) {
         throw new DynamiteModule.zzc("Failed to create IDynamiteLoader.", (zza)null);
      } else {
         IObjectWrapper var4;
         try {
            var4 = var3.zza(zzn.zzw(var0), var1, var2);
         } catch (RemoteException var6) {
            throw new DynamiteModule.zzc("Failed to load remote module.", var6, (zza)null);
         }

         if (zzn.zzE(var4) == null) {
            throw new DynamiteModule.zzc("Failed to load remote module.", (zza)null);
         } else {
            return new DynamiteModule((Context)zzn.zzE(var4));
         }
      }
   }

   private static zzj zzaT(Context var0) {
      Class var1 = DynamiteModule.class;
      synchronized(DynamiteModule.class) {
         if (zzaSG != null) {
            return zzaSG;
         } else if (com.google.android.gms.common.zze.zzoW().isGooglePlayServicesAvailable(var0) != 0) {
            return null;
         } else {
            Object var10000;
            try {
               Object var3;
               IBinder var5;
               IInterface var6;
               if ((var3 = (var5 = (IBinder)var0.createPackageContext("com.google.android.gms", 3).getClassLoader().loadClass("com.google.android.gms.chimera.container.DynamiteLoaderImpl").newInstance()) == null ? null : ((var6 = var5.queryLocalInterface("com.google.android.gms.dynamite.IDynamiteLoader")) instanceof zzj ? (zzj)var6 : new zzk(var5))) == null) {
                  return null;
               }

               zzaSG = (zzj)var3;
               var10000 = var3;
            } catch (Exception var7) {
               String var10002 = String.valueOf(var7.getMessage());
               String var10001;
               if (var10002.length() != 0) {
                  var10001 = "Failed to load IDynamiteLoader from GmsCore: ".concat(var10002);
               } else {
                  String var10003 = new String;
                  var10001 = var10003;
                  var10003.<init>("Failed to load IDynamiteLoader from GmsCore: ");
               }

               Log.e("DynamiteModule", var10001);
               return null;
            }

            return (zzj)var10000;
         }
      }
   }

   public final Context zztC() {
      return this.zzaSQ;
   }

   private static DynamiteModule zzc(Context var0, String var1, int var2) throws DynamiteModule.zzc {
      Log.i("DynamiteModule", (new StringBuilder(51 + String.valueOf(var1).length())).append("Selected remote version of ").append(var1).append(", version >= ").append(var2).toString());
      Class var4 = DynamiteModule.class;
      zzl var3;
      synchronized(DynamiteModule.class) {
         var3 = zzaSH;
      }

      if (var3 == null) {
         throw new DynamiteModule.zzc("DynamiteLoaderV2 was not cached.", (zza)null);
      } else {
         DynamiteModule.zza var8;
         if ((var8 = (DynamiteModule.zza)zzaSJ.get()) != null && var8.zzaSR != null) {
            Context var6;
            if ((var6 = zza(var0.getApplicationContext(), var1, var2, var8.zzaSR, var3)) == null) {
               throw new DynamiteModule.zzc("Failed to get module context", (zza)null);
            } else {
               return new DynamiteModule(var6);
            }
         } else {
            throw new DynamiteModule.zzc("No result cursor", (zza)null);
         }
      }
   }

   private static Context zza(Context var0, String var1, int var2, Cursor var3, zzl var4) {
      try {
         return (Context)zzn.zzE(var4.zza(zzn.zzw(var0), var1, var2, zzn.zzw(var3)));
      } catch (Exception var6) {
         String var10002 = String.valueOf(var6.toString());
         String var10001;
         if (var10002.length() != 0) {
            var10001 = "Failed to load DynamiteLoader: ".concat(var10002);
         } else {
            String var10003 = new String;
            var10001 = var10003;
            var10003.<init>("Failed to load DynamiteLoader: ");
         }

         Log.e("DynamiteModule", var10001);
         return null;
      }
   }

   private static void zza(ClassLoader var0) throws DynamiteModule.zzc {
      try {
         IBinder var2;
         IInterface var3;
         zzaSH = (zzl)((var2 = (IBinder)var0.loadClass("com.google.android.gms.dynamiteloader.DynamiteLoaderV2").getConstructor().newInstance()) == null ? null : ((var3 = var2.queryLocalInterface("com.google.android.gms.dynamite.IDynamiteLoaderV2")) instanceof zzl ? (zzl)var3 : new zzm(var2)));
      } catch (IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException var4) {
         throw new DynamiteModule.zzc("Failed to instantiate dynamite loader", var4, (zza)null);
      }
   }

   public final IBinder zzcV(String var1) throws DynamiteModule.zzc {
      try {
         return (IBinder)this.zzaSQ.getClassLoader().loadClass(var1).newInstance();
      } catch (InstantiationException | IllegalAccessException | ClassNotFoundException var3) {
         DynamiteModule.zzc var10000 = new DynamiteModule.zzc;
         String var10003 = String.valueOf(var1);
         String var10002;
         if (var10003.length() != 0) {
            var10002 = "Failed to instantiate module class: ".concat(var10003);
         } else {
            String var10004 = new String;
            var10002 = var10004;
            var10004.<init>("Failed to instantiate module class: ");
         }

         var10000.<init>(var10002, var3, (zza)null);
         throw var10000;
      }
   }

   private DynamiteModule(Context var1) {
      this.zzaSQ = (Context)zzbo.zzu(var1);
   }

   @DynamiteApi
   public static class DynamiteLoaderClassLoader {
      public static ClassLoader sClassLoader;
   }

   static class zza {
      public Cursor zzaSR;

      private zza() {
      }

      // $FF: synthetic method
      zza(zza var1) {
         this();
      }
   }

   public static class zzc extends Exception {
      private zzc(String var1) {
         super(var1);
      }

      private zzc(String var1, Throwable var2) {
         super(var1, var2);
      }

      // $FF: synthetic method
      zzc(String var1, zza var2) {
         this(var1);
      }

      // $FF: synthetic method
      zzc(String var1, Throwable var2, zza var3) {
         this(var1, var2);
      }
   }

   static class zzb implements zzh {
      private final int zzaSS;
      private final int zzaST;

      public zzb(int var1, int var2) {
         this.zzaSS = var1;
         this.zzaST = 0;
      }

      public final int zzb(Context var1, String var2, boolean var3) {
         return 0;
      }

      public final int zzE(Context var1, String var2) {
         return this.zzaSS;
      }
   }

   public interface zzd {
      zzi zza(Context var1, String var2, zzh var3) throws DynamiteModule.zzc;
   }
}
