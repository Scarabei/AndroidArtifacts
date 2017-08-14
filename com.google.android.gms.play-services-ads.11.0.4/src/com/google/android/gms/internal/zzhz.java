package com.google.android.gms.internal;

import android.content.Context;
import android.os.DeadObjectException;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.common.internal.zzd;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.common.internal.zzg;

@zzzn
public final class zzhz extends zzd {
   zzhz(Context var1, Looper var2, zzf var3, zzg var4) {
      super(var1, var2, 123, var3, var4, (String)null);
   }

   protected final String zzdb() {
      return "com.google.android.gms.ads.service.CACHE";
   }

   protected final String zzdc() {
      return "com.google.android.gms.ads.internal.cache.ICacheService";
   }

   public final zzid zzdd() throws DeadObjectException {
      return (zzid)super.zzrf();
   }

   // $FF: synthetic method
   protected final IInterface zzd(IBinder var1) {
      if (var1 == null) {
         return null;
      } else {
         IInterface var3;
         return (IInterface)((var3 = var1.queryLocalInterface("com.google.android.gms.ads.internal.cache.ICacheService")) instanceof zzid ? (zzid)var3 : new zzie(var1));
      }
   }
}
