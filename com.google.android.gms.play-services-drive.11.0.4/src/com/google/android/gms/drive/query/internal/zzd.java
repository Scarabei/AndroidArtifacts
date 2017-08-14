package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.metadata.SearchableMetadataField;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

public final class zzd extends zza {
   public static final Creator CREATOR = new zze();
   private MetadataBundle zzaRe;
   private final MetadataField zzaRf;

   zzd(MetadataBundle var1) {
      this.zzaRe = var1;
      this.zzaRf = zzi.zza(var1);
   }

   public zzd(SearchableMetadataField var1) {
      this(MetadataBundle.zzb(var1, (Object)null));
   }

   public final Object zza(zzj var1) {
      return var1.zzd(this.zzaRf);
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 1, this.zzaRe, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
