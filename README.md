# Tower Quest: A game by Julia Clade and Katherine Irving Seguel
Welcome to Tower Quest.
In the following README you will find information on how to play the game, 
how to open the file and run it and acknowledgements for images and sound used.

## How to play:
Game setup is easy. Chose an alphanumeric name between 3 and 15 characters,
game difficulty, round numbers and 3 starting towers from the provided options.

Before starting a round you can go shopping for upgrades and more 
towers. There is a limit of 10 towers in total and no limit for upgrades.
You can equip  these new towers in the inventory, by swapping between 
reserve tower and main tower lists. Upgrades can also be applied, but beware,
they have to match the resource type of the tower.
Make sure you always have at least one tower in your main tower list.

Now you are ready to play!
Chose a round option and press start. A round consists of a track and a 
number of carts of a certain resource type. Your selected main towers are placed
along this track and load their resources. The carts will travel to these 
towers and if resources match, they will get loaded with the amount of the 
resources the tower has accumulated, but no more than the resource amount 
that the tower specifies.

If all carts are full at the end of a round, the round is won! You will earn 
money based on the carts filled and resource types. gold is worth the most, 
followed by coal and then wood.

Between rounds, you get the opportunity to go shopping, sell towers and 
apply upgrades to towers to improve your chances of winning.

Beware! You might be surprised by a random event happening! These can be 
beneficial, or really wreck your day. But I won't spoil this surprise any 
more. You will just have to find out yourself.

Good luck!!

## Authors
Julia Clade and Katherine Irving Seguel

## Prerequisites
- JDK >= 17 [click here to get the latest stable OpenJDK release (as of writing this README)](https://jdk.java.net/18/)
- *(optional)* Gradle [Download](https://gradle.org/releases/) and [Install](https://gradle.org/install/)

## Build and Run Jar
1. Open a command line interface inside the project directory and run 
  `./gradlew jar` to create a packaged Jar. The Jar file is located at 
   build/libs/jcl186_kir21-tower_quest.jar
2. Navigate to the build/libs/ directory (you can do this with `cd build/libs`)
3. Run the command `java -jar jcl186_kir21-tower_quest.jar` to open the 
   application.

## How to import from jar into IntelliJ
1. Open IntelliJ IDEA 
2. Click on "File" in the top menu, choose "New" and then select "Project" 
   from the dropdown menu.
3. In the New Project window, select the SDK (Java Development Kit) you want to use.
4. Select the project type (e.g., Java, Kotlin, etc.) you want to create.
5. Enter a name and project location for your project.
6. Once your project is open in IntelliJ, right-click on the "libs" folder in 
   the project view (or create one if it doesn't exist) where you want to add 
   the JAR file. Select "Add as Library" from the context menu.
7. In the "Add Library" dialog, choose "Java" from the options on the left.
8. Click on "From Maven..." or "From filesystem..." depending on where your JAR
   file is located.
   If from Maven: Search for the library you want and select it.
   If from filesystem: Navigate to the location of the JAR file on your computer
   and select it.
9. Click "OK" to add the JAR file as a library to your IntelliJ project.

## How to import from eng-git into IntelliJ
1. Copy Repository URL: Go to your eng-git repository and copy the HTTPS 
2. in IntelliJ: Click 'File'--> 'New' --> 'Project from version control'
3. Paste Repository URL.
4. Choose directory where to save the local clone.
5. Authentication (if needed): Enter your GitLab credentials if prompted.
6. Clone and Open Project: Click Clone to clone the repository and open it in 
   IntelliJ IDEA.


## Attributions for images and sound

### Sound
1. violin-lose.wav: https://pixabay.com/sound-effects/violin-lose-5-185126/
2. success1.wav: https://pixabay.com/sound-effects/success-1-6297/
3. success-fanfare.wav: https://pixabay.com/sound-effects/success-fanfare-trumpets-6185/
4. money.wav:https://www.soundsnap.com/cash_register_antique_open_close_02_wav
5. potion.wav:https://pixabay.com/sound-effects/search/cartoon_wink_magic_sparkle/
6. swap.wav:https://pixabay.com/sound-effects/search/select-sound-thing/
7. random.wav:https://pixabay.com/sound-effects/search/game-bonus-/

### Code
1. code written by ChatGPT for sound as per this chat:
   https://chat.openai.com/share/21fb3acc-cad3-4c56-a047-559938bd96e7

### Images
1. background.png: https://www.vectorstock.com/royalty-free-vector/top-view-blank-wooden-table-with-leaves-vector-34973442
   Author: Designed by brgfx / Freepik
   License: Personal and commercial use
2. rocks.png: https://www.vecteezy.com/vector-art/5449701-set-of-seamless-texture-stone-step-by-step-drawing-seamless-pattern-of-improvement-and-progress
   Author: Designed by babysofja / Freepik
   License: Personal and commercial use
3. dark_rock.png: https://www.vecteezy.com/vector-art/5449701-set-of-seamless-texture-stone-step-by-step-drawing-seamless-pattern-of-improvement-and-progress
   Author: Personal by babysofja / Freepik
   License: Standard and commercial use
4. coalTower.png: Created by Adobe Illustration AI
   License: Personal use
5. coalUpgrade.png: Created by Adobe Illustration AI
      License: Personal use
6. diamon.png: Created by Adobe Illustration AI
   License: Personal use
7. goldUpgrade.png: Created by Adobe Illustration AI
   License: Personal use
8. goldtower.png: Created by Adobe Illustration AI
   License: Personal use
9. winscreen.png: Created by Adobe Illustration AI
   License: Personal use
10. woodUpgrade.png: Created by Adobe Illustration AI
    License: Personal use 
11. woodtower.png: Created by Adobe Illustration AI
        License: Personal use

