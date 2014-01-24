/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package genmod;

import java.util.ArrayList;

/**
 *
 * @author Juan Rios anonbeat@gmail.com
 */
public class Module {
    private String name;
    private String version;
    private String author;
    private String web;
    private String category;
    private String depends;
    private String description;
    private boolean active;
    private boolean installable;
    private ArrayList<ModClass> classes;

    /**
     * 
     */
    public Module()
    {
        classes = new ArrayList();
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
     * @return the version
     */
    public String getVersion() {
        return version;
    }

    /**
     * @param version the version to set
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @param author the author to set
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * @return the web
     */
    public String getWeb() {
        return web;
    }

    /**
     * @param web the web to set
     */
    public void setWeb(String web) {
        this.web = web;
    }

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return the depends
     */
    public String getDepends() {
        return depends;
    }

    /**
     * @param depends the depends to set
     */
    public void setDepends(String depends) {
        this.depends = depends;
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
     * @return the active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * @param active the active to set
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * @return the installable
     */
    public boolean isInstallable() {
        return installable;
    }

    /**
     * @param installable the installable to set
     */
    public void setInstallable(boolean installable) {
        this.installable = installable;
    }

    /**
     * @return the classes
     */
    public ArrayList<ModClass> getClasses() {
        return classes;
    }

    /**
     * @param classes the classes to set
     */
    public void setClasses(ArrayList<ModClass> classes) {
        this.classes = classes;
    }
    
    
}
