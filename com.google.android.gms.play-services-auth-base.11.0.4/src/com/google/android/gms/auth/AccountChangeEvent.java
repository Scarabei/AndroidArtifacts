package com.google.android.gms.auth;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbe;
import com.google.android.gms.common.internal.zzbo;
import java.util.Arrays;

public class AccountChangeEvent extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zza();
   private int mVersion;
   private long zzakg;
   private String zzakh;
   private int zzaki;
   private int zzakj;
   private String zzakk;

   AccountChangeEvent(int var1, long var2, String var4, int var5, int var6, String var7) {
      this.mVersion = var1;
      this.zzakg = var2;
      this.zzakh = (String)zzbo.zzu(var4);
      this.zzaki = var5;
      this.zzakj = var6;
      this.zzakk = var7;
   }

   public AccountChangeEvent(long var1, String var3, int var4, int var5, String var6) {
      this.mVersion = 1;
      this.zzakg = var1;
      this.zzakh = (String)zzbo.zzu(var3);
      this.zzaki = var4;
      this.zzakj = var5;
      this.zzakk = var6;
   }

   public String getAccountName() {
      return this.zzakh;
   }

   public int getChangeType() {
      return this.zzaki;
   }

   public int getEventIndex() {
      return this.zzakj;
   }

   public String getChangeData() {
      return this.zzakk;
   }

   public void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1, this.mVersion);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzakg);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.zzakh, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 4, this.zzaki);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 5, this.zzakj);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 6, this.zzakk, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public String toString() {
      String var1 = "UNKNOWN";
      switch(this.zzaki) {
      case 1:
         var1 = "ADDED";
         break;
      case 2:
         var1 = "REMOVED";
         break;
      case 3:
         var1 = "RENAMED_FROM";
         break;
      case 4:
         var1 = "RENAMED_TO";
      }

      String var2 = this.zzakh;
      String var4 = this.zzakk;
      int var5 = this.zzakj;
      return (new StringBuilder(91 + String.valueOf(var2).length() + String.valueOf(var1).length() + String.valueOf(var4).length())).append("AccountChangeEvent {accountName = ").append(var2).append(", changeType = ").append(var1).append(", changeData = ").append(var4).append(", eventIndex = ").append(var5).append("}").toString();
   }

   public int hashCode() {
      return Arrays.hashCode(new Object[]{this.mVersion, this.zzakg, this.zzakh, this.zzaki, this.zzakj, this.zzakk});
   }

   public boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (var1 instanceof AccountChangeEvent) {
         AccountChangeEvent var2 = (AccountChangeEvent)var1;
         return this.mVersion == var2.mVersion && this.zzakg == var2.zzakg && zzbe.equal(this.zzakh, var2.zzakh) && this.zzaki == var2.zzaki && this.zzakj == var2.zzakj && zzbe.equal(this.zzakk, var2.zzakk);
      } else {
         return false;
      }
   }
}
