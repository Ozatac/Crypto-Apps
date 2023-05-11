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

|         Home Screen           |           Details Screen            |                Details Screen             |
| :----------------------------------: | :---------------------------------------: | :---------------------------------------: |
|      ![resim](https://user-images.githubusercontent.com/36333407/226935107-12378616-9289-484b-b317-2dd69bdfd832.png)      |        ![Screenshot_20230322_164433](https://user-images.githubusercontent.com/36333407/226935206-07c7647b-89c7-4a46-a62c-b7efd4745c24.png)         |        ![Screenshot_20230322_164411](https://user-images.githubusercontent.com/36333407/226935247-f98d5e22-2c81-409a-92b0-4f33fa5cd36c.png)       |

## 👨🏻‍💻 Developed By

<a href="https://twitter.com/piashcse" target="_blank">
  <img src="https://avatars.githubusercontent.com/u/36333407?v=4" width="80" align="left">
</a>

**Tunahan Özataç**

[![Twitter](https://img.shields.io/badge/-twitter-grey?logo=twitter)](https://twitter.com/tunahan_ozatac)
[![Medium](https://img.shields.io/badge/-medium-grey?logo=medium)](https://medium.com/@tunahan.ozatac)
[![Linkedin](https://img.shields.io/badge/-linkedin-grey?logo=linkedin)](https://www.linkedin.com/in/tunahan-ozatac/)
