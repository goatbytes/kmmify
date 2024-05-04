/*
 * Copyright (c) 2024 GoatBytes.IO. All rights reserved.
 *
 * This file is part of peopledatalabs-kotlin project by GoatBytes.IO and is released under the
 * GoatBytes.IO PDL Software License Agreement.
 *
 * For details, see the LICENSE.md file in the root of the project or contact legal@goatbytes.io
 * for a full copy.
 *
 * RESTRICTIONS: This software is provided for internal use only. Unauthorized use, copying,
 * modification, or distribution of this software is strictly prohibited. This software is offered
 * "AS IS", without warranties of any kind, and subject to the limitations stated in the license
 * agreement.
 *
 */

package build

import build.Version.Identifier.Alpha
import build.Version.Identifier.Beta
import build.Version.Identifier.RC
import build.Version.Identifier.SNAPSHOT

/**
 * Represents a base class for different versioning strategies.
 */
sealed class Version {

  /** Represents the version as a human-readable string. */
  abstract val name: String

  /** Represents the version as a unique, sortable numeric code. */
  abstract val code: Long

  /** Enumerates common pre-release identifiers for software versions. */
  enum class Identifier {
    Alpha, Beta, RC, Release, SNAPSHOT
  }

  /**
   * Contains metadata related to the build or version, including Git information and build timing.
   *
   * @property buildNumber An incremental number representing the sequence of the build.
   * @property gitSha The Git SHA hash of the commit from which the build was created.
   * @property gitBranch The name of the Git branch from which the build was created.
   * @property buildTime The timestamp representing when the build was created.
   */
  data class Metadata(
    val buildNumber: Int?,
    val gitSha: String?,
    val gitBranch: String?,
    val buildTime: String?,
  )

  /**
   * Represents a semantic version, including major, minor, and patch levels, along with optional
   * pre-release identifiers and metadata.
   *
   * @property major The major version number, indicating incompatible API changes.
   * @property minor The minor version number, indicating added functionality in a backwards-compatible manner.
   * @property patch The patch version number, indicating backwards-compatible bug fixes.
   * @property identifier An optional pre-release identifier to denote version stability.
   * @property metadata Optional additional metadata providing context about the build or version.
   */
  data class Semantic(
    val major: Int,
    val minor: Int,
    val patch: Int,
    val identifier: Identifier? = null,
    val metadata: Metadata? = null
  ) : Version() {

    override val name by lazy {
      "$major.$minor.$patch${preRelease()}${buildMetadata()}"
    }

    override val code: Long
      get() {
        val majorPart = major * MAJOR_MULTIPLIER
        val minorPart = minor * MINOR_MULTIPLIER
        val patchPart = patch * PATCH_MULTIPLIER
        val identifierPart = (identifier?.ordinal ?: 0) * IDENTIFIER_MULTIPLIER
        val buildNumberPart = metadata?.buildNumber?.toLong() ?: 0L
        return majorPart + minorPart + patchPart + identifierPart + buildNumberPart
      }

    override fun toString() = name

    private fun preRelease(): String = when (identifier) {
      Identifier.Release, null -> ""
      SNAPSHOT -> "-SNAPSHOT"
      else -> "-${identifier.name.lowercase()}"
    }

    private fun buildMetadata() = buildString {
      when (identifier) {
        Alpha, Beta, RC, SNAPSHOT -> metadata?.run {
          var separator = "+"
          listOf(
            buildNumber?.takeIf { it > 0 },
            buildTime,
            gitBranch?.takeIf { it !in listOf("main") && !it.contains("/") },
            gitSha
          ).mapNotNull { it }.forEach { data ->
            append(separator).append(data).also { separator = "." }
          }
        }
        else -> {
          // no-op
        }
      }
    }

    private companion object {
      private const val MAJOR_MULTIPLIER = 1_000_000_000_000L
      private const val MINOR_MULTIPLIER = 1_000_000_000L
      private const val PATCH_MULTIPLIER = 1_000_000L
      private const val IDENTIFIER_MULTIPLIER = 100_000L
    }
  }
}
