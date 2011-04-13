package Compiler.Parser.Expression;

import Compiler.*;
import Compiler.Class;
import Compiler.Parser.Expression.CExpression.*;
import java.util.LinkedList;

/**
 * Managt alle Expressions für die Sprache C
 * @author Coolo
 */
public class ExpressionManagerC extends ExpressionManager {
    /**
     * Gibt eine block Expression
     * @return
     */
    public ExpressionBlock getBlockExpression(boolean gen) {
        return new CExpressionBlock(getParser(),gen);
    }
    /**
     * Gibt eine Operator Expression
     * @param op der operator
     * @param left linker operand
     * @param right rechter operand
     * @return
     */
    public ExpressionOperator getOperatorExpression(Operator op, Expression left, Expression right) throws SyntaxException {
        return new CExpressionOperator(getParser(), op, left,right);
    }
    /**
     * Erstellt eine Zahl Expression in c
     */
    public ExpressionInteger getIntegerExpression(int num) {
        return new CExpressionInteger(getParser(),num);
    }
    /**
     * Erstellt eine Zahl Expression in c
     */
    public ExpressionByte getByteExpression(byte num) {
        return new CExpressionByte(getParser(),num);
    }
    /**
     * Erstellt eine Zahl Expression in c
     */
    public ExpressionShort getShortExpression(short num) {
        return new CExpressionShort(getParser(),num);
    }
    /**
     * Erstellt eine Zahl Expression in c
     */
    public ExpressionLong getLongExpression(long num) {
        return new CExpressionLong(getParser(),num);
    }
    /**
     * Erstellt eine Zahl Expression in c
     */
    public ExpressionFloat getFloatExpression(float num) {
        return new CExpressionFloat(getParser(),num);
    }
    /**
     * Erstellt eine Zahl Expression in c
     */
    public ExpressionDouble getDoubleExpression(double num) {
        return new CExpressionDouble(getParser(),num);
    }
    /**
     * Erstellt eine Zahl Expression in c
     */
    public ExpressionString getStringExpression(String num) {
        return new CExpressionString(getParser(),num);
    }
    /**
     * Erstellt eine Zahl Expression in c
     */
    public ExpressionBoolean getBooleanExpression(boolean num) {
        return new CExpressionBoolean(getParser(),num);
    }
    /**
     * Erstellt einen c cast
     */
    public ExpressionCast getCastExpression(Expression expr, Datatype from, Datatype to) {
        return new CExpressionCast(getParser(),expr,from,to);
    }
    /**
     * Erstellt eine C If
     */
    public ExpressionIf getIfExpression(Expression fork, ExpressionBlock block, ExpressionBlock elseBlock, LinkedList<ExpressionIf> elseifs) {
        return new CExpressionIf(getParser(),fork,block,elseBlock, elseifs);
    }
    /**
     * Funktionsaufruf in C => erstellt
     */
    public ExpressionFunctionCall getFunctionCallExpression(Function func, LinkedList<Expression> parameters, Class c) {
        return new CExpressionFunctionCall(getParser(), func, parameters, c);
    }
    /**
     * Erstellt eine C deklaration
     */
    public ExpressionDeclaration getDeclarationExpression(LinkedList<Variable> decVar, LinkedList<Expression> values,boolean s) throws SyntaxException {
        return new CExpressionDeclaration(getParser(),decVar, values,s);
    }
    /**
     * Eine Variabelnzuweisung
     */
    public ExpressionAssignment getAssignmentExpression(Variable var,Expression get, Expression expr) throws SyntaxException {
        return new CExpressionAssignment(getParser(),var,get, expr);
    }
    /**
     * Variablenzugriff
     */
    public ExpressionVariable getVariableExpression(Variable var) throws SyntaxException {
        return new CExpressionVariable(getParser(), var);
    }
    /**
     * Funktionsblock
     */
    public ExpressionFunctionDeclaration getFunctionDeclarationExpression(ExpressionBlock block, CodeFunction func) {
        return new CExpressionFunctionDeclaration(getParser(),block,func);
    }
    /**
     * return
     */
    public ExpressionReturn getReturnExpression(Expression expr) {
        return new CExpressionReturn(getParser(), expr);
    }
    /**
     * new
     */
    public ExpressionNew getNewExpression(Datatype data) {
        return new CExpressionNew(getParser(), data);
    }
    public ExpressionNew getNewExpression(Datatype data, LinkedList<Expression> expr) {
        return new CExpressionNew(getParser(),data,expr);
    }
    /**
     * Access in C
     */
    public ExpressionAccess getAccessExpression(ExpressionIdentifier left, ExpressionIdentifier right) throws SyntaxException {
        return new CExpressionAccess(getParser(), left,right);
    }
    /**
     * Attay Zugri9ff
     */
    public ExpressionArray getArrayExpression(ExpressionIdentifier var, LinkedList<Expression> arrs) throws SyntaxException {
        return new CExpressionArray(getParser(), var, arrs);
    }
    /**
     * Anonyme FUnktion
     */
    public ExpressionAnonymousFunc getAnonymousFuncExpression(CodeFunction func) {
        return new CExpressionAnonymousFunc(getParser(), func);
    }
    /**
     * anonymer funktionsaufruf
     */
    public ExpressionAnonymousFuncCall getAnonymousFuncCallExpression(Datatype data, Expression name, LinkedList<Expression> expr) {
        return new CExpressionAnonymousFuncCall(getParser(), data, name, expr);
    }
    /**
     * Methode
     */
    public ExpressionMethod getMethodExpression(Datatype data, ExpressionIdentifier name, Class c) {
        return new CExpressionMethod(getParser(), data, name, c);
    }
    /**
     * Null
     */
    public ExpressionNull getNullExpression() {
        return new CExpressionNull(getParser());
    }
    /**
     * while in c
     */
    public ExpressionWhile getWhileExpression(Expression cond, ExpressionBlock block) {
        return new CExpressionWhile(getParser(),cond,block);
    }
    /**
     * for in c
     */
     public ExpressionFor getForExpression(Expression init, Expression cond, Expression increment, ExpressionBlock block) {
         return new CExpressionFor(getParser(), init, cond, increment, block);
     }
     /**
      * simples for
      */
     public ExpressionSimpleFor getSimpleForExpression(ExpressionIdentifier var,Expression init, Expression cond,Expression increment, ExpressionBlock block) {
         return new CExpressionSimpleFor(getParser(),var, init, cond, increment, block);
     }

     /**
      * Yield return
      */
      public ExpressionYield getYieldExpression(Expression expr) {
          return new CExpressionYield(getParser(),expr);
      }

      /**
       * yield break
       */
      public ExpressionYield getYieldExpression() {
          return new CExpressionYield(getParser());
      }

      /**
       * loopevent
       */
    public ExpressionLoopEvent getLoopEventExpression(boolean isContinue) {
          return new CExpressionLoopEvent(getParser(), isContinue);
      }
      /**
       * select
       */
    public ExpressionSelect getSelectExpression(Expression start, LinkedList<Expression> conds,LinkedList<ExpressionBlock> blocks,ExpressionBlock def) {
        return new CExpressionSelect(getParser(), start, conds, blocks, def);
    }
    /**
     * each in
     */
    public ExpressionEachIn getEachInExpression(Class c, ExpressionBlock block, ExpressionIdentifier var, ExpressionIdentifier in,Expression init) {
        return new CExpressionEachIn(getParser(),c, block, var, in,init);
    }

    /**
     * property
     */
    public ExpressionProperty getPropertyExpression(Property prop,ExpressionIdentifier self) {
        return new CExpressionProperty(getParser(),prop,self);
    }
 }