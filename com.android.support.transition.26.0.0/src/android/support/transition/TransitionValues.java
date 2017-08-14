package android.support.transition;

import android.view.View;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class TransitionValues {
   public final Map values = new HashMap();
   public View view;
   final ArrayList mTargetedTransitions = new ArrayList();

   public boolean equals(Object other) {
      return other instanceof TransitionValues && this.view == ((TransitionValues)other).view && this.values.equals(((TransitionValues)other).values);
   }

   public int hashCode() {
      return 31 * this.view.hashCode() + this.values.hashCode();
   }

   public String toString() {
      String returnValue = "TransitionValues@" + Integer.toHexString(this.hashCode()) + ":\n";
      returnValue = returnValue + "    view = " + this.view + "\n";
      returnValue = returnValue + "    values:";

      String s;
      for(Iterator var2 = this.values.keySet().iterator(); var2.hasNext(); returnValue = returnValue + "    " + s + ": " + this.values.get(s) + "\n") {
         s = (String)var2.next();
      }

      return returnValue;
   }
}
