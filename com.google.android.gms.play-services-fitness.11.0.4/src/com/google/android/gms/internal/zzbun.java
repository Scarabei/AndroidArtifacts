package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzq;
import com.google.android.gms.common.internal.zzz;
import com.google.android.gms.common.util.zzj;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public abstract class zzbun extends zzz {
   protected zzbun(Context var1, Looper var2, int var3, ConnectionCallbacks var4, OnConnectionFailedListener var5, zzq var6) {
      super(var1, var2, var3, var6, var4, var5);
   }

   public final boolean zzrg() {
      return true;
   }

   public final boolean zzmv() {
      return !zzj.zzaG(this.getContext());
   }

   protected final Set zzb(Set var1) {
      Set var2 = var1;
      HashSet var3 = new HashSet(var1.size());
      Iterator var4 = var1.iterator();

      while(true) {
         Scope var5;
         Scope var6;
         Scope var7;
         do {
            if (!var4.hasNext()) {
               return var3;
            }
         } while(!(var6 = (var7 = var5 = (Scope)var4.next()).equals(new Scope("https://www.googleapis.com/auth/fitness.activity.read")) ? new Scope("https://www.googleapis.com/auth/fitness.activity.write") : (var7.equals(new Scope("https://www.googleapis.com/auth/fitness.location.read")) ? new Scope("https://www.googleapis.com/auth/fitness.location.write") : (var7.equals(new Scope("https://www.googleapis.com/auth/fitness.body.read")) ? new Scope("https://www.googleapis.com/auth/fitness.body.write") : (var7.equals(new Scope("https://www.googleapis.com/auth/fitness.nutrition.read")) ? new Scope("https://www.googleapis.com/auth/fitness.nutrition.write") : (var7.equals(new Scope("https://www.googleapis.com/auth/fitness.blood_pressure.read")) ? new Scope("https://www.googleapis.com/auth/fitness.blood_pressure.write") : (var7.equals(new Scope("https://www.googleapis.com/auth/fitness.blood_glucose.read")) ? new Scope("https://www.googleapis.com/auth/fitness.blood_glucose.write") : (var7.equals(new Scope("https://www.googleapis.com/auth/fitness.oxygen_saturation.read")) ? new Scope("https://www.googleapis.com/auth/fitness.oxygen_saturation.write") : (var7.equals(new Scope("https://www.googleapis.com/auth/fitness.body_temperature.read")) ? new Scope("https://www.googleapis.com/auth/fitness.body_temperature.write") : (var7.equals(new Scope("https://www.googleapis.com/auth/fitness.reproductive_health.read")) ? new Scope("https://www.googleapis.com/auth/fitness.reproductive_health.write") : var7))))))))).equals(var5) && var2.contains(var6));

         var3.add(var5);
      }
   }

   public abstract String zzdb();

   public abstract String zzdc();

   public abstract IInterface zzd(IBinder var1);
}
