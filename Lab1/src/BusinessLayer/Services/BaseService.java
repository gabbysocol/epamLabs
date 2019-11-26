package BusinessLayer.Services;

import DataAccess.Repository.ReadWriteData;

import java.util.ArrayList;

public abstract class BaseService<T> {

    private ReadWriteData _readWriteData;
    protected ArrayList<T> _entities;

    public BaseService(String filePath){
        _readWriteData = new ReadWriteData<T>(filePath);
    }

    protected void loadEntities(){
        _entities = (ArrayList<T>)_readWriteData.readArrayOfEntities();
    }

    public T getEntityByPosition(int position) {
        if (_entities == null)
            loadEntities();
        if (position < _entities.size() && position >= 0)
            return _entities.get(position);
        return null;
    }

    public int getAmountOfEntities(){
        if (_entities == null)
            loadEntities();
        return _entities.size();
    }
    
    public void showAll() {
        if (_entities == null)
            loadEntities();
        for (T entity: _entities ) {
            System.out.println(entity);
        }
    }

    public void addEntity(T entity) {
        if (_entities == null)
            loadEntities();
        this._entities.add(entity);
    }

    public void addEntity(T[] entities) {
        if(_entities == null)
            loadEntities();
        for(T entity : entities){
            this.addEntity(entity);
        }
    }

    public void removeEntity(T entity)  {
        if (_entities == null)
            loadEntities();
        _entities.remove(entity);
    }

    public void removeEntity(T[] entities) {
        if (_entities == null)
            loadEntities();
        for(T entity : entities) {
            this.removeEntity(entity);
        }
    }

    public void updateEntityByPosition(T entityNew, int position)  {
        if (_entities == null)
            loadEntities();
        this._entities.set(position, entityNew);
    }

    public void saveEntities()  {
        if(_entities != null)
            _readWriteData.writeArrayOfEntities(_entities);
    }

}
