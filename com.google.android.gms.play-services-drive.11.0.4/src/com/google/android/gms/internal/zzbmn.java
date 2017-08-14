package com.google.android.gms.internal;

import android.os.ParcelFileDescriptor;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.common.util.zzn;
import com.google.android.gms.drive.DriveContents;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.ExecutionOptions;
import com.google.android.gms.drive.MetadataChangeSet;
import com.google.android.gms.drive.zzc;
import com.google.android.gms.drive.zzp;
import com.google.android.gms.drive.zzr;
import java.io.InputStream;
import java.io.OutputStream;

public final class zzbmn implements DriveContents {
   private final zzc zzaOg;
   private boolean mClosed = false;
   private boolean zzaOh = false;
   private boolean zzaOi = false;

   public zzbmn(zzc var1) {
      this.zzaOg = (zzc)zzbo.zzu(var1);
   }

   public final DriveId getDriveId() {
      return this.zzaOg.getDriveId();
   }

   public final int getMode() {
      return this.zzaOg.getMode();
   }

   public final ParcelFileDescriptor getParcelFileDescriptor() {
      if (this.mClosed) {
         throw new IllegalStateException("Contents have been closed, cannot access the output stream.");
      } else {
         return this.zzaOg.getParcelFileDescriptor();
      }
   }

   public final InputStream getInputStream() {
      if (this.mClosed) {
         throw new IllegalStateException("Contents have been closed, cannot access the input stream.");
      } else if (this.zzaOg.getMode() != 268435456) {
         throw new IllegalStateException("getInputStream() can only be used with contents opened with MODE_READ_ONLY.");
      } else if (this.zzaOh) {
         throw new IllegalStateException("getInputStream() can only be called once per Contents instance.");
      } else {
         this.zzaOh = true;
         return this.zzaOg.getInputStream();
      }
   }

   public final OutputStream getOutputStream() {
      if (this.mClosed) {
         throw new IllegalStateException("Contents have been closed, cannot access the output stream.");
      } else if (this.zzaOg.getMode() != 536870912) {
         throw new IllegalStateException("getOutputStream() can only be used with contents opened with MODE_WRITE_ONLY.");
      } else if (this.zzaOi) {
         throw new IllegalStateException("getOutputStream() can only be called once per Contents instance.");
      } else {
         this.zzaOi = true;
         return this.zzaOg.getOutputStream();
      }
   }

   public final PendingResult reopenForWrite(GoogleApiClient var1) {
      if (this.mClosed) {
         throw new IllegalStateException("DriveContents already closed.");
      } else if (this.zzaOg.getMode() != 268435456) {
         throw new IllegalStateException("reopenForWrite can only be used with DriveContents opened with MODE_READ_ONLY.");
      } else {
         this.zzsN();
         return var1.zzd(new zzbmo(this, var1));
      }
   }

   public final PendingResult commit(GoogleApiClient var1, MetadataChangeSet var2) {
      return this.zza(var1, var2, (zzp)null);
   }

   public final PendingResult commit(GoogleApiClient var1, MetadataChangeSet var2, ExecutionOptions var3) {
      zzp var10003;
      if (var3 == null) {
         var10003 = null;
      } else {
         zzr var5 = new zzr();
         if (var3 != null) {
            var5.setConflictStrategy(var3.zzsR());
            var5.setNotifyOnCompletion(var3.zzsQ());
            String var6;
            if ((var6 = var3.zzsP()) != null) {
               var5.setTrackingTag(var6);
            }
         }

         var10003 = (zzp)var5.build();
      }

      return this.zza(var1, var2, var10003);
   }

   private final PendingResult zza(GoogleApiClient var1, MetadataChangeSet var2, zzp var3) {
      if (var3 == null) {
         var3 = (zzp)(new zzr()).build();
      }

      if (this.zzaOg.getMode() == 268435456) {
         throw new IllegalStateException("Cannot commit contents opened with MODE_READ_ONLY");
      } else {
         boolean var10000;
         switch(var3.zzsR()) {
         case 1:
            var10000 = true;
            break;
         default:
            var10000 = false;
         }

         if (var10000 && !this.zzaOg.zzsK()) {
            throw new IllegalStateException("DriveContents must be valid for conflict detection.");
         } else {
            var3.zze(var1);
            if (this.mClosed) {
               throw new IllegalStateException("DriveContents already closed.");
            } else if (this.getDriveId() == null) {
               throw new IllegalStateException("Only DriveContents obtained through DriveFile.open can be committed.");
            } else {
               MetadataChangeSet var4 = var2 != null ? var2 : MetadataChangeSet.zzaMz;
               this.zzsN();
               return var1.zze(new zzbmp(this, var1, var4, var3));
            }
         }
      }
   }

   public final void discard(GoogleApiClient var1) {
      if (this.mClosed) {
         throw new IllegalStateException("DriveContents already closed.");
      } else {
         this.zzsN();
         ((zzbmr)var1.zze(new zzbmr(this, var1))).setResultCallback(new zzbmq(this));
      }
   }

   public final zzc zzsM() {
      return this.zzaOg;
   }

   public final void zzsN() {
      zzn.zza(this.zzaOg.getParcelFileDescriptor());
      this.mClosed = true;
   }

   public final boolean zzsO() {
      return this.mClosed;
   }

   // $FF: synthetic method
   static zzc zza(zzbmn var0) {
      return var0.zzaOg;
   }
}
