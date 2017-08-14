package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.ads.mediation.AdUrlAdapter;
import com.google.ads.mediation.MediationAdapter;
import com.google.ads.mediation.NetworkExtras;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.mediation.customevent.CustomEvent;
import com.google.android.gms.ads.mediation.customevent.CustomEventAdapter;
import com.google.android.gms.ads.mediation.customevent.CustomEventExtras;
import java.util.Map;

@zzzn
public final class zzup extends zzur {
   private Map zzMY;

   public final zzut zzah(String var1) throws RemoteException {
      return this.zzaj(var1);
   }

   public final boolean zzai(String var1) throws RemoteException {
      try {
         Class var2 = Class.forName(var1, false, zzup.class.getClassLoader());
         return CustomEvent.class.isAssignableFrom(var2);
      } catch (Throwable var3) {
         zzajc.zzaT((new StringBuilder(80 + String.valueOf(var1).length())).append("Could not load custom event implementation class: ").append(var1).append(", assuming old implementation.").toString());
         return false;
      }
   }

   public final void zzg(Map var1) {
      this.zzMY = var1;
   }

   private final zzut zzaj(String var1) throws RemoteException {
      try {
         Class var2 = Class.forName(var1, false, zzup.class.getClassLoader());
         if (MediationAdapter.class.isAssignableFrom(var2)) {
            MediationAdapter var6 = (MediationAdapter)var2.newInstance();
            NetworkExtras var4 = (NetworkExtras)this.zzMY.get(var6.getAdditionalParametersType());
            return new zzvo(var6, var4);
         } else if (com.google.android.gms.ads.mediation.MediationAdapter.class.isAssignableFrom(var2)) {
            com.google.android.gms.ads.mediation.MediationAdapter var3 = (com.google.android.gms.ads.mediation.MediationAdapter)var2.newInstance();
            return new zzvj(var3);
         } else {
            zzajc.zzaT((new StringBuilder(64 + String.valueOf(var1).length())).append("Could not instantiate mediation adapter: ").append(var1).append(" (not a valid adapter).").toString());
            throw new RemoteException();
         }
      } catch (Throwable var5) {
         return this.zzak(var1);
      }
   }

   private final zzut zzak(String var1) throws RemoteException {
      try {
         zzajc.zzaC("Reflection failed, retrying using direct instantiation");
         if ("com.google.ads.mediation.admob.AdMobAdapter".equals(var1)) {
            return new zzvj(new AdMobAdapter());
         }

         if ("com.google.ads.mediation.AdUrlAdapter".equals(var1)) {
            return new zzvj(new AdUrlAdapter());
         }

         if ("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter".equals(var1)) {
            return new zzvj(new CustomEventAdapter());
         }

         if ("com.google.ads.mediation.customevent.CustomEventAdapter".equals(var1)) {
            com.google.ads.mediation.customevent.CustomEventAdapter var2 = new com.google.ads.mediation.customevent.CustomEventAdapter();
            CustomEventExtras var3 = (CustomEventExtras)this.zzMY.get(var2.getAdditionalParametersType());
            return new zzvo(var2, var3);
         }
      } catch (Throwable var4) {
         zzajc.zzc((new StringBuilder(43 + String.valueOf(var1).length())).append("Could not instantiate mediation adapter: ").append(var1).append(". ").toString(), var4);
      }

      throw new RemoteException();
   }
}
