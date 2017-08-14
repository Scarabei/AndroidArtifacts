package com.google.android.gms.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.drive.CreateFileActivityBuilder;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.drive.DriveApi;
import com.google.android.gms.drive.DriveFile;
import com.google.android.gms.drive.DriveFolder;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.OpenFileActivityBuilder;
import com.google.android.gms.drive.query.Query;
import java.util.List;

public final class zzblo implements DriveApi {
   public final PendingResult query(GoogleApiClient var1, Query var2) {
      if (var2 == null) {
         throw new IllegalArgumentException("Query must be provided.");
      } else {
         return var1.zzd(new zzblp(this, var1, var2));
      }
   }

   public final PendingResult newDriveContents(GoogleApiClient var1) {
      return var1.zzd(new zzblq(this, var1, 536870912));
   }

   public final PendingResult fetchDriveId(GoogleApiClient var1, String var2) {
      return var1.zzd(new zzblr(this, var1, var2));
   }

   public final DriveFile getFile(GoogleApiClient var1, DriveId var2) {
      if (var2 == null) {
         throw new IllegalArgumentException("Id must be provided.");
      } else if (!var1.isConnected()) {
         throw new IllegalStateException("Client must be connected");
      } else {
         return new zzbms(var2);
      }
   }

   public final DriveFolder getFolder(GoogleApiClient var1, DriveId var2) {
      if (var2 == null) {
         throw new IllegalArgumentException("Id must be provided.");
      } else if (!var1.isConnected()) {
         throw new IllegalStateException("Client must be connected");
      } else {
         return new zzbmx(var2);
      }
   }

   public final DriveFolder getRootFolder(GoogleApiClient var1) {
      zzbmh var2;
      if (!(var2 = (zzbmh)var1.zza(Drive.zzajR)).zzth()) {
         throw new IllegalStateException("Client is not yet connected");
      } else {
         DriveId var3;
         return (var3 = var2.zztf()) != null ? new zzbmx(var3) : null;
      }
   }

   public final DriveFolder getAppFolder(GoogleApiClient var1) {
      zzbmh var2;
      if (!(var2 = (zzbmh)var1.zza(Drive.zzajR)).zzth()) {
         throw new IllegalStateException("Client is not yet connected");
      } else {
         DriveId var3;
         return (var3 = var2.zztg()) != null ? new zzbmx(var3) : null;
      }
   }

   public final PendingResult requestSync(GoogleApiClient var1) {
      return var1.zze(new zzbls(this, var1));
   }

   public final PendingResult isAutobackupEnabled(GoogleApiClient var1) {
      return var1.zzd(new zzblt(this, var1));
   }

   public final OpenFileActivityBuilder newOpenFileActivityBuilder() {
      return new OpenFileActivityBuilder();
   }

   public final CreateFileActivityBuilder newCreateFileActivityBuilder() {
      return new CreateFileActivityBuilder();
   }

   public final PendingResult cancelPendingActions(GoogleApiClient var1, List var2) {
      zzbmh var3 = (zzbmh)var1.zza(Drive.zzajR);
      zzbo.zzaf(var2 != null);
      zzbo.zzaf(!var2.isEmpty());
      zzbo.zza(var3.isConnected(), "Client must be connected");
      return var1.zze(new zzbmm(var3, var1, var2));
   }
}
