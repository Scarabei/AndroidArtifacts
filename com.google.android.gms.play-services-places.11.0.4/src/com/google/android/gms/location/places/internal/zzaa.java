package com.google.android.gms.location.places.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.location.places.PlaceFilter;
import com.google.android.gms.location.places.PlaceReport;
import com.google.android.gms.location.places.PlacesOptions;
import java.util.Locale;

public final class zzaa extends com.google.android.gms.common.internal.zzz {
   private final zzat zzbko;
   private final Locale zzbjV;

   private zzaa(Context var1, Looper var2, com.google.android.gms.common.internal.zzq var3, ConnectionCallbacks var4, OnConnectionFailedListener var5, String var6, PlacesOptions var7) {
      super(var1, var2, 67, var3, var4, var5);
      this.zzbjV = Locale.getDefault();
      String var8 = null;
      if (var3.getAccount() != null) {
         var8 = var3.getAccount().name;
      }

      this.zzbko = new zzat(var6, this.zzbjV, var8, (String)null, 0);
   }

   protected final String zzdb() {
      return "com.google.android.gms.location.places.PlaceDetectionApi";
   }

   protected final String zzdc() {
      return "com.google.android.gms.location.places.internal.IGooglePlaceDetectionService";
   }

   public final void zza(com.google.android.gms.location.places.zzm var1, PlaceFilter var2) throws RemoteException {
      if (var2 == null) {
         var2 = PlaceFilter.zzvS();
      }

      ((zzp)this.zzrf()).zza((PlaceFilter)var2, this.zzbko, var1);
   }

   public final void zza(com.google.android.gms.location.places.zzm var1, PlaceReport var2) throws RemoteException {
      zzbo.zzu(var2);
      ((zzp)this.zzrf()).zza((PlaceReport)var2, this.zzbko, var1);
   }

   // $FF: synthetic method
   protected final IInterface zzd(IBinder var1) {
      if (var1 == null) {
         return null;
      } else {
         IInterface var3;
         return (IInterface)((var3 = var1.queryLocalInterface("com.google.android.gms.location.places.internal.IGooglePlaceDetectionService")) instanceof zzp ? (zzp)var3 : new zzq(var1));
      }
   }

   // $FF: synthetic method
   zzaa(Context var1, Looper var2, com.google.android.gms.common.internal.zzq var3, ConnectionCallbacks var4, OnConnectionFailedListener var5, String var6, PlacesOptions var7, zzab var8) {
      this(var1, var2, var3, var4, var5, var6, var7);
   }
}
