package com.google.android.gms.wallet.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.FrameLayout;
import com.google.android.gms.R.string;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.dynamic.LifecycleDelegate;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.dynamic.zzo;
import com.google.android.gms.dynamic.zzr;
import com.google.android.gms.internal.ga;
import com.google.android.gms.internal.ge;
import com.google.android.gms.internal.gz;
import com.google.android.gms.wallet.MaskedWallet;
import com.google.android.gms.wallet.MaskedWalletRequest;

public final class SupportWalletFragment extends Fragment {
   private SupportWalletFragment.zzb zzbPZ;
   private boolean mCreated = false;
   private final zzr zzbQa = zzr.zza(this);
   private final SupportWalletFragment.zzc zzbQb = new SupportWalletFragment.zzc((zza)null);
   private SupportWalletFragment.zza zzbQc = new SupportWalletFragment.zza(this);
   private final Fragment zzaSE = this;
   private WalletFragmentOptions zzbQd;
   private WalletFragmentInitParams zzbQe;
   private MaskedWalletRequest zzbQf;
   private MaskedWallet zzbQg;
   private Boolean zzbQh;

   public static SupportWalletFragment newInstance(WalletFragmentOptions var0) {
      SupportWalletFragment var1 = new SupportWalletFragment();
      Bundle var2;
      (var2 = new Bundle()).putParcelable("extraWalletFragmentOptions", var0);
      var1.zzaSE.setArguments(var2);
      return var1;
   }

   public final void initialize(WalletFragmentInitParams var1) {
      if (this.zzbPZ != null) {
         this.zzbPZ.initialize(var1);
         this.zzbQe = null;
      } else {
         if (this.zzbQe == null) {
            this.zzbQe = var1;
            if (this.zzbQf != null) {
               Log.w("SupportWalletFragment", "updateMaskedWalletRequest() was called before initialize()");
            }

            if (this.zzbQg != null) {
               Log.w("SupportWalletFragment", "updateMaskedWallet() was called before initialize()");
               return;
            }
         } else {
            Log.w("SupportWalletFragment", "initialize(WalletFragmentInitParams) was called more than once. Ignoring.");
         }

      }
   }

   public final void updateMaskedWalletRequest(MaskedWalletRequest var1) {
      if (this.zzbPZ != null) {
         this.zzbPZ.updateMaskedWalletRequest(var1);
         this.zzbQf = null;
      } else {
         this.zzbQf = var1;
      }
   }

   public final void updateMaskedWallet(MaskedWallet var1) {
      if (this.zzbPZ != null) {
         this.zzbPZ.updateMaskedWallet(var1);
         this.zzbQg = null;
      } else {
         this.zzbQg = var1;
      }
   }

   public final void setEnabled(boolean var1) {
      if (this.zzbPZ != null) {
         this.zzbPZ.setEnabled(var1);
         this.zzbQh = null;
      } else {
         this.zzbQh = var1;
      }
   }

   public final void setOnStateChangedListener(SupportWalletFragment.OnStateChangedListener var1) {
      this.zzbQc.zza(var1);
   }

   public final int getState() {
      return this.zzbPZ != null ? this.zzbPZ.getState() : 0;
   }

   public final void onInflate(Activity var1, AttributeSet var2, Bundle var3) {
      super.onInflate(var1, var2, var3);
      if (this.zzbQd == null) {
         this.zzbQd = WalletFragmentOptions.zza((Context)var1, (AttributeSet)var2);
      }

      Bundle var4;
      (var4 = new Bundle()).putParcelable("attrKeyWalletFragmentOptions", this.zzbQd);
      this.zzbQb.onInflate(var1, var4, var3);
   }

   public final void onCreate(Bundle var1) {
      super.onCreate(var1);
      if (var1 != null) {
         var1.setClassLoader(WalletFragmentOptions.class.getClassLoader());
         WalletFragmentInitParams var2;
         if ((var2 = (WalletFragmentInitParams)var1.getParcelable("walletFragmentInitParams")) != null) {
            if (this.zzbQe != null) {
               Log.w("SupportWalletFragment", "initialize(WalletFragmentInitParams) was called more than once.Ignoring.");
            }

            this.zzbQe = var2;
         }

         if (this.zzbQf == null) {
            this.zzbQf = (MaskedWalletRequest)var1.getParcelable("maskedWalletRequest");
         }

         if (this.zzbQg == null) {
            this.zzbQg = (MaskedWallet)var1.getParcelable("maskedWallet");
         }

         if (var1.containsKey("walletFragmentOptions")) {
            this.zzbQd = (WalletFragmentOptions)var1.getParcelable("walletFragmentOptions");
         }

         if (var1.containsKey("enabled")) {
            this.zzbQh = var1.getBoolean("enabled");
         }
      } else {
         WalletFragmentOptions var3;
         if (this.zzaSE.getArguments() != null && (var3 = (WalletFragmentOptions)this.zzaSE.getArguments().getParcelable("extraWalletFragmentOptions")) != null) {
            var3.zzby(this.zzaSE.getActivity());
            this.zzbQd = var3;
         }
      }

      this.mCreated = true;
      this.zzbQb.onCreate(var1);
   }

   public final View onCreateView(LayoutInflater var1, ViewGroup var2, Bundle var3) {
      return this.zzbQb.onCreateView(var1, var2, var3);
   }

   public final void onStart() {
      super.onStart();
      this.zzbQb.onStart();
   }

   public final void onResume() {
      super.onResume();
      this.zzbQb.onResume();
      FragmentManager var1;
      Fragment var2;
      if ((var2 = (var1 = this.zzaSE.getActivity().getSupportFragmentManager()).findFragmentByTag("GooglePlayServicesErrorDialog")) != null) {
         var1.beginTransaction().remove(var2).commit();
         GooglePlayServicesUtil.showErrorDialogFragment(GooglePlayServicesUtil.isGooglePlayServicesAvailable(this.zzaSE.getActivity()), this.zzaSE.getActivity(), -1);
      }

   }

   public final void onPause() {
      super.onPause();
      this.zzbQb.onPause();
   }

   public final void onStop() {
      super.onStop();
      this.zzbQb.onStop();
   }

   public final void onSaveInstanceState(Bundle var1) {
      super.onSaveInstanceState(var1);
      var1.setClassLoader(WalletFragmentOptions.class.getClassLoader());
      this.zzbQb.onSaveInstanceState(var1);
      if (this.zzbQe != null) {
         var1.putParcelable("walletFragmentInitParams", this.zzbQe);
         this.zzbQe = null;
      }

      if (this.zzbQf != null) {
         var1.putParcelable("maskedWalletRequest", this.zzbQf);
         this.zzbQf = null;
      }

      if (this.zzbQg != null) {
         var1.putParcelable("maskedWallet", this.zzbQg);
         this.zzbQg = null;
      }

      if (this.zzbQd != null) {
         var1.putParcelable("walletFragmentOptions", this.zzbQd);
         this.zzbQd = null;
      }

      if (this.zzbQh != null) {
         var1.putBoolean("enabled", this.zzbQh.booleanValue());
         this.zzbQh = null;
      }

   }

   public final void onDestroy() {
      super.onDestroy();
      this.mCreated = false;
   }

   public final void onActivityResult(int var1, int var2, Intent var3) {
      super.onActivityResult(var1, var2, var3);
      if (this.zzbPZ != null) {
         this.zzbPZ.onActivityResult(var1, var2, var3);
      }

   }

   // $FF: synthetic method
   static WalletFragmentOptions zza(SupportWalletFragment var0, WalletFragmentOptions var1) {
      return var0.zzbQd = null;
   }

   // $FF: synthetic method
   static WalletFragmentInitParams zza(SupportWalletFragment var0, WalletFragmentInitParams var1) {
      return var0.zzbQe = null;
   }

   // $FF: synthetic method
   static MaskedWalletRequest zza(SupportWalletFragment var0, MaskedWalletRequest var1) {
      return var0.zzbQf = null;
   }

   // $FF: synthetic method
   static MaskedWallet zza(SupportWalletFragment var0, MaskedWallet var1) {
      return var0.zzbQg = null;
   }

   // $FF: synthetic method
   static Boolean zza(SupportWalletFragment var0, Boolean var1) {
      return var0.zzbQh = null;
   }

   public interface OnStateChangedListener {
      void onStateChanged(SupportWalletFragment var1, int var2, int var3, Bundle var4);
   }

   static class zza extends ge {
      private SupportWalletFragment.OnStateChangedListener zzbQi;
      private final SupportWalletFragment zzbQj;

      zza(SupportWalletFragment var1) {
         this.zzbQj = var1;
      }

      public final void zza(int var1, int var2, Bundle var3) {
         if (this.zzbQi != null) {
            this.zzbQi.onStateChanged(this.zzbQj, var1, var2, var3);
         }

      }

      public final void zza(SupportWalletFragment.OnStateChangedListener var1) {
         this.zzbQi = var1;
      }
   }

   static class zzb implements LifecycleDelegate {
      private final ga zzbQk;

      private zzb(ga var1) {
         this.zzbQk = var1;
      }

      private final void initialize(WalletFragmentInitParams var1) {
         try {
            this.zzbQk.initialize(var1);
         } catch (RemoteException var3) {
            throw new RuntimeException(var3);
         }
      }

      private final void setEnabled(boolean var1) {
         try {
            this.zzbQk.setEnabled(var1);
         } catch (RemoteException var3) {
            throw new RuntimeException(var3);
         }
      }

      private final void updateMaskedWalletRequest(MaskedWalletRequest var1) {
         try {
            this.zzbQk.updateMaskedWalletRequest(var1);
         } catch (RemoteException var3) {
            throw new RuntimeException(var3);
         }
      }

      private final void updateMaskedWallet(MaskedWallet var1) {
         try {
            this.zzbQk.updateMaskedWallet(var1);
         } catch (RemoteException var3) {
            throw new RuntimeException(var3);
         }
      }

      private final int getState() {
         try {
            return this.zzbQk.getState();
         } catch (RemoteException var2) {
            throw new RuntimeException(var2);
         }
      }

      public final void onInflate(Activity var1, Bundle var2, Bundle var3) {
         WalletFragmentOptions var4 = (WalletFragmentOptions)var2.getParcelable("extraWalletFragmentOptions");

         try {
            this.zzbQk.zza(zzn.zzw(var1), var4, var3);
         } catch (RemoteException var6) {
            throw new RuntimeException(var6);
         }
      }

      public final void onCreate(Bundle var1) {
         try {
            this.zzbQk.onCreate(var1);
         } catch (RemoteException var3) {
            throw new RuntimeException(var3);
         }
      }

      public final View onCreateView(LayoutInflater var1, ViewGroup var2, Bundle var3) {
         try {
            return (View)zzn.zzE(this.zzbQk.onCreateView(zzn.zzw(var1), zzn.zzw(var2), var3));
         } catch (RemoteException var5) {
            throw new RuntimeException(var5);
         }
      }

      public final void onStart() {
         try {
            this.zzbQk.onStart();
         } catch (RemoteException var2) {
            throw new RuntimeException(var2);
         }
      }

      public final void onResume() {
         try {
            this.zzbQk.onResume();
         } catch (RemoteException var2) {
            throw new RuntimeException(var2);
         }
      }

      public final void onPause() {
         try {
            this.zzbQk.onPause();
         } catch (RemoteException var2) {
            throw new RuntimeException(var2);
         }
      }

      public final void onStop() {
         try {
            this.zzbQk.onStop();
         } catch (RemoteException var2) {
            throw new RuntimeException(var2);
         }
      }

      public final void onDestroyView() {
      }

      public final void onDestroy() {
      }

      public final void onLowMemory() {
      }

      public final void onSaveInstanceState(Bundle var1) {
         try {
            this.zzbQk.onSaveInstanceState(var1);
         } catch (RemoteException var3) {
            throw new RuntimeException(var3);
         }
      }

      private final void onActivityResult(int var1, int var2, Intent var3) {
         try {
            this.zzbQk.onActivityResult(var1, var2, var3);
         } catch (RemoteException var5) {
            throw new RuntimeException(var5);
         }
      }

      // $FF: synthetic method
      zzb(ga var1, zza var2) {
         this(var1);
      }
   }

   class zzc extends com.google.android.gms.dynamic.zza implements OnClickListener {
      // $FF: synthetic field
      private SupportWalletFragment zzbQl;

      private zzc() {
         this.zzbQl = SupportWalletFragment.this;
         super();
      }

      protected final void zza(zzo var1) {
         FragmentActivity var2 = this.zzbQl.zzaSE.getActivity();
         if (this.zzbQl.zzbPZ == null && this.zzbQl.mCreated && var2 != null) {
            try {
               ga var3 = gz.zza(var2, this.zzbQl.zzbQa, this.zzbQl.zzbQd, this.zzbQl.zzbQc);
               this.zzbQl.zzbPZ = new SupportWalletFragment.zzb(var3, (zza)null);
               SupportWalletFragment.zza(this.zzbQl, (WalletFragmentOptions)null);
            } catch (GooglePlayServicesNotAvailableException var4) {
               return;
            }

            var1.zza(this.zzbQl.zzbPZ);
            if (this.zzbQl.zzbQe != null) {
               this.zzbQl.zzbPZ.initialize(this.zzbQl.zzbQe);
               SupportWalletFragment.zza(this.zzbQl, (WalletFragmentInitParams)null);
            }

            if (this.zzbQl.zzbQf != null) {
               this.zzbQl.zzbPZ.updateMaskedWalletRequest(this.zzbQl.zzbQf);
               SupportWalletFragment.zza(this.zzbQl, (MaskedWalletRequest)null);
            }

            if (this.zzbQl.zzbQg != null) {
               this.zzbQl.zzbPZ.updateMaskedWallet(this.zzbQl.zzbQg);
               SupportWalletFragment.zza(this.zzbQl, (MaskedWallet)null);
            }

            if (this.zzbQl.zzbQh != null) {
               this.zzbQl.zzbPZ.setEnabled(this.zzbQl.zzbQh.booleanValue());
               SupportWalletFragment.zza(this.zzbQl, (Boolean)null);
            }
         }

      }

      protected final void zza(FrameLayout var1) {
         Button var2;
         (var2 = new Button(this.zzbQl.zzaSE.getActivity())).setText(string.wallet_buy_button_place_holder);
         int var3 = -1;
         int var4 = -2;
         WalletFragmentStyle var5;
         if (this.zzbQl.zzbQd != null && (var5 = this.zzbQl.zzbQd.getFragmentStyle()) != null) {
            DisplayMetrics var6 = this.zzbQl.zzaSE.getResources().getDisplayMetrics();
            var3 = var5.zza("buyButtonWidth", var6, -1);
            var4 = var5.zza("buyButtonHeight", var6, -2);
         }

         var2.setLayoutParams(new LayoutParams(var3, var4));
         var2.setOnClickListener(this);
         var1.addView(var2);
      }

      public final void onClick(View var1) {
         FragmentActivity var2;
         GooglePlayServicesUtil.showErrorDialogFragment(GooglePlayServicesUtil.isGooglePlayServicesAvailable(var2 = this.zzbQl.zzaSE.getActivity()), var2, -1);
      }

      // $FF: synthetic method
      zzc(zza var2) {
         this();
      }
   }
}
