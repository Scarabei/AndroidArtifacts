package com.google.android.gms.internal;

public enum zzasr {
   /** @deprecated */
   @Deprecated
   zzamH("ClientLoginDisabled"),
   /** @deprecated */
   @Deprecated
   zzamI("DeviceManagementRequiredOrSyncDisabled"),
   /** @deprecated */
   @Deprecated
   zzamJ("SocketTimeout"),
   zzamK("Ok"),
   zzamL("UNKNOWN_ERR"),
   zzamM("NetworkError"),
   zzamN("ServiceUnavailable"),
   zzamO("InternalError"),
   zzamP("BadAuthentication"),
   zzamQ("EmptyConsumerPackageOrSig"),
   zzamR("InvalidSecondFactor"),
   zzamS("PostSignInFlowRequired"),
   zzamT("NeedsBrowser"),
   zzamU("Unknown"),
   zzamV("NotVerified"),
   zzamW("TermsNotAgreed"),
   zzamX("AccountDisabled"),
   zzamY("CaptchaRequired"),
   zzamZ("AccountDeleted"),
   zzana("ServiceDisabled"),
   zzanb("NeedPermission"),
   zzanc("NeedRemoteConsent"),
   zzand("INVALID_SCOPE"),
   zzane("UserCancel"),
   zzanf("PermissionDenied"),
   zzang("INVALID_AUDIENCE"),
   zzanh("UNREGISTERED_ON_API_CONSOLE"),
   zzani("ThirdPartyDeviceManagementRequired"),
   zzanj("DeviceManagementInternalError"),
   zzank("DeviceManagementSyncDisabled"),
   zzanl("DeviceManagementAdminBlocked"),
   zzanm("DeviceManagementAdminPendingApproval"),
   zzann("DeviceManagementStaleSyncRequired"),
   zzano("DeviceManagementDeactivated"),
   zzanp("DeviceManagementScreenlockRequired"),
   zzanq("DeviceManagementRequired"),
   zzanr("ALREADY_HAS_GMAIL"),
   zzans("WeakPassword"),
   zzant("BadRequest"),
   zzanu("BadUsername"),
   zzanv("DeletedGmail"),
   zzanw("ExistingUsername"),
   zzanx("LoginFail"),
   zzany("NotLoggedIn"),
   zzanz("NoGmail"),
   zzanA("RequestDenied"),
   zzanB("ServerError"),
   zzanC("UsernameUnavailable"),
   zzanD("GPlusOther"),
   zzanE("GPlusNickname"),
   zzanF("GPlusInvalidChar"),
   zzanG("GPlusInterstitial"),
   zzanH("ProfileUpgradeError");

   private final String zzanI;

   private zzasr(String var3) {
      this.zzanI = var3;
   }

   public static final zzasr zzbW(String var0) {
      zzasr var1 = null;
      zzasr[] var2;
      int var3 = (var2 = values()).length;

      for(int var4 = 0; var4 < var3; ++var4) {
         zzasr var5;
         if ((var5 = var2[var4]).zzanI.equals(var0)) {
            var1 = var5;
         }
      }

      return var1;
   }
}
