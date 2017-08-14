package com.google.android.gms.internal;

import java.net.URI;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;

public final class zzal extends HttpEntityEnclosingRequestBase {
   public zzal() {
   }

   public zzal(String var1) {
      this.setURI(URI.create(var1));
   }

   public final String getMethod() {
      return "PATCH";
   }
}
