package com.zykov.andrii.a201801307_az_nycschools;

import android.content.Context;

import com.zykov.andrii.a201801307_az_nycschools.data.SchoolSatWrapper;
import com.zykov.andrii.a201801307_az_nycschools.data.SchoolWrapper;
import com.zykov.andrii.a201801307_az_nycschools.presenter.SchoolsPresenterImpl;
import com.zykov.andrii.a201801307_az_nycschools.utils.nycschoolservice.NYCSchoolsAPI;
import com.zykov.andrii.a201801307_az_nycschools.view.ISchoolView;
import com.zykov.andrii.a201801307_az_nycschools.view.SchoolsFragment;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

/**
 * Created by andrii on 3/8/18.
 */


public class SchoolsPresenterUnitTest {

    @Captor
    ArgumentCaptor<SchoolWrapper> schoolWrapperCaptor;

    @ClassRule
    public static final RxImmediateSchedulerRule schedulers = new RxImmediateSchedulerRule();

    @Mock
    private NYCSchoolsAPI nycSchoolsAPI;

    @Mock
    private ISchoolView view;

    @Mock
    private Context contextMock;

    private String SCHOOL_NAME_MOCK_STRING = "school_1";

    private String SCHOOL_DBN_MOCK_STRING_1 = "12345";

    private static final String ERROR_STRING = "ERROR_STRING";

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void loadSchoolsSuccess() {
        SchoolsFragment.ISchoolsFragmentPresenter presenter = new SchoolsPresenterImpl(nycSchoolsAPI, contextMock);
        presenter.bindView(view);
        List<SchoolWrapper> response = new ArrayList<>();
        response.add(new SchoolWrapper(SCHOOL_NAME_MOCK_STRING));
        when(nycSchoolsAPI.getSchools())
                .thenReturn(Observable.just(response));
        presenter.loadSchools();
        InOrder inOrder = Mockito.inOrder(view);
        inOrder.verify(view, times(1)).showProgressBar();
        inOrder.verify(view, times(1)).showSchools(response);
        inOrder.verify(view, times(1)).hideProgressBar();
    }


    @Test
    public void loadSchoolsError() {
        SchoolsFragment.ISchoolsFragmentPresenter presenter = new SchoolsPresenterImpl(nycSchoolsAPI, contextMock);
        presenter.bindView(view);
        when(contextMock.getString(R.string.load_schools_error))
                .thenReturn(ERROR_STRING);
        Exception exception = new Exception();
        when(nycSchoolsAPI.getSchools())
                .thenReturn(Observable.error(exception));
        presenter.loadSchools();
        InOrder inOrder = Mockito.inOrder(view);
        inOrder.verify(view, times(1)).showProgressBar();
        inOrder.verify(view, times(1)).showError(ERROR_STRING);
        inOrder.verify(view, times(1)).showReloadButton();
        inOrder.verify(view, times(1)).hideProgressBar();
    }

    @Test
    public void showSchoolDetailsSuccess() {
        SchoolsFragment.ISchoolsFragmentPresenter presenter = new SchoolsPresenterImpl(nycSchoolsAPI, contextMock);
        presenter.bindView(view);
        List<SchoolSatWrapper> response = new ArrayList<>();
        response.add(new SchoolSatWrapper(SCHOOL_NAME_MOCK_STRING, SCHOOL_DBN_MOCK_STRING_1));
        SchoolWrapper sw = new SchoolWrapper(SCHOOL_NAME_MOCK_STRING);
        sw.setDbn(SCHOOL_DBN_MOCK_STRING_1);
        when(nycSchoolsAPI.getSchoolsSat())
                .thenReturn(Observable.just(response));
        presenter.onSchoolItemSelected(sw);
        InOrder inOrder = Mockito.inOrder(view);
        inOrder.verify(view, times(1)).showProgressBar();
        inOrder.verify(view, times(1)).showSchoolDetails(schoolWrapperCaptor.capture());
        inOrder.verify(view, times(1)).hideProgressBar();
        assertEquals(sw, schoolWrapperCaptor.getValue());
        assertEquals(response.get(0), schoolWrapperCaptor.getValue().getSchoolSatWrapper());
    }

}
