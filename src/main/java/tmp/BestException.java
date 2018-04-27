package tmp;

import java.sql.*;
class BestException {
    public final void doStuff(int val) throws SQLException {
        if (val < 0) throw new NullPointerException();
        //if (val < 1) throw new IOException();
        if (val < 2) throw new OutOfMemoryError();
    }
}
