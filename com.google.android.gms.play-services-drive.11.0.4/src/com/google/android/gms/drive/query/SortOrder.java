package com.google.android.gms.drive.query;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.google.android.gms.drive.metadata.SortableMetadataField;
import com.google.android.gms.drive.query.internal.zzf;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SortOrder extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzc();
   private List zzaRa;
   private boolean zzaRb;

   SortOrder(List var1, boolean var2) {
      this.zzaRa = var1;
      this.zzaRb = var2;
   }

   public void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1, this.zzaRa, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzaRb);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public String toString() {
      return String.format(Locale.US, "SortOrder[%s, %s]", TextUtils.join(",", this.zzaRa), this.zzaRb);
   }

   public static class Builder {
      private final List zzaRa = new ArrayList();
      private boolean zzaRb = false;

      public SortOrder.Builder addSortAscending(SortableMetadataField var1) {
         this.zzaRa.add(new zzf(var1.getName(), true));
         return this;
      }

      public SortOrder.Builder addSortDescending(SortableMetadataField var1) {
         this.zzaRa.add(new zzf(var1.getName(), false));
         return this;
      }

      public SortOrder build() {
         return new SortOrder(this.zzaRa, false);
      }
   }
}
