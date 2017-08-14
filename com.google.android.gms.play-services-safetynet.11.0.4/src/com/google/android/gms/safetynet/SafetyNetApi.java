package com.google.android.gms.safetynet;

import android.content.Context;
import com.google.android.gms.common.annotation.KeepForSdkWithMembers;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Response;
import com.google.android.gms.common.api.Result;
import java.util.List;

@KeepForSdkWithMembers
public interface SafetyNetApi {
   /** @deprecated */
   @Deprecated
   PendingResult attest(GoogleApiClient var1, byte[] var2);

   PendingResult lookupUri(GoogleApiClient var1, List var2, String var3);

   /** @deprecated */
   @Deprecated
   PendingResult lookupUri(GoogleApiClient var1, String var2, int... var3);

   /** @deprecated */
   @Deprecated
   PendingResult lookupUri(GoogleApiClient var1, String var2, String var3, int... var4);

   boolean lookupUriInLocalBlacklist(String var1, int... var2);

   /** @deprecated */
   @Deprecated
   boolean isVerifyAppsEnabled(Context var1);

   /** @deprecated */
   @Deprecated
   PendingResult isVerifyAppsEnabled(GoogleApiClient var1);

   /** @deprecated */
   @Deprecated
   PendingResult enableVerifyApps(GoogleApiClient var1);

   /** @deprecated */
   @Deprecated
   PendingResult listHarmfulApps(GoogleApiClient var1);

   /** @deprecated */
   @Deprecated
   PendingResult verifyWithRecaptcha(GoogleApiClient var1, String var2);

   public static class RecaptchaTokenResponse extends Response {
      public String getTokenResult() {
         return ((SafetyNetApi.RecaptchaTokenResult)this.getResult()).getTokenResult();
      }
   }

   /** @deprecated */
   @Deprecated
   public interface RecaptchaTokenResult extends Result {
      String getTokenResult();
   }

   public static class HarmfulAppsResponse extends Response {
      public List getHarmfulAppsList() {
         return ((SafetyNetApi.HarmfulAppsResult)this.getResult()).getHarmfulAppsList();
      }

      public long getLastScanTimeMs() {
         return ((SafetyNetApi.HarmfulAppsResult)this.getResult()).getLastScanTimeMs();
      }
   }

   /** @deprecated */
   @Deprecated
   public interface HarmfulAppsResult extends Result {
      List getHarmfulAppsList();

      long getLastScanTimeMs();
   }

   public static class VerifyAppsUserResponse extends Response {
      public boolean isVerifyAppsEnabled() {
         return ((SafetyNetApi.VerifyAppsUserResult)this.getResult()).isVerifyAppsEnabled();
      }
   }

   /** @deprecated */
   @Deprecated
   public interface VerifyAppsUserResult extends Result {
      boolean isVerifyAppsEnabled();
   }

   @KeepForSdkWithMembers
   public static class SafeBrowsingResponse extends Response {
      public String getMetadata() {
         return ((SafetyNetApi.SafeBrowsingResult)this.getResult()).getMetadata();
      }

      public List getDetectedThreats() {
         return ((SafetyNetApi.SafeBrowsingResult)this.getResult()).getDetectedThreats();
      }
   }

   /** @deprecated */
   @Deprecated
   @KeepForSdkWithMembers
   public interface SafeBrowsingResult extends Result {
      String getMetadata();

      List getDetectedThreats();
   }

   public static class AttestationResponse extends Response {
      public String getJwsResult() {
         return ((SafetyNetApi.AttestationResult)this.getResult()).getJwsResult();
      }
   }

   /** @deprecated */
   @Deprecated
   public interface AttestationResult extends Result {
      String getJwsResult();
   }
}
