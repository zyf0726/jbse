package jbse.tree;

import jbse.val.Expression;
import jbse.val.Reference;
import jbse.val.ReferenceSymbolic;

/**
 * {@link DecisionAlternative_XALOAD} for the case a read access to an array
 * returned a {@link ReferenceSymbolic} to an object in the heap that has 
 * been discovered earlier in the symbolic execution.
 * 
 * @author Pietro Braione
 */
public final class DecisionAlternative_XALOAD_Aliases 
extends DecisionAlternative_XALOAD_Unresolved implements DecisionAlternative_XYLOAD_GETX_Aliases {
    private final long objectPosition;
    private final int hashCode;

    /**
     * Constructor.
     * 
     * @param arrayAccessExpression the array access {@link Expression}.
     * @param referenceToResolve the {@link ReferenceSymbolic} loaded from the array.
     * @param fresh {@code true} iff {@code valToLoad} is fresh, i.e., 
     *        is not stored in the array and, therefore, must be written
     *        back to the array.
     * @param arrayReference when {@code fresh == true} is a {@link Reference} to the array 
     *        where {@code valueToLoad} originates from.
     * @param objectPosition a {@code long}, the position in the heap of the object
     *        {@code referenceToResolve} refers to.
     * @param objectOrigin a {@link ReferenceSymbolic}, the origin of the object {@code referenceToResolve} 
     *        refers to.
     * @param branchNumber an {@code int}, the branch number.
     */
    public DecisionAlternative_XALOAD_Aliases(Expression arrayAccessExpression, ReferenceSymbolic referenceToResolve, boolean fresh,
                                              Reference arrayReference, long objectPosition, ReferenceSymbolic objectOrigin, int branchNumber) {
        super(ALT_CODE + "_Aliases:" + arrayAccessExpression + ":" + objectOrigin.asOriginString(), arrayAccessExpression, referenceToResolve, fresh, arrayReference, branchNumber);
        this.objectPosition = objectPosition;
        final int prime = 1733;
        int result = super.hashCode();
        result = prime * result +
            (int) (this.objectPosition ^ (this.objectPosition >>> 32));
        this.hashCode = result;
    }

    @Override
    public long getObjectPosition() {
        return this.objectPosition;
    }

    @Override
    public void accept(VisitorDecisionAlternative_XALOAD v) throws Exception {
        v.visitDecisionAlternative_XALOAD_Aliases(this);
    }

    @Override
    public int hashCode() {
        return this.hashCode;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        final DecisionAlternative_XALOAD_Aliases other =
        (DecisionAlternative_XALOAD_Aliases) obj;
        if (this.objectPosition != other.objectPosition) {
            return false;
        }
        return true;
    }
}
