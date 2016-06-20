package validation;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckInput{
	
	public static String isNumber(String number){
		String expression = "^[0-9]*$";
        CharSequence inputStr =number;
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(inputStr);
       
	        	if(matcher.matches());
	            else
	            	System.err.println(number + " is not a number!");
              
        return number;
	}
	/*----------------Accept all character include symbol------------------*/
	public static String isCharacter(String text){
		try {
			Integer.parseInt(text);
			System.err.println(text + " is not a character or special character!");
		} catch (Exception nfe) {}
		
		return text;
	}
	/*-----------------Accept only character-------------------------*/
	public static String isOnlyChar(String string){
		String expression = "^[a-zA-Z]*$";
        CharSequence inputStr = string;
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(inputStr);
        if(matcher.matches());
        else
        		System.err.println(string + " is not a character!");
        
        return string;
	}
	/*------------------------Check digit for input-----------------------*/
	public static String isCheckDigit(String string,int digit,String message){
		char[] name=string.toCharArray();
		if(name.length<=digit)
			System.out.println(string);
		else
			System.err.println("You can input " + digit + "only!");
		
		return string;
	}
	/*--------------------Check rule to input email----------------*/
	public static String isValidEmail(String email,String message){
		String regex="^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
		CharSequence inputString=email;
		Pattern pattern=Pattern.compile(regex);
		Matcher match=pattern.matcher(inputString);
		if(match.matches());
		else System.err.println(email + " is not valid email rule!");
			
		return email;
	}
	/*---------------------Check input gender----------------*/
	public static String isValidGender(String gender,String message){
		String regex= "^M(ale)?$|^m(ale)?$|^F(emale)?$|^f(emale)?$";
		CharSequence inputString=gender;
		Pattern pattern=Pattern.compile(regex);
		Matcher match=pattern.matcher(inputString);
		if(match.matches());
		else System.err.println("Can input only gender(M/Male,F/Female)!");
		
		return gender;
	}
	/*--------Check to input phone number and return like (0xx)-xxx-xxx------*/
	public static String isPhoneNumber(String phoneNumber,String message){
		String expression = "^[0-9]{9,10}$";
        CharSequence inputStr = phoneNumber;
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(inputStr);
        if(matcher.matches());
        else
        	System.err.println(phoneNumber + " is not a phone number!");
        
        return String.valueOf(phoneNumber).replaceFirst("(\\d{3})(\\d{3})(\\d+)", "($1)-$2-$3");
	}
	
	public static boolean Confirmation(String arg){
		///add your code here
		while(true){
			System.out.print(arg+"[y/n]? ");
			@SuppressWarnings("resource")
			String s=new Scanner(System.in).next();
			switch(s.toUpperCase()){
			case "Y":return true;
			case "N":return false;
			default:break;
			}
		}
	}
	/*public int inputData(String str,String str1,String message){  
		Scanner in=new Scanner(System.in);
        while(!in.hasNextInt()){
            System.out.println(str1);
            System.out.print(str);
            in.next();
        }
        int data = in.nextInt();
        return data;
	}*/
}
