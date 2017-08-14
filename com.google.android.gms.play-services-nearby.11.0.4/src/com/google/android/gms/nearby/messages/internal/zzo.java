package com.google.android.gms.nearby.messages.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzed;
import com.google.android.gms.internal.zzef;
import java.util.List;

public final class zzo extends zzed implements zzm {
   zzo(IBinder var1) {
      super(var1, "com.google.android.gms.nearby.messages.internal.IMessageListener");
   }

   public final void zza(zzaf var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzc(1, var2);
   }

   public final void zzb(zzaf var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzc(2, var2);
   }

   public final void zzH(List var1) throws RemoteException {
      Parcel var2;
      (var2 = this.zzZ()).writeTypedList(var1);
      this.zzc(4, var2);
   }
}
