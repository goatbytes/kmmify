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

package build

/**
 * Represents the operating system types that can be identified.
 */
enum class Os {
  Mac, Windows, Linux;
  companion object {
    /** The current operating system. */
    val current: Os by lazy {
      val osName = System.getProperty("os.name").lowercase()
      when {
        "mac" in osName -> Mac
        "linux" in osName -> Linux
        "windows" in osName -> Windows
        else -> error("Unsupported build environment: $osName")
      }
    }
    val isLinux get() = current == Linux
    val isWindows get() = current == Windows
    val isMacOS get() = current == Mac
  }
}
