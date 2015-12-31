package com.feisystems.bham.domain.decisionfacts;

import java.io.Serializable;

import org.codehaus.jackson.annotate.*;
@JsonIgnoreProperties({"version", "displayName", "codeSystemName", "codeSystemVersion", "originalText"})
public class ProcedureCode extends AbstractCodedConcept implements Serializable{
	private static final long serialVersionUID = 1L;

}
