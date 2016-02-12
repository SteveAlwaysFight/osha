/*
 * Copyright (c) 2016. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.oshippa.server.dao;

import com.oshippa.common.db.dao.HibernateBaseGenericDaoImpl;
import com.oshippa.common.model.Element;
import com.oshippa.server.exception.ElementNotFoundException;
import com.oshippa.server.exception.FileElementDuplicatedException;
import com.oshippa.server.model.file.Folder;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by steve on 2/12/16.
 */
@Repository("folderDao")
public class FolderDaoImpl extends HibernateBaseGenericDaoImpl<Folder,String> implements FolderDao{
    /**
     * 构造方法，根据实例类自动获取实体类类型
     *
     * @param serverHibernateSessionFactory
     */
    @Autowired
    public FolderDaoImpl(SessionFactory serverHibernateSessionFactory) {
        super(serverHibernateSessionFactory);
    }


    @Override
    public List<Folder> getRootFolderByModuleId(String moduleId) {
        Map map = new HashMap<>();
        map.put("moduleId",moduleId);
        map.put("depth",0);
        return super.getListbyParams(map);
    }

    @Override
    public Folder getFolderById(String id) {
        return super.get(id);
    }

    @Override
    public void deleteFolder(String id) {
        Folder folder = this.getFolderById(id);
        if(folder==null){
            throw new ElementNotFoundException("Folder");
        }
        folder.setDeleted(true);
        this.update(folder);
    }

    @Override
    public Folder updateFolder(String id, Folder folder) {
        Folder folder1 = this.getFolderById(id);
        if(folder1==null){
            throw new ElementNotFoundException("Folder");
        }
        folder1.setDeleted(folder.isDeleted());
        folder1.setName(folder.getName());
        folder1.setDepth(folder.getDepth());
        folder1.setDescription(folder.getDescription());
        folder1.setModule_id(folder.getModule_id());
        folder1.setParentFoldId(folder.getParentFoldId());
        super.update(folder1);
        return folder1;
    }

    @Override
    public Folder createFolder(Folder folder) {
        if(folder.getParentFoldId() == null){
            folder.setParentFoldId("null");
        }
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("from Folder folder where folder.name=:name and folder.parentFolderId=:parentFolderId");
        query.setString("parentFolderId",folder.getParentFoldId());
        query.setString("name",folder.getName());
        if(query.list().size()!=0){
            throw new FileElementDuplicatedException(folder.getName());
        }
        super.save(folder);
        session.getTransaction().commit();
        folder.setParentFoldId(null);
        return folder;
    }

    @Override
    public Folder createFolderToParentFolder(String parentId, Folder folder) {
        Folder parent = super.checkExist(parentId," parent folder ");
        folder.setParentFoldId(parent.getId());
        folder.setDepth(parent.getDepth()+1);
        folder.setModule_id(parent.getModule_id());
        this.save(folder);
        return folder;
    }

    @Override
    public List<Folder> getFoldersByParentFolder(String parentId) {
        super.checkExist(parentId," parent folder ");
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("from Folder folder where folder.parentFoldId=:parentFolderId and folder.isDeleted=false ");
        List<Folder> list = query.list();
        return list;
    }



//    private Folder checkExisting(String id,String msg){
//        Folder parent = this.getFolderById(id);
//        if(null == parent){
//            throw new ElementNotFoundException(msg);
//        }
//        return parent;
//    }
}
