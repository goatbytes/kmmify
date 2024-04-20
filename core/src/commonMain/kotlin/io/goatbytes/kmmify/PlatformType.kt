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

package io.goatbytes.kmmify

/**
 * Represents a hierarchy of platform types supported in a Kotlin Multiplatform project.
 * This sealed class enables type-safe representation of platform-specific traits and operations.
 */
sealed class PlatformType {

  /**
   * Provides a common name property that must be implemented by all platform types.
   */
  abstract val name: String

  /**
   * Represents the Android operating system.
   */
  data object Android : PlatformType() {
    override val name get() = "Android"
  }

  /**
   * Represents the Java Virtual Machine platform typically used for desktop or server-side apps.
   */
  data object JVM : PlatformType() {
    override val name get() = "JVM"
  }

  /**
   * Represents the JavaScript environment, commonly used for web platforms.
   */
  data object JS : PlatformType() {
    override val name get() = "JS"
  }

  /**
   * Represents a generic category for native platforms, serving as a base class for specific
   * native operating systems.
   */
  abstract class Native : PlatformType() {
    /**
     * Represents the iOS operating system, commonly used in iPhone and iPad devices.
     */
    data object IOS : Native() {
      override val name get() = "iOS"
    }

    /**
     * Represents the macOS operating system, used in computers manufactured by Apple.
     */
    data object MacOS : Native() {
      override val name get() = "macOS"
    }

    /**
     * Represents the tvOS operating system, used in Apple TV devices.
     */
    data object TVOS : Native() {
      override val name get() = "tvOS"
    }

    /**
     * Represents the watchOS operating system, used in Apple Watch devices.
     */
    data object WatchOS : Native() {
      override val name get() = "watchOS"
    }

    /**
     * Represents the Linux operating system, commonly used in a variety of desktop, server, and
     * embedded systems.
     */
    data object Linux : Native() {
      override val name get() = "Linux"
    }
  }
}
