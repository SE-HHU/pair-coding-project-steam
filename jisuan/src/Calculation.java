import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;
import java.lang.String;


class CommonFactor {
    public int getCommonfactor(int x,int y) //y > x
    {
        for(int i = x ;i >=1 ;i--)
        {
            if(x % i == 0 && y % i == 0)
                return i;
        }
        return 1;
    }

    public Fraction RemoveCommonfactor(Fraction unit1){
        int commonfactor = getCommonfactor(unit1.molecule,unit1.denominator);
        unit1.denominator /= commonfactor;
        unit1.molecule /= commonfactor;
        return unit1;
    }
}

class Answer extends CommonFactor{
    public Fraction getAnswer(Fraction unit1, Fraction unit2,int operator)
    {
        Fraction fr = new Fraction(1,-5);
        if(unit1.denominator == -5 || unit2.denominator == -5)
        {
            return fr;
        }
        if(operator == 3 && ((unit1.molecule * unit2.denominator) > (unit2.molecule * unit1.denominator))  ||
                operator == 1 && (unit1.molecule * unit2.denominator < (unit2.molecule * unit1.denominator)))
        {
            return fr;
        }
        if(operator == 0)
        {
            fr.molecule= (unit1.molecule * unit2.denominator + unit2.molecule * unit1.denominator);
            fr.denominator = (unit1.denominator * unit2.denominator);
            return RemoveCommonfactor(fr);
        }
        else if(operator == 1)
        {
            fr.denominator = (unit1.denominator * unit2.denominator);
            fr.molecule= (unit1.molecule * unit2.denominator - unit2.molecule * unit1.denominator);
            return RemoveCommonfactor(fr);
        }
        else if(operator == 2)
        {
            fr.molecule = (unit1.molecule * unit2.molecule);
            fr.denominator = (unit1.denominator * unit2.denominator);
            return RemoveCommonfactor(fr);
        }else
        {
            int swap = unit2.molecule;
            unit2.molecule = unit2.denominator;
            unit2.denominator = swap;
            return getAnswer(unit1,unit2,2);
        }
    }

    public static void main(String[] args) {
        Answer an = new Answer();
        Fraction f1 = new Fraction(2,3);
        Fraction f2 = new Fraction(3,4);
        Fraction f3 = new Fraction(7,8);
        Fraction fan = an.getAnswer(f1,f2,f3,0,0,1);
        System.out.println(fan.denominator);
    }
    public Fraction getAnswer(Fraction unit1, Fraction unit2 ,Fraction unit3, int operator1,int operator2,int bracket)
    {
        if (bracket == 0) {
            if (operator2 == 2 || operator2 == 3) {
                return getAnswer(unit1, getAnswer(unit2, unit3, operator2), operator1);
            } else {
                return getAnswer(getAnswer(unit1, unit2, operator1), unit3, operator2);
            }
        } else if(bracket == 1)
            {
                return getAnswer(getAnswer(unit1,unit2,operator1),unit3,operator2);
            }
            else
            {
                return getAnswer(unit1,getAnswer(unit2,unit3,operator2),operator1);
            }
        }

    public Fraction getAnswer(Fraction unit1, Fraction unit2,Fraction unit3 ,Fraction unit4 ,
                              int operator1,int operator2,int operator3,int bracket)
    {
        if(bracket == 0) {
            if (operator3 == 2 || operator3 == 3) {
                return getAnswer(unit1, unit2, getAnswer(unit3, unit4, operator3), operator1, operator2,0);
            }
            if (operator2 == 2 || operator2 == 3) {
                return getAnswer(unit1, getAnswer(unit2, unit3, operator2), unit4, operator1, operator3,0);
            } else {
                return getAnswer(getAnswer(unit1, unit2, operator1), unit3, unit4, operator2, operator3,0);
            }
        }
        else if(bracket == 1)
            {
                return getAnswer(getAnswer(unit1,unit2,operator1),unit3,unit4,operator2,operator3,0);
            }
            else if(bracket == 2)
            {
                return getAnswer(unit1,unit2,getAnswer(unit3,unit4,operator3),operator1,operator2,0);
            }
            else if(bracket == 3)
            {
                return getAnswer(unit1,getAnswer(unit2,unit3,operator2),unit4,operator1,operator3,0);
            }
            else if(bracket == 4)
            {
                return getAnswer(getAnswer(unit1,unit2,unit3,operator1,operator2,0),unit4,operator3);
            }
            else
            {
                return getAnswer(unit1,getAnswer(unit2,unit3,unit4,operator2,operator3,0),operator1);
            }
        }
    }
class Needbrackets{
    public int addBrackets(int OperatorNumber)
    {
        if(OperatorNumber == 1)
        {
            return -1;    // two units needn't brackets
        }
        else if (OperatorNumber == 2)
        {
            return (int) (Math.random() * 3); // 0 nobracket    1 frontbracket    2behindbrackt
        }
        else
        {
            return (int) (Math.random() * 6);
            // 0 nobracket    1 frontbracket    2 behindbracket   3middlebracket     4frontbrackets    5behindbrackets
         }
    }
}

class Outpre{
    public String ReturnOperator(int OperatorNumber) {
        if(OperatorNumber == 0) {
            return "+";

        }
        else if(OperatorNumber == 1) {
            return "-";
        }
        else if(OperatorNumber == 2) {
            return "*";
        }
        else {
            return "÷";
        }
    }
    public String ReturnFraction(Fraction unit)
    {
        if(unit.denominator == 1 || unit.denominator == unit.molecule)
            return String.valueOf(unit.molecule);
        else
            return String.valueOf(unit.molecule) + "/" + String.valueOf(unit.denominator);
    }


}
class Output extends Outpre {
    public void OutputQuestionSerialnumber(int serialnumber) {
        try {
            File file = new File("C:\\pr\\question1.txt");
            FileWriter fw = new FileWriter(file, true);
            String s = new String();
            s = String.valueOf(serialnumber) + ": ";
            fw.write(s, 0, s.length());
            fw.close();
        } catch (IOException e) {
            System.out.println("序号输出异常");
        }
    }

    public void OutputAnswerSerialnumber(int serialnumber) {
        try {
            File file = new File("C:\\pr\\answer1.txt");
            FileWriter fw = new FileWriter(file, true);
            String s = new String();
            s = String.valueOf(serialnumber) + ": ";
            fw.write(s, 0, s.length());
            fw.close();
        } catch (IOException e) {
            System.out.println("序号输出异常");
        }
    }

    public void OutputQuestion(Fraction unit1, Fraction unit2, int OperatorNumber) {
        try {
            File file1 = new File("C:\\pr\\question1.txt");
            FileWriter fw1 = new FileWriter(file1, true);
            String s = new String();
            s = ReturnFraction(unit1) + " " + ReturnOperator(OperatorNumber) + " " + ReturnFraction(unit2) + " " + "=";
            fw1.write(s, 0, s.length());
            fw1.write("\n");
            fw1.close();
        } catch (IOException e) {
            System.out.println("题目写入异常");
        }
    }



    public void OutputQuestion(Fraction unit1,Fraction unit2,Fraction unit3,int OperatorNumber1,int OperatorNumber2,int bracket) {
        try {
            String s = "";
            File file1 = new File("C:\\pr\\question1.txt");
            FileWriter fw1 = new FileWriter(file1, true);
            if(bracket == 0)
            {
                s = ReturnFraction(unit1) + " " + ReturnOperator(OperatorNumber1) + " " + ReturnFraction(unit2) +
                        " " + ReturnOperator(OperatorNumber2) + " " + ReturnFraction(unit3) + " " + "=";
            }
            else if(bracket == 1)
            {
                 s = "(" + ReturnFraction(unit1) + " " + ReturnOperator(OperatorNumber1) + " " + ReturnFraction(unit2) + ")"
                         + " " + ReturnOperator(OperatorNumber2) + " " + ReturnFraction(unit3) + " " + "=";
            }
            else if(bracket == 2)
            {
                s = ReturnFraction(unit1) + " " + ReturnOperator(OperatorNumber1) + " (" + ReturnFraction(unit2) +
                        " " + ReturnOperator(OperatorNumber2) + " " + ReturnFraction(unit3) + ") " + "=";
            }
            fw1.write(s, 0, s.length());
            fw1.write("\n");
            fw1.close();
            }
        catch (IOException e) {
            System.out.println("题目写入异常");
        }
        }
    public void OutputQuestion(Fraction unit1,Fraction unit2,Fraction unit3,Fraction unit4,int OperatorNumber1,int OperatorNumber2,int OperatorNumber3,int bracket) {
        try {
            String s = "";
            File file1 = new File("C:\\pr\\question1.txt");
            FileWriter fw1 = new FileWriter(file1, true);
            if(bracket == 0)
            {
                s = ReturnFraction(unit1) + " " + ReturnOperator(OperatorNumber1) + " " + ReturnFraction(unit2) +
                        " " + ReturnOperator(OperatorNumber2) + " " + ReturnFraction(unit3) +
                        " "+ ReturnOperator(OperatorNumber3) + " " + ReturnFraction(unit4) + " " + "=";
            }
            else if(bracket == 1)
            {
                s =  "(" + ReturnFraction(unit1) + " " + ReturnOperator(OperatorNumber1) + ReturnFraction(unit2)+ ")" +
                        " " + ReturnOperator(OperatorNumber2) + " " + ReturnFraction(unit3) +
                        " "+ ReturnOperator(OperatorNumber3) + " " + ReturnFraction(unit4) + " " + "=";
            }
            else if(bracket == 2)
            {
                s =  ReturnFraction(unit1) + " " + ReturnOperator(OperatorNumber1) + " (" + ReturnFraction(unit2) +
                        " " + ReturnOperator(OperatorNumber2) + " " + ReturnFraction(unit3) +
                        ") "+ ReturnOperator(OperatorNumber3) + " " + ReturnFraction(unit4) + " " + "=";
            }
            else  if(bracket == 3)
            {
                s =  ReturnFraction(unit1) + " " + ReturnOperator(OperatorNumber1) + " " + ReturnFraction(unit2) +
                        " " + ReturnOperator(OperatorNumber2) + " (" + ReturnFraction(unit3) +
                        " "+ ReturnOperator(OperatorNumber3) + " " + ReturnFraction(unit4) + ") " + "=";
            }
            else  if(bracket == 4)
            {
                s =  "(" + ReturnFraction(unit1) + " " + ReturnOperator(OperatorNumber1) + " " + ReturnFraction(unit2) +
                        " " + ReturnOperator(OperatorNumber2) + " " + ReturnFraction(unit3) +
                        "） "+ ReturnOperator(OperatorNumber3) + " " + ReturnFraction(unit4) + " " + "=";
            }
            else
            {
                s = ReturnFraction(unit1) + " " + ReturnOperator(OperatorNumber1) + " (" + ReturnFraction(unit2) +
                        " " + ReturnOperator(OperatorNumber2) + " " + ReturnFraction(unit3) +
                        " "+ ReturnOperator(OperatorNumber3) + " " + ReturnFraction(unit4) + ") " + "=";
            }
            fw1.write(s, 0, s.length());
            fw1.write("\n");
            fw1.close();
        }
        catch (IOException e) {
            System.out.println("题目写入异常");
        }
    }


    public void outputanswer(Fraction answer) {
        try {
            String s = new String();
            File file1 = new File("C:\\pr\\answer1.txt");
            FileWriter fw1 = new FileWriter(file1, true);
            s = ReturnFraction(answer);
            fw1.write(s, 0, s.length());
            fw1.write("\n");
            fw1.close();
        } catch (IOException e) {
            System.out.println("答案写入异常");
        }

    }
}

class UI{
    public void GetUI()
    {
        JFrame frame = new JFrame();
        frame.setTitle("小学生折磨器");
        Container con = frame.getContentPane();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(250,300);
        frame.setLayout(new FlowLayout(FlowLayout.CENTER,30,8));
        frame.setLocationRelativeTo(null);
        con.setBackground(Color.cyan);
        frame.setResizable(false);
        JLabel jl = new JLabel("    嘉然今天吃什么  ");
        JLabel jl0 = new JLabel("题目数量：          ");
        JLabel jl1 = new JLabel("自然数最大值：   ");
        JLabel jl2 = new JLabel("分数最大值:         ");
        JLabel jl3 = new JLabel("分数分母最大值:  ");
        JLabel jl4 = new JLabel("是否添加括号（YorN): ");
        JLabel jl5 = new JLabel( "运算符个数（max=3）：");
        JTextField text0  = new JTextField(5);
        JTextField text1 = new JTextField(5);
        JTextField text2 = new JTextField(5);
        JTextField text3 = new JTextField(5);
        JTextField text4 = new JTextField(2);
        JTextField text5 = new JTextField(2);
        JButton bu = new JButton();
        bu.setText("已确认数据");
        bu.setBackground(Color.green);
        con.add(jl);
        con.add(jl0);
        con.add(text0);
        con.add(jl1);
        con.add(text1);
        con.add(jl2);
        con.add(text2);
        con.add(jl3);
        con.add(text3);
        con.add(jl4);
        con.add(text4);
        con.add(jl5);
        con.add(text5);
        con.add(bu);
        bu.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String str0 = text0.getText().trim();
                String str1 =text1.getText().trim();
                String str2 = text2.getText().trim();
                String str3 = text3.getText().trim();
                String str4 = text4.getText().trim();
                String str5 = text5.getText().trim();
                int Number = Integer.parseInt(str0);
                int RangeNumber = Integer.parseInt(str1);
                Double TrueFractionRange = Double.parseDouble(str2);
                int Denominator = Integer.parseInt(str3);
                boolean brackets = false;
                if(str4.equals("Y"))
                {
                    brackets = true;
                }
                else
                    brackets = false;
                int OperatorNumber = Integer.parseInt(str5);
                DoOnce do1 = new DoOnce();
                do1.doit(Number, RangeNumber ,TrueFractionRange,Denominator, brackets , OperatorNumber);
            }
        });
        frame.setVisible(true);

    }

    public static void main(String[] args) {
        UI ui = new UI();
        ui.GetUI();
    }

}

class  Preparation {
    int[][] OperatorUnit;
    Fraction[][] QuestionUnit;

    public Preparation(int[][] operatorUnit, Fraction[][] questionUnit) {
        OperatorUnit = operatorUnit;
        QuestionUnit = questionUnit;
    }

    public void DoQuestionOperatorUnit(int number , int RangeNumber, int OperatorNumber, double TrueFractionRange, int Denominator)
    {

        for(int i=0;i<number;i++)
        {
            for(int j =0 ; j<OperatorNumber ; j++)
            {
                OperatorUnit[i][j] = (int) (Math.random() * 4);
            }

        }
        for(int i = 0 ;i< number;i++)
        {
            for(int j = 0 ;j < OperatorNumber ;j++)
            {
                int denominator;
                int molecule;
                if(Math.random()>0.5) {
                    denominator = (int) (Math.random() * Denominator + 1);
                    molecule = (int) (Math.random() * (denominator * TrueFractionRange));
                    if (molecule <= 0 || denominator <= 0) {
                        {
                            molecule = (int) (Math.random() * RangeNumber) + 1;
                        }
                    }
                }
                else
                {
                    denominator = 1 ;
                    molecule = (int) (Math.random() * RangeNumber);
                }
                QuestionUnit[i][j] = new Fraction(molecule,denominator);

            }
        }


    }
}
class DoOnce {
    public void doit(int number , int RangeNumber,double TrueFractionRange,int Denominator
            ,boolean needbrackets,int OperatorNumber)   //   0 < range < rangenumber   questionunit = operatornumber +1
    {
        Fraction[][] fractions = new Fraction[number][OperatorNumber + 1];
        for(int i =0 ;i < number ;i++)
        {
            for(int j = 0 ;j < OperatorNumber+1 ;j++)
            {
                fractions[i][j] = new Fraction(1,1);
            }
        }
        int[][] ints = new int[number][OperatorNumber];
        Preparation pr = new Preparation(ints,fractions);
        pr.DoQuestionOperatorUnit(number,RangeNumber,OperatorNumber,TrueFractionRange,Denominator);
        Fraction answer[] = new Fraction[number];
        int brackets[] = new int[number];
        for(int i = 0 ;i < number ;i++)
        {
            brackets[i] = 0;
        }
        if(needbrackets)
        {
            Needbrackets needbrackets1 = new Needbrackets();
            for(int i = 0 ;i < number;i++)
            {
                brackets[i] = needbrackets1.addBrackets(OperatorNumber);
            }
        }

        for(int i=0;i < number ;i++)
        {
            Answer an = new Answer();
            if(OperatorNumber == 1)
            {
                answer[i] = an.getAnswer(pr.QuestionUnit[i][0],pr.QuestionUnit[i][1],
                        pr.OperatorUnit[i][0]);
            }
           else if(OperatorNumber == 2)
            {
                answer[i] = an.getAnswer(pr.QuestionUnit[i][0],pr.QuestionUnit[i][1],
                        pr.QuestionUnit[i][2], pr.OperatorUnit[i][0],
                        pr.OperatorUnit[i][1],brackets[i]);
            }else
            {
                answer[i] = an.getAnswer(pr.QuestionUnit[i][0],pr.QuestionUnit[i][1],
                        pr.QuestionUnit[i][2],pr.QuestionUnit[i][3],
                        pr.OperatorUnit[i][0],pr.OperatorUnit[i][1],pr.OperatorUnit[i][2],brackets[i]);
            }
            if(answer[i].denominator == (-5))
            {
                for(int j =0 ; j< OperatorNumber; j++) {
                    pr.OperatorUnit[i][j] = (int) (Math.random() * 4);
                    }
                for(int j =0 ;j<(OperatorNumber+1);j++)
                {
                    int denominator = (int) (Math.random() * Denominator + 1);
                    pr.QuestionUnit[i][j].denominator = denominator;
                    pr.QuestionUnit[i][j].molecule = (int) (Math.random() * (denominator * TrueFractionRange));
                }
                i--;
                continue;
            }
        }
        Output op = new Output();
        if(OperatorNumber == 1)
        {
            for(int i = 0;i < number ;i++)
            {
                op.OutputQuestionSerialnumber(i+1);
                op.OutputQuestion(pr.QuestionUnit[i][0],pr.QuestionUnit[i][1],pr.OperatorUnit[i][0]);
            }
        }
        else if(OperatorNumber == 2)
        {
            for(int i = 0 ;i< number ;i++)
            {
                op.OutputQuestionSerialnumber(i+1);
                op.OutputQuestion(pr.QuestionUnit[i][0],pr.QuestionUnit[i][1],pr.QuestionUnit[i][2],
                        pr.OperatorUnit[i][0],pr.OperatorUnit[i][1],brackets[i]);
            }
        }
        else
        {
            for(int i = 0 ;i< number ;i++)
            {
                op.OutputQuestionSerialnumber(i+1);
                op.OutputQuestion(pr.QuestionUnit[i][0],pr.QuestionUnit[i][1],pr.QuestionUnit[i][2],
                        pr.QuestionUnit[i][3],pr.OperatorUnit[i][0],pr.OperatorUnit[i][1],
                        pr.OperatorUnit[i][2],brackets[i]);
            }
        }
        for(int i = 0 ;i < number ;i++)
        {
            op.OutputAnswerSerialnumber(i+1);
            op.outputanswer(answer[i]);
        }
    }

}
class Mmain {
    public static void main(String[] args) {
        UI ui = new UI();
        ui.GetUI();

    }
}

