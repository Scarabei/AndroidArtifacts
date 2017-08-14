package com.google.android.gms.drive;

import android.content.IntentSender;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.internal.zzblc;
import com.google.android.gms.internal.zzbmn;

public class CreateFileActivityBuilder {
   public static final String EXTRA_RESPONSE_DRIVE_ID = "response_drive_id";
   private final zzblc zzaLX = new zzblc(0);
   private DriveContents zzaLY;
   private boolean zzaLZ;

   public CreateFileActivityBuilder setInitialDriveContents(DriveContents var1) {
      if (var1 != null) {
         if (!(var1 instanceof zzbmn)) {
            throw new IllegalArgumentException("Only DriveContents obtained from the Drive API are accepted.");
         }

         if (var1.getDriveId() != null) {
            throw new IllegalArgumentException("Only DriveContents obtained through DriveApi.newDriveContents are accepted for file creation.");
         }

         if (var1.zzsO()) {
            throw new IllegalArgumentException("DriveContents are already closed.");
         }

         this.zzaLX.zzaM(var1.zzsM().zzaLT);
         this.zzaLY = var1;
      } else {
         this.zzaLX.zzaM(1);
      }

      this.zzaLZ = true;
      return this;
   }

   public CreateFileActivityBuilder setInitialMetadata(MetadataChangeSet var1) {
      this.zzaLX.zza(var1);
      return this;
   }

   public CreateFileActivityBuilder setActivityStartFolder(DriveId var1) {
      this.zzaLX.zza(var1);
      return this;
   }

   public CreateFileActivityBuilder setActivityTitle(String var1) {
      this.zzaLX.zzcQ(var1);
      return this;
   }

   public IntentSender build(GoogleApiClient var1) {
      zzbo.zzb(this.zzaLZ, "Must call setInitialDriveContents to CreateFileActivityBuilder.");
      zzbo.zza(var1.isConnected(), "Client must be connected");
      if (this.zzaLY != null) {
         this.zzaLY.zzsN();
      }

      return this.zzaLX.build(var1);
   }
}
