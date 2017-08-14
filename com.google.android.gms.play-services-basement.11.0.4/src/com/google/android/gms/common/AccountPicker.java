package com.google.android.gms.common;

import android.accounts.Account;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.gms.common.internal.zzbo;
import java.util.ArrayList;

public final class AccountPicker {
   public static Intent newChooseAccountIntent(Account var0, ArrayList var1, String[] var2, boolean var3, String var4, String var5, String[] var6, Bundle var7) {
      Intent var27 = new Intent();
      zzbo.zzb(true, "We only support hostedDomain filter for account chip styled account picker");
      String var28 = "com.google.android.gms.common.account.CHOOSE_ACCOUNT";
      var27.setAction(var28);
      var27.setPackage("com.google.android.gms");
      var27.putExtra("allowableAccounts", var1);
      var27.putExtra("allowableAccountTypes", var2);
      var27.putExtra("addAccountOptions", var7);
      var27.putExtra("selectedAccount", var0);
      var27.putExtra("alwaysPromptForAccount", var3);
      var27.putExtra("descriptionTextOverride", var4);
      var27.putExtra("authTokenType", var5);
      var27.putExtra("addAccountRequiredFeatures", var6);
      var27.putExtra("setGmsCoreAccount", false);
      var27.putExtra("overrideTheme", 0);
      var27.putExtra("overrideCustomTheme", 0);
      var27.putExtra("hostedDomainFilter", (String)null);
      return var27;
   }
}
