package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.os.DeadObjectException;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbo;

public abstract class zzbay extends zzbbe implements zzbaz {
   private final Api.zzc zzaBM;
   private final Api zzayW;

   /** @deprecated */
   @Deprecated
   protected zzbay(Api.zzc var1, GoogleApiClient var2) {
      super((GoogleApiClient)zzbo.zzb(var2, "GoogleApiClient must not be null"));
      this.zzaBM = (Api.zzc)zzbo.zzu(var1);
      this.zzayW = null;
   }

   protected zzbay(Api var1, GoogleApiClient var2) {
      super((GoogleApiClient)zzbo.zzb(var2, "GoogleApiClient must not be null"));
      this.zzaBM = var1.zzpd();
      this.zzayW = var1;
   }

   public final Api.zzc zzpd() {
      return this.zzaBM;
   }

   public final Api zzpg() {
      return this.zzayW;
   }

   public final void zzb(Api.zzb var1) throws DeadObjectException {
      try {
         this.zza(var1);
      } catch (DeadObjectException var3) {
         this.zzc(var3);
         throw var3;
      } catch (RemoteException var4) {
         this.zzc(var4);
      }
   }

   public final void zzr(Status var1) {
      zzbo.zzb(!var1.isSuccess(), "Failed result must not be success");
      Result var2 = this.zzb(var1);
      this.setResult(var2);
   }

   protected abstract void zza(Api.zzb var1) throws RemoteException;

   private final void zzc(RemoteException var1) {
      Status var2 = new Status(8, var1.getLocalizedMessage(), (PendingIntent)null);
      this.zzr(var2);
   }
}
