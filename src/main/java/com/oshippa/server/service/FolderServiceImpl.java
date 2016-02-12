/*
 * Copyright (c) 2016. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.oshippa.server.service;

import com.oshippa.common.db.service.BaseGenericServiceImpl;
import com.oshippa.server.dao.FolderDao;
import com.oshippa.server.dao.ModuleDao;
import com.oshippa.server.model.file.Folder;
import com.oshippa.server.transfd.file_element.folder.CreateFolderToParentFolderRequest;
import com.oshippa.server.transfd.file_element.folder.CreateRootFolderRequest;
import com.oshippa.server.transfd.file_element.folder.FolderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by steve on 2/12/16.
 */
@Service
public class FolderServiceImpl extends BaseGenericServiceImpl<Folder, String> implements FolderService {


    @Autowired
    FolderDao folderDao;
    @Autowired
    ModuleDao moduleDao;

    @Autowired
    public FolderServiceImpl(Validator validator) {
        super(validator);
    }

    @Override
    public FolderResponse createRootFolderUnderModule(CreateRootFolderRequest createRootFolderRequest) {
        moduleDao.checkExist(createRootFolderRequest.getModuleId(), " module ");
        Folder folder = new Folder();
        folder.setDepth(0);
        folder.setName(createRootFolderRequest.getName());
        folder.setDescription(createRootFolderRequest.getDescription());
        folder.setModule_id(createRootFolderRequest.getModuleId());
        folderDao.createFolder(folder);
        FolderResponse response = new FolderResponse(folder);
        return response;
    }

    @Override
    public List<FolderResponse> getChildFolder(String id) {
        List<Folder> list = folderDao.getFoldersByParentFolder(id);
        List<FolderResponse> listResponse = new ArrayList<>();
        for (Folder folder : list) {
            listResponse.add(new FolderResponse(folder));
        }
        return listResponse;
    }

    @Override
    public FolderResponse createFolderToParent(CreateFolderToParentFolderRequest createFolderToParentFolderRequest) {
        Folder folder = new Folder();
        folder.setDescription(createFolderToParentFolderRequest.getDescription());
        folder.setName(createFolderToParentFolderRequest.getName());
        folder.setDeleted(false);
        folderDao.createFolderToParentFolder(createFolderToParentFolderRequest.getParentFolderId(), folder);
        return new FolderResponse(folder);
    }


}
