package controller.validate;

public class NumberValidator implements Validator{
    @Override
    public boolean validate(String numberInput) {
        return numberInput.matches("^[1-9][0-9]{3}$");
    }
}
