package android.support.test.internal.runner;

import android.app.Instrumentation;
import android.os.Build;
import android.os.Bundle;
import android.os.Build.VERSION;
import android.support.test.filters.RequiresDevice;
import android.support.test.filters.SdkSuppress;
import android.support.test.filters.Suppress;
import android.support.test.internal.util.AndroidRunnerParams;
import android.support.test.internal.util.Checks;
import android.util.Log;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import org.junit.runner.Computer;
import org.junit.runner.Description;
import org.junit.runner.Request;
import org.junit.runner.Runner;
import org.junit.runner.manipulation.Filter;
import org.junit.runner.manipulation.NoTestsRemainException;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.InitializationError;

public class TestRequestBuilder {
   private static final String LOG_TAG = "TestRequestBuilder";
   static final String EMULATOR_HARDWARE = "goldfish";
   private static final String[] DEFAULT_EXCLUDED_PACKAGES = new String[]{"junit", "org.junit", "org.hamcrest", "org.mockito", "android.support.test.internal.runner.junit3", "org.jacoco"};
   static final String MISSING_ARGUMENTS_MSG = "Must provide either classes to run, or apks to scan";
   static final String AMBIGUOUS_ARGUMENTS_MSG = "Ambiguous arguments: cannot provide both test package and test class(es) to run";
   private List mApkPaths;
   private Set mIncludedPackages;
   private Set mExcludedPackages;
   private Set mIncludedClasses;
   private Set mExcludedClasses;
   private TestRequestBuilder.ClassAndMethodFilter mClassMethodFilter;
   private Filter mFilter;
   private boolean mSkipExecution;
   private final TestRequestBuilder.DeviceBuild mDeviceBuild;
   private long mPerTestTimeout;
   private final Instrumentation mInstr;
   private final Bundle mArgsBundle;
   private ClassLoader mClassLoader;
   private boolean mIgnoreSuiteMethods;

   public TestRequestBuilder(Instrumentation instr, Bundle bundle) {
      this(new TestRequestBuilder.DeviceBuildImpl(), instr, bundle);
   }

   TestRequestBuilder(TestRequestBuilder.DeviceBuild deviceBuildAccessor, Instrumentation instr, Bundle bundle) {
      this.mApkPaths = new ArrayList();
      this.mIncludedPackages = new HashSet();
      this.mExcludedPackages = new HashSet();
      this.mIncludedClasses = new HashSet();
      this.mExcludedClasses = new HashSet();
      this.mClassMethodFilter = new TestRequestBuilder.ClassAndMethodFilter();
      this.mFilter = (new TestRequestBuilder.AnnotationExclusionFilter(Suppress.class)).intersect(new TestRequestBuilder.AnnotationExclusionFilter(android.test.suitebuilder.annotation.Suppress.class)).intersect(new TestRequestBuilder.SdkSuppressFilter()).intersect(new TestRequestBuilder.RequiresDeviceFilter()).intersect(this.mClassMethodFilter);
      this.mSkipExecution = false;
      this.mPerTestTimeout = 0L;
      this.mIgnoreSuiteMethods = false;
      this.mDeviceBuild = (TestRequestBuilder.DeviceBuild)Checks.checkNotNull(deviceBuildAccessor);
      this.mInstr = (Instrumentation)Checks.checkNotNull(instr);
      this.mArgsBundle = (Bundle)Checks.checkNotNull(bundle);
   }

   public TestRequestBuilder addApkToScan(String apkPath) {
      this.mApkPaths.add(apkPath);
      return this;
   }

   public TestRequestBuilder setClassLoader(ClassLoader loader) {
      this.mClassLoader = loader;
      return this;
   }

   public TestRequestBuilder addTestClass(String className) {
      this.mIncludedClasses.add(className);
      return this;
   }

   public TestRequestBuilder removeTestClass(String className) {
      this.mExcludedClasses.add(className);
      return this;
   }

   public TestRequestBuilder addTestMethod(String testClassName, String testMethodName) {
      this.mIncludedClasses.add(testClassName);
      this.mClassMethodFilter.addMethod(testClassName, testMethodName);
      this.mIgnoreSuiteMethods = true;
      return this;
   }

   public TestRequestBuilder removeTestMethod(String testClassName, String testMethodName) {
      this.mClassMethodFilter.removeMethod(testClassName, testMethodName);
      this.mIgnoreSuiteMethods = true;
      return this;
   }

   public TestRequestBuilder addTestPackage(String testPackage) {
      this.mIncludedPackages.add(testPackage);
      return this;
   }

   public TestRequestBuilder removeTestPackage(String testPackage) {
      this.mExcludedPackages.add(testPackage);
      return this;
   }

   public TestRequestBuilder addTestSizeFilter(TestSize forTestSize) {
      if (!TestSize.NONE.equals(forTestSize)) {
         this.mFilter = this.mFilter.intersect(new TestRequestBuilder.SizeFilter(forTestSize));
      } else {
         Log.e("TestRequestBuilder", String.format("Unrecognized test size '%s'", forTestSize.getSizeQualifierName()));
      }

      return this;
   }

   public TestRequestBuilder addAnnotationInclusionFilter(String annotation) {
      Class annotationClass = this.loadAnnotationClass(annotation);
      if (annotationClass != null) {
         this.mFilter = this.mFilter.intersect(new TestRequestBuilder.AnnotationInclusionFilter(annotationClass));
      }

      return this;
   }

   public TestRequestBuilder addAnnotationExclusionFilter(String notAnnotation) {
      Class annotationClass = this.loadAnnotationClass(notAnnotation);
      if (annotationClass != null) {
         this.mFilter = this.mFilter.intersect(new TestRequestBuilder.AnnotationExclusionFilter(annotationClass));
      }

      return this;
   }

   public TestRequestBuilder addShardingFilter(int numShards, int shardIndex) {
      this.mFilter = this.mFilter.intersect(new TestRequestBuilder.ShardingFilter(numShards, shardIndex));
      return this;
   }

   public TestRequestBuilder setSkipExecution(boolean b) {
      this.mSkipExecution = b;
      return this;
   }

   public TestRequestBuilder setPerTestTimeout(long millis) {
      this.mPerTestTimeout = millis;
      return this;
   }

   public TestRequestBuilder addFromRunnerArgs(RunnerArgs runnerArgs) {
      Iterator i$ = runnerArgs.tests.iterator();

      RunnerArgs.TestArg test;
      while(i$.hasNext()) {
         test = (RunnerArgs.TestArg)i$.next();
         if (test.methodName == null) {
            this.addTestClass(test.testClassName);
         } else {
            this.addTestMethod(test.testClassName, test.methodName);
         }
      }

      i$ = runnerArgs.notTests.iterator();

      while(i$.hasNext()) {
         test = (RunnerArgs.TestArg)i$.next();
         if (test.methodName == null) {
            this.removeTestClass(test.testClassName);
         } else {
            this.removeTestMethod(test.testClassName, test.methodName);
         }
      }

      i$ = runnerArgs.testPackages.iterator();

      String notAnnotation;
      while(i$.hasNext()) {
         notAnnotation = (String)i$.next();
         this.addTestPackage(notAnnotation);
      }

      i$ = runnerArgs.notTestPackages.iterator();

      while(i$.hasNext()) {
         notAnnotation = (String)i$.next();
         this.removeTestPackage(notAnnotation);
      }

      if (runnerArgs.testSize != null) {
         this.addTestSizeFilter(TestSize.fromString(runnerArgs.testSize));
      }

      if (runnerArgs.annotation != null) {
         this.addAnnotationInclusionFilter(runnerArgs.annotation);
      }

      i$ = runnerArgs.notAnnotations.iterator();

      while(i$.hasNext()) {
         notAnnotation = (String)i$.next();
         this.addAnnotationExclusionFilter(notAnnotation);
      }

      if (runnerArgs.testTimeout > 0L) {
         this.setPerTestTimeout(runnerArgs.testTimeout);
      }

      if (runnerArgs.numShards > 0 && runnerArgs.shardIndex >= 0 && runnerArgs.shardIndex < runnerArgs.numShards) {
         this.addShardingFilter(runnerArgs.numShards, runnerArgs.shardIndex);
      }

      if (runnerArgs.logOnly) {
         this.setSkipExecution(true);
      }

      return this;
   }

   public TestRequest build() {
      this.mIncludedPackages.removeAll(this.mExcludedPackages);
      this.mIncludedClasses.removeAll(this.mExcludedClasses);
      this.validate(this.mIncludedClasses);
      TestLoader loader = new TestLoader();
      loader.setClassLoader(this.mClassLoader);
      if (this.mIncludedClasses.isEmpty()) {
         this.loadClassesFromClassPath(loader, this.mExcludedClasses);
      } else {
         this.loadClasses(this.mIncludedClasses, loader);
      }

      Collection loadedClasses = loader.getLoadedClasses();
      Request request = classes(new AndroidRunnerParams(this.mInstr, this.mArgsBundle, this.mSkipExecution, this.mPerTestTimeout, this.mIgnoreSuiteMethods), new Computer(), (Class[])loadedClasses.toArray(new Class[loadedClasses.size()]));
      return new TestRequest(loader.getLoadFailures(), new TestRequestBuilder.LenientFilterRequest(request, this.mFilter));
   }

   private void validate(Set classNames) {
      if (classNames.isEmpty() && this.mApkPaths.isEmpty()) {
         throw new IllegalArgumentException("Must provide either classes to run, or apks to scan");
      } else if ((!this.mIncludedPackages.isEmpty() || !this.mExcludedPackages.isEmpty()) && !classNames.isEmpty()) {
         throw new IllegalArgumentException("Ambiguous arguments: cannot provide both test package and test class(es) to run");
      }
   }

   private static Request classes(AndroidRunnerParams runnerParams, Computer computer, Class... classes) {
      try {
         Runner suite = computer.getSuite(new AndroidRunnerBuilder(runnerParams), classes);
         return Request.runner(suite);
      } catch (InitializationError var4) {
         throw new RuntimeException("Suite constructor, called as above, should always complete");
      }
   }

   private void loadClassesFromClassPath(TestLoader loader, Set excludedClasses) {
      Collection classNames = this.getClassNamesFromClassPath();
      Iterator i$ = classNames.iterator();

      while(i$.hasNext()) {
         String className = (String)i$.next();
         if (!excludedClasses.contains(className)) {
            loader.loadIfTest(className);
         }
      }

   }

   private void loadClasses(Collection classNames, TestLoader loader) {
      Iterator i$ = classNames.iterator();

      while(i$.hasNext()) {
         String className = (String)i$.next();
         loader.loadClass(className);
      }

   }

   private Collection getClassNamesFromClassPath() {
      if (this.mApkPaths.isEmpty()) {
         throw new IllegalStateException("neither test class to execute or apk paths were provided");
      } else {
         Log.i("TestRequestBuilder", String.format("Scanning classpath to find tests in apks %s", this.mApkPaths));
         ClassPathScanner scanner = this.createClassPathScanner(this.mApkPaths);
         ClassPathScanner.ChainedClassNameFilter filter = new ClassPathScanner.ChainedClassNameFilter();
         filter.add(new ClassPathScanner.ExternalClassNameFilter());
         String[] arr$ = DEFAULT_EXCLUDED_PACKAGES;
         int len$ = arr$.length;

         for(int i$ = 0; i$ < len$; ++i$) {
            String pkg = arr$[i$];
            if (!this.mIncludedPackages.contains(pkg)) {
               this.mExcludedPackages.add(pkg);
            }
         }

         Iterator i$ = this.mIncludedPackages.iterator();

         String pkg;
         while(i$.hasNext()) {
            pkg = (String)i$.next();
            filter.add(new ClassPathScanner.InclusivePackageNameFilter(pkg));
         }

         i$ = this.mExcludedPackages.iterator();

         while(i$.hasNext()) {
            pkg = (String)i$.next();
            filter.add(new ClassPathScanner.ExcludePackageNameFilter(pkg));
         }

         try {
            return scanner.getClassPathEntries(filter);
         } catch (IOException var7) {
            Log.e("TestRequestBuilder", "Failed to scan classes", var7);
            return Collections.emptyList();
         }
      }
   }

   ClassPathScanner createClassPathScanner(List apkPaths) {
      return new ClassPathScanner(apkPaths);
   }

   private Class loadAnnotationClass(String className) {
      try {
         Class clazz = Class.forName(className);
         return clazz;
      } catch (ClassNotFoundException var3) {
         Log.e("TestRequestBuilder", String.format("Could not find annotation class: %s", className));
      } catch (ClassCastException var4) {
         Log.e("TestRequestBuilder", String.format("Class %s is not an annotation", className));
      }

      return null;
   }

   private int getDeviceSdkInt() {
      return this.mDeviceBuild.getSdkVersionInt();
   }

   private String getDeviceHardware() {
      return this.mDeviceBuild.getHardware();
   }

   // $FF: synthetic class
   static class SyntheticClass_1 {
   }

   private static class MethodFilter extends Filter {
      private final String mClassName;
      private Set mIncludedMethods = new HashSet();
      private Set mExcludedMethods = new HashSet();

      public MethodFilter(String className) {
         this.mClassName = className;
      }

      public String describe() {
         return "Method filter for " + this.mClassName + " class";
      }

      public boolean shouldRun(Description description) {
         if (description.isTest()) {
            String methodName = description.getMethodName();
            methodName = this.stripParameterizedSuffix(methodName);
            if (this.mExcludedMethods.contains(methodName)) {
               return false;
            } else {
               return this.mIncludedMethods.isEmpty() || this.mIncludedMethods.contains(methodName) || methodName.equals("initializationError");
            }
         } else {
            return true;
         }
      }

      private String stripParameterizedSuffix(String name) {
         Pattern suffixPattern = Pattern.compile(".+(\\[[0-9]+\\])$");
         if (suffixPattern.matcher(name).matches()) {
            name = name.substring(0, name.lastIndexOf(91));
         }

         return name;
      }

      public void add(String methodName) {
         this.mIncludedMethods.add(methodName);
      }

      public void remove(String methodName) {
         this.mExcludedMethods.add(methodName);
      }
   }

   private static class ClassAndMethodFilter extends Filter {
      private Map mMethodFilters;

      private ClassAndMethodFilter() {
         this.mMethodFilters = new HashMap();
      }

      public boolean shouldRun(Description description) {
         if (this.mMethodFilters.isEmpty()) {
            return true;
         } else {
            if (description.isTest()) {
               String className = description.getClassName();
               TestRequestBuilder.MethodFilter methodFilter = (TestRequestBuilder.MethodFilter)this.mMethodFilters.get(className);
               if (methodFilter != null) {
                  return methodFilter.shouldRun(description);
               }
            } else {
               Iterator i$ = description.getChildren().iterator();

               while(i$.hasNext()) {
                  Description child = (Description)i$.next();
                  if (this.shouldRun(child)) {
                     return true;
                  }
               }
            }

            return false;
         }
      }

      public String describe() {
         return "Class and method filter";
      }

      public void addMethod(String className, String methodName) {
         TestRequestBuilder.MethodFilter mf = (TestRequestBuilder.MethodFilter)this.mMethodFilters.get(className);
         if (mf == null) {
            mf = new TestRequestBuilder.MethodFilter(className);
            this.mMethodFilters.put(className, mf);
         }

         mf.add(methodName);
      }

      public void removeMethod(String className, String methodName) {
         TestRequestBuilder.MethodFilter mf = (TestRequestBuilder.MethodFilter)this.mMethodFilters.get(className);
         if (mf == null) {
            mf = new TestRequestBuilder.MethodFilter(className);
            this.mMethodFilters.put(className, mf);
         }

         mf.remove(methodName);
      }

      // $FF: synthetic method
      ClassAndMethodFilter(TestRequestBuilder.SyntheticClass_1 x0) {
         this();
      }
   }

   private static class BlankRunner extends Runner {
      private BlankRunner() {
      }

      public Description getDescription() {
         return Description.createSuiteDescription("no tests found", new Annotation[0]);
      }

      public void run(RunNotifier notifier) {
      }

      // $FF: synthetic method
      BlankRunner(TestRequestBuilder.SyntheticClass_1 x0) {
         this();
      }
   }

   private static class LenientFilterRequest extends Request {
      private final Request mRequest;
      private final Filter mFilter;

      public LenientFilterRequest(Request classRequest, Filter filter) {
         this.mRequest = classRequest;
         this.mFilter = filter;
      }

      public Runner getRunner() {
         try {
            Runner runner = this.mRequest.getRunner();
            this.mFilter.apply(runner);
            return runner;
         } catch (NoTestsRemainException var2) {
            return new TestRequestBuilder.BlankRunner();
         }
      }
   }

   private static class ShardingFilter extends Filter {
      private final int mNumShards;
      private final int mShardIndex;

      ShardingFilter(int numShards, int shardIndex) {
         this.mNumShards = numShards;
         this.mShardIndex = shardIndex;
      }

      public boolean shouldRun(Description description) {
         if (description.isTest()) {
            return Math.abs(description.hashCode()) % this.mNumShards == this.mShardIndex;
         } else {
            Iterator i$ = description.getChildren().iterator();

            Description each;
            do {
               if (!i$.hasNext()) {
                  return false;
               }

               each = (Description)i$.next();
            } while(!this.shouldRun(each));

            return true;
         }
      }

      public String describe() {
         return String.format("Shard %s of %s shards", this.mShardIndex, this.mNumShards);
      }
   }

   private class RequiresDeviceFilter extends TestRequestBuilder.AnnotationExclusionFilter {
      RequiresDeviceFilter() {
         super(RequiresDevice.class);
      }

      protected boolean evaluateTest(Description description) {
         if (!super.evaluateTest(description)) {
            return !"goldfish".equals(TestRequestBuilder.this.getDeviceHardware());
         } else {
            return true;
         }
      }

      public String describe() {
         return String.format("skip tests annotated with RequiresDevice if necessary");
      }
   }

   private class SdkSuppressFilter extends TestRequestBuilder.ParentFilter {
      private SdkSuppressFilter() {
         super((TestRequestBuilder.SyntheticClass_1)null);
      }

      protected boolean evaluateTest(Description description) {
         SdkSuppress s = this.getAnnotationForTest(description);
         return s == null || TestRequestBuilder.this.getDeviceSdkInt() >= s.minSdkVersion();
      }

      private SdkSuppress getAnnotationForTest(Description description) {
         SdkSuppress s = (SdkSuppress)description.getAnnotation(SdkSuppress.class);
         if (s != null) {
            return s;
         } else {
            Class testClass = description.getTestClass();
            return testClass != null ? (SdkSuppress)testClass.getAnnotation(SdkSuppress.class) : null;
         }
      }

      public String describe() {
         return String.format("skip tests annotated with SdkSuppress if necessary");
      }

      // $FF: synthetic method
      SdkSuppressFilter(TestRequestBuilder.SyntheticClass_1 x1) {
         this();
      }
   }

   private static class AnnotationExclusionFilter extends TestRequestBuilder.ParentFilter {
      private final Class mAnnotationClass;

      AnnotationExclusionFilter(Class annotation) {
         super((TestRequestBuilder.SyntheticClass_1)null);
         this.mAnnotationClass = annotation;
      }

      protected boolean evaluateTest(Description description) {
         Class testClass = description.getTestClass();
         return (testClass == null || !testClass.isAnnotationPresent(this.mAnnotationClass)) && description.getAnnotation(this.mAnnotationClass) == null;
      }

      public String describe() {
         return String.format("not annotation %s", this.mAnnotationClass.getName());
      }
   }

   private static class SizeFilter extends TestRequestBuilder.ParentFilter {
      private final TestSize mTestSize;

      SizeFilter(TestSize testSize) {
         super((TestRequestBuilder.SyntheticClass_1)null);
         this.mTestSize = testSize;
      }

      public String describe() {
         return "";
      }

      protected boolean evaluateTest(Description description) {
         if (this.mTestSize.testMethodIsAnnotatedWithTestSize(description)) {
            return true;
         } else if (this.mTestSize.testClassIsAnnotatedWithTestSize(description)) {
            Iterator i$ = description.getAnnotations().iterator();

            Annotation a;
            do {
               if (!i$.hasNext()) {
                  return true;
               }

               a = (Annotation)i$.next();
            } while(!TestSize.isAnyTestSize(a.annotationType()));

            return false;
         } else {
            return false;
         }
      }
   }

   private static class AnnotationInclusionFilter extends TestRequestBuilder.ParentFilter {
      private final Class mAnnotationClass;

      AnnotationInclusionFilter(Class annotation) {
         super((TestRequestBuilder.SyntheticClass_1)null);
         this.mAnnotationClass = annotation;
      }

      protected boolean evaluateTest(Description description) {
         Class testClass = description.getTestClass();
         return description.getAnnotation(this.mAnnotationClass) != null || testClass != null && testClass.isAnnotationPresent(this.mAnnotationClass);
      }

      protected Class getAnnotationClass() {
         return this.mAnnotationClass;
      }

      public String describe() {
         return String.format("annotation %s", this.mAnnotationClass.getName());
      }
   }

   private abstract static class ParentFilter extends Filter {
      private ParentFilter() {
      }

      public boolean shouldRun(Description description) {
         if (description.isTest()) {
            return this.evaluateTest(description);
         } else {
            Iterator i$ = description.getChildren().iterator();

            Description each;
            do {
               if (!i$.hasNext()) {
                  return false;
               }

               each = (Description)i$.next();
            } while(!this.shouldRun(each));

            return true;
         }
      }

      protected abstract boolean evaluateTest(Description var1);

      // $FF: synthetic method
      ParentFilter(TestRequestBuilder.SyntheticClass_1 x0) {
         this();
      }
   }

   private static class DeviceBuildImpl implements TestRequestBuilder.DeviceBuild {
      private DeviceBuildImpl() {
      }

      public int getSdkVersionInt() {
         return VERSION.SDK_INT;
      }

      public String getHardware() {
         return Build.HARDWARE;
      }

      // $FF: synthetic method
      DeviceBuildImpl(TestRequestBuilder.SyntheticClass_1 x0) {
         this();
      }
   }

   interface DeviceBuild {
      int getSdkVersionInt();

      String getHardware();
   }
}
