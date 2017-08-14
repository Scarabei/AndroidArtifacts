package com.google.android.gms.identity.intents;

public interface AddressConstants {
   public interface Themes {
      /** @deprecated */
      @Deprecated
      int THEME_HOLO_DARK = 0;
      /** @deprecated */
      @Deprecated
      int THEME_HOLO_LIGHT = 1;
      int THEME_DARK = 0;
      int THEME_LIGHT = 1;
   }

   public interface ErrorCodes {
      int ERROR_CODE_NO_APPLICABLE_ADDRESSES = 555;
   }

   public interface ResultCodes {
      int RESULT_ERROR = 1;
   }

   public interface Extras {
      String EXTRA_ADDRESS = "com.google.android.gms.identity.intents.EXTRA_ADDRESS";
      String EXTRA_ERROR_CODE = "com.google.android.gms.identity.intents.EXTRA_ERROR_CODE";
   }
}
