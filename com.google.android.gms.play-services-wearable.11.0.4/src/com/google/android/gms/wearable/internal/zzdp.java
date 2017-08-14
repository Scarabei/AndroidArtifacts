package com.google.android.gms.wearable.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.zzbaz;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

final class zzdp {
   private final Map zzaWU = new HashMap();

   public final void zzam(IBinder var1) {
      Map var2 = this.zzaWU;
      synchronized(this.zzaWU) {
         IInterface var13;
         Object var3 = var1 == null ? null : ((var13 = var1.queryLocalInterface("com.google.android.gms.wearable.internal.IWearableService")) instanceof zzdn ? (zzdn)var13 : new zzdo(var1));
         zzfp var4 = new zzfp();
         Iterator var5 = this.zzaWU.entrySet().iterator();

         while(var5.hasNext()) {
            Entry var6;
            zzga var7 = (zzga)(var6 = (Entry)var5.next()).getValue();

            String var9;
            try {
               ((zzdn)var3).zza(var4, (zzd)(new zzd(var7)));
               if (Log.isLoggable("WearableClient", 2)) {
                  String var8 = String.valueOf(var6.getKey());
                  var9 = String.valueOf(var7);
                  Log.d("WearableClient", (new StringBuilder(27 + String.valueOf(var8).length() + String.valueOf(var9).length())).append("onPostInitHandler: added: ").append(var8).append("/").append(var9).toString());
               }
            } catch (RemoteException var14) {
               var9 = String.valueOf(var6.getKey());
               String var10 = String.valueOf(var7);
               Log.d("WearableClient", (new StringBuilder(32 + String.valueOf(var9).length() + String.valueOf(var10).length())).append("onPostInitHandler: Didn't add: ").append(var9).append("/").append(var10).toString());
            }
         }

      }
   }

   public final void zza(zzfw var1, zzbaz var2, Object var3, zzga var4) throws RemoteException {
      Map var5 = this.zzaWU;
      synchronized(this.zzaWU) {
         if (this.zzaWU.get(var3) != null) {
            var2.setResult(new Status(4001));
         } else {
            this.zzaWU.put(var3, var4);

            try {
               ((zzdn)var1.zzrf()).zza(new zzdq(this.zzaWU, var3, var2), (zzd)(new zzd(var4)));
            } catch (RemoteException var8) {
               this.zzaWU.remove(var3);
               throw var8;
            }

         }
      }
   }

   public final void zza(zzfw var1, zzbaz var2, Object var3) throws RemoteException {
      Map var4 = this.zzaWU;
      synchronized(this.zzaWU) {
         zzga var5;
         if ((var5 = (zzga)this.zzaWU.remove(var3)) == null) {
            var2.setResult(new Status(4002));
         } else {
            var5.clear();
            ((zzdn)var1.zzrf()).zza(new zzdr(this.zzaWU, var3, var2), (zzeo)(new zzeo(var5)));
         }
      }
   }
}
