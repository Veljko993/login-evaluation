package eco.login.evaluation;

import eco.login.evaluation.validation.ValidOperation;

@ValidOperation
public class FilterDataDto {
    private String field;
    private String operation;
    private String value;
}
