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
 * Provides a common interface to interact with platform-specific features.
 */
actual class Platform actual constructor() {
  /**
   * Retrieves the type of the platform on which the application is running.
   * @return The enum value representing the platform type.
   */
  actual fun type(): PlatformType = PlatformType.JS
}
