/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package criptoaes;
import java.util.concurrent.TimeUnit;
import java.util.Calendar;
/**
 *
 * @author 10070187
 */
public class Principal {
    public static void main(String[] args) throws Exception {        
        
        long startTime = System.currentTimeMillis();
        System.out.println("Início da Decriptação: " + Calendar.getInstance().getTime());
        CriptoAES.gerarChave();
//        Thread a = new Thread(CriptoAES.gerarChave());
//        Thread b = new Thread(CriptoAES.gerarChave());
//        a.start();
//        b.start();    
//       
//        a.join();
//        b.join();
        
        long estimatedTime = System.currentTimeMillis() - startTime;
        System.out.println("Senha Final: " + CriptoAES.Char_Final);
        System.out.println("Texto Decriptado: " + CriptoAES.textoDecriptado);
        System.out.println("Nro de tentativas: " + CriptoAES.nro_tentativas);
        System.out.println("Minutos Totais: " + TimeUnit.MILLISECONDS.toMinutes(estimatedTime));    
        System.out.println("Final da decriptação: " + Calendar.getInstance().getTime());        
    }
}