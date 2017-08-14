package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.net.http.SslError;
import android.webkit.WebChromeClient;

@TargetApi(14)
public class zzahj extends zzahh {
   public final WebChromeClient zzm(zzaka var1) {
      return new zzalf(var1);
   }

   public final String zza(SslError var1) {
      return var1.getUrl();
   }

   public int zzhX() {
      return 1;
   }
}
