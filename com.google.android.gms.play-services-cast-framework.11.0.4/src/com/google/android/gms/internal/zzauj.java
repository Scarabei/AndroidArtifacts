package com.google.android.gms.internal;

import android.app.Service;
import android.content.Context;
import android.os.AsyncTask;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.cast.framework.CastOptions;
import com.google.android.gms.cast.framework.zzac;
import com.google.android.gms.cast.framework.zzi;
import com.google.android.gms.cast.framework.zzk;
import com.google.android.gms.cast.framework.zzm;
import com.google.android.gms.cast.framework.zzs;
import com.google.android.gms.cast.framework.zzu;
import com.google.android.gms.cast.framework.media.CastMediaOptions;
import com.google.android.gms.cast.framework.media.zzd;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.dynamite.DynamiteModule.zzc;
import java.util.Map;

public final class zzauj {
   private static final zzayo zzarK = new zzayo("CastDynamiteModule");

   public static zzk zza(Context var0, CastOptions var1, zzauo var2, Map var3) {
      zzaum var4 = zzan(var0);

      try {
         IObjectWrapper var5 = zzn.zzw(var0.getApplicationContext());
         return var4.zza(var5, var1, var2, var3);
      } catch (RemoteException var6) {
         zzarK.zzb(var6, "Unable to call %s on %s.", new Object[]{"newCastContextImpl", zzaum.class.getSimpleName()});
         return null;
      }
   }

   public static zzu zza(Context var0, String var1, String var2, zzac var3) {
      zzaum var4 = zzan(var0);

      try {
         return var4.zza(var1, var2, var3);
      } catch (RemoteException var6) {
         zzarK.zzb(var6, "Unable to call %s on %s.", new Object[]{"newSessionImpl", zzaum.class.getSimpleName()});
         return null;
      }
   }

   public static zzm zza(Context var0, CastOptions var1, IObjectWrapper var2, zzi var3) {
      zzaum var4 = zzan(var0);

      try {
         return var4.zza(var1, var2, var3);
      } catch (RemoteException var6) {
         zzarK.zzb(var6, "Unable to call %s on %s.", new Object[]{"newCastSessionImpl", zzaum.class.getSimpleName()});
         return null;
      }
   }

   public static zzd zza(Service var0, IObjectWrapper var1, IObjectWrapper var2, CastMediaOptions var3) {
      zzaum var4 = zzan(var0.getApplicationContext());

      try {
         IObjectWrapper var5 = zzn.zzw(var0);
         return var4.zza(var5, var1, var2, var3);
      } catch (RemoteException var6) {
         zzarK.zzb(var6, "Unable to call %s on %s.", new Object[]{"newMediaNotificationServiceImpl", zzaum.class.getSimpleName()});
         return null;
      }
   }

   public static zzs zza(Service var0, IObjectWrapper var1, IObjectWrapper var2) {
      zzaum var3 = zzan(var0.getApplicationContext());
      IObjectWrapper var4 = zzn.zzw(var0);

      try {
         return var3.zza(var4, var1, var2);
      } catch (RemoteException var6) {
         zzarK.zzb(var6, "Unable to call %s on %s.", new Object[]{"newReconnectionServiceImpl", zzaum.class.getSimpleName()});
         return null;
      }
   }

   public static zzavj zza(Context var0, AsyncTask var1, zzavl var2, int var3, int var4, boolean var5, long var6, int var8, int var9, int var10) {
      zzaum var11 = zzan(var0.getApplicationContext());

      try {
         return var11.zza(zzn.zzw(var1), var2, var3, var4, var5, 2097152L, 5, 333, 10000);
      } catch (RemoteException var13) {
         zzarK.zzb(var13, "Unable to call %s on %s.", new Object[]{"newFetchBitmapTaskImpl", zzaum.class.getSimpleName()});
         return null;
      }
   }

   private static zzaum zzan(Context var0) {
      try {
         IBinder var2;
         if ((var2 = DynamiteModule.zza(var0, DynamiteModule.zzaSL, "com.google.android.gms.cast.framework.dynamite").zzcV("com.google.android.gms.cast.framework.internal.CastDynamiteModuleImpl")) == null) {
            return null;
         } else {
            IInterface var3;
            return (zzaum)((var3 = var2.queryLocalInterface("com.google.android.gms.cast.framework.internal.ICastDynamiteModule")) instanceof zzaum ? (zzaum)var3 : new zzaun(var2));
         }
      } catch (zzc var4) {
         throw new RuntimeException(var4);
      }
   }
}
