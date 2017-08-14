package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.fitness.data.Field;
import com.google.android.gms.internal.zzbwb;
import com.google.android.gms.internal.zzbwc;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DataTypeCreateRequest extends com.google.android.gms.common.internal.safeparcel.zza {
   private final int zzaku;
   private final String mName;
   private final List zzaWL;
   private final zzbwb zzaWM;
   public static final Creator CREATOR = new zzq();

   DataTypeCreateRequest(int var1, String var2, List var3, IBinder var4) {
      this.zzaku = var1;
      this.mName = var2;
      this.zzaWL = Collections.unmodifiableList(var3);
      this.zzaWM = zzbwc.zzR(var4);
   }

   private DataTypeCreateRequest(DataTypeCreateRequest.Builder var1) {
      this(var1.mName, var1.zzaWL, (zzbwb)null);
   }

   public DataTypeCreateRequest(DataTypeCreateRequest var1, zzbwb var2) {
      this(var1.mName, var1.zzaWL, var2);
   }

   private DataTypeCreateRequest(String var1, List var2, zzbwb var3) {
      this.zzaku = 3;
      this.mName = var1;
      this.zzaWL = Collections.unmodifiableList(var2);
      this.zzaWM = var3;
   }

   public String getName() {
      return this.mName;
   }

   public List getFields() {
      return this.zzaWL;
   }

   public boolean equals(Object var1) {
      if (var1 != this) {
         if (var1 instanceof DataTypeCreateRequest) {
            DataTypeCreateRequest var3 = (DataTypeCreateRequest)var1;
            if (com.google.android.gms.common.internal.zzbe.equal(this.mName, var3.mName) && com.google.android.gms.common.internal.zzbe.equal(this.zzaWL, var3.zzaWL)) {
               return true;
            }
         }

         return false;
      } else {
         return true;
      }
   }

   public int hashCode() {
      return Arrays.hashCode(new Object[]{this.mName, this.zzaWL});
   }

   public String toString() {
      return com.google.android.gms.common.internal.zzbe.zzt(this).zzg("name", this.mName).zzg("fields", this.zzaWL).toString();
   }

   public void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 1, this.getName(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 2, this.getFields(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.zzaWM == null ? null : this.zzaWM.asBinder(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1000, this.zzaku);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   // $FF: synthetic method
   DataTypeCreateRequest(DataTypeCreateRequest.Builder var1, zzp var2) {
      this(var1);
   }

   public static class Builder {
      private String mName;
      private List zzaWL = new ArrayList();

      public DataTypeCreateRequest.Builder setName(String var1) {
         this.mName = var1;
         return this;
      }

      public DataTypeCreateRequest.Builder addField(Field var1) {
         if (!this.zzaWL.contains(var1)) {
            this.zzaWL.add(var1);
         }

         return this;
      }

      public DataTypeCreateRequest.Builder addField(String var1, int var2) {
         zzbo.zzb(var1 != null && !var1.isEmpty(), "Invalid name specified");
         Field var3 = Field.zzm(var1, var2);
         return this.addField(var3);
      }

      public DataTypeCreateRequest build() {
         zzbo.zza(this.mName != null, "Must set the name");
         zzbo.zza(!this.zzaWL.isEmpty(), "Must specify the data fields");
         return new DataTypeCreateRequest(this, (zzp)null);
      }
   }
}
