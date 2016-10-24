package practice;

import java.util.Arrays;

/**
 * <p>
 *     Given a list of salaries of employees and a target 'payroll', find the salary cap (the salary
 *     that each employee receives) that satisfies the employees optimally. Specifically, any employee
 *     who used to get a salary that is less than the cap is unaffected and any employee who used to
 *     get a salary more than the cap is moderately affected. There is no requirement that they are
 *     affected in proportion of their salaries.
 * </p>
 * <p>
 *     There are two equivalent approaches:
 *     <ul>
 *         <li>
 *             Standard Binary Search based technique which relies on invariant
 *             minSalary &lt;= cap &lt;= maxSalary and a tolerance value (epsilon, say 1e-4), or
 *         </li>
 *         <li>
 *             A more analytical solution that is based on the observation that the payroll
 *             increases linearly as cap, as long as the cap does not exceed anyone's salary.
 *         </li>
 *     </ul>
 * </p>
 * Created by kedar on 10/23/16.
 */
public class SalaryCap {

    static double analytical(int[] salaries, int payroll) {
        Arrays.sort(salaries);
        int n = salaries.length;
        int runningSum = 0;
        for (int i = 0; i < n; i++) {
            int curr = salaries[i];
            runningSum += curr;
            int linear = curr * n;
            if (linear == payroll) {
                return curr; // the cap is the current salary
            } else if (linear < payroll &&
                    ((i + 1) < n && (salaries[i + 1] * n > payroll))) {
                // salaries[i] < cap < salaries[i+1]
                int rem = n - i - 1;
                assert rem > 0;
                return (payroll - runningSum) * 1.0 / rem;
            } else {
                // do nothing
            }
        }
        throw new AssertionError("exhausted, n: " + n + ", running sum: " + runningSum);
    }
}
