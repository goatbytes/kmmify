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

import org.gradle.api.JavaVersion
import org.gradle.api.Project
import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * Contains build configuration properties for the project.
 */
object BuildConfig {

  const val NAME = "kmmify"
  const val URL = "https://${SCM.ORG}.github.io/${SCM.NAME}"
  const val DESCRIPTION = "A Kotlin Multiplatform Template"

  const val GROUP = "io.goatbytes.kmmify"
  val VERSION by lazy { Version.get(project) }

  /**
   * Version Update Instructions:
   *
   * - IDENTIFIER:    Indicate the current stage of release (Alpha, Beta, RC, or Release)
   *                  based on the software's readiness for deployment.
   * - MAJOR:         Increment for incompatible API changes. Resets minor and patch levels to 0.
   * - MINOR:         Increment for adding functionality in a backwards-compatible manner.
   *                  Resets the patch level to 0.
   * - PATCH:         Increment for making backwards-compatible bug fixes.
   * - BUILD_NUMBER:  Increment with each build to uniquely identify it.
   *
   * Refer to the Semantic Versioning specification at https://semver.org/ for detailed guidelines.
   */
  object Version {
    private const val MAJOR = 0
    private const val MINOR = 0
    private const val PATCH = 1
    private const val BUILD_NUMBER = 0
    private val IDENTIFIER = build.Version.Identifier.SNAPSHOT

    private val BUILD_TIME_PATTERN: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHH")

    /**
     * Generates the project version using semantic versioning.
     *
     * @param project The project
     * @return The project version
     */
    fun get(project: Project) = build.Version.Semantic(
      major = MAJOR,
      minor = MINOR,
      patch = PATCH,
      identifier = IDENTIFIER,
      metadata = build.Version.Metadata(
        buildNumber = BUILD_NUMBER,
        gitSha = project.gitCommitSha,
        gitBranch = project.gitBranchName,
        buildTime = LocalDateTime.now().format(BUILD_TIME_PATTERN)
      )
    )
  }

  /**
   * Contains the License name and URL for the project
   */
  object License {
    const val NAME = "Apache v2.0"
    const val URL = "https://${SCM.PATH}/blob/main/LICENSE.txt"
  }

  private lateinit var project: Project

  /**
   * Android Build Configuration
   */
  object Android {
    const val NAMESPACE = "$GROUP.android"
    const val COMPILE_SDK = 34
    const val MIN_SDK = 24
    val javaVersion = JavaVersion.VERSION_1_8
  }

  /**
   * Apple Target Build Configuration
   */
  object Apple {
    const val FRAMEWORK_NAME = NAME
  }

  /**
   * Contains configuration for Detekt static analysis plugin.
   */
  object Detekt {
    val CONFIG by lazy { "${project.rootDir}/detekt.yml" }
    val jvmTarget = JavaVersion.VERSION_1_8.toString()
  }

  /**
   * Contains constants for Dokka
   */
  object Dokka {
    const val DOC_LINK = "https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/"
    const val PKG_LIST = "https://kotlin.github.io/kotlinx.coroutines/package-list"
    val outputDirectory: File get() = project.file("${project.rootDir}/docs/docs")
  }

  /**
   * Defines constants for SCM (Software Configuration Management) related to the project.
   */
  object SCM {
    /** Host of the SCM. */
    const val HOST = "github.com"

    /** Organization name within the SCM. */
    const val ORG = "goatbytes"

    /** Project name within the SCM. */
    const val NAME = "kmmify"

    /** Full SCM path constructed from host, organization, and project name. */
    const val PATH = "$HOST/$ORG/$NAME"
  }

  /**
   * Contains the developer metadata for the POM.
   */
  object Developer {
    const val ID = "goatbytes"
    const val NAME = "GoatBytes.IO"
    const val EMAIL = "engineering@goatbytes.io"
    const val URL = "https://goatbytes.io"
    const val TIMEZONE = "America/Los_Angeles"
  }

  /**
   * Initialize the [BuildConfig] to reference the root project
   *
   * @param rootProject The root project
   * @return The [BuildConfig] for chaining any method calls.
   */
  fun initialize(rootProject: Project) = apply {
    project = rootProject
  }
}
