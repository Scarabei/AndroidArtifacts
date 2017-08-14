package com.google.android.gms.internal;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.SparseArray;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzbo;
import java.io.FileDescriptor;
import java.io.PrintWriter;

public class zzbau extends zzbba {
   private final SparseArray zzaBB = new SparseArray();

   public static zzbau zza(zzbdr var0) {
      zzbdt var1;
      zzbau var2;
      return (var2 = (zzbau)(var1 = zzb(var0)).zza("AutoManageHelper", zzbau.class)) != null ? var2 : new zzbau(var1);
   }

   private zzbau(zzbdt var1) {
      super(var1);
      this.zzaEG.zza("AutoManageHelper", this);
   }

   public final void zza(int var1, GoogleApiClient var2, GoogleApiClient.OnConnectionFailedListener var3) {
      zzbo.zzb(var2, "GoogleApiClient instance cannot be null");
      zzbo.zza(this.zzaBB.indexOfKey(var1) < 0, (new StringBuilder(54)).append("Already managing a GoogleApiClient with id ").append(var1).toString());
      zzbbb var4 = (zzbbb)this.zzaBN.get();
      boolean var5 = this.mStarted;
      String var6 = String.valueOf(var4);
      Log.d("AutoManageHelper", (new StringBuilder(49 + String.valueOf(var6).length())).append("starting AutoManage for client ").append(var1).append(" ").append(var5).append(" ").append(var6).toString());
      zzbau.zza var7 = new zzbau.zza(var1, var2, var3);
      this.zzaBB.put(var1, var7);
      if (this.mStarted && var4 == null) {
         var6 = String.valueOf(var2);
         Log.d("AutoManageHelper", (new StringBuilder(11 + String.valueOf(var6).length())).append("connecting ").append(var6).toString());
         var2.connect();
      }

   }

   public final void zzal(int var1) {
      zzbau.zza var2 = (zzbau.zza)this.zzaBB.get(var1);
      this.zzaBB.remove(var1);
      if (var2 != null) {
         var2.zzaBD.unregisterConnectionFailedListener(var2);
         var2.zzaBD.disconnect();
      }

   }

   public final void onStart() {
      super.onStart();
      boolean var1 = this.mStarted;
      String var2 = String.valueOf(this.zzaBB);
      Log.d("AutoManageHelper", (new StringBuilder(14 + String.valueOf(var2).length())).append("onStart ").append(var1).append(" ").append(var2).toString());
      if (this.zzaBN.get() == null) {
         for(int var3 = 0; var3 < this.zzaBB.size(); ++var3) {
            zzbau.zza var4;
            if ((var4 = this.zzam(var3)) != null) {
               var4.zzaBD.connect();
            }
         }
      }

   }

   public final void onStop() {
      super.onStop();

      for(int var1 = 0; var1 < this.zzaBB.size(); ++var1) {
         zzbau.zza var2;
         if ((var2 = this.zzam(var1)) != null) {
            var2.zzaBD.disconnect();
         }
      }

   }

   public final void dump(String var1, FileDescriptor var2, PrintWriter var3, String[] var4) {
      for(int var5 = 0; var5 < this.zzaBB.size(); ++var5) {
         zzbau.zza var6;
         if ((var6 = this.zzam(var5)) != null) {
            var3.append(var1).append("GoogleApiClient #").print(var6.zzaBC);
            var3.println(":");
            var6.zzaBD.dump(String.valueOf(var1).concat("  "), var2, var3, var4);
         }
      }

   }

   protected final void zza(ConnectionResult var1, int var2) {
      Log.w("AutoManageHelper", "Unresolved error while connecting client. Stopping auto-manage.");
      if (var2 < 0) {
         Log.wtf("AutoManageHelper", "AutoManageLifecycleHelper received onErrorResolutionFailed callback but no failing client ID is set", new Exception());
      } else {
         zzbau.zza var3;
         if ((var3 = (zzbau.zza)this.zzaBB.get(var2)) != null) {
            this.zzal(var2);
            GoogleApiClient.OnConnectionFailedListener var4 = var3.zzaBE;
            if (var3.zzaBE != null) {
               var4.onConnectionFailed(var1);
            }
         }

      }
   }

   protected final void zzps() {
      for(int var1 = 0; var1 < this.zzaBB.size(); ++var1) {
         zzbau.zza var2;
         if ((var2 = this.zzam(var1)) != null) {
            var2.zzaBD.connect();
         }
      }

   }

   @Nullable
   private final zzbau.zza zzam(int var1) {
      return this.zzaBB.size() <= var1 ? null : (zzbau.zza)this.zzaBB.get(this.zzaBB.keyAt(var1));
   }

   class zza implements GoogleApiClient.OnConnectionFailedListener {
      public final int zzaBC;
      public final GoogleApiClient zzaBD;
      public final GoogleApiClient.OnConnectionFailedListener zzaBE;
      // $FF: synthetic field
      private zzbau zzaBF;

      public zza(int var2, GoogleApiClient var3, GoogleApiClient.OnConnectionFailedListener var4) {
         this.zzaBF = zzbau.this;
         super();
         this.zzaBC = var2;
         this.zzaBD = var3;
         this.zzaBE = var4;
         var3.registerConnectionFailedListener(this);
      }

      public final void onConnectionFailed(@NonNull ConnectionResult var1) {
         String var2 = String.valueOf(var1);
         Log.d("AutoManageHelper", (new StringBuilder(27 + String.valueOf(var2).length())).append("beginFailureResolution for ").append(var2).toString());
         this.zzaBF.zzb(var1, this.zzaBC);
      }
   }
}
