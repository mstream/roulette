package io.mstream.roulette.output.format;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.function.Function;


@Component
public class TableTemplateBuilder implements ObjectToStringFormatter<Integer> {

	private final int nameColumnWidth;
	private final int otherColumnWidth;
	private final String placeholderTemplate = "%%-%ds";

	@Autowired
	public TableTemplateBuilder(
			@Value( "${view.format.nameColumnWidth}" )
			int nameColumnWidth,
			@Value( "${view.format.otherColumnWidth}" )
			int otherColumnWidth ) {
		this.nameColumnWidth = nameColumnWidth;
		this.otherColumnWidth = otherColumnWidth;
	}

	@Override public String apply( Integer columnsNumber ) {
		StringBuilder sb = new StringBuilder( );
		sb.append( String.format( placeholderTemplate, nameColumnWidth ) );
		for ( int i = 1; i < columnsNumber; i++ ) {
			sb.append( String.format( placeholderTemplate, otherColumnWidth ) );
		}
		return sb.toString( );
	}
}
