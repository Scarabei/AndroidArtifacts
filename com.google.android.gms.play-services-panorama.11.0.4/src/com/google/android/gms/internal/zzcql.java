package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.common.zzc;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzq;
import com.google.android.gms.common.internal.zzz;
import com.google.android.gms.panorama.zza;

public final class zzcql extends zzz {
   public zzcql(Context var1, Looper var2, zzq var3, ConnectionCallbacks var4, OnConnectionFailedListener var5) {
      super(var1, var2, 3, var3, var4, var5);
   }

   protected final String zzdc() {
      return "com.google.android.gms.panorama.internal.IPanoramaService";
   }

   protected final String zzdb() {
      return "com.google.android.gms.panorama.service.START";
   }

   public final zzc[] zzrd() {
      return new zzc[]{zza.zzbzQ};
   }

   // $FF: synthetic method
   public final IInterface zzd(IBinder var1) {
      if (var1 == null) {
         return null;
      } else {
         IInterface var3;
         return (IInterface)((var3 = var1.queryLocalInterface("com.google.android.gms.panorama.internal.IPanoramaService")) instanceof zzcqc ? (zzcqc)var3 : new zzcqd(var1));
      }
   }
}
