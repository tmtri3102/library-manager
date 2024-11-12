package controller.validate;

public class TextValidator implements Validator {
    @Override
    public boolean validate(String textInput) {
        return textInput.matches("^[A-Z][a-zA-Z0-9\\s]*$");
    }
}
