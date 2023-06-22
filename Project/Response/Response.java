package Response;

public enum Response {
    OK(200, "OK"),
    CREATED(201, "Criado com Sucesso"),
    UNAUTHORIZED(401, "Procedimento nao Autorizado"),
    NOT_FOUND(406, "Dados nao Encontrado"),
    NOT_ACCEPTABLE(406, "Procedimento nao Aceito"),
    COMMON_ACCESS(700, "Bem Vindo ao Sistema"),
    MANAGER_ACCESS(701, "Bem Vindo ao Sistema");

    private final int value;
    private final String message;

    private Response(int value, String message) {
        this.value = value;
        this.message = message;
    }

    public int getValue() {
        return value;
    }

    public String getMessage() {
        return message;
    }
}
