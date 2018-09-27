# Mockito Sample Project

### by Romell Domínguez
[![](snapshot/icono.png)](https://www.romellfudi.com/)

Sometimes test functional requirements its very complex, if you don't know how test your code, 'mock' interface components, you gonna go toxic way from implements test unit cases. Hope use This project for begging write excellent codes

**Won't explain technical details about the configuration of the project android**

All Testing are working using  Mockito 1.10.* dependency

[![center](snapshot/mockito.png)](https://github.com/mockito/mockito)

First we add dependecies at gradle config project file:

- Permission library (for request permissions at Android 5):
- SharePreferenceLib library (save internal memory)
- Glide library for structure data
- Junit, mockito, hamcrest & powermock for testing

![center](snapshot/b.png#center)

![center](snapshot/c.png#center)

## Lets Coooode! 

At CameraUnitTest testing class, declare variables: 
1.  A mock reference to interface **V**iew class Camera, whole be testing to make all possible workflows(coverage testing)
2.  An inject mock object (not reference), **P**resenter class camera to intialize our workflow paths
3.  An object parametrizable ArgumentCaptor class using String class for catch the response 
4.  A intialize method Mockito workspace run each workflow path

```java
    @Mock
    CameraContract.CameraView cameraView;

    @InjectMocks
    private CameraPresenter cameraPresenter = new CameraPresenter(cameraView);

    @Captor
    private ArgumentCaptor<String> captorString; // object or Callback

    @Before()
    public void preparate() {
        MockitoAnnotations.initMocks(this);
    }
```

### Build a JunitTestCase
In syntexis the requirement need testing a case whose capture a picture, the realized pic is captured, visualized (display) and eliminated (remove in memory). Assuring that should be really(exactly) the same photo captured from the beginning.

```java
    @Test
    public void takePic() {
        cameraPresenter.takePicture();
        verify(cameraView, times(1)).openCamera(captorString.capture());
        String path = captorString.getValue();
        show(path);

        cameraPresenter.viewPicture();
        verify(cameraView, times(1)).loadPicture(captorString.capture());
        show(captorString.getValue());
        assertThat(captorString.getValue(),is(equalTo(path)));

        cameraPresenter.pullImageFile().delete();
        cameraPresenter.viewPicture();
        verify(cameraView,times(1)).showDefaultPicture();

    }
```
Oblivious `captorString` allow catch the `takePicture` object
Same way we check that it is the same on having been loaded for `viewPicture`
But if remove that pic, just verificate whetere the presenter is displaying a default picture by using  `showDefaultPicture` method

A second example, need testing save/load pic from  our SharePreference class, creating `saveload` unit test:

```java
    @Test
    public void saveLoad() {
        String toSave = "save";
        sharePresent.saveInput(toSave);
        verify(shareView).reLoadList();
        when(repository.load()).thenReturn(toSave);

        sharePresent.loadInput();
        verify(shareView).loadText(stringArgumentCaptor.capture());
        assertThat(toSave,is(equalTo(stringArgumentCaptor.getValue())));
    }
```

First, we validate that our object value stores and if could recover, and second we validate that in our survey, this one obtaining the values storing in `repository` memory, notice that not exist a direct reference in our code of unit test

When all test cases had been coded, Go run:

![center](snapshot/a.png#center)

![center](snapshot/e.png#center) 

As seen all testing had been run, passed successful

**NOTICE never we pass a reference of `cameraView` to the `cameraPresenter` presenter or the  `repository` sharePreference at `shareView`, this one is the great advantage of mockito: IT ALLOWS TO CAPTURE INTERFACES WITH ONLY USING THE EXACT NAME OF THE INTERFACE VARIABLE USED IT**

<style>
img[src*='#center'] { 
    width:500px;
    display: block;
    margin: auto;
}
</style>