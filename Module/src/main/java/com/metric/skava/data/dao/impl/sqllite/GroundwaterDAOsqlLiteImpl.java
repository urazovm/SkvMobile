package com.metric.skava.data.dao.impl.sqllite;

import android.content.Context;
import android.database.Cursor;

import com.metric.skava.calculator.rmr.model.Groundwater;
import com.metric.skava.data.dao.LocalGroundwaterDAO;
import com.metric.skava.data.dao.exception.DAOException;
import com.metric.skava.data.dao.impl.sqllite.helper.MappedIndexInstanceBuilder4SqlLite;
import com.metric.skava.data.dao.impl.sqllite.table.GroundwaterTable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by metricboy on 3/18/14.
 */
public class GroundwaterDAOsqlLiteImpl extends SqlLiteBaseDAO implements LocalGroundwaterDAO {

    private Context mContext;
    private MappedIndexInstanceBuilder4SqlLite mappedIndexInstaceBuilder;

    public Context getContext() {
        return mContext;
    }

    public GroundwaterDAOsqlLiteImpl(Context context) throws DAOException {
        super(context);
        mContext = context;
        mappedIndexInstaceBuilder = new MappedIndexInstanceBuilder4SqlLite(mContext);
    }

    @Override
    public Groundwater getGroundwater(String indexCode, String groupCode, String code) throws DAOException {
        String[] names = new String[]{GroundwaterTable.INDEX_CODE_COLUMN, GROUP_CODE_COLUMN, CODE_COLUMN};
        String[] values = new String[]{indexCode, groupCode, code};
        Cursor cursor = getRecordsFilteredByColumns(GroundwaterTable.MAPPED_INDEX_DATABASE_TABLE, names , values, null );
        List<Groundwater> list = assambleGroundwaters(cursor);
        if (list.isEmpty()) {
            throw new DAOException("Entity not found. [Index Code : " + indexCode + ", Group Code: "+ groupCode + ", Code: " + code + " ]");
        }
        if (list.size() > 1) {
            throw new DAOException("Multiple records for same code. [Index Code : " + indexCode + ", Group Code: "+ groupCode + ", Code: " + code + " ]");
        }
        cursor.close();
        return list.get(0);
    }


    protected List<Groundwater> assambleGroundwaters(Cursor cursor) throws DAOException {
        List<Groundwater> list = new ArrayList<Groundwater>();
        while (cursor.moveToNext()) {
            Groundwater newInstance = mappedIndexInstaceBuilder.buildGroundwaterFromCursorRecord(cursor);
            list.add(newInstance);
        }
        return list;
    }


    @Override
    public List<Groundwater> getAllGroundwaters() throws DAOException {
        Cursor cursor = getAllRecords(GroundwaterTable.MAPPED_INDEX_DATABASE_TABLE);
        List<Groundwater> list = assambleGroundwaters(cursor);
        cursor.close();
        return list;
    }


    @Override
    public void saveGroundwater(Groundwater assessment) throws DAOException {

    }

    @Override
    public boolean deleteGroundwater(String indexCode, String groupCode, String code) {
        return false;
    }

    @Override
    public int deleteAllGroundwaters() {
        return 0;
    }


}
