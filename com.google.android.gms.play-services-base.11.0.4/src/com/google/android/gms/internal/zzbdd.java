package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.common.internal.zzbx;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public final class zzbdd implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, zzbbj {
   private final Queue zzaEn;
   private final Api.zze zzaCy;
   private final Api.zzb zzaEo;
   private final zzbat zzaAK;
   private final zzbbt zzaEp;
   private final Set zzaEq;
   private final Map zzaEr;
   private final int zzaEs;
   private final zzbej zzaEt;
   private boolean zzaDA;
   private ConnectionResult zzaEu;
   // $FF: synthetic field
   private zzbdb zzaEm;

   @WorkerThread
   public zzbdd(zzbdb var1, GoogleApi var2) {
      this.zzaEm = var1;
      super();
      this.zzaEn = new LinkedList();
      this.zzaEq = new HashSet();
      this.zzaEr = new HashMap();
      this.zzaEu = null;
      this.zzaCy = var2.zza(zzbdb.zza(var1).getLooper(), this);
      if (this.zzaCy instanceof zzbx) {
         zzbx var3 = (zzbx)this.zzaCy;
         this.zzaEo = null;
      } else {
         this.zzaEo = this.zzaCy;
      }

      this.zzaAK = var2.zzph();
      this.zzaEp = new zzbbt();
      this.zzaEs = var2.getInstanceId();
      if (this.zzaCy.zzmv()) {
         this.zzaEt = var2.zza(zzbdb.zzb(var1), zzbdb.zza(var1));
      } else {
         this.zzaEt = null;
      }
   }

   public final void onConnected(@Nullable Bundle var1) {
      if (Looper.myLooper() == zzbdb.zza(this.zzaEm).getLooper()) {
         this.zzqq();
      } else {
         zzbdb.zza(this.zzaEm).post(new zzbde(this));
      }
   }

   @WorkerThread
   private final void zzqq() {
      this.zzqt();
      this.zzi(ConnectionResult.zzazX);
      this.zzqv();
      Iterator var1 = this.zzaEr.values().iterator();

      while(var1.hasNext()) {
         zzbef var2 = (zzbef)var1.next();

         try {
            var2.zzaBu.zzb(this.zzaEo, new TaskCompletionSource());
         } catch (DeadObjectException var5) {
            this.onConnectionSuspended(1);
            this.zzaCy.disconnect();
            break;
         } catch (RemoteException var6) {
            ;
         }
      }

      zzbdd var3 = this;

      while(var3.zzaCy.isConnected() && !var3.zzaEn.isEmpty()) {
         zzbam var4 = (zzbam)var3.zzaEn.remove();
         var3.zzb(var4);
      }

      this.zzqw();
   }

   public final void onConnectionSuspended(int var1) {
      if (Looper.myLooper() == zzbdb.zza(this.zzaEm).getLooper()) {
         this.zzqr();
      } else {
         zzbdb.zza(this.zzaEm).post(new zzbdf(this));
      }
   }

   @WorkerThread
   private final void zzqr() {
      this.zzqt();
      this.zzaDA = true;
      this.zzaEp.zzpQ();
      zzbdb.zza(this.zzaEm).sendMessageDelayed(Message.obtain(zzbdb.zza(this.zzaEm), 9, this.zzaAK), zzbdb.zzc(this.zzaEm));
      zzbdb.zza(this.zzaEm).sendMessageDelayed(Message.obtain(zzbdb.zza(this.zzaEm), 11, this.zzaAK), zzbdb.zzd(this.zzaEm));
      zzbdb.zza((zzbdb)this.zzaEm, -1);
   }

   @WorkerThread
   public final void zzh(@NonNull ConnectionResult var1) {
      zzbo.zza(zzbdb.zza(this.zzaEm));
      this.zzaCy.disconnect();
      this.onConnectionFailed(var1);
   }

   public final void zza(ConnectionResult var1, Api var2, boolean var3) {
      if (Looper.myLooper() == zzbdb.zza(this.zzaEm).getLooper()) {
         this.onConnectionFailed(var1);
      } else {
         zzbdb.zza(this.zzaEm).post(new zzbdg(this, var1));
      }
   }

   @WorkerThread
   public final void onConnectionFailed(@NonNull ConnectionResult var1) {
      zzbo.zza(zzbdb.zza(this.zzaEm));
      if (this.zzaEt != null) {
         this.zzaEt.zzqI();
      }

      this.zzqt();
      zzbdb.zza((zzbdb)this.zzaEm, -1);
      this.zzi(var1);
      if (var1.getErrorCode() == 4) {
         this.zzt(zzbdb.zzqo());
      } else if (this.zzaEn.isEmpty()) {
         this.zzaEu = var1;
      } else {
         synchronized(zzbdb.zzqp()) {
            if (zzbdb.zze(this.zzaEm) != null && zzbdb.zzf(this.zzaEm).contains(this.zzaAK)) {
               zzbdb.zze(this.zzaEm).zzb(var1, this.zzaEs);
               return;
            }
         }

         if (!this.zzaEm.zzc(var1, this.zzaEs)) {
            if (var1.getErrorCode() == 18) {
               this.zzaDA = true;
            }

            if (this.zzaDA) {
               zzbdb.zza(this.zzaEm).sendMessageDelayed(Message.obtain(zzbdb.zza(this.zzaEm), 9, this.zzaAK), zzbdb.zzc(this.zzaEm));
               return;
            }

            String var2 = String.valueOf(this.zzaAK.zzpr());
            this.zzt(new Status(17, (new StringBuilder(38 + String.valueOf(var2).length())).append("API: ").append(var2).append(" is not available on this device.").toString()));
         }

      }
   }

   @WorkerThread
   public final void zza(zzbam var1) {
      zzbo.zza(zzbdb.zza(this.zzaEm));
      if (this.zzaCy.isConnected()) {
         this.zzb(var1);
         this.zzqw();
      } else {
         this.zzaEn.add(var1);
         if (this.zzaEu != null && this.zzaEu.hasResolution()) {
            this.onConnectionFailed(this.zzaEu);
         } else {
            this.connect();
         }
      }
   }

   @WorkerThread
   public final void signOut() {
      zzbo.zza(zzbdb.zza(this.zzaEm));
      this.zzt(zzbdb.zzaEc);
      this.zzaEp.zzpP();
      Iterator var1 = this.zzaEr.keySet().iterator();

      while(var1.hasNext()) {
         zzbdy var2 = (zzbdy)var1.next();
         this.zza((zzbam)(new zzbar(var2, new TaskCompletionSource())));
      }

      this.zzi(new ConnectionResult(4));
      this.zzaCy.disconnect();
   }

   public final Api.zze zzpJ() {
      return this.zzaCy;
   }

   public final Map zzqs() {
      return this.zzaEr;
   }

   @WorkerThread
   public final void zzqt() {
      zzbo.zza(zzbdb.zza(this.zzaEm));
      this.zzaEu = null;
   }

   @WorkerThread
   public final ConnectionResult zzqu() {
      zzbo.zza(zzbdb.zza(this.zzaEm));
      return this.zzaEu;
   }

   @WorkerThread
   private final void zzb(zzbam var1) {
      var1.zza(this.zzaEp, this.zzmv());

      try {
         var1.zza(this);
      } catch (DeadObjectException var2) {
         this.onConnectionSuspended(1);
         this.zzaCy.disconnect();
      }
   }

   @WorkerThread
   public final void zzt(Status var1) {
      zzbo.zza(zzbdb.zza(this.zzaEm));
      Iterator var2 = this.zzaEn.iterator();

      while(var2.hasNext()) {
         ((zzbam)var2.next()).zzp(var1);
      }

      this.zzaEn.clear();
   }

   @WorkerThread
   public final void resume() {
      zzbo.zza(zzbdb.zza(this.zzaEm));
      if (this.zzaDA) {
         this.connect();
      }

   }

   @WorkerThread
   private final void zzqv() {
      if (this.zzaDA) {
         zzbdb.zza(this.zzaEm).removeMessages(11, this.zzaAK);
         zzbdb.zza(this.zzaEm).removeMessages(9, this.zzaAK);
         this.zzaDA = false;
      }

   }

   @WorkerThread
   public final void zzqd() {
      zzbo.zza(zzbdb.zza(this.zzaEm));
      if (this.zzaDA) {
         this.zzqv();
         Status var1;
         if (zzbdb.zzg(this.zzaEm).isGooglePlayServicesAvailable(zzbdb.zzb(this.zzaEm)) == 18) {
            var1 = new Status(8, "Connection timed out while waiting for Google Play services update to complete.");
         } else {
            var1 = new Status(8, "API failed to connect while resuming due to an unknown error.");
         }

         this.zzt(var1);
         this.zzaCy.disconnect();
      }

   }

   private final void zzqw() {
      zzbdb.zza(this.zzaEm).removeMessages(12, this.zzaAK);
      zzbdb.zza(this.zzaEm).sendMessageDelayed(zzbdb.zza(this.zzaEm).obtainMessage(12, this.zzaAK), zzbdb.zzh(this.zzaEm));
   }

   @WorkerThread
   public final void zzqx() {
      zzbo.zza(zzbdb.zza(this.zzaEm));
      if (this.zzaCy.isConnected() && this.zzaEr.size() == 0) {
         if (this.zzaEp.zzpO()) {
            this.zzqw();
            return;
         }

         this.zzaCy.disconnect();
      }

   }

   @WorkerThread
   public final void connect() {
      zzbo.zza(zzbdb.zza(this.zzaEm));
      if (!this.zzaCy.isConnected() && !this.zzaCy.isConnecting()) {
         if (this.zzaCy.zzpe() && zzbdb.zzi(this.zzaEm) != 0) {
            zzbdb.zza(this.zzaEm, zzbdb.zzg(this.zzaEm).isGooglePlayServicesAvailable(zzbdb.zzb(this.zzaEm)));
            if (zzbdb.zzi(this.zzaEm) != 0) {
               ConnectionResult var2 = new ConnectionResult(zzbdb.zzi(this.zzaEm), (PendingIntent)null);
               this.onConnectionFailed(var2);
               return;
            }
         }

         zzbdh var1 = new zzbdh(this.zzaEm, this.zzaCy, this.zzaAK);
         if (this.zzaCy.zzmv()) {
            this.zzaEt.zza(var1);
         }

         this.zzaCy.zza(var1);
      }
   }

   @WorkerThread
   public final void zza(zzbav var1) {
      zzbo.zza(zzbdb.zza(this.zzaEm));
      this.zzaEq.add(var1);
   }

   @WorkerThread
   private final void zzi(ConnectionResult var1) {
      Iterator var2 = this.zzaEq.iterator();

      while(var2.hasNext()) {
         ((zzbav)var2.next()).zza(this.zzaAK, var1);
      }

      this.zzaEq.clear();
   }

   final boolean isConnected() {
      return this.zzaCy.isConnected();
   }

   public final boolean zzmv() {
      return this.zzaCy.zzmv();
   }

   public final int getInstanceId() {
      return this.zzaEs;
   }

   final zzctk zzqy() {
      return this.zzaEt == null ? null : this.zzaEt.zzqy();
   }

   // $FF: synthetic method
   static void zzc(zzbdd var0) {
      var0.zzqq();
   }

   // $FF: synthetic method
   static void zzd(zzbdd var0) {
      var0.zzqr();
   }
}
