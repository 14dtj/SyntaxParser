package syntax;

/**
 * Created by tj on 2016/11/2.
 */
public class CFGHelper {

    public static final String[] cfgs = {
            "S->id=E;",
            "S->if(C){S}else{S}",
            "S->while(C){S}",
            "E->TE'",
            "E'->+TE'",
            "E'->ε",
            "T->FT'",
            "T'->*FT'",
            "T'->ε",
            "F->(E)",
            "F->num",
            "F->id",
            "C->DC'",
            "C'->||DC'",
            "C'->ε",
            "D->(C)",
            "D->id==num"
    };

    public static final String[][] splitCfgs = {
            {";", "E", "=", "id"},
            {"}", "S", "{", "else", "}", "S", "{", ")", "C", "(", "if"},
            {"}", "S", "{", ")", "C", "(", "while"},
            {"E'", "T"},
            {"E'", "T", "+"},
            {"-1"},
            {"T'", "F"},
            {"T'", "F", "*"},
            {"-1"},
            {")", "E", "("},
            {"num"},
            {"id"},
            {"C'", "D"},
            {"C'", "D", "||"},
            {"-1"},
            {")", "C", "("},
            {"num", "==", "id"}
    };

    public static final int[][] parseTable = {
            //   id	=	;	if	(	)	{	}	e	w	+	*	n	||	==	$
            {0, -1, -1, 1, -1, -1, -1, -1, -1, 2, -1, -1, -1, -1, -1, -1},//S
            {3, -1, -1, -1, 3, -1, -1, -1, -1, -1, -1, -1, 3, -1, -1, -1},//E
            {-1, -1, 5, -1, -1, 5, -1, -1, -1, -1, 4, -1, -1, -1, -1, 5},//E'
            {6, -1, -1, -1, 6, -1, -1, -1, -1, -1, -1, -1, 6, -1, -1, -1},//T
            {-1, -1, 8, -1, -1, 8, -1, -1, -1, -1, 8, 7, -1, -1, -1, 8},//T'
            {11, -1, -1, -1, 9, -1, -1, -1, -1, -1, -1, -1, 10, -1, -1, -1},//F
            {12, -1, -1, -1, 12, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},//C
            {-1, -1, -1, -1, -1, 14, -1, -1, -1, -1, -1, -1, -1, 13, -1, 14},//C'
            {16, -1, -1, -1, 15, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}//D
    };

    public static final String[] terminals = {"id", "=", ";", "if", "(", ")", "{", "}",
            "else", "while", "+", "*", "num", "||", "==", "$"};

    public static final String[] n_terminals = {"S", "E", "E'", "T", "T'", "F", "C", "C'", "D"};

    public static int getCfg(String stackStr, String inputStr) {
        int rowIndex = find(n_terminals, stackStr);
        int colIndex = find(terminals, inputStr);
        if ((rowIndex != -1) && (colIndex != -1)) {
            int index = parseTable[rowIndex][colIndex];
            return index;
        }
        return -1;
    }

    private static int find(String[] list, String value) {
        for (int i = 0; i < list.length; i++) {
            if (list[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    public static boolean isTerminal(String value) {
        for (String str : terminals) {
            if (str.equals(value)) {
                return true;
            }
        }
        return false;
    }
}
