package com.google.android.gms.drive;

import android.content.IntentSender;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.drive.query.Filter;
import com.google.android.gms.drive.query.internal.FilterHolder;
import com.google.android.gms.internal.zzbmh;
import com.google.android.gms.internal.zzbom;
import com.google.android.gms.internal.zzbqe;

public class OpenFileActivityBuilder {
   public static final String EXTRA_RESPONSE_DRIVE_ID = "response_drive_id";
   private String zzaoy;
   private String[] zzaMC;
   private Filter zzaMD;
   private DriveId zzaME;

   public OpenFileActivityBuilder setActivityTitle(String var1) {
      this.zzaoy = (String)zzbo.zzu(var1);
      return this;
   }

   public OpenFileActivityBuilder setMimeType(String[] var1) {
      zzbo.zzb(var1 != null, "mimeTypes may not be null");
      this.zzaMC = var1;
      return this;
   }

   public OpenFileActivityBuilder setSelectionFilter(Filter var1) {
      zzbo.zzb(var1 != null, "filter may not be null");
      zzbo.zzb(!com.google.android.gms.drive.query.internal.zzk.zza(var1), "FullTextSearchFilter cannot be used as a selection filter");
      this.zzaMD = var1;
      return this;
   }

   public OpenFileActivityBuilder setActivityStartFolder(DriveId var1) {
      this.zzaME = (DriveId)zzbo.zzu(var1);
      return this;
   }

   public IntentSender build(GoogleApiClient var1) {
      zzbo.zza(var1.isConnected(), "Client must be connected");
      if (this.zzaMC == null) {
         this.zzaMC = new String[0];
      }

      if (this.zzaMC.length > 0 && this.zzaMD != null) {
         throw new IllegalStateException("Cannot use a selection filter and set mimetypes simultaneously");
      } else {
         FilterHolder var2 = this.zzaMD == null ? null : new FilterHolder(this.zzaMD);

         try {
            return ((zzbom)((zzbmh)var1.zza(Drive.zzajR)).zzrf()).zza(new zzbqe(this.zzaoy, this.zzaMC, this.zzaME, var2));
         } catch (RemoteException var4) {
            throw new RuntimeException("Unable to connect Drive Play Service", var4);
         }
      }
   }
}
