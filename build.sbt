name := """play-java-spring-jpa-rest"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  "org.springframework" % "spring-context" % "4.0.4.RELEASE",
  "org.springframework" % "spring-orm" % "4.0.4.RELEASE",
  javaJpa.exclude("org.hibernate.javax.persistence", "hibernate-jpa-2.0-api"),
  "org.hibernate" % "hibernate-entitymanager" % "4.3.8.Final", // replace by your jpa implementation
  "org.springframework" % "spring-test" % "4.0.4.RELEASE" % "test"
)

fork in Test := false
