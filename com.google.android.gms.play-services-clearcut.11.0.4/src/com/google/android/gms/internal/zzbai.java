package com.google.android.gms.internal;

import android.content.ContentResolver;
import android.content.Context;

final class zzbai {
   final ContentResolver zzazQ;

   zzbai(Context var1) {
      if (var1 != null) {
         if (zzbah.zzazO == null) {
            zzbah.zzazO = zzbha.zzaP(var1).checkCallingOrSelfPermission("com.google.android.providers.gsf.permission.READ_GSERVICES") == 0;
         }

         if (zzbah.zzazO.booleanValue()) {
            this.zzazQ = var1.getContentResolver();
            hi.zzb(this.zzazQ, new String[]{"gms:playlog:service:sampling_"});
            return;
         }
      }

      this.zzazQ = null;
   }
}
