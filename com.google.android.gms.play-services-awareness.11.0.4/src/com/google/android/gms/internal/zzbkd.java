package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;

public final class zzbkd extends zzbki {
   private final zzbkg zzaLE;
   private zzbaz zzaLF;
   private zzbaz zzaLG;
   private zzbaz zzaLH;
   private zzbaz zzaLI;
   private zzbaz zzaLJ;
   private zzbaz zzaLK;
   private zzbaz zzaLL;

   public static zzbkd zza(zzbaz var0, zzbkg var1) {
      return new zzbkd(var0, (zzbaz)null, (zzbaz)null, (zzbaz)null, (zzbaz)null, (zzbaz)null, (zzbaz)null, (zzbkg)null);
   }

   public static zzbkd zzd(zzbaz var0) {
      return new zzbkd((zzbaz)null, (zzbaz)null, (zzbaz)null, (zzbaz)null, var0, (zzbaz)null, (zzbaz)null, (zzbkg)null);
   }

   public static zzbkd zze(zzbaz var0) {
      return new zzbkd((zzbaz)null, (zzbaz)null, (zzbaz)null, (zzbaz)null, (zzbaz)null, var0, (zzbaz)null, (zzbkg)null);
   }

   private zzbkd(zzbaz var1, zzbaz var2, zzbaz var3, zzbaz var4, zzbaz var5, zzbaz var6, zzbaz var7, zzbkg var8) {
      this.zzaLF = var1;
      this.zzaLG = null;
      this.zzaLH = null;
      this.zzaLI = null;
      this.zzaLJ = var5;
      this.zzaLK = var6;
      this.zzaLL = null;
      this.zzaLE = null;
   }

   public final void zzd(Status var1) throws RemoteException {
      if (this.zzaLF == null) {
         zzeq.zzd("ContextManagerPendingResult", "Unexpected callback to onStatusResult.");
      } else {
         this.zzaLF.setResult(var1);
         this.zzaLF = null;
      }
   }

   public final void zza(Status var1, DataHolder var2, DataHolder var3) throws RemoteException {
      zzeq.zzd("ContextManagerPendingResult", "Unexpected callback to onReadResult.");
   }

   public final void zza(Status var1, zzbkl var2) throws RemoteException {
      zzeq.zzd("ContextManagerPendingResult", "Unexpected callback to onWriteBatchResult");
   }

   public final void zza(Status var1, DataHolder var2) throws RemoteException {
      zzeq.zzd("ContextManagerPendingResult", "Unexpected callback to onStateResult");
   }

   public final void zza(Status var1, zzati var2) throws RemoteException {
      if (this.zzaLJ == null) {
         zzeq.zzd("ContextManagerPendingResult", "Unexpected callback to onSnapshotResult");
      } else {
         this.zzaLJ.setResult(new zzbke(this, var1, var2));
         this.zzaLJ = null;
      }
   }

   public final void zza(Status var1, zzbjf var2) {
      if (this.zzaLK == null) {
         zzeq.zzd("ContextManagerPendingResult", "Unexpected callback to onFenceQueryResult");
      } else {
         this.zzaLK.setResult(new zzbkf(this, var2, var1));
         this.zzaLK = null;
      }
   }

   public final void zza(Status var1, zzbjd var2) {
      zzeq.zzd("ContextManagerPendingResult", "Unexpected callback to onFenceEvaluateResult");
   }
}
