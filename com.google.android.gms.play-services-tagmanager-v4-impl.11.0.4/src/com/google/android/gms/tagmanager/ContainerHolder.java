package com.google.android.gms.tagmanager;

import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;

public interface ContainerHolder extends Releasable, Result {
   Container getContainer();

   void setContainerAvailableListener(ContainerHolder.ContainerAvailableListener var1);

   void refresh();

   public interface ContainerAvailableListener {
      void onContainerAvailable(ContainerHolder var1, String var2);
   }
}
