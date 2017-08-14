package com.google.android.gms.location.places;

import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.internal.zzbay;
import com.google.android.gms.internal.zzcdz;
import com.google.android.gms.location.places.internal.zzw;

public class zzm extends zzw {
   private static final String TAG = zzm.class.getSimpleName();
   private final zzm.zzd zzbjN;
   private final zzm.zza zzbjO;
   private final zzm.zze zzbjP;
   private final zzm.zzf zzbjQ;
   private final zzm.zzc zzbjR;

   public zzm(zzm.zzd var1) {
      this.zzbjN = var1;
      this.zzbjO = null;
      this.zzbjP = null;
      this.zzbjQ = null;
      this.zzbjR = null;
   }

   public zzm(zzm.zza var1) {
      this.zzbjN = null;
      this.zzbjO = var1;
      this.zzbjP = null;
      this.zzbjQ = null;
      this.zzbjR = null;
   }

   public zzm(zzm.zzf var1) {
      this.zzbjN = null;
      this.zzbjO = null;
      this.zzbjP = null;
      this.zzbjQ = var1;
      this.zzbjR = null;
   }

   public zzm(zzm.zzc var1) {
      this.zzbjN = null;
      this.zzbjO = null;
      this.zzbjP = null;
      this.zzbjQ = null;
      this.zzbjR = var1;
   }

   public final void zzO(DataHolder var1) throws RemoteException {
      zzbo.zza(this.zzbjN != null, "placeEstimator cannot be null");
      if (var1 == null) {
         if (Log.isLoggable(TAG, 6)) {
            Log.e(TAG, "onPlaceEstimated received null DataHolder", new Throwable());
         }

         this.zzbjN.zzr(Status.zzaBo);
      } else {
         Bundle var2;
         int var3 = (var2 = var1.zzqN()) == null ? 100 : PlaceLikelihoodBuffer.zzz(var2);
         PlaceLikelihoodBuffer var4 = new PlaceLikelihoodBuffer(var1, var3);
         this.zzbjN.setResult(var4);
      }
   }

   public final void zzP(DataHolder var1) throws RemoteException {
      if (var1 == null) {
         if (Log.isLoggable(TAG, 6)) {
            Log.e(TAG, "onAutocompletePrediction received null DataHolder", new Throwable());
         }

         this.zzbjO.zzr(Status.zzaBo);
      } else {
         this.zzbjO.setResult(new AutocompletePredictionBuffer(var1));
      }
   }

   public final void zzQ(DataHolder var1) throws RemoteException {
      if (var1 == null) {
         if (Log.isLoggable(TAG, 6)) {
            Log.e(TAG, "onPlaceUserDataFetched received null DataHolder", new Throwable());
         }

         null.zzr(Status.zzaBo);
      } else {
         null.setResult(new zzcdz(var1));
      }
   }

   public final void zzF(Status var1) throws RemoteException {
      this.zzbjQ.setResult(var1);
   }

   public final void zzR(DataHolder var1) throws RemoteException {
      PlaceBuffer var2 = new PlaceBuffer(var1);
      this.zzbjR.setResult(var2);
   }

   public abstract static class zzf extends zzm.zzb {
      public zzf(Api var1, GoogleApiClient var2) {
         super(var1, var2);
      }

      // $FF: synthetic method
      protected final Result zzb(Status var1) {
         return var1;
      }
   }

   public abstract static class zza extends zzm.zzb {
      public zza(Api var1, GoogleApiClient var2) {
         super(var1, var2);
      }

      // $FF: synthetic method
      protected final Result zzb(Status var1) {
         return new AutocompletePredictionBuffer(DataHolder.zzau(var1.getStatusCode()));
      }
   }

   /** @deprecated */
   @Deprecated
   public abstract static class zze extends zzm.zzb {
   }

   public abstract static class zzc extends zzm.zzb {
      public zzc(Api var1, GoogleApiClient var2) {
         super(var1, var2);
      }

      // $FF: synthetic method
      protected final Result zzb(Status var1) {
         return new PlaceBuffer(DataHolder.zzau(var1.getStatusCode()));
      }
   }

   public abstract static class zzd extends zzm.zzb {
      public zzd(Api var1, GoogleApiClient var2) {
         super(var1, var2);
      }

      // $FF: synthetic method
      protected final Result zzb(Status var1) {
         return new PlaceLikelihoodBuffer(DataHolder.zzau(var1.getStatusCode()), 100);
      }
   }

   public abstract static class zzb extends zzbay {
      public zzb(Api var1, GoogleApiClient var2) {
         super(var1, var2);
      }
   }
}
