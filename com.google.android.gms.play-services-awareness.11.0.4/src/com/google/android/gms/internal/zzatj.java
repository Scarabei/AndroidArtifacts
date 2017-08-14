package com.google.android.gms.internal;

import com.google.android.gms.awareness.SnapshotApi;
import com.google.android.gms.awareness.state.BeaconState;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.internal.zzbo;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public final class zzatj implements SnapshotApi {
   public final PendingResult getBeaconState(GoogleApiClient var1, Collection var2) {
      zzbo.zzb(var2, "beaconTypes cannot be null");
      zzbo.zzb(var2.size() > 0, "beaconTypes must not be empty");
      ArrayList var4 = new ArrayList();
      Iterator var5 = var2.iterator();

      while(var5.hasNext()) {
         BeaconState.TypeFilter var6 = (BeaconState.TypeFilter)var5.next();
         var4.add((zzasv)var6);
      }

      return this.zza(var1, var4);
   }

   public final PendingResult getBeaconState(GoogleApiClient var1, BeaconState.TypeFilter... var2) {
      zzbo.zzb(var2, "beaconTypes cannot be null");
      zzbo.zzb(var2.length > 0, "beaconTypes must not be empty");
      ArrayList var4 = new ArrayList();
      BeaconState.TypeFilter[] var5;
      int var6 = (var5 = var2).length;

      for(int var7 = 0; var7 < var6; ++var7) {
         BeaconState.TypeFilter var8 = var5[var7];
         var4.add((zzasv)var8);
      }

      return this.zza(var1, var4);
   }

   public final PendingResult getTimeIntervals(GoogleApiClient var1) {
      zzbay var2 = var1.zzd(zza(var1, 10008));
      return new zzatk(this, var2);
   }

   public final PendingResult getDetectedActivity(GoogleApiClient var1) {
      zzbay var2 = var1.zzd(zza(var1, 10002));
      return new zzatm(this, var2);
   }

   public final PendingResult getHeadphoneState(GoogleApiClient var1) {
      zzbay var2 = var1.zzd(zza(var1, 10004));
      return new zzato(this, var2);
   }

   public final PendingResult getLocation(GoogleApiClient var1) {
      zzbay var2 = var1.zzd(zza(var1, 10005));
      return new zzatq(this, var2);
   }

   public final PendingResult getPlaces(GoogleApiClient var1) {
      zzbay var2 = var1.zzd(zza(var1, 10006));
      return new zzats(this, var2);
   }

   public final PendingResult getWeather(GoogleApiClient var1) {
      zzbay var2 = var1.zzd(zza(var1, 10007));
      return new zzatu(this, var2);
   }

   private final PendingResult zza(GoogleApiClient var1, ArrayList var2) {
      zzbay var3 = var1.zzd(new zzatz(var1, 10003, var2));
      return new zzatw(this, var3);
   }

   private static zzbjx zza(GoogleApiClient var0, int var1) {
      return new zzaty(var0, var1);
   }
}
