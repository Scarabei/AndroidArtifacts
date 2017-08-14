package com.google.android.gms.internal;

import android.content.Context;
import android.os.DeadObjectException;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.common.internal.zzd;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.common.internal.zzg;

public final class zzcas extends zzd {
   public zzcas(Context var1, Looper var2, zzf var3, zzg var4) {
      super(var1, var2, 116, var3, var4, (String)null);
   }

   protected final String zzdb() {
      return "com.google.android.gms.gass.START";
   }

   protected final String zzdc() {
      return "com.google.android.gms.gass.internal.IGassService";
   }

   public final zzcax zzvz() throws DeadObjectException {
      return (zzcax)super.zzrf();
   }

   // $FF: synthetic method
   protected final IInterface zzd(IBinder var1) {
      if (var1 == null) {
         return null;
      } else {
         IInterface var3;
         return (IInterface)((var3 = var1.queryLocalInterface("com.google.android.gms.gass.internal.IGassService")) instanceof zzcax ? (zzcax)var3 : new zzcay(var1));
      }
   }
}
