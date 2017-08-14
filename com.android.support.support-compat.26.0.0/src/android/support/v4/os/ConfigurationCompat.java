package android.support.v4.os;

import android.content.res.Configuration;
import android.os.Build.VERSION;

public final class ConfigurationCompat {
   public static LocaleListCompat getLocales(Configuration configuration) {
      return VERSION.SDK_INT >= 24 ? LocaleListCompat.wrap(configuration.getLocales()) : LocaleListCompat.create(configuration.locale);
   }
}
