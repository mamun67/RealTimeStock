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
//jsoup lib
import org.jsoup.Jsoup;  
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author 
 */
public class Login {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    
    static String date = (new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime())).toString();
    public static void main(String[] args) throws Exception {
        
               WebClient webClient = new WebClient(BrowserVersion.FIREFOX_38); //Initiate a WebClient variable.  
               webClient = tremorLogin(webClient);
                              
                   }
    //Login into Yahoo and return the client(Saves the cookies).
      private static WebClient tremorLogin(WebClient webClient) throws Exception
    {
        webClient.getOptions().setJavaScriptEnabled(false);    
        final HtmlPage currentPage = webClient.getPage("url"); //Load page at the STRING address.
        final  HtmlInput username = currentPage.getElementByName("username"); //Find element called loginuser for username
        username.setValueAttribute("userid"); //Set value for username       
        final  HtmlInput password = currentPage.getElementByName("passwd"); //Find element called loginpassword for password
        password.setValueAttribute("password"); //Set value for password   
        WebResponse response1 = currentPage.getWebResponse();
        String content2 = response1.getContentAsString();    
        final HtmlPage page2; 
        HtmlElement htmlElement = currentPage.getFirstByXPath("//*[@id=\"login-signin\"]");
        HtmlPage src=htmlElement.click();       
        return webClient;
    }
      
      private static void GenFile(WebClient webClient) throws IOException
      {
          Page page = webClient.getPage("portfolio url");
               WebResponse response = page.getWebResponse();
               File file = new File("./output/"+date);
            if(file.isDirectory()){
             createcsv(content);
            }else{File outp = new File("./output");
                if(outp.isDirectory()){
                    File dir = new File("./output/"+date);
                    dir.mkdir();
                    createcsv(content);
                }
                else{
                    outp.mkdir();
                    File dir = new File("./output/"+date);
                    dir.mkdir();
                     createcsv(content);
                }
            }
           
      }
      
      public static void createcsv(String content) throws IOException
      {
          
          
          
         // the code for csv file creation goes here... 
          File input = new File("web.html");
	Document doc = Jsoup.parse(input, "UTF-8", "http://example.com/");
        File file = new File("./output/"+date);
        String fname = Integer.toString(file.listFiles().length +1);
          
         
	//Document doc = Jsoup.parse(input);
	try {
		//FileWriter writer = new FileWriter("dataout.csv");
                FileWriter writer = new FileWriter("./output/"+date+"/stockdata_"+fname+".csv");
		for (Element table : doc.select("table.yfi_portfolios_multiquote")) {
			for (Element row : table.select("tr")) {
				Elements ths = row.select("th");
				Elements tds = row.select("td");
				if(ths.size()>0){
					for ( Element cellh : ths){
						writer.write(cellh.text().replace(",","").concat(","));
					}
				}
				for ( Element cell : tds){
					writer.write(cell.text().replace(",","").concat(","));
				}
				writer.write("\n");
			}
			writer.close();
		}
	} catch (IOException e) {
		e.getStackTrace();
	}
          
          
      }
}
