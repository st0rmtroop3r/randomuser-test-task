# Randomuser Test Task

### Task
Create a small mobile application on Android platform. The application should contain two screens - a list of users and information about user.

Use https://randomuser.me/ service API as data provider.

The design of the application should be quite simple and meet “material design” and Google’s recommendations: https://material.io/guidelines/

### The application has to reflect following:
* Your skills
* Android framework understanding and as well as libraries that was used
* Ability to work with network
* Ability to build User Interface

### Done
Android application based on MVP pattern
#### Used:
* Dagger 2 for dependency injection
* RxJava, RxAndroid, Retrofit, OkHttp and Gson for handling requests to https://randomuser.me/ REST API
* ButterKnife for binding Views
* Picasso for image loading
* RecyclerView support library for handling list of users
* XML animation - elevation and ripples
* Fade and ChangeBounds transitions for user details appearance/disappearance animation

### TODO
* Add two panel mode for tablets
* Add circular reveal animation for user details pane appearance in two panel mode
* Add map to user details
* Some UI improvements...
