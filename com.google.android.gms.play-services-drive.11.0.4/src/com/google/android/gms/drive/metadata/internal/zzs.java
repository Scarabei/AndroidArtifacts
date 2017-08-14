package com.google.android.gms.drive.metadata.internal;

import android.os.Bundle;
import com.google.android.gms.common.data.DataHolder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import org.json.JSONArray;
import org.json.JSONException;

public final class zzs extends com.google.android.gms.drive.metadata.zzb {
   public zzs(String var1, int var2) {
      super(var1, Collections.singleton(var1), Collections.emptySet(), 4300000);
   }

   protected final Collection zzd(DataHolder var1, int var2, int var3) {
      try {
         String var5;
         if ((var5 = var1.zzd(this.getName(), var2, var3)) == null) {
            return null;
         } else {
            ArrayList var6 = new ArrayList();
            JSONArray var7 = new JSONArray(var5);

            for(int var8 = 0; var8 < var7.length(); ++var8) {
               var6.add(var7.getString(var8));
            }

            return Collections.unmodifiableCollection(var6);
         }
      } catch (JSONException var9) {
         throw new IllegalStateException("DataHolder supplied invalid JSON", var9);
      }
   }

   // $FF: synthetic method
   protected final Object zzc(DataHolder var1, int var2, int var3) {
      return this.zzd(var1, var2, var3);
   }

   // $FF: synthetic method
   protected final void zza(Bundle var1, Object var2) {
      Collection var5 = (Collection)var2;
      var1.putStringArrayList(this.getName(), new ArrayList(var5));
   }

   // $FF: synthetic method
   protected final Object zzq(Bundle var1) {
      return var1.getStringArrayList(this.getName());
   }
}
