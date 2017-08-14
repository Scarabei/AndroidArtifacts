package com.google.android.gms.wearable.internal;

import android.net.Uri;
import android.util.Log;
import com.google.android.gms.wearable.DataItem;
import com.google.android.gms.wearable.DataItemAsset;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public final class zzca implements DataItem {
   private Uri mUri;
   private byte[] zzbdY;
   private Map zzbSE;

   public zzca(DataItem var1) {
      this.mUri = var1.getUri();
      this.zzbdY = var1.getData();
      HashMap var2 = new HashMap();
      Iterator var3 = var1.getAssets().entrySet().iterator();

      while(var3.hasNext()) {
         Entry var4;
         if ((var4 = (Entry)var3.next()).getKey() != null) {
            var2.put((String)var4.getKey(), (DataItemAsset)((DataItemAsset)var4.getValue()).freeze());
         }
      }

      this.zzbSE = Collections.unmodifiableMap(var2);
   }

   public final boolean isDataValid() {
      return true;
   }

   public final Uri getUri() {
      return this.mUri;
   }

   public final byte[] getData() {
      return this.zzbdY;
   }

   public final Map getAssets() {
      return this.zzbSE;
   }

   public final DataItem setData(byte[] var1) {
      throw new UnsupportedOperationException();
   }

   public final String toString() {
      boolean var2 = Log.isLoggable("DataItem", 3);
      StringBuilder var3;
      StringBuilder var10000 = var3 = new StringBuilder("DataItemEntity{ ");
      String var4 = String.valueOf(this.mUri);
      var10000.append((new StringBuilder(4 + String.valueOf(var4).length())).append("uri=").append(var4).toString());
      var4 = String.valueOf(this.zzbdY == null ? "null" : this.zzbdY.length);
      var3.append((new StringBuilder(9 + String.valueOf(var4).length())).append(", dataSz=").append(var4).toString());
      int var10 = this.zzbSE.size();
      var3.append((new StringBuilder(23)).append(", numAssets=").append(var10).toString());
      if (var2 && !this.zzbSE.isEmpty()) {
         var3.append(", assets=[");
         var4 = "";

         for(Iterator var5 = this.zzbSE.entrySet().iterator(); var5.hasNext(); var4 = ", ") {
            Entry var6 = (Entry)var5.next();
            String var8 = (String)var6.getKey();
            String var9 = String.valueOf(((DataItemAsset)var6.getValue()).getId());
            var3.append((new StringBuilder(2 + String.valueOf(var4).length() + String.valueOf(var8).length() + String.valueOf(var9).length())).append(var4).append(var8).append(": ").append(var9).toString());
         }

         var3.append("]");
      }

      var3.append(" }");
      return var3.toString();
   }
}
