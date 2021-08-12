# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in C:\Users\Rebekah\AppData\Local\Android\sdk1/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebViewFragment with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

-ignorewarnings
-keep class * {
    public private *;
}

-keep class com.google.android.gms.* { *; }
-dontwarn com.google.android.gms.**

-dontwarn org.xmlpull.v1.**
-dontnote org.xmlpull.v1.**
-keep class org.xmlpull.* { *; }

#retRofit and for Parse
# Platform calls Class.forName on types which do not exist on Android to determine platform.
-dontnote retrofit2.Platform
# Platform used when running on RoboVM on iOS. Will not be used at runtime.
-dontnote retrofit2.Platform$IOS$MainThreadExecutor
# Platform used when running on Java 8 VMs. Will not be used at runtime.
-dontwarn retrofit2.Platform$Java8
# Retain declared checked exceptions for use by a Proxy instance.
-keepattributes Exceptions
# Required for Parse
 -keepattributes *Annotation*
 # Retain generic type information for use by reflection by converters and adapters.
 -keepattributes Signature
 -dontwarn com.squareup.**
 -dontwarn okio.**
 -dontnote android.net.http.*
 -dontwarn org.conscrypt.**
-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}
-keepclassmembers class * {
    native <methods>;
}
-dontwarn okio.**
-dontwarn com.squareup.okhttp.**
-dontwarn okhttp3.**
-dontwarn javax.annotation.**

 -keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase
 # Keep source file names, line numbers, and Parse class/method names for easier debugging
 -keepattributes SourceFile,LineNumberTable
 -keep class com.parse.*{ *; }
 -dontwarn com.parse.**

 -keepclasseswithmembernames class * {
    native <methods>;
 }

 #-keepclassmembers,allowobfuscation class * {
 #@com.google.gson.annotations.SerializedName <fields>;
# }
 -keep class com.google.gson.* { *; }
 -keep class com.google.inject.* { *; }

#Kotlin Reflection
 -keepclassmembers class kotlin.Metadata {
   public <methods>;
 }
 -keep class kotlin.reflect.jvm.internal.* { *; }

#XML resources
-keepattributes InnerClasses
  -keep class *.R
  -keep class **.R$* {
     <fields>;
 }

 #mapbox
 -keep class com.mapbox.android.telemetry.* { *; }
 -dontwarn com.mapbox.android.telemetry.*

 #databinding
 -dontwarn androidx.databinding.**
 -keep class androidx.databinding.* { *; }
 -keep class * extends androidx.databinding.DataBinderMapper { *; }