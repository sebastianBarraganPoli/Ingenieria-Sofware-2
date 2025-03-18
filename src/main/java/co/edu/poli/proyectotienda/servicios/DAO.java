package co.edu.poli.proyectotienda.servicios;

import java.util.List;

public interface DAO {

	/**
     * @param o 
     * @return
     */
    public String create(Object o);

    /**
     * @return
     */
    public List<Object> list();

    /**
     * @param id 
     * @return
     */
    public Object read(String id);

    /**
     * @param id 
     * @param o 
     * @return
     */
    public String update(String id, Object o);

    /**
     * @param id 
     * @return
     */
    public String delete(String id);
}
