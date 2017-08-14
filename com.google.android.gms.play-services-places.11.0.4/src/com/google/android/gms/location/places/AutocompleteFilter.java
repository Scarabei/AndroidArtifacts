package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbe;
import java.util.Arrays;
import java.util.List;

public class AutocompleteFilter extends com.google.android.gms.common.internal.safeparcel.zza implements ReflectedParcelable {
   public static final int TYPE_FILTER_NONE = 0;
   public static final int TYPE_FILTER_GEOCODE = 1007;
   public static final int TYPE_FILTER_ADDRESS = 2;
   public static final int TYPE_FILTER_ESTABLISHMENT = 34;
   public static final int TYPE_FILTER_REGIONS = 4;
   public static final int TYPE_FILTER_CITIES = 5;
   public static final Creator CREATOR = new zzc();
   private int zzaku;
   private boolean zzbjm;
   private List zzbjn;
   private String zzbjo;
   private int zzbjp;

   AutocompleteFilter(int var1, boolean var2, List var3, String var4) {
      this.zzaku = var1;
      this.zzbjn = var3;
      this.zzbjp = var3 != null && !var3.isEmpty() ? ((Integer)var3.iterator().next()).intValue() : 0;
      this.zzbjo = var4;
      if (this.zzaku <= 0) {
         this.zzbjm = !var2;
      } else {
         this.zzbjm = var2;
      }
   }

   public int getTypeFilter() {
      return this.zzbjp;
   }

   public int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzbjm, this.zzbjp, this.zzbjo});
   }

   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof AutocompleteFilter)) {
         return false;
      } else {
         AutocompleteFilter var2 = (AutocompleteFilter)var1;
         return this.zzbjp == var2.zzbjp && this.zzbjm == var2.zzbjm && this.zzbjo == var2.zzbjo;
      }
   }

   public String toString() {
      return zzbe.zzt(this).zzg("includeQueryPredictions", this.zzbjm).zzg("typeFilter", this.zzbjp).zzg("country", this.zzbjo).toString();
   }

   public void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 1, this.zzbjm);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzbjn, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.zzbjo, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1000, this.zzaku);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public static final class Builder {
      private boolean zzbjm = false;
      private int zzbjp = 0;
      private String zzbjo = "";

      public final AutocompleteFilter.Builder setTypeFilter(int var1) {
         this.zzbjp = var1;
         return this;
      }

      public final AutocompleteFilter.Builder setCountry(String var1) {
         this.zzbjo = var1;
         return this;
      }

      public final AutocompleteFilter build() {
         return new AutocompleteFilter(1, false, Arrays.asList(this.zzbjp), this.zzbjo);
      }
   }
}
