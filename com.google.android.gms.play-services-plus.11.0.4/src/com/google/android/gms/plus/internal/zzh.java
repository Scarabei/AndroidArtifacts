package com.google.android.gms.plus.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.zzao;
import com.google.android.gms.common.internal.zzq;
import com.google.android.gms.common.internal.zzz;
import com.google.android.gms.internal.zzbaz;
import com.google.android.gms.internal.zzcri;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

public final class zzh extends zzz {
   private Person zzbAA;
   private final zzn zzbAB;

   public zzh(Context var1, Looper var2, zzq var3, zzn var4, ConnectionCallbacks var5, OnConnectionFailedListener var6) {
      super(var1, var2, 2, var3, var5, var6);
      this.zzbAB = var4;
   }

   public final boolean zzmv() {
      Set var1;
      if ((var1 = this.zzry().zzc(Plus.API)) != null && !var1.isEmpty()) {
         return var1.size() != 1 || !var1.contains(new Scope("plus_one_placeholder_scope"));
      } else {
         return false;
      }
   }

   public final String getAccountName() {
      this.zzre();

      try {
         return ((zzf)this.zzrf()).getAccountName();
      } catch (RemoteException var2) {
         throw new IllegalStateException(var2);
      }
   }

   public final zzao zza(zzbaz var1, int var2, String var3) {
      this.zzre();
      zzj var4 = new zzj(var1);

      try {
         return ((zzf)this.zzrf()).zza(var4, 1, var2, -1, var3);
      } catch (RemoteException var5) {
         var4.zza(DataHolder.zzau(8), (String)null);
         return null;
      }
   }

   public final void zzj(zzbaz var1) {
      this.zzre();
      zzj var2 = new zzj(var1);

      try {
         ((zzf)this.zzrf()).zza(var2, 2, 1, -1, (String)null);
      } catch (RemoteException var3) {
         var2.zza(DataHolder.zzau(8), (String)null);
      }
   }

   public final void zzc(zzbaz var1, String[] var2) {
      this.zza(var1, Arrays.asList(var2));
   }

   public final void zza(zzbaz var1, Collection var2) {
      this.zzre();
      zzj var3 = new zzj(var1);

      try {
         ((zzf)this.zzrf()).zza(var3, new ArrayList(var2));
      } catch (RemoteException var4) {
         var3.zza(DataHolder.zzau(8), (String)null);
      }
   }

   public final Person zzAf() {
      this.zzre();
      return this.zzbAA;
   }

   public final void zzAe() {
      this.zzre();

      try {
         this.zzbAA = null;
         ((zzf)this.zzrf()).zzAe();
      } catch (RemoteException var2) {
         throw new IllegalStateException(var2);
      }
   }

   public final void zzk(zzbaz var1) {
      this.zzre();
      this.zzAe();
      zzk var2 = new zzk(var1);

      try {
         ((zzf)this.zzrf()).zza(var2);
      } catch (RemoteException var3) {
         var2.zzf(8, (Bundle)null);
      }
   }

   protected final String zzdc() {
      return "com.google.android.gms.plus.internal.IPlusService";
   }

   protected final String zzdb() {
      return "com.google.android.gms.plus.service.START";
   }

   protected final Bundle zzmo() {
      Bundle var1;
      (var1 = this.zzbAB.zzAi()).putStringArray("request_visible_actions", this.zzbAB.zzAg());
      var1.putString("auth_package", this.zzbAB.zzAh());
      return var1;
   }

   protected final void zza(int var1, IBinder var2, Bundle var3, int var4) {
      if (var1 == 0 && var3 != null && var3.containsKey("loaded_person")) {
         this.zzbAA = zzcri.zzp(var3.getByteArray("loaded_person"));
      }

      super.zza(var1, var2, var3, var4);
   }

   // $FF: synthetic method
   protected final IInterface zzd(IBinder var1) {
      if (var1 == null) {
         return null;
      } else {
         IInterface var3;
         return (IInterface)((var3 = var1.queryLocalInterface("com.google.android.gms.plus.internal.IPlusService")) instanceof zzf ? (zzf)var3 : new zzg(var1));
      }
   }
}
