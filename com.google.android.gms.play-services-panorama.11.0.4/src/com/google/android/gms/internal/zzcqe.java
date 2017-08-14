package com.google.android.gms.internal;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.panorama.PanoramaApi;

public final class zzcqe implements PanoramaApi {
   public final PendingResult loadPanoramaInfo(GoogleApiClient var1, Uri var2) {
      return var1.zzd(new zzcqf(this, var1, var2));
   }

   public final PendingResult loadPanoramaInfoAndGrantAccess(GoogleApiClient var1, Uri var2) {
      return var1.zzd(new zzcqg(this, var1, var2));
   }

   private static void zza(Context var0, zzcqc var1, zzcqa var2, Uri var3, Bundle var4) throws RemoteException {
      var0.grantUriPermission("com.google.android.gms", var3, 1);
      zzcqh var5 = new zzcqh(var0, var3, var2);

      try {
         var1.zza(var5, var3, (Bundle)null, true);
      } catch (RemoteException var8) {
         var0.revokeUriPermission(var3, 1);
         throw var8;
      } catch (RuntimeException var9) {
         var0.revokeUriPermission(var3, 1);
         throw var9;
      }
   }

   private static void zzb(Context var0, Uri var1) {
      var0.revokeUriPermission(var1, 1);
   }

   // $FF: synthetic method
   static void zzb(Context var0, zzcqc var1, zzcqa var2, Uri var3, Bundle var4) throws RemoteException {
      zza(var0, var1, var2, var3, (Bundle)null);
   }

   // $FF: synthetic method
   static void zzc(Context var0, Uri var1) {
      zzb(var0, var1);
   }
}
