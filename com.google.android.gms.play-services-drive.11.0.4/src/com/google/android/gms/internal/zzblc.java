package com.google.android.gms.internal;

import android.content.IntentSender;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.MetadataChangeSet;

public final class zzblc {
   private MetadataChangeSet zzaNA;
   private Integer zzaNB;
   private String zzaoy;
   private DriveId zzaME;
   private final int zzaNC = 0;

   public zzblc(int var1) {
   }

   public final void zza(MetadataChangeSet var1) {
      this.zzaNA = (MetadataChangeSet)zzbo.zzu(var1);
   }

   public final void zza(DriveId var1) {
      this.zzaME = (DriveId)zzbo.zzu(var1);
   }

   public final void zzcQ(String var1) {
      this.zzaoy = (String)zzbo.zzu(var1);
   }

   public final void zzaM(int var1) {
      this.zzaNB = var1;
   }

   public final IntentSender build(GoogleApiClient var1) {
      zzbo.zzb(this.zzaNA, "Must provide initial metadata to CreateFileActivityBuilder.");
      zzbo.zza(var1.isConnected(), "Client must be connected");
      zzbmh var2 = (zzbmh)var1.zza(Drive.zzajR);
      this.zzaNA.zzsW().setContext(var2.getContext());
      int var3 = this.zzaNB == null ? 0 : this.zzaNB.intValue();

      try {
         return ((zzbom)var2.zzrf()).zza(new zzbld(this.zzaNA.zzsW(), var3, this.zzaoy, this.zzaME, Integer.valueOf(0)));
      } catch (RemoteException var5) {
         throw new RuntimeException("Unable to connect Drive Play Service", var5);
      }
   }
}
