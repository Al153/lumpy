package Compiler.Parser.Expression;

import Compiler.Datatype;
import Compiler.Parser.Parser;

/**
 * Eine 2 Byte Ganzzahl
 * @author Coolo
 */
public abstract class ExpressionShort extends Expression {
    private short number;

    /**
     * Konstruktor
     * @param p
     * @param num
     */
    public ExpressionShort(Parser p, short num) {
        super(p,new Datatype(Datatype.SHORT_DATATYPE,0,null,null));

        this.number = num;
    }



    /**
     * Die Nummer/der Wert der gehalten wird
     * @return
     */
    public short getNumber() {
        return number;
    }
}