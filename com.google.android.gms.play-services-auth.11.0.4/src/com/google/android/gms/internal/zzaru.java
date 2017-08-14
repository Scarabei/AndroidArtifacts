package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.auth.api.credentials.CredentialRequest;

public final class zzaru extends zzed implements zzart {
   zzaru(IBinder var1) {
      super(var1, "com.google.android.gms.auth.api.credentials.internal.ICredentialsService");
   }

   public final void zza(zzarr var1, CredentialRequest var2) throws RemoteException {
      Parcel var3;
      zzef.zza(var3 = this.zzZ(), var1);
      zzef.zza(var3, var2);
      this.zzb(1, var3);
   }

   public final void zza(zzarr var1, zzarv var2) throws RemoteException {
      Parcel var3;
      zzef.zza(var3 = this.zzZ(), var1);
      zzef.zza(var3, var2);
      this.zzb(2, var3);
   }

   public final void zza(zzarr var1, zzarp var2) throws RemoteException {
      Parcel var3;
      zzef.zza(var3 = this.zzZ(), var1);
      zzef.zza(var3, var2);
      this.zzb(3, var3);
   }

   public final void zza(zzarr var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(4, var2);
   }
}
