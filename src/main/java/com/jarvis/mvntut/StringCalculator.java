package com.jarvis.mvntut;
import java.util.List;
import java.util.ArrayList;

public class StringCalculator {

    public static String[] parseDelimiters(String numbers) {
    	//[--][+-][,]\n
    	char stopFlag = '\n';
    	int curIdx = numbers.indexOf("//") + 2;
    	String curDelim = "";
    	List<String> delims = new ArrayList<String>();
    	while(numbers.charAt(curIdx) != stopFlag) {
    		char chr = numbers.charAt(curIdx);
    		if(chr == '[') {
    			curDelim = "";
    		} else if(chr == ']') {
    			delims.add(curDelim);
    		} else {
    			curDelim += chr;
    		}
    		++ curIdx;
    	}
    	return delims.toArray(new String[0]);
    }
    
    public static String buildDelimiter(String[] delimiters) {
//    	String res = "";
//    	for(String delim : delimiters) {
//    		res += delim;
//    		res += "|";
//    	}
//    	return res;
    	return String.join("|", delimiters);
    }

    public static int add(final String numbers) {
        String delimiter = ",|n";
        String numbersWithoutDelimiter = numbers;
        if (numbers.startsWith("//")) {
        	char signChr = numbers.charAt(numbers.indexOf("//") + 2);
        	if(signChr == '[') {
        		delimiter = buildDelimiter(parseDelimiters(numbers));
        	} else {
        		delimiter = signChr + "";
        	}
            numbersWithoutDelimiter = numbers.substring(numbers.indexOf("\n") + 1);
        }
        return add(numbersWithoutDelimiter, delimiter);
    }

    private static int add(final String numbers, final String delimiter) {
        int returnValue = 0;
        String[] numbersArray = numbers.split(delimiter);
        List negativeNumbers = new ArrayList();

        for (String number : numbersArray) {
            if (!number.trim().isEmpty()) {
                int numberInt = Integer.parseInt(number.trim());
                if(numberInt > 1000) {
                    continue;
                } else if(numberInt < 0) {
                    negativeNumbers.add(numberInt);
                }
                returnValue += numberInt;
            }
        }

        if(negativeNumbers.size() > 0) {
            throw new RuntimeException("Negatives not allowed: " + negativeNumbers);
        }
        return returnValue;
    }



}