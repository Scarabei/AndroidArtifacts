package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.content.Context;
import android.os.Looper;
import android.support.annotation.NonNull;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.zzc;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import java.util.Iterator;
import java.util.Set;

public abstract class zzz extends zzd implements Api.zze, zzad {
   private final zzq zzaCA;
   private final Set zzame;
   private final Account zzajb;

   protected zzz(Context var1, Looper var2, int var3, zzq var4, GoogleApiClient.ConnectionCallbacks var5, GoogleApiClient.OnConnectionFailedListener var6) {
      this(var1, var2, zzae.zzaC(var1), GoogleApiAvailability.getInstance(), var3, var4, (GoogleApiClient.ConnectionCallbacks)zzbo.zzu(var5), (GoogleApiClient.OnConnectionFailedListener)zzbo.zzu(var6));
   }

   private zzz(Context var1, Looper var2, zzae var3, GoogleApiAvailability var4, int var5, zzq var6, GoogleApiClient.ConnectionCallbacks var7, GoogleApiClient.OnConnectionFailedListener var8) {
      super(var1, var2, var3, var4, var5, var7 == null ? null : new zzaa(var7), var8 == null ? null : new zzab(var8), var6.zzrr());
      this.zzaCA = var6;
      this.zzajb = var6.getAccount();
      Set var10;
      Set var11;
      Iterator var12 = (var11 = this.zzb(var10 = var6.zzro())).iterator();

      Scope var13;
      do {
         if (!var12.hasNext()) {
            this.zzame = var11;
            return;
         }

         var13 = (Scope)var12.next();
      } while(var10.contains(var13));

      throw new IllegalStateException("Expanding scopes is not permitted, use implied scopes instead");
   }

   @NonNull
   protected Set zzb(@NonNull Set var1) {
      return var1;
   }

   public final Account getAccount() {
      return this.zzajb;
   }

   protected final zzq zzry() {
      return this.zzaCA;
   }

   protected final Set zzrh() {
      return this.zzame;
   }

   public zzc[] zzrd() {
      return new zzc[0];
   }
}
