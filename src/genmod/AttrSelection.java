/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package genmod;

import java.io.Serializable;

/**
 *
 * @author Juan Rios anonbeat@gmail.com
 */
public class AttrSelection implements Serializable {
    private String value;
    private String name;

    public AttrSelection()
    {
    }
   
    public AttrSelection( String value, String name )
    {
        this.value = value;
        this.name = name;
    }
    
    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    
}
