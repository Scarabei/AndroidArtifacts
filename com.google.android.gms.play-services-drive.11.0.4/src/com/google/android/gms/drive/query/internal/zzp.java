package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import com.google.android.gms.drive.metadata.SearchableCollectionMetadataField;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import java.util.Collection;
import java.util.Collections;

public final class zzp extends zza {
   public static final zzq CREATOR = new zzq();
   private MetadataBundle zzaRe;
   private final com.google.android.gms.drive.metadata.zzb zzaRr;

   zzp(MetadataBundle var1) {
      this.zzaRe = var1;
      this.zzaRr = (com.google.android.gms.drive.metadata.zzb)zzi.zza(var1);
   }

   public zzp(SearchableCollectionMetadataField var1, Object var2) {
      this(MetadataBundle.zzb(var1, Collections.singleton(var2)));
   }

   public final Object zza(zzj var1) {
      return var1.zza(this.zzaRr, ((Collection)this.zzaRe.zza(this.zzaRr)).iterator().next());
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 1, this.zzaRe, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
