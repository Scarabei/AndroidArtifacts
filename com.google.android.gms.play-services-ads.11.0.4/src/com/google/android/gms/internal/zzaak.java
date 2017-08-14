package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzzn
public final class zzaak extends zza {
   public static final Creator CREATOR = new zzaal();
   private boolean zzTN;
   @Nullable
   private List zzTO;

   public zzaak() {
      this(false, Collections.emptyList());
   }

   public zzaak(boolean var1, List var2) {
      this.zzTN = var1;
      this.zzTO = var2;
   }

   @Nullable
   public static zzaak zze(JSONObject var0) {
      if (var0 == null) {
         return new zzaak();
      } else {
         JSONArray var1 = var0.optJSONArray("reporting_urls");
         ArrayList var2 = new ArrayList();
         if (var1 != null) {
            for(int var3 = 0; var3 < var1.length(); ++var3) {
               try {
                  var2.add(var1.getString(var3));
               } catch (JSONException var5) {
                  zzafr.zzc("Error grabbing url from json.", var5);
               }
            }
         }

         return new zzaak(var0.optBoolean("enable_protection"), var2);
      }
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zza(var1, 2, this.zzTN);
      zzd.zzb(var1, 3, this.zzTO, false);
      zzd.zzI(var1, var5);
   }
}
