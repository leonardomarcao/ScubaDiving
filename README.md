## Scuba Diving Game

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/d113af0da84b4ab9b17b8ffc29c58ecf?branch=admin-javafx)](https://app.codacy.com/project/leonardomarcao/ScubaDiving/dashboard)

<p align="center"><a target="_blank" href="https://www.youtube.com/watch?v=x9mtLNFgO_s"><img src="https://i.imgur.com/AOlRFtY.png" alt="Scuba Diving - Game project on sustainable development"></a></p>

### About

Game project developed at the Paulista University on sustainable development. The Scuba Diving Game has a didactic purpose of helping other people understand a little about sustainability, with the main scenario being a scuba diver who collects garbage at the bottom of the ocean.

### How does it work?

Scuba Diving Game was developed through a 2D Java Game Library, called Slick2D. Slick2d is an easy to use set of tools and utilites wrapped around LWJGL and OpenGL bindings to make 2D Java game development easier.

### Instalattion

Following the steps of Slick2D [wiki](http://slick.ninjacave.com/wiki/index.php?title=Main_Page)
#### Downloading and Extracting Slick2D and LWJGL
1. Download [Slick2D](http://slick.ninjacave.com/)
2. Download LWJGL **[2.9.3](https://sourceforge.net/projects/java-game-lib/files/Official%20Releases/LWJGL%202.9.3/)**
3. Extract the LWJGL zip (lwjgl-x.x.zip) file somewhere in your computer, remember or note down the location, you will need this later. We suggest you create a library (/lib) folder to store all these files in a well-known place.

#### Setting Up Slick2D and LWJGL in NetBeans
Extracted from LWJGL with Netbeans
1. Open up NetBeans.
2. Go to Tools --> Libraries in the menu bar.
3. Click on the New Library button.
4. Type in Slick2D or any other name that you want for the Library Name.
5. Select Classpath tab for your newly created Library and then click the Add JAR/Folder... button.
6. Go to where you extracted lwjgl-X.X.zip and add the following '.jar'('Ctrl' or 'Alt' to select multiple files)
   1. lwjgl.jar
   2. slick.jar
   3. jinput.jar
   4. lwjgl_util.jar (if want to use OpenGL's GLU class)

#### Setting Up a Project to use LWJGL in NetBeans
In a new Java project:
1. Right-Click your project node or go to File>Project Properties and select Libraries
2. Add your Slick2D Library , created as instructed above
3. Select Run
4. On VM Options put the following:
   `-Djava.library.path=<lwjgl-X.X path>/native/<linux|macosx|solaris|windows>`
   
Note: Remember to select the natives of your operating system.

After all the steps above, the Scuba Diving Game project can now be compiled.

### License 

MIT License

Copyright (c) 2018 Leonardo Marcão

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
