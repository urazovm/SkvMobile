package com.metric.skava.data.dao.impl.sqllite;

import android.content.Context;
import android.database.Cursor;

import com.metric.skava.app.context.SkavaContext;
import com.metric.skava.app.data.IdentifiableEntity;
import com.metric.skava.app.database.utils.CursorUtils;
import com.metric.skava.app.model.Permission;
import com.metric.skava.app.model.User;
import com.metric.skava.data.dao.LocalExcavationProjectDAO;
import com.metric.skava.data.dao.LocalPermissionDAO;
import com.metric.skava.data.dao.LocalTunnelDAO;
import com.metric.skava.data.dao.LocalTunnelFaceDAO;
import com.metric.skava.data.dao.LocalUserDAO;
import com.metric.skava.data.dao.exception.DAOException;
import com.metric.skava.data.dao.impl.sqllite.table.PermissionTable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by metricboy on 3/14/14.
 */
public class PermissionDAOsqlLiteImpl extends SqlLiteBasePersistentEntityDAO<Permission> implements LocalPermissionDAO {

    private LocalUserDAO localUserDAO;

    private LocalExcavationProjectDAO projectDAO;
    private LocalTunnelDAO localTunnelDAO;
    private LocalTunnelFaceDAO localTunnelFaceDAO;

    public PermissionDAOsqlLiteImpl(Context context, SkavaContext skavaContext) throws DAOException {
        super(context, skavaContext);
        localUserDAO = getDAOFactory().getLocalUserDAO();
        projectDAO = getDAOFactory().getLocalExcavationProjectDAO();
        localTunnelDAO = getDAOFactory().getLocalTunnelDAO();
        localTunnelFaceDAO = getDAOFactory().getLocalTunnelFaceDAO();
    }


    @Override
    protected List<Permission> assemblePersistentEntities(Cursor cursor) throws DAOException {
        List<Permission> list = new ArrayList<Permission>();
        while (cursor.moveToNext()) {
            String userCode = CursorUtils.getString(PermissionTable.USER_CODE_COLUMN, cursor);
            String actionCode = CursorUtils.getString(PermissionTable.ACTION_COLUMN, cursor);
            String targetTypeAsString = CursorUtils.getString(PermissionTable.TARGET_TYPE_CODE_COLUMN, cursor);
            String targetCode = CursorUtils.getString(PermissionTable.TARGET_CODE_COLUMN, cursor);

            User grantedUser = localUserDAO.getUserByCode(userCode);

            Permission.Action actionType = Permission.Action.valueOf(actionCode);

            Permission.IdentifiableEntityType targetType = Permission.IdentifiableEntityType.valueOf(targetTypeAsString);

            IdentifiableEntity targetEntity = null;

            switch (targetType){
                case PROJECT:
                    targetEntity = projectDAO.getExcavationProjectByCode(targetCode);
                    break;
                case TUNNEL:
                    targetEntity = localTunnelDAO.getTunnelByUniqueCode(targetCode);
                    break;
                case FACE:
                    targetEntity = localTunnelFaceDAO.getTunnelFaceByCode(targetCode);
                    break;
            }
            Permission newInstance = new Permission(grantedUser, actionType, targetType, targetEntity);
            list.add(newInstance);
        }
        return list;
    }

    @Override
    protected void savePersistentEntity(String tableName, Permission newPermission) throws DAOException {
        String[] columns = new String[]{PermissionTable.USER_CODE_COLUMN, PermissionTable.ACTION_COLUMN, PermissionTable.TARGET_TYPE_CODE_COLUMN, PermissionTable.TARGET_CODE_COLUMN};
        String[] values = new String[]{newPermission.getWho().getCode(), newPermission.getWhat().name(), newPermission.getWhere().name(), newPermission.getWhereExactly().getCode()};
        saveRecord(tableName, columns, values);
    }

    @Override
    public List<Permission> getAllPermissions() throws DAOException {
        List<Permission> list = getAllPersistentEntities(PermissionTable.PERMISSION_DATABASE_TABLE);
        return list;
    }

    @Override
    public List<Permission> getPermissionsByUser(User user) throws DAOException {
        List<Permission> permissionList;
        Cursor cursor = getRecordsFilteredByColumn(PermissionTable.PERMISSION_DATABASE_TABLE, PermissionTable.USER_CODE_COLUMN, user.getCode(), null);
        permissionList = assemblePersistentEntities(cursor);
        return permissionList;
    }

    @Override
    public List<User> getUsersByPermissionTarget(Permission.Action what, IdentifiableEntity where) throws DAOException {
        return null;
    }

    @Override
    public void savePermission(Permission newPermission) throws DAOException {
        savePersistentEntity(PermissionTable.PERMISSION_DATABASE_TABLE, newPermission);
    }

    @Override
    public boolean deletePermissionsByUser(User user) {
        int numDeleted = deletePersistentEntitiesFilteredByColumn(PermissionTable.PERMISSION_DATABASE_TABLE, PermissionTable.USER_CODE_COLUMN, user.getCode());
        return numDeleted != -1;
    }

    @Override
    public int deleteAllPermissions() {
        return deleteAllPersistentEntities(PermissionTable.PERMISSION_DATABASE_TABLE);
    }
}
