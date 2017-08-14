package com.google.android.gms.auth.api.signin.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.internal.zzed;
import com.google.android.gms.internal.zzef;

public final class zzu extends zzed implements zzt {
   zzu(IBinder var1) {
      super(var1, "com.google.android.gms.auth.api.signin.internal.ISignInService");
   }

   public final void zza(zzr var1, GoogleSignInOptions var2) throws RemoteException {
      Parcel var3;
      zzef.zza(var3 = this.zzZ(), var1);
      zzef.zza(var3, var2);
      this.zzb(101, var3);
   }

   public final void zzb(zzr var1, GoogleSignInOptions var2) throws RemoteException {
      Parcel var3;
      zzef.zza(var3 = this.zzZ(), var1);
      zzef.zza(var3, var2);
      this.zzb(102, var3);
   }

   public final void zzc(zzr var1, GoogleSignInOptions var2) throws RemoteException {
      Parcel var3;
      zzef.zza(var3 = this.zzZ(), var1);
      zzef.zza(var3, var2);
      this.zzb(103, var3);
   }
}
