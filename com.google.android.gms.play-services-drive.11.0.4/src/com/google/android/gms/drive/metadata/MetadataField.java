package com.google.android.gms.drive.metadata;

import android.os.Bundle;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

public interface MetadataField {
   String getName();

   Object zzp(Bundle var1);

   Object zza(DataHolder var1, int var2, int var3);

   void zza(Object var1, Bundle var2);

   void zza(DataHolder var1, MetadataBundle var2, int var3, int var4);
}
