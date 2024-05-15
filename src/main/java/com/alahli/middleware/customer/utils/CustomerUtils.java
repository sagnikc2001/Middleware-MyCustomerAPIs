package com.alahli.middleware.customer.utils;

import org.springframework.stereotype.Component;

@Component
public class CustomerUtils {

	/**
	 * Converts a given encoded Arabic string to a readable Arabic string.
	 * 
	 * @param data The encoded Arabic string in the format "key:value"
	 * @return Readable Arabic string
	 */
	public String toArabicString(String data) {
		if (data == null || "".equals(data))
			return "";
		String[] dataParts = data.split(":");
		if (dataParts.length != 2)
			return "";
		String[] charCodes = dataParts[1].split(",");
		StringBuilder sbhex = new StringBuilder();
		for (int i = 0; i < charCodes.length; i++) {

			if ("".equals(charCodes[i])) {
				charCodes[i] = "0";
			}
			if (Integer.parseInt(charCodes[i].trim()) >= 16)
				sbhex.append(Integer.toHexString(Integer.parseInt(charCodes[i].trim())));
		}

		StringBuilder sb = new StringBuilder();
		String str = sbhex.toString();
		for (int i = 0; i < str.length(); i = i + 2) {
			String tempstr = str.substring(i, i + 2);
			if (tempstr.charAt(0) == 'b')
				tempstr = "066" + tempstr.substring(1, 2);
			else if (tempstr.charAt(0) == 'c')
				tempstr = "062" + tempstr.substring(1, 2);
			else if (tempstr.charAt(0) == 'd') {
				/*
				 * if(tempstr.equals( "DB")) tempstr = "06AC"; else if(tempstr.equals( "DC"))
				 * tempstr = "06AD"; else if(tempstr.equals( "DD")) tempstr = "06AE";
				 * if(tempstr.equals( "DE")) tempstr = "0635"; else if(tempstr.equals( "DF"))
				 * tempstr = "06B0";
				 */
				if (tempstr.equals("db") || tempstr.equals("de") || tempstr.equals("dc") || tempstr.equals("dd")
						|| tempstr.equals("df"))
					tempstr = tempstr;
				else
					tempstr = "063" + tempstr.substring(1, 2);
			} else if (tempstr.charAt(0) == 'e')
				tempstr = "064" + tempstr.substring(1, 2);
			else if (tempstr.charAt(0) == 'f')
				tempstr = "065" + tempstr.substring(1, 2);
			int j;
			j = Integer.parseInt(tempstr, 16);

			char c = (char) j;
			sb.append(c);
			// char a = str.charAt(i);
			// char b = str.charAt(i + 1);
			// sb.append((char) ((hexToInt(a) << 4) | hexToInt(b)));
		}
		return sb.toString();
	}

	/**
	 * Converts a given hexadecimal string to its corresponding string
	 * representation.
	 * 
	 * @param hex The input hexadecimal string to be converted
	 * @return The string representation of the hexadecimal input
	 */

	public String convertHexToString(String hex) {

		StringBuilder sb = new StringBuilder();
		StringBuilder temp = new StringBuilder();
		temp.append("Decimal:");
		for (int i = 0; i < hex.length() - 1; i += 2) {
			// grab the hex in pairs
			String output = hex.substring(i, (i + 2));
			// convert hex to decimal
			int decimal = Integer.parseInt(output, 16);
			// convert the decimal to character
			sb.append((char) decimal);
			temp.append(decimal);
			temp.append(",");
		}
		// System.out.println(sb.toString());
		// return sb.toString();
		return temp.toString();
	}

}
