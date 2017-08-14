package com.google.android.gms.maps.model;

import android.os.RemoteException;
import com.google.android.gms.common.internal.zzbo;

public final class IndoorLevel {
   private final com.google.android.gms.maps.model.internal.zzm zzbnx;

   public IndoorLevel(com.google.android.gms.maps.model.internal.zzm var1) {
      this.zzbnx = (com.google.android.gms.maps.model.internal.zzm)zzbo.zzu(var1);
   }

   public final String getName() {
      try {
         return this.zzbnx.getName();
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public final String getShortName() {
      try {
         return this.zzbnx.getShortName();
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public final void activate() {
      try {
         this.zzbnx.activate();
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public final boolean equals(Object var1) {
      if (!(var1 instanceof IndoorLevel)) {
         return false;
      } else {
         try {
            return this.zzbnx.zza(((IndoorLevel)var1).zzbnx);
         } catch (RemoteException var3) {
            throw new RuntimeRemoteException(var3);
         }
      }
   }

   public final int hashCode() {
      try {
         return this.zzbnx.hashCodeRemote();
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }
}
