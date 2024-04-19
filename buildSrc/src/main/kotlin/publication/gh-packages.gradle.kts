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

@file:Suppress("FunctionName")

package publication

import BuildConfig
import BuildConfig.Developer
import BuildConfig.SCM
import java.net.URI

plugins {
  `maven-publish`
  signing
}

publishing {
  repositories {
    maven {
      name = "GitHubPackages"
      url = URI("https://maven.pkg.github.com/${SCM.ORG}/${SCM.NAME}/")
      credentials {
        username = System.getenv("GH_USERNAME")
        password = System.getenv("GH_TOKEN")
      }
    }
  }
  publications.withType<MavenPublication> {
    artifact(
      tasks.register("${name}JavadocJar", Jar::class) {
        archiveClassifier.set("javadoc")
        archiveAppendix.set(this@withType.name)
      }
    )
    pom.run {
      name.set(BuildConfig.NAME)
      description.set(BuildConfig.DESCRIPTION)
      url.set(BuildConfig.URL)
      licenses {
        license {
          name.set(BuildConfig.License.NAME)
          url.set(BuildConfig.License.URL)
          distribution.set("repo")
        }
      }
      developers {
        developer {
          id.set(Developer.ID)
          name.set(Developer.NAME)
          email.set(Developer.EMAIL)
          url.set(Developer.URL)
          timezone.set(Developer.TIMEZONE)
        }
      }
      scm {
        url.set("https://${SCM.PATH}/")
        connection.set("scm:git:git://${SCM.PATH}.git")
        developerConnection.set("scm:git:ssh://git@${SCM.PATH}.git")
      }
    }
  }
}

signing {
  if (SigningConfig.Release.hasCredentials(project)) {
    val config = SigningConfig.Release(project)
    useInMemoryPgpKeys(config.keyId, config.keyRing, config.password)
    sign(publishing.publications)
  }
}
