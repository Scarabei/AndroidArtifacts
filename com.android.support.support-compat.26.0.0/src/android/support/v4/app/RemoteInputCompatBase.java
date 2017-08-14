package android.support.v4.app;

import android.os.Bundle;
import java.util.Set;

/** @deprecated */
@Deprecated
class RemoteInputCompatBase {
   public abstract static class RemoteInput {
      protected abstract String getResultKey();

      protected abstract CharSequence getLabel();

      protected abstract CharSequence[] getChoices();

      protected abstract boolean getAllowFreeFormInput();

      protected abstract Bundle getExtras();

      protected abstract Set getAllowedDataTypes();

      public interface Factory {
         RemoteInputCompatBase.RemoteInput build(String var1, CharSequence var2, CharSequence[] var3, boolean var4, Bundle var5, Set var6);

         RemoteInputCompatBase.RemoteInput[] newArray(int var1);
      }
   }
}
