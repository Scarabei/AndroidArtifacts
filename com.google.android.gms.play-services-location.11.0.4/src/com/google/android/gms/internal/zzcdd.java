package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.content.ContentProviderClient;
import android.content.Context;
import android.location.Location;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.zzj;
import com.google.android.gms.location.zzm;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public final class zzcdd {
   private final zzcdt zzbiB;
   private final Context mContext;
   private ContentProviderClient zzbiM = null;
   private boolean zzbiN = false;
   private final Map zzaWU = new HashMap();
   private final Map zzbiO = new HashMap();

   public zzcdd(Context var1, zzcdt var2) {
      this.mContext = var1;
      this.zzbiB = var2;
   }

   public final Location getLastLocation() {
      this.zzbiB.zzre();

      try {
         return ((zzccz)this.zzbiB.zzrf()).zzdv(this.mContext.getPackageName());
      } catch (RemoteException var2) {
         throw new IllegalStateException(var2);
      }
   }

   public final LocationAvailability zzvQ() {
      this.zzbiB.zzre();

      try {
         return ((zzccz)this.zzbiB.zzrf()).zzdw(this.mContext.getPackageName());
      } catch (RemoteException var2) {
         throw new IllegalStateException(var2);
      }
   }

   public final void zza(LocationRequest var1, zzbdw var2, zzccu var3) throws RemoteException {
      this.zzbiB.zzre();
      zzcdh var4 = this.zzf(var2);
      zzccz var10000 = (zzccz)this.zzbiB.zzrf();
      zzcdn var5 = zzcdn.zza(var1);
      var10000.zza(new zzcdp(1, var5, var4.asBinder(), (PendingIntent)null, (IBinder)null, var3 != null ? var3.asBinder() : null));
   }

   public final void zza(zzcdn var1, zzbdw var2, zzccu var3) throws RemoteException {
      this.zzbiB.zzre();
      zzcde var4 = this.zzg(var2);
      ((zzccz)this.zzbiB.zzrf()).zza(new zzcdp(1, var1, (IBinder)null, (PendingIntent)null, var4.asBinder(), var3 != null ? var3.asBinder() : null));
   }

   private final zzcdh zzf(zzbdw var1) {
      Map var3 = this.zzaWU;
      synchronized(this.zzaWU) {
         zzcdh var2;
         if ((var2 = (zzcdh)this.zzaWU.get(var1.zzqG())) == null) {
            var2 = new zzcdh(var1);
         }

         this.zzaWU.put(var1.zzqG(), var2);
         return var2;
      }
   }

   private final zzcde zzg(zzbdw var1) {
      Map var3 = this.zzbiO;
      synchronized(this.zzbiO) {
         zzcde var2;
         if ((var2 = (zzcde)this.zzbiO.get(var1.zzqG())) == null) {
            var2 = new zzcde(var1);
         }

         this.zzbiO.put(var1.zzqG(), var2);
         return var2;
      }
   }

   public final void zza(LocationRequest var1, PendingIntent var2, zzccu var3) throws RemoteException {
      this.zzbiB.zzre();
      zzccz var10000 = (zzccz)this.zzbiB.zzrf();
      zzcdn var4 = zzcdn.zza(var1);
      var10000.zza(new zzcdp(1, var4, (IBinder)null, var2, (IBinder)null, var3 != null ? var3.asBinder() : null));
   }

   public final void zza(zzbdy var1, zzccu var2) throws RemoteException {
      this.zzbiB.zzre();
      zzbo.zzb(var1, "Invalid null listener key");
      Map var3 = this.zzaWU;
      synchronized(this.zzaWU) {
         zzcdh var4;
         if ((var4 = (zzcdh)this.zzaWU.remove(var1)) != null) {
            var4.release();
            ((zzccz)this.zzbiB.zzrf()).zza(zzcdp.zza((zzm)var4, var2));
         }

      }
   }

   public final void zzb(zzbdy var1, zzccu var2) throws RemoteException {
      this.zzbiB.zzre();
      zzbo.zzb(var1, "Invalid null listener key");
      Map var3 = this.zzbiO;
      synchronized(this.zzbiO) {
         zzcde var4;
         if ((var4 = (zzcde)this.zzbiO.remove(var1)) != null) {
            var4.release();
            ((zzccz)this.zzbiB.zzrf()).zza(zzcdp.zza((zzj)var4, var2));
         }

      }
   }

   public final void zza(PendingIntent var1, zzccu var2) throws RemoteException {
      this.zzbiB.zzre();
      ((zzccz)this.zzbiB.zzrf()).zza(new zzcdp(2, (zzcdn)null, (IBinder)null, var1, (IBinder)null, var2 != null ? var2.asBinder() : null));
   }

   public final void zzai(boolean var1) throws RemoteException {
      this.zzbiB.zzre();
      ((zzccz)this.zzbiB.zzrf()).zzai(var1);
      this.zzbiN = var1;
   }

   public final void zzc(Location var1) throws RemoteException {
      this.zzbiB.zzre();
      ((zzccz)this.zzbiB.zzrf()).zzc(var1);
   }

   public final void zza(zzccu var1) throws RemoteException {
      this.zzbiB.zzre();
      ((zzccz)this.zzbiB.zzrf()).zza(var1);
   }

   public final void removeAllListeners() {
      try {
         Map var1 = this.zzaWU;
         Iterator var2;
         synchronized(this.zzaWU) {
            var2 = this.zzaWU.values().iterator();

            while(true) {
               if (!var2.hasNext()) {
                  this.zzaWU.clear();
                  break;
               }

               zzcdh var3;
               if ((var3 = (zzcdh)var2.next()) != null) {
                  ((zzccz)this.zzbiB.zzrf()).zza(zzcdp.zza((zzm)var3, (zzccu)null));
               }
            }
         }

         var1 = this.zzbiO;
         synchronized(this.zzbiO) {
            var2 = this.zzbiO.values().iterator();

            while(var2.hasNext()) {
               zzcde var9;
               if ((var9 = (zzcde)var2.next()) != null) {
                  ((zzccz)this.zzbiB.zzrf()).zza(zzcdp.zza((zzj)var9, (zzccu)null));
               }
            }

            this.zzbiO.clear();
         }
      } catch (RemoteException var8) {
         throw new IllegalStateException(var8);
      }
   }

   public final void zzvR() {
      if (this.zzbiN) {
         try {
            this.zzai(false);
         } catch (RemoteException var2) {
            throw new IllegalStateException(var2);
         }
      }
   }
}
