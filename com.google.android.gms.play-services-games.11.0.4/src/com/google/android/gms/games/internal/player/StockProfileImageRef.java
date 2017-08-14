package com.google.android.gms.games.internal.player;

import android.net.Uri;
import android.os.Parcel;

public class StockProfileImageRef extends com.google.android.gms.common.data.zzc implements StockProfileImage {
   public String getImageUrl() {
      return this.getString("image_url");
   }

   public final Uri zzvl() {
      return Uri.parse(this.getString("image_uri"));
   }

   public int describeContents() {
      return 0;
   }

   public void writeToParcel(Parcel var1, int var2) {
      ((StockProfileImageEntity)((StockProfileImage)this.freeze())).writeToParcel(var1, var2);
   }

   // $FF: synthetic method
   public Object freeze() {
      return new StockProfileImageEntity(this);
   }
}
