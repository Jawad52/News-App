# News-App

Introduction
------------

A News app is a sample app consist of three screens: Splash, news list and details news. App is illustrating current Android Architecture.

The application uses combined MVVM and Clean Architecture and Repository patterns. MVVM is a template of a client application architectureImplemented and it is separate data presentation logic from business logic by moving it into particular class for a clear distinction. Implemented Architecture principles follow Google recommended [Impilimentation guidance to app architecture] (https://developer.android.com/jetpack/docs/guide).

![Guide to app architecture](screenshots/guide-to-app-architecture.png "Guide to app architecture")

The application is written entirely in Kotlin.

Android Jetpack is used as an Architecture glue including but not limited to ViewModel, LiveData,
Lifecycles, Navigation, and Room.

The application does network HTTP requests via Retrofit, OkHttp and GSON. Loaded data is saved to
SQL based database Room, which serves as single source of truth and support offline mode.

Coroutines 
----------
Kotlin Coroutine is a way of doing things asynchronously in a sequential manner. Creating a coroutine is a lot cheaper vs creating a thread. Kotlin Coroutines manage background threads with simplified code and reducing needs for callbacks.
Combination of Coroutines and Kotlin build in functions (transformation, collections)

Dagger 2 is used for architecture level dependency injection.

Glide is used for image loading.

Advantages of Using Clean Architecture
--------------------------------------

* Code is even more easily testable than with plain MVVM.
* Code is further decoupled.
* The project is even easier to maintain.
* Team member can add new features even more quickly.

Disadvantages of Clean Architecture
-----------------------------------
* It has a slightly steep learning curve. How all the layers work together may take some time to understand, especially if you are coming from patterns like simple MVVM or MVP.
* It adds a lot of extra classes, so it’s not ideal for low-complexity projects.

Test Library
------------
*  For testing framework used Espresso (UI test)
*  JUnit4 used to test classes

Screenshots
-----------
![NewsAppSplashScreen](screenshots/Screenshot_0.jpg "Splash Screen")
![NewsList](screenshots/Screenshot_1.jpg "News List screen")
![NewsDetails](screenshots/Screenshot_2.jpg "News Details screen")

