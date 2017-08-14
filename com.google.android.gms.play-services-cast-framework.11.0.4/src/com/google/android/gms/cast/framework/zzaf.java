package com.google.android.gms.cast.framework;

import android.os.RemoteException;
import android.support.annotation.NonNull;
import com.google.android.gms.dynamic.IObjectWrapper;

public final class zzaf extends zzz {
   private final SessionManagerListener zzasx;
   private final Class zzasy;

   public zzaf(@NonNull SessionManagerListener var1, @NonNull Class var2) {
      this.zzasx = var1;
      this.zzasy = var2;
   }

   public final IObjectWrapper zznn() {
      return com.google.android.gms.dynamic.zzn.zzw(this.zzasx);
   }

   public final void zzz(@NonNull IObjectWrapper var1) throws RemoteException {
      Session var2 = (Session)com.google.android.gms.dynamic.zzn.zzE(var1);
      if (this.zzasy.isInstance(var2)) {
         this.zzasx.onSessionStarting((Session)this.zzasy.cast(var2));
      }

   }

   public final void zzc(@NonNull IObjectWrapper var1, String var2) throws RemoteException {
      Session var3 = (Session)com.google.android.gms.dynamic.zzn.zzE(var1);
      if (this.zzasy.isInstance(var3)) {
         this.zzasx.onSessionStarted((Session)this.zzasy.cast(var3), var2);
      }

   }

   public final void zze(@NonNull IObjectWrapper var1, int var2) throws RemoteException {
      Session var3 = (Session)com.google.android.gms.dynamic.zzn.zzE(var1);
      if (this.zzasy.isInstance(var3)) {
         this.zzasx.onSessionStartFailed((Session)this.zzasy.cast(var3), var2);
      }

   }

   public final void zzA(@NonNull IObjectWrapper var1) throws RemoteException {
      Session var2 = (Session)com.google.android.gms.dynamic.zzn.zzE(var1);
      if (this.zzasy.isInstance(var2)) {
         this.zzasx.onSessionEnding((Session)this.zzasy.cast(var2));
      }

   }

   public final void zzf(@NonNull IObjectWrapper var1, int var2) throws RemoteException {
      Session var3 = (Session)com.google.android.gms.dynamic.zzn.zzE(var1);
      if (this.zzasy.isInstance(var3)) {
         this.zzasx.onSessionEnded((Session)this.zzasy.cast(var3), var2);
      }

   }

   public final void zzd(@NonNull IObjectWrapper var1, String var2) throws RemoteException {
      Session var3 = (Session)com.google.android.gms.dynamic.zzn.zzE(var1);
      if (this.zzasy.isInstance(var3)) {
         this.zzasx.onSessionResuming((Session)this.zzasy.cast(var3), var2);
      }

   }

   public final void zza(@NonNull IObjectWrapper var1, boolean var2) throws RemoteException {
      Session var3 = (Session)com.google.android.gms.dynamic.zzn.zzE(var1);
      if (this.zzasy.isInstance(var3)) {
         this.zzasx.onSessionResumed((Session)this.zzasy.cast(var3), var2);
      }

   }

   public final void zzg(@NonNull IObjectWrapper var1, int var2) throws RemoteException {
      Session var3 = (Session)com.google.android.gms.dynamic.zzn.zzE(var1);
      if (this.zzasy.isInstance(var3)) {
         this.zzasx.onSessionResumeFailed((Session)this.zzasy.cast(var3), var2);
      }

   }

   public final void zzh(@NonNull IObjectWrapper var1, int var2) throws RemoteException {
      Session var3 = (Session)com.google.android.gms.dynamic.zzn.zzE(var1);
      if (this.zzasy.isInstance(var3)) {
         this.zzasx.onSessionSuspended((Session)this.zzasy.cast(var3), var2);
      }

   }
}
