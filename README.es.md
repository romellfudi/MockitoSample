# Mockito Sample Project
[![](https://img.shields.io/badge/language-EN-blue.svg)](./)
[![CircleCi](https://img.shields.io/circleci/project/github/romellfudi/MockitoSample.svg)](https://circleci.com/gh/romellfudi/MockitoSample/tree/master)

### by Romell Domínguez
[![](snapshot/icono.png)](https://www.romellfudi.com/)

Probar requerimientos funcionales aveces suele ser muy complejos, y si no sabes hacer pruebas unitarias o recién estas comenzando se vuelve demasiado 'tóxico' usarlo de manera inapropiada, por ello presento este app donde manejo la construcción de casos de pruebas.

**No entraré en detalles tecnicos únicamente en la configuración del proyecto android**

Las pruebas unitarias fueron trabajadas con  Mockito 1.10.*

[![center](snapshot/mockito.png)](https://github.com/mockito/mockito)

Primero debemos configurar todas nuestras dependencias, creando una extensión en nuestro gradle del proyecto y del módulo.

- Las librerías Permissionlib(para permisos requests apartir de Android 5):
- La libraría SharePreferenceLib(para almacenamiento en disco)
- La libraría Glide para estructuras de datos
- Junit, mockito, hamcrest & powermock para la creación y ejecución de pruebas unitarias

```groovy
glideVersion = '3.6.1'
junitVersion = '4.12'
mockitoVersion = '1.10.19'
powerMockito = '1.6.2'
hamcrestVersion = '1.3'
```

## Lets Coooode! 

En nuestra clase test CameraUnitTest, declaramos variables de pruebas : 
1.  Manejamos una referencia a nuestra interfaz Camara, la cual vamos a probar (hacer los caminos de ejecución)
2.  Un objeto inicializador, deacuerdo al patrón MVP será nuestro **P**resentador Camara
3.  Un objeto de tipo ArgumentCaptor para obtener la información que ha sido vínculada a nuestro objeto mock
4.  He incializar la libraría de prueba

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

### Construcción de la JunitTestCase
En síntesis se requiere probar que la toma realizada sea capturada, visualizada(display) y eliminada. Asegurandonos que sea efectivamente la misma foto capturada desde el inicio

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
Como puede darse cuenta, nuestro objeto *captorString* permite captura el objeto pasado internamente durante el flujo de *takePicture*
De la misma manera verificamos que sea la misma al ser cargada por *viewPicture* 
Pero para eliminar la captura, únicamente verificamos la visualización de la imagen por defecto: mediante la pregunta si el métoto *showDefaultPicture* fue invocado por la vista. 

Un segundo ejemplo, un caso de prueba para nuestra clase SharePreference:

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

Primero validamos que nuestro objeto valor se almacene y se pueda recuperar, y segundo validamos que en nuestra vista(shareView) este obteniendo los valores almacenadores en memoria(repository), note que no exíste una referencia directa en nuestro código de prueba

Una vez creados los casos de pruebas, los ejecutamos normalmente

![center](snapshot/a.png#center)

![center](snapshot/e.png#center) 

Como se puede apreciar las pruebas se ejecutaron con total normalidad.

**Ojo en ningún momento pasamos la referencia de la vista(cameraView) al presentador(cameraPresenter) o el repositorio sharePreference(repository) a la vista (shareView), esta es la gran ventaja de mockito: PERMITE CAPTURAR INTERFACES CON ÚNICAMENTE USANDO EL NOMBRE EXACTO DE LA VARIABLE INTERFAZ USADA**

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