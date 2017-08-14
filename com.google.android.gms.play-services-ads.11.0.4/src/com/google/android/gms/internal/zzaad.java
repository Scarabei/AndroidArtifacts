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
public final class zzaad extends zzd {
   private int zzSx;

   public zzaad(Context var1, Looper var2, zzf var3, zzg var4, int var5) {
      super(var1, var2, 8, var3, var4, (String)null);
      this.zzSx = var5;
   }

   protected final String zzdb() {
      return "com.google.android.gms.ads.service.START";
   }

   protected final String zzdc() {
      return "com.google.android.gms.ads.internal.request.IAdRequestService";
   }

   public final zzaam zzgC() throws DeadObjectException {
      return (zzaam)super.zzrf();
   }

   // $FF: synthetic method
   protected final IInterface zzd(IBinder var1) {
      if (var1 == null) {
         return null;
      } else {
         IInterface var3;
         return (IInterface)((var3 = var1.queryLocalInterface("com.google.android.gms.ads.internal.request.IAdRequestService")) instanceof zzaam ? (zzaam)var3 : new zzaao(var1));
      }
   }
}
