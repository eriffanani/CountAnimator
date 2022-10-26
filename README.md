## Number Counter Animator
Give effect for shifting the calculation of numbers from the starting value to the final value.

<img width="250px" src="https://user-images.githubusercontent.com/26743731/197971187-0cdee834-ea85-42af-878e-4c8c3df5612f.gif"/> <img width="250px" src="https://user-images.githubusercontent.com/26743731/197971176-b281f0bf-9bf8-4ceb-8e18-ccba2e1d3dbf.gif"/> <img width="250px" src="https://user-images.githubusercontent.com/26743731/197971197-42af6e4d-16d8-47fc-8b7c-1cffb52e9015.gif"/>

[![](https://jitpack.io/v/eriffanani/CountAnimator.svg)](https://jitpack.io/#eriffanani/CountAnimator)

## Installation
* **build.gradle** (Old way)
```gradle
maven { url 'https://jitpack.io' }
```
* **setting.gradle**: dependencyResolutionManagement (New way)
```gradle
maven { url 'https://jitpack.io' }
```

#### dependencies
```gradle
implementation 'com.github.eriffanani:CountAnimator:1.0.1'
```

### How To Use
#### Basic

* Java
```Java
CountAnimator anim = new CountAnimator(1000, 0);
anim.doOnUpdate((value, valueStr) -> {
  // TODO ACTION            
});
anim.start();
```

* Kotlin
```kotlin
val anim = CountAnimator(1000, 0)
anim.doOnUpdate { value, valueStr ->
  myTextView.text = valueStr
}
anim.start()
```

#### Action Support
```java
anim.start();
anim.pause();
anim.resume();
anim.stop();
```

#### Input String Support
```java
new CountAnimator("€10.000", "€100");
```

#### Duration
```java
anim.duration(3); // 3 Seconds
anim.duration(3000L); // 3 Seconds
```

#### End Listener
* Java
```java
anim.doOnEnd(() -> {
  // TODO ACTION            
});
```
* Kotlin
```kotlin
anim.doOnEnd {
  // TODO ACTION
}
```

#### Number Formatting
* Decimal
```kotlin
anim.format(CountFormatDecimal("#,###"))
```
* Currency
Use your country code and set fraction digits ex: 2
```java
anim.format(new CountFormatCurrency("EUR"));
anim.format(new CountFormatCurrency("EUR", 2));
```

#### Information
This library is still being developed further, please provide feedback if you find a bug. Thank you

### Licence
```license
Copyright 2022 Mukhammad Erif Fanani

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
