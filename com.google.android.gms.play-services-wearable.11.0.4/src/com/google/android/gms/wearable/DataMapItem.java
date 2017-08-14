package com.google.android.gms.wearable;

import android.net.Uri;
import android.util.Base64;
import android.util.Log;
import com.google.android.gms.internal.ado;
import com.google.android.gms.internal.hc;
import com.google.android.gms.internal.hd;
import com.google.android.gms.internal.he;
import java.util.ArrayList;

public class DataMapItem {
   private final Uri mUri;
   private final DataMap zzbRf;

   public static DataMapItem fromDataItem(DataItem var0) {
      if (var0 == null) {
         throw new IllegalStateException("provided dataItem is null");
      } else {
         return new DataMapItem(var0);
      }
   }

   private DataMapItem(DataItem var1) {
      this.mUri = var1.getUri();
      this.zzbRf = zza((DataItem)var1.freeze());
   }

   public Uri getUri() {
      return this.mUri;
   }

   public DataMap getDataMap() {
      return this.zzbRf;
   }

   private static DataMap zza(DataItem var0) {
      if (var0.getData() == null && var0.getAssets().size() > 0) {
         throw new IllegalArgumentException("Cannot create DataMapItem from a DataItem  that wasn't made with DataMapItem.");
      } else if (var0.getData() == null) {
         return new DataMap();
      } else {
         try {
            ArrayList var1 = new ArrayList();
            int var8 = var0.getAssets().size();

            for(int var9 = 0; var9 < var8; ++var9) {
               DataItemAsset var4;
               if ((var4 = (DataItemAsset)var0.getAssets().get(Integer.toString(var9))) == null) {
                  String var6 = String.valueOf(var0);
                  throw new IllegalStateException((new StringBuilder(64 + String.valueOf(var6).length())).append("Cannot find DataItemAsset referenced in data at ").append(var9).append(" for ").append(var6).toString());
               }

               Asset var5 = Asset.createFromRef(var4.getId());
               var1.add(var5);
            }

            he var10 = he.zzy(var0.getData());
            return hc.zza(new hd(var10, var1));
         } catch (NullPointerException | ado var7) {
            String var2 = String.valueOf(var0.getUri());
            String var3 = String.valueOf(Base64.encodeToString(var0.getData(), 0));
            Log.w("DataItem", (new StringBuilder(50 + String.valueOf(var2).length() + String.valueOf(var3).length())).append("Unable to parse datamap from dataItem. uri=").append(var2).append(", data=").append(var3).toString());
            var2 = String.valueOf(var0.getUri());
            throw new IllegalStateException((new StringBuilder(44 + String.valueOf(var2).length())).append("Unable to parse datamap from dataItem.  uri=").append(var2).toString(), var7);
         }
      }
   }
}
