package clientServer;

public class AES {
	private static final int[][] sbox = {{0x63, 0x7c, 0x77, 0x7b, 0xf2, 0x6b, 0x6f, 0xc5, 0x30, 0x01, 0x67, 0x2b, 0xfe, 0xd7, 0xab, 0x76}, {0xca, 0x82, 0xc9, 0x7d, 0xfa, 0x59, 0x47, 0xf0, 0xad, 0xd4, 0xa2, 0xaf, 0x9c, 0xa4, 0x72, 0xc0}, {0xb7, 0xfd, 0x93, 0x26, 0x36, 0x3f, 0xf7, 0xcc, 0x34, 0xa5, 0xe5, 0xf1, 0x71, 0xd8, 0x31, 0x15}, {0x04, 0xc7, 0x23, 0xc3, 0x18, 0x96, 0x05, 0x9a, 0x07, 0x12, 0x80, 0xe2, 0xeb, 0x27, 0xb2, 0x75}, {0x09, 0x83, 0x2c, 0x1a, 0x1b, 0x6e, 0x5a, 0xa0, 0x52, 0x3b, 0xd6, 0xb3, 0x29, 0xe3, 0x2f, 0x84}, {0x53, 0xd1, 0x00, 0xed, 0x20, 0xfc, 0xb1, 0x5b, 0x6a, 0xcb, 0xbe, 0x39, 0x4a, 0x4c, 0x58, 0xcf}, {0xd0, 0xef, 0xaa, 0xfb, 0x43, 0x4d, 0x33, 0x85, 0x45, 0xf9, 0x02, 0x7f, 0x50, 0x3c, 0x9f, 0xa8}, {0x51, 0xa3, 0x40, 0x8f, 0x92, 0x9d, 0x38, 0xf5, 0xbc, 0xb6, 0xda, 0x21, 0x10, 0xff, 0xf3, 0xd2}, {0xcd, 0x0c, 0x13, 0xec, 0x5f, 0x97, 0x44, 0x17, 0xc4, 0xa7, 0x7e, 0x3d, 0x64, 0x5d, 0x19, 0x73}, {0x60, 0x81, 0x4f, 0xdc, 0x22, 0x2a, 0x90, 0x88, 0x46, 0xee, 0xb8, 0x14, 0xde, 0x5e, 0x0b, 0xdb}, {0xe0, 0x32, 0x3a, 0x0a, 0x49, 0x06, 0x24, 0x5c, 0xc2, 0xd3, 0xac, 0x62, 0x91, 0x95, 0xe4, 0x79}, {0xe7, 0xc8, 0x37, 0x6d, 0x8d, 0xd5, 0x4e, 0xa9, 0x6c, 0x56, 0xf4, 0xea, 0x65, 0x7a, 0xae, 0x08}, {0xba, 0x78, 0x25, 0x2e, 0x1c, 0xa6, 0xb4, 0xc6, 0xe8, 0xdd, 0x74, 0x1f, 0x4b, 0xbd, 0x8b, 0x8a}, {0x70, 0x3e, 0xb5, 0x66, 0x48, 0x03, 0xf6, 0x0e, 0x61, 0x35, 0x57, 0xb9, 0x86, 0xc1, 0x1d, 0x9e}, {0xe1, 0xf8, 0x98, 0x11, 0x69, 0xd9, 0x8e, 0x94, 0x9b, 0x1e, 0x87, 0xe9, 0xce, 0x55, 0x28, 0xdf}, {0x8c, 0xa1, 0x89, 0x0d, 0xbf, 0xe6, 0x42, 0x68, 0x41, 0x99, 0x2d, 0x0f, 0xb0, 0x54, 0xbb, 0x16}};
	private static final int[][] invertSbox = {{0x52, 0x09, 0x6a, 0xd5, 0x30, 0x36, 0xa5, 0x38, 0xbf, 0x40, 0xa3, 0x9e, 0x81, 0xf3, 0xd7, 0xfb}, {0x7c, 0xe3, 0x39, 0x82, 0x9b, 0x2f, 0xff, 0x87, 0x34, 0x8e, 0x43, 0x44, 0xc4, 0xde, 0xe9, 0xcb}, {0x54, 0x7b, 0x94, 0x32, 0xa6, 0xc2, 0x23, 0x3d, 0xee, 0x4c, 0x95, 0x0b, 0x42, 0xfa, 0xc3, 0x4e}, {0x08, 0x2e, 0xa1, 0x66, 0x28, 0xd9, 0x24, 0xb2, 0x76, 0x5b, 0xa2, 0x49, 0x6d, 0x8b, 0xd1, 0x25}, {0x72, 0xf8, 0xf6, 0x64, 0x86, 0x68, 0x98, 0x16, 0xd4, 0xa4, 0x5c, 0xcc, 0x5d, 0x65, 0xb6, 0x92}, {0x6c, 0x70, 0x48, 0x50, 0xfd, 0xed, 0xb9, 0xda, 0x5e, 0x15, 0x46, 0x57, 0xa7, 0x8d, 0x9d, 0x84}, {0x90, 0xd8, 0xab, 0x00, 0x8c, 0xbc, 0xd3, 0x0a, 0xf7, 0xe4, 0x58, 0x05, 0xb8, 0xb3, 0x45, 0x06}, {0xd0, 0x2c, 0x1e, 0x8f, 0xca, 0x3f, 0x0f, 0x02, 0xc1, 0xaf, 0xbd, 0x03, 0x01, 0x13, 0x8a, 0x6b}, {0x3a, 0x91, 0x11, 0x41, 0x4f, 0x67, 0xdc, 0xea, 0x97, 0xf2, 0xcf, 0xce, 0xf0, 0xb4, 0xe6, 0x73}, {0x96, 0xac, 0x74, 0x22, 0xe7, 0xad, 0x35, 0x85, 0xe2, 0xf9, 0x37, 0xe8, 0x1c, 0x75, 0xdf, 0x6e}, {0x47, 0xf1, 0x1a, 0x71, 0x1d, 0x29, 0xc5, 0x89, 0x6f, 0xb7, 0x62, 0x0e, 0xaa, 0x18, 0xbe, 0x1b}, {0xfc, 0x56, 0x3e, 0x4b, 0xc6, 0xd2, 0x79, 0x20, 0x9a, 0xdb, 0xc0, 0xfe, 0x78, 0xcd, 0x5a, 0xf4}, {0x1f, 0xdd, 0xa8, 0x33, 0x88, 0x07, 0xc7, 0x31, 0xb1, 0x12, 0x10, 0x59, 0x27, 0x80, 0xec, 0x5f}, {0x60, 0x51, 0x7f, 0xa9, 0x19, 0xb5, 0x4a, 0x0d, 0x2d, 0xe5, 0x7a, 0x9f, 0x93, 0xc9, 0x9c, 0xef}, {0xa0, 0xe0, 0x3b, 0x4d, 0xae, 0x2a, 0xf5, 0xb0, 0xc8, 0xeb, 0xbb, 0x3c, 0x83, 0x53, 0x99, 0x61}, {0x17, 0x2b, 0x04, 0x7e, 0xba, 0x77, 0xd6, 0x26, 0xe1, 0x69, 0x14, 0x63, 0x55, 0x21, 0x0c, 0x7d}};
	private static final int[][] matrix = {{2,1,1,3},{3,2,1,1},{1,3,2,1},{1,1,3,2}};
	private static final int[][] inverseMatrix = {{14,9,13,11}, {11,14,9,13}, {13,11,14,9}, {9,13,11,14}};
	private static final int[] cron = {0x8d, 0x01, 0x02, 0x04, 0x08, 0x10, 0x20, 0x40, 0x80, 0x1b, 0x36, 0x6c, 0xd8, 0xab, 0x4d, 0x9a, 0x2f, 0x5e, 0xbc, 0x63, 0xc6, 0x97, 0x35, 0x6a, 0xd4, 0xb3, 0x7d, 0xfa, 0xef, 0xc5, 0x91, 0x39, 0x72, 0xe4, 0xd3, 0xbd, 0x61, 0xc2, 0x9f, 0x25, 0x4a, 0x94, 0x33, 0x66, 0xcc, 0x83, 0x1d, 0x3a, 0x74, 0xe8, 0xcb, 0x8d, 0x01, 0x02, 0x04, 0x08, 0x10, 0x20, 0x40, 0x80, 0x1b, 0x36, 0x6c, 0xd8, 0xab, 0x4d, 0x9a, 0x2f, 0x5e, 0xbc, 0x63, 0xc6, 0x97, 0x35, 0x6a, 0xd4, 0xb3, 0x7d, 0xfa, 0xef, 0xc5, 0x91, 0x39, 0x72, 0xe4, 0xd3, 0xbd, 0x61, 0xc2, 0x9f, 0x25, 0x4a, 0x94, 0x33, 0x66, 0xcc, 0x83, 0x1d, 0x3a, 0x74, 0xe8, 0xcb, 0x8d, 0x01, 0x02, 0x04, 0x08, 0x10, 0x20, 0x40, 0x80, 0x1b, 0x36, 0x6c, 0xd8, 0xab, 0x4d, 0x9a, 0x2f, 0x5e, 0xbc, 0x63, 0xc6, 0x97, 0x35, 0x6a, 0xd4, 0xb3, 0x7d, 0xfa, 0xef, 0xc5, 0x91, 0x39, 0x72, 0xe4, 0xd3, 0xbd, 0x61, 0xc2, 0x9f, 0x25, 0x4a, 0x94, 0x33, 0x66, 0xcc, 0x83, 0x1d, 0x3a, 0x74, 0xe8, 0xcb, 0x8d, 0x01, 0x02, 0x04, 0x08, 0x10, 0x20, 0x40, 0x80, 0x1b, 0x36, 0x6c, 0xd8, 0xab, 0x4d, 0x9a, 0x2f, 0x5e, 0xbc, 0x63, 0xc6, 0x97, 0x35, 0x6a, 0xd4, 0xb3, 0x7d, 0xfa, 0xef, 0xc5, 0x91, 0x39, 0x72, 0xe4, 0xd3, 0xbd, 0x61, 0xc2, 0x9f, 0x25, 0x4a, 0x94, 0x33, 0x66, 0xcc, 0x83, 0x1d, 0x3a, 0x74, 0xe8, 0xcb, 0x8d, 0x01, 0x02, 0x04, 0x08, 0x10, 0x20, 0x40, 0x80, 0x1b, 0x36, 0x6c, 0xd8, 0xab, 0x4d, 0x9a, 0x2f, 0x5e, 0xbc, 0x63, 0xc6, 0x97, 0x35, 0x6a, 0xd4, 0xb3, 0x7d, 0xfa, 0xef, 0xc5, 0x91, 0x39, 0x72, 0xe4, 0xd3, 0xbd, 0x61, 0xc2, 0x9f, 0x25, 0x4a, 0x94, 0x33, 0x66, 0xcc, 0x83, 0x1d, 0x3a, 0x74, 0xe8, 0xcb};
	
	public static void byteSub(int[][] bytes) {
		for(int i = 0; i < bytes.length; i++) {
			for(int j = 0; j < bytes[i].length; j++) {
				bytes[i][j] = sbox[bytes[i][j]/16][bytes[i][j]%16];
			}
		}
	}
	
	public static void byteSub(int[] bytes) {
		int tmp;
		for(int i = 0; i < bytes.length; i++) {
			tmp =  bytes[i];
			bytes[i] = sbox[tmp/16][tmp%16];
		}
	}
	
	public static void invertByteSub(int[][] arr) {
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr[i].length; j++) {
				arr[i][j] = invertSbox[arr[i][j]/16][arr[i][j]%16];
			}
		}
	}
	
	public static void leftShift(int[] bytes) {
		int tmp = bytes[0];
		for(int i = 0; i < bytes.length-1; i++) {
			bytes[i] = bytes[i + 1];
		}
		bytes[bytes.length-1] = tmp;
	}
	
	public static void rightShift(int[] bytes) {
		int tmp = bytes[bytes.length - 1];
		for(int i = 2; i >=0; i--) {
			bytes[i + 1] = bytes[i];
		}
		bytes[0] = tmp;
	}
	
	public static void rightLeft(int[] bytes) {
		int tmp;
		for(int i = 0; i < 2; i++) {
			tmp = bytes[i + 2];
			bytes[i + 2] = bytes[i];
			bytes[i] = tmp;
		}
	}
	
	public static void shiftRows(int[][] subbedBytes) {
		for(int i = 0; i < subbedBytes.length; i++) {
			if(i % 4 == 1) {
				rightShift(subbedBytes[i]);
			}
			else if(i % 4 == 2) {
				rightLeft(subbedBytes[i]);
			}
			else if(i % 4 == 3){
				leftShift(subbedBytes[i]);
			}
		}
	}
	
	public static void invertShiftRows(int[][] subbedBytes) {
		for(int i = subbedBytes.length - 1; i >= 0; i--) {
			if(i % 4 == 3) {
				rightShift(subbedBytes[i]);
			}
			else if(i % 4 == 2) {
				rightLeft(subbedBytes[i]);
			}
			else if(i % 4 == 1){
				leftShift(subbedBytes[i]);
			}
		}
	}
	
	//copies the matrixes
	public static void copyMatrix(int[][] bytes, int[][] matrix) {
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[i].length; j++) {
				bytes[i][j] = matrix[i][j];
			}
		}
	}
	
	public static void mixColumns(int[][] bytes) {
		//TODO - fix mixColumns algorithm - wrong output
		int[][] retval = new int[bytes.length][bytes[0].length];
		for(int i = 0; i < bytes.length; i++) {
			for(int j = 0; j < 4; j++) {
				retval[i][j] = bytes[i][j];
			}
		}
		for(int i = 0; i < bytes.length; i++) {
			for(int j = 0; j < bytes[i].length; j++) {
					retval[i][j] = (bytes[i][0] * matrix[0][i]) ^ (bytes[i][1] * matrix[1][i]) ^ (bytes[i][2] * matrix[2][i]) ^ (bytes[i][3] * matrix[3][i]);
			}
		}
		for(int i = 0; i < bytes.length; i++) {
			for(int j = 0; j < 4; j++) {
				bytes[i][j] = retval[i][j];
			}
		}
	}
	
	public static void invertMixColumns(int[][] bytes) {
		//TODO - fix invertMixColumns - wrong output
		int[][] retval = new int[bytes.length][bytes[0].length];
		for(int i = 0; i < bytes[0].length; i++) {
			for(int j = 0; j < bytes.length; j++) {
				retval[i][j] = (bytes[i][0] * inverseMatrix[0][i]) ^ (bytes[i][1] * inverseMatrix[1][i]) ^ (bytes[i][1] * inverseMatrix[2][i]) ^ (bytes[i][3] * inverseMatrix[3][i]);
			}
		}
		copyMatrix(bytes, retval);
	}
	
	//initialize the array with the right size
	public static int[][] arrayStart(String msg){
		int length = msg.length();
		char[] bytes = msg.toCharArray();
		while(length % 4 != 0) {
			length++;
		}
		int[][] arr = new int[4][4];
		for(int i = 0, k = 0; i < arr.length; i++) {
			for(int j = 0; j < arr[i].length; j++) {
				if(k < msg.length()) {
					arr[i][j] = bytes[k];
					k++;
				}
				else {
					arr[i][j] = (char) length;
				}
			}
		}
		return arr;
	}
	
	//add extra bytes
	public static void padding(int[][] arr, int length) {
		if(length % 4 == 0) {
			return;
		}
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr[0].length; j++) {
				if(arr[i][j] == 0) {
					arr[i][j] = ' ';
				}
			}
		}
	}
	
	//prints bytes matrix as hex
	public static void byteToHex(int[][] arr) {
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr[i].length; j ++) {
				System.out.print(String.format("%02X", arr[i][j]));
			}
		}
		System.out.println('\n');
	}
	
	public static int[][] getKey(String key) {
		int[][] keyMatrix = new int[4][4];
		for(int i = 0, l = 0; i < keyMatrix.length; i++) {
			for(int j = 0; j < keyMatrix[i].length; j++, l++) {
				keyMatrix[i][j] = (int) key.charAt(l);
			}
		}
		return keyMatrix;
	}
	
	public static int[][] expandKey(int[][] arr) {
		int[][] key = new int[arr.length*10][arr[0].length];
		copyMatrix(key, arr);
		int cronIndex = 0;
		for(int i = 4; i < key.length; i++) {
			if(i % 4 == 0) {
				key[i] = key[i - 4];
				rightShift(key[i]);
				byteSub(key[i]);
				for(int j = 0; j < key[i].length; j++) {
					key[i][j] ^= key[i][j];
				}
				key[i][0] ^= cron[cronIndex++];
			}
			else {
				for(int j = 0; j < key[i].length; j++) {
					key[i][j] = (key[i - 1][j] ^ key[i - 4][j]);
				}
			}
		}
		return key;
	}
	
	public static void addRoundKey(int[][] bytes, int[][] key, int round) {
		for(int i = 0; i < bytes.length; i++) {
			for(int j = 0; j < bytes[i].length; j++) {
				bytes[i][j] ^= key[i + round * 3][j];
			}
		}
	}
	
	public static String retString(int[][] arr) {
		char[] retVal = new char[arr.length * arr[0].length];
		for(int i = 0, l = 0; i < arr.length; i++) {
			for(int j = 0; j < arr[i].length; j++) {
				retVal[l] = (char) arr[i][j];
				l++;
			}
		}
		return new String(retVal);
	}
	
	public static String encrypt(String msg, String key) {
		int[][] keyMatrix = getKey(key);
		int[][] arr = arrayStart(msg);
		padding(arr, msg.length());
		keyMatrix = expandKey(keyMatrix);
		addRoundKey(arr, keyMatrix, 0);
		
		for(int i = 1; i < 9; i++) {
			byteSub(arr);
			shiftRows(arr);
			//mixColumns(arr);
			addRoundKey(arr, keyMatrix, i);
		}
		
		byteSub(arr);
		shiftRows(arr);
		addRoundKey(arr, keyMatrix, 9);
		
		return retString(arr);
	}
	
	public static String decrypt(String msg, String key) {
		int[][] keyMatrix = expandKey(getKey(key));
		int[][] arr = arrayStart(msg);
		addRoundKey(arr, keyMatrix, 9);
		invertShiftRows(arr);
		invertByteSub(arr);
		
		for(int i = 8; i > 0; i--) {
			addRoundKey(arr, keyMatrix, i);
			//invertMixColumns(arr);
			invertShiftRows(arr);
			invertByteSub(arr);
		}

		addRoundKey(arr, keyMatrix, 0);
		return retString(arr);
	}
	
	public static void printMatrix(int[][] matrix) {
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[i].length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	//TODO - remove main once complete (used for testing)
	public static void main(String[] args) {
		String msg = "abcdefghijklrewa";
		System.out.println(msg);
		String key = "abcdefghijklrewt";
		msg = encrypt(msg, key);
		System.out.println(msg);
		msg = decrypt(msg, key);
		System.out.print(msg);
	}

}
