package com.google.android.gms.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.drive.DriveContents;
import com.google.android.gms.drive.DriveFolder;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.ExecutionOptions;
import com.google.android.gms.drive.MetadataChangeSet;
import com.google.android.gms.drive.zzm;
import com.google.android.gms.drive.zzo;
import com.google.android.gms.drive.metadata.internal.zzk;
import com.google.android.gms.drive.query.Filters;
import com.google.android.gms.drive.query.Query;
import com.google.android.gms.drive.query.SearchableField;

public final class zzbmx extends zzbnn implements DriveFolder {
   public zzbmx(DriveId var1) {
      super(var1);
   }

   public final PendingResult listChildren(GoogleApiClient var1) {
      return this.queryChildren(var1, (Query)null);
   }

   public final PendingResult queryChildren(GoogleApiClient var1, Query var2) {
      zzblo var10000 = new zzblo();
      Query.Builder var5 = (new Query.Builder()).addFilter(Filters.in(SearchableField.PARENTS, this.getDriveId()));
      if (var2 != null) {
         if (var2.getFilter() != null) {
            var5.addFilter(var2.getFilter());
         }

         var5.setPageToken(var2.getPageToken());
         var5.setSortOrder(var2.getSortOrder());
      }

      return var10000.query(var1, var5.build());
   }

   public final PendingResult createFile(GoogleApiClient var1, MetadataChangeSet var2, DriveContents var3) {
      zzb(var2);
      return this.zza(var1, var2, var3, (zzm)null);
   }

   public final PendingResult createFile(GoogleApiClient var1, MetadataChangeSet var2, DriveContents var3, ExecutionOptions var4) {
      zzb(var2);
      zzo var6 = new zzo();
      if (var4 != null) {
         if (var4.zzsR() != 0) {
            throw new IllegalStateException("May not set a conflict strategy for new file creation.");
         }

         String var7;
         if ((var7 = var4.zzsP()) != null) {
            var6.setTrackingTag(var7);
         }

         var6.setNotifyOnCompletion(var4.zzsQ());
      }

      return this.zza(var1, var2, var3, (zzm)var6.build());
   }

   private static void zzb(MetadataChangeSet var0) {
      if (var0 == null) {
         throw new IllegalArgumentException("MetadataChangeSet must be provided.");
      } else {
         zzk var1;
         if ((var1 = zzk.zzcS(var0.getMimeType())) != null && (var1.zzts() || var1.isFolder())) {
            throw new IllegalArgumentException("May not create shortcut files using this method. Use DriveFolder.createShortcutFile() instead.");
         }
      }
   }

   private final PendingResult zza(GoogleApiClient var1, MetadataChangeSet var2, DriveContents var3, zzm var4) {
      if (var4 == null) {
         var4 = (zzm)(new zzo()).build();
      }

      if (var2 == null) {
         throw new IllegalArgumentException("MetadataChangeSet must be provided.");
      } else {
         zzk var13;
         if ((var13 = zzk.zzcS(var2.getMimeType())) != null && var13.isFolder()) {
            throw new IllegalArgumentException("May not create folders using this method. Use DriveFolder.createFolder() instead of mime type application/vnd.google-apps.folder");
         } else {
            var4.zze(var1);
            if (var3 != null) {
               if (!(var3 instanceof zzbmn)) {
                  throw new IllegalArgumentException("Only DriveContents obtained from the Drive API are accepted.");
               }

               if (var3.getDriveId() != null) {
                  throw new IllegalArgumentException("Only DriveContents obtained through DriveApi.newDriveContents are accepted for file creation.");
               }

               if (var3.zzsO()) {
                  throw new IllegalArgumentException("DriveContents are already closed.");
               }
            }

            zzk var5 = zzk.zzcS(var2.getMimeType());
            int var11;
            if (var3 == null) {
               var11 = var5 != null && var5.zzts() ? 0 : 1;
            } else {
               var11 = var3.zzsM().getRequestId();
               var3.zzsN();
            }

            String var7;
            if ((var7 = var4.zzsU()) != null) {
               var2 = var2.zza(zzbrc.zzaQA, var7);
            }

            int var14 = (var13 = zzk.zzcS(var2.getMimeType())) != null && var13.zzts() ? 1 : 0;
            return var1.zze(new zzbmy(this, var1, var2, var11, var14, var4));
         }
      }
   }

   public final PendingResult createFolder(GoogleApiClient var1, MetadataChangeSet var2) {
      if (var2 == null) {
         throw new IllegalArgumentException("MetadataChangeSet must be provided.");
      } else if (var2.getMimeType() != null && !var2.getMimeType().equals("application/vnd.google-apps.folder")) {
         throw new IllegalArgumentException("The mimetype must be of type application/vnd.google-apps.folder");
      } else {
         return var1.zze(new zzbmz(this, var1, var2));
      }
   }
}
