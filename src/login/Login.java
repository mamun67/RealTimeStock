/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlInput;  
import java.io.IOException;

/**
 *
 * @author mamun
 */
public class Login {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        
               WebClient webClient = new WebClient(BrowserVersion.FIREFOX_38); //Initiate a WebClient variable.  
               webClient = tremorLogin(webClient);
                              
             
    }
    //Login into Yahoo and return the client(Saves the cookies).
      private static WebClient tremorLogin(WebClient webClient) throws Exception
    {
        webClient.getOptions().setJavaScriptEnabled(false);    
        final HtmlPage currentPage = webClient.getPage("https:"); //Load page at the STRING address.
        final  HtmlInput username = currentPage.getElementByName("username"); //Find element called loginuser for username
        username.setValueAttribute("userid"); //Set value for username       
        final  HtmlInput password = currentPage.getElementByName("passwd"); //Find element called loginpassword for password
        password.setValueAttribute("pass"); //Set value for password   
        WebResponse response1 = currentPage.getWebResponse();
        String content2 = response1.getContentAsString();    
        final HtmlPage page2; 
        HtmlElement htmlElement = currentPage.getFirstByXPath("//*[@id=\"login-signin\"]");
        HtmlPage src=htmlElement.click();       
        return webClient;
    }
      
      private static void GenFile(WebClient webClient) throws IOException
      {
          Page page = webClient.getPage("porfolio page link");
               WebResponse response = page.getWebResponse();
               String content = response.getContentAsString();
               System.out.println(content);
           
      }
      
      private static void createcsv(String content)
      {
          
      }
}
