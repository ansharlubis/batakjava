# Minimal ExtendJ Extension Template

This is a minimal template for ExtendJ extensions.

If you want to build static analysis, use the
[analysis template](https://bitbucket.org/extendj/analysis-template/).
If you want to build a compiler with bytecode generation, use the
[compiler template](https://bitbucket.org/extendj/compiler-template/).


## License

This code is simple template code that is meant to be copied,
and I give anyone permission to use it without attribution.  If you copy this
code to create your own project, you can delete the LICENSE file.  The license
is there just to make it possible to use this project if your employer is
strict about Open Source licensing.


## Cloning this Project

To clone this project you will need [Git][3] installed.

Use this command to clone the project with Git:

    git clone --recursive <REPOSITORY URL>

The `--recursive` flag makes Git also clone the ExtendJ submodule while cloning
the template repository.

To manually clone or update ExtendJ, use these commands:

    cd backend-extension-base
    git submodule init
    git submodule update

This downloads the ExtendJ Git repository into a subdirectory named `extendj`.


## Build and Run

This project is built with [Gradle][1], but you do not need to install Gradle to use it.
If you have Java installed, run the following commands to build the project:

    ./gradlew jar


If you are running on Windows, replace `./gradlew` by just `gradlew`.

Run the generated compiler with

    java -jar template.jar <Java Source File>


The jarfile name is based on the project name in `settings.gradle`.


## File Overview

Here is a short description of some notable files in this project:

* `build.gradle` - the main Gradle build script. More about this below.
* `gradlew.bat` - script for building on Windows.
* `gradlew` - script for building on Unix-likes.
* `testfiles/Test.java` - a simple Java file to test the generated compiler.
* `settings.gradle` - sets the Gradle project name to `template`. Edit this.
* `<PROJECT NAME>.jar` - the generated compiler Jar file.


## Extension Architecture

We use the [JastAdd Gradle plugin][2] to build this project. This plugin has its own
DSL for JastAdd modules which significantly simplifies the process for
combining extensions with the core ExtendJ compiler.

The template build script has a small module specification which starts with this line:

    include("extendj/jastadd_modules")


This line includes the core ExtendJ modules by loading the file with the path
`extendj/jastadd_modules`. That file is a module specification which in turn
includes modules from subdirectories in the `extendj` directory.

Module specifications can define multiple modules. In the build script,
there is just one module named `template`:

    module "template", {
        imports "java8 frontend"
        java {
            basedir "src/java/"
            include "**/*.java"
        }
        jastadd {
            basedir "src/jastadd/"
            include "**/*.ast"
            include "**/*.jadd"
            include "**/*.jrag"
        }
    }


The build script has comments to show how to add parser or scanner files to the module.
Parser and scanner files will be necessary if you want to make Java language extensions.

The module uses an `imports` clause to import all of the JastAdd files from
the core ExtendJ module `java8 frontend`. Each supported Java version in
ExtendJ has a frontend and backend module. The frontend module is used if you do not
want to generate bytecode.


## Rebuilding

Although the Gradle plugin can handle some automatic rebuilding when a source
file changes, it does not handle all possible cases. In some situations you
will need to force Gradle to rebuild your project. This can be done with the following command:

    ./gradlew clean jar


It is necessary to rebuild the project if you remove any JastAdd AST class.
The Gradle plugin will otherwise leave the old AST classes among the
generated Java code and this can cause compilation problems.


## Upgrading ExtendJ

Use the following commands to update to the latest version of ExtendJ:

    cd extendj
    git fetch origin
    git reset --hard origin/master


This may be necessary if a bugfix that you need was committed to ExtendJ in a version
later than the version that this template repository links to.

I recommend that you use a test suite to ensure that your extension
functionality is preserved after upgrading the core ExtendJ compiler.


## Additional Resources

More examples on how to build ExtendJ-like projects with the [JastAdd Gradle
plugin][2] can be found here:

* [JastAdd Example: GradleBuild](http://jastadd.org/web/examples.php?example=GradleBuild)

[1]:https://gradle.org/
[2]:https://github.com/jastadd/jastaddgradle
[3]:https://git-scm.com/
