package com.google.android.gms.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.DriveResource;
import com.google.android.gms.drive.MetadataChangeSet;
import com.google.android.gms.drive.events.ChangeListener;
import com.google.android.gms.drive.events.zzj;
import java.util.ArrayList;
import java.util.Set;

public class zzbnn implements DriveResource {
   protected final DriveId zzaLV;

   public zzbnn(DriveId var1) {
      this.zzaLV = var1;
   }

   public DriveId getDriveId() {
      return this.zzaLV;
   }

   public PendingResult getMetadata(GoogleApiClient var1) {
      return var1.zzd(new zzbno(this, var1, false));
   }

   public PendingResult listParents(GoogleApiClient var1) {
      return var1.zzd(new zzbnp(this, var1));
   }

   public PendingResult setParents(GoogleApiClient var1, Set var2) {
      if (var2 == null) {
         throw new IllegalArgumentException("ParentIds must be provided.");
      } else {
         ArrayList var3 = new ArrayList(var2);
         return var1.zze(new zzbnq(this, var1, var3));
      }
   }

   public PendingResult updateMetadata(GoogleApiClient var1, MetadataChangeSet var2) {
      if (var2 == null) {
         throw new IllegalArgumentException("ChangeSet must be provided.");
      } else {
         return var1.zze(new zzbnr(this, var1, var2));
      }
   }

   public PendingResult delete(GoogleApiClient var1) {
      return var1.zze(new zzbns(this, var1));
   }

   public PendingResult addChangeListener(GoogleApiClient var1, ChangeListener var2) {
      return ((zzbmh)var1.zza(Drive.zzajR)).zza(var1, this.zzaLV, var2);
   }

   public PendingResult removeChangeListener(GoogleApiClient var1, ChangeListener var2) {
      return ((zzbmh)var1.zza(Drive.zzajR)).zzb(var1, this.zzaLV, var2);
   }

   public PendingResult addChangeSubscription(GoogleApiClient var1) {
      return ((zzbmh)var1.zza(Drive.zzajR)).zza(var1, this.zzaLV);
   }

   public PendingResult removeChangeSubscription(GoogleApiClient var1) {
      zzbmh var10000 = (zzbmh)var1.zza(Drive.zzajR);
      DriveId var4 = this.zzaLV;
      zzbmh var5 = var10000;
      zzbo.zzaf(zzj.zza(1, var4));
      zzbo.zza(var5.isConnected(), "Client must be connected");
      return var1.zze(new zzbml(var5, var1, var4, 1));
   }

   public PendingResult trash(GoogleApiClient var1) {
      return var1.zze(new zzbnt(this, var1));
   }

   public PendingResult untrash(GoogleApiClient var1) {
      return var1.zze(new zzbnu(this, var1));
   }
}
