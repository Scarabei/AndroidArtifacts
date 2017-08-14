package com.google.android.gms.internal;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import com.google.android.gms.ads.mediation.NetworkExtras;
import com.google.android.gms.ads.search.SearchAdRequest;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.Set;

@zzzn
public final class zzla {
   private final Date zzda;
   private final String zzAk;
   private final int zzAe;
   private final Set zzdc;
   private final Location zzde;
   private final boolean zzsu;
   private final Bundle zzAS;
   private final Map zzAT;
   private final String zzAi;
   private final String zzAo;
   private final SearchAdRequest zzAU;
   private final int zzAh;
   private final Set zzAV;
   private final Bundle zzAm;
   private final Set zzAW;
   private final boolean zzAq;

   public zzla(zzlb var1) {
      this(var1, (SearchAdRequest)null);
   }

   public zzla(zzlb var1, SearchAdRequest var2) {
      this.zzda = zzlb.zza(var1);
      this.zzAk = zzlb.zzb(var1);
      this.zzAe = zzlb.zzc(var1);
      this.zzdc = Collections.unmodifiableSet(zzlb.zzd(var1));
      this.zzde = zzlb.zze(var1);
      this.zzsu = zzlb.zzf(var1);
      this.zzAS = zzlb.zzg(var1);
      this.zzAT = Collections.unmodifiableMap(zzlb.zzh(var1));
      this.zzAi = zzlb.zzi(var1);
      this.zzAo = zzlb.zzj(var1);
      this.zzAU = var2;
      this.zzAh = zzlb.zzk(var1);
      this.zzAV = Collections.unmodifiableSet(zzlb.zzl(var1));
      this.zzAm = zzlb.zzm(var1);
      this.zzAW = Collections.unmodifiableSet(zzlb.zzn(var1));
      this.zzAq = zzlb.zzo(var1);
   }

   public final Date getBirthday() {
      return this.zzda;
   }

   public final String getContentUrl() {
      return this.zzAk;
   }

   public final int getGender() {
      return this.zzAe;
   }

   public final Set getKeywords() {
      return this.zzdc;
   }

   public final Location getLocation() {
      return this.zzde;
   }

   public final boolean getManualImpressionsEnabled() {
      return this.zzsu;
   }

   /** @deprecated */
   @Deprecated
   public final NetworkExtras getNetworkExtras(Class var1) {
      return (NetworkExtras)this.zzAT.get(var1);
   }

   public final Bundle getNetworkExtrasBundle(Class var1) {
      return this.zzAS.getBundle(var1.getName());
   }

   public final Bundle getCustomEventExtrasBundle(Class var1) {
      Bundle var2;
      return (var2 = this.zzAS.getBundle("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter")) != null ? var2.getBundle(var1.getName()) : null;
   }

   public final String getPublisherProvidedId() {
      return this.zzAi;
   }

   public final String zzdx() {
      return this.zzAo;
   }

   public final SearchAdRequest zzdy() {
      return this.zzAU;
   }

   public final boolean isTestDevice(Context var1) {
      Set var10000 = this.zzAV;
      zzji.zzds();
      return var10000.contains(zzaiy.zzV(var1));
   }

   public final Map zzdz() {
      return this.zzAT;
   }

   public final Bundle zzdA() {
      return this.zzAS;
   }

   public final int zzdB() {
      return this.zzAh;
   }

   public final Bundle getCustomTargeting() {
      return this.zzAm;
   }

   public final Set zzdC() {
      return this.zzAW;
   }

   public final boolean isDesignedForFamilies() {
      return this.zzAq;
   }
}
