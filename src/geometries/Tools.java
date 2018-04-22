package geometries;

/**
 * This Class contains useful tools and methods for the geometries packages.
 */
public class Tools {

    //Matrix operations on Algebra ???

    /**
     * Helper function to check equality with a multiple value in the triangle.
     * @param x The object to verify.
     * @param valueToVerify Array of all the values to verify.
     * @param valueChecked Vector contains all values checked.
     * @return True: if value was equals in a value in the array valueToverify.
     */
    public static boolean checkedEqualOneToMany(Object x, Object[] valueToVerify, java.util.Vector valueChecked){

        for (int i=0;i<valueToVerify.length;i++) {

            if(!valueChecked.contains(i)) { //if the value was checked don't check

                if(x.equals(valueToVerify[i]))  { //If equals
                    valueChecked.add(i); //Add the index
                    return true;
                }
            }
        }
        return false;
    }


    public static boolean sameSign(double[] valueToveify){

        boolean positif=(valueToveify[0]>0)?true:false;

        for (int i=0; i<valueToveify.length;i++) {

            if(i!=0){
                if((valueToveify[i]>0 && !positif) || (valueToveify[i]<0 && positif))
                    return false;

                positif=(valueToveify[i]>0)?true:false;
            }

        }
        return true;
    }





}
