/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operationssmarter;

/**
 *
 * @author 00342605
 */
public class OperationsSmarter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String operation;
        
        Operations op = new Operations();
        
        operation = op.getOperation();
        
        op.MakeOp(operation);
        
    }
        
    
}
