# Mockito Sample into Android Project
[![](https://img.shields.io/badge/language-ES-blue.svg)](./README.es)
[![CircleCi](https://img.shields.io/circleci/project/github/romellfudi/MockitoSample.svg)](https://circleci.com/gh/romellfudi/MockitoSample/tree/master)
[![](https://github.com/romellfudi/MockitoSample/workflows/Android%20CI/badge.svg)](https://github.com/romellfudi/MockitoSample/actions)

### by Romell Domínguez
[![](https://raw.githubusercontent.com/romellfudi/assets/master/favicon.ico)](https://www.romellfudi.com/)

Sometimes test functional requirements its very complex, if you don't know how test your code, 'mock' interface components, you gonna go toxic way from implements test unit cases. Hope use This project for begging write excellent codes

**Won't explain technical details about the configuration of the project android**

All Testing are working using  Mockito 1.10.* dependency

[![center](snapshot/mockito.png)](https://github.com/mockito/mockito)

First we add dependecies at gradle config project file:

- Permission library (for request permissions at Android 5):
- SharePreferenceLib library (save internal memory)
- Glide library for structure data
- Junit, mockito, hamcrest & powermock for testing

```groovy
glideVersion = '3.6.1'
junitVersion = '4.12'
mockitoVersion = '1.10.19'
powerMockito = '1.6.2'
hamcrestVersion = '1.3'
```

## Lets Coooode! 

At CameraUnitTest testing class, declare variables: 
1.  A mock reference to interface **V**iew class Camera, whole be testing to make all possible workflows(coverage testing)
2.  An inject mock object (not reference), **P**resenter class camera to intialize our workflow paths
3.  An object parametrizable ArgumentCaptor class using String class for catch the response 
4.  A intialize method Mockito workspace run each workflow path

```kotlin
@RelaxedMockK
lateinit var cameraView: CameraView

lateinit var cameraPresenter: CameraPresenter

val captorString = slot<String>() // object or Callback

@Before
fun preparate() {
    MockKAnnotations.init(this, relaxUnitFun = true)
    cameraPresenter = CameraPresenter(cameraView)
}
```

### Build a JunitTestCase
In syntexis the requirement need testing a case whose capture a picture, the realized pic is captured, visualized (display) and eliminated (remove in memory). Assuring that should be really(exactly) the same photo captured from the beginning.

```kotlin
@Test
fun takePic() {
    cameraPresenter.takePicture()
    verify { cameraView.openCamera(capture(captorString)) }
    val path = captorString.captured
    show(path)
    cameraPresenter.viewPicture()
    verify(exactly = 1) { cameraView.loadPicture(capture(captorString)) }
    show(captorString.captured)
    assertThat(captorString.captured, equalTo(path))
    cameraPresenter.pullImageFile()!!.delete()
    cameraPresenter.viewPicture()
    verify(exactly = 1) { cameraView.showDefaultPicture() }
}
```
Oblivious `captorString` allow catch the `takePicture` object
Same way we check that it is the same on having been loaded for `viewPicture`
But if remove that pic, just verificate whetere the presenter is displaying a default picture by using  `showDefaultPicture` method

A second example, need testing save/load pic from  our SharePreference class, creating `saveload` unit test:

```kotlin
@Test
fun saveLoad() {
    val toSave = "save"
    sharePresent.visibleForTesting(repository)
    sharePresent.saveInput(toSave)
    verify { shareView.clearText() }
    verify { shareView.reLoadList() }
    `when`(repository.load()).thenReturn(toSave)
    sharePresent.loadInput()
    verify { shareView.loadText(capture(captorString)) }
    assertThat(toSave, equalTo(captorString.captured))
}
```

First, we validate that our object value stores and if could recover, and second we validate that in our survey, this one obtaining the values storing in `repository` memory, notice that not exist a direct reference in our code of unit test

When all test cases had been coded, Go run:

![center](snapshot/a.png#center)

![center](snapshot/e.png#center) 

As seen all testing had been run, passed successful

**NOTICE never we pass a reference of `cameraView` to the `cameraPresenter` presenter or the  `repository` sharePreference at `shareView`, this one is the great advantage of mockito: IT ALLOWS TO CAPTURE INTERFACES WITH ONLY USING THE EXACT NAME OF THE INTERFACE VARIABLE USED IT**

### License
```
Copyright 2018 Romell D.Z.

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

<style>
img[src*='#center'] { 
    width:500px;
    display: block;
    margin: auto;
}
</style>