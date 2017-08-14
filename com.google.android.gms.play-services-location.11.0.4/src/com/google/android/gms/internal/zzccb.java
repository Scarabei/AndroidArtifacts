package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.location.Location;
import android.os.Looper;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.location.FusedLocationProviderApi;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

public final class zzccb implements FusedLocationProviderApi {
   public final Location getLastLocation(GoogleApiClient var1) {
      zzcdj var2 = LocationServices.zzg(var1);

      try {
         return var2.getLastLocation();
      } catch (Exception var3) {
         return null;
      }
   }

   public final LocationAvailability getLocationAvailability(GoogleApiClient var1) {
      zzcdj var2 = LocationServices.zzg(var1);

      try {
         return var2.zzvQ();
      } catch (Exception var3) {
         return null;
      }
   }

   public final PendingResult requestLocationUpdates(GoogleApiClient var1, LocationRequest var2, LocationListener var3) {
      zzbo.zzb(Looper.myLooper(), "Calling thread must be a prepared Looper thread.");
      return var1.zze(new zzccc(this, var1, var2, var3));
   }

   public final PendingResult requestLocationUpdates(GoogleApiClient var1, LocationRequest var2, LocationListener var3, Looper var4) {
      return var1.zze(new zzcch(this, var1, var2, var3, var4));
   }

   public final PendingResult requestLocationUpdates(GoogleApiClient var1, LocationRequest var2, LocationCallback var3, Looper var4) {
      return var1.zze(new zzcci(this, var1, var2, var3, var4));
   }

   public final PendingResult requestLocationUpdates(GoogleApiClient var1, LocationRequest var2, PendingIntent var3) {
      return var1.zze(new zzccj(this, var1, var2, var3));
   }

   public final PendingResult removeLocationUpdates(GoogleApiClient var1, LocationListener var2) {
      return var1.zze(new zzcck(this, var1, var2));
   }

   public final PendingResult removeLocationUpdates(GoogleApiClient var1, PendingIntent var2) {
      return var1.zze(new zzccl(this, var1, var2));
   }

   public final PendingResult removeLocationUpdates(GoogleApiClient var1, LocationCallback var2) {
      return var1.zze(new zzccd(this, var1, var2));
   }

   public final PendingResult setMockMode(GoogleApiClient var1, boolean var2) {
      return var1.zze(new zzcce(this, var1, var2));
   }

   public final PendingResult setMockLocation(GoogleApiClient var1, Location var2) {
      return var1.zze(new zzccf(this, var1, var2));
   }

   public final PendingResult flushLocations(GoogleApiClient var1) {
      return var1.zze(new zzccg(this, var1));
   }
}
