package Latte.Program;

import build.tools.javazic.Main;

/**
 * Created by kasia on 20.12.14.
 */
public class MainFunction{

    private int returnValue = -1; // 0
    private boolean returnStatement = false; //return 0;
    private boolean returnType = false; // int
    private String funName = "MAIN";

    public MainFunction(String funName){
        this.funName = funName;
    }
    public int getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(int returnValue) {
        this.returnValue = returnValue;
    }

    public boolean isReturnStatement() {
        return returnStatement;
    }

    public void setReturnStatement(boolean returnStatement) {
        this.returnStatement = returnStatement;
    }

    public boolean isReturnType() {
        return returnType;
    }

    public void setReturnType(boolean returnType) {
        this.returnType = returnType;
    }
    // undefined reference to `main' ;int m()

    public boolean checkIsOkay(){
        if(returnValue == 0 && returnStatement && returnType && funName.equals("main"))
            return true;
        return false;
    }
}
