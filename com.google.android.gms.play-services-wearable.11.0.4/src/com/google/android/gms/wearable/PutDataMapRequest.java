package com.google.android.gms.wearable;

import android.net.Uri;
import android.util.Log;
import com.google.android.gms.internal.adp;
import com.google.android.gms.internal.hc;
import com.google.android.gms.internal.hd;

public class PutDataMapRequest {
   private final PutDataRequest zzbRg;
   private final DataMap zzbRf;

   private PutDataMapRequest(PutDataRequest var1, DataMap var2) {
      this.zzbRg = var1;
      this.zzbRf = new DataMap();
      if (var2 != null) {
         this.zzbRf.putAll(var2);
      }

   }

   public static PutDataMapRequest createFromDataMapItem(DataMapItem var0) {
      return new PutDataMapRequest(PutDataRequest.zzt(var0.getUri()), var0.getDataMap());
   }

   public static PutDataMapRequest createWithAutoAppendedId(String var0) {
      return new PutDataMapRequest(PutDataRequest.createWithAutoAppendedId(var0), (DataMap)null);
   }

   public static PutDataMapRequest create(String var0) {
      return new PutDataMapRequest(PutDataRequest.create(var0), (DataMap)null);
   }

   public Uri getUri() {
      return this.zzbRg.getUri();
   }

   public DataMap getDataMap() {
      return this.zzbRf;
   }

   public PutDataMapRequest setUrgent() {
      this.zzbRg.setUrgent();
      return this;
   }

   public boolean isUrgent() {
      return this.zzbRg.isUrgent();
   }

   public PutDataRequest asPutDataRequest() {
      hd var1 = hc.zza(this.zzbRf);
      this.zzbRg.setData(adp.zzc(var1.zzbTF));
      int var2 = var1.zzbTG.size();

      for(int var3 = 0; var3 < var2; ++var3) {
         String var4 = Integer.toString(var3);
         Asset var5 = (Asset)var1.zzbTG.get(var3);
         String var6;
         if (var4 == null) {
            var6 = String.valueOf(var5);
            throw new IllegalStateException((new StringBuilder(26 + String.valueOf(var6).length())).append("asset key cannot be null: ").append(var6).toString());
         }

         if (var5 == null) {
            IllegalStateException var10000 = new IllegalStateException;
            String var10003 = String.valueOf(var4);
            String var10002;
            if (var10003.length() != 0) {
               var10002 = "asset cannot be null: key=".concat(var10003);
            } else {
               String var10004 = new String;
               var10002 = var10004;
               var10004.<init>("asset cannot be null: key=");
            }

            var10000.<init>(var10002);
            throw var10000;
         }

         if (Log.isLoggable("DataMap", 3)) {
            var6 = String.valueOf(var5);
            Log.d("DataMap", (new StringBuilder(33 + String.valueOf(var4).length() + String.valueOf(var6).length())).append("asPutDataRequest: adding asset: ").append(var4).append(" ").append(var6).toString());
         }

         this.zzbRg.putAsset(var4, var5);
      }

      return this.zzbRg;
   }
}
