package com.google.android.gms.internal;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.metadata.internal.zzm;
import java.util.Arrays;
import java.util.Iterator;

public final class zzbrw extends zzm {
   public static final zzbrw zzaQO = new zzbrw();

   private zzbrw() {
      super("driveId", Arrays.asList("sqlId", "resourceId", "mimeType"), Arrays.asList("dbInstanceId"), 4100000);
   }

   protected final boolean zzb(DataHolder var1, int var2, int var3) {
      Iterator var4 = this.zztk().iterator();

      String var5;
      do {
         if (!var4.hasNext()) {
            return true;
         }

         var5 = (String)var4.next();
      } while(var1.zzcv(var5));

      return false;
   }

   // $FF: synthetic method
   protected final Object zzc(DataHolder var1, int var2, int var3) {
      long var7 = var1.zzqN().getLong("dbInstanceId");
      String var9 = var1.zzd(zzbrc.zzaQn.getName(), var2, var3);
      int var10 = "application/vnd.google-apps.folder".equals(var9) ? 1 : 0;
      String var11 = var1.zzd("resourceId", var2, var3);
      Long var12 = var1.zzb("sqlId", var2, var3);
      return new DriveId("generated-android-null".equals(var11) ? null : var11, var12.longValue(), var7, var10);
   }
}
