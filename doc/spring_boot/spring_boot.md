**Spring Boot 基础**
_https://www.ibm.com/developerworks/cn/java/j-spring-boot-basics-perry/index.html_
**Spring Boot Reference Guide**
_https://docs.spring.io/spring-boot/docs/current-SNAPSHOT/reference/htmlsingle/#getting-started-first-application_


## Getting started
### Maven installation

Spring Boot dependencies use the org.springframework.boot groupId. Typically your Maven POM file will inherit from the spring-boot-starter-parent project and declare dependencies to one or more “Starters”. Spring Boot also provides an optional Maven plugin to create executable jars.
Here is a typical pom.xml file:

    <?xml version="1.0" encoding="UTF-8"?>
    <project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
        <modelVersion>4.0.0</modelVersion>
    
        <groupId>com.example</groupId>
        <artifactId>myproject</artifactId>
        <version>0.0.1-SNAPSHOT</version>
    
        <!-- Inherit defaults from Spring Boot -->
        <parent>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-parent</artifactId>
            <version>2.0.0.BUILD-SNAPSHOT</version>
        </parent>
    
        <!-- Add typical dependencies for a web application -->
        <dependencies>
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-web</artifactId>
            </dependency>
        </dependencies>
    
        <!-- Package as an executable jar -->
        <build>
            <plugins>
                <plugin>
                    <groupId>org.springframework.boot</groupId>
                    <artifactId>spring-boot-maven-plugin</artifactId>
                </plugin>
            </plugins>
        </build>
    </project>

### Installing the Spring Boot CLI
The Spring Boot CLI is a command line tool that can be used if you want to quickly prototype with Spring. It allows you to run Groovy scripts, which means that you have a familiar Java-like syntax, without so much boilerplate code.
OSX Homebrew installation:

    $ brew tap pivotal/tap
    $ brew install springboot

Homebrew will install spring to /usr/local/bin.
Test: 

    $ spring run ./app.groovy

### Developing your first Spring Boot application
#### Creating the POM
#### Adding classpath dependencies

    $ mvn dependency:tree

#### Writing the code
> Starters and Auto-Configuration
>
> Auto-configuration is designed to work well with “Starters”, but the two concepts are not directly tied. You are free to pick-and-choose jar dependencies outside of the starters and Spring Boot will still do its best to auto-configure your application.

#### Running the example

    mvn spring-boot:run
    
#### Creating an executable jar
To create an executable jar we need to add the spring-boot-maven-plugin to our pom.xml. Insert the following lines just below the dependencies section:

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>

The spring-boot-starter-parent POM includes <executions> configuration to bind the repackage goal. If you are not using the parent POM you will need to declare this configuration yourself.

    $ mvn package
    $ jar tvf target/{myproject}-0.0.1-SNAPSHOT.jar
    $ java -jar target/{myproject}-0.0.1-SNAPSHOT.jar


## Using Spring Boot

#### Build systems

**Starters**

The starters contain a lot of the dependencies that you need to get a project up and running quickly and with a consistent, supported set of managed transitive dependencies.

> What’s in a name
>
> All official starters follow a similar naming pattern; spring-boot-starter-*, where * is a particular type of application.
> Third party starters should not start with spring-boot as it is reserved for official Spring Boot artifacts. 
> A third-party starter for acme will be typically named acme-spring-boot-starter.
    
TO.DO. (Table) _Spring Boot application starters_

#### Structuring your code

**Locating the main application class**

We generally recommend that you locate your main application class in a root package above other classes. 
The `@EnableAutoConfiguration` annotation is often placed on your main class, and it implicitly defines a base “search package” for certain items.
Using a root package also allows the `@ComponentScan` annotation to be used without needing to specify a basePackage attribute. 
You can also use the `@SpringBootApplication` annotation if your main class is in the root package.

Here is a typical layout:

    com
     +- example
         +- myproject
             +- Application.java
             |
             +- domain
             |   +- Customer.java
             |   +- CustomerRepository.java
             |
             +- service
             |   +- CustomerService.java
             |
             +- web
                 +- CustomerController.java

The Application.java file would declare the main method, along with the basic `@Configuration`.

	package com.example.myproject;
	
	import org.springframework.boot.SpringApplication;
	import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
	import org.springframework.context.annotation.ComponentScan;
	import org.springframework.context.annotation.Configuration;
	
	@Configuration
	@EnableAutoConfiguration
	@ComponentScan
	public class Application {
	
	    public static void main(String[] args) {
	        SpringApplication.run(Application.class, args);
	    }
	
	}

#### Configuration classes

Spring Boot favors Java-based configuration. 
Although it is possible to use `SpringApplication` with an XML sources, we generally recommend that your primary source is a single `@Configuration` class.
Usually the class that defines the `main` method is also a good candidate as the primary `@Configuration`.

**Importing additional configuration classes**

@Import
@ComponentScan

**Importing XML configuration**

@ImportResource

#### Auto-configuration

#### Spring Beans and dependency injection
