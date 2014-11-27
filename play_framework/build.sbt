name := """play-bigpipe-with-rxjava-hystrix"""

version := "1.0"

lazy val root = (project in file(".")).enablePlugins(PlayScala, SbtWeb)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  "com.netflix.hystrix"  % "hystrix-core"   % "1.4.0-RC5",
  "com.netflix.hystrix"  % "hystrix-metrics-event-stream"  % "1.4.0-RC5",
  "com.netflix.rxjava"   % "rxjava-scala"   % "0.20.7",
  "com.netflix.rxjava"   % "rxjava-core"   % "0.20.7",
  "org.webjars" % "bootstrap" % "3.1.1",
  "org.webjars" % "font-awesome" % "4.1.0",
  "org.webjars" % "headjs" % "1.0.3",
  "com.netflix.ribbon" % "ribbon-core" % "2.0-RC13",
  "com.netflix.ribbon" % "ribbon-httpclient" % "2.0-RC13",
  "com.netflix.ribbon" % "ribbon-loadbalancer" % "2.0-RC13"
)

TwirlKeys.templateFormats += ("pagelet" -> "pagelet.PageletObservableFormat")

includeFilter in (Assets, LessKeys.less) := "*.less"
