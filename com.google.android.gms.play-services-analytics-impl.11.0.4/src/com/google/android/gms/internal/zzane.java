package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public final class zzane implements Parcelable {
   private String zzIi;
   private String zzagP;
   private String mValue;
   /** @deprecated */
   @Deprecated
   public static final Creator CREATOR = new zzanf();

   public final String getId() {
      return this.zzIi;
   }

   public final String getValue() {
      return this.mValue;
   }

   /** @deprecated */
   @Deprecated
   public zzane() {
   }

   /** @deprecated */
   @Deprecated
   zzane(Parcel var1) {
      this.zzIi = var1.readString();
      this.zzagP = var1.readString();
      this.mValue = var1.readString();
   }

   /** @deprecated */
   @Deprecated
   public final int describeContents() {
      return 0;
   }

   /** @deprecated */
   @Deprecated
   public final void writeToParcel(Parcel var1, int var2) {
      var1.writeString(this.zzIi);
      var1.writeString(this.zzagP);
      var1.writeString(this.mValue);
   }
}
