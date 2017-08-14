package com.google.android.gms.internal;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Process;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.common.internal.zzq;
import com.google.android.gms.common.internal.zzz;
import com.google.android.gms.common.util.zzw;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.events.ChangeListener;
import com.google.android.gms.drive.events.zzj;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class zzbmh extends zzz {
   private final String zzake;
   private final Bundle zzaNQ;
   private final boolean zzaNR;
   private volatile DriveId zzaNS;
   private volatile DriveId zzaNT;
   private volatile boolean zzaNU = false;
   private ConnectionCallbacks zzaNV;
   private Map zzaNW = new HashMap();
   private Map zzaNX = new HashMap();
   private Map zzaNY = new HashMap();
   private Map zzaNZ = new HashMap();

   public zzbmh(Context var1, Looper var2, zzq var3, ConnectionCallbacks var4, OnConnectionFailedListener var5, Bundle var6) {
      super(var1, var2, 11, var3, var4, var5);
      this.zzake = var3.zzrq();
      this.zzaNV = var4;
      this.zzaNQ = var6;
      Intent var7;
      (var7 = new Intent("com.google.android.gms.drive.events.HANDLE_EVENT")).setPackage(var1.getPackageName());
      List var8;
      String var10;
      switch((var8 = var1.getPackageManager().queryIntentServices(var7, 0)).size()) {
      case 0:
         this.zzaNR = false;
         return;
      case 1:
         ServiceInfo var9;
         if (!(var9 = ((ResolveInfo)var8.get(0)).serviceInfo).exported) {
            var10 = String.valueOf(var9.name);
            throw new IllegalStateException((new StringBuilder(60 + String.valueOf(var10).length())).append("Drive event service ").append(var10).append(" must be exported in AndroidManifest.xml").toString());
         }

         this.zzaNR = true;
         return;
      default:
         var10 = String.valueOf(var7.getAction());
         throw new IllegalStateException((new StringBuilder(72 + String.valueOf(var10).length())).append("AndroidManifest.xml can only define one service that handles the ").append(var10).append(" action").toString());
      }
   }

   public final boolean zzmv() {
      return !this.getContext().getPackageName().equals(this.zzake) || !zzw.zzf(this.getContext(), Process.myUid());
   }

   public final boolean zzrg() {
      return true;
   }

   protected final String zzdc() {
      return "com.google.android.gms.drive.internal.IDriveService";
   }

   protected final Bundle zzmo() {
      String var1;
      zzbo.zzu(var1 = this.getContext().getPackageName());
      zzbo.zzae(!this.zzry().zzro().isEmpty());
      Bundle var2 = new Bundle();
      if (!var1.equals(this.zzake)) {
         var2.putString("proxy_package_name", this.zzake);
      }

      var2.putAll(this.zzaNQ);
      return var2;
   }

   protected final String zzdb() {
      return "com.google.android.gms.drive.ApiService.START";
   }

   public final DriveId zztf() {
      return this.zzaNS;
   }

   public final DriveId zztg() {
      return this.zzaNT;
   }

   public final boolean zzth() {
      return this.zzaNU;
   }

   public final void disconnect() {
      if (this.isConnected()) {
         try {
            ((zzbom)this.zzrf()).zza(new zzblm());
         } catch (RemoteException var10) {
            ;
         }
      }

      super.disconnect();
      Map var1 = this.zzaNW;
      synchronized(this.zzaNW) {
         this.zzaNW.clear();
      }

      var1 = this.zzaNX;
      synchronized(this.zzaNX) {
         this.zzaNX.clear();
      }

      var1 = this.zzaNY;
      synchronized(this.zzaNY) {
         this.zzaNY.clear();
      }

      var1 = this.zzaNZ;
      synchronized(this.zzaNZ) {
         this.zzaNZ.clear();
      }
   }

   protected final void zza(int var1, IBinder var2, Bundle var3, int var4) {
      if (var3 != null) {
         var3.setClassLoader(this.getClass().getClassLoader());
         this.zzaNS = (DriveId)var3.getParcelable("com.google.android.gms.drive.root_id");
         this.zzaNT = (DriveId)var3.getParcelable("com.google.android.gms.drive.appdata_id");
         this.zzaNU = true;
      }

      super.zza(var1, var2, var3, var4);
   }

   public final boolean zzti() {
      return this.zzaNR;
   }

   final PendingResult zza(GoogleApiClient var1, DriveId var2, ChangeListener var3) {
      zzbo.zzaf(zzj.zza(1, var2));
      zzbo.zzb(var3, "listener");
      zzbo.zza(this.isConnected(), "Client must be connected");
      Map var4 = this.zzaNW;
      synchronized(this.zzaNW) {
         Object var5;
         if ((var5 = (Map)this.zzaNW.get(var2)) == null) {
            var5 = new HashMap();
            this.zzaNW.put(var2, var5);
         }

         zzboc var6;
         if ((var6 = (zzboc)((Map)var5).get(var3)) == null) {
            var6 = new zzboc(this.getLooper(), this.getContext(), 1, var3);
            ((Map)var5).put(var3, var6);
         } else if (var6.zzaO(1)) {
            return new zzbme(var1, Status.zzaBm);
         }

         var6.zzaN(1);
         zzbkr var10 = new zzbkr(1, var2);
         return var1.zze(new zzbmi(this, var1, var10, var6));
      }
   }

   final PendingResult zzb(GoogleApiClient var1, DriveId var2, ChangeListener var3) {
      zzbo.zzaf(zzj.zza(1, var2));
      zzbo.zza(this.isConnected(), "Client must be connected");
      zzbo.zzb(var3, "listener");
      Map var4 = this.zzaNW;
      synchronized(this.zzaNW) {
         Map var5;
         if ((var5 = (Map)this.zzaNW.get(var2)) == null) {
            return new zzbme(var1, Status.zzaBm);
         } else {
            zzboc var6;
            if ((var6 = (zzboc)var5.remove(var3)) == null) {
               return new zzbme(var1, Status.zzaBm);
            } else {
               if (var5.isEmpty()) {
                  this.zzaNW.remove(var2);
               }

               zzbqk var10 = new zzbqk(var2, 1);
               return var1.zze(new zzbmj(this, var1, var10, var6));
            }
         }
      }
   }

   final PendingResult zza(GoogleApiClient var1, DriveId var2) {
      zzbkr var5 = new zzbkr(1, var2);
      zzbo.zzaf(zzj.zza(var5.zzaJo, var5.zzaLV));
      zzbo.zza(this.isConnected(), "Client must be connected");
      if (!this.zzaNR) {
         throw new IllegalStateException("Application must define an exported DriveEventService subclass in AndroidManifest.xml to add event subscriptions");
      } else {
         return var1.zze(new zzbmk(this, var1, var5));
      }
   }

   // $FF: synthetic method
   protected final IInterface zzd(IBinder var1) {
      if (var1 == null) {
         return null;
      } else {
         IInterface var3;
         return (IInterface)((var3 = var1.queryLocalInterface("com.google.android.gms.drive.internal.IDriveService")) instanceof zzbom ? (zzbom)var3 : new zzbon(var1));
      }
   }
}
