package com.google.android.gms.auth.api.proxy;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.util.Patterns;
import com.google.android.gms.common.annotation.KeepForSdkWithMembers;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.common.internal.safeparcel.zzd;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

@KeepForSdkWithMembers
public class ProxyRequest extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final int VERSION_CODE = 2;
   public static final Creator CREATOR = new zza();
   public static final int HTTP_METHOD_GET = 0;
   public static final int HTTP_METHOD_POST = 1;
   public static final int HTTP_METHOD_PUT = 2;
   public static final int HTTP_METHOD_DELETE = 3;
   public static final int HTTP_METHOD_HEAD = 4;
   public static final int HTTP_METHOD_OPTIONS = 5;
   public static final int HTTP_METHOD_TRACE = 6;
   public static final int HTTP_METHOD_PATCH = 7;
   public static final int LAST_CODE = 7;
   private int versionCode;
   public final String url;
   public final int httpMethod;
   public final long timeoutMillis;
   public final byte[] body;
   private Bundle zzalF;

   ProxyRequest(int var1, String var2, int var3, long var4, byte[] var6, Bundle var7) {
      this.versionCode = var1;
      this.url = var2;
      this.httpMethod = var3;
      this.timeoutMillis = var4;
      this.body = var6;
      this.zzalF = var7;
   }

   public Map getHeaderMap() {
      LinkedHashMap var1 = new LinkedHashMap(this.zzalF.size());
      Iterator var2 = this.zzalF.keySet().iterator();

      while(var2.hasNext()) {
         String var3 = (String)var2.next();
         var1.put(var3, this.zzalF.getString(var3));
      }

      return Collections.unmodifiableMap(var1);
   }

   public String toString() {
      String var1 = this.url;
      int var2 = this.httpMethod;
      return (new StringBuilder(42 + String.valueOf(var1).length())).append("ProxyRequest[ url: ").append(var1).append(", method: ").append(var2).append(" ]").toString();
   }

   public void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zza(var1, 1, this.url, false);
      zzd.zzc(var1, 2, this.httpMethod);
      zzd.zza(var1, 3, this.timeoutMillis);
      zzd.zza(var1, 4, this.body, false);
      zzd.zza(var1, 5, this.zzalF, false);
      zzd.zzc(var1, 1000, this.versionCode);
      zzd.zzI(var1, var5);
   }

   @KeepForSdkWithMembers
   public static class Builder {
      private String zzalG;
      private int zzalH;
      private long zzWC;
      private byte[] zzalI;
      private Bundle zzalJ;

      public Builder(String var1) {
         this.zzalH = ProxyRequest.HTTP_METHOD_GET;
         this.zzWC = 3000L;
         this.zzalI = null;
         this.zzalJ = new Bundle();
         zzbo.zzcF(var1);
         if (Patterns.WEB_URL.matcher(var1).matches()) {
            this.zzalG = var1;
         } else {
            throw new IllegalArgumentException((new StringBuilder(51 + String.valueOf(var1).length())).append("The supplied url [ ").append(var1).append("] is not match Patterns.WEB_URL!").toString());
         }
      }

      public ProxyRequest.Builder setHttpMethod(int var1) {
         zzbo.zzb(var1 >= 0 && var1 <= ProxyRequest.LAST_CODE, "Unrecognized http method code.");
         this.zzalH = var1;
         return this;
      }

      public ProxyRequest.Builder setTimeoutMillis(long var1) {
         zzbo.zzb(var1 >= 0L, "The specified timeout must be non-negative.");
         this.zzWC = var1;
         return this;
      }

      public ProxyRequest.Builder putHeader(String var1, String var2) {
         zzbo.zzh(var1, "Header name cannot be null or empty!");
         this.zzalJ.putString(var1, var2 == null ? "" : var2);
         return this;
      }

      public ProxyRequest.Builder setBody(byte[] var1) {
         this.zzalI = var1;
         return this;
      }

      public ProxyRequest build() {
         if (this.zzalI == null) {
            this.zzalI = new byte[0];
         }

         return new ProxyRequest(2, this.zzalG, this.zzalH, this.zzWC, this.zzalI, this.zzalJ);
      }
   }
}
