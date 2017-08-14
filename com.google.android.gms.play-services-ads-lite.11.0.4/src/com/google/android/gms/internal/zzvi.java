package com.google.android.gms.internal;

import android.location.Location;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import java.util.Date;
import java.util.Set;

@zzzn
public final class zzvi implements MediationAdRequest {
   private final Date zzda;
   private final int zzAe;
   private final Set zzdc;
   private final boolean zzdd;
   private final Location zzde;
   private final int zzMZ;
   private final boolean zzAq;

   public zzvi(@Nullable Date var1, int var2, @Nullable Set var3, @Nullable Location var4, boolean var5, int var6, boolean var7) {
      this.zzda = var1;
      this.zzAe = var2;
      this.zzdc = var3;
      this.zzde = var4;
      this.zzdd = var5;
      this.zzMZ = var6;
      this.zzAq = var7;
   }

   public final Date getBirthday() {
      return this.zzda;
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

   public final boolean isTesting() {
      return this.zzdd;
   }

   public final int taggedForChildDirectedTreatment() {
      return this.zzMZ;
   }

   public final boolean isDesignedForFamilies() {
      return this.zzAq;
   }
}
