package com.google.android.gms.auth.api.signin.internal;

import android.accounts.Account;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.PendingResults;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.internal.zzbay;
import com.google.android.gms.internal.zzbdb;
import com.google.android.gms.internal.zzbec;
import com.google.android.gms.internal.zzbgb;
import java.util.HashSet;
import java.util.Iterator;

public final class zze {
   private static zzbgb zzaml = new zzbgb("GoogleSignInCommon", new String[0]);

   public static Intent zza(Context var0, GoogleSignInOptions var1) {
      zzaml.zzb("GoogleSignInCommon", new Object[]{"getSignInIntent()"});
      SignInConfiguration var2 = new SignInConfiguration(var0.getPackageName(), var1);
      Intent var3;
      (var3 = new Intent("com.google.android.gms.auth.GOOGLE_SIGN_IN")).setClass(var0, SignInHubActivity.class);
      var3.putExtra("config", var2);
      return var3;
   }

   public static OptionalPendingResult zza(GoogleApiClient var0, Context var1, GoogleSignInOptions var2) {
      zzy var3;
      GoogleSignInResult var14;
      label41: {
         zzy var5 = var3 = zzy.zzaj(var1);
         zzaml.zzb("GoogleSignInCommon", new Object[]{"getEligibleSavedSignInResult()"});
         zzbo.zzu(var2);
         GoogleSignInOptions var7;
         if ((var7 = var5.zzmO()) != null) {
            Account var10000 = var7.getAccount();
            Account var12 = var2.getAccount();
            Account var11 = var10000;
            if ((var10000 == null ? var12 == null : var11.equals(var12)) && !var2.zzmB() && (!var2.isIdTokenRequested() || var7.isIdTokenRequested() && var2.getServerClientId().equals(var7.getServerClientId()))) {
               HashSet var8 = new HashSet(var7.zzmA());
               HashSet var9 = new HashSet(var2.zzmA());
               GoogleSignInAccount var10;
               if (var8.containsAll(var9) && (var10 = var5.zzmN()) != null && !var10.zzmw()) {
                  var14 = new GoogleSignInResult(var10, Status.zzaBm);
                  break label41;
               }
            }
         }

         var14 = null;
      }

      GoogleSignInResult var4 = var14;
      if (var14 != null) {
         zzaml.zzb("GoogleSignInCommon", new Object[]{"Eligible saved sign in result found"});
         return PendingResults.zzb(var4, var0);
      } else {
         zzaml.zzb("GoogleSignInCommon", new Object[]{"trySilentSignIn()"});
         zzbay var13 = var0.zzd(new zzf(var0, var3, var2));
         return new zzbec(var13);
      }
   }

   public static PendingResult zza(GoogleApiClient var0, Context var1) {
      zzaml.zzb("GoogleSignInCommon", new Object[]{"Signing out"});
      zzai(var1);
      return var0.zze(new zzh(var0));
   }

   public static PendingResult zzb(GoogleApiClient var0, Context var1) {
      zzaml.zzb("GoogleSignInCommon", new Object[]{"Revoking access"});
      zzai(var1);
      return var0.zze(new zzj(var0));
   }

   private static void zzai(Context var0) {
      zzy.zzaj(var0).zzmP();
      Iterator var1 = GoogleApiClient.zzpk().iterator();

      while(var1.hasNext()) {
         ((GoogleApiClient)var1.next()).zzpl();
      }

      zzbdb.zzql();
   }

   public static GoogleSignInResult getSignInResultFromIntent(Intent var0) {
      if (var0 != null && (var0.hasExtra("googleSignInStatus") || var0.hasExtra("googleSignInAccount"))) {
         GoogleSignInAccount var1 = (GoogleSignInAccount)var0.getParcelableExtra("googleSignInAccount");
         Status var2 = (Status)var0.getParcelableExtra("googleSignInStatus");
         if (var1 != null) {
            var2 = Status.zzaBm;
         }

         return new GoogleSignInResult(var1, var2);
      } else {
         return null;
      }
   }
}
