package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.metadata.SearchableMetadataField;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

public final class zzn extends zza {
   public static final zzo CREATOR = new zzo();
   private MetadataBundle zzaRe;
   private MetadataField zzaRf;

   zzn(MetadataBundle var1) {
      this.zzaRe = var1;
      this.zzaRf = zzi.zza(var1);
   }

   public zzn(SearchableMetadataField var1, Object var2) {
      this(MetadataBundle.zzb(var1, var2));
   }

   public final Object zza(zzj var1) {
      return var1.zzd(this.zzaRf, this.zzaRe.zza(this.zzaRf));
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 1, this.zzaRe, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
