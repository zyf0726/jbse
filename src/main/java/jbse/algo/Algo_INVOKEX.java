package jbse.algo;


import java.util.function.Supplier;

/**
 * Algorithm for the invoke* bytecodes
 * (invoke[interface/special/static/virtual]).
 *  
 * @author Pietro Braione
 */
final class Algo_INVOKEX extends Algo_INVOKEX_NoData<BytecodeData_1ZME> {
    public Algo_INVOKEX(boolean isInterface, boolean isSpecial, boolean isStatic) {
        super(isInterface, isSpecial, isStatic);
    }

    @Override
    protected Supplier<BytecodeData_1ZME> bytecodeData() {
        return () -> BytecodeData_1ZME.withInterfaceMethod(this.isInterface).get();
    }
}
