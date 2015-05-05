package vldForm;

import java.io.*;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import java.util.*;

@WebServlet("/show-params")
public class ShowParameters extends HttpServlet {
	Map<String, String> messages = new HashMap<String, String>();
	boolean foundEmpty = false;
	boolean foundInvalid = false;
	@Override
	  public void doGet(HttpServletRequest request,
	                    HttpServletResponse response)
	      throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		messages.clear();//clears the messages 
		
	    String itemNum = request.getParameter("itemNum");
	    String cardType = request.getParameter("cardType");
	    String cardNum = request.getParameter("cardNum");
	    String cardNum2 = request.getParameter("cardNum2");
		
	    checkNum(itemNum); //check if item number is numeric
 
	    //check if any field is missing
	    foundInvalid = checkEmpty(request);
		if (foundInvalid)
			// give a general error message of missing fields
			messages.put("epy", "Missing required fields"); 
		
		String[] parName = {"firstName", "lastName","city","state","zip",};
		//validate first name, last name, city, state, zip code
		for(String name:parName)
			checkVali(name, request);
		
		
		try{
			//check if payment method is selected
			if(cardType == null){
				throw new NullPointerException();
			}
			//check if card number is missing
			if(cardNum == null){
				foundInvalid = true;
				messages.put("cardNum", "<--Enter card number");
			}
			//check if card number is confirmed correctly
			if(!cardNum.equals(cardNum2)){
				foundInvalid = true;
				messages.put("cardNum", "<--Card numbers don't match");
			}
		
			//validate payment method
		switch (cardType){
		case "Visa":
			if(cardNum.charAt(0)=='4'&& cardNum.length()== 16)
				break;
			else{
				foundInvalid = true;
				messages.put("cardNum", "<--Invalid card number");
				break;
			}
				
		case "MasterCard":
			if(cardNum.charAt(0)=='5'&& cardNum.length()== 16)
				break;
			else{
				foundInvalid = true;
				messages.put("cardNum", "<--Invalid card number");
				break;
			}
		case "Discover":
			if(cardNum.charAt(0)=='6'&& cardNum.length()== 16)
				break;
			else{
				foundInvalid = true;
				messages.put("cardNum", "<--Invalid card number");
				break;
			}
		case "Amex":
			if(cardNum.charAt(0)=='3'&& cardNum.length()== 15)
				break;
			else{
				foundInvalid = true;
				messages.put("cardNum", "<--Invalid card number");
				break;
			}
			default:
				break;			
		}
		
		}
		catch (NullPointerException e){
			foundInvalid = true;
			messages.put("cardType", "<--Select payment method");
		}
		catch (Exception e){
			System.out.println(e);
		}
		
		if(foundInvalid ){
			//if errors are found, return the form with error messages
			foundInvalid = false;
			request.setAttribute("messages", messages);		
			request.getRequestDispatcher("/show-parameters-get-form.jsp").include(request, response);
		}		
		else{
			printOut(response,request);
		}					
		out.close();		
	}
	
	  private void checkVali(String param, HttpServletRequest request) {
		  /*This method is to get the parameters, and then validate them from
		   * a file. If the value is not contained in the file, generate a error message 
		   */
		  String paramValue = request.getParameter(param);
		  Scanner scanner = null;
		  List <String> list=new ArrayList<>();
		  try{
			  if(param==null){
				  throw new NullPointerException();
			  }
				  
			  if(param.equals("firstName") || param.equals("lastName"))
				  //checkname.txt contains a list of names 
				  scanner= new Scanner(new File("C:\\Users\\PH\\workspace\\ValidateForm\\src\\checkname.txt"));
			  else 
				  //read from the files that contains other data
				  scanner= new Scanner(new File("C:\\Users\\PH\\workspace\\ValidateForm\\src\\" + param + ".txt"));				
				
			    while(scanner.hasNextLine()){
			        list.add(scanner.nextLine().toLowerCase().trim()); 
			    }
			    
			    if(!list.contains(paramValue.toLowerCase().trim())){
					foundInvalid = true;
					if(param.equals("zip"))
						messages.put(param, "<--Invalid " + param + " code");
					else
						messages.put(param, "<--Invalid " + param);
				}			    
		  }
		  catch (FileNotFoundException e){
			  //handle exception if file is not found
			  System.out.println(e);
		  }
		  catch (NullPointerException e){
			  foundInvalid = true;
			  messages.put(param, "<--This field is required!");
		  }
		  
		  scanner.close();
	}

	private boolean checkEmpty(HttpServletRequest request) {
		  Enumeration<String> paramNames = request.getParameterNames();		
			boolean found = false;
			while(paramNames.hasMoreElements()) {
			      String paramName = paramNames.nextElement();
			      String[] paramValues =
					        request.getParameterValues(paramName);
			      
			      //description and middle name can be empty
			      if (paramName.equals("initial") || paramName.equals("description"))
			    	  continue;
			      else{
			    	  if (paramValues.length == 1) {
				        String paramValue = paramValues[0];
				        if (paramValue.length() == 0 || paramValue.length()==1){
				        	messages.put(paramName, "<--This field is required!");
				        	found = true;
				        }
			    	  }			             	
			      }	
			}
			if (found)
				return true;
			else
				return false;
	}
	private void printOut(HttpServletResponse response, HttpServletRequest request)
			throws IOException{
		
		PrintWriter out = response.getWriter();
		String docType =
			      "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
			      "Transitional//EN\">\n";
			    String title = "Reading All Request Parameters";
			    out.println(docType +
			                "<HTML>\n" +
			                "<HEAD><TITLE>" + title + "</TITLE></HEAD>\n" +
			                "<BODY BGCOLOR=\"#FDF5E6\">\n" +
			                "<H1 ALIGN=CENTER>" + title + "</H1>\n" +
			                "<TABLE BORDER=1 ALIGN=CENTER>\n" +
			                "<TR BGCOLOR=\"#FFAD00\">\n" +
			                "<TH>Parameter Name<TH>Parameter Value(s)");
			    Enumeration<String> paramNames = request.getParameterNames();
			    while(paramNames.hasMoreElements()) {
			      String paramName = paramNames.nextElement();
			      if(paramName.equals("initial") || paramName.equals("description"))
		        		continue;
			      out.print("<TR><TD>" + paramName + "\n<TD>");
			      String[] paramValues =
			        request.getParameterValues(paramName);
			      
			      if (paramValues.length == 1) {
			        String paramValue = paramValues[0];
			        if (paramValue.length() == 0)
			        		out.println("<I>No Value</I>");  
			        else
			          out.println(paramValue);
			      } else {
			        out.println("<UL>");
			        for(int i=0; i<paramValues.length; i++) {
			          out.println("<LI>" + paramValues[i]);
			        }
			        out.println("</UL>");
			      }
			    }
			    out.println("</TABLE>\n</BODY></HTML>");	  		
	}
	
	private void checkNum(String str) {
		try{
			if(str == null)
				throw new NullPointerException();
			else
				Integer.parseInt(str);
		}
		catch (NumberFormatException e){
			foundInvalid = true;
			messages.put("itemNum", "<--Invalid item number!");
		}
		catch (NullPointerException e){
			foundInvalid = true;
			messages.put("itemNum", "<--This field is required!");
		}

	}

	@Override
	  public void doPost(HttpServletRequest request,
	                     HttpServletResponse response)
	      throws ServletException, IOException {
	    doGet(request, response);
	  }
	  
	}
