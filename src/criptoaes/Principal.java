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
//        CriptoAES.gerarChave();
        Thread a = new Thread(CriptoAES.gerarChave());
        Thread b = new Thread(CriptoAES.gerarChave2());
        Thread c = new Thread(CriptoAES.gerarChave3());
        a.start();
        b.start();    
        c.start();
        
        a.join();
        b.join();
        c.join();
        
        long estimatedTime = System.currentTimeMillis() - startTime;
        String str = new String(CriptoAES.Char_Final);
        System.out.println("Senha Final: " + str);
        System.out.println("Texto Decriptado: " + CriptoAES.textoDecriptado);
        System.out.println("Nro de tentativas: " + CriptoAES.nro_tentativas);
        System.out.println("Nro de tentativas: " + 3);
        System.out.println("Minutos Totais: " + TimeUnit.MILLISECONDS.toMinutes(estimatedTime));    
        System.out.println("Final da decriptação: " + Calendar.getInstance().getTime());        
    }
}