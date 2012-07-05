import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

    val appName         = "itemLister"
    val appVersion      = "1.0-SNAPSHOT"

    val appDependencies = Seq(
      // Add your project dependencies here,
		"org.hibernate" % "hibernate-entitymanager" % "4.1.4.Final",
		"org.springframework" % "spring-web" % "3.1.1.RELEASE",
		"org.springframework.data" % "spring-data-jpa" % "1.1.0.RELEASE",
		"commons-io" % "commons-io" % "1.3.2",
    	"play" %% "spring" % "2.0"
      
    )

    val main = PlayProject(appName, appVersion, appDependencies, mainLang = JAVA).settings(
      // Add your own project settings here
      resolvers += "SpringSource Repository" at "http://repo.springsource.org/release",
      resolvers += "Spring Maven Release Repository" at "http://repo.springsource.org/libs-release",
      resolvers += "TAMU Release Repository" at "https://maven.library.tamu.edu/content/repositories/releases/",

	  ebeanEnabled := false            
    )

}
