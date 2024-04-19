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
 * Represents the architecture types that can be identified.
 */
enum class Arch {
  X64, Arm32, Arm64;
  companion object {
    /** The current architecture. */
    val current: Arch by lazy {
      val arch = System.getProperty("os.arch").lowercase()
      when {
        "amd64" in arch || "x86_64" in arch -> X64
        "arm" in arch || "arm32" in arch -> Arm32
        "aarch64" in arch -> Arm64
        else -> error("Unsupported build environment: $arch")
      }
    }
    val isX64 get() = current == X64
    val isArm32 get() = current == Arm32
    val isArm64 get() = current == Arm64
  }
}
