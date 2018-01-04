package com.tugaydemirel.database;


import java.util.ArrayList;

/**
 *
 * @author Tugay Demirel
 */
public interface CrudProcesses {
    
    boolean update(Object o);
    
    ArrayList<? extends Object> read(Object o);
    
    boolean create(Object o);
    
    boolean delete(Object o);
    
}