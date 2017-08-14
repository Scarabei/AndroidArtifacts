package com.google.android.gms.dynamic;

import android.os.Bundle;
import java.util.Iterator;

final class zzb implements zzo {
   // $FF: synthetic field
   private zza zzaSv;

   zzb(zza var1) {
      this.zzaSv = var1;
      super();
   }

   public final void zza(LifecycleDelegate var1) {
      zza.zza(this.zzaSv, var1);
      Iterator var2 = zza.zza(this.zzaSv).iterator();

      while(var2.hasNext()) {
         ((zzi)var2.next()).zzb(zza.zzb(this.zzaSv));
      }

      zza.zza(this.zzaSv).clear();
      zza.zza((zza)this.zzaSv, (Bundle)null);
   }
}
