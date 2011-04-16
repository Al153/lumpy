package Compiler;

import Compiler.Parser.Expression.ExpressionDeclaration;
import Compiler.Parser.Parser;
import java.util.*;

/**
 * Eine Klasse. Mit diesem Konstrukt wird OOP in Lumpy implementiert.
 * @author Coolo
 */
public class Class extends Datatype {
    public static final int IS_CLASS = 0;
    public static final int IS_EXTENSION = 1;
    public static final int IS_STRUCT = 2;
    public static int classID = 1000;
    private LinkedList<Variable> attribute;
    private LinkedList<Function> methods;
    private LinkedList<Property> propertys;
    private LinkedList<ExpressionDeclaration> startDecs;
    private LinkedList<Class> inheriting;
    private Token startToken;
    private HashMap<String, String> replacer;
    private Scope scope;
    private Class inherit;
    private Parser parser; //in welchem parser wurde es definiert
    private int typ = 0;


    /**
     * Erstellt eine im programm definierte Klasse
     * @param name
     */
    public Class(String name, Token tok, Parser p) {
        super(classID++,0,null);
        this.attribute = new LinkedList<Variable>();
        this.methods = new LinkedList<Function>();
        this.startDecs = new LinkedList<ExpressionDeclaration>();
        this.propertys = new LinkedList<Property>();
        this.inheriting = new LinkedList<Class>();
        this.name = name;
        this.startToken = tok;
        this.parser = p;
    }

    /**
     * Neues Property
     * @param prp
     */
    public void newProperty(Property prp) {
        propertys.add(prp);
        
    }

    /**
     * Neues Attribut
     * @param isPublic
     * @param var
     */
    public void newAttribute(boolean isPublic, Variable var) {
        attribute.add(var);
        var.setPublic(isPublic);
    }
    /**
     * Neue Methode
     * @param isPublic
     * @param func
     */
    public void newMethod(boolean isPublic, Function func,Variable thisVar, Variable superVar) throws SyntaxException {
        methods.add(func);
        func.setPublic(isPublic);
        func.notCallable();

        if (func instanceof CodeFunction) {
            CodeFunction cfunc = (CodeFunction)func;

            if (cfunc.getName().equals("new")) {
                cfunc.setName("new");
                cfunc.setTyp(CodeFunction.IS_CONSTRUCT);
            } else {
                cfunc.setTyp(CodeFunction.IS_METHOD);
            }

            cfunc.getScope().setClass(this);
            cfunc.setSynonym(getName()+"_"+cfunc.getName());

            
            cfunc.getScope().newVariable(thisVar);

            
            if (this.getInherit() != null) cfunc.getScope().newVariable(superVar);

            
        }
    }
    /**
     * Gibt alle Propertys zurück
     */
     public LinkedList<Property> getPropertys() {
         return propertys;
     }


    /**
     * Gibt alle Methoden zurück
     */
    public LinkedList<Function> getMethods() {
        if (inherit != null) {
            LinkedList<Function> list = new LinkedList<Function>(methods);
            list.addAll(inherit.getMethods());
            return list;
        } else {
            return methods;
        }
    }
    /**
     * Gibt alle Attribute zurück
     */
    public LinkedList<Variable> getAttibutes() {
        if (inherit != null) {
            LinkedList<Variable> list = new LinkedList<Variable>();
            list.addAll(inherit.getAttibutes());
            list.addAll(attribute);
            return list;
        } else {
            return attribute;
        }
    }
    /**
     * Gibt die direkt deklarierten attribute zurücl
     */
     public LinkedList<Variable> getRawAttributes() {
         return attribute;
     }
    /**
     * Setzt den Klassenscope
     */
    public void setScope(Scope s) {
        this.scope = s;
    }
    /**
     * Gibt den Scope zurück
     */
    public Scope getScope() {
        return scope;
    }
    /**
     * Gibt die Liste mit den startdecs zurück
     */
    public LinkedList<ExpressionDeclaration> getStartDecs() {
        return startDecs;
    }

    /**
     * Vererbt von der Klasse
     */
    public void inherits(Class c) {
        c.inheriting.add(this);
        this.inherit = c;
    }

    /**
     * die vererbende klasse
     */
    public Class getInherit() {
            return inherit;
    }
    /**
     * Welce Klassen von dieser Klasse erben
     */
    public LinkedList<Class> getInheriting() {
        return inheriting;
    }

    /**
     * Gibt den starttoken zurück
     */
    public Token getStartToken() {
        return startToken;
    }
    /**
     * Setzt den Replacer
     */
    public void setReplacer(HashMap<String, String> rep) {
        this.replacer = rep;
    }

    /**
     * gibt die replacer
     */
    public HashMap<String,String> getReplacer() {
        return replacer;
    }
    /**
     * Gibt den Parser zurück, wos deklariert wurde
     */
    public Parser getParser() {
        return parser;
    }

    /**
     * setzt den Typ
     */
    public void setTyp(int t) {
        this.typ = t;
    }

    /**
     * gibt den typ zurück
     */
    public int getTyp() {
        return typ;
    }
}
