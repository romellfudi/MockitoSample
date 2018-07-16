package fudi.freddy.mokitosample.camera;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * @version 1.0
 * @autor Romell Domínguez
 * @date 8/13/17
 */

public class CameraUnitTest {

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

    @Test
    public void defaultPic() {
        cameraPresenter.takePicture();
        verify(cameraView).openCamera(captorString.capture());
        show(captorString.getValue());
        cameraPresenter.viewDefaultPicture();
        verify(cameraView).showDefaultPicture();
    }

    private void show(String string) {
        System.out.println(string);
    }
}