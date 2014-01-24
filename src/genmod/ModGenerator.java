/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package genmod;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.swing.JOptionPane;

/**
 * 
 * @author Juan Rios anonbeat@gmail.com
 */
class MenuItem {
    private String name;
    private String id;
    private String parent;
    private String action;

    static ArrayList<MenuItem> menuItems = new ArrayList<>();

    /**
     * Creates a root entry
     * @param name 
     */
    public MenuItem( String name )
    {
        String nameId = ModGenerator.nameToId( name.toLowerCase() );
        this.name = name;
        this.id = "menu_" + nameId;
        this.parent = "";
        this.action = "";
    }

    /**
     * Creates an entry with no action
     * @param name
     * @param parent 
     */
    public MenuItem( String name, String parent )
    {
        String nameId = ModGenerator.nameToId( name.toLowerCase() );
        this.name = name;
        this.id = "menu_" + nameId;
        this.parent = parent;
        this.action = "";
    }

    /**
     * Creates an action entry
     * @param name
     * @param id
     * @param parent
     * @param action 
     */
    public MenuItem( String name, String id, String parent, String action )
    {
        String nameId = ModGenerator.nameToId( name.toLowerCase() );
        this.name = name;
        if( id.isEmpty() )
        {
            id = "menu_" + nameId;
        }
        this.id = id;
        this.parent = parent;
        if( action.isEmpty() )
        {
            action = "action_" + nameId;
        }
        this.action = action;
    }

    /**
     * Adds a menuitem to the list
     * @param menuItem 
     */
    public static void appendMenuItem( MenuItem menuItem )
    {
        menuItems.add( menuItem );
    }
    
    /**
     * Searches a menuItem by its name
     * @param name
     * @return 
     */
    public static MenuItem searchMenuItem( String name )
    {
        for( MenuItem menuItem : menuItems  )
        {
            if( menuItem.getName().toLowerCase().equals( name ) )
            {
                return menuItem;
            }
        }
        return null;
    }

    /**
     * Clears the menu list
     */
    public static void clearMenuItems()
    {
        menuItems.clear();
    }
    
    /***
     * 
     * @return 
     */
    public static ArrayList<MenuItem> getMenuItems()
    {
        return menuItems;
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
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the parent
     */
    public String getParent() {
        return parent;
    }

    /**
     * @param parent the parent to set
     */
    public void setParent(String parent) {
        this.parent = parent;
    }

    /**
     * @return the action
     */
    public String getAction() {
        return action;
    }

    /**
     * @param action the action to set
     */
    public void setAction(String action) {
        this.action = action;
    }
}

/**
 *
 * @author Juan Rios
 */
public class ModGenerator {
    //
    private Module module;
    private String outputName;
    private String tmpPath;
    private String modPath;
    private String secPath;
    private String moduleName;
    //
    private static final String MODULE_MAIN_PYTHON_FILENAME = "__init__.py";
    private static final String MODULE_MAIN_MODULE_FILENAME = "__openerp__.py";
    private static final String MODULE_MAIN_SECURITY_FILENAME = "security" + File.separatorChar + "ir.model.access.csv";
    //
    private static final String MODULE_HEADER = 
        "# -*- coding: utf-8 -*-\n" +
        "##############################################################################\n" +
        "#\n" +
        "#    OpenERP, Open Source Management Solution\n" +
        "#    Copyright (C) 2004-2013  OpenERP S.A. (<http://openerp.com>).\n" +
        "#\n" +
        "#    This program is free software: you can redistribute it and/or modify\n" +
        "#    it under the terms of the GNU Affero General Public License as\n" +
        "#    published by the Free Software Foundation, either version 3 of the\n" +
        "#    License, or (at your option) any later version.\n" +
        "#\n" +
        "#    This program is distributed in the hope that it will be useful,\n" +
        "#    but WITHOUT ANY WARRANTY; without even the implied warranty of\n" +
        "#    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the\n" +
        "#    GNU Affero General Public License for more details.\n" +
        "#\n" +
        "#    You should have received a copy of the GNU Affero General Public License\n" +
        "#    along with this program.  If not, see <http://www.gnu.org/licenses/>.\n" +
        "#\n" +
        "##############################################################################\n" +
        "# Generated by GenMod (J.Rios anonbeat@gmail.com)\n";
    //
    private static final String MODULE_MAIN_PYTHON = 
        "{\n" +
        "        \"name\" : \"{MODULE_NAME}\",\n" +
        "        \"version\" : \"{MODULE_VERSION}\",\n" +
        "        \"author\" : \"{MODULE_AUTHOR}\",\n" +
        "        \"website\" : \"{MODULE_WEBSITE}\",\n" +
        "        \"category\" : \"{MODULE_CATEGORY}\",\n" +
        "        \"description\" : \"\"\"{MODULE_DESCRIPTION}\"\"\",\n" +
        "        \"depends\" : [{MODULE_DEPENDS}],\n" +
        "        \"init_xml\" : [ ],\n" +
        "        \"demo_xml\" : [ ],\n" +
        "        \"update_xml\" : ['{MODULO_VIEW_FILE_NAME}', 'security/ir.model.access.csv'],\n" +
        "        \"installable\" : {MODULE_INSTALLABLE}\n" +
        "}\n";

    private static final String MODULE_CLASS =
        "class {MODULE_CLASS_NAME}(osv.osv):\n" +
        "    \"\"\"{MODULE_CLASS_PROPERTY_DESCRIPTION}\"\"\"\n" +
        "    _name = '{MODULE_CLASS_PROPERTY_NAME}'\n" +
        "    _columns = {\n{MODULE_CLASS_PROPERTY_COLUMNS}    }\n" +
        "    _order = '{MODULE_CLASS_PROPERTY_ORDER}'\n" +
        "    _defaults = {\n{MODULE_CLASS_PROPERTY_DEFAULTS}    }\n" +
        "{MODULE_CLASS_NAME}()\n\n";

    private static final String[] MODULE_ATTRIBUTE = {
        "'{MODULE_ATTRIBUTE_NAME}': fields.boolean( '{MODULE_ATTRIBUTE_FIELD_NAME}'{MODULE_ATTRIBUTE_OPTIONAL} ),\n",
        "'{MODULE_ATTRIBUTE_NAME}': fields.integer( '{MODULE_ATTRIBUTE_FIELD_NAME}'{MODULE_ATTRIBUTE_OPTIONAL} ),\n",
        "'{MODULE_ATTRIBUTE_NAME}': fields.float( '{MODULE_ATTRIBUTE_FIELD_NAME}'{MODULE_ATTRIBUTE_OPTIONAL} ),\n",
        "'{MODULE_ATTRIBUTE_NAME}': fields.char( '{MODULE_ATTRIBUTE_FIELD_NAME}'{MODULE_ATTRIBUTE_OPTIONAL} ),\n",
        "'{MODULE_ATTRIBUTE_NAME}': fields.text( '{MODULE_ATTRIBUTE_FIELD_NAME}'{MODULE_ATTRIBUTE_OPTIONAL} ),\n",
        "'{MODULE_ATTRIBUTE_NAME}': fields.date( '{MODULE_ATTRIBUTE_FIELD_NAME}'{MODULE_ATTRIBUTE_OPTIONAL} ),\n",
        "'{MODULE_ATTRIBUTE_NAME}': fields.datetime( '{MODULE_ATTRIBUTE_FIELD_NAME}'{MODULE_ATTRIBUTE_OPTIONAL} ),\n",
        "'{MODULE_ATTRIBUTE_NAME}': fields.binary( '{MODULE_ATTRIBUTE_FIELD_NAME}'{MODULE_ATTRIBUTE_OPTIONAL} ),\n",
        "'{MODULE_ATTRIBUTE_NAME}': fields.selection( {MODULE_ATTRIBUTE_SELECTIONS}, '{MODULE_ATTRIBUTE_FIELD_NAME}'{MODULE_ATTRIBUTE_OPTIONAL} ),\n",
        "'{MODULE_ATTRIBUTE_NAME}': fields.many2one( '{MODULE_ATTRIBUTE_OTHER_NAME}', '{MODULE_ATTRIBUTE_FIELD_NAME}'{MODULE_ATTRIBUTE_OPTIONAL} ),\n",
        "'{MODULE_ATTRIBUTE_NAME}': fields.one2many( '{MODULE_ATTRIBUTE_OTHER_NAME}', " +
            "'{MODULE_ATTRIBUTE_RELATION}', '{MODULE_ATTRIBUTE_FIELD_NAME}'{MODULE_ATTRIBUTE_OPTIONAL} ),\n",
        "'{MODULE_ATTRIBUTE_NAME}': fields.many2many( '{MODULE_ATTRIBUTE_OTHER_NAME}', " +
            "'{MODULE_ATTRIBUTE_RELATION}', '{MODULE_ATTRIBUTE_OBJECT_ID}', " +
            "'{MODULE_ATTRIBUTE_OTHER_ID}', {MODULE_ATTRIBUTE_FIELD_NAME}'{MODULE_ATTRIBUTE_OPTIONAL} ),\n",
    };
    
    private static final String MODULE_VIEW_HEADER = 
        "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
        "<!-- Generated by GenMod (J.Rios anonbeat@gmail.com) -->\n" +
        "<openerp>\n" +
        "  <data>\n";
    
    private static final String MODULE_VIEW_TAIL =
        "  </data>\n" +
        "</openerp>\n";

    private static final String MODULE_VIEW_CLASS =
        "    <!-- ====== {MODULE_VIEW_CLASS_NAME} ===== -->\n" +
        "    <record model=\"ir.ui.view\" id=\"view_{MODULE_VIEW_CLASS_NAME_ID}_form\">\n" +
        "      <field name=\"name\">{MODULE_VIEW_CLASS_NAME}.form</field>\n" +
        "      <field name=\"model\">{MODULE_VIEW_CLASS_NAME}</field>\n" +
        "      <field name=\"type\">form</field>\n" +
        "      <field name=\"arch\" type=\"xml\">\n" +
        "        <form string=\"{MODULE_VIEW_CLASS_NAME}\">\n" + 
        "{MODULE_VIEW_CLASS_FORM}" +
        "        </form>\n" +
        "      </field>\n" +
        "    </record>\n" +
        "    <record model=\"ir.ui.view\" id=\"view_{MODULE_VIEW_CLASS_NAME_ID}_tree\">\n" +
        "      <field name=\"name\">{MODULE_VIEW_CLASS_NAME}.tree</field>\n" +
        "      <field name=\"model\">{MODULE_VIEW_CLASS_NAME}</field>\n" +
        "      <field name=\"type\">tree</field>\n" +
        "      <field name=\"arch\" type=\"xml\">\n" +
        "        <tree string=\"{MODULE_VIEW_CLASS_NAME}\">\n" +
        "{MODULE_VIEW_CLASS_TREE}" +
        "        </tree>\n" +
        "      </field>\n" +
        "    </record>\n" +
        "    <record model=\"ir.actions.act_window\" id=\"action_{MODULE_VIEW_CLASS_NAME_ID}\">\n" +
        "      <field name=\"name\">{MODULE_VIEW_MENU_NAME}</field>\n" +
        "      <field name=\"res_model\">{MODULE_VIEW_CLASS_NAME}</field>\n" +
        "      <field name=\"view_type\">form</field>\n" +
        "      <field name=\"view_mode\">{MODULE_VIEW_CLASS_MODE}</field>\n" +
        "    </record>\n";
    
    private static final String MODULE_SECURITY_HEADER = 
        "\"id\",\"name\",\"model_id:id\",\"group_id:id\",\"perm_read\"," + 
        "\"perm_write\",\"perm_create\",\"perm_unlink\"\n";

    private static final String MODULE_SECURITY_CLASS = 
        "\"access_{MODULE_VIEW_CLASS_NAME_ID}\",\"{MODULE_VIEW_CLASS_NAME}\"," + 
        "\"model_{MODULE_VIEW_CLASS_NAME_ID}\",\"base.group_user\",1,0,0,0\n";
    
    /**
     * 
     * @param module 
     */
    public ModGenerator( Module module )
    {
        this.module = module;
        moduleName = nameToId( module.getName().toLowerCase() );
    }
    
    /***
     * 
     * @return
     * @throws Exception 
     */
    private boolean createTempFolder() throws Exception
    {
        // Create a temporary folder
        File temp = File.createTempFile( "genmod", "" );
        if( temp.delete() )
        {
            if( temp.mkdir() )
            {
                tmpPath = temp.getAbsolutePath();
                return true;
            }
        }
        return false;
    }

    /**
     * Convert all spaces to underscores. Mainly used to get project name
     * @param name
     * @return 
     */
    public static String nameToId( String name )
    {
        return name.replaceAll("[ \\.]", "_" );
    }

    /***
     * 
     * @param str
     * @return 
     */
    private String stringClean( String str )
    {
        str = str.replace( "'", "\\'" );
        str = str.replace( "\"", "\\\"" );
        return str;
    }
    
    /**
     * Creates the module folders inside the temporary folder
     * @return true if the folders could be created...
     * @throws Exception 
     */
    private boolean createModuleFolders() throws Exception
    {
        // Create a temporary folder
        File moduleDir = new File( tmpPath + File.separatorChar + moduleName );
        if( moduleDir.mkdir() )
        {
            modPath = moduleDir.getAbsolutePath();
            File secDir = new File( modPath + File.separatorChar + "security" );
            if( secDir.mkdir() )
            {
                secPath = secDir.getAbsolutePath();
                return true;
            }
        }
        return false;
    }
    
    /**
     * creates the main module file
     * @throws Exception 
     */
    void createMainPythonFile() throws Exception
    {
        File pyFile = new File( modPath + File.separatorChar + MODULE_MAIN_PYTHON_FILENAME );
        if( !pyFile.exists() )
        {
            pyFile.createNewFile();
        }
        FileOutputStream fos = new FileOutputStream( pyFile );
        fos.write( MODULE_HEADER.getBytes() );
        fos.write( ( "import " + moduleName + "\n" ).getBytes() );
        fos.close();
    }
    
    /**
     * Create the main module file where the module definition is
     * @throws Exception 
     */
    private void createMainModuleFile() throws Exception
    {
        File mainFile = new File( modPath + File.separatorChar + MODULE_MAIN_MODULE_FILENAME );
        if( !mainFile.exists() )
        {
            mainFile.createNewFile();
        }
        FileOutputStream fos = new FileOutputStream( mainFile );
        fos.write( MODULE_HEADER.getBytes() );
        //
        String content = MODULE_MAIN_PYTHON;
        content = content.replace( "{MODULE_NAME}", module.getName() );
        content = content.replace( "{MODULE_VERSION}" , module.getVersion() );
        content = content.replace( "{MODULE_AUTHOR}", stringClean( module.getAuthor() ) );
        content = content.replace( "{MODULE_WEBSITE}", stringClean( module.getWeb() ) );
        content = content.replace( "{MODULE_CATEGORY}", stringClean( module.getCategory() ) );
        content = content.replace( "{MODULE_DESCRIPTION}", module.getDescription() );
        if( module.getDepends().isEmpty() )
        {
            module.setDepends( "'base'" );
        }
        content = content.replace( "{MODULE_DEPENDS}", module.getDepends() );
        content = content.replace( "{MODULO_VIEW_FILE_NAME}", moduleName + "_view.xml" );
        content = content.replace( "{MODULE_INSTALLABLE}", module.isInstallable() ? "True" : "False" );
        fos.write( content.getBytes() );
        fos.close();
    }

    /**
     * Build the selection parameters for the attribute
     * @param attribute
     * @return 
     */
    private String getAttributeSelections( ModAttr attribute )
    {
        String selStr = "[";
        ArrayList<AttrSelection> selections = attribute.getSelections();
        for( int index = 0; index < selections.size(); index++ )
        {
            AttrSelection selection = selections.get( index );
            if( index > 0 )
            {
                selStr += ",";
            }
            selStr += "('" + stringClean( selection.getValue() ) + "','" + stringClean( selection.getName() ) + "')";
        }
        selStr += "]";
        return selStr;
    }
    
    /**
     * Build the optional parameters for the attribute
     * @param attribute 
     * @return 
     */
    private String getAttributeOptional( ModAttr attribute )
    {
        String optional = "";
        
        if( attribute.getSize() != 0 )
        {
            optional += ", size=" + attribute.getSize();
        }
        
        if( !attribute.getDescription().isEmpty() )
        {
            optional += ", help='" + stringClean( attribute.getDescription() ) + "'";
        }
        
        if( attribute.isTranslate() )
        {
            optional += ", translate=True";
        }
        
        if( attribute.isReadOnly() )
        {
            optional += ", readonly=True";
        }
        
        if( attribute.isRequired() )
        {
            optional += ", required=True";
        }
        
        if( !attribute.getString().isEmpty() )
        {
            optional += ", string='" + stringClean( attribute.getString() ) + "'";
        }

        return optional;
    }

    /**
     * Create the column definition for the module class
     * @param attribute
     * @return string 
     */
    private String getClassColumn( ModAttr attribute )
    {
        String content = MODULE_ATTRIBUTE[ attribute.getType() ];
        content = content.replace( "{MODULE_ATTRIBUTE_NAME}", attribute.getName() );
        content = content.replace( "{MODULE_ATTRIBUTE_FIELD_NAME}", stringClean( attribute.getFieldName() ) );
        //
        if( attribute.getType() == ModAttr.ATTR_TYPE_SELECTION )
        {
            content = content.replace( "{MODULE_ATTRIBUTE_SELECTIONS}", getAttributeSelections( attribute ) );
        }
        //
        content = content.replace( "{MODULE_ATTRIBUTE_OTHER_NAME}", attribute.getRelOthName() );
        content = content.replace( "{MODULE_ATTRIBUTE_RELATION}", attribute.getRelRelId() );
        content = content.replace( "{MODULE_ATTRIBUTE_OBJECT_ID}", attribute.getRelObjId() );
        content = content.replace( "{MODULE_ATTRIBUTE_OTHER_ID}", attribute.getRelOthId() );

        content = content.replace( "{MODULE_ATTRIBUTE_OPTIONAL}", getAttributeOptional( attribute ) );
        return "        " + content;
    }
    
    /**
     * Create the columns definitions for the module class
     * @param attributes
     * @return string
     */
    private String getClassColumns( ArrayList<ModAttr> attributes )
    {
        String columns = "";
        for( ModAttr modAttr : attributes )
        {
            columns += getClassColumn( modAttr );
        }
        return columns;
    }
    
    /**
     * Create the defaults definitions for the module attribute
     * @param attributes
     * @return string
     */
    private String getClassDefaults( ArrayList<ModAttr> attributes )
    {
        String defaults = "";
        for( ModAttr attribute : attributes )
        {
            if( !attribute.getInitVal().isEmpty() )
            {
                defaults += ( "        '" + attribute.getName() + "' : " );
                boolean isText = ( ( attribute.getType() == ModAttr.ATTR_TYPE_CHAR ) ||
                        ( attribute.getType() == ModAttr.ATTR_TYPE_TEXT ) );
                if( isText )
                {
                    defaults += "'";
                }
                defaults +=  stringClean( attribute.getInitVal() );
                if( isText )
                {
                    defaults += "'";
                }
                defaults +=  ",\n";
            }
        }
        return defaults;
    }
    
    /**
     * 
     * @param fos
     * @param modClass 
     */
    private void createClass( FileOutputStream fos, ModClass modClass ) throws Exception
    {
        String content = MODULE_CLASS;
        content = content.replace( "{MODULE_CLASS_NAME}", nameToId( modClass.getName() ) );
        content = content.replace( "{MODULE_CLASS_PROPERTY_DESCRIPTION}", modClass.getDescription() );
        content = content.replace( "{MODULE_CLASS_PROPERTY_NAME}", modClass.getName() );
        content = content.replace( "{MODULE_CLASS_PROPERTY_COLUMNS}", getClassColumns( modClass.getAttributes() ) );
        content = content.replace( "{MODULE_CLASS_PROPERTY_ORDER}", modClass.getOrder() );
        content = content.replace( "{MODULE_CLASS_PROPERTY_DEFAULTS}", getClassDefaults( modClass.getAttributes() ) );
        fos.write( content.getBytes() );
    }
    
    /**
     * Create the module file where the classes are
     * @throws Exception 
     */
    private void createModuleFile() throws Exception
    {
        File pyFile = new File( modPath + File.separatorChar + moduleName + ".py" );
        if( !pyFile.exists() )
        {
            pyFile.createNewFile();
        }
        FileOutputStream fos = new FileOutputStream( pyFile );
        fos.write( MODULE_HEADER.getBytes() );
        fos.write( "from osv import fields, osv\n\n".getBytes() );
        for( ModClass modClass : module.getClasses() )
        {
            createClass( fos, modClass );
        }
        fos.close();
    }
    
    /**
     * Generate the form section of the view file
     * @param modClass the class to do the form 
     * @return a string with the form
     */
    private String getClassViewForm( ModClass modClass )
    {
        String formStr = "";
        int select = 1;
        for( ModAttr attribute : modClass.getAttributes() )
        {
            switch( attribute.getType() )
            {
                case ModAttr.ATTR_TYPE_TEXT :
                case ModAttr.ATTR_TYPE_ONE2MANY :
                case ModAttr.ATTR_TYPE_MANY2MANY :
                    formStr += "          <separator string=\"" + attribute.getFieldName() + "\" colspan=\"4\"/>\n";
                    formStr += "          <field name=\"" + attribute.getName() + "\" colspan=\"4\" nolabel=\"1\" ";
                    break;
                default :
                    formStr += "          <field name=\"" + attribute.getName() + "\" ";
                    break;
            }
            formStr += "select=\"" + select + "\" />\n";
            if( select > 1 )
                select = 0;
            else if( select > 0 )
                select++;
        }
        return formStr;
    }

    /**
     * 
     * @param modClass
     * @return 
     */
    private String getClassViewTree( ModClass modClass )
    {
        String treeStr = "";
        ArrayList<ModAttr> attributes = modClass.getAttributes();
        for( ModAttr attribute : attributes )
        {
            treeStr += "          <field name=\"" + attribute.getName() + "\" />\n";
        }
        return treeStr;
    }

    /**
     * 
     * @param modClass
     * @return 
     */
    private String getClassViewMode( ModClass modClass )
    {
        String mode = "tree,form";
        for( ModAttr attribute : modClass.getAttributes() )
        {
            if( ( attribute.getType() == ModAttr.ATTR_TYPE_DATE ) ||
                ( attribute.getType() == ModAttr.ATTR_TYPE_DATETIME ) )
            {
                mode += ",calendar";
                break;
            }
        }
        return mode;
    }
    
    /**
     * 
     * @param fos
     * @param modClass
     * @throws Exception 
     */
    private void createClassView( FileOutputStream fos, ModClass modClass ) throws Exception
    {
        String[] menus =modClass.getMenuPath().split( "/" );
        if( ( menus.length == 0 ) || menus[ 0 ].isEmpty() )
        {
            throw new Exception( "The class '" + modClass.getName() + "'menu Path with errors." );
        }
        // Create the menu entries
        // Search for the root menu. If doesnt exist yet create it.
        MenuItem rootMenu = MenuItem.searchMenuItem( menus[ 0 ].toLowerCase() );
        if( rootMenu == null )
        {
            rootMenu = new MenuItem( menus[ 0 ] );
            MenuItem.appendMenuItem( rootMenu );
        }
        MenuItem parentMenu = rootMenu;
        MenuItem menuItem = null;
        for( int index = 1; index < menus.length; index++ )
        {
            menuItem = MenuItem.searchMenuItem( menus[ index ].toLowerCase() );
            if( menuItem == null )
            {
                // Check if its the last menu entry that indicate it is the action menu 
                if( index == ( menus.length - 1 ) ) // It is the action 
                {
                    menuItem = new MenuItem( menus[ index ], "", parentMenu.getId(), "action_" + nameToId( modClass.getName() ).toLowerCase() );
                    MenuItem.appendMenuItem( menuItem );
                }
                else
                {
                    menuItem = new MenuItem( menus[ index ], parentMenu.getId() );
                    MenuItem.appendMenuItem( menuItem );
                }
            }
            parentMenu = menuItem;
        }
        if( menuItem == null )
        {
            menuItem = rootMenu;
            menuItem.setAction( "action_" + nameToId( modClass.getName() ).toLowerCase() );
        }
        
        String content = MODULE_VIEW_CLASS;
        content = content.replace( "{MODULE_VIEW_CLASS_NAME_ID}", nameToId( modClass.getName() ) );
        content = content.replace( "{MODULE_VIEW_CLASS_NAME}", modClass.getName() );
        content = content.replace( "{MODULE_VIEW_MENU_NAME}", menuItem.getName() );
        content = content.replace( "{MODULE_VIEW_CLASS_FORM}", getClassViewForm( modClass ) );
        content = content.replace( "{MODULE_VIEW_CLASS_TREE}", getClassViewTree( modClass ) );
        content = content.replace( "{MODULE_VIEW_CLASS_MODE}", getClassViewMode( modClass ) );
        fos.write( content.getBytes() );
    }

    /***
     * 
     * @param fos 
     */
    private void createModuleViewMenuItems( FileOutputStream fos ) throws Exception
    {
        String menuStr = "    <!-- ==== Menu Items ===== -->\n";
        for( MenuItem menuItem : MenuItem.getMenuItems() )
        {
            menuStr += "    <menuitem name=\"" + menuItem.getName() + "\" id=\"" + 
                   menuItem.getId() + "\" ";
            if( !menuItem.getParent().isEmpty() )
            {
                menuStr += "parent=\"" + menuItem.getParent() + "\" ";
            }
            if( !menuItem.getAction().isEmpty() )
            {
                menuStr += "action=\"" + menuItem.getAction() + "\" ";
            }
            menuStr += "/>\n";
        }
        fos.write( menuStr.getBytes() );
    }
    
    /***
     * 
     * @throws Exception 
     */
    private void createModuleViewFile() throws Exception
    {
        File pyFile = new File( modPath + File.separatorChar + moduleName + "_view.xml" );
        if( !pyFile.exists() )
        {
            pyFile.createNewFile();
        }
        FileOutputStream fos = new FileOutputStream( pyFile );
        //
        fos.write( MODULE_VIEW_HEADER.getBytes() );
        //
        MenuItem.clearMenuItems();
        for( ModClass modClass : module.getClasses() )
        {
            createClassView( fos, modClass );
        }
        createModuleViewMenuItems( fos );
        //
        fos.write( MODULE_VIEW_TAIL.getBytes() );
        //
        fos.close();
    }

    /**
     * 
     * @param fos
     * @param modClass
     * @throws Exception 
     */
    private void createClassSecurity( FileOutputStream fos, ModClass modClass ) throws Exception
    {
        String content = MODULE_SECURITY_CLASS;
        content = content.replace( "{MODULE_VIEW_CLASS_NAME_ID}", nameToId( modClass.getName() ) );
        content = content.replace( "{MODULE_VIEW_CLASS_NAME}", modClass.getName() );
        fos.write( content.getBytes() );
    }
    
    /***
     * 
     * @throws Exception 
     */
    private void createModuleSecurityFile() throws Exception
    {
        File pyFile = new File( modPath + File.separatorChar + MODULE_MAIN_SECURITY_FILENAME );
        if( !pyFile.exists() )
        {
            pyFile.createNewFile();
        }
        FileOutputStream fos = new FileOutputStream( pyFile );
        //
        fos.write( MODULE_SECURITY_HEADER.getBytes() );
        for( ModClass modClass : module.getClasses() )
        {
            createClassSecurity( fos, modClass );
        }
        //
        fos.close();
    }

    /**
     * Add folder recursivelly to the zip stream
     * @param zos the zip stream
     * @param srcFile  the source 
     */
    private void addZipEntry( ZipOutputStream zos, File srcFile, String zipPath ) throws Exception
    {
        System.out.println( "Zipping file '" + zipPath + srcFile.getName() + "'" );
        // if the file is directory, use recursion
        if( srcFile.isDirectory() ) 
        {
            zipPath += srcFile.getName() + "/";
            zos.putNextEntry( new ZipEntry( zipPath ) );
            for( File file : srcFile.listFiles() ) 
            {
                addZipEntry( zos, file, zipPath );
            }
        }
        else
        {
            // create byte buffer
            byte[] buffer = new byte[ 1024 ];

            FileInputStream fis = new FileInputStream( srcFile );

            zos.putNextEntry( new ZipEntry( zipPath + srcFile.getName() ) );

            int length;
            while( ( length = fis.read( buffer ) ) > 0 ) 
            {
                zos.write( buffer, 0, length );
            }

            zos.closeEntry();

            // close the InputStream
            fis.close();
        }
   }    

    /**
     * Create the output file from the temporary folder
     * @throws Exception 
     */
    private void createZipFile() throws Exception
    {
        //create byte buffer
        byte[] buffer = new byte[1024];

        File outputFile = new File( outputName );
        if( outputFile.exists() )
        {
            if( !outputFile.delete() )
            {
                throw new Exception( "Could not overwirte the file " + outputName );
            }
        }
        //create object of FileOutputStream
        FileOutputStream fos = new FileOutputStream( outputFile );

        //create object of ZipOutputStream from FileOutputStream
        ZipOutputStream zos = new ZipOutputStream( fos );

        //create File object from directory name
        File dir = new File( modPath );
        addZipEntry( zos, dir, "" );
        
        //close the ZipOutputStream
        zos.close();
    }
    
    /**
     * 
     * @param filename 
     */
    public boolean generate( String filename )
    {
        setOutputName( filename );
        try {
            // Create the temporary folder
            if( createTempFolder() )
            {
                // Create a folder with the name of the module inside the temporary folder
                if( createModuleFolders() )
                {
                    // Create the python main file
                    createMainPythonFile();

                    // Create the main module file
                    createMainModuleFile();

                    // Create the module python file
                    createModuleFile();

                    // Create the module view file
                    createModuleViewFile();

                    // Create the module security file
                    createModuleSecurityFile();
                    
                    // Create the zip file
                    createZipFile();

                    // delete the temporary folder
                    deleteTmpPath();
                    
                    return true;
                }
                else
                {
                    JOptionPane.showMessageDialog( null, "Could not create the module folder", "Error", JOptionPane.ERROR_MESSAGE );
                }
            }
            else
            {
                JOptionPane.showMessageDialog( null, "Could not create a temporary folder", "Error", JOptionPane.ERROR_MESSAGE );
            }
        }
        catch( Exception e )
        {
            JOptionPane.showMessageDialog( null, e.toString(), "Excepción", JOptionPane.ERROR_MESSAGE );
        }
        return false;
    }

    /**
     * @return the outputName
     */
    public String getOutputName()
    {
        return outputName;
    }

    /**
     * @param outputName the outputName to set
     */
    public void setOutputName( String outputName )
    {
        this.outputName = outputName;
    }

    /**
     * 
     * @param file
     * @throws Exception 
     */
    private void deleteFolder( File file ) throws Exception
    {
        if( file.isDirectory() ) 
        {
            for( File f : file.listFiles() )
                deleteFolder( f );
        }
        if( !file.delete() )
            throw new Exception( "Failed to delete file: " + file );
    }
       
    /**
     * 
     * @throws Exception 
     */
    private void deleteTmpPath() throws Exception 
    {
        deleteFolder( new File( tmpPath ) );
    }
}
