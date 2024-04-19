/*
 * Copyright 2024 GoatBytes.IO
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

import build.Os
import org.jetbrains.kotlin.gradle.dsl.JsModuleKind

plugins {
  alias(libs.plugins.kotlin.multiplatform)
  alias(libs.plugins.android.library)
  id("publication.gh-packages")
}

kotlin {
  applyDefaultHierarchyTemplate()

  targets.all {
    compilations.all {
      kotlinOptions {
        freeCompilerArgs += listOf(
          "-Xexpect-actual-classes",
        )
      }
    }
  }

  js(IR) {
    compilations.all {
      kotlinOptions {
        moduleKind = JsModuleKind.MODULE_UMD.kind
        sourceMap = true
        metaInfo = true
      }
    }
    nodejs {
      testTask {
        useMocha {
          timeout = "30s"
        }
      }
    }
    browser {
      webpackTask {
        outputDirectory.set(layout.buildDirectory.get().file("dist/js").asFile)
      }
    }
    binaries.executable()
  }

  jvm()

  androidTarget {
    publishLibraryVariants("release")
    compilations.all {
      kotlinOptions {
        jvmTarget = BuildConfig.Android.javaVersion.toString()
      }
    }
  }

  if (Os.isMacOS) {
    appleTargetsWithFramework(BuildConfig.Apple.FRAMEWORK_NAME)
  }

  if (Os.isLinux) {
    linuxX64("linux") {
      compilations.getByName("main") {
        kotlinOptions {
          // Required for CPointer etc. since Kotlin 1.9.
          freeCompilerArgs += "-opt-in=kotlinx.cinterop.ExperimentalForeignApi"
        }
      }
    }
  }

  sourceSets {
    commonMain.dependencies {
      implementation(libs.kotlinx.coroutines.core)
    }
    commonTest.dependencies {
      implementation(libs.kotlin.test)
      implementation(libs.kotlinx.coroutines.test)
    }
    androidMain.dependencies {
      implementation(libs.kotlin.stdlib)
    }
    androidUnitTest.dependencies {
      implementation(libs.junit)
      implementation(libs.mockito.core)
      implementation(libs.kotlinx.coroutines.test)
    }
    jsMain.dependencies {
      implementation(libs.kotlinx.coroutines.core.js)
    }
    jsTest.dependencies {
      implementation(kotlin("test-js"))
      implementation(libs.kotlinx.coroutines.test)
    }
    appleMain.dependencies {
      implementation(libs.kotlinx.coroutines.core)
    }
  }
}

android {
  namespace = BuildConfig.Android.NAMESPACE
  compileSdk = BuildConfig.Android.COMPILE_SDK
  defaultConfig {
    minSdk = BuildConfig.Android.MIN_SDK
  }
}
