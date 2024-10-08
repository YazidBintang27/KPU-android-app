plugins {
   alias(libs.plugins.android.application)
   alias(libs.plugins.kotlin.android)
   id("com.google.devtools.ksp")
   id("com.google.dagger.hilt.android")
   id ("androidx.navigation.safeargs")
}

android {
   namespace = "com.latihan.kpuapp"
   compileSdk = 34

   buildFeatures {
      viewBinding = true
   }

   defaultConfig {
      applicationId = "com.latihan.kpuapp"
      minSdk = 24
      targetSdk = 34
      versionCode = 1
      versionName = "1.0"

      testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
   }

   buildTypes {
      release {
         isMinifyEnabled = false
         proguardFiles(
            getDefaultProguardFile("proguard-android-optimize.txt"),
            "proguard-rules.pro"
         )
      }
   }
   compileOptions {
      sourceCompatibility = JavaVersion.VERSION_1_8
      targetCompatibility = JavaVersion.VERSION_1_8
   }
   kotlinOptions {
      jvmTarget = "1.8"
   }
}

dependencies {

   implementation(libs.androidx.core.ktx)
   implementation(libs.androidx.appcompat)
   implementation(libs.material)
   implementation(libs.androidx.activity)
   implementation(libs.androidx.constraintlayout)
   testImplementation(libs.junit)
   androidTestImplementation(libs.androidx.junit)
   androidTestImplementation(libs.androidx.espresso.core)

   implementation(libs.navigation.fragment)
   implementation(libs.navigation.ui)
   implementation(libs.androidx.room.runtime)
   ksp(libs.androidx.room.compiler)
   implementation(libs.androidx.room.ktx)
   implementation(libs.hilt.android)
   ksp(libs.dagger.compiler)
   ksp(libs.hilt.compiler)
   implementation(libs.androidx.lifecycle.viewmodel.ktx)
   implementation(libs.play.services.location)
   implementation(libs.glide)
   annotationProcessor(libs.compiler)

}