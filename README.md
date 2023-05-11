## Features
* 100% Kotlin
* MVVM architecture
* Android Architecture.
* Kotlin Coroutines + Flow
* Single activity pattern
* Dependency injection

<img src="./art/moviehunt-demo.gif" align="right" width="32%"/>

## Built With 🛠
- [Kotlin](https://kotlinlang.org/) - First class and official programming language for Android development.
- [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - For asynchronous and more..
- [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/-flow/) - A cold asynchronous data stream that sequentially emits values and completes normally or with an exception.
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - Collection of libraries that help you design robust, testable, and maintainable apps.
  - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - Data objects that notify views when the underlying database changes.
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores UI-related data that isn't destroyed on UI changes.
  - [Data Binding](https://developer.android.com/topic/libraries/data-binding) - Declarative way to bind data to UI layout.
  - [Navigation component](https://developer.android.com/guide/navigation) - Fragment routing handler. (Upcoming)

- [Dependency Injection](https://developer.android.com/training/dependency-injection)
  - [Hilt](https://dagger.dev/hilt) - Easier way to incorporate Dagger DI into Android apps.
- [Retrofit](https://square.github.io/retrofit/) - A type-safe HTTP client for Android and Java.
- [Material Components for Android](https://github.com/material-components/material-components-android) - Modular and customizable Material Design UI components for Android.
- [Glide](https://github.com/bumptech/glide) - Image loading.
- [Logging Interceptor](https://github.com/square/okhttp/blob/master/okhttp-logging-interceptor/README.md) -  logs HTTP request and response data.


## Architectures

MVVM

We follow Google recommended [Guide to app architecture](https://developer.android.com/jetpack/guide) to structure our architecture based on MVVM, reactive UI using LiveData / RxJava observables and data binding.

* **View**: Activity/Fragment with UI-specific logics only.
* **ViewModel**: It keeps the logic away from View layer, provides data streams for UI and handle user interactions.
* **Model**: Repository pattern, data layers that provide interface to manipulate data from both the local and remote data sources. The local data sources will serve as [single source of truth](https://en.wikipedia.org/wiki/Single_source_of_truth).

|         Home Screen           |           Favorite Screen            |                Details Screen             |
| :----------------------------------: | :---------------------------------------: | :---------------------------------------: |
|      <img src="https://github.com/Ozatac/Crypto-Apps/assets/36333407/09afde73-435f-4248-a287-e25aa5daac16" width="225" height="500"/>      |        <img src="https://github.com/Ozatac/Crypto-Apps/assets/36333407/74251e70-b5b5-436b-a78f-918c8415ffaf" width="225" height="500"/>         |        <img src="https://github.com/Ozatac/Crypto-Apps/assets/36333407/176216f4-177f-4021-a654-4c0c8d651284" width="225" height="500"/>       |

|         Splash Screen           |           Login Screen            |                Sign Up Screen             |
| :----------------------------------: | :---------------------------------------: | :---------------------------------------: |
|      <img src="https://github.com/Ozatac/Crypto-Apps/assets/36333407/f1206290-8148-4879-86a5-0be4790f0fe6" width="225" height="500"/>      |        <img src="https://github.com/Ozatac/Crypto-Apps/assets/36333407/8f63d765-b682-40a8-a384-969c28967d12" width="225" height="500"/>
         |        <img src="https://github.com/Ozatac/Crypto-Apps/assets/36333407/2dcfbd9e-e93e-4c6d-b74f-39f15b2c13a3" width="225" height="500"/>       |

## 👨🏻‍💻 Developed By

<a href="https://twitter.com/piashcse" target="_blank">
  <img src="https://avatars.githubusercontent.com/u/36333407?v=4" width="80" align="left">
</a>

**Tunahan Özataç**

[![Twitter](https://img.shields.io/badge/-twitter-grey?logo=twitter)](https://twitter.com/tunahan_ozatac)
[![Medium](https://img.shields.io/badge/-medium-grey?logo=medium)](https://medium.com/@tunahan.ozatac)
[![Linkedin](https://img.shields.io/badge/-linkedin-grey?logo=linkedin)](https://www.linkedin.com/in/tunahan-ozatac/)
