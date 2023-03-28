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
    String operation;
    Scanner scanner = new Scanner(System.in);
    
    public void getOperation(){
        System.out.println("give me a operation");
        operation = scanner.next();
    }
    
    
    private char[] getOps(){
        char[] ops;
        
        
        ops = operation.replaceAll("[1234567890]", "").toCharArray();
        
        return ops;
    }
    private double[] getNums(){
        String[] strNums;
        
        strNums = operation.replaceAll("[+/*xX]", ",").split(",");
        try{
            double[] Nums = new double[strNums.length];
            for(int i = 0; i < strNums.length; i++){
                Nums[i] = Double.parseDouble(strNums[i]);
            }
            
            return Nums;
        }
        catch(Exception e){
            System.out.print(e);
            return new double[0];
        }
    }
    
    public void MakeOp(){
        char[] ops = this.getOps();
        double[] nums = this.getNums();
        
        if(nums[0] == 0 && nums.length == 0){
            System.out.println("Invalid operators, or leaving");
            System.exit(0);
        }
        
    } 
}
