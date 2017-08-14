package android.support.v4.hardware.fingerprint;

import android.content.Context;
import android.os.Handler;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.os.CancellationSignal;
import java.security.Signature;
import javax.crypto.Cipher;
import javax.crypto.Mac;

public final class FingerprintManagerCompat {
   private Context mContext;
   static final FingerprintManagerCompat.FingerprintManagerCompatImpl IMPL;

   public static FingerprintManagerCompat from(Context context) {
      return new FingerprintManagerCompat(context);
   }

   private FingerprintManagerCompat(Context context) {
      this.mContext = context;
   }

   public boolean hasEnrolledFingerprints() {
      return IMPL.hasEnrolledFingerprints(this.mContext);
   }

   public boolean isHardwareDetected() {
      return IMPL.isHardwareDetected(this.mContext);
   }

   public void authenticate(@Nullable FingerprintManagerCompat.CryptoObject crypto, int flags, @Nullable CancellationSignal cancel, @NonNull FingerprintManagerCompat.AuthenticationCallback callback, @Nullable Handler handler) {
      IMPL.authenticate(this.mContext, crypto, flags, cancel, callback, handler);
   }

   static {
      if (VERSION.SDK_INT >= 23) {
         IMPL = new FingerprintManagerCompat.Api23FingerprintManagerCompatImpl();
      } else {
         IMPL = new FingerprintManagerCompat.LegacyFingerprintManagerCompatImpl();
      }

   }

   @RequiresApi(23)
   private static class Api23FingerprintManagerCompatImpl implements FingerprintManagerCompat.FingerprintManagerCompatImpl {
      public boolean hasEnrolledFingerprints(Context context) {
         return FingerprintManagerCompatApi23.hasEnrolledFingerprints(context);
      }

      public boolean isHardwareDetected(Context context) {
         return FingerprintManagerCompatApi23.isHardwareDetected(context);
      }

      public void authenticate(Context context, FingerprintManagerCompat.CryptoObject crypto, int flags, CancellationSignal cancel, FingerprintManagerCompat.AuthenticationCallback callback, Handler handler) {
         FingerprintManagerCompatApi23.authenticate(context, wrapCryptoObject(crypto), flags, cancel != null ? cancel.getCancellationSignalObject() : null, wrapCallback(callback), handler);
      }

      private static FingerprintManagerCompatApi23.CryptoObject wrapCryptoObject(FingerprintManagerCompat.CryptoObject cryptoObject) {
         if (cryptoObject == null) {
            return null;
         } else if (cryptoObject.getCipher() != null) {
            return new FingerprintManagerCompatApi23.CryptoObject(cryptoObject.getCipher());
         } else if (cryptoObject.getSignature() != null) {
            return new FingerprintManagerCompatApi23.CryptoObject(cryptoObject.getSignature());
         } else {
            return cryptoObject.getMac() != null ? new FingerprintManagerCompatApi23.CryptoObject(cryptoObject.getMac()) : null;
         }
      }

      static FingerprintManagerCompat.CryptoObject unwrapCryptoObject(FingerprintManagerCompatApi23.CryptoObject cryptoObject) {
         if (cryptoObject == null) {
            return null;
         } else if (cryptoObject.getCipher() != null) {
            return new FingerprintManagerCompat.CryptoObject(cryptoObject.getCipher());
         } else if (cryptoObject.getSignature() != null) {
            return new FingerprintManagerCompat.CryptoObject(cryptoObject.getSignature());
         } else {
            return cryptoObject.getMac() != null ? new FingerprintManagerCompat.CryptoObject(cryptoObject.getMac()) : null;
         }
      }

      private static FingerprintManagerCompatApi23.AuthenticationCallback wrapCallback(final FingerprintManagerCompat.AuthenticationCallback callback) {
         return new FingerprintManagerCompatApi23.AuthenticationCallback() {
            public void onAuthenticationError(int errMsgId, CharSequence errString) {
               callback.onAuthenticationError(errMsgId, errString);
            }

            public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
               callback.onAuthenticationHelp(helpMsgId, helpString);
            }

            public void onAuthenticationSucceeded(FingerprintManagerCompatApi23.AuthenticationResultInternal result) {
               callback.onAuthenticationSucceeded(new FingerprintManagerCompat.AuthenticationResult(FingerprintManagerCompat.Api23FingerprintManagerCompatImpl.unwrapCryptoObject(result.getCryptoObject())));
            }

            public void onAuthenticationFailed() {
               callback.onAuthenticationFailed();
            }
         };
      }
   }

   private static class LegacyFingerprintManagerCompatImpl implements FingerprintManagerCompat.FingerprintManagerCompatImpl {
      public boolean hasEnrolledFingerprints(Context context) {
         return false;
      }

      public boolean isHardwareDetected(Context context) {
         return false;
      }

      public void authenticate(Context context, FingerprintManagerCompat.CryptoObject crypto, int flags, CancellationSignal cancel, FingerprintManagerCompat.AuthenticationCallback callback, Handler handler) {
      }
   }

   private interface FingerprintManagerCompatImpl {
      boolean hasEnrolledFingerprints(Context var1);

      boolean isHardwareDetected(Context var1);

      void authenticate(Context var1, FingerprintManagerCompat.CryptoObject var2, int var3, CancellationSignal var4, FingerprintManagerCompat.AuthenticationCallback var5, Handler var6);
   }

   public abstract static class AuthenticationCallback {
      public void onAuthenticationError(int errMsgId, CharSequence errString) {
      }

      public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
      }

      public void onAuthenticationSucceeded(FingerprintManagerCompat.AuthenticationResult result) {
      }

      public void onAuthenticationFailed() {
      }
   }

   public static final class AuthenticationResult {
      private FingerprintManagerCompat.CryptoObject mCryptoObject;

      public AuthenticationResult(FingerprintManagerCompat.CryptoObject crypto) {
         this.mCryptoObject = crypto;
      }

      public FingerprintManagerCompat.CryptoObject getCryptoObject() {
         return this.mCryptoObject;
      }
   }

   public static class CryptoObject {
      private final Signature mSignature;
      private final Cipher mCipher;
      private final Mac mMac;

      public CryptoObject(Signature signature) {
         this.mSignature = signature;
         this.mCipher = null;
         this.mMac = null;
      }

      public CryptoObject(Cipher cipher) {
         this.mCipher = cipher;
         this.mSignature = null;
         this.mMac = null;
      }

      public CryptoObject(Mac mac) {
         this.mMac = mac;
         this.mCipher = null;
         this.mSignature = null;
      }

      public Signature getSignature() {
         return this.mSignature;
      }

      public Cipher getCipher() {
         return this.mCipher;
      }

      public Mac getMac() {
         return this.mMac;
      }
   }
}
