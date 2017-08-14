package com.google.ads.mediation;

import android.location.Location;
import com.google.ads.AdRequest;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

/** @deprecated */
@Deprecated
public class MediationAdRequest {
   private final Date zzda;
   private final AdRequest.Gender zzdb;
   private final Set zzdc;
   private final boolean zzdd;
   private final Location zzde;

   public MediationAdRequest(Date var1, AdRequest.Gender var2, Set var3, boolean var4, Location var5) {
      this.zzda = var1;
      this.zzdb = var2;
      this.zzdc = var3;
      this.zzdd = var4;
      this.zzde = var5;
   }

   public Integer getAgeInYears() {
      if (this.zzda == null) {
         return null;
      } else {
         Calendar var1 = Calendar.getInstance();
         Calendar var2 = Calendar.getInstance();
         var1.setTime(this.zzda);
         Integer var3 = var2.get(1) - var1.get(1);
         if (var2.get(2) < var1.get(2) || var2.get(2) == var1.get(2) && var2.get(5) < var1.get(5)) {
            var3 = var3.intValue() - 1;
         }

         return var3;
      }
   }

   public Date getBirthday() {
      return this.zzda;
   }

   public AdRequest.Gender getGender() {
      return this.zzdb;
   }

   public Set getKeywords() {
      return this.zzdc;
   }

   public Location getLocation() {
      return this.zzde;
   }

   public boolean isTesting() {
      return this.zzdd;
   }
}
