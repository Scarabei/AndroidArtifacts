package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.cast.Cast;
import com.google.android.gms.cast.CastMediaControlIntent;
import com.google.android.gms.cast.framework.CastOptions;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.Session;
import com.google.android.gms.cast.framework.SessionProvider;

public final class zzauk extends SessionProvider {
   private final CastOptions zzarQ;
   private final zzavb zzasB;

   public zzauk(Context var1, CastOptions var2, zzavb var3) {
      super(var1, var2.getSupportedNamespaces().isEmpty() ? CastMediaControlIntent.categoryForCast(var2.getReceiverApplicationId()) : CastMediaControlIntent.categoryForCast(var2.getReceiverApplicationId(), var2.getSupportedNamespaces()));
      this.zzarQ = var2;
      this.zzasB = var3;
   }

   public final Session createSession(String var1) {
      return new CastSession(this.getContext(), this.getCategory(), var1, this.zzarQ, Cast.CastApi, new zzaul(), new zzavn(this.getContext(), this.zzarQ, this.zzasB));
   }

   public final boolean isSessionRecoverable() {
      return this.zzarQ.getResumeSavedSession();
   }
}
