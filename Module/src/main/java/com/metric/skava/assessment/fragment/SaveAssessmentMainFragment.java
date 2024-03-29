package com.metric.skava.assessment.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.metric.skava.R;
import com.metric.skava.app.exception.SkavaSystemException;
import com.metric.skava.app.fragment.SkavaFragment;
import com.metric.skava.app.model.Assessment;
import com.metric.skava.app.util.SkavaConstants;
import com.metric.skava.data.dao.LocalAssessmentDAO;
import com.metric.skava.data.dao.RemoteAssessmentDAO;
import com.metric.skava.data.dao.exception.DAOException;
import com.metric.skava.data.dao.impl.dropbox.AssessmentDAODropboxImpl;

/**
 * Created by metricboy on 4/2/14.
 */
public class SaveAssessmentMainFragment extends SkavaFragment {

    private LocalAssessmentDAO mLocalAssessmentDAO;
    private RemoteAssessmentDAO mRemoteAssessmentDAO;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initLocalDAO();
        initRemoteDAO();
    }

    private void initLocalDAO() {
        try {
            mLocalAssessmentDAO = getDAOFactory().getLocalAssessmentDAO();
        } catch (DAOException e) {
            Log.e(SkavaConstants.LOG, e.getMessage());
            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
            throw new SkavaSystemException(e);
        }
    }

    private void initRemoteDAO() {
        try {
            mRemoteAssessmentDAO = new AssessmentDAODropboxImpl(getActivity(), getSkavaContext());
        } catch (DAOException e) {
            Log.e(SkavaConstants.LOG, e.getMessage());
            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
            throw new SkavaSystemException(e);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.assessment_save_fragment, container, false);
        Button saveBtn = (Button) rootView.findViewById(R.id.save_button);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Assessment assessment = getCurrentAssessment();
                    mLocalAssessmentDAO.saveAssessment(assessment);
                    mRemoteAssessmentDAO.saveAssessment(assessment);
                } catch (DAOException e) {
                    Log.e(SkavaConstants.LOG, e.getMessage());
                    Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
                    throw new SkavaSystemException(e);
                }
            }
        });

        Button sendBtn = (Button) rootView.findViewById(R.id.save_and_send_button);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Assessment assessment = getCurrentAssessment();
                    mLocalAssessmentDAO.saveAssessment(assessment);
                    mRemoteAssessmentDAO.saveAssessment(assessment);
                } catch (DAOException e) {
                    Log.e(SkavaConstants.LOG, e.getMessage());
                    Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
                    throw new SkavaSystemException(e);
                }
            }
        });

        return rootView;
    }

}
