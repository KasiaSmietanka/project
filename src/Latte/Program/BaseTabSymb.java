package Latte.Program;

import build.tools.javazic.Main;

import java.util.HashMap;

public class BaseTabSymb {
    // przechowywanie naglówków fukcji
    // sprawdzanie czy zmienne nie zostały ponownie zdefiniowane w tym samym bloku
    // unikalne nazwy funkcji
    // zwracany typ funkcji a wartosc wyrazenia - 2 Visitor

    //=========================================================================================================


    //=========================================================================================================
    // do sprawdzenia istnienia funkcji main z int - typ, return 0, i nazwa main; nie moze miec zadnych parametrow
    private MainFunction mainFun = null;

    public MainFunction getMainFun() {
        return mainFun;
    }

    public void setMainFun(MainFunction mainFun) {
        this.mainFun = mainFun;
    }
}
