package Database;

import java.util.HashMap;
import java.util.Map;

import Rules.Objects.Rule;
import Users.Objects.User;
import Users.Objects.Manager;
import Users.Objects.Common;

public class Database {
    public static Map<String, User> getUsers() {
        Map<String, User> users = new HashMap<>();

        users.put("admin", new Manager("admin", "Administrador", "admin"));
        users.put("admin2", new Manager("admin2", "Administrador 2", "admin2"));
        users.put("comum", new Common("comum", "Comum", "comum"));
        users.put("comum2", new Common("comum2", "Comum 2", "comum2"));

        return users;
    }

    public static Map<String, Rule> getRules() {
        Map<String, Rule> rules = new HashMap<>();

        Rule rule1 = new Rule("Idade", "O participante deve ser maior de idade");
        Rule rule2 = new Rule("Localidade", "O participante deve residir no Brasil");

        rules.put(rule1.getId(), rule1);
        rules.put(rule2.getId(), rule2);

        return rules;
    }
}