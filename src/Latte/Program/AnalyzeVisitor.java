package Latte.Program;

import Latte.Absyn.*;


public class AnalyzeVisitor {

    private static String MAIN = "main";

    private boolean correctness = true;

    public void checkCorrectness(){
        if(!correctness)
            System.err.println("line: " + "wrong definition of 'main' function");
    }

    public boolean isCorrectness() {
        return correctness;
    }

    public void setCorrectness(boolean correctness) {
        this.correctness = correctness;
    }

    public void compile(Program parse_tree) {

        parse_tree.accept(new ProgramVisitor(), null);

    }

    public class ProgramVisitor implements Program.Visitor<String, BaseTabSymb> {
        public String visit(Latte.Absyn.Prog p, BaseTabSymb arg) {
      /* Code For Prog Goes Here */

            BaseTabSymb tabSymb = new BaseTabSymb();
            for (TopDef x : p.listtopdef_) {
                x.accept(new TopDefVisitor(),tabSymb);
            }
            if(tabSymb.getMainFun() == null){
                tabSymb.setMainFun(new MainFunction("MAIN"));
            }

            correctness = tabSymb.getMainFun().checkIsOkay();

            return null;
        }

    }

    public class TopDefVisitor implements TopDef.Visitor<String, BaseTabSymb> {
        public String visit(Latte.Absyn.FnDef p, BaseTabSymb arg) {
      /* Code For FnDef Goes Here */

            p.type_.accept(new TypeVisitor(), arg);
            //p.ident_;
            if(p.type_ instanceof Int && p.ident_.equals(MAIN) && (p.listarg_.size() == 0)){
                if(arg.getMainFun() == null){
                    MainFunction mainFun = new MainFunction(MAIN);
                    arg.setMainFun(mainFun);
                    mainFun.setReturnType(true);
                } else{
                    correctness = false;
                }
            }

            for (Arg x : p.listarg_) {
            }
            p.block_.accept(new BlockVisitor(), arg);

            return null;
        }

    }

    public class ArgVisitor implements Arg.Visitor<String, BaseTabSymb> {
        public String visit(Latte.Absyn.Ar p, BaseTabSymb arg) {
      /* Code For Ar Goes Here */

            p.type_.accept(new TypeVisitor(), arg);
            //p.ident_;

            return null;
        }

    }

    public class BlockVisitor implements Block.Visitor<String, BaseTabSymb> {
        public String visit(Latte.Absyn.Blk p, BaseTabSymb arg) {
      /* Code For Blk Goes Here */
            int size = p.liststmt_.size();
            if(arg.getMainFun() != null){
                MainFunction mainFunc = arg.getMainFun();

                if(p.liststmt_.get(size - 1) instanceof Ret){
                    mainFunc.setReturnStatement(true);

                }

            }
            for (Stmt x : p.liststmt_) {
                x.accept(new StmtVisitor(), arg);

                // check if multiple return expressions
                if(arg.getMainFun() != null){
                    MainFunction mainFunc = arg.getMainFun();
                    if(x instanceof Ret && (x != p.liststmt_.get(size - 1))){
                        mainFunc.setReturnStatement(false);
                    }
                }
            }

            return null;
        }

    }

    public class StmtVisitor implements Stmt.Visitor<String, BaseTabSymb> {
        public String visit(Latte.Absyn.Empty p, BaseTabSymb arg) {
      /* Code For Empty Goes Here */


            return null;
        }

        public String visit(Latte.Absyn.BStmt p, BaseTabSymb arg) {
      /* Code For BStmt Goes Here */

            p.block_.accept(new BlockVisitor(), arg);

            return null;
        }

        public String visit(Latte.Absyn.Decl p, BaseTabSymb arg) {
      /* Code For Decl Goes Here */

            p.type_.accept(new TypeVisitor(), arg);
            for (Item x : p.listitem_) {
            }

            return null;
        }

        public String visit(Latte.Absyn.Ass p, BaseTabSymb arg) {
      /* Code For Ass Goes Here */

            //p.ident_;
            p.expr_.accept(new ExprVisitor(), arg);

            return null;
        }

        public String visit(Latte.Absyn.Incr p, BaseTabSymb arg) {
      /* Code For Incr Goes Here */

            //p.ident_;

            return null;
        }

        public String visit(Latte.Absyn.Decr p, BaseTabSymb arg) {
      /* Code For Decr Goes Here */

            //p.ident_;

            return null;
        }

        public String visit(Latte.Absyn.Ret p, BaseTabSymb arg) {
      /* Code For Ret Goes Here */

            p.expr_.accept(new ExprVisitor(), arg);

            return null;
        }

        public String visit(Latte.Absyn.VRet p, BaseTabSymb arg) {
      /* Code For VRet Goes Here */


            return null;
        }

        public String visit(Latte.Absyn.Cond p, BaseTabSymb arg) {
      /* Code For Cond Goes Here */

            p.expr_.accept(new ExprVisitor(), arg);
            p.stmt_.accept(new StmtVisitor(), arg);

            return null;
        }

        public String visit(Latte.Absyn.CondElse p, BaseTabSymb arg) {
      /* Code For CondElse Goes Here */

            p.expr_.accept(new ExprVisitor(), arg);
            p.stmt_1.accept(new StmtVisitor(), arg);
            p.stmt_2.accept(new StmtVisitor(), arg);

            return null;
        }

        public String visit(Latte.Absyn.While p, BaseTabSymb arg) {
      /* Code For While Goes Here */

            p.expr_.accept(new ExprVisitor(), arg);
            p.stmt_.accept(new StmtVisitor(), arg);

            return null;
        }

        public String visit(Latte.Absyn.SExp p, BaseTabSymb arg) {
      /* Code For SExp Goes Here */

            p.expr_.accept(new ExprVisitor(), arg);

            return null;
        }

    }

    public class ItemVisitor implements Item.Visitor<String, BaseTabSymb> {
        public String visit(Latte.Absyn.NoInit p, BaseTabSymb arg) {
      /* Code For NoInit Goes Here */

            //p.ident_;

            return null;
        }

        public String visit(Latte.Absyn.Init p, BaseTabSymb arg) {
      /* Code For Init Goes Here */

            //p.ident_;
            p.expr_.accept(new ExprVisitor(), arg);

            return null;
        }

    }

    public class TypeVisitor implements Type.Visitor<String, BaseTabSymb> {
        public String visit(Latte.Absyn.Int p, BaseTabSymb arg) {
      /* Code For Int Goes Here */


            return null;
        }

        public String visit(Latte.Absyn.Str p, BaseTabSymb arg) {
      /* Code For Str Goes Here */


            return null;
        }

        public String visit(Latte.Absyn.Bool p, BaseTabSymb arg) {
      /* Code For Bool Goes Here */


            return null;
        }

        public String visit(Latte.Absyn.Void p, BaseTabSymb arg) {
      /* Code For Void Goes Here */


            return null;
        }

        @Override
        public String visit(Fun p, BaseTabSymb arg) {
            return null;
        }

    }

    public class ExprVisitor implements Expr.Visitor<String, BaseTabSymb> {
        public String visit(Latte.Absyn.EVar p, BaseTabSymb arg) {
      /* Code For EVar Goes Here */

            //p.ident_;

            return null;
        }

        public String visit(Latte.Absyn.ELitInt p, BaseTabSymb arg) {
      /* Code For ELitInt Goes Here */

            //p.integer_;
            MainFunction mainFunc = arg.getMainFun();
            if(mainFunc != null && mainFunc.isReturnStatement()){
                mainFunc.setReturnValue(p.integer_);
            }

            return null;
        }

        public String visit(Latte.Absyn.ELitTrue p, BaseTabSymb arg) {
      /* Code For ELitTrue Goes Here */


            return null;
        }

        public String visit(Latte.Absyn.ELitFalse p, BaseTabSymb arg) {
      /* Code For ELitFalse Goes Here */


            return null;
        }

        public String visit(Latte.Absyn.EApp p, BaseTabSymb arg) {
      /* Code For EApp Goes Here */

            //p.ident_;
            for (Expr x : p.listexpr_) {
            }

            return null;
        }

        public String visit(Latte.Absyn.EString p, BaseTabSymb arg) {
      /* Code For EString Goes Here */

            //p.string_;

            return null;
        }

        public String visit(Latte.Absyn.Neg p, BaseTabSymb arg) {
      /* Code For Neg Goes Here */

            p.expr_.accept(new ExprVisitor(), arg);

            return null;
        }

        public String visit(Latte.Absyn.Not p, BaseTabSymb arg) {
      /* Code For Not Goes Here */

            p.expr_.accept(new ExprVisitor(), arg);

            return null;
        }

        public String visit(Latte.Absyn.EMul p, BaseTabSymb arg) {
      /* Code For EMul Goes Here */

            p.expr_1.accept(new ExprVisitor(), arg);
            p.mulop_.accept(new MulOpVisitor(), arg);
            p.expr_2.accept(new ExprVisitor(), arg);

            return null;
        }

        public String visit(Latte.Absyn.EAdd p, BaseTabSymb arg) {
      /* Code For EAdd Goes Here */

            p.expr_1.accept(new ExprVisitor(), arg);
            p.addop_.accept(new AddOpVisitor(), arg);
            p.expr_2.accept(new ExprVisitor(), arg);

            return null;
        }

        public String visit(Latte.Absyn.ERel p, BaseTabSymb arg) {
      /* Code For ERel Goes Here */

            p.expr_1.accept(new ExprVisitor(), arg);
            p.relop_.accept(new RelOpVisitor(), arg);
            p.expr_2.accept(new ExprVisitor(), arg);

            return null;
        }

        public String visit(Latte.Absyn.EAnd p, BaseTabSymb arg) {
      /* Code For EAnd Goes Here */

            p.expr_1.accept(new ExprVisitor(), arg);
            p.expr_2.accept(new ExprVisitor(), arg);

            return null;
        }

        public String visit(Latte.Absyn.EOr p, BaseTabSymb arg) {
      /* Code For EOr Goes Here */

            p.expr_1.accept(new ExprVisitor(), arg);
            p.expr_2.accept(new ExprVisitor(), arg);

            return null;
        }

    }

    public class AddOpVisitor implements AddOp.Visitor<String, BaseTabSymb> {
        public String visit(Latte.Absyn.Plus p, BaseTabSymb arg) {
      /* Code For Plus Goes Here */


            return null;
        }

        public String visit(Latte.Absyn.Minus p, BaseTabSymb arg) {
      /* Code For Minus Goes Here */


            return null;
        }

    }

    public class MulOpVisitor implements MulOp.Visitor<String, BaseTabSymb> {
        public String visit(Latte.Absyn.Times p, BaseTabSymb arg) {
      /* Code For Times Goes Here */


            return null;
        }

        public String visit(Latte.Absyn.Div p, BaseTabSymb arg) {
      /* Code For Div Goes Here */


            return null;
        }

        public String visit(Latte.Absyn.Mod p, BaseTabSymb arg) {
      /* Code For Mod Goes Here */


            return null;
        }

    }

    public class RelOpVisitor implements RelOp.Visitor<String, BaseTabSymb> {
        public String visit(Latte.Absyn.LTH p, BaseTabSymb arg) {
      /* Code For LTH Goes Here */


            return null;
        }

        public String visit(Latte.Absyn.LE p, BaseTabSymb arg) {
      /* Code For LE Goes Here */


            return null;
        }

        public String visit(Latte.Absyn.GTH p, BaseTabSymb arg) {
      /* Code For GTH Goes Here */


            return null;
        }

        public String visit(Latte.Absyn.GE p, BaseTabSymb arg) {
      /* Code For GE Goes Here */


            return null;
        }

        public String visit(Latte.Absyn.EQU p, BaseTabSymb arg) {
      /* Code For EQU Goes Here */


            return null;
        }

        public String visit(Latte.Absyn.NE p, BaseTabSymb arg) {
      /* Code For NE Goes Here */


            return null;
        }

    }
}