package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.metadata.SearchableMetadataField;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

public final class zzb extends zza {
   public static final zzc CREATOR = new zzc();
   private zzx zzaRd;
   private MetadataBundle zzaRe;
   private MetadataField zzaRf;

   zzb(zzx var1, MetadataBundle var2) {
      this.zzaRd = var1;
      this.zzaRe = var2;
      this.zzaRf = zzi.zza(var2);
   }

   public zzb(zzx var1, SearchableMetadataField var2, Object var3) {
      this(var1, MetadataBundle.zzb(var2, var3));
   }

   public final Object zza(zzj var1) {
      return var1.zza(this.zzaRd, this.zzaRf, this.zzaRe.zza(this.zzaRf));
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 1, this.zzaRd, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzaRe, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
