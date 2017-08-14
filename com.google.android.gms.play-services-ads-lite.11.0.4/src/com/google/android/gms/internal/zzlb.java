package com.google.android.gms.internal;

import android.location.Location;
import android.os.Bundle;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.mediation.NetworkExtras;
import com.google.android.gms.ads.mediation.admob.AdMobExtras;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

public final class zzlb {
   private final HashSet zzAX = new HashSet();
   private final Bundle zzAS = new Bundle();
   private final HashMap zzAY = new HashMap();
   private final HashSet zzAZ = new HashSet();
   private final Bundle zzAm = new Bundle();
   private final HashSet zzBa = new HashSet();
   private Date zzda;
   private String zzAk;
   private int zzAe = -1;
   private Location zzde;
   private boolean zzsu = false;
   private String zzAi;
   private String zzAo;
   private int zzAh = -1;
   private boolean zzAq;

   public final void zzD(String var1) {
      this.zzAX.add(var1);
   }

   /** @deprecated */
   @Deprecated
   public final void zza(NetworkExtras var1) {
      if (var1 instanceof AdMobExtras) {
         this.zza(AdMobAdapter.class, ((AdMobExtras)var1).getExtras());
      } else {
         this.zzAY.put(var1.getClass(), var1);
      }
   }

   public final void zza(Class var1, Bundle var2) {
      this.zzAS.putBundle(var1.getName(), var2);
   }

   public final void zzb(Class var1, Bundle var2) {
      if (this.zzAS.getBundle("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter") == null) {
         this.zzAS.putBundle("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter", new Bundle());
      }

      this.zzAS.getBundle("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter").putBundle(var1.getName(), var2);
   }

   public final void zzE(String var1) {
      this.zzAZ.add(var1);
   }

   public final void zzF(String var1) {
      this.zzAZ.remove(var1);
   }

   public final void zza(Date var1) {
      this.zzda = var1;
   }

   public final void zzG(String var1) {
      this.zzAk = var1;
   }

   public final void zzk(int var1) {
      this.zzAe = var1;
   }

   public final void zzb(Location var1) {
      this.zzde = var1;
   }

   public final void setManualImpressionsEnabled(boolean var1) {
      this.zzsu = var1;
   }

   public final void zzH(String var1) {
      this.zzAi = var1;
   }

   public final void zzI(String var1) {
      this.zzAo = var1;
   }

   public final void zzh(boolean var1) {
      this.zzAh = var1 ? 1 : 0;
   }

   public final void zzf(String var1, String var2) {
      this.zzAm.putString(var1, var2);
   }

   public final void zzJ(String var1) {
      this.zzBa.add(var1);
   }

   public final void zzi(boolean var1) {
      this.zzAq = var1;
   }

   // $FF: synthetic method
   static Date zza(zzlb var0) {
      return var0.zzda;
   }

   // $FF: synthetic method
   static String zzb(zzlb var0) {
      return var0.zzAk;
   }

   // $FF: synthetic method
   static int zzc(zzlb var0) {
      return var0.zzAe;
   }

   // $FF: synthetic method
   static HashSet zzd(zzlb var0) {
      return var0.zzAX;
   }

   // $FF: synthetic method
   static Location zze(zzlb var0) {
      return var0.zzde;
   }

   // $FF: synthetic method
   static boolean zzf(zzlb var0) {
      return var0.zzsu;
   }

   // $FF: synthetic method
   static Bundle zzg(zzlb var0) {
      return var0.zzAS;
   }

   // $FF: synthetic method
   static HashMap zzh(zzlb var0) {
      return var0.zzAY;
   }

   // $FF: synthetic method
   static String zzi(zzlb var0) {
      return var0.zzAi;
   }

   // $FF: synthetic method
   static String zzj(zzlb var0) {
      return var0.zzAo;
   }

   // $FF: synthetic method
   static int zzk(zzlb var0) {
      return var0.zzAh;
   }

   // $FF: synthetic method
   static HashSet zzl(zzlb var0) {
      return var0.zzAZ;
   }

   // $FF: synthetic method
   static Bundle zzm(zzlb var0) {
      return var0.zzAm;
   }

   // $FF: synthetic method
   static HashSet zzn(zzlb var0) {
      return var0.zzBa;
   }

   // $FF: synthetic method
   static boolean zzo(zzlb var0) {
      return var0.zzAq;
   }
}
