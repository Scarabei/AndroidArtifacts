package com.google.android.gms.tagmanager;

import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.dynamite.DynamiteModule.zzc;
import com.google.android.gms.measurement.AppMeasurement;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

final class zzbf {
   private static volatile DynamiteModule zzbEA;
   private static volatile zzcq zzbEB;
   private static final Map zzbEC = new HashMap();
   private static final Map zzbED = new HashMap();

   static IBinder zzbn(Context var0) {
      try {
         zzct var2 = zzcu.asInterface(zzbq(var0).zzcV("com.google.android.gms.tagmanager.TagManagerServiceProviderImpl"));

         try {
            return var2.getService(zzn.zzw(var0), zzbr(var0), new zzbj()).asBinder();
         } catch (RemoteException var4) {
            throw new IllegalStateException(var4);
         }
      } catch (zzc var5) {
         throw new RuntimeException(var5);
      }
   }

   static void zzbo(Context var0) {
      zzcq var1 = zzbp(var0);
      Class var2 = zzbf.class;
      synchronized(zzbf.class) {
         try {
            var1.initialize(zzn.zzw(var0), zzbr(var0), new zzbj());
         } catch (RemoteException var5) {
            throw new IllegalStateException(var5);
         }

      }
   }

   static void zza(Intent var0, Context var1) {
      zzcq var2 = zzbp(var1);
      Class var3 = zzbf.class;
      synchronized(zzbf.class) {
         try {
            var2.previewIntent(var0, zzn.zzw(var1), zzn.zzw(zzbEA.zztC()), zzbr(var1), new zzbj());
         } catch (RemoteException var6) {
            throw new IllegalStateException(var6);
         }

      }
   }

   private static zzcq zzbp(Context var0) {
      zzcq var1 = zzbEB;
      if (zzbEB == null) {
         Class var2 = zzbf.class;
         synchronized(zzbf.class) {
            var1 = zzbEB;
            if (zzbEB == null) {
               try {
                  var1 = zzbEB = zzcr.asInterface(zzbq(var0).zzcV("com.google.android.gms.tagmanager.TagManagerApiImpl"));
               } catch (zzc var5) {
                  throw new RuntimeException(var5);
               }
            }
         }
      }

      return var1;
   }

   private static DynamiteModule zzbq(Context var0) throws zzc {
      DynamiteModule var1 = zzbEA;
      if (zzbEA == null) {
         Class var2 = zzbf.class;
         synchronized(zzbf.class) {
            var1 = zzbEA;
            if (zzbEA == null) {
               var1 = zzbEA = DynamiteModule.zza(var0, DynamiteModule.zzaSO, "com.google.android.gms.tagmanager");
            }
         }
      }

      return var1;
   }

   private static zzcn zzbr(Context var0) {
      AppMeasurement var1 = AppMeasurement.getInstance(var0);
      return new zzbg(var1);
   }

   private static Object zzb(String var0, Class var1) {
      Object var2 = null;

      try {
         Class var3;
         Class[] var4 = (var3 = Class.forName(var0)).getInterfaces();
         boolean var5 = false;
         Class[] var6 = var4;
         int var7 = var4.length;

         for(int var8 = 0; var8 < var7; ++var8) {
            if (var6[var8].equals(var1)) {
               var5 = true;
               break;
            }
         }

         if (!var5) {
            String var15 = String.valueOf(var1.getCanonicalName());
            Log.e("GoogleTagManagerAPI", (new StringBuilder(30 + String.valueOf(var0).length() + String.valueOf(var15).length())).append(var0).append(" doesn't implement ").append(var15).append(" interface.").toString());
         } else {
            try {
               var2 = var3.getConstructor().newInstance();
            } catch (NoSuchMethodException var9) {
               Log.e("GoogleTagManagerAPI", String.valueOf(var0).concat(" doesn't have a valid no-arg constructor"));
            } catch (SecurityException var10) {
               Log.e("GoogleTagManagerAPI", String.valueOf(var0).concat(" doesn't have an accessible no-arg constructor"));
            } catch (InvocationTargetException var11) {
               Log.e("GoogleTagManagerAPI", String.valueOf(var0).concat(" construction threw an exception."));
            } catch (InstantiationException var12) {
               Log.e("GoogleTagManagerAPI", String.valueOf(var0).concat(" is an abstract class."));
            } catch (IllegalAccessException var13) {
               Log.e("GoogleTagManagerAPI", String.valueOf(var0).concat(" doesn't have an accessible no-arg constructor"));
            }
         }
      } catch (ClassNotFoundException var14) {
         Log.e("GoogleTagManagerAPI", String.valueOf(var0).concat(" can't be found in the application."));
      }

      return var2;
   }

   // $FF: synthetic method
   static Map zzBg() {
      return zzbEC;
   }

   // $FF: synthetic method
   static Object zzc(String var0, Class var1) {
      return zzb(var0, var1);
   }

   // $FF: synthetic method
   static Map zzym() {
      return zzbED;
   }
}
