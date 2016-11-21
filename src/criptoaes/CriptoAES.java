/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package criptoaes;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.security.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import sun.misc.*;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class CriptoAES implements Runnable{
    private static final String Algoritmo = "AES";
    public static String possibilidades;
    public static String pedaco = "";
    public static byte[] Char_Final = new byte[16];
    public static int nro_tentativas = 0;
    private static boolean stop = false;
    static String senha = "12pingpongQ";
    static String textoEncriptado = "6j7W7b51dLus8COS0tVzf8t4Qf7Ch2sO5wBP7eeyEmkW+mIT4eEdk+CejhqT7/t/Dbo2lQZlias7AMqw0OD9NNkkaZSRd2qbrxjlzClLdVClBf877N90JutLjxw7d9q9+MYbRzYJGkCEvUWFHF7gvg==";;
    public static String textoDecriptado = "";

// metodo para encriptar  utilizando texto e chave passados
    public static String encrypt(String texto, byte[] chave) throws Exception {
        Key key = generateKey(chave); //gera a "key" a partir da chave passada pelo usuário
        Cipher c = Cipher.getInstance(Algoritmo); //cria o algortimo de criptografia no modo escolhido, ex:AES
        c.init(Cipher.ENCRYPT_MODE, key);//inicia o algoritmo no modo encriptação com a chave criada
        //System.out.println(c.getAlgorithm());
        byte[] resultadoEncriptado = c.doFinal(texto.getBytes()); //encripta o texto passado
        String teste = new String (resultadoEncriptado);
        System.out.println("isso em binario "+teste);
        String encriptadoEmBase64 = new BASE64Encoder().encode(resultadoEncriptado); //transforma para Base 64 para poder exibir na tela

        return encriptadoEmBase64;
    }

    // metodo para decriptar  utilizando texto encriptado (em base 64) e chave passada
    public static String decrypt(String textoEncriptado, byte[] chave) throws Exception {
        
        Key key = generateKey(chave); //gera a "key" a partir da chave passada pelo usuário
        Cipher c = Cipher.getInstance(Algoritmo); //cria o algortimo de criptografia no modo escolhido, ex:AES
        c.init(Cipher.DECRYPT_MODE, key); //inicia o algoritmo no modo decriptação com a chave criada
        byte[] decodificadoBase64paraByte = new BASE64Decoder().decodeBuffer(textoEncriptado); //transforma de Base 64 para bytes
        byte[] resultadoDecriptado = c.doFinal(decodificadoBase64paraByte); //decripta valor em byte
        byte[] decriptadoEmByte = resultadoDecriptado;
        CharsetDecoder cs = Charset.forName("UTF-8").newDecoder();
        try{
            cs.decode(ByteBuffer.wrap(decriptadoEmByte));
            return new String(decriptadoEmByte);
        }catch(CharacterCodingException e){
        throw new Exception();
        }
    }
    
    //funcao para gerar key de encriptação a partir da chave do usuário
    private static Key generateKey(byte[] chave) throws Exception {
        Key key = new SecretKeySpec(chave, Algoritmo);
        return key;
    }
    
    public synchronized static String gerarChave(){
        possibilidades = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        senha = "12pingpongQ";
        textoEncriptado = "6j7W7b51dLus8COS0tVzf8t4Qf7Ch2sO5wBP7eeyEmkW+mIT4eEdk+CejhqT7/t/Dbo2lQZlias7AMqw0OD9NNkkaZSRd2qbrxjlzClLdVClBf877N90JutLjxw7d9q9+MYbRzYJGkCEvUWFHF7gvg==";
        textoDecriptado = "";
        char[] pedaco = new char[5];
        try{
            char[] senha_Char = senha.toCharArray();
            System.arraycopy(new String(senha_Char).getBytes("UTF-8"), 0, Char_Final, 0, new String(senha_Char).getBytes("UTF-8").length);
            for(int i=0;i<possibilidades.length();i++){                 
                pedaco[0] = possibilidades.charAt(i);
                System.out.println("Thread 1");
//                System.out.println("olha o i " + i);
                for(int x=0; x<possibilidades.length();x++){
                    pedaco[1] = possibilidades.charAt(x);
//                    System.out.println("olha o x " + x);
                    for(int y=0;y<possibilidades.length();y++){
                    pedaco[2] = possibilidades.charAt(y);                     
//                    System.out.println("olha o y " + y);
                        for(int z=0;z<possibilidades.length();z++){
                        pedaco[3] = possibilidades.charAt(z); 
//                                System.out.println("olha o z " + z);
                            for(int w=0;w<possibilidades.length();w++){
                                   pedaco[4] = possibilidades.charAt(w); 
//                                    System.out.println("olha o w " + w);
                                   System.arraycopy(new String(pedaco).getBytes("UTF-8"), 0, Char_Final, 11, new String(pedaco).getBytes("UTF-8").length);
//                                 senha = new StringBuilder().append(senha_Char).append(pedaco).toString();
//                                 String senha_for = senha_Char + pedaco;
//                                 byte[] senha_final = senha.getBytes("UTF-8");
                                     nro_tentativas++;
                                    try{
                                    textoDecriptado = decrypt(textoEncriptado, Char_Final); 
                                        if(!textoDecriptado.equals("") && textoDecriptado.contains("texto") ){
                                            stop = true;
                                            return textoDecriptado;
                                        }
                                    }catch(Exception ex){}
                                }                                                       
                            }
                        }
                    }
                }
            }catch(UnsupportedEncodingException ex){
            Logger.getLogger(ex.getMessage());
            }
        return textoEncriptado;
    }       

    public synchronized static String gerarChave2(){
        possibilidades = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz";
        senha = "12pingpongQ";
        textoEncriptado = "6j7W7b51dLus8COS0tVzf8t4Qf7Ch2sO5wBP7eeyEmkW+mIT4eEdk+CejhqT7/t/Dbo2lQZlias7AMqw0OD9NNkkaZSRd2qbrxjlzClLdVClBf877N90JutLjxw7d9q9+MYbRzYJGkCEvUWFHF7gvg==";
        textoDecriptado = "";
        char[] pedaco = new char[5];
        try{
            char[] senha_Char = senha.toCharArray();
            System.arraycopy(new String(senha_Char).getBytes("UTF-8"), 0, Char_Final, 0, new String(senha_Char).getBytes("UTF-8").length);
            for(int i=0;i<possibilidades.length();i++){                 
                pedaco[0] = possibilidades.charAt(i);
                System.out.println("Thread 2");
//                System.out.println("olha o i " + i);
                for(int x=0; x<possibilidades.length();x++){
                    pedaco[1] = possibilidades.charAt(x);
//                    System.out.println("olha o x " + x);
                    for(int y=0;y<possibilidades.length();y++){
                    pedaco[2] = possibilidades.charAt(y);                     
//                    System.out.println("olha o y " + y);
                        for(int z=0;z<possibilidades.length();z++){
                        pedaco[3] = possibilidades.charAt(z); 
//                                System.out.println("olha o z " + z);
                            for(int w=0;w<possibilidades.length();w++){
                                   pedaco[4] = possibilidades.charAt(w); 
//                                    System.out.println("olha o w " + w);
                                   System.arraycopy(new String(pedaco).getBytes("UTF-8"), 0, Char_Final, 11, new String(pedaco).getBytes("UTF-8").length);
//                                 senha = new StringBuilder().append(senha_Char).append(pedaco).toString();
//                                 String senha_for = senha_Char + pedaco;
//                                 byte[] senha_final = senha.getBytes("UTF-8");
                                     nro_tentativas++;
                                    try{
                                    textoDecriptado = decrypt(textoEncriptado, Char_Final); 
                                        if(!textoDecriptado.equals("") && textoDecriptado.contains("texto") ){
                                            stop = true;
                                            return textoDecriptado;
                                        }
                                    }catch(Exception ex){}
                                }                                                       
                            }
                        }
                    }
                }
            }catch(UnsupportedEncodingException ex){
            Logger.getLogger(ex.getMessage());
            }
        return textoEncriptado;
    }
    
    public synchronized static String gerarChave3(){
        possibilidades = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        senha = "12pingpongQ";
        textoEncriptado = "6j7W7b51dLus8COS0tVzf8t4Qf7Ch2sO5wBP7eeyEmkW+mIT4eEdk+CejhqT7/t/Dbo2lQZlias7AMqw0OD9NNkkaZSRd2qbrxjlzClLdVClBf877N90JutLjxw7d9q9+MYbRzYJGkCEvUWFHF7gvg==";
        textoDecriptado = "";
        char[] pedaco = new char[5];
        try{
            char[] senha_Char = senha.toCharArray();
            System.arraycopy(new String(senha_Char).getBytes("UTF-8"), 0, Char_Final, 0, new String(senha_Char).getBytes("UTF-8").length);
            for(int i=0;i<possibilidades.length();i++){                 
                pedaco[0] = possibilidades.charAt(i);
                System.out.println("Thread 3");
//                System.out.println("olha o i " + i);
                for(int x=0; x<possibilidades.length();x++){
                    pedaco[1] = possibilidades.charAt(x);
//                    System.out.println("olha o x " + x);
                    for(int y=0;y<possibilidades.length();y++){
                    pedaco[2] = possibilidades.charAt(y);                     
//                    System.out.println("olha o y " + y);
                        for(int z=0;z<possibilidades.length();z++){
                        pedaco[3] = possibilidades.charAt(z); 
//                                System.out.println("olha o z " + z);
                            for(int w=0;w<possibilidades.length();w++){
                                   pedaco[4] = possibilidades.charAt(w); 
//                                    System.out.println("olha o w " + w);
                                   System.arraycopy(new String(pedaco).getBytes("UTF-8"), 0, Char_Final, 11, new String(pedaco).getBytes("UTF-8").length);
//                                 senha = new StringBuilder().append(senha_Char).append(pedaco).toString();
//                                 String senha_for = senha_Char + pedaco;
//                                 byte[] senha_final = senha.getBytes("UTF-8");
                                     nro_tentativas++;
                                    try{
                                    textoDecriptado = decrypt(textoEncriptado, Char_Final); 
                                        if(!textoDecriptado.equals("") && textoDecriptado.contains("texto") ){
                                            stop = true;
                                            return textoDecriptado;
                                        }
                                    }catch(Exception ex){}
                                }                                                       
                            }
                        }
                    }
                }
            }catch(UnsupportedEncodingException ex){
            Logger.getLogger(ex.getMessage());
            }
        return textoEncriptado;
    }
    
    @Override
    public void run() {
//        gerarChave();
//        gerarChave2();
//        gerarChave3();
        do {if (stop != true) {
                gerarChave();
                gerarChave2();
                gerarChave3();
            }else{
            Thread.currentThread().interrupt();
            }
        } while (this.textoDecriptado !=null);
    }
}