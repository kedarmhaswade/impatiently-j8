package practice;

import java.util.*;

import static practice.Arity.BINARY;
import static practice.Arity.UNARY;
import static practice.Operator.*;
import static practice.Permutations.*;

enum Arity {
    UNARY,
    BINARY,
    TERNARY
}

enum Operator {
    SQ_ROOT(UNARY, "√"),
    NEGATION(UNARY, "~"),
    ADDITION(BINARY, "+"),
    SUBTRACTION(BINARY, "-"),
    MULTIPLICATION(BINARY, "×"),
    DIVISION(BINARY, "/"),
    EXPONENTIATION(BINARY, "^");
    // FACTORIAL
    // MOD
    private final Arity arity;
    private final String symbol;

    Operator(Arity arity, String symbol) {
        this.arity = arity;
        this.symbol = symbol;
    }

    public double perform(double first, double... rest) {
        switch (this) {
            case SQ_ROOT:
                return Math.sqrt(first);
            case NEGATION:
                return -first;
            case ADDITION:
                return first + rest[0];
            case SUBTRACTION:
                return first - rest[0];
            case MULTIPLICATION:
                return first * rest[0];
            case DIVISION:
                return first / rest[0]; // may throw ArithmeticException or return Double.POSITIVE_INFINITY or Double.NEGATIVE_INFINITY
            case EXPONENTIATION:
                return Math.pow(first, rest[0]);
            default:
                throw new RuntimeException(symbol + ": Operation not implemented yet");
        }
    }

    public Arity getArity() {
        return arity;
    }

    @Override
    public String toString() {
        return symbol;
    }

    public String asInfix(Object first, Object... rest) {
        switch (this) {
            case ADDITION:
            case SUBTRACTION:
            case MULTIPLICATION:
            case DIVISION:
            case EXPONENTIATION:
                return parenthesize(first + symbol + rest[0]);
            case SQ_ROOT:
            case NEGATION:
                return parenthesize(symbol + first);
            default:
                throw new RuntimeException(symbol + ": Operation not implemented yet");
        }
    }

    static String parenthesize(String m) {
        return "(" + m + ")";
    }
}

public class Fazlur {

    public static final List<Operator> ARITHMETIC_OPERATORS = List.of(ADDITION, SUBTRACTION, MULTIPLICATION, DIVISION); // could be a Set
    // UNARY operators are problematic, you can apply them over and over! For now, let me just apply it once.
    public static final List<Operator> POPULAR_OPERATORS = List.of(SQ_ROOT, ADDITION, SUBTRACTION, MULTIPLICATION, DIVISION);
    private final List<List<Object>> operandPermutations = new ArrayList<>();

    public static void main(String[] args) {
//        new Fazlur().solve(6, List.of(2, 2, 2));
//        new Fazlur().solve(6, List.of(3, 3, 3));
//        new Fazlur().solve(6, List.of(4, 4, 4));
//        new Fazlur().solve(6, List.of(5, 5, 5));
//        new Fazlur().solve(6, List.of(6, 6, 6));
//        new Fazlur().solve(6, List.of(7, 7, 7));
        new Fazlur().solve(6, List.of(8, 8, 8));
        new Fazlur().solve(6, List.of(9, 9, 9));
//        new Fazlur().solve(24, List.of(1, 3, 4, 6));
    }

    /*
     * <p>
     *     We can determine the number of operators that, when favorably arranged with operands, yields an expression that
     *     can be evaluated. For example, an expression with one binary operator and two operands can be evaluated;
     *     an expression with two binary operators and four operands, however, cannot be.
     * </p>
     * <p>
     *     Binary operators seem to render well-formed (solvable) expressions. A binary operator takes two operands and
     *     produces one result. The set that the operands and the result belong to is closed under the operation of the operator.
     *     To evaluate an expression with <code>n</code> operands, it should have exactly <code>n-1</code> binary operators.
     * </p>
     * <p>
     *     Unary operators complicate the evaluation of expressions because they can be applied to a single operand
     *     ad infinitum. This complicates the case where you have both unary and binary operators in an expression.
     * </p>
     * <p>
     *     We use heuristics to determine the number of operators.
     * </p>
     */

    public static final double evaluatePostfix(List<Object> expr) {
        // expr is something like [15, 8, 7, +, /] which evaluates to 1
        Deque<Double> stack = new ArrayDeque<>();
        for (int i = 0; i < expr.size(); i++) {
            Object obj = expr.get(i);
            if (obj instanceof Number) {
                if (obj instanceof Integer) {
                    stack.push(((Integer) obj).doubleValue());
                } else if (obj instanceof Double) {
                    stack.push(((Double) obj).doubleValue());
                } else {
                    throw new IllegalArgumentException("unexpected number type: " + obj.getClass());
                }
            } else if (obj instanceof Operator) {
                Operator op = (Operator) obj;
                double partial;
                if (op.getArity() == UNARY) {
                    double od = 0;
                    try {
                        od = stack.pop();
                    } catch (NoSuchElementException e) {
                        throw new RuntimeException("got a unary operator: " + op + ", but there are no operands");
                    }
                    partial = op.perform(od);
                    if (!Double.isFinite(partial)) {
                        throw new RuntimeException("partial result: " + partial + "operation: " + op + " on operand: " + od + " is not finite");
                    }
                    stack.push(partial);
                } else if (op.getArity() == BINARY) {
                    double od2 = 0; // the second operand pops first
                    try {
                        od2 = stack.pop();
                    } catch (NoSuchElementException e) {
                        throw new RuntimeException("got a binary operator: " + op + ", but there is no second operand");
                    }
                    double od1 = 0;
                    try {
                        od1 = stack.pop();
                    } catch (Exception e) {
                        throw new RuntimeException("got a binary operator: " + op + " and the second operand:" + od2 + " is available, but there is no first operand");
                    }
                    partial = op.perform(od1, od2);
                    if (!Double.isFinite(partial)) {
                        throw new RuntimeException("partial result: " + partial + "operation: " + op + " on operands: " + od1 + ", " + od2 + " is not finite");
                    }
                    stack.push(partial);
                } else {
                    throw new RuntimeException("unsupported operator: " + op + ", arity: " + op.getArity());
                }
            } else {
                throw new RuntimeException("can't evaluate the expression which has an unsupported object: " + obj);
            }
        }
        double result = stack.pop();
        if (!stack.isEmpty()) {
            throw new RuntimeException("bug: report the expression: " + expr);
        }
        return result;
    }

    public List<Object> solve(int a, List<Integer> operands) {
        System.out.println("operands: " + operands);
        solveWithBasicArithmetic(a, operands);
        solveWithPopular(a, operands);
        return null;
    }

    /**
     * <p>
     * Uses only the operators in the set of {@link Fazlur#ARITHMETIC_OPERATORS} for solution.
     * </p>
     *
     * @param a        the integer answer to which the operation is to be coerced
     * @param operands the list of integer operands
     */
    private void solveWithBasicArithmetic(int a, List<Integer> operands) {
        System.out.println("Using only the basic arithmetic operators");
        int nop = operands.size() - 1; // for n operands, we must have n-1 binary operators
        List<List<Operator>> operatorPermutations = kTuples(nop, ARITHMETIC_OPERATORS);
//        System.out.println("Number of operator perms: " + operatorPermutations.size());
//        System.out.println(operatorPermutations);
        evalPostfixes(a, operands, nop, operatorPermutations);
    }

    private void solveWithPopular(int a, List<Integer> operands) {
        // having both unary and binary operators complicates the matter!
        System.out.println("Using the basic arithmetic operators and the square-root operator");
        for (int nop = operands.size() - 2; nop <= operands.size() + 1; nop++) {
            System.out.println("number of operators: " + nop);
            List<List<Operator>> operatorPermutations = kTuples(nop, POPULAR_OPERATORS);
//            System.out.println("Number of operator perms: " + operatorPermutations.size());
//            System.out.println(operatorPermutations);
            evalPostfixes(a, operands, nop, operatorPermutations);
        }
    }

    public static void evalPostfixes(int a, List<Integer> operands, int nop, List<List<Operator>> allOperators) {
        int nod = operands.size();
        int expLen = nod + nop; // the length of the expression
        // consider operands : [6, 7, 8, 9] (length: nod)
        // binary operator permutation example: [+, ×, -] (length: nop)
        // expLen = nod + nop = 7
//        System.out.println("We can place " + nod + " operands in " + expLen + " locations in " + calculateNPK(expLen, nod) + " ways");
        int[][] odLocations = nPk(expLen, nod); // these covers all permutations of nod operands in expLen locations
//        System.out.println("locations: " + Arrays.deepToString(odLocations));
        //Optimization: We must have an operand in the 0th index of the expression for it to be a valid postfix expression
        odLocations = Arrays.stream(odLocations).filter(array -> Arrays.stream(array).anyMatch(n -> n == 0)).toArray(int[][]::new);
//        System.out.println("locations: " + Arrays.deepToString(odLocations));
        List<Object> oldExpr = null;
        for (int i = 0; i < odLocations.length; i++) {
            List<Object> expr = new ArrayList<>(Collections.nCopies(expLen, null));
            int[] odArr = odLocations[i];
            // an example arrangement: [1, 4, 0, 5] (has one 0)
            for (int j = 0; j < odArr.length; j++) {
                expr.set(odArr[j], operands.get(j));
            }
            // operands are placed; example expr is now [8, 6, null, null, 7, 9, null]
            //                                            0    1  2   3    4  5  6
            if (expr.equals(oldExpr)) {
                continue; // Optimization: we have already seen these operators
            }
            for (List<Operator> operatorPermutation : allOperators) {
                // example operatorPermutation: [+, ×, -]
                assert nop == operatorPermutation.size() : "Permutation: " + operatorPermutation + "size mismatch; exp: " + nop + ", actual: " + operatorPermutation.size();
//                    System.out.println("operator perm: " + operatorPermutation);
                int k = 0;
                for (int j = 0; j < expLen; j++) {
                    if (expr.get(j) == null) {
                        expr.set(j, operatorPermutation.get(k));
                        k++;
                    }
                }
                assert expr.stream().noneMatch(Objects::isNull) : "no expression element may be null";
                // example expr is now [8, 6, +, ×, 7, 9, -]; evaluate it (of course it may be an invalid postfix expression)
                try {
                    double result = evaluatePostfix(expr);
//                        System.out.println(expr + " = " + result);
//                        System.in.read();
                    if (result == a) {
                        System.out.println("Bingo! " + expr + " evaluates to: " + a);
                    } else {
                        //System.out.println(expr + " evaluates to: " + result);
                    }
                } catch (Exception e) {
                    //System.err.println("ignore invalid expression: " + expr);
                } finally {
                    // reset the operators
                    for (int j = 0; j < expLen; j++) {
                        if (expr.get(j) instanceof Operator) {
                            expr.set(j, null);
                            k++;
                        }
                    }
                    oldExpr = expr;
                }
            }
        }
    }

    private void populateOperands(List<Object> perm) {
        List<Object> cp = new ArrayList<>(perm);
        operandPermutations.add(cp);
    }
}