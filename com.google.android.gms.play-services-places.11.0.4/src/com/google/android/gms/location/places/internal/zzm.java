package com.google.android.gms.location.places.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.location.places.AddPlaceRequest;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.PlacesOptions;
import com.google.android.gms.maps.model.LatLngBounds;
import java.util.List;
import java.util.Locale;

public final class zzm extends com.google.android.gms.common.internal.zzz {
   private final zzat zzbko;

   private zzm(Context var1, Looper var2, com.google.android.gms.common.internal.zzq var3, ConnectionCallbacks var4, OnConnectionFailedListener var5, String var6, PlacesOptions var7) {
      super(var1, var2, 65, var3, var4, var5);
      Locale var8 = Locale.getDefault();
      String var9 = null;
      if (var3.getAccount() != null) {
         var9 = var3.getAccount().name;
      }

      this.zzbko = new zzat(var6, var8, var9, (String)null, 0);
   }

   protected final String zzdb() {
      return "com.google.android.gms.location.places.GeoDataApi";
   }

   protected final String zzdc() {
      return "com.google.android.gms.location.places.internal.IGooglePlacesService";
   }

   public final void zza(com.google.android.gms.location.places.zzm var1, String var2, @Nullable LatLngBounds var3, @Nullable AutocompleteFilter var4) throws RemoteException {
      zzbo.zzb(var1, "callback == null");
      if (var2 == null) {
         var2 = "";
      }

      if (var4 == null) {
         var4 = (new AutocompleteFilter.Builder()).build();
      }

      ((zzr)this.zzrf()).zza(var2, var3, var4, this.zzbko, var1);
   }

   public final void zza(com.google.android.gms.location.places.zzm var1, List var2) throws RemoteException {
      ((zzr)this.zzrf()).zza((List)var2, this.zzbko, (zzv)var1);
   }

   public final void zza(com.google.android.gms.location.places.zzm var1, AddPlaceRequest var2) throws RemoteException {
      zzbo.zzb(var2, "userAddedPlace == null");
      ((zzr)this.zzrf()).zza((AddPlaceRequest)var2, this.zzbko, (zzv)var1);
   }

   public final void zza(com.google.android.gms.location.places.zzd var1, String var2) throws RemoteException {
      zzbo.zzb(var2, "placeId cannot be null");
      ((zzr)this.zzrf()).zza((String)var2, this.zzbko, (zzt)var1);
   }

   public final void zza(com.google.android.gms.location.places.zzd var1, String var2, int var3, int var4, int var5) throws RemoteException {
      zzbo.zzb(var2, "fifeUrl cannot be null");
      zzbo.zzb(var3 > 0, "width should be > 0");
      zzbo.zzb(var3 > 0, "height should be > 0");
      ((zzr)this.zzrf()).zza(var2, var3, var4, var5, this.zzbko, var1);
   }

   // $FF: synthetic method
   protected final IInterface zzd(IBinder var1) {
      if (var1 == null) {
         return null;
      } else {
         IInterface var3;
         return (IInterface)((var3 = var1.queryLocalInterface("com.google.android.gms.location.places.internal.IGooglePlacesService")) instanceof zzr ? (zzr)var3 : new zzs(var1));
      }
   }

   // $FF: synthetic method
   zzm(Context var1, Looper var2, com.google.android.gms.common.internal.zzq var3, ConnectionCallbacks var4, OnConnectionFailedListener var5, String var6, PlacesOptions var7, zzn var8) {
      this(var1, var2, var3, var4, var5, var6, var7);
   }
}
