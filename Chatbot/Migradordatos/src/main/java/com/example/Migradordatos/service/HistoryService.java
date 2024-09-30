package com.example.Migradordatos.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class HistoryService 
{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public HistoryService(JdbcTemplate jdbcTemplate) 
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean ChecaContenido() 
    {
        
        String checkTableExistsSql = "SHOW TABLES LIKE 'history'";
        String countRowsSql = "SELECT COUNT(*) FROM history";
        
        try 
        {
           
            boolean tableExists = jdbcTemplate.queryForList(checkTableExistsSql).size() > 0;

            if (tableExists) 
            {
                
                Integer count = jdbcTemplate.queryForObject(countRowsSql, Integer.class);
                if (count != null && count > 0)
                {
                    return true;
                } 
                else 
                {
                    System.out.println("El historial está vacio");
                    return false;
                    
                }
            } 
            else 
            {
                return false;
            }

        } 
        catch (Exception e) 
        {
            System.err.println("Error al verificar o borrar la tabla: " + e.getMessage());
            return false;
        }
    }

    public boolean VaciarTabla() 
    {
        String sql = "DELETE FROM history";
        try 
        {
            jdbcTemplate.execute(sql);
            return true;
        } 
        catch (Exception e)
        {
            System.err.println("Error al borrar la tabla: " + e.getMessage());
            return false;
        }
    }
}
