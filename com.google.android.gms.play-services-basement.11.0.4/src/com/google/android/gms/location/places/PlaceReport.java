package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbe;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;
import java.util.Arrays;

public class PlaceReport extends zza implements ReflectedParcelable {
   public static final Creator CREATOR = new zzl();
   private int zzaku;
   private final String zzbjI;
   private final String mTag;
   private final String zzaeK;

   public static PlaceReport create(String var0, String var1) {
      String var4 = "unknown";
      zzbo.zzu(var0);
      zzbo.zzcF(var1);
      zzbo.zzcF(var4);
      byte var6 = -1;
      switch(var4.hashCode()) {
      case -1436706272:
         if (var4.equals("inferredGeofencing")) {
            var6 = 2;
         }
         break;
      case -1194968642:
         if (var4.equals("userReported")) {
            var6 = 1;
         }
         break;
      case -284840886:
         if (var4.equals("unknown")) {
            var6 = 0;
         }
         break;
      case -262743844:
         if (var4.equals("inferredReverseGeocoding")) {
            var6 = 4;
         }
         break;
      case 1164924125:
         if (var4.equals("inferredSnappedToRoad")) {
            var6 = 5;
         }
         break;
      case 1287171955:
         if (var4.equals("inferredRadioSignals")) {
            var6 = 3;
         }
      }

      boolean var10000;
      switch(var6) {
      case 0:
      case 1:
      case 2:
      case 3:
      case 4:
      case 5:
         var10000 = true;
         break;
      default:
         var10000 = false;
      }

      zzbo.zzb(var10000, "Invalid source");
      return new PlaceReport(1, var0, var1, var4);
   }

   PlaceReport(int var1, String var2, String var3, String var4) {
      this.zzaku = var1;
      this.zzbjI = var2;
      this.mTag = var3;
      this.zzaeK = var4;
   }

   public String getPlaceId() {
      return this.zzbjI;
   }

   public String getTag() {
      return this.mTag;
   }

   public String toString() {
      zzbg var1;
      (var1 = zzbe.zzt(this)).zzg("placeId", this.zzbjI);
      var1.zzg("tag", this.mTag);
      if (!"unknown".equals(this.zzaeK)) {
         var1.zzg("source", this.zzaeK);
      }

      return var1.toString();
   }

   public boolean equals(Object var1) {
      if (!(var1 instanceof PlaceReport)) {
         return false;
      } else {
         PlaceReport var2 = (PlaceReport)var1;
         return zzbe.equal(this.zzbjI, var2.zzbjI) && zzbe.equal(this.mTag, var2.mTag) && zzbe.equal(this.zzaeK, var2.zzaeK);
      }
   }

   public int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzbjI, this.mTag, this.zzaeK});
   }

   public void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zzc(var1, 1, this.zzaku);
      zzd.zza(var1, 2, (String)this.getPlaceId(), false);
      zzd.zza(var1, 3, (String)this.getTag(), false);
      zzd.zza(var1, 4, (String)this.zzaeK, false);
      zzd.zzI(var1, var5);
   }
}
