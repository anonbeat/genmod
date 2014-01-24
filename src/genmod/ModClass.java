/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package genmod;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Juan Rios anonbeat@gmail.com
 */
public class ModClass implements Serializable {
    //
    private String name;
    private String description;
    private String order;
    private String menuPath;
    
    //
    private ArrayList<ModAttr> attributes;

    public ModClass()
    {
        name = "";
        description = "";
        order = "";
        attributes = new ArrayList();
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

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the order
     */
    public String getOrder() {
        return order;
    }

    /**
     * @param order the order to set
     */
    public void setOrder(String order) {
        this.order = order;
    }

    /**
     * @return the menuPath
     */
    public String getMenuPath() {
        return menuPath;
    }

    /**
     * @param menuPath the menuPath to set
     */
    public void setMenuPath(String menuPath) {
        this.menuPath = menuPath;
    }

    /**
     * @return the attributes
     */
    public ArrayList<ModAttr> getAttributes() {
        return attributes;
    }
    
    /**
     * @return the attributes
     */
    public void setAttributes( ArrayList<ModAttr> attributes ) 
    {
        this.attributes = attributes;
    }


}
