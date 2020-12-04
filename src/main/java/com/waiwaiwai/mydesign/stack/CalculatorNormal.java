package com.waiwaiwai.mydesign.stack;

import org.junit.Test;

import java.util.Stack;

/**
 * @Author: wangzhenglei
 * @DateTime: 2020/12/2 16:00
 * @Description: 模拟一个计算器  有括号的时候应该怎么整 ?
 *  左括号不处理压入栈   遇到右括号再运算整个括号里面的
 */
public class CalculatorNormal {

    @Test
    public void test() {
        String str = "1+12*2+13*4-12/4";
        double cal = cal(str);
        System.out.println(cal);
    }

    public double cal(String str) {
        // 连个栈
        Stack<Double> dataStack = new Stack<>();
        Stack<String> operatorStack = new Stack<>();
        // + - * /
        // 截取字符串
        char[] chars = str.toCharArray();
        StringBuilder data = new StringBuilder();
        for (char aChar : chars) {
            String aStr = String.valueOf(aChar);
            if (" ".equals(aStr)) {
                continue;
            }
            if("+".equals(aStr) || "-".equals(aStr)|| "*".equals(aStr)|| "/".equals(aStr)) {
                dataStack.push(Double.parseDouble(data.toString()));
                // 比对计算  数据有两个字符后才运算   然后运算符比较优先级
                if (!operatorStack.isEmpty()) {
                    handler(dataStack, operatorStack, aStr);
                } else {
                    operatorStack.push(aStr);
                }
                // 重新把数据置空
                data = new StringBuilder();
            }else {
                data.append(aStr);
            }
        }
        // for循环结束data肯定还有一份数据
        dataStack.push(Double.parseDouble(data.toString()));
        while (!operatorStack.isEmpty()) {
            calcaul(dataStack, operatorStack);
        }
        return dataStack.pop();
    }

    private void handler (Stack<Double> dataStack, Stack<String> operatorStack, String operator) {
        String opera = operatorStack.pop();
        operatorStack.push(opera);
        boolean boo = compareOperator(operator, opera);
        if (boo) { // 优先级高
            // 优先级高就接着插入数据  插入运算符
            operatorStack.push(operator);
        } else {
            // 优先级低、一致就运算, 然后把计算完后的数据压入栈顶  把运算符压入运算符栈顶
            // 运算
            calcaul(dataStack, operatorStack);
            // 插入运算符
            operatorStack.push(operator);
        }
    }

    private void calcaul(Stack<Double> dataStack, Stack<String> operatorStack) {
        // 计算
        Double pop1 = dataStack.pop();
        Double pop2 = dataStack.pop();
        String opera = operatorStack.pop();
        double result = 0;
        if ("+".equals(opera)) {
            result =  pop1 + pop2;
        }
        if ("-".equals(opera)) {
            result =  pop2 - pop1;
        }
        if ("*".equals(opera)) {
            result =  pop1 * pop2;
        }
        if ("/".equals(opera)) {
            result =  pop2 / pop1;
        }
        dataStack.push(result);
    }

    // 比较运算符优先级
    private boolean compareOperator(String o1, String o2) {
        if (!"+".equals(o1) && !"-".equals(o1) && !"*".equals(o1) && !"/".equals(o1)) {
            return false;
        }
        if (!"+".equals(o2) && !"-".equals(o2) && !"*".equals(o2) && !"/".equals(o2)) {
            // 报错
            return false;
        }
        int c1 = 0;
        int c2 = 0;
        if ("+".equals(o1) || "-".equals(o1)) {
            c1 = 1;
        }
        if ("+".equals(o2) || "-".equals(o2)) {
            c2 = 1;
        }
        if ("*".equals(o1) || "/".equals(o1)) {
            c1 = 2;
        }
        if ("*".equals(o2) || "/".equals(o2)) {
            c2 = 2;
        }
        return c1 > c2;
    }



}
