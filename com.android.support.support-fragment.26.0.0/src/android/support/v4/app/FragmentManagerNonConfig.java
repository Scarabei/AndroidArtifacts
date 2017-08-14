package android.support.v4.app;

import java.util.List;

public class FragmentManagerNonConfig {
   private final List mFragments;
   private final List mChildNonConfigs;

   FragmentManagerNonConfig(List fragments, List childNonConfigs) {
      this.mFragments = fragments;
      this.mChildNonConfigs = childNonConfigs;
   }

   List getFragments() {
      return this.mFragments;
   }

   List getChildNonConfigs() {
      return this.mChildNonConfigs;
   }
}
