package com.google.android.gms.internal;

import android.annotation.SuppressLint;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.plus.People;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;
import java.util.Collection;

public final class zzcra implements People {
   public final Person getCurrentPerson(GoogleApiClient var1) {
      return Plus.zzc(var1, true).zzAf();
   }

   @SuppressLint({"MissingRemoteException"})
   public final PendingResult loadVisible(GoogleApiClient var1, int var2, String var3) {
      return var1.zzd(new zzcrb(this, var1, var2, var3));
   }

   @SuppressLint({"MissingRemoteException"})
   public final PendingResult loadVisible(GoogleApiClient var1, String var2) {
      return var1.zzd(new zzcrc(this, var1, var2));
   }

   @SuppressLint({"MissingRemoteException"})
   public final PendingResult loadConnected(GoogleApiClient var1) {
      return var1.zzd(new zzcrd(this, var1));
   }

   @SuppressLint({"MissingRemoteException"})
   public final PendingResult load(GoogleApiClient var1, Collection var2) {
      return var1.zzd(new zzcre(this, var1, var2));
   }

   @SuppressLint({"MissingRemoteException"})
   public final PendingResult load(GoogleApiClient var1, String... var2) {
      return var1.zzd(new zzcrf(this, var1, var2));
   }
}
