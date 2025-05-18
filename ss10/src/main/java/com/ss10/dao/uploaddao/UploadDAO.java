package com.ss10.dao.uploaddao;

import com.ss10.model.UploadFile;

public interface UploadDAO{
    boolean save(String file, String description);
}
