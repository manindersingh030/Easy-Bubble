package com.eworl.easybubble.db;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

import com.eworl.easybubble.db.program;

import com.eworl.easybubble.db.programDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig programDaoConfig;

    private final programDao programDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        programDaoConfig = daoConfigMap.get(programDao.class).clone();
        programDaoConfig.initIdentityScope(type);

        programDao = new programDao(programDaoConfig, this);

        registerDao(program.class, programDao);
    }
    
    public void clear() {
        programDaoConfig.getIdentityScope().clear();
    }

    public programDao getProgramDao() {
        return programDao;
    }

}