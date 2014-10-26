package by.mentoring.service;

import by.mentoring.model.BaseStoreObject;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public abstract class BaseService<T extends  BaseStoreObject> {

    private static final Logger LOGGER = Logger.getLogger(BaseService.class);

    protected String pathToStorageFile;
    protected File storageFile;
    protected ObjectMapper mapper = new ObjectMapper();

    public BaseService(String pathToStorageFile) {
        this.pathToStorageFile = pathToStorageFile;
        checkFileExists(pathToStorageFile);
    }

    public T saveOrUpdate(T baseObject) throws IOException {
        List<T> allObjects = getAll();

        int biggestId = 0;
        int objId = biggestId;
        for (T obj : allObjects) {
            biggestId = biggestId < obj.getId() ? obj.getId() : biggestId;
            if (baseObject.getId() == obj.getId()) {
                obj = baseObject;
                objId = obj.getId();
            }
        }
        if (biggestId != 0 || allObjects.isEmpty()) {
            baseObject.setId(++biggestId);
            objId = biggestId;
            allObjects.add(baseObject);
        }

        mapper.writeValue(storageFile, allObjects);
        System.out.println(objId);
        return get(objId);
    }

    public List<T> getAll() {
        List<T> list = new LinkedList<>();
        try {
            list = mapper.readValue(storageFile, getValueType());
        } catch (IOException e) {
            LOGGER.error(e);
        }
        return list;
    }

    protected abstract JavaType getValueType();

    public T get(int id) {
        List<T> allObjects = getAll();
        for (T obj : allObjects) {
            if (obj.getId() == id) {
                return obj;
            }
        }
        return null;
    }

    public void remove(int id) throws IOException {
        List<T> allObjects = getAll();
        for (T obj : allObjects) {
            if (obj.getId() == id) {
                allObjects.remove(obj);
            }
        }
        mapper.writeValue(storageFile, allObjects);
    }


    private void checkFileExists(String pathToStorageFile) {
        storageFile = new File(pathToStorageFile);
        if (!storageFile.exists()) {
            try {
                storageFile.createNewFile();
            } catch (IOException e) {
                LOGGER.error("Error while creating file", e);
            }
        }
    }
}
