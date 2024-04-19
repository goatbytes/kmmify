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
 * Enum class representing supported CI/CD platforms. It provides utility methods to detect the
 * current CI/CD platform and to fetch the branch name and commit SHA based on platform-specific
 * environment variables.
 *
 * @param detectionVar The environment variable used to detect if running on the specific CI/CD platform.
 * @param branchNameVar The environment variable that stores the git branch name on this platform.
 * @param commitShaVar The environment variable that stores the git commit SHA on this platform.
 */
enum class CIPlatform(
  private val detectionVar: String,
  private val branchNameVar: String,
  private val commitShaVar: String
) {
  /**
   * GitHub Actions is GitHub's automation and CI/CD platform, allowing users to automate workflows
   * directly from their GitHub repositories.
   * More info: https://github.com/features/actions
   */
  GITHUB_ACTIONS("GITHUB_ACTIONS", "GITHUB_REF_NAME", "GITHUB_SHA"),

  /**
   * GitLab CI/CD is integrated into GitLab and provides a well-supported platform for automating
   * the build, test, and deployment of your applications.
   * More info: https://docs.gitlab.com/ee/ci/
   */
  GITLAB_CI("GITLAB_CI", "CI_COMMIT_REF_NAME", "CI_COMMIT_SHA"),

  /**
   * Jenkins is an open-source automation server that provides hundreds of plugins to support
   * building, deploying, and automating any project.
   * More info: https://www.jenkins.io/
   */
  JENKINS("JENKINS_URL", "GIT_BRANCH", "GIT_COMMIT"),

  /**
   * CircleCI offers cloud-based CI/CD services to automate the software development process.
   * It is known for its flexibility and scalability.
   * More info: https://circleci.com/
   */
  CIRCLE_CI("CIRCLECI", "CIRCLE_BRANCH", "CIRCLE_SHA1"),

  /**
   * Bitbucket Pipelines is Bitbucket's integrated tool for CI/CD, directly configured through a
   * YAML file in the Bitbucket repository.
   * More info: https://bitbucket.org/product/features/pipelines
   */
  BITBUCKET_PIPELINES("BITBUCKET_COMMIT", "BITBUCKET_BRANCH", "BITBUCKET_COMMIT"),

  /**
   * Travis CI is a cloud-based CI service that automatically builds and tests code changes,
   * offering seamless integration with GitHub.
   * More info: https://travis-ci.com/
   */
  TRAVIS_CI("TRAVIS", "TRAVIS_BRANCH", "TRAVIS_COMMIT"),

  /**
   * Azure Pipelines is a part of Azure DevOps Services, providing CI/CD that works with any
   * language, platform, and cloud.
   * More info: https://azure.microsoft.com/en-us/services/devops/pipelines/
   */
  AZURE_PIPELINES("TF_BUILD", "BUILD_SOURCEBRANCHNAME", "BUILD_SOURCEVERSION"),

  /**
   * Bamboo by Atlassian is a CI/CD server that integrates with other Atlassian products. It is
   * known for its build and deployment projects management.
   * More info: https://www.atlassian.com/software/bamboo
   */
  BAMBOO("bamboo_planKey", "bamboo_repository_branch_name", "bamboo_planRepository_revision"),

  /**
   * TeamCity by JetBrains is a powerful CI/CD server known for its ease of setup, comprehensive
   * build, test, and deployment features.
   * More info: https://www.jetbrains.com/teamcity/
   */
  TEAMCITY("TEAMCITY_VERSION", "env.BRANCH_NAME", "build.vcs.number"),

  /**
   * Bitrise is a Mobile Continuous Integration and Delivery CI/CD for mobile app development, known
   * for its excellent support for iOS, Android, Flutter, and React Native projects.
   * More info: https://www.bitrise.io/
   */
  BITRISE("BITRISE_IO", "BITRISE_GIT_BRANCH", "BITRISE_GIT_COMMIT");

  companion object {
    /**
     * Detects the current CI/CD platform based on the presence of a platform-specific environment variable.
     *
     * @return The detected [CIPlatform] or `null` if the platform is not recognized.
     */
    fun detect(): CIPlatform? = values().firstOrNull { System.getenv(it.detectionVar) != null }
  }

  /**
   * Gets the git branch name for the current CI/CD platform.
   *
   * @return The branch name if available and applicable for the detected platform, otherwise `null`.
   */
  fun getBranchName(): String? = System.getenv(branchNameVar)

  /**
   * Gets the git commit SHA for the current CI/CD platform.
   *
   * @return The commit SHA if available and applicable for the detected platform, otherwise `null`.
   */
  fun getCommitSha(): String? = System.getenv(commitShaVar)
}
