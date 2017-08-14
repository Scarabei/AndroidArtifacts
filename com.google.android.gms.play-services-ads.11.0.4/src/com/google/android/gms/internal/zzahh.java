package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.app.DownloadManager.Request;
import android.content.Context;
import android.graphics.Paint;
import android.net.Uri;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import java.util.Set;

@TargetApi(11)
public class zzahh extends zzahg {
   public boolean zza(Context var1, WebSettings var2) {
      super.zza(var1, var2);
      return ((Boolean)zzait.zzb(new zzahi(this, var1, var2))).booleanValue();
   }

   public final boolean zzr(View var1) {
      var1.setLayerType(1, (Paint)null);
      return true;
   }

   public final boolean zzq(View var1) {
      var1.setLayerType(0, (Paint)null);
      return true;
   }

   public final boolean zza(Window var1) {
      var1.setFlags(16777216, 16777216);
      return true;
   }

   public zzakb zzb(zzaka var1, boolean var2) {
      return new zzale(var1, var2);
   }

   public WebChromeClient zzm(zzaka var1) {
      return new zzakw(var1);
   }

   public final Set zzh(Uri var1) {
      return var1.getQueryParameterNames();
   }

   public final boolean zza(Request var1) {
      var1.allowScanningByMediaScanner();
      var1.setNotificationVisibility(1);
      return true;
   }
}
