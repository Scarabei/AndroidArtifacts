package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.common.internal.zzbo;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class IndoorBuilding {
   private final com.google.android.gms.maps.model.internal.zzj zzbnw;

   public IndoorBuilding(com.google.android.gms.maps.model.internal.zzj var1) {
      this.zzbnw = (com.google.android.gms.maps.model.internal.zzj)zzbo.zzu(var1);
   }

   public final int getDefaultLevelIndex() {
      try {
         return this.zzbnw.getActiveLevelIndex();
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public final int getActiveLevelIndex() {
      try {
         return this.zzbnw.getActiveLevelIndex();
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public final List getLevels() {
      try {
         List var1 = this.zzbnw.getLevels();
         ArrayList var2 = new ArrayList(var1.size());
         Iterator var3 = var1.iterator();

         while(var3.hasNext()) {
            IBinder var4 = (IBinder)var3.next();
            var2.add(new IndoorLevel(com.google.android.gms.maps.model.internal.zzn.zzae(var4)));
         }

         return var2;
      } catch (RemoteException var5) {
         throw new RuntimeRemoteException(var5);
      }
   }

   public final boolean isUnderground() {
      try {
         return this.zzbnw.isUnderground();
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public final boolean equals(Object var1) {
      if (!(var1 instanceof IndoorBuilding)) {
         return false;
      } else {
         try {
            return this.zzbnw.zzb(((IndoorBuilding)var1).zzbnw);
         } catch (RemoteException var3) {
            throw new RuntimeRemoteException(var3);
         }
      }
   }

   public final int hashCode() {
      try {
         return this.zzbnw.hashCodeRemote();
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }
}
