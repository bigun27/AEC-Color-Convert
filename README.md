# AEC-Color-Convert
This is the source release of the AEC Color Convert program, version 1.2.2.

## What is this?
I created the base of this project as a one-off program for a company to convert their Color database into a CSV file.
The database in question was an ancient DBF that did not abide by the standard, thus a custom converter was necessary.
However, after the program was delivered, I continued to work and expand the functionality.
While this may not be super useful for anyone else, as it was built as a use-case utility, the source is here under the MIT License if you need it.

## How to use
The source here can be compiled using Java just like any other Java program.
The .exe executable provided in the releases are .jar files that have been wrapped by [launch4j](http://launch4j.sourceforge.net/) for ease of use on Windows. The executable has not been signed, so antivirus false positives are possible.
The .jar defaults to CLI mode, but running with the ```--gui``` argument will start the GUI. The .exe opens the GUI by default.

As this is a Java program, Java is required. When using the .exe executable, it prefers to use a local copy of OpenJDK, but will fall back to the system-installed version if it is newer. More details can be found in the 3rd-party-requirements document.

## Can I contribute?
Of course you can, that's the magic of open source! If you have a problem, open an Issue. If you have code, open a Pull Request.
