package com.google.android.gms.ads;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import com.google.android.gms.common.annotation.KeepForSdkWithMembers;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.internal.zzajc;
import com.google.android.gms.internal.zzji;
import com.google.android.gms.internal.zzwx;

@KeepForSdkWithMembers
public class AdActivity extends Activity {
   public static final String CLASS_NAME = "com.google.android.gms.ads.AdActivity";
   public static final String SIMPLE_CLASS_NAME = "AdActivity";
   private zzwx zzrP;

   protected void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.zzrP = zzji.zzdt().zzb((Activity)this);
      if (this.zzrP == null) {
         zzajc.zzaT("Could not create ad overlay.");
         this.finish();
      } else {
         try {
            this.zzrP.onCreate(var1);
         } catch (RemoteException var3) {
            zzajc.zzc("Could not forward onCreate to ad overlay:", var3);
            this.finish();
         }
      }
   }

   protected void onRestart() {
      super.onRestart();

      try {
         if (this.zzrP != null) {
            this.zzrP.onRestart();
         }

      } catch (RemoteException var2) {
         zzajc.zzc("Could not forward onRestart to ad overlay:", var2);
         this.finish();
      }
   }

   protected void onStart() {
      super.onStart();

      try {
         if (this.zzrP != null) {
            this.zzrP.onStart();
         }

      } catch (RemoteException var2) {
         zzajc.zzc("Could not forward onStart to ad overlay:", var2);
         this.finish();
      }
   }

   protected void onResume() {
      super.onResume();

      try {
         if (this.zzrP != null) {
            this.zzrP.onResume();
         }

      } catch (RemoteException var2) {
         zzajc.zzc("Could not forward onResume to ad overlay:", var2);
         this.finish();
      }
   }

   protected void onPause() {
      try {
         if (this.zzrP != null) {
            this.zzrP.onPause();
         }
      } catch (RemoteException var2) {
         zzajc.zzc("Could not forward onPause to ad overlay:", var2);
         this.finish();
      }

      super.onPause();
   }

   protected void onSaveInstanceState(Bundle var1) {
      try {
         if (this.zzrP != null) {
            this.zzrP.onSaveInstanceState(var1);
         }
      } catch (RemoteException var3) {
         zzajc.zzc("Could not forward onSaveInstanceState to ad overlay:", var3);
         this.finish();
      }

      super.onSaveInstanceState(var1);
   }

   protected void onStop() {
      try {
         if (this.zzrP != null) {
            this.zzrP.onStop();
         }
      } catch (RemoteException var2) {
         zzajc.zzc("Could not forward onStop to ad overlay:", var2);
         this.finish();
      }

      super.onStop();
   }

   protected void onDestroy() {
      try {
         if (this.zzrP != null) {
            this.zzrP.onDestroy();
         }
      } catch (RemoteException var2) {
         zzajc.zzc("Could not forward onDestroy to ad overlay:", var2);
      }

      super.onDestroy();
   }

   private final void zzaa() {
      if (this.zzrP != null) {
         try {
            this.zzrP.zzaa();
            return;
         } catch (RemoteException var2) {
            zzajc.zzc("Could not forward setContentViewSet to ad overlay:", var2);
         }
      }

   }

   public void setContentView(int var1) {
      super.setContentView(var1);
      this.zzaa();
   }

   public void setContentView(View var1) {
      super.setContentView(var1);
      this.zzaa();
   }

   public void setContentView(View var1, LayoutParams var2) {
      super.setContentView(var1, var2);
      this.zzaa();
   }

   public void onBackPressed() {
      boolean var1 = true;

      try {
         if (this.zzrP != null) {
            var1 = this.zzrP.zzfK();
         }
      } catch (RemoteException var3) {
         zzajc.zzc("Could not forward onBackPressed to ad overlay:", var3);
      }

      if (var1) {
         super.onBackPressed();
      }

   }

   protected void onActivityResult(int var1, int var2, Intent var3) {
      try {
         this.zzrP.onActivityResult(var1, var2, var3);
      } catch (Exception var5) {
         zzajc.zzc("Could not forward onActivityResult to ad overlay:", var5);
      }

      super.onActivityResult(var1, var2, var3);
   }

   public void onConfigurationChanged(Configuration var1) {
      super.onConfigurationChanged(var1);

      try {
         this.zzrP.zzo(zzn.zzw(var1));
      } catch (RemoteException var3) {
         zzajc.zzc("Failed to wrap configuration.", var3);
      }
   }
}
