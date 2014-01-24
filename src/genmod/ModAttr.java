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
public class ModAttr implements Serializable {
    //
    public static final int ATTR_TYPE_BOOLEAN = 0;
    public static final int ATTR_TYPE_INTEGER = 1;
    public static final int ATTR_TYPE_FLOAT = 2;
    public static final int ATTR_TYPE_CHAR = 3;
    public static final int ATTR_TYPE_TEXT = 4;
    public static final int ATTR_TYPE_DATE = 5;
    public static final int ATTR_TYPE_DATETIME = 6;
    public static final int ATTR_TYPE_BINARY = 7;
    public static final int ATTR_TYPE_SELECTION = 8;
    public static final int ATTR_TYPE_MANY2ONE = 9;
    public static final int ATTR_TYPE_ONE2MANY = 10;
    public static final int ATTR_TYPE_MANY2MANY = 11;
    //
    public static final String ATTR_TYPE_NAMES[] = {
        "Boolean",
        "Integer",
        "Float",
        "Char",
        "Text",
        "Date",
        "DateTime",
        "Binary",
        "Selection",
        "Many2One",
        "One2Many",
        "Many2Many"
    };

    //
    private String  name;
    private String  fieldName;
    private int     type;
    private int     size;
    private String  description;
    private String  string;
    private String  initVal;
    private boolean translate;
    private boolean required;
    private boolean readOnly;
    private ArrayList<AttrSelection> selections;
    private String  relRelId;
    private String  relObjId;
    private String  relOthId;
    private String  relOthName;

    public ModAttr()
    {
        selections = new ArrayList<>();
    }
    
    /**
     * 
     * @param name
     * @param type
     * @param size
     * @param description
     * @param string
     * @param defect
     * @param translate 
     */
    public ModAttr( String name, int type, int size, String description, 
            String string, String initVal, boolean translate, boolean required, 
            ArrayList<AttrSelection> selections )
    {
        this.name = name;
        this.type = type;
        this.size = size;
        this.description = description;
        this.string = string;
        this.initVal = initVal;
        this.translate = translate;
        this.required = required;
        this.selections = selections;
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
     * @return the fieldName
     */
    public String getFieldName() {
        return fieldName;
    }

    /**
     * @param fieldName the fieldName to set
     */
    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    /**
     * @return the type
     */
    public int getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * @return the size
     */
    public int getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(int size) {
        this.size = size;
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
     * @return the string
     */
    public String getString() {
        return string;
    }

    /**
     * @param string the string to set
     */
    public void setString(String string) {
        this.string = string;
    }

    /**
     * @return the translate
     */
    public boolean isTranslate() {
        return translate;
    }

    /**
     * @param translate the translate to set
     */
    public void setTranslate(boolean translate) {
        this.translate = translate;
    }

    /**
     * @return the initVal
     */
    public String getInitVal() {
        return initVal;
    }

    /**
     * @param initVal the initVal to set
     */
    public void setInitVal( String initVal ) 
    {
        this.initVal = initVal;
    }

    /**
     * @return the required
     */
    public boolean isRequired() {
        return required;
    }

    /**
     * @param required the required to set
     */
    public void setRequired(boolean required) {
        this.required = required;
    }

    /**
     * @return the readOnly
     */
    public boolean isReadOnly() {
        return readOnly;
    }

    /**
     * @param readOnly the readOnly to set
     */
    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    /**
     * @return the selections
     */
    public ArrayList<AttrSelection> getSelections() {
        return selections;
    }

    /**
     * @param selections the selections to set
     */
    public void setSelections( ArrayList<AttrSelection> selections) {
        this.selections = selections;
    }

    /**
     * @return the relRelId
     */
    public String getRelRelId() {
        return relRelId;
    }

    /**
     * @param relRelId the relRelId to set
     */
    public void setRelRelId(String relRelId) {
        this.relRelId = relRelId;
    }

    /**
     * @return the relObjId
     */
    public String getRelObjId() {
        return relObjId;
    }

    /**
     * @param relObjId the relObjId to set
     */
    public void setRelObjId(String relObjId) {
        this.relObjId = relObjId;
    }

    /**
     * @return the relOthId
     */
    public String getRelOthId() {
        return relOthId;
    }

    /**
     * @param relOthId the relOthId to set
     */
    public void setRelOthId(String relOthId) {
        this.relOthId = relOthId;
    }

    /**
     * @return the relOthName
     */
    public String getRelOthName() {
        return relOthName;
    }

    /**
     * @param relOthName the relOthName to set
     */
    public void setRelOthName(String relOthName) {
        this.relOthName = relOthName;
    }
}
