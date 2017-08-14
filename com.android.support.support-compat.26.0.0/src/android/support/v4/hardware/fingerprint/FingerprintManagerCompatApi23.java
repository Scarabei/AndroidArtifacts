package android.support.v4.hardware.fingerprint;

import android.annotation.SuppressLint;
import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;
import android.hardware.fingerprint.FingerprintManager.AuthenticationResult;
import android.os.CancellationSignal;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import java.security.Signature;
import javax.crypto.Cipher;
import javax.crypto.Mac;

@RequiresApi(23)
@RestrictTo({Scope.LIBRARY_GROUP})
public final class FingerprintManagerCompatApi23 {
   private static FingerprintManager getFingerprintManagerOrNull(Context context) {
      return context.getPackageManager().hasSystemFeature("android.hardware.fingerprint") ? (FingerprintManager)context.getSystemService(FingerprintManager.class) : null;
   }

   @SuppressLint({"MissingPermission"})
   static boolean hasEnrolledFingerprints(Context context) {
      FingerprintManager fp = getFingerprintManagerOrNull(context);
      return fp != null && fp.hasEnrolledFingerprints();
   }

   @SuppressLint({"MissingPermission"})
   static boolean isHardwareDetected(Context context) {
      FingerprintManager fp = getFingerprintManagerOrNull(context);
      return fp != null && fp.isHardwareDetected();
   }

   @SuppressLint({"MissingPermission"})
   static void authenticate(Context context, FingerprintManagerCompatApi23.CryptoObject crypto, int flags, Object cancel, FingerprintManagerCompatApi23.AuthenticationCallback callback, Handler handler) {
      FingerprintManager fp = getFingerprintManagerOrNull(context);
      if (fp != null) {
         fp.authenticate(wrapCryptoObject(crypto), (CancellationSignal)cancel, flags, wrapCallback(callback), handler);
      }

   }

   private static android.hardware.fingerprint.FingerprintManager.CryptoObject wrapCryptoObject(FingerprintManagerCompatApi23.CryptoObject cryptoObject) {
      if (cryptoObject == null) {
         return null;
      } else if (cryptoObject.getCipher() != null) {
         return new android.hardware.fingerprint.FingerprintManager.CryptoObject(cryptoObject.getCipher());
      } else if (cryptoObject.getSignature() != null) {
         return new android.hardware.fingerprint.FingerprintManager.CryptoObject(cryptoObject.getSignature());
      } else {
         return cryptoObject.getMac() != null ? new android.hardware.fingerprint.FingerprintManager.CryptoObject(cryptoObject.getMac()) : null;
      }
   }

   private static FingerprintManagerCompatApi23.CryptoObject unwrapCryptoObject(android.hardware.fingerprint.FingerprintManager.CryptoObject cryptoObject) {
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

   private static android.hardware.fingerprint.FingerprintManager.AuthenticationCallback wrapCallback(final FingerprintManagerCompatApi23.AuthenticationCallback callback) {
      return new android.hardware.fingerprint.FingerprintManager.AuthenticationCallback() {
         public void onAuthenticationError(int errMsgId, CharSequence errString) {
            callback.onAuthenticationError(errMsgId, errString);
         }

         public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
            callback.onAuthenticationHelp(helpMsgId, helpString);
         }

         public void onAuthenticationSucceeded(AuthenticationResult result) {
            callback.onAuthenticationSucceeded(new FingerprintManagerCompatApi23.AuthenticationResultInternal(FingerprintManagerCompatApi23.unwrapCryptoObject(result.getCryptoObject())));
         }

         public void onAuthenticationFailed() {
            callback.onAuthenticationFailed();
         }
      };
   }

   public abstract static class AuthenticationCallback {
      public void onAuthenticationError(int errMsgId, CharSequence errString) {
      }

      public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
      }

      public void onAuthenticationSucceeded(FingerprintManagerCompatApi23.AuthenticationResultInternal result) {
      }

      public void onAuthenticationFailed() {
      }
   }

   public static final class AuthenticationResultInternal {
      private FingerprintManagerCompatApi23.CryptoObject mCryptoObject;

      public AuthenticationResultInternal(FingerprintManagerCompatApi23.CryptoObject crypto) {
         this.mCryptoObject = crypto;
      }

      public FingerprintManagerCompatApi23.CryptoObject getCryptoObject() {
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
