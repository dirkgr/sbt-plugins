package org.allenai.plugins

import sbt._
import sbt.Keys._

object CoreRepositories {

  /** Common resolvers */
  object Resolvers {
    private val ai2RepoUrl = "http://utility.allenai.org:8081/nexus/content/repositories/"
    private val sonatypeUrl = "https://oss.sonatype.org"

    val sonatypeSnapshots =
      "Sonatype Snapshots" at s"${sonatypeUrl}/content/repositories/snapshots"

    val sonatypeReleases =
      "Sonatype Releases" at s"${sonatypeUrl}/service/local/staging/deploy/maven2"

    val ai2PrivateSnapshots = "AI2 Private Snapshots" at s"${ai2RepoUrl}/snapshots"
    val ai2PrivateReleases = "AI2 Private Releases" at s"${ai2RepoUrl}/releases"
    val ai2PublicSnapshots = "AI2 Public Snapshots" at s"${ai2RepoUrl}/public-snapshots"
    val ai2PublicReleases = "AI2 Public Releases" at s"${ai2RepoUrl}/public-releases"

    // needed for spray-json:
    val spray = "spray" at "http://repo.spray.io/"

    val typesafeReleases = "Typesafe Releases" at "http://repo.typesafe.com/typesafe/releases/"
    /** Default set of resolvers that will be added via CoreSettings */
    val defaults = Seq(
      spray,
      typesafeReleases,
      ai2PublicReleases)
  }

  /** Provides publishTo setting for specific repositories */
  object PublishTo {
    import Resolvers._

    /** Sets publishTo to public Sonatype repo according to isSnapshot value */
    val sonatype = {
      publishTo := {
        if (isSnapshot.value) Some(sonatypeSnapshots) else Some(sonatypeReleases)
      }
    }

    /** Sets publishTo to private AI2 repo according to isSnapshot value */
    val ai2Private = {
      publishTo := {
        if (isSnapshot.value) Some(ai2PrivateSnapshots) else Some(ai2PrivateReleases)
      }
    }

    /** Sets publishTo to public AI2 repo according to isSnapshot value */
    val ai2Public = {
      publishTo := {
        if (isSnapshot.value) Some(ai2PublicSnapshots) else Some(ai2PublicReleases)
      }
    }
  }
}