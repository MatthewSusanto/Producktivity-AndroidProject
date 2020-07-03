# ``PRODUCKTIVITY ANDROID APP``
**This is a productivity application for Android devices I built using Java and XML.**  <br />
**The purpose of this application is to increase the productivity of the user by using the Pomodoro inspired technique. It eliminates phone distractions and notification while the application is active.**  <br />
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
- **Homepage, sign in, sign up, and shopping bag**  <br />
![](https://github.com/MatthewSusanto/resource/blob/master/videoGif/HeroSignin.gif?)  <br />
___
- **Showcase of the on-sale and most popular items, about, newsletter, FAQ links, and footer**  <br />
![](https://github.com/MatthewSusanto/resource/blob/master/videoGif/HomeFooter.gif?)
___
- **Product search feature, collection list, and promo code pop-ups**  <br />
![](https://github.com/MatthewSusanto/resource/blob/master/videoGif/SearchCollection.gif?)
___
- **Product details and product recommendations**  <br />
![](https://github.com/MatthewSusanto/resource/blob/master/videoGif/ProductDetail.gif?)
___
- **Select colour/size/quantity of the product, add to the shopping bag, and removing/adding quantity from the shopping bag**  <br />
![](https://github.com/MatthewSusanto/resource/blob/master/videoGif/CartModal.gif?)
___
- **Shopping bag summary, adding/removing items from the shopping bag, applying promo code, and order history**  <br />
![](https://github.com/MatthewSusanto/resource/blob/master/videoGif/CartPromoCode.gif?)
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

