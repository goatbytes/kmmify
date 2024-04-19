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

import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.DetektCreateBaselineTask

BuildConfig.initialize(project)

plugins {
  alias(libs.plugins.android.library).apply(false)
  alias(libs.plugins.kotlin.multiplatform).apply(false)
  alias(libs.plugins.detekt)
  alias(libs.plugins.dokka)
}

allprojects {
  group = BuildConfig.GROUP
  version = BuildConfig.VERSION
}

dependencies {
  detektPlugins(libs.detekt.formatting)
}

detekt {
  buildUponDefaultConfig = true
  config.setFrom(BuildConfig.DETEKT_CONFIG)
}

tasks.withType<Detekt>().configureEach {
  reports {
    html.required.set(true)
    md.required.set(true)
    sarif.required.set(false)
    txt.required.set(false)
    xml.required.set(false)
  }
}

tasks.withType<Detekt>().configureEach {
  jvmTarget = BuildConfig.Dokka.jvmTarget
}

tasks.withType<DetektCreateBaselineTask>().configureEach {
  jvmTarget = BuildConfig.Dokka.jvmTarget
}

val detektAll by tasks.registering(Detekt::class) {
  description = "Run detekt analysis on entire project"
  parallel = true
  buildUponDefaultConfig = true
  config.setFrom(BuildConfig.DETEKT_CONFIG)
  setSource(files(projectDir))
  include("**/*.kt", "**/*.kts")
  exclude("resources/", "*/build/*")
}

tasks.dokkaHtml {
  outputDirectory.set(BuildConfig.Dokka.outputDirectory)
  dokkaSourceSets {
    configureEach {
      noStdlibLink.set(false)
      noJdkLink.set(false)
      externalDocumentationLink {
        url.set(java.net.URL(BuildConfig.Dokka.DOC_LINK))
        packageListUrl.set(java.net.URL(BuildConfig.Dokka.PKG_LIST))
      }
      displayName.set(
        when (val name = displayName.get() ?: name) {
          "jvm" -> "JVM"
          "js" -> "JavaScript"
          else -> name.capitalize()
        }
      )
    }
  }
}
