# This project is dead

# Android stub artifacts

These are artifacts published in the JitPack repository. These are not functional and only provides stubbed implementations. All methods in all classes throw a runtime exception. Because an Android app runs on a device, it will never use these libraries for execution, but the API compatibility allows an app to be compiled as if it was the real library.

That would be useful if you are trying to build a java part of your Android code.

## Repo
[![](https://github.com/Scarabei/AndroidArtifacts.svg)](https://github.com/Scarabei/AndroidArtifacts)

## Usage

### In your library

#### Step 1. Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:

```	
ext {
   androidArtifactsVersion = "5278588d80-2017-07-24"
}

allprojects {
   repositories {
      ...
      maven { url 'https://jitpack.io' }
   }
}
```

For the latest ```androidArtifactsVersion``` value see the builds section: https://github.com/Scarabei/AndroidArtifacts/releases

#### Step 2. Add the dependency

```
  dependencies {
      compile "com.github.Scarabei.AndroidArtifacts:%put-dependency-name-here%:$androidArtifactsVersion"
  }
```
Now you can develop/compile your java library aganst the Android API without Android framework. Also you can publish it on a Maven repo. 


### In your Android app project

#### Step 4. Import your library.

This part is on you. Should look something like:

```
dependencies {
      compile "com.your.java-lib"
   }
```

#### Step 5 (Optional). Enable multidex.
That would be necessary if you fail to compile your Android app with errors like:
 - ```Error:Execution failed for task :app:transformClassesWithDexForDebug.```
 - ```Error:Error converting bytecode to dex```

or similar. 

Edit your gradle settings:

```
android {
    
    defaultConfig {
        minSdkVersion ...
        targetSdkVersion ...
        ...

        multiDexEnabled = true
    }

    dexOptions {
        javaMaxHeapSize "4g" //specify the heap size for the dex process
    }
```

See the link for details: https://developer.android.com/studio/build/multidex.html

#### Step 6. Exclude the AndroidArtifacts stub.

```
android {
   ...
   configurations {
        all*.exclude group: "com.github.Scarabei.AndroidArtifacts", module: "put-dependency-name-here"
    }
}
```

By this we tell AbdroidStudio to discard the stub jar to use the real Flutter API on a device.

