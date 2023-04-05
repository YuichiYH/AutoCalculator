/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operationssmarter;

import java.util.Scanner;

/**
 *
 * @author 00342605
 */
public class Operations {
    Scanner scanner = new Scanner(System.in);
    
    public String getOperation(){
        System.out.println("give me a operation");
        String operation = scanner.next();
        return operation;
    }
    
    
    private char[] getOps(String operation){
        char[] ops;
        
        ops = operation.replaceAll("[1234567890.NaInfinity]", "").toCharArray();
        
        return ops;
    }
    private double[] getNums(String operation){
        String[] strNums;
        
        //TO-DO
        // Regex doesnt work, for some reason . is selected
        operation = operation.replaceAll("NaN", "");
        operation = operation.replaceAll("[\\+\\-\\/*xX]", ",");
        strNums = operation.split(",");
        try{
            double[] Nums = new double[strNums.length];
            for(int i = 0; i < strNums.length; i++){
                Nums[i] = Double.parseDouble(strNums[i]);
            }
            
            return Nums;
        }
        catch(Exception e){
            System.out.print(e);
            return new double[1];
        }
    }
    
    private String getNewOp(double[] Nums, char[] Ops, double[] result){
        String newArray = "";
        boolean again = false;
        if (!Double.isNaN(Nums[0])){
            newArray += Nums[0];    
        }
        for(int i = 0; i<Ops.length; i++){
            if(Ops[i] != 0){
                newArray += Ops[i];
                if (!Double.isNaN(Nums[i+1])){
                    newArray += Nums[i+1];
                }
                again = false;
            }
            else{
                if(!again){
                    newArray += result[i];
                }
                again = true;
                
            }
        }
        
        return newArray;
    }
    
    public String MakeOp(String operation){
        char[] opsMult = this.getOps(operation), opsDiv, opsSum, opsSub;
        double[] numsMult = this.getNums(operation), numsDiv, numsSum, numsSub, afterCalc = new double[opsMult.length];
        int counter = 0;
        String newOp;
        boolean again = false;
        
        if (operation.isEmpty()){
            System.out.println("Empty operation");
            return "";
        }
        
        if(numsMult[0] == 0 && numsMult.length == 1){
            System.out.println("Invalid operators, or leaving");
            System.exit(0);
        }
        
        //check mult
        //Notes - transform used values in NaN to check if they are already used
        for(int i = 0; i < opsMult.length; i++){
            if(opsMult[i] == 'x' || opsMult[i] == '*' || opsMult[i] == 'X'){
                if (again){
                    afterCalc[counter] *= numsMult[i+1];
                }
                else{
                    afterCalc[counter] = numsMult[i] * numsMult[i+1];   
                }
                numsMult[i] = Double.NaN;
                numsMult[i+1] = Double.NaN;
                opsMult[i] = 0;
                again= true;
            }
            else{
                counter = i+1;
                again = false;
            }
        }
        newOp = this.getNewOp(numsMult, opsMult, afterCalc);
        
        System.out.println(newOp);
        
        if (numsMult.length == 0){
            System.out.println(newOp);
            return newOp;
        }
        
        numsDiv = this.getNums(newOp);
        opsDiv = this.getOps(newOp);
        counter = 0;
        again = false;
        //check div
        for(int i = 0; i < opsDiv.length; i++){
            if(opsDiv[i] == '/'){
                if (again){
                    afterCalc[counter] /= numsDiv[i+1];
                }
                else{
                    afterCalc[counter] = numsDiv[i] / numsDiv[i+1];   
                }
                numsDiv[i] = Double.NaN;
                numsDiv[i+1] = Double.NaN;
                opsDiv[i] = 0;
                again= true;
            }
            else{
                counter = i+1;
                again = false;
            }
        }
        if (numsDiv.length == 0){
            System.out.println(newOp);
            return newOp;
        }
        newOp = this.getNewOp(numsDiv, opsDiv, afterCalc);
        
        System.out.println(newOp);
        
        
        numsSum = this.getNums(newOp);
        opsSum = this.getOps(newOp);
        counter = 0;
        again = false;
        //check sum
        for(int i = 0; i < opsSum.length; i++){
            //somehow NaN is going to AfterCalc
            if(opsSum[i] == '+'){
                if (again){
                    afterCalc[counter] += numsSum[i+1];
                }
                else{
                    afterCalc[counter] = numsSum[i] + numsSum[i+1];   
                }
                numsSum[i] = Double.NaN;
                numsSum[i+1] = Double.NaN;
                opsSum[i] = 0;
                again= true;
            }
            else{
                counter = i+1;
                again = false;
            }
        }
        if (numsSum.length == 0){
            System.out.println(newOp);
            return newOp;
        }
        newOp = this.getNewOp(numsSum, opsSum, afterCalc);
        
        System.out.println(newOp);
        
        
        numsSub = this.getNums(newOp);
        opsSub = this.getOps(newOp);
        counter = 0;
        again = false;
        //check sub
        for(int i = 0; i < opsSub.length; i++){
            if(opsSub[i] == '-'){
                if (again){
                    afterCalc[counter] -= numsSub[i+1];
                }
                else{
                    afterCalc[counter] = numsSub[i] - numsSub[i+1];   
                }
                numsSub[i] = Double.NaN;
                numsSub[i+1] = Double.NaN;
                opsSub[i] = 0;
                again= true;
            }
            else{
                counter = i+1;
                again = false;
            }
        }
        if (numsSub.length == 0){
            System.out.println(newOp);
            return newOp;
        }
        newOp = this.getNewOp(numsSub, opsSub, afterCalc);
        

        System.out.println(newOp);
        return newOp;
        
    } 
}
