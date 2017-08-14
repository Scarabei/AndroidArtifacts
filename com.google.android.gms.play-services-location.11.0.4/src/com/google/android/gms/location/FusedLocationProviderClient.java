package com.google.android.gms.location;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.location.Location;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.internal.zzbh;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.internal.zzbas;
import com.google.android.gms.internal.zzbdw;
import com.google.android.gms.internal.zzbdy;
import com.google.android.gms.internal.zzbea;
import com.google.android.gms.internal.zzber;
import com.google.android.gms.internal.zzcco;
import com.google.android.gms.internal.zzccv;
import com.google.android.gms.internal.zzcdn;
import com.google.android.gms.internal.zzceb;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

public class FusedLocationProviderClient extends GoogleApi {
   public FusedLocationProviderClient(@NonNull Context var1) {
      super(var1, LocationServices.API, (ApiOptions)null, new zzbas());
   }

   public FusedLocationProviderClient(@NonNull Activity var1) {
      super(var1, LocationServices.API, (ApiOptions)null, new zzbas());
   }

   @RequiresPermission(
      anyOf = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"}
   )
   public Task getLastLocation() {
      return this.zza(new zze(this));
   }

   @RequiresPermission(
      anyOf = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"}
   )
   public Task getLocationAvailability() {
      return this.zza(new zzf(this));
   }

   @RequiresPermission(
      anyOf = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"}
   )
   public Task requestLocationUpdates(LocationRequest var1, LocationCallback var2, @Nullable Looper var3) {
      zzcdn var4 = zzcdn.zza(var1);
      zzbdw var9 = zzbea.zzb(var2, zzceb.zzb(var3), LocationCallback.class.getSimpleName());
      zzg var10 = new zzg(this, var9, var4, var9);
      zzh var11 = new zzh(this, var9.zzqG());
      zzbo.zzu(var10);
      zzbo.zzu(var11);
      zzbo.zzb(var10.zzqG(), "Listener has already been released.");
      zzbo.zzb(var11.zzqG(), "Listener has already been released.");
      zzbo.zzb(var10.zzqG().equals(var11.zzqG()), "Listener registration and unregistration methods must be constructed with the same ListenerHolder.");
      return super.zzaAN.zza(this, var10, var11);
   }

   @RequiresPermission(
      anyOf = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"}
   )
   public Task requestLocationUpdates(LocationRequest var1, PendingIntent var2) {
      return zzbh.zzb(LocationServices.FusedLocationApi.requestLocationUpdates(this.zzpi(), var1, var2));
   }

   public Task removeLocationUpdates(PendingIntent var1) {
      return zzbh.zzb(LocationServices.FusedLocationApi.removeLocationUpdates(this.zzpi(), var1));
   }

   public Task removeLocationUpdates(LocationCallback var1) {
      zzbdy var3 = zzbea.zza(var1, LocationCallback.class.getSimpleName());
      zzbo.zzb(var3, "Listener key cannot be null.");
      return super.zzaAN.zza(this, var3);
   }

   @RequiresPermission(
      anyOf = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"}
   )
   public Task setMockMode(boolean var1) {
      return zzbh.zzb(LocationServices.FusedLocationApi.setMockMode(this.zzpi(), var1));
   }

   @RequiresPermission(
      anyOf = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"}
   )
   public Task setMockLocation(Location var1) {
      return zzbh.zzb(LocationServices.FusedLocationApi.setMockLocation(this.zzpi(), var1));
   }

   public Task flushLocations() {
      return zzbh.zzb(LocationServices.FusedLocationApi.flushLocations(this.zzpi()));
   }

   static class zza extends zzccv {
      private final TaskCompletionSource zzalE;

      public zza(TaskCompletionSource var1) {
         this.zzalE = var1;
      }

      public final void zza(zzcco var1) {
         Status var10000 = var1.getStatus();
         TaskCompletionSource var2 = this.zzalE;
         zzber.zza(var10000, (Object)null, var2);
      }
   }
}
