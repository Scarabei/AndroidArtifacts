package com.google.android.gms.auth.api.signin.internal;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.SignInAccount;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;

@KeepName
public class SignInHubActivity extends FragmentActivity {
   private zzy zzamw;
   private boolean zzamx = false;
   private SignInConfiguration zzamy;
   private boolean zzamz;
   private int zzamA;
   private Intent zzamB;

   protected void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.zzamw = zzy.zzaj(this);
      Intent var2 = this.getIntent();
      if (!"com.google.android.gms.auth.GOOGLE_SIGN_IN".equals(var2.getAction())) {
         String var10002 = String.valueOf(var2.getAction());
         String var10001;
         if (var10002.length() != 0) {
            var10001 = "Unknown action: ".concat(var10002);
         } else {
            String var10003 = new String;
            var10001 = var10003;
            var10003.<init>("Unknown action: ");
         }

         Log.e("AuthSignInClient", var10001);
         this.finish();
      }

      this.zzamy = (SignInConfiguration)var2.getParcelableExtra("config");
      if (this.zzamy == null) {
         Log.e("AuthSignInClient", "Activity started with invalid configuration.");
         this.setResult(0);
         this.finish();
      } else if (var1 == null) {
         Intent var4 = new Intent("com.google.android.gms.auth.GOOGLE_SIGN_IN");
         SignInHubActivity var3 = this;
         var4.setPackage("com.google.android.gms");
         var4.putExtra("config", this.zzamy);

         try {
            var3.startActivityForResult(var4, 40962);
         } catch (ActivityNotFoundException var5) {
            this.zzamx = true;
            Log.w("AuthSignInClient", "Could not launch sign in Intent. Google Play Service is probably being updated...");
            this.zzU(17);
         }
      } else {
         this.zzamz = var1.getBoolean("signingInGoogleApiClients");
         if (this.zzamz) {
            this.zzamA = var1.getInt("signInResultCode");
            this.zzamB = (Intent)var1.getParcelable("signInResultData");
            this.zzmM();
         }

      }
   }

   protected void onSaveInstanceState(Bundle var1) {
      super.onSaveInstanceState(var1);
      var1.putBoolean("signingInGoogleApiClients", this.zzamz);
      if (this.zzamz) {
         var1.putInt("signInResultCode", this.zzamA);
         var1.putParcelable("signInResultData", this.zzamB);
      }

   }

   protected void onActivityResult(int var1, int var2, Intent var3) {
      if (!this.zzamx) {
         this.setResult(0);
         switch(var1) {
         case 40962:
            if (var3 != null) {
               SignInAccount var6;
               if ((var6 = (SignInAccount)var3.getParcelableExtra("signInAccount")) != null && var6.zzmD() != null) {
                  GoogleSignInAccount var8 = var6.zzmD();
                  this.zzamw.zza(var8, this.zzamy.zzmL());
                  var3.removeExtra("signInAccount");
                  var3.putExtra("googleSignInAccount", var8);
                  this.zzamz = true;
                  this.zzamA = var2;
                  this.zzamB = var3;
                  this.zzmM();
                  return;
               }

               if (var3.hasExtra("errorCode")) {
                  int var7 = var3.getIntExtra("errorCode", 8);
                  this.zzU(var7);
                  return;
               }
            }

            this.zzU(8);
         default:
         }
      }
   }

   public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent var1) {
      return true;
   }

   private final void zzmM() {
      this.getSupportLoaderManager().initLoader(0, (Bundle)null, new SignInHubActivity.zza((zzx)null));
   }

   private final void zzU(int var1) {
      Status var2 = new Status(var1);
      Intent var3;
      (var3 = new Intent()).putExtra("googleSignInStatus", var2);
      this.setResult(0, var3);
      this.finish();
   }

   class zza implements LoaderCallbacks {
      // $FF: synthetic field
      private SignInHubActivity zzamC;

      private zza() {
         this.zzamC = SignInHubActivity.this;
         super();
      }

      public final Loader onCreateLoader(int var1, Bundle var2) {
         return new zzb(this.zzamC, GoogleApiClient.zzpk());
      }

      public final void onLoaderReset(Loader var1) {
      }

      // $FF: synthetic method
      public final void onLoadFinished(Loader var1, Object var2) {
         this.zzamC.setResult(this.zzamC.zzamA, this.zzamC.zzamB);
         this.zzamC.finish();
      }

      // $FF: synthetic method
      zza(zzx var2) {
         this();
      }
   }
}
