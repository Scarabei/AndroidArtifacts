package com.google.android.gms.internal;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.drive.DriveSpace;
import com.google.android.gms.drive.metadata.internal.zzl;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public final class zzbrj extends zzl {
   public zzbrj(int var1) {
      super("spaces", Arrays.asList("inDriveSpace", "isAppData", "inGooglePhotosSpace"), Collections.emptySet(), 7000000);
   }

   protected final Collection zzd(DataHolder var1, int var2, int var3) {
      ArrayList var4 = new ArrayList();
      if (var1.zze("inDriveSpace", var2, var3)) {
         var4.add(DriveSpace.zzaMl);
      }

      if (var1.zze("isAppData", var2, var3)) {
         var4.add(DriveSpace.zzaMm);
      }

      if (var1.zze("inGooglePhotosSpace", var2, var3)) {
         var4.add(DriveSpace.zzaMn);
      }

      return var4;
   }

   // $FF: synthetic method
   protected final Object zzc(DataHolder var1, int var2, int var3) {
      return this.zzd(var1, var2, var3);
   }
}
