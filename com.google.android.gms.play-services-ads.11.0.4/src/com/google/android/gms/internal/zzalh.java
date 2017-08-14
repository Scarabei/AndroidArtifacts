package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.support.annotation.Nullable;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;

@zzzn
@TargetApi(21)
public final class zzalh extends zzalg {
   public zzalh(zzaka var1, boolean var2) {
      super(var1, var2);
   }

   @Nullable
   public final WebResourceResponse shouldInterceptRequest(WebView var1, WebResourceRequest var2) {
      return var2 != null && var2.getUrl() != null ? this.zza(var1, var2.getUrl().toString(), var2.getRequestHeaders()) : null;
   }
}
