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

package publication

import org.gradle.api.Project

/**
 * Represents the configuration for signing artifacts, encapsulating necessary details.
 *
 * @property keyId The ID of the signing key.
 * @property keyRing The location of the keyring containing the signing key.
 * @property password The password for accessing the signing key.
 */
sealed class SigningConfig(
  val keyId: String,
  val keyRing: String,
  val password: String
) {

  /**
   * Specific signing configuration for the release publication.
   *
   * @param project The Gradle project context to derive properties from.
   */
  class Release(project: Project) : SigningConfig(
    keyId = project prop KEY_ID,
    keyRing = project prop KEYRING,
    password = project prop PASSWORD
  ) {
    companion object {

      /**
       * Checks if the necessary signing credentials are present in the project's properties
       * or environment variables.
       *
       * @param project The Gradle project to check for properties.
       * @return True if all required credentials are available; false otherwise.
       */
      fun hasCredentials(project: Project): Boolean {
        return listOf(KEY_ID, KEYRING, PASSWORD).all { name -> project hasPropOrEnv name }
      }

      private const val KEY_ID = "SIGNING_KEY_ID"
      private const val KEYRING = "SIGNING_KEYRING"
      private const val PASSWORD = "SIGNING_PASSWORD"
    }
  }

  private companion object {
    /**
     * Retrieves a project property or environment variable value by name.
     *
     * @param name The name of the property or environment variable to retrieve.
     * @return The value of the property or environment variable.
     */
    private infix fun Project.prop(name: String): String {
      return (System.getenv(name) ?: findProperty(name)) as String
    }

    /**
     * Checks if a property or environment variable is present.
     *
     * @param name The name of the property or environment variable to check.
     * @return True if the property or environment variable is present; false otherwise.
     */
    private infix fun Project.hasPropOrEnv(name: String): Boolean {
      return hasProperty(name) || System.getenv(name) != null
    }
  }
}
