package android.support.v4.view.accessibility;

import android.os.Bundle;
import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import java.util.ArrayList;
import java.util.List;

public class AccessibilityNodeProviderCompat {
   public static final int HOST_VIEW_ID = -1;
   private static final AccessibilityNodeProviderCompat.AccessibilityNodeProviderImpl IMPL;
   private final Object mProvider;

   public AccessibilityNodeProviderCompat() {
      this.mProvider = IMPL.newAccessibilityNodeProviderBridge(this);
   }

   public AccessibilityNodeProviderCompat(Object provider) {
      this.mProvider = provider;
   }

   public Object getProvider() {
      return this.mProvider;
   }

   @Nullable
   public AccessibilityNodeInfoCompat createAccessibilityNodeInfo(int virtualViewId) {
      return null;
   }

   public boolean performAction(int virtualViewId, int action, Bundle arguments) {
      return false;
   }

   @Nullable
   public List findAccessibilityNodeInfosByText(String text, int virtualViewId) {
      return null;
   }

   @Nullable
   public AccessibilityNodeInfoCompat findFocus(int focus) {
      return null;
   }

   static {
      if (VERSION.SDK_INT >= 19) {
         IMPL = new AccessibilityNodeProviderCompat.AccessibilityNodeProviderKitKatImpl();
      } else if (VERSION.SDK_INT >= 16) {
         IMPL = new AccessibilityNodeProviderCompat.AccessibilityNodeProviderJellyBeanImpl();
      } else {
         IMPL = new AccessibilityNodeProviderCompat.AccessibilityNodeProviderStubImpl();
      }

   }

   @RequiresApi(19)
   private static class AccessibilityNodeProviderKitKatImpl extends AccessibilityNodeProviderCompat.AccessibilityNodeProviderStubImpl {
      public Object newAccessibilityNodeProviderBridge(final AccessibilityNodeProviderCompat compat) {
         return AccessibilityNodeProviderCompatKitKat.newAccessibilityNodeProviderBridge(new AccessibilityNodeProviderCompatKitKat.AccessibilityNodeInfoBridge() {
            public boolean performAction(int virtualViewId, int action, Bundle arguments) {
               return compat.performAction(virtualViewId, action, arguments);
            }

            public List findAccessibilityNodeInfosByText(String text, int virtualViewId) {
               List compatInfos = compat.findAccessibilityNodeInfosByText(text, virtualViewId);
               if (compatInfos == null) {
                  return null;
               } else {
                  List infos = new ArrayList();
                  int infoCount = compatInfos.size();

                  for(int i = 0; i < infoCount; ++i) {
                     AccessibilityNodeInfoCompat infoCompat = (AccessibilityNodeInfoCompat)compatInfos.get(i);
                     infos.add(infoCompat.unwrap());
                  }

                  return infos;
               }
            }

            public Object createAccessibilityNodeInfo(int virtualViewId) {
               AccessibilityNodeInfoCompat compatInfo = compat.createAccessibilityNodeInfo(virtualViewId);
               return compatInfo == null ? null : compatInfo.unwrap();
            }

            public Object findFocus(int focus) {
               AccessibilityNodeInfoCompat compatInfo = compat.findFocus(focus);
               return compatInfo == null ? null : compatInfo.unwrap();
            }
         });
      }
   }

   @RequiresApi(16)
   private static class AccessibilityNodeProviderJellyBeanImpl extends AccessibilityNodeProviderCompat.AccessibilityNodeProviderStubImpl {
      public Object newAccessibilityNodeProviderBridge(final AccessibilityNodeProviderCompat compat) {
         return AccessibilityNodeProviderCompatJellyBean.newAccessibilityNodeProviderBridge(new AccessibilityNodeProviderCompatJellyBean.AccessibilityNodeInfoBridge() {
            public boolean performAction(int virtualViewId, int action, Bundle arguments) {
               return compat.performAction(virtualViewId, action, arguments);
            }

            public List findAccessibilityNodeInfosByText(String text, int virtualViewId) {
               List compatInfos = compat.findAccessibilityNodeInfosByText(text, virtualViewId);
               if (compatInfos == null) {
                  return null;
               } else {
                  List infos = new ArrayList();
                  int infoCount = compatInfos.size();

                  for(int i = 0; i < infoCount; ++i) {
                     AccessibilityNodeInfoCompat infoCompat = (AccessibilityNodeInfoCompat)compatInfos.get(i);
                     infos.add(infoCompat.unwrap());
                  }

                  return infos;
               }
            }

            public Object createAccessibilityNodeInfo(int virtualViewId) {
               AccessibilityNodeInfoCompat compatInfo = compat.createAccessibilityNodeInfo(virtualViewId);
               return compatInfo == null ? null : compatInfo.unwrap();
            }
         });
      }
   }

   static class AccessibilityNodeProviderStubImpl implements AccessibilityNodeProviderCompat.AccessibilityNodeProviderImpl {
      public Object newAccessibilityNodeProviderBridge(AccessibilityNodeProviderCompat compat) {
         return null;
      }
   }

   interface AccessibilityNodeProviderImpl {
      Object newAccessibilityNodeProviderBridge(AccessibilityNodeProviderCompat var1);
   }
}
