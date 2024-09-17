import java.math.BigInteger;
import java.util.List;

public class Houvarda_Eleni_Base58Check {
    private static final String code_string = "123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz";
    private static final BigInteger FIFTY_EIGHT = BigInteger.valueOf(58);
    
    private static String convert_bytes_to_big_integer(String hash) {
            BigInteger x;
            int base = 16;

            //count number of leading zeros
            int leadZero = 0;
            while(hash.charAt(leadZero) == '0'){
                leadZero++;
            }

            //ignore leading zeros
            if (hash.substring(0, 2).equals("0x")) {
                hash = hash.substring(2);
            }
            x = new BigInteger(hash,base);
 
            //convert to base58
            StringBuilder output_string = new StringBuilder();
            while (x.compareTo(BigInteger.valueOf(0))> 0) {
                int  remainder =  x.mod(FIFTY_EIGHT).intValue();
                output_string.append(code_string.charAt(remainder));
                x = x.divide(FIFTY_EIGHT);
            }

            //add one's at the end for leading zeros
            while(leadZero>=2){
                output_string = output_string.append('1');
                leadZero-=2;
            }

            //reverse string
            return output_string.reverse().toString();
        }
    
        public static void main(String[] args) {
    
           //test cases
           String ex1 = convert_bytes_to_big_integer("12345678");
           String ex2 = convert_bytes_to_big_integer("0012345678");
           String ex3 = convert_bytes_to_big_integer("005729f7d356615e74174c3e46fbb747b36523e2bad50b2737");
           String ex4 = convert_bytes_to_big_integer("6fa6d9ee57dfb82898a61758fb53fa2223999fd3f557d9e675");
           String ex5 = convert_bytes_to_big_integer("8024606dc6ece601de7e54be7cc706eb2e26a5b87fa3389858ce0b078d461b34057ef522c5");

           //results
           System.out.printf("hash: %s b58: %s\n","12345678", ex1);
           System.out.printf("hash: %s b58: %s\n","0012345678", ex2);
           System.out.printf("hash: %s b58: %s\n","005729f7d356615e74174c3e46fbb747b36523e2bad50b2737", ex3);
           System.out.printf("hash: %s b58: %s\n","6fa6d9ee57dfb82898a61758fb53fa2223999fd3f557d9e675", ex4);
           System.out.printf("hash: %s b58: %s\n","8024606dc6ece601de7e54be7cc706eb2e26a5b87fa3389858ce0b078d461b34057ef522c5", ex5);
        }
    }
