package com.google.android.gms.wearable.internal;

import android.net.Uri;
import android.util.Log;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.wearable.DataItem;
import com.google.android.gms.wearable.DataItemAsset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public final class zzcd extends com.google.android.gms.common.data.zzc implements DataItem {
   private final int zzbcP;

   public zzcd(DataHolder var1, int var2, int var3) {
      super(var1, var2);
      this.zzbcP = var3;
   }

   public final Uri getUri() {
      return Uri.parse(this.getString("path"));
   }

   public final byte[] getData() {
      return this.getByteArray("data");
   }

   public final Map getAssets() {
      HashMap var1 = new HashMap(this.zzbcP);

      for(int var2 = 0; var2 < this.zzbcP; ++var2) {
         zzbz var3;
         if ((var3 = new zzbz(this.zzaCX, this.zzaFx + var2)).getDataItemKey() != null) {
            var1.put(var3.getDataItemKey(), var3);
         }
      }

      return var1;
   }

   public final DataItem setData(byte[] var1) {
      throw new UnsupportedOperationException();
   }

   public final String toString() {
      boolean var2 = Log.isLoggable("DataItem", 3);
      byte[] var3 = this.getData();
      Map var4 = this.getAssets();
      StringBuilder var5;
      StringBuilder var10000 = var5 = new StringBuilder("DataItemInternal{ ");
      String var6 = String.valueOf(this.getUri());
      var10000.append((new StringBuilder(4 + String.valueOf(var6).length())).append("uri=").append(var6).toString());
      var6 = String.valueOf(var3 == null ? "null" : var3.length);
      var5.append((new StringBuilder(9 + String.valueOf(var6).length())).append(", dataSz=").append(var6).toString());
      int var12 = var4.size();
      var5.append((new StringBuilder(23)).append(", numAssets=").append(var12).toString());
      if (var2 && !var4.isEmpty()) {
         var5.append(", assets=[");
         var6 = "";

         for(Iterator var7 = var4.entrySet().iterator(); var7.hasNext(); var6 = ", ") {
            Entry var8 = (Entry)var7.next();
            String var10 = (String)var8.getKey();
            String var11 = String.valueOf(((DataItemAsset)var8.getValue()).getId());
            var5.append((new StringBuilder(2 + String.valueOf(var6).length() + String.valueOf(var10).length() + String.valueOf(var11).length())).append(var6).append(var10).append(": ").append(var11).toString());
         }

         var5.append("]");
      }

      var5.append(" }");
      return var5.toString();
   }

   // $FF: synthetic method
   public final Object freeze() {
      return new zzca(this);
   }
}
