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

@file:Suppress("Filename")

import build.CIPlatform
import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet
import java.util.*

val Project.gitBranchName: String
  get() = CIPlatform.detect()?.getBranchName() ?: exec(
    "git branch --show-current || git symbolic-ref --short HEAD || echo 'unknown'"
  )

val Project.gitCommitSha: String
  get() = CIPlatform.detect()?.getCommitSha() ?: exec(
    "git --no-pager log -1 --format=%h || git rev-parse --short HEAD || echo 'unknown'"
  )

/**
 * Executes a single command line specified as a string.
 *
 * @param command string representing the command to execute and its arguments, separated by spaces.
 * @return The command's output as a string.
 */
fun Project.exec(command: String) = external.CommandLine(this).execute(command).stdout

/**
 * Sets up all Apple targets for a multiplatform module.
 *
 * @param baseName The name of the framework
 */
@Suppress("NOTHING_TO_INLINE")
inline fun KotlinMultiplatformExtension.appleTargetsWithFramework(baseName: String) {
  // List of iOS targets
  listOf(iosX64(), iosArm64()).forEach { target ->
    target.binaries.framework(baseName)
  }
  // macOS targets
  listOf(macosX64(), macosArm64()).forEach { target ->
    target.binaries.framework(baseName)
  }
  // tvOS targets
  listOf(tvosX64(), tvosArm64()).forEach { target ->
    target.binaries.framework(baseName)
  }
  // watchOS targets
  listOf(watchosX64(), watchosArm64(), watchosArm32()).forEach { target ->
    target.binaries.framework(baseName)
  }
}

/**
 * Returns a copy of this string having its first letter title-cased using the rules of the default
 * locale, or the original string if it's empty or already starts with a title case letter.
 */
fun String.capitalize() =
  replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }

inline val NamedDomainObjectContainer<KotlinSourceSet>.androidUnitTest
  get(): KotlinSourceSet = getByName("androidUnitTest")
