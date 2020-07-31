package fudi.freddy.mokitosample.share;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @version 1.0
 * @autor Romell Domínguez
 * @date 8/13/17
 */

public class ShareUnitTest {

    @Mock
    ShareContract.ShareView shareView;
    @Mock
    Repository repository;

    @InjectMocks
    SharePresent sharePresent = new SharePresent(shareView);

    @Captor
    ArgumentCaptor<String> stringArgumentCaptor;


    @Before
    public void prepare() {
        MockitoAnnotations.initMocks(this);
//        sharePresent.setShareView(shareView);
    }

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
}
