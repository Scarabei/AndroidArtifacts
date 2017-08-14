package android.support.v4.os;

import android.content.Context;
import android.os.UserManager;
import android.os.Build.VERSION;

public class UserManagerCompat {
   public static boolean isUserUnlocked(Context context) {
      return VERSION.SDK_INT >= 24 ? ((UserManager)context.getSystemService(UserManager.class)).isUserUnlocked() : true;
   }
}
