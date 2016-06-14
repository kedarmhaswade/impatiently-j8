package tmp;

/**
 * Created by kmhaswade on 4/20/16.
 */
class B{
    private int total;
    public void add(int amount)throws Exception{
        if(amount < 0)
            throw new Exception("Amount is negative");
        total += amount;
    }
    public int getTotal() {
        return total;
    }
}
public class A {
    public B b = new B();
    public void addToB(int amount)throws Exception{
        try{
            b.add(amount);
        }catch(Exception ex){
            try{
                b.add(-amount);
            }catch(Exception ex2){
            }
            throw new Exception("Amount was negative. It was inverted and then added.");
        }

    }
    public int getTotalFromB() {
        return b.getTotal();
    }

    public static void main(String[] args) {
        A a = null;
        try {
            a = new A();
            a.addToB(10); // no exception here, total should be 10
            a.addToB(-10);
        } catch (Exception e) {
            // exception here, but the total should be 20, or not?
            System.out.println(a.getTotalFromB());
        }
    }
}