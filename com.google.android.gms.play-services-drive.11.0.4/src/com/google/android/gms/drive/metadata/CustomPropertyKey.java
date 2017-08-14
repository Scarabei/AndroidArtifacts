package com.google.android.gms.drive.metadata;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbo;
import java.util.regex.Pattern;
import org.json.JSONException;
import org.json.JSONObject;

public class CustomPropertyKey extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzc();
   public static final int PUBLIC = 0;
   public static final int PRIVATE = 1;
   private String zzBN;
   private int mVisibility;
   private static final Pattern zzaPF = Pattern.compile("[\\w.!@$%^&*()/-]+");

   public CustomPropertyKey(String var1, int var2) {
      zzbo.zzb(var1, "key");
      zzbo.zzb(zzaPF.matcher(var1).matches(), "key name characters must be alphanumeric or one of .!@$%^&*()-_/");
      zzbo.zzb(var2 == 0 || var2 == 1, "visibility must be either PUBLIC or PRIVATE");
      this.zzBN = var1;
      this.mVisibility = var2;
   }

   public void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzBN, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 3, this.mVisibility);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public String getKey() {
      return this.zzBN;
   }

   public int getVisibility() {
      return this.mVisibility;
   }

   public int hashCode() {
      String var1 = this.zzBN;
      int var2 = this.mVisibility;
      return (new StringBuilder(11 + String.valueOf(var1).length())).append(var1).append(var2).toString().hashCode();
   }

   public boolean equals(Object var1) {
      if (var1 == null) {
         return false;
      } else if (var1 == this) {
         return true;
      } else if (!(var1 instanceof CustomPropertyKey)) {
         return false;
      } else {
         CustomPropertyKey var2;
         return (var2 = (CustomPropertyKey)var1).getKey().equals(this.zzBN) && var2.getVisibility() == this.mVisibility;
      }
   }

   public String toString() {
      String var1 = this.zzBN;
      int var2 = this.mVisibility;
      return (new StringBuilder(31 + String.valueOf(var1).length())).append("CustomPropertyKey(").append(var1).append(",").append(var2).append(")").toString();
   }

   public JSONObject toJson() throws JSONException {
      JSONObject var1;
      (var1 = new JSONObject()).put("key", this.getKey());
      var1.put("visibility", this.getVisibility());
      return var1;
   }

   public static CustomPropertyKey fromJson(JSONObject var0) throws JSONException {
      return new CustomPropertyKey(var0.getString("key"), var0.getInt("visibility"));
   }
}
