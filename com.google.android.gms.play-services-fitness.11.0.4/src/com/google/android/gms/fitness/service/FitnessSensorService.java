package com.google.android.gms.fitness.service;

import android.annotation.TargetApi;
import android.app.AppOpsManager;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.CallSuper;
import android.util.Log;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.util.zzq;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.result.DataSourcesResult;
import com.google.android.gms.internal.zzbvy;
import com.google.android.gms.internal.zzbxg;
import com.google.android.gms.internal.zzbzo;
import com.google.android.gms.internal.zzbzq;
import com.google.android.gms.internal.zzbzt;
import java.util.List;

public abstract class FitnessSensorService extends Service {
   public static final String SERVICE_INTERFACE = "com.google.android.gms.fitness.service.FitnessSensorService";
   private FitnessSensorService.zza zzaXC;

   @CallSuper
   public void onCreate() {
      super.onCreate();
      this.zzaXC = new FitnessSensorService.zza(this, (zza)null);
   }

   @CallSuper
   public IBinder onBind(Intent var1) {
      if ("com.google.android.gms.fitness.service.FitnessSensorService".equals(var1.getAction())) {
         if (Log.isLoggable("FitnessSensorService", 3)) {
            String var2 = String.valueOf(var1);
            String var3 = String.valueOf(this.getClass().getName());
            Log.d("FitnessSensorService", (new StringBuilder(20 + String.valueOf(var2).length() + String.valueOf(var3).length())).append("Intent ").append(var2).append(" received by ").append(var3).toString());
         }

         return this.zzaXC.asBinder();
      } else {
         return null;
      }
   }

   public abstract List onFindDataSources(List var1);

   public abstract boolean onRegister(FitnessSensorServiceRequest var1);

   public abstract boolean onUnregister(DataSource var1);

   @TargetApi(19)
   protected final void zztZ() throws SecurityException {
      int var1 = Binder.getCallingUid();
      if (zzq.zzsc()) {
         ((AppOpsManager)this.getSystemService("appops")).checkPackage(var1, "com.google.android.gms");
      } else {
         String[] var2;
         if ((var2 = this.getPackageManager().getPackagesForUid(var1)) != null) {
            String[] var3 = var2;
            int var4 = var2.length;

            for(int var5 = 0; var5 < var4; ++var5) {
               if (var3[var5].equals("com.google.android.gms")) {
                  return;
               }
            }
         }

         throw new SecurityException("Unauthorized caller");
      }
   }

   static class zza extends zzbzt {
      private final FitnessSensorService zzaXD;

      private zza(FitnessSensorService var1) {
         this.zzaXD = var1;
      }

      public final void zza(zzbzo var1, zzbvy var2) throws RemoteException {
         this.zzaXD.zztZ();
         List var3 = this.zzaXD.onFindDataSources(var1.getDataTypes());
         var2.zza(new DataSourcesResult(var3, Status.zzaBm));
      }

      public final void zza(FitnessSensorServiceRequest var1, zzbxg var2) throws RemoteException {
         this.zzaXD.zztZ();
         if (this.zzaXD.onRegister(var1)) {
            var2.zzu(Status.zzaBm);
         } else {
            var2.zzu(new Status(13));
         }
      }

      public final void zza(zzbzq var1, zzbxg var2) throws RemoteException {
         this.zzaXD.zztZ();
         if (this.zzaXD.onUnregister(var1.getDataSource())) {
            var2.zzu(Status.zzaBm);
         } else {
            var2.zzu(new Status(13));
         }
      }

      // $FF: synthetic method
      zza(FitnessSensorService var1, zza var2) {
         this(var1);
      }
   }
}
