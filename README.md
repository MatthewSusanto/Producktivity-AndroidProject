# ``PRODUCKTIVITY ANDROID APP``
**This is a productivity application for Android devices I built using Java and XML.**  <br />
  <br />
**The purpose of this application is to increase the productivity of the user by using the Pomodoro inspired technique. It eliminates phone distractions and notification while the application is active.**  <br />
  <br />
**The application keeps track of how much time you spent being productive, sets up the environment to do productive activities, and rewards your productivity.**
___
**I will be showing:**

+ **How to install and run the application**
+ **Preview of the application**
+ **What did I learn?**
+ **Technologies used**
+ **Difficulties I encountered, along with the solutions**

___
## ``How to install and run the application?``

You need to make sure that you have your package manager installed

Please make sure that you are in the right directory

1. **`cd essentials`** cd to the essentials directory
2. **`npm install`** once you are in essentials directory, install the dependencies
3. **`npm start`** start the application by using local a server (this is in development mode)

This might take a while to load up
___
## ``Preview of the application``
1. You need to allow Producktivity permission to access your phone DND to use the feature the first time you run the application.
2. Once the start button is pressed, it will activate a 30 minutes timer. Once finished, it will reward you a duckling. You need 5 ducklings to get a duck as a reward.
3. You will then take a 5 minutes break after you earn a duckling, you can also activate "Focus Mode" to skip these breaks.
4. You can pause the running timer and it will give you an option to restart the timer back to 00:00.
5. **Keep in mind that in the preview below, the timer will be 5 seconds instead of 30 minutes. The preview GIF speed will also be faster than the actual application.  <br />
___
- **The application will ask you to allow permission to access your phone DND settings the first time you run the application**  <br />
  <br />
![](https://github.com/MatthewSusanto/resource/blob/master/producktivityGIF/AllowPermission.gif?)  <br />
___

- **Activating Focus Mode will allow you to run the timer continuously without breaks in between**  <br />
  <br />
![](https://github.com/MatthewSusanto/resource/blob/master/producktivityGIF/TimerShowcaseFocus.gif?)
___
- **Deactivating Focus Mode will pause the timer once you earn a duckling, allowing you to take breaks in between. Pausing the timer will let you reset the timer back to 00:00**  <br /> 
<br />
![](https://github.com/MatthewSusanto/resource/blob/master/producktivityGIF/TimerShowcasePause.gif?)
___
## ``What did I learn?``

- Exposes me to the world of mobile development
- Fundamental concepts of mobile development (activities, services, etc)
- Create, load, and update the state of the application locally
- Create and connect the UI components with Java
- Working with System Permissions (accessing device data)

___
## ``Technologies Used``

- Java
- Android Studio
- XML

___
## ``Difficulties I encountered, along with the solutions``

- **Difficulty:** The Java language used for mobile application programming is different from what I learn at University  <br />
**Solution:** Managed to take online courses provided by Google, Udemy courses, and YouTube tutorials to be comfortable with it

- **Difficulty:** Wasn't able to keep track of the amount of duck/time spent after the application closes  <br />
**Solution:** Solved it by using SharedPreferences, creating a local file, accessing, and updating the values

- **Difficulty:** Sound and notifications keep showing up and I wasn't able to figure out the way to solve this problem  <br />
 **Solution:** Opening a setting for the user automatically and directing the user to allow access for my application to change the DND state of the phone

