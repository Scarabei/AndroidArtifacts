package com.google.android.gms.internal;

import android.content.ComponentName;
import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.analytics.zzl;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.common.stats.zza;
import java.util.Collections;
import java.util.List;

public final class zzamn extends zzamh {
   private final zzamp zzagg;
   private zzany zzagh;
   private final zzanm zzagi;
   private zzaoo zzagj;

   protected zzamn(zzamj var1) {
      super(var1);
      this.zzagj = new zzaoo(var1.zzkq());
      this.zzagg = new zzamp(this);
      this.zzagi = new zzamo(this, var1);
   }

   protected final void zzjD() {
   }

   public final boolean isConnected() {
      zzl.zzjC();
      this.zzkD();
      return this.zzagh != null;
   }

   public final boolean zzb(zzanx var1) {
      zzbo.zzu(var1);
      zzl.zzjC();
      this.zzkD();
      zzany var2 = this.zzagh;
      if (this.zzagh == null) {
         return false;
      } else {
         String var3;
         if (var1.zzlI()) {
            var3 = zzank.zzlu();
         } else {
            var3 = zzank.zzlv();
         }

         List var4 = Collections.emptyList();

         try {
            var2.zza(var1.zzdV(), var1.zzlG(), var3, var4);
            this.zzkP();
            return true;
         } catch (RemoteException var5) {
            this.zzbo("Failed to send hits to AnalyticsService");
            return false;
         }
      }
   }

   public final boolean zzkO() {
      zzl.zzjC();
      this.zzkD();
      zzany var1 = this.zzagh;
      if (this.zzagh == null) {
         return false;
      } else {
         try {
            var1.zzkk();
            this.zzkP();
            return true;
         } catch (RemoteException var2) {
            this.zzbo("Failed to clear hits from AnalyticsService");
            return false;
         }
      }
   }

   private final void zzkP() {
      this.zzagj.start();
      this.zzagi.zzs(((Long)zzans.zzahO.get()).longValue());
   }

   public final boolean connect() {
      zzl.zzjC();
      this.zzkD();
      if (this.zzagh != null) {
         return true;
      } else {
         zzany var1;
         if ((var1 = this.zzagg.zzkR()) != null) {
            this.zzagh = var1;
            this.zzkP();
            return true;
         } else {
            return false;
         }
      }
   }

   private final void zza(zzany var1) {
      zzl.zzjC();
      this.zzagh = var1;
      this.zzkP();
      this.zzkv().onServiceConnected();
   }

   public final void disconnect() {
      zzl.zzjC();
      this.zzkD();

      try {
         zza.zzrU();
         Context var10000 = this.getContext();
         zzamp var2 = this.zzagg;
         var10000.unbindService(var2);
      } catch (IllegalStateException var3) {
         ;
      } catch (IllegalArgumentException var4) {
         ;
      }

      if (this.zzagh != null) {
         this.zzagh = null;
         this.zzkv().zzkn();
      }

   }

   private final void onServiceDisconnected(ComponentName var1) {
      zzl.zzjC();
      if (this.zzagh != null) {
         this.zzagh = null;
         this.zza((String)"Disconnected from device AnalyticsService", (Object)var1);
         this.zzkv().zzkn();
      }

   }

   private final void zzkQ() {
      zzl.zzjC();
      if (this.isConnected()) {
         this.zzbo("Inactivity, disconnecting from device AnalyticsService");
         this.disconnect();
      }
   }

   // $FF: synthetic method
   static zzamp zza(zzamn var0) {
      return var0.zzagg;
   }

   // $FF: synthetic method
   static void zza(zzamn var0, zzany var1) {
      var0.zza(var1);
   }

   // $FF: synthetic method
   static void zza(zzamn var0, ComponentName var1) {
      var0.onServiceDisconnected(var1);
   }

   // $FF: synthetic method
   static void zzb(zzamn var0) {
      var0.zzkQ();
   }
}
