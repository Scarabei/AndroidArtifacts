package com.google.android.gms.internal;

import android.app.Activity;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.dynamic.zzp;
import com.google.android.gms.dynamic.zzq;

@zzzn
public final class zzww extends zzp {
   public zzww() {
      super("com.google.android.gms.ads.AdOverlayCreatorImpl");
   }

   public final zzwx zze(Activity var1) {
      try {
         IObjectWrapper var2 = zzn.zzw(var1);
         IBinder var3;
         if ((var3 = ((zzxa)this.zzaS(var1)).zzp(var2)) == null) {
            return null;
         } else {
            IInterface var4;
            return (zzwx)((var4 = var3.queryLocalInterface("com.google.android.gms.ads.internal.overlay.client.IAdOverlay")) instanceof zzwx ? (zzwx)var4 : new zzwz(var3));
         }
      } catch (RemoteException var5) {
         zzajc.zzc("Could not create remote AdOverlay.", var5);
         return null;
      } catch (zzq var6) {
         zzajc.zzc("Could not create remote AdOverlay.", var6);
         return null;
      }
   }

   // $FF: synthetic method
   protected final Object zzb(IBinder var1) {
      if (var1 == null) {
         return null;
      } else {
         IInterface var3;
         return (var3 = var1.queryLocalInterface("com.google.android.gms.ads.internal.overlay.client.IAdOverlayCreator")) instanceof zzxa ? (zzxa)var3 : new zzxb(var1);
      }
   }
}
