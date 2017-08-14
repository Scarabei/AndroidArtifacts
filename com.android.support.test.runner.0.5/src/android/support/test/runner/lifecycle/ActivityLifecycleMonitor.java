package android.support.test.runner.lifecycle;

import android.app.Activity;
import java.util.Collection;

public interface ActivityLifecycleMonitor {
   void addLifecycleCallback(ActivityLifecycleCallback var1);

   void removeLifecycleCallback(ActivityLifecycleCallback var1);

   Stage getLifecycleStageOf(Activity var1);

   Collection getActivitiesInStage(Stage var1);
}
