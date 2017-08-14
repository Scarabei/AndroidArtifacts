package com.google.android.gms.auth.api.proxy;

import android.app.PendingIntent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.annotation.KeepForSdkWithMembers;
import com.google.android.gms.common.internal.safeparcel.zzd;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

@KeepForSdkWithMembers
public class ProxyResponse extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzb();
   public static final int STATUS_CODE_NO_CONNECTION = -1;
   private int versionCode;
   public final int googlePlayServicesStatusCode;
   public final PendingIntent recoveryAction;
   public final int statusCode;
   private Bundle zzalF;
   public final byte[] body;

   public static ProxyResponse createErrorProxyResponse(int var0, PendingIntent var1, int var2, Map var3, byte[] var4) {
      return new ProxyResponse(1, var0, var1, var2, zzn(var3), var4);
   }

   private static Bundle zzn(Map var0) {
      Bundle var1 = new Bundle();
      if (var0 == null) {
         return var1;
      } else {
         Iterator var2 = var0.entrySet().iterator();

         while(var2.hasNext()) {
            Entry var3 = (Entry)var2.next();
            var1.putString((String)var3.getKey(), (String)var3.getValue());
         }

         return var1;
      }
   }

   ProxyResponse(int var1, int var2, PendingIntent var3, int var4, Bundle var5, byte[] var6) {
      this.versionCode = var1;
      this.googlePlayServicesStatusCode = var2;
      this.statusCode = var4;
      this.zzalF = var5;
      this.body = var6;
      this.recoveryAction = var3;
   }

   public ProxyResponse(int var1, PendingIntent var2, int var3, Bundle var4, byte[] var5) {
      this(1, var1, var2, var3, var4, var5);
   }

   private ProxyResponse(int var1, Bundle var2, byte[] var3) {
      this(1, 0, (PendingIntent)null, var1, var2, var3);
   }

   public ProxyResponse(int var1, Map var2, byte[] var3) {
      this(var1, zzn(var2), var3);
   }

   public Map getHeaders() {
      if (this.zzalF == null) {
         return Collections.emptyMap();
      } else {
         HashMap var1 = new HashMap();
         Iterator var2 = this.zzalF.keySet().iterator();

         while(var2.hasNext()) {
            String var3 = (String)var2.next();
            var1.put(var3, this.zzalF.getString(var3));
         }

         return var1;
      }
   }

   public void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zzc(var1, 1, this.googlePlayServicesStatusCode);
      zzd.zza(var1, 2, this.recoveryAction, var2, false);
      zzd.zzc(var1, 3, this.statusCode);
      zzd.zza(var1, 4, this.zzalF, false);
      zzd.zza(var1, 5, this.body, false);
      zzd.zzc(var1, 1000, this.versionCode);
      zzd.zzI(var1, var5);
   }
}
