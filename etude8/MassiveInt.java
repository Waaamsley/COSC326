import static java.lang.Math.abs;
import java.util.*;


    public class MassiveInt{
          private ArrayList<Integer> number;
          private boolean negative = false;


        public MassiveInt(){
        }
        
        public MassiveInt(String str){
            int mod = 48;
           this.number = new ArrayList<Integer>();
            boolean zeroPadding = false;

            if (str.length() == 1 && str.charAt(0) == '0') {
               this.number.add(0);
            } else if (!str.isEmpty() && str.charAt(0) != '-') {
                for (int i = 0; i < str.length(); i++) {
                    if (str.charAt(0) == '0') {
                        if (i == 0) {
                            zeroPadding = true;
                        } else if (str.charAt(i) != '0') {
                            zeroPadding = false;
                        }
                    }
                    if (!zeroPadding) {
                       this.number.add(str.charAt(i) - mod);
                    }
                }
            } else {
               this.negative = true;
                for (int i = 1; i < str.length(); i++) {
                    if (str.charAt(1) == '0') {
                        if (i == 1) {
                            zeroPadding = true;
                        } else if (str.charAt(i) != '0') {
                            zeroPadding = false;
                        }
                    } if (!zeroPadding) {
                        if (str.charAt(i) != '-') {
                            this.number.add(str.charAt(i) - mod);
                        }
                    }
                }      
            }
            if(this.number.size()==0){
              this.negative = false;
                this.number.add(0);
            }
        }
        
        
     public MassiveInt(ArrayList<Integer> number) {
        this.number= new ArrayList<Integer>(number);
    }

    public void negate() {
        this.negative = true;
    }
        public void pos(){
            this.negative = false;
        }

        public static  int compareTo(MassiveInt n1, MassiveInt n2){
            ArrayList<Integer> num1 = n1.number;
            ArrayList<Integer> num2 = n2.number;

            if(!n1.negative && n2.negative){;
                return 1;
            }else if(n1.negative && !n2.negative){
                return -1;
            } else if(num1.size() > num2.size()){
                return 1;
                   
            }else if(num1.size() < num2.size()){
                return -1;
            } else{
                int comparision = 0;
                for(int i = 0 ; i < num1.size() ; i++){
                    if(num1.get(i).compareTo(num2.get(i)) == -1){
                        return -1;
                    }else if(num1.get(i).compareTo(num2.get(i)) == 1){
                        return 1;
                    }
                }
            }
            return 0;
        }
        
        
        public static MassiveInt addition(MassiveInt n1, MassiveInt n2){
          ArrayList<Integer> additions = new ArrayList<Integer>();
          ArrayList<Integer> num1 = n1.number;
          ArrayList<Integer> num2 = n2.number;
          boolean swapped = false;
          
          if(num1.size() < num2.size()){
            Integer[] temp = num1.toArray(new Integer[num1.size()]);
            num1 = num2;
            num2 = new ArrayList<Integer>(Arrays.asList(temp));
            swapped = true;
          }else if(num1.size() == num2.size()){
              for(int i = 0 ; i < num2.size() ; i++){
                  if(num2.get(i) > num1.get(i)){
                      swapped = true;
                      
                  }else if(num2.get(i) == num1.get(i)){
                      i = num2.size();
                  }
              }
          }
          if(n1.negative && !n2.negative){
              n1.pos();
              if(swapped){
                  return subtraction(n2,n1);
              } else{
                  return subtraction(n2,n1);
              }
          }else if(!n1.negative && n2.negative){
              n2.pos();
              if(swapped){
                  return subtraction(n1,n2);
              }
              else{
                  return subtraction(n2,n1);
              }
          }
          int index1 = num1.size();
          int count = 0;
          int index2 = num2.size();

          Integer remainder = 0;
          Integer sum =0;
          Collections.reverse(num1);
          Collections.reverse(num2);
          for(int i = 0; i < index2 ; i++){
              sum = num1.get(i) + num2.get(i) + remainder;
          additions.add(sum % 10);
          remainder = (sum >= 10 ? 1 : 0);;
  
          count++;
          }
          int ind1 = index1;
          while(remainder >= 1){             
              int copy = ind1-index2;
              if(copy == 0){
                  additions.add(remainder);
                  remainder = 0;
              }else{
                  for(int i = index2 ; i < index1 ; i++){
                      sum = num1.get(i) +remainder;
                      additions.add(sum%10);
                      remainder = (sum >= 10 ? 1 : 0);
                      ind1--;
                      count++;
                  }
              }
          }
          
          while(count < index1){
               additions.add(num1.get(count));
            count++;
          }
         
          Collections.reverse(additions);
          
          StringBuilder sb = new StringBuilder();
          if(n1.negative){
            sb.append("-");
          }
          for(Integer i  : additions){
            sb.append(i.toString());
          }
          return new MassiveInt(sb.toString());
        }


        public static MassiveInt multiply(MassiveInt n1, MassiveInt n2){
            MassiveInt big;
            MassiveInt small;
            MassiveInt one = new MassiveInt("1");
            MassiveInt result = new MassiveInt("0");
            boolean sm = false;
            if (compareTo(n1, n2) >= 0){
                big = new MassiveInt(n1.toString());
                small = new MassiveInt(n2.toString());
            }else{
                big = new MassiveInt(n2.toString());
                small = new MassiveInt(n1.toString());
                sm = true;
            }

            boolean negi = false;
            if (n1.negative && !n2.negative || !n1.negative && n2.negative){
                negi = true;
            }
            big.negative = false;
            small.negative = false;
            
            ArrayList<MassiveInt> tens = new ArrayList<MassiveInt>();
            tens.add(big);
            int count = 0;
            int count2 = 0;
            MassiveInt resTemp = new MassiveInt("0");
            MassiveInt couTemp = new MassiveInt("0");
            MassiveInt counter = new MassiveInt("0");
            int t = 10;
            boolean neg = false;
            
            
            while(compareTo(counter, small) < 0){
                resTemp = new MassiveInt(result.toString());
                couTemp = new MassiveInt(counter.toString());
                result = addition(result, new MassiveInt(tens.get(count2).toString()));
                count++;
                counter = addition(counter, new MassiveInt(one.toString()));
                if(count == t){
                    one = multHelp(one);
                    count2++;
                    count = 0;
                    t = 9;
                    tens.add(new MassiveInt(result.toString()));
                }
                //System.out.println(tens.get(count2).toString() + " " + one.toString() + " " + counter.toString()+ " " + small.toString());
            }

            //System.out.println("\n" + resTemp.toString() + "\n" + result.toString() + "\n" + couTemp.toString() + " " + counter.toString());
            
            MassiveInt remaining = subtraction(small, new MassiveInt(couTemp.toString()));
            
            //System.out.println(remaining.toString());
            result = new MassiveInt(resTemp.toString());
            //System.out.println(result.toString());
            for (int i = 0; i < remaining.number.size(); i++){
                for (int j = 0; j < remaining.number.get(i); j++){
                    //System.out.println(new MassiveInt(tens.get(remaining.number.size() - i - 1).toString()));
                    result = addition(result, new MassiveInt(tens.get(remaining.number.size() - i - 1).toString()));
                }
            }

            if (negi){
                result.negative = true;
            }
            return result;
            
        }

        public static MassiveInt multHelp(MassiveInt tenMe){
            MassiveInt res = new MassiveInt("0");
            for (int i = 0; i < 10; i++){
                res = addition(res, new MassiveInt(tenMe.toString()));
            }
            return res;
        }
            
            
        public static MassiveInt subtraction(MassiveInt n1, MassiveInt n2){
            ArrayList<Integer> nu1 = n1.number;
            ArrayList<Integer> nu2 = n2.number;
            final long LONG_MASK = 0xffffffffL;
            int[] big;
            int[] small;
            int [] num2 = new int[nu2.size()];
            int [] num1 = new int[nu1.size()];
            if(n1.negative && !n2.negative){
              n2.negate();
              return addition(n1,n2);
            }
            else if(!n1.negative && n2.negative){
              n2.pos();
              return addition(n1,n2);
            }else{
              
              

            for(int i = 0 ; i < nu1.size() ; i++){
                if(nu1.get(i) != null){
                    num1[i] = nu1.get(i);
                }
            }
            for(int i = 0 ; i < nu2.size() ; i++){
                if(nu2.get(i) != null){
                    num2[i] = nu2.get(i);
                }
            }
            int index1 = num1.length;
            int index2 = num2.length;
            boolean swap = false;
            if(num1.length < num2.length){
                swap = true; 
            }
            else if(num1.length < num2.length || num1.length == num2.length){        
                for(int i = 0 ; i < num2.length; i++){
                    if(num2[i] < num1[i]){
                        i = num2.length;
                    
                    }else if (num2[i] == num1[i]){
                    }else{
                        swap = true;
                        i = num2.length;
                    }
                    }
            }
            if(swap){
                big = num2;
                small = num1;
            }else{
                big = num1;
                small =num2;
            }
            index1 = big.length;
            index2 = small.length;
            
            int result[] = new int[index1];
            long difference = 0;
               for(int i : big){
                   //System.out.print(i);
            }
               //System.out.println();
            for(int i : small){
                //System.out.print(i);
            }
            //System.out.println();
            
            while(index2 > 0) {
                
                difference = (big[--index1] & LONG_MASK) -
                    (small[--index2] & LONG_MASK) + (difference >> 32);
                result[index1] = (int)difference;
            }

            // Subtract remainder of longer number while borrow propagates
            boolean borrow = (difference >>32 != 0);
            while(index1 > 0 &&  borrow){
                borrow = ((result[--index1] = big[index1] - 1) == -1);
            
            }
                   
            // Copy remainder of longer number
            while (index1 > 0){
                result[--index1] = big[index1];
            }

        
            StringBuilder sb = new StringBuilder();
            if(swap == true){
                sb.append("-");
                swap = false;
            }
            for(int i : result){
                if(i < 0){
                    sb.append(10+i);
                }else{
                    sb.append(i);
                }
            }
               
            return new MassiveInt(sb.toString());
            }
        }

        public static String[] divide(MassiveInt n1, MassiveInt n2){;
            if (n2.toString().equals("0")){
                String[] a = new String[2];
                a[0] = "0";
                a[1] = "0";
                return a;
            }
            ArrayList<Integer> num1 = n1.number;
            ArrayList<Integer> num2 = n2.number;
            ArrayList<Integer> div;
            String[] result = new String[2];
            StringBuilder sb = new StringBuilder();
            boolean range = false;
            boolean size = false;
            
            if(num2.size() > num1.size()){
                result[0] = "0";
                result[1] =n1.toString();
                return result;
            }
          else if(num2.size() == 1 && num2.get(0) <= 0){
                result[0] = "-1"; 
                return  result;
            }
          else  if(num1.size() == num2.size()){        
                for(int i = 0 ; i < num2.size(); i++){
                    if(num2.get(i) > num1.get(i)){
                        result[0] = "0";  
                        result[1] =n1.toString();
                        return  result;
                    }
                }
            }
            boolean negi = false;
            if (n1.negative && !n2.negative || !n1.negative && n2.negative){
                negi = true;
            }
            n1.negative = false;
            n2.negative = false;
            int c = 0;
            MassiveInt count = new MassiveInt("10");
            MassiveInt high = new MassiveInt("10");
            MassiveInt low = new MassiveInt("0");
            MassiveInt diff = new MassiveInt("10");
            MassiveInt temp = new MassiveInt("0");
            MassiveInt up = new MassiveInt("100");
            MassiveInt zero = new MassiveInt("0");
            MassiveInt rem = (subtraction(n1, multiply(n2, count)));
            MassiveInt desire = new MassiveInt(n2.toString());
            boolean down = false;
            while((compareTo(rem, desire) >= 0 || compareTo(rem, zero) < 0)){
 
                //System.out.println("[]-[]-[]-[]-[]");
                //System.out.println(rem.toString() + " " + count.toString() + " " + low.toString() + " " + high.toString());
                if (compareTo(rem, zero) < 0){
					//System.out.println("h1");
                    high = new MassiveInt(count.toString());
                    diff = subtraction(high, new MassiveInt(low.toString()));
					//System.out.println(diff.toString());
                    diff =  truncate(diff);
					//System.out.println(diff.toString() + " " + low.toString());
                    down = true;
                    count = addition(diff, new MassiveInt(low.toString()));
                }else if (compareTo(rem, desire) >= 0){
					//System.out.println("h2");
                    if (down){
                        low = new MassiveInt(count.toString());
                        diff = subtraction(high, low);
                        diff = truncate(diff);
                        count = addition(diff, new MassiveInt(low.toString()));
                    }else{
                        low = new MassiveInt(high.toString());
                        count = multiply(count, up);
                        high = new MassiveInt(count.toString());
                    }
                }
                temp = multiply(n2, count);
                rem = subtraction(n1, temp);
                //c++;
                //System.out.println(rem.toString() + " " + desire.toString() + " " + count.toString());
            }
            if (negi){
                count.negative = true;
            }
            result[0] = count.toString();
            result[1] = rem.toString();
            
            return result;
        }
            

        public static MassiveInt truncate(MassiveInt n1){
			List<MassiveInt> past = new ArrayList<MassiveInt>();
            MassiveInt num = new MassiveInt(n1.toString());
			MassiveInt two = new MassiveInt("2");
			MassiveInt pastR = new MassiveInt("1");
			MassiveInt result = new MassiveInt("1");
			int count = 0;
			int count2 = 0;
			int t = 10;
			past.add(result);
			MassiveInt rDoubled = new MassiveInt("0");
			while (compareTo(rDoubled, num) < 0){
				pastR = new MassiveInt(result.toString());
				result = addition(result, new MassiveInt(past.get(count2).toString()));
				rDoubled = multiply(new MassiveInt(result.toString()), new MassiveInt(two.toString()));
				count++;
				if (count == t){
					t = 9;
					count = 0;
					count2++;
					past.add(new MassiveInt(result.toString()));
				}
			}
			
			MassiveInt one = new MassiveInt("1");
			result = new MassiveInt(pastR.toString());
			rDoubled = multiply(new MassiveInt(result.toString()), new MassiveInt(two.toString()));
			//while (compareTo(rDoubled, num) < 0){
				//result = addition(new MassiveInt(result.toString()), new MassiveInt(one.toString()));
				//rDoubled = multiply(new MassiveInt(result.toString()), new MassiveInt(two.toString()));
			//}
			return new MassiveInt(result.toString());
        }        
        public static MassiveInt gcd(MassiveInt n1, MassiveInt n2){
        MassiveInt a;
        MassiveInt b;

        if (compareTo(n1, n2) < 0) {
            a = n2;
            b = n1;
        } else {
            a = n1;
            b = n2;
        }

        String[] denominator;
        MassiveInt remainder;
        do {
            denominator= divide(a, b);
            remainder = new MassiveInt(denominator[1]);
            if (remainder.toString().compareTo("0") == 0) {
                break;
            }
            a = b;
            b = remainder;
        } while (true);

        return b;
        }

        @Override
        public String toString(){
            StringBuilder sb = new StringBuilder();
            if(negative){
                sb.append("-");
            }
            for(int i = 0 ; i < number.size(); i++){
                sb.append(abs(number.get(i)));
            }
            return sb.toString();
        }

    }



    
    
    
       
        
          
            


        
            