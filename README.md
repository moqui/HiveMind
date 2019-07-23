## HiveMind Project Management and Service ERP

[![license](http://img.shields.io/badge/license-CC0%201.0%20Universal-blue.svg)](https://github.com/moqui/HiveMind/blob/master/LICENSE.md)
[![build](https://travis-ci.org/moqui/HiveMind.svg)](https://travis-ci.org/moqui/HiveMind)
[![release](http://img.shields.io/github/release/moqui/HiveMind.svg)](https://github.com/moqui/HiveMind/releases)
[![commits since release](http://img.shields.io/github/commits-since/moqui/HiveMind/v1.4.1.svg)](https://github.com/moqui/HiveMind/commits/master)
[![downloads](http://img.shields.io/github/downloads/moqui/HiveMind/total.svg)](https://github.com/moqui/HiveMind/releases)

[![LinkedIn Group](https://img.shields.io/badge/linked%20in%20group-moqui-blue.svg)](https://www.linkedin.com/groups/4640689)
[![Google Group](https://img.shields.io/badge/google%20group-moqui--applications-blue.svg)](https://groups.google.com/d/forum/moqui-applications)
[![Stack Overflow](https://img.shields.io/badge/stack%20overflow-moqui-blue.svg)](http://stackoverflow.com/questions/tagged/moqui)

HiveMind is a comprehensive ERP application for service organizations. It includes a project management application 
featuring project/task, request, and content (wiki) management. There is support for multiple vendors, multiple clients, 
flexible billing rates, time recording, expenses, invoicing (AR/AP), payments, and general ledger.

### Running HiveMind

To run HiveMind you need Moqui Framework, HiveMind itself, and the components it depends on. Moqui supports a few 
methods for setup and deployment as described in the documentation here:

<http://www.moqui.org/docs/framework/Run+and+Deploy>

The easiest way to try HiveMind is with the binary distribution available on GitHub:

<https://github.com/moqui/HiveMind/releases>

If you don't have gradle or ant installed you can use this command line to run Java directly:

    $ java -jar moqui.war

### Build and Run Locally

To get and locally run the latest HiveMind you'll need JDK 8 or later (OpenJDK or Oracle), and either a git client or you can 
use the binary download link on GitHub.

Java can be downloaded here (make sure to use the Download button under the **JDK** column, NOT the under the JRE column):

<http://www.oracle.com/technetwork/java/javase/downloads/index.html>

The following instructions use the Gradle Wrapper to build. You can optionally download and install Gradle 
(from <http://www.gradle.org/downloads>) and use **gradle** instead of **./gradlew** in the example commands.

To download Moqui/Mantle/HiveMind source and build/run locally use the following steps:

#### Step 1: Download Moqui Framework

Zip: <https://github.com/moqui/moqui-framework/archive/master.zip>

Git: <https://github.com/moqui/moqui-framework.git>

From either source you should put the contents in a **moqui** directory for the next steps. If you use the Zip download 
change the directory name from **moqui-framework-master** to **moqui**. If you clone the Git repository clone it into 
a **moqui** directory. 

#### Step 2: Download HiveMind and Dependencies

This is easy with the dependency configuration per component, and the Gradle get component tasks. With Gradle Wrapper 
you don't need to install Gradle separately to do this. The HiveMind component is configured by default in the Moqui 
addons.xml file, so just run:

    $ ./gradlew getComponent -Pcomponent=HiveMind

If you downloaded the zip archive for Moqui Framework this will download the zip archives for HiveMind and each 
component it depends on. If you cloned from the git repository this will clone all components from their repositories. 

#### Step 3: Build and Load Data

From the **moqui** directory run:
 
    $ ./gradlew load

This will build Moqui and load seed and demo data from all components into an embedded H2 database.

#### Step 4: Run Moqui

From the **moqui** directory run:
 
    $ java -jar moqui.war

#### Step 5: Access the HiveMind applications

For the HiveMind Project Management application, in your browser go to:

<http://localhost:8080/vapps/hm>

Or for the admin application go to:

<http://localhost:8080/vapps/hmadmin>

Use the button in the lower-left corner of the screen login as John Doe.

### Setup Commands Quick Reference

Java 8 JDK is required (OpenJDK or Oracle): <http://www.oracle.com/technetwork/java/javase/downloads/index.html>

Here are command line steps for initial checkout, setup, and run:

    $ git clone git@github.com:moqui/moqui-framework.git moqui
    $ cd moqui
    $ ./gradlew getComponent -Pcomponent=HiveMind
    $ ./gradlew load
    $ java -jar moqui.war

Here are steps for a basic update (for development with clean out and rebuild of database):

    $ cd moqui
    $ ./gradlew cleanAll gitPullAll
    $ ./gradlew load
    $ java -jar moqui.war

To access the project management app go to something like <http://localhost:8080/vapps/hm> in a
web browser. To access the admin app go to <http://localhost:8080/vapps/hmadmin>.
