package com.metric.skava.data.dao.impl.dropbox.datastore.tables;

import com.dropbox.sync.android.DbxDatastore;
import com.dropbox.sync.android.DbxTable;

/**
 * Created by metricboy on 3/24/14.
 */
public class ExcavationProjectDropboxTable extends DropboxBaseTable implements DropboxTable {


    public ExcavationProjectDropboxTable(DbxDatastore datastore) {
        super(datastore);
    }

    @Override
    public DbxTable getBaseDropboxTable() {
        return mDatastore.getTable("Projects");
    }

    @Override
    public boolean shouldSortByOrdinalColumn() {
        return false;
    }

    @Override
    public boolean shouldSortByKeyColumn() {
        return false;
    }


}
