package com.google.android.gms.drive.query;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.drive.query.internal.zzr;
import com.google.android.gms.drive.query.internal.zzt;
import com.google.android.gms.drive.query.internal.zzx;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public class Query extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzb();
   private zzr zzaQR;
   private String zzaQS;
   private SortOrder zzaQT;
   final List zzaQU;
   final boolean zzaQV;
   private List zzaMU;
   final boolean zzaQW;
   private final Set zzaMV;

   private Query(zzr var1, String var2, SortOrder var3, List var4, boolean var5, List var6, Set var7, boolean var8) {
      this.zzaQR = var1;
      this.zzaQS = var2;
      this.zzaQT = var3;
      this.zzaQU = var4;
      this.zzaQV = var5;
      this.zzaMU = var6;
      this.zzaMV = var7;
      this.zzaQW = var8;
   }

   private Query(zzr var1, String var2, SortOrder var3, List var4, boolean var5, Set var6, boolean var7) {
      this(var1, var2, var3, var4, var5, var6 == null ? null : new ArrayList(var6), var6, var7);
   }

   Query(zzr var1, String var2, SortOrder var3, List var4, boolean var5, List var6, boolean var7) {
      this(var1, var2, var3, var4, var5, var6, var6 == null ? null : new HashSet(var6), var7);
   }

   public Filter getFilter() {
      return this.zzaQR;
   }

   /** @deprecated */
   @Deprecated
   public String getPageToken() {
      return this.zzaQS;
   }

   public SortOrder getSortOrder() {
      return this.zzaQT;
   }

   public final Set zztt() {
      return this.zzaMV;
   }

   public void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 1, this.zzaQR, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.zzaQS, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.zzaQT, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzb(var1, 5, this.zzaQU, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 6, this.zzaQV);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 7, this.zzaMU, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 8, this.zzaQW);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public String toString() {
      return String.format(Locale.US, "Query[%s,%s,PageToken=%s,Spaces=%s]", this.zzaQR, this.zzaQT, this.zzaQS, this.zzaMU);
   }

   // $FF: synthetic method
   Query(zzr var1, String var2, SortOrder var3, List var4, boolean var5, Set var6, boolean var7, zza var8) {
      this(var1, var2, var3, var4, var5, var6, var7);
   }

   public static class Builder {
      private final List zzaQX = new ArrayList();
      private String zzaQS;
      private SortOrder zzaQT;
      private List zzaQU;
      private boolean zzaQV;
      private Set zzaMV;
      private boolean zzaQW;

      public Builder() {
      }

      public Builder(Query var1) {
         this.zzaQX.add(var1.getFilter());
         this.zzaQS = var1.getPageToken();
         this.zzaQT = var1.getSortOrder();
         this.zzaQU = var1.zzaQU;
         this.zzaQV = var1.zzaQV;
         this.zzaMV = var1.zztt();
         this.zzaQW = var1.zzaQW;
      }

      public Query.Builder addFilter(Filter var1) {
         if (!(var1 instanceof zzt)) {
            this.zzaQX.add(var1);
         }

         return this;
      }

      public Query.Builder setSortOrder(SortOrder var1) {
         this.zzaQT = var1;
         return this;
      }

      /** @deprecated */
      @Deprecated
      public Query.Builder setPageToken(String var1) {
         this.zzaQS = var1;
         return this;
      }

      public Query build() {
         return new Query(new zzr(zzx.zzaRz, this.zzaQX), this.zzaQS, this.zzaQT, this.zzaQU, this.zzaQV, this.zzaMV, this.zzaQW, (zza)null);
      }
   }
}
