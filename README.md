Geometry library
================

This library provides simple geometric data structures
and associated operations. All the data container classes
are immutable.

This library depends on [my matrix package](https://github.com/kazocsaba/matrix).

Using
-----

The library resides in the central Maven repository with group ID `hu.kazocsaba.math` and
artifact ID `geometry`. If you use a project management system which can fetch dependencies
from there, you can just add the library as a dependency. E.g. in Maven:

	<dependency>
		<groupId>hu.kazocsaba.math</groupId>
		<artifactId>geometry</artifactId>
		<version>a.b.c</version>
	</dependency>

You can also browse the [online javadoc](http://kazocsaba.github.com/geometry/apidocs/index.html).

Features
--------

**Line** in 2D and 3D space, represented as `X(t)=P+t*D`

- distance of point from line, distance between two lines
- rotation around 3D line
- miscellaneous convenience query functions

**Plane** in 3D space

- distance of point from plane
- intersection with line and plane

**Segment** in 2D and 3D

- perpendicular bisector line in 2D

**Box** in 2D

- intersection with line (returns a segment)

**Circle** in 3D

Additional features are added as needed.
