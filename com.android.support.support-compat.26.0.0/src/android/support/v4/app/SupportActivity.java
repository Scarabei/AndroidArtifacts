package android.support.v4.app;

import android.app.Activity;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.util.SimpleArrayMap;

@RestrictTo({Scope.LIBRARY_GROUP})
public class SupportActivity extends Activity {
   private SimpleArrayMap mExtraDataMap = new SimpleArrayMap();

   @RestrictTo({Scope.LIBRARY_GROUP})
   public void putExtraData(SupportActivity.ExtraData extraData) {
      this.mExtraDataMap.put(extraData.getClass(), extraData);
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   public SupportActivity.ExtraData getExtraData(Class extraDataClass) {
      return (SupportActivity.ExtraData)this.mExtraDataMap.get(extraDataClass);
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   public static class ExtraData {
   }
}
