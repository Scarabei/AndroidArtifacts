package android.support.v4.view.accessibility;

import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import java.util.List;

@RequiresApi(16)
class AccessibilityNodeProviderCompatJellyBean {
   public static Object newAccessibilityNodeProviderBridge(final AccessibilityNodeProviderCompatJellyBean.AccessibilityNodeInfoBridge bridge) {
      return new AccessibilityNodeProvider() {
         public AccessibilityNodeInfo createAccessibilityNodeInfo(int virtualViewId) {
            return (AccessibilityNodeInfo)bridge.createAccessibilityNodeInfo(virtualViewId);
         }

         public List findAccessibilityNodeInfosByText(String text, int virtualViewId) {
            return bridge.findAccessibilityNodeInfosByText(text, virtualViewId);
         }

         public boolean performAction(int virtualViewId, int action, Bundle arguments) {
            return bridge.performAction(virtualViewId, action, arguments);
         }
      };
   }

   interface AccessibilityNodeInfoBridge {
      Object createAccessibilityNodeInfo(int var1);

      boolean performAction(int var1, int var2, Bundle var3);

      List findAccessibilityNodeInfosByText(String var1, int var2);
   }
}
