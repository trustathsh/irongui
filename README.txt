irongui
=======

This package contains irongui, a program to visualize the content
of a MAP Server (MAPS), a crucial component within the TNC
architecture [1].

irongui generally acts as a MAP client. However, there are two
ways to actually retrieve the data from the MAPS

1. By using the dump request, a non standard IF-MAP operation
that is currently only supported by irond. The dump is used to
bootstrap the visualization process. Further request are standard
IF-MAP subscriptions and polls. The dump request is issued when
you click on the start button.

2. By using only standard IF-MAP operations. This has the benefit
that irongui can visualize any IF-MAP 2.0 compliant MAPS. In
order to use this feature, and due to the absence of a dump
operation, you must tell irongui which part of the MAPS you want
to be visualized. You can do this by clicking on the subscription
button and fill in the parameters accordingly. Note that this
feature is pretty new and might still contain several bugs.

The development was started within the bachelor
thesis of Tobias Ruhe at Hochschule Hannover (University of
Applied Sciences and Arts Hannover). irongui is now maintained
and extended within the ESUKOM research project. More information
can be found at the Trust@FHH website [2].


Software Requirements
=====================
	- Java SE 1.5 or higher


Installation
============
The program is distributed in two variants: a binary and a source
distribution. The first one contains binaries only, the other one
includes sources and build-scripts. Both are shipped as
.zip-archive that contain the following:

irongui-x.x.x-bin
+-- .irongui		 # configuration file (generated)
+-- irongui.jar		 # executable jar-archive
+-- keystore/		 # sample keys for authentication
+-- img/		 # images/icons used by irongui
+-- log4j.properties 	 # config for debug output
+-- start.bat		 # start-script for windows
+-- start.sh		 # start-script for linux
+-- README.txt		 # this file
+-- LICENSE.txt		# Apache 2.0 license
+-- CHANGELOG.txt	# Changelog of irongui
+-- NOTICE.txt		# empty

irongui-x.x.x-src/
+-- pom.xml		 # maven-buildfile
+-- src/		 # the sources for irongui
+-- README.txt		# this file
+-- LICENSE.txt		# Apache 2.0 license
+-- CHANGELOG.txt	# Changelog of irongui
+-- NOTICE.txt		# empty

You can build the binary distribution by using maven package within
the source distribution.

Configuration
=============
The configuration is maintained within the .irongui file. This file
is expected to reside either in irongui's current working directory
or in the users home directory. irongui comes with a .irongui file that
has two default connections configured.

You can also edit the content of the 'log4j.properties' file in order
to adjust logging parameters.


Running
========
Depending on your system, just execute the start.sh or start.bat
script to run the program. Alternatively you can execute
following command:

	java -jar irongui.jar

Feedback
========
If you have any questions, problems or comments, please contact
	trust@f4-i.fh-hannover.de


LICENSE
=======
irongui is licensed under the Apache License, Version 2.0 [3].


URLs
====
[1] http://www.trustedcomputinggroup.org/developers/trusted_network_connect

[2] http://trust.inform.fh-hannover.de

[3] http://www.apache.org/licenses/LICENSE-2.0.html

