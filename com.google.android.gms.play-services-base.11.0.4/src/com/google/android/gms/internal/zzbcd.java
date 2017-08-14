package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.zze;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.zzal;
import com.google.android.gms.common.internal.zzbr;
import com.google.android.gms.common.internal.zzq;
import com.google.android.gms.common.internal.zzr;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Lock;

public final class zzbcd implements zzbcw {
   private final zzbcx zzaCZ;
   private final Lock zzaCv;
   private final Context mContext;
   private final zze zzaCF;
   private ConnectionResult zzaCO;
   private int zzaDc;
   private int zzaDd = 0;
   private int zzaDe;
   private final Bundle zzaDf = new Bundle();
   private final Set zzaDg = new HashSet();
   private zzctk zzaDh;
   private boolean zzaDi;
   private boolean zzaDj;
   private boolean zzaDk;
   private zzal zzaDl;
   private boolean zzaDm;
   private boolean zzaDn;
   private final zzq zzaCA;
   private final Map zzaCD;
   private final Api.zza zzaBe;
   private ArrayList zzaDo = new ArrayList();

   public zzbcd(zzbcx var1, zzq var2, Map var3, zze var4, Api.zza var5, Lock var6, Context var7) {
      this.zzaCZ = var1;
      this.zzaCA = var2;
      this.zzaCD = var3;
      this.zzaCF = var4;
      this.zzaBe = var5;
      this.zzaCv = var6;
      this.mContext = var7;
   }

   public final void begin() {
      this.zzaCZ.zzaDU.clear();
      this.zzaDj = false;
      this.zzaCO = null;
      this.zzaDd = 0;
      this.zzaDi = true;
      this.zzaDk = false;
      this.zzaDm = false;
      boolean var1 = false;
      HashMap var2 = new HashMap();

      Api var4;
      Api.zze var5;
      boolean var6;
      for(Iterator var3 = this.zzaCD.keySet().iterator(); var3.hasNext(); var2.put(var5, new zzbcf(this, var4, var6))) {
         var4 = (Api)var3.next();
         var5 = (Api.zze)this.zzaCZ.zzaDF.get(var4.zzpd());
         var1 |= var4.zzpb().getPriority() == 1;
         var6 = ((Boolean)this.zzaCD.get(var4)).booleanValue();
         if (var5.zzmv()) {
            this.zzaDj = true;
            if (var6) {
               this.zzaDg.add(var4.zzpd());
            } else {
               this.zzaDi = false;
            }
         }
      }

      if (var1) {
         this.zzaDj = false;
      }

      if (this.zzaDj) {
         this.zzaCA.zzc(System.identityHashCode(this.zzaCZ.zzaCl));
         zzbcm var7 = new zzbcm(this, (zzbce)null);
         this.zzaDh = (zzctk)this.zzaBe.zza(this.mContext, this.zzaCZ.zzaCl.getLooper(), this.zzaCA, this.zzaCA.zzrt(), var7, var7);
      }

      this.zzaDe = this.zzaCZ.zzaDF.size();
      this.zzaDo.add(zzbda.zzqj().submit(new zzbcg(this, var2)));
   }

   private final boolean zzpW() {
      --this.zzaDe;
      if (this.zzaDe > 0) {
         return false;
      } else if (this.zzaDe < 0) {
         Log.w("GoogleApiClientConnecting", this.zzaCZ.zzaCl.zzqg());
         Log.wtf("GoogleApiClientConnecting", "GoogleApiClient received too many callbacks for the given step. Clients may be in an unexpected state; GoogleApiClient will now disconnect.", new Exception());
         this.zze(new ConnectionResult(8, (PendingIntent)null));
         return false;
      } else if (this.zzaCO != null) {
         this.zzaCZ.zzaDX = this.zzaDc;
         this.zze(this.zzaCO);
         return false;
      } else {
         return true;
      }
   }

   private final void zza(zzctx var1) {
      if (this.zzan(0)) {
         ConnectionResult var2;
         if ((var2 = var1.zzpz()).isSuccess()) {
            zzbr var3;
            ConnectionResult var4;
            if (!(var4 = (var3 = var1.zzAx()).zzpz()).isSuccess()) {
               String var5 = String.valueOf(var4);
               Log.wtf("GoogleApiClientConnecting", (new StringBuilder(48 + String.valueOf(var5).length())).append("Sign-in succeeded with resolve account failure: ").append(var5).toString(), new Exception());
               this.zze(var4);
            } else {
               this.zzaDk = true;
               this.zzaDl = var3.zzrH();
               this.zzaDm = var3.zzrI();
               this.zzaDn = var3.zzrJ();
               this.zzpX();
            }
         } else if (this.zzd(var2)) {
            this.zzpZ();
            this.zzpX();
         } else {
            this.zze(var2);
         }
      }
   }

   private final void zzpX() {
      if (this.zzaDe == 0) {
         if (!this.zzaDj || this.zzaDk) {
            zzbcd var1 = this;
            ArrayList var2 = new ArrayList();
            this.zzaDd = 1;
            this.zzaDe = this.zzaCZ.zzaDF.size();
            Iterator var3 = this.zzaCZ.zzaDF.keySet().iterator();

            while(var3.hasNext()) {
               Api.zzc var4 = (Api.zzc)var3.next();
               if (var1.zzaCZ.zzaDU.containsKey(var4)) {
                  if (var1.zzpW()) {
                     var1.zzpY();
                  }
               } else {
                  var2.add((Api.zze)var1.zzaCZ.zzaDF.get(var4));
               }
            }

            if (!var2.isEmpty()) {
               var1.zzaDo.add(zzbda.zzqj().submit(new zzbcj(var1, var2)));
            }
         }

      }
   }

   public final void onConnected(Bundle var1) {
      if (this.zzan(1)) {
         if (var1 != null) {
            this.zzaDf.putAll(var1);
         }

         if (this.zzpW()) {
            this.zzpY();
         }

      }
   }

   public final void zza(ConnectionResult var1, Api var2, boolean var3) {
      if (this.zzan(1)) {
         this.zzb(var1, var2, var3);
         if (this.zzpW()) {
            this.zzpY();
         }

      }
   }

   private final void zzpY() {
      this.zzaCZ.zzqi();
      zzbda.zzqj().execute(new zzbce(this));
      if (this.zzaDh != null) {
         if (this.zzaDm) {
            this.zzaDh.zza(this.zzaDl, this.zzaDn);
         }

         this.zzad(false);
      }

      Iterator var1 = this.zzaCZ.zzaDU.keySet().iterator();

      while(var1.hasNext()) {
         Api.zzc var2 = (Api.zzc)var1.next();
         ((Api.zze)this.zzaCZ.zzaDF.get(var2)).disconnect();
      }

      Bundle var3 = this.zzaDf.isEmpty() ? null : this.zzaDf;
      this.zzaCZ.zzaDY.zzm(var3);
   }

   public final zzbay zzd(zzbay var1) {
      this.zzaCZ.zzaCl.zzaCJ.add(var1);
      return var1;
   }

   public final zzbay zze(zzbay var1) {
      throw new IllegalStateException("GoogleApiClient is not connected yet.");
   }

   public final void connect() {
   }

   public final boolean disconnect() {
      this.zzqa();
      this.zzad(true);
      this.zzaCZ.zzg((ConnectionResult)null);
      return true;
   }

   public final void onConnectionSuspended(int var1) {
      this.zze(new ConnectionResult(8, (PendingIntent)null));
   }

   private final void zzb(ConnectionResult var1, Api var2, boolean var3) {
      int var4 = var2.zzpb().getPriority();
      if ((!var3 || (var1.hasResolution() ? true : this.zzaCF.zzak(var1.getErrorCode()) != null)) && (this.zzaCO == null || var4 < this.zzaDc)) {
         this.zzaCO = var1;
         this.zzaDc = var4;
      }

      this.zzaCZ.zzaDU.put(var2.zzpd(), var1);
   }

   private final void zzpZ() {
      this.zzaDj = false;
      this.zzaCZ.zzaCl.zzaDG = Collections.emptySet();
      Iterator var1 = this.zzaDg.iterator();

      while(var1.hasNext()) {
         Api.zzc var2 = (Api.zzc)var1.next();
         if (!this.zzaCZ.zzaDU.containsKey(var2)) {
            this.zzaCZ.zzaDU.put(var2, new ConnectionResult(17, (PendingIntent)null));
         }
      }

   }

   private final boolean zzd(ConnectionResult var1) {
      return this.zzaDi && !var1.hasResolution();
   }

   private final void zze(ConnectionResult var1) {
      this.zzqa();
      this.zzad(!var1.hasResolution());
      this.zzaCZ.zzg(var1);
      this.zzaCZ.zzaDY.zzc(var1);
   }

   private final void zzad(boolean var1) {
      if (this.zzaDh != null) {
         if (this.zzaDh.isConnected() && var1) {
            this.zzaDh.zzAq();
         }

         this.zzaDh.disconnect();
         this.zzaDl = null;
      }

   }

   private final void zzqa() {
      ArrayList var1;
      int var2 = (var1 = (ArrayList)this.zzaDo).size();
      int var3 = 0;

      while(var3 < var2) {
         Object var10000 = var1.get(var3);
         ++var3;
         ((Future)var10000).cancel(true);
      }

      this.zzaDo.clear();
   }

   private final Set zzqb() {
      if (this.zzaCA == null) {
         return Collections.emptySet();
      } else {
         HashSet var1 = new HashSet(this.zzaCA.zzrn());
         Map var2;
         Iterator var3 = (var2 = this.zzaCA.zzrp()).keySet().iterator();

         while(var3.hasNext()) {
            Api var4 = (Api)var3.next();
            if (!this.zzaCZ.zzaDU.containsKey(var4.zzpd())) {
               var1.addAll(((zzr)var2.get(var4)).zzame);
            }
         }

         return var1;
      }
   }

   private final boolean zzan(int var1) {
      if (this.zzaDd != var1) {
         Log.w("GoogleApiClientConnecting", this.zzaCZ.zzaCl.zzqg());
         String var2 = String.valueOf(this);
         Log.w("GoogleApiClientConnecting", (new StringBuilder(23 + String.valueOf(var2).length())).append("Unexpected callback in ").append(var2).toString());
         int var4 = this.zzaDe;
         Log.w("GoogleApiClientConnecting", (new StringBuilder(33)).append("mRemainingConnections=").append(var4).toString());
         var2 = String.valueOf(zzao(this.zzaDd));
         String var3 = String.valueOf(zzao(var1));
         Log.wtf("GoogleApiClientConnecting", (new StringBuilder(70 + String.valueOf(var2).length() + String.valueOf(var3).length())).append("GoogleApiClient connecting is in step ").append(var2).append(" but received callback for step ").append(var3).toString(), new Exception());
         this.zze(new ConnectionResult(8, (PendingIntent)null));
         return false;
      } else {
         return true;
      }
   }

   private static String zzao(int var0) {
      switch(var0) {
      case 0:
         return "STEP_SERVICE_BINDINGS_AND_SIGN_IN";
      case 1:
         return "STEP_GETTING_REMOTE_SERVICE";
      default:
         return "UNKNOWN";
      }
   }

   // $FF: synthetic method
   static Context zza(zzbcd var0) {
      return var0.mContext;
   }

   // $FF: synthetic method
   static zze zzb(zzbcd var0) {
      return var0.zzaCF;
   }

   // $FF: synthetic method
   static Lock zzc(zzbcd var0) {
      return var0.zzaCv;
   }

   // $FF: synthetic method
   static zzbcx zzd(zzbcd var0) {
      return var0.zzaCZ;
   }

   // $FF: synthetic method
   static void zza(zzbcd var0, ConnectionResult var1) {
      var0.zze(var1);
   }

   // $FF: synthetic method
   static boolean zze(zzbcd var0) {
      return var0.zzaDj;
   }

   // $FF: synthetic method
   static zzctk zzf(zzbcd var0) {
      return var0.zzaDh;
   }

   // $FF: synthetic method
   static Set zzg(zzbcd var0) {
      return var0.zzqb();
   }

   // $FF: synthetic method
   static zzal zzh(zzbcd var0) {
      return var0.zzaDl;
   }

   // $FF: synthetic method
   static boolean zzb(zzbcd var0, ConnectionResult var1) {
      return var0.zzd(var1);
   }

   // $FF: synthetic method
   static void zzi(zzbcd var0) {
      var0.zzpZ();
   }

   // $FF: synthetic method
   static void zzj(zzbcd var0) {
      var0.zzpX();
   }

   // $FF: synthetic method
   static boolean zza(zzbcd var0, int var1) {
      return var0.zzan(0);
   }

   // $FF: synthetic method
   static void zza(zzbcd var0, ConnectionResult var1, Api var2, boolean var3) {
      var0.zzb(var1, var2, var3);
   }

   // $FF: synthetic method
   static boolean zzk(zzbcd var0) {
      return var0.zzpW();
   }

   // $FF: synthetic method
   static void zza(zzbcd var0, zzctx var1) {
      var0.zza(var1);
   }
}
