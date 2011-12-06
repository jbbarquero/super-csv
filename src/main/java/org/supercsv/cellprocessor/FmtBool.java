package org.supercsv.cellprocessor;

import org.supercsv.cellprocessor.ift.BoolCellProcessor;
import org.supercsv.cellprocessor.ift.StringCellProcessor;
import org.supercsv.exception.ClassCastInputCSVException;
import org.supercsv.util.CSVContext;

/**
 * Converts a Boolean into a formatted string. If you want to convert from a String to a Boolean, use the
 * {@link ParseBool} processor.
 * 
 * @since 1.50
 * @author Dominique De Vito
 */
public class FmtBool extends CellProcessorAdaptor implements BoolCellProcessor {
	
	private final String trueValue;
	private final String falseValue;
	
	/**
	 * Constructs a new <tt>FmtBool</tt> processor, which converts a Boolean into a formatted string.
	 * 
	 * @param trueValue
	 *            the String to use if the value is true
	 * @param falseValue
	 *            the String to use if the value is false
	 */
	public FmtBool(final String trueValue, final String falseValue) {
		super();
		this.trueValue = trueValue;
		this.falseValue = falseValue;
	}
	
	/**
	 * Constructs a new <tt>FmtBool</tt> processor, which converts a Boolean into a formatted string, then calls the
	 * next processor in the chain.
	 * 
	 * @param trueValue
	 *            the String to use if the value is true
	 * @param falseValue
	 *            the String to use if the value is false
	 * @param next
	 *            the next processor in the chain
	 */
	public FmtBool(final String trueValue, final String falseValue, final StringCellProcessor next) {
		super(next);
		this.trueValue = trueValue;
		this.falseValue = falseValue;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Object execute(final Object value, final CSVContext context) {
		validateInputNotNull(value, context, this);
		if( !(value instanceof Boolean) ) {
			throw new ClassCastInputCSVException("the value '" + value + "' is not of type Boolean", context, this);
		}
		String result = ((Boolean) value).booleanValue() ? trueValue : falseValue;
		return next.execute(result, context);
	}
}