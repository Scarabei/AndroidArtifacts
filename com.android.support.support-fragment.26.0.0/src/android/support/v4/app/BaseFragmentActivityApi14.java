package android.support.v4.app;

import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.IntentSender.SendIntentException;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

@RequiresApi(14)
abstract class BaseFragmentActivityApi14 extends SupportActivity {
   boolean mStartedIntentSenderFromFragment;

   public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
      View v = this.dispatchFragmentsOnCreateView(parent, name, context, attrs);
      return v == null ? super.onCreateView(parent, name, context, attrs) : v;
   }

   public View onCreateView(String name, Context context, AttributeSet attrs) {
      View v = this.dispatchFragmentsOnCreateView((View)null, name, context, attrs);
      return v == null ? super.onCreateView(name, context, attrs) : v;
   }

   abstract View dispatchFragmentsOnCreateView(View var1, String var2, Context var3, AttributeSet var4);

   public void startIntentSenderForResult(IntentSender intent, int requestCode, @Nullable Intent fillInIntent, int flagsMask, int flagsValues, int extraFlags) throws SendIntentException {
      if (!this.mStartedIntentSenderFromFragment && requestCode != -1) {
         checkForValidRequestCode(requestCode);
      }

      super.startIntentSenderForResult(intent, requestCode, fillInIntent, flagsMask, flagsValues, extraFlags);
   }

   static void checkForValidRequestCode(int requestCode) {
      if ((requestCode & -65536) != 0) {
         throw new IllegalArgumentException("Can only use lower 16 bits for requestCode");
      }
   }
}
