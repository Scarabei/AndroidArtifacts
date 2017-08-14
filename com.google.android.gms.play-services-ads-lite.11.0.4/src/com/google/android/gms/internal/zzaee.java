package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.common.internal.zzbe;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;
import java.util.Arrays;
import org.json.JSONArray;
import org.json.JSONException;

@zzzn
public final class zzaee extends zza {
   public static final Creator CREATOR = new zzaef();
   public final String type;
   public final int zzWW;

   public zzaee(RewardItem var1) {
      this(var1.getType(), var1.getAmount());
   }

   public zzaee(String var1, int var2) {
      this.type = var1;
      this.zzWW = var2;
   }

   @Nullable
   public static zzaee zza(JSONArray var0) throws JSONException {
      return var0 != null && var0.length() != 0 ? new zzaee(var0.getJSONObject(0).optString("rb_type"), var0.getJSONObject(0).optInt("rb_amount")) : null;
   }

   @Nullable
   public static zzaee zzaz(@Nullable String var0) {
      if (TextUtils.isEmpty(var0)) {
         return null;
      } else {
         try {
            return zza(new JSONArray(var0));
         } catch (JSONException var1) {
            return null;
         }
      }
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zza(var1, 2, this.type, false);
      zzd.zzc(var1, 3, this.zzWW);
      zzd.zzI(var1, var5);
   }

   public final boolean equals(Object var1) {
      if (var1 != null && var1 instanceof zzaee) {
         zzaee var2 = (zzaee)var1;
         return zzbe.equal(this.type, var2.type) && zzbe.equal(this.zzWW, var2.zzWW);
      } else {
         return false;
      }
   }

   public final int hashCode() {
      return Arrays.hashCode(new Object[]{this.type, this.zzWW});
   }
}
