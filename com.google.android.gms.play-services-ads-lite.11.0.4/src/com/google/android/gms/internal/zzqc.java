package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.widget.FrameLayout;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.dynamic.zzp;
import com.google.android.gms.dynamic.zzq;

@zzzn
public final class zzqc extends zzp {
   public zzqc() {
      super("com.google.android.gms.ads.NativeAdViewDelegateCreatorImpl");
   }

   public final zzow zzb(Context var1, FrameLayout var2, FrameLayout var3) {
      try {
         IObjectWrapper var4 = zzn.zzw(var1);
         IObjectWrapper var5 = zzn.zzw(var2);
         IObjectWrapper var6 = zzn.zzw(var3);
         IBinder var7;
         if ((var7 = ((zzoz)this.zzaS(var1)).zza(var4, var5, var6, 11020000)) == null) {
            return null;
         } else {
            IInterface var8;
            return (zzow)((var8 = var7.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.INativeAdViewDelegate")) instanceof zzow ? (zzow)var8 : new zzoy(var7));
         }
      } catch (zzq | RemoteException var9) {
         zzajc.zzc("Could not create remote NativeAdViewDelegate.", var9);
         return null;
      }
   }

   // $FF: synthetic method
   protected final Object zzb(IBinder var1) {
      if (var1 == null) {
         return null;
      } else {
         IInterface var3;
         return (var3 = var1.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.INativeAdViewDelegateCreator")) instanceof zzoz ? (zzoz)var3 : new zzpa(var1);
      }
   }
}
